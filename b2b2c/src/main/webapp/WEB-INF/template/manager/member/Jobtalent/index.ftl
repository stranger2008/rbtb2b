<html>
<head>
<title>人才库</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
 
</head>
<body>
<@s.form action="/member_Jobtalent_list.action" method="post">
  <div class="cont_main">
 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>招聘管理>人才库</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('jobtalent.inbox_id','/member_Jobtalent_delete.action');">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('jobtalent.inbox_id')"/></td>
    <td width="50%" align="center" class="top_td">简历名称</td>
    <td width="20%" align="center" class="top_td">添加时间</td>
  </tr>
    <#list jobtalentList as sysjobtalent>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="jobtalent.inbox_id" value="${sysjobtalent.inbox_id?if_exists}"/></td>
    <td align="center"><a href="/member_Resume_audit.action?flageResume=2&resume.resume_id=${sysjobtalent.resume_id?if_exists}" style="color:blue;">${sysjobtalent.resume_name?if_exists}</a></td>
    <td align="center">${sysjobtalent.in_date?if_exists}</td>
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
		<td align="right">添加时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="enddateString_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
</@s.form>

<!--搜索区域结束-->
</body>
</html>
