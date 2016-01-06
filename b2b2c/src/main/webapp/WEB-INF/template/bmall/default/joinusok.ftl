<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<title>会员注册成功-${cfg_mallwebname?if_exists}</title>
</head>
<style>
</style>
<body>
	<center>
    <p>恭喜您注册成功</p>
    <p>
	    <#if isemailactive?if_exists=='0'>
	       尊敬的${user_name?if_exists},请登录您的邮箱<a href='http://www.${emailurl?if_exists}' style="color:red;" target="_blank">激活您的账号</a>
	    <#else>
	       尊敬的${user_name?if_exists},您现在可以<a href="/signin.html" style="color:red;">登录后台</a>
	    </#if>
    </p>
    </center>
</body>
</html>
