<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(memberconfig.web_title)?if_exists}—信用指数明细</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="/templets/${templateStyle?if_exists}/css/galleryview.css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
</head>
<@s.form action="/member_Credithistory_list.action" method="post">
<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 信用指数明细</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
 <!--左部开始-->
 <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
 <!--左部结束-->
  
<div class="r_body f_right">
      <div class="right_main">
            <div class="r_title"><h2><span class="title_font">信用指数明细</span></h2></div> 
            <div class="other_main">
              
   <table width="100%" class="cont_title">
	</table>
	<div style="border:1px solid #e1e2e3;border-bottom:none;margin-top:10px;">
    <table width="100%" cellspacing="0" border="0" >
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="40%"  align="center" class="top_td">指数理由</td>
    <td width="20%"  align="center" class="top_td">指数值</td>
    <td width="20%"  align="center" class="top_td">现有指数</td>
    <td width="20%"  align="center" class="top_td">获得时间</td>
    </tr>
   <#list credithistoryList as  credithistory>
  <tr> 
    <td align="left">
       ${credithistory.reason?if_exists}
    </td>
        <td align="center">
         ${credithistory.c_num?if_exists}
    </td>
        <td align="center">
         ${credithistory.now_num?if_exists}
    </td>
        <td align="center">
         ${credithistory.in_date?if_exists}
    </td>
  </tr>
  </#list>
    </table>
     
             <div class="clear"></div>
           </div>
       </div> 
      
</div>
      
  <!--右部结束--> 
 
  </div>
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</@s.form>
</body>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/include/js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="/include/js/jquery.timers-1.1.2.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/supply.js"></script>
</html>
