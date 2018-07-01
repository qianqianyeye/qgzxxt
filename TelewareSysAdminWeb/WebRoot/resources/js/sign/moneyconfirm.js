$(function(){
	//initTenantSelect();
	loadData();
 });
var url;

function loadData(){
	 //表格初始化
	var userid=$(window.parent.document).find("#userid").text();
	$('#moneytable').mtable({
		"url" : "sign/gsqr?userid="+userid,
		"operSwitch" : [ {
			icon : 'fa fa-hand-o-up',
			method : "confirm",
		},{
			icon : 'fa fa-xing',
			method : "refuse",
		}],
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
			name : "username",
			title : "申请人"
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

	function confirm(row){
		var id=row.id
		 $.post("sign/confirm?id="+id+"&type=pass", {}, function (data) {
				 if(data=='true'){
					 $.mal({
		    				text : '操作成功！',
		    				type : 'success'
		    			});
						
						$("#moneytable").mtable("reload");
				 }else{
					 $.mal({
							text : '操作失败!',
							type : 'error'
						});
				 }
			  });
	}
	
	function refuse(row){
		var id=row.id
		 $.post("sign/confirm?id="+id+"&type=refuse", {}, function (data) {
				 if(data=='true'){
					 $.mal({
		    				text : '操作成功！',
		    				type : 'success'
		    			});
						
						$("#moneytable").mtable("reload");
				 }else{
					 $.mal({
							text : '操作失败!',
							type : 'error'
						});
				 }
			  });
	}
	
	
	
