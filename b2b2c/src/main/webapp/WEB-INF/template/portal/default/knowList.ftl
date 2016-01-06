<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${list_seo_title?if_exists}</title>
<meta name="keywords" content="${list_seo_keyword?if_exists}" />
<meta name="description" content="${list_seo_desc?if_exists}" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/know_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/templets/${templateStyle?if_exists}/js/productattr.js"></script>
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<script type="text/javascript">
//点击提问的时候保留标题
function changgetext()
{
 $("#ask_titles").val($("#searchanswertext").val());
}
</script>
<title>瑞宝通2b2知道页</title>
</head>
<body>
<@s.form action="/portal/know!list.action" method="post" name="searchForm">
<#include "/${templateRoute?if_exists}/top.html">
<!--导航结束-->
<div class="position"><P>当前位置: ${page_position?if_exists}</P></div>
<div class="w960">
   <div class="left_main f_left">
   <#include "/WEB-INF/template/portal/default/cateList.ftl" /> 
      <#if cateList?exists && (cateList.size() > 1) >
	      <div class="industry_main">
	         <h2 class="industry_title">
	         <img class="title_tb" src="/templets/${templateStyle?if_exists}/images/xtb_001.gif" />按行业浏览         
	         </h2>
	         <div class="dis_more_1 dis_more">
		       <#list cateList as category>
		            <a href="###;"  title="${category.cat_name?if_exists}"  onclick="setHiddenVal('cat_id_para','${(category.cat_id)?if_exists}');" style="width:140px;">
<#if category.cat_name?length lt 7>
			            	${category.cat_name?if_exists}(${category.num?if_exists})
			            <#else>
			            	${category.cat_name[0..6]}...(${category.num?if_exists})
			            </#if>
</a>
		       </#list>		      
	         </div>
	         <#if cateList?exists && (cateList.size() > 8) >
		         <span class="display_more ">
		            <a href="###;" onclick="displayMore('dis_more_1','60px',this);">[显示更多]</a>
		         </span>
		     </#if>
	      </div>     
	  </#if> 
   <#include "/WEB-INF/template/portal/default/attrList.ftl" />   
      <div class="flll_title">
          <ul>
             <li <#if ''== ans_state?if_exists  > class="flll_title_h"</#if>><a href="###;" onclick="setHiddenVal('ans_state_value','8');" >全部</a></li>
             <li <#if '0'== ans_state?if_exists  > class="flll_title_h"</#if>><a href="###;" onclick="setHiddenVal('ans_state_value','0');" >待解决问题</a></li>
             <li <#if '1'== ans_state?if_exists  > class="flll_title_h"</#if>><a href="###;" onclick="setHiddenVal('ans_state_value','1');">已解决问题</a></li>
             <li <#if '10'== ans_state?if_exists  > class="flll_title_h"</#if>><a href="###;" onclick="setHiddenVal('ans_state_value','10');">高分问题</a></li>
        </ul>
     </div>   
      
 <#if askList?if_exists && (askList?size > 0)>    
      <div class="flll_main">
          <ul>
            <li class="flll_bt" ><span class="flll_li_cont">标题[共${titleCount?if_exists}项]</span><span class="hds">回答数</span>  <span class="zt">状态</span>
            <span class="twsj">提问时间</span><div class="clear"></li>
            <#list askList as ask>
              <#assign rbttime='${(ask.in_date)?if_exists}'/>  
               <li><span class="flll_li_cont"><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('know','${ask.ask_id?
if_exists}','${rbttime?if_exists}')")}" target="_blank">
               	 <#if ask.title?length lt 26>
                    ${ask.title?if_exists}
				 <#else>
					${ask.title[0..25]}...
				 </#if></a><font color='red'> 
				 <#if ask.integral?if_exists!='0'>
				 ${ask.integral?if_exists}
				 </#if>
				 </font></span>
				 <span class="hds">${ask.num?if_exists}</span>  
                 <span  <#if '1'== ask.answer_state?if_exists  >class="zt zt1" <#else> class="zt zt2"</#if> ></span> 
                 <span class="twsj">${ask.in_date[0..9]?if_exists}</span><div class="clear"></li>
            </#list>         
            </ul>
          <div class="clear"></div>    
          <div style="text-align:center;margin-top:13px;">
              ${pageBar?if_exists}<br/>   
          </div>   
      </div>
             <#else>
           <div class="search_no_info">对不起！没有找到符合你搜索条件的信息!</div>
        </#if>  
   </div>
     
   <div class="right_main f_right">
   
   <div class="all">
        <div class="all_l f_left"></div>
        <div class="all_c f_left">
              <input type="text" id="searchanswertext" onblur="changgetext();"  class="input_text"/>
              <div class="input_buttom">
              <input type="button" value="搜索问题" onclick="serarchop('know','searchanswertext');" class="k_da f_left"/>
              <input type="button" value="我要提问" onclick="document.myAskForm.submit();" class="k_tw f_left"/>
              <input type="button" value="我要回答" onclick="myanswers('know','searchanswertext');" class="k_tw f_left"/></div>
        </div>
        <div class="all_r f_left"></div>
     </div>
     <div class="clear"></div>
     <div class="ph">
      <h2 class="title_text">推荐问题</h2>	 
      <div class="ph_main">
       <#list recomList as recom>
       <#assign rbttime='${(recom.in_date)?if_exists}'/>  
            <ul>
                 <li class="k_nav_name">
                 <a target="_blank" style="color:black;font-size:12px;" href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('know','${recom.ask_id?
if_exists}','${rbttime?if_exists}')")}">
                  <#if recom.title?length lt 18>
                    ${recom.title?if_exists}
				 <#else>
					${recom.title[0..17]}...
				 </#if></a></li>                 
            </ul>       
       </#list>    
     </div>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=64"></script>
   </div>
   <div class="ad">
     <script src="/include/advshow.jsp?pos_id=65"></script>
   </div>
   </div>
</div>
<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
<@s.hidden name="cat_id" id="cat_id_para"/>
<@s.hidden name="ans_state" id="ans_state_value" value=""/>
<@s.hidden name="searchText"/>
</@s.form>
 <form action="/member_Ask_cate.action" name="myAskForm" method="post">
    <input type="hidden" name="loc" value="" />
    <input type="hidden" name="ask.title" id="ask_titles"  />
 </form>
 <script>
  document.myAskForm.loc.value = "/member_Ask_cate.action";
</script>
<script type="text/javascript">
function setHiddenVal(para_name,para_value){
        document.getElementById(para_name).value = para_value;
       	document.searchForm.submit();
}
//我要回答
function myanswers(searchType,id)
{
 var sid="#"+id
 var wd='';//获取搜索的关键字
 var searchtext=$(sid).val();
 if(searchtext!='')
 {
 wd=encodeURIComponent(encodeURIComponent($(sid).val()));
 location.href="/portal/"+searchType+"!list.action?wd="+wd+"&state1=0"; 
 }
 else
 {
  alert("请输入回答内容！");
 }
} 
//搜索框搜索问题
function serarchop(searchType,id,state)
{
 var sid="#"+id
 var wd='';//获取搜索的关键字
 if($(sid).val()!='')
 {
  wd=encodeURIComponent(encodeURIComponent($(sid).val()));
  location.href="/portal/"+searchType+"!list.action?wd="+wd; 
 }
 else
 {
  alert("请输入搜索内容！");
 }
} 
</script>
</body>
</html>
