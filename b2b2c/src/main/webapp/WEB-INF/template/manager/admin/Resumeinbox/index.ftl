<html>
  <head>
    <title>简历收件箱列表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  </head>
<body>
<@s.form action="/admin_Resumeinbox_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 简历收件箱列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('resumeinbox.inbox_id','/admin_Resumeinbox_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('resumeinbox.inbox_id')"/></td>
    <td width="15%" align="center" class="top_td">企业名称</td>
    <td width="10%"  align="center" class="top_td">职位名称</td>
    <td width="15%"  align="center" class="top_td">简历名称</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="5%"  align="center" class="top_td">面试邀请</td>
    <td width="10%"  align="center" class="top_td">应聘时间</td>
    <td width="10%"  align="center" class="top_td">应聘会员名称</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
   
  <#list resumeinboxList as sysresumein>
    <tr bgcolor="<#if sysresumein_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="resumeinbox.inbox_id" value="${sysresumein.inbox_id?if_exists}"/></td>
    <td align="center" style="color:#F16800;">
	      <#if sysresumein.cust_name?if_exists!=''>
		      <#if sysresumein.cust_name?length lt 20>
				<a  onclick=linkToInfo("/admin_Job_audit.action","flageJob=1&job.job_id=${sysresumein.job_id?if_exists}");>
			    ${sysresumein.cust_name?if_exists}
			    </a>
			  <#else>
			    <a  onclick=linkToInfo("/admin_Job_audit.action","flageJob=1&job.job_id=${sysresumein.job_id?if_exists}");>
			    ${sysresumein.cust_name[0..19]}
			  	</a>
			  </#if>
	   	  </#if>
    </td>
    <td align="center">
    <a style="color:black;" onclick=linkToInfo("/admin_Job_audit.action","flageJob=1&job.job_id=${sysresumein.job_id?if_exists}");>
    ${sysresumein.title?if_exists}</a></td>
    <td align="center">
    <a style="color:black;" onclick=linkToInfo("/admin_Resume_audit.action","flageResume=1&resume.resume_id=${sysresumein.resume_id?if_exists}");>

    ${sysresumein.resume_name?if_exists}</a>
    </td>
    
    <td align="center">      
    	<#if sysresumein.state?if_exists='0'>
    		<a href="/admin_Resumeinbox_list.action?state_s=0"><font style='color:red;'>未查看</font></a>
   		<#elseif sysresumein.state?if_exists='1' >
   		    <a href="/admin_Resumeinbox_list.action?state_s=1"><font style='color:green;'>已查看</font></a>
   		</#if>
   	</td>
   	
    <td align="center">
    <#if sysresumein.isinvite?if_exists='0'>
    	<a href="/admin_Resumeinbox_list.action?isinvite_s=0"><font style='color:red;'>未邀请</font></a>
    <#elseif  sysresumein.isinvite?if_exists='1' >
    	<a href="/admin_Resumeinbox_list.action?isinvite_s=0"><font style='color:red;'>已邀请</font></a>
    </#if></td>
    
    <td align="center">${sysresumein.apply_date?if_exists}</td>
    <td align="center">${sysresumein.user_name?if_exists}</td>
    <td align="center">
    <a onclick=linkToInfo("/admin_Resumeinbox_view.action","resumeinbox.inbox_id=${sysresumein.inbox_id?if_exists}");>
    <img src="/include/images/bj.gif" /></a></td>
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
