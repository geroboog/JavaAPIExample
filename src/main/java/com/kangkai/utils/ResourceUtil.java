package com.kangkai.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import javax.imageio.IIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResourceUtil {
	/** logger */
    public static Logger logger = LoggerFactory
            .getLogger(ResourceUtil.class);

    /** classpath prefix */
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    /** URL prefix for loading from the file system: "file:" */
    public static final String FILE_URL_PREFIX = "file:";

    /** URL protocol for a file in the file system: "file" */
    public static final String URL_PROTOCOL_FILE = "file";

    /** URL protocol for an entry from a jar file: "jar" */
    public static final String URL_PROTOCOL_JAR = "jar";

    /** URL protocol for an entry from a zip file: "zip" */
    public static final String URL_PROTOCOL_ZIP = "zip";

    /** URL protocol for an entry from a WebSphere jar file: "wsjar" */
    public static final String URL_PROTOCOL_WSJAR = "wsjar";

    /** URL protocol for an entry from an OC4J jar file: "code-source" */
    public static final String URL_PROTOCOL_CODE_SOURCE = "code-source";

    /** Separator between JAR URL and file path within the JAR */
    public static final String JAR_URL_SEPARATOR = "!/";

    /**
     * get a ClassLoader
     * 
     * @return
     */
    public static ClassLoader getClassLoader() {
        ClassLoader cl = null;
        cl = Thread.currentThread().getContextClassLoader();
        if(cl == null){
            // No thread context class loader, use class loader of this class.
            cl = ResourceUtil.class.getClassLoader();
        }
        return cl;
    }

    /**
     * get config file name as URL
     * 
     * @param configFileName
     * @return
     * @throws Exception
     */
    public static URL getUrl(String configFileName) {
        if(StringUtil.isEmpty(configFileName)){
            return null;
        }

        if(configFileName.startsWith(CLASSPATH_URL_PREFIX)){
            configFileName = configFileName.substring(CLASSPATH_URL_PREFIX
                    .length());
        }

        URL url = getClassLoader().getResource(configFileName);
        if(url == null){
            String message = configFileName + " cannot be found";
            logger.error(message);
        }
        return url;
    }

    /**
     * is jar URL
     * 
     * @param url
     * @return
     */
    public static boolean isJarURL(URL url) {
        String protocol = url.getProtocol();
        return (URL_PROTOCOL_JAR.equals(protocol)
                || URL_PROTOCOL_ZIP.equals(protocol)
                || URL_PROTOCOL_WSJAR.equals(protocol) || (URL_PROTOCOL_CODE_SOURCE
                .equals(protocol) && url.getPath().indexOf(JAR_URL_SEPARATOR) != -1));
    }

    public static URL extractJarFileURL(URL jarUrl) {
        String urlFile = jarUrl.getFile();
        int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
        if(separatorIndex != -1){
            String jarFile = urlFile.substring(0, separatorIndex);
            try{
                return new URL(jarFile);
            }catch (MalformedURLException ex){
                // Probably no protocol in original jar URL, like
                // "jar:C:/mypath/myjar.jar".
                // This usually indicates that the jar file resides in the file
                // system.
                if(!jarFile.startsWith("/")){
                    jarFile = "/" + jarFile;
                }
                try{
                    return new URL(FILE_URL_PREFIX + jarFile);
                }catch (MalformedURLException e){
                    String message = "error happened when extractJarFileURL";
                    logger.error(message, e);
                    return null;
                }
            }
        }else{
            return jarUrl;
        }
    }

    /**
     * get config file name as File
     * 
     * @param file
     * @return
     */
    public static File getFile(String configFileName) {
        URL url = getUrl(configFileName);
        if(url == null){
            return null;
        }
        URI uri = toURI(url);
        return new File(uri.getSchemeSpecificPart());
    }

    /**
     * get config file as InputStream
     * 
     * @param configFileName
     * @return
     * @throws Exception
     */
    public static InputStream getFileAsStream(String configFileName) {
        if(StringUtil.isEmpty(configFileName)){
            return null;
        }

        String path = configFileName;
        if(configFileName.startsWith(CLASSPATH_URL_PREFIX)){
            path = configFileName.substring(CLASSPATH_URL_PREFIX.length());
        }
        URL url = getUrl(path);
        if(url == null){
            return null;
        }
        if(isJarURL(url)){
            url = extractJarFileURL(url);
            if(url == null){
                return null;
            }
            JarFile currentJar = null;
            try{
                currentJar = new JarFile(toURI(url).getSchemeSpecificPart());
                JarEntry dbEntry = currentJar.getJarEntry(path);
                return currentJar.getInputStream(dbEntry);
            }catch (IOException e){
                return null;
            }
        }

        try{
            return new FileInputStream(new File(toURI(url)
                    .getSchemeSpecificPart()));
        }catch (FileNotFoundException e){
            return null;
        }
    }

    public static URI toURI(URL url) {
        try{
            return new URI(url.toString().replaceAll(" ", "%20"));
        }catch (URISyntaxException e){
            String message = "error happen when change URL to URI";
            logger.error(message, e);
            return null;
        }
    }

    /**
     * get config files seperated by ',' as File[]
     * 
     * @param configFile
     *            seperated by ','
     * @return
     */
    public static File[] getResources(String configFiles) {
        if(StringUtil.isTrimEmpty(configFiles)){
            return null;
        }
        String[] configFileArray = configFiles.split("\\s*,\\s*");
        if(configFileArray == null || configFileArray.length == 0){
            return null;
        }
        List<File> fileList = new ArrayList<File>();
        for(int i = 0; i < configFileArray.length; i++){
            String configFile = configFileArray[i];
            if(configFile.indexOf("*") >= 0){
                String config = configFile;
                if(configFile.startsWith(CLASSPATH_URL_PREFIX)){
                    config = configFile
                            .substring(CLASSPATH_URL_PREFIX.length());
                }
                int pos = config.lastIndexOf("/");
                String path = null;
                String fileName = null;
                File searchPath = null;

                if(pos >= 0){
                    path = config.substring(0, pos);
                    searchPath = getFile(path);
                    fileName = config.substring(pos + 1);
                }else{
                    searchPath = getFile("./");
                    fileName = config;
                }
                String s = fileName.replace('.', '#');
                s = s.replaceAll("#", "\\\\.");
                s = s.replace('*', '#');
                s = s.replaceAll("#", ".*");
                s = s.replace('?', '#');
                s = s.replaceAll("#", ".?");
                s = "^" + s + "$";
                final Pattern pattern = Pattern.compile(s);

                File[] files = searchPath.listFiles(new FilenameFilter() {

                    public boolean accept(File dir, String name) {

                        return pattern.matcher(name).find();
                    }

                });
                if(files != null){
                    for(File f : files){
                        fileList.add(f);
                    }
                }
            }else{
                fileList.add(getFile(configFile));
            }
        }
        return fileList.toArray(new File[fileList.size()]);
    }

    /**
     * 加载properties文件
     * 
     * @param configFile
     * @return
     */
    public static Properties loadProperties(String configFile) {
        Properties props = new Properties();
        InputStream stream = null;
        try{
            stream = ResourceUtil.getFileAsStream(configFile);
            props.load(stream);
        }catch (Exception e){
            // re-throw
            String message = "errors occured when reading properties file: "
                    + configFile;
				throw new IORuntimeException(message, e);
			
        }finally{
            if(stream != null){
                try{
                    stream.close();
                }catch (IOException e){
                    // nothing to do
                }
            }
        }

        return props;
    }
}
