<html>
<head>
<title>修改邮箱</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">修改邮箱</a>
    </div>
<@s.form action="/bmall_Memberuser_updateE.action"  method="post" name="formshopcongif" validate="true">
 <@s.hidden name="setpos" value="${setpos?if_exists}"/>
<div class="rightside f_right">
     <div class="rpostion"><h2>账号管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">修改邮箱</h2>
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
            
            
          <tr><td class="firsttd">密码<font color="red">*</font></td>
            <td>
        		<@s.password name="oldpasswd" cssClass="winput"  maxLength="32"/>
        		<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
            </td></tr>
          
            <tr><td class="firsttd">旧邮箱:</td>
            <td>
        		<@s.textfield name="memberuser.email" cssStyle="display:none;"/>${memberuser.email?if_exists}
        		<@s.fielderror><@s.param>email</@s.param></@s.fielderror>
            </td></tr>
            
             <tr><td  class="firsttd">新邮箱<font color="red">*</font></td>
             <td>
        		<@s.textfield name="newemail" cssClass="winput" maxLength="50"/>
        		<@s.fielderror><@s.param>newemail</@s.param></@s.fielderror>
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

