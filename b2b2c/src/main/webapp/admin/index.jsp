<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>管理员登陆</title>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="/admin/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">   
    function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    }   
</script>
<script type="text/javascript">
  //获取cookeis的值
$(document).ready(function () {
	var names = getck("loginName");
	$("#uname").val(names);
});
function getck(sname) {//获取单个cookies
	var acookie = document.cookie.split("; ");
	for (var i = 0; i < acookie.length; i++) {
		var arr = acookie[i].split("=");
		if (sname == arr[0]) {
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

<s:form action="/adminlogin.action" method="post" validate="true">

 <div class="cont">
    <div class="main">
       <div class="main_l"><a href="<%=SysconfigFuc.getSysValue("cfg_index")%>"><img src="/admin/images/logo.jpg"  class="logo" border="0" /></a></div>
       <div class="main_r">
          <ul class="name">
          <li>
          	<s:textfield name="sysuser.user_name" id="uname" maxLength="20"/>
          </li>
          <li>
          	<s:password name="sysuser.passwd" maxLength="20"/>
          </li>
          </ul>
          <div class="clear"></div>	
           <ul class="yz">
          <li>
          		<s:textfield name="userrand" maxLength="4" cssClass="yz_input"/>
          </li>
          <li class="yz_pic">
          	<img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
          </li>
          </ul>
           <div class="clear"></div>
          <ul class="buttom">
            <li>
            	<s:submit value="" cssClass="submit" cssStyle="cursor:pointer;"/>
            	<s:token/>
            </li>
            <li class="check"><input type="checkbox" name="remusername" id="remusername" />记住用户名</li>
          </ul>
           <div class="clear"></div>
          <div class="ts">
          	<s:fielderror><s:param>adminloginMessage</s:param></s:fielderror>
          </div>
       </div>
    </div>
    <div class="bottom"></div>
 </div>
 
 </s:form>
 
</body>
</html>