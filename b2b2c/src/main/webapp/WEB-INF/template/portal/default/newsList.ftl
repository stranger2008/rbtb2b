<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/information_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/news!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   	  <#include "/WEB-INF/template/portal/default/cateList.ftl" />   
   	   <#include "/WEB-INF/template/portal/default/attrList.ftl" />     
   <div class="flll_main">
   <#if newList?if_exists && (newList?size > 0)>
        <ul class="infor_mat">
          <#list newList as news>
         <#assign rbttime='${(news.in_date)?if_exists}'/>  
	          <li>	          
	          <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('news','${news.news_id?if_exists}','${rbttime?if_exists}')")}" 
	          target="_blank">
	          <#if news.title?length lt 36>${news.title?if_exists}<#else> ${news.title[0..35]}</#if>
	          </a>
	          <#if rbttime?length lt 10>${rbttime?if_exists}<#else> ${rbttime[0..9]}</#if>      
	          </li>
	          
	          <#if (news_index+1)%5==0>
	          	<li><hr style="border:1px dotted #cecece;"/></li>
	          </#if>
	          
          </#list>           
        </ul>        
        <div class="page_main">
          ${pageBar?if_exists}<br/>
      </div>
            <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
      <div class="clear"></div>
     </div>
   </div>
   <div class="right_main f_right">
      <div class="area_title"><h2 class="title_text">热门资讯</h2></div>
      <ul class="area_main">
           <#if newsTopList?if_exists && (newsTopList?size > 0)>
	          <#list newsTopList as topList>
	           <#assign rbttime='${(topList.in_date)?if_exists}'/> 
	          <li><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('news','${topList.news_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
	          <#if topList.title?length lt 16>${topList.title?if_exists}<#else> ${topList.title[0..15]}</#if>
	          </a></li>
	          </#list>
          </#if>
      </ul>
      <div class="area_title"><h2 class="title_text">推荐排行</h2></div>
      <ul class="ph_main">
          <#list recomList as recom>
          <#assign rbttime='${(recom.in_date)?if_exists}'/> 
          <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('news','${recom.news_id?if_exists}','${rbttime?if_exists}')")}" target="_blank"> 
         <#if recom.title?length lt 16>${recom.title?if_exists}<#else> ${recom.title[0..15]}</#if>
         
          </a></li>
          </#list>
   </ul>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=59"></script>
   </div>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=60"></script>
   </div>
  </div>
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
     /*资讯排行*/
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
