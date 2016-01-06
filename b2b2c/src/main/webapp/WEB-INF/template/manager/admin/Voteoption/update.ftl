<html>
  <head>
    <title>修改选项</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Voteoption_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
             <tr>
             <td class="table_name">调查选项名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="vote_option.option_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>vote_option.option_name</@s.param></@s.fielderror>
             </td>
           </tr>
               <tr>
             <td class="table_name">投票数:</td>
             <td>
             	<@s.textfield name="vote_option.option_count" cssClass="txtinput" style="width:80px;" maxLength="8"/>
             	<@s.fielderror><@s.param>vote_option.option_count</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.ch_num')}"/>"> 
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="vote_option.option_id"/>
	       <@s.hidden name="vote_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表"<a onclick="linkToInfo('/admin_Voteoption_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>