<html>
  <head>
    <title>企业站设置</title>
<#include "/include/uploadInc.html">
 <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
  </head>
  <body>
   <div class="cont_main">
   	<@s.form action="/member_Memberconfig_update.action" method="post" validate="true" onsubmit="return showPicPath();">
   	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>店铺设置>企业站设置</td>
 	</tr>
	</table>
      <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="19%" valign="middle" class="left_td">企业站名称:</td>
             <td>
             	<@s.textfield name="memberconfig.web_name" cssClass="txtinput" maxLength="50" cssStyle="width:400px;"/>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">首页SEO标题:</td>
             <td>
             	<@s.textfield name="memberconfig.web_title" cssClass="txtinput" maxLength="50" cssStyle="width:400px;"/>
             </td>
           </tr>
           
            <tr>
             <td  valign="middle" class="left_td">网站关键字:</td>
             <td>
             	<@s.textfield name="memberconfig.keywords" cssClass="txtinput" maxLength="100" cssStyle="width:400px;"/>
             </td>
           </tr>
           <tr>
             <td  valign="middle" class="left_td">企业站背景色:</td>
             <td>
             	<@s.textfield name="memberconfig.back_color" id="title_color" cssClass="iColorPicker" />
             	<@s.fielderror><@s.param>memberconfig.back_color</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td  valign="middle" class="left_td">公司简介显示字数:</td>
             <td>
             	<@s.textfield name="memberconfig.word_num" cssClass="txtinput" maxLength="6"/>
             	<@s.fielderror><@s.param>memberconfig.word_num</@s.param></@s.fielderror>
             </td>
           </tr>
              <tr>
             <td valign="middle" class="left_td">自定义背景图片:</td>
             <td>
             
             	<table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
	              			 <@s.textfield name="memberconfig.back_img"  id="uploadresult" cssStyle="width:300px;"/>
	              			<div id="fileQueue"></div>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImg("uploadifyfile","uploadresult","fileQueue");</script>
             			</td>
             		</tr>
             	</table>
	              
             </td>
           </tr>
           		<tr>
             		 <td valign="middle" class="left_td">平铺及对齐方式:</td>
             		<td><@s.select name="memberconfig.back_img_repeat" list=r"#{'':'默认','no-repeat':'不平铺','repeat-x':'X轴平铺','repeat-y':'Y轴平铺'}"   /> 
             		<@s.select name="memberconfig.back_img_position" list=r"#{'':'默认','left':'居左对齐','center':'居中对齐','right':'居右对齐'}"   /> </td>
             		</tr>
          <tr>
             <td valign="middle" class="left_td">自定义LOGO:</td>
             <td>
             
             	<table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
				               <@s.textfield name="memberconfig.logo_img"  id="uploadresult1" cssStyle="width:300px;"/>
				              <div id="fileQueue1"></div>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
				              <script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td style="padding-left:3px;">
				              (宽:160px 高:80px)
             			</td>
             		</tr>
             	</table>
	              
		      </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">Banner:</td>
             <td>
             	<table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
				               <@s.textfield name="memberconfig.banner"  id="uploadresult2" cssStyle="width:300px;"/>
				              <div id="fileQueue2"></div>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile2" id="uploadifyfile2"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult2');"/>
				              <script>uploadImg("uploadifyfile2","uploadresult2","fileQueue2");</script>
             			</td>
             			<td style="padding-left:3px;">
				              (宽:960px 高:80px)
             			</td>
             		</tr>
             	</table>
		      </td>
           </tr>
           
           <tr>
             <td valign="middle" class="left_td">自定义横幅:</td>
             <td>
             
             	<table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
				              <@s.textfield name="memberconfig.header_img"  id="uploadresult3" cssStyle="width:300px;"/>
				              <div id="fileQueue3"></div>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile3" id="uploadifyfile3"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult3');"/>
				              <script>uploadImg("uploadifyfile3","uploadresult3","fileQueue3");</script>
             			</td>
             			<td style="padding-left:3px;">
				              (宽:960px 高:200px)
             			</td>
             		</tr>
             	</table>

             </td>
           </tr>
           <tr>
             <td  valign="middle" class="left_td">是否显示访问统计数:</td>
             <td>
             	<@s.radio name="memberconfig.is_dis" list=r"#{'0':'显示','1':'不显示'}"/>
             </td>
           </tr>
            <tr>
             <td  valign="middle" class="left_td">访问统计数:</td>
             <td>
             	<@s.textfield name="memberconfig.loc_num" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberconfig.loc_num</@s.param></@s.fielderror>
             </td>
           </tr>
           
             	<@s.hidden name="memberconfig.qq" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"/>      
             	<@s.hidden name="memberconfig.aliim" cssClass="txtinput" cssStyle="width:300px;" maxLength="50"/>   
             	<@s.hidden name="memberconfig.msn" cssClass="txtinput" style="width:80px;" maxLength="50"/>   
             	<@s.hidden name="memberconfig.skype" cssClass="txtinput" style="width:80px;" maxLength="50"/>
     
             <tr>
             <td   valign="middle" class="left_td">网站公告:</td>
             <td>
             	<@s.textarea name="memberconfig.site_notice" cssClass="txtinput" cssStyle="width:500px;height:100px"
             	onkeyup="set_textarea_length(this,300);"/>
             </td>
           </tr>
           <tr>
             <td   valign="middle" class="left_td">网站描述:</td>
             <td>
             	<@s.textarea name="memberconfig.site_desc" cssClass="txtinput" cssStyle="width:500px;height:100px"
             	onkeyup="set_textarea_length(this,200);"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom" style="text-align:center;margin-top:10px;">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="memberconfig.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" cssClass="sub"/>
	     </div>
	     
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