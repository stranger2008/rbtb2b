<html>
  <head>
    <title>敏感字管理</title>
  </head>
  <body>
<@s.form action="/admin_Filterword_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:系统管理 > 系统工具 > 敏感字管理 
 </div>

 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Filterword_add.action','');">添加敏感字</a></li>
     <li class="sc"><a onclick="delInfo('filterword.word_id','/admin_Filterword_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('filterword.word_id')"/></td>
    <td width="30%" align="center" class="top_td">敏感字</td>
    <td width="30%"  align="center" class="top_td">替换字</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list filterwordList as word>

    <tr bgcolor="<#if word_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="filterword.word_id" value="${word.word_id?if_exists}"/></td>
    <td align="center">${word.name?if_exists}</td>
    <td align="center">${word.rep_name?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Filterword_view.action','filterword.word_id=${word.word_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">敏感字:</td>
		<td><@s.textfield name="name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">替换字:</td>
		<td><@s.textfield name="rep_name_s" maxLength="20"/></td>
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