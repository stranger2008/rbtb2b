<html>
<head>
<title>简历收件箱</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Resumed_list.action" method="post">
  <div class="cont_main">
  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>简历管理>我投的简历</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
     <li class="sc"><a onclick="delInfo('resumeinbox.inbox_id','/member_Resumeinbox_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('resumeinbox.inbox_id')"/></td>
    <td width="10%"  align="center" class="top_td">职位名称</td>
    <td width="15%"  align="center" class="top_td">简历名称</td>
    <td width="5%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">面试邀请</td>
    <td width="10%"  align="center" class="top_td">应聘时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
   <#list resumeinboxList as sysresumein>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="resumeinbox.inbox_id" value="${sysresumein.inbox_id?if_exists}"/></td>
    <td align="center">
    <a href="/member_Job_audit.action?job.job_id=${sysresumein.job_id?if_exists}" >
    <#if sysresumein.title?if_exists!=''>
    <#if sysresumein.title?length lt 7>
   ${sysresumein.title?if_exists}
   <#else>
    ${sysresumein.title[0..6]}...
   </#if>
   </#if>
    </a></td>
    <td align="center"><a href="/member_Resume_audit.action?flageResume=1&resume.resume_id=${sysresumein.resume_id?if_exists}" >
   <#if sysresumein.resume_name?if_exists!=''>
    <#if sysresumein.resume_name?length lt 10>
   ${sysresumein.resume_name?if_exists}
   <#else>
    ${sysresumein.resume_name[0..9]}
   </#if>
   </#if>
    </a></td>
    <td align="center"><#if sysresumein.state?if_exists='0'>
    <a href="/member_Resumeinbox_list.action?state_s=0">
    <font class="redcolor">
      未查看
    </font>
    </a>
   	<#elseif sysresumein.state?if_exists='1' >
   	<a href="/member_Resumeinbox_list.action?state_s=1">
   	<font class="greencolor">
   	已查看
   	</font>
   	</a>
   </#if></td>
   <td align="center">
   <#if sysresumein.isinvite?if_exists='0'>
   <a href="/member_Resumeinbox_list.action?isinvite_s=0">
   <font class="redcolor">
   未邀请
   </font>
   </a>
   <#elseif  sysresumein.isinvite?if_exists='1' >
   <a href="/member_Resumeinbox_list.action?isinvite_s=1">
   <font class="greencolor">
   已邀请
   </font>
   </a>
   </#if></td>
    <td align="center">${sysresumein.apply_date?if_exists}</td>
    <td align="center">
    <a onclick=linkToInfo("/member_Resumed_view.action","resumeinbox.inbox_id=${sysresumein.inbox_id?if_exists}");>
    <img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">状态:</td>
		<td>
		<@s.select name="state_s" list=r"#{'2':'请选择','0':'未查看','1':'已查看'}" />
        </td>
	</tr>
	<tr>
		<td align="right">面试邀请:</td>
		<td>
		<@s.select name="isinvite_s" list=r"#{'2':'请选择','0':'未邀请','1':'已邀请'}" />
		</td>
	</tr>
	<tr>
		<td align="right">应聘时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="apply_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
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
