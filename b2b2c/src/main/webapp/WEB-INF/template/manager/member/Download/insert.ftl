<html>
<head>
<title>添加下载</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"download");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
	<#include "/include/uploadInc.html">
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Download_insert.action" method="post" validate="true" onsubmit="return showPicPath();">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>下载管理>添加下载</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">下载标题<span class="mustfield">*</span></td>
    <td width="83%">
    <@s.textfield name="download.title" cssClass="txtinput" maxLength="600" cssStyle="width:400px;"/>
    <@s.fielderror><@s.param>download.title</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
  <tr>
    <td valign="middle" class="left_td">标题图片<span class="mustfield">*</span></td>
    <td>
    
     <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="download.img_path" id="uploadresult" cssStyle="width:300px;"/>
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
        <div>
     </div>
    </td>
  </tr>
  
    <tr>
         <td valign="middle" class="left_td">上传文件<span class="mustfield">*</span></td>
         <td>
         
          <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			  <@s.textfield name="download.file_path" id="uploadresult1" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             				<script>uploadComponent("uploadifyfile1","uploadresult1","fileQueue1","file",false);</script>
             			</td>
             			<td>(可引用外部链接地址，直接粘贴至文本框)</td>
             		</tr>
             	</table> 
             	 <@s.fielderror><@s.param>download.file_path</@s.param></@s.fielderror>             
     </td>
         
    </tr>
  
  
   <tr>
             <td valign="middle" class="left_td">所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="download.cat_attr" />
	                 <a href="/member_Download_cate.action?cat_attr=0">[重新选择]</a>
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
    <td valign="middle" class="left_td">文件大小<span class="mustfield">*</span></td>
    <td>
       <@s.textfield name="download.file_size" cssClass="txtinput" maxLength="20"/>
       <@s.fielderror><@s.param>download.file_size</@s.param></@s.fielderror>
       <@s.select name="download.size_unit" list=r"#{'KB':'KB','MB':'MB','GB':'GB','TB':'TB'}" headerKey="0" headerValue="请选择"/>  
       <@s.fielderror><@s.param>download.size_unit</@s.param></@s.fielderror>
   </td>
  </tr>
  
  
  <tr>
    <td valign="middle" class="left_td">软件版本:</td>
    <td>
    <@s.textfield name="download.version" cssClass="txtinput" maxLength="50"/>
   </td>
  </tr>
  
  
  <tr>
    <td valign="middle" class="left_td">文件类型<span class="mustfield">*</span></td>
    <td>
     <@s.select name="download.file_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
     <@s.fielderror><@s.param>download.file_type</@s.param></@s.fielderror>
    </td>
  </tr>
  
  
  <tr>
    <td valign="middle" class="left_td">下载关键字:</td>
    <td>
     <@s.textfield name="download.keyword" cssClass="txtinput" maxLength="10"/>
   </td>
  </tr>
  
  
     <tr>
    <td valign="middle" class="left_td" >下载说明<span class="mustfield">*</span></td>
    <td>
	   <@s.textarea name="download.down_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="down_desc" />
	   <@s.fielderror><@s.param>download.down_desc</@s.param></@s.fielderror>
    </td>
  </tr>
 
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="download.is_recom" value="0"/>
	       <@s.hidden name="download.fare" value="0"/>
	       <@s.hidden name="download.clicknum" value="0"/>
	       <@s.hidden name="download.info_state" value="0"/>
	       <@s.hidden name="download.down_num" value="0"/>
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="linkToInfo('/member_Download_list.action','');"/>
    </td>
  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
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
