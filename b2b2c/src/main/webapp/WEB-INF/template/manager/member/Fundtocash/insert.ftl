<html>
<head>
<title>资金提现申请</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/jquery.bankInput.js"></script> 
<script>
$(document).ready(function(){   
   $("#account").bankInput();
});
</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Fundtocash_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>资金提取>资金提现申请</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td valign="middle" class="left_td">金额<span class="mustfield">*</span></td>
    <td>
   <@s.textfield name="fundtocash.fund_num" cssClass="txtinput" maxLength="8"/>
                <@s.fielderror><@s.param>fundtocash.fund_num</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">收款方式<span class="mustfield">*</span></td>
    <td>
   <@s.select name="fundtocash.getcash_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>fundtocash.getcash_type</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">收款账号<span class="mustfield">*</span></td>
    <td>
   <@s.textfield id="account" name="fundtocash.account" cssClass="txtinput" cssStyle="width:200px;" maxLength="19"/>
                <@s.fielderror><@s.param>fundtocash.account</@s.param></@s.fielderror>
                (现金支付除外，必填)
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">账号姓名<span class="mustfield">*</span></td>
    <td>
   	<@s.textfield name="fundtocash.account_name" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundtocash.account_name</@s.param></@s.fielderror>
                (现金支付除外，必填)
   </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
          <@s.hidden name="key_name"/>
	       ${listSearchHiddenField?if_exists}
      <@s.submit value="提交"  cssClass="sub"/>
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="linkToInfo('/member_Fundtocash_list.action','');"/>
    </td>
  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
