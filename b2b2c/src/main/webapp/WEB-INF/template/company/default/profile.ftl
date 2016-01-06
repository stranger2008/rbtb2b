<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>公司简介—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
<style>
.files li{
	width:700px;
}
</style>
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 公司简介 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部开始-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
  <div class="r_body f_right">
      <div class="right_main">
            <div class="r_title">
              <h2><span class="title_font">公司介绍</span></h2>
            </div> 
            <div class="other_main">
                <P class="jj_p">
                <#if (member.cust_desc)?if_exists!=''>
                	${member.cust_desc?if_exists}
                <#else>
                	暂无数据
                </#if>
                </p>
           </div>
       </div>
      <div class="right_main padding_10">
            <div class="r_title">
              <h2><span class="title_font">公司档案</span></h2>
            </div> 
            <div class="other_main">
             
			<ul class="files">
                <li><strong>公司名称：</strong>${(member.cust_name)?if_exists}</li>    
                <li><strong>经营模式：</strong>${(member.client_status)?if_exists}</li>
                <li><strong class="f_left">经营范围：</strong><span class="f_left" style="width: 630px; display:block;">${(member.main_product)?if_exists}</span></br></li>
                <li><strong>公司规模：</strong>${(member.staff_num)?if_exists}</li>
                <li><strong>注册资金：</strong>${(member.reg_money)?if_exists}${(member.reg_money_type)?if_exists}</li>
                <li><strong>注册年份：</strong>${(member.reg_date)?if_exists}</li>
                <li><strong>法人代表：</strong>${(member.corporate)?if_exists}</li>
                <li><strong>年销售额：</strong>${(member.year_sum)?if_exists}</li>
                <li><strong>商标：</strong>${(member.brand_name)?if_exists}</li>
                <li><strong>是否提供OEM代加工：</strong>${(member.isoem)?if_exists}</li>    
              </ul>
            
           </div>
       </div>
      <div class="right_main padding_10">
            <div class="r_title"><h2><span class="title_font f_left">联系方式</span><a href="/showroom/${user_name?if_exists}/contactus.html" class="more f_right">更多>></a></h2></div> 
            <div class="other_main">
              <ul class="files">
              	<li><strong>联 系 人：</strong>${(member.contact_depart)?if_exists} ${(member.contact_job)?if_exists} ${(member.contact_name)?if_exists}&nbsp;${(member.contact_sex)?if_exists}
              		<#if (member.contact_qq)?if_exists != ''>
              			<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(member.contact_qq)?if_exists}&site=qq&menu=yes">
                        	<img border="0" src="/templets/${templateStyle?if_exists}/images/sm_qq.gif"/>
                    	</a>
                    </#if>
                    <#if (member.contact_msn)?if_exists != ''>
	                    <a href="msnim:add?contact=${(member.contact_msn)?if_exists}" target="_blank">
	                        <img src="/templets/${templateStyle?if_exists}/images/sm_msn.gif"/>
	                    </a>
                    </#if>
              	</li>
              	<#if  member.phone?if_exists><li><span><strong>公司电话：</strong>${(member.phone)?if_exists}</span></li></#if>
              	<#if  member.contact_cellphone?if_exists><li><span><strong>手机号码：</strong>${(member.contact_cellphone)?if_exists}</span></li></#if>
              	<#if  member.email?if_exists><li><span><strong>电子邮件：</strong>${(member.email)?if_exists}</span></li></#if>
              	<#if  member.fax?if_exists><li><span><strong>公司传真：</strong>${(member.fax)?if_exists}</span></li></#if>
              	<#if  member.address?if_exists><li><strong>联系地址：</strong>${(member.address)?if_exists}</li></#if>
              	<#if  member.website?if_exists><li><strong>公司网址：</strong><a href="${(member.website)?if_exists}" target="_blank">${(member.website)?if_exists}</a></li> </#if>
              </ul>  
           </div>
       </div>
  <div class="right_main padding_10">
  <@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post" >
  <input type="hidden" name="infotype" id="info_type" value="company" />
  <input type="hidden" name="infoids" id="infoids" value="${(member.cust_id)?if_exists}" />
  <input type="hidden" name="commtitle" id="comment_title" value="${(member.cust_name)?if_exists}" />
  <input type="hidden" id="idwidth" value="450" />
  <#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/commentinfo.html">
  </@s.form>     
</div>
      
  <!--右部结束--> 
  </div>
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
