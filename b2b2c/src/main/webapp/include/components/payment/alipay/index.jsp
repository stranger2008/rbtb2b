<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.SysconfigFuc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到帐付款</title>
<% 
	String cust_id = "",payvalue="";
	if(request.getParameter("cust_id")!=null){
		cust_id = request.getParameter("cust_id");
	}
	if(request.getParameter("payvalue")!=null){
		payvalue = request.getParameter("payvalue");
	}
%>
<BODY>
<FORM name=alipayment action=alipayto.jsp method="post">
	<INPUT name=aliorder value="<%=SysconfigFuc.getSysValue("cfg_webname")%>-会员在线充值" />
	<INPUT name=alimoney value="<%=payvalue %>"/>
	<INPUT name=alibody value=""/>
	<input name="pay_bank" value="directPay">
	<input name="cust_id" value="<%=cust_id %>">
	<input name="_input_charset" value="utf-8">
</FORM>
</BODY>
<script type="text/javascript">
document.alipayment.submit();
</script>