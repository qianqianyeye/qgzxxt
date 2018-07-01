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
			icon : 'fa fa-commenting-o',
			method : "comment",
		},{
			icon : 'fa fa-book',
			method : "apply",
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
			name : "userid",
			title : "发布人id",
			visible:false
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
	
	
	
	function comment(row){
		$('#fm').clearForm();
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		$("#userid").val(userid);
		$("#username").val(username);
		$("#dlg_title").text('评论');
		var workid=row.id;
		$("#workid").val(workid);
		$("#dlg").modal("show");
		url="work/addcomment";
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
		    				text : '操作失败!',
		    				type : 'error'
		    			});
					}
		    	},
		    	beforeSubmit:function(){
		    		return $("#fm").valid();
		    	}
		    });
	}
	
	function apply(row){
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		$.mal({
			text : '确定要申请吗?'
		}, function() {
		$.post("work/applywork?workid="+row.id+"&fuserid="+row.userid+"&suserid="+userid+"&workname="+row.workname+"&fusername="+row.username+"&susername="+username+"&money="+row.money, {
			
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
	
