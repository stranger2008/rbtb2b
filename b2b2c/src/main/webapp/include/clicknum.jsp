<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.PublicFuc"%>
<%
	//table_name 表名 field_name 字段名 field_value 字段值
	
	//修改对应的点击量，并返回修改后的值
	
	String table_name="",field_name="",field_value="";
	if(request.getParameter("t")!=null){
		table_name = request.getParameter("t");
	}
	if(request.getParameter("n")!=null){
		field_name = request.getParameter("n");
	}
	if(request.getParameter("v")!=null){
		field_value = request.getParameter("v");
	}
	String click_num = "0";
	if(!table_name.equals("") && !field_name.equals("") && !field_value.equals("")){
		
		Map map = new HashMap();
		map.put("tableName",table_name);
		map.put("fieldName",field_name);
		map.put("fieldValue",field_value);
		click_num = PublicFuc.updateGetClickNum(map);
	}
	out.println("document.write('"+click_num+"');");
%>