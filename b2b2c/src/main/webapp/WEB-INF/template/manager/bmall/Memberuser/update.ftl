<html>
<head>
<title>账户管理</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
 $(document).ready(function(){
 });
</script>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">账户信息</a>
    </div>
<@s.form action="/bmall_Memberuser_updateuser.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>账号管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">账户修改或查看</h2>
       <div class="table_infor f_left">
          <table style="width:750px;">
            <tr><td class="firsttd">用户名称:</td><td>
        	<@s.label name="memberuser.user_name"/>
        	<@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
            </td></tr>
            
            <!--
            <tr><td  class="firsttd3">用户类型:</td><td>
        	<#if memberuser.user_type=="1">管理员</#if>
        	<#if memberuser.user_type=="2">子账号</#if>
            </td></tr>
            
            <tr><td  class="firsttd3">角色:</td><td>
        	<@s.label name="role.role_name" cssClass="txtinput" maxLength="200"/>
            </td></tr>
            
            <tr><td  class="firsttd">Email:</td><td>
        	<@s.textfield name="memberuser.email" cssClass="txtinput" maxLength="60"/>
        	<@s.fielderror><@s.param>memberuser.email</@s.param></@s.fielderror>
            </td></tr>  
            -->
            <tr><td  class="firsttd">真实姓名:</td><td>
        	<@s.textfield name="memberuser.real_name" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>memberuser.real_name</@s.param></@s.fielderror>
            </td></tr>  
            
            <tr><td  class="firsttd">性别:</td>
            <td>
        	<@s.radio name="memberuser.sex" list=r"#{'男':'男','女':'女','保密':'保密'}" />
            </td>
            </tr>
            
            <tr><td  class="firsttd">部门:</td><td>
        	<@s.textfield name="memberuser.org_name" cssClass="txtinput" maxLength="30"/>
        	<@s.fielderror><@s.param>memberuser.org_name</@s.param></@s.fielderror>
            </td></tr> 
            
            <tr><td  class="firsttd">职位:</td><td>
        	<@s.textfield name="memberuser.career" cssClass="txtinput" maxLength="30"/>
        	<@s.fielderror><@s.param>memberuser.career</@s.param></@s.fielderror>
            </td></tr> 
            
            <tr><td  class="firsttd">手机:</td><td>
        	<@s.textfield name="memberuser.cellphone" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>memberuser.cellphone</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">电话:</td><td>
        	<@s.textfield name="memberuser.phone" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>memberuser.phone</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">QQ:</td><td>
        	<@s.textfield name="memberuser.qq" cssClass="txtinput" maxLength="15"/>
        	<@s.fielderror><@s.param>memberuser.qq</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">MSN:</td><td>
        	<@s.textfield name="memberuser.msn" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>memberuser.msn</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">Skype:</td><td>
        	<@s.textfield name="memberuser.skype" cssClass="txtinput" maxLength="30"/>
        	<@s.fielderror><@s.param>memberuser.skype</@s.param></@s.fielderror>
            </td></tr> 
            <tr>
             	<td colspan="2" align="center">
             		<@s.submit value="提  交" cssClass="submitbut"/>
             		<@s.hidden name="oldemail" value="${memberuser.email?if_exists}"/>
             		<@s.hidden name="memberuser.user_id"/>
             		<@s.hidden name="memberuser.cust_id"/>
             		<@s.hidden name="memberuser.passwd"/>
             		<#if type?if_exists=='buy'>
             		</#if>
             	</td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

