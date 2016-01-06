<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/listInfo.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/proshow.css" rel="stylesheet" type="text/css" >	



<title>${(classified.title)?if_exists}-信息详情 -${area_name?if_exists}${list_seo_title?if_exists}</title>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">


</head>

<body>
<@s.form method="post" name="searchForm">
<#include "/${(templateRoute?if_exists)}/top.html">
  <div class="position"><P><span class="f_left">当前位置:<#if isviewarea?if_exists=='0'&& cfg_area_ip?if_exists=='0'>
    <a href="/area-${hidden_area_id?if_exists}.html">${area_name?if_exists}</a><#else><a href="/area-${EareaName?if_exists}.html">${areaName?if_exists}</a></#if>><a href="/classified/cat-${catupid?if_exists}.html"><#if isviewarea?if_exists=='0'>${area_name?if_exists}</#if>${cateupname?if_exists}</a> ><a href="/classified/cat-${cat_id?if_exists}.html"><#if isviewarea?if_exists=='0'>${area_name?if_exists}</#if>${categoryname?if_exists}</a> ><a href="/classified/detail_${info_id?if_exists}.html">${categoryname?if_exists}详情</a></span><#if isviewarea?if_exists=='0'><span class="cs_list f_right"></span></#if></p></div>  
<div class="w960">
 <div class="contM">
   <div class="detailTitle">
    <h1 class="contMT">${(classified.title)?if_exists}</h1>
    <P class=""><span class="f_left">发布：${(classified.in_date)?if_exists}</span><span class="f_left pageviews">该信息被浏览：${(classified.clicknum)?if_exists}</em> 次</span><span class="f_right"><a href="/member_Classified_list.action">删除</a> | <a href="/member_Classified_list.action">修改</a> </span><br  class="clear"/></P>
  </div>
  <div class="pic">
  	<div class=jqzoom id=spec-n1 style="border:1px solid #d6d6d6;" >
  		<#if classified.img_path!=''>
		  			<#if ((classified.img_path)?index_of(",") > (-1))>      			
		  				<#assign startpos = "${classified.img_path?if_exists}"?index_of(',')>
		                 <#if ((startpos-1)>-1)>
		                       <#assign img =(classified.img_path)[(0)..(startpos-1)]>
		                       <#assign pos = "${img?if_exists}"?index_of('.')>
		          				  <#if ((pos-1)>-1)>
		          				  	
		          				 		<#assign b_img =(img)[(0)..(pos-1)]>
		          				  		<#assign type =(img)[(pos)..(img?length-1)]>
		          				  </#if>		          				  
			             		 	<img src="${b_img?if_exists}${type?if_exists}" jqimg="${b_img?if_exists}${type?if_exists}" width='310px' height="310px"/>	
		                 </#if>
		             <#else>
            			     <#assign pos = "${classified.img_path?if_exists}"?index_of('.')>
          				     <#if ((pos-1)>-1)>
          				 		<#assign b_img =(classified.img_path)[(0)..(pos-1)]>
          				  		<#assign type =(classified.img_path)[(pos)..(classified.img_path?length-1)]>
          				     </#if>
		             		 	<img src="${b_img?if_exists}${type?if_exists}" jqimg="${b_img?if_exists}${type?if_exists}" width='310px' height="310px"/> 	
					</#if>	
				<#else>
						<img src="${cfg_nopic?if_exists}" width='320px' height="310"/> 	
		        </#if>
		</div>
	<div  class="specn5" id=spec-n5>
		<div class=lcontrol id=spec-left>
			 <img src="/templets/default/images/left.gif" />
		</div>
		<div  class="speclist" id=spec-list>
			<ul class=listul>
				<#list classified.img_path?split(",") as s>
			          				  <#assign pos = "${s?if_exists}"?index_of('.')>
			          				  <#if ((pos-1)>-1)>
			          				 		<#assign b_img =(s)[(0)..(pos-1)]>			          				 		
			          				  		<#assign type =(s)[(pos)..(s?length-1)]>
			          				  </#if>
								      <li>
								      	 <img src="${b_img?if_exists}${type?if_exists}">
								      </li>						      
			</#list>			
			</ul>
		</div>
		<div class=rcontrol id=spec-right>
				<img src="/templets/default/images/right.gif" />
		</div>
    </div>
   </div>
     <ul class="contact">
        <li>联系人：${(classified.contact)?if_exists}</li>
        <li>电话：${(classified.phone)?if_exists}</li>
        <li>在线联系：<a href="http://wpa.qq.com/msgrd?v=3&uin=${(classified.qqmsn)?if_exists}&site=qq&menu=yes" target="_blank">
<span style="font-size: medium">
<img border="0" src="http://wpa.qq.com/pa?p=1:${(classified.qqmsn)?if_exists}:16" alt="">
</span>
</a></li>
		<#if member_type=='0'>
        <li class="InQy"><b><a href="/showroom/${user_name?if_exists}">进入企业站</a></b></li>
        </#if>
        <li><!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
        <a class="bds_qzone"></a>
        <a class="bds_tsina"></a>
        <a class="bds_tqq"></a>
        <a class="bds_renren"></a>
        <span class="bds_more">更多</span>
		<a class="shareCount"></a>
    </div>
<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
</script><br class="clear" />
<!-- Baidu Button END --></li>
        <li>联系我时，请说是在<a href="/" style="color: #f00">本网站</a>上看到的，谢谢！</li>
        <li class="ts"><a href="/member_Complaint_add.action">投诉电话被其冒用</a><a href="/member_Memberreport_add.action">举报该信息虚假/违法</a><a href="/portal/message.action">对本网站说两句</a></li>
     </ul>
     <br class="clear" />
 
	   <ul class="attribute1">

   </ul>

	 <div class="contmain">
	      <P>${(classified.info_desc)?if_exists}</P>
	
	 </div>
 </div>
 <div class="conright">
	<div class="s_ad">
	           <h3><span></span></h3>
	           <ul>
	               <li> <script src="/include/advshow.jsp?pos_id=80&amp;area_id=${(hidden_area_id)?if_exists}"></script></li>
	               <li> <script src="/include/advshow.jsp?pos_id=21&amp;area_id=${(hidden_area_id)?if_exists}"></script></li>
	           </ul>         
	         </div>
	
	</div>
</div>



<div class="clear"></div>
<@s.hidden name="cat_id" id="cat_id"/>
<@s.hidden name="area_id" id="area_id"/>
<@s.hidden name="hidden_area_id" id="hidden_area_id"/>
<@s.hidden name="info_id" id="info_id"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/supply.js"></script>
<script type="text/javascript" src="/include/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/include/js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="/include/js/jquery.timers-1.1.2.js"></script>
<script src="/templets/default/js/jquery126.js" type=text/javascript></script>
<script src="/templets/default/js/base.js" type=text/javascript></script>
<script src="/templets/default/js/zoomlib.js" type=text/javascript></script>
<script src="/templets/default/js/zoom.js" type=text/javascript></script>
<script src="/templets/default/js/use.js" type="text/javascript"></script>
</body>
</html>
