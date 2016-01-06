<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/credit.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<script type="text/javascript" src="/templets/bmall/js/change.js"></script>
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<@s.form action="/shop/${user_name?if_exists}/shopcredit.html" method="post">
<#include "/WEB-INF/template/shop/top.ftl">

<#include "/WEB-INF/template/shop/credittop.ftl">
<div class="clear"></div>

<div class="w980">
   <div class="discusslist">
   
     <div class="distitle">
       <ul>
         <li  class="dislbut1" >
           <a href="/shop/${user_name?if_exists}/shopcredit.html" >
           	买家的评价
           	</a>
         </li>
         <li class="dislbut2" >
          <a href="/shop/${user_name?if_exists}/shopcreditofbuy.html" >
         	给他人的评价
         </a>
         </li>
       </ul> 
     </div>
     
      <div class="discont" id="dis_mains1">
        <#if buygoodsevalList?if_exists && (buygoodsevalList?size > 0)>
       <table width="100%" cellpadding="0" cellspacing="0" class="distable">
         <tr><th width="5%"></th>
         <th width="35%"></th>
         <th width="20%">评价人</th>
         <th width="40%">宝贝信息</th></tr>
         <#list buygoodsevalList  as buylist>
         <tr>
	         <td width="5%">
	            <#if buylist.g_eval=="1">
	         	<img src="/templets/shop/images/xzhp_07.gif">
	         	<#elseif buylist.g_eval=="0">
	         	<img src="/templets/shop/images/xzzp_07.gif">
	         	<#elseif buylist.g_eval=="-1">
	         	<img src="/templets/shop/images/xzcp_07.gif">
	         	</#if>
	         	
	         </td>
	         <td width="35%">
	         	${(buylist.g_comment)?if_exists}<p>[${(buylist.evalcom_data)?if_exists}]</p>
	         </td>
	         <td width="20%">
	         	买家 ：<a href="###;" class="blue">${(buylist.user_name)?if_exists}</a>
	         </td>
	         <td width="40%">
	         	<a href="/mall-goodsdetail-${buylist.goods_id?if_exists}.html" target="_blank" class="blue">${(buylist.goods_name)?if_exists}</a><p class="orange">${(buylist.sale_price)?if_exists}元</p>
	         </td>
        </tr>
       </#list>

       </table>
       
	    <div class="page_main">
			    ${pageBar?if_exists} 
	 	</div>
	    <#else>
	     <div class="noinfor">
			抱歉！没有找到相关的评论。
		 </div>
	   </#if>
           
      </div>
            
     </div>
   </div>
</div>
<div class="clear"></div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
