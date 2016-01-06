 <html>
<head>
<title>资金流水列表</title>
</head>
<body>
<@s.form action="/bmall_Fundhistory_list.action">
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">资金流水</a>
    </div>
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">账号管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的资金</td>
       </span></h2>
      
       
       <table cellspacing="0" class="bmall_list_table">
         <tr align="center">
         <td class="fthstyle1" width="10%">收入</td>
         
	     <td class="fthstyle1" width="10%">支出</td>
    
     	 <td class="fthstyle1" width="10%">余额</td>
     	 
     	 <td class="fthstyle1" width="10%">操作人</td>
    
     	 <td class="fthstyle1" width="30%">事由</td>
    
     	 <td class="fthstyle1" width="20%">备注</td>
     	 
     	 <td class="fthstyle1" width="15%">操作时间</td>
         </tr>
        <#list fundhistoryList as fundhistory>
      <tr align="center"> 
    	<td>${fundhistory.fund_in?if_exists}</td>
    
    	<td>${fundhistory.fund_out?if_exists}</td>
    
    	<td>${fundhistory.balance?if_exists}</td>
    
    	<td>${fundhistory.user_name?if_exists}</td>
    	
    	<td>${fundhistory.reason?if_exists}</td>
    	
    	<td>${fundhistory.remark?if_exists}</td>
    	
    	<td>${fundhistory.in_date?if_exists}</td>
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

