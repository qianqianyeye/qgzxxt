$(function(){
	//initTenantSelect();
	//alert("a")
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
	
	$('#registtable').mtable({
		"url" : "regist/registlist",
		
		"operSwitch" : [ {
			icon : 'fa fa-plus',
			method : "pass",
		}, {
			icon : 'fa fa-trash-o the-icons-red',
			method : "refuse",
		}],
		columns : [ {
			name : "userid",
			title : "userid",
			visible:false
		}, {
			name : "username",
			title : "用户名"
		}, {
			name : "phone",
			title : "电话"
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
			name : "age",
			title : "年龄"
		}]
	});
	
}

	function search(){
		var msearch_name=$("#toolbar").find('[name=msearch_name]');
		var msearch_value=$("#toolbar").find('[name=msearch_value]');
		var name=msearch_name.val();
		var value=msearch_value.val();
		var yhmc;
		if(name=="yhmc"){
			yhmc=value;
		}
		$("#registtable").mtable('reload',{
			url: "regist/registlist",
			data: {
				username:yhmc
			}
		});
	}
	
	function pass(row){
		$.mal({
			text : '确定要通过吗?'
		}, function() {
			//alert(row.userid)
			var ids=row.userid
			$.post("regist/passorrefuse?userid="+ids+"&statue=1", {
				
			}, function(result) {
				if (result) {
					$("#registtable").mtable("reload");
					$.mal({
						text : '操作成功',
						type : 'success' 
					});
				} else {
					$.mal({
						text : '删除失败',
						type : 'error'
					});
				}
			}, 'json');
		});
	}
	function refuse(row){
		$.mal({
			text : '确定要删除吗?'
		}, function() {
			alert(row.userid)
			var ids=row.userid
			$.post("regist/passorrefuse?userid="+ids+"&statue=0", {
			}, function(result) {
				if (result) {
					$("#registtable").mtable("reload");
					$.mal({
						text : '操作成功',
						type : 'success' 
					});
				} else {
					$.mal({
						text : '删除失败',
						type : 'error'
					});
				}
			}, 'json');
		});
	}
	
	
	function deleteSelect(){
		var registtableSelect=$("#registtable").mtable("getSelected");
		//var ms=JSON.stringify(monitorspotSelect);
		if(registtableSelect.length>0){
		var ids = new Array();
		for(var i=0;i<registtableSelect.length;i++){
			ids.push(registtableSelect[i].userid)
		}
		
		$.mal({
			text : '确定要删除吗?'
		}, function() {
			
			$.post("user/delteorpassSelect?userid="+ids+"&statue=0", {
				
			}, function(result) {
				if (result) {
					$("#registtable").mtable("reload");
					$.mal({
						text : '操作成功',
						type : 'success' 
					});
				} else {
					$.mal({
						text : '删除失败',
						type : 'error'
					});
				}
			}, 'json');
		});
		}else{
		
			$.mal({
				text : '请先选择要删除的数据'
			})
		}
		
	}
	
	function passSelect(){
		var registtableSelect=$("#registtable").mtable("getSelected");
		//var ms=JSON.stringify(monitorspotSelect);
		if(registtableSelect.length>0){
		var ids = new Array();
		for(var i=0;i<registtableSelect.length;i++){
			ids.push(registtableSelect[i].userid)
		}
		
		$.mal({
			text : '确定要通过吗?'
		}, function() {
			
			$.post("user/delteorpassSelect?userid="+ids+"&statue=1", {
				
			}, function(result) {
				if (result) {
					$("#registtable").mtable("reload");
					$.mal({
						text : '操作成功',
						type : 'success' 
					});
				} else {
					$.mal({
						text : '删除失败',
						type : 'error'
					});
				}
			}, 'json');
		});
		}else{
		
			$.mal({
				text : '请先选择要通过的数据'
			})
		}
		
	}