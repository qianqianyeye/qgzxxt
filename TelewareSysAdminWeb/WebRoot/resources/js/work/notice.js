$(function(){
	loadData();
 });
var url;

function loadData(){
	
	var userid=$(window.parent.document).find("#userid").text();
	var username=$(window.parent.document).find("#username").text();
	
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
		"url" : "work/notice?fuserid="+userid,
		"operSwitch" : [ {
			icon : 'fa fa-bell',
			method : "notice",
		}],
		columns : [ {
			name : "workid",
			title : "workid",
			visible:false
		},{
			name : "workname",
			title : "岗位名称"
		},{
			name : "susername",
			title : "接收人"
		},{
			name : "suserid",
			title : "接收人id",
			visible:false
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
		var userid=$(window.parent.document).find("#userid").text();
		var username=$(window.parent.document).find("#username").text();
		var name=msearch_name.val();
		var value=msearch_value.val();
		var workname;
		if(name=="gwmc"){
			workname=value;
		}
		$("#noticetable").mtable('reload',{
			url: "work/noticesearch?userid="+userid,
			data: {
				workname:workname
			}
		});
	}
	
	
	function notice(row){
		var fuserid=$(window.parent.document).find("#userid").text();
		var fusername=$(window.parent.document).find("#username").text();
		var suserid = row.suserid;
		var susername = row.susername;
		var workname = row.workname;
		var workid= row.workid;
		var statue = row.statue;
		
			$("#fuserid").val(fuserid);
			$("#fusername").val(fusername);
			
			$("#suserid").val(suserid);
			$("#susername").val(susername);
			
			$("#workname").val(workname);
			$("#workid").val(workid);
			$("#statue").val(statue);
			if(statue=='0'){
				$("#sta").val('未审核');
			}else if(statue=='1'){
				$("#sta").val('已通过');
			}else if(statue=='2'){
				$("#sta").val('未通过');
			}
			
			$("#dlg_title").text('发布通知');
			$("#dlg").modal("show");
			url="work/sendnotice?workname="+workname+"&susername="+susername;
	
	}
	
	function save(){
		 $("#fm").ajaxSubmit({
		    	url:url,
		    	success:function(result){
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
	
	
