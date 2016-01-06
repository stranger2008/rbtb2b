<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/introduce.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<#include "/WEB-INF/template/shop/top.ftl">

<div class="w980">

  <div class="introback">
   <div class="introcont">
    <h2 class="nh2">${(shopconfig.shop_name)?if_exists} </h2>
    <h2>基本信息</h2>
    <ul>
      <li>认证情况：<a href="#"><img src="/templets/shop/images/xzapprove_23.gif"></a></li> 
      <li>店铺：<a href="/shop/${user_name?if_exists}/shopindex.html" target="_blank" >${(shopconfig.shop_name)?if_exists}</a></li> 
      <li>卖家信用：</li>
      <li>买家信用：</li>
      <li>宝贝信息：<a href="/shop/${user_name?if_exists}/goodslist.html" target="_blank">出售中的宝贝</a></li>
      <li>主营产品：<a href="/shop/${user_name?if_exists}/goodslist.html" target="_blank">${(shopconfig.busi_range)?if_exists}</a></li>
      <li>所在地区：${member.area_attr?if_exists}</li>
      <li>注册时间：${(shopconfig.in_date)?if_exists[0..10]} </li>
    </ul>
    <h2>联系信息</h2>
    <ul>
      <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q Q：</li>
      <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MSN：</li>
      <li>站内信件：</il>
    </ul>
   </div>
    
  </div>
  
</div>

<div class="clear"></div>
  
  
  
  
<div class="clear"></div>
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
