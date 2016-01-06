<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${news.title?if_exists}-最新公告-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/sortlist.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="/templets/${templateStyle?if_exists}/js.js"></script>    
<link rel="StyleSheet" href="/include/components/goodstree/dtree.css" type="text/css" />
<script type="text/javascript" src="/include/components/goodstree/dtree.js"></script>  
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>

<#include "/WEB-INF/template/bmall/default/top.ftl">
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}">首页</a> > <a href="/mallarticle.html">最新公告</a>> ${news.title?if_exists} </p></div>
<div class="w980">
  <div class="contL">
    <#if cattreeList?exists && ( cattreeList.size() > 0 ) > 
     <div class="Sidebar">
        <h2>商品分类</h2>
        <ul class="sideFl">
        <script type="text/javascript">
			a = new dTree('a');
			a.add('1111111111',-1,'');
			<#list cattreeList as cattree>
			a.add(${cattree.cat_id?if_exists},${cattree.up_cat_id?if_exists},'<a href="/mall-goodslist-${cattree.en_name?if_exists}.html">${cattree.cat_name?if_exists}</a>&nbsp;','#');
			</#list>
			document.write(a);
	  	</script>
        </ul>
    </div>
   </#if>
  </div> 
  
<@s.form action="/mall/goods!articleDetail.action"  method="post">
  <div class="contR">
    <div class="slist">
      <div class="slistcont">
          <h2 class="detitle">${news.title?if_exists}</h2>	
          <div class="decont">
            <p class="f_right patop"><span>上传时间：${news.in_date?if_exists.substring(0,19)} </span><span> 浏览次数：<script src="/include/clicknum.jsp?t=news&n=news_id&v=${news.news_id?if_exists}"></script></span></p>
            <div class="clear"></div>
            ${news.content?if_exists}
            <div class="clear"></div>
          </div>
          <#if oneID?if_exists==''>
          <p class="syp"></p>
          <#else>
          <p class="syp"><span>上一篇：</span>
          <a href="/mallarticle-${oneID?if_exists}-${id1?if_exists}.html">${oneTitle?if_exists}</a>
          </#if>
          
          <#if twoID?if_exists==''>
          <p class="syp"></p>
          <#else>
          <p  class="syp"><span>下一篇：</span>
          <a href="/mallarticle-${twoID?if_exists}-${id2?if_exists}.html">${twoTitle?if_exists}</a>     
          </p>
          </#if>
      </div>   
    </div>
  </div> 
   </@s.form> 
<div class="clear"></div>
</div>

<div class="clear"></div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
