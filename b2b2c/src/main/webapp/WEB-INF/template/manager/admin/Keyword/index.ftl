<html>
  <head>
    <title>关键字管理</title>
  </head>
  <body>
  
<@s.form action="/admin_Keyword_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 关键字管理 
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Keyword_add.action','');">添加关键字</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('keyword.key_id','/admin_Keyword_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('keyword.key_id')"/></td>
    <td width="20%" align="center" class="top_td">关键字标题</td>
    <td width="20%"  align="center" class="top_td">类型</td>
    <td width="20%"  align="center" class="top_td">搜索频率</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list keywordList as keyword>
  <tr>
    <td><input type="checkbox" name="keyword.key_id" value="${keyword.key_id?if_exists}"/></td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Keyword_view.action','keyword.key_id=${keyword.key_id?if_exists}');">${keyword.key_name?if_exists}</a>
    </td>
    <td align="center">
    		${(keyword.module_name)?if_exists}
    </td>
    <td align="center">${keyword.num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Keyword_view.action','keyword.key_id=${keyword.key_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td class="table_name" align="right">关键字标题:</td>
		<td><@s.textfield name="key_name_s" maxLength="20"/></td>
	</tr>
	 <tr>
             <td class="table_name">类型:</td>
             <td>
             <@s.select name="module_type_s" list="commparaList" listValue="module_name" listKey="module_type" headerKey="" headerValue="请选择"/>
             <@s.fielderror><@s.param>keyword.module_type</@s.param></@s.fielderror>
             </td>
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
</@s.form>

  </body>
</html>