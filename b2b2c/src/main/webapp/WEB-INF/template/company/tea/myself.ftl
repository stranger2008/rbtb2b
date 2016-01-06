<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>Map 地图—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/map.css" rel="stylesheet" type="text/css">
</head>
<body>
<input type="hidden" id="addr" value="${(member.address)?if_exists}" />
<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/companymyself.action?user_name=${user_name?if_exists}">Map 地图</a></div>
   
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lmaptback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       <div class="bigmap">
          <!-- 嵌入谷歌地图 -->
           <div class="golmap f_right">
           <div id="googleSearch"></div>
           <div id="googleResult" style="width:690px; margin:4px 0;"></div>
           <div id="googleMap" style="width:690px; height:485px; border:solid 1px #ccc">loading...</div>
           </div>
       </div>          
     </div>
     
     <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
   
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
