<html>
  <head>
    <title>添加商品</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"goods"); 
		 is_supply_insert="supply";//用于添加商品时的分类不能为空，才添加select的size属性	
		 //如果为添加商品属性的分类时， 则要加上这个属性          
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
  	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">新增商品</a><span>></span><a href="#">选择分类</a>
    </div>
<@s.form action="/bmall_Goods_gopage.action"  method="post" validate="true"> 

<div class="main_index f_left">
	 <div class="main_cont"  style="height:800px;">      
	   <table width="100%">
	      <tr><td><span style="font-size:14px;">&nbsp;请选择分类至最后一级:</span>&nbsp;&nbsp;
	      <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror></td></tr>
	      <tr><td><div id="divlist"></div></td></tr>
	      <tr><td style="text-align:center;">	      
	      ${listSearchHiddenField?if_exists}
          <input type="button"  value="重新选择" class="submitbut" style="cursor:pointer;" onclick="linkToInfo('/bmall_Goods_cate.action','');"/>
          <#if goods?if_exists!=''>
              <@s.hidden name="update_value" value="${goods.goods_id?if_exists}"/>   
	          <#if goods.goods_id?if_exists!="">
	             <@s.submit value="修改商品" cssClass="submitbut"/>
	          <#else>
	             <@s.submit value="添加商品" cssClass="submitbut"/>	
	          </#if>  
	       <#else>
	          <@s.submit value="添加商品" cssClass="submitbut"/>	              
           </#if>      
          </td></tr>
	   </table>				
	 </div>   
 </div>
 <div class="buttom">
  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
  <@s.hidden id="hidden_cat_value"/>
  <@s.hidden name="goods.goods_id"/>
  <@s.hidden name="info_infoattr_id" />
 </div>   
</@s.form>	 
</body>
</html>