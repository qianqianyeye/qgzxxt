$(function(){
	//initTenantSelect();
	loadData();
 });
var url;

function loadData(){
	var userid=$(window.parent.document).find("#userid").text();
	 //表格初始化
	$("#toolbar").mboxtoolbar({
		"search" : {
			texts : [ {
				text : "岗位名称",
				value : "gwmc"
			}],
			onClick : function(value) {
				search(value);
			}
		}
	});
	
	$('#worktable').mtable({
		"url" : "work/myworkdata?userid="+userid,
		columns : [ {
			name : "id",
			title : "ID",
			visible:false
		}, {
			name : "workname",
			title : "岗位名称"
		}, {
			name : "content",
			title : "岗位内容"
		},{
			name : "username",
			title : "发布人"
		},{
			name : "userid",
			title : "发布人id",
			visible:false
		},{
			name : "money",
			title : "薪酬(元/小时)"
			
		},{
			name : "passstatue",
			title : "状态",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.passstatue) )
				{
					case 0: text='待审批'; break;
					case 1: text='已通过';  break;
					case 2: text='未通过';  break;
					default: text='';
				}
				return text;
			}
		}]
	});
	
}

	function search(){
		var msearch_name=$("#toolbar").find('[name=msearch_name]');
		var msearch_value=$("#toolbar").find('[name=msearch_value]');
		var name=msearch_name.val();
		var value=msearch_value.val();
		var workname;
		var userid=$(window.parent.document).find("#userid").text();
		if(name=="gwmc"){
			workname=value;
		}
		$("#worktable").mtable('reload',{
			url: "work/myworkdata?userid="+userid,
			data: {
				workname:workname
			}
		});
	}
	
	
