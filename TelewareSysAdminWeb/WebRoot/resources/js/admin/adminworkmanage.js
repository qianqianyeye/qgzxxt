$(function(){
	//initTenantSelect();
	loadData();
 });
var url;

function loadData(){
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
		"url" : "work/workdata",
		"operSwitch" : [ {
			icon : 'fa fa-pencil-square-o',
			method : "editwork",
		}, {
			icon : 'fa fa-trash-o the-icons-red',
			method : "deletework",
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
			name : "username",
			title : "发布人"
		},{
			name : "money",
			title : "薪酬(元/小时)"
		},{
			name : "statue",
			title : "状态",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.statue) )
				{
					case 0: text='禁用'; break;
					case 1: text='启用';  break;
					default: text='';
				}
				return text;
			}
			
		},{
			name:"comment",
			title:"评论:",
			breakpoints : "xs sm md lg"
		}]
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
	
	function addMonitorspot(){
		$('#fm').clearForm();
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		$("#statue").val("1");
		$("#userid").val(userid);
		$("#username").val(username);
		$("#dlg_title").text('添加岗位');
		$("#dlg").modal("show");
		  $('#money').attr('disabled',false);
		url="work/addwork";
	}
	
	function editwork(row){
		$('#fm').clearForm();
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		
			//$("#userid").val(userid);
			//$("#username").val(username);
			$("#dlg_title").text('修改岗位');
			$("#fm").mform(row);
			$("#dlg").modal("show");
			//$("#money")
			  $('#money').attr('disabled',true);
			url="work/updatework";
		
	}
	
	function save(){
	
		 $("#fm").ajaxSubmit({
		    	url:url,
		    	success:function(result){
		    		//alert(result)
		    		if (result == "true") {
		    			$.mal({
		    				text : '操作成功！',
		    				type : 'success'
		    			});
						$("#dlg").modal("hide");
						$("#worktable").mtable("reload");
					} else {
						$.mal({
		    				text : '操作失败!,只能修改自己发布的岗位!',
		    				type : 'error'
		    			});
					}
		    	},
		    	beforeSubmit:function(){
		    		return $("#fm").valid();
		    	}
		    });
	}
	
	function deletework(row){
		$.mal({
			text : '确定要删除吗?'
		}, function() {
			var ids = row.id;
				$.post("work/deleteWork?id="+ids, {
					
				}, function(result) {
					if (result) {
						$("#worktable").mtable("reload");
						$.mal({
							text : '操作成功',
							type : 'success' 
						});
					} else {
						$.mal({
							text : '删除失败,只能删除自己发布的岗位!',
							type : 'error'
						});
					}
				}, 'json');
			
		});
	}
	
