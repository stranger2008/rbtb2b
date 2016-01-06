<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/classification.css" rel="stylesheet" type="text/css" />
<title>${list_seo_title?if_exists}</title>
</head>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<!--头部logo——search结束-->
<!--导航结束-->

<div class="w960">
  <div class="main"> 
     <script src="/include/advshow.jsp?pos_id=117&area_id=${hidden_area_id?if_exists}"></script>
     <#assign s = (classCatList?size/2)?int />
      <#list classCatList as classCat>
      <#if classCat_index==0><div class="mains"></#if>
       <#if classCat_index==(classCatList?size/2)?int><div class="mains2"></#if>
       <div class="m_cont">
           <h2 class="m_title">
           <a href="/classified/cat-${classCat.cat_id?if_exists}.html">${(classCat.cat_name)?if_exists}</a><span>»</span></h2>
           <div class="a_cont">
            <#assign n = 0 />
            <#list twoclassCatList as twoclassCat>
                  <#if n lt 16>
                  <#list twoclassCat as twocat>
                    <#if twocat.up_cat_id == classCat.cat_id?if_exists>
                     <#assign n = n+1 />
                     <a href="/classified/cat-${twocat.cat_id?if_exists}.html" target="_blank">${(twocat.cat_name)?if_exists}</a>
                    </#if>
                  </#list>
                  </#if>
             </#list>
          </div>
      </div>
      <#if classCat_index==((classCatList?size/2)?int)-1></div></#if>
      <#if classCat_index==classCatList?size-1></div></#if>
      </#list>  
  </div>  

<!--侧栏-->
   <div class="sidebar">
   
       <div class="hy">
           <h2>欢迎访问<span>${area_name?if_exists}&nbsp;分站</span></h2>  
     </div>
        
        <ul class="contact">
            <li class="tel">电话：${(organize.phone)?if_exists}</li></br>
            <li class="qq">QQ：${(organize.qq)?if_exists}</li></br>
            <li class="msn">MSN：${(organize.msn)?if_exists} </li></br>
            <li class="skype">Skype：${(organize.skype)?if_exists}</li></br>
            <li class="e_mail">邮箱：${(organize.email)?if_exists} </li></br>
            <li class="cz">传真：${(organize.fax)?if_exists}</li></br>
            <li class="report"><a href="/member_Memberreport_add.action">虚假信息举报</a> <a href="/member_Complaint_add.action">投诉</a> </li>
        </ul>

        <div class="link">
          <p class="links"><span class="right_title_main">赞助商链接</span><span class="right_title_more"><a href="http://www.ruibaotong.net" target="_blank"/>我要加入</a></span></P>
          <div class="links_main">
             <script src="/include/advshow.jsp?pos_id=118&area_id=${hidden_area_id?if_exists}"></script>
             <script src="/include/advshow.jsp?pos_id=119&area_id=${hidden_area_id?if_exists}"></script>
          </div>
        </div>
           
   </div>
   
</div>

<!--侧栏结束-->
</div>


<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
