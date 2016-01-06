<html>
  <head>
    <title>添加下载</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"download"); 
		 is_supply_insert="supply";//用于添加供应时的分类不能为空，才添加select的size属性	
		 //如果为添加供应属性的分类时， 则要加上这个属性          
   	     $(".select").attr("size",2); 
   	     //提示信息
   	     var msg='${is_select?if_exists}';
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
<@s.form action="/admin_Download_gopage.action"  method="post" validate="true"> 

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 下载管理 > 添加下载
   </div>
	 <div class="main_top">   
	 </div>
	 
	 <div class="main_cont"  style="height:800px;">      
	   <table width="100%">
	      <tr><td><span style="font-size:14px;">请选择分类至最后一级:</span>&nbsp;&nbsp;<a href="admin_Category_list.action?type=download&ajaxRequestRandom=1" target="_blank">[分类管理]</a><@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror></td></tr>
	      <tr><td><div id="divlist"></div></td></tr>
	      <tr><td style="text-align:center;">	      
	      ${listSearchHiddenField?if_exists}
          <input type="button"  value="重新选择" class="sub" style="cursor:pointer;" onclick="linkToInfo('/admin_Download_cate.action','');"/>
          <#if download?if_exists!=''>
              <@s.hidden name="update_value" value="${download.down_id?if_exists}"/>   
	          <#if download.down_id?if_exists!="">
	             <@s.submit value="修改下载"  />
	          <#else>
	             <@s.submit value="添加下载"  />	
	          </#if>  
	       <#else>
	          <@s.submit value="添加下载" />	              
           </#if>      
          </td></tr>
	   </table>				
	 </div>   
 </div>
 <div class="buttom">
  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
  <@s.hidden id="hidden_cat_value"/>
  <@s.hidden name="download.down_id"/>
    <@s.hidden name="info_infoattr_id" />
 </div>   
</@s.form>	 
</body>
</html>