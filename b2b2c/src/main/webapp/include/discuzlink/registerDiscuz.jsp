<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.rbt.common.Constants"%>
 <% 

    String strjs="";
    HttpSession sessions=request.getSession();
    if(sessions.getAttribute(Constants.DISCUZ_REGISTER_JS)!=null){
       //获取登录成功返回可执行的JS串，在输入执行JS串
       strjs=sessions.getAttribute(Constants.DISCUZ_REGISTER_JS).toString();
       //清空登录论坛返回的JS串，就是清空SESSION的值
       sessions.setAttribute(Constants.DISCUZ_REGISTER_JS,"");
    }
    if(!strjs.contains("script")){
     strjs="<script type='text/javascript'>alert('"+strjs+"');</script>";
    }
	
 %>
 document.write(<%=strjs %>);
