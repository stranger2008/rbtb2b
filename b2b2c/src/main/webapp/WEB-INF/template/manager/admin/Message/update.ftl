<html>
  <head>
    <title>查看网站留言</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 网站留言管理 > 查看网站留言
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Message_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">留言名称:</font></td>
             <td>
             	 <@s.label name="message.title" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">留言内容:</td>
             <td>
             	${message.content?if_exists}
             </td>
           </tr>
             <tr>
             <td class="table_name">联系人:</td>
             <td>
                <@s.label name="message.name" cssClass="txtinput"/>
             </td>
           </tr>
           
          <tr>
             <td class="table_name">联系电话:</td>
             <td>
             	<@s.label name="message.phone" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">电子邮件:</td>
             <td>
                <@s.label name="message.email" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">QQ:</td>
             <td>
             	 <@s.label name="message.qq" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">MSN:</td>
             <td>
             	<@s.label name="message.msn" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">Skype:</td>
             <td>
             	<@s.label name="message.skype" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">留言时间:</td>
             <td>
             	<@s.label name="message.in_date.substring(0,19)" cssClass="txtinput"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="message.mess_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Message_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>