<html>
<head>
<title>角色管理</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Role_list.action" method="post">
  <div class="cont_main">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>账号管理>角色管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Role_add.action','');">添加角色</a></li>
       <li><a onclick="delInfo('role.role_id','/member_Role_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
	   <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('role.role_id')"/></td>
	   <td width="20%" align="center" class="top_td">角色名</td>
       <td width="17%" align="center" class="top_td">编辑</td>
  </tr>
   <#list rolelist as role>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="role.role_id" value="${role.role_id?if_exists}"/></td>
    <td align="center">${role.role_name?if_exists}</td>
    <td align="center">    
    <a onclick="linkToInfo('/member_Role_view.action','role.role_id=${role.role_id?if_exists}');" class="xg">修改</a>
    <a onclick="delOneInfo('role.role_id','/member_Role_delete.action','${role.role_id?if_exists}')" class="dele">删除</a>
    </td>
  </tr>
    </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>
