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
<@s.form action="/portal/buy!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
   	<#include "/WEB-INF/template/portal/default/cateList.ftl" />    
		  <div class="industry_main">
		     <div class="industry_main_search">
			     <table width="100%">
				     <tr>
					      <td><@s.select name="type" list="paraList" listKey='para_value'  listValue='para_key' headerKey='' headerValue="求购类型"  id="id_type"  /></td>	    
					     <td><@s.select name="sortby" list=r"#{'1':'按过期时间降序','2':'按过期时间升序','3':'信用指数降序','4':'信用指数升序'}"  headerKey='' headerValue="默认排序"   id="id_sortby"      /></td>
					     <td><@s.select name="date_in_date" list=r"#{'-1':'1天内新消息','-3':'3天内新消息','-7':'7天内新消息','-15':'15天内新消息','-30':'30天内新消息'}"  headerKey='' headerValue="所有发布时间"   id="id_date_in_date"    /></td>
					      <td><@s.select name="level_code" list="levelList" listKey='level_id'  listValue='level_name' headerKey='' headerValue="所有会员"   id="id_level_code"    /></td>
					     <td width="160" align="right" style="padding-right:20px;"></td>
				     </tr>
			     </table>
		     </div> 
		  </div>  	
	  <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
      <div class="flll_main">
      <#if buyList?if_exists && (buyList?size > 0)>
        <div class="clear"></div>
        <#list buyList as buy>   
         <#assign rbttime='${(buy.in_date)}'/>  
         <div class="flll_main_mid">
            <table width="100%" height="100%" style="height:130px;">
               <tr>
					<td rowspan="4">
	                </td>
	                <td rowspan="4" class="mod_td2">
		               <div class="divMiddle" style="width:118px;height:112px;line-height:112px;">
				           <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('buy','${buy.buy_id?if_exists}','${rbttime?if_exists}')")}"
				            target="_blank"  class="AMiddle"  style="width:118px;">
			               <#if (buy.img_path)?if_exists != ''>
			                 <img  src="${buy.img_path?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${buy.img_path?if_exists}','108','102')")} class="ImgMiddle"/>
			               <#else>
			                 <img  src="${(cfg_nopic)?if_exists}" 
	                        ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${(cfg_nopic)?if_exists}','108','102')")} class="ImgMiddle"/>
			               </#if>
			               </a>
			           </div>
	                 </td>
	                 <td  class="mod_td3" colspan="2">
	                     <div style="float:left;">
							 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('buy','${buy.buy_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
							    <h2 class="flll_title1"> 
			                    <#if buy.title?length lt 21>
							      ${buy.title?if_exists}
							    <#else>
							      ${buy.title[0..20]}...
							    </#if></h2>
							  </a>
						  </div>
						  <#if buy.c_num?if_exists!=''>
							 <span class="td_span">							  
								  <span class="td_span_1">信用指数:</span>
		                          <span class="td_span_2">${buy.c_num?if_exists}</span>
		                     </span>
		                  </#if>	                                               
	                 </td>	                
	                 <td rowspan="4" class="mod_td5" style="width:70px;">
                           <font color="#333333">
                           <#if buy.area_name!=''>
		                     [${buy.area_name?if_exists}]
		                   </#if>                          
                           </font>
	                 </td>
               </tr>
               <tr>
	               <td class="mod_td4" colspan="2">
		                  <#if buy.content?if_exists !=''>
			                  <#if buy.content?length lt 53>
							      ${buy.content?if_exists}
							  <#else>
							      ${buy.content[0..52]}...    
							  </#if>   
						  </#if>    
	               </td>
               </tr>
               <tr>
                  <td>
                  <span style="float:right;">
                      过期时间:
                      <#if buy.end_date?length lt 10>
						${buy.end_date?if_exists}
					  <#else>
					      ${buy.end_date[0..9]}   
					  </#if> 

                  <span>
                 </td>
               </tr>
               <tr>
               <td >
                  <span >	                                          
	                   &nbsp;
	              </span>  
	              <span style="float:right;">	           
	              	    &nbsp;      		  
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
      <div class="area_title"><h2 class="title_text">求购排行</h2></div>
      <ul class="ph_main">
        <#list buyTopList as top>
          <#assign rbttime='${(top.in_date)?if_exists}'/>  
         <li><span class="n1">1</span><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('buy','${top.buy_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
         <#if top.title?length lt 16>${top.title?if_exists}
		<#else>${top.title[0..15]}... </#if></a></a></li>
        </#list>
     </ul>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=53"></script>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=54"></script>
   </div>
   </div>
</div>
<div class="clear"></div>
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
           document.getElementById(para_name).value = "";
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
	$("#id_type").change(function(){//事件触发  
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
     //select改变表单提交事件
	$("#id_level_code").change(function(){//事件触发  
		document.searchForm.submit();//提交表单
    });  
});
</script>
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="area_id" id="area_value_para"/>
<@s.hidden name="searchText"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
</body>
</html>
