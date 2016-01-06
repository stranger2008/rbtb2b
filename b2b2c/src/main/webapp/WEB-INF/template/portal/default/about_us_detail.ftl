<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/About_us_detailed.css" rel="stylesheet" type="text/css" />
<title>${aboutus.title?if_exists}-${cfg_webname?if_exists}</title>
</head>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a>>${aboutus.title?if_exists}</P></div>


<div class="w960">
    <ul class="about_us">
     <#list comparaList as compara>
      <li><a href="/aboutus_${compara.para_value?if_exists}.html">${compara.para_key?if_exists}</a></li>
     </#list>
    </ul>
    
    <div class="about_main">
       <div class="industry_main">
         <h2 class="left_title">${aboutus.title?if_exists}</h2>
         <div class="date">
           <p><span class="date_text">上传时间：${aboutus.in_date?if_exists}</span><span class="date_text"></span></p>
         </div>
         <div class="about_main_c">
           ${aboutus.content?if_exists}
         </div>
      </div>
       
    </div>



</div>
<div class="clear"></div>


<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
