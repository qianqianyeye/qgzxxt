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
<title>${consoleSysName}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit"> 
<link type="favicon" rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="勤工助学系统">
<link href="<%=basePath%>resources/css/reference/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>resources/css/reference/scrollbar/jquery.scrollbar.min.css" type="text/css"></link>
<link href="<%=basePath%>resources/css/reference/animate.css" rel="stylesheet">
<link href="<%=basePath%>resources/css/reference/style.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>resources/js/reference/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/web/publicscript.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/home/home.js"></script>
<style>
.tabs-with-icon {
	padding-left: 0px;
}

#page-wrapper{
	padding: 0;
}

.fa-mobile{
	font-size: 18px;
}

.fa{
	width:13px;
}

.tab-content{
  height: calc(100% - 40px);
}

.tab-pane{
  height: 100%;
}

.nav-tabs a {
    color: #76838f;
    border-radius: 0;
}

.nav-tabs > li > a {
    border-radius: 0;
    margin-right: 0;
}

.nav-tabs > li > a{
	color:#797c82;
}

.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover {
	color:#3a3a3a;
}

.nav-tabs > li:first-child.active > a {
    border-left: 1px solid #fff;
}

</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="tenantid" style="display:none">${tenantid}</div>
<div id="userid" style="display:none">${userid}</div>
<div id="username" style="display:none">${username}</div>
	<div class="row" style="height: 60px;background-color: #0c1217;border-bottom: 1px solid #000;">
		<div class="col-md-6">
			<Button class="navbar-minimalize" style="background-color: #0c1217;border: none;padding: 17px 12px 0 12px;margin: 0;outline-style:none;float: left;">
				<img src="resources/images/frame/cloud_white.png" style="height:25px ">
			</Button>
			<div style="float: left;height: 100%;line-height:60px;font-size: 20px;color: white;">勤工助学系统</div>
		</div>
		<div class="col-md-6"><%--
			<div style="float: right; height: 100%;line-height:60px;font-size: 14px;padding-right: 10px">
				<a id="ddd" style="color: white;" onclick="loginout()">安全退出</a>
			</div>
			<div style="float: right;height: 100%;line-height:60px; font-size: 14px;padding-right: 10px">
				<a id="editpass" data-toggle="modal" data-target="#myModal6" style="color: white;">修改密码</a>
			</div>--%>
			<div style="float: right;height: 100%;line-height:60px; font-size: 14px;padding-right: 10px;color: white;">
				<span id="loginUserId"></span>
			</div>
		</div>
	</div>
	<div id="wrapper" style="height: calc(100% - 60px);">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation" style="height: inherit;">
		<div class="nav-close">
			<i class="fa fa-times-circle"></i>
		</div>
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<li id="startPage"><a class="J_menuItem" href="homeData/index"> <i class="fa fa-home"></i> <span class="nav-label">起始页</span> </a>
				</li>
				<li class="hidden-folded padder m-t m-b-sm text-muted text-xs"><span class="ng-scope">分类</span>
				</li>
			</ul>
		</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1" style="overflow: hidden;">
			<div class="mtabs" id="content-main" style="height: 100%"></div>
			<%--<div class="row J_mainContent" id="content-main" style="height: 100%">
				<iframe id="J_iframe" width="100%" height="100%" src="homeData/index" frameborder="0" data-id="index/data/dataHome" seamless></iframe>
			</div>--%>
		</div>
		<!--右侧部分结束-->
	</div>
	<div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal m-t" id="commentForm">
						<div class="form-group">
							<label class="col-sm-5 control-label">新密码：</label>
							<div class="col-sm-7">
								<input id="txtNewPass" name="txtNewPass" minlength="2" type="text" class="form-control" required="" aria-required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">确认密码：</label>
							<div class="col-sm-7">
								<input id="txtRePass" name="txtRePass" minlength="2" type="text" class="form-control" required="" aria-required="true">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button id="btnEp" type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal inmodal fade" id="loading" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="top: 45%">
			<div class="sk-spinner sk-spinner-three-bounce">
				<div class="sk-bounce1"></div>
				<div class="sk-bounce2"></div>
				<div class="sk-bounce3"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="resources/js/reference/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/scrollbar/jquery.scrollbar.min.js"></script>
	<script src="resources/js/reference/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/js/reference/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="resources/js/reference/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/mcui.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			// MetsiMenu
			$('#side-menu').metisMenu();

			// 打开右侧边栏
			$('.right-sidebar-toggle').click(function() {
				$('#right-sidebar').toggleClass('sidebar-open');
			});

			//固定菜单栏
			$(function() {
				$('.sidebar-collapse').slimScroll({
					height : '100%',
					railOpacity : 0.9,
					alwaysVisible : false
				});
			});

			// 菜单切换
			$('.navbar-minimalize').click(function() {
				$("body").toggleClass("mini-navbar");
				SmoothlyMenu();
			});

			// 侧边栏高度
			function fix_height() {
				var heightWithoutNavbar = $("body > #wrapper").height() - 61;
				$(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
			}
			fix_height();

			$(window).bind("load resize click scroll", function() {
				if (!$("body").hasClass('body-small')) {
					fix_height();
				}
			});

			//侧边栏滚动
			$(window).scroll(function() {
				if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
					$('#right-sidebar').addClass('sidebar-top');
				} else {
					$('#right-sidebar').removeClass('sidebar-top');
				}
			});

			$('.full-height-scroll').slimScroll({
				height : '100%'
			});

			$('#side-menu>li').click(function() {
				if ($('body').hasClass('mini-navbar')) {
					NavToggle();
				}
			});
			$('#side-menu>li li a').click(function() {
				if ($(window).width() < 769) {
					NavToggle();
				}
			});

			$('.nav-close').click(NavToggle);

			//ios浏览器兼容性处理
			if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
				$('#content-main').css('overflow-y', 'auto');
			}

		});

		$(window).bind("load resize", function() {
			if ($(this).width() < 769) {
				$('body').addClass('mini-navbar');
				$('.navbar-static-side').fadeIn();
			}
		});

		function NavToggle() {
			$('.navbar-minimalize').trigger('click');
		}

		function SmoothlyMenu() {
			if (!$('body').hasClass('mini-navbar')) {
				$('#side-menu').hide();
				setTimeout(function() {
					$('#side-menu').fadeIn(500);
				}, 100);
			} else if ($('body').hasClass('fixed-sidebar')) {
				$('#side-menu').hide();
				setTimeout(function() {
					$('#side-menu').fadeIn(500);
				}, 300);
			} else {
				$('#side-menu').removeAttr('style');
			}
		}
	</script>
</body>
</html>