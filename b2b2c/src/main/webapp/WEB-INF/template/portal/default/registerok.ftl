<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/sent_successfully.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title>会员注册成功信息页</title>
</head>

<style>
	.sent_sucd_c span{
		font-size:14px;
	}
	.sent_sucd_c span a{
		font-size:14px;
	}
</style>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a> > <a href="/register.html">会员注册</a></P></div>

<div class="w960">
    <div class="sent_sucd_cont">
        <div class="sent_sucd_t"></div>
        <div class="sent_sucd_c">
            <P class="cg">恭喜您注册成功</P>
            <div>
            <#if isemailactive?if_exists=='0'>
               <span style="padding-left:50px;">尊敬的${user_name?if_exists},请登录您的邮箱<a href='http://www.${emailurl?if_exists}' target="_blank"><span style="color:red;">激活您的账号</span></a>或者<a href="/"><span style="color:red;">返回首页</a></span>
            <#else>
               <span style="padding-left:50px;"><span>尊敬的${user_name?if_exists},您现在可以<a href="/login.html"><span style="color:red;">登录后台</span></a>，完善您的信息或者<a href="/"><span style="color:red;">返回首页</a></span>
            </#if>
            </div>
        </div>
        <div class="sent_sucd_d"></div>
    </div>

</div>

<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
