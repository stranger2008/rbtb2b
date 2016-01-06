 <html>
<head>
<title>资金提取管理</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">资金提现</a>
    </div>
<@s.form action="/bmall_Fundtocash_list.action" method="post">
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
         <input type="button" value="申请提现" class="submitbut" onclick="linkToInfo('/bmall_Fundtocash_add.action','');"/>
         <input type="button" value="删除" class="submitbut"  onclick="delInfo('fundtocash.trade_id','/bmall_Fundtocash_delete.action')"/>
        </div>
       </div>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr align="center">
         <td width="3%" class="fthstyle1"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundtocash.trade_id')"/></td>
         <td class="fthstyle1" width="10%">提现金额</td>
         
	     <td class="fthstyle1" width="15%">收款方式</td>
    
     	 <td class="fthstyle1" width="20%">收款账号</td>
     	 
     	 <td class="fthstyle1" width="15%">账号姓名</td>
    
     	 <td class="fthstyle1" width="10%">状态</td>
    
     	 <td class="fthstyle1" width="10%">申请时间</td>
     	 
     	 <td class="fthstyle1" width="10%">查看</td>
         </tr>
         <#list fundtocashList as fundtocash >
      <tr align="center"> 
        
 	    <td><input type="checkbox" name="fundtocash.trade_id" value="${fundtocash.trade_id?if_exists}"/></td>
 	
    	<td>${fundtocash.fund_num?if_exists}</td>
    
    	<td><a onclick="linkToInfo('/member_Fundtocash_list.action','getcash_type_s=${fundtocash.getcash_type?if_exists}');">${fundtocash.model_value?if_exists}</a></td>
    
    	<td>${fundtocash.account?if_exists}</td>
    
    	<td>${fundtocash.account_name?if_exists}</td>
    	
    	<td><a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');">
        <#if fundtocash.order_state?if_exists=='0'>
        <font color='red'>未审核</font></a>
        </#if><#if fundtocash.order_state?if_exists=='1'><font color='blue'>已审核</font></a></#if>
	    <#if fundtocash.order_state?if_exists=='2'><font color='green'>已处理</font></a></#if>
	    <#if fundtocash.order_state?if_exists=='3'><font color='green'>作废</font></a></#if>
	    </a></td>
    	
    	<td>${fundtocash.in_date?if_exists}</td>
    	
    <td>
    <a onclick="linkToInfo('/bmall_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">查看</a>
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

