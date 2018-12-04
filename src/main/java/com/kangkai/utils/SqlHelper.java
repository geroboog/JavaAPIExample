package com.kangkai.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class SqlHelper {
	private String sqlType;
	private String tableName;
	private String sqlStr;
	private ArrayList<String> selectValueList;
	private HashMap<String,Object> insertValueMap;
	private ArrayList<String> whereList;
	private String orderByStr;
	private Integer begin;
	private Integer pageSize;
	public SqlHelper() {
		begin=0;
		pageSize=0;
		sqlType="";
		tableName="";
		sqlStr="";
		orderByStr="";
		selectValueList=new ArrayList<String>();
		insertValueMap=new HashMap<String,Object>();
		whereList=new ArrayList<String>();
	}
	
	
	public void setInsertValueMap(String key,Object value) {
		this.insertValueMap.put(key, value);
	}



	public void setselectValueList(String value) {
		this.selectValueList.add(value);
	}


	public void setwhereList(String value) {
		this.whereList.add(value);
	}


	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getSqlStr()
	{
		return sqlStr;
	}
	public void generateSqlStr()
	{
		try
		{
			if(sqlType.trim().equals("select"))//实例化查询语句
			{
				sqlStr="select";	
				if(selectValueList.size()==0)
				{
					sqlStr+=" * ";
				}else
				{
					for (int i=0;i<selectValueList.size();i++) 
					{
						if(i!=0)
						{
							sqlStr+=" , ";
						}
						sqlStr+="'"+selectValueList.get(i)+"'";
					}
					sqlStr+="";
				}
				
				sqlStr+=" from "+"t_"+tableName;
				
				if(whereList.size()!=0)
				{
					sqlStr+=" where ";
					
					for (int i=0;i<whereList.size();i++) 
					{
						if(i!=0)
						{
							sqlStr+=" ";
						}
						sqlStr += whereList.get(i);
					}
				}
				
				if(orderByStr!="")
				{
					sqlStr +=" "+orderByStr+" ";
				}
				if(begin!=0)
				{
					sqlStr+=" limit "+begin+" , "+pageSize+" ";
				}
				
			}else if(sqlType.trim().equals("insert"))
			{
				sqlType.trim().equals("select");
			}
			
		}catch(Exception ex)
		{
			System.out.println("请先实例化Sql语句");
		}
	}



	public Integer getBegin() {
		return begin;
	}



	public void setBegin(Integer begin) {
		this.begin = begin;
	}



	public Integer getPageSize() {
		return pageSize;
	}



	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
