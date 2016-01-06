<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<% 
	String user_name = "",cust_type="",loc="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_TYPE)!=null){
		cust_type = session.getAttribute(Constants.CUST_TYPE).toString();
	}
	
	if(request.getParameter("loc")!=null && !"".equals(request.getParameter("loc"))){
	    loc = request.getParameter("loc");
	}
	
	String index_url = "",logout_url="";
	
	if(cust_type.equals(Constants.MEMBER_TYPE))
	{
		index_url = "/memberindex.action";
		logout_url="/member_Memberuser_logout.action";
	}
	else if(cust_type.equals(Constants.ADMIN_TYPE))
	{
		index_url = "/adminindex.action";
		logout_url="/admin_Sysuser_logout.action";
	}
	
	
	
	if(!user_name.equals("")){
%>
	document.write('<a href="<%=index_url%>"><%=user_name %></a><a href="<%=logout_url%>">[退出登录]</a>');
<%	
	}else{
%>
	document.write('<a href="/login.html<%=loc %>">[用户登录]</a><a href="/register.html">[免费注册]</a>');
<%
	}
%>