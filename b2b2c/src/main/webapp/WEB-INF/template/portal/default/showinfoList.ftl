<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/exhibition_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/showinfo!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   	  <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
   	   <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
      <div class="exhibit_main">
         <#if exhibitionList?if_exists && (exhibitionList?size > 0)>
	       <#list exhibitionList as exhibition>     
	          <#assign rbttime='${(exhibition.in_date)?if_exists}'/>  
		      	<div class="exhibit_main_cont">		      	
		      	    <div style="width:113px;height:106px;float:left;">
		      	        <div  class="divMiddle" style="width:113px;height:106px;line-height:106px;">
				        	<a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('showinfo','${exhibition.exh_id?if_exists}','${rbttime?if_exists}')")}" 
				        	target="_blank"  class="AMiddle"  style="width:113px;">
				                <img src="${exhibition.img_path?if_exists}"  class="ImgMiddle"
				                 ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${exhibition.img_path?if_exists}','103','96')")} />			        	
				        	</a>
				         </div>
		        	</div>		        	
		            <div class="exhibit_pcont f_left" style="padding-left:15px;">
		               <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('showinfo','${exhibition.exh_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
		               <h2 class="exhibit_title1">		            	
		               <#if exhibition.title?length lt 20>
		            	   ${exhibition.title?if_exists}
			           <#else>
			               ${exhibition.title[0..19]?if_exists}
			           </#if>
		            	</h2></a>
		                <p>地址:		                
		                <#if exhibition.address?length lt 18>
		            	   ${exhibition.address?if_exists}
			           <#else>
			               ${exhibition.address[0..17]?if_exists}
			           </#if></p>			           
		                <p>主办:
		                <#if exhibition.host_unit?length lt 21>
		            	   ${exhibition.host_unit?if_exists}
			           <#else>
			               ${exhibition.host_unit[0..20]?if_exists}...
			           </#if></p>
		                <p><#if exhibition.start_date?length lt 10>
		            	   ${exhibition.start_date?if_exists}
			           <#else>
			               ${exhibition.start_date[0..9]?if_exists}
			           </#if> ~ 
			           <#if exhibition.end_date?length lt 10>
		            	   ${exhibition.end_date?if_exists}
			           <#else>
			               ${exhibition.end_date[0..9]?if_exists}
			           </#if></p>
		            </div>
		            <div class="exhibit_ing f_right">
		                <p class="f_left">[${exhibition.area_name?if_exists}]</p>
		                <div class="ex_now">
		                 <#if exhibition.is_progress?if_exists=="0">
		                   未开始
		                 <#elseif exhibition.is_progress?if_exists=="1">
		                   进行中
		                 <#else>
		                   已结束
		                 </#if>
		                </div>
		            </div>	
		        </div>
	       </#list>  
        <div class="clear"></div>
        <div class="page_main">
         <div style="text-align:center;">
            ${pageBar?if_exists}<br/>     
        </div>

      	</div>
      <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
      </div>
	</div>
   <!--左边部分结束--> 
   
   	<div class="right_main f_right">
   	 
      
      <div class="area_title"><h2 class="title_text">热门排行</h2></div>
      <ul class="ph_main">
         <#list topList as top>
          <#assign rbttime='${(top.in_date)?if_exists}'/>  
         <li><span class="n1">1</span><a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('showinfo','${top.exh_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
         <#if top.title?length lt 16>${top.title?if_exists}
		 <#else>${top.title[0..15]}... </#if></a></li>
         </#list>
   </ul>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=95"></script>
   </div>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=96"></script>
   </div>
 </div>
    <!--右边部分结束-->
</div>
<div class="clear"></div>
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="area_id" id="area_value_para"/>
<@s.hidden name="searchText"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
}
$(document).ready(function(){
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });    
});
</script>
</body>
</html>
