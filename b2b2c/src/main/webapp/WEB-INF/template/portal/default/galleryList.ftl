<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/picture_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
</head>
<body>
<@s.form action="/portal/gallery!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<#include "/WEB-INF/template/portal/default/position.ftl" />  
<div class="w960">
   <div class="left_main f_left">
       <#include "/WEB-INF/template/portal/default/cateList.ftl" />    
     <div class="clear"></div>
      <#include "/WEB-INF/template/portal/default/attrList.ftl" />    
     <#if galleryList?if_exists && (galleryList?size > 0)>
      <div class="flll_main">
         <div class="pic_main">
          <#list galleryList as gallery>
          <#assign rbttime='${(gallery.in_date)?if_exists}'/>    
          <div>
              <div class="pic_sigle">
	               <div class="divMiddle" style="width:176px;height:133px;line-height:133px;">
		              <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('gallery','${gallery.gal_id?if_exists}','${rbttime?if_exists}')")}"
			               target="_blank"  class="AMiddle"  style="width:176px;">
			              <img src="${gallery.img_path?if_exists}" 
			              ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${gallery.img_path?if_exists}','166','123')")} class="ImgMiddle"/>
		              </a>
		            </div>		            
	              <p style="margin-top:10px;"> <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('gallery','${gallery.gal_id?if_exists}','${rbttime?if_exists}')")}" 
	                  target="_blank">
		               <#if gallery.title?length lt 16>${gallery.title?if_exists}
					   <#else>${gallery.title[0..15]}...
					   </#if>
				  </a></p>		
				</div>	 
		  </div>
            </#list>
         </div>
         <div class="clear"></div>
       <div class="page_main">
          ${pageBar?if_exists}<br/>    
      </div>

      <div class="clear"></div>  
      </div>  
                  <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>
   </div>
   <div class="right_main f_right">
      <div class="area_title">
        <h2 class="title_text">推荐图片</h2>
      </div> 
       <div class="hotpic_main">
       <div class="clear"/>
            <#list galleryRecList as galleryrec>
             <#assign rbttime='${(galleryrec.in_date)?if_exists}'/>    
             <div class="hotpic_main_sigdiv" >    
                  <div  class="divMiddle" style="width:110px;height:82px;line-height:82px;">
		             <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('gallery','${galleryrec.gal_id?if_exists}','${rbttime?if_exists}')")}" 
		                 target="_blank"  class="AMiddle"  style="width:110px;">
		                 <img src="${galleryrec.img_path?if_exists}" 
		                 ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${galleryrec.img_path?if_exists}','100','72')")} class="ImgMiddle"/>
		             </a>       
			     </div>          
			     <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('gallery','${galleryrec.gal_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
		             <p style="text-align:center;">
		             <#if galleryrec.title?length lt 10>
		                 ${galleryrec.title?if_exists}
					 <#else>
					     ${galleryrec.title[0..9]}...
					 </#if>           
		             </p>
		         </a>
	         </div>
            </#list>             
       </div> 
       <div class="clear"></div>
      </div>  
               
	     
      <div class="area_title"><h2 class="title_text">图库排行</h2></div>
      <ul class="ph_main">
         <#list galleryTopList as gallerytop>
           <#assign rbttime='${(gallerytop.in_date)?if_exists}'/>    
         <li><span class="n1">1</span>
         <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('gallery','${gallerytop.gal_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank"> <#if gallerytop.title?length lt 16>
		${gallerytop.title?if_exists}
		 <#else>
		 ${gallerytop.title[0..15]}...
		 </#if></a>
		</li>
		</#list>        
      </ul>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=71"></script>
   </div>
   <div class="ad">
      <script src="/include/advshow.jsp?pos_id=72"></script>
   </div>
      </div>
</div>
 <div class="clear"/> </div> </div>
<#include "/${templateRoute?if_exists}/bottom.html">
<@s.hidden name="searchText"/>
<@s.hidden name="cat_id" id="cat_id_para"/>
</@s.form>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
    document.getElementById(para_name).value = para_value;
   	document.searchForm.submit();
}
$(document).ready(function(){
     /*供应排行*/
	 $(".ph_main .n1:lt(3)").addClass("n1");
	 $(".ph_main .n1:gt(2)").addClass("n2");
     var value_array = [1,2,3,4,5,6,7,8,9,10]; 
	 $(".ph_main .n1").each(function(i){  
		 $(this).text(value_array[i]);
     });   
     $("img").lazyload();  
});
</script>
</body>
</html>
