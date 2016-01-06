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
<@s.form  id="searchForm" action="/portal/supply!list.action" method="post" name="searchForm">
<input type="hidden" id="cust_id" name="cust_id" value="${cust_id?if_exists}"/>
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   		  <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
		  <div class="industry_main">
		     <div class="industry_main_search">
			     <table width="100%">  
				     <tr>
					     <td width="34%">关键字:<@s.textfield  name="searchText" /></td>
					     <td>最低价:<@s.textfield  name="lowwer_price" cssClass="price" onkeyup="checkNum(this)"/></td>
					     <td>最高价:<@s.textfield  name="height_price" cssClass="price" onkeyup="checkNum(this)"/></td>
					     <td>起订量:<@s.textfield  name="min_order" cssClass="price" onkeyup="checkNum(this)"/> 以下</td>
					     <td><@s.submit value="确定" /></td>
				     </tr>
			     </table>
			     <table width="100%">
				     <tr>
					     <td><@s.select name="type" list="paraList" listKey='para_value'  listValue='para_key' headerKey='' headerValue="供应类型"  id="id_type" /></td>
					     <td><@s.select name="level_code" list="levelList" listKey='level_id'  listValue='level_name' headerKey='' headerValue="所有会员"  id="id_level_code"     /></td>
					     <td><@s.select name="client_status" list="clientList" listKey='para_key'  listValue='para_key'  headerKey='' headerValue="所有经营模式"   id="id_client_status"   /></td>
					     <td><@s.select name="sortby" list=r"#{'1':'价格由高到低','2':'价格由低到高','3':'更新时间降序','4':'更新时间升序','5':'信用指数降序','6':'信用指数升序'}"  headerKey='' headerValue="默认排序"   id="id_sortby"   /></td>
					     <td><@s.select name="date_in_date" list=r"#{'-1':'1天内新消息','-3':'3天内新消息','-7':'7天内新消息','-15':'15天内新消息','-30':'30天内新消息'}"  headerKey='' headerValue="所有发布时间"   id="id_date_in_date"  /></td>
					     <td><input type="checkbox" <#if '1'== search_images?if_exists  > checked=true </#if>    onclick="setHiddenValbyCheck('search_images',this)" style="vertical-align:middle;"/>图片</td>
				     </tr>
			     </table>
		     </div> 
	  </div>  	
      <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
      <div class="flll_main" >
         <#if supplyList?if_exists && (supplyList?size > 0) || keywordAdsList?if_exists && (keywordAdsList?size > 0)>
         <div class="flll_main_top">          
            <table>
            <tr><td><input class=" flll_main_top_jg f_left" id="all_cb_box" name="" type="checkbox" value="" />
             <a class="flll_main_top_jg f_left" href="###;" onclick="toContrast();"><img src="/templets/${templateStyle?if_exists}/images/xtb_002.gif" /></a></td>
            <td><a class="flll_main_top_jg f_left" href="###;" onclick="send_askprice();"><img src="/templets/${templateStyle?if_exists}/images/xtb_003.gif" /></a></td>
            </table>
         </div>   
         <div class="wdbg">
       <#if  keywordAdsList?if_exists && (keywordAdsList?size > 0)>  
        <#list keywordAdsList as supply>
          <#assign rbttime='${(supply.in_date)}'/>  
           <div class="flll_main_mid" >
              <table width="100%">
                 <tr>
					<td rowspan="4" class="mod_td1">
					  <input class="cb_box" name="cb_box" type="checkbox" value="${supply.supply_id?if_exists}" />	
					  <input type="hidden" class="hidden_cust_name" value="${supply.cust_name?if_exists}"/>	
	                </td>
	                <td rowspan="4" class="mod_td2">
		               <div class="divMiddle" style="width:118px;height:112px;line-height:112px;">
				           <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?if_exists}','${rbttime?if_exists}')")}"
				            target="_blank"  class="AMiddle"  style="width:118px;">
			               <#if (supply.img_path)?if_exists != ''>
			                 <img  src="${supply.img_path?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${supply.img_path?if_exists}','108','102')")} class="ImgMiddle"/>
			               <#else>
			                 <img  src="${(cfg_nopic)?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','108','102')")} class="ImgMiddle"/>
			               </#if>
			               </a>
			           </div>
	                 </td>
	                 <td  class="mod_td3" colspan="2">
	                     <div style="float:left;">
							 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
							    <h2 class="flll_title1"> 
			                    <#if supply.title?length lt 21>
							      ${supply.title?if_exists}
							    <#else>
							      ${supply.title[0..20]}...
							    </#if></h2>
							  </a>
						  </div>
						  <#if supply.c_num?if_exists!=''>
							 <span class="td_span">							  
								  <span class="td_span_1">信用指数:</span>
		                          <span class="td_span_2">${supply.c_num?if_exists}</span>
		                     </span>
		                  </#if>	                                               
	                 </td>	                
	                 <td rowspan="4" class="mod_td5">
			              <ul class="td_ul">
				              <li class="flll_main_mid_right_text" >
					               <#if supply.price?if_exists==0>
					                    面议
					               <#else>
					                 ${supply.price?if_exists}元/${supply.unit?if_exists}
					               </#if>
				               </li>
				           <#if supply.onlineorder=='1'>  
				              <li>
					              <a href="###;" onclick="return checkmyself(${(supply.cust_id)?if_exists},${(supply.supply_id)?if_exists})">
					                 <img  src="/templets/${templateStyle?if_exists}/images/purchase.gif" />
					              </a>
				              </li>
				           </#if>  
				               <li>
				               	  <a href="###;" onclick="single_askprice('${supply.cust_name?if_exists}')">
					                 <img src="/templets/${templateStyle?if_exists}/images/askprice.gif" />
					              </a>
				               </li>
				          </ul>     
	                 </td>
               </tr>
               <tr>
	               <td class="mod_td4" colspan="2">	 
	               <div style="font-size:13px;margin-height:20px;margin-top:0px;">              
	                      <#if supply.status_name?if_exists!=''>	
	               		   	<div style="margin-top:2px;">经营模式:${(supply.status_name)?if_exists}</div>
	               		  </#if> 
	              		  <#if supply.min_order!=''> 
				            <div style="margin-top:2px;">   最低起批量:${supply.min_order?if_exists}${supply.unit?if_exists}起订</div>
				          </#if> 
		                   <div style="margin-top:2px;"><#if supply.content?if_exists !=''>
			                  <#if supply.content?length lt 30>
							      ${supply.content?if_exists}
							  <#else>
							      ${supply.content[0..29]}...    
							  </#if>   
						  </#if>  </div>
				  </div>  
	               </td>
               </tr>
               <tr>
                  <td>
                  <span class="area_name">
                   <#if supply.area_name!=''>
                     [${supply.area_name?if_exists}]
                   </#if>
                  </span>
                  <span style="float:right;">
                     ${supply.in_date[0..9]?if_exists}
                  <span>
                 </td>
               </tr>
               <tr>
               <td >
                  <span >	                                          
	                    <span class="cust_name_id">
	                   		 <a href="/showroom/${supply.user_name?if_exists}" target="_blank">${supply.cust_name?if_exists}</a>
	                    </span>
	                    <#if supply.level_code!='4'>
	                    	<#if (supply.level_name)?if_exists>
                       			 <span class="flll_main_mid_text" >[${(supply.level_name)?if_exists}]</span>
                       		 </#if>
	                    <#else>
	                    	<span style="color:red; ">[${(supply.level_name)?if_exists}]</span>
	                    </#if>
	              </span>  
	              <span style="float:right;">	           
	              	  <#if supply.contact_qq?if_exists!="">
                          <a target="_blank" href=" http://wpa.qq.com/msgrd?v=3&uin=${supply.contact_qq?if_exists}&site=qq&menu=yes">
                          <img border="0" src="http://wpa.qq.com/pa?p=1:${supply.contact_qq?if_exists}:16"></a>
             		  </#if>             		  
             	  </span>    
               </td>         
               </tr>
            </table>
        </div>             
        </#list>   
         </#if>   
         </div>          
        <#list supplyList as supply>
        <#assign rbttime='${(supply.in_date)}'/>  
        <div class="flll_main_mid" >
            <table style="height:120px;" width="100%">
               <tr>
					<td rowspan="4" class="mod_td1">
					  <input class="cb_box" name="cb_box" type="checkbox" value="${supply.supply_id?if_exists}" />	
					  <input type="hidden" class="hidden_cust_name" value="${supply.cust_name?if_exists}"/>	
	                </td>
	                <td rowspan="4" class="mod_td2">
		               <div class="divMiddle" style="width:118px;height:112px;line-height:112px;">
				           <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?if_exists}','${rbttime?if_exists}')")}"
				            target="_blank"  class="AMiddle"  style="width:118px;">
			               <#if (supply.img_path)?if_exists != ''>
			                 <img  src="${supply.img_path?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${supply.img_path?if_exists}','108','102')")} class="ImgMiddle"/>
			               <#else>
			                 <img  src="${(cfg_nopic)?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','108','102')")} class="ImgMiddle"/>
			               </#if>
			               </a>
			           </div>
	                 </td>
	                 <td  class="mod_td3" colspan="2">
	                     <div style="float:left;">
							 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
							    <h2 class="flll_title1"> 
			                    <#if supply.title?length lt 21>
							      ${supply.title?if_exists}
							    <#else>
							      ${supply.title[0..20]}...
							    </#if></h2>
							  </a>
						  </div>
						  <#if supply.c_num?if_exists!=''>
							 <span class="td_span">
							  
								  <span class="td_span_1">信用指数:</span>
		                          <span class="td_span_2">${supply.c_num?if_exists}</span>
		                     </span>
		                  </#if>	                                               
	                 </td>	                
	                 <td rowspan="4" class="mod_td5">
			              <ul class="td_ul">			                  
				              <li class="flll_main_mid_right_text" >
					               <#if supply.price?if_exists==0>
					                    面议
					               <#else>
					                 ${supply.price?if_exists}元/${supply.unit?if_exists}
					               </#if>
				              </li>
				           <#if supply.onlineorder=='1'>  
				              <li>
					              <a href="###;" onclick="return checkmyself(${(supply.cust_id)?if_exists},${(supply.supply_id)?if_exists})">
					                 <img  src="/templets/${templateStyle?if_exists}/images/purchase.gif" />
					              </a>
				              </li>
				           </#if>  
				               <li>
				               	  <a href="###;" onclick="single_askprice('${supply.cust_name?if_exists}')">
					                 <img src="/templets/${templateStyle?if_exists}/images/askprice.gif" />
					              </a>
				               </li>
				          </ul>     
	                 </td>
               </tr>
               <tr>
	               <td class="mod_td4" colspan="2" >
	                <div style="font-size:13px;margin-height:20px;margin-top:0px;"">
	               		 <#if supply.status_name?if_exists!=''>	
	               		   	<div>经营模式:${(supply.status_name)?if_exists}</div>
	               		  </#if> 
	                      <#if supply.min_order!=''> 
				            <div style="margin-top:2px;">最低起批量:${supply.min_order?if_exists}${supply.unit?if_exists}起订</div>
				          </#if> 
		                  <#if supply.content?if_exists !=''>
			               <div style="margin-top:2px;">   <#if supply.content?length lt 30>
							      ${supply.content?if_exists}
							  <#else>
							      ${supply.content[0..29]}...    
							  </#if>  
						   </div>	   
						  </#if>  
						  
				   </div>
	               </td>
               </tr>
               <tr>
                  <td class="mod_td6">                 
	                   <#if supply.area_name!=''>
	                     <span class="area_name"> [${supply.area_name?if_exists}]</span>
	                   </#if>                  
	                  <span style="float:right;">
	                     ${supply.in_date[0..9]?if_exists}
	                  <span>
                 </td>
               </tr>
               <tr>
               <td  class="mod_td6">
                  <span class="area_name">	                                          
	                    <span class="cust_name_id">
	                   		 <a href="/showroom/${supply.user_name?if_exists}" target="_blank">${supply.cust_name?if_exists}</a>
	                    </span>
	                    <#if supply.level_code!='4'>
	                    	<#if (supply.level_name)?if_exists>
                       			 <span class="flll_main_mid_text" >[${(supply.level_name)?if_exists}]</span>
                       		 </#if>
	                    <#else>
	                    	<span style="color:red; ">[${(supply.level_name)?if_exists}]</span>
	                    </#if>
	              </span>  
	              <span style="float:right;">	           
	              	  <#if supply.contact_qq?if_exists!="">
                          <a target="_blank" href=" http://wpa.qq.com/msgrd?v=3&uin=${supply.contact_qq?if_exists}&site=qq&menu=yes">
                          <img border="0" src="http://wpa.qq.com/pa?p=1:${supply.contact_qq?if_exists}:16"></a>
             		  </#if>             		  
             	  </span>    
               </td>         
               </tr>
            </table>
        </div>        
      </#list> 
      <div class="right_bottom">
         <input class=" flll_main_top_jg f_left" id="all_cb_box_bt" name="" type="checkbox" value="" />
         <a class="f_left" href="###;" onclick="toContrast();"><img src="/templets/${templateStyle?if_exists}/images/xtb_008.gif" /></a>
         <a class="f_left" href="###;" onclick="send_askprice();"><img src="/templets/${templateStyle?if_exists}/images/xtb_003.gif" /></a>
         <p class="f_right"><a href="#"><img src="/templets/${templateStyle?if_exists}/images/xtb_007.gif" />返回顶部</a></p>
      </div>
      <div class="page_main">   
           <#if supplyList?if_exists && (supplyList?size > 0)>
	           ${pageBar?if_exists}  
	        </#if>      
	  </div>   
      <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
      </#if>
      <script>
        function toContrast(){
        	 var count=0;
		     $("input:checkbox[name=cb_box]").each(function(){	  
	             if(this.checked){
	               count++;
	             }
	         })
	         if(count>=2){
	            document.forms[0].action="/portal/supply!contrast.action";
	            document.forms[0].submit();
	         }else{
	             alert("请至少选择两条数据对比!");
	         }
        }
      </script>
      </div>
   </div>
   <div class="right_main f_right">
      <div class="zzsads">
            <div><a href="###;">推荐链接</a></div>
	        <#list sponsorAdsList as ads>   
	           <table>
	              <tr><td>
	              <#if ads.title?length lt 14>
	                   <a class="titleurl" href="${ads.link_url?if_exists}">${ads.title?if_exists}</a>
				  <#else>
					   <a class="titleurl" href="${ads.link_url?if_exists}">${ads.title[0..13]?if_exists}...</a>
				  </#if>
	              </td><tr>
	              <tr><td class="td2">
	              <#if ads.content?length lt 38>
	                   <a href="${ads.link_url?if_exists}">${ads.content?if_exists}</a>
				  <#else>
					   <a href="${ads.link_url?if_exists}">${ads.content[0..37]?if_exists}...</a>
				  </#if>	              
	              </td><tr>
	              <tr><td >
                     <a class="linkurl" href="${ads.link_url?if_exists}">${ads.link_url?if_exists}</a>
	              </td><tr>
	           </table>
	        </#list>  
	        <div>			
	           <table>
	           		<tr><td><a class="titleurl" href="http://demo-v1.ruibaotong.net">▶来${web_title?if_exists}推广你的产品<a></td></tr>
	           		<tr><td>咨询热线：${web_phone?if_exists}</td></tr>
	           		<tr><td><a class="linkurl" href="${web_basehost?if_exists}">${web_basehost?if_exists}<a></td></tr>
	           </table>
	        </div>
      </div>
      
      <div class="area_title"><h2 class="title_text">供应排行</h2></div>
      <ul class="ph_main">      
        <#list supplyTopList as supply>
       <#assign rbttime='${(supply.in_date)}'/>  
         <li><span class="n1">1</span>
         <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank"><#if supply.title?length lt 16>
         			${supply.title?if_exists}
					 <#else>
					 ${supply.title[0..15]}...
					 </#if></a>
		</li>
		</#list>
      </ul>
   <div class="ad">
    <script src="/include/advshow.jsp?pos_id=51"></script>
   </div>
   <div class="ad">
    <script src="/include/advshow.jsp?pos_id=52"></script>
   </div>
 </div>
</div>
<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
	<@s.hidden name="cat_id" id="cat_id_para"/>
	<@s.hidden name="area_id" id="area_value_para"/>
	<@s.hidden name="search_images" id="search_images" />
	
</@s.form>
<!-- 发送信件的提交表单 -->
<form action="/member_Memberinbox_add.action" method="post" name="addMemberinboxFormds">
   <input type="hidden" id="send_name" name="send_name" value=""/>
   <input type="hidden" name="memberinbox.mess_type" value="2"/>
   <input type="hidden" name="loc" value="" />
</form>		    
<script>
	document.addMemberinboxFormds.loc.value = document.location.href;
</script>
<script type="text/javascript">
function checkmyself(cust_id,supplyid){ 
  if($("#cust_id").val()==""){
    window.location.replace("/login.html?loc=/a/supply/index.html");
  }else{
	  if($("#cust_id").val()==cust_id){
	     alert("您不能购买自己的商品！");
	  }else{
	     window.location.replace("/order_"+ supplyid+".html");
	  }
  }  
}
</script>
<script type="text/javascript">	
$(document).ready(function(){
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });
     //select 框选中
    $("#select_value option[value="+$("#sortby").val()+"]").attr("selected",true)
    //全选与反选
    $("#all_cb_box,#all_cb_box_bt").click(function(){
      if(this.checked){
         $("input:checkbox[name=cb_box]").each(function(){	  
            this.checked=true;
         })
      }else{
         $("input:checkbox[name=cb_box]").each(function(){	  
            this.checked=false;
         })
      }
    });
    //属性选择背景色的改变
    $(".searchspan").hover(function() {
	    $(this).addClass("blue");
	}, function() {
		$(this).removeClass("blue");
	});
	   //select改变表单提交事件
	$("#id_type").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
     //select改变表单提交事件
	$("#id_level_code").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
     //select改变表单提交事件
	$("#id_client_status").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
     //select改变表单提交事件
	$("#id_sortby").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
     //select改变表单提交事件
	$("#id_date_in_date").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  	  	
});
</script>
</body>
</html>
