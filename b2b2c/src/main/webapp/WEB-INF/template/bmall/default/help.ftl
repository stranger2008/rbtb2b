<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${aboutus.title?if_exists}-帮助中心-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/help.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="/templets/bmall/js/js.js"></script>    
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>
<!--导航开始-->
<#include "/WEB-INF/template/bmall/default/top.ftl" >
<!--导航结束-->
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}">首页</a> > 帮助中心 </p></div>

<div class="w980">
  <div class="contL">
    <div class="Sidebar">
        <h2>帮助中心</h2>
        <ul class="sideFl">
           <#list commpareaIndexList as helpcatlist >
           	<li class="zk"><span style="color:#2953A6;">${helpcatlist.para_key?if_exists}</span></li>
	           <#list aboutusIndexList as helplist>
	           <#if helplist.ch_id=helpcatlist.para_value>
	           <li><A href="/mallhelp-${helplist.info_id?if_exists}.html">${helplist.title?if_exists}</A></li>
	           </#if>
	           </#list >

           </#list>
        </ul>
    </div>
  </div>
  
  <div class="contR">

        <h2><span>${aboutus.title?if_exists}</span></h2>
  
        ${aboutus.content?if_exists}
       
  </div>
</div>



<div class="clear"></div>
<!--底部开始-->
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</div>
</body>
</html>
