<html>
<head>
<title>设置支付密码</title>
</head>
<body>
 <div class="cont_main">
 <@s.form id="password" action="/member_Memberfund_updatepasswd.action" method="post" validate="true">
 
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>会员资料>设置支付密码</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
        <tr>
             <td width="19%" valign="middle" class="left_td">旧密码<font color='red'>*</font></td>
             <td>
             	<@s.password name="oldpasswd" cssClass="txtinput"  maxLength="32"/>
             	<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">新密码<font color='red'>*</font></td>
             <td>
             	<@s.password name="newpasswd" cssClass="txtinput"  maxLength="32"/>
             	<@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
             <td valign="middle" class="left_td">确认密码<font color='red'>*</font></td>
             <td>
             	<@s.password name="confirmpasswd" cssClass="txtinput"  maxLength="32"/>
             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr> 
	    <td colspan="4" class="subbut">
	      <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		  <@s.hidden name="Memberfund.cust_id" />
			  <@s.hidden name="message" />
		  ${listSearchHiddenField?if_exists}
	      <@s.submit value="保存"  cssClass="sub"/>
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
