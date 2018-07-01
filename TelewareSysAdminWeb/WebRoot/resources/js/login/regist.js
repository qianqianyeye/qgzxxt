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
			} else if ($('#sex').val() == '') {
				show_err_msg('请先选择性别！');
				$('#password').focus();
			}else if ($('#phone').val() == '') {
				show_err_msg('请先填写电话！');
				$('#password').focus();
			}else if ($('#age').val() == '') {
				show_err_msg('请先填写年龄！');
				$('#password').focus();
			}else {
				// 这里登陆提交表单数据信息
				var tenantId = $('#tenant').val();
				var name = $('#name').val();
				var passwd = $('#password').val();
				var sex = $('#sex').val();
				var phone = $('#phone').val();
				var age = $('#age').val();
				var urlPath = "admin/regist";
				var params = {
					"tenantid" : tenantId,
					"username" : name,
					"password" : passwd,
					"sex":sex,
					"phone":phone,
					"age":age,
					"statue":"0"
				};
				$.ajax({
					type : "POST", // 请求方式
					url : urlPath, // 请求路径
					data : params, // 传参
					dataType : 'json', // 返回值类型
			        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
					success : function(json) {
						if(json==true){
							alert("注册成功!等待审核中!")
						}else{
							alert('用户名已存在!')
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
