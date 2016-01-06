<html>
  <head>
    <title>我的头像</title>
	<#include "/include/uploadInc.html">
  </head>
  <body>
	<div class="postion">
  		 <a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的头像</a>
    </div>
<@s.form action="/bmall_Member_updatePhoto.action" method="post" validate="true" >
<div class="rightside f_right">
    <div class="rpostion"><h2>修改会员头像</h2></div>
    <div class="base_infor">
		<div class="table_infor f_left" >
        <table>	
	        <tr><td  valign="middle">我的头像<font color="red">*</font></td> 
	        	<td >
	        	   <#if member.logo_path?if_exists>
			       	 	<a target="_self" href="/bmall_Member_updateMemberPhoto.action"><img src="${member.logo_path?if_exists}"  width="110" height="110" alt="修改头像"></a>
			       <#else>
			       		<img src="/include/images/nopic.jpg"  width="110" height="110">
			       </#if>		
		        	             
		         </td>
		    </tr>
	    <tr ><td></td><td>
		             <table border="0" cellpadding="0" cellspacing="0">
		             		<tr> <td ></td>
		             			<td style="padding:0px;" >
		             			    <div id="fileQueue"></div>
			              			  <@s.textfield name="logo_path" id="uploadresult" cssStyle="width:300px;"/>
		             			</td>
		             			<td style="padding-left:3px;" >
		             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			              			<script>uploadOneImg();</script>
		             			</td>
		             		</tr>
		             		<tr> <td height="15px"></td><td><@s.fielderror><@s.param>logo_path</@s.param></@s.fielderror></td></tr> 	
		             		<tr  align="center"><td></td><td><@s.submit value="提  交" cssClass="submitbut"/></td></tr> 		             			             		
		              </table>		             							
			</td>	 
		</tr>
		           		         
	           		   		           
		          
        </table>
         </div>
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
  </@s.form>
</body>
</html>

