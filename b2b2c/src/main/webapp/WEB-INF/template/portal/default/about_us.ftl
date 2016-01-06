<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign path="${request.getContextPath()}"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/help_center.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<title>${aboutus.title?if_exists}-${cfg_webname?if_exists}</title>
</head>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a>> 帮助中心 > ${aboutus.title?if_exists}</P></div>

<div class="w960">
	  <div class="contL">
		    <div class="Sidebar">
		        <h2>帮助中心</h2>
		        <ul class="sideFl">
		           <#assign ch_url="">
		          <#list commpareaIndexList as helpcatlist >
		             <#assign ccount=0>
		              <#list aboutusIndexList as helplist>
			           <#if helplist.ch_id=helpcatlist.para_value>
			           <#assign ccount=ccount+1>
			           <#assign ch_url="/aboutus_detail_${helplist.info_id?if_exists}.html">
			           </#if>
			           </#list >
		          
		          
		           	<li class="zk"><span style="color:#2953A6;"><#if ccount==1><a href="${ch_url?if_exists}">${helpcatlist.para_key?if_exists}</a><#else>${helpcatlist.para_key?if_exists}</#if></span></li>
		           	   <#if ccount gt 1>
			           <#list aboutusIndexList as helplist>
			           <#if helplist.ch_id=helpcatlist.para_value>
			           <li><a  href="/aboutus_detail_${helplist.info_id?if_exists}.html">${helplist.title?if_exists}</a></li>
			           </#if>
			           </#list >
			          </#if>
			           
           			</#list>
		        </ul>
		    </div>
	  </div>
      <div class="about_main">
       <div class="industry_main">
         <h2 class="left_title">${aboutus.title?if_exists}</h2>
        
         <div class="about_main_c">
           ${aboutus.content?if_exists}
         </div>
      </div>
       
    </div>
  
  
  
  
<div class="clear"></div>
</div>
<#include "/${templateRoute?if_exists}/bottom.html">

</body>
