<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<% 
	String user_name = "",cust_type="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_TYPE)!=null){
		cust_type = session.getAttribute(Constants.CUST_TYPE).toString();
	}
	
	String index_url = "",logout_url="";
	
	if(cust_type.equals(Constants.MEMBER_TYPE))
	{
		index_url = "/bmall-index.action";
		logout_url="/bmall_Memberuser_exit.action";
	}
	else if(cust_type.equals(Constants.ADMIN_TYPE))
	{
		index_url = "/adminindex.action";
		logout_url="/admin_Sysuser_logout.action";
	}
	
	if(!user_name.equals("")){
%>
	document.write('<a href="<%=index_url%>"><%=user_name %></a><a href="<%=logout_url%>">&nbsp;&nbsp;[退出登录]</a>');
<%	
	}else{
%>
	document.write('<a href="/signin.html">[用户登录]</a>&nbsp;&nbsp;<a href="/joinus.html">[免费注册]</a>');
<%
	}
%>