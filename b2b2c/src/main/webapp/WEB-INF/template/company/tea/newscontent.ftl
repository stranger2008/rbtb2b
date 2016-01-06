<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(membernews.title)?if_exists}—新闻详细—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/news.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/news.html">本店动态</a><span>></span><a href="###">动态详情</a></div>
   
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lnewsback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       <div class="newscont">
         <h2 class="newsth2">${(membernews.title)?if_exists}</h2>
         <p class="time"><span>${(membernews.in_date)?if_exists.substring(0,19)}</span><span></span></p>
         ${(membernews.content)?if_exists}
       </div>
       
      </div>
     
     <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
   
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
