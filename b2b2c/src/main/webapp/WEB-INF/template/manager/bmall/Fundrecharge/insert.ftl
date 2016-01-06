<html>
<head>
<title>账号充值</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">账号充值</a><span>></span><a href="#">添加收货地址</a>
    </div>
<@s.form action="/bmall_Fundrecharge_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的资金</h2></div>
     <div class="base_infor">
       <h2 class="base_title">账号充值</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td class="firsttd">充值金额<font color="red">*</font></td><td>
        	<@s.textfield name="fundrecharge.fund_num" value="1" maxLength="20"/>元
            <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
            </td></tr>
              
            <tr><td class="firsttd">支付平台<font color="red">*</font></td>
        	 <td>
             <@s.select name="fundrecharge.payplat" list="paymentList"  headerKey="0" listValue="pay_name" listKey="pay_code" headerValue="请选择"/>  
             <@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>        	           	            
             </td>
            </tr>  

            <tr>
             <td colspan="2" align="center">
             <@s.submit value="提  交" cssClass="submitbut"/>
		     <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundrecharge_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

