package com.kangkai.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.junit.Test;

/**
 * MySQL数据库备份
 * 
 */
public class MySQLDatabaseBackup {

	@Test
	public void fun() {
		System.out.println("开始备份...");
		backup(Constants.DB_HOST_IP, Constants.DB_USERNAME, Constants.DB_PASSWORD, Constants.DATABASE);
		System.out.println("备份成功...");

		// System.out.println("开始还原...");
		// load1();
		// System.out.println("还原成功...");
	}

	public static String backup(String hostIp, String username, String password, String database) {
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 调用mysql的安装目录的命令
			// windows下本地备份
			// Process child =
			// rt.exec("D://program//mysql5.7.12//mysql-5.7.12-winx64//bin//mysqldump
			// -h " + hostIp + " -u"
			// + username + " -p" + password + " " + database);
			// linux下备份
			Process child = rt.exec("mysqldump -h " + hostIp + " -u" + username + " -p" + password + " " + database);
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			String formatedDate = DateUtils.getFormatedDate("yyyyMM");
			int day = new Date().getDay();
			String filePath = Constants.BACKUP_LOCAL_PATH + formatedDate;
			File saveFile = new File(filePath);
			if (!saveFile.exists()) {// 如果目录不存在
				saveFile.mkdirs();// 创建文件夹
			}
			String fileName = day + ".sql";
			FileOutputStream fout = new FileOutputStream(filePath + "/" + fileName);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			return filePath + "/" + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static void load() { try { String fPath = "c:/test.sql"; Runtime
	 * rt = Runtime.getRuntime(); // 调用 mysql 安装目录的命令 Process child = rt.exec(
	 * "C://Program Files//MySQL//MySQL Server 5.1//bin//mysql -u root -p root dlgs_test"
	 * );
	 * 
	 * OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流 String inStr;
	 * StringBuffer sb = new StringBuffer(""); String outStr; BufferedReader br
	 * = new BufferedReader(new InputStreamReader(new FileInputStream(fPath),
	 * "utf-8")); while ((inStr = br.readLine()) != null) { sb.append(inStr +
	 * "\r\n"); } outStr = sb.toString(); System.out.println(outStr);
	 * OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
	 * System.out.println("7777777777777777777777777777777777777");
	 * writer.write(outStr);
	 * System.out.println("888888888888888888888888888888888888888");
	 * writer.flush(); out.close(); br.close(); writer.close();
	 * System.out.println(""); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * public static void load1() { try { String fPath = "c:/test.sql"; Runtime
	 * rt = Runtime.getRuntime();
	 * 
	 * // 调用 mysql 的 cmd: Process child = rt .exec(
	 * "C://Program Files//MySQL//MySQL Server 5.1//bin//mysql.exe -uroot -proot dlgs_test "
	 * ); OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流 String
	 * inStr; StringBuffer sb = new StringBuffer(""); String outStr;
	 * BufferedReader br = new BufferedReader(new InputStreamReader(new
	 * FileInputStream(fPath), "utf8")); while ((inStr = br.readLine()) != null)
	 * { sb.append(inStr + "\r\n"); } outStr = sb.toString();
	 * 
	 * OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
	 * writer.write(outStr); // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
	 * writer.flush(); // 别忘记关闭输入输出流 out.close(); br.close(); writer.close();
	 * 
	 * System.out.println("");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
}