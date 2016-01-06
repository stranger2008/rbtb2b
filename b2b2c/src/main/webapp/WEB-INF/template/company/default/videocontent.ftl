<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(video.title)?if_exists}—视频详情—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > <a href="/showroom/${user_name?if_exists}/video.html" style="font-size:14px;">公司视频</a> > 视频详情</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部开始-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
      <div class="right_main">
            <div class="r_title">
              <h2><span class="title_font">公司视频</span></h2>
            </div> 
            <div class="other_main">
                <div class="video">
                 <embed src="${(video.video_path)?if_exists}" width="480" 
                 height="400" type="application/x-shockwave-flash" 
                 extendspage="http://get.adobe.com/flashplayer/" 
                 autostart="true" quality="high" allowfullscreen="true">
                 </embed>
                </div>
           </div>
       </div>
       <div class="right_main padding_10">
       <@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post" >
        <input type="hidden" name="infotype" id="info_type" value="video" />
  		<input type="hidden" name="infoids" id="infoids" value="${(video.video_id)?if_exists}" />
  		<input type="hidden" name="commtitle" id="comment_title" value="${(video.title)?if_exists}" />
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
