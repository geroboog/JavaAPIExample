package com.kangkai.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArrUtils {
	
	 public ArrUtils() {
		// TODO Auto-generated constructor stub
	}
	
	 public static Integer[] StrArrToInteger(String questionStr)
	 {
		 try
		 {
		 String[] questionStrArr=questionStr.split(",");
			Integer[] questionList=new Integer[questionStrArr.length];
			for(int i=0;i<questionStrArr.length;i++)
			{
				questionList[i]=new Integer(questionStrArr[i]);
			}
			return questionList;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }
	 public static String IntegerArrtoString(Integer[] questionId)
	 {
		 try
		 {
		 String questionStr="";
			for(int i=0;i<questionId.length;i++)
			{
				if(i!=0)
				{
					questionStr+=",";
				}
				questionStr+=questionId[i].toString();
			}
			return questionStr;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }
	 
	 public static String StringArrtoString(String[] questionId)
	 {
		 try
		 {
		 String questionStr="";
			for(int i=0;i<questionId.length;i++)
			{
				if(i!=0)
				{
					questionStr+=",";
				}
				questionStr+=questionId[i];
			}
			return questionStr;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }
	 public static String StringArrtoStringWithSort(String[] questionId,String sort)
	 {
		 try
		 {
		 String questionStr="";
			for(int i=0;i<questionId.length;i++)
			{
				if(i!=0)
				{
					questionStr+=",";
				}
				questionStr+=sort+questionId[i]+sort;
			}
			return questionStr;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }
	 public static String IntegerArrtoStringAdd(Integer[] questionId,Integer addQuestionId)
	 {
		 try
		 {
		 String questionStr="";
			for(int i=0;i<questionId.length;i++)
			{
				if(i!=0)
				{
					questionStr+=",";
				}
				questionStr+=questionId[i].toString();
			}
			questionStr+=",";
			questionStr+=addQuestionId.toString();
			return questionStr;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }
	 public static String IntegerArrtoStringSub(Integer[] questionId)
	 {
		 try
		 {
		 String questionStr="";
			for(int i=0;i<questionId.length-1;i++)
			{
				if(i!=0)
				{
					questionStr+=",";
				}
				questionStr+=questionId[i].toString();
			}
			return questionStr;
		 }catch(Exception ex)
		 {
			 return null;
		 }
	 }

	public static List<String> IntegerListtoStringList(List<Integer> companyList) {
		List<String> companyStrList=new ArrayList<String>();
 		try
		{
			for(int i=0;i<companyList.size();i++)
			{
				companyStrList.add(""+companyList.get(i));
			}
			return companyStrList;
		}
		catch(Exception ex)
		{
			return null	;
		}
 		
	}
	public static List<String> IntegerListtoStringList(List<Integer> companyList,String sort) {
		List<String> companyStrList=new ArrayList<String>();
 		try
		{
			for(int i=0;i<companyList.size();i++)
			{
				companyStrList.add(sort+companyList.get(i));
			}
			return companyStrList;
		}
		catch(Exception ex)
		{
			return null	;
		}
 		
	}
	
	public static boolean isDateOneSmallerThanDateTwo(Date dateOne,Date dateTwo,int second) {
		if((dateOne.getTime()-dateTwo.getTime())<second*1000)
		{
			return true;
		}else
		{
			return false;
		}
 		
	}
	
}
