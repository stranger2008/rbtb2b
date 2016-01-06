<html>
  <head>
    <title>添加广告信息</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <#include "/include/uploadInc.html">
     <script type="text/javascript" src="/include/js/admin/advinfo.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
		  cate_select(${cfg_topcatid?if_exists},1,"${advpos.module_type?if_exists}");	
		  area_select("${cfg_topareaid?if_exists}");
		  });
    </script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:网站管理 > 广告管理 > 广告管理 > 添加广告
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Advinfo_insert.action?posid=${posid?if_exists}&two_pages_link=no" method="post" validate="true" onsubmit="return showPicPath();">
    <@s.hidden name="type" id="type"/>
        <table class="wwtable" cellspacing="1" cellpadding="8">
         <tr>
             <td class="table_name" width="19%">广告位名称:</td>
             <td>
                <@s.label name="dds" value="${(advpos.pos_name)?if_exists}"/>
                </span>
             </td>
             <td class="table_name" width="19%">广告类型:</td>
             <td style="display:none;">
             	<@s.select id="advtype" name="advpos.pos_type" list="advcommparaList" listValue="para_key" listKey="para_value"/>
             </td>
             <td ><span id="idvalue"></span></td>
           </tr>
           
           <tr id="category" style="display:none;">
             <td class="table_name" width="19%">模板类型:</td>
             <td ><span id="modulevalue"></span></td>
             <td style="display:none;">
             	<@s.select name="advpos.module_type" list="modulecommparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="无所属模快"/>
             </td>
             <td class="table_name" width="19%">分类:</td>
             <td>
                <div id="divlist"></div>  
                <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>            	            
             </td>      
           </tr>
           
            <tr>
             <td width="19%" class="table_name">广告名称<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="advinfo.adv_name" cssClass="txtinput" cssStyle="width:600px;" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.adv_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name" >所在地区:</td>             
              <td colspan="3">
             	<table>
             		<tr>
             			<td class="tdbottom">
             				<div id="arealist"></div>
             			</td>
             			<td class="tdbottom">
             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
	              		</td>
	              	</tr>
	            </table>       
             </td>
           </tr>
           <tr id="moduletype" style="display:none;">
             <td class="table_name">所属模块<font color='red'>*</font></td>
             <td colspan="3">
                <@s.select name="advinfo.module_type" list="modulecommparaList" id="paratype" listValue="module_name" listKey="module_type" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>advinfo.module_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="keyword" style="display:none;">
             <td class="table_name">关键字<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textfield name="advinfo.keyword" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.keyword</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="catattr" style="display:none;">
             <td class="table_name">分类标识串<font color='red'>*</font></td>
             <td colspan="3">
	             <div id="divlist"></div>  
                 <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror> 
             </td>
           </tr>
             <tr id="advimagemulti" style="display:none;">
             <td class="table_name" >上传图片<font color='red'>*</font></td>
             <td colspan="3">
                  <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
             			    
	              			<@s.textfield name="img_pathmulti"   id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             			    
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImgMulti();</script>
             			</td>
             			<td>
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>img_pathmulti</@s.param></@s.fielderror> 
             			</td>
             		</tr>
             	</table>     	            
	        </td>
	       </tr >  
            <tr  id="advimage" style="display:none;">
             <td class="table_name">上传图片<font color='red'>*</font></td>
             <td colspan="3">
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			<@s.textfield  name="img_path"   id="uploadresult1" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td>
             			  &nbsp;&nbsp;<@s.fielderror><@s.param>advinfo.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
		       
             </td>
           </tr>
           
           <tr id="advlink" style="display:none;">
             <td class="table_name">链接地址<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textfield name="advinfo.link_url" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
	            <@s.fielderror><@s.param>advinfo.link_url</@s.param></@s.fielderror> 
	            <img src="/include/images/light.gif" style="vertical-align:middle;" alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
            <tr id="advflash" style="display:none;">
             <td class="table_name">上传flash<font color='red'>*</font></td>
             <td colspan="3">
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue2"></div>
	              			<@s.textfield name="advinfo.flash_url"   id="uploadresult2" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile2" id="uploadifyfile2"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult2');"/>
	              			<script>uploadComponent("uploadifyfile2","uploadresult2","fileQueue2","flash",false);</script>
             			</td>
             			<td>
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>advinfo.flash_url</@s.param></@s.fielderror> 
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
           <tr id="advtitle" style="display:none;">
             <td class="table_name">显示标题<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textfield name="advinfo.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
	            <@s.fielderror><@s.param>advinfo.title</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr id="advcontent" style="display:none;">
             <td class="table_name">显示描述<font color='red'>*</font></td>
             <td colspan="3">
                <@s.textarea name="advinfo.content" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
                <@s.fielderror><@s.param>advinfo.content</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr id="advcode" style="display:none;">
             <td class="table_name">广告代码<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textarea name="advinfo.adv_code" cssClass="txtinput" rows="5" cssStyle="width:600px;"/>
	            <@s.fielderror><@s.param>advinfo.adv_code</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr>
             <td class="table_name">广告介绍:</td>
             <td colspan="3">
	            <@s.textarea name="advinfo.adv_desc" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
	            <@s.fielderror><@s.param>advinfo.adv_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <tr id="advintr" style="display:none;">
             <td width="19%" class="table_name">信息标识<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield id="intrvalue" name="advinfo.info_id" value="0" cssClass="txtinput" cssStyle="width:50px;" maxLength="100"/>
             	<input type="button" name="info_id" value="选择" onclick="isview('${(advpos.module_type)?if_exists}');"/>
             	<@s.fielderror><@s.param>advinfo.info_id</@s.param></@s.fielderror>
             </td>
           </tr>
             <td class="table_name">播放时间<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield id="txtStartDate" name="advinfo.start_date" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})"/>
             	 
             	<@s.textfield id="txtEndDate" name="advinfo.end_date" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})"/>
             	
             	 
             	<@s.fielderror><@s.param>advinfo.start_date</@s.param></@s.fielderror>
             	<@s.fielderror><@s.param>advinfo.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">客户标识:</td>
             <td colspan="3">
	            <@s.textfield name="advinfo.cust_id" value="0" cssClass="txtinput" maxLength="20"/>
	            <@s.fielderror><@s.param>advinfo.cust_id</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">广告状态:</td>
             <td colspan="3">
	            <@s.radio name="advinfo.adv_state" list=r"#{'0':'已审核','1':'未审核'}" value="0"/>
	            <@s.fielderror><@s.param>advinfo.adv_state</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">点击统计:</td>
             <td colspan="3">
             	<@s.radio name="advinfo.iscount" list=r"#{'0':'开启','1':'关闭'}" value="1"/>
             	<@s.fielderror><@s.param>advinfo.iscount</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">点击次数:</td>
             <td colspan="3">
             	<@s.textfield name="advinfo.addnum" value="0" cssClass="txtinput" cssStyle="width:50px;" maxLength="11"/>
             	<@s.fielderror><@s.param>advinfo.addnum</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">备注:</td>
             <td colspan="3">
             	<@s.textarea name="advinfo.remark" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>advinfo.remark</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>  
	       
	       ${listSearchHiddenField?if_exists}   
	       <@s.hidden name="advpos.pos_id"/>   
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	        <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Advinfo_list.action?posid=${posid?if_exists}&two_pages_link=no','');"/>
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