load();//加载遮罩
$(function(){
	loadData();
 });
//加载数据
function loadData(){
	var torf = false;
	if(bool=="true"){
		torf = true;
	}
	var modelType = $("#log_modelType").val();
	var userName = $("#log_userName").val();
	var deptName = $("#log_deptName").val();
	var tableName = $("#tableName").combobox('getValue');
	var params = {"modelType":modelType,"userName":userName, "tableName":tableName, "deptName":deptName};
    $("#tt").datagrid({
        title: "用户管理",
        url: "loginfo/log_search",
        iconCls: "icon-save",
        queryParams: params,
        pageSize:30,
        fit:true,
		fitColumns:true,
        sortName: 'logId',
        sortOrder: 'desc',
        remoteSort:false,
        idField: 'logId',
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        frozenColumns: [[
                         { field: 'ck', checkbox: true },
                         { field: 'editdata', title: '查看', width: 32,formatter:formatEditOper}
                     ]],
        columns: [[
            { field: "logId", title: "序号", width: 20, sortable: true ,hidden:true},
            { field: "modelType", title: "模块类型",align: "center", width: 40, sortable: true },
            { field: "msg", title: "内容",align: "left", width: 150, sortable: true },
	        { field: "loginUserName", title: "用户",align: "center", width: 50, sortable: true },
	        { field: "methodName", title: "方法名称", align: "center",width: 60, sortable: true ,hidden:true},
	        { field: "clientAgent", title: "客户端类型",align: "center", width: 50, 
	        	formatter: function (value, row, index) {
	                if (value!=null && value=='1') {
	                    return "Android";
	                } else if(value!=null && value=='2'){
	                	return "IPhone";
					}
	                return value;
	            }
	        },
	        { field: "clientType", title: "型号",align: "center", width: 50, sortable: true },
	        { field: "tenantId", title: "租户ID",align: "center", width: 50, sortable: true ,hidden:torf},
	        { field: "logTime", title: "时间",align: "center", width: 50, sortable: true }
        ]],
        onLoadSuccess : function(data){
        	disLoad();//取消遮罩
        }
    });
}

//查看按钮事件
function formatEditOper(val,row,index){
	return '<img title="编辑" alt="编辑" src="resources/css/jquery-easyui/themes/icons/search.png" onclick="editFieldTypeData('+index+')"/>';
}

/**
 * 编辑字段类型表单数据信息
 * @param index
 */
function editFieldTypeData(index){
	var row = $('#tt').datagrid('getData').rows[index];
    $("#lookDialog").dialog('open').dialog('setTitle', "日志基础信息");
    SetValue(row);
}

/*给对话框中各个控件赋值*/
function SetValue(o) {
    $("#Key1").text(o.modelType);
    $("#msg").text(o.msg);
    $("#userName").text(o.loginUserName==null?"":o.loginUserName);
    $("#methodName").text(o.methodName);
    $("#xh").text(o.clientAgent==null?"":o.clientAgent);
    $("#gjlx").text(o.clientType==null?"":o.clientType);
    $("#tenantId").text(o.tenantId==null?"":o.tenantId);
    $("#csgs").text(o.paramMuch==null?"":o.paramMuch);
    $("#sj").text(o.logTime);
}

//删除事件
function logDataRemove(){
	var ips = $("#tt").datagrid('getSelections');
	var tableName = $("#tableName").combobox('getValue');
	if(tableName==null||tableName==""){
		$.messager.alert("友情提示", "请先选择表名！");
		return false;
	}
    if (ips.length > 0) {
    	 var ids = [];
         $.each(ips, function (i, o) {
             ids.push(o.logId);
         });
        $.messager.confirm("友情提示", "您确认要删除数据吗？", function (r) {
            if (r) {
                $.post("loginfo/deleteLogData", { "ids": ids.join(','), "tableName":tableName}, function (data) {
                	if(data){
                		loadData();
                	}else{
                		$.messager.alert("友情提示", "数据删除失败！");
                	}
                },"json");
            }
        });
    } else {
        $.messager.alert("友情提示", "请先选择要删除的数据！");
    }
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