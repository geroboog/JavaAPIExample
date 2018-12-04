package com.kangkai.utils;

import com.kangkai.utils.ScalarConstants;

/**
 * 数字相关操作类
 * 
 * @author tgf(2008-12-23)
 * 
 */
public class NumberUtil {
	 /**
     * 判断是否全是数字(没有正负号，没有小数点)
     * 
     * @param value
     * @return
     */
    public static boolean isDigits(String value) {
        String pattern = "^\\d+$";
        return value == null ? false : value.matches(pattern);
    }

    /**
     * 判断是否是整数
     * 
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        String pattern = "^[+-]?\\d+$";
        return value == null ? false : value.matches(pattern);
    }

    /**
     * 判断是否是浮点数
     * 
     * @param value
     * @return
     */
    public static boolean isFloat(String value) {
        String pattern = "^[+-]?\\d+\\.\\d+";
        return value == null ? false : value.matches(pattern);
    }

    /**
     * 判断是否是数字,包括小数
     * 
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        if(value == null){
            return false;
        }
            
        value = value.trim();
        String pattern = "^[+-]?\\d+(?:\\.\\d+|)$";
        return value.matches(pattern);
    }

    /**
     * 是否是正数, 包括0
     * 
     * @param value
     * @return
     */
    public static boolean isPositiveNumber(String value) {
        String pattern = "^[+]?\\d+(?:\\.\\d+|)$";
        return value == null ? false : value.matches(pattern);
    }

    /**
     * 是否是负数,包括-0
     * 
     * @param value
     * @return
     */
    public static boolean isNegativeNumber(String value) {
        String pattern = "^\\-\\d+(?:\\.\\d+|)$";
        return value == null ? false : value.matches(pattern);
    }

    /**
     * 将字符串转换为浮点数,返回无限小double值
     * 
     * @param str
     * @return
     */
    public static double parseDouble(String str) {
        return parseDouble(str, ScalarConstants.INVALID_DOUBLE);
    }

    /**
     * 将字符串转换为浮点数,无效则返回defaultValue值
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static double parseDouble(String str, double defaultValue) {
        try{
            double val = Double.parseDouble(str);
            return val;
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为整数,返回无限小int值
     * 
     * @param str
     * @return
     */
    public static int parseInt(String str) {
        return parseInt(str, ScalarConstants.INVALID_INT);
    }

    /**
     * 将字符串转换为整数,无效则返回defaultValue值
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static int parseInt(String str, int defaultValue) {
        try{
            int val = Integer.parseInt(str);
            return val;
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为long类型,返回无限小long值
     * 
     * @param str
     * @return
     */
    public static long parseLong(String str) {
        return parseLong(str, ScalarConstants.INVALID_LONG);
    }

    /**
     * 将字符串转换为long类型,无效则返回defaultValue值
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static long parseLong(String str, long defaultValue) {
        try{
            long val = Long.parseLong(str);
            return val;
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为float类型,返回无限小float值
     * 
     * @param str
     * @return
     */
    public static float parseFloat(String str) {
        return parseFloat(str, ScalarConstants.INVALID_FLOAT);
    }

    /**
     * 将字符串转换为float类型,无效则返回defaultValue值
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static float parseFloat(String str, float defaultValue) {
        try{
            float val = Float.parseFloat(str);
            return val;
        }catch (Exception e){
            return defaultValue;
        }
    }
}
