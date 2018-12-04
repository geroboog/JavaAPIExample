package com.kangkai.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {
	public static double add(double d1, double d2)
	{        // 进行加法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}
	
	
	public static double sub(double d1, double d2)
	{        // 进行减法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}
	
	public static double mul(double d1, double d2)
	{        // 进行乘法运算
		
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		BigDecimal bg = new BigDecimal(b1.multiply(b2).doubleValue()).setScale(0, RoundingMode.UP);

		return  bg.doubleValue();
	}
	
	public static double div(double d1,
			double d2,int len) {// 进行除法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2,len,BigDecimal.
				ROUND_HALF_UP).doubleValue();
	}

	public static double round(double d,
			int len) {     // 进行四舍五入操作
		
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len,BigDecimal.
				ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		double a=100;
		double b=0.01;
		double c=BigDecimalUtil.add(a, b);
		System.out.println(c);
	}
}
