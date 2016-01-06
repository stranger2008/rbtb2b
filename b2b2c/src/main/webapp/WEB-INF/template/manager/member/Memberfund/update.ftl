<html>
<head>
<title>账户信息</title>
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
        <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>账户信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">总金额：</td>
    <td width="83%">
     <@s.label name="memberfund.fund_num" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">可用金额：</td>
    <td>
      <@s.label name="memberfund.use_num" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">冻结金额：</td>
    <td>
    <@s.label name=" memberfund.freeze_num" cssClass="txtinput"/>
   </td>
  </tr>

  <tr>
    <td colspan="4" class="subbut">
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
