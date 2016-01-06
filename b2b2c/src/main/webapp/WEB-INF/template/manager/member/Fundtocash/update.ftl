<html>
<head>
<title>查看资金提现</title>
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>资金提取>查看资金提现</td>
 	</tr>
	</table>
	
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">金额:</td>
    <td width="83%">
    <@s.label name="fundtocash.fund_num" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">收款方式:</td>
    <td>
    	<@s.label name="sgetcash_type" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">收款账号：</td>
    <td>
     <@s.label name="fundtocash.account" cssClass="txtinput"/>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">账号姓名：</td>
    <td>
 <@s.label name="fundtocash.account_name" cssClass="txtinput"/>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">申请时间：</td>
    <td>
    <#if fundtocash.in_date?if_exists>
    	${(fundtocash.in_date)[0..18]}
    </#if>
   </td>
  </tr>
 
  <tr>
    <td valign="middle" class="left_td">状态:</td>
    <td>
    <#if fundtocash.order_state?if_exists=='0'>未审核
    <#elseif fundtocash.order_state?if_exists=='1'>已审核
    <#elseif fundtocash.order_state?if_exists=='2'>已处理
    <#elseif fundtocash.order_state?if_exists=='3'>作废</#if>
   </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >备注:</td>
    <td>
     <@s.label name="fundtocash.remark" cssClass="txtinput"/>
    </td>
  </tr>

  <tr>
    <td colspan="4" class="subbut">
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	      <@s.hidden name="sgetcash_type"/>
	       <@s.hidden name="fundtocash.account_name"/>
	       <@s.hidden name="fundtocash.account"/>
	       <@s.hidden name="fundtocash.fund_num"/>
	       <@s.hidden name="fundtocash.cust_id"/>
	       <@s.hidden name="fundtocash.trade_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	        <input type="button" name="returnList" value="返回列表" class="sub" 
	        	 onclick="linkToInfo('/member_Fundtocash_list.action','');"/>
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
