<html>
  <head>
    <title>修改举报</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Memberreport_update.action" method="post" validate="true">
   	 	 <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>投诉举报>修改举报</td>
      </tr>
    </table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">举报地址<span class="mustfield">*</span></td>
              <td width="83%">
             	<@s.textfield name="memberreport.link_url" cssClass="txtinput" maxLength="600" cssStyle="width:400px;"/>
             	<@s.fielderror><@s.param>memberreport.link_url</@s.param></@s.fielderror>
             </td>
           </tr>
           
		  <tr>
		    <td valign="middle" class="left_td">举报类型<span class="mustfield">*</span></td>
		    <td>
		     <@s.select name="memberreport.report_type" list="CommparaList" listValue="para_key" listKey="para_value" />
		     <@s.fielderror><@s.param>memberreport.report_type</@s.param></@s.fielderror>
		    </td>
		  </tr>
           
           <tr>
             <td valign="middle" class="left_td">上传图片证据：</td>
             <td>
             
             <table border="0" cellpadding="0" cellspacing="0">
         <tr>
             <td style="padding:0px;">
             	<div id="fileQueue"></div>
	            <@s.textfield name="memberreport.img_path" id="uploadresult" cssStyle="width:300px;"/>
             </td>
             <td style="padding-left:3px;">
             	<input type="file" name="uploadifyfile" id="uploadifyfile"/>
            </td>
             <td style="padding-left:3px;">
             	<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	             <script>uploadOneImgMulti();</script>
             </td>
          </tr>
         </table>   
		  <@s.fielderror><@s.param>memberreport.img_path</@s.param></@s.fielderror>
             </td>
          </tr>
         
            <tr>
             <td valign="middle" class="left_td">举报内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="memberreport.re_desc" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="set_textarea_length(this,400);"/>
                <@s.fielderror><@s.param>memberreport.re_desc</@s.param></@s.fielderror>
             </td>
           </tr>
         
           <tr>
           <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="memberreport.report_id" id="memberreport.report_id"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" cssClass="sub"/>
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="document.forms[0].action='/member_Memberreport_list.action';document.forms[0].submit();"/>
           </td>
           </tr>
         </table>
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