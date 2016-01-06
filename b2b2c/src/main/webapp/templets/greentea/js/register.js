
//验证用户名
function UserNameIsNull(){
    $("#user_nameForm").html("");
	$("#user_nameForm").removeClass("errorform");
    var reg_username = /^[a-zA-Z][a-zA-Z0-9|_]{0,20}$/;//由数字、26个英文字母或者下划线组成的字符串
    if ($.trim($("#user_name").val()) == "") {
        $("#user_nameForm").removeClass("rightform");
		$("#user_nameError").html("用户名不能为空");
		$("#user_nameError").addClass("error");
		return false;
	}else if(!reg_username.test($("#user_name").val())){
	    $("#user_nameForm").removeClass("rightform");
	    $("#user_nameError").html("用户名只能以英文字母开头");
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
	}else{
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
//验证联系人
function Contact_nameIsNull(){
   $("#contactnameForm").html("");
   $("#contactnameForm").removeClass("errorform");
   if ($.trim($("#contact_name").val()) == "") {
        $("#contactnameForm").removeClass("rightform");
		$("#contact_nameError").html("联系人不能为空");
		$("#contact_nameError").addClass("error");
		return false;
	}else{
	    $("#contact_nameError").html("");
	    $("#contact_nameError").removeClass("error");
	    $("#contactnameForm").addClass("rightform");
	    return true;
	}
}
function Contact_nameForm(){
   $("#contact_nameError").html("");
   $("#contact_nameError").removeClass("error");
   if($.trim($("#contact_name").val()) == ""){
     $("#contactnameForm").html("请输入联系人姓名,方便日后联系您");
     $("#contactnameForm").addClass("errorform");
   }
}
//验证所属地区
function Areafouce(){
        var areacount=0;	
           //验证所属地区的选择下拉框是否已选择		           
	    if($("select[id^=arealevel]").length>0){
	  
	        $("select[id^=arealevel]").each(function(){	
	              if($(this).val()=="0"){ 
	                  areacount+=1;
				}
	        });
	    
	        if(areacount>0)
	        {
	            $("#areaForm").removeClass("rightform");
				$("#arealistError").html("请选择所属地区");
				$("#arealistError").addClass("error");
				return false;
	        } else{				        
	             $("#arealistError").html("");
	             $("#arealistError").removeClass("error");
	             $("#areaForm").addClass("rightform");
	             return true;
	        }
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
   Areafouce();
   $("#emailError").html("");
   $("#emailError").removeClass("error");
   if($.trim($("#email").val()) == ""){
     $("#emailForm").html("请输入常用的电子邮箱,便于日后找回密码");
     $("#emailForm").addClass("errorform");
   }
}
//验证电话号码
function PhoneIsNull(){
   $("#phoneForm").html("");
   $("#phoneForm").removeClass("errorform");
   var reg_phone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;//电话号码正则表达式]
   var value = $("#phone").val();
   if (value != '' && !reg_phone.test(value)) {
        $("#phoneForm").removeClass("rightform");
	    $("#phoneError").html("电话号码格式不正确");
	    $("#phoneError").addClass("error");
		return false;
	}else if(reg_phone.test(value)){
	    $("#phoneError").html("");
	    $("#phoneError").removeClass("error");
	    $("#phoneForm").addClass("rightform");
	    return true;
	}else{
	    $("#phoneError").html("");
	    $("#phoneError").removeClass("error");
	    return true;
	}
}
function phoneForm(){
     $("#phoneError").html("");
	 $("#phoneError").removeClass("error");
    if($("#phone").val() == ""){
        $("#phoneForm").removeClass("rightform");
	    $("#phoneForm").html("电话号码格式为:区号-号码");
	    $("#phoneForm").addClass("errorform");
	}
}
//验证手机号码
function CellphoneIsNull(){
   $("#cellphoneForm").html("");
   $("#cellphoneForm").removeClass("errorform");
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
   var value = $("#cellphone").val();
   if(value!='' && !reg_phone.test(value)){
        $("#cellphoneForm").removeClass("rightform");
	    $("#cellphoneError").html("手机号码格式不正确");
	    $("#cellphoneError").addClass("error");
		return false;
	}else if(reg_phone.test(value)){
	    $("#cellphoneError").html("");
	    $("#cellphoneError").removeClass("error");
	    $("#cellphoneForm").addClass("rightform");
	    return true;
	}else{
	    $("#cellphoneError").html("");
	    $("#cellphoneError").removeClass("error");
	    return true;
	}
	
}
function CellphoneForm(){
    $("#cellphoneError").html("");
	$("#cellphoneError").removeClass("error");
    if($("#cellphone").val() == ""){
        $("#cellphoneForm").removeClass("rightform");
	    $("#cellphoneForm").html("手机号码格式以13/14/15/18等开头");
	    $("#cellphoneForm").addClass("errorform");
	}
}

//验证公司名称
function Cust_nameIsNull(){
    $("#custnameForm").html("");
	$("#custnameForm").removeClass("errorform");
    if ($.trim($("#cust_name").val()) == "") {
        $("#custnameForm").removeClass("rightform");
		$("#cust_nameError").html("公司名称不能为空");
		$("#cust_nameError").addClass("error");
		return false;
	}else if(!checkCompanyname()){
	    $("#custnameForm").removeClass("rightform");
	    $("#cust_nameError").addClass("error");
	    return false;
	}else{
	    $("#cust_nameError").html("");
	    $("#cust_nameError").removeClass("error");
	    $("#custnameForm").addClass("rightform");
	    return true;
	}
}
function Cust_nameForm(){
    $("#cust_nameError").html("");
	$("#cust_nameError").removeClass("error");
    if ($.trim($("#cust_name").val()) == ""){
       $("#custnameForm").html("请输入公司名称");
	   $("#custnameForm").addClass("errorform");
    }
}
//验证供应产品
function Cust_supplyIsNull(){
    $("#cust_supplyForm").html("");
	$("#cust_supplyForm").removeClass("errorform");
	var radioes = document.getElementsByName("member.cust_rage");
    var rad_val = "2";
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			rad_val = radioes[i].value;
		}
	}
	
	if (rad_val == "0" || rad_val == "2") {
	    if ($("#cust_supply").val() == "") {
	        $("#cust_supplyForm").removeClass("rightform");
			$("#cust_supplyError").html("供应产品不能为空");
			$("#cust_supplyError").addClass("error");
			return false;
		}else{
		    $("#cust_supplyError").html("");
		    $("#cust_supplyError").removeClass("error");
		    $("#cust_supplyForm").addClass("rightform");
		    return true;
		}
	}else{
	    $("#cust_supplyError").html("");
	    $("#cust_supplyError").removeClass("error");
	    $("#cust_supplyForm").addClass("rightform");
	    return true;
	}
}
function Cust_supplyForm(){
    $("#cust_supplyError").html("");
	$("#cust_supplyError").removeClass("error");
    var radioes = document.getElementsByName("member.cust_rage");
    var rad_val = "2";
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			rad_val = radioes[i].value;
		}
	}
	if (rad_val == "0" || rad_val == "2") {
	    if ($("#cust_supply").val() == "") {
			$("#cust_supplyForm").html("请输入供应产品");
			$("#cust_supplyForm").addClass("errorform");
		}
	}
}

//验证采购产品
function Cust_stockIsNull(){
    $("#cust_stockForm").html("");
	$("#cust_stockForm").removeClass("errorform");
	var radioes = document.getElementsByName("member.cust_rage");
    var rad_val = "2";
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			rad_val = radioes[i].value;
		}
	}
	
	if (rad_val == "1" || rad_val == "2") {
		if ($("#cust_stock").val() == "") {
		    $("#cust_stockForm").removeClass("rightform");
			$("#cust_stockError").html("采购产品不能为空");
			$("#cust_stockError").addClass("error");
			return false;
		}else{
		    $("#cust_stockError").html("");
		    $("#cust_stockError").removeClass("error");
		    $("#cust_stockForm").addClass("rightform");
		    return true;
		}	
	}else{
	    $("#cust_stockError").html("");
	    $("#cust_stockError").removeClass("error");
	    $("#cust_stockForm").addClass("rightform");
	    return true;
	}
}
function Cust_stockForm(){
    $("#cust_stockError").html("");
	$("#cust_stockError").removeClass("error");
	var radioes = document.getElementsByName("member.cust_rage");
    var rad_val = "2";
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			rad_val = radioes[i].value;
		}
	}
	if (rad_val == "1" || rad_val == "2") {
		if ($("#cust_stock").val() == "") {
			$("#cust_stockForm").html("请输入采购产品");
			$("#cust_stockForm").addClass("errorform");
		}
	}
}

//验证公司类型
function Cust_typeIsNull(){
    $("#custtypeForm").html("");
	$("#custtypeForm").removeClass("errorform");
    if($("select[id^=cust_type]").val()=="0"){
        $("#custtypeForm").removeClass("rightform");
		$("#cust_typeError").html("公司类型不能为空");
		$("#cust_typeError").addClass("error");
		return false;          
    }else{
        $("#cust_typeError").html("");
        $("#cust_typeError").removeClass("error");
        $("#custtypeForm").addClass("rightform");
        return true;
    }
}
function Cust_typeForm(){
     $("#cust_typeError").html("");
     $("#cust_typeError").removeClass("error");
    if($("select[id^=cust_type]").val()=="0"){
		$("#custtypeForm").html("请选择公司类型");
		$("#custtypeForm").addClass("errorform");
    }
}
//验证经营模式
function StatusIsNull(){
    var checks = document.getElementsByName("member.client_status");
	var res = false;
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			res = true;
		}
	}
	if(res==false) {
	    $("#statusForm").removeClass("rightform");
		$("#statusError").html("请选择经营模式");
		$("#statusError").addClass("error");
		return false;
	}else{
	    $("#statusError").html("");
	    $("#statusError").removeClass("error");
	    $("#statusForm").addClass("rightform");
	    return true;
	}
}
//验证所属行业分类
function Catefouce(){
     //验证所属分类的选择下拉框是否已选择
     if($("select[id^=level]").length>0){
        var catecount=0;
        $("select[id^=level]").each(function(){	
              if($(this).val()=="0"){ 
                  catecount+=1;
			}
        });
        if(catecount>0)
        {
            $("#catForm").removeClass("rightform");
			$("#divlistError").html("请选择所属行业");
			$("#divlistError").addClass("error");
			return false;
        }else{
            $("#divlistError").html("");
            $("#divlistError").removeClass("error");
            $("#catForm").addClass("rightform");
			return true;
        }       
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
   Catefouce();
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
  if(!Contact_nameIsNull()){
       flag = false;
  }
  if(!Areafouce()){
       flag = false;
  }
  if(!PhoneIsNull()){
      flag = false;
  }
  if(!CellphoneIsNull()){
      flag = false;
  }
  if(!EmailIsNull()){
      flag = false;
  }
  var radioes = document.getElementsByName('member.mem_type');
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			if (radioes[i].value == "0") {
				  if(!Cust_nameIsNull()){
				     flag = false;
				  }
				  if(!Cust_typeIsNull()){
				       flag = false;
				  }
				  if(!StatusIsNull()){
				       flag = false;
				  }
				  if(!Catefouce()){
				       flag = false;
				  }
				  if(!Cust_supplyIsNull()){
				       flag = false;
				  }
				  if(!Cust_stockIsNull()){
				       flag = false;
				  }
			} 
		}
	}

  if(!RandsIsNull()){
       flag = false;
  }
  return flag;
}
//提交表单
function registerFrom(){
  if(checkFromAll()){
    document.forms[0].action = "/portal/member!register.action";
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
	$("#cellphoneError").html("");
	$("#emailError").html("");
	$("#contact_nameError").html("");
	$("#cust_nameError").html("");
	$("#cust_supplyError").html("");
	$("#cust_stockError").html("");
	$("#cust_typeError").html("");
	$("#statusError").html("");
	$("#randsError").html("");
	$("#divlistError").html("");
	$("#arealistError").html("");
}

//验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "imgrand.action?d=" + timenow;
}
//当选择会员类型为企业时，才显示公司信息
function getRadioValue(name) {
	var radioes = document.getElementsByName(name);
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			if (radioes[i].value == "0") {
				$("#bus").css("display","");
			} else {
				$("#bus").css("display","none");
			}
		}
	}
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
		   url:"/portal/member!validateCode.action?commentrand=" + commentrand, 
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

//当公司名称已经存在时，进行验证
function checkCompanyname(){
        var tips=true;
	    var cust_name = $("#cust_name").val();
	    var vtext=encodeURIComponent(encodeURIComponent(cust_name));
		$.ajax({
		   type:"post", 
		   url:"/portal/member!checkCompanyname.action?cust_name="+vtext, 
		   datatype:"json", 
		   async:false, 
		   success:function (data) {
			if (data == "1") {
				$("#cust_nameError").html("公司名称已经存在，请重新输入");
				tips=false;
			}else{
			   $("#cust_nameError").html("");
			   tips=true;
			}
		}});
		return tips;
  }

//注册页面加载时公司类型数据的方法,添加到select中
function cust_type_select(){
 	$.ajax({  	 
            type: "post",    	     
            url: "/portal/member!typelist.action",       
            datatype:"json",
            async:false,
            success: function(data){          
   	           $("#typelist").append(data);
   	        }
     });
 } 
 //当选择不同公类别时，有不同的产品
 function rage_select(name){
    var radioes = document.getElementsByName(name);
    var rad_val = "2";
	for (var i = 0; i < radioes.length; i++) {
		if (radioes[i].checked) {
			rad_val = radioes[i].value;
		}
	}
	//alert(rad_val);
	if (rad_val == "0") {
	    $("#supply_star").html("*");
	    $("#buy_star").html("");
	} 
	//alert(rad_val == "1");
	if(rad_val == "1") {
	    $("#supply_star").html("");
	    $("#buy_star").html("*");
	}
    if(rad_val == "2"){
	     $("#supply_star").html("*");
	    $("#buy_star").html("*");
	     $("#chanpin").css("padding-left","0px");
	     $("#gongying").css("padding-left","0px");
	}
 }
 