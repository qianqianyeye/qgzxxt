load();//加载遮罩
$(function(){
	$('#date').datepicker(
			{format: 'yyyy/mm/dd'}
	);
	initTenantSelect();
	loadData();
 });

function initTenantSelect() {
	Web.initTenantSelect2($("#log_tenantId"), function() {
		$("#log_tenantId").parent().show();
	}, function(record) {
		search();
	});
}


function getSearchUrl(){
var msearch_name=$("#toolbar").find('[name=msearch_name]');
	var msearch_value=$("#toolbar").find('[name=msearch_value]');
	var modelType="";
	var methodName="";
	var tenantId = $("#log_tenantId").val();
	if(msearch_name.val()=="log_modelType"){
		modelType =msearch_value.val();
		methodName="";
	}
	if(msearch_name.val()=="log_methodName"){
		methodName =msearch_value.val();
		modelType="";
	}
	var tableName = $("#table").val();
	var url = "loginfo/searchLogInfo?tenantId="+tenantId+"&modelType="+modelType+"&methodName="+methodName+"&tableName="+tableName+"&logType="+logType;
	return url;
}
function search(){
	$('#tt').mtable('reload',{
		url:getSearchUrl()
	});
}

//加载数据
function loadData(){
	
	var torf = false;
	if(bool=="true"){
		torf = true;
	}
	

	$("#toolbar").mboxtoolbar({
		"search" : {
			texts : [ {
				text : "模块类型",
				value : "log_modelType"
			}, {
				text : "方法名",
				value : "log_methodName"
			}],
			onClick : function(value) {
				search(value);
			}
		}
	});
	
	
	var url=getSearchUrl();
	//ddd
	
    $('#tt').mtable({
		"url" : url,
		"operSwitch" : [ {
			icon : 'fa fa-search',
			method : "editFieldTypeData",
		}, {
			icon : 'fa fa-trash-o the-icons-red',
			method : "logDataRemove",
		}],
		
		
		columns : [ {
			name : "logId",
			title : "序号",
			visible:false
		}, {
			name : "modelType",
			title : "模块类型"
		}, {
			name : "msg",
			title : "内容",
				breakpoints : "xs sm md lg"
		},{
			name : "loginUserName",
			title : "用户"
		}, {
			name : "methodName",
			title : "方法名称",
			visible:false
		}, {
			name : "clientAgent",
			title : "客户端类型",
			formatter: function (value, options, rowData) {
                if (value!=null && value=='1') {
                    return "Android";
                } else if(value!=null && value=='2'){
                	return "IPhone";
				}
                return value;
            }
		},{
			name : "clientType",
			title : "型号"
		},{
			name : "tenantId",
			title : "租户ID",
			visible:torf	
		},{
			name : "logTime",
			title : "时间"
		}]
	});

}




//redis清除事件
function initTable(){
	load();//加载遮罩
	$.post("loginfo/cleanRedis",function(result){
		loadData();
	});
}

/**
 * 编辑字段类型表单数据信息
 * @param index
 */
function editFieldTypeData(row){
	 $("#lookDialog").mform(row);
	 $("#dlg_title").text('日志基础信息');
	 $("#lookDialog").modal("show");
	 SetValue(row);
}

/*给对话框中各个控件赋值*/
function SetValue(o) {
    $("#Key1").val(o.modelType);
    $("#msg").val(o.msg);
    $("#userName").val(o.loginUserName==null?"":o.loginUserName);
    $("#methodName").val(o.methodName);
    $("#xh").val(o.clientAgent==null?"":o.clientAgent);
    $("#gjlx").val(o.clientType==null?"":o.clientType);
    $("#tenantId").val(o.tenantId==null?"":o.tenantId);
    $("#csgs").val(o.paramMuch==null?"":o.paramMuch);
    $("#sj").val(o.logTime);
    
}

//删除事件
function logDataRemove(row){
	
	
	$.mal({
		text : '确定要删除吗?'
	}, function() {
		var ids = [row.logId];
		var tableName=$("#table").val();
		$.post("loginfo/deleteLogData?ids="+ids, {
			tableName:tableName
		}, function(result) {
			if (result) {
			
				$.mal({
					text : '操作成功',
					type : 'success' ,
				});
				search();
			} else {
				$.mal({
					text : '删除失败',
					type : 'error'
				});
			}
		}, 'json');
	});
}

//根据日期来删除数据
function cleanLogData(){
	var logdate = $("#logdate").val();
	var tableName = $("#table").val();
	if(tableName==null||tableName==""){
		$.mal({
			text : '请先选择表名！',
			type : 'error' 
		});
		return false;
	}
	if(logdate==null||logdate==""){
		
		$.mal({
			text : '请先选择日期！',
			type : 'error' 
		});
		return false;
	}
	
	
	$.mal({
		text : '确定要删除吗'+logdate+"之前的数据吗？"
	}, function() {
		
		$.post("loginfo/cleanLogData", {
			"logdate": logdate, "tableName":tableName, "logType":logType
		}, function(result) {

				$.mal({
					text : '操作成功',
					type : 'success' 
				});
				search();
		}, 'json');
	});
}
//弹出加载层
function load() {  
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在加载数据，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
}
//取消加载层  
function disLoad() {  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
}