package com.kangkai.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	 /**
     * 搜索字符串失败返回的index常量
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 转换正则表达式特殊字符
     * 
     * @param content
     * @return String
     */
    public static String escapePattern(String content) {
        // TODO 这个不要使用正则表达式,或者要测试一下正则表达式的性能
        if(content == null){
            return null;
        }
        content = content.replaceAll("\\\\", "\\\\\\\\");
        content = content.replaceAll("\\?", "\\\\?");
        content = content.replaceAll("\\+", "\\\\+");
        content = content.replaceAll("\\*", "\\\\*");
        content = content.replaceAll("\\[", "\\\\[");
        content = content.replaceAll("\\]", "\\\\]");
        content = content.replaceAll("\\{", "\\\\{");
        content = content.replaceAll("\\}", "\\\\}");
        content = content.replaceAll("\\(", "\\\\(");
        content = content.replaceAll("\\)", "\\\\)");
        content = content.replaceAll("\\-", "\\\\-");
        content = content.replaceAll("\\$", "\\\\\\$");
        return content;
    }

    /**
     * 过滤特殊字符 遇到特殊字符就中断截取,值只能是[a-zA-Z0-9]
     * 
     * @param string
     * @return 没有该参数则返回空字符串，不返回null.
     */
    public String getSimpleString(String string) {
        if(string == null){
            return "";
        }
        String regex = "[a-zA-Z0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        if(m.find()){
            return m.group();
        }
        return "";
    }

    /**
     * 判断字符串是否为 null 或 空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 经过 trim 后是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为 null 和 空
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 判断是否是邮件地址
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if(isTrimEmpty(email)){
            return false;
        }
        return email.matches(RegexConstants.EMAIL_PATTEN);
    }

    /**
     * 验证是否是电话号码区号-电话或手机
     * 
     * @param telphone
     * @return
     */
    public static boolean isTelphone(String telphone) {
        if(isTrimEmpty(telphone)){
            return false;
        }
        return telphone.matches(RegexConstants.TELPHONE_PATTEN);
    }

    /**
     * 验证是否是邮政编码
     * 
     * @param zip
     * @return
     */
    public static boolean isZip(String zip) {
        if(isTrimEmpty(zip)){
            return false;
        }
        return zip.matches(RegexConstants.ZIP_PATTERN);
    }

    /**
     * 去除html文件的空白, 注释
     * 
     * @param html
     * @return
     */
    public static String compressHtml(String html) {
        // 去除注释<!-- -->
        if(html == null){
            return null;
        }

        html = html.replaceAll("<!--.*-->", " ");
        // 去除空格
        html = html.replaceAll(" {2,}", " ");
        // 去除回车
        html = html.replaceAll("[\\n\\t\\r]", "");
        return html;
    }

    /**
     * 判断是否是http/https协议的url
     * 
     * @param url
     * @return
     */
    public static boolean isHttpUrl(String url) {
        if(isTrimEmpty(url)){
            return false;
        }
        return url.matches(RegexConstants.HTTP_URL_PATTEN);
    }

    /**
     * 简单判断是否是静态文件
     * 
     * @param path
     * @return
     */
    public static boolean isMediaFile(String path) {
        if(isEmpty(path)){
            return false;
        }
        return path.matches(RegexConstants.STATIC_FILE_PATTEN);
    }

    /**
     * 生成模糊查询关键字
     * 
     * @param q
     * @return
     */
    public static String createLikeQ(String q) {
        if(isTrimEmpty(q)){
            return q;
        }
        // 转义sql like 查询的关键字%,_
        String likeQ = q.replaceAll("(%|_)", "\\\\$1");
        // 头尾加上%进行模糊查询
        return "%" + likeQ + "%";
    }

    /**
     * 是否是公有IP ipv4
     * 
     * @param ip
     * @return
     */
    public static boolean isPublicIp(String ip) {
        // 首先必须是合法IP
        if(!isIp(ip)){
            return false;
        }
            
        // 减去私有地址,回路127,本机(0.0.0.0),广播地址(255.255.255.255)
        // 私有地址
        // A:10.0.0.0--10.255.255.255
        // B:172.16.0.0--172.31.255.255
        // C:192.168.0.0--192.168.255.255
        String ex = "^10.*$|^192\\.168.*$|^172\\.16.*$|^0\\.0\\.0\\.0$|^255\\.255\\.255\\.255$|^127.*$";// 172直接去掉全部
        return !ip.matches(ex);
    }

    /**
     * 连接字符串
     * 
     * @param strings
     * @return
     */
    public static String buildStrings(String... strings) {
        StringBuilder sb = new StringBuilder();
        for(String string : strings){
            sb.append(string);
        }
        return sb.toString();
    }

    /**
     * 是否是合法ip,包括私有ip ipv4
     * 
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        if(StringUtil.isEmpty(ip)){
            return false;
        }
            
        return ip.matches(RegexConstants.IP_V4_PATTEN);
    }

    /**
     * 如果是String参数为null，返回空字符串，否则返回原字符串
     * 
     * @param str
     * @return
     */
    public static String defaultStr(String str) {
        return str == null ? "" : str;
    }

    /**
     * 返回介于给定的开始和结果字符串中间的字符串
     * 
     * @param str
     * @param open
     * @param close
     * @return
     */
    public static String substringBetween(String str, String open, String close) {
        if(str == null || open == null || close == null){
            return null;
        }
        int start = str.indexOf(open);
        if(start != INDEX_NOT_FOUND){
            int end = str.indexOf(close, start + open.length());
            if(end != INDEX_NOT_FOUND){
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * 返回给定的包含开始和结果字符串的子字符串
     * 
     * @param str
     * @param open
     * @param close
     * @return
     */
    public static String subStrIncludeEdges(String str, String open,
            String close) {
        if(str == null || open == null || close == null){
            return null;
        }
        try{
            int start = str.indexOf(open);
            if (start != INDEX_NOT_FOUND) {
                int end = str.indexOf(close, start + open.length());
                if (end != INDEX_NOT_FOUND) {
                    return str.substring(start, end + close.length());
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    /**
     * 删除给定的字符串里面的空白字符
     * 
     * StringUtil.deleteWhitespace(null) = null StringUtil.deleteWhitespace("")
     * = "" StringUtil.deleteWhitespace("abc") = "abc"
     * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
     * 
     * @param str
     * @return
     */
    public static String deleteWhitespace(String str) {
        if(isEmpty(str)){
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for(int i = 0; i < sz; i++){
            if(!Character.isWhitespace(str.charAt(i))){
                chs[count++] = str.charAt(i);
            }
        }
        if(count == sz){
            return str;
        }
        return new String(chs, 0, count);
    }

    /**
     * 判断是否是
     * 
     * @param url
     * @return
     */
    public static boolean isHttpImgUrl(String url) {
        if(isTrimEmpty(url)){
            return false;
        }
        return url.matches(RegexConstants.HTTP_IMG_URL_PATTEN);
    }

    /**
     * 判断是否是合法的手机号码
     * 
     * @param url
     * @return
     */
    public static boolean isCellphone(String cellphone) {
        if(isTrimEmpty(cellphone)){
            return false;
        }
        return cellphone.matches(RegexConstants.CELLPHONE_PATTEN);
    }

    // 优惠券面额只能是3，5，10，20，50，100
    public static boolean couponPriceMatch(int string) {
        List<Integer> regex = getPriceList();
        if(regex.contains(string)){
            return true;
        }
        return false;
    }

    // 验证数值
    public static boolean couponPriceMatch(int regex[], int price) {

        for(int i = 0; i < regex.length; i++){
            if(price == regex[i]){
                return true;
            }
        }
        return false;
    }

    // 暂时这样写，
    static List<Integer> regex = new ArrayList<Integer>();

    private static List<Integer> getPriceList() {
        if(regex == null || regex.size() == 0){
            regex.add(3);
            regex.add(5);
            regex.add(10);
            regex.add(20);
            regex.add(50);
            regex.add(100);
        }
        return regex;
    }
    
    public static void main(String[] args) {
		System.out.println(false&&true&&true);
	}
}
