//应用概况数据选择
/*    	$(document).ready(function(){
 $("#mainTable td").mouseover(function(){
 var tr_column = $(this).parent().find("td").index($(this)[0]);
 //alert(tr_column);
 $("#mainTable tr").each(function(i){
 $(this).children().eq(tr_column).css("color","#396fff");
 });
 });
 $("#mainTable td").mouseout(function(){
 var tr_column = $(this).parent().find("td").index($(this)[0]);
 var column = Number($("#divFlag").attr("column"));
 if(tr_column != column){
 $("#mainTable tr").each(function(i){
 if(i == 1){
 $(this).children().eq(tr_column).css("color","");
 }else{
 $(this).children().eq(tr_column).css("color","#333");
 }
 });
 }
 });
 $("#mainTable td").click(function(){
 var column = Number($("#divFlag").attr("column"));
 $("#mainTable tr").each(function(i){
 if(i == 1){
 $(this).children().eq(column).css("color","");
 }else{
 $(this).children().eq(column).css("color","#333");
 }
 });
 var tr_column = $(this).parent().find("td").index($(this)[0]);
 var divLeft = 10+tr_column*15;
 $("#divFlag").css("margin-left",divLeft+"%");
 $("#divFlag").attr("column",tr_column);
 });
 });*/
//应用概况折线图
var main2height = $("#block_left").height();
$("#main2").height(main2height - 10);
var period = [ '00:00-01:00', '01:00-02:00', '02:00-03:00', '03:00-04:00', '04:00-05:00', '05:00-06:00', '06:00-07:00', '07:00-08:00', '08:00-09:00', '09:00-10:00', '10:00-11:00', '11:00-12:00', '12:00-13:00', '13:00-14:00', '14:00-15:00', '15:00-16:00', '16:00-17:00', '17:00-18:00', '18:00-19:00', '19:00-20:00', '20:00-21:00', '21:00-22:00', '22:00-23:00', '23:00-24:00' ]
var myChart2 = echarts.init(document.getElementById('main2'));
var lognum = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ], times = [];
option = {
	backgroundColor : '#ffffff',// 背景色
	title : {
		text : '日活跃度',
		padding: 10,              
		textStyle: {
			fontSize: 15
		}
	},
	tooltip : {
		trigger : 'axis'
	},
	color : [ '#396fff', '#c2d1fb' ],
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : {
		type : 'category',
		boundaryGap : false,
		data : period
	},
	yAxis : {
		type : 'value'
	},
	series : [ {
		name : '活跃度',
		type : 'line',
		symbol : 'none',
		itemStyle : {
			normal : {
				areaStyle : {
					type : 'default',
					color : '#b9e8f7'
				},
				lineStyle : {
					color : '#4fc5ea'
				}
			}
		},
		data : lognum
	} ]
};
myChart2.setOption(option);
var loadEchart2=function(){
	myChart2.showLoading();
	$.post('homeData/getDayActive', {
		tenantId : $("#tenantId").val()
	}, function(result) {
		if (result) {
			myChart2.hideLoading();
			$.each(result, function(i, row) {
				var hour = parseInt(row['HOUR']);
				lognum[hour] = row['LOGNUM'];
			});
			myChart2.setOption({
				series : [ {
					data : lognum
				} ]
			});
		}
	}, 'json').error(function() {
		myChart2.hideLoading();
	});
}
loadEchart2();
/*
 * $.ajax({ type : "post", async : true, //同步执行 url :
 * "homeData/getDayActive", data : {tenantId:$("#tenantId").val()},
 * dataType : "json", //返回数据形式为json success : function(result) { if (result) {
 * times = period.slice(0, result[result.length-1].HOUR); lognum = new
 * Array(result[result.length-1].HOUR); for(var i=0;i<result.length;i++){
 * lognum[result[i].HOUR] = result[i].LOGNUM; } for(var j =
 * 0;lognum.length;j++){ if(typeof(lognum[i]) == "undefined"){ lognum[i] = 0; } } }
 * myChart2.setOption({ xAxis: { data:times }, series: [{ name:'人数', data:lognum }]
 * }); myChart2.hideLoading(); }, error : function(errorMsg) {
 * alert("不好意思，图表请求数据失败啦!"); myChart.hideLoading(); } });
 */
var myChart8 = echarts.init(document.getElementById('main8'));
var dataAxis = [];
var data = [];

var dataShadow = [];

option = {
	title : {
		text : '部门开通人数',
		subtext : 'TOP10',
			padding: 10,              
			textStyle: {
				fontSize: 15
			}
	},
	backgroundColor : '#ffffff',// 背景色
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'shadow'
		},
		formatter : function(params) {
			return params[0].value;
		}
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : {
		type : 'value'
	},
	yAxis : {
		type : 'category',
		data : dataAxis,
		axisLine : {
			show : false
		},
		axisTick : {
			show : false
		}
	},
	series : [ { // For shadow
		type : 'bar',
		itemStyle : {
			normal : {
				color : 'rgba(0,0,0,0.05)'
			}
		},
		barGap : '-100%',
		barCategoryGap : '40%',
		data : dataShadow,
		animation : false
	}, {
		type : 'bar',
		itemStyle : {
			normal : {
				color : new echarts.graphic.LinearGradient(0, 0, 1, 1, [ {
					offset : 0,
					color : '#b9e8f7'
				}, {
					offset : 0.7,
					color : '#71c8e3'
				}, {
					offset : 1,
					color : '#5ec7e8'
				} ])
			},
			emphasis : {
				color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
					offset : 0,
					color : '#51c7ec'
				}, {
					offset : 0.7,
					color : '#51c7ec'
				}, {
					offset : 1,
					color : '#51c7ec'
				} ])
			}
		},
		data : data
	} ]
};
myChart8.setOption(option);
myChart8.showLoading();
$.post('homeData/getDepartment', {
	tenantId : $("#tenantId").val()
}, function(result) {
	if (result) {
		myChart8.hideLoading();
		for ( var i = 0; i < result.length; i++) {
			dataAxis.push(result[i].DEPTNAME);
		}
		dataAxis.reverse();
		for ( var i = 0; i < result.length; i++) {
			var num = new Number(result[i].HUMANNUM);
			data.push(result[i].HUMANNUM);
		}
		data.reverse();
		/*  for(var i = 0;i<data.length;i++){
		  	dataShadow.push(20);
		  }*/
		myChart8.setOption({
			yAxis : {
				data : dataAxis
			},
			series : [ {
				data : dataShadow
			}, {
				data : data
			} ]
		});
	}
}, 'json').error(function() {
	myChart8.hideLoading();
});

var myChart9 = echarts.init(document.getElementById('main9'));

var deviceName = [], device = [];

option = {
	title : {
		text : '设备分布',
			padding: 10,              
			textStyle: {
				fontSize: 15
			}
	},
	backgroundColor : '#ffffff',// 背景色
	tooltip : {
		trigger : 'item',
		formatter : "{a} <br/>{b} : {c} ({d}%)"
	},
	legend : {
		orient : 'vertical',
		right : 10,
		top : 30,
		data : deviceName
	},
	series : [ {
		name : '开通人数',
		type : 'pie',
		radius : [ '50%', '60%' ],
		avoidLabelOverlap : false,
		color:[ '#a398e7','#5bd0f4'],
		label : {
			normal : {
				show : false,
				position : 'center'
			},
			emphasis : {
				show : true,
				textStyle : {
					fontSize : '30',
					fontWeight : 'bold'
				}
			}
		},
		labelLine : {
			normal : {
				show : false
			}
		},
		data : device
	} ]
};
myChart9.setOption(option);
myChart9.showLoading();
$.post('homeData/getDeviceNum', {
	tenantId : $("#tenantId").val()
}, function(result) {
	if (result) {
		myChart9.hideLoading();
		for ( var i = 0; i < result.length; i++) {
			console.log(result[i].DEVICENAME);
			deviceName.push(result[i].DEVICENAME);
		}
		for ( var i = 0; i < result.length; i++) {
			console.log(result[i].DEVICENAME);
			device.push({
				value : result[i].DEVICENUM,
				name : result[i].DEVICENAME
			});
		}
		myChart9.setOption({
			legend : {
				data : deviceName
			},
			series : [ {
				data : device
			} ]
		});
	}
}, 'json').error(function() {
	myChart9.hideLoading();
});
