<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/vote.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/vote_detail.js"></script>
<title>${vote.vote_title?if_exists}-投票调查${webtitleString?if_exists}</title>
</head>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position">
  <P>当前位置: <a href="/index.html">首页</a> > <a href="/vote/list.html">投票调查</a> > 正文</P></div>

<@s.form action="/portal/vote!update.action" method="post" name="searchForm">
<div class="w960">
  <div class="company_intro">
    <h2 class="company_intro_t">投票调查</h2>
     
     <h3 class="company_title">${vote.vote_title?if_exists}</h3>
     <P class="company_time"><span class="time_litle">添加时间：${vote.in_date?if_exists}&nbsp;&nbsp;&nbsp;&nbsp;有效期：${vote.start_date?if_exists} 至 ${vote.end_date?if_exists}</span> 
     <span class="com_ps">    
	 <#if isshowvote?if_exists=='1'>
         [进行中]
     <#elseif  isshowvote?if_exists=='2'>
         [未开始]
     <#else>
        [已结束]
     </#if>
     总票数：<font color="#CC0000">${vote.vote_count?if_exists}</font></span></P>
     <P style="padding:20px; line-height:22px; font-size:14px;" >
     <#if (vote_optionList?size > 0)>
     ${vote.vote_title?if_exists}
     </#if>
     </P>
     <div class="vote_show">
			<table cellpadding="6" cellspacing="1" width="100%" bgcolor="#E3EEF5">
			  <#if (vote_optionList?size > 0)>
			    <#list vote_optionList as voteoption>
                <tr bgcolor="#FFFFFF">
                    <td width="20"><div class="vote_show_n">${voteoption_index?if_exists+1}</div></td>
                    <td>${voteoption.option_name?if_exists}</td>
                    <td width="260"><div class="vote_show_p"><div class="vote_show_1" style="width:${voteoption.pers?if_exists}%;"></div></div></td>
                    <td width="80" align="center">${voteoption.pers?if_exists}%</td>
                    <td width="80" align="center">${voteoption.option_count?if_exists}</td>
                </tr>
                </#list>  
              <#else>
       			<tr bgcolor="#FFFFFF" class="search_no_info"><td>对不起！没有投票选项信息!</td></tr>
    	      </#if>
		    </table>
	 </div>
	 <#if isshowvote?if_exists=='1'>
      <div class=vote>
      <div><a href="###;" target=_blank>${vote.vote_title?if_exists}</a></div>
      <input type="hidden" id="ismultis" name="vote_is_multi"  value="${vote.is_multi?if_exists}" >
      <input type="hidden" id="voteids" name="vote_id"  value="${vote.vote_id?if_exists}" >
      
      <#if (vote_optionList?size > 0)>
        <ul>
		<#list vote_optionList as voteoption>
           <li>
            <#if vote.is_multi?if_exists=='checkbox' >
        	<input type="checkbox" name="chkbtvalue" id="${voteoption.option_id?if_exists}" value="${voteoption.option_id?if_exists}" >
        	<#elseif vote.is_multi?if_exists=='radio'>
        	<input type="radio" name="chkbtvalue" id="${voteoption.option_id?if_exists}" value="${voteoption.option_id?if_exists}" >
        	</#if>
        	${voteoption.option_name?if_exists}
        <!--<@s.radio name="job_state_s" list="vote_optionList" listValue="option_name" listKey="option_id" />-->    
          </li>   
        </#list>  
        </ul>
        <#else>
       	   <div class="search_no_info">对不起！没有投票选项信息!</div>
    	</#if>
       <p><input class="btn" type="button" onclick="addvotes();"  value="投票"></p>
      </div>
      </#if>
  </div>
</div>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
</body>
</html>
