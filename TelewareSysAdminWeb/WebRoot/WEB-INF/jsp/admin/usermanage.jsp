<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<base href="<%=basePath%>">
<title></title>
	
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
	<script src="<%=basePath%>resources/js/web.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>/resources/js/admin/usermanage.js"></script>

	</head>
<script type="text/javascript">


</script>
<body style="overflow: hidden;">
		 
	<div class="row footable-ibox-model">
		<div class="ibox-title ibox-notitle">
			<h5>用户管理</h5>
		</div>
		
		<div class="ibox-toolbar" id="toolbar">
		
		<div class="ibox-toolbar-butdiv" style="display: inline-block;">
				<button class="btn btn-primary btn-toolbar" type="button" onclick="addMonitorspot()">
					<i class="fa fa-plus"></i>&nbsp;添加
				</button>
		</div>
			
		</div>
		<div class="ibox-content">
			<table id="usertable" class="table table-striped"></table>
		</div>
	</div>
	
	</div>
		<div class="modal inmodal fade" id="dlg" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 id="dlg_title" class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form id="fm" method="post" class="form-horizontal">
					  <input type="text" id="userid" name="userid" style="display: none"/>
						<div class="form-group">
							<label class="col-sm-2 control-label">用户选择</label>
							<div class="col-sm-10">
							  <select id="tenantid" name="tenantid" class="form-control m-b ibox-ty-sty">
								<option value="0" selected>管理员</option>
								<option value="1" >学生</option>
								<option value="2" >老师</option>
                               </select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
							  <select id="sex" name="sex" class="form-control m-b ibox-ty-sty">
								<option value="0" selected>男</option>
								<option value="1" >女</option>
                               </select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10">
							  <select id="statue" name="statue" class="form-control m-b ibox-ty-sty">
								<option value="1" selected>启用</option>
								<option value="0" >禁用</option>
                               </select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">登录名称</label>
							<div class="col-sm-10">
							<input id="username" name="username" type="text" class="form-control"  required/>
							</div>
						</div>
						
	
						
						<div class="form-group">
							<label class="col-sm-2 control-label">登录密码</label>
							<div class="col-sm-10">
							<input id="password" name="password" type="text" class="form-control"  required/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">电话号码</label>
							<div class="col-sm-10">
							<input id="phone" name="phone" type="text" class="form-control"  required/>
							</div>
						</div>
						
					   <div class="form-group">
							<label class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10">
							<input id="age" name="age" type="text" class="form-control"  required/>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button  onclick="save()" type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>
