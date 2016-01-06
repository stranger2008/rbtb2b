<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>商品品牌-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/brand.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<script type="text/javascript">
	function nTabs(thisObj, Num) {
	    if (thisObj.className == "curr") return;
	    var tabList = document.getElementById("myTab").getElementsByTagName("li");
	    for (i = 0; i < tabList.length; i++) {//点击之后，其他tab变成灰色，内容隐藏，只有点击的tab和内容有属性
	        if (i == Num) {
	            thisObj.className = "curr";
	            document.getElementById("myTab_Content" + i).style.display = "block";
	        } else {
	            tabList[i].className = "";
	            document.getElementById("myTab_Content" + i).style.display = "none";
	        }
	    }
	}
</script>
</head>

<body>
<!--导航开始-->
<#include "/WEB-INF/template/bmall/default/top.ftl" >
<!--导航结束-->
<div class="content w980">
   <div class="contT"></div>
   <div class="contC">
       <div class="contM">
          <div class="contMTj"></div>
           <#assign count=1>
          <ul class="conTC">
              <#list goodbrandIndexList as gbrand>
               <#if count<=30 && gbrand.is_recom=="1">
              	<li><a style= "cursor:pointer;" target="_blank" onclick="pselect('b','${gbrand.brand_id?if_exists}')">
              	
              	<img src="<#if (gbrand.logo)?if_exists!="">${(gbrand.logo)?if_exists}<#else>/include/images/nopic.jpg</#if>" width="138px" height="46px" />
              	
              	</a><br /><a style= "cursor:pointer;" target="_blank" onclick="pselect('b','${gbrand.brand_id?if_exists}')">
              	
              	<#if (gbrand.brand_name)?if_exists?length lt 20>
			      ${(gbrand.brand_name)?if_exists}
			    <#else>
			      ${gbrand.brand_name[0..19]}
			    </#if>
              	</a></li>
              </#if>
               <#assign count=count+1>
              </#list>
             
              <div class="clear"></div>
          </ul>
          
          <div class="contMB"></div>
       </div>
       
       <div class="contTab">

          <ul id="myTab" class="tabSort">
             <#assign ccount=0>
              <#list categoryIndexList as catlist>
              <#if ccount<10>
              <li onClick="nTabs(this,${ccount});"  class="<#if catlist_index==0>curr</#if>" >
              <a href="###;" >${(catlist.cat_name)?if_exists}</a>
              </li>
               <#assign ccount=ccount+1>
               </#if>
              </#list>
              
          </ul>
          <div class="clear"></div>
             <#assign gcount=0>
             <#list categoryIndexList as catlist>
             <#if gcount<10>
             <ul class="conTC contTbaM"  id="myTab_Content${gcount}"  <#if catlist_index!=0>style="display:none;"</#if> >
              <#list goodbrandIndexList as gblist>
                  <#if gblist.goods_attr?if_exists?contains(catlist.cat_id)==true>
              	  <li> 
              	  <a style= "cursor:pointer;" target="_blank" onclick="pselect('b','${gblist.brand_id?if_exists}')">
              	  	<img src="<#if (gblist.logo)?if_exists!="">${(gblist.logo)?if_exists}<#else>/include/images/nopic.jpg</#if>" width="138px" height="46px" />
              	  </a><br />
	              	  <a style= "cursor:pointer;" target="_blank" onclick="pselect('b','${gblist.brand_id?if_exists}')">
	              	    <#if (gbrand.brand_name)?if_exists?length lt 20>
					      ${(gblist.brand_name)?if_exists}
					    <#else>
					      ${gblist.brand_name[0..19]}
					    </#if>
	              	  </a></li>
              	  </#if>
              </#list>
              <div class="clear"></div>
            </ul>
            
             <#assign gcount=gcount+1>
             </#if>
           </#list>

       </div>
       
       
   </div>
  <div class="contB"></div>
</div>

<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
