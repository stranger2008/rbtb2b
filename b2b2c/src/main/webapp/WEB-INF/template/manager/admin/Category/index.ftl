<html>
  <head>
    <title>分类管理</title>
    <link rel="StyleSheet" href="/include/css/admin/category.css" type="text/css" />
    <link rel="StyleSheet" href="/include/css/admin/alert.css" type="text/css" />
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/admin/category.js"></script>	
 	<script type="text/javascript">
	$(document).ready(function(){ 	
	       mod_type=$("#type").val();
	       mtype=mod_type;       
	       if($("#cate_attr_str").val()!=''){
	           cat_div_backselect(${cfg_topcatid?if_exists},mod_type);
		   }else{
			   load_supply(${cfg_topcatid?if_exists},mod_type);	                
		   }	       
           //select回选
	       selectCheckObj("cate.module_type",mod_type);  
           //菜单名称改变触发事件
           menutitle=$("#modletype option:selected").text()+"分类";      
		   $("#modletype").change(function(){//事件触发  
			   $('option:selected', this).each(function(){
                    code_value=this.value;
                    $("#type").val(code_value);
                    menutitle=$("#modletype option:selected").text()+"分类"; 
                    $("#cat_select_moudle").val(menutitle);
                    load_supply(${cfg_topcatid?if_exists},code_value);                  	
			   });  	
		  });       	  	   
    });		   
   </script>
  </head>
  <body >
<@s.form action="/admin_Category_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 分类管理 > 分类管理
 </div>
 <div class="main_top">   
 </div>
 <div class="main_cont"  style="height:800px;margin-top:-28px;">      
	<div id="modletype">请选择所属的模块:<@s.select id="cate.module_type" name="cate.module_type" list="commparaList" listValue="module_name" listKey="module_type" /></div>
	<div id="divlist">				
	</div>   
 </div>
 <div class="clear">
 </div>
</div>
</div>
<div class="clear" style="height:500px;">
</div>   
	  <@s.hidden id="mod_type" name="mod_type" value="supply"/>
	  <@s.hidden id="type" name="type"/>
	  <@s.hidden name="category.cat_level" id="level"/>	  
	  <@s.hidden name="category.up_cat_id" id="up_cat"/>
	  <@s.hidden name="category.cat_id" id="cat_id"/>
	  <@s.hidden name="cate_attr_str" id="cate_attr_str"/>
	  <@s.hidden name="cat_select_moudle" id="cat_select_moudle"/>	  
	  <!--用于JS判断回选串中是否存在该记录顶级ID-->
	  <@s.hidden id="top_cat_id" value="${cfg_topcatid?if_exists}"/>
      <!--排序插件开始-->
      <@s.hidden name="cate_sort_id" id="scoreid"/>
      <@s.hidden name="cate_sort_Num" id="scoreNum"/> 
      <!--排序插件结束-->
</@s.form>
<script type="text/javascript">
      <!--分类排序方法-->
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