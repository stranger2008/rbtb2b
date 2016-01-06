<html>
  <head>
    <title>修改图库</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
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
	<#include "/include/uploadInc.html">
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Gallery_update.action" method="post" validate="true">
   	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>图库管理>修改图库</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
        <#if gallery.info_state?if_exists=='2'>
           <tr>
             <td width="17%" valign="middle" class="left_td" style="font-weight:bold;">未通过理由:</td>
             <td>
                <@s.label name="gallery.no_reason" cssClass="txtinput"/>
             </td>
           </tr>
           </#if>
           <tr>
             <td width="17%" valign="middle" class="left_td">图库标题<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="gallery.title" cssStyle="width:300px" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>gallery.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">上传封面图片<span class="mustfield">*</span></td>
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
             <td valign="middle" class="left_td">所属分类<span class="mustfield">*</span></td>
             <td>
                <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="gallery.cat_attr" />
              		<a href="/member_Gallery_cate.action?cat_attr=0&gallery.gal_id=${gallery.gal_id?if_exists}">[重新选择]</a>
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
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
             <td valign="middle" class="left_td">图库说明<span class="mustfield">*</span></td>
             <td>
             <@s.textarea name="gallery.gal_desc" cssClass="txtinput" id="desc" cssStyle="width:435px;height:90px;"/>
             <@s.fielderror><@s.param>gallery.gal_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td colspan="4" class="subbut">
               <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="gallery.gal_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="gallery.cust_id"/>
	       <@s.hidden name="gallery.is_recom" value="0"/>
	       <@s.hidden name="gallery.fare" value="0"/>
	       <@s.hidden name="gallery.clicknum" value="0"/>
	       <@s.hidden name="gallery.info_state" value="0"/>
	       ${listSearchHiddenField?if_exists}
	         <@s.hidden name="gallery.infoattr_id" />
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Gallery_list.action','');"/>
             </td>
           </tr>
         </table>
	  </@s.form>
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