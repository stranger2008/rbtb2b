<html>
  <head>
    <title>修改会员等级设置</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统设置 > 修改会员等级设置
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Malllevelset_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
		          
		           <tr>
		             <td class="table_name">等级名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.level_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>malllevelset.level_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">积分下限<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.inter_lower" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>malllevelset.inter_lower</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">积分上限<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.inter_height" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>malllevelset.inter_height</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">区别卖家买家<font color='red'>*</font></td>
		             <td>
		             <@s.radio name="malllevelset.mem_type" list=r"#{'0':'卖家','1':'买家'}"  /> 
		             	<@s.fielderror><@s.param>malllevelset.mem_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">级别图标</td>
		             <td>
		             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="malllevelset.img_url" id="uploadresult" cssStyle="width:300px;"/>
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
		            <@s.fielderror><@s.param>malllevelset.img_url</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">默认折扣率<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.discount" cssClass="txtinput" maxLength="20"/>
		             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Malllevelset.discount')}"/>"> 
		             	<@s.fielderror><@s.param>malllevelset.discount</@s.param></@s.fielderror>
		             </td>
		           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	        <@s.hidden name="malllevelset.level_id" /> 
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Malllevelset_list.action','');"/>
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

