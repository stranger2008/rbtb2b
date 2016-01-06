<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/message.css" rel="stylesheet" type="text/css" />
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/webmessage.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title>网站留言-${cfg_webname?if_exists}</title>
<script>
window.onload=function() {
    var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
        .text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
            $("html, body").animate({ scrollTop: 0 }, 120);
    }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);
        }
    };
    $(window).bind("scroll", $backToTopFun);
    $(function() { $backToTopFun(); });
};
$(document).ready(function() {	
		$("#reset").click(function() {
			$("#title").attr("value",'');
			$("#content").attr("value",'');
			$("#name1").attr("value",'');
			$("#phone").attr("value",'');
			$("#email").attr("value",'');
			$("#qq").attr("value",'');
			$("#textfield7").attr("value",'');
			$("#textfield8").attr("value",''); 		
		});
	});
</script>
</head>
<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->

<div class="position"><P>当前位置: <a href="/">首页</a> > <a href="###;">网站留言</a></P></div>
<@s.form action="/portal/message!insertmessage.action" method="post" validate="true" id="msgfrom">
<input id="name" name="name" type="hidden"/>
<div class="w960">
  <div class="company_intro">
  <h2 class="company_intro_t">网站留言</h2>
  <table width="900" border="0" bgcolor="#e5e5e5" cellspacing="1">
  <tr>
    <td width="120" class="td_l">留言主题<span class="red">*</span> </td>
    <td width="773"><input type="text" name="title" id="title" value="${title?if_exists}"/><@s.fielderror><@s.param>title</@s.param></@s.fielderror></td>
    
  </tr>
  <tr>
    <td class="td_l"> 留言内容<span class="red">*</span> </td>
    <td><textarea  type="text" name="content" id="content" cols="45" rows="5" maxLength="200" >${content?if_exists}</textarea><@s.fielderror><@s.param>content</@s.param></@s.fielderror></td>
    
  </tr>
  <tr>
    <td class="td_l">联系人<span class="red">*</span> </td>
    <td><input type="text"  name="name1" id="name1" maxLength="10" onblur="NameIsNull()" value="${name1?if_exists}"/><@s.fielderror><@s.param>name</@s.param></@s.fielderror></td>
  </tr>
  <tr>
    <td class="td_l"> 手机号码<span class="red">*</span> </td>
    <td><input type="text" name="phone" id="phone" value="${phone?if_exists}"/><@s.fielderror><@s.param>phone</@s.param></@s.fielderror></td>
  </tr>
  <tr>
    <td class="td_l"> 电子邮件<span class="red">*</span> </td>
    <td><input type="text" name="email" id="email"value="${email?if_exists}"/></span><@s.fielderror><@s.param>email</@s.param></@s.fielderror></td>
  </tr>
  <tr>
    <td class="td_l">QQ</td>
    <td><input type="text" name="qq" id="qq" onblur="IsQQ()" value="${qq?if_exists}"/></td>
  </tr>
  <tr>
    <td class="td_l">MSN </td>
    <td><input type="text" name="msn" id="textfield7" value="${msn?if_exists}"/></td>
  </tr>
  <tr>
    <td class="td_l">Skype</td>
    <td><input type="text" name="skype" id="textfield8" value="${skype?if_exists}"/></td>
  </tr>
  <tr>
    <td class="td_l">验证码<span class="red">*</span> </td>
    <td><input type="text" maxlength="4" name="commentrand" id="rands" style="width:50px;" /> 
        <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/><@s.fielderror><@s.param>commentrand</@s.param></@s.fielderror></td>
  </tr>
  <tr>
    <td class="td_l">&nbsp;</td>
    <td><input  type="submit"  value="提交" class="tj"/>
    <input type="button"  value="重置"  class="cz" id="reset"/></td>
  </tr>
</table>
</@s.form>
<@s.form action="/webmessage.action" method="post" validate="true" id="msgfrom">


<#list webmessageList as webmessage>
<table width="900" border="0" bgcolor="#e5e5e5" cellspacing="1">
  <tr>
    <td width="120" class="td_l">评论标题：</td>
    <td>${(webmessage.title)?if_exists}<font style="font-size:12px;margin-left:10px;color:#3D3D3D;">${(webmessage.in_date)?if_exists}</font></td>
  </tr>
  <tr>
    <td width="120" class="td_l">详细内容：</td>
    <td>${(webmessage.content)?if_exists}</td>
  </tr>
</table>
</#list>


 <div class="listbottom">
        ${pageBar?if_exists}
</div>
</@s.form>
</div>
</div>

<#include "/${templateRoute?if_exists}/bottom.html">

</body>
</html>
