<html>
  <head>
    <title>回复留言</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 > 回复留言 
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Busimes_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">商铺名称:</td>
		             <td>
		             	<@s.label name="cust_name" value="${member.cust_name?if_exists}" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>	
		             <td class="table_name">留言人:</td>
		             <td>
		             	<@s.label name="user_name" value="${memberuser.user_name?if_exists}" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	            
		           <tr>
		             <td class="table_name">留言内容:</td>
		             <td>
		             	<@s.label name="busimes.msg_content" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">留言日期：</td>
		             <td>
		             	<@s.label name="busimes.msg_date" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">回复内容:</td>
		             <td>
		             	<@s.textarea name="busimes.re_content" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>busimes.re_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否有效</td>
		             <td>
		              <@s.radio name="busimes.is_enable" list=r"#{'0':'是','1':'否'}"/>
		             </td>
		           </tr>
		           
         </table>
	     <div class="buttom">
	       <@s.hidden name="busimes.trade_id"/>
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

