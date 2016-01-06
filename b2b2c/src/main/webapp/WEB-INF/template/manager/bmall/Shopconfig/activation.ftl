<html>
<head>
<title>激活我的店铺</title>
<#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
</head>
<body>
 <@s.form action="/bmall_Shopconfig_bmallactivation.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>您还未激活店铺</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >
            <tr><td class="firsttd" >店铺名称<font color='red'>*</font></td><td>
        	<@s.textfield name="shopconfig.shop_name" cssClass="txtinput" />
         	<@s.fielderror><@s.param>shopconfig.shop_name</@s.param></@s.fielderror>
            </td></tr>  
            <tr><td  class="firsttd">经营模式<font color='red'>*</font></td><td>
             <@s.radio name="shopconfig.busi_mode" list="commparaList" listValue="para_key" listKey="para_value" value='0'/>
             <@s.fielderror><@s.param>shopconfig.busi_mode</@s.param></@s.fielderror>
            </td></tr> 
              <tr>
         	<td colspan="2" align="center">
         		<@s.submit value="激活店铺" cssClass="submitbut"/></p>
         	</td>
        </tr>    
          </table>
       </div>
     </div>
</div>
 </@s.form>
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

















