<html>
<head>
<title>账号管理</title>
</head>
<body>
<@s.form action="/member_Memberuser_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>账号管理</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Memberuser_add.action','');">添加账号</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('memberuser.user_id','/member_Memberuser_delete.action')">删除</a></li>
     </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
   <td width="3%" class="top_td">  
   		<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberuser.user_id')"/>  		
   </td>
    <td width="20%"  align="center" class="top_td">用户名</td>
    <td width="20%"  align="center" class="top_td">用户类型</td>
    <td width="20%"  align="center" class="top_td">角色名称</td>
    <td width="17%" align="center" class="top_td">编辑</td>
  </tr>
   <#list memberuserList as user>
  <tr bgcolor="#FFFFFF">
    <td>
    	<#if user.user_type!='1'>
    	<input type="checkbox" name="memberuser.user_id" value="${user.user_id?if_exists}"/>
    	</#if>
    </td>
    <td align="center">${user.user_name?if_exists}</td>
    <td align="center"><#if user.user_type=='1'>管理员<#else>子账号</#if></td>
    <td align="center">
		    <#if user.user_type!='1'>
		    	${user.role_name?if_exists}
		    </#if>
    </td>
    <td align="center">
     <a onclick="linkToInfo('/member_Memberuser_view.action','memberuser.user_id=${user.user_id?if_exists}');" class="xg">修改</a>
     <#if user.user_type!='1'>
         <a onclick="delOneInfo('memberuser.user_id','/member_Memberuser_delete.action','${user.user_id?if_exists}')" class="dele">删除</a>
     </#if> 
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
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">用户名:</td>
		<td><@s.textfield name="user_name_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">用户类型:</td>
		<td><@s.select name="user_type_s" list=r"#{'':'请选择','1':'管理员','2':'子账号'}"/></td>
	</tr>
	<tr>
		<td align="right">角色代码:</td>
		<td> <@s.select name="role_code_s" list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/></td>
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
