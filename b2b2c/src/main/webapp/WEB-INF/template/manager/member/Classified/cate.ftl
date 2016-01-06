<html>
  <head>
    <title>添加分类信息</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	
	<script type="text/javascript">
	  $(document).ready(function(){ 
	      //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"classified");      
		 is_supply_insert="supply";//用于添加供应时的分类不能为空，才添加select的size属性	
		 //如果为添加供应属性的分类时， 则要加上这个属性          
   	     $(".select").attr("size",2); 
   	     //提示信息
   	     var msg=queryString("is_select");
   	     if(msg==1){
   	        jNotify("请选择分类至未级!");
   	     }  
	  });
	</script>
    <style type="text/css">
      .select{width:216px;height:320px;margin:10px 0px 0px 30px;font-size:14px;}  
    </style>
  </head>
  <body >
<@s.form action="/member_Classified_gopage.action"  method="post" validate="true"> 
<table width="84%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>分类信息管理>添加分类信息</td>
 </tr>
</table>
<div class="main_index f_left">
   
	 <div class="main_top">   
	 </div>	 
	 <div class="main_cont"  style="height:800px;">      
	   <table  width="100%">
	      <tr><td><span style="font-size:14px;">请选择分类信息至最后一级:</span></td></tr>
	      <tr><td ><div id="divlist"></div></td></tr>
	      <tr><td style="text-align:center;">
          <input type="button"  value="重新选择" style="cursor:pointer;" onclick="document.forms[0].action='/member_Classified_cate.action';document.forms[0].submit();"/>              
          <#if classified?if_exists!=''>     
              <@s.hidden name="update_value" value="${classified.info_id?if_exists}"/>  
	          <#if classified.info_id?if_exists!="">
	             <@s.submit value="修改分类信息"/>
	          <#else>
	             <@s.submit value="添加分类信息"/>	  
	          </#if>
	       <#else>
	          <@s.submit value="添加分类信息"/>	             
           </#if></td></tr>
	   </table>				
	 </div>   
 </div>
 <div class="buttom">
  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
  <@s.hidden name="is_member" value="is" />
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
  <@s.hidden name="classified.info_id"/>
 </div>   
</@s.form>	 
</body>
</html>