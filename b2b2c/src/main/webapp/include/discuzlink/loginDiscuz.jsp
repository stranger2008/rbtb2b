<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.rbt.common.Constants"%>
 <% 
    //将该页面的链接 <script src="/include/discuzlink/logintDiscuz.jsp"></script> 放在登录成功页面就可以了，
    //当用户登录成功时候，调整到成功页面，JSP页面自动会被执行，就能同时也登录论坛
    String state="0";
    String strjs="",strloginout="";
    HttpSession sessions=request.getSession();
    //获取判断是否已经登录论坛，从session中获取DISCUZ_STATE的状态，如果值为：1：表示已经登录。如果值为：0表示还没有登录
    if(sessions.getAttribute(Constants.DISCUZ_STATE)!=null&&!sessions.getAttribute(Constants.DISCUZ_STATE).equals("")){
       state=sessions.getAttribute(Constants.DISCUZ_STATE).toString();
    }
    if(state.equals("0")&&sessions.getAttribute(Constants.DISCUZ_LOGIN_JS)!=null&&!sessions.getAttribute(Constants.DISCUZ_LOGIN_JS).equals("")){
      if(sessions.getAttribute(Constants.DISCUZ_LOGINOUT_JS)!=null){
	       //获取登录成功返回可执行的JS串，在输入执行JS串
	       strloginout=sessions.getAttribute(Constants.DISCUZ_LOGINOUT_JS).toString();
	       //清空登录论坛返回的JS串，就是清空SESSION的值
	       sessions.setAttribute(Constants.DISCUZ_LOGINOUT_JS,"");
      }
       //获取登录成功返回可执行的JS串，在输入执行JS串
       strjs=sessions.getAttribute(Constants.DISCUZ_LOGIN_JS).toString();
       //改变登录的状态值为1：表示已经登录！
       sessions.setAttribute(Constants.DISCUZ_STATE,"1");
       //清空登录论坛返回的JS串，就是清空SESSION的值
       sessions.setAttribute(Constants.DISCUZ_LOGIN_JS,"");
    }
 		 
 %>
 document.write('<%=strloginout %>');
 document.write('<%=strjs %>');
 


