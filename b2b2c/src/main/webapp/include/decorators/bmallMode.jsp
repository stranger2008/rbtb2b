<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rbt.common.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="x-ua-compatible" content="ie=7" />
    <link href="/include/css/bmall/public.css" rel="stylesheet" type="text/css" />
	<link href="/include/css/bmall/index.css" rel="stylesheet" type="text/css" />
	<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/components/artDialog4.0.3/artDialog.js?skin=default"></script>
	<script type="text/javascript" src="/include/components/artDialog4.0.3/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="/include/js/jNotify.jquery.js"></script>	
	<script type="text/javascript" src="/include/js/common.js"></script>
	<script type="text/javascript" src="/templets/bmall/js/goods.js"></script>
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
 <div class="user_top">
  <div class="user">
      <P class="welcome f_left">您好<s:property value="#session.user_name"/>，欢迎来瑞宝通商城！  <span>[ <a href="bmall_Memberuser_exit.action">退出</a> ]</span></P>
      <P class="help f_right"><span class="order"><a href="/bmall_Goodsorder_list.action">我的订单</a></span><span class="help_sp"><a href="/mall/goods!mallcart.action" target="_blank" class="help_car">购物车</a></span><span><a href="/mallhelp.html" target="_blank">帮助中心</a></span></P>
  </div>
</div>

<div class="logo_top w980">
  <a href="/mallindex.html"><img src="/templets/bmall/images/logo_06.gif"  class="f_left"/></a>
  <div class="search f_left">
    <% 
   String openmall = SysconfigFuc.getSysValue("cfg_openmall");
   if("0".equals(openmall)){
   %>
     <ul class="se_dp">
     	<li class="se_dp_h">商品</li>
     	<li>店铺</li>
     </ul>
       <% 
   }
  %>
     <br class="clear"/>
     <div class="sea_main" >
        <input type="hidden" id="catstr" name="catstr" value=""/>
         <input type="text" id="selValue" class="sea_text" />
        <input type="button" class="sea_button" onclick="return pselect('p','');" value="" />
     </div>
  </div>
  <a href="#"><img src="/templets/bmall/images/phone.gif" class="f_right"/></a>
</div>
<div class="clear"></div>




<div class="w980">
	<% 
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "b2c");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
    	String frist_menu_id = "";
    	if(session.getAttribute("first_menu_id") == null || session.getAttribute("first_menu_id").toString().equals("")){
    	    HashMap topMap=new HashMap();
    	    if(topMenuList!=null&&topMenuList.size()>0){
    	       topMap=(HashMap)topMenuList.get(0);
    	       frist_menu_id=topMap.get("menu_id").toString();    	       
    	       session.setAttribute("first_menu_id", frist_menu_id);
    	    }    	    
    	}else{ 	
    		frist_menu_id = session.getAttribute("first_menu_id").toString();
    	}
    %>
    
 <!-- 一级菜单读取开始 -->
 <div class="title_nav">
      <ul>    
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
     				cssStr = " class=\"title_navli\"";
     			}
     %>
        <li<%=cssStr %>><a target="<%=target %>" onclick="setSession('<%=url%>','<%=menu_id %>');"><%=menu_name %></a></li>	
     <%
     		}
     	}
     %>
       </ul>
  </div>   
<script type="text/javascript">
function setSession(actionName,fieldVal){	
   	document.getElementById('commonForm').action=actionName;
   	document.getElementById('top_menu').value = fieldVal;  
    document.getElementById('commonForm').submit();	   
}
$(document).ready(function(){
		$(".ulli").click(function(){
		    var sh=$(this).parent(".lsul").children(".lsul2");
		    var sha=$(this).children("a");
		    var showFlag=sh.css("display");	
		    if(showFlag=="none"){
		    	sh.css("display","block");	
		    	$(this).parent(".lsul").children(".lsul2")[0].style.display="block";
		    	$(this).children(".lsul2").addClass("blockdiv");
		    }else{
		    	sh.css("display","none");
		    	$(this).parent(".lsul").children(".lsul2").visibility="hidden";
		    	$(this).parent(".lsul").children(".lsul2")[0].style.display="none";
		    }
		});
	});
</script>

</div>
<div class="clear"></div>



<div class="w980">

</div>


<div class="w980">
   <div class="leftside f_left">
    <%
        //第二级菜单绑定
		HashMap twoMap = new HashMap();
		List twoList = SysmenuFuc.getMenuListByUpmenuid(frist_menu_id,"b2c");				
		if(twoList!=null && twoList.size()>0){
			for (int i=0;i<twoList.size();i++) {
				twoMap = (HashMap)twoList.get(i);
				String menu_id = "",menu_name="";
				if(twoMap.get("menu_id")!=null) menu_id = twoMap.get("menu_id").toString();
				if(twoMap.get("menu_name")!=null) menu_name = twoMap.get("menu_name").toString();
				String twoCss="";
				if(i==0) twoCss="lsli";
	%>
   		<ul class="lsul">
             <li class="ulli <%=twoCss%>"><a class="up"><%=menu_name%></a></li>           
             <ul class="lsul2">
              <%
                //三级菜单获取
                List threeList = new ArrayList();       
				threeList = SysmenuFuc.getMenuListByUpmenuid(menu_id,"b2c");
				if(threeList!=null && threeList.size()>0){
					for(int j=0;j<threeList.size();j++){
						Map threeMap = (HashMap)threeList.get(j);
						String down_menu_id = "",down_menu_name="",down_url="",target="";
						if(threeMap.get("menu_id")!=null) down_menu_id = threeMap.get("menu_id").toString();
						if(threeMap.get("menu_name")!=null) down_menu_name = threeMap.get("menu_name").toString();
						if(threeMap.get("url")!=null) down_url = threeMap.get("url").toString();
						if(threeMap.get("target")!=null) target = threeMap.get("target").toString();
						String threeCss = "";
						if(j==threeList.size()-1){
							threeCss = "lsul2nli";
						}				
               %>
	          	 <li class="<%=threeCss %>"><a href="<%=down_url %>" target="<%=target %>"><%=down_menu_name %></a></li>
	           <%
	           		}
	           }
	            %>
	         </ul>
           
   		</ul>
 <%
 		}
 	}
%>  

  </div>
   <decorator:body />
</div>
<div class="clear"></div>

<div class="bottom">
   <div class="w980">
       <ul class="bottom_help">
           <li class="bottom_t way">新手上路</li>
           <li><a href="#">新手指南</a></li>
           <li><a href="#">账户注册</a></li>
           <li><a href="#">购物流程</a></li>
           <li><a href="#">网站地图</a></li>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t security">购物保障</li>
           <li><a href="#">7天无理由退换货</a></li>
           <li><a href="#">100%正品保障</a></li>
           <li><a href="#">提供发票</a></li>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t after_sales">售后服务</li>
           <li><a href="#">统一售后专线：</a></li>
           <li><a href="#">0592-6036010</a></li>
           <li><a href="#">其他业务咨询：</a></li>
           <li><a href="#">0592-6036010</a></li>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t about">关于瑞宝通</li>
           <li><a href="#">公司简介</a></li>
           <li><a href="#">加入瑞宝通</a></li>
           <li><a href="#">合作专区</a></li>
           <li><a href="#">联系我们</a></li>
       </ul>
       <div class="clear"></div>
   </div>
   <div class="w980 copyright">
      <P class="copyright_about"><a href="#">关于我们</a><a href="#">服务条款</a><a href="#">广告服务</a><a href="#">诚聘精英</a><a href="#">客服中心</a><a href="#">网站导航</a><a href="#">版权说明</a></P>
      <P>Copyright 2003-2011 ruibaotong, All Rights Reserved</P>
      <P>瑞宝通（厦门）信息科技有限公司 版权所有 闽ICP备11017665号</P>
   </div>
</div>
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
<s:form  action="" id="commonForm">
	<s:hidden name="top_menu" id="top_menu"/>
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
