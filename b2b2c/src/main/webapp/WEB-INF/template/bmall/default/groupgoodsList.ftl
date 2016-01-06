<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>团购-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/buy.css" rel="stylesheet" type="text/css">
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>
<script text="javascript">
  window.onload = function(){
    var cat_id=$("#cat_id").val();
    if(cat_id!=''){
       $("#"+cat_id).addClass("alls");
    }
    var hight=$("#hight").val();
    if(hight!=''){
    	$("#"+hight).addClass("alls");
    }
  }

  function cataction(cat_id){
    $("#cat_id").val(cat_id);
    document.forms[0].action="/mall/goods!groupList.action";
	document.forms[0].submit();	
  }
  
  function priaction(pricedown,priceup,p){
    $("#pricedown").val(pricedown);
    $("#priceup").val(priceup);
    $("#hight").val(p);
    document.forms[0].action="/mall/goods!groupList.action";
	document.forms[0].submit();	
  }
  
</script>
<body>
<#include "/WEB-INF/template/bmall/default/top.ftl">
<!--导航结束-->

<!--团购-->
<div class="w980">
<@s.form action="/mall/goods!groupList.action" method="post">
<@s.hidden id="cat_id" name="cat_id"/> 
<@s.hidden id="hight" name="hight"/> 
<@s.hidden id="pricedown" name="pricedown"/> 
<@s.hidden id="priceup" name="priceup"/>
   <div class="buysel">
   
     <div class="buyclass">
       <div class="seloption f_left">分类：</div>
       <div class="selcont f_left"><a  style="cursor:pointer" onclick="cataction('')" class="all">全部</a>
       <#list categoryList as category>
       <a id="${(category.cat_id)?if_exists}" style="cursor:pointer" onclick="cataction(${(category.cat_id)?if_exists});">${(category.cat_name)?if_exists}</a>
       </#list>
       </div>
      <div class="clear"></div>  
     </div>
     
     <div class="buyclass">
       <div class="seloption f_left">价格：</div>
       <div class="selcont f_left">
       <a onclick="priaction('','','')" style="cursor:pointer" class="all">全部</a>
       <a id="p1" style="cursor:pointer" onclick="priaction('0','49','p1');">0-49元</a><a id="p2" style="cursor:pointer" onclick="priaction('50','99','p2');">50-99元</a>
       <a id="p3" style="cursor:pointer" onclick="priaction('100','199','p3');">100-199元</a><a id="p4" style="cursor:pointer" onclick="priaction('200','499','p4');">200-499元</a>
       <a id="p5" style="cursor:pointer" onclick="priaction('500','999','p5');">500-999元</a><a id="p6" style="cursor:pointer" onclick="priaction('1000','','p6');">1000元以上</a>
       </div>
      <div class="clear"></div>  
     </div>
       
   </div>
  
</div>

<div class="w980"> 

  <div class="lw695">
   <#list groupgoodsList as groupgoods>
    <ul>
	      <#assign s = ((groupgoods.group_price/groupgoods.sale_price?double)*10)/>
	      <#assign p = s?string.currency/>
	      <li><a href="/mall/goods!groupdetail.action?group_id=${(groupgoods.group_id)?if_exists}" class="buytitle"><#if (groupgoods.group_title)?length lt 20> ${(groupgoods.group_title)?if_exists}<#else>${(groupgoods.group_title)[0..19]}</#if></a></li>
	      <li><div class="buypic"><a href="/mall/goods!groupdetail.action?group_id=${(groupgoods.group_id)?if_exists}"><img src="${(groupgoods.group_img)?if_exists}" width="290px" height="190px"></a></div></li>
	      <li><div class="buyprice"><p class="f_left">原价：<span class="price">￥${(groupgoods.sale_price)?if_exists}</span><b>${p}折</b></span><p class="f_right"><b>${(groupgoods.already_buy)?if_exists}</b>人已购买</p></div></li>
	      <li>
	      <div class="buyback">
	      	 <span class="bcost">￥${(groupgoods.group_price)?if_exists}</span>
	      	 
		      <a href="/mall/goods!groupdetail.action?group_id=${(groupgoods.group_id)?if_exists}" class="bbbut">
		      		<img src="/templets/bmall/images/xzbuybutton_03.gif">
		      </a>
	      </div>
	      </li>
    </ul>
    </#list>
    <div class="clear"></div>
    
    <div class="page_main">
		${pageBar?if_exists} 
	</div>
      
  </div>
  
  <div class="rw285">
	<!--团购公告-->
    <h2 class="noticeh2">
    	<div style="margin-left:240px;margin-top:10px;font-size:14px;color:#e3341a;">
    		<a href="/mall/goods!noticeGroup.action">更多</a>
		</div>
    </h2>
    <div class="notcont">
      <ul>
      	<#assign nncount=0>
       	<#list newsIndexList as newsLists>
          <#if nncount lt 8>
	          <li ><a href="/mall/goods!noticeDetail.action?nid=${newsLists.news_id?if_exists}">
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

    <h2 class="recomh2"></h2>
    
     <#list hotgroupList as hotgroup>
     <#assign s = ((hotgroup.group_price/hotgroup.sale_price?double)*10)/>
      <#assign p = s?string.currency/>
    <ul class="recul">
      <li><div class="recompic"><a href="/mall/goods!groupdetail.action?group_id=${(hotgroup.group_id)?if_exists}"><img src="<#if (hotgroup.group_img)?if_exists!=''>${(hotgroup.group_img)?if_exists}<#else>/include/images/nopic.jpg</#if>" width="260px" height="176px"></a></div></li>
      <li><#if (hotgroup.group_title)?length lt 40> ${(hotgroup.group_title)?if_exists}<#else>${(hotgroup.group_title)[0..39]}</#if></li>
      <li><div class="reprice"><p class="f_left">原价：<span class="price">${(hotgroup.sale_price)?if_exists}元</span><b>${p}折</b></span><p class="f_right"><b>${(hotgroup.already_buy)?if_exists}</b>人已购买</p></div></li>
      <li><div class="reback"><span class="costs">￥${(hotgroup.group_price)?if_exists}</span><a href="/mall/goods!groupdetail.action?group_id=${(hotgroup.group_id)?if_exists}" class="recbut"><img src="/templets/bmall/images/recbut_03.gif"></a></div></li>
    </ul>
    </#list>
    
    
    
    <div class="clear"></div>
    
  </div>
  
</div>

<!--底部开始-->
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
