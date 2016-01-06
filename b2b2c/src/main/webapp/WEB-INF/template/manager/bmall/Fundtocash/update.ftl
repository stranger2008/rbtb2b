<html>
<head>
<title>查看资金提现</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">资金提现</a><span>></span><a href="#">查看资金提现</a>
    </div>
<@s.form action="/bmall_Fundtocash_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的资金</h2></div>
     <div class="base_infor">
       <h2 class="base_title">查看资金提现</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">金额：</td><td>
        	<@s.label name="fundtocash.fund_num" cssClass="txtinput"/>
            </td></tr>
              
            <tr><td  class="firsttd">收款方式：</td>
        	 <td>
                 <@s.label name="sgetcash_type" cssClass="txtinput"/>        	           	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">收款账号：</td><td>
        	 <@s.label name="fundtocash.account" cssClass="txtinput"/>
            </td></tr>  
            
            <tr><td  class="firsttd">账号姓名：</td><td>
        	<@s.label name="fundtocash.account_name" cssClass="txtinput"/>
            </td></tr> 
            
            <tr><td  class="firsttd">申请时间：</td><td>
        	<@s.label name="fundtocash.in_date" cssClass="txtinput"/>
            </td></tr> 
            
            <tr><td  class="firsttd">状态：</td><td>
        	<#if fundtocash.order_state?if_exists=='0'>未审核
		    <#elseif fundtocash.order_state?if_exists=='1'>已审核
		    <#elseif fundtocash.order_state?if_exists=='2'>已处理
		    <#elseif fundtocash.order_state?if_exists=='3'>作废</#if>
            </td></tr> 
            
            <tr>
			    <td class="firsttd" >备注：</td>
			    <td>
			     <@s.label name="fundtocash.remark" cssClass="txtinput"/>
			    </td>
		    </tr>

            <tr>
             <td colspan="2" align="center">
	             <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundtocash_list.action','');"/>
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

