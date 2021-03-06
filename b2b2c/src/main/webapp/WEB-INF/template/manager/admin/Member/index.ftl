<html>
  <head>
    <title>会员列表</title>
     <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
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
<@s.form action="/admin_Member_list.action" method="post">
<@s.hidden name="member.recommend" id="admin_member_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 会员列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Member_add.action','');">添加会员</a></li>
     <li class="sc"><a onclick="delInfo('member.cust_id','/admin_Member_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','member.cust_id','/admin_Member_updateisrecom.action','admin_member_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','member.cust_id','/admin_Member_updateisrecom.action','admin_member_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" align="center" class="top_td">    
    <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.cust_id')"/>    
    </td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="5%" align="center" class="top_td">会员类型</td>
    <td width="10%" align="center" class="top_td">会员级别</td>
    <td width="15%" align="center" class="top_td">所在地区</td>
    <td width="15%" align="center" class="top_td">所属分类</td>
    <td width="5%" align="center" class="top_td">会员状态</td>
    <td width="10%" align="center" class="top_td">注册日期</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list memberList as member>
  <tr bgcolor="<#if member_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td align="center">
    	<input type="checkbox" name="member.cust_id" value="${member.cust_id?if_exists}"/>
    </td>
    <td align="left">
      <#if member.mem_type=='0'>
	     <a onclick="linkToInfo('/admin_Member_view.action','member.cust_id=${member.cust_id?if_exists}');">
	      <#if member.cust_name?if_exists!=''>
	      <#if member.cust_name?length lt 21>${member.cust_name?if_exists}<#else>${member.cust_name[0..20]}..</#if>
	      </#if>
	     </a>
	  <#elseif member.mem_type=='1'>
	     <a onclick="linkToInfo('/admin_Member_viewPerson.action','member.cust_id=${member.cust_id?if_exists}');">
	      <#if member.cust_name?if_exists!=''>
	      <#if member.cust_name?length lt 21>${member.cust_name?if_exists}<#else>${member.cust_name[0..20]}..</#if>
	      </#if>
	     </a>
	  </#if>
     <a href="/admin_Member_list.action?recommend_s=1"><span class="redcolor"><#if member.recommend=='1'>[推荐]</#if></span></a>
    </td>
    <td align="center"><a href="/admin_Member_list.action?mem_type_s=${member.mem_type?if_exists}" onclick="document.forms[0].submit();"><#if member.mem_type=='0'>企业<#else>个人</#if></a></td>
    <td align="center">
    <#if (member.level_name)?if_exists!=''>
      ${(member.level_name)?if_exists}<#if (member.exprie)?if_exists==0><span class="redcolor">[已过期]</span></#if>
    </#if>
    </td>
    <td align="left">
    <#if member.area_attr?if_exists!=''>
      ${member.area_attr?if_exists}
    </#if>
    </td>
    <td align="left">
    <#if member.cat_attr?if_exists!=''>
      ${member.cat_attr?if_exists}
    </#if>
    </td>
    <td align="center">
    <a href="/admin_Member_list.action?state_code_s=${member.info_state?if_exists}">
       <#if member.info_state?if_exists=='0'><span class="orangecolor">未审核</span>
       <#elseif member.info_state?if_exists=='1'><span class="bluecolor">正常</span>
       <#elseif member.info_state=='2'><span class="greencolor">未通过</span>
       <#elseif member.info_state=='3'><span class="redcolor">禁用</span>
       </#if>
    </a>
    </td>
    <td align="center">${member.in_date?if_exists}</td>  
    <td align="center">
     <#if (member.mem_type)?if_exists=='0'>
       <a onclick="linkToInfo('/admin_Member_view.action','member.cust_id=${member.cust_id?if_exists}');" title="企业会员修改"><img src="/include/images/bj.gif" /></a>
     <#elseif (member.mem_type)?if_exists=='1'>
       <a onclick="linkToInfo('/admin_Member_viewPerson.action','member.cust_id=${member.cust_id?if_exists}');" title="个人会员修改"><img src="/include/images/bj.gif" /></a>
     </#if>
      <a onclick="linkToInfo('/admin_Memberinter_list.action','member.cust_id=${member.cust_id?if_exists}&memberinter_name_s=${member.cust_name?if_exists}&two_pages_link=no');" title="会员积分管理"><img src="/include/images/admin/inter.gif" /></a>
      <a onclick="linkToInfo('/admin_Memberfund_list.action','member.cust_id=${member.cust_id?if_exists}&memberfund_name_s=${member.cust_name?if_exists}&two_pages_link=no');" title="会员资金管理"><img src="/include/images/admin/money.gif" /></a>
      <a href="/admin_Levelinfo_view.action?levelinfo.cust_id=${member.cust_id?if_exists}" title="会员级别信息"><img src="/include/images/admin/medit.gif" /></a>
      <a onclick="linkToInfo('/admin_Memberuser_list.action','cust_id_s=${member.cust_id?if_exists}&mem_type_s=${member.mem_type?if_exists}');" title="密码重置"><img src="/include/images/admin/accedit.gif" /></a>
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
		<td align="right">会员级别:</td>
		<td><@s.select name="level_s" list="levelList" listValue="level_name" listKey="level_id" headerKey="" headerValue="请选择" /></td>
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
		<td><@s.select name="state_code_s" list=r"#{'':'请选择','1':'正常','3':'禁用'}"/></td>
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