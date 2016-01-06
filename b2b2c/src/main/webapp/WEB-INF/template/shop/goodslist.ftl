<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${(shopconfig.shop_name)?if_exists}-${cfg_mallwebname?if_exists}</title>
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<script type="text/javascript" src="/templets/shop/js/change.js"></script>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/shop/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/templets/shop/css/goodslist.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/shop/jsinclude.ftl">
</head>

<body>
<#include "/WEB-INF/template/shop/top.ftl">


<div class="w980">
 <#include "/WEB-INF/template/shop/left.ftl">
<@s.form id="shop" action="/shop/${user_name?if_exists}/goodslist.html" method="post">
<@s.hidden id="search_price_state" name="search_price_state"/>
<@s.hidden id="serarch_type" name="serarch_type"/>
<@s.hidden id="search_show" name="search_show"/>
<@s.hidden id="selName" name="selName"/>
<@s.hidden id="uppri" name="uppri"/>
<@s.hidden id="downpri" name="downpri"/>
<@s.hidden name="cat_id"/>
  <div class="contR">
      <div class="mod_bd">
         <p class="f_left">排序： 
	         <span class="mr"><a href="###;" onclick="goodsaction('mr','','${user_name?if_exists}')" <#if serarch_type?if_exists==''>class="redback"</#if>>默认</a></span>
	         <span class="time"><a href="###;"   onclick="goodsaction('time','','${user_name?if_exists}')" <#if serarch_type?if_exists=='time'>class="redback"</#if>>时间</a></span>
	         <span class="xs"><a href="###;"  onclick="goodsaction('sales','','${user_name?if_exists}')" <#if serarch_type?if_exists=='sales'>class="redback"</#if>>销售</a></span>
	         <span class="jg">
	          <#if search_price_state?if_exists=='down'>
		        <a href="###;" onclick="goodsaction('salesup','','${user_name?if_exists}')" class="redback">价格</a></span>
		         <#else>
		         <a href="###;" onclick="goodsaction('salesdown','','${user_name?if_exists}')" >价格</a></span>
		         </#if>
	         </span>
         </p>
         <p class="f_right">展现：
	         <span class="lb"><a href="###;"  onclick="goodsaction('','list','${user_name?if_exists}')" <#if search_show?if_exists=='list'>class="redback"</#if>>列表</a></span>
	         <span class="dt"><a href="###;"  onclick="goodsaction('','bigimg','${user_name?if_exists}')" <#if search_show?if_exists=='bigimg'>class="redback"</#if>>大图</a></span>
         </p>  
     </div>
      <div class="clear"></div>
     <#if goodsList?if_exists && (goodsList?size > 0)>
       <#if search_show?if_exists=="list">
        <ul class="goodst">
        
        <#list  goodsList as gbiglist>
        <li class="item_list">
             <div class="photo">
             
             <a href="/mall-goodsdetail-${gbiglist.goods_id?if_exists}.html" target="_blank" >
             
              <#if gbiglist.img_path?if_exists==''>
                  <img width="83" height="83" src="/include/images/nopic.jpg">
             <#else>
	              <img  src="<#if gbiglist.img_path?if_exists?contains(",")==true><#assign imgindexs=gbiglist.img_path?if_exists?index_of(",",0)><#assign imgpath=gbiglist.img_path?if_exists?substring(0,imgindexs)>${imgpath?if_exists}
			      <#else> ${gbiglist.img_path?if_exists}</#if> " 
			       title="${gbiglist.goods_name?if_exists}" alt="${gbiglist.goods_name?if_exists}"    width="83" height="83"  />
             </#if>
             
             </a></div>
             <h3> <a href="/mall-goodsdetail-${gbiglist.goods_id?if_exists}.html" target="_blank" >${gbiglist.goods_name?if_exists}</a></h3>
             <div class="attribute_nav">
                <ul>
                   <li class="sale"><span>&nbsp;</span><span style="display:">销量：<strong>${gbiglist.saled_num?if_exists}</strong></span></li>
                   <li class="price">
                     <p class="price_wrap">
                        <strong class="strong">¥${gbiglist.sale_price?if_exists}</strong>
                        <span>¥${gbiglist.market_price?if_exists}</span>
                     </p>
                   </li>
                 </ul>  
             </div>
         </li>
          </#list>    
     </ul>
     <div class="clear"></div>
       <#else>
          <ul class="goods">
          
            <#list  goodsList as glist>
           <li><a href="/mall-goodsdetail-${glist.goods_id?if_exists}.html" target="_blank" >
											      
	      <#if glist.img_path?if_exists==''>
              <img width="216" height="263" src="/include/images/nopic.jpg">
         <#else>
              <img  src="<#if glist.img_path?if_exists?contains(",")==true><#assign imgindexs=glist.img_path?if_exists?index_of(",",0)><#assign imgpath=glist.img_path?if_exists?substring(0,imgindexs)>${imgpath?if_exists}
		      <#else> ${glist.img_path?if_exists}</#if> " 
		       title="${glist.goods_name?if_exists}" alt="${glist.goods_name?if_exists}"    ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${imgpath?if_exists}','216','263')")}  />
         </#if>
	      
	      <br />
	      <#if glist.goods_name?if_exists.size lt 17>
	      ${glist.goods_name?if_exists}
	      <#else>
	       ${glist.goods_name[0..15]?if_exists}
	      </#if>
	      </a><br /><p class="price">
	      <span class="market">市场价：<span>￥${glist.market_price?if_exists}</span></span><span class="sold">售价：￥${glist.sale_price?if_exists}</span></p>
	      </li>
		</#list>  
       
     </ul>
     <div class="clear"></div>
       </#if>
	    <div class="page_main">
			    ${pageBar?if_exists} 
	 	</div>
	    <#else>
	     <div class="noinfor">
			抱歉！没有找到相关的商品。
		 </div>
	   </#if>
  </div>
<div class="clear"></div>
</div>
  
  
  
  
<div class="clear"></div>
</div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
