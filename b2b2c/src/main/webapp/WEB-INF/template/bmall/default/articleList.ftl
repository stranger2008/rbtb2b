<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>最新公告-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/sortlist.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="/templets/${templateStyle?if_exists}/js.js"></script>  
<link rel="StyleSheet" href="/include/components/goodstree/dtree.css" type="text/css" />
<script type="text/javascript" src="/include/components/goodstree/dtree.js"></script>  
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>

<#include "/WEB-INF/template/bmall/default/top.ftl">
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}">首页</a> > 最新公告 </p></div>
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
  <@s.form action="/mall/goods!articleList.action"  method="post">
  <div class="contR">
    <div class="slist">
      <div class="slistcont">
        <ul>
         <#list articleList as news> 
		             <li>  
				          <a href="/mallarticle-${news.news_id?if_exists}-${news_index}.html">				        
				           <#if news.title?length lt 40>
			     			 ${news.title?if_exists}
						   <#else>
						      ${news.title[0..39]}...
						   </#if>  
				          </a>
		          		  <span>${news.in_date?if_exists}</span>
		             </li>

         </#list>
                      
        </ul>
        
        <div class="page_main">  
           <div class="listbottom" style="text-align:center;">
	        	${pageBar?if_exists}
	       </div>
        </div>
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
