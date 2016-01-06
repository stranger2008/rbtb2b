<html>
<head>
<title>资金流水</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form  method="post">
  <div class="cont_main">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>资金流水</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="10%"  align="center" class="top_td">收入</td>
    <td width="10%"  align="center" class="top_td">支出</td>
    <td width="10%"  align="center" class="top_td">余额</td>
    <td width="10%"  align="center" class="top_td">操作人</td> 
    <td width="15%"  align="center" class="top_td">事由</td>
    <td width="15%"  align="center" class="top_td">备注</td>
    <td width="15%"  align="center" class="top_td">操作时间</td>
  </tr>
     <#list fundhistoryList as fundhistory>
  <tr bgcolor="#FFFFFF">
     <td align="center">${fundhistory.fund_in?if_exists}</td>
    <td align="center">${fundhistory.fund_out?if_exists}</td>
    <td align="center">${fundhistory.balance?if_exists}</td>
    <td align="center">${fundhistory.user_name?if_exists}</td>
    <td align="center">${fundhistory.reason?if_exists}</td>
    <td align="center">${fundhistory.remark?if_exists}</td>
    <td align="center">${fundhistory.in_date?if_exists}</td>
  </tr>
    </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
         <td class="table_name">时间段:</td>
         <td>
         <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
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
