<html>
<head>
<title>修改提示问题</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">修改提示问题</a>
    </div>
<@s.form action="/bmall_Memberuser_updateque.action"  method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
     <@s.hidden name="setpos" value="${setpos?if_exists}"/>
     <div class="rpostion"><h2>账号管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">修改密保问题</h2>
       <div class="table_infor f_left">
          <table style="width:750px;">
           <#if setpos?if_exists=='2'>
           <tr><td class="firsttd">提示问题：</td>
            <td>
        		<@s.label name="passwd_ques" value="${passwd_ques?if_exists}" cssClass="winput" maxLength="50"/>
            </td></tr>
             <tr><td  class="firsttd">回答提示问题<font color="red">*</font></td>
             <td>
        		<@s.textfield name="passwd_answer"  cssClass="winput" maxLength="50"/>
        		<@s.fielderror><@s.param>passwd_answer</@s.param></@s.fielderror>
            </td></tr>
            </#if>
            <tr><td class="firsttd"> <#if setpos?if_exists=='2'>修改</#if>提示问题<font color="red">*</font></td>
            <td>
        		<@s.textfield name="apasswd_ques"  cssClass="winput" maxLength="50"/>
        		<@s.fielderror><@s.param>apasswd_ques</@s.param></@s.fielderror>
            </td></tr>
            
             <tr><td  class="firsttd">答案<font color="red">*</font></td>
             <td>
        		<@s.textfield name="apasswd_answer"  cssClass="winput" maxLength="50"/>
        		<@s.fielderror><@s.param>apasswd_answer</@s.param></@s.fielderror>
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

