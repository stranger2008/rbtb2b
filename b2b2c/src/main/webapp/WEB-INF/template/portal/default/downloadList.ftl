<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/download_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/download!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
  <div class="left_main f_left">
   	 <#include "/WEB-INF/template/portal/default/cateList.ftl" />   
      <div class="clear"></div>
       <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
<#if downloadList?if_exists && (downloadList?size > 0)>
      <div class="dldetail_title">
      <ul>
       <li <#if "1"==sortbycondition>class="dldetail_title_h"</#if> ><a  href="###;" onclick="setHiddenVal('sortbycondition','1');">按时间排序</a></li>
       <li <#if "2"==sortbycondition>class="dldetail_title_h"</#if>><a href="###;" onclick="setHiddenVal('sortbycondition','2');">按点击量排序</a></li>
     </ul>
     </div>

<div class="dldetail_main">
      <#list downloadList as download>
        <ul class="dldetail_main_mid">
        <#assign rbttime='${(download.in_date)?if_exists}'/>
           <li class="dldetail_main_title">
           <a style="background:url(/include/images/downloadimages/${download.file_type?if_exists}.gif) no-repeat left center;padding-left:20px;" 
           href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('download','${download.down_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">${download.title?if_exists}</a><span>[下载工具]</span></li>
           <li class="dldetail_main_text1"><span class="dldetail_main_text1_n">
            <#if 0==download.fare?if_exists>免费软件<#else>共享软件</#if></span>
            <span class="dldetail_main_text1_n">更新时间：${download.update_time?if_exists}</span>
            <span class="dldetail_main_text1_n">人气：${download.down_num?if_exists}</span>
            <span class="dldetail_main_text1_last">软件大小：${download.file_size?if_exists} ${download.size_unit?if_exists}</span></li>
            <li class="dldetail_main_text2"><a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('download','${download.down_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">
            <#if download.down_desc?length lt 130>
                    ${download.down_desc?if_exists}
			<#else>
					${download.down_desc[0..129]}...
			</#if>
            </a></li>
        </ul>
      </#list>
        <div class="page_main">
          <div style="text-align:center;margin-top:13px;">
              ${pageBar?if_exists}<br/>   
          </div>    
      </div>
      <div class="clear"></div>
      <div class="right_bottom">
        <p class="f_right"><a href="#"><img src="/templets/${templateStyle?if_exists}/images/xtb_007.gif" />返回顶部</a></p>
      </div>

    </div>
       <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
    <div class="clear"></div>
      <div class="clear"></div><div class="clear"></div>
   </div>
   <div class="right_main f_right">
      <div class="area_title"><h2 class="title_text">下载排行</h2></div>
       <ul class="ph_main">       
      <#list downTopList as downtop>
         <#assign rbttime='${(downtop.in_date)?if_exists}'/>
         <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('download','${downtop.down_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">
          <#if downtop.title?length lt 26>
           ${downtop.title?if_exists}
			<#else>
			${downtop.title[0..25]}...
		  </#if>
         </a></li>  
       </#list>
   </ul>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=75"></script>
   </div>
    <div class="area_title"><h2 class="title_text">推荐下载</h2></div>
      <ul class="area_main">
      <#list recomList as recom>
       <#assign rbttime='${(recom.in_date)?if_exists}'/>
        <li><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('download','${recom.down_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">
          <#if recom.title?length lt 26>
           ${recom.title?if_exists}
			<#else>
			${recom.title[0..25]}...
		  </#if>
       </a></li>  
     </#list>
     </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=76"></script>
   </div>
   <div>
     <script src="/include/advshow.jsp?pos_id=77"></script>
   </div>
 </div>
</div>
<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="searchText"/>	
<@s.hidden name="sortbycondition" id="sortbycondition"/>
</@s.form>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
}
$(document).ready(function(){
     /*下载排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });
})
</script>
</body>
</html>
