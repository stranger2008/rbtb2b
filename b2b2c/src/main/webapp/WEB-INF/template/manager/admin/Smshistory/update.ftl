<html>
  <head>
    <title>查看短信发送详情</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 手机短信管理 > 查看短信发送详情
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Smshistory_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">手机号:</td>
             <td>
               <@s.label name="smshistory.phoneattr" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">短信内容:</td>
             <td>
             	 <@s.label name="smshistory.content" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">发送时间:</td>
             <td>
             	 <@s.label name="smshistory.send_date" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">操作人:</td>
             <td>
             	 <@s.label name="user_name" cssClass="txtinput"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav.nav_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Smshistory_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>