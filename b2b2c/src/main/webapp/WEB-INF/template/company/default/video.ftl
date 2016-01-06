<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>公司视频—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 公司视频 </span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左边开始-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
      
       <!--目录式浏览-->
       <@s.form action="/companyvideo.action?user_name=${user_name?if_exists}" method="post" validate="true">
       <div class="right_main">
            <div class="r_title">
              <h2><span class="title_font f_left">公司视频</span></h2>
            </div> 
            <div class="other_main">
               <ul class="ccpx">
               <#list videoList as video>
               <li>
               <div  class="divMiddle" style="width:156px;height:126px;line-height:126px;">
	               <a href="/showroom/${user_name?if_exists}/video/detail_${video.video_id?if_exists}.html"  class="AMiddle"  style="width:156px;">
	               <img src="${video.img_path?if_exists}" width="146px" height="116px"
                      ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(video.img_path)?if_exists}','146','116')")} class="ImgMiddle"/></a>
               </div>	
               <p style="margin-top:6px;"><a  href="/showroom/${user_name?if_exists}/video/detail_${video.video_id?if_exists}.html"> 
               <#if video.title?length lt 7>${video.title?if_exists}<#else>${video.title[0..6]}...</#if></a></p>                
               </li> 
               </#list>
               <#if videoList.size()==0><span style="padding-left:20px;">暂无数据</span></#if>
              </ul>
              <div class="clear"></div>
              <!--目录方式浏览--> 
               <P class="page">
               ${pageBar?if_exists}
               </P>
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
