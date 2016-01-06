<html>
  <head>
    <title>修改下载</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){      
         //所属分类的回选
           <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"download");  
		 </#if>
	  });
	</script> 
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:功能模块 > 下载管理 > 下载列表 > 修改下载
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Download_update.action" method="post" validate="true" onsubmit="return showPicPath();">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">下载标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="download.title" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>download.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">标题图片<font color='red'>*</font></td>
             <td>
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			<@s.textfield name="download.img_path" id="uploadresult"  cssStyle="width:300px;"/>
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
                  <@s.fielderror><@s.param>download.img_path</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">上传文件<font color='red'>*</font></td>
             <td>
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			<div id="fileQueue1"></div>
				         <@s.textfield name="download.file_path"  id="uploadresult1"  cssStyle="width:300px;"/>     
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             				  <script>uploadComponent("uploadifyfile1","uploadresult1","fileQueue1","file",false);</script>
             			</td>
             			<td>&nbsp;&nbsp;<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.file_path')}"/>"></td>
             		</tr>
             	</table>
               <@s.fielderror><@s.param>download.file_path</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="download.cat_attr" />
	                 <a href="/admin_Download_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=download&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=download&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
             <td class="table_name">文件大小<font color='red'>*</font></td>
             <td>
                <@s.textfield name="download.file_size" cssClass="txtinput" maxLength="20"/>
                <@s.fielderror><@s.param>download.file_size</@s.param></@s.fielderror>
             	<@s.select name="download.size_unit" list=r"#{'KB':'KB','MB':'MB','GB':'GB','TB':'TB'}" />  
             	<@s.fielderror><@s.param>download.size_unit</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">软件版本<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="download.version" cssClass="txtinput" maxLength="50"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">文件类型<font color='red'>*</font></td>
             <td>
             
             <@s.select name="download.file_type" list="commparaList" listValue="para_key" listKey="para_value" />
             <@s.fielderror><@s.param>download.file_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">下载关键字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="download.keyword" cssClass="txtinput" maxLength="20"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">下载说明:</td>
             <td>
             	<@s.textarea name="download.down_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="desc" />
				<@s.fielderror><@s.param>download.down_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否推荐:</td>
             <td>
             	<@s.radio name="download.is_recom" list=r"#{'0':'否','1':'是'}"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.textfield name="download.fare"  cssClass="txtinput" style="width:80px;"  onkeyup="checkNum(this);" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>download.fare</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield name="download.clicknum" cssClass="txtinput" style="width:80px;" onkeyup="checkNum(this);" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>">
             	<@s.fielderror><@s.param>download.clicknum</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">下载次数:</td>
             <td>
             	<@s.textfield name="download.down_num" cssClass="txtinput" style="width:80px;" onkeyup="checkNum(this);" maxLength="10"/>
             	<@s.fielderror><@s.param>download.down_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">信息状态:</td>
             <td>
             	<@s.radio name="download.info_state" list=r"#{'1':'正常','3':'禁用'}"/>                	            
             </td>
           </tr>       
    <tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="download.down_id"/>
	       <@s.hidden name="download.cust_id"/>	       
	       ${listSearchHiddenField?if_exists}
	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Download_list.action','');"/>
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