<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/classify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/shop/js/change.js"></script>
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<@s.form action="/shop/${user_name?if_exists}/shopcategory.html" method="post">
<#include "/WEB-INF/template/shop/top.ftl">
<div class="w980">
 <#include "/WEB-INF/template/shop/left.ftl">
  
   <div class="contR">
      <#list membercatList as mlist>
        <#if mlist.cat_level==1>
      <h2 class="clah2"><a href="/shop/${user_name?if_exists}/goodslist/cat_${(mlist.cat_id)?if_exists}.html"   >${(mlist.cat_name)?if_exists}</a></h2>
	      <div class="clacont">
	            <#list membercatList as mlisttwo>
	                 <#if mlist.cat_id==mlisttwo.up_cat_id>
	            		  <a href="/shop/${user_name?if_exists}/goodslist/cat_${(mlisttwo.cat_id)?if_exists}.html"   >${(mlisttwo.cat_name)?if_exists}</a>
	            	   </#if>
	         	</#list>
	      </div>
	    </#if>
      </#list>
      
  </div>
<div class="clear"></div>
</div>
  
  
  
<div class="clear"></div>
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
