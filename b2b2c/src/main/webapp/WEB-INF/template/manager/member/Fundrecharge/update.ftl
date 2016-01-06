<html>
<head>
<title>查看账户充值信息</title>
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>账户充值>查看账户充值信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">充值金额:</td>
    <td width="83%">
    <@s.label name="fundrecharge.fund_num" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">支付平台:</td>
    <td>
    	<@s.label name="fundrecharge.payplat" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">支付时间：</td>
    <td>
<@s.label name="fundrecharge.pay_date" cssClass="txtinput"/>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">审核状态:</td>
    <td>
    <#if fundrecharge.order_state?if_exists=='0'>未审核
    <#elseif fundrecharge.order_state?if_exists=='1'>已审核
    <#elseif fundrecharge.order_state?if_exists=='2'>作废</#if>
   </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >备注:</td>
    <td>
     <@s.label name="fundrecharge.remark" cssClass="txtinput"/> 
    </td>
  </tr>

  <tr>
    <td colspan="4" class="subbut">
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/member_Fundrecharge_list.action','');" class="sub" />
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
