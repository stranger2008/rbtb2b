<html>
  <head>
    <title>添加图片</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
<div class="tj_main f_left">
<div class="pageLocation">
 	当前位置:功能模块 > 图库管理 > 添加图片
   </div>
   <div class="tj_main_cont">   
   	<@s.form action="/admin_Gallerypic_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
            <tr>
             <td class="table_name">图库名称</td>
             <td>
             <@s.hidden name="gallerypic.gal_id"/>
             <@s.fielderror><@s.param>gallerypic.gal_id</@s.param></@s.fielderror>             
             <#list galleryList as gal>
                  <#if gal.gal_id==gallerypic.gal_id>
                      ${gal.title?if_exists}
                  </#if>
             </#list>
             </td>
            </tr>
           
              <tr>
             <td class="table_name" >图库图片上传<font color='red'>*</font></td>
             <td> 
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="gallerypic.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImgMulti();</script>
             			</td>
             		</tr>
             	</table>
                <@s.fielderror><@s.param>gallerypic.img_path</@s.param></@s.fielderror>
             
               	            
	        </td>
	       </tr>  
	        
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Gallery_list.action','');"/>
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