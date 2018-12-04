package com.kangkai.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ModelObjFileReader {
	
	  private List<Double[]> bodyArr=new ArrayList<Double[]>();
	  private List<Double[]> hairArr=new ArrayList<Double[]>();
	  private List<Double[]> pantsArr=new ArrayList<Double[]>();
	  private  List<Double[]> braArr=new ArrayList<Double[]>();
	  private  List<Double[]> headArr=new ArrayList<Double[]>();
	  private List<Double[]> legArr=new ArrayList<Double[]>();
	  private List<Double[]> footArr=new ArrayList<Double[]>();
	  private List<Double[]> waistArr=new ArrayList<Double[]>();
	  private List<Double[]> eye_RArr=new ArrayList<Double[]>();
	  private List<Double[]> eye_LArr=new ArrayList<Double[]>();
      
	  private  List<String> bodyInfoArr=new ArrayList<String>();
	  private  List<String> hairInfoArr=new ArrayList<String>();
	  private  List<String> pantsInfoArr=new ArrayList<String>();
	  private  List<String> braInfoArr=new ArrayList<String>();
	  private  List<String> headInfoArr=new ArrayList<String>();
	  private List<String> legInfoArr=new ArrayList<String>();
      private  List<String> footInfoArr=new ArrayList<String>();
      private List<String> waistInfoArr=new ArrayList<String>();
      private  List<String> eye_RInfoArr=new ArrayList<String>();
      private  List<String> eye_LInfoArr=new ArrayList<String>();
	

	public  void readObjFile() 
	{
		File file;
		FileInputStream fileInputStream;
		String ObjectFileURL="E://girlbody.obj";
		file=new File(ObjectFileURL);
		if(file.isFile() && file.exists())
		{
				
				try {
					fileInputStream=new FileInputStream(file);//先查找文件
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				InputStreamReader reader = null;
				BufferedReader bufferedReader = null;
				try {
					reader = new InputStreamReader(
					        new FileInputStream(file),"GBK");
					bufferedReader = new BufferedReader(reader);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}//考虑到编码格式
				
				String str="";
	           
	            //头发数据
	            getObjectInfo(hairArr,hairInfoArr,"hair",bufferedReader);
				
				
				 //身体数据
				getObjectInfo(bodyArr,bodyInfoArr,"body",bufferedReader);
				
				
				 //短裤数据
				getObjectInfo(pantsArr,pantsInfoArr,"pants",bufferedReader);
				
				
				 //胸罩数据
				getObjectInfo(braArr,braInfoArr,"bra",bufferedReader);
				
				
				
				 //腿数据
				getObjectInfo(legArr,legInfoArr,"leg",bufferedReader);
				
				
				
				 //小腿数据
				getObjectInfo(footArr,footInfoArr,"foot",bufferedReader);
				
				
				 //腰围数据
				getObjectInfo(waistArr,waistInfoArr,"waist",bufferedReader);
				
				
				 //头部数据
				getObjectInfo(headArr,headInfoArr,"head",bufferedReader);
				
				 //眼数据
				getObjectInfo(eye_RArr,eye_RInfoArr,"eye_R",bufferedReader);
				getObjectInfo(eye_LArr,eye_LInfoArr,"eye_L",bufferedReader);
				
				tallerFun(10);
				biggerFun(1.1);
				FileWriter writer = null;
				 BufferedWriter bw = null;
				try {
					writer = new FileWriter("E://test2.Obj");//写文件
					bw = new BufferedWriter(writer);
					bw.write("# 3ds Max Wavefront OBJ Exporter v0.97b - (c)2007 guruware");
					bw.newLine();
					bw.write("# 创建的文件:04.02.2017 17:46:30");
					bw.newLine();
					bw.write("");
					bw.newLine();
					bw.write("mtllib girlbody.mtl");
					bw.newLine();
					bw.write("");
					bw.newLine();
					
					 //头发数据
					writeObjectInfo(hairArr,hairInfoArr,"hair",bw);
					
					
					 //身体数据
					writeObjectInfo(bodyArr,bodyInfoArr,"body",bw);
					
					
					 //短裤数据
					writeObjectInfo(pantsArr,pantsInfoArr,"pants",bw);
					
					
					 //胸罩数据
					writeObjectInfo(braArr,braInfoArr,"bra",bw);
					
					
					
					 //腿数据
					writeObjectInfo(legArr,legInfoArr,"leg",bw);
					
					
					
					 //小腿数据
					writeObjectInfo(footArr,footInfoArr,"foot",bw);
					
					
					 //腰围数据
					writeObjectInfo(waistArr,waistInfoArr,"waist",bw);
					
					
					 //头部数据
					writeObjectInfo(headArr,headInfoArr,"head",bw);
					//眼数据
					writeObjectInfo(eye_RArr,eye_RInfoArr,"eye_R",bw);
					writeObjectInfo(eye_LArr,eye_LInfoArr,"eye_L",bw);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					bufferedReader.close();
					reader.close();
					bw.close();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	

	private  void getObjectInfo(List<Double[]> objArr,List<String> objInfoArr,String objName,BufferedReader bufferedReader)
	{
		String str="";
		try {
			while((str = bufferedReader.readLine()) != null) 
			{
				if(str.contains("object "+objName))
				{
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
      
		try {
			while((str = bufferedReader.readLine()) != null) 
			{
				if(str.contains("object "+objName+"Info"))
				{
					break;
				}
				String[] dataArr=str.split(" ");
				Double[] data=new Double[3];
				data[0]=new Double(dataArr[2]);//X轴
				data[1]=new Double(dataArr[3]);//Z轴
				data[2]=new Double(dataArr[4]);//Y轴
				objArr.add(data);
			}
			while((str = bufferedReader.readLine()) != null) 
			{
				if(str.contains("object "+objName+"Info"))
				{
					break;
				}
				String str2=str;
				objInfoArr.add(str2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void writeObjectInfo(List<Double[]> objArr,List<String> objInfoArr,String objName,BufferedWriter bw)
	{
		try {
			bw.write("#");
			bw.newLine();
			bw.write("# object "+objName);
			bw.newLine();
			bw.write("#");
			bw.newLine();
			for(int i=0;i<objArr.size();i++)
			{
				bw.write("v  "+objArr.get(i)[0]+" "+objArr.get(i)[1]+" "+objArr.get(i)[2]);
				bw.newLine();
			}
			for(int i=0;i<objInfoArr.size();i++)
			{
				bw.write(objInfoArr.get(i));
				bw.newLine();
			}
			bw.write("");
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//增高算法
	private  void tallerFun(double tallerData)
	{
		List<Double[]> sortLegArr=sortArr(legArr,"z");
		Double[] smallestLegPoint=sortLegArr.get(0);
		List<Double[]> sortFootArr=sortArr(footArr,"z");
		Double[] smallestFootPoint=sortFootArr.get(0);
		dealTallFun(tallerData/2,footArr,smallestFootPoint[1],2);
		dealTallFun(tallerData/2,legArr);
		dealTallFun(tallerData/2,legArr,smallestLegPoint[1],10);
		dealTallFun(tallerData/2,bodyArr,smallestFootPoint[1],2);
		dealTallFun(tallerData/2,bodyArr,smallestLegPoint[1],2);
		dealTallFun(tallerData,hairArr);
		dealTallFun(tallerData,pantsArr);
		dealTallFun(tallerData,braArr);
		dealTallFun(tallerData,headArr);
		dealTallFun(tallerData,waistArr);
		dealTallFun(tallerData,eye_RArr);
		dealTallFun(tallerData,eye_LArr);
	}
	//变粗算法
	private  void biggerFun(double tallerData)
	{
		dealBiggerFun(tallerData,waistArr);
	}
	private void dealTallFun(double data,List<Double[]> arr)
	{
		for(int i=0;i<arr.size();i++)
		{
			arr.get(i)[1]=arr.get(i)[1]+data;
		}
	}
	private void dealBiggerFun(double data,List<Double[]> arr)
	{
		for(int i=0;i<arr.size();i++)
		{
			arr.get(i)[0]=arr.get(i)[0]*data;
			arr.get(i)[2]=arr.get(i)[2]*data;
		}
	}
	private void dealBiggerFun(double data,List<Double[]> arr,double pointSmall,double pointBig,int field)
	{
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i)[0]>pointSmall+field&&arr.get(i)[0]<pointSmall-field)
			{
				arr.get(i)[0]=arr.get(i)[0]*data;
				arr.get(i)[2]=arr.get(i)[2]*data;
			}
		}
	}
	//用于某个点以上的点移动一定的高度
	private void dealTallFun(double data,List<Double[]> arr,double point,int field)
	{
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i)[1]>(point+field))
			{
			arr.get(i)[1]=arr.get(i)[1]+data;
			}
		}
	}
	private List<Double[]> sortArr(List<Double[]> oldArr,String type)
	{
		List<Double[]> newArr=new ArrayList<Double[]>();
		newArr.addAll(oldArr);
		directInsertSort(newArr,type);
		return newArr;
		
	}
	 private  void directInsertSort(List<Double[]> newArr,String type) 
	 {
		 if(type.equals("x"))
		 {
			 Double[] sentinel =null;
			    int j;  
			    for (int i = 1; i < newArr.size(); i++) {  
			        j = i - 1;  
			        sentinel = newArr.get(i);//哨兵位  
			        while (j >= 0 && sentinel[0] < newArr.get(j)[0]) {  
			        	newArr.set(j+1, newArr.get(j));//将大于sentinel的值整体后移一个单位   
			            j--;  
			        }  
			        newArr.set(j+1, sentinel);  
			    }  
		 }
		 if(type.equals("y"))
		 {
			 Double[] sentinel =null;
			    int j;  
			    for (int i = 1; i < newArr.size(); i++) {  
			        j = i - 1;  
			        sentinel = newArr.get(i);//哨兵位  
			        while (j >= 0 && sentinel[2] < newArr.get(j)[2]) {  
			        	newArr.set(j+1, newArr.get(j));//将大于sentinel的值整体后移一个单位   
			            j--;  
			        }  
			        newArr.set(j+1, sentinel);  
			    }  
		 }
		 if(type.equals("z"))
		 {
			    Double[] sentinel =null;
			    int j;  
			    for (int i = 1; i < newArr.size(); i++) {  
			        j = i - 1;  
			        sentinel = newArr.get(i);//哨兵位  
			        while (j >= 0 && sentinel[1] < newArr.get(j)[1]) {  
			        	newArr.set(j+1, newArr.get(j));//将大于sentinel的值整体后移一个单位   
			            j--;  
			        }  
			        newArr.set(j+1, sentinel);  
			    }  
		 }
	}
	 
	public static void main(String[] args) 
	{
		ModelObjFileReader modelObjFileReade=new ModelObjFileReader();
		modelObjFileReade.readObjFile();
	}
}
