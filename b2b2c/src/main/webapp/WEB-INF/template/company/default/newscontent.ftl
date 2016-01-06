<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(membernews.title)?if_exists}—新闻详细—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > <a href="/showroom/${user_name?if_exists}/news.html" style="font-size:14px;">新闻中心</a> > 新闻详细</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部结束-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
     
       <!--目录式浏览-->
        <div class="right_main">
           <div class="r_title"><h2><span class="title_font">新闻中心</span></h2></div> 
            <div class="other_main">
               <h2 class="news_title">${(membernews.title)?if_exists}</h2> 
               <P class="news_m"><span>发布时间：${membernews.in_date?if_exists.substring(0,19)} </span></P>
               <div class="news_cont" style="table-layout:fixed; word-break: break-all; overflow:hidden;">
                 ${(membernews.content)?if_exists}
               </div>
           </div>
       </div>
    <!--目录式浏览-->
   
  </div>
      
  <!--右部结束--> 
 
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
