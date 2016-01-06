<html>
  <head>
    <title>修改模板</title>
    <script type="text/javascript" src="/include/js/admin/membertemplate.js"></script>
    <#include "/include/uploadInc.html">
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 企业站模板管理 > 修改模板
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membertemplate_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">模板名称<font color='red'>*</font></td>
             <td colspan="2">
             	<@s.textfield name="membertemplate.temp_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>membertemplate.temp_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">模板编码：</td>
             <td colspan="2">
             	<@s.label name="membertemplate.temp_code" cssClass="txtinput"/>
             </td>
           </tr>
           
           <tr>
             <td width="19%" class="table_name">模板样式<font color='red'>*</font></td>
             <td colspan="2">
             	<@s.textfield name="membertemplate.temp_loc" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>membertemplate.temp_loc</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td width="19%" class="table_name">作者<font color='red'>*</font></td>
             <td colspan="2">
             	<@s.textfield name="membertemplate.author" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>membertemplate.author</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name" >模板图片上传<font color='red'>*</font></td>
             <td colspan="2">
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="membertemplate.temp_img" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             		</tr>
             	</table>  
	               <@s.fielderror><@s.param>membertemplate.temp_img</@s.param></@s.fielderror>     	            
	        </td>
	       </tr>  
	        <tr>
             <td width="19%" class="table_name">价格<font color='red'>*</font></td>
             <td width="19%">
             	<@s.textfield name="membertemplate.price" cssClass="txtinput" maxLength="6"/>
             	<@s.fielderror><@s.param>membertemplate.price</@s.param></@s.fielderror>
             </td>
            <td>
             	<@s.radio name="membertemplate.p_unit" list=r"#{'0':'积分','1':'资金'}"/>
             	<@s.fielderror><@s.param>membertemplate.p_unit</@s.param></@s.fielderror>
             </td>
           </tr>
	       
            <tr>
             <td class="table_name">排序:</td>
             <td colspan="2">
             	<@s.textfield name="membertemplate.sort_no" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>membertemplate.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
           <tr>
             <td class="table_name">会员级别<font color='red'>*</font></td>
             <td colspan="2"> 
                <@s.checkboxlist id="status" name="membertemplate.mem_level" list="memberlevleList" listKey="level_id" headerKey="0" listValue="level_name" value="#request.level"  >
                </@s.checkboxlist>
                <@s.fielderror><@s.param>membertemplate.mem_level</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">文件的位置<font color='red'>*</font></td>
             <td colspan="2">
             	<@s.textfield name="membertemplate.file_pos" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>membertemplate.file_pos</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="membertemplate.temp_id"/>
	        <@s.hidden name="mall_type" />  
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Membertemplate_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Membertemplate_malllist.action'</#if>,'');" />
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