<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.* "%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/include/css/wrong.css" rel="stylesheet" type="text/css" />
<title>404页面</title>
</head>
<body>
<div class="top">
  <a href="/"><img src="<%=SysconfigFuc.getSysValue("cfg_logourl") %>" class="logo"/></a>
  <P class="top_right"><a href="/aboutus_1.html">帮助中心</a></P>
  <div class="clear"></div>
</div>
<div class="w960">
  <div class="sorry-pic"></div>             
  
  <div class="sorry-content">
    <h2>很抱歉，您查找的页面不存在，可能已被删除或转移</h2>
    <p>您可以：</p>
    <p>1.检查访问的页面网址是否正确</p>
    <p>2.去其他地方逛逛:<a href="/"><%=SysconfigFuc.getSysValue("cfg_indexname") %></a></p>
   </div>
  <br class="clear" />
</div>
<jsp:include page="/a/bottom.html"></jsp:include>

</body>
</html>