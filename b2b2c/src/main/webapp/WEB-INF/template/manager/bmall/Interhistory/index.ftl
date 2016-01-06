 <html>
<head>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
<title>积分管理</title>
</head>
<body>
<div class="postion">
 	<a href="#">我是卖家</a><span>></span><a href="#">交易管理</a><span>></span><a href="#">积分管理</a>
	</div>
<@s.form action="/bmall_Interhistory_bmalllist.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">交易管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">积分管理</td>
       </span></h2>
      <table width="100%">
       		<tr>
					<td align="right">操作人:</td> 
					<td><@s.textfield name="user_name_s"  maxLength="50"/></td>
					<td align="right">操作时间段:</td> 
					<td>
						<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 						
					至						
						<@s.textfield id="txtendDate" name="enddateString" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>
					<td rowspan="2" width="25%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
       	</table>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
         <td class="fthstyle1" width="5%"></td>
         <td class="fthstyle1" width="10%">操作人</td>
	     <td class="fthstyle1" width="10%">添加</td>
     	 <td class="fthstyle1" width="10%">减少</td>
     	 <td class="fthstyle1" width="15%">余下积分</td>
     	 <td class="fthstyle1" width="20%">事由</td>
     	 <td class="fthstyle1" width="20%">备注</td>
     	 <td class="fthstyle1" width="10%">操作时间</td>
         <tr>
         <#list interhistoryList as indexhistory>
      <tr>   
 	    <td></td>
 	    <td>${indexhistory.user_name?if_exists}</td>
 	    
    	<td>${indexhistory.inter_in?if_exists}</td>
    
    	<td>${indexhistory.inter_out?if_exists}</td>
       
        <td>${indexhistory.thisinter?if_exists}</td>
    
        <td>${indexhistory.reason?if_exists}</td>
    
    	<td>${indexhistory.remark?if_exists}</td>
    	
    	<td>${indexhistory.in_date?if_exists}</td>
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

