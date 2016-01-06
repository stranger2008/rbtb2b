
//验证用户名
function UserNameIsNull(){
    $("#user_nameForm").html("");
	$("#user_nameForm").removeClass("errorform");
    var reg_username = /^[a-zA-Z][a-zA-Z0-9|_]{3,20}$/;//由数字、26个英文字母或者下划线组成的字符串
    if ($.trim($("#user_name").val()) == "") {
        $("#user_nameForm").removeClass("rightform");
		$("#user_nameError").html("用户名不能为空");
		$("#user_nameError").addClass("error");
		return false;
	}else if(!reg_username.test($("#user_name").val())){
	    $("#user_nameForm").removeClass("rightform");
	    $("#user_nameError").html("请输入以字母开头，4-20位的字母或数字");
	    $("#user_nameError").addClass("error");
		return false;
	}
	else if(!reg_username.test($("#user_name").val())){
	    $("#user_nameForm").removeClass("rightform");
	    $("#user_nameError").html("用户名只能由数字、字母或者下划线组成");
	    $("#user_nameError").addClass("error");
		return false;
	}
	else if(!checkUserName()){
	    $("#user_nameForm").removeClass("rightform");
	    $("#user_nameError").addClass("error");
	    return false;
	}
	else{
	    $("#user_nameError").html("");
	    $("#user_nameError").removeClass("error");
	    $("#user_nameForm").addClass("rightform");
	    return true;
	}
}
function UserNameForm(){
   $("#user_nameError").html("");
   $("#user_nameError").removeClass("error");
   if($.trim($("#user_name").val()) == ""){
     $("#user_nameForm").html("请输入以字母开头，4-20位的字母或数字");
     $("#user_nameForm").addClass("errorform");
   }
}
//验证密码
function PasswdIsNull(){
    $("#passwordForm").html("");
    $("#passwordForm").removeClass("errorform");
    var reg_passwd=/^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线
    if ($.trim($("#passwd").val()) == "") {
        $("#passwordForm").removeClass("rightform");
		$("#passwdError").html("密码不能为空");
		$("#passwdError").addClass("error");
		return false;
	}else if(!reg_passwd.test($("#passwd").val())){
	    $("#passwordForm").removeClass("rightform");
	    $("#passwdError").html("密码只能由6-20个字母、数字、下划线组成");
	    $("#passwdError").addClass("error");
		return false;
	}else if($.trim($("#passwd").val()) == ($.trim($("#user_name").val()))){
	    $("#passwordForm").removeClass("rightform");
	    $("#passwdError").html("密码不能与用户名一样");
	    $("#passwdError").addClass("error");
		return false;
	}else{
	    $("#passwdError").html("");
	    $("#passwdError").removeClass("error");
	    $("#passwordForm").addClass("rightform");
	    return true;
	}
}
function PasswordForm(){
   $("#passwdError").html("");
   $("#passwdError").removeClass("error");
   if($.trim($("#passwd").val()) == ""){
      $("#passwordForm").html("请输入6-20个英文字母、数字或符号");
      $("#passwordForm").addClass("errorform");
   }
}
//验证确认密码
function Confirm_pwdIsNull(){
    $("#confirmpwdForm").html("");
    $("#confirmpwdForm").removeClass("errorform");
    if($.trim($("#confirm_pwd").val()) == ""){
        $("#confirmpwdForm").removeClass("rightform");
	    $("#confirm_pwdError").html("确认密码不能为空");
	    $("#confirm_pwdError").addClass("error");
	    return false;
	}else if($.trim($("#passwd").val()) != $.trim($("#confirm_pwd").val())) {
	    $("#confirmpwdForm").removeClass("rightform");
	    $("#confirm_pwdError").html("密码与确认密码不一致");
	    $("#confirm_pwdError").addClass("error");
		return false;
    }else{
        $("#confirm_pwdError").html("");
        $("#confirm_pwdError").removeClass("error");
	    $("#confirmpwdForm").addClass("rightform");
		return true;
    }
}
function Confirm_pwdForm(){
   $("#confirm_pwdError").html("");
   $("#confirm_pwdError").removeClass("error");
   if($.trim($("#confirm_pwd").val()) == ""){
     $("#confirmpwdForm").html("请重复输入密码");
     $("#confirmpwdForm").addClass("errorform");
   }
}

//验证电子邮件
function EmailIsNull(){
   $("#emailForm").html("");
   $("#emailForm").removeClass("errorform");
   var reg_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//电子邮箱正则表达式
   if ($.trim($("#email").val()) == "") {
        $("#emailForm").removeClass("rightform");
		$("#emailError").html("电子邮箱不能为空");
		$("#emailError").addClass("error");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    $("#emailForm").removeClass("rightform");
	    $("#emailError").html("电子邮箱格式不正确");
	    $("#emailError").addClass("error");
		return false;
	}else if(!checkEmail()){
	    $("#emailForm").removeClass("rightform");
	    $("#emailError").addClass("error");
	    return false;
	}else{
	    $("#emailError").html("");
	    $("#emailError").removeClass("error");
	    $("#emailForm").addClass("rightform");
	    return true;
	}
}
function EmailForm(value){
   $("#emailError").html("");
   $("#emailError").removeClass("error");
   if($.trim($("#email").val()) == ""){
     $("#emailForm").html("请输入常用的电子邮箱,便于日后找回密码");
     $("#emailForm").addClass("errorform");
   }
}

//验证码
function RandsIsNull(){
    $("#randForm").html("");
    $("#randForm").removeClass("errorform");
    if ($.trim($("#rands").val()) == "") {
        $("#randForm").removeClass("rightform");
		$("#randsError").html("验证码不能为空");
		$("#randsError").addClass("error");
		return false;
	}else if(!validateCode()){
	    $("#randForm").removeClass("rightform");
    	$("#randsError").addClass("error");
	    return false;
	}else{
	    $("#randsError").html("");
	    $("#randsError").removeClass("error");
	    $("#randForm").addClass("rightform");
	    return true;
	}
}
function RandsForm(value){
   $("#randsError").html("");
   $("#randsError").removeClass("error");
   if($.trim($("#rands").val()) == ""){
     $("#randForm").html("请输入验证码");
     $("#randForm").addClass("errorform");
   }
}
//验证表单所有元素
function checkFromAll(){
  var flag = true;
  if(!UserNameIsNull()){
      flag = false;
  }
  if(!PasswdIsNull()){
      flag = false;
  }
  if(!Confirm_pwdIsNull()){
      flag = false;
  }
  
  if(!EmailIsNull()){
      flag = false;
  }

  if(!RandsIsNull()){
       flag = false;
  }
  return flag;
}
//提交表单
function registerForm(){
  if(checkFromAll()){
     document.forms[0].action = "/mall/member!mallregister.action";
	 document.forms[0].submit();
	 return true;
 }else{
    return false;   
 }
}

//清空错误文本内容
function ClearErrorInfo() {
    $("#user_nameError").html("");
	$("#passwdError").html("");
	$("#confirm_pwdError").html("");
	$("#emailError").html("");
	$("#randsError").html("");
}

//验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "imgrand.action?d=" + timenow;
}
 //当在用户名文本输入框失去焦点的时候，进行异步验证
  function checkUserName() {  
        var tips=true;
	    var user_name = $("#user_name").val();
		$.ajax({
		type:"post", 
		url:"/portal/member!checkUserName.action?user_name=" + user_name, 
		datatype:"json",
		async:false, 
		success:function (data) {
			if (data == "1") { 
				$("#user_nameError").html("该用户名已经被占用了，请重新输入");
				tips=false;
			}else if(data == "2"){
                $("#user_nameError").html("本系统不允许此类用户名注册，请重新输入");
                tips = false;
             }else{
			   $("#user_nameError").html("");
			   tips=true;
			}
		  }
		});
		return tips;
	}
//异步验证邮箱是否已被注册过
 function checkEmail(){
      var tips = true;
      var  email = $("#email").val();
      $.ajax({
        type:"post",
        url:"/portal/member!checkEmail.action?email="+email,
        datatype:"json",
        async:false,
        success:function(data){
            if(data == "1"){
               $("#emailError").html("邮箱已被占用，请重新输入");
               tips = false;
            }else{
               $("#emailError").html("");
			   tips = true;
            }
        }
      });
      return tips;
 }
 
//当验证码输入框失去焦点时，进行异步验证
	function validateCode() {
	    var tips=true;
	    var commentrand = $("#rands").val();
		$.ajax({
		   type:"post", 
		   url:"/portal/member!validateCode.action?commentrand=" + commentrand+"&ajaxRequestRandom="+Math.random(), 
		   datatype:"json", 
		   async:false, 
		   success:function (data) {
			if (data == "1") {
				$("#randsError").html("验证码不正确，请重新输入");
				tips=false;
			}else{
			   $("#randsError").html("");
			   tips=true;
			}
		}});
		return tips;
    }
    
 