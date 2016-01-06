<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/supply_lis.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/product!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   		  <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
	     <div class="industry_main">
		     <div class="industry_main_search">
			     <table width="100%">
				     <tr>
				          <td><@s.select name="client_status" list="clientList" listKey='para_key'  listValue='para_key'  headerKey=''  id="id_client_status"  headerValue="所有经营模式" /></td>
					     <td><@s.select name="sortby" list=r"#{'1':'更新时间降序','2':'更新时间升序','3':'信用指数降序','4':'信用指数升序'}"   id="id_sortby"    headerKey='' headerValue="默认排序" /></td>
				         <td><@s.select name="level_code" list="levelList" listKey='level_id'  listValue='level_name'  id="id_level_code"    headerKey='' headerValue="所有会员" /></td>
				         <td><input name="" type="checkbox" <#if '1'== search_images?if_exists  > checked=true </#if>    onclick="setHiddenValbyCheck('search_images',this)"/>图片</td>
				         <td width="251"></td>
				     </tr>
			     </table>
		     </div> 
		  </div>  	
	<#include "/WEB-INF/template/portal/default/attrList.ftl" />    
      <div class="flll_main">
      <#if productList?if_exists && (productList?size > 0)>
        <div class="clear"></div>
        <#list productList as product>     
        <#assign rbttime='${(product.in_date)?if_exists}'/> 
        <div class="flll_main_mid" >
            <table width="100%" height="100%" style="height:130px;">
               <tr>
					<td rowspan="4" >		
	                </td>
	                <td rowspan="4" class="mod_td2">
		               <div class="divMiddle" style="width:118px;height:112px;line-height:112px;">
				           <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('product','${product.product_id?if_exists}','${rbttime?if_exists}')")}"
				            target="_blank"  class="AMiddle"  style="width:118px;">
			               <#if (product.img_path)?if_exists != ''>
			                 <img  src="${product.img_path?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${product.img_path?if_exists}','108','102')")} class="ImgMiddle"/>
			               <#else>
			                 <img  src="${(cfg_nopic)?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','108','102')")} class="ImgMiddle"/>
			               </#if>
			               </a>
			           </div>
	                 </td>
	                 <td  class="mod_td3" colspan="2">
	                     <div style="float:left;">
							 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('product','${product.product_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
							    <h2 class="flll_title1"> 
			                    <#if product.title?length lt 21>
							      ${product.title?if_exists}
							    <#else>
							      ${product.title[0..20]}...
							    </#if></h2>
							  </a>
						  </div>
						  <#if product.c_num?if_exists!=''>
							 <span class="td_span">
							  
								  <span class="td_span_1">信用指数:</span>
		                          <span class="td_span_2">${product.c_num?if_exists}</span>
		                     </span>
		                  </#if>	                                               
	                 </td>	                
	                 <td rowspan="4" class="mod_td5">
			              <ul>
				               <li>
				                  <#if product.area_name!=''>
				                     [${product.area_name?if_exists}]
				                   </#if>
				               </li>
				          </ul>     
	                 </td>
               </tr>
               <tr>
	               <td class="mod_td4" colspan="2">
	                    <#if product.status_name?if_exists!=''>	
	               		   经营模式:${(product.status_name)?if_exists}<br/>
	               		</#if> 
		                  <#if product.content?if_exists !=''>
			                  <#if product.content?length lt 53>
							      ${product.content?if_exists}
							  <#else>
							      ${product.content[0..52]}...    
							  </#if>   
						  </#if>    
	               </td>
               </tr>
               <tr>
                  <td>
                  <span class="area_name">  
                      <font color="#333333">${product.in_date[0..10]?if_exists}</font>
                  </span>
                 </td>
               </tr>
               <tr>
               <td >
                  <span >	                                          
	                    <span class="cust_name_id">
	                   		 <a href="/showroom/${product.user_name?if_exists}" target="_blank">${product.cust_name?if_exists}</a>
	                    </span>
	                    <#if product.level_code!='4'>
	                    	<#if (product.level_name)?if_exists>
                       			 <span class="flll_main_mid_text" >[${(product.level_name)?if_exists}]</span>
                       		 </#if>
	                    <#else>
	                    	<span style="color:red; ">[${(product.level_name)?if_exists}]</span>
	                    </#if>
	              </span>  
	              <span style="float:right;">	           
	              	  <#if product.contact_qq?if_exists!="">
                          <a target="_blank" href=" http://wpa.qq.com/msgrd?v=3&uin=${product.contact_qq?if_exists}&site=qq&menu=yes">
                          <img border="0" src="http://wpa.qq.com/pa?p=1:${product.contact_qq?if_exists}:16"></a>
             		  </#if>             		  
             	  </span>    
               </td>         
               </tr>
            </table>
        </div>  
        <div class="clear"></div>        
       </#list>        
        <div class="page_main">
         <div style="text-align:center;">
            ${pageBar?if_exists}<br/>     
        </div>
      </div>
      <div class="right_bottom">
         <p class="f_right"><a href="#"><img src="/templets/${templateStyle?if_exists}/images/xtb_007.gif" />返回顶部</a></p>
      </div>
            <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
      <div class="clear"></div>
      </div>
   </div>
   <div class="right_main f_right">
       
      <div class="area_title"><h2 class="title_text">产品排行</h2></div>
      <ul class="ph_main">
        <#list TopList as top>
         <#assign rbttime='${(top.in_date)?if_exists}'/>  
         <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('product','${top.product_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
         <#if top.title?length lt 16>${top.title?if_exists}
		<#else>${top.title[0..15]}... </#if></a></a></li>
        </#list>
   </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=55"></script>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=56"></script>
   </div>
   </div>
</div>
<script type="text/javascript">
	function setHiddenVal(para_name,para_value){
            document.getElementById(para_name).value = para_value;
	       	document.searchForm.submit();
	}
	function setHiddenValbyCheck(para_name,obj_id){
        if($(obj_id).attr("checked")==true){
          	   document.getElementById(para_name).value = "1";
		       document.searchForm.submit();
        }else{
               document.getElementById(para_name).value = " ";
		       document.searchForm.submit();
        }
	}
$(document).ready(function(){
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });
       //select改变表单提交事件
	$("#id_client_status").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });    
    $("#id_sortby").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
    $("#id_level_code").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });    
});
</script>
<div class="clear"></div>
	<@s.hidden name="cat_id" id="cat_id_para"/>
	<@s.hidden name="area_id" id="area_value_para"/>
	<@s.hidden name="search_images" id="search_images" />
	<@s.hidden name="searchText"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
</body>
</html>
