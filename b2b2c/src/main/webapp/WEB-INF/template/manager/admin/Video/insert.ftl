<html>
  <head>
    <title>添加视频</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){ 
	  	 <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"video");  
		 </#if>		  
	  });
	</script>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 视频管理 > 添加视频
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Video_insert.action" method="post" validate="true" onsubmit="return showPicPath();">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">视频标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="video.title" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>video.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">标题图片<font color='red'>*</font></td>
             <td>
             
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			    <@s.textfield name="video.img_path" id="uploadresult" cssStyle="width:300px;"/>
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
             <td class="table_name">上传视频<font color='red'>*</font></td>
             <td>
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			    <input name="video.video_path" id="uploadresult1" style="width:300px;"/>
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
             <td class="table_name">所属分类<font color='red'>*</font></td>
             <td>
                <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="video.cat_attr" />
	                 <a href="/admin_Video_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=video&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=video&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
            <tr>
             <td class="table_name">视频关键字:</td>
             <td>
             	<@s.textfield name="video.keyword" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>video.keyword</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">视频说明:</td>
             <td>
             	<@s.textarea name="video.video_desc" cssClass="txtinput" cssStyle="width:435px;height:90px;" id="desc" />
             	<@s.fielderror><@s.param>video.video_desc</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
             	<@s.radio name="video.is_recom" list=r"#{'0':'否','1':'是'}" value="0"/>
             	<@s.fielderror><@s.param>video.is_recom</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.textfield name="video.fare" value="0" cssClass="txtinput" style="width:80px;" onkeyup="checkNum(this);" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>video.fare</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield name="video.clicknum" value="0" cssClass="txtinput" style="width:80px;" onkeyup="checkNum(this);" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>">
             	<@s.fielderror><@s.param>video.clicknum</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>	       
	       <@s.hidden name="video.info_state" value="1"/>
	       ${listSearchHiddenField?if_exists}
	      	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Video_list.action';document.forms[0].submit();"/>
	     </div>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
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