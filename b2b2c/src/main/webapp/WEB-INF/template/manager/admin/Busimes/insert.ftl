<html>
  <head>
    <title>商家留言</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 > 商家留言
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Busimes_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">

		           <tr>
		             <td class="table_name">被留言的商店ID<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="busimes.get_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>busimes.get_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">留言内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="busimes.msg_content" cssStyle="width:500px;height:100px;" cssClass="txtinput" maxLength="20" onkeyup="set_textarea_length(this,600);"/>
		             	<@s.fielderror><@s.param>busimes.msg_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Busimes_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

