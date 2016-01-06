<html>
<head>
<title>账户充值</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Fundrecharge_insert.action" method="post" validate="true" target="_blank">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>账户充值>账户充值信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">充值金额<span class="mustfield">*</span></td>
    <td width="83%">
     <@s.textfield name="fundrecharge.fund_num" cssClass="txtinput" value="1" maxLength="20"/>元
      <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">支付平台<span class="mustfield">*</span></td>
    <td>
   <@s.select name="fundrecharge.payplat" list="paymentList"  headerKey="0" listValue="pay_name" listKey="pay_code" headerValue="请选择"/>  
   <@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
      <@s.submit value="提交"  cssClass="sub"/>
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="linkToInfo('/member_Fundrecharge_list.action','');"/>
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
