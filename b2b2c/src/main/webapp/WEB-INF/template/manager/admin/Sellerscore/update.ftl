<html>
  <head>
    <title>修改记录商家打分信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Sellerscore_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">trade_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.trade_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.trade_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">get_cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.get_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.get_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">user_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.user_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.user_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">self_cust_id<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.self_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.self_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">desc_score<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.desc_score" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.desc_score</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">service_score<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.service_score" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.service_score</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">delivery_score<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.delivery_score" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.delivery_score</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">in_date<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sellerscore.in_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sellerscore.in_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sellerscore_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

