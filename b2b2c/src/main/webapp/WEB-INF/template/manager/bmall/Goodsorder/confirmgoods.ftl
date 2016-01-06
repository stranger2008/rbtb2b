<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>确认订单</title>
<link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />
</head>
<body>
<div class="rightside f_right">
	<div class='contitle'>
		<h2>我已收到货，同意付款</h2>
	</div>
	<div class='conorder'>
		<h3>订单信息</h3>
	</div>
	<div class='gdthdiv'>
		<table width="100%"  cellspacing="1" cellpadding="8" bgcolor='#DDDDDD'>
			    <tr bgcolor="#E8F2FF"><th width="46%">商品详细</th><th>单价(元)</th><th>数量</th><th>优惠</th><th>商品总价(元)</th><th>运费(元)</th></tr>
				<#list detailList as detail>
				<tr bgcolor="#FFFFFF">				
				<td style='text-align:left;'>
		      		<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
		      		   <table>
		      		   <tr>
			      		   <td>
				      		  	 <div class='gs_img'>
				      				<img src="${(detail.img_path)?if_exists}" width='60' height='60'>
				      			 </div>
			      			</td>
			      		    <td class='gname' style="text-align:left;">
			      		    	<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
					      			${(detail.goods_name)?if_exists}
					      		</a>
			      		    </td>
		      		   </tr>
		      		   </table>
			      	</a>										
				</td>
				
				
				
				<td>${detail.order_price?if_exists}</td><td>${detail.order_num}</td><td>-</td>
				<td>
				
				<script type="text/javascript">
					var price=${detail.order_price?if_exists};
					var num=${detail.order_num};
					document.write(price*num);
				</script>
		
				
				</td>
				<td>运费(元)</td>
				</tr>
			</#list>
		</table>
	</div>
	<div class='totalmon'>
		<span><font>实付款:</font><font class='totalprice'>${goodsorder.tatal_amount?if_exists}</font><font>元</font></span>
		<div class='clear'></div>
	</div>
	<div class='orderdetail2'>
		<table   cellpadding="6">
		<tr>
			<td>订单编号：</td><td>${goodsorder.order_id?if_exists}</td>		
		</tr>		
		<tr>
			<td>卖家昵称：</td><td>${saleMember.cust_name?if_exists}</td>		
		</tr>	
		<tr>
			<td>收货信息：</td><td>${goodsorder.address?if_exists}</td>		
		</tr>
		<tr>
			<td>成交时间：</td><td>${goodsorder.order_time?if_exists}</td>		
		</tr>
		</table>
	</div>
	<div class='confirm'>
		 <ul>
		     <li class='conmg'>请收到货后，再确认收货！否则您可能钱货两空！</li><br/>
   			 <li>如果您想申请退款，请<a href="#" style="color:#3366CC;">点击这里</a>	</li>	  
		 </ul>
		 <div class='btncf'>
		 <@s.form action='/bmall_Goodsorder_comfirmpay.action'>
		 	  <span style='font-weight:600;'>支付密码:&nbsp;</span><input name='comfirmpwd' type='password'/><br/><br/>
		 	   <@s.submit value="确认订单" cssClass='btn1' /> 		
		 	   <@s.hidden name="oid"/>
		 </@s.form> 	  
		 </div>
	</div>
</div>
</body>
</html>



