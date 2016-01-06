<html>
  <head>
    <title>系统菜单管理</title>
    <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	<script type="text/javascript" >
	function changeStyle(color) {
	if (color == "admin") {
		document.getElementById("admin").style.color = "red";
		document.getElementById("member").style.color = "#333333";
		document.getElementById("b2c").style.color = "#333333";
		document.getElementById("adminmenu").style.display = "";
		document.getElementById("membermenu").style.display = "none";
		document.getElementById("b2cmenu").style.display = "none";
	}
	if (color == "member") {
		document.getElementById("member").style.color = "red";
		document.getElementById("admin").style.color = "#333333";
		document.getElementById("b2c").style.color = "#333333";
		document.getElementById("adminmenu").style.display = "none";
		document.getElementById("membermenu").style.display = "";
		document.getElementById("b2cmenu").style.display = "none";
	}
	if (color == "b2c") {
		document.getElementById("b2c").style.color = "red";
		document.getElementById("admin").style.color = "#333333";
		document.getElementById("member").style.color = "#333333";
		document.getElementById("adminmenu").style.display = "none";
		document.getElementById("membermenu").style.display = "none";
		document.getElementById("b2cmenu").style.display = "";
	}
}
	
	</script>
  </head>
  <body>
  
<@s.form action="/admin_Sysmenu_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统菜单管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Sysmenu_add.action','');">添加菜单

</a></li>     
    <!-- <li class="sc"><a onclick="delInfo

('sysuser.user_id','/admin_Sysuser_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>-->
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="10%" align="center" class="top_td show_top">
       <a style="cursor:pointer;" id="admin" onclick="changeStyle('admin')" style="color:#FF0000;">管理员后台菜单</a>
    </td>
    <td width="10%" align="center" class="top_td show_top">
       <a style="cursor:pointer;" id="member" onclick="changeStyle('member')">&nbsp;会员后台菜单</a>
    </td>
     <td width="80%" align="left" class="show_top top_td">
       <a style="cursor:pointer;" id="b2c" onclick="changeStyle('b2c')">&nbsp;商城菜单</a>
       <a href="javascript: d.openAll();a.openAll();e.openAll();" style="color:#F16800;">全部打开</a> | <a href="javascript: d.closeAll();a.closeAll();e.closeAll();" style="color:#F16800;">全部关闭</a>
    </td>
  </tr>
  <tr id="adminmenu" style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="left" class="top_td show_top" colspan="3">
       <script type="text/javascript">
		a = new dTree('a');

		a.add(1111111111,-1,'管理员菜单&nbsp;');
		
		<#list adminmenuList as menu>
		
		a.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'${menu.menu_name?if_exists}&nbsp;<a href=### onclick=delOneInfo("sysmenu.menu_id","/admin_Sysmenu_deletemenu.action","${menu.menu_id?if_exists}");>删除</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_add.action","sysmenu.up_menu_id=${menu.menu_id?if_exists}&sysmenu.menu_level=${menu.menu_level?if_exists}");>添加子菜单</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_view.action","sysmenu.menu_id=${menu.menu_id?if_exists}");>修改</a>','#');
		
		 </#list>
		
		document.write(a);
	  </script>
    </td>
  </tr>
  <tr id="membermenu" style="background:url(images/top_tr.gif) repeat-x;display:none;">
    <td width="20%" align="left" class="top_td show_top" colspan="3">
       <script type="text/javascript">
		d = new dTree('d');

		d.add(1111111111,-1,'会员菜单&nbsp;');
		
		<#list membermenuList as menu>
		
		d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'${menu.menu_name?if_exists}&nbsp;<a href=### onclick=delOneInfo("sysmenu.menu_id","/admin_Sysmenu_deletemenu.action","${menu.menu_id?if_exists}");>删除</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_add.action","sysmenu.up_menu_id=${menu.menu_id?if_exists}&sysmenu.menu_level=${menu.menu_level?if_exists}");>添加子菜单</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_view.action","sysmenu.menu_id=${menu.menu_id?if_exists}");>修改</a>','#');
		
		 </#list>
		
		document.write(d);
	  </script>
    </td>
  </tr>
   <tr id="b2cmenu" style="background:url(images/top_tr.gif) repeat-x;display:none;">
    <td width="20%" align="left" class="top_td show_top" colspan="3">
       <script type="text/javascript">
		e = new dTree('e');

		e.add(1111111111,-1,'商城菜单&nbsp;');
		
		<#list b2cmenuList as menu>
		
		e.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'${menu.menu_name?if_exists}&nbsp;<a href=### onclick=delOneInfo("sysmenu.menu_id","/admin_Sysmenu_deletemenu.action","${menu.menu_id?if_exists}");>删除</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_add.action","sysmenu.up_menu_id=${menu.menu_id?if_exists}&sysmenu.menu_level=${menu.menu_level?if_exists}");>添加子菜单</a>&nbsp;<a href=### onclick=linkToInfo("/admin_Sysmenu_view.action","sysmenu.menu_id=${menu.menu_id?if_exists}");>修改</a>','#');
		
		 </#list>
		
		document.write(e);
	  </script>
    </td>
  </tr>
  
  
  </table>
  
  
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>

</@s.form>

  </body>
</html>