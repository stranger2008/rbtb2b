<html>
<head>
<title>修改密码</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">修改密码</a>
    </div>
<@s.form action="/bmall_Memberuser_updatePassword.action"  method="post" name="formshopcongif" validate="true">
 <@s.hidden name="setpos" value="${setpos?if_exists}"/>
<div class="rightside f_right">
     <div class="rpostion"><h2>账号管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">修改密码</h2>
       <div class="table_infor f_left">
          <table style="width:750px;">
          <#if setpos?if_exists=='2'>
            <tr><td  class="firsttd">密保问题:</td>
             <td>
        		${memberuser.passwd_ques?if_exists}
            </td></tr>
            
            <tr><td class="firsttd">密保答案<font color="red">*</font></td>
            <td>
        		<@s.textfield name="confanswer" cssClass="winput"  maxLength="32"/>
        		<@s.fielderror><@s.param>passwd_answer</@s.param></@s.fielderror>
            </td></tr>
            </#if>
            <tr><td class="firsttd">旧密码<font color="red">*</font></td>
            <td>
        		<@s.password name="oldpasswd" cssClass="winput"  maxLength="32"/>
        		<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
            </td></tr>
                        
             <tr><td  class="firsttd">新密码<font color="red">*</font></td>
             <td>
        		<@s.password name="newpasswd" cssClass="winput" maxLength="32"/>
        		<@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">确认新密码<font color="red">*</font></td>
            <td>
        		<@s.password name="confirmpasswd" cssClass="winput" maxLength="32"/>
        		<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
            </td></tr>  

            <tr>
             	<td colspan="2" style="padding-left:210px;">
             		<@s.submit value="提  交" cssClass="submitbut"/>
             		
             	</td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

