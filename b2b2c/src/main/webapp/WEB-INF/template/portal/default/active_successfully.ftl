<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/sent_successfully.css" rel="stylesheet" type="text/css" />
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title>会员注册成功信息页-${cfg_webname?if_exists}</title>
</head>

<style>
	.sent_sucd_c span a{
		font-size:14px;
		margin-right:20px;
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
            <P class="cg">您的账号已成功激活</P>
            <div style="text-align:center;font-size:">
              您可以继续一下操作：
              <br/><br/>
              <span><a href="/login.html">登录会员后台</a></span><span><a href="/">返回首页</a></span>
            </div>
        </div>
        <div class="sent_sucd_d"></div>
    </div>

</div>

<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
