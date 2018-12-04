<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>分享赚钱</title>
<link rel="stylesheet" href="http://oikhafxxs.bkt.clouddn.com/res/app/css/index.css" />
</head>
<style>
.shareMain img{ width:100%; vertical-align: bottom;}
.shareInfo{ width:100%; height:auto; overflow: hidden; padding: .4rem 0; background: url(../res/app/images/bg1.png) no-repeat center; background-size: 100% 100%; text-align: center;}
.shareInfo input{ width:60%; height:1rem; background: rgba(255,255,255,.6); border-radius: 5px; border: none; text-align: center; margin-bottom: .2rem; color:#999; box-shadow:3px -3px 3px 0px #af9e7c; -webkit-box-shadow: 3px -3px 3px 0px #af9e7c;}
.shareInfo a.loginBtn{ display: inline-block; width:4rem; height:1.5rem; background: url(../res/app/images/loginBtn.png) no-repeat center; background-size:contain;}
.shareBt{ width:100%; height:auto; overflow: hidden; padding: .4rem 0; background: url(../res/app/images/bg2.png) no-repeat center; background-size: 100% 100%; text-align: center;}
.shareBt img{ width:4rem;}
.shareBt a.download{ display: inline-block; width:4rem; height:1.5rem; background: url(../res/app/images/download.png) no-repeat center; background-size:contain; margin-top: .2rem;}
</style>
<body>
<div class="wrap">
    <div class="shareMain">
        <header><img src="http://oikhafxxs.bkt.clouddn.com/res/app/images/shareHead.jpg" alt=""></header>
        <section class="shareInfo">
        <form id="joinRecommendForm">
       		<input name="redPacketId" id="redPacketId" type="hidden" value="${redPacketId}">
            <input name="phone" id="phone" type="text" class="phoneNum" placeholder="输入手机号码" onPless="" />
            <p><a href="javascirpt:void(0);" class="loginBtn" id="submitPhone" onclick="return false;">点击获取</a> </p>
        </form>
        </section>
        <section class="shareImg">
            <img src="http://oikhafxxs.bkt.clouddn.com/res/app/images/shareMain.jpg" alt="" />
        </section>
        <section class="shareBt">
            <img src="http://oikhafxxs.bkt.clouddn.com/res/app/images/qr.jpg" alt="二维码" />
            <p><a href="javascirpt:void(0);" class="loginBtn"></a></p>
        </section>
    </div>
</div>
<script src="http://oikhafxxs.bkt.clouddn.com/res/app/js/flexible.js"></script>
<script src="http://oikhafxxs.bkt.clouddn.com/res/app/js/jquery.min.js"></script>
<script src="http://oikhafxxs.bkt.clouddn.com/res/app/js/jquery.form.min.js"></script>
<script src="http://oikhafxxs.bkt.clouddn.com/res/js/common.js"></script>
<script>


$(function(){
	
	$('#phone').click(function(){
		$('html,body').animate({scrollTop:$('#phone').offset().top - 50}, 0);
	});
	
	// 提交手机号码
	$('#submitPhone').click(function(){
		var options = {
			target : '',
			beforeSubmit : joinRecommendBeforeSubmit,
			success : joinRecommendSuccess,
			url : '/kangkai/recommend/recommendCoupon.action',
			type : 'post',
			resetForm : true,
			dataType : 'json'
		};
		$('#joinRecommendForm').ajaxSubmit(options);
	});
	
	 document.onkeydown=function(){
         if (event.keyCode == 13){
        	 $('#submitPhone').click();
         }
         else{
         }
        }
	
//	function joinRecommend() {
//		var options = {
//			target : '',
//			beforeSubmit : joinRecommendBeforeSubmit,
//			success : joinRecommendSuccess,
//			url : '/app/joinRecommend',
//			type : 'post',
//			resetForm : true,
//			dataType : 'json'
//		};
//		$('#joinRecommendForm').ajaxForm(options);
//	}
//	joinRecommend();

	function joinRecommendBeforeSubmit(formData, jqForm, options) {
		var phone = $("#phone").val(),
		phoneReg = /^1\d{10}$/;
		
		if (!phoneReg.test(phone)) {
			var str = '请输入11位手机号码';
			showTips(str);
			$("#phone").focus();
			return false;
		} else {
			return true;
		}
	}

	function joinRecommendSuccess(responseText, statusText, xhr, $form) {
		if (responseText.errcode == 0 || responseText.errcode == 1) {
//			showTips("link");
//			window.location.href = "";
		} else {
			showTips(responseText.msg);
		}
	}
	
	function showTips(str){
		var tips = '<div class="errorBox"><div class="errorMain"><p>' + str + '</p></div></div>';
		$('body').append(tips);
	
		$('.errorBox').show();
		setTimeout("$('.errorBox').remove();",1500); 
	}
});
</script>
</body>
</html>