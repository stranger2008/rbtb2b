<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/shopList.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/bmall/js/goods.js"></script>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title><#if catName?if_exists==''>搜索<#else>${catName?if_exists}</#if>-${cfg_mallwebname?if_exists}</title>
</head>
<body>
<@s.form action="/mall-shoplist.html" method="post">
<#include "/WEB-INF/template/bmall/default/top.ftl">
<div class="clear"></div>
<!--导航结束-->
<!--导航结束-->
<div class="position"><p class="f_left">您当前的位置：<a href="${indexName?if_exists}">首页</a> > <a href="/mall-shoplist.html">店铺</a> > ${selvaluep?if_exists}</p>
  <p class="f_right xg">相关店铺<span>${count}</span>个</p></div>
<div class="clear"></div>
<div class="searchFilter">
</div>
<div class="w980">
	<#if shopList.size() gt 0>
	  <#list shopList as slist>
	  <ul class="shopInfo">
	     <li class="shop-img">
	     
	      <a  href="/shop/${(slist.user_name)?if_exists}.html"  target="_blank">
	      <#if slist.shop_logo?if_exists==''>
	              <img width="80px" height="80px"  src="/include/images/nopic.jpg" />
	         <#else>
	             <img src="${(slist.shop_logo)?if_exists}"  width="80px"  height="80px"  />
	        </#if>
	     </a>
	     
	     
	     </li>
	     <li class="shop-about"><h2><a href="/shop/${(slist.user_name)?if_exists}.html"  target="_blank">
	     ${(slist.shop_name)?if_exists}</a></h2><p><span>主营产品：</span><span title="${(slist.busi_range)?if_exists}">
	      <#if (slist.busi_range)?length lt 50>
		       ${(slist.busi_range)?if_exists}
		    <#else>
		      ${slist.busi_range[0..49]}...
		    </#if>
	     </span></p>
	     <p>掌柜: <span style="color:#2953A6;"><a  href="/shop/${(slist.user_name)?if_exists}.html"  target="_blank">${(slist.user_name)?if_exists}</a></span></p>
	     </li>
	     <li class="shop-loc"><#if slist.area_attr !=null&&slist.area_attr !="">${(slist.area_attr)?if_exists}<#else>暂无地区信息</#if></li>
	     <li class="shop-grade j_grade">
	     <#if slist.msn!=null&&slist.msn!="">
	     <a href="msnim:chat?contact=${(slist.msn)?if_exists}" target=blank><img src="/templets/default/images/sm_msn.gif"/></a>
	     </#if>
	      <#if slist.qq!=null&&slist.qq!="">
	     <a href="http://wpa.qq.com/msgrd?v=3&uin=${(slist.qq)?if_exists}&site=qq&menu=yes" target="_blank">
	     	<img border="0" src="http://wpa.qq.com/pa?p=1:${(slist.qq)?if_exists}:16" alt="">
	     </a>
	     </#if>
	      <#if slist.alliwang!=null&&slist.alliwang!="">
	      <a target="_blank" href="http://amos.im.alisoft.com/msg.aw?v=2&uid=${(slist.alliwang)?if_exists}&site=cntaobao&s=1&charset=utf-8" >
           <img border="0" src="http://amos.im.alisoft.com/online.aw?v=2&uid=${(slist.alliwang)?if_exists}&site=cntaobao&s=1&charset=utf-8" 
           alt="有问题随时都可以M我哦"  /></a>
	     </#if>
	      </li>
	     <div class="clear"> </div>
	  </ul>
	</#list>
	 <div class="page_main">${pageBar?if_exists}</div>
<#else>
<div class="noinfor">
	抱歉！没有找到相关的店铺。
 </div>
 <div class="searchFilter">
</div>
</#if>
</div>



<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
