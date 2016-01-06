<html>
  <head>
    <title>修改视频</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
    <script type="text/javascript">
	  $(document).ready(function(){      
         //所属分类的回选
          <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"video");  
		 </#if>  
	  });
	</script> 
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Video_update.action" method="post" onsubmit="return showPicPath();" validate="true">
       <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>视频管理>修改视频</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">视频标题<span class="mustfield">*</span></td>
             <td width="83%">
             	<@s.textfield name="video.title" cssClass="txtinput" maxLength="600" cssStyle="width:400px;"/>
             	<@s.fielderror><@s.param>video.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">上传图片<span class="mustfield">*</span></td>
             <td>
             <table border="0" cellpadding="0" cellspacing="0">
         <tr>
             <td style="padding:0px;">
             	<div id="fileQueue"></div>
	            <@s.textfield name="video.img_path" id="uploadresult" cssStyle="width:300px;" />
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
		  <@s.fielderror><@s.param>video.img_path</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">上传视频<span class="mustfield">*</span></td>
             <td>
             
               <table border="0" cellpadding="0" cellspacing="0">
            <tr>
             <td style="padding:0px;">
             	<div id="fileQueue1"></div>
	             <@s.textfield name="video.video_path" id="uploadresult1" cssStyle="width:300px;"/>
             </td>
             <td style="padding-left:3px;">
             	<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
               <script>uploadComponent("uploadifyfile1","uploadresult1","fileQueue1","flash",false);</script>              
            </td>
           </tr>
           </table>
           <@s.fielderror><@s.param>video.video_path</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">所属分类：</font></td>
             <td>
               <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="video.cat_attr" />
              		<a href="/member_Video_cate.action?cat_attr=0&video.video_id=${video.video_id?if_exists}">[重新选择]</a>
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
             <td valign="middle" class="left_td">视频关键字:</td>
             <td>
             	<@s.textfield name="video.keyword" cssClass="txtinput" maxLength="10"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">视频说明:</td>
             <td>
             	<@s.textarea name="video.video_desc" cssClass="txtinput" cssStyle="width:435px;height:90px;" id="desc" />
             </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
              <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="video.video_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="video.cust_id" value="0"/>
	       <@s.hidden name="video.is_recom" value="0"/>
	       <@s.hidden name="video.fare" value="0"/>
	       <@s.hidden name="video.clicknum" value="0"/>
	       <@s.hidden name="video.info_state" value="0"/>
	       ${listSearchHiddenField?if_exists}
	         <@s.hidden name="video.infoattr_id" />
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
	      <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Video_list.action','');"/>
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