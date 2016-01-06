<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>资质证书—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 荣誉资质 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部结束-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
       <!--目录式浏览-->
       <@s.form action="/companymembercert.action?user_name=${user_name?if_exists}" method="post" validate="true">
       <div class="right_main">
           <div class="r_title"><h2><span class="title_font">资质证书</span></h2></div> 
            <div class="other_main">
            
            <table width="100%" border="0" cellspacing="3" cellpadding="1" >
            	<tr align="center" style="background:#efefef;font-size:13px;height:30px;">
            		<td width="30%" >证书</td>
            		<td width="20%">证书名称</td>
            		<td width="15%">发证机构</td>
            		<td width="15%">发证日期</td>
            		<td width="15%">到期日期</td>
            	</tr>
            	<#if membercertallList.size()==0>
            	   <tr align="center"><td colspan='5'>暂无数据</td></tr> 
            	<#else>        	
	            	<#list membercertallList as membercertall>
		            	<tr align="center">
		            		<td width="30%" >
		            		<div><a href="/showroom/${user_name?if_exists}/cert/detail_${membercertall.cert_id?if_exists}.html">
		            			<#if membercertall.img_path?if_exists>
		            				<img src="${membercertall.img_path?if_exists}" width="160px;"height="100px;"/>
		            			<#else>
		            				<img src="/include/images/nopic.jpg" width="160px;"height="100px;"/>
		            			</#if>							                
		            			</a>
		            		</div></td>
		            		<td width="20%"><a href="/showroom/${user_name?if_exists}/cert/detail_${membercertall.cert_id?if_exists}.html">${membercertall.title?if_exists}</a></td>
		            		<td width="15%">${membercertall.organize?if_exists}</td>           		
		            		<td width="15%">${membercertall.start_date?if_exists}</td>
		            		<td width="15%">${membercertall.end_date?if_exists}</td>
		            	</tr>
	            	</#list>
            	</#if>
            </table>
  			<#if  membercertallList?if_exists && (membercertallList.size()>0)>
              <!--目录方式浏览--> 
               <P class="page">
               ${pageBar?if_exists}
               </P>
             </#if>  
               <div class="clear"></div>
           </div>
       </div>
       </@s.form>
    <!--目录式浏览-->
   
  </div>
      
  <!--右部结束--> 
 
</div>

<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
