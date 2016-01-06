<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign path="${request.getContextPath()}"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/About_us.css" rel="stylesheet" type="text/css" />
<title><#if pos_type_name=="">广告服务<#else>${pos_type_name?if_exists}</#if>-${cfg_webname?if_exists}</title>
</head>

<body>
<!--广告位展示-->
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a>><#if pos_type_name=="">广告服务<#else>${pos_type_name?if_exists}</#if></P></div>
<@s.form action="/advpos_${pos_type_s?if_exists}.html" method="post">
<div class="w960">
    <ul class="about_us">
    <li><a href="/index.html" target="_blank" >网站首页</a></li>
     <li><a href="/advpos_all.html" >广告服务</a></li>
    <#list commparaList as compara>
      <li><a href="/advpos_${compara.para_value?if_exists}.html">${compara.para_key?if_exists}</a></li>
     </#list>
    </ul>
    
    <style>
    	.top_td{
    		font-weight:bold;
    	}
    	.listbottom{
			height:27px;
			width:100%;
			line-height:27px;
			background:url(/include/images/bottom.gif) repeat-x;
			text-align:center;
		}
		.listbottom a{
			text-decoration:none;
			color:#000;
		}
    </style>
    
    <div class="about_main">
       <h2 class="about_main_title"><#if pos_type_name=="">广告服务<#else>${pos_type_name?if_exists}</#if></h2>
       <table width="100%" border="0" cellspacing="5" cellpadding="5">
  <tr bgcolor="#F1F7FC">
    <td width="8%" align="center" class="top_td">编号</td>
    <td width="30%" align="center" class="top_td">广告位名称</td>
    <td width="10%"  align="center" class="top_td">广告类型</td>
    <td width="12%"  align="center" class="top_td">规格(宽*高px)</td>
    <td width="8%"  align="center" class="top_td">价格(元)</td>
    <td width="8%"  align="center" class="top_td">示意图</td>
    <td width="8%"  align="center" class="top_td">预订</td>
  </tr>
  <#list advposList as pos>
  <tr>
    <td align="center" style="color:#B8860B;font-weight:bold;">${pos.pos_id?if_exists}</td>
    <td align="left" >${pos.pos_name?if_exists}</td>
    <td align="center">${pos.para_key?if_exists}</td>
    <td align="center">${pos.p_width?if_exists}*${pos.p_height?if_exists}</td>
    <td align="center" style="color:#DAA520;font-weight:bold;"><#if pos.price?if_exists==0>面议<#else>${pos.price?if_exists}</#if></td>
    <td align="center"><#if pos.img_path?if_exists==''><font color='#808080'>暂无</font><#else><a href="${pos.img_path?if_exists}" target="_blank"><font color='#228B22'>查看</font></a></#if> </td>
    <td align="center" style="color:#808080">暂无</td>
  </tr>
  </#list>
 </table>
  <div class="listbottom">
   ${pageBar?if_exists}
  </div>
</div>
</div>
<div class="clear"></div>
</@s.form>
<#include "/${templateRoute?if_exists}/bottom.html">


</body>
</html>
