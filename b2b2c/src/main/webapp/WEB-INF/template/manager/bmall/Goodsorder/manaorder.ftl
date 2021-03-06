 <html>
<head>
 <title>订单列表</title>
 <link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />

</head>
<body>
	<div class="postion">
 		<a href="#">我是卖家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的订单</a>
	</div>
   <@s.form action="/bmall_Goodsorder_manaorder.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>订单中心</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>我的订单</span></h2>
       <table width="100%">
		       	<tr>
		       		<td align="right">订单编号:</td> 
					<td><@s.textfield name="order_id_s" maxLength="50"/></td>
					<td align="right">商品名称:</td> 
					<td><@s.textfield name="goods_name_s" maxLength="50"/></td>
					<td align="right">买家会员名称:</td> 
					<td><@s.textfield name="buy_cust_name" maxLength="50"/></td>
					<td rowspan="2" width="10%">
						<@s.submit name="" value="" cssClass="sbut"/>					
					</td>
				</tr>
				<tr>
					<td align="right">订单状态:</td><td><@s.select name="order_state_s" list="orStateList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
					</td> 
					<td align="right">付款状态:</td><td><@s.select name="pay_state_s" list="payList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
					</td>
					<td align="right">发货状态:</td><td><@s.select name="send_state_s" list="sendList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
					</td>		
			   </tr>
	       </table>
       
       <table cellspacing="0"  width="100%">
         <tr>
	         <td class="fthstyle2" width='275'>商品</td>
	         <td class="fthstyle2" width='60'>单价(元)</td>
	         <td class="fthstyle2" width='60'>数量</td>
	         <td class="fthstyle2" width='100'>实付款</td>
	         <td class="fthstyle2" width='135'>
				状态
			 </td>
	         <td class="fthstyle2">交易操作</td>
         </tr>
    
         <#list goodsorderList as goodsorder>
             <tr >
             	<td colspan='7'>
	             	<div class='goodsmain'>
	             	
	             		<div class='ordertitle'>
							<input type="checkbox">			
							<span>订单编号:</span>
							<span>${(goodsorder.order_id)?if_exists}</span>
							<span>成功时间:</span>
							<span>${(goodsorder.order_time)?if_exists}</span>
							<#if web_openmall?if_exists==0>
							<span class='degn_span' style='margin-left:10px;'><a href="#">${(goodsorder.buy_cust_name)?if_exists}</a></span>
							</#if>
						</div>
						
						<div class='detailmain'>
												
							<table width="100%">
							<tr>
								<td width='420' class="detail_td1">
									<#list detailList as detail>
										  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
										      <div class='detailgs'>
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
											      		<td width='199px;'  class='degn_td1'>
												      		<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
												      			${(detail.goods_name)?if_exists}
												      		</a>
											      		</td>
											      		<td width='70' align='center'>${(detail.order_price)?if_exists}</td>
											      		<td width='70' align='center'>${(detail.order_num)?if_exists}</td>
											      	</tr>
											      </table>
											  </div>							
										  </#if>	 
									</#list>
								</td>
								<td width='110' align='center'  class="detail_td1">
								${(goodsorder.tatal_amount)?if_exists}<br/>
								<font color="#808080">(
									<#if goodsorder.ship_free!='0'>
										(快递${goodsorder.ship_free?if_exists}元)
									<#else>
										 卖家承担运费
									</#if>)
								</font>
								</td>
								<td width='135' align='center'  class="detail_td1" style='line-height:20px;'>
								   <#if goodsorder.order_para!=''>
								   		${(goodsorder.order_para)?if_exists},
								   </#if>
								   <#if goodsorder.pay_para!=''>
								   		${(goodsorder.pay_para)?if_exists},
								   </#if>
								   <#if goodsorder.send_para!=''>
								   		${(goodsorder.send_para)?if_exists}
								   </#if>								   
								   <br/>
								   <#if goodsorder.order_state=='6' && goodsorder.pay_state=='1'&& goodsorder.send_state=='1'>
								   		 评价完成								   
								   </#if>
								</td>
								<td align='center' valign='top'>
									<br/><br/>
								    <a href="/bmall_Goodsorder_manaorderdetail.action?oid=${goodsorder.order_id?if_exists}" >订单详情</a>
									<#if goodsorder.order_state=='1' && goodsorder.pay_state=='0'>
										<br/>
										等待买家付款
									</#if>
								
									<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='1'>
											<br/>
											等待买家确认收货
									</#if>
									<#if goodsorder.order_state=='1' && goodsorder.pay_state=='1'&& goodsorder.send_state=='4'>
											<br/>交易成功
									</#if>
									<#if goodsorder.order_state=='5' && goodsorder.pay_state=='1'&& goodsorder.send_state=='1'>
											<br/>
											<a href="/bmall_Goodsorder_eval.action?oid=${goodsorder.order_id}"><img src="/include/images/evalcom.png"/></a>
									</#if>
								</td>
							</tr>							
							</table>								
						</div>						
					</div>
             	</td>
             </tr>
         </#list>
         
       </table>
       
        <div class="listbottom" style="margin-top:10px;">
       		${pageBar?if_exists}
        </div>
      </div>     
   </div>
      
</div>
</@s.form>
</body>
</html>

