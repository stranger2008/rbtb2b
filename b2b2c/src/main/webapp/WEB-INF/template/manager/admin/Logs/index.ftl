<html>
  <head>
    <title>系统日志管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script type="text/javascript">
    //重置系统日志
	function resetLogs(){
		if(window.confirm("确定要重置系统日志吗?")) {
			document.forms[0].action='/admin_Logs_reset.action';
			document.forms[0].submit();
		}
	}	
    </script>
  </head>
  <body>

<@s.form action="/admin_Logs_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统工具 > 系统日志管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('logs.log_id','/admin_Logs_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="xg"><a onclick="resetLogs();">重置</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="1%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('logs.log_id')"/>&nbsp;</td>
    <td width="10%" align="center" class="top_td">用户名</td>    
    <td width="10%" align="center" class="top_td">IP</td>
    <td width="60%" align="center" class="top_td">操作内容</td>
    <td width="10%" align="center" class="top_td">操作时间</td>
  </tr>

  <#list logslist as logs>
    <tr bgcolor="<#if logs_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="logs.log_id" value="${logs.log_id?if_exists}"/></td>
    <td align="center">${logs.user_name?if_exists}</td>
    <td align="center">${logs.ip?if_exists}</td>
    <td align="center">${logs.content?if_exists}</td>
    <td align="center">${logs.in_date?if_exists}</td>  
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
		<td align="right">用户名:</td>
		<td><@s.textfield name="user_name_s"/></td>
	</tr>
	
	<tr>
		<td align="right">操作内容:</td>
		<td><@s.textfield name="content_s"/></td>
	</tr>
	
	<tr>
		<td align="right">IP:</td>
		<td><@s.textfield name="ip_s"/></td>
	</tr>
	
	   <tr>
     		<td align="right" >时间段:</td>
             <td>
            		<@s.textfield id="txtStartDate" name="starttime_s"  type="text" cssClass="Wdate"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})" />&nbsp;至
             		<@s.textfield id="txtEndDate" name="endtime_s"  type="text" cssClass="Wdate"  onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})" />
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