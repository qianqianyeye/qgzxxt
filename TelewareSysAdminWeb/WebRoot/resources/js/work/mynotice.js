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
	
	$('#noticetable').mtable({
		"url" : "notice/mynoticedata?suserid="+userid,
		columns : [ {
			name : "id",
			title : "ID",
			visible:false
		}, {
			name : "workname",
			title : "岗位名称"
		},{
			name : "fusername",
			title : "发送人"
		},{
			name : "fuserid",
			title : "发布人id",
			visible:false
		},{
			name : "content",
			title : "消息内容"
		},{
			name : "time",
			title : "通知时间",
		},{
			name : "statue",
			title : "状态",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.statue) )
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
		$("#noticetable").mtable('reload',{
			url: "notice/mynoticedata?suserid="+userid,
			data: {
				workname:workname
			}
		});
	}
	
	
