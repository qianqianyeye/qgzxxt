<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title> - FooTable</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="renderer" content="webkit"> 
<link href="<%=basePath%>hAdmin/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>hAdmin/css/footable.bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/font-awesome.min.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/animate.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/style.css" rel="stylesheet">
</head>
<body>
<div class="row footable-ibox-model">
   <div class="ibox-title">
       <h5>FooTable行切换，排序，分页演示</h5>
   </div>
   <div class="ibox-toolbar" id="toolbar">
       
   </div>
   <div class="ibox-content">
       <table id="accordion-example-23" class="table table-striped" visible="false"></table>
   </div>
</div>

    <!-- 全局js -->
    <script src="<%=basePath%>resources/js/reference/jquery.min.js"></script>
    <script src="<%=basePath%>resources/js/reference/bootstrap.min.js"></script>
    <script src="<%=basePath%>hAdmin/js/footable.min.js"></script>
    <script src="<%=basePath%>resources/js/reference/mcui.js"></script>
    <script src="<%=basePath%>hAdmin/newjs/tableurl.js"></script>
    
</body>
</html>
