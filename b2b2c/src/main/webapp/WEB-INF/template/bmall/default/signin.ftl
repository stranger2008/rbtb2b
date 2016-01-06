<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>会员登录-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">

<script type="text/javascript">   
    function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    }   
    
  //获取cookeis的值
$(document).ready(function () {
	var username = getck("signinName");
	$("#user_name").val(username);
});
function getck(susername) {//获取单个cookies
	var acookie = document.cookie.split("; ");
	for (var i = 0; i < acookie.length; i++) {
		var arr = acookie[i].split("=");
		if (susername == arr[0]) {
			if (arr.length > 1) {
				return unescape(arr[1]);
			} else {
				return "";
			}
		}
	}
	return "";
}

</script>
</head>
<body>
   <div class="top">
      <a href="${cfg_mallindexurl}" target="_blank"><img src="/templets/bmall/images/logo_06.gif" class="f_left"/></a>
      <a href="/mallhelp.html" class="f_right">帮助中心</a>
      <div class="clear"></div>
   </div>
   
   
   <div class="cont">
      <div class="form">
        <@s.form action="/mall/memberuser!signinUser.action" method="post" validate="true">
            <div class="form_hd">用户登录</div>
            <div class="field">
                <label>用户名</label>
                <@s.textfield id="user_name" name="memberuser.user_name" maxLength="20"  cssClass="loginN"/>
                <div class="errorDiv"><@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror></div>
            </div>
            <div class="field">
                <label>密&nbsp;&nbsp;码</label>
                <@s.password id="passwd" name="memberuser.passwd" maxLength="20"  cssClass="loginN"/>
                <div class="errorDiv"><@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror></div>
            </div>
            <div class="field">
                <label>验证码</label>
                <@s.textfield name="commentrand" maxLength="4" cssClass="loginY"/>
                <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
                <div class="errorDiv"><@s.fielderror><@s.param>commentrand</@s.param></@s.fielderror></div>
            </div>
            <div class="safe">
                <span><input type="checkbox" name="mallusername" id="mallusername" /><label>记住用户名</label></span>
                <span><input  type="checkbox" name="autosignin" id="autosignin"><label>自动登录</label></span>
            </div>    
            <div class="submit">
            	<input type="hidden" name="loc" id="refloc" value=""/>
               <@s.submit cssClass="J_Submit" tabindex="5" type="submit" value="登   录"/>
               <a class="forget_pw" tabindex="6" target="_blank" href="/mall/memberuser!executepwd.action">忘记密码?</a>
            </div>
            <P  class="tishi"><span>提示：</span>还不是瑞宝通会员？点击这里<a href="/joinus.html">免费注册</a></P>
          </div>
      <div class="pic">
        <img width="435" height="276" style="" alt="" src="/templets/bmall/images/T1kxiLXiJqXXXXXXXX-435-276.jpg">
      </div>
      <div class="clear"></div>
   </div>
   </@s.form>
   
  <!--底部开始-->
  <#include "/WEB-INF/template/bmall/default/footer.ftl">
 <!--底部结束-->
 
</body>
</html>

<script type="text/javascript">   

 var urlhref = window.location.href;
   var locstr = '?loc=';
   if(urlhref.indexOf(locstr) > -1){
   	  var posi = urlhref.indexOf('?loc=');
   	  var loc = urlhref.substring(posi,urlhref.length);
   	  loc = loc.replace(locstr,'');
   	  document.getElementById('refloc').value = loc;
   }

</script>

