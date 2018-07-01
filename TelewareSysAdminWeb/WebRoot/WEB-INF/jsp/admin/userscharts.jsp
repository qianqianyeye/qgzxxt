<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户统计分析</title>
    
    <meta name="viewport"
		content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
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
	<script type="text/javascript" src="<%=basePath%>resources/js/reference/echarts.min.js"></script>
   
  </head>
  <script type="text/javascript">
  $(function(){
	  option = {
			  title: {
	   		        text: '男女比例',
	   		        x:'center'
	   		       
	   		    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['男生','女生']
			    },
			    series: [
			        {
			            name:'男女比例',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:<%=request.getAttribute("boy")%>, name:'男生'},
			                {value:<%=request.getAttribute("girl")%>, name:'女生'}
			            ]
			        }
			    ]
			};
	   var myChart = echarts.init(document.getElementById('pie'));
	   myChart.setOption(option);
	   
	   optionline = {
			   title: {
	   		        text: '热门岗位',
	   		        x:'center'
	   		       
	   		    },
			    xAxis: {
			        type: 'category',
			        data: <%=request.getAttribute("worknames")%>,
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        data: <%=request.getAttribute("counts")%>,
			        type: 'line'
			    }]
			};
	   var broline=echarts.init(document.getElementById('line'));
	   //var myChart2 = echarts.init(document.getElementById('line'));
	   broline.setOption(optionline);
	   
	   optionbar = {
			   title: {
	   		        text: '总收入最高TOP10',
	   		        x:'center'
	   		       
	   		    },
			    xAxis: {
			        type: 'category',
			        data: <%=request.getAttribute("usernames")%>
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        data: <%=request.getAttribute("moneys")%>,
			        type: 'bar'
			    }]
			};
	   var bar=echarts.init(document.getElementById('bar'));
	   //var myChart2 = echarts.init(document.getElementById('line'));
	   bar.setOption(optionbar);
  })
  </script>
  
  <body>

	
    <div class="gram">
    	<div id="pie" style="width:100%; height: 400px;"></div>
	</div>
	<br/>
	
	 <div class="gram">
    	<div id="line" style="width:100%; height: 400px;"></div>
	</div>
	
	<div class="gram">
    	<div id="bar" style="width:100%; height: 400px;"></div>
	</div>
	
  </body>
 
</html>
 