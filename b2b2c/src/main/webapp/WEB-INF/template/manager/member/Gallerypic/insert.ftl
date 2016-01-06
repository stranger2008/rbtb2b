<html>
  <head>
    <title>添加图片</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Gallerypic_insert.action" method="post" validate="true">
      	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>图库管理>添加图片</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
            <tr>
             <td width="17%" valign="middle" class="left_td">图库名称</td>
             <td width="83%">
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
             <td valign="middle" class="left_td">图库图片上传<span class="mustfield">*</span></td>
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
	        
	       <tr>
	         <td colspan="4" class="subbut">
	          <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	          <@s.hidden name="nav_name"/>
	          
	          ${listSearchHiddenField?if_exists}
	          <@s.submit value="保存" cssClass="sub"/>
	          <input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Gallery_list.action','');"/>
	         </td>
           </tr>
         </table>
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