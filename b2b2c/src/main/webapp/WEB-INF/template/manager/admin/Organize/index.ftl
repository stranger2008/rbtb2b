<html>
  <head>
    <title>组织部门管理</title>
	<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/admin/organize.js"></script>
	<link rel="StyleSheet" href="/include/css/admin/alert.css" type="text/css" />
	<link rel="StyleSheet" href="/include/css/admin/organize.css" type="text/css" />	
	<script type="text/javascript">	 
	 $(document).ready(function(){ 
	     //加载第一级部门
	    showorga("1111111111",0);	
     });	    
    </script>
  </head>
  <body>
	<@s.form action="/admin_Organize_list.action" method="post" validate="true">
	<div class="main_index f_left">
	 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 组织部门管理
   </div>
 		<div class="main_top">
   		<ul class="main_top_ul">
     		<li class="tj"><a href="/admin_Organize_add.action?organize.up_org_id=1111111111&organize.org_level=1">添加部门</a></li>
    		</ul>
 		</div>
    <div class="main_cont">
 	 <div id="orgalist" style="height:400px;"> 	 
 	 </div>
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>
</@s.form>	
  </body>
</html>