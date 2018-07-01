<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<link type="favicon" rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="resources/css/reference/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="resources/css/reference/bootstrap-select.min.css" type="text/css"></link>
<link rel="stylesheet" href="resources/css/frame/login.css" type="text/css"></link>
<script type="text/javascript" src="resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/js/reference/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/reference/bootstrap-select.min.js"></script>
<script type="text/javascript" src="resources/js/login/login.js"></script>
<script type="text/javascript">
	$(function() {
		initFrame();
		loadCurrentYear();
	});

	function initFrame(){
		var wh =  window.screen.height;
		var lh = $(".login").outerHeight();
		var top=wh/2-lh/2-60;
		$(".login").css('top',top);
	}
	
	function loadCurrentYear() {
		var date = new Date;
		var year = date.getFullYear();
		$("#currentYear").text(year);
	}
	
</script>
</head>
<body style="background-color:black;background-image:url(resources/images/frame/login_bg.jpg);">
	<div class="loginHead">
		<div class="logo">
			<div style="padding: 10px 20px;display: inline-block;">
				<img src="resources/images/frame/teleware_white.png" style="height:35px;" />
			</div>
			<div style="display:none;line-height: 70px; font-size: 25px;text-align: right;padding-right: 50px;float: right;">
			</div>
		</div>
	</div>
	<div class="login">
		<div class="fl">
			<img src="resources/images/frame/login_zt.png" class="login_zt" />
		</div>
		<div class="fr">
			<div class="loginword"  style="color: white;">
				<img src="resources/images/frame/cloud_white.png" style="height: 25px;"><span id="consoleSysName"> </span>
			</div>
			<div class="loginbox" style="background-color: white;">
				<ul>
					<li id="error" style="margin: 0px 0px 10px 0px;display: none">
						<div name="message" style="color: #F15533;background: #FEEEEB;padding: 5px 15px;"></div>
					</li>
					<li><select id="tenant" name="tenant" class="selectpicker" data-live-search="true" title="用户选择">
					</select></li>
					<li><input id="name" name="name" type="text" class="intxt user" placeholder="登录名称" /></li>
					<li><input id="password" value="" name="password" type="password" class="intxt pass" placeholder="登录密码" /></li>
					<li><input type="button" value="登 录" id="submit_btnlogin" class="btndl" /></li>
				</ul>
			</div>
		</div>

	</div>
	<div class="foot">
		<div style="padding-left: 20px">
			<font id="currentYear"></font> 勤工助学系统
		</div>
	</div>
</body>
</html>