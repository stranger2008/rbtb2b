<html>
  <head>
    <title>查看邮件发送历史记录</title>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 查看邮件发送历史记录
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Emailhistory_update.action" method="post" validate="true">	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <!--强制换行css样式-->
           <tr style="table-layout:fixed; word-break: break-all; overflow:hidden;">
             <td class="table_name">收件人地址:</td>
             <td>
             	 <@s.label name="emailhistory.get_email"/>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">邮件标题:</td>
             <td>
              <@s.label name="emailhistory.title"/>
             </td>
           </tr>
            <tr>
             <td width="19%" class="table_name">发件人邮箱:</td>
             <td>
              <@s.label name="emailhistory.send_email"/>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">发件人名称:</td>
             <td>
             	<@s.label name="emailhistory.send_name"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">邮件模板:</td>
             <td>
             <@s.select name="emailhistory.temp_id" list="emailtemplateList" listValue="temp_name" listKey="temp_id" />
             </td>
           </tr>
             <tr>
             <td class="table_name">邮件内容:</font></td>
             <td colspan="3">
             	${emailhistory.content?if_exists}
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="trade_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Emailhistory_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>