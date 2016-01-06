<html>
  <head>
    <title>发送短信</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 手机短信管理 > 发送短信
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Smshistory_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">手机号<font color='red'>*</font></td>
             <td>
               <@s.textfield name="smshistory.phoneattr" cssClass="txtinput" /><input type="button" name="userList" value="群发" onclick="document.forms[0].action='/admin_Smshistory_indexuser.action';document.forms[0].submit();"/>
               <@s.fielderror><@s.param>smshistory.phoneattr</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">短信内容<font color='red'>*</font></td>
             <td>
               <@s.textarea name="smshistory.content" cssClass="txtinput" cssStyle="width:400px;height:100px"
               onkeyup="set_textarea_length(this,300);"/>
               <@s.fielderror><@s.param>smshistory.content</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav.nav_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="发送"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Smshistory_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>