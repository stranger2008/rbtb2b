 <html>
<head>
<title>账户充值</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">账号充值</a>
    </div>
<@s.form action="/bmall_Fundrecharge_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">账号管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的资金</td>
       </span></h2>
       <div class="rosearch">
        <div align="right">
         <input type="button" value="账号充值" class="submitbut" onclick="linkToInfo('/bmall_Fundrecharge_add.action','');"/>
         <input type="button" value="删除" class="submitbut"  onclick="delInfo('fundrecharge.trade_id','/member_Fundrecharge_delete.action')"/>
        </div>
       </div>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
         <td width="6%" class="fthstyle1"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundrecharge.trade_id')"/></td>
         <td class="fthstyle1" width="20%">充值金额</td>
         
	     <td class="fthstyle1" width="20%">支付平台</td>
    
     	 <td class="fthstyle1" width="20%">支付时间</td>
     	 
     	 <td class="fthstyle1" width="20%">状态</td>

     	 <td class="fthstyle1" width="14%">查看</td>
         <tr>
         <#list fundrechargeList as fundrecharge>
      <tr> 
        
 	    <td width="6%" ><input type="checkbox" name="fundrecharge.trade_id" value="${fundrecharge.trade_id?if_exists}"/></td>
 	
    	<td>${fundrecharge.fund_num?if_exists}</td>
    
    	<td>${fundrecharge.payplat?if_exists}</td>
    
    	<td>${fundrecharge.pay_date?if_exists}</td>
    
    	<td>
           <a onclick="linkToInfo('/bmall_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');">
		    <#if fundrecharge.order_state?if_exists=='0'>
		    <font color='red'>未审核</font></a>
		    </#if>
		    <#if fundrecharge.order_state?if_exists=='1'>
		    <font color='blue'>审核</font></a>
		    </#if>
		    <#if fundrecharge.order_state?if_exists=='2'>
		    <font color='green'>作废</font></a>
		    </#if>
		    </a>
        </td>
    <td>
    <a onclick="linkToInfo('/bmall_Fundrecharge_view.action','fundrecharge.trade_id=${fundrecharge.trade_id?if_exists}');">查看</a>
    </td>
  </tr>
  </#list>
       </table>
        <div class="listbottom">
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

