<html>
<head>
<title>收货地址修改</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
</script> 
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">账号充值</a><span>></span><a href="#">查看记录</a>
    </div>
<@s.form action="/bmall_Fundrecharge_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>账号充值</h2></div>
     <div class="base_infor">
       <h2 class="base_title">查看记录</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">充值金额：</td><td>
        	 <@s.label name="fundrecharge.fund_num" cssClass="txtinput"/>
            </td></tr>
              
            <tr><td  class="firsttd">支付平台：</td>
        	 <td>
                 <@s.label name="fundrecharge.payplat" cssClass="txtinput"/>         	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">支付时间：</td><td>
        	<@s.label name="fundrecharge.pay_date" cssClass="txtinput"/>
            </td></tr>  
            
             <tr><td  class="firsttd">审核状态：</td><td>
        	<#if fundrecharge.order_state?if_exists=='0'>未审核
		    <#elseif fundrecharge.order_state?if_exists=='1'>已审核
		    <#elseif fundrecharge.order_state?if_exists=='2'>作废</#if>
            </td></tr> 
            
             <tr><td  class="firsttd">备注：</td><td>
        	 <@s.label name="fundrecharge.remark" cssClass="txtinput"/> 
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
	             <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundrecharge_list.action','');"/>
	             <@s.hidden name="buyeraddr.addr_id"/>
	         </td>
	             
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

