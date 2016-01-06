<html>
  <head>
    <title>会员资金提现申请</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金提现记录 > 会员资金提现申请
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Fundtocash_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">  
          <tr>
             <td class="table_name">申请账号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="cust_name" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">金额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.fund_num" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
                <tr>
             <td class="table_name">收款方式<font color='red'>*</font></td>
             <td>
             <@s.select name="fundtocash.getcash_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>fundtocash.getcash_type</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">收款账号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.account" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.account</@s.param></@s.fielderror>
                <img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Memberchconfig')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">账号姓名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundtocash.account_name" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.account_name</@s.param></@s.fielderror>
                 <img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Memberchconfig')}"/>"> 
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="key_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundtocash_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>