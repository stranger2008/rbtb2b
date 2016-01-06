<html>
  <head>
    <title>修改支付方式</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/keyboard.js"></script>
</head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 支付方式管理 > 修改支付方式
   </div>
   <div class="tj_main_cont">
   	<@s.form id="form" action="/admin_Payment_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
            <tr>
             <td class="table_name">支付接口编码:</td>
              <td width="83%">
             	<@s.label name="payment.pay_code" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">支付方式<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.pay_name" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>payment.pay_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">支付方式描述:</td>
             <td>
             	<@s.textarea name="payment.pay_desc" cssClass="txtinput" cssStyle="width:500px;height:100px"/>
             	<@s.fielderror><@s.param>payment.pay_desc</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">卖家收款账户<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.seller_name" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.seller_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">商户账号<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.pay_account" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.pay_account</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">旧密钥:</td>
             <td>
             	<@s.password name="oldpasswd" type="password" cssClass="txtinput" maxLength="30" cssStyle="width:300px;" />           
             	<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">新密钥:</td>
             <td>
             	<@s.password name="passwd" type="password" cssClass="txtinput" maxLength="30" cssStyle="width:300px;" />
             	 <span id="span0" onClick="showkeyboard()" style={cursor:hand;}>
					<img border="0" width="56" height="18"  src="/include/images/softkeyboard.gif">
				 </span>
             	<@s.fielderror><@s.param>passwd</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">支付手续费:</td>
             <td>
             	<@s.textfield name="payment.hand_fare" cssClass="txtinput" maxLength="3"/>
             	<@s.fielderror><@s.param>payment.hand_fare</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Payment.hand_fare')}"/> '>
             </td>
           </tr>
           <tr>
             <td class="table_name" >是否启用:</td>
             <td>
         	 <@s.radio name="payment.enabled" list=r"#{'0':'是','1':'否'}" checked="true" />
         	 <@s.fielderror><@s.param>payment.enabled</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="payment.pay_id"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Payment_list.action';document.forms[0].submit();"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>

<div class="clear"></div> 
  </body>
</html>