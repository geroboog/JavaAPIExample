package  com.kangkai.utils;

import java.util.Properties;


public class PropsConfigReader {
	 private Properties props;

	    public PropsConfigReader(String propertiesFile) {
	        props = ResourceUtil.loadProperties(propertiesFile);
	    }

	    /**
	     * return the propsperties instance
	     * 
	     * @param key
	     * @return
	     */
	    public Properties getPropeties() {
	        return this.props;
	    }

	    public String getString(String key) {
	        return props.getProperty(key);
	    }

	    public int getInt(String key) {
	        String value = getString(key);
	        return NumberUtil.parseInt(value);
	    }

	    public long getLong(String key) {
	        String value = getString(key);
	        return NumberUtil.parseLong(value);
	    }

	    public boolean getBoolean(String key) {
	        String value = getString(key);
	        return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
	    }
}
