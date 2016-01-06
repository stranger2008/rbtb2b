<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.SysconfigFuc"%>
<%@ page import="com.rbt.function.GoodsOrderFuc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝付款</title>
<% 
	String payvalue="",pay_order_id="";
	//获取商品的订单号
	if(request.getParameter("order_id")!=null){
		pay_order_id = request.getParameter("order_id");
	}
	//获取订单总额
	payvalue=GoodsOrderFuc.getOrderAllMomey(pay_order_id);
%>
<BODY>
<FORM name="mallpayment" action="/include/components/payment/mallpay/alipayto.jsp" method="post">
	<INPUT name="aliorder" type="hidden" value="<%=SysconfigFuc.getSysValue("cfg_mallwebname")%>-在线支付" />
	<INPUT name="alimoney" type="hidden" value="<%=payvalue %>"/>
	<INPUT name="alibody" type="hidden" value="商品订单号为:<%=pay_order_id %>--<%=SysconfigFuc.getSysValue("cfg_mallwebname") %>"/>
	<input name="pay_bank" type="hidden" value="directPay" id="pay_type" />
	<input name="order_id" type="hidden" value="<%=pay_order_id %>" />
	<input name="_input_charset" type="hidden" value="utf-8" />
</FORM>
</BODY>
<script type="text/javascript">
	document.mallpayment.submit();
</script>







