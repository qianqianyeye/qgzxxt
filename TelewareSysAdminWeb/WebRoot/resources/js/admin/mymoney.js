$(function(){
	//initTenantSelect();
	loadData();
 });
var url;

function loadData(){
	 //表格初始化
	var userid=$(window.parent.document).find("#userid").text();
	$('#moneytable').mtable({
		"url" : "tongji/moneydata",
		columns : [ {
			name : "id",
			title : "ID",
			visible:false
		},{
			name : "workid",
			title : "workid",
			visible:false
		}, {
			name : "workname",
			title : "岗位名称"
		},{
			name : "money",
			title : "当日总薪酬(元)"
		},{
			name : "timelength",
			title : "工作时长(时:分)"
		},{
			name : "time",
			title : "日期"
		},{
			name : "statue",
			title : "状态",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.statue) )
				{
					case 0: text='未申请'; break;
					case 1: text='已申请';  break;
					default: text='';
				}
				return text;
			}
			
		},{
			name : "confirmstatue",
			title : "确认状态",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.confirmstatue) )
				{
					case 0: text='待审核'; break;
					case 1: text='已确认';  break;
					case 2: text='未确认';  break;
					default: text='';
				}
				return text;
			}
			
		}]
	});
	
}
	
	
	
