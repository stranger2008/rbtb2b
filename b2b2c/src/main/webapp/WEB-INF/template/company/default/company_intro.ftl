<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/company/css/company_intro.css" rel="stylesheet" type="text/css" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<title>${(memberintro.cust_name)?if_exists}</title>
</head>

<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/" style="font-size:14px;">首页</a>><#if (memberintro.mem_type)?if_exists=='0'> 公司信息<#elseif (memberintro.mem_type)?if_exists=='1'>个人信息</#if></P></div>


<div class="w960">

  <#if (memberintro.mem_type)?if_exists=='0'>
  <div class="company_intro">
      <h2 class="company_intro_t">关于我们</h2>
      <div class="company_intro_main">
          <h2 class="company_name">${(memberintro.cust_name)?if_exists}</h2>
         <p> ${(memberintro.cust_desc)?if_exists}</p>
      </div>
  </div>
  
  
  <div class="company_intro pa_top">
      <h2 class="company_intro_t">详细信息</h2>
      <div class="company_intro_main">
          <P><b>主营产品或服务：</b>${(memberintro.cust_supply)?if_exists}</P>
          <P><b>经营方式：</b> ${(memberintro.client_status)?if_exists}</P>    
          <P><b>主营行业：</b>${(memberintro.main_product)?if_exists}</P>  
      </div>
  </div>
  
  
  <div class="company_intro pa_top">
      <h2 class="company_intro_t">联系我们</h2>
      <div class="company_intro_main">
           <div class="contact_infor">
               <h2 class="contact_name">${(memberintro.cust_name)?if_exists}</h2>
               <ul>
                  <li>注册日期：${(memberintro.in_date)?if_exists}</li>
                  <li>联系人：${(memberintro.contact_name)?if_exists}  </li>
                  <li>电话:${(memberintro.phone)?if_exists} </li>
                  <li>传真：${(memberintro.fax)?if_exists} </li>
                  <li>公司网址：<a href="${(memberintro.website)?if_exists}">${(memberintro.website)?if_exists}</a></li>
                  <li>地址： ${(memberintro.address)?if_exists} </li>
                  
                  <li>在线联系：
                  	<#if (memberintro.contact_qq)?if_exists != ''>
                  		<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(memberintro.contact_qq)?if_exists}&site=qq&menu=yes">
                  			<img border="0" style="vertical-align:middle;" src="http://wpa.qq.com/pa?p=2:${(memberintro.contact_qq)?if_exists}:41" alt="点击这里给我发消息" title="点击这里给我发消息">
                  		</a>
                  	</#if>
                  	
                  	<#if (memberintro.contact_msn)?if_exists != ''>
                  		<a target="_blank" href="msnim:chat?contact=${(memberintro.contact_msn)?if_exists}">
                  			<img src="/templets/company/images/msn.gif" border="0" style="vertical-align:middle;"/>
                  		</a>
                  	</#if>
                  	
                  </li>
                  
               </ul>
           </div>
           
      </div>
  </div>
  <#elseif (memberintro.mem_type)?if_exists=='1'>
  
  <div class="company_intro pa_top">
      <h2 class="company_intro_t">个人信息</h2>
      <div class="company_intro_main">
          <P><b>个人名称：</b>${(memberintro.cust_name)?if_exists}</P>
          <P><b>性别：</b> ${(memberintro.contact_sex)?if_exists}</P>    
          <P><b>邮箱：</b>${(memberintro.email)?if_exists}</P>
          <P><b>电话：</b>${(memberintro.phone)?if_exists}</P>
          <P><b>手机：</b> ${(memberintro.cell_phone)?if_exists}</P>    
          <P><b>所在地区：</b>${(memberintro.area_attr)?if_exists}</P>
          <P><b>联系地址：</b>${(memberintro.address)?if_exists}</P>
      </div>
  </div>
  </#if>  

</div>

<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
