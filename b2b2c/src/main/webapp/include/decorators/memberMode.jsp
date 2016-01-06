<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rbt.common.*" %>
<%@ page import="com.rbt.service.ISysmoduleService;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/include/css/member/public.css" rel="stylesheet" type="text/css" />
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<jsp:include page="/WEB-INF/template/portal/default/jsinclude.html"></jsp:include>
<script type="text/javascript" src="/include/components/artDialog4.0.3/artDialog.js?skin=default"></script>
<script type="text/javascript" src="/include/components/artDialog4.0.3/jquery.artDialog.js?skin=default"></script>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script> 
<script type="text/javascript" src="/include/js/jNotify.jquery.js"></script>
<script type="text/javascript" src="/include/js/top.js"></script>
<script type="text/javascript">
function showDown(menu_id,size){
	for(var i=0;i<size;i++){
		if(document.getElementById('three_'+menu_id+'_'+i).style.display=='none'){
			document.getElementById('three_'+menu_id+'_'+i).style.display = 'block';
		}else{
			document.getElementById('three_'+menu_id+'_'+i).style.display = 'none';
		}
	}
}
</script>
<decorator:head />
<title>
	会员中心-<decorator:title default="Welcome" />-<%=SysconfigFuc.getSysValue("cfg_webname") %>
</title>
</head>

<body>
<input type="hidden" id="file_size" value="<%=SysconfigFuc.getSysValue("cfg_filesize")%>"/>
<input type="hidden" id="flash_size" value="<%=SysconfigFuc.getSysValue("cfg_flashsize")%>"/>
<input type="hidden" id="image_size" value="<%=SysconfigFuc.getSysValue("cfg_imgsize")%>"/>
<input type="hidden" id="image_type" value="<%=SysconfigFuc.getSysValue("cfg_imgtype")%>"/>
<input type="hidden" id="flash_type" value="<%=SysconfigFuc.getSysValue("cfg_mediatype")%>"/>
<input type="hidden" id="file_type" value="<%=SysconfigFuc.getSysValue("cfg_attachtype")%>"/>
<div class="top_main">
<div class="top">
   <div class="top_l f_left">
     <a href="<%=SysconfigFuc.getSysValue("cfg_index")%>"><img src="/include/images/member/logo.jpg"  class="logo"/></a>
     <p class="f_left">您好，<s:property value="#session.user_name"/>  <a href="/member_Memberuser_logout.action">[退出]</a></P>
   </div>
   <div class="top_r">
    <p class="sc">
    	<a onclick="window.external.addFavorite(this.href,this.title);return false;" rel="sidebar" href="javascript:void(0);" title='会员中心-<decorator:title default="Welcome" />-<s:property value="#session.web_name"/>'  >加入收藏</a>|
    	<a href="/" target="_blank">网站首页</a>
    </p>
    <div class="sear">
    <select id="modselect">
    <%
	   ISysmoduleService sysmoduleService = (ISysmoduleService) com.rbt.function.CreateSpringContext.getContext().getBean("sysmoduleService");
       Map pageMap = new HashMap();
       pageMap.put("state_code", "0");
       List paralist= sysmoduleService.getList(pageMap);
       if(paralist!=null&&paralist.size()>0){
          for (Iterator iter = paralist.iterator(); iter.hasNext();) {
				HashMap listMap = (HashMap)iter.next();
				String para_value="",para_key="";
				if(listMap.get("module_type")!=null){
				    para_value=listMap.get("module_type").toString();
				}
				if(listMap.get("module_name")!=null){
				    para_key=listMap.get("module_name").toString();
				}
    %>
      <option value="<%=para_value %>"><%=para_key %></option>
	<%
				}
			}
	%>
    </select>
    <input id="searchText" type="text" name="" style="width:150px;"/>
    <input id="searchButton" type="button" name="" value="" style="border:0; background:url(/include/images/member/ss.jpg) no-repeat; height:22px;width:55px;" />
    </div>
   </div>
  </div>
<div class="clear"></div>
<!--top结束-->
<div class="navs">
  <div class="nav_l"></div>
  <div class="nav_c">
    <ul class="nav_main">
    
    <%! 
    	public String getFirstMenuIdByList(List topMenuList) {
			if (topMenuList == null || topMenuList.size() == 0)
				return "";
			HashMap menuMap = (HashMap) topMenuList.get(0);
			String menu_id = "";
			if (menuMap.get("menu_id") != null) {
				menu_id = menuMap.get("menu_id").toString();
			}
			return menu_id;
		}
    %>
    
    <% 
    
    	//syscode sys:运营商 com:会员
		//user_type: 1：会员管理员 2：会员子账号 3：运营商管理员 4：运营商子账号
		
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "com");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
		//out.println(topMenuList.size());
    
    	String frist_menu_id = "";
		if (request.getParameter("up_menu_id") != null && !request.getParameter("up_menu_id").toString().equals("")) {
			frist_menu_id = request.getParameter("up_menu_id");
		} else if (session.getAttribute("first_menu_id") != null && !session.getAttribute("first_menu_id").toString().equals("")) {
			frist_menu_id = session.getAttribute("first_menu_id").toString();
		}else{
			frist_menu_id = getFirstMenuIdByList(topMenuList);
		}
		session.setAttribute("first_menu_id", frist_menu_id);

		//菜单权限串，登陆的时候放进去的
		String menu_right = "";
		if(session.getAttribute("menu_right")!=null){
			menu_right = session.getAttribute("menu_right").toString();
		}
		System.out.println(menu_right+"===============================================");
		//操作权限串，登陆的时候放进去的
		String oper_right = "";
		if(session.getAttribute("oper_right")!=null){
			oper_right = session.getAttribute("oper_right").toString();
		}
		
		//out.println(menu_right+"==="+oper_right);
    
    	
    %>
    
     <!-- 一级菜单读取开始 -->
     
     <% 
     	if(topMenuList!=null && topMenuList.size()>0){
     		HashMap mMap = new HashMap();
     		for(Iterator it = topMenuList.iterator();it.hasNext();){
     			mMap = (HashMap)it.next();
     			String menu_name = "",menu_id = "",url = "",target = "";
     			if(mMap.get("menu_name")!=null) menu_name = mMap.get("menu_name").toString();
     			if(mMap.get("menu_id")!=null) menu_id = mMap.get("menu_id").toString();
     			if(mMap.get("url")!=null) url = mMap.get("url").toString();
     			if(mMap.get("target")!=null) target = mMap.get("target").toString();
     			String cssStr = "";
     			if(frist_menu_id.equals(menu_id)){
     				cssStr = " class=\"home1\"";
     			}
     			
     			if(menu_right.indexOf(menu_id) == -1){
     				continue;
     			}
     			
     %>
     
     	<li<%=cssStr %>><a href="<%=url %>" target="<%=target %>"><%=menu_name %></a></li>
     
     <%
     			
     		}
     	}
     %>
     
     
     <!-- 一级菜单读取结束 -->
    
    </ul>
   <ul class="dp">
       <%
        String custid =(String)session.getAttribute("cust_id");
        String userName =(String)session.getAttribute("user_name");
        String memtype = MemberFuc.getMemberByPk(custid).getMem_type();
        
        if("0".equals(memtype)){
        %>
        <li><a href="/showroom/<%=userName %>" target='_blank'><%=SysconfigFuc.getSysValue("cfg_syscompanyname") %></a></li>
        <li class="last" ><a href="/memberindex.action">会员中心首页</a></li>
        <% 
        }
        else
        {
         %>
          <li class="last" style="margin-left: 120px;background:none;"><a href="/memberindex.action">会员中心首页</a></li>
         <%
        }
      %>
    </ul>
    
  </div>
  <div class="nav_r"></div>
</div>
<!--导航结束-->
<div class="clear"></div>
</div>
<div class="cont">
  <div class="sidebar">
  
  
<% 
	String parentMenuId = "";
	if (request.getParameter("parentMenuId") != null && !request.getParameter("parentMenuId").toString().equals("")) {
		parentMenuId = request.getParameter("parentMenuId");
	} else if (session.getAttribute("parentMenuId") != null && !session.getAttribute("parentMenuId").toString().equals("")) {
		parentMenuId = session.getAttribute("parentMenuId").toString();
	}
	session.setAttribute("parentMenuId", parentMenuId);
	if (request.getParameter("up_menu_id") != null && !request.getParameter("up_menu_id").toString().equals("")) {
		parentMenuId = "";
	} 
%>
  
  
  <%
		
		List downList = new ArrayList();
		HashMap downMap = new HashMap();
		List menuList = SysmenuFuc.getMenuListByUpmenuid(frist_menu_id,"com");
		if(menuList!=null && menuList.size()>0){
			int s = 0;
			
			for (Iterator iter = menuList.iterator(); iter.hasNext();) {
				HashMap menuMap = (HashMap)iter.next();
				String menu_id = "",menu_name="";
				if(menuMap.get("menu_id")!=null) menu_id = menuMap.get("menu_id").toString();
				if(menuMap.get("menu_name")!=null) menu_name = menuMap.get("menu_name").toString();
				if(menu_right.indexOf(menu_id) == -1){
					
     				continue;
     			}
     			
     			
     			String dis_down = "style='display:none;'";
				if(parentMenuId.equals("") && s == 0){
					dis_down = "";
				}else if(menu_id.equals(parentMenuId)){
					dis_down = "";
				}
				
				//三级菜单获取  
				int downSize = 0;
				String menuid ="";
				downList = SysmenuFuc.getMenuListByUpmenuid(menu_id,"com");
				for (Iterator down = downList.iterator(); down.hasNext();) {
				       HashMap indexmap = (HashMap)down.next();
				       if(indexmap.get("menu_id")!=null) menuid = indexmap.get("menu_id").toString();
				       if(menu_right.indexOf(menuid) > -1){
				          downSize++;
				       }
				}
				
				//if(downList!=null && downList.size()>0){
				//	downSize = downList.size();
				//}
     			
%>
  
    <h2 class="sidebar_t" style="cursor:pointer;" onclick="showDown('<%=menu_id %>','<%=downSize %>');"><%=menu_name %></h2>
    <ul>
    
    
    <% 
				  		
				  		if(menuList!=null && menuList.size()>0){
				  			int k = 0;
							for (Iterator iters = downList.iterator(); iters.hasNext();) {
								downMap = (HashMap)iters.next();
								String down_menu_id = "",down_menu_name="",down_url="",target="";
								if(downMap.get("menu_id")!=null) down_menu_id = downMap.get("menu_id").toString();
								if(downMap.get("menu_name")!=null) down_menu_name = downMap.get("menu_name").toString();
								if(downMap.get("url")!=null) down_url = downMap.get("url").toString();
								if(downMap.get("target")!=null) target = downMap.get("target").toString();
								String liCss = " class=\"link\"";
								if(request.getRequestURI().indexOf(down_url) > -1){
									liCss = " class=\"hover\"";
								}
								if(menu_right.indexOf(down_menu_id) == -1){
				     				continue;
				     			}
				     			String para = "?";
				     			if(down_url.indexOf("?") > -1){
				     				para = "&";
				     			}
				  %>
				  
				  
				  
				  <li<%=liCss %><%=dis_down %> id="three_<%=menu_id %>_<%=k %>"><a href="<%=down_url %><%=para %>parentMenuId=<%=menu_id %>" target="<%=target %>"><%=down_menu_name %></a></li>
				  
				  
				  <%
				  				k++;
							}
						}
				  %>
    
    </ul>
    
    <%
    			s++;
			}
		}
	%>
    
    <h2 class="sidebar_kf">客服中心</h2>
    <ul class="kf">
       <li style="line-height:25px; background:url(/include/images/member/tel.gif) no-repeat 10px center;padding-left:35px;">服务电话</li>
       <li class="tel"><%=SysconfigFuc.getSysValue("cfg_phone") %></li>
       <li>服务时间</li>
       <li>8:30-17:30(周一至周日)</li>
    </ul>
  </div>
  
<decorator:body />
   
<div class="clear"></div>
<!--cont结束-->

<div style="height:30px;"></div>
<% String filerootbottom="/"+SysconfigFuc.getSysValue("cfg_templateroute")+"/bottom.html"; %>  
<jsp:include page="<%=filerootbottom %>" />

<s:form  action="" id="commonForm">
	<s:hidden name="" id="commonText" value=""/>
	<input type="hidden" name="token_value" value="${get_token_value}"/>
	
	<s:hidden name="" id="rsrv1" value=""/>
	<s:hidden name="" id="rsrv2" value=""/>
	<s:hidden name="" id="rsrv3" value=""/>
	<s:hidden name="" id="rsrv4" value=""/>
	<s:hidden name="" id="rsrv5" value=""/>
	<s:hidden name="" id="rsrv6" value=""/>
	<s:hidden name="" id="rsrv7" value=""/>
	<s:hidden name="" id="rsrv8" value=""/>
	<s:hidden name="" id="rsrv9" value=""/>
	<s:hidden name="" id="rsrv10" value=""/>
	${listSearchHiddenField}
	
</s:form>

</body>
</html>

<script type="text/javascript">  
	var actionMessage = '<s:actionmessage/>';
	if(actionMessage!=''){
       	alertShow(actionMessage,'succeed');
	}
	//操作成功提示
	function alertShow(val,parameter){
	    var icon = parameter;
		art.dialog({
			width: '10%',
			title: '系统信息提示',
			icon: icon,
	        content: '<div class="decorate">'+val+'</div><div class="count_down">3秒后自动关闭...</div>',
	        time: 3,
	        noText: '关闭',
            noFn: true
     });
	}
	
</script>
<script type="text/javascript" src="/include/js/common.js"></script>

