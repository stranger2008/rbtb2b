<html>
  <head>
    <title>会员资金变动</title>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 资金管理 > 会员资金变动
 </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Memberfund_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">会员名称:</td>
             <td>
             	 <@s.label name="cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">类型<font color='red'>*</font></td>
             <td>
             	<@s.radio name="radiochecked" list=r"#{'0':'收入','1':'支出'}" value="0"/>
             </td>
           </tr>   <tr>
             <td class="table_name">金额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fund_num" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">事由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="reason" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>reason</@s.param></@s.fielderror>
             </td>
           </tr>
              <tr>
             <td class="table_name">备注：</td>
             <td>
             	<@s.textfield name="remark" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>remark</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">注意<font color='red'>*</font></td>
             <td>
             	此表单一经提交，将不可以再修改或删除，请务必谨慎操作
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="memberfund.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberfund_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>