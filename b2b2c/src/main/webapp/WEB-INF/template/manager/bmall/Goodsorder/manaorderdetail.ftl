<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>商品订单详情</title>
<link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />
</head>
<body>
<div class="postion">
	<a href="#">我是卖家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的订单</a><span>></span>订单详情
</div>
<div class="rightside f_right">
    <div class="rpostion"><h2>订单信息</h2></div>
	<div>
		<table width='100%'>		
			<tr><td colspan='2'>
				 <div class="saletitle2" >
				   <table width="100%">
				   <tr><td  class='fdwold' width='60'>收货地址:</td>
				   <td class='coe_td'>${goodsorder.consignee?if_exists} ，${goodsorder.mobile?if_exists}，
						${goodsorder.telephone?if_exists} ，${area_name?if_exists}${goodsorder.address?if_exists} ，${goodsorder.zip_code?if_exists}</td></tr>					  	   
				   </table>			 	
				 </div>
			</td></tr>		
			
			<tr><td colspan='2'>
				 <div class="saletitle2" >
				   <table width="100%">
				   <tr><td  class='fdwold' width='60'>买家留言:</td>
				   <td class='coe_td'>${goodsorder.mem_remark?if_exists}</td></tr>					  	   
				   </table>			 	
				 </div>
			</td></tr>				
			<#if web_openmall?if_exists==0>
					<tr><td colspan='2'>
						 <div class="saletitle1" >
						   <table width="100%">
						   <tr><td  class='fdwold'>卖家信息:</td></tr>
						   <tr>
						   <td style="padding-left:60px;">
						   		<table width="100%">
						   			<tr>
							   			<td width='39%'><span>店铺名称:</span>${shopconfig.shop_name?if_exists}</td>
							   			<td  width='32%'><span>真实姓名:</span>${saleMember.contact_name?if_exists}</td>
							   			<td><span>所在城市:</span>${sale_area_String?if_exists}</td>
						   			</tr>
						   			<tr>
							   			<td><span>联系电话:</span>${saleMember.contact_cellphone?if_exists}</td>
							   			<td><span>邮 件:</span>${saleMember.email?if_exists}</td>
						   			</tr>
						   		</table>
						   </td>
						   </tr>			   
						   </table>			 	
						 </div>
						</td>
					</tr>
			</#if>			

			 <tr><td colspan='2'>
			 <div class="saletitle1" >
			   <table width="100%">
			   <tr><td class='fdwold'>其它信息:</td></tr>
			   <tr>
			   <td style="padding-left:60px;">
			   		<table width="100%">
			   			<tr>
				   			<td width='39%'><span>配送方式:</span>${sendModeName?if_exists} </td>
				   			<td  width='32%'><span>支付方式:</span>${payment.pay_name?if_exists} </td>
				   			<td><span>可得积分:</span>${goodsorder.give_inter?if_exists}  </td>
			   			</tr>
			   		</table>
			   </td>
			   </tr>			   
			   </table>
			 	
			 </div>
			 			 
			</td></tr>
			
			<tr><td colspan='2'>
				 <div class="saletitle1" >
				   <table width="100%">
				   <tr><td  class='fdwold'>发票信息:</td></tr>
				   <tr>
				   <td style="padding-left:60px;">
				   		<table width="100%">
				   			<tr>
					   			<td width='39%'><span>发票类型:</span>
						   			<#if goodsorder.invoice_type=='0'>
						   				普通发票
						   			<#elseif goodsorder.invoice_type=='1'>
						   				增值税发票
						   			<#elseif goodsorder.invoice_type=='2'>
						   			 	 无需发票
						   			</#if>
					   			</td>
					   			<#if goodsorder.invoice_type=='1'>
					   			<td  width='32%'><span>发票抬头:</span>${goodsorder.invoice_top?if_exists} </td>
					   			<td><span>单位名称:</span>${goodsorder.company_name?if_exists}  </td>
				   			</tr>
				   			<tr>
					   			<td><span>纳税人识别号:</span>${goodsorder.ident_number?if_exists} </td>
					   			<td><span>注册地址:</span>${goodsorder.regis_address?if_exists}</td>
					   			<td><span>注册电话:</span>${goodsorder.regis_tel?if_exists}</td>
				   			</tr>
				   			<tr>
					   			<td><span>开户银行:</span>${goodsorder.open_bank?if_exists} </td>
					   			<td><span>银行帐户:</span>${goodsorder.bank_account?if_exists}</td>
					   			<td></td>
				   			</tr>
				   			<tr>
				   			<td>
				   				<span>发票内容:</span>
				   				${goodsorder.invoice_content?if_exists}
				   			</td>
				   			</#if>
				   			</tr>
				   		</table>
				   </td>
				   </tr>			   
				   </table>
				 	
				 </div>
				 			 
				</td>
			 </tr>

			 
<@s.form id="upsubmit" action='/bmall_Goodsorder_upPrice.action'>
		 <script type="text/javascript">
		 	var uphtml="";
		 	function upchange(){
		 		var uphtml=$("#upspan").html();
		 		$("#upspan").html("<a onclick='uppost();'>[更新]</a>&nbsp;<a onclick='upcancle();'>[取消]</a>");
		 		//获取配送费用
		 		var ship=$("#ship_free").html();
		 		$("#ship_free").html("<input id='up_ship' value='"+ship+"' class='monwidth' onkeyup='set_textarea_length(this,8)'/>");
		 		var tax=$("#tax").html();
		 		$("#tax").html("<input id='up_tax'value='"+tax+"' class='monwidth' onkeyup='set_textarea_length(this,8)'/>");
		 		var insured=$("#insured").html();
		 		$("#insured").html("<input id='up_insured' value='"+insured+"' class='monwidth' onkeyup='set_textarea_length(this,8)'/>");	 				 		
		 	}
		 	
		 	function upcancle(){
		 		$("#upspan").html("<a onclick='upchange()'>[修改]</a>");
		 		$("#ship_free").html($("#up_ship").val());
		 		$("#tax").html($("#up_tax").val());
		 		$("#insured").html($("#up_insured").val());
		 	}
		 	
		 	//提交订单
		 	function uppost(){
		 		$("#hd_up_ship").val($("#up_ship").val());
		 		$("#hd_up_tax").val($("#up_tax").val());
		 		$("#hd_up_insured").val($("#up_insured").val());
		 		$("#upsubmit").submit();
		 	}
		 	
		 </script>
<@s.hidden name="oid"/>
<@s.hidden name="up_ship" id='hd_up_ship'/>
<@s.hidden name="up_tax"  id='hd_up_tax'/>
<@s.hidden name="up_insured"  id='hd_up_insured'/>
</@s.form> 				 
			 <tr><td colspan='2'>
			 <div class="saletitle1" >
			   <table width="100%" >
			   <tr><td style='padding-top:8px;'><span  class='fdwold'>其它费用:</span>
			   <span class='upspan' id="upspan"><a onclick='upchange()'>[修改]</a><span></td></tr>
			   <tr>
			   <td style="padding-left:60px;">
			   		<table width="100%">
			   			<tr>
				   			<td width='39%'><span>配送费用:</span><span id="ship_free">${goodsorder.ship_free?if_exists}</span></td>
				   			<td width='32%'><span>发票税额:</span><span id="tax">${goodsorder.tax_invoice?if_exists}</span></td>
				   			<td><span>保价费用:</span><span id="insured">${goodsorder.insured?if_exists}</span></td>
			   			</tr>
			   			<tr>
				   			<td><span>优惠折扣:</span>${goodsorder.discount?if_exists} </td>
				   			<td><span>优惠金额:</span>${goodsorder.discount_money?if_exists} </td>
				   			<td></td>
			   			</tr>
			   			<tr>
			   			<td colspan='3' class='exl'><span>优惠说明:</span>
			   			${goodsorder.dis_explain?if_exists} </td>
			   			</tr>
			   		</table>
			   </td>
			   </tr>			   
			   </table>			 	
			 </div>
			</td></tr>
		
			<tr><td colspan='2'>
			 <div class="saletitle1" >
			   <table width="100%">
			   <tr><td  class='fdwold'>订单状态:</td></tr>
			   <tr>
			   <td style="padding-left:60px;">
			   		<table width="100%">
			   			<tr>
				   			<td width='39%'><span>订单状态:</span>${orderStateName?if_exists} </td>
				   			<td  width='32%'><span>付款状态:</span>${payStateName?if_exists} </td>
				   			<td><span>发货状态:</span>${sendStateName?if_exists}  </td>
			   			</tr>
			   			<tr>
				   			<td><span>下单时间:</span>${goodsorder.order_time?if_exists} </td>
				   			<td><span>付款时间:</span>${goodsorder.pay_time?if_exists} </td>
				   			<td><span>发货时间:</span>${goodsorder.send_time?if_exists} </td>
			   			</tr>
			   		</table>
			   </td>
			   </tr>			   
			   </table>			 	
			 </div>
			</td></tr>
		</table>
	</div>
	
	<div class='goodsdetail3'>
	<div class='gdtitle1'>
		<table class='gdtd1' width="100%">
			<tr>
				<td width="346">商品名称</td>
				<td width="120">商品属性</td>
				<td width="80">单价(元)</td>
				<td width="50">数量</td>
				<td>商品总价</td>
			</tr>
		</table>
	</div>
	<div>
		<table class="gdtd2" width="100%" cellspacing="1" cellpadding="8" bgcolor="#DDDDDD">
		<#list detailList as detail>
			<tr bgcolor="#FFFFFF">
				<td width="346" align="left">
				  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
					      <table width='100%'>
					      	<tr >
					      		<td>
					      			<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
						      		<div class='gs_img'>
						      		    <#if detail.img_path!=''>
								  			<#if ((detail.img_path)?index_of(",") > (-1))>      			
								  				<#assign startpos = "${detail.img_path?if_exists}"?index_of(',')>
								                <#if ((startpos-1)>-1)>
								                    <#assign img =(detail.img_path)[(0)..(startpos-1)]>
								                 </#if>
								             <#else> 
								             		<#assign img =detail.img_path>
								             </#if> 
							      			<img src="${(img)?if_exists}" width='60' height='60'>
							      		 <#else>
							      			<img src="${(cfg_nopic)?if_exists}" width='60' height='60'>
							      		 </#if>	
						      		</div>
						      		</a>
					      		</td>
					      		<td   class='degn_td1'>
						      		<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
						      			${(detail.goods_name)?if_exists}
						      		</a>
					      		</td>
					      	</tr>
					      </table>				
				</#if>	 
				</td>
				<td width="110">${(detail.goods_attr)?if_exists}</td>
				<td width="50">${(detail.order_price)?if_exists}</td>
				<td width="50">${(detail.order_num)?if_exists}</td>
				<#if detail_index=='0'>
					<td rowspan="${detail?size}">${goodsorder.goods_amount?if_exists}</td>	
				</#if>		
			</tr>		
			</#list>
		</table>	
	</div>	
	</div>	
	<div class='f_right btmonut'>
			<br/>
			<span class='f_right'>
				<font class='f_size'>配送费用:</font><font class='f_size'>${goodsorder.ship_free?if_exists}</font>
				<font class='f_size'>+发票税额:</font><font class='f_size'>${goodsorder.tax_invoice?if_exists}</font>
				<font class='f_size'>+保价费用:</font><font class='f_size'>${goodsorder.insured?if_exists}<font>
			</span><br/><br/>
			<div class="clear"></div>
			<span style="float:right;"><font class='fb_size'>实付款:</font><font class='totalprice2'>${goodsorder.tatal_amount?if_exists}</font><font>元</font></span>			
	</div>
	<div class="clear"></div>
	<div class='return'>
		<div class='operorderstate'>
		<#if goodsorder.order_state=='2' || goodsorder.order_state=='3'>	
			    <@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="1"/>
					<@s.submit value="确认" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>
		<#if goodsorder.order_state=='0' && goodsorder.pay_state=='0'&& goodsorder.send_state=='0'>	
				<@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="1"/>
					<@s.submit value="确认" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="1"/>
					<@s.hidden name="order_pay_state" value="1"/>
					<@s.submit value="付款" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="3"/>
					<@s.submit value="无效" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="2"/>
					<@s.submit value="取消" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>	
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='0'&& goodsorder.send_state=='0'>	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="1"/>
					<@s.submit value="付款" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="3"/>
					<@s.submit value="无效" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subOrder.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_order_state" value="2"/>
					<@s.submit value="取消" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>	
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='0'>	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="0"/>
					<@s.submit value="未付款" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="4"/>
					<@s.submit value="退款申请" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="1"/>
					<@s.submit value="发货" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="3"/>
					<@s.submit value="配货" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	

		</#if>	
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='3'>	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="0"/>
					<@s.submit value="未付款" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="4"/>
					<@s.submit value="退款申请" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="1"/>
					<@s.submit value="发货" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='1'>	
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="5"/>
					<@s.submit value="退货申请" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="4"/>
					<@s.submit value="确认收货" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='5'>
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="6"/>
					<@s.hidden name="order_order_state" value="2"/>				
					<@s.hidden name="order_pay_state" value="0"/>
					<@s.submit value="退货成功" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 
				<@s.form action='/bmall_Goodsorder_subSend.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_send_state" value="7"/>
					<@s.submit value="退货失败" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 	
		</#if>
		
		<#if goodsorder.order_state=='1' && goodsorder.pay_state=='4'&& goodsorder.send_state=='0'>
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="5"/>
					<@s.hidden name="order_send_state" value="0"/>
					<@s.hidden name="order_order_state" value="2"/>				
					<@s.submit value="退款成功" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 
				<@s.form action='/bmall_Goodsorder_subPay.action'>
					<@s.hidden name="oid"/>
					<@s.hidden name="order_pay_state" value="6"/>
					<@s.submit value="退款失败" cssClass="submitbut"/>
					<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
				</@s.form> 					
		</#if>
		<@s.form action='/bmall_Goodsorder_manaorder.action'>
		    	<@s.submit value="返回订单" cssClass="submitbut"/>
		</@s.form>
		</div>
		<div class="ordermsgshow"></div>		
	</div>
</div>
</body>
</html>
