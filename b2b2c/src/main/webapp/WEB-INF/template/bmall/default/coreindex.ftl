<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/include/css/JSelectCategory.css" type="text/css" rel="stylesheet">
<link href="/templets/bmall/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
		$('#banner').cycle({ 
				fx:'scrollLeft',
				pager:'#btn'
		});
	})
</script>
</head>

<body>
<!--导航开始-->
<#include "/WEB-INF/template/bmall/default/top.ftl" >
<!--导航结束-->
<div class="w980">

 <div class="class">
   
 <div class="box">
<div class="allsort">
	<div class=mt>
		<strong>全部商品分类</strong>
	</div>
	<div class=mc>
	
	
	  <#assign cgcount=1>
	  <#list categoryIndexList as onecat>
	    <#if cgcount lt 10>
		    	<div class="item ">
				<span><h3><a href="/mall-goodslist-${onecat.en_name?if_exists}.html">${onecat.cat_name?if_exists}</a></h3><s></s></span>
				<div class=i-mc>
					<div class=subitem>
					<dl class=fore>
					
					 <#list twocategoryIndexList as twocat>
						     <#if twocat.up_cat_id=onecat.cat_id>						     
							    <dt>
							    	<a href="/mall-goodslist-${twocat.en_name?if_exists}.html">
								     <#if twocat.cat_name?length lt 5>
								      ${twocat.cat_name?if_exists}
								      <#else>
								       ${twocat.cat_name[0..3]?if_exists}
								      </#if>
								     </a>
								     
								    <dd style='width:420px;line-height:25px;height:25px;'>
									  <#list threecategoryIndexList  as therecat>
								         <#if therecat.up_cat_id=twocat.cat_id>
								  				<em><a href="/mall-goodslist-${therecat.en_name?if_exists}.html">${therecat.cat_name?if_exists}</a></em>								  				
								  		 </#if>
									  </#list>
									   
									</dd>
									<div class='clear'></div>
							    </dt>
							    <div class='clear'></div>
							  </#if>
					  </#list>
					  </dl>
					</div>
				</div>
			</div>
			<#assign cgcount=cgcount+1>
	    </#if>    
	 </#list>
	
		<div class=extra>
			<a target='_blank' href="/mallcate.html">全部商品分类</a>
		</div>
	</div>
</div>
</div>

   </div>
   

   <!--所有分类结束-->
   <div class="banner f_right">
	   <div id="banner">
	        <#assign advcount=1>
	        <#list advinfoIndexList as advlist>
		             <#if advcount lt 6>
			               <a href="${advlist.link_url?if_exists}"  target="_blank">
			               	 <img  src="${advlist.img_path?if_exists}" width="740px" height="353px"/>
			               </a>
		        	 </#if>
		        	   <#assign advcount=advcount+1>
	    	</#list>
		</div>
		<div id="btn"></div>
   </div>
   <!--Banner结束-->
   <br class="clear"/>
</div>
<!--Banner部分结束-->

<div class="w980">
   <div class="recom">
      <h2><a href="/mallbrand.html" target="_blank"></a></h2>
      <ul>
      
        <#list goodbrandIndexList as goodbrands>
         <li><a style= "cursor:pointer;" onclick="pselect('b','${goodbrands.brand_id?if_exists}')"   target="_blank">
         
         <#if goodbrands.logo?if_exists==''>
               <img width="128" height="85" src="/include/images/nopic.jpg">
         <#else>
               <img src="${goodbrands.logo?if_exists}"  title="${goodbrands.brand_name?if_exists}"   alt="${goodbrands.brand_name?if_exists}"   width="128px" height="85px" />
         </#if>
         
      
         </a>
         </li>
          </#list>
         
      </ul>
   </div>
   
   
   
   
   <div class="news">
      <h2><span class="title_main">最新公告</span><span class="more"><a href="/mallarticle.html">更多>></a></span></h2>
      <ul>
      <#assign nncount=1>
       <#list newsIndexList as newsLists>
          <#if nncount lt 10>
	          <li ><a href="/mallarticle-${newsLists.news_id?if_exists}-${newsLists_index}.html">
	          <#if newsLists.title?length lt 16>
			      ${newsLists.title?if_exists}
			    <#else>
			      ${newsLists.title[0..15]}...
			    </#if>
				</a></li>
				 <#assign nncount=nncount+1>
			</#if>
         </#list>
        
      </ul>
   </div>
</div>
<div class="clear"></div>
<!--推荐结束-->



<!--一楼结束-->
<#assign cnum=1>
 <#list categoryIndexList as categorylist>
		  <#if cnum lt 6>
					<div class="w980">
					   <div class="floor floor_${cnum}">
					      <h2>${categorylist.cat_name?if_exists}</h2>
					      <P class="f_right">
					       <#assign tcnum=1>
					      <#list twocategoryIndexList as twocategory>
						       <#if categorylist.cat_id==twocategory.up_cat_id>
								       <#if tcnum lt 11>
								         <a href="/mall-goodslist-${twocategory.en_name?if_exists}.html">${twocategory.cat_name?if_exists}</a>
								       </#if>
								       <#assign tcnum=tcnum+1>
								</#if>					       
					      </#list>
	
					      </P>
					      <div class="clear"></div>
					   </div>
					    <ul class="goods">
					     <#assign gcount=1 >
					    <#list goodsIndexList as glist>
						      <#if glist.cat_attr?if_exists?contains(categorylist.cat_id)==true>
								          <#if gcount lt 9>
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
											      <#assign gcount=gcount+1 >
									      </#if>
								      
						      </#if>
					   </#list>
					   
					   </ul>
					   <br class="clear"/>
					</div>
		</#if>
	<#assign cnum=cnum+1>
</#list>
<!--二楼结束-->
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.cycle.js"></script>
<script src="/include/js/JSelectCategory.js" type="text/javascript"></script>
<script type="text/javascript"> 
	$(".allsort").hoverForIE6({current:"allsorthover",delay:200});
	$(".allsort .item").hoverForIE6({delay:150});
</script>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
