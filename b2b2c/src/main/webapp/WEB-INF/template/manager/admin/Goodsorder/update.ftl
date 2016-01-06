<html>
  <head>
    <title>修改订单商品表</title>
    <style type="text/css">
    	.table_name{
    		width:280px;
    	}
    </style>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsorder_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2" ><h2>商品信息</h2></td></tr>		   
		           <tr>
		             <td class="table_name">订单号<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.order_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.order_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	    	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>购买人信息</h2></td></tr>		       
		           <tr>
		             <td class="table_name">买家名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.buy_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.buy_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>卖家信息</h2></td></tr>		     
		           <tr>
		             <td class="table_name">卖家名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.sale_cust_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.sale_cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
		    </table>
	        <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2" ><h2>商品信息</h2></td></tr>		     
		           <tr>
		             <td class="table_name">收货人姓名<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.consignee" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.consignee</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收货人地区<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.area_attr" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.area_attr</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收货人地址<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.address" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.address</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">邮编<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.zip_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.zip_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">电话<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.telephone" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.telephone</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">移动电话<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.mobile" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.mobile</@s.param></@s.fielderror>
		             </td>
		           </tr>
	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>商品价格</h2></td></tr>		          
		           <tr>
		             <td class="table_name">商品金额<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.goods_amount" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.goods_amount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">运费<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.ship_free" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.ship_free</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票税额<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.tax_invoice" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.tax_invoice</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠折扣<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.discount" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.discount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠金额<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.discount_money" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.discount_money</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠说明<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.dis_explain" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.dis_explain</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保价费用<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.insured" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.insured</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">订单总金额<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.tatal_amount" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.tatal_amount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">使用积分<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.inter_money" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.inter_money</@s.param></@s.fielderror>
		             </td>
		           </tr>
	    	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>订单其它信息</h2></td></tr>	       
		           <tr>
		             <td class="table_name">商品购买方式<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.buy_mode" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.buy_mode</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款方式标识<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.pay_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.pay_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">配送方式标识<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.send_mode" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.send_mode</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">订单状态<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.order_state" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.order_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款状态<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.pay_state" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.pay_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发货状态<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.send_state" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.send_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">下单时间<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.order_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.order_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款时间<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.pay_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.pay_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发货时间<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.send_time" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.send_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">会员留言<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.mem_remark" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.mem_remark</@s.param></@s.fielderror>
		             </td>
		           </tr>
	  </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>发票信息</h2></td></tr>	             
		           <tr>
		             <td class="table_name">发票类型<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.invoice_type" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.invoice_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票抬头<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.invoice_top" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.invoice_top</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">单位名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.company_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.company_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">纳税人识别号<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.ident_number" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.ident_number</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">注册地址<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.regis_address" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.regis_address</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">注册电话<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.regis_tel" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.regis_tel</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开户银行<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.open_bank" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.open_bank</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">银行帐户<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.bank_account" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.bank_account</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsorder.invoice_content" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsorder.invoice_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsorder_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

