<html>
  <head>
    <title>提现申请审核</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金提现记录 > 提现申请审核
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Fundtocash_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">  
           <tr>
             <td class="table_name">会员名称：</td>
             <td>
             	 <@s.label name="scust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">金额:</td>
             <td>
             	<@s.label name="fundtocash.fund_num" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">收款方式:</td>
             <td>  
             	<@s.label name="sgetcash_type" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">收款账号：</td>
             <td>  
             	<@s.label name="fundtocash.account" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">账号姓名：</td>
             <td>  
             	<@s.label name="fundtocash.account_name" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">申请时间：</td>
             <td>
             	<@s.label name="fundtocash.in_date" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">操作人：</td>
             <td>
             	<@s.label name="username" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">状态<font color='red'>*</font></td>
             <td>
             	<@s.radio name="fundtocash.order_state" list=r"#{'0':'未审核','1':'已审核','2':'已处理','3':'作废'}"/>
             </td>
           </tr>
               <tr>
             <td class="table_name">备注<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="fundtocash.remark" cssClass="txtinput" maxLength="200" cssStyle="width:280px;height:80px;"
             	 onkeyup="set_textarea_length(this,100);"/>
                <@s.fielderror><@s.param>fundtocash.remark</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       <@s.hidden name="sgetcash_type"/>
	       <@s.hidden name="fundtocash.account_name"/>
	       <@s.hidden name="fundtocash.account"/>
	       <@s.hidden name="fundtocash.fund_num"/>
	       <@s.hidden name="fundtocash.cust_id"/>
	       <@s.hidden name="fundtocash.trade_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundtocash_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>