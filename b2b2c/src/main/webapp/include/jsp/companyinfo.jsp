<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<%@ page import="com.rbt.model.Member"%>
<%@ page import="com.rbt.model.Memberuser"%>
<%@ page import="com.rbt.function.MemberuserFuc"%>
<%@ page import="com.rbt.model.Levelinfo" %>
<%@ page import="com.rbt.function.LevelinfoFuc"%>
<%@ page import="com.rbt.model.Membercredit"%>
<%@ page import="com.rbt.function.MembercreditFuc"%>
<%@ page import="com.rbt.function.MemberFuc" %>
<%@ page import="com.rbt.function.AreaFuc" %>
<%@ page import="com.rbt.function.SysconfigFuc" %>
<%         
	String user_name = "",supplycust_id="",sessioncust_id="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_ID)!=null){
		 sessioncust_id= session.getAttribute(Constants.CUST_ID).toString();
	}
	String cust_name = "",contact_name ="",contact_sex="",contact_job="",
	contact_qq="",contact_msn="",cust_user_name="",phone="",contact_cellphone="",
	area_name = "",address = "",c_num="0",level_code="";
	if(request.getParameter("cust_id") != null){
	    supplycust_id = request.getParameter("cust_id").toString();
	   if(!supplycust_id.equals("")){
		    Member member = MemberFuc.getMemberByPk(supplycust_id);
		    if(member != null){
		    	cust_name = member.getCust_name();
			    contact_name = member.getContact_name();
			    contact_sex = member.getContact_sex();
			    contact_job =  member.getContact_job();
			    contact_qq = member.getContact_qq();
			    contact_msn = member.getContact_msn();
			    phone = member.getPhone();
			    contact_cellphone = member.getContact_cellphone();
			    area_name = AreaFuc.getAreaNameByMap(member.getArea_attr());
		    	address = member.getAddress();
		    	if(address!=null && address.length()>19){
		    		address=address.substring(0,18)+"...";
		    	}
		    }
		    Membercredit credit = MembercreditFuc.getMembercreditByPk(supplycust_id);
		    if(credit != null){
		    	c_num = credit.getC_num();
		    }
		    Memberuser memberuser = MemberuserFuc.getMemberuserById(supplycust_id);
		    if(memberuser != null){
		    	cust_user_name = memberuser.getUser_name();
		    }
		    if(!sessioncust_id.equals("")){
		    	Levelinfo levelinfo = LevelinfoFuc.getLevelinfoById(sessioncust_id);
		    	if(levelinfo != null){
		    		level_code = levelinfo.getLevel_code();
		    	}
		    }
	   }
	}
	String templateStyle = SysconfigFuc.getSysValue("cfg_templatestyle");
	
	//model为模型名称，如供应为supply，求购为buy
	String model = "";
	if(request.getParameter("model") != null){
		model = request.getParameter("model");
	}
	
	boolean isDis = true;
	if(model.equals("buy")){
		//level_code为4，企业VIP
		if( (user_name.equals("") || !"4".equals(level_code)) ){
			isDis = false;
		}
	}
	
	if(isDis){
%>
	document.write('<div class="comp_prof f_right">'
         +'<h2 class="comp_prof_t">公司基本资料</h2>'
         +'<div class="comp_prof_cont">'            
              +'<h2 class="comp_prof_name">'              
              +'<%=cust_name %>'              
              +'</h2>'
              +'<ul>'
                +'<li>'
                 	+'<span>'
                 		+'联系人：<%=contact_name %>&nbsp;<%=contact_sex %>&nbsp;<%=contact_job %>'
                 		+'<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=contact_qq %>&site=qq&menu=yes">'
						+'<img border="0" src="/templets/<%=templateStyle %>/images/sm_qq.gif"/></a>'
						+'<a href="msnim:chat?contact=<%=contact_msn %>">'
						+'<img src="/templets/<%=templateStyle %>/images/sm_msn.gif"/></a>'
                 	+'</span>'
                 +'</li>'
                 +'<li>'
                 	+'<a href="###" onclick="document.addMemberfriendFormds.submit();">[加为商友]</a>' 
                 	+'<a href="###" onclick="document.sendLetterForm.submit();">[发送信件]</a>'
                 	+'<a href="###" onclick="document.collectCompanyForm.submit();">[收藏公司]</a>'
                 	+'<form action="/member_Memberfriend_showview.action" method="post" name="addMemberfriendFormds">'
                 		+'<input type="hidden" name="memberfriend.cust_name" value="<%=cust_name %>"/>'
                 		+'<input type="hidden" name="loc" value="" />'
                 	+'</form>'
                 	+'<script>'
				    	+'document.addMemberfriendFormds.loc.value = document.location.href;'
				    +'</script>'
				    +'<form action="/member_Memberinbox_add.action" method="post" name="sendLetterForm">'
				       +'<input type="hidden" name="send_name" value="<%=cust_name %>"/>'
				       +'<input type="hidden" name="memberinbox.mess_type" value="4"/>'
                 	   +'<input type="hidden" name="loc" value="" />'
				    +'</form>'
				    	+'<script>'
				    	+'document.sendLetterForm.loc.value = document.location.href;'
				    +'</script>'
				    +'<form action="/member_Collect_add.action" name="collectCompanyForm" method="post">'
    					+'<input type="hidden" name="collect.title" id="title" value="<%=cust_name %>" />'	
    					+'<input type="hidden" name="collect.link_url" id="link_url1"  />'
    					+'<input type="hidden" name="loc" value="" />'
   					 +'</form>'
    				+'<script>'
    					+'document.collectCompanyForm.loc.value = document.location.href;'
    					+'document.getElementById("link_url1").value = document.location.href;'
    				+'</script>'
                 +'</li>'
                 +'<li ><div class="credit_css"><font class="cre_zi">信用指数:</font><font class="cre_num"><%=c_num %></font></div></li>'
                 +'<li><span>电话：<%=phone %>&nbsp;<%=contact_cellphone %></span></li>'
                 +'<li><span>地区：<%=area_name %></span></li>'
                 +'<li><span>地址：<%=address %></span></li>'
              +'</ul>'
              +'<a class="showroom" href="/showroom/<%=cust_user_name %>" target="_blank"><img src="/templets/<%=templateStyle %>/images/into.gif" /></a>'
         +'</div>'
      +'</div>');
<%	
	}else{
%>
	document.write('<div class="comp_prof f_right">'
         +'<h2 class="comp_prof_t">公司基本资料</h2>'
         +'<div class="comp_prof_cont"><div class="con_porod_no">'
              +'<P class="no"><b>您还没有申请为企业VIP会员</b></P>'
              +'<P class="other"><b>申请为企业VIP会员后，您可以...</b></P>'
              +'<ul class="comp_xx">'
                 +'<li><img src="/templets/<%=templateStyle %>/images/ico_edit.gif" align="absmiddle"/>发布供求信息</li>'
                 +'<li><img src="/templets/<%=templateStyle %>/images/ico_product.gif" align="absmiddle"/>推广企业产品</li>'
                 +'<li><img src="/templets/<%=templateStyle %>/images/ico_homepage.gif" align="absmiddle"/>建立企业商铺</li>'
                 +'<li><img src="/templets/<%=templateStyle %>/images/ico_message.gif" align="absmiddle"/> 在线洽谈生意</li>'
              +'</ul>'
              +'<br  class="clear"/>'
             +'<div class="go_zc"><a href="###" onclick="document.addMemberupgradeFormds.submit();"><img src="/templets/<%=templateStyle %>/images/user_reg.gif" /></a></div>'
         			+'<form action="/member_Memberupgrade_add.action" method="post" name="addMemberupgradeFormds">'
                 	+'</form>'
                 	+'<script>'
				    	+'document.addMemberupgradeFormds.loc.value = document.location.href;'
				    +'</script>'
         +'</div></div>'
      +'</div>');
<%
	}
%>