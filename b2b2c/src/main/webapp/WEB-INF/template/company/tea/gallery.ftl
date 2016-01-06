<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>公司相册—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/picture.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/gallery.html">图片展示</a></div>
   <@s.form  action="/companygallery.action?user_name=${user_name?if_exists}" method="post" validate="true">
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lpictback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       <div class="picture">
         <ul>
           <#list galleryList as gallery>
           <li><div><a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallery.gal_id?if_exists}.html">
           <img src="${gallery.img_path?if_exists}" width="138px" height="110px"></a></div>
           <a href="/showroom/${user_name?if_exists}/gallerypic/detail_${gallery.gal_id?if_exists}.html"><p>
           <#if gallery.title?length lt 7>${gallery.title?if_exists}<#else>${gallery.title[0..6]}...</#if></p></a>
           </li>  
           </#list>
           <#if galleryList.size()==0><span style="padding-left:20px;">暂无数据</span></#if>
         </ul>
         <div class="clear"></div>
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
