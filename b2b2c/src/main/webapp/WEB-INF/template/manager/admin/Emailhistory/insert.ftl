<html>
  <head>
    <title>邮件发送</title>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 邮件发送
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Emailhistory_insert.action" method="post" validate="true">	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">邮件地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="emailhistory.get_email" cssClass="txtinput" cssStyle="width:600px"/><input type="button" name="userList" value="获取邮件地址" onclick="document.forms[0].action='/admin_Emailhistory_indexuser.action';document.forms[0].submit();"/>
             	<@s.fielderror><@s.param>emailhistory.get_email</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">邮件标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="emailhistory.title" size="70" maxLength="100"/>
             	<@s.fielderror><@s.param>emailhistory.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">邮件模板:</td>
             <td>
             <@s.select name="emailhistory.temp_id" list="emailtemplateList" listValue="temp_name" listKey="temp_id" headerKey="0" headerValue="请选择" />
             </td>
           </tr>
             <tr>
             <td class="table_name">邮件正文<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textarea name="emailhistory.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
				<@s.fielderror><@s.param>emailhistory.content</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="trade_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="发送"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Emailhistory_list.action','');" />
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>