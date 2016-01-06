<html>
  <head>
    <title>修改敏感字</title>
  </head>
  <body>


<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:系统管理 > 系统工具 > 敏感字管理  > 修改敏感字
 </div>

   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Filterword_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">敏感字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="filterword.name" cssClass="txtinput" style="width:250px;" maxLength="20"/>
             	<@s.fielderror><@s.param>filterword.name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">替换字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="filterword.rep_name" cssClass="txtinput" style="width:250px;" maxLength="20"/>
             	<@s.fielderror><@s.param>filterword.rep_name</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="filterword.word_id"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Filterword_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>