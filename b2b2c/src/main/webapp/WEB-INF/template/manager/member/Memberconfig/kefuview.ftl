<html>
  <head>
    <title>在线客服设置</title>
<#include "/include/uploadInc.html">
 <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
  </head>
  <body>
   <div class="cont_main">
   	<@s.form action="/member_Memberconfig_update.action?point=1" method="post" validate="true" onsubmit="return showPicPath();">
   	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>店铺设置>在线客服设置</td>
 	</tr>
	</table>
      <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">         
             	
           <tr>
             <td  valign="middle" class="left_td">QQ:</td>
             <td>
             	<@s.textfield name="memberconfig.qq" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"/>（添加多个用“,”隔开）
             	<@s.fielderror><@s.param>memberconfig.qq</@s.param></@s.fielderror>
             </td>
           </tr>         
           <tr>
             <td  valign="middle" class="left_td">阿里旺旺:</td>
             <td>
             	<@s.textfield name="memberconfig.aliim" cssClass="txtinput" cssStyle="width:300px;" maxLength="50"/>（添加多个用“,”隔开）
             	<@s.fielderror><@s.param>memberconfig.aliim</@s.param></@s.fielderror>
             </td>
           </tr>          
           <tr>
             <td  valign="middle" class="left_td">MSN:</td>
             <td>
             	<@s.textfield name="memberconfig.msn" cssClass="txtinput" style="width:80px;" maxLength="50"/>
             	<@s.fielderror><@s.param>memberconfig.msn</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td  valign="middle" class="left_td">Skype:</td>
             <td>
             	<@s.textfield name="memberconfig.skype" cssClass="txtinput" style="width:80px;" maxLength="50"/>
             	<@s.fielderror><@s.param>memberconfig.skype</@s.param></@s.fielderror>
             </td>
           </tr>
           
           
           
        <@s.hidden name="memberconfig.web_name" cssClass="txtinput" maxLength="50" cssStyle="width:400px;"/>    
     	<@s.hidden name="memberconfig.web_title" cssClass="txtinput" maxLength="50" cssStyle="width:400px;"/>
     	<@s.hidden name="memberconfig.keywords" cssClass="txtinput" maxLength="100" cssStyle="width:400px;"/>
     	<@s.hidden name="memberconfig.back_color" id="title_color"  />   
     	<@s.hidden name="memberconfig.word_num" cssClass="txtinput" maxLength="6"/>
        <@s.hidden name="memberconfig.back_img"  id="uploadresult" cssStyle="width:300px;"/>
		<@s.hidden name="memberconfig.back_img_repeat" list=r"#{'':'默认','no-repeat':'不平铺','repeat-x':'X轴平铺','repeat-y':'Y轴平铺'}"   /> 
     	<@s.hidden name="memberconfig.back_img_position" list=r"#{'':'默认','left':'居左对齐','center':'居中对齐','right':'居右对齐'}"   />
		<@s.hidden name="memberconfig.logo_img"  id="uploadresult1" cssStyle="width:300px;"/>   
		<@s.hidden name="memberconfig.banner"  id="uploadresult2" cssStyle="width:300px;"/>      
		<@s.hidden name="memberconfig.header_img"  id="uploadresult3" cssStyle="width:300px;"/>
		<@s.hidden name="memberconfig.is_dis" list=r"#{'0':'显示','1':'不显示'}"/>           
     	<@s.hidden name="memberconfig.loc_num" cssClass="txtinput" style="width:80px;" maxLength="4"/>
     	<@s.hidden name="memberconfig.site_notice" cssClass="txtinput" cssStyle="width:500px;height:100px"
     	onkeyup="set_textarea_length(this,300);"/>
   
     	<@s.hidden name="memberconfig.site_desc" cssClass="txtinput" cssStyle="width:500px;height:100px"
     	onkeyup="set_textarea_length(this,200);"/>
            
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

  
  </body>
</html>