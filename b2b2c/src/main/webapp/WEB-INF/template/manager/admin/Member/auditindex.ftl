<html>
  <head>
    <title>未审核会员列表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	   $(document).ready(function(){ 
	 	//所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"company");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
<body>
<@s.form action="/admin_Member_auditList.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 未审核会员列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('member.cust_id','/admin_Member_checkDel.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" align="center" class="top_td">    
    	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.cust_id')"/>    
    </td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="10%" align="center" class="top_td">会员类型</td>
    <td width="20%" align="center" class="top_td">所在地区</td>
    <td width="15%" align="center" class="top_td">所属分类</td>
    <td width="10%" align="center" class="top_td">会员状态</td>
    <td width="10%" align="center" class="top_td">注册日期</td>
    <td width="5%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list memberList as member>
  <tr bgcolor="<#if member_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
      <td align="center">
    	<input type="checkbox" name="member.cust_id" value="${member.cust_id?if_exists}"/>
    </td>
    <td style="padding-left:15px;">
    <#if (member.mem_type)?if_exists=='0'>
     <a onclick="linkToInfo('/admin_Member_audit.action','member.cust_id=${member.cust_id?if_exists}');">
      <#if member.cust_name?length lt 21>${member.cust_name?if_exists}<#else>${member.cust_name[0..20]}..</#if>
     </a>
    <#elseif (member.mem_type)?if_exists=='1'>
       <a onclick="linkToInfo('/admin_Member_auditPerson.action','member.cust_id=${member.cust_id?if_exists}');">
          <#if member.cust_name?length lt 21>${member.cust_name?if_exists}<#else>${member.cust_name[0..20]}..</#if>
       </a>
     
    </#if>
    </td>
    <td align="center"><a href="/admin_Member_auditList.action?mem_type_s=${member.mem_type?if_exists}" onclick="document.forms[0].submit();"><#if member.mem_type=='0'>企业<#else>个人</#if></a></td>
    <td align="center">
    <#if member.area_attr?if_exists!=''>
    <#if member.area_attr?length lt 21>${member.area_attr?if_exists}<#else>${member.area_attr[0..20]}..</#if>
    </#if>
    </td>
    <td align="center">
    <#if member.cat_attr?if_exists!=''>
      ${member.cat_attr?if_exists}
    </#if>
    </td>
    <td align="center">
     <a href="/admin_Member_auditList.action?state_code_s=${member.info_state?if_exists}">
      <#if member.info_state=='0'><span class="redcolor">未审核</span><#else><span class="bluecolor">未通过</span></#if>
     </a>
    </td>
    <td align="center">${member.in_date?if_exists}</td>
    <td align="center">
      <#if (member.mem_type)?if_exists=='0'>
        <a onclick="linkToInfo('/admin_Member_audit.action','member.cust_id=${member.cust_id?if_exists}');"><img src="/include/images/audit.png" /></a>
      <#elseif (member.mem_type)?if_exists=='1'>
        <a onclick="linkToInfo('/admin_Member_auditPerson.action','member.cust_id=${member.cust_id?if_exists}');"><img src="/include/images/audit.png" /></a>
     </#if>
    </td>
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
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">会员类型:</td>
		<td><@s.select name="mem_type_s" list=r"#{'':'请选择','0':'企业','1':'个人'}"/></td>
	</tr>
	<tr>
		<td align="right">所在地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">会员状态:</td>
		<td><@s.select name="state_code_s" list=r"#{'':'请选择','0':'未审核','2':'未通过'}"/></td>
	</tr>
	<tr>
		<td align="right">注册日期:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
</@s.form>

  </body>
</html>