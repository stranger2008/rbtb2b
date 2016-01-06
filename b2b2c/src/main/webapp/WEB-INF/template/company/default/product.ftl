<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${memberconfig.keywords?if_exists}">
<meta name="description" content="${memberconfig.site_desc?if_exists}">  
<title>产品信息—${memberconfig.web_title?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 产品中心</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
 <!--左部开始-->
 <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
 <!--左部结束-->
  
 <div class="r_body f_right">
      <#if (productCateList.size()>0)>
      <div class="right_main">
            <div class="r_title"><h2><span class="title_font">产品分类</span></h2></div> 
            <div class="other_main fl">
             <#list productCateList as productCate>
              <a href="/showroom/${user_name?if_exists}/product/cat_${productCate.cat_id?if_exists}.html">${productCate.cat_name?if_exists}</a>
             </#list>
           </div>
       </div>
     </#if>
    
    <!--橱窗式浏览-->  
    <@s.form action="/companyproduct.action?user_name=${user_name?if_exists}" method="post" validate="true">
     <div class="right_main <#if (productCateList.size()>0)>padding_10</#if>">
            <div class="r_title">
              <h2><span class="title_font f_left">产品信息</span></h2>
            </div> 
            <div class="other_main">
               <ul class="ccpx">
               <#list allproductList as allproduct>
               		<li >               		
               		<div  class="divMiddle" style="width:156px;height:126px;line-height:126px;">
               		
               			<#assign pro_img_path = (allproduct.img_path)?if_exists>
               			<#if pro_img_path == ''>
						   <#assign pro_img_path = cfg_nopic>
						</#if>
               		
               			<a href="/showroom/${user_name?if_exists}/product/detail_${allproduct.product_id?if_exists}.html"   class="AMiddle"  style="width:156px;">
               				<img src="${pro_img_path}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${pro_img_path}','146','116')")} class="ImgMiddle"/>
               			</a>
                    </div>	                    
               			<p style="margin-top:8px;"><a href="/showroom/${user_name?if_exists}/product/detail_${allproduct.product_id?if_exists}.html" >
               				<#if allproduct.title?length lt 7>${allproduct.title?if_exists}<#else>${allproduct.title[0..6]}...</#if>
               			</a></p>               				
               		</li> 
               </#list>
              </ul>
              <div class="clear"></div>
               
              <!--目录方式浏览--> 
                <P class="page">
               ${pageBar?if_exists}
               </P>
               <div class="clear"></div>
           </div>
       </div>
       </@s.form>
    <!--橱窗式浏览--> 
   
  </div>
      
  <!--右部结束--> 
 
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
</body>
</html>
