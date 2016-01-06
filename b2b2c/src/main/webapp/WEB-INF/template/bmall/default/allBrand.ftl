<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>所有品牌列表</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/brand.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>
<@s.form id="brand" action="/mall/goods!allBrand.action"  method="post">
<@s.hidden name="bid" id="bid"/>
<#include "/WEB-INF/template/bmall/default/top.ftl">
<div class="content w980">
   <div class="contT"></div>
   <div class="contC">
            <div class="contM">
          <div class="contMTj"></div>
          <ul class="conTC">
          
          		<#list recomList as recom>
                	<li>
                		<div style="height:50px;">
	                	<a  style= "cursor:pointer;" onclick="pselect('b','${recom.brand_id?if_exists}')" ><img src="${recom.logo?if_exists}" width='139' height='47'/></a>
	                	</div>
	                	<br />
	                	<a  style= "cursor:pointer;" onclick="pselect('b','${recom.brand_id?if_exists}')" >${recom.brand_name?if_exists}</a>
                	</li>
              	</#list>
              <div class="clear"></div>
          </ul>
          
          <div class="contMB"></div>
       </div>
       
	   <script type="text/javascript">
	        function nTabs(val) {
	            $("#bid").val(val);
	            $("#brand").submit();
	        }
	   </script>       
       <div class="contTab">
          <ul id="myTab" class="tabSort">
          	  <#list allcateList as cate>
	              <li <#if bid==cate.cat_id>class="curr"</#if> ><a onclick="nTabs('${cate.cat_id}');" style="cursor:pointer;">${cate.cat_name?if_exists}</a></li>
	          </#list>
              <div class="clear"></div>
          </ul>
          
            <ul class="conTC contTbaM"  id="myTab_Content0">
            	<#list brandList as brand>
              		<li><a style= "cursor:pointer;" onclick="pselect('b','${brand.brand_id?if_exists}')" >
              		<div style="height:50px;"><img src="${brand.logo?if_exists}" width='137' height='47'/></div></a>
              		<br /><a  style= "cursor:pointer;" onclick="pselect('b','${brand.brand_id?if_exists}')" >${brand.brand_name}</a></li>
             	</#list>
              <div class="clear"></div>
            </ul>

       </div>
       
       
   </div>
  <div class="contB"></div>
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
