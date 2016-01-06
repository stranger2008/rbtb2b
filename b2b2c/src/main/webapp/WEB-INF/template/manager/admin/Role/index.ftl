<html>
  <head>
    <title>角色管理</title>
  </head>
  <body>

<@s.form action="/admin_Role_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 角色管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Role_add.action','');">添加角色</a></li>     
     <li class="sc"><a onclick="delInfo('role.role_id','/admin_Role_delete.action');">删除</a></li>
   </ul>

 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('role.role_id')"/></td>
    <td width="20%" align="center" class="top_td">角色名</td>
    <td width="20%" align="center" class="top_td">管理员数量</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list rolelist as role>

    <tr bgcolor="<#if role_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="role.role_id" value="${role.role_id?if_exists}"/></td>
    <td align="center">${role.role_name?if_exists}</td>
    <td align="center">
     <#if (role.adminnum)?if_exists=='0'>
       ${role.adminnum?if_exists}
     <#else>
       <a href="/admin_Sysuser_list.action?role_id_s=${(role.role_id)?if_exists}">${role.adminnum?if_exists}</a>
     </#if>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Role_view.action','role.role_id=${role.role_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">用户类型:</td>
		<td><@s.textfield name="user_type_s"/></td>
	</tr>
	<tr>
		<td align="right">匿名:</td>
		<td><@s.textfield name="nike_name_s"/></td>
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