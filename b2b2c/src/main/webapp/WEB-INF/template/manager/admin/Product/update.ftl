<html>
  <head>
    <title>修改产品</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	  <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){      
	     //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"product");  
		 </#if> 
         //所属地区的回选
          area_select("${cfg_topareaid?if_exists}");
          
	  });
	  
	</script> 
	<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
    </style>    
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 产品管理 > 产品列表 > 修改产品
   </div>
   <div class="tj_main_cont">
   	<@s.form  action="/admin_Product_update.action" method="post" validate="true">
       <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:150px;">产品标题<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="product.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>product.title</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name"  >产品图片:</td>
             <td>
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="product.img_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(123);uploadOneImgMulti();</script>
             			</td>
             		</tr>
             	</table>  
             	<@s.fielderror><@s.param>product.img_path</@s.param></@s.fielderror>
             </td>
           </tr>

           </tr>
           <tr>
            <td class="table_name">所属分类<font color='red'> *</font></td>
             <td >
             <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="product.cat_attr" />
              		<a href="/admin_Product_cate.action?cat_attr=0&product.product_id=${product.product_id?if_exists}">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=product&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=product&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>  

             </td>    
           </tr>
            <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>  
           <tr style="display:none;">
             <td class="table_name">属性:</td>
             <td >
                	<@s.textfield name="product.attr_desc" cssClass="txtinput" cssStyle="width:300px;" />
             	<@s.fielderror><@s.param>product.attr_desc</@s.param></@s.fielderror>
             </td>
           <tr>
           <td class="table_name">所在地区:</td>
             <td>
             	  <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
               <td class="table_name">产品型号:</td>
             <td >
             	<@s.textfield name="product.model" cssStyle="width:200px;" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>product.model</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             
              <td class="table_name">产品规格:</td>
             <td >
             	<@s.textfield name="product.standard" cssClass="txtinput" cssStyle="width:200px;" maxLength="50"/>
             	<@s.fielderror><@s.param>product.standard</@s.param></@s.fielderror><br/>
             </td>
              
           </tr>
           <tr>
             <td class="table_name">产品品牌:</td>
             <td >
             	<@s.textfield name="product.brand" cssClass="txtinput" cssStyle="width:200px;" maxLength="50"/>
             	<@s.fielderror><@s.param>product.brand</@s.param></@s.fielderror>
             </td>
              
           </tr>
           <tr style="display:none;">
             <td class="table_name">自定义产品分类:</td>
             <td  >
              <@s.select name="product.self_cat_id" list="membercatList" listValue="cat_name" listKey="cat_id" headerKey="0" headerValue="请选择" />
              <@s.fielderror><@s.param>product.self_cat_id</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">详细说明<font color='red'> *</font></td>
             <td >
             	<@s.textarea name="product.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             	<@s.fielderror><@s.param>product.content</@s.param></@s.fielderror>
             </td>
           </tr>
              <tr> 
             <td class="table_name">是否推荐:</td>
             <td >
              <@s.radio name="product.is_recom" list=r"#{'0':'否','1':'是'}"   /> 
              <@s.fielderror><@s.param>product.is_recom</@s.param></@s.fielderror>
             </td>
           </tr>
             
             <tr> 
             <td class="table_name">点击量:</td>
             <td >
             	<@s.textfield name="product.clicknum" cssClass="txtinput" cssStyle="width:100px;" maxLength="100" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>product.clicknum</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>"> 
             </td>
           </tr>
             <tr> 
             <td class="table_name">内容收费:</td>
             <td >
             	<@s.textfield name="product.fare" cssClass="txtinput" cssStyle="width:100px;" maxLength="100" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>product.fare</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>"> 
             </td>
           </tr>
           <tr>
             <td class="table_name">信息状态:</td>
             <td >
              <table>
               <tr>
               <td colspan="3"><@s.radio name="product.info_state" list=r"#{'1':'正常','3':'禁用'}"  /></td>
               <@s.fielderror><@s.param>product.info_state</@s.param></@s.fielderror>
               </tr>
              </table>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="product.cust_id"/>
	       <@s.hidden name="product.product_id" id="id"/>
           ${listSearchHiddenField?if_exists}
	        <@s.hidden name="product.infoattr_id" />
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <input type="button" name="sumit" value="重置" onclick="resetModify('/admin_Product_view.action?product.product_id=');" />
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value" />
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Product_list.action','');"/>
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