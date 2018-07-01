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
				text : "用户名称",
				value : "yhmc"
			}],
			onClick : function(value) {
				search(value);
			}
		}
	});
	
	$('#usertable').mtable({
		"url" : "user/userdata",
		"operSwitch" : [ {
			icon : 'fa fa-pencil-square-o',
			method : "edituser",
		}, {
			icon : 'fa fa-trash-o the-icons-red',
			method : "deleteuser",
		}],
		columns : [ {
			name : "userid",
			title : "userid",
			visible:false
		}, {
			name : "username",
			title : "用户名"
		}, {
			name : "password",
			title : "密码",
			breakpoints : "xs sm md lg"
		},{
			name : "phone",
			title : "电话"
		},{
			name : "age",
			title : "年龄"
		},{
			name : "sex",
			title : "性别",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.sex) )
				{
					case 0: text='男'; break;
					case 1: text='女';  break;
					default: text='';
				}
				return text;
			}
		},{
			name : "tenantid",
			title : "类别",formatter:function(value, options, rowData)
			{
				var text;
				switch(parseInt(rowData.tenantid) )
				{
					case 0: text='管理员'; break;
					case 1: text='学生';  break;
					case 2: text='老师';  break;
					default: text='';
				}
				return text;
			}
			
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
			
		}]
	});
	
}

	function search(){
		var msearch_name=$("#toolbar").find('[name=msearch_name]');
		var msearch_value=$("#toolbar").find('[name=msearch_value]');
		var name=msearch_name.val();
		var value=msearch_value.val();
		var workname;
		if(name=="yhmc"){
			username=value;
		}
		$("#usertable").mtable('reload',{
			url: "user/userdata",
			data: {
				username:username
			}
		});
	}
	
	function addMonitorspot(){
		$('#fm').clearForm();
		$("tenantid").val("0");
		$("sex").val("0");
		$("statue").val("0");
		$("#dlg_title").text('添加用户');
		$("#dlg").modal("show");
		url="admin/regist";
	}
	
	function edituser(row){
		$('#fm').clearForm();
		$("#dlg_title").text('修改用户信息');
		$("#fm").mform(row);
		$("#dlg").modal("show");
		url="users/adminupdateUserData";
		
	}
	
	function save(){
	
		 $("#fm").ajaxSubmit({
		    	url:url,
		    	success:function(result){
		    		//alert(result)
		    		if (result == "true") {
		    			$.mal({
		    				text : '操作成功!',
		    				type : 'success'
		    			});
						$("#dlg").modal("hide");
						$("#usertable").mtable("reload");
					} else {
						$.mal({
		    				text : '操作失败(可能是用户名已存在)!',
		    				type : 'error'
		    			});
					}
		    	},
		    	beforeSubmit:function(){
		    		return $("#fm").valid();
		    	}
		    });
	}
	
	function deleteuser(row){
		$.mal({
			text : '确定要删除吗?'
		}, function() {
			var userid = row.userid;
				$.post("user/deleteUser?userid="+userid, {
				}, function(result) {
					if (result) {
						$("#usertable").mtable("reload");
						$.mal({
							text : '操作成功',
							type : 'success' 
						});
					} else {
						$.mal({
							text : '删除失败!',
							type : 'error'
						});
					}
				}, 'json');
			
		});
	}
	
