<html>
  <head>
    <title>添加图库</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"gallery");  
		 </#if>		 
	  });
	</script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:功能模块 > 图库管理 > 添加图库
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Gallery_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">图库标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="gallery.title" cssClass="txtinput" maxLength="600"/>
             	<@s.fielderror><@s.param>gallery.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">上传封面图片<font color='red'>*</font></td>
             <td>
             
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="gallery.img_path" id="uploadresult" cssStyle="width:300px;"/>
                 		</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             		</tr>
             	</table>
                <@s.fielderror><@s.param>gallery.img_path</@s.param></@s.fielderror>
               
             </td>
           </tr>
             <tr>
                <td class="table_name" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="gallery.cat_attr" />
	                 <a href="/admin_Gallery_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=gallery&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=gallery&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>        	             
             </td>
           </tr>
          <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>     
            <tr>
             <td class="table_name">图库说明<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="gallery.gal_desc" cssClass="txtinput" id="desc" cssStyle="width:435px;height:90px;"/>
				<@s.fielderror><@s.param>gallery.gal_desc</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
             	<@s.radio name="gallery.is_recom" list=r"#{'0':'否','1':'是'}" value="0"/>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	        <@s.hidden name="gallery.info_state" value="1"/>
	       	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Gallery_list.action','');" />
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