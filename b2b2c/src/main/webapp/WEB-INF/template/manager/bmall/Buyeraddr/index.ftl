 <html>
<head>
<title>收货地址列表</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">收货地址</a>
    </div>
<@s.form action="/bmall_Buyeraddr_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">账号管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">收货地址</td>
       </span></h2>
       <div class="rosearch">
        <div align="right">
         <input type="button" value="添加地址" class="submitbut" onclick="linkToInfo('/bmall_Buyeraddr_add.action','');"/>
         <input type="button" value="删除" class="submitbut"  onclick="delInfo('buyeraddr.addr_id','/bmall_Buyeraddr_delete.action')"/>
        </div>
       </div>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
         <td width="6%" class="fthstyle1"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buyeraddr.addr_id')"/></td>
         <td class="fthstyle1">收货人姓名</td>
         
	     <td class="fthstyle1">地区</td>
    
     	 <td class="fthstyle1">详细地址</td>
     	 
     	 <td class="fthstyle1">邮编</td>
    
     	 <td class="fthstyle1">联系方式</td>
    
     	 <td class="fthstyle1">发布日期</td>
     	 
     	 <td class="fthstyle1">操作</td>
         <tr>
         <#list buyeraddrList as buyeraddr>
      <tr> 
        
 	    <td width="6%" align="center"><input type="checkbox" name="buyeraddr.addr_id" value="${buyeraddr.addr_id?if_exists}"/></td>
 	
    	<td align="center">${buyeraddr.cust_name?if_exists}</td>
    
    	<td align="center">${buyeraddr.area_attr?if_exists}</td>
    
    	<td align="center">${buyeraddr.address?if_exists}</td>
    
    	<td align="center">${buyeraddr.post_code?if_exists}</td>
    	
    	<td align="center">
    	<#if (buyeraddr.cell_phone?if_exists) && (buyeraddr.phone?if_exists)>
    	${buyeraddr.phone?if_exists}/${buyeraddr.cell_phone?if_exists}
    	<#elseif buyeraddr.phone?if_exists>
    	${buyeraddr.phone?if_exists}
    	<#else>
    	${buyeraddr.cell_phone?if_exists}
    	</#if>
    	</td>
    	
    	<td align="center">${buyeraddr.in_date?if_exists}</td>
    	
    <td align="center">
    <a onclick="linkToInfo('/bmall_Buyeraddr_view.action','buyeraddr.addr_id=${buyeraddr.addr_id?if_exists}');">修改</a>
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

