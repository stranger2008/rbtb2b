<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/vote.css" rel="stylesheet" type="text/css" />
<title>投票调查${webtitleString?if_exists}</title>
</head>

<body>
<@s.form action="/portal/vote!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置:  <a href="/index.html">首页</a> > 投票调查</P></div>


<div class="w960">
  <div class="company_intro">
    <h2 class="company_intro_t">投票调查</h2>
    <#if (voteList?size > 0)>
     <ul class="com_main">
        <#list voteList as vote>
         <#assign rbttime='${(vote.in_date)?string("yyyy-MM-dd")}'/>  
           <li>
           <a href="/portal/vote!view.action?vote_id=${vote.vote_id?if_exists}" target="_blank"><#if vote.vote_title?length lt 36 >${vote.vote_title?if_exists}<#else> ${vote.vote_title[0..35]}</#if>      
		     <#if vote.state_in?if_exists==1>
	             [进行中]
	         <#elseif  vote.state_before?if_exists==1>
	             [未开始]
	         <#else>
	             [已结束]
	         </#if>             
           </a>
           <span class="f_gray">票数:${vote.vote_count?if_exists}</span><span class="f_gray" align=middle>${vote.in_date?if_exists}</span></li>
          </#list>  
     </ul>
      <div class="page_main_r">
          ${pageBar?if_exists}<br/>
      </div>
     <#else>
        
     </#if>
  </div>


</div>

<#include "/${templateRoute?if_exists}/bottom.html">

</@s.form>
</body>
</html>
