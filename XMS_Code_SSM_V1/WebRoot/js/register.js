//表单项是否通过验证
	var array={"email":false,"password":false,"nickname":false,"code":false};

//邮箱的验证
$(function(){
	array.email=false;
	$("#email").blur(function(){
		var email=$("#email").val();
		//前端
		//非空检测
		if(email==""){
			$("#p_email").html("邮箱不能为空").css("color","red");
			return;
		}
		//格式检测
		var regrex=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(!regrex.test(email)){
			$("#p_email").html("邮箱格式不正确").css("color","blue");
			return;
		}
		//服务器端检测
		//ajax检测
		$("#p_email").html("正在检测...").css("color","green");
		$.post(
			"/XMS_Code_SSM_V1/register/validateEmail.do",
			{"email":email},
			function(data){
				if(data){
					$("#p_email").html("邮箱可以注册").css("color","green");
					array.email=true;
				}else{
					$("#p_email").html("邮箱已注册").css("color","red");
				}
			}
		);
	});
});

//密码验证
$(function(){
	array.password=false;
	$("#password").blur(function(){
		var password= $("#password").val();
		//非空检测
		if(password==""){
			$("#p_password").html("密码不能为空").css("color","red")
			return;
		}
		//格式要求
		var regrex=/^[0-9]{6,12}$/;
		if(regrex.test(password)){
			$("#p_password").html("密码格式正确").css("color","green");
			array.password=true;
		}else{
			$("#p_password").html("密码格式不正确").css("color","red");
		}
		
	});
});

$(function(){
	array.nickname=false;
	$("#nickname").blur(function(){
		var nickname=$("#nickname").val();
		//非空检测
		if(nickname==""){
			$("#p_nickname").html("昵称不能为空").css("color","red");
			return;
		}
		//格式检测
		var regrex=/^[\u4e00-\u9fa5]{2,6}$/;
		if(regrex.test(nickname)){
			$("#p_nickname").html("昵称格式正确").css("color","green");
			array.nickname=true;
		}else{
			$("#p_nickname").html("昵称格式不正确").css("color","red");
		}
	});
});
	//生成验证码
function createCode(){
	$.post(
		"/XMS_Code_SSM_V1/register/createCode.do",
		function(data){
			if(data){
				$("#p_code").html("验证码已发送").css("color","green");
			}
		}
	);
}
//验证码的校验
$(function(){
	array.code=false;
	$("#code").blur(function(){
		var code=$("#code").val();
		//非空检测
		if(code==""){
			$("#p_code").html("验证码不能为空").css("color","red");
			return;
		}
		//格式检测
		var regexp = /^[0-9]{6}$/;
		if(!regexp.test(code)){
			$("#p_code").html("验证码格式不正确").css("color","red")
		}
		//ajax检测
		$.post(
			"/XMS_Code_SSM_V1/register/checkCode.do",
			{"code":code},
			function(data){
				if(data){
					$("#p_code").html("验证码正确").css("color","green")
					array.code=true;
				}else{
					$("#p_code").html("验证码不正确").css("color","red")
				}
			}
		);
			
		
	});
});
//表单提交处理
function putOn(){
	//检查表单项是否通过验证
	if(array.email&&array.password&&array.nickname&&array.code){
		document.forms[0].submit();
	}else{
		alert("请检查表单项");
		return false;
	}
}













