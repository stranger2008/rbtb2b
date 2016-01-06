<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rbt.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/include/css/admin/index.css" rel="stylesheet" type="text/css" />
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<link href="/include/css/admin/guided.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jNotify.jquery.js"></script>
<script type="text/javascript" src="/include/js/admin_system_set_guide.js"></script>
<script type="text/javascript" src="/include/components/artDialog4.0.3/artDialog.js?skin=default"></script>
<script type="text/javascript" src="/include/components/artDialog4.0.3/jquery.artDialog.js?skin=default"></script>
<script type="text/javascript">
function showDown(menu_id,size){
	for(var i=0;i<size;i++){
		if(document.getElementById('three_'+menu_id+'_'+i).style.display=='none'){
			document.getElementById('three_'+menu_id+'_'+i).style.display ='block';
		}else{
			document.getElementById('three_'+menu_id+'_'+i).style.display ='none';
		}
	}
}

</script>
<style type="text/css">
#tinybox{position:absolute; display:none; padding:10px; background:#ffffff; border:10px solid #E3E8EC; z-index:2000;}
#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
</style>

<decorator:head />
<title>
   <decorator:title default="Welcome" />-<%=SysconfigFuc.getSysValue("cfg_webname") %>
</title>
</head>
<body>
<input type="hidden" id="file_size" value="<%=SysconfigFuc.getSysValue("cfg_filesize")%>"/>
<input type="hidden" id="flash_size" value="<%=SysconfigFuc.getSysValue("cfg_flashsize")%>"/>
<input type="hidden" id="image_size" value="<%=SysconfigFuc.getSysValue("cfg_imgsize")%>"/>
<input type="hidden" id="image_type" value="<%=SysconfigFuc.getSysValue("cfg_imgtype")%>"/>
<input type="hidden" id="flash_type" value="<%=SysconfigFuc.getSysValue("cfg_mediatype")%>"/>
<input type="hidden" id="file_type" value="<%=SysconfigFuc.getSysValue("cfg_attachtype")%>"/>
<!--头部开始-->
<div class="top">
<div class="top_l  f_left" id="topDiv">
  <div class="logo"><a href="<%=SysconfigFuc.getSysValue("cfg_index")%>" target="_blank"><img src="/include/images/admin/logo.jpg" /></a></div>
  <div id="centerPoint"></div>
  <ul class="nav">    
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
		map.put("syscode", "sys");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
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
		//操作权限串，登陆的时候放进去的
		String oper_right = "";
		if(session.getAttribute("oper_right")!=null){
			oper_right = session.getAttribute("oper_right").toString();
		}		
		//out.println(menu_right+"==="+oper_right);   
    	//user_type: 1：会员管理员 2：会员子账号 3：运营商管理员 4：运营商子账号
    	String user_type = "";
		if(session.getAttribute(Constants.USER_TYPE)!=null){
			user_type = session.getAttribute(Constants.USER_TYPE).toString();
		}  	
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
     			String cssStr = " class=\"nav_a\"";
     			if(frist_menu_id.equals(menu_id)){
     				cssStr = " class=\"nav_hover\"";
     			}
     			if(!user_type.equals("3")){
     				if(menu_right.indexOf(menu_id) == -1){
	     				continue;
	     			}
     			}
     %>    
     	<li<%=cssStr %>><a href="<%=url %>" target="<%=target %>"><%=menu_name %></a></li>    	
     <%    			
     		}
     	}
     %>
     
  </ul>
</div>
<div class="top_r  f_right">
  <div class="top_1 f_right">
  <div class="title"><a href="http://www.ruibaotong.net/help/index.html" target="_blank" class="hp">帮助</a><a href="http://www.ruibaotong.net/guanyuwomen/contact.html" target="_blank" class="kf">客户服务</a></div>
  <P>你好，${user_name}<a href="/admin_Sysuser_logout.action">[退出]</a><a href="/adminindex.action">桌面</a><a href="/">网站首页</a></P>
  </div>
</div>
</div>
<div class="clear"></div>
<!--头部结束-->

<!--侧栏开始-->
<div class="cont">
<div style="width:150px;border-right:9px solid #6f777a;height:auto;float:left;border-bottom:9px solid #6f777a;">
<div class="sidebar f_left" style="display:block;" id="left_sidebar">
<!-- 
<ul>
 <li class="sidebar_title">功能列表</li>
</ul>
 -->
 
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
		List menuList = SysmenuFuc.getMenuListByUpmenuid(frist_menu_id,"sys");
		
		if(menuList!=null && menuList.size()>0){
			int s = 0;
			System.out.print("************" + menuList.size() + " ************************");
			for (Iterator iter = menuList.iterator(); iter.hasNext();) {
				HashMap menuMap = (HashMap)iter.next();
				String menu_id = "",menu_name="";
				if(menuMap.get("menu_id")!=null) menu_id = menuMap.get("menu_id").toString();
				if(menuMap.get("menu_name")!=null) menu_name = menuMap.get("menu_name").toString();
				
				if(!user_type.equals("3")){
     				if(menu_right.indexOf(menu_id) == -1){
	     				continue;
	     			}
     			}
				
				String dis_down = "style='display:none;'";
				if(parentMenuId.equals("") && s == 0){
					dis_down = "";
				}else if(menu_id.equals(parentMenuId)){
					dis_down = "";
				}
				
				//三级菜单获取
				downList = SysmenuFuc.getMenuListByUpmenuid(menu_id,"sys");
				
				int downSize = 0;
				if(downList!=null && downList.size()>0){
					downSize = downList.size();
				}
%>
    <ul>
		<li class="first_li" style="cursor:pointer;" ><a href="###" onclick="showDown('<%=menu_id %>','<%=downSize %>');return false;"><%=menu_name %></a></li>
    
    	
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
								if(down_url.indexOf(request.getRequestURI()) > -1){
									liCss = " class=\"hover\"";
								}
								if(!user_type.equals("3")){
				     				if(menu_right.indexOf(down_menu_id) == -1){
					     				continue;
					     			}
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


<!-- 
<ul>
 <li class="sidebar_last">商品分类目录</li>
</ul>
 -->
</div>


</div>
<decorator:body />
 
</div>
<div style="clear:both;"></div>

<div id="backgroundPopup"></div>

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
			height: '10%',
			title: '系统信息提示',
			icon: icon,
	        content: '<div class="g_decorate">'+val+'</div><div class="count_down">3秒后自动关闭...</div>',
	        time: 3,
	        noText: '关闭',
            noFn: false
       });  
   	}
</script>
<script type="text/javascript" src="/include/js/common.js"></script>





