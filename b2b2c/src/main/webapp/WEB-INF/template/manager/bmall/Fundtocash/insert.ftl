<html>
<head>
<title>资金提现申请</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">资金提现申请</a><span>
    </div>
<@s.form action="/bmall_Fundtocash_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的资金</h2></div>
     <div class="base_infor">
       <h2 class="base_title">资金提现申请</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td class="firsttd">金额<font color="red">*</font></td><td>
        	<@s.textfield name="fundtocash.fund_num"  maxLength="8"/>
            <@s.fielderror><@s.param>fundtocash.fund_num</@s.param></@s.fielderror>
            </td></tr>
              
            <tr><td class="firsttd">收款方式<font color="red">*</font></td>
        	 <td>
             <@s.select name="fundtocash.getcash_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>fundtocash.getcash_type</@s.param></@s.fielderror>          	           	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">收款账号<font color="red">*</font></td><td>
        	<@s.textfield name="fundtocash.account" cssStyle="width:200px;" maxLength="20"/>
            <@s.fielderror><@s.param>fundtocash.account</@s.param></@s.fielderror>(现金支付除外，必填)
            </td></tr>  
            
            
            <tr><td class="firsttd">账号姓名<font color="red">*</font></td><td>
        	<@s.textfield name="fundtocash.account_name" cssStyle="width:200px;" maxLength="20"/>
            <@s.fielderror><@s.param>fundtocash.account_name</@s.param></@s.fielderror>(现金支付除外，必填)
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundtocash_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

