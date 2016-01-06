<html>
  <head>
    <title>订单商品表列表</title>   
     <link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
     <style type="text/css">
		td{border:0px;}
	</style>
  </head>
  <body>
<@s.form action="/admin_Goodsorder_manaorder.action" method="post">
<@s.hidden name="goodsorder.is_recom" id="admin_goodsorder_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 订单管理 > 订单列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" cellspacing="0">
           <tr>
	         <td class="fthstyle2" width='32%'>商品</td>
	         <td class="fthstyle2" width='7%'>单价(元)</td>
	         <td class="fthstyle2" width='5%'>数量</td>
	         <td class="fthstyle2" width='12%'>实付款</td>
	         <td class="fthstyle2" width='12%'>
				状态
			 </td>
	         <td class="fthstyle2">交易操作</td>
         </tr>
  <#list goodsorderList as goodsorder>
             <tr >
             	<td colspan='7'>
	             	<div class='goodsmain'>
	             	
	             		<div class='ordertitle'>			
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
								<td width='530' class="detail_td1">
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
											      		<td width='299px;'  class='degn_td1'>
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
								<td width='175' align='center'  class="detail_td1" style='line-height:20px;'>
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
								<td align='center' valign='middle'>
								    <a href="/admin_Goodsorder_manaorderdetail.action?oid=${goodsorder.order_id?if_exists}" >订单详情</a>
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
											<a href="/admin_Goodsorder_eval.action?oid=${goodsorder.order_id}"><img src="/include/images/evalcom.png"/></a>
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
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
			<td align="right">订单编号:</td> 
			<td><@s.textfield name="order_id_s" maxLength="50" cssStyle='width:160px;'/></td>
		</tr>
		
		<tr>
			<td align="right">商品名称:</td> 
			<td><@s.textfield name="goods_name_s" maxLength="50"  cssStyle='width:160px;'/></td>
		</tr>
		
		<tr>
			<td align="right">买家会员名称:</td> 
			<td><@s.textfield name="buy_cust_name" maxLength="50"  cssStyle='width:160px;'/></td>
		</tr>

		<tr>
			<td align="right">付款状态:</td> 
			<td><@s.select name="pay_state_s" list="payList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
		</tr>
		
		<tr>
			<td align="right">订单状态:</td> 
			<td><@s.select name="order_state_s" list="orStateList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
		</tr>
		
		<tr>
			<td align="right">发货状态:</td> 
			<td><@s.select name="send_state_s" list="sendList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
		</tr>
		
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
				<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
			</td>
		</tr>
	</table>
</div>
 <style type="text/css">
.fthstyle2{
	height:33px;
	line-height:33px;
	text-align:center;
	background:url(/include/images/bmall/ta-th2012.gif);
	padding-left:5px;
}
</style>
</@s.form>
</body>
</html>

