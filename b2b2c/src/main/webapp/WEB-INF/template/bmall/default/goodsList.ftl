<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/List1.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/List2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/components/goodstree/dtree.js"></script>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<title><#if catName?if_exists==''>搜索<#else>${catName?if_exists}</#if>-${cfg_mallwebname?if_exists}</title>
</head>
<body>      
<@s.form action="/mall/goods!list.action" method="post">
<@s.hidden id="sales" name="sales"/>
<@s.hidden id="type" name="type"/>
<@s.hidden id="show" name="show"/>
<@s.hidden id="brandlight" value="${brandlight?if_exists}"/>
<@s.hidden id="en_name" name="en_name" value="${beiginen_name?if_exists}"/>
<@s.hidden id="cat_name" name="cat_name" value="${cat_name?if_exists}"/>
<#include "/WEB-INF/template/bmall/default/top.ftl">
<div class="clear"> </div>
<!--导航结束-->
<div class="w980">
  <div class="contL">
  <#if cattreeList?exists && ( cattreeList.size() > 0 ) > 
    <div class="Sidebar">
        <h2>商品分类</h2>
        <ul class="sideFl">
        <script type="text/javascript">
			a = new dTree('a');
			a.add(${beigincat_id?if_exists},-1,'');
			<#list cattreeList as cattree>
			a.add(${cattree.cat_id?if_exists},${cattree.up_cat_id?if_exists},'<a href="/mall-goodslist-${cattree.en_name?if_exists}.html">${cattree.cat_name?if_exists}</a>&nbsp;','#');
			</#list>
			document.write(a);
	  	</script>
        </ul>
    </div>
    </#if>
    <div class="Sidebar">
        <h2>热门产品</h2>
        <ul class="hotC">
        	<#if hotList?exists && ( hotList.size() > 0 ) > 
	           <#list hotList as hot>
	           		<li>
	           			<a href="/mall-goodsdetail-${hot.goods_id?if_exists}.html" alt="${hot.goods_name?if_exists}" title="${hot.goods_name?if_exists}" target="_blank">
	           			<#if hot.img_path?if_exists==''>
	           				<img width="200" height="260" src="/include/images/nopic.jpg">
	           			<#else>
	           				<img width="200" height="260" src="${hot.img_path?if_exists}" />
	           			</#if>
	           			</a><br /><P><span><b>￥${hot.sale_price?if_exists}</b></span><br />
	           			<a href="/mall-goodsdetail-${hot.goods_id?if_exists}.html" target="_blank">
	           			<#if hot.goods_name?length lt 30>
	           				${hot.goods_name?if_exists}
	           			<#else>
	           				${hot.goods_name?if_exists[0..29]}
	           			</#if>
	           			</a><br />销量：<b>最近成交${hot.saled_num?if_exists}笔</b></P>
	           		</li>
	           </#list>
           <#else>
	           <li class="noinfor">
	           		无热门商品
	           </li>
           </#if>
        </ul>
    </div>    
  </div>
   
  <div class="contR">
     <div class="position"><p class="f_left">您当前的位置：<#if postName?if_exists!=''>${postName?if_exists}<#else><#if brandname?if_exists!=''><a href="${indexName?if_exists}">首页</a> > ${brandname?if_exists}</#if>
<#if selvaluep?if_exists!=''><a href="${indexName?if_exists}">首页</a> > ${selvaluep?if_exists}</#if></#if>  </p><p class="f_right xg">相关商品<span>${count?if_exists}</span>件</p></div>
     <#if (( goodsBrandList?exists && ( goodsBrandList.size() > 0 ) ) || (  categoryattrList?exists && ( categoryattrList.size() > 0 ))	)> 
     <div class="contTj">
     
     	<#if goodsBrandList?exists && ( goodsBrandList.size() > 0 ) > 
     
         <div class="contPt">
            <span class="f_left">品牌：</span>
            <div class="f_left" class="xcont">
               <a style= "cursor:pointer;" onclick="brand('brand')"><span id="brand">不限</span></a>
	            <#list goodsBrandList as goodsBrand>
	               <a style= "cursor:pointer;" onclick="brand('brand${goodsBrand.brand_id?if_exists}')" ><span id="brand${goodsBrand.brand_id?if_exists}">${goodsBrand.brand_name?if_exists}</span></a> 
	            </#list>
            </div>
            <div class="clear"></div>
       	</div>
       	
       	</#if>
       
       <#if categoryattrList?exists && ( categoryattrList.size() > 0 ) > 
       
	       <div class="contP">
	       <#list categoryattrList as categoryattr>
	            <div class="chiMa"><span>${categoryattr.attr_name?if_exists}：</span> 
	            <#assign vals = "${categoryattr.default_val?if_exists}" />
	                      <a style="cursor:pointer;" onclick="attrselect('attr${categoryattr.attr_id?if_exists}|');">不限</a>
		            <#list vals?split("|") as s>
		                  <a style="cursor:pointer;" onclick="attrselect('${categoryattr.attr_id?if_exists}|${s}|${categoryattr.attr_name?if_exists}');"> ${s}</a>
		            </#list>
	            </div>
	       </#list>
	       <#if sbattr?if_exists!=''>
	        <div class="chiMa"><span>筛选条件：</span>
	          ${sbattr?if_exists}
	        </div>
	        </#if>
	        <div class="clear"></div>
	       </div>
	     </#if>
	       
     </div>
     
     </#if>
     
     
     <#if goodsList?if_exists && (goodsList?size > 0)>
     
		     <div class="mod_bd">
		         <p class="f_left">排序：<span class="mr"><a style= "cursor:pointer;" onclick="goodsactionbmall('ms','')" <#if type?if_exists==''>class="redback"</#if> >
		          默认
		         </a></span>
		         <span class="time"><a style= "cursor:pointer;" onclick="goodsactionbmall('time','')" <#if type?if_exists=='time'>class="redback"</#if>>时间</a></span>
		         <span class="xs"><a style= "cursor:pointer;" onclick="goodsactionbmall('sales','')" <#if type?if_exists=='sales'>class="redback"</#if>>销售</a></span>
		         <#if ordersales?if_exists=='down'>
		         <span class="jg"><a style= "cursor:pointer;" onclick="goodsactionbmall('salesup','')" class="redback">价格</a></span>
		         <#else>
		         <span class="jg"><a style= "cursor:pointer;" onclick="goodsactionbmall('salesdown','')" >价格</a></span>
		         </#if>
		         </p>
		         <p class="f_left">展现：<span class="lb"><a style= "cursor:pointer;" onclick="goodsactionbmall('','list')" <#if show?if_exists=='list'>class="redback"</#if>>列表</a></span><span class="dt"><a style= "cursor:pointer;" onclick="goodsactionbmall('','img')" <#if show?if_exists=='img'>class="redback"</#if>>大图</a></span></p>  
		     </div>
		      <div class="clear"></div>
      
      
      
		     <!-- 信息展示区开始 -->
		     <#if viewtype?if_exists=="list">
		      <ul class="goodslist">
		       <#list goodsList as goods>
		         <li class="item_list">
		             <div class="photo"><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" title="${goods.goods_name?if_exists}" alt="${goods.goods_name?if_exists}" target="_blank">
		             <#if goods.img_path?if_exists==''>
		             <img width="80" height="80" src="/include/images/nopic.jpg">
		             <#else>
		             <img width="80" height="80" src="${goods.img_path?if_exists}">
		             </#if>
		             </a></div>
		             <h3><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
		             <#if goods.goods_name?length lt 30>
	           			${goods.goods_name?if_exists}
	           			<#else>
	           			${goods.goods_name?if_exists[0..29]}
	           			</#if>
		             </a></h3>
		             <div class="attribute_nav">
		                <ul>
		                   <li class="sale"><span>&nbsp;</span><span style="display:">销量：<strong>${goods.saled_num?if_exists}</strong></span></li>
		                   <li class="place"></li>
		                   <li class="price">
		                     <p class="price_wrap">
		                        <strong class="strong">¥${goods.sale_price?if_exists}</strong>
		                        <span>¥${goods.market_price?if_exists}</span>
		                     </p>
		                   </li>
		                 </ul>  
		             </div>
		         </li>
		       </#list>
		        </ul>
		     <#else>
		      <ul class="goods">
		        <#list goodsList as goods>
		             <li>
	             		<div  class="goodsimg">
	             			<a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" title="${goods.goods_name?if_exists}" alt="${goods.goods_name?if_exists}" target="_blank">
	             			 <#if goods.img_path?if_exists==''>
		                     <img width="210" height="260" src="/include/images/nopic.jpg">
		                     <#else>
		                     <img width="210" height="260" src="${goods.img_path?if_exists}">
		                     </#if>
	             			</a>
	             		</div>
	             		<br /><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" target="_blank">
	             		<#if goods.goods_name?length lt 30>
	           			${goods.goods_name?if_exists}
	           			<#else>
	           			${goods.goods_name?if_exists[0..29]}
	           			</#if>
	             		</a><br />
		             	<p class="price">
		             		<span class="market">市场价：<span>￥${goods.market_price?if_exists}</span></span>
		             		<span class="sold">售价：￥${goods.sale_price?if_exists}</span>
		             	</p>
		             </li>
		        </#list>
		        </ul>
		     </#if>
		     
		     <!-- 信息展示区结束 -->
		     	<div class="clear"></div>
		     
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

<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
