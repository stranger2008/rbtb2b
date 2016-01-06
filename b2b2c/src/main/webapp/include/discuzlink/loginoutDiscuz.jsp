<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.rbt.common.Constants"%>
 <% 
    //将该页面的链接 <script src="/include/discuzlink/loginoutDiscuz.jsp"></script> 放在登录页面就可以了，
    //当用户退出登录时候，调整到登录页面，JSP页面自动会被执行，就能退出论坛登录
    String strjs="";
    HttpSession sessions=request.getSession();
    if(sessions.getAttribute(Constants.DISCUZ_LOGINOUT_JS)!=null){
       //获取登录成功返回可执行的JS串，在输入执行JS串
       strjs=sessions.getAttribute(Constants.DISCUZ_LOGINOUT_JS).toString();
       //清空登录论坛返回的JS串，就是清空SESSION的值
       sessions.setAttribute(Constants.DISCUZ_LOGINOUT_JS,"");
    }
	
 %>
 document.write('<%=strjs %>');

