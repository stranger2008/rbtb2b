<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>${(buy.title)?if_exists}—求购详细—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="/templets/${templateStyle?if_exists}/css/galleryview.css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/addcomments_detail.js"></script>
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > <a href="/showroom/${user_name?if_exists}/buy.html" style="font-size:14px;">采购清单</a> > 采购详细</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
 <!--左部开始-->
 <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
 <!--左部结束-->
  
<div class="r_body f_right">
      <div class="right_main">
            <div class="r_title"><h2><span class="title_font">求购详细信息</span></h2></div> 
            <div class="other_main">
              
              <div class="prod">
                  <div class="porod_pic f_left">
                     <input type="hidden" value='${(buy.img_path)?if_exists}' id="imgpath"/>
                     <!-- 绑定商品图片开始 -->
                     <div id="photos" class="galleryview" >
                     </div>
                     <!-- 绑定商品图片结束 -->  
                  </div>
      
                  <ul class="cp_fu f_left">
                     <li><span class="cp_fu_t">${(buy.title)?if_exists}</span></li>
                     <li><span class="cp_l">规格要求：</span>${(buy.standard)?if_exists}</li>
                     <li><span class="cp_l">包装要求：</span>${(buy.pack)?if_exists}</li>
                     <li><span class="cp_l">价格要求：</span>${(buy.price)?if_exists}</li>
                     <li><span class="cp_l">数量要求：</span>${(buy.buy_num)?if_exists}</li>
                     <li><span class="cp_l">最后更新：</span>${(buy.in_date)?if_exists.substring(0,19)}</li>
                     <li><span class="cp_l">过期时间：</span>${(buy.end_date)?if_exists.substring(0,19)}</li>
                     <li><span class="cp_l">浏览次数：</span><span id="clicks">
                     <script src="/include/clicknum.jsp?t=buy&n=buy_id&v=${(buy.buy_id)?if_exists}"></script>
                     </span></li>
                     
                     <li class="inqiurybtn" onclick="document.sendInquiryForm.submit();">在线报价&nbsp;&nbsp;&nbsp;
                     <!-- 发送询价请求 -->
			         <form action="/member_Memberinbox_add.action" method="post" name="sendInquiryForm">
			         <input type="hidden" name="send_name" value="${(member.cust_name)?if_exists}"/>
			         <input type="hidden" name="memberinbox.mess_type" value="1"/><!--报价-->
                     <input type="hidden" name="loc" value="" />
			         </form>
			         <script>
			         document.sendInquiryForm.loc.value = document.location.href;
			         </script>
                     <!-- 发送询价请求 --> 
                     </li>
                  </ul> 
  			 </div>
             <div class="clear"></div>
           </div>
       </div>
    
      <div class="right_main padding_10">
            <div class="r_title"><h2><span class="title_font">产品介绍</span></h2></div>
            <div class="other_main">
               ${(buy.content)?if_exists}
            </div> 
     </div>
     
     
<div class="right_main padding_10">
<@s.form action="/portal/membercomment!addcomments.action" name="commentForm" method="post" >
  <input type="hidden" name="infotype" id="info_type" value="buy" />
  <input type="hidden" name="infoids" id="infoids" value="${(buy.buy_id)?if_exists}" />
  <input type="hidden" name="commtitle" id="comment_title" value="${(buy.title)?if_exists}" />
  <input type="hidden" id="idwidth" value="450" />
 <#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/commentinfo.html">
</@s.form>
 <!-- 评论信息标签的结束位置 -->
     
     
       
       
</div>
      
  <!--右部结束--> 
 
  </div>
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/supply.js"></script>
<script type="text/javascript" src="/include/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/include/js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="/include/js/jquery.timers-1.1.2.js"></script>
</body>
</html>
