<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>留言—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/message.css" rel="stylesheet" type="text/css">
<script src="/templets/company/tea/js/googlemap.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
</head>
<body>
<input type="hidden" id="company_address" value="${(member.address)?if_exists}"/>
<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="w960">
   <div class="postion"><span class="hpos">您当前所在的位置：</span><a href="/showroom/${user_name?if_exists}">茶馆首页</a><span>></span><a href="/showroom/${user_name?if_exists}/contactus.html">留 言</a></div>
   
   <div class="qycont">
   
      <div class="lw700"> 
       <div class="title">
         <div class="lmagtback"></div>
         <div class="rtback"></div>
          <div class="clear"></div>        
       </div> 
       <@s.form  action="/getleave.action?user_name=${user_name?if_exists}" method="post" validate="true">
       <!--留言-->
       <div class="message">
          <h2 class="magtitle">最新留言</h2>
          <#if memberinboxList?exists>
          <#list memberinboxList as memberinbox>
          <div class="newmag">
             <div class="lhandpic"><#if (memberinbox.logo_path)!=''><img src="${(memberinbox.logo_path)?if_exists}" width="75px" height="75px;"/><#else><img src="/templets/company/tea/images/head_07.gif"></#if>
                  
                  <p>
             <a href="#">
             <#if memberinbox.send_cust_id?if_exists=='-1'>游客<#else>${memberinbox.cust_name?if_exists}</#if>
             </a></p></div>
             <div class="rnewmag">
               <div class="newmagcont"><p class="newtp">标题:${memberinbox.title?if_exists}</p><p>内容:${memberinbox.content?if_exists}</p></div>
               <div class="newmagtime">[发表时间:${memberinbox.in_date?if_exists}]</div>
             </div>
          </div>
          </#list>
           <P class="page">
               ${pageBar?if_exists}
           </P>
         </#if>
         </@s.form>
           <@s.form action="/companyleave.action?user_name=${user_name?if_exists}" method="post" validate="true" onsubmit="return doCheckSubmit();">
          <h2 class="magtitle">给我留言</h2>   
           <div class="magcont">
              <table>
              	<tr height="30px;"><td width="10%"><span class="xzspan">标&nbsp;&nbsp;题<font color='red'>*</font></span></td>
              		<td><@s.textfield name="title" cssClass="magttext" maxLength="100" value=""/></td>
              		<td ><span class="xzts"><font color='red'><@s.fielderror><@s.param>title</@s.param></@s.fielderror></font></span></td>
              	</tr>
              	<tr height="30px;"><td><span class="xzspan f_left">内&nbsp;&nbsp;容<font color='red'>*</font></span></td>
              		<td><@s.textarea name="content" cssClass="tarea" value="" /></td>
              	<td ><font color='red'><@s.fielderror><@s.param>content</@s.param></@s.fielderror></font></td>
              	</tr>
              	<tr height="30px;"><td><span class="xzspan f_left">验证码<font color='red'>*</font></span></td>
              		<td><input type="text" maxlength="4" name="commentrand" id="rands" style="height:20px;width:50px;border: 1px solid #DDDDDD;vertical-align:middle;"/>
              		<img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/> </td>
              		<td><font color="red"><@s.fielderror><@s.param>commentrand</@s.param></@s.fielderror></font></td>
              	</tr>
              	<tr><td colspan="3"><p class="magp"><input type="submit" class="intbut" value="提 交"><font color='red'><@s.fielderror><@s.param>submit</@s.param></@s.fielderror></font></p></td></tr>
              </table>
            </div>
           </div>
           </@s.form>    
        </div>
     
    <#include "/WEB-INF/template/company/${temp_loc?if_exists}/right.ftl">
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
