<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/video_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/video!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
      <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
      <div class="clear"></div>   
       <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
       <#if (videoList?size > 0)> 
      <div class="flll_main">
         <div>         
         <#list videoList as video>
         <#assign rbttime='${(video.in_date)?if_exists}'/>  
            <div class="video_list" >
	            <div style="text-align:center;">            
	               <div  class="divMiddle" style="width:176px;height:133px;line-height:133px;">
		                <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('video','${video.video_id?if_exists}','${rbttime?if_exists}')")}"
		                 target="_blank" class="AMiddle"  style="width:176px;">
		                    <img src="${video.img_path?if_exists}"
		                     ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${video.img_path?if_exists}','166','123')")} class="ImgMiddle"/>
		                </a>
	               </div>
	               <p class="video_title"><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('video','${video.video_id?if_exists}','${rbttime?if_exists}')")}"
	              	   target="_blank">
	             	  <#if video.title?length lt 12>${video.title?if_exists}<#else>${video.title[0..11]}...</#if></a>
	               </p>
	            </div>   
            </div>       
          </#list>              
         </div>
         <div class="clear"></div>
       <div class="page_main">
         <div style="text-align:center;">
            ${pageBar?if_exists}<br/>  
         </div>  
      </div>
      <div class="clear"></div>  
      </div>  
     <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
     </#if>
   </div>
   <div class="right_main f_right">   
       <div class="area_title"><h2 class="title_text">推荐视频</h2></div>      
       <div class="hotpic_main">    
              <div class="clear"/>    
              <#list recomList as recom>
              <#assign rbttime='${(recom.in_date)?if_exists}'/>  
              <div class="hotpic_main_vlign">
	              <div style="text-align:center;margin-left:13px;">  
	                   <div  class="divMiddle" style="width:110px;height:82px;line-height:82px;">
			               <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('video','${recom.video_id?if_exists}','${rbttime?if_exists}')")}" target="_blank"  class="AMiddle"  style="width:110px;">
			                  <img src="${recom.img_path?if_exists}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${recom.img_path?if_exists}','100','72')")} class="ImgMiddle"/>
			               </a>
		               </div>
		               <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('video','${recom.video_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
		               		<p style="text-align:center;"><#if recom.title?length lt 7>${recom.title?if_exists}<#else>${recom.title[0..6]}...</#if></p>
		               </a>
		          </div>  
              </div>
            </#list>        
       </div>
       <div class="clear"></div> 
       </div> 
      <div class="area_title"><h2 class="title_text">视频排行</h2></div>
      <ul class="ph_main">
       <#list topList as top>
        <#assign rbttime='${(top.in_date)?if_exists}'/>  
         <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('video','${top.video_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">
              <#if top.title?if_exists!="">
              <#if top.title?length lt 16>${top.title?if_exists}
			  <#else>${top.title[0..15]}...
			  </#if>
			  </#if>
			  </a></li>
       </#list>    
      </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=73"></script>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=74"></script>
   </div>
 </div>
</div>
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
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });    
     $("img").lazyload(); 
});
</script>
</body>
</html>
