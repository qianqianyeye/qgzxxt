var $wintree;
/**
 * 菜单权限配置管理
 */
$(function(){
	var height=document.body.scrollHeight-150;
	$("#iframepagedevicetree").load(function(){
		$("#iframepagedevicetree").contents().find(".footable-ibox-model").height(height);
	});
	initSearchToolbar();
	initTenantSelect();
	initTable();
	initComboxData();
	initSelectUser2();
	initdevicetree();
	$("#userId").click(function(){
		openDeviceTree();
	})
});

function initdevicetree(){
	$wintree = $('#selectUser');
}

function initSelectUser(){
	initdevicetree();
	$wintree.modal('hide');
}

/**
 * 选择人员iframe
 * @param index
 */
function openDeviceTree(){
	initdevicetree();
	$wintree.modal('show');
}

function initTenantSelect(){
	Web.initTenantSelect2(
		$("#search_tenantId"),
		function(){
			$("#search_tenantId").parent().show();
		},function(record){
			searchTenant();
	});
}

function initSearchToolbar(){
	$("#toolbar").mboxtoolbar({
		"search" : {
			texts : [ {
				text : "用户名称",
				value : 'username'
			}, {
				text : "角色名称",
				value : 'roleName'
			}, {
				text : "权限角色名称",
				value : 'subconfigName'
			} ],
			onClick : function(value) {
				search(value);
			}
		}
	});
}


/*初始化数据*/
function initTable(queryData) {
	var bsm_tenantName={};
    $("#grid").mtable({
        url: "rolepower/searchRolePower",
        data:queryData,
        checkbox:'checkbox',
		"operSwitch" : [ {
			icon : 'fa fa-pencil-square-o',
			method : "editRolePower",
		}, {
			icon : 'fa fa-trash-o the-icons-red',
			method : "deleteRolePower",
		} ],
        onLoadDataSuccess:function(data){
        	var rows=data.rows;
        	if(rows){
        		$.each(rows,function(i,row){
        			if(row.tenantModel){
        				bsm_tenantName[row.bsm]= row.tenantModel.remark;
              		  
              	  	}else{
              	  	bsm_tenantName[row.bsm]= "";
              	  	}
        		});
        	}
        }, 
        columns: [
                    { name: "objectId", title: "序号",visible:false},
                    { name: "tenantName", title: "租户名称",
         	              formatter: function (value, options, rowData) {
         	              return bsm_tenantName[rowData.bsm];
         	          }	
                    },
                   	{ name: "userId", title: "用户编号",visible:false },
                   	{ name: "username", title: "用户名称" },
                   	{ name: "roleName", title: "角色名称"},
                    { name: "subConfigId", title: "权限配置项目编号",visible:false},
    	          	{ name: "subConfigName", title: "权限角色名称"},
    	          	{ name: "isEnable", title: "是否启用"},
         	        { name: "district", title: "行政区号" }
                 ]
    });
}


/**
 * 初始化选择用户选项的弹出框
 */
function initSelectUser2(){
	$("#subConfigId").click(function(){
		$("#initModule").modal('show');
		initSudoConfigs();
	})
}


//sub
var selectSubConfigs;
var subconfigsData;
//初始化子系统权限配置保存
function getChecked() {
//	load("正在保存，请稍候...");
	selectSubConfigs = '';
	var nodes = $('#subconfigs').treeview('getChecked');
	//var nodes = $('#subconfigs').tree('getChecked', [ 'checked', 'indeterminate' ]);
	if (nodes.length <= 0) {
		$.mal({
			text : '请先选择！',
			type : 'warning'
		});
	} else {
		$.each(nodes, function(i, obj) {
			getSelectSubConfigs(obj, subconfigsData);
		});
		selectSubConfigs = selectSubConfigs.substring(1);
	}
	$("#subConfigId").val(selectSubConfigs);
	$("#initModule").modal('hide');
}
//初始化子系统权限数据
function getSelectSubConfigs(obj, subconfig) {
	if (obj.nodes) {
		return false;
	}else{
		selectSubConfigs += ","+obj.id+":"+obj.text;
	}
}

function deleteRolePowers(){
	var rows = $("#grid").mtable('getSelected');//选择所需要删除的sudo
	 if(rows.length<=0){
		 $.mal({
				text : '至少选择一项！',
				type : 'warning'
			});
	 }else{
		 deleteRolePower(null,rows);
	 }
}

/**
 * 这里是删除子类配置的数据信息内容
 */
function deleteRolePower(row,rows){
	 var arrayObj=[];
	 if(row){
		 arrayObj = [ row.objectId ];
	 }else{
	 	$.each(rows,function(i,row){
	 		arrayObj.push(row.objectId);
		});
	 }
  	 var strObjectIds = "";
   	 for(var item in arrayObj) {
   		 strObjectIds += arrayObj[item]+",";
   	 }
	 var urlPath = "rolepower/removeRolePower";
   	 var params = {"objectId":strObjectIds};
	   	$.mal({
			text : '您确认要删除这些数据吗？'
		}, function() {
			 $.ajax({  
		         url:urlPath,  
		         type:"post",  
		         dataType:"json",  
		         data:params,
		         contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		         success:function(data){//回调函数
		        	 if(data == "1"){
		        		 $.mal({
								text : '删除成功！',
								type : 'success'
							});
		        	 }else{
		        		 $.mal({
								text : '删除失败！',
								type : 'error'
						 });
		        	 }
		        	 $("#grid").mtable('reload');
		         },
		         error:function(errormsg){
		        	 
		         }
		     });
		});
}


function btnUpdateRolePower() {
	var objectId = $("#objectId").val();
	var userId = $("#userId").val();
	var roleName = $("#roleName").val();
	var subConfigId = $("#subConfigIdup").val();
	var subConfigIdup = $("#subConfigIdup").find("option:selected").text();
	var username = $("#username").val();
	var district = $("#district").val();
	var isEnable = $("input[name='isEnable']:checked").val();
	if (username == null && username == '') {
		 $.mal({
				text : '用户名称不能为空！',
				type : 'warning'
			});
		return;
	}
  	 var urlPath = "rolepower/updateRolePower";
  	 var params = {"objectId":objectId,"userId":userId,"roleName":roleName,"subConfigId":subConfigId,
  			 "district":district ,"isEnable":isEnable,"username":username,"subConfigName":subConfigIdup,"isSelected":isSelected};
	$.ajax({
        url:urlPath,  
        type:"post",  
        dataType:"json",  
        data:params,
        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
        success:function(data){//回调函数
       	 if(data == "1"){
       		 $.mal({
 				text : '修改成功！',
 				type : 'success'
 			});
       		$("#AddDialog").modal('hide');
       		ClearValue();
       		reloadPage();
       	 }else if (data == "2") {
       		 $.mal({
 				text : '权限编号已存在，请检查！',
 				type : 'error'
 			});
		}else if (data == "3") {
			 $.mal({
	 				text : '角色名称唯一性,请检查！',
	 				type : 'error'
	 			});
		}else{
			$.mal({
 				text : '修改失败，请您检查！',
 				type : 'error'
 			});
       	 }
       	reloadPage();
        },
        error:function(errormsg){
       	 
        }
    });  
}

/**
 * 展示添加对话框
 */
function ShowAddDialog() {
	ClearValue();
	$(".modal-title").text("添加子系统权限配置");
	$("#AddDialog").modal('show');
	$("#subConfigId").show();
	$("#subConfigIdup").hide();
	$("#btnUpdateRolePower").hide();
	$("#btnAddRolePower").show();
}


/**
 * 展示添加对话框
 */
function ShowAddAllDialog() {
    $("#AddAllDialog").dialog('open').dialog('setTitle', "一键初始化");
    
}


/**
 * 这里进行对设备用户进行赋值
 * @param loginId
 * @param username
 */
function setDeviceValue(loginId,username,organiseId,userid,duty){
	$("#district").val(organiseId);
	$("#username").val(username);
	$('#userId').val(userid);
	$('#roleName').val(duty);
	
	var roleNameType = document.getElementById("roleName");

	if (duty == null || duty == '') {
		roleNameType.disabled=false;
	}else {
		roleNameType.disabled=true;
	}
}


/*清除添加对话框中各个控件的数据*/
function ClearValue() {
	$('#userId').val('');
	$('#username').val('');
	$('#roleName').val('');
	$('#subConfigId').val('');
	$('#subConfigIdup').val('');
	$('#district').val('');
	$('#isEnable').val('');
}


function btnAddRolePower(){
	var userId = $("#userId").val();
	var roleName = $("#roleName").val();
	var subConfigIdtemp = $("#subConfigId").val();
	var district = $("#district").val();
	var tenantid = $("#tenantid").val('');
	var isEnable = $("#isEnable").val();
	var username = $("#username").val();
	if (userId  == null || userId == '') {
		 $.mal({
				text : '用户编号不能为空！',
				type : 'warning'
			});
		return;
	}
	if (roleName  == null || roleName == '') {
		 $.mal({
				text : '请选择用户编号为角色名称赋值！',
				type : 'warning'
			});
		return;
	}
	if (district  == null || district == '') {
		 $.mal({
				text : '请选择用户编号为行政区编号赋值！',
				type : 'warning'
			});
		return;
	}
	if (username == null || username == '') {
		 $.mal({
				text : '请选择用户编号为用户名称赋值！',
				type : 'warning'
			});
		 return;
	}
	if (subConfigIdtemp == null || subConfigIdtemp == '') {
		 $.mal({
				text : '权限编号不能为空！',
				type : 'warning'
			});
		return;
	}  
		var urlPath = "rolepower/addRolePower";
		var params = {"userId":userId,"roleName":roleName,"isEnable":isEnable,"district":district,
				"username":username,"subConfigSelect":subConfigIdtemp
				 };
		 $.ajax({  
	         url:urlPath,  
	         type:"post",  
	         dataType:"json",  
	         data:params,
	         contentType:"application/x-www-form-urlencoded;charset=UTF-8",
	         success:function(data){//回调函数
//	        	 var json = eval(data);
	        	 if(data == "1"){
	        		 $.mal({
	     				text : '添加成功！',
	     				type : 'success'
	     			});
	        		 setTimeout(function() {
	        			 $("#AddDialog").modal('hide');
	        			 ClearValue();
	        			 reloadPage();
	        			 initTenantSelect()
	  				}, 1200);
	        	 }else if (data == "2") {
	        		 $.mal({
		     				text : '权限角色名称重复添加，请检查！',
		     				type : 'error'
		     			});
				}else if (data == "3") {
					$.mal({
	     				text : '角色名称唯一，请重复添加！',
	     				type : 'error'
	     			});
				}else{
					$.mal({
	     				text : '添加失败！',
	     				type : 'error'
	     			});
	        	 }
	         },
	         error:function(errormsg){
	        	 
	         }
	     });  
	}
	
//初始化修改页面权限系统配置
var isSelected = false;
function initComboxData(){
	var urlPath = "contentConfig/getClassMenuCom";
	var params = {};
	 $.ajax({  
         url:urlPath,  
         type:"post",  
         dataType:"json",  
         data:params,
         contentType:"application/x-www-form-urlencoded;charset=UTF-8",
         success:function(data){//回调函数
        	 var option = "";
        	 for ( var i = 0; i < data.length; i++) {
        		 option+="<option value='"+data[i].id+"'>"+data[i].text+"</option>"
			 }
        	 $("#subConfigIdup").append(option);
         },
         error:function(errormsg){
        	 alert("这里获取的错误信息："+errormsg);
         }
     }); 
	 $("#subConfigIdup").change(function(){
		 isSelected = true;
	 })
}

function reloadPage(){
	$('#grid').mtable('reload');
}


function editRolePower(row){
	$("#btnUpdateRolePower").show();
	$("#btnAddRolePower").hide();
	$("#AddDialog").modal('show');
	$("#subConfigId").hide();
	$("#subConfigIdup").show();
	$(".modal-title").text("修改子系统权限配置");
	SetValue(row);//给修改对话框中各个控件赋值
}

/*给修改对话框中各个控件赋值*/
function SetValue(o) {
    $("#objectId").val(o.objectId);
    $("#userId").val(o.userId);
    $("#roleName").val(o.roleName);
    $("#username").val(o.username);
    $("#subConfigId").val(o.subConfigId);
    $("#subConfigIdup").val(o.subConfigId);
    $("#district").val(o.district);
/*    $("#subConfigName").val(o.subConfigName);*/    
    if(o.isEnable=='是'){
		 $("input:radio[name=isEnable]")[0].checked = true;
	 }else{
		 $("input:radio[name=isEnable]")[1].checked = true;
	 }
}


function btnAddAllRolePower(){
	var subConfigIdtemp = $("#subConfigIdAll").textbox('getValue');
	if (subConfigIdtemp == null || subConfigIdtemp == '') {
		$.messager.alert( "友情提示", "权限编号不能为空");
		return;
	}
		var urlPath = "rolepower/addAllRolePower";
		var params = {"subConfigSelect":subConfigIdtemp};
		 $.ajax({  
	         url:urlPath,  
	         type:"post",  
	         dataType:"json",  
	         data:params,
	         contentType:"application/x-www-form-urlencoded;charset=UTF-8",
	         success:function(data){//回调函数
	        	 if(data == "1"){
	        		 $.messager.alert("提示","添加成功!","info",function(){
	        			 $("#subConfigIdAll").val("");
	        			 $("#AddAllDialog").dialog('close');
	      			     reloadPage();
	      			   initTenantSelect();
	        		 });
				}else{
	        		 $.messager.alert("提示","添加失败!","info",function(){
	        		 });
	        	 }
	         },
	         error:function(errormsg){
	        	 
	         }
	     });  
	}

function search(obj) {
	
	var selectValue = obj.name;
	var searchValue = obj.value;
	var username = null;
	var subconfigName = null;
	var roleName = null;
	if(selectValue == 'username'){
		username = searchValue;
	}else if(selectValue == 'subconfigName'){
		subconfigName = searchValue;
	}else if(selectValue == 'roleName'){
		roleName = searchValue;
	}
	var queryParams = {
		username:username,
		subconfigName:subconfigName,
		roleName:roleName
    }
	initTable(queryParams);
}

/**
 * 查询
 */
function searchTenant(){
	var tenantId=  $('#search_tenantId').val();
    $("#grid").mtable('reload',{
        url: "rolepower/searchRolePower",
        data: {
            tenantId: tenantId
        }
    });
}

/**
 * 初始化子系统(temp)
 */
function initSudoConfigs() {
		init_tenantId = '783';
		$.post("subconfig/subconfigtree", function(result) {
			if (result) {
				subconfigsData = result;
				var o = setSubConfigChild(result);
				if (o.nodes.length > 0) {
					$("#sub").css("display", "block");
					$('#subconfigs').treeview({
						data : o.nodes, // 数据源
						emptyIcon : '',
						showCheckbox : true, // 是否显示复选框
						multiSelect : false, // 多选
						onNodeChecked : function(event, data) {
					          var nodes=data.nodes;
					          if(nodes){
					            $.each(nodes,function(i,node){
					              $('#subconfigs').treeview('checkNode', [ node.nodeId, { silent: true }]);
					            });
					          }
					        },
					        onNodeUnchecked : function(event, data) {
					          var nodes=data.nodes;
					          if(nodes){
					            $.each(nodes,function(i,node){
					              $('#subconfigs').treeview('uncheckNode', [ node.nodeId, { silent: true }]);
					            });
					          }
					        }
					});
				} else {
					$("#sub").css("display", "none");
				}
			}
			$("#initSudoConfig").modal("show");
		},'json')
}

function setSubConfigChild(subconfig) {
	var o = {
		"id" : subconfig.id,
		"text" : subconfig.text
	};
	if (subconfig.children) {
		var array = [];
		$.each(subconfig.children, function(i, obj) {
			array[i] = setSubConfigChild(obj);
		});
		if(array.length>0){
			o["nodes"] = array;
		}else{
			o["nodes"] = null;
		}
	}
	return o;
}