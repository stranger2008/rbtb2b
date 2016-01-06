<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>商城列表</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/allSort.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>
<#include "/WEB-INF/template/bmall/default/top.ftl">   
<div class="w980">
   <div class="f_left">
	
	<#list allfirstList as onecate>  
			  <#if onecate.cat_level=='1' && onecate_index%2==0>
		      		<h2 class="mt"><a href="/mall-goodslist-${onecate.en_name?if_exists}.html">${onecate.cat_name?if_exists}</a></h2>
		      		<div class="mc">
		      			<#list allcateList as twocate>
		      				<#if twocate.cat_level=='2' && twocate.up_cat_id==onecate.cat_id>
						         <div class="fore">
						            <div class="mcT"><a href="/mall-goodslist-${twocate.en_name?if_exists}.html">${twocate.cat_name?if_exists}</a></div>
						            <div class="mcC">
							            <#list allcateList as threecate >
								            <#if threecate.cat_level=='3' && threecate.up_cat_id==twocate.cat_id>	
								            	<a href="/mall-goodslist-${threecate.en_name?if_exists}.html">${threecate.cat_name?if_exists}</a>								            	 
								            </#if>				         
							            </#list>
						            </div> 
						            <div class="clear"></div>
						         </div>   	
						    </#if>	
				        </#list>        
				    </div>   
		      </#if>     
	</#list>	       
   </div>
   <div class="f_right">
			<#list allfirstList as onecate>  
			  <#if onecate.cat_level=='1' && onecate_index%2==1>
		      		<h2 class="mt"><a href="/mall-goodslist-${onecate.en_name?if_exists}.html">${onecate.cat_name?if_exists}</a></h2>
		      		<div class="mc">
		      			<#list allcateList as twocate>
		      				<#if twocate.cat_level=='2' && twocate.up_cat_id==onecate.cat_id>
						         <div class="fore">
						            <div class="mcT"><a href="/mall-goodslist-${twocate.en_name?if_exists}.html">${twocate.cat_name?if_exists}</a></div>
						            <div class="mcC">
							            <#list allcateList as threecate >
								            <#if threecate.cat_level=='3' && threecate.up_cat_id==twocate.cat_id>	
								            	<a href="/mall-goodslist-${threecate.en_name?if_exists}.html">${threecate.cat_name?if_exists}</a>								            	 
								            </#if>				         
							            </#list>
						            </div> 
						            <div class="clear"></div>
						         </div>   	
						    </#if>	
				        </#list>        
				    </div>   
		      </#if>     
	</#list>	      
   </div>
</div><#include "/WEB-INF/template/bmall/default/bottom.ftl">>
</body>
</html>
