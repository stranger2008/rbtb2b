<html>
<head>
<title>积分流动</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form  method="post">
  <div class="cont_main">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>积分管理>积分流动</td>
 	</tr>
	</table>
		<div style="margin-top:10px;">
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="10%"  align="center" class="top_td">添加</td>
    <td width="10%"  align="center" class="top_td">减少</td>
    <td width="10%"  align="center" class="top_td">余下积分数</td>
    <td width="10%"  align="center" class="top_td">操作人</td>
    <td width="15%"  align="center" class="top_td">事由</td>
    <td width="15%"  align="center" class="top_td">备注</td>
    <td width="15%"  align="center" class="top_td">操作时间</td>
  </tr>
     <#list interhistoryList as interhistory>
  <tr bgcolor="#FFFFFF">
      <td align="center">${interhistory.inter_in?if_exists}</td>
    <td align="center">${interhistory.inter_out?if_exists}</td>
    <td align="center">${interhistory.thisinter?if_exists}</td>
    <td align="center">${interhistory.user_name?if_exists}</td>
    <td align="center">${interhistory.reason?if_exists}</td>
    <td align="center">${interhistory.remark?if_exists}</td>
    <td align="center">${interhistory.in_date?if_exists}</td>
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
         <td class="table_name">操作时间:</td>
         <td>
         <@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="enddateString" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
