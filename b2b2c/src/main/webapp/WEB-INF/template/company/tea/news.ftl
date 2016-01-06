<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>本店动态—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/news.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="#">本店动态</a></div>
   <@s.form action="/companynews.action?user_name=${user_name?if_exists}" method="post" validate="true">
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lnewsback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       <div class="news">
         <ul>
         <#list allnewsList as allnews>
                   <li><a href="/showroom/${user_name?if_exists}/news/detail_${allnews.cert_id?if_exists}.html">${allnews.title?if_exists}</a>${allnews.in_date.toString().substring(0,10)?if_exists}</li> 
                   </#list>
        <#if allnewsList.size()==0><span style="padding-left:10px;">暂无数据</span></#if>
         </ul>
       </div>
        <P class="page">
               ${pageBar?if_exists}
               </P>
     </div>
     </@s.form>
    <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
   
</div>


<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
