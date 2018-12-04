package com.kangkai.controller;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kangkai.utils.UploadUtils;
import com.kangkai.service.appService.IUserService;
import com.kangkai.utils.Constants;
import com.kangkai.utils.Json;
import com.kangkai.utils.QiNiuTokenUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/qiniu")
public class UploadController {

	// 获取日志工具
	Log log = LogFactory.getLog(this.getClass());
	@Resource
	private IUserService userService;
	/**
	 * 获得上传凭证
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	@RequestMapping(value = "uploadToken")
	@ResponseBody
	public Json uploadToken(@RequestBody JSONObject data) throws InvalidKeyException, NoSuchAlgorithmException {
		Json json = new Json();
		Integer userId;
		String token;
		log.info("请求执行uploadToken方法,请求的数据:" + data);
		try {
			userId = data.getInt("userId");
			token=data.getString("token");
		} catch (Exception e) {
			e.printStackTrace();
			json.setCode(0);
			json.setData(null);
			json.setMsg("请求的参数不合法");
			log.error("请求的参数不合法，错误信息:" + e.getMessage());
			return json;
		}
		json=userService.validateToken(userId, token);
		if(json.getCode()==100)
		{
		String tokenupload = QiNiuTokenUtils.tokenupload();
		json.setData(tokenupload);
		}
		return json;
	}

	
	/**
	 * 上传文件
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam(value = "filedata", required = false) MultipartFile file,HttpServletResponse response) throws IOException {
		Json json = new Json();
		log.info("请求执行uploadUserIcon方法,请求的数据:"+file);
		String result="";
		String error="";
		response.setCharacterEncoding("utf-8");
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			if(!(suffix.equals("bmp")||suffix.equals("gif")||suffix.equals("jpeg")||suffix.equals("tiff")||suffix.equals("png")||suffix.equals("svg")||suffix.equals("jpg")||suffix.equals("emf")||suffix.equals("lic")||suffix.equals("eps")||suffix.equals("tga"))){
				error="您上传的图片格式有误";
			}
			String saveName = UUID.randomUUID().toString() + "." + suffix;
			CommonsMultipartFile cf = (CommonsMultipartFile) file; // 这个myfile是MultipartFile的
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File _file = fi.getStoreLocation();
			new UploadUtils().uploadFile(_file, saveName);
			result=Constants.QINIU_URL_PREFIX+saveName;
		}catch(Exception e)	
		{
			error="上传失败";
		}
		String returnData = "{\"err\":\"" + error + "\",\"msg\":\"" + result + "\"}";
		response.getWriter().println(returnData);
	}
}
