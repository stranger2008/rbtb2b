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
<div class="position"><p>您当前的位置：<a href="/groupgoods.html">团购</a> > 
		<a href="/mall/goods!noticeGroup.action">团购公告栏</a>>
			<#if news.title?length lt 50> 
				${news.title?if_exists} 
			<#else>   
				${news.title[0..49]}...
			</#if>
	</p>
</div>
<div class="w980"> 
 <@s.form action="/mall/goods!noticeGroup.action"  method="post">
  <div class="noticeR">
    <div class="noticeL">
      <div class="noticescont">
		 <h2 style="text-align:center;padding-top:10px;">${news.title?if_exists}</h2>	
          <div class="decont">
            <p class="f_right patop"><span>上传时间：${news.in_date?if_exists.substring(0,19)} </span><span> 浏览次数：<script src="/include/clicknum.jsp?t=news&n=news_id&v=${news.news_id?if_exists}"></script></span></p>
            <div class="clear"></div>
            ${news.content?if_exists}
            <div class="clear"></div>
          </div>
      </div>   
    </div>
  </div>
 </@s.form>
  
  <div class="rw285">
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

</body>
</html>
