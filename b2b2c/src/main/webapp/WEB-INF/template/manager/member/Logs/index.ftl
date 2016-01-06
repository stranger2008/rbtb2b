<html>
  <head>
    <title>会员操作日志</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
  </head>
  <body>
<@s.form action="/member_Logs_list.action" method="post">
<div class="cont_main">
 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>账号管理>操作日记</td>
 	</tr>
	</table>
  <div class="finder_bar_bg">
   <ul >
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="cont_main">
  <table width="100%" cellspacing="0" border="0">
      <tr style="background:url(images/top_tr.gif) repeat-x;">
	   <td width="10%" align="center" class="top_td">IP</td>
	   <td width="60%" align="center" class="top_td">操作内容</td>
       <td width="17%" align="center" class="top_td">操作时间</td>
  </tr>
  <#list logslist as logs>
    <tr bgcolor="<#if logs_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td align="center">${logs.ip?if_exists}</td>
    <td align="left" style="padding-left:20px;">${logs.content?if_exists}</td>
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
		<td align="right">IP:</td>
		<td><@s.textfield name="ip_s"/></td>
	</tr>
	
	<tr>
		<td align="right">操作内容:</td>
		<td><@s.textfield name="content_s" size="40"/></td>
	</tr>
	
	
	<tr>
     <td align="right" >时间段:</td>
         <td>
         	<@s.textfield id="txtStartDate" name="starttime_s"  cssClass="Wdate"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})" />-
            <@s.textfield id="txtEndDate" name="endtime_s"  cssClass="Wdate"  onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})" />
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