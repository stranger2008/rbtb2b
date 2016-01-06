<html>
  <head>
    <title>人才库列表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  </head>
<body>
<@s.form action="/admin_Jobtalent_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 人才库列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('jobtalent.inbox_id','/admin_Jobtalent_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('jobtalent.inbox_id')"/></td>
    <td width="20%"  align="center" class="top_td">简历名称</td>
    <td width="20%" align="center" class="top_td">企业名称</td>
    <td width="10%"  align="center" class="top_td">添加时间</td>
    <td width="10%"  align="center" class="top_td">操作</td>
  </tr>
   
  <#list jobtalentList as sysjobtalent>
    <tr bgcolor="<#if sysjobtalent_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="jobtalent.inbox_id" value="${sysjobtalent.inbox_id?if_exists}"/></td>
    <td align="center">
    <a style="color:#F16800;" href="###" onclick=linkToInfo("/admin_Resume_audit.action","flageResume=2&resume.resume_id=${sysjobtalent.resume_id?if_exists}");>
    <#if sysjobtalent.resume_name?if_exists!=''>
   <#if sysjobtalent.resume_name?length lt 20>
   ${sysjobtalent.resume_name?if_exists}
   <#else>
    ${sysjobtalent.resume_name[0..19]}
   </#if>
   </#if>
    </a></td>
   <td align="center">
     <#if sysjobtalent.cust_name?if_exists!=''>
	    <#if sysjobtalent.cust_name?length lt 18>
	   ${sysjobtalent.cust_name?if_exists}
	   <#else>
	    ${sysjobtalent.cust_name[0..17]}
	   </#if>
	 </#if>
    </td>
    <td align="center">${sysjobtalent.in_date?if_exists}</td>
    <td align="center">
    <a title="查看" onclick=linkToInfo("/admin_Resume_audit.action","flageResume=2&resume.resume_id=${sysjobtalent.resume_id?if_exists}");>
     <img src="/include/images/view.gif" /></a></td>
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

<!--搜索区域结束-->

</@s.form>
  </body>
</html>
