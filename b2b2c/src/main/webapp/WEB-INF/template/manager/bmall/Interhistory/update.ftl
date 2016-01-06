<html>
<head>
<title>资金兑换积分</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">资金兑换积分</a><span>></span><a href="#">收货地址修改</a>
    </div>
<@s.form action="/bmall_Interhistory_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的资金</h2></div>
     <div class="base_infor">
       <h2 class="base_title">资金兑换积分</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">您目前可用金额:</td><td>
        	 <@s.label id="use_fund" name="use_fund"  cssClass="txtinput"/>元
            </td></tr>
              
            <tr><td  class="firsttd">剩余的积分数:</td>
        	 <td>
                 <@s.label id="integer_num" name="integer_num"  cssClass="txtinput"/>         	           	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">兑换规则:</td><td>
        	&nbsp;<span class="mustfield">1</span>&nbsp;个虚拟币兑换&nbsp;<span class="mustfield">${rech_value?if_exists}</span>&nbsp;个积分数
            </td></tr>  
            
             <tr><td  class="firsttd">兑换金额<font color="red">*</font></td><td>
        	 <@s.textfield name="rech_fund"   maxlength="8"/>
             <@s.fielderror><@s.param>rech_fund</@s.param></@s.fielderror>
             </td></tr> 
            
             <tr><td  class="firsttd">支付密码<font color="red">*</font></td><td>
        	 <input type="password" name="password" maxlength="8">
             <@s.fielderror><@s.param>password</@s.param></@s.fielderror>
            </td></tr> 
            
            <tr>
             <td colspan="2" align="center">
	         <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	         ${listSearchHiddenField?if_exists}
	         <@s.submit value="兑换" cssClass="submitbut"/>
	         </td> 
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

