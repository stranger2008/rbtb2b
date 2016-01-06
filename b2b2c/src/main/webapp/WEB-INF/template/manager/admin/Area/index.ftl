<html>
  <head>
    <title>地区管理</title>
	<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/admin/area.js"></script>
	<link rel="StyleSheet" href="/include/css/admin/area.css" type="text/css" />	
	<link rel="StyleSheet" href="/include/css/admin/alert.css" type="text/css" />
	<script type="text/javascript">	 
	 $(document).ready(function(){ 
	     //加载第一级地区
	    showarea("${cfg_topareaid?if_exists}",1,1);	
     });	    
    </script>
  </head>
  <body>
	<@s.form action="/admin_Area_list.action" method="post" validate="true">
	<@s.hidden name="admin_Sort_id" id="admin_Sort_id"/>
	<@s.hidden name="area.area_id" id="area_id"/>
	<@s.hidden name="area.up_area_id" id="up_area_id"/>
	<@s.hidden name="area.area_level" id="area_level"/>	
	
	<div class="main_index f_left">
	  <div class="pageLocation">
 	    当前位置:系统管理 > 系统设置 > 地区管理
      </div>
 		<div class="main_top">
   		<ul class="main_top_ul">
     		<li class="tj"><a href="/admin_Area_add.action?area.up_area_id=${cfg_topareaid?if_exists}&area.area_level=1">添加地区</a></li>	
   		</ul>
 		</div>
    <div class="main_cont">
 		<div id="arealist" style="height:800px;"> 	 
 	</div>
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>
	  <@s.hidden name="area_sort_id" id="scoreid"/>
      <@s.hidden name="area_sort_Num" id="scoreNum"/> 
</@s.form>
<script type="text/javascript">
      function updateSort(actionName)      
      {  
 	       art.dialog({
		   title: '系统信息提示',
	       content: '<div class="decorate">您确定要排序吗？</div>',
	       width: '15%',
	       icon: 'question',
	       yesFn: function () {
	           var admin_group_id='';
	           var admin_group_num='';
		       $("input:checkbox").each(function(){	                 	  
	                 admin_group_id+=this.value+',';	 
		       });		       
		       $("input:text").each(function(){	                 	  
	                 admin_group_num+=this.value+',';     
		       });		       
	           var len=admin_group_id.length;
	           var lenNum=admin_group_num.length;
	           admin_group_id=admin_group_id.substring(0,len-1);
	           admin_group_num=admin_group_num.substring(0,lenNum-1);      
	           document.getElementById('scoreid').value = admin_group_id;//用于隐藏所有的ID
	           document.getElementById('scoreNum').value = admin_group_num;//用于隐藏文本所有的值
			   document.forms[0].action=actionName;			   		 
			   document.forms[0].submit();
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    });	   
     } 
    </script> 	
  </body>
</html>