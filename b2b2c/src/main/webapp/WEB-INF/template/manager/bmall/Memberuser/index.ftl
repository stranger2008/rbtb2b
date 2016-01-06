 <html>
<head>
<title>账户信息</title>
</head>
<body>
<@s.form action="/bmall_Goodseval_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">账户管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">账户信息</td>
       </span></h2>
       <div class="rosearch">
         <input type="text" name="" value="商品名称，商品编号，订单编号" class="srtext">
         <input type="button" name="" value="" class="sbut">
       </div>
       
      <table cellspacing="0" class="bmall_list_table">
         <tr>
         
	     <td class="fthstyle1">用户名</td>
    
     	 <td class="fthstyle1">角色</td>
     	 
     	 <td class="fthstyle1">真实姓名</td>
    
     	 <td class="fthstyle1">性别</td>
    
     	 <td class="fthstyle1">部门</td>
    
     	 <td class="fthstyle1">职位</td>
    
     	 <td class="fthstyle1">手机号</td>
     	 
     	 <td class="fthstyle1">操作</td>
         <tr>
         <#list memberuserList as memberuser>
      <tr>   
 	
    	<td align="center">${memberuser.user_name?if_exists}</td>
    	
    	<td align="center">
    	<#if memberuser.role_code?if_exists=='1'>好评</#if>
    	<#if memberuser.role_code?if_exists=='0'>中评</#if>
    	<#if memberuser.role_code?if_exists=='-1'>差评</#if>
    	</td>
    
    	<td align="center">${memberuser.real_name?if_exists}</td>
    
    	<td align="center">${memberuser.sex?if_exists}</td>
    
        <td align="center">${memberuser.org_name?if_exists}</td>
    
    	<td align="center">${memberuser.career?if_exists}</td>
    	
    	<td align="center">${memberuser.cellphone?if_exists}</td>
    	
    <td align="center">
    <a onclick="linkToInfo('/bmall_Memberuser_view.action','memberuser.user_id=${memberuser.user_id?if_exists}');">查看</a>
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

