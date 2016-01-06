<html>
  <head>
    <title>会员充值</title>
  </head>
  <body>


<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金充值记录表 > 会员充值
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Fundrecharge_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">  
           <tr>
             <td class="table_name">会员名称：</td>
             <td>
             	 <@s.label name="fundrecharge.cust_id" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值金额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fundrecharge.fund_num" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td valign="middle" class="left_td">支付平台<span class="mustfield">*</span></td>
            <td>
             <@s.select name="fundrecharge.payplat" list="paymentList"  headerKey="0" listValue="pay_name" listKey="pay_code" headerValue="请选择"/>  
             <@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>
            </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="key_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundrecharge_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>