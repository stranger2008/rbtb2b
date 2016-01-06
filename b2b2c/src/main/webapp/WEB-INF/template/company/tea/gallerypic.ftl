<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>${(gallerypic.title)?if_exists}—图片展示—${memberconfig.web_title?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/picture.css" rel="stylesheet" type="text/css">

</head>

<!-- 头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/gallery.html">图片展示</a><span>></span><a href="###">图片详情</a></div>
   
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lpictback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       <#if gallerypicList?exists && ( gallerypicList.size() > 0 )>
       <div class="piccont">
          <div class="bigpic">
     <div id="agallery" class="ad-gallery">
      <div class="ad-image-wrapper"></div>
      <div class="ad-controls"></div>
      <div class="ad-nav">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list">
          <#list gallerypicList as gallerypic> 
           <li>
              <a href="${(gallerypic.img_path)?if_exists}"> 
                <img src="${(gallerypic.img_path)?if_exists}" width="88" height="88"  alt="${(gallerypic.pic_desc)?if_exists}" >
              </a>
            </li>
          </#list>
          </ul>
        </div>
      </div>
    </div>
          </div>
       </div>
      <#else>
       <div style="padding-left:10px;padding-top:5px;">暂无图片</div>
      </#if>
     </div>
     
    <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
   
</div>

<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<#include "/include/components/gallerypic/cominc.html">
<!--底部结束-->
</body>
</html>
