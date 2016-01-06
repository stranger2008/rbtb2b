<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业新闻-${web_title?if_exists}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/information_list.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
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
</head>
<body>
<@s.form action="/portal/membernews!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置:<a href="/">首页</a> > 企业新闻 </P></div>
<div class="w960">
   <div class="left_main f_left">    
   <div class="flll_main">
   <#if (membernewsList?size > 0)>
        <ul class="infor_mat">
          <#list membernewsList as news>
         <#assign rbttime='${(news.in_date)?string("yyyy-MM-dd")}'/>  
	          <li><a href="/showroom/${news.user_name?if_exists}/news/detail_${news.cert_id}.html" target="_blank">
	          <#if news.title?length lt 36>${news.title?if_exists}<#else> ${news.title[0..35]}</#if>
	          </a>${rbttime?if_exists}</li>
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
      <div class="area_title"><h2 class="title_text">最新企业</h2></div>
      <ul class="area_main">
          <#list newsTopList as topList>
          <li><a href="/showroom/${topList.user_name?if_exists}" target="_blank">
	         <#if topList.cust_name?length lt 16>${topList.cust_name?if_exists}
			 <#else>${topList.cust_name[0..15]}... </#if></a></a></li></li>
          </#list>
      </ul>
   
      <div class="area_title"><h2 class="title_text">企业推荐</h2></div>
      <ul class="ph_main">
         <#list recomList as top>
	         <li><span class="n1">1</span><a href="/showroom/${top.user_name?if_exists}" target="_blank">
	         <#if top.cust_name?length lt 16>${top.cust_name?if_exists}
			 <#else>${top.cust_name[0..15]}... </#if></a></a></li>
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
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
</body>
</html>
