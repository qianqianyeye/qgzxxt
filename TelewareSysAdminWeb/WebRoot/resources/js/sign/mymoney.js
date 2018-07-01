$(function(){
	//initTenantSelect();
	loadData();
 });
var url;

function loadData(){
	 //表格初始化
	var userid=$(window.parent.document).find("#userid").text();
	$('#moneytable').mtable({
		"url" : "sign/moneydata?userid="+userid,
		"operSwitch" : [ {
			icon : 'fa fa-hand-o-up',
			method : "signApply",
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

	function signApply(row){
		var workid=row.workid;
		var userid=$(window.parent.document).find("#userid").text();
		 $.post("sign/applytime?userid="+userid+"&workid="+workid, {}, function (data) {
				 if(data=='true'){
					 $.mal({
		    				text : '申请成功！',
		    				type : 'success'
		    			});
						
						$("#moneytable").mtable("reload");
				 }else{
					 $.mal({
							text : '请不要重复申请或者请先签到签退!',
							type : 'error'
						});
				 }
			  });
	}
	
	function search(){
		var msearch_name=$("#toolbar").find('[name=msearch_name]');
		var msearch_value=$("#toolbar").find('[name=msearch_value]');
		var name=msearch_name.val();
		var value=msearch_value.val();
		var workname;
		if(name=="gwmc"){
			workname=value;
		}
		$("#worktable").mtable('reload',{
			url: "work/search",
			data: {
				workname:workname
			}
		});
	}
	
	
	
