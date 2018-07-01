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
<title>起始页</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit"> 
<link type="favicon" rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="移动政务云平台管理控制台">
<link href="<%=basePath%>resources/css/reference/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>resources/css/reference/scrollbar/jquery.scrollbar.min.css" type="text/css"></link>
<link href="<%=basePath%>resources/css/reference/animate.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/style.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>resources/js/reference/jquery.min.js"></script>
</head>
<style>
.aligncenter { 
clear: both; 
display: block; 
margin:auto; 
} 
</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
	<img class="aligncenter" src="<%=basePath%>resources/images/qgzx/qin.jpg" style="width:70%;height:100%"/>
</body>
</html>