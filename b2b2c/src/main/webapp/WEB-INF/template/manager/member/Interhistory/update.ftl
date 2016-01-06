<html>
  <head>
    <title>兑换积分</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<#include "/include/uploadInc.html">
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Interhistory_update.action" method="post" validate="true">
   	 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>兑换积分</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">您目前可用金额:</td>
             <td>
               <@s.label id="use_fund" name="use_fund"  cssClass="txtinput"/>元
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">剩余的积分数:</td>
             <td>
               <@s.label id="integer_num" name="integer_num"  cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">兑换规则:</td>
             <td>
               &nbsp;<span class="mustfield">1</span>&nbsp;个虚拟币兑换&nbsp;<span class="mustfield">${rech_value?if_exists}</span>&nbsp;个积分数
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">兑换金额<span class="mustfield">*</span></td>
             <td>
              <@s.textfield name="rech_fund" cssClass="txtinput"  maxlength="8"/>
              <@s.fielderror><@s.param>rech_fund</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">支付密码<span class="mustfield">*</span></td>
             <td>
             <input type="password" name="password" maxlength="8">
              <@s.fielderror><@s.param>password</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="兑换" cssClass="sub"/>
             </td>
           </tr>
         </table>
	  </@s.form>
</div>

</div>
<div class="clear"></div>
  </body>
</html>