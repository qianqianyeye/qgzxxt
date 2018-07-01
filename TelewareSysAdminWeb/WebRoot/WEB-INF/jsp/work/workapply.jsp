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
	
	<script type="text/javascript" src="<%=basePath%>/resources/js/work/workapply.js"></script>

	</head>
<script type="text/javascript">


</script>
<body style="overflow: hidden;">
		 
	<div class="row footable-ibox-model">
		<div class="ibox-title ibox-notitle">
			<h5>岗位申请</h5>
		</div>
		
		<div class="ibox-toolbar" id="toolbar">

		</div>
		<div class="ibox-content">
			<table id="worktable" class="table table-striped"></table>
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
					 <input type="text" id="id" name="id" style="display: none" />
					  <input type="text" id="workid" name="workid" style="display: none" />
					 <input type="text" id="userid" name="userid" style="display: none"/>
					  <input type="text" id="username" name="username" style="display: none"/>
						<div class="form-group">
							<label class="col-sm-2 control-label">评论内容:</label>
							<div class="col-sm-10">
								<textarea class="form-control" type="text" id="content" name="content" required></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button  onclick="save()" type="button" class="btn btn-primary">评论</button>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>
