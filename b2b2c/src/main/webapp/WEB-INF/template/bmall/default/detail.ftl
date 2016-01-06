<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${goods.goods_name?if_exists}-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/detail.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/scroll.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/jquery.jqzoom.css" rel="stylesheet" type="text/css" />
<link href="/include/css/page.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" href="/include/components/goodstree/dtree.css" type="text/css" />
<script type="text/javascript" src="/include/components/goodstree/dtree.js"></script>
<link href="/templets/shop/css/detailmess.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>
<body>

<#include "/WEB-INF/template/bmall/default/top.ftl">
<@s.form action="/mall/goods!detail.action?gid=${(goods.goods_id)?if_exists}">
<div class="position"><p>您当前的位置：${postName?if_exists}  ${goods.goods_name?if_exists}</p></div>
<div class="clear"></div>
<!--top产品详细结束-->
<div class="w980">

	<div class="bar">	
	<#if web_openmall?if_exists=='0'>
	<!--是否开启商城区域-->
     <div class="barD">
        <h2>商家信息</h2>
        <ul class="shopPpromise">
           <li><a href="#">正品保障</a></li>
           <li><a href="#">提供发票</a></li>
           <li><a href="#">七天退换</a></li>
        </ul>
        <div class="shopIntro">
            <div class="shopRate">
               <h4>店铺动态评分<span class="compare">与同行业相比</span></h4>
               <ul>
                  <#list scoreList?first as score>
	                 <li>描述相符：<a href="#" target="_blank" ><em class="count">
		                 <#if score.desc_score?length lt 4>
		                 	${score.desc_score?if_exists?string.number}
		                 <#else>
		                 	${score.desc_score[0..3]}
		                 </#if>
	                 </em><span class="rateinfo"><b>高于</b><em>3.57%</em></span></a></li>
	                 <li>服务态度：<a href="#" target="_blank" ><em class="count">
		                 <#if score.service_score?length lt 4>
		                 	${score.service_score?if_exists?string.number}
		                 <#else>
		                 	${score.service_score[0..3]}
		                 </#if>	                 
	                 </em><span class="rateinfo"><b>高于</b><em>3.57%</em></span></a></li>
	                 <li>发货速度：<a href="#" target="_blank" ><em class="count">
		                 <#if score.delivery_score?length lt 4>
		                 	${score.delivery_score?if_exists?string.number}
		                 <#else>
		                 	${score.delivery_score[0..3]}
		                 </#if>
	                 </em><span class="lower"><b>低于</b><em>3.57%</em></span></a></li>
	              </#list>
               </ul> 
            </div>
            <div class="extend">
                <ul>
                   <li><label>公 司 名：</label>${member.cust_name?if_exists}</li>
                   <div class="clear"></div>
                   <li><label>所 在 地：</label>${member.area_attr?if_exists}</li>
                   <div class="clear"></div>
                   <li><label>商&nbsp;&nbsp;&nbsp;&nbsp;家：</label><a href="#">${memberuser.user_name?if_exists}</a></li>
                   <div class="clear"></div>
                </ul> 
            </div>  
             <div class="other" style="text-align:center">
                 <a class="enter" target="_blank" title="进入掌柜的店铺" href="/shop/${memberuser.user_name?if_exists}.html" ></a>
                 <a href="javascript:void(1);" onclick="insertColl('1');">收藏本店铺</a>&nbsp;
			    	<input type="hidden" id="title" name="collect.title" id="title" value="${goods.goods_name?if_exists}" />	
			    	<input type="hidden" id="link_url" name="collect.link_url"  />
             </div> 
           
             <div class="search_cont">
			    <p class="search_cont_P">关键字：<input type="text" style="width:118px" id="bselValue" /></p>
				<p class="search_cont_p1" style="padding:10px 0 0 25px;*padding:10px 0 0 20px">价格：<input type="text" id="downprice" value="${downpri?if_exists}" name="" style="width:47px;*width:43px"/> 到 <input type="text" id="upprice" value="${uppri?if_exists}" name="" style="width:47px;*width:43px"/></p>
			    <input type="button"  onclick="return priceselect();" class="search_b"/ > 
			 </div> 
			
        </div>
     </div>
     <!--是否开启商城区域结束-->
    </#if>
    
    <!--是否开启商城分类开始-->
	<#if web_openmall?if_exists=='0' && ( custCatList.size() > 0 )>
	    <div class="Sidebar barD">
	        <h2>店铺商品分类</h2>
	          <#if cattreeList?exists && ( cattreeList.size() > 0 ) > 
		         <ul class="sideFl">
				     <#list custCatList as custcat>
						<li class="zk"><a href="#">
						 <#if custcat.cat_name?length lt 20>
						  		<a href="/shop/${(memberuser.user_name)?if_exists}/goodslist/cat_${custcat.cat_id}.html">${custcat.cat_name?if_exists}</a>
						   <#else>
						   		<a href="/shop/${(memberuser.user_name)?if_exists}/goodslist/cat_${custcat.cat_id}.html">${custcat.cat_name[0..18]}...</a>
							</#if>	
						</a></li>
					 </#list>
				 </ul>
	         </#if>
	    </div>
    <#else>
       <#if (cattreeList.size() > 0)>
	     <div class="Sidebar barD">
	        <h2>商品分类</h2>
	          <#if cattreeList?exists && ( cattreeList.size() > 0 ) > 
		           <ul class="sideFl">
				      <script type="text/javascript">
						  a = new dTree('a');
						  a.add(${topCat?if_exists},-1,'');
						  <#list cattreeList as cattree>
							a.add(${cattree.cat_id?if_exists},${cattree.up_cat_id?if_exists},'<a href="/mall-goodslist-${cattree.en_name?if_exists}.html">${cattree.cat_name?if_exists}</a>&nbsp;','#');
						  </#list>
						  document.write(a);
					  </script>
			      </ul>
	         </#if>
	    </div>
	    </#if>
    </#if>
   
   <!--是否开启商城分类结束--> 
   
   <#if goods.img_path!=''>
		<#if ((goods.img_path)?index_of(",") > (-1))>      			
			<#assign startpos = "${goods.img_path?if_exists}"?index_of(',')>
                 <#if ((startpos-1)>-1)>
                       <#assign img =(goods.img_path)[(0)..(startpos-1)]> 
                 </#if>
        </#if>
   <#else>
   			 <#assign img =cfg_nopic> 
   </#if>             
   <#if (saleList.size()>0)>
   <div class="barD">
         <h2>热销排行</h2>
 		 <ul class="hot_cont">
         	<#list saleList as sale>
	            <li><a href="/mall-goodsdetail-${sale.goods_id}.html">
	                 <#if sale.img_path!=''>
		      			<#if ((sale.img_path)?index_of(",") > (-1))>      			
		      				    <#assign startpos = "${sale.img_path?if_exists}"?index_of(',')>
	                     		<#if ((startpos-1)>-1)>
	                          		 <#assign img =(sale.img_path)[(0)..(startpos-1)]>	            
	            					 <img src="${img?if_exists}" class="hot_cont_l"/>
	            			    </#if>
	        			 <#else>
	        					 <img src="${sale.img_path}" class="hot_cont_l"/>
	            		</#if>
		            <#else>
		            		<img src="${cfg_nopic?if_exists}" class="hot_cont_l"/>
		            </#if>
	            </a>
	            <p class="hot_cont_r"><a href="/mall-goodsdetail-${sale.goods_id}.html">
		             <#if sale.goods_name?length lt 11>
		                ${sale.goods_name?if_exists}
		             <#else>
		             	${sale.goods_name?if_exists[0..10]}
		             </#if>	            
	            </a><br /><span class="price">￥${sale.sale_price?if_exists}元</span></p></li>
	            <div class="clear"></div>
	        </#list>
         </ul>
       </div>
      </#if> 
  <!--加载最近浏览商品开始-->
  <script type="text/javascript">
  	    var seeNum=0;
  	    if($.cookie("existid"+${goods.goods_id?if_exists})==${goods.goods_id?if_exists}){
			  var coknum=$.cookie("existidpos"+${goods.goods_id?if_exists});
			  $.cookie("goodName" + coknum, "");
			  $.cookie("goodImg" + coknum, "");
			  $.cookie("goodPrice" + coknum, "");
			  $.cookie("goodId" + coknum, "");
  	    }
  	    if($.cookie("cookieNum") != null){
		  seeNum = parseInt($.cookie("cookieNum"));		
	 	}
	 	seeNum+=1;
	 	$.cookie("goodName" + seeNum, "${goods.goods_name?if_exists}", { expires: 7 , path: '/'});
		$.cookie("goodImg" + seeNum," ${singleImg?if_exists}", { expires: 7 , path: '/'}); 
		$.cookie("goodPrice" + seeNum, "${goods.sale_price?if_exists}", { expires: 7 , path: '/'}); 
		$.cookie("goodId" + seeNum, "${goods.goods_id?if_exists}", { expires: 7, path: '/' }); 
		$.cookie("existid" + ${goods.goods_id?if_exists}, "${goods.goods_id?if_exists}", { expires: 7, path: '/' }); 
		$.cookie("existidpos" + ${goods.goods_id?if_exists}, seeNum, { expires: 7, path: '/' }); 
	 	$.cookie("cookieNum", seeNum, { expires: 7, path: '/' });  	    
  </script>
  <!--加载最近浏览商品结束-->
     <div class="barD">
         <h2>您最近浏览的商品</h2>
         <ul class="hot_cont">
            <script type="text/javascript">
            	 var shownum=0;
			     if($.cookie("cookieNum") != null){
					  var cookieNum = parseInt($.cookie("cookieNum"),{path: '/'});
					  for(var i = cookieNum; i>0; i--)
					  {
						  var name=$.cookie("goodName" + i);
						  if(name!=null&&name.length>16){
						     name=name.substring(0,15);
						  }
						  var price=$.cookie("goodPrice" + i);
						  var goodsimg=$.cookie("goodImg" + i);
						  var goodsid=$.cookie("goodId"+i);						  
						  if(goodsid!=''){	
						      if(shownum<7){
						      	  document.write("<li><a href='/mall-goodsdetail-"+goodsid+".html'><img src='"+goodsimg+"' class='hot_cont_l' width='64' height='64'/></a>"+
					              "<p class='hot_cont_r'><a href='/mall-goodsdetail-"+goodsid+".html'>"+name+"</a><br /><span class='price'>￥"+price+"元</span></p>"+
					              "</li><div class='clear'></div>");
					              shownum++;
						      }					  		
						  }
					  }				  
				  }    
			 </script>              
         </ul>
      </div>
	</div>
 <!--商品基本信息开始-->
   <div class="contM" id="detail">       
     <div class="gm">
        <p class="cont_t_r_name" >${goods.goods_name?if_exists}</p>
     <div class="pic">
    	    <div  class="clearfix" style="height:290px;border:1px solid #e1e2e3;width:320px;" >
		        <#if goods.img_path!=''>
		  			<#if ((goods.img_path)?index_of(",") > (-1))>      			
		  				<#assign startpos = "${goods.img_path?if_exists}"?index_of(',')>
		                 <#if ((startpos-1)>-1)>
		                       <#assign img =(goods.img_path)[(0)..(startpos-1)]>
		                       <#assign pos = "${img?if_exists}"?index_of('.')>
		          				  <#if ((pos-1)>-1)>		          				  	
		          				 		<#assign b_img =(img)[(0)..(pos-1)]>
		          				  		<#assign type =(img)[(pos)..(img?length-1)]>
		          				  </#if>		          				  
							      <a  href="${b_img?if_exists}_big${type?if_exists}" class="jqzoom" rel='gal1'>
			             		 	<img src="${b_img?if_exists}${type?if_exists}" >	
			             		 </a>
		                 </#if>
		             <#else>
            			     <#assign pos = "${goods.img_path?if_exists}"?index_of('.')>
          				     <#if ((pos-1)>-1)>
          				 		<#assign b_img =(goods.img_path)[(0)..(pos-1)]>
          				  		<#assign type =(goods.img_path)[(pos)..(goods.img_path?length-1)]>
          				     </#if>
		             		 <a  href="${b_img?if_exists}_big${type?if_exists}" class="jqzoom" rel='gal1'>
		             		 	<img src="${b_img?if_exists}${type?if_exists}" /> 	
		             		 </a>
					</#if>	
				<#else>
						<img src="${cfg_nopic?if_exists}" width='320px' height="290"/> 	
		        </#if>
     </div> 
     <div class="clear"></div> 
      <!--相册图片开始--> 
       <#if goods.img_path!=''>
		   <div class="listImg">	
		      <div class="wrapper clearfix">
			        <ul  class="clearfix" >	    
			          			<#list goods.img_path?split(",") as s>
			          				  <#assign pos = "${s?if_exists}"?index_of('.')>
			          				  <#if ((pos-1)>-1)>
			          				 		<#assign b_img =(s)[(0)..(pos-1)]>			          				 		
			          				  		<#assign type =(s)[(pos)..(s?length-1)]>
			          				  </#if>
								      <li>
								      	 <a  href='###;' rel="{gallery: 'gal1', smallimage: '${b_img?if_exists}${type?if_exists}',largeimage: '${b_img?if_exists}_big${type?if_exists}'}">
								      	 		<img src="${b_img?if_exists}_small${type?if_exists}">
								      	 </a>
								      </li>						      
								</#list>			        
				      	<div class="clear"></div>          
			        </ul>  
		      </div>
		    </div>  
	    </#if>  
	    <div class='ljshare' style="padding-top:8px;">
      			<!--分享代码开始-->
				   <div >
					<!-- JiaThis Button BEGIN -->
							<div id="jiathis_style_32x32">
							<a class="jiathis_button_qzone"></a>
							<a class="jiathis_button_tsina"></a>
							<a class="jiathis_button_tqq"></a>
							<a class="jiathis_button_renren"></a>
							<a class="jiathis_button_kaixin001"></a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
							<a class="jiathis_counter_style"></a>
							</div>
							<script type="text/javascript" >
							var jiathis_config={
								summary:"",
								hideMore:true
							}
							</script>
							<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
						<!-- JiaThis Button END -->
				  </div>
			    <!--分享代码结束--> 
      		</div>    
      </div>

     <!--相册图片结束--> 
     

    <div class="cont_t_r" >
             <div class="cont_t_r_brand_c">
		       <P><span class="cont_t_r_b_sp">品   牌：</span>${(goodsbrand.brand_name)?if_exists}&nbsp;<a href="###;" onclick="insertColl('0');"><font color="red">收藏此商品</font></a></P>
			   <P class="cont_t_r_b_P numbe"><span class="cont_t_r_b_sp">商品款号：</span><span class="cont_t_r_b_sp1">${goods.goods_no?if_exists}</span>		
			   <#if evalCount=='0'>
			   		还未有人评价
			   <#else>
			  	   (已有<font class="evalcount"></font>人评价)
			   </#if></P>
			   <p class="cont_t_r_b_P"><span class="cont_t_r_b_sp">价  格：</span>
			   <span class="cont_t_r_b_sp2">￥${goods.sale_price?if_exists}</span>元</p>
			   <p class="numbe">(市场同类商品价格：￥${goods.market_price?if_exists}元)</p>
			   <#if goods.mem_price!=''>
				   <p class="cont_t_r_b_P">
				      <span class="cont_t_r_b_sp">会员优惠价格：</span><span class="cont_t_r_b_sp1">
				      详细<a class='adetail' onclick="showmemprice(this);"><img src="/templets/bmall/images/pulsdetail.png" style="vertical-align:middle;"/></a>
				      <div id="memdiv" class="memdiv">
				      		<#list goods.mem_price?split(",") as s>			      		     
			      		         <#assign len = "${s?if_exists}"?length>
		                     	 <#assign startpos = "${s?if_exists}"?index_of('|')>
		                     	 <#if (startpos > -1) >
		                     	     <#assign name = s[0..(startpos-1)]>
		                     	     <span>${name?if_exists}</span>:
		                             <#assign price = s[(startpos+1)..(len-1)]>
		                     	     <font color='#C30008'>${price?if_exists}</font>元
		                     	 </#if>
		                     	 <br/>
				      		</#list>
				      </div>
				      </span>
				      <br class='clear'/>
				   </p>
			   </#if>			   

			   <p class="cont_t_r_b_P">
			   <div class='clear'></div>
			    <span class="cont_t_r_b_sp">配  送：</span>
			    <span>
			     <!-- <span class="sendarea">至 ${userAreaName?if_exists}</span>-->
			     <span class="sendarea" onclick="shiparea();"  style="cursor:pointer;">
			     	<span>&nbsp;至&nbsp;</span><span id="areabotton">${areaipName?if_exists}</span>
			     </span>
			     <span class="sendprice">
			      <#if goods.is_ship=='0'>
			   		 <span style="margin-left:5px;">卖家承担运费</span>
			   	  <#elseif goods.is_ship=='1'>
                     <span>
				    	<#list sendmodeList as send>
				    		<#if goods.ship_price?if_exists!="">
					            <#list (goods.ship_price)?split(",") as ss>				            	
					          		 <#if ((ss)?index_of("|") > (-1))>    		          		   			
						  				<#assign startpos = "${ss?if_exists}"?index_of('|')>
						  				<#assign endpos = "${ss?if_exists}"?length>
						                	<#if ((startpos-1)>-1)>
						                       	<#assign m_mode =(ss)[(0)..(startpos-1)]>
						                       	<#assign m_price =(ss)[(startpos+1)..(endpos-1)]>					                        
						                        <#if send.smode_id==m_mode?if_exists>
						                        	<span class="smodeprice">${send.smode_name}:${m_price?if_exists}&nbsp;</span>					                        	
						                        </#if>	
						                    </#if>
						               </#if> 
					          	 </#list>
					          </#if>
				          </#list>
			         </span><br class="clear"/>
				   <#elseif goods.is_ship=='2'>
				   		<span id="autoship">
				   		
						</span><br class="clear"/>
				   </#if>	
			   </span>	   
			   
			  </span>
			 </p> 
			   
			   
			   
			   
			   
		</div>
		 <br class="clear"/>
        <div class="cont_t_r_size_c" id="yesbuyid">
        			<!-- 绑定商品属性开始-->
				   <#list attrList  as attr>
							       <P>
				                	   <span class="cont_t_r_b_sp_3" id="size_name_${attr.attr_id?if_exists}">${attr.attr_name?if_exists}</span>
				                	   <span class="cont_t_r_b_sp_3">：</span>
				                	   <span id="size_select_tip_${attr.attr_id?if_exists}" style="color:red;display:none;">*请选择${attr.attr_name?if_exists}</span>
				            	   </P>
				            	   
				            	   
				            	    <ul class="size" id="size_attr_${attr.attr_id?if_exists}">
				            	    
				            	    
			                     	   <#list attrvalueList as t>
			                     	   		<#if t.attr_id == attr.attr_id>
			                     	   			   <#if  "${attr.dft_value?if_exists}"?index_of("${t.trade_id?if_exists}") gt (-1) >
												    		<li>
														    		<a href="###;" onclick="checksizeattr('${attr.attr_id?if_exists}','size_attr_${attr.attr_id?if_exists}','size_name_${attr.attr_id?if_exists}','size_a_${t.trade_id?if_exists}');" 
														    		id="size_a_${t.trade_id?if_exists}">${t.attr_value_name?if_exists}</a>
												    		</li>
									    			</#if>
									    	</#if>
			                     	   </#list>
			                     	   
			                     	   
			                     	 </ul>
			                     	 
			                     	 <input type="hidden" id="size_select_value_${attr.attr_id?if_exists}" value="0" />
					                 <div class="clear"></div> 
		           </#list>
    			   <!-- 绑定商品属性结束-->
    			   
    			   
    			   
    			   
					<P class="number" style="clear:both"><span class="cont_t_r_b_sp_3">我要买：</span>
					   <input type="hidden" id="hidden_goods_now_stock" value="${goods.now_stock?if_exists}" />
					<input type="text" style="width:50px;" onfocus="inputGoodsnum();"  maxlength="10" onblur="buyChangeGoodsNum(this);" id="buynumid" value="1" /> 件  (库存   <font style="color:red;">${goods.now_stock?if_exists}</font>   件)
					<span  style="color:red;" id="buycountmax"></span>
					</P> 
					<#if anum!=1>
					<P class="number" style="clear:both"><span class="cont_t_r_b_sp_3">已选择：</span>
					 <span class="cont_t_r_b_sp_3" style="font-weight: bold;" id="size_select_value"></span>
					 <input type="hidden" id="cart_goods_attr_name" />
					 <input id="type_size_count" value="${anum}" type="hidden"/>
					</P> 
					</#if>
                    <div class="gw_div">
                       <ul class="gw">
                          <li><a href="###;" onclick="buynow(this);" ><img src="/templets/bmall/images/gm.gif" /></a></li>
                          <li ><a href="###;" onclick="AddMallCart(this,'1');"><img src="/templets/bmall/images/jrgw.gif" /></a></li>
                       </ul>
                       <div class="clear"></div>
                   </div>
             </div>
              <div class="cont_t_r_size_c" style="color:#B94708;display:none;"  id="nobuyid" >
              该商品已售完，不能购买，您可以看看其它商品！
              </div>
   </div>
   
   <!--商品基本信息结束-->
   <div class="clear"></div>     
 
<!--详情开始-->   
 <div id="detailContent"> 
      <ul class="tabbar">
         <li class="selected"><a>商品详情</a></li>
         <li><a>评价详情(<b class='evalcount'></b>)</a></li>
         <li><a>成交记录(<b class='tradeCount'></b>)</a></li>
         <li><a>服务质量</a></li>
         <li><a>给我推荐</a></li>
         <li><a>商品咨询</a></li>
      </ul>
      
      <!--商品描述DIV开始-->
      <div class="description tabdiv">
      		  <!--商品描述属性开始-->
			      <div>
			      	 <#if goods.size_attr!=''>
					      <ul class="promo">
						      <#list goods.size_attr?split(",") as s>
						      		<#assign len = "${s?if_exists}"?length>	
						             <!-- 得到属性ID的位置 -->
				                     <#assign idpos = "${s?if_exists}"?index_of('|')>
				                     <#if (idpos>-1)>
				                         <#assign getRightStr = s[(idpos+1)..(len-1)]>
				                     </#if>
				                     <#assign valpos = "${getRightStr?if_exists}"?index_of('|')>
				                     <#assign rightlen = "${getRightStr?if_exists}"?length>	
				                     <#if ((valpos-1)>-1)>
				                         <#assign thisval = getRightStr[0..(valpos-1)]><!--获取值-->
				                         <#assign thisName = getRightStr[(valpos+1)..(rightlen-1)]><!--获取名称-->
				                     </#if>
				                     <li>
				                     <#if thisName!=''>
				                     	${thisName?if_exists}:
				                     </#if>			                     
				                     <!--判断是否存在多个值-->
				                     <#assign ismorevalLen = "${thisval?if_exists}"?index_of(':')>	
				                     <#if (ismorevalLen>-1)>
				                     	<#list thisval?split(":") as t>
				                     	  ${t}&nbsp;
				                     	</#list>
				                     <#else>
				                     	${thisval?if_exists}
				                     </#if>
				                     
				                     </li>	                     
						      </#list>
					      </ul>
					      <div class='clear'></div>
					   </#if>
				  </div>
			   <!--商品描述属性结束-->
			   <br/>
			  <!--商品描述开始-->
			  <div class="cont_pic">
			  	${goods.goods_detail?if_exists}		
			  	<#if goods.goods_detail?if_exists==''>			  		 
			  		 <div class="nulldata">无商品详细描述</div>
			  	</#if>	  
			  </div>			  
			  <!--商品描述结束-->
			  
       </div>
       <!--商品描述DIV结束-->
       
      <!--商品评价DIV开始-->
      <div class="tabdiv">
	       <p class="cont_right_nav">评价详情(<font class='evalcount'></font>)</p>
	       <div class="com_desc" style='display:none;'>
			      <div class="com_desc_l" >
				      <P class="com_p">商品与描述相符</P>
					  <P class="com_p1"><span  class="com_sp">4.5</span>分 <img src="/templets/bmall/images/stars.jpg" /></P>
					  <P class="com_p1">(共打分<span style="color:#FF6600">1</span>次)</P>
				  </div>
				  <div class="com_desc_r">
				      <div class="com_desc_r_t" style="margin-left:215px">3.0</div>				 
					  <div class="com_desc_r_c"><img src="/templets/bmall/images/points_2.jpg" /></div>
					  <div class="com_desc_r_d">
					      <ul>
						      <li><p>1分</p><p>非常不满</p></li>
							  <li><p>2分</p><p>不满意</p></li>
							  <li><p>3分</p><p>一般</p></li>
							  <li><p>4分</p><p>满意</p></li>
							  <li><p>5分</p><p>非常满意</p></li>
						  </ul>
					   </div>
				  </div>
		   </div>
           
           
           <script type="text/javascript">
           		function showEvalContent(sval){
           			$('#evalList').mall({
					    actionName:'/mall/goods!evalComment.action?gid=${gid?if_exists}&en='+sval,
						columnModel:ColumnEvalTitle,
						pageSize:2,
						otherMethod:"otherEval"
					});
           		}	
           </script>	       
	       
	       <div class="rate">
	            <select class="act-changetype"  onchange="showEvalContent(this.value);" >
	             	<option selected="selected" value="0">有评论内容</option>
		            <option value="">所有评论</option>		           
	            </select>
	        </div>
	        <!--AJAX加载评论内容框-->
	        <div id="evalList" >        		
	        
	        </div>       
      </div>
      <!--商品评价DIV结束-->
      <div class="clear"></div>
      
      <!--商品成功交易开始-->
      <div class="tabdiv">
	     	 <p class="cont_right_nav">成功交易(<font class='tradeCount'></font>)</p>
	         <div>  
	         	 <!--AJAX加载评论内容框-->    
	         	 <div id='success' class="mj" > 
	         	 
	         	 </div>
	         	<div class="clear"></div>
	        </div>  
      </div>     
      <!--商品成功交易结束-->
      
       <!--商品服务质量开始-->
      <div class="tabdiv">
	      <p class="cont_right_nav">服务质量</p>       
         <div id="J_sellerinfo" class="tb-sellerinfo entry-content J_TAjaxContainer" style="display:none;">
		        <div class="serviceQuality">
		          	<h2>服务质量记录</h2>
			          <ul class="clearfix">
			            <li><a>退款速度：</a><span class="desc"><span class="orange"> 3.1天 </span>（比行业平均水平<span class="orange">慢0.6天</span>）</span></li>
			            <li><a>纠纷退款：</a><span class="desc"><span class="green">无 </span>超过<em>30天</em>无纠纷退款</span></li>
			            <li><a>投诉纠纷：</a><span class="desc"><span class="green">无 </span>超过<em>360天</em>无投诉</span></li>
			            <li><a>处罚情况：</a><span class="desc"> 该会员遵守淘宝用户行为管理规则良</span></li>
			            <li><a>违规行为：</a><span class="desc"><span class="orange">有 </span><em>30天</em>内有<em>1笔</em>违规行为</span></li>
			          </ul> 
			          <div class="clear"></div>
		       </div> 
	     </div>    

	     <div class="dynamicRate">
	             <ul class="seller f_left">
		              <li class="RateInfo">
		                <div class="item"><span class="title">宝贝与描述相符：</span><em class="count" >            
		                	<#if (scoreList?size > 0)>
				                <#list scoreList?first as score>
										
							                 <#if score.desc_score?length lt 4>
							                 	${score.desc_score?if_exists?string.number}
							                 <#else>
							                 	${score.desc_score[0..3]}
							                 </#if>
							                 <em>分</em>					             
				                  </#list>	
		                	<#else>										
									<font class='noscore'>暂无评分</font>
							</#if>
										                  
								         
		                 	        	
		                    <b class="percent over">4.93%</b></em></div>
		              </li> 
		              <li class="RateInfo">
		                <div class="item"><span class="title">卖家服务态度：</span><em class="count" >
			                 <#if (scoreList?size > 0)>
				                <#list scoreList?first as score>
					                 <#if score.service_score?length lt 4>
					                 	${score.service_score?if_exists?string.number}
					                 <#else>
					                 	${score.service_score[0..3]}
					                 </#if>	  
							         <em>分</em>					             
				                  </#list>	
		                	<#else>										
									<font class='noscore'>暂无评分</font>
							</#if>										 
		                <b class="percent di">4.93%</b></em></div>
		              </li>   
		              <li class="RateInfo">
		                <div class="item"><span class="title">卖家发货速度：</span><em class="count" >
			                 <#if (scoreList?size > 0)>
				                <#list scoreList?first as score>
						                	 <#if score.delivery_score?length lt 4>
							                 	${score.delivery_score?if_exists?string.number}
							                 <#else>
							                 	${score.delivery_score[0..3]}
							                 </#if>	
							                 <em>分</em>					             
				                  </#list>	
		                	<#else>										
									<font class='noscore'>暂无评分</font>
							</#if>
										 
		               		 <b class="percent pi"></b></em></div>
		              </li>     
		            </ul>
		           
	            <div class="clear"></div>
	            <!--店铺动态评分 -->
	      </div>  
	  </div>
  	 <!--商品服务质量结束-->
  	 
  	 
  	 
  	 
  	 <!--商品给我推荐开始-->
  	 <div class="tabdiv">
	           <p class="cont_right_nav">给我推荐</p> 
	           <#if recomGoodsList==null||recomGoodsList?size==0>
	           <div class="nulldata">无推荐商品</div>	           
	           </#if>
	           <ul class="goods">
	           
	            <#list recomGoodsList as recom>
	              <li >
	              	    <div style="width:220px;height:265px;" >
		              	    <div class="divMiddle"  style="width:210px;height:260px;" >
			              	     <#if (recom.img_path?index_of(',') > 0)>
			              	     	 <#assign startpos = "${recom.img_path?if_exists}"?index_of(',')>
			                     		<#if ((startpos-1)>-1)>
			                     			<#assign img =(recom.img_path)[(0)..(startpos-1)]>
			                     			<a href="/mall-goodsdetail-${recom.goods_id}.html" >
			              	      			<img src="${img?if_exists}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(img)?if_exists}','200','250')")} class="ImgMiddle2"/>
			              	      			</a>
			              	      	   </#if>
			              	      	   
			              	     <#else>
			              	     <a href="/mall-goodsdetail-${recom.goods_id}.html" >
			              	     		<img src="${cfg_nopic?if_exists}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','200','250')")} class="ImgMiddle2"/>
			              	     		</a>
		              	      	</#if>
	              	        </div>
	              	    </div>
	              	    <br/>
		               		<a href="/mall-goodsdetail-${recom.goods_id}.html">${recom.goods_name?if_exists}</a>               
		               <br />
		               <p class="price"><span class="market">
		               	市场价： <span>￥${recom.market_price?if_exists}元</span></span>
		              <span class="sold">售价：￥${recom.sale_price?if_exists}元</span>
		              </p>
	              </li>
	            </#list>
	          </ul>     		
  	 </div>
  	<!--商品给我推荐结束-->
  	
  	
    <!--商品咨询开始-->
    <p class="cont_right_nav">商品咨询</p> 
     <#if goodsaskList?exists && ( goodsaskList.size() > 0 ) > 
		    <#list goodsaskList as goodsask>
		     <div class="reply">
		      <p class="f_right">网友：${(goodsask.user_name)?if_exists}<span>${(goodsask.c_date)?if_exists}</span></p>
		      <div class="clear"></div>
		      <p class="qtion">咨询内容：${(goodsask.c_content)?if_exists}</p>
		      <p class="answer">咨询回复： 
		      <#if goodsask.re_content!=null && goodsask.re_content!="">
					${(goodsask.re_content)?if_exists}
			  <#else>
					暂无回复
			  </#if></p>
		     </div>
		    </#list>
		    
		    <div class="page_main">
				${pageBar?if_exists} 
			</div>
    <#else>
    <div style="text-align:center;font-size:14px;padding-top:40px;font-weight:bold;color:#666666;">暂无此商品的咨询信息</div>
    </#if>
     <div class="message">
      <h2 class="mesh2">发表咨询</h2>
      <div class="mescont">
        <p>声明：您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</p>
        <p><b>咨询类型：</b><@s.radio id="commpara" list="commparaList" name="comm_type" listValue="para_key" listKey="para_value"/></p>
        <p><b>咨询内容：</b></p>
        <p><@s.textarea id="content" name="c_content" cssStyle="width:500px;height:100px;"/></p>
        <p><a href="#" onclick="subgoodsask();"><img src="/templets/shop/images/tij.gif" ></a></p>
      </div>
     </div>
  	<!--商品给我推荐结束-->
  	
  	</div>
  </div>	
</div>
<!--加入购物车显示效果开始-->
 <div class="cart" id="searchDiv" style="display:none;">
		   <p class="f_right"><a href="###;" onclick="closeCart('searchDiv');"  class="close">关闭</a></p></p>
		   <div class="clear"></div>
		   <div class="f_left cartpic"><img src="/templets/bmall/images/xzcart_03.gif"></div>
		   <div class="f_left cartcont">
			     <p class="fpsize">该商品已经成功放入购物车</p>
			     <p>购物车共<span class="fred" id="gcountnum">0</span>商品  合计：<span class="fred" id="gcountmomey">0.00</span>元</p>
			     <p><a href="###;" onclick="closeCart('searchDiv');" > << 继续购物 </a><a href="/mall/goods!mallcart.action" class="cjs">去购物车结算</a></p>
		   </div>
		   <div class="clear"></div>
		
</div>
<!--加入购物车显示效果结束-->
<input id="cart_goods_id" type="hidden" value="${goods.goods_id?if_exists}" />
<input id="cart_goods_name" type="hidden" value="${goods.goods_name?if_exists}" />
<input id="cart_goods_give_inter" type="hidden" value="${goods.give_inter?if_exists}" />
<input id="cart_goods_sale_price" type="hidden" value="${goods.sale_price?if_exists}" />
<input id="cart_goods_img_path" type="hidden" value="${goods.img_path?if_exists}" />
<input id="cart_goods_now_stock" type="hidden" value="${goods.now_stock?if_exists}" />
<input id="cart_goods_cust_name" type="hidden" value="${member.cust_name?if_exists}" />
<input id="cart_goods_cust_id" type="hidden" value="${member.cust_id?if_exists}" />
<input id="shipareaid" type="hidden" value="${shipareaid?if_exists}" />
<input id="areaid" type="hidden" value="${areaid?if_exists}" />
<input id="sareaid" type="hidden" value="${shipareaid?if_exists}" />
<input id="careaid" type="hidden" />
<input id="c_id" type="hidden" value="${member.cust_id?if_exists}"/>
<input id="t_id" type="hidden" value="${goods.ship_price?if_exists}"/>


<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/common.js"></script>
<script type="text/javascript" src="/include/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/templets/bmall/js/scroll.js"></script>
<script type="text/javascript" src="/templets/bmall/js/jquery.jqzoom-core.js"></script>
<script type="text/javascript" src="/templets/bmall/js/goods.js"></script>
<script type="text/javascript" src="/templets/bmall/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/templets/bmall/js/cart.js"></script>
<script type="text/javascript" src="/templets/bmall/js/collect.js"></script>
<script type="text/javascript" src="/include/components/goodstree/dtree.js"></script>
<script type="text/javascript" src="/templets/shop/js/change.js"></script>

<#include "/WEB-INF/template/bmall/default/bottom.ftl">
<script type="text/javascript">
	$(document).ready(function () {
		//图片滚动
	    $('.listImg').infiniteCarousel();
	    //放大镜插件
	  	$('.jqzoom').jqzoom({
	        zoomType: 'reverse',
	        lens:true,
	        preloadImages: false,
	        alwaysOn:false
	    });
		//成功交易记录插件
		$('#success').mall({
		    actionName:'/mall/goods!dealTrade.action?gid=${gid?if_exists}',
			columnModel:ColumnTitle,
			otherMethod:"otherTrade",
			pageSize:10,
			nullData:"未有成功交易记录"
		});
		//评价记录插件
		$('#evalList').mall({
		    actionName:'/mall/goods!evalComment.action?gid=${gid?if_exists}&en=0',
			columnModel:ColumnEvalTitle,
			pageSize:10,
			otherMethod:"otherEval",
			nullData:"还未有人评论"
		});
		//tab页切换插件
		$("#detailContent").mallTab({
			displayIndex:"0"			
		});		
	    //加载购物车信息
	    showCartGoods();
	    //加载规格是否选择
		loadSelectAttr()
		//控制商品是否能购买，当商品库存数量为0的时候，不显示购买按钮
		ifBuyGoods(); 
		<#if goods.is_ship==2>
            var sp_id=$("#areaid").val();
			shipprice(sp_id);
		</#if>
	});
	
	
</script>
<div id="showship" style="display:none;">
	<span onclick="closearea();" class="shipclose">关闭</span>
 	${carea_name?if_exists}
 	<div class="clear"></div>
 	<div class="twoarea">
 	</div>
</div>
</@s.form>
<@s.form id="fgoodslist" action="/shop/${memberuser.user_name?if_exists}/goodslist.html" method="post">
             <@s.hidden id="selName" name="selName"/>
             <@s.hidden id="uppri" name="uppri"/>
             <@s.hidden id="downpri" name="downpri"/>
</@s.form>
</body>
</html>
