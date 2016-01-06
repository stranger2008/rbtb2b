<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>${gallery.title?if_exists}—相册详细—${memberconfig.web_title?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">

</head>

<!-- 头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > <a href="/companygallery.action?user_name=${user_name?if_exists}" style="font-size:14px;">公司相册</a> > 相册详细 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部结束-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
      <div class="r_body f_right">
    
    
    <div class="right_main">
      <div class="r_title"><h2><span class="title_font">公司相册</span></h2></div> 
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
    
    
    
    
    
    
      <div class="right_main padding_10">
            <div class="r_title">
              <h2><span class="title_font">详细信息</span></h2></div>
            <div class="other_main">
              ${gallery.gal_desc}
            </div> 
     </div>
     
  <@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post">
   <div class="right_main padding_10">
   <input type="hidden" name="infotype" id="info_type" value="gallery" />
   <input type="hidden" name="infoids" id="infoids" value="${gallery.gal_id?if_exists}" />
   <input type="hidden" name="commtitle" id="comment_title" value="${gallery.gal_id?if_exists}" />
   <input type="hidden" id="idwidth" value="450" />
   <#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/commentinfo.html">
   </div>
   </@s.form>
  </div>
  <!--右部结束--> 
   
</div>

<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<#include "/include/components/gallerypic/inc.html">
<!--底部结束-->
</body>
</html>
