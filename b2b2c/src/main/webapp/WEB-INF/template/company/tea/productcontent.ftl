<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(product.title)?if_exists}—产品详细—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/product.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="/templets/${templateStyle?if_exists}/css/galleryview.css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/product.html">产品橱窗</a><span>></span><a href="###">产品详情</a></div>
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lprotback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div>
       
       <div class="procont">
           <div class="lbigpro">
            <input type="hidden" value='${(product.img_path)?if_exists}' id="imgpath"/>
                     <!-- 绑定商品图片开始 -->
                     <div id="photos" class="galleryview" >
                     </div>
           </div>
           <div class="lprointro">
             <p>产品名称：<span>${(product.title)?if_exists}</span></p>
             <p>产品规格：<span>${(product.standard)?if_exists}</span></p>
             <p>产品品牌：<span>${(product.brand)?if_exists}</span></p>
             <p>产品型号：<span>${(product.model)?if_exists}</span></p>
             <p>最后更新：<span>${(product.in_date)?if_exists.substring(0,19)}</span></p>
             <p>浏览次数：<span> <span id="clicks">
                     <script src="/include/clicknum.jsp?t=product&n=product_id&v=${(product.product_id)?if_exists}"></script>
                    </span></span></p>
           </div>
           <div class="clear"></div>
       </div>
       
       <!--评论-->  
   <div class="right_main padding_10">
   <@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post" >
   <input type="hidden" name="infotype" id="info_type" value="product" />
   <input type="hidden" name="infoids" id="infoids" value="${(product.product_id)?if_exists}" />
   <input type="hidden" name="commtitle" id="comment_title" value="${(product.title)?if_exists}" />
   <input type="hidden" id="idwidth" value="450" />
   <#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/commentinfo.html">    
   </@s.form> 
   </div>
   </div>
    <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/include/js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="/include/js/jquery.timers-1.1.2.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/supply.js"></script>
</body>
</html>
