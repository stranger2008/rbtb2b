<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(membercert.title)?if_exists}—资质详情—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="/templets/${templateStyle?if_exists}/css/galleryview.css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > <a href="/showroom/${user_name?if_exists}/membercert.html" style="font-size:14px;">荣誉资质</a> > 资质详情</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
 <!--左部开始-->
 <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
 <!--左部结束-->
  
<div class="r_body f_right">
      <div class="right_main">
            <div class="r_title"><h2><span class="title_font">证书详细信息</span></h2></div> 
            <div class="other_main">
              
              <div class="prod">
                  <div class="porod_pic f_left" style="text-align:center;">
                  	<#if (membercert.img_path)?if_exists!=''>
					   <img  src='${(membercert.img_path)?if_exists}' onload="if(this.width>500){this.width = 200;}"/>
					<#else>
					   <img  src='${cfg_nopic?if_exists}' onload="if(this.width>200){this.width = 200;}"/>
					</#if>
                  </div>
                  <ul class="cp_fu f_left">
                     <li><span class="cp_l">证书名称：</span><span class="cp_fu_t">${(membercert.title)?if_exists}</span></li>
                     <li><span class="cp_l">颁发机构：</span>${(membercert.organize)?if_exists}</li>
                     <li><span class="cp_l">发证日期：</span>${(membercert.start_date)?if_exists.substring(0,10)}</li>
                     <li><span class="cp_l">到期日期：</span>${(membercert.end_date)?if_exists.substring(0,10)}</li>
                     <li><span class="cp_l">发布日期：</span>${(membercert.in_date)?if_exists.substring(0,10)}</li>
                  </ul> 
  			 </div>
             <div class="clear"></div>
           </div>
       </div>
    
      <div class="right_main padding_10">
            <div class="r_title"><h2><span class="title_font">证书介绍</span></h2></div>
            <div class="other_main">
              <div style="width:724px;padding:0px 10px 0px 10px;">
               ${(membercert.cert_desc)?if_exists}
              </div> 
            </div> 
     </div>
     
     
   <div class="right_main padding_10">
   <@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post" >
   <input type="hidden" name="infotype" id="info_type" value="cer" />
   <input type="hidden" name="infoids" id="infoids" value="${(membercert.cert_id)?if_exists}" />
   <input type="hidden" name="commtitle" id="comment_title" value="${(membercert.title)?if_exists}" />
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
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/include/js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="/include/js/jquery.timers-1.1.2.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/supply.js"></script>
</html>
