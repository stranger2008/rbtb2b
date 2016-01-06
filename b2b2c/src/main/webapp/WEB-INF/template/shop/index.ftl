<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<#include "/WEB-INF/template/shop/top.ftl">
<div class="banner">
  <img src="${(shopconfig.adv_image)?if_exists}" width="980px" height="142px;"/>
</div>

<div class="w980">
   <#include "/WEB-INF/template/shop/left.ftl">
   
   <#if membercatrecomcount==0>
   <div class="contR">
	    <h1 class="mainT"><span>精品推荐</span></h1>
	    <#assign rcount=0>
		<ul class="goods">
		         <#list goodsRecomList as relist>
			           <#if rcount lt 24>
				          <li>
					         <a href="/mall-goodsdetail-${relist.goods_id?if_exists}.html" target="_blank" >
					          <#if relist.img_path?if_exists==''>
				                  <img width="216" height="263" src="/include/images/nopic.jpg">
				             <#else>
					              <img  src="<#if relist.img_path?if_exists?contains(",")==true><#assign imgindexs=relist.img_path?if_exists?index_of(",",0)><#assign imgpath=relist.img_path?if_exists?substring(0,imgindexs)>${imgpath?if_exists}
							      <#else> ${relist.img_path?if_exists}</#if> " 
							       title="${relist.goods_name?if_exists}" alt="${relist.goods_name?if_exists}"   width="216" height="263"   />
				             </#if>
					          <br /><#if relist.goods_name?if_exists.size lt 32>
								      ${relist.goods_name?if_exists}
								      <#else>
								       ${relist.goods_name[0..31]?if_exists}
								      </#if>
								 </a>
					          <br /><p class="price"><span class="market">市场价：<span>￥${relist.market_price?if_exists}</span></span><span class="sold">售价：￥${relist.sale_price?if_exists}</span></p>
						  </li>
				          <#assign rcount=rcount+1>
			          </#if>
		          </#list>
	     </ul>
	     <div class="clear"></div>
  </div>
  <#else>
  <#list membercatRecomList as  mclist>
  <div class="contR">
	    <h1 class="mainT"><span>${(mclist.cat_name)?if_exists}</span></h1>
	    <#assign mccount=0>
		<ul class="goods">
		     <#list goodsRecomList as grlist> 
		           <#if grlist.self_cat?if_exists?contains((mclist.cat_id)?string)==true>
			          <#if mccount lt 6>
				           <li>
					         <a href="/mall-goodsdetail-${grlist.goods_id?if_exists}.html" target="_blank" >
					          <#if grlist.img_path?if_exists==''>
				                  <img width="216" height="263" src="/include/images/nopic.jpg">
				             <#else>
					              <img  src="<#if grlist.img_path?if_exists?contains(",")==true><#assign imgindexs=grlist.img_path?if_exists?index_of(",",0)><#assign imgpath=grlist.img_path?if_exists?substring(0,imgindexs)>${imgpath?if_exists}
							      <#else> ${grlist.img_path?if_exists}</#if> " 
							       title="${grlist.goods_name?if_exists}" alt="${grlist.goods_name?if_exists}"   width="216" height="263"   />
				             </#if>
					          <br /><#if grlist.goods_name?if_exists.size lt 32>
								      ${grlist.goods_name?if_exists}
								      <#else>
								       ${grlist.goods_name[0..31]?if_exists}
								      </#if>
								 </a>
					          <br /><p class="price"><span class="market">市场价：<span>￥${grlist.market_price?if_exists}</span></span><span class="sold">售价：￥${grlist.sale_price?if_exists}</span></p>
						  </li>
						    <#assign mccount=mccount+1>
					  </#if>
				  </#if>
			 </#list>
	     </ul>
	     <div class="clear"></div>
  </div>
  </#list>
  </#if>
  
  
  
  
<div class="clear"></div>
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
