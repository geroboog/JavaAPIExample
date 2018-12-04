package com.kangkai.utils;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kangkai.utils.UpTokenUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 七牛工具类
 * 
 * educate 空间域名 :o778fb40m.bkt.clouddn.com
 * @author Administrator
 *
 */
public class UploadUtils {
	Log log = LogFactory.getLog(this.getClass());

	// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
	UploadManager uploadManager = new UploadManager();

	// 上传内存中数据
	public void upload(File file, String key, String upToken) {
		try {
			Response res = uploadManager.put(file, key, upToken);
			if (res.isOK()) {
				// success
			} else {
				//
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时简单状态信息
			log.error(r.toString());
			try {
				// 响应的文本信息
				log.error(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	public void uploadFile(File file, String key, String upToken) {
		try {
			Response res = uploadManager.put(file, key, upToken);
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时简单状态信息
			log.error(r.toString());
			try {
				// 响应的文本信息
				log.error(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	public void uploadFile(File file, String key) {
		upload(file, key, UpTokenUtils.getUpToken0());
	}
	
	public void  uploadData(byte[] data,String key){
		try {
			Response res = uploadManager.put(data, key, UpTokenUtils.getUpToken0());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时简单状态信息
			log.error(r.toString());
			try {
				// 响应的文本信息
				log.error(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
}
