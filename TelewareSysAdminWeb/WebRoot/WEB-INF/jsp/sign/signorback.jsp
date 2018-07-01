<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>签到签退</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<link href="<%=basePath%>resources/css/reference/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>resources/css/reference/font-awesome.min.css" rel="stylesheet">
	<link href="<%=basePath%>resources/css/reference/footable/footable.bootstrap.min.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=basePath%>resources/css/reference/animate.css" rel="stylesheet">
	<link href="<%=basePath%>resources/css/reference/style.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/form/jquery.form.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/jquery-validation/messages_zh.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/footable/footable.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/slimscroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/mcui.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/web.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/sign/signorback.js"></script>
  </head>
  
 <body style="overflow: hidden;">
 	<body style="overflow: hidden;">
  	<div class="row footable-ibox-model">
		<div class="ibox-title">
			<h5>签到签退</h5>
		</div>
		<div class="ibox-toolbar" id="toolbar">
			<div style="display: inline-block;">
					<button id="signorback" class="btn btn-primary btn-toolbar" type="button" style="margin-left: 0">
						<i class="fa fa-pencil-square-o"></i>&nbsp;<spcn id="signstatue"></spcn>
					</button>
					
					<button id="timeconfirm" class="btn btn-primary btn-toolbar" type="button" style="margin-left: 0">
						<i class="fa fa-pencil-square-o"></i>&nbsp;工时申请
					</button>
					
			</div>
		</div>
		
		<input id="workid" value="${requestScope.workid}" type="hidden"/>
		<div class="ibox-content">
			<form id="classmenuForm" method="post" class="form-horizontal">
			<%--
			
				<div class="form-group">
					<label class="col-sm-2 control-label">签到时间:</label>
					<div class="col-sm-10">
	       				<input id="signtime" name="signtime" class="form-control" value="" disabled/>
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">签退时间:</label>
					<div class="col-sm-10">
	       				<input id="backtime" name="backtime" class="form-control" value="" disabled/>
					</div>
				</div>
				
				--%>
				<div class="form-group">
					<label class="col-sm-2 control-label">今日总时长(时:分):</label>
					<div class="col-sm-10">
	       				<input id="timelength" name="timelength" class="form-control" value="" disabled/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">今日总工资(元):</label>
					<div class="col-sm-10">
	       				<input id="money" name="money" class="form-control" value="" disabled/>
					</div>
				</div>
				
			</form>
		</div>
	</div>
  </body>
</html>
