<html>
  <head>
    <title>选择新闻资讯</title>
    <script type="text/javascript" src="/include/js/admin/selectnewlist.js"></script>
  </head>
  <body>

<@s.form action="/admin_Subject_selectnewslist.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 专题管理 > 选择新闻资讯
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="qiyong"><a onclick="selInfo('news.news_id');">选中</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="ret"><a href="javascript:window.close();">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td">
    <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('news.news_id')"/>
    <input type="hidden" name="selecttype" id="selecttype" value="${selecttype?if_exists}"/>
    </td>
    <td width="70%"  align="center" class="top_td">资讯标题</td>
    <td width="10%" align="center" class="top_td" >发布时间</td>
  </tr>
  
  <#list newsList as sysnews>

    <tr bgcolor="<#if sysnews_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td>
    <input type="checkbox" name="news.news_id" value="${sysnews.news_id?if_exists}"/>
     <input type="hidden"  value="${sysnews.title?if_exists}" id="${sysnews.news_id?if_exists}"/>
    </td>
    <td align="center">
     <#if sysnews.title?if_exists!=''>
     <#if sysnews.title?length lt 50>
        ${sysnews.title?if_exists}
    <#else>
       ${sysnews.title[0..49]}
    </#if>
    </#if>
    </td>
    <td align="center" >${sysnews.in_date?if_exists}</td>
  </tr>

  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">资讯标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
<div class="clear"></div>
</@s.form>

  </body>
</html>
