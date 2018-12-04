package com.kangkai.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * @author likai QQ:903344654
 * @version 创建时间：2016年4月20日上午10:14:55
 * 类说明：二维码生成，解析辅助类
 */
public class QrCodeUtils {
	
	final static String imgType = "png";
	
    /** 
     * 生成二维码(QRCode)图片的公共方法 
     * @param content 存储内容 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     * @return 
     */ 
    private static BufferedImage qRCodeCommon(String content, String imgType, int size) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(size);  
            // 获得内容的字节数组，设置编码格式  
            byte[] contentBytes = content.getBytes("utf-8");  
            // 图片尺寸  
            int imgSize = 67 + 12 * (size - 1);  
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // 设置背景颜色  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
   
            // 设定图像颜色> BLACK  
            gs.setColor(Color.BLACK);  
            // 设置偏移量，不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容> 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }  
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bufImg;  
    }
    
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param output 输出流 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     */ 
    public static void encoderQRCode(String content, OutputStream output, String imgType, int size) {  
        try {  
            BufferedImage bufImg = qRCodeCommon(content, imgType, size);  
            // 生成二维码QRCode图片  
            ImageIO.write(bufImg, imgType, output);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param output 输出流 
     * @param imgType 图片类型 
     */ 
    public static void encoderQRCode(String content, OutputStream output, String imgType) {  
        encoderQRCode(content, output, imgType, 7);  
    }  
	
    
    /**
     * 
     * @param content 需要转成二维码的内容
     * @return
     * @author likai QQ:903344654
     * @version 创建时间：2016年4月20日上午10:25:54
     * 方法说明：将需要转成二维码的内容转为二维码并且上传到服务器
     */
	public static String uploadQrCodeImg(String content){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		encoderQRCode(content, output, imgType);
		byte[] data = output.toByteArray();
		String raveName = "QrCode/"+"qrCodeManage"+UUID.randomUUID()+"."+imgType;
		new UploadUtils().uploadData(data, raveName);
		return raveName;
	}
	
	
	/**
	 * 
	 * @param clazzCode 班级编号
	 * @return
	 * @author likai QQ:903344654
	 * @version 创建时间：2016年4月20日上午10:50:11
	 * 方法说明：生成班级二维码并且上传并返回地址
	 */
	public static String createClazzQrCode(String clazzId){
		String content = "clazzId="+clazzId;
		String imgPath = uploadQrCodeImg(content);
		return imgPath;
	}
	
	/**
	 * 
	 * @param 家庭作业的二维码
	 * @return
	 * @author likai QQ:903344654
	 * @version 创建时间：2016年4月20日上午10:50:11
	 * 方法说明：生成二维码并且上传并返回地址
	 */
	public static String createHomeWorkTestPaperQrCode(String homeWorkTestPaperId){
		String content = "HomeWorkTestPaperId="+homeWorkTestPaperId;
		String imgPath = uploadQrCodeImg(content);
		return imgPath;
	}
	
	
	/**
	 * 
	 * @param 班级家庭作业的二维码
	 * @return
	 * @author likai QQ:903344654
	 * @version 创建时间：2016年4月20日上午10:50:11
	 * 方法说明：生成班级二维码并且上传并返回地址
	 */
	public static String createClazzHomeWorkTestPaperQrCode(String clazzHomeWorkTestPaperId){
		String content = "clazzTestPaperId="+clazzHomeWorkTestPaperId;
		String imgPath = uploadQrCodeImg(content);
		return imgPath;
	}
	
	/**
	 * 
	 * @param 试卷的二维码
	 * @return
	 * @author likai QQ:903344654
	 * @version 创建时间：2016年4月20日上午10:50:11
	 * 方法说明：生成班级二维码并且上传并返回地址
	 */
	public static String createTestPaperQrCode(String testPaperId){
		String content = "testPaperId="+testPaperId;
		String imgPath = uploadQrCodeImg(content);
		return imgPath;
	}
	
    /**
     * 
     * @param input 输入流
     * @return
     * @author likai QQ:903344654
     * @version 创建时间：2016年4月20日上午10:30:08
     * 方法说明：解析二维码
     */
    public static String decoderQRCode(InputStream input) {  
        BufferedImage bufImg = null;  
        String content = null;  
        try {  
            bufImg = ImageIO.read(input);  
            QRCodeDecoder decoder = new QRCodeDecoder();  
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");   
        } catch (IOException e) {  
            System.out.println("Error: " + e.getMessage());  
            e.printStackTrace();  
        } catch (DecodingFailedException dfe) {  
            System.out.println("Error: " + dfe.getMessage());  
            dfe.printStackTrace();  
        }  
        return content;  
    }
    
    
    /**
     * 
     * @param file
     * @return
     * @author likai QQ:903344654
     * @version 创建时间：2016年4月20日上午10:37:15
     * 方法说明:解析移动端上传上来的图片
     */
    public String decoderQRCodeFile(File file){
    	FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String content = decoderQRCode(fis);
    	return content;
    }
    
    /**
     * 
     * @param file
     * @return
     * @author likai QQ:903344654
     * @version 创建时间：2016年4月20日上午10:37:15
     * 方法说明:解析移动端上传上来的图片,字节数组
     */
    public static String decoderQRCodeByteArray(byte[] data){
    	ByteArrayInputStream bais = new ByteArrayInputStream(data);
    	String content = decoderQRCode(bais);
    	return content;
    }
    
    public static void main(String[] args) {
//    	QrCodeUtils handler = new QrCodeUtils();
//    	String imgAddress = handler.createClazzQrCode("笨蛋！哈哈哈哈哈！");
//    	System.out.println(imgAddress);
//    	File file = new File("E:/dddd.png");
//    	
//    	String aaa = handler.decoderQRCodeFile(file);
//    	System.out.println(aaa);
//    	FileOutputStream file = null;
//		try {
//			file = new FileOutputStream("E:/dddd.png");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	encoderQRCode("笨蛋笨蛋！！！", file, imgType);
    	
    	System.out.println(createClazzQrCode(1+""));
    	
	}
    
    public static class TwoDimensionCodeImage implements QRCodeImage  {
        BufferedImage bufImg;  
         
           public TwoDimensionCodeImage(BufferedImage bufImg) {  
               this.bufImg = bufImg;  
           }  
              
           @Override
		public int getHeight() {  
               return bufImg.getHeight();  
           }  
          
           @Override
		public int getPixel(int x, int y) {  
               return bufImg.getRGB(x, y);  
           }  
          
           @Override
		public int getWidth() {  
               return bufImg.getWidth();
           }  
   }
    
    
	
	
}
