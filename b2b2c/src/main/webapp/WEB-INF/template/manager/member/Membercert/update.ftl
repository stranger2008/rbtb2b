<html>
<head>
<title>修改资质</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
    <#include "/include/uploadInc.html">
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Membercert_update.action" method="post" validate="true">
       <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>荣誉资质>修改资质</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
          <#if membercert.cert_state=='2'>
            <tr>
             <td valign="middle" class="left_td" style="font-weight:bold;">未通过理由:</td>
             <td>
             	${membercert.no_reason?if_exists}
             </td>
           </tr>
         </#if>   		
	     <tr>
           <tr>
             <td width="19%" valign="middle" class="left_td">证书标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membercert.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>membercert.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">发证机构<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membercert.organize" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>membercert.organize</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">发证时间<font color='red'>*</font></td>
             <td>
              <#if membercert.start_date?if_exists?length lt 10>
               <@s.textfield cssClass="Wdate"   name="membercert.start_date" onfocus="WdatePicker({readOnly:true})" />
             <#else>
             <@s.textfield cssClass="Wdate" value="${membercert.start_date?if_exists[0..9]}"  name="membercert.start_date" onfocus="WdatePicker({readOnly:true})" />         
             </#if>
             	<@s.fielderror><@s.param>membercert.start_date</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">到期日期<font color='red'>*</font></td>
             <td>
              <#if membercert.end_date?if_exists?length lt 10>
               <@s.textfield cssClass="Wdate"   name="membercert.end_date" onfocus="WdatePicker({readOnly:true})" />
             <#else>
             <@s.textfield cssClass="Wdate" value="${membercert.end_date?if_exists[0..9]}"  name="membercert.end_date" onfocus="WdatePicker({readOnly:true})" />         
             </#if>
             	<@s.fielderror><@s.param>membercert.end_date</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">证书图片<font color='red'>*</font></td>
             <td>
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="membercert.img_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>&nbsp;&nbsp;<@s.fielderror><@s.param>membercert.img_path</@s.param></@s.fielderror></td>
             		</tr>
             	</table> 
             </td>
           </tr>
           <tr>
           <td valign="middle" class="left_td">证书介绍:</td>
             <td>
                <@s.textarea name="membercert.cert_desc" id="cert_desc" cssClass="txtinput" cssStyle="width:380px;height:120px;"/>
				<@s.fielderror><@s.param>membercert.cert_desc</@s.param></@s.fielderror>
             </td>
	     </tr>
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="membercert.cert_id"/>
	       <@s.hidden name="membercert.cust_id"/>
	       ${listSearchHiddenField?if_exists}
	      <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Membercert_list.action','');"/>
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
