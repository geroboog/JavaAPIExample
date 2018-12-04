package com.kangkai.utils;

/**
 * 常量
 * 
 * @author weidoukeji
 *
 */
public class Constants {
	public static final int IS_USE_YES=0;
	public static final int IS_USE_NO=1;
	public static final int CLOTHES_SLOVING=2;
	/**
	 * 数据库服务器IP地址
	 */
	public static final String WEBIP = "http://120.77.49.17:8080/";
	/**
	 * 数据库服务器IP地址
	 */
	public static final String DB_HOST_IP = "120.25.230.143";

	/**
	 * 数据库用户名
	 */
	public static final String DB_USERNAME = "youhuo";

	/**
	 * 数据库密码
	 */
	public static final String DB_PASSWORD = "Weidoukeji123";

	/**
	 * 数据库名称
	 */
	public static final String DATABASE = "youhuo";

	/**
	 * 本服务器备份保存路径
	 */
	public static final String BACKUP_LOCAL_PATH = "D:/usr/backup/yoho/";

	/**
	 * 远程服务器备份保存路径
	 */
	public static final String BACKUP_REMOTE_PATH = "/usr/backup/yoho/";

	/**
	 * sftp服务器ip地址
	 */
	public static final String SFTP_HOST_IP = "120.24.168.153";

	/**
	 * sftp服务器端口号
	 */
	public static final int SFTP_HOST_PORT = 22;

	/**
	 * sftp服务器用户名
	 */
	public static final String SFTP_HOST_USERNAME = "root";

	/**
	 * sftp服务器密码
	 */
	public static final String SFTP_HOST_PASSWORD = "Weidoukeji123";

	/**
	 * 七牛AK密钥
	 */
	public static final String QINIU_ACCESS_KEY = "hAkbNltRL5rv48HrADx8jKfBpYE_brx8yidTPXHm";

	/**
	 * 七牛SK密钥
	 */
	public static final String QINIU_SECRET_KEY = "skxXucmmBLnGKP9VgrGk04Uz2Z3j-k24uvnAOPX1";

	/**
	 * 七牛空间名
	 */
	public static final String QINIU_BUCKET = "kangkai";

	/**
	 * 七牛空间域名前缀
	 */
	public static final String QINIU_URL_PREFIX = "http://oktwlv8ef.bkt.clouddn.com/";

	/**
	 * 环信应用org_name
	 */
	//public static final String IM_ORG_NAME = "91440300349979223m";

	/**
	 * 环信应用app_name
	 */
	//public static final String IM_APP_NAME = "yoho";

	/**
	 * 环信应用Client Id
	 */
	//public static final String IM_CLIENT_ID = "YXA6HQcuQFCjEea4zc_dnPD52A";

	/**
	 * 环信应用Client Secret
	 */
	//public static final String IM_CLIENT_SECRET = "YXA6Ooi0Q23HalYWNp48AbEPkf-VwBc";
	/**
	 * 极光appkey
	 */
	public static final String JPUSHAPPKEY = "49de2e1770fb6cf21c11d8a1";//"7846f8ea7d2456ec5cbec31d";
	/**
	 * 极光masterkey
	 */
	public static final String JPUSHMASTERSECRECT = "7388d078504c88a91a8523e0";//"ea9b6770ad4ba26710ee75b8";
	
	/**
	 * 短信sid
	 */
	public static final String SMSSID="aaf98f895427cf500154364deae114f0";
	/**
	 * 短信token
	 */
	public static final String SMSTOKEN="eb8ca5a9649748819ef7a5b08242579d";
	/**
	 * 短信APPID
	 */
	public static final String SMSAPPID="aaf98f895427cf5001543654ea4b1504";
	/**
	 * 评价最大值
	 */
	public static final int EVALUATIONMAX=5;
	/**
	 * 量体师待匹配
	 */
	public static final int SURVEYOR_NEEDEDMATCH_STATE=0;
	/**
	 * 量体师待上门
	 */
	public static final int SURVEYOR_NEEDEDSERVE_STATE=1;
	/**
	 * 量体师服务中
	 */
	public static final int SURVEYOR_SERVEING_STATE=2;
	/**
	 * 量体师待评价
	 */
	public static final int SURVEYOR_NEEDEDCOMMENT_STATE=3;
	/**
	 * 待付款
	 */
	public static final int PRODUCTNEEDEDPAY_STATE=0;
	/**
	 * 待发货
	 */
	public static final int PRODUCTNEEDEDSEND_STATE=1;
	/**
	 * 待收货
	 */
	public static final int PRODUCTNEEDEDRECEIPT_STATE=2;
	/**
	 * 待评价
	 */
	public static final int PRODUCTNEEDEDCOMMENT_STATE=3;
	/**
	 * 售后
	 */
	public static final int PRODUCTNEEDEDSERVE_STATE=4;
	/**
	 * 已完成
	 */
	public static final int PRODUCTCOMPLETE_STATE=99;
	/**
	 * 取消
	 */
	public static final int PRODUCTCANCEL_STATE=100;
	
	/**
	 * 待上门
	 */
	public static final String USER_SURVEYOR_NEEDEDSERVE_MESSAGE="等待量体师上门服务中，如有疑问请联系量体师";
	/**
	 * 服务中
	 */
	public static final String USER_SURVEYOR_SERVEING_MESSAGE="量体师已上门服务，如有疑问请联系量体师";
	/**
	 * 待评价
	 */
	public static final String USER_SURVEYOR_NEEDEDCOMMENT_MESSAGE="量体师服务结束，亲请给个大大的好评哦";
	
	/**
	 * 待上门
	 */
	public static final String SURVEYOR_NEEDEDSERVE_MESSAGE="等待量体师上门服务中，请尽快联系客户";
	/**
	 * 服务中
	 */
	public static final String SURVEYOR_SERVEING_MESSAGE="量体师已上门服务，如有疑问请联系客户";
	/**
	 * 待评价
	 */
	public static final String SURVEYOR_NEEDEDCOMMENT_MESSAGE="量体师服务结束";
	
	
	/**
	 * 待付款
	 */
	public static final String PRODUCTNEEDEDPAY_MESSAGE="您有新的待付款订单";
	/**
	 * 待发货
	 */
	public static final String PRODUCTNEEDEDSEND_MESSAGE="您的订单已付款，等待发货";
	/**
	 * 待收货
	 */
	public static final String PRODUCTNEEDEDRECEIPT_MESSAGE="您的订单已发货";
	/**
	 * 售后
	 */
	public static final String PRODUCTNEEDEDSERVE_MESSAGE="您有一条售后订单信息";
	/**
	 * 已完成
	 */
	public static final String PRODUCTCOMPLETE_MESSAGE="您的订单已签收";
	/**
	 * 取消
	 */
	public static final String PRODUCTCANCEL_MESSAGE="您的订单已取消";
	/**
	 * 用户量体师提示信息
	 */
	public static final String[] USERSURVEYORMESSAGE={
		USER_SURVEYOR_NEEDEDSERVE_MESSAGE,
		USER_SURVEYOR_SERVEING_MESSAGE,
		USER_SURVEYOR_NEEDEDCOMMENT_MESSAGE
		} ;
	/**
	 * 量体师提示信息
	 */
	public static final String[] SURVEYORSURVEYORMESSAGE={
			SURVEYOR_NEEDEDSERVE_MESSAGE,
			SURVEYOR_SERVEING_MESSAGE,
			SURVEYOR_NEEDEDCOMMENT_MESSAGE
			} ;
	/**
	 * 商品提示信息
	 */
	public static final String[] PRODUCTMESSAGE={
		PRODUCTNEEDEDPAY_MESSAGE,
		PRODUCTNEEDEDSEND_MESSAGE,
		PRODUCTNEEDEDRECEIPT_MESSAGE,
		PRODUCTNEEDEDSERVE_MESSAGE,
		PRODUCTCOMPLETE_MESSAGE,
		PRODUCTCANCEL_MESSAGE
		} ;
	public static final String[] BUYSUBJECT={
			"康楷购物"
			} ;
	/**
	 * 上线获得金额比例
	 */
	public static final double MONEYRATEFORRECOMMEND = 0.005;
	
	public static enum PRODUCTORDERMESSAGETYPE{
		productOrderMessage(0),surveyorOrderMessage(1);
		private int _value;
		 private PRODUCTORDERMESSAGETYPE(int value)
		   {
		         _value = value;
		   }
		  public int value()
		   {
		      return _value;
		   }	
	};
	public static enum BUYTYPE{
		buyProduct("0");
		private String _value;
		 private BUYTYPE(String value)
		   {
		         _value = value;
		   }
		  public String value()
		   {
		      return _value;
		   }	
	};
	public static enum WASTETYPE{
		buy(0),earn(1);
		private int _value;
		 private WASTETYPE(int value)
		   {
		         _value = value;
		   }
		  public int value()
		   {
		      return _value;
		   }	
	}
	public static Integer getRedPacketNum=15;
	public static double LIMITMONEYRATE=100;
	public static double MYREDPACKETRATE=0.3;
	public static double OTHERREDPACKETRATE=(1-MYREDPACKETRATE)/(getRedPacketNum-1);
}
