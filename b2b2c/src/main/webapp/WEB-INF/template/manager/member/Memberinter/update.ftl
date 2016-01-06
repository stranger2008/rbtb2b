<html>
<head>
<title>积分管理</title>
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
       <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:交易管理>我的资金>积分管理</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">总积分：</td>
    <td width="83%">
     <@s.label name="memberinter.fund_num" cssClass="txtinput"/>
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
     <input type="button" name="returnList" value="查看积分流" class="sub" onclick="document.forms[0].action='/member_Interhistory_list.action';document.forms[0].submit();"/>
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
