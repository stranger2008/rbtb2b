<html>
  <head>
    <title>手机短信管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Smshistory_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 手机短信管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Smshistory_add.action','');">发送短信</a></li>
     
     <li class="sc"><a onclick="delInfo('smshistory.trade_id','/admin_Smshistory_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('smshistory.trade_id')"/></td>
    <td width="15%" align="center" class="top_td">手机号</td>
    <td width="30%"  align="center" class="top_td">短信正文</td>
    <td width="20%"  align="center" class="top_td">操作人</td>
    <td width="20%"  align="center" class="top_td">发送时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
  <#list smshistoryList as smshistory>
  <tr>
    <td><input type="checkbox" name="smshistory.trade_id" value="${smshistory.trade_id?if_exists}"/></td>
    <td align="center">
    <#if smshistory.phoneattr?if_exists!=''>
    <#if smshistory.phoneattr?length lt 20>
    ${smshistory.phoneattr?if_exists}
    <#else>
    ${smshistory.phoneattr[0..19]}
    </#if>
    </#if></td>
    <td align="center">
    <#if smshistory.content?if_exists!=''>
    <#if smshistory.content?length lt 20>
    ${smshistory.content?if_exists}
    <#else>
    ${smshistory.content[0..19]}
    </#if>
    </#if>
    </td>
    <td align="center">${smshistory.user_name?if_exists}</td>
    <td align="center">${smshistory.send_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Smshistory_view.action','smshistory.trade_id=${smshistory.trade_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">手机号:</td>
		<td><@s.textfield name="phomeattr_s"/></td>
	</tr>
	<tr>
		<td align="right">操作人:</td>
		<td><@s.textfield name="user_name_s"/></td>
	</tr>
	 <tr>
    <td align="right" >时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
          至
           <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
           
           
         </td>
    </tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
</@s.form>

  </body>
</html>