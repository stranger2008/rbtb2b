<html>
  <head>
    <title>添加支付方式</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
</head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 支付方式管理 > 添加支付方式
   </div>
   <div class="tj_main_cont">
   	<@s.form id="navform" action="/admin_Payment_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
            <tr>
             <td class="table_name">支付接口编码<font color="red">*</font></td>
              <td width="83%">
             	<@s.textfield name="payment.pay_code" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>payment.pay_code</@s.param></@s.fielderror>
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
             <td class="table_name">密钥<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.passwd" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.passwd</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">支付手续费:</td>
             <td>
             	<@s.textfield name="payment.hand_fare" cssClass="txtinput" maxLength="3" value="0"/>
             	<@s.fielderror><@s.param>payment.hand_fare</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Payment.hand_fare')}"/> '>
             </td>
           </tr>
           <tr>
             <td class="table_name" >是否启用:</td>
             <td>
         	 <@s.radio name="payment.enabled" list=r"#{'0':'是','1':'否'}" value="0" checked="true" />
         	 <@s.fielderror><@s.param>payment.enabled</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
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