<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>产品橱窗—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/product.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/product.html">产品橱窗</a></div>
   <div class="qycont">
     <div class="lw700"> 
       <div class="title">
         <div class="lprotback"></div>
         <div class="rtback">
          <#list productCateList as productCate>
              <a href="/showroom/${user_name?if_exists}/product/cat_${productCate.cat_id?if_exists}.html">${productCate.cat_name?if_exists}</a>
          </#list>
         </div>
          <div class="clear"></div>        
       </div>
        <@s.form action="/companyproduct.action?user_name=${user_name?if_exists}" method="post" validate="true">
       <div class="prouct">
         <ul>
           <#list allproductList as allproduct>
           <li> <div><a href="/showroom/${user_name?if_exists}/product/detail_${allproduct.product_id?if_exists}.html">
           		<img src="${allproduct.img_path}" width="138px" height="110px">
           		</a></div>
           		<a href="/showroom/${user_name?if_exists}/product/detail_${allproduct.product_id?if_exists}.html">
           			<p><#if allproduct.title?length lt 7>${allproduct.title?if_exists}<#else>${allproduct.title[0..6]}...</#if></p>
           		</a>
           </li>
           </#list>
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
 
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
