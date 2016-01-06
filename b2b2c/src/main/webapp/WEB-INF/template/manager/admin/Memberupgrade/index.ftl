<html>
  <head>
    <title>会员升级管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Memberupgrade_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 会员升级
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('memberupgrade.grade_id','/admin_Memberupgrade_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberupgrade.grade_id')"/></td>
    
    <td width="10%"  align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">当前级别</td>
    <td width="10%"  align="center" class="top_td">申请级别</td>
    <td width="10%"  align="center" class="top_td">申请时间</td>
    <td width="10%"  align="center" class="top_td">审核状态</td>
     <td width="10%"  align="center" class="top_td">审核用户名</td>
    <td width="10%"  align="center" class="top_td">审核时间</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list memberupgradeList as upgrade>

    <tr bgcolor="<#if upgrade_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="memberupgrade.grade_id" value="${upgrade.grade_id?if_exists}"/></td>
    
    <td align="center">${upgrade.cust_name?if_exists}</td>
    
    <td align="center">
        <#if upgrade.now_level?if_exists==1>个人普通会员
        <#elseif upgrade.now_level?if_exists==2>个人VIP会员
        <#elseif upgrade.now_level?if_exists==3>企业普通会员
        <#elseif upgrade.now_level?if_exists==4>企业VIP会员
        </#if>
    </td>
    <td align="center">
        <#if upgrade.apply_level?if_exists==1>个人普通会员
        <#elseif upgrade.apply_level?if_exists==2>个人VIP会员
        <#elseif upgrade.apply_level?if_exists==3>企业普通会员
        <#elseif upgrade.apply_level?if_exists==4>企业VIP会员
        </#if>
    </td>
    <td align="center">${upgrade.in_date?if_exists}</td>
    
    <td align="center">
     <a href="/admin_Memberupgrade_list.action?audit_state_s=${upgrade.audit_state?if_exists}">
      <#if upgrade.audit_state=='0'><span class="redcolor">未审核</span>
      <#elseif upgrade.audit_state=='1'><span class="greencolor">正常</span>
      <#else><span class="bluecolor">未通过</span>
      </#if>
     </a>
    </td>
    <td align="center">${upgrade.audit_user?if_exists}</td>
    <td align="center">${upgrade.audit_date?if_exists}</td>
    <td align="center">
    
    <a onclick="linkToInfo('/admin_Memberupgrade_audit.action','memberupgrade.grade_id=${upgrade.grade_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">当前级别:</td>
		<td><@s.select name="now_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
	</tr>
	<tr>
		<td align="right">申请级别:</td>
		<td><@s.select name="apply_level_s" list=r"#{'':'请选择','1':'个人普通会员','2':'个人VIP会员','3':'企业普通会员','4':'企业VIP会员'}"/></td>
	</tr>
	<tr>
		<td align="right">申请时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="out_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		               </td>
	</tr>
	<tr>
		<td align="right">申请用户名:</td>
		<td><@s.textfield name="user_name_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">审核状态:</td>
		<td><@s.select name="audit_state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过'}"/></td>
	</tr>
	<tr>
		<td align="right">审核用户名:</td>
		<td><@s.textfield name="audit_user_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">审核时间:</td>
		<td><input id="audit_date_s" name="audit_date_s"  type="text" class="Wdate" readonly="true"
			   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'audit_date_end_s\')}',readOnly:true})" />&nbsp;至&nbsp;
			   <input id="audit_date_end_s" name="audit_date_end_s"  type="text" class="Wdate" readonly="true"
			   onclick="WdatePicker({minDate:'#F{$dp.$D(\'audit_date_s\')}',readOnly:true})" /></td>
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