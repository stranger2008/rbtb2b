<html>
  <head>
    <title>密码修改</title>
    <script type="text/javascript" src="/include/js/admin/memberuser.js"></script>
  </head>
  <body>
<@s.form action="/admin_Memberuser_list.action" method="post">
<@s.hidden name="admin_memberuser_userid" id="admin_memberuser_userid"/>
<@s.hidden name="admin_memberuser_passwd" id="admin_memberuser_passwd"/>
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="xg" ><a onclick="updatePasswdBatch('/admin_Memberuser_updatePasswdBatch.action');">密码修改</a></li>
     <li class="ret" ><a onclick="document.forms[0].action='/admin_Member_list.action';document.forms[0].submit();">返回列表</a></li>
   </ul>
 </div>
 <#if mem_type_s=='0'>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberuser.user_id')"/></td>
    <td width="20%" align="center" class="top_td">企业名称</td>
    <td width="20%" align="center" class="top_td">用户名</td>
    <td width="20%" align="center" class="top_td">用户类型</td>
    <td width="30%"  align="center" class="top_td"><div><span style="margin-right:150px;">密&nbsp;&nbsp;码</span></div></td>
  </tr>
  
  <#list memberuserList as user>

  <tr bgcolor="<#if user_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="memberuser.user_id" value="${user.user_id?if_exists}"/></td>
    <td align="center">${user.cust_name?if_exists}</td>
    <td align="center">${user.user_name?if_exists}</td>
    <td align="center"><#if user.user_type=='1'>管理员账号<#else>子账号</#if></td>
    <td align="left"><@s.password name="memberuser.passwd" id="${user.user_id?if_exists}" value="${user.passwd?if_exists}" cssStyle="width:300px;" maxLength="32" onkeyup="checkNum(this)"  /></td>
    </td>
  </tr>

  </#list>
  <tr>
    <td colspan="5">&nbsp;&nbsp;</td>
  </tr>
  
</table>
 </div>
 <#elseif mem_type_s=='1'>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberuser.user_id')"/></td>
    <td width="20%" align="center" class="top_td">企业名称</td>
    <td width="20%" align="center" class="top_td">用户名</td>
    <td width="20%" align="center" class="top_td">用户类型</td>
    <td width="30%"  align="center" class="top_td"><div><span style="margin-right:150px;">密&nbsp;&nbsp;码</span></div></td>
  </tr>
  
  <#list memberuserList as user>

  <tr bgcolor="<#if user_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="memberuser.user_id" value="${user.user_id?if_exists}"/></td>
    <td align="center">${user.cust_name?if_exists}</td>
    <td align="center">${user.user_name?if_exists}</td>
    <td align="center"><#if user.user_type=='1'>管理员账号<#else>子账号</#if></td>
    <td align="left"><@s.password name="memberuser.passwd" id="${user.user_id?if_exists}" cssStyle="width:300px;" maxLength="32" onkeyup="checkNum(this)"  /></td>
    </td>
  </tr>

  </#list>
  <tr>
    <td colspan="5">&nbsp;&nbsp;</td>
  </tr>
  
</table>
 </div>
 <div class="clear"></div>
</div>
</#if>
<@s.hidden name="mem_type_s"/>
<@s.hidden name="cust_id_s"/>
<@s.hidden name="levelinfo.user_id"/>
</@s.form>

  </body>
</html>