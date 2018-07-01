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
/*	//关闭窗口
	$("#closedId").click(function(){
		closeWindowPage();
	});
	*/
	//修改
	$("#updateId").click(function(){
		updateClassMenuContentData();
	});
}

function loadData(){
	var userid=$(window.parent.document).find("#userid").text();
	  $.post("users/getUsersData?userid="+userid, {}, function (data) {
		  var json = JSON.parse(data);
		  $("#username").val(json.username);
		  $("#phone").val(json.phone);
		  $("#age").val(json.age);
		  $("#sex").val(json.sex);
		  $("#userid").val(json.userid);
	  });
	
}
/**
 * 修改栏目菜单内容数据-按钮事件
 */
function updateClassMenuContentData(){
	
	 $('#classmenuForm').ajaxSubmit({
		 url:'users/updateUserData',
	    	success:function(result){
	    		//alert(result)
	    		if (result=='true') {
	    			//window.parent.document
	    			var username = $("#username").val();
	    			$(window.parent.document).find("#username").text(username);
	    			$.mal({
	    				text : '修改成功!',
	    				type : 'success'
	    			});
	    			
				} else {
					$.mal({
	    				text : '修改失败，用户名已存在！',
	    				type : 'error'
	    			});
				}
	    	},
	    	beforeSubmit:function(){
	    		return $("#classmenuForm").valid();
	    	}
	    });
}


