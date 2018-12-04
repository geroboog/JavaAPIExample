package com.kangkai.utils;

import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * 七牛获取令牌工具
 * 
 * @author Administrator
 *
 */
public class UpTokenUtils {
	public static String ACCESS_KEY = Constants.QINIU_ACCESS_KEY;
	public static String SECRET_KEY = Constants.QINIU_SECRET_KEY;
	
	public static String bucket = Constants.QINIU_BUCKET;

	public static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	public static BucketManager bucketManager = new BucketManager(auth);

	// 简单上传，使用默认策略
	public static String getUpToken0() {
		return auth.uploadToken(bucket);
	}

	// 覆盖上传
	public static String getUpToken1() {
		return auth.uploadToken(bucket, "key");
	}

	// 设置指定上传策略
	public static String getUpToken2() {
		return auth.uploadToken(bucket, null, 3600, new StringMap().put("callbackUrl", "call back url")
				.putNotEmpty("callbackHost", "").put("callbackBody", "key=$(key)&hash=$(etag)"));
	}

	// 设置预处理、去除非限定的策略字段
	public static String getUpToken3() {
		return auth.uploadToken(bucket, null, 3600, new StringMap().putNotEmpty("persistentOps", "")
				.putNotEmpty("persistentNotifyUrl", "").putNotEmpty("persistentPipeline", ""), true);
	}

	/**
	 * 生成上传token
	 *
	 * @param bucket
	 *            空间名
	 * @param key
	 *            key，可为 null
	 * @param expires
	 *            有效时长，单位秒。默认3600s
	 * @param policy
	 *            上传策略的其它参数，如 new StringMap().put("endUser",
	 *            "uid").putNotEmpty("returnBody", "")。 scope通过
	 *            bucket、key间接设置，deadline 通过 expires 间接设置
	 * @param strict
	 *            是否去除非限定的策略字段，默认true
	 * @return 生成的上传token
	 */
	public static String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict) {
		return null;
	}

}
