<html>
  <head>
    <title>会员充值审核</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金充值记录表 > 会员充值审核
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Fundrecharge_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">  
           <tr>
             <td class="table_name">会员名称：</td>
             <td>
             	 <@s.label name="cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">充值金额:</td>
             <td>
             	<@s.label name="fundrecharge.fund_num" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">支付平台:</td>
             <td>  
             	<@s.label name="fundrecharge.payplat" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">支付时间</td>
             <td>  
             	<@s.label name="fundrecharge.pay_date" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.radio name="fundrecharge.order_state" list=r"#{'0':'未审核','1':'审核','2':'作废'}"/>
             </td>
           </tr>
              <tr>
             <td class="table_name">备注：</font></td>
             <td colspan="3">
             	<@s.textarea name="fundrecharge.remark" id="remark" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'remark');  
				</script>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="fundrecharge.trade_id"/>
	       <@s.hidden name="fundrecharge.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fundrecharge_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>