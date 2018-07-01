/**
 * 新增内容发布处理信息
 */

$(function(){
	initHandlerEvent();
	loadData();
});



/**
 * 刷新窗口的内容
 */
function reflashWindow(){
	window.location.reload(true);
}


/**
 * 初始化事件信息
 */
function initHandlerEvent(){
	$("#updateId").click(function(){
		updateClassMenuContentData();
	});
}

function loadData(){
	var userid=$(window.parent.document).find("#userid").text();
	  $.post("sign/getSignWork?userid="+userid, {}, function (data) {
		
		  var json = JSON.parse(data);
		  var list="";
		  for(var i=0;i<json.length;i++){
			  list += '<option value ="'+json[i].id+'">'+json[i].workname+'</option>'
		  }
		  if(list!=""){
			  $("#workid").html(list);
		  }
		
	  });
	
}
/**
 * 修改栏目菜单内容数据-按钮事件
 */
function updateClassMenuContentData(){
	if($("#workid").val()!='fff'){
		var workid=$("#workid").val();
		window.location.href='goto/sign?workid='+workid;
	}else{
		alert("暂无岗位可打卡")
	}

}


