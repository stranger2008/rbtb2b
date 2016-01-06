<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<%@ page import="com.rbt.model.Resume"%>
<%@ page import="com.rbt.function.ResumeFuc"%>
<% 
	String user_name = "",mem_type="",resume_id="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.MEM_TYPE)!=null){
		mem_type = session.getAttribute(Constants.MEM_TYPE).toString();
	}
	String cellphone="",phone="",qq="",msn="",skype="",email="";
	if(request.getParameter("resume_id") != null){
	    resume_id = request.getParameter("resume_id").toString();
	    Resume resume = ResumeFuc.getResumeByPk(resume_id);
	    cellphone = resume.getCellphone();
	    phone = resume.getPhone();
	    qq = resume.getQq();
	    msn = resume.getMsn();
	    skype = resume.getSkype();
	    email = resume.getEmail();
	}
	if(!user_name.equals("") && mem_type.equals("0")){
%>
	document.write('<table width="850" border="0" cellspacing="1" cellpadding="" bgcolor="#F6F5F5" id="table_lianxi" style="display: none;">'
          +'<tr>'
             +'<td width="100" height="31" bgcolor="#FFFFFF" class="main_info_title">手机号码</td>'
             +'<td width="180" bgcolor="#FFFFFF" class="main_info_text"><%=cellphone %></td>'
             +'<td width="110" bgcolor="#FFFFFF" class="main_info_title">联系电话</td>'
             +'<td width="170" bgcolor="#FFFFFF" class="main_info_text"><%=phone %></td>'
             +'<td width="110" bgcolor="#FFFFFF" class="main_info_title">QQ</td>'
             +'<td width="200" bgcolor="#FFFFFF" class="main_info_text"><%=qq %></td>'
           +'</tr>'
           +'<tr>'
             +'<td width="100" height="31" bgcolor="#FFFFFF" class="main_info_title">MSN</td>'
             +'<td width="180" bgcolor="#FFFFFF" class="main_info_text"><%=msn %></td>'
             +'<td width="110" bgcolor="#FFFFFF" class="main_info_title">SKYPE</td>'
             +'<td width="170" bgcolor="#FFFFFF" class="main_info_text"><%=skype %></td>'
             +'<td width="110" bgcolor="#FFFFFF" class="main_info_title">电子邮箱</td>'
             +'<td width="200" bgcolor="#FFFFFF" class="exhibition_info_text2"><%=email %></td>'
           +'</tr>'
        +'</table>');
<%	
	}else if(!user_name.equals("") && mem_type.equals("1")){
%>
    document.write('<table width="850" border="0" cellspacing="1" cellpadding="" bgcolor="#F6F5F5" id="table_quanxian" style="display: none;">'
           +'<tr>'
             +'<td bgcolor="#FFFFFF" class="main_info_title">您不是企业会员，无法查看联系方式!</td>'
           +'</tr>'
        +'</table>');
<%	
	}else{
%>
	document.write('<table width="850" border="0" cellspacing="1" cellpadding="" bgcolor="#F6F5F5" id="table_login" >'
           +'<tr>'
             +'<td bgcolor="#FFFFFF" class="main_info_title">您还没有登录，请登录后查看联系方式!</td>'
           +'</tr>'
           +'<tr>'
             +'<td bgcolor="#FFFFFF" class="main_info_title">请<a href="/login.html" target="_blank" style="color: red;" >登录</a> 或 <a href="/register.html" target="_blank" style="color: red;">免费注册</a></td>'  
           +'</tr>'
        +'</table>');
<%
	}
%>