<html>
  <head>
    <title>操作权限管理</title>
  </head>
  <body>
<@s.form action="/admin_Roleright_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 操作权限管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Roleright_add.action','');">添加权限</a></li>
     <li class="sc"><a onclick="delInfo('roleright.right_id','/admin_Roleright_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('roleright.right_id')"/></td>
    <td width="20%" align="center" class="top_td">权限名称</td>
    <td width="20%"  align="center" class="top_td">后台类型</td>
    <td width="20%"  align="center" class="top_td">所属菜单</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list rolerightList as right>

    <tr bgcolor="<#if right_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="roleright.right_id" value="${right.right_id?if_exists}"/></td>
    <td align="center">${right.right_name?if_exists}</td>
    <td align="center"><a href="/admin_Roleright_list.action?syscode_s=${right.syscode?if_exists}" onclick="document.forms[0].submit();"><#if right.syscode=='sys'>管理员后台<#else>会员后台</#if></a></td>
    <td align="center"><a href="/admin_Roleright_list.action?menu_name_s=${right.menu_name?if_exists}" onclick="document.forms[0].submit();">${right.menu_name?if_exists}</a></td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Roleright_view.action','roleright.right_id=${right.right_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">权限名称:</td>
		<td><@s.textfield name="right_name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">后台类型:</td>
		<td><@s.select name="syscode_s" list=r"#{'':'请选择','sys':'管理员后台','com':'会员后台'}"/></td>
	</tr>
	<tr>
		<td align="right">所属菜单:</td>
		<td><@s.textfield name="menu_attr_s" maxLength="100"/></td>
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