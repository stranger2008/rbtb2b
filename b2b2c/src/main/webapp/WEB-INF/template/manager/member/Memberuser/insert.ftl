<html>
<head>
<title>添加账号</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Memberuser_insert.action" method="post" validate="true">
   <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>账号管理>添加账号</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
        <tr>
             <td width="19%" valign="middle" class="left_td">用户名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberuser.user_name" cssClass="txtinput"  maxLength="20"/>
             	<@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">密码<font color='red'>*</font></td>
             <td>
               <@s.password name="memberuser.passwd" cssClass="txtinput" maxLength="32"/>
               <@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror>
             </td>
        </tr>
         <tr>
             <td valign="middle" class="left_td">角色名称<font color='red'>*</font></td>
             <td>
               <@s.select name="memberuser.role_code" list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/>
               <#if user_type?if_exists=='1'>
                &nbsp;&nbsp;[<a href="/member_Role_add.action?ajaxRequestRandom=1" target="_blank">添加角色</a>]
               </#if>
               <@s.fielderror><@s.param>memberuser.role_code</@s.param></@s.fielderror>
             </td>
        </tr>
	    <tr>
             <td valign="middle" class="left_td">真实姓名:</td>
             <td>
               <@s.textfield name="memberuser.real_name" cssClass="txtinput"  maxLength="10"/>
               <@s.fielderror><@s.param>memberuser.real_name</@s.param></@s.fielderror>
             </td>
        </tr>
         <tr>
             <td valign="middle" class="left_td">电子邮箱:</td>
             <td>
               <@s.textfield name="memberuser.email" cssClass="txtinput"  maxLength="60"/>
               <@s.fielderror><@s.param>memberuser.email</@s.param></@s.fielderror>
             </td>
           </tr>     		
	    <tr>
        <tr>
             <td valign="middle" class="left_td">性别:</td>
             <td>
                <@s.select  name="memberuser.sex" cssStyle="width:100px;" list=r"#{'男':'男','女':'女'}" />
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">部门:</td>
             <td>
               <@s.textfield name="memberuser.org_name" cssClass="txtinput"  maxLength="10"/>
               <@s.fielderror><@s.param>memberuser.org_name</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">职位:</td>
             <td>
               <@s.textfield name="memberuser.career" cssClass="txtinput"  maxLength="10"/>
               <@s.fielderror><@s.param>memberuser.career</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">手机:</td>
             <td>
               <@s.textfield name="memberuser.cellphone" cssClass="txtinput" maxLength="20"/>
               <@s.fielderror><@s.param>memberuser.cellphone</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">电话:</td>
             <td>
               <@s.textfield name="memberuser.phone" cssClass="txtinput"  maxLength="20"/>
               <@s.fielderror><@s.param>memberuser.phone</@s.param></@s.fielderror>
             </td>
        </tr>
         <tr>
             <td valign="middle" class="left_td">QQ:</td>
             <td>
               <@s.textfield name="memberuser.qq" cssClass="txtinput"  maxLength="20"/>
               <@s.fielderror><@s.param>memberuser.qq</@s.param></@s.fielderror>
             </td>
        </tr>
         <tr>
             <td valign="middle" class="left_td">MSN:</td>
             <td>
               <@s.textfield name="memberuser.msn" cssClass="txtinput"  maxLength="60"/>
               <@s.fielderror><@s.param>memberuser.msn</@s.param></@s.fielderror>
             </td>
        </tr>
         <tr>
             <td valign="middle" class="left_td">Skype:</td>
             <td>
               <@s.textfield name="memberuser.skype" cssClass="txtinput"  maxLength="60"/>
               <@s.fielderror><@s.param>memberuser.skype</@s.param></@s.fielderror>
             </td>
        </tr> 
	    <td colspan="4" class="subbut">
	      <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		  <@s.hidden name="memberuser.user_type" value="2"/>
		  ${listSearchHiddenField?if_exists}
	      <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Memberuser_list.action','');"/>
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
