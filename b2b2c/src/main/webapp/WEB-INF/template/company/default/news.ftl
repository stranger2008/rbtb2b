<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>新闻中心—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 新闻中心 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部结束-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
       <!--目录式浏览-->
       <@s.form action="/companynews.action?user_name=${user_name?if_exists}" method="post" validate="true">
       <div class="right_main">
           <div class="r_title"><h2><span class="title_font">新闻中心</span></h2></div> 
            <div class="other_main">
               <ul class="news_list">
                   <#list allnewsList as allnews>
                   <li><a href="/showroom/${user_name?if_exists}/news/detail_${allnews.cert_id?if_exists}.html">${allnews.title?if_exists}</a>${allnews.in_date.toString().substring(0,10)?if_exists}</li> 
                   </#list>
                   <#if allnewsList.size()==0><span style="padding-left:10px;">暂无数据</span></#if>
              </ul>
              <!--目录方式浏览--> 
               <P class="page">
               ${pageBar?if_exists}
               </P>
               <div class="clear"></div>
           </div>
       </div>
       </@s.form>
    <!--目录式浏览-->
   
  </div>
      
  <!--右部结束--> 
 
</div>

<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
