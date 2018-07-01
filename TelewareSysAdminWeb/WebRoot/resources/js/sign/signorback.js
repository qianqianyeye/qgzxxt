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
	$("#signorback").click(function(){
		updateClassMenuContentData();
	});
	
	$("#timeconfirm").click(function(){
		timeconfirm();
	});
}

function loadData(){
	var userid=$(window.parent.document).find("#userid").text();
	var workid=$("#workid").val();
	  $.post("sign/initSign?userid="+userid+"&workid="+workid, {}, function (data) {
		  var json = JSON.parse(data);
		  $("#signstatue").text(json.nextstatue);
		  if(json.money!=null){
			  $("#timelength").val(json.timelength);
			  $("#money").val(json.money);
		  }
	  });
	
}
/**
 * 修改栏目菜单内容数据-按钮事件
 */
function updateClassMenuContentData(){
	var workid=$("#workid").val();
	var userid=$(window.parent.document).find("#userid").text();
	 $.post("sign/signorback?userid="+userid+"&workid="+workid, {}, function (data) {
		//  alert(data)
		 if(data=='true'){
			 window.location.href='goto/sign?workid='+workid;
			//alert("操作成功!")
			 $.mal({
 				text : '申请成功！',
 				type : 'success'
 			});
			 
		 }else{
			 //alert("操作失败!")
			 $.mal({
					text : '操作失败!',
					type : 'error'
				});
		 }
	  });

}

function timeconfirm(){
	var signstatue=$("#signstatue").text();
	if(signstatue=="签退"){
		 $.mal({
				text : '不要忘记先签退哦!',
				type : 'error'
			});
	}else{
		var workid=$("#workid").val();
		var userid=$(window.parent.document).find("#userid").text();
		 $.post("sign/applytime?userid="+userid+"&workid="+workid, {}, function (data) {
				 //alert(data)
				 if(data=='true'){
					
					 $.mal({
		    				text : '申请成功！',
		    				type : 'success'
		    			});
					// window.location.href='goto/sign?workid='+workid;
				 }else{
					 $.mal({
							text : '请不要重复申请或者请先签到签退!',
							type : 'error'
						});
				 }
			  });
	}
	
	
}

