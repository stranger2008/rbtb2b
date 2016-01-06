<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/brand_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/brand!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
       <#include "/WEB-INF/template/portal/default/cateList.ftl" />   

	    <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
      <div class="clear"></div> 
       
      <#if brandList?if_exists && (brandList?size > 0)>
      <div class="flll_main">
         <div>
		     <#list brandList as brand>     
		     <#assign rbttime='${(brand.in_date)?if_exists}'/>
		     <#if rbttime?if_exists?length  gt 10><#assign rbttime=rbttime?if_exists[0..10] /></#if>
		       <div class="brand_cont">
		         <div style="float:left;">
		            <div  class="divMiddle" style="width:139px;height:65px;line-height:65px;">
				         <a  href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('brand','${brand.brand_id?if_exists}','${(brand.in_date)?if_exists}')")}" 
				         target="_blank"  class="AMiddle"  style="width:139px;"> 
				           <img src=" ${brand.img_path?if_exists}" 
				             ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${brand.img_path?if_exists}','129','55')")} class="ImgMiddle"/>
				         </a>
				     </div>
				  </div>
				  <table style="padding-left:20px;">
				  	<tr><td width="80%"><b><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('brand','${brand.brand_id?if_exists}','${(brand.in_date)?if_exists}')")}" target="_blank">
		             <#if (brand.title)?if_exists?length lt 22>${(brand.title)?if_exists}
					 <#else>${(brand.title)?if_exists[0..21]}...</#if>
			       	 </a>
					 </b></td>
					 <td  style="padding-left:30px;">[${(brand.area_name)?if_exists}]</td>
					</tr>
				  	<tr ><td  style="padding-top:20px;"><a href="/showroom/${brand.user_name?if_exists}" target="_blank">${(brand.cust_name)?if_exists}</a> </td>
				  	<td  style="padding-top:20px;"><span class="date">${(rbttime)?if_exists}</span></td>
				  	</tr>
				  </table>
		         
		      </div>
		      </#list> 
		      <div class="clear"></div>
         </div>
        
          
       <div class="page_main">
           <div style="text-align:center;"> ${pageBar?if_exists}<br/></div>
       </div>
      
      <div class="clear"></div>  
      </div> 
      <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
      </#if> 
   </div>
   
  
   <div class="right_main f_right">
      
       
      <div class="area_title">
        <h2 class="title_text">推荐品牌</h2>
      </div>
       <ul class="hotpic_main">
          <#list brandrecomList as brandrecom>
          <#assign rbttime='${(brandrecom.in_date)?if_exists}'/>           
             <li>
	             <div  class="divMiddle" style="width:104px;height:77px;line-height:77px;">
	               <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('brand','${brandrecom.brand_id?if_exists}','${rbttime?if_exists}')")}"
	                target="_blank"  class="AMiddle"  style="width:104px;">
	                  <img src="${brandrecom.img_path?if_exists}"
	                   ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${brandrecom.img_path?if_exists}','94','67')")} class="ImgMiddle"/>
	               </a>
	             </div>
               <br/>
               <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('brand','${brandrecom.brand_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
                <#if (brandrecom.title)?if_exists?length lt 8>
                  ${(brandrecom.title)?if_exists}
		          <#else>
		          ${(brandrecom.title)?if_exists[0..7]}
		        </#if>
		       </a>	       
		    </li>
         
         </#list>
         
         <div class="clear"></div>
         
       </ul>
   
   	  
      
      <div class="area_title"><h2 class="title_text">搜索排行</h2></div>
      <ul class="ph_main">
        <#list brandrankList as brandrank>
        <#assign rbttime='${(brandrank.in_date)?if_exists}'/>
         <li><span class="n1">1</span>
         <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('brand','${brandrank.brand_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
         <#if (brandrank.title)?if_exists?length lt 21>
            ${(brandrank.title)?if_exists}
		 <#else>
		   ${(brandrank.title)?if_exists[0..20]}... 
		 </#if>
		 </a>
         </li>
        </#list>     
      </ul>
	  <div class="ad">
		 <script src="/include/advshow.jsp?pos_id=112"></script>
	  </div>
      <div class="ad">
         <script src="/include/advshow.jsp?pos_id=113"></script>
      </div>
   </div>
</div>

<div class="clear"></div>
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="type" id="para_value_para"/>
<@s.hidden name="area_id" id="area_value_para"/>
<@s.hidden name="searchText"/>
<#include "/${templateRoute?if_exists}/bottom.html">
</@s.form>
<script type="text/javascript">
    function setHiddenVal(para_name,para_value){
            document.getElementById(para_name).value = para_value;
	       	document.searchForm.submit();
	}
$(document).ready(function(){
     /*品牌排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });
});
</script>
</body>
</html>
