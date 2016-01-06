<html>
  <head>
    <title>查看订单商品</title>
    <style type="text/css">
    	.table_name{
    		width:280px;
    	}
    </style>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 订单管理 > 查看订单
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsorder_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2" ><h2>商品信息</h2></td></tr>		   
		           <tr>
		             <td class="table_name">订单号</td>
		             <td>
		               	 ${goodsorder.order_id?if_exists}
		             </td>
		           </tr>
	    	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>购买人信息</h2></td></tr>		       
		           <tr>
		             <td class="table_name">买家名称</td>
		             <td>
		              ${buyer.cust_name?if_exists}
		             </td>
		           </tr>
	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>卖家信息</h2></td></tr>		     
		           <tr>
		             <td class="table_name">卖家名称</td>
		             <td>
		              ${saler.cust_name?if_exists}
		             </td>
		           </tr>
		    </table>
	        <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2" ><h2>商品信息</h2></td></tr>		     
		           <tr>
		             <td class="table_name">收货人姓名</td>
		             <td>
		             ${goodsorder.consignee?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收货人地区</td>
		             <td>
		              ${area_name?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收货人地址</td>
		             <td>
		              ${goodsorder.address?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">邮编</td>
		             <td>
		              ${goodsorder.zip_code?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">电话</td>
		             <td>
		               ${goodsorder.telephone?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">移动电话</td>
		             <td>
			              ${goodsorder.mobile?if_exists}
		             </td>
		           </tr>
	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>商品价格</h2></td></tr>		          
		           <tr>
		             <td class="table_name">商品金额</td>
		             <td>
		             	 ${goodsorder.goods_amount?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">运费</td>
		             <td>
			             ${goodsorder.ship_free?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票税额</td>
		             <td>
		            	${goodsorder.tax_invoice?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠折扣</td>
		             <td>
			             ${goodsorder.discount?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠金额</td>
		             <td>
		              ${goodsorder.discount_money?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">优惠说明</td>
		             <td>
		             ${goodsorder.dis_explain?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保价费用</td>
		             <td>
			             ${goodsorder.insured?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">订单总金额</td>
		             <td>
		             	${goodsorder.tatal_amount?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">使用积分</td>
		             <td>
			             ${goodsorder.inter_money?if_exists}
		             </td>
		           </tr>
	    	    </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>订单其它信息</h2></td></tr>	       
		           <tr>
		             <td class="table_name">商品购买方式</td>
		             <td>
			             <#if goodsorder.buy_mode=='0'>
			             	正常购买
			             <#elseif goodsorder.buy_mode=='1'>
			             	团购
			             <#elseif goodsorder.buy_mode=='2'>
			             	秒杀
			             </#if>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款方式名称</td>
		             <td>
		            	 ${payName?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">配送方式名称</td>
		             <td>
			              ${sendModeName?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">订单状态</td>
		             <td>
			              ${orderStateName?if_exists}

		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款状态</td>
		             <td>
		             	  ${payStateName?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发货状态</td>
		             <td>
		                  ${sendStateName?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">下单时间</td>
		             <td>
		             ${goodsorder.order_time?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">付款时间</td>
		             <td>
		             ${goodsorder.pay_time?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发货时间</td>
		             <td>
			             ${goodsorder.send_time?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">会员留言</td>
		             <td>
			             ${goodsorder.mem_remark?if_exists}
		             </td>
		           </tr>
	  </table>
	     <table class="wwtable" cellspacing="1" cellpadding="8">       
        	<tr><td colspan="2"><h2>发票信息</h2></td></tr>	             
		           <tr>
		             <td class="table_name">发票类型</td>
		             <td>
			             <#if goodsorder.invoice_type='0'>
			             	  	普通发票
			             <#elseif goodsorder.invoice_type='1'>
			             		增值税发票
			             </#if>		             	 
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票抬头</td>
		             <td>
		             ${goodsorder.invoice_top?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">单位名称</td>
		             <td>
		              ${goodsorder.company_name?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">纳税人识别号</td>
		             <td>
		              ${goodsorder.ident_number?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">注册地址</td>
		             <td>
		               ${goodsorder.regis_address?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">注册电话</td>
		             <td>
		              ${goodsorder.regis_tel?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开户银行</td>
		             <td>
		              ${goodsorder.open_bank?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">银行帐户</td>
		             <td>
		             ${goodsorder.bank_account?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发票内容</td>
		             <td>
		              ${goodsorder.invoice_content?if_exists}
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

 