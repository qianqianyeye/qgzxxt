document.onkeydown = function(e) {
	if ($(".bac").length == 0) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			var obtnLogin = document.getElementById("submit_btnlogin")
			obtnLogin.focus();
		}
	}
}
var loginflag = false;
$(function() {
	// 提交表单
	$('#submit_btnlogin').click(function() {
		if(!loginflag){

			if($('#tenant').val()== ''){
				show_err_msg('请先选择用户身份！');
			}else if ($('#tenantname').val() == '') {
				show_err_msg('用户名称还没填呢！');
				$('#name').focus();
				
			} else if ($('#password').val() == '') {
				show_err_msg('密码还没填呢！');
				$('#password').focus();
			} else {
				// 这里登陆提交表单数据信息
				var tenantId = $('#tenant').val();
				var name = $('#name').val();
				var passwd = $('#password').val();
				var urlPath = "admin/tenantlogin";
				var params = {
					"tenantId" : tenantId,
					"name" : name,
					"password" : passwd
				};
				$.ajax({
					type : "POST", // 请求方式
					url : urlPath, // 请求路径
					data : params, // 传参
					dataType : 'json', // 返回值类型
			        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
					success : function(json) {
						//alert(json)
						if(json !=false){
							//alert(json)
							window.location.href='goto/home?tenantId='+tenantId+"&userid="+json+"&username="+name;
						}else{
							alert('用户不存在或者输入有误或者账号还在审核中!')
						}
					}
				});
			}
		}
	});
});

function regist(){
	window.location.href='goto/regist';
}

function show_err_msg(message){
	$('#error').show(); 
	$('#error').find('[name=message]').text(message);
	window.setTimeout(hide,1500); 
	function hide() 
	{ 
		$('#error').hide(); 
	} 
}
