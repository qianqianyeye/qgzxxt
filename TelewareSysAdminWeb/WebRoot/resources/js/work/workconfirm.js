$(function(){
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
		"url" : "work/confirmworkdata?userid="+userid,
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
		}, {
			name : "workname",
			title : "岗位名称"
		}, {
			name : "content",
			title : "岗位内容"
		},{
			name : "susername",
			title : "申请人"
		},{
			name : "applyid",
			title : "申请ID",
			visible:false
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
		},{
			name : "suserid",
			title : "申请人",
			visible:false
		}]
	});
	
}

	function search(){
		var msearch_name=$("#toolbar").find('[name=msearch_name]');
		var msearch_value=$("#toolbar").find('[name=msearch_value]');
		var name=msearch_name.val();
		var value=msearch_value.val();
		var userid=$(window.parent.document).find("#userid").text();
		var workname;
		if(name=="gwmc"){
			workname=value;
		}
		$("#worktable").mtable('reload',{
			url: "work/confirmworkdata?userid="+userid,
			data: {
				workname:workname
			}
		});
	}
	
	
	function confirm(row){
		
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		$.mal({
			text : '确定要通过吗?'
		}, function() {
		$.post("work/passwork?suserid="+row.suserid+"&workid="+row.id+"&statue=1", {
			
		}, function(result) {
			//alert(result);
			
				$.mal({
					text : result.tishi,
					type : 'success' 
				});
				$("#worktable").mtable("reload");
		}, 'json');
		})
	}
	
	function refuse(row){
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		//alert(row.suserid);
		$.mal({
			text : '确定要拒绝吗?'
		}, function() {
		$.post("work/passwork?suserid="+row.suserid+"&workid="+row.id+"&statue=2", {
			
		}, function(result) {
				$.mal({
					text :  result.tishi,
					type : 'success' 
				});
				$("#worktable").mtable("reload");
		}, 'json');
		})
	}
	
