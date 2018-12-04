package com.kangkai.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;

public class QiNiuTokenUtils {

	public static String ACCESS_KEY = Constants.QINIU_ACCESS_KEY;
	public static String SECRET_KEY = Constants.QINIU_SECRET_KEY;
	public static String bucket = Constants.QINIU_BUCKET;

	/**
	 * 上传凭证
	 * 
	 * @param fileName
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String tokenupload() throws NoSuchAlgorithmException, InvalidKeyException {

		String putpolicy = "{\"scope\":\""+bucket+"\",\"deadline\":" + System.currentTimeMillis() / 1000L + 3600 + "}";

		String t3 = UrlSafeBase64.encodeToString(putpolicy);
		Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY), "HmacSHA1"));
		String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(t3)));
		return ACCESS_KEY + ":" + t2 + ":" + t3;
	}

	/**
	 * 下载凭证
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String tokendownload(String DownloadUrl) throws NoSuchAlgorithmException, InvalidKeyException {

		// String DownloadUrl =
		// "http://7xl4ck.com1.z0.glb.clouddn.com/photo1.jpg?e=1637846514";
		Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY), "HmacSHA1"));
		String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(DownloadUrl)));

		return DownloadUrl + "&token=" + ACCESS_KEY + ":" + t2;
	}

	/**
	 * 管理凭证
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String tokenmanage() throws NoSuchAlgorithmException, InvalidKeyException {

		// 删除方法
		String signingStr = "/delete/Y3BwZGVtbzpwaG90by0y\n";
		// fetch方法
		// String signingStr =
		// "/fetch/aHR0cHM6Ly9zY29udGVudC5jZG5pbnN0YWdyYW0uY29tL2hwaG90b3MteGFmMS90NTEuMjg4NS0xNS9lMTUvMTEzODAyOTRfMTU5MDk3MDMwNzgyOTc1Nl8xNTQ5OTUzOTE5X24uanBn/to/cGhwZGVtbzpmZXRjaC5qcGc=\n";
		Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY), "HmacSHA1"));
		String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(signingStr)));

		return ACCESS_KEY + ":" + t2;
	}

	/**
	 * 同步saveas生成签名链接
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String tokendownload() throws NoSuchAlgorithmException, InvalidKeyException {

		String urlbase64 = UrlSafeBase64.encodeToString("目标bucket:目标key");
		String DownloadUrl = "domain/key?imageView2/../..|saveas/" + urlbase64;
		// 例如可以参考下面的例子,注意domain前面没有http://
		// String DownloadUrl =
		// "7xrnxn.com1.z0.glb.clouddn.com/QQ20160402-2.png?imageMogr2/crop/!300x300a10a10|saveas/"
		// + urlbase64;

		Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY), "HmacSHA1"));
		String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(DownloadUrl)));

		// 打印加好凭证的url
		return DownloadUrl + "/sign/" + ACCESS_KEY + ":" + t2;
	}

	/**
	 * 刷新凭证
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String refreshToken() throws NoSuchAlgorithmException, InvalidKeyException {

		String signingStr = "/refresh\n";

		Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY), "HmacSHA1"));
		String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(signingStr)));

		return ACCESS_KEY + ":" + t2;
	}

	@Test
	public void show() throws InvalidKeyException, NoSuchAlgorithmException {
		String tokenupload = tokenupload();
		System.out.println(tokenupload);
	}
}