<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>我的购物车-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/cart.css" rel="stylesheet" type="text/css" />
 <#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
 <script type="text/javascript">
   $("document").ready(function(){
		mallReadCookies();
 });
  </script>
</head>

<body>
<#include "/WEB-INF/template/bmall/default/top.ftl" >
<!--导航结束-->
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}" target="_blank">首页</a> > 购物车</p></div>

<div class="w980">
  <div class="location"><span class="em">1.我的购物车</span><span>2.填写核对信息单</span><span >3.成功提交订单</span></div>
  
  <div class="cartProduct">
     <table id="carttable">
            <tr>
                <th class="product"> 商品 </th>
                <th class="name"> 名称 </th>
                <th class="size"> 属性 </th>
                <th class="point"> 赠送积分 </th>
                <th class="price"> 单价 </th>
                <th class="amount"> 数量 </th>
                <th class="present"> 优惠 </th>
                <th class="amount"> 小计 </th>
                <th class="operate"> 操作 </th>
            </tr>
           
     </table>
  </div>
  <div class="pay_bar">
     <ul>
        <li class="op"><a  href="###;" onclick="mallClearAllCookies();">清空购物车</a>
       <span class="total">共选中<span id="goodscountall">0</span>种商品</span></li>
        <li class="price">商品总价（不含运费）：<span id="allcountmoney">0.00</span>元</li>
        <li class="goOn"><a href="${cfg_mallindexurl}" target="_blank">
        <img src="/templets/bmall/images/goOn.gif" /></a><a  style="text-decoration:none;"  href="/mall/goods!orderinfo.action"  >
        <img id="goodsjs" src="/templets/bmall/images/Js.gif" /></a></li>
     </ul>
  </div>
</div>
<div class="w980">
  <div class="cuxiao">
    <ul class="tab">
        <li class="selected"><a href="###;">推荐商品</a></li>
        <li style="display:none;"><a href="###;">最近浏览商品</a></li>
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
<!--用于加入收藏夹-->

<input id="collect_title" type="hidden" />
<input id="collect_link_url" type="hidden" />
<div class="clear"></div>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.cookie.js"></script>
 <script type="text/javascript" src="/templets/bmall/js/cart.js"></script>
 <script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
