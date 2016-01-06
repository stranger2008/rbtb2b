<html>
  <head>
    <title>修改友情链接</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <#include "/include/uploadInc.html">	 
     <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){      
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>  
  </head>
  <body>


<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 友情链接管理 > 修改友情链接
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Link_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">友情链接名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="link.link_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>link.link_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">友情链接分组:</td>
             <td>
               <@s.select name="link.link_group" list="link_groupList" listValue="name" listKey="id" />
                <a href="<#if mall_type?if_exists=='b2b'>/admin_Linkgroup_list.action?ajaxRequestRandom=1'<#elseif (mall_type?if_exists)=='b2c'>/admin_Linkgroup_malllist.action?ajaxRequestRandom=1</#if>" 
             target="_blank">[分组管理]</a>
             </td>
           </tr>
           <tr>
                <td class="table_name">所属地区:</td>
                <td colspan="3" >
             	<div id="arealist"></div>
                <img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Link.area_attr')}"/>">   
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">链接地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="link.url" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>link.url</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
           <tr>
             <td class="table_name">链接图片:</td>
             <td>
             	  <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="link.img_path" id="uploadresult" cssStyle="width:300px;" value="${(link.img_path)?if_exists}"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>link.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="link.sort_no" value="${link.sort_no!0}" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>link.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
           <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="link.is_display" list=r"#{'0':'有效','1':'无效'}" value="${link.is_display!0}"/>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.is_display')}"/>"> 
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="link.link_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <@s.hidden name="mall_type" />
	       <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
		   <!--所属地区插件隐藏字段结束区域-->
	     <input type="button" name="returnList" value="返回列表"
	        onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malllist.action'</#if>,'');"/>
	     
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