<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>订单提交成功-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/help.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="/templets/bmall/js/js.js"></script>    
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/templets/bmall/js/orderinfo.js"></script>
<link href="/templets/bmall/css/cart.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<script type="text/javascript">
 $(document).ready(function(){
  mallClearAllCookies();
});
</script>
</head>

<body>

<#include "/WEB-INF/template/bmall/default/top.ftl" >
<!--导航结束-->
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}" target="_blank">首页</a> > 订单提交成功</p></div>

<div class="w980">
  <div class="location"><img src="/templets/bmall/images/xzwfpic_03.png"></div>
  
  <div class="cartProduct">
     <#if isSelfGoodString=="1">
     <table><tr><td style="color:red;">抱歉,由于会员不能购买会员自己发布的商品,系统已经在订单中删除您自己发布的商品！</td></tr></table>
     </#if>
     <table> 
        <tr>
            <th class="product"> 订单号 </th>
            <th class="name"> 订单状态 </th>
            <th class="size"> 订单总价 </th>
            <th class="operate"> 操作 </th>
        </tr>
     </table>
  </div>
  <div class="pay_bar">
  <table >
    <#list orderSuccessList as olist>
    
            <tr>
                <th width="40%" class="product">订单号:<span style="color:red;"> ${olist.orderid?if_exists}</span> </th>
                <th width="30%" class="name">订单状态:<span style="color:red;">未付款 </span></th>
                <th width="20%" class="size">订单总价:<span style="color:red;">${olist.order_price?if_exists}</span>元 </th>
                <th width="10%" class="operate"><a target="_blank" href="/mallpay-${olist.orderid?if_exists}.html" >付款</a> </th>
            </tr>

       </#list>
       </table>
  </div>
</div>

<div class="w980">
  <div class="cuxiao">
    <ul class="tab">
        <li class="selected"><a href="#">推荐商品</a></li>
        <li style="display:none;"><a href="#">最近浏览商品</a></li>
    </ul>
    <ul class="productList">
       <#assign tcnum=1>
       <#list goodsList as glist>
          <#if tcnum lt 7>
         <li>
         <a href="/mall-goodsdetail-${glist.goods_id?if_exists}.html" target="_blank" >
         <#if glist.img_path?if_exists==''>
                <img width="133" height="133" src="/include/images/nopic.jpg">
         <#else>
              <img  src="<#if glist.img_path?if_exists?contains(",")==true><#assign imgindexs=glist.img_path?if_exists?index_of(",",0)><#assign imgpath=glist.img_path?if_exists?substring(0,imgindexs)>${imgpath?if_exists} <#else> ${glist.img_path?if_exists}</#if> " 
               title="${glist.goods_name?if_exists}" alt="${glist.goods_name?if_exists}"     ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${imgpath?if_exists}','133','133')")}   class="lazy"  />
         </#if>
         </a>
         <a class="name" href="/mall-goodsdetail-${glist.goods_id?if_exists}.html" target="_blank"> 
         	<#if glist.goods_name?if_exists.size lt 17>${glist.goods_name?if_exists}<#else>${glist.goods_name[0..15]?if_exists} </#if>
         </a>
         <div class="price">现价：￥<b>${glist.sale_price?if_exists}</b>元</div>
         </li>
         <#assign tcnum=tcnum+1>
         </#if>
         
        </#list>
        <div class="clear"></div>    
    </ul>
  </div>

</div>
<div class="clear"></div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">

</body>
</html>
