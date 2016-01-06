<html>
  <head>
    <title>添加商品品牌表</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript" src="/include/js/admin/member.js"></script>	
    <script type="text/javascript">
	  $(document).ready(function(){
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"goods");           
         //提交表单
         $("#com_insert").submit(function(){
              var area_attr_str="";
              $("input:hidden[name='all_area_id_str']").each(function(){
                  area_attr_str+=$(this).val()+"|";
              }) 
              $("#area_attr_str").val(area_attr_str);     
              return true;
              alert(area_attr_str);
         }); 
	  });
	  
	</script>
	<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
	 .oper_add{color:#990000;}
	 .oper{margin-left:20px;color:#990000;}
	 .cert_td1{width:160px;padding-right:20px;text-align:right;}
	 .cat_sel{margin-left:7px;}
</style>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 添加商品品牌
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsbrand_insert.action" method="post" validate="true" id="com_insert" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		           <tr>
		             <td class="table_name">品牌名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsbrand.brand_name" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
		             	<@s.fielderror><@s.param>goodsbrand.brand_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">品牌网址:</td>
		             <td>
		             	<@s.textfield name="goodsbrand.brand_site" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
		             	<@s.fielderror><@s.param>goodsbrand.brand_site</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">品牌logo:</td>
		             <td>
			              <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			  <@s.textfield name="goodsbrand.logo" id="uploadresult" cssStyle="width:300px;"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
		              			<script>uploadOneImg();</script>
	             			</td>
	             		</tr>
	             	</table>  
		             	<@s.fielderror><@s.param>goodsbrand.logo</@s.param></@s.fielderror>
		             </td>
		           </tr>

		           <tr>
		             <td class="table_name">商品分类串关联:</td>
		             <td>
		             
		             <input type="hidden" id="area_attr_str" name="area_attr_str" value="${area_attr_str?if_exists}">
	             	<div>
		                 <table border="0" cellpadding="0" cellspacing="0">
		                 	<tr>
			                 	<td  colspan="2" class="tdbottom">
			                 		<div id="divlist" style="margin-left:-10px;"></div>
			                 	</td>
		                 	   <td class="tdbottom" >
			                 	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
			                 	   <@s.fielderror><@s.param>goodsbrand.goods_attr</@s.param></@s.fielderror>
		                 	   </td>
		                 	</tr>
		                 </table>	
		                 <div id="show_add_cat">
		                     
		                 </div>
			         </div>

		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsbrand.sort_no" cssClass="txtinput" maxLength="11" value="0" cssStyle="width:80px;" onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>goodsbrand.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">状态:</td>
		             <td>
		              <@s.radio name="goodsbrand.info_state" list=r"#{'0':'正常','1':'禁用'}" value="0" /> 
           			  <@s.fielderror><@s.param>goodsbrand.info_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
		             <tr> 
			             <td class="table_name">是否推荐:</td>
			             <td >
			              	<@s.radio name="goodsbrand.is_recom" list=r"#{'0':'否','1':'是'}" value='0'  /> 
			              	<@s.fielderror><@s.param>goodsbrand.is_recom</@s.param></@s.fielderror>
			             </td>
		           </tr>
		            <tr>
		             <td class="table_name">详细说明:</td>
		             <td>
		             <@s.textarea name="goodsbrand.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
					<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
					<script type="text/javascript">
				     CKEDITOR.replace( 'content');  
					</script>
		            <@s.fielderror><@s.param>goodsbrand.content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	         <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsbrand_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
</body>
</html>

