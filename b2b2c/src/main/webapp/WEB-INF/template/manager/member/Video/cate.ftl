<html>
  <head>
    <title>添加视频</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>    
    <script type="text/javascript" src="/include/js/admin/category.js"></script> 
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"video");  
		 is_supply_insert="supply";//用于添加视频时的分类不能为空，才添加select的size属性	
		 //如果为添加视频属性的分类时， 则要加上这个属性          
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
<@s.form action="/member_Video_gopage.action"  method="post" validate="true"> 
<div class="main_index f_left">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>视频管理>添加视频</td>
 	</tr>
	</table>
	 <div class="main_top">   
	 </div>	 
	 <div class="main_cont"  style="height:800px;">      
	   <table  width="100%">
	      <tr><td><span style="font-size:14px;">请选择分类至最后一级:</span></td></tr>
	      <tr><td style="width:760px;"><div id="divlist"></div></td></tr>
	      <tr><td style="text-align:center;">	      
	      ${listSearchHiddenField?if_exists}	      
          <input type="button"  value="重新选择" style="cursor:pointer;" onclick="linkToInfo('/member_Video_cate.action','');"/>           
          <#if video?if_exists!=''>     
              <@s.hidden name="update_value" value="${video.video_id?if_exists}"/>  
	          <#if video.video_id?if_exists!="">
	             <@s.submit value="修改视频"/>
	          <#else>
	             <@s.submit value="添加视频" />	  
	          </#if>
	       <#else>
	          <@s.submit value="添加视频" />	             
           </#if></td></tr>
	   </table>				
	 </div>   
 </div>
 <div class="buttom">
  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
  <@s.hidden id="hidden_cat_value"/>
  <@s.hidden name="is_member" value="member"/>
  <@s.hidden name="video.video_id"/>
    <@s.hidden name="info_infoattr_id" />
 </div>   
</@s.form>	 
</body>
</html>