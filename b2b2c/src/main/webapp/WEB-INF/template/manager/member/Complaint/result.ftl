<html>
  <head>
    <title>查看处理结果</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Complaint_update.action" method="post" validate="true" onsubmit="return checkall()">
   	 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>投诉举报>我发起的投诉>查看处理结果</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
         <tr>
             <td width="17%" valign="middle" class="left_td">处理结果:</td>
             <td>
             	${complaint.do_result?if_exists}
             </td>
           </tr>
           
           <tr>
           <td colspan="4" class="subbut">
            <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden id="selid" name="selid" value="1"/>
	       <@s.hidden name="complaint.info_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <#if type?if_exists=='send'><input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Complaint_list.action','');"/></#if>
	       <#if type?if_exists=='receive'><input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Complaint_auditList.action','');"/></#if>
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