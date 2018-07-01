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
    
    <title>修改个人信息</title>
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
	<script type="text/javascript" src="<%=basePath%>resources/js/login/updateusers.js"></script>
  </head>
  
 <body style="overflow: hidden;">
 	<body style="overflow: hidden;">
  	<div class="row footable-ibox-model">
		<div class="ibox-title">
			<h5>修改个人信息</h5>
		</div>
		<div class="ibox-toolbar" id="toolbar">
			<div style="display: inline-block;">
			<%--
				<button id="closedId" class="btn btn-primary btn-toolbar" type="button">
					<i class="fa fa-close"></i>&nbsp;关闭
				</button>
				<c:if test="${requestScope.operation1 == 0 }">
					<button id="savesId" class="btn btn-primary btn-toolbar" type="button" style="margin-left: 0">
						<i class="fa fa-save"></i>&nbsp;保存
					</button>
				</c:if>
				--%>
					<button id="updateId" class="btn btn-primary btn-toolbar" type="button" style="margin-left: 0">
						<i class="fa fa-pencil-square-o"></i>&nbsp;修改
					</button>
			</div>
		</div>
		<div class="ibox-content">
			<form id="classmenuForm" method="post" class="form-horizontal">
				<input id="userid" name="userid" class="form-control" value="" type="hidden"/>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名:</label>
					<div class="col-sm-10">
	       				<input id="username" name="username" class="form-control" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">新密码:</label>
					<div class="col-sm-10">
	       				<input id="password" name="password"  type="password" class="form-control" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">电话:</label>
					<div class="col-sm-10">
	       				<input id="phone" name="phone" class="form-control" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">年龄:</label>
					<div class="col-sm-10">
	       				<input id="age" name="age" class="form-control" value="" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">性别:</label>
					<div class="col-sm-10">
					<select id="sex" name="sex" class="form-control" style="height: 35px;">
					  <option value ="0">男</option>
					   <option value ="1" >女</option>
					 
					</select>
					</div>
				</div>

				
			</form>
		</div>
	</div>
  </body>
</html>
