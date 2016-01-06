<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/subject_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>

<body>
<@s.form action="/portal/subject!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />    
  
<div class="w960">  
  <!--左-->
   <div class="l_body f_left">
    <#include "/WEB-INF/template/portal/default/cateList.ftl" /> 
  <#include "/WEB-INF/template/portal/default/attrList.ftl" />
   <#if subjectList?if_exists && (subjectList?size > 0)>
   <!--industy end--> 
       <div class="clear"></div>
       <div class="columns">
        <#list subjectList as subject>
         <#assign rbttime='${(subject.in_date)?if_exists}'/>  
         <div class="co_content">
          <p class="co_font">
          	<a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('subject','${subject.sub_id?if_exists}','${rbttime?if_exists}')")}" target="_blank" class="co_color">
          		<#if subject.title?length lt 46>${subject.title?if_exists}
			  <#else>${subject.title[0..45]}...
			  </#if>
          	</a>
          </p>
          <p><#if subject.sub_desc?length lt 96>${subject.sub_desc?if_exists}
			  <#else>${subject.sub_desc[0..95]}...
			  </#if>[<a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('subject','${subject.sub_id?if_exists}','${rbttime?if_exists}')")}" class="co_color2">详细</a>]</p>
		 <p><span>日期：<a class="co_color2"><#if subject.in_date?length lt 10>${subject.in_date?if_exists} <#else>${subject.in_date[0..9]}</#if></a></span>
		 <span> 浏览：<a class="co_color2">${subject.clicknum?if_exists}</a></span></p> 
         </div>
        </#list>
         
         <div class="page_main">
         <div style="text-align:center;">
            ${pageBar?if_exists}<br/>  
         </div>  
      </div>
      
      </div><!--columns end-->
               <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>  
   </div><!--l_body end-->
   
   <!--右-->
   <div class="right_main f_right">
      <div class="area_title"><h2 class="title_text">热门专题</h2></div>
       <ul class="ph_main">
	      <#list topList as recom>
	             <#assign rbttime='${(recom.in_date)?if_exists}'/>
	         <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('subject','${recom.sub_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
	          <#if recom.title?length lt 18>
				${recom.title?if_exists}
			  <#else>
				${recom.title[0..16]}...
			  </#if></a></li>
	      </#list>
      </ul>
      <div class="area_title" style="margin-top:10px;"><h2 class="title_text">专题推荐</h2></div>
      <ul class="ph_main">
	      <#list recomList as recom>
	      	<#assign rbttime='${(recom.in_date)?if_exists}'/>
	         <li><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('subject','${recom.sub_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
	          <#if recom.title?length lt 20>
				${recom.title?if_exists}
			  <#else>
				${recom.title[0..19]}...
			  </#if></a></li>
	      </#list>
      </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=70"></script>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=69"></script>
   </div>
 </div><!--r_body end-->
</div>
<div class="clear"></div>
<@s.hidden name="searchText"/>
<#include "/${templateRoute?if_exists}/bottom.html">
<@s.hidden name="cat_id" id="cat_id_para"/>
</@s.form>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
    document.getElementById(para_name).value = para_value;
   	document.searchForm.submit();
}
$(document).ready(function(){
     /*专题排行*/
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
