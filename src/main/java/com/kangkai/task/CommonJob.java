package com.kangkai.task;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.kangkai.utils.Constants;
import com.kangkai.utils.DateUtils;
import com.kangkai.utils.MySQLDatabaseBackup;
import com.kangkai.utils.SFTPUtils;

public class CommonJob {

	public void work() throws SftpException {
		// 备份数据库，获得备份文件路径
		String file = MySQLDatabaseBackup.backup(Constants.DB_HOST_IP, Constants.DB_USERNAME, Constants.DB_PASSWORD,
				Constants.DATABASE);
		if (file != null) {
			SFTPUtils sf = new SFTPUtils();
			String directory = Constants.BACKUP_REMOTE_PATH + DateUtils.getFormatedDate("yyyyMM") + "/";

			String uploadFile = file;
			ChannelSftp sftp = sf.connect(Constants.SFTP_HOST_IP, Constants.SFTP_HOST_PORT,
					Constants.SFTP_HOST_USERNAME, Constants.SFTP_HOST_PASSWORD);
			try {
				Vector content = sftp.ls(directory);
				if (content == null) {
					sftp.mkdir(directory);
				}
			} catch (SftpException e) {
				sftp.mkdir(directory);
			}
			sf.upload(directory, uploadFile, sftp);
			System.out.println("finished");
		}
	}
}
