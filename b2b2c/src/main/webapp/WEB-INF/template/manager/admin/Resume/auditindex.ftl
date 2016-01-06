<html>
  <head>
    <title>简历审核列表</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
	 	//所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"resume");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Resume_auditList.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 简历审核列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('resume.resume_id','/admin_Resume_checkDel.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
  	<td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('resume.resume_id')"/></td>
    <td width="20%"  align="center" class="top_td">简历名称</td>
    <td width="15%" align="center" class="top_td">会员名称</td>
    <td width="20%" align="center" class="top_td">分类</td>
    <td width="20%" align="center" class="top_td">地区</td>
    <td width="5%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
     <td width="5%" align="center" class="top_td" >审核</td>
  </tr>
  
  <#list resumeList as sysresume>

    <tr bgcolor="<#if sysresume_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="resume.resume_id" value="${sysresume.resume_id?if_exists}"/></td>
    <td align="left" class="audit_left">
    <#if sysresume.resume_name?if_exists!=''>
    <a onclick=linkToInfo("/admin_Resume_audit.action","resume.resume_id=${sysresume.resume_id?if_exists}");>
    <#if sysresume.resume_name?length lt 16>
   ${sysresume.resume_name?if_exists}
   <#else>
    ${sysresume.resume_name[0..15]}...
   </#if>
   </a>
   </#if>
    <#if sysresume.is_recom?if_exists='1'>
    <span class="redcolor">[推荐]</span>
    </#if>
   </td>
    <td align="center">
     <#if sysresume.cust_name?if_exists!=''>
     <#if sysresume.cust_name?length lt 15>
   ${sysresume.cust_name?if_exists}
   <#else>
    ${sysresume.cust_name[0..14]}...
   </#if>
   </#if>
   </td>
    <td align="center" >
         <a onclick="linkToInfo('/admin_Resume_auditList.action','cat_attr_s=${sysresume.cat_attr_id?if_exists}');">${sysresume.cat_attr?if_exists}</a>
    </td>
    <td align="center" >${sysresume.area_attr?if_exists}</td>
   <td align="center" >
    <#if sysresume.info_state?if_exists='0'>
   <a href="/admin_Resume_auditList.action?job_state_s=0" >
   <span class="redcolor">未审核</span>
   </a>
   <#elseif sysresume.info_state?if_exists='2'>
   <a href="/admin_Resume_auditList.action?job_state_s=2" >
   <span class="bluecolor">未通过</span>
   </a>
   </#if>
   </td>
    <td align="center" >${sysresume.in_date?if_exists}</td >
      
    <td align="center"><a onclick=linkToInfo("/admin_Resume_audit.action","resume.resume_id=${sysresume.resume_id?if_exists}");><img src="/include/images/audit.png" /></a></td>
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
		<td align="right">简历名称:</td>
		<td><@s.textfield name="resume_name_s"  cssStyle="width:245px;" /></td>
	</tr>
	<tr>
		<td align="right">分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">真实姓名:</td>
		<td><@s.textfield name="real_name_s" maxLength="100" cssStyle="width:200px;"/></td>
	</tr>
	<tr>
		<td align="right">状态:</td>
		<td><@s.select name="job_state_s" cssStyle="width:100px;" list=r"#{'请选择':'请选择','0':'未审核','2':'未通过'}"  />
		</td>
	</tr>
	<tr>
		<td align="right">发布时间:</td>
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<!--搜索区域结束-->

</@s.form>

  </body>
</html>
