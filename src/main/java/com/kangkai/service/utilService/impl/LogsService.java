package com.kangkai.service.utilService.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.kangkai.dao.DaoSupport;
import com.kangkai.mapper.util.LogsMapper;
import com.kangkai.pojo.Logs;
import com.kangkai.service.utilService.ILogsService;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.PageData;

/** 
 * 说明： 修改或查看公司资料
 * 创建人：FH Q287356385
 * 创建时间：2016-09-06
 * @version
 */
@Service("logsService")
public class LogsService implements Runnable ,ILogsService{

	private String content;
	private Integer userId=null;
	private Integer adminId=null;
	private String tableName="";
	
	@Resource(name = "logsMapper")
	private LogsMapper logsMapper;

	@Override
	public void run() {
		Logs logs=new Logs();
		logs.setContent(content);
		logs.setUserId(userId);
		logs.setAdminId(adminId);
		logs.setTableName(tableName);
		logsMapper.insert(logs);
	}
	
	@Override
	public Boolean checkTable() {
		String tableName=getCurrentTableName();
		Integer num=logsMapper.existTable(tableName);
		if(num>0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	@Override
	public void createTable() {
		String tableName=getCurrentTableName();
		logsMapper.createLogTable(tableName);
		logsMapper.alterLogTable(tableName);
	}
	private String getCurrentTableName()
	{
		String tableName="sys_log"+DateUtils.getJFPTime();
		return tableName;
	}
	
	@Override
	public void writeLogsForUser(String content, Integer userId) {
		this.content=content;
		this.userId=userId;
		this.adminId=null;
		this.tableName=getCurrentTableName();
		run();
	}
	@Override
	public void writeLogsForAdmin(String content, Integer adminId) {
		this.content=content;
		this.userId=null;
		this.adminId=adminId;
		this.tableName=getCurrentTableName();
		run();
	}
	
	
	
	
}

