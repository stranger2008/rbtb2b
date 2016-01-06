<html>
  <head>
    <title>广告管理</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <script type="text/javascript">
	  $(document).ready(function(){ 	    
		  area_select("${cfg_topareaid?if_exists}");
		  });
     </script>
  </head>
  <body>
<@s.form action="/admin_Advinfo_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:网站管理 > 广告管理 > 广告管理 > 广告列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">    
     <li class="sc"><a onclick="delInfo('advinfo.adv_id','/admin_Advinfo_delete.action?posid=${posid?if_exists}&two_pages_link=no')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('advinfo.adv_id')"/></td>
    <td width="26%" align="center" class="top_td">广告位名称</td>
    <td width="20%" align="center" class="top_td">广告名称</td>
    <td width="10%" align="center" class="top_td">所属地区</td>
    <td width="7%" align="center" class="top_td">广告类型</td>
    <td width="5%" align="center" class="top_td">开始时间</td>
    <td width="5%" align="center" class="top_td">结束时间</td>
    <td width="5%" align="center" class="top_td">广告状态</td>
    <td width="5%" align="center" class="top_td">点击统计</td>
    <td width="5%" align="center" class="top_td">点击次数</td>
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list advinfoList as adv>

    <tr bgcolor="<#if adv_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="advinfo.adv_id" value="${adv.adv_id?if_exists}"/></td>
    <td align="left">${adv.pos_name?if_exists}</td>
    <td align="left">
      <#if adv.adv_name?if_exists!=''>
      <#if adv.adv_name?if_exists?length lt 25>${adv.adv_name?if_exists}<#else>${adv.adv_name?if_exists[0..24]}..</#if>
      </#if>
    </td>
    <td align="center">${adv.area_attr?if_exists}</td>
    <td align="center">${adv.pos_type?if_exists}</td>
    <td align="center">
    <#if adv.start_date?string!=''>
    <#if  adv.start_date?length   lt   10  >   ${adv.start_date} <#else> ${adv.start_date[0..9]}</#if>
    </#if>
    </td>
    <td align="center">
    <#if adv.end_date?string!=''>
    <#if  adv.end_date?length   lt   10  >   ${adv.end_date} <#else> ${adv.end_date[0..9]}</#if>
    </#if>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Advinfo_list.action','adv_state_s=${adv.adv_state?if_exists}');">
    <#if adv.adv_state?if_exists=='0'>
        <font color='green'>已审核</font>
    <#else>
         <font color='red'>未审核</font>
   
    </#if>
     </a>
     </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Advinfo_list.action','iscount_s=${adv.iscount?if_exists}');">
    <#if adv.iscount?if_exists=='0'>
    
    <font color='green'>开启</font>
    
    <#else><font color='red'>关闭</font>
    
    </#if> 
    </a>
    </td>
    <td align="center">${adv.addnum?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Advinfo_view.action','advinfo.adv_id=${adv.adv_id?if_exists}&type=${adv.type?if_exists}&tablename=${adv.module_type?if_exists}&posid=${posid?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">广告名称:</td>
		<td><@s.textfield name="adv_name_s" size="40"/></td>
	</tr>
	<tr>
		<td align="right">所在地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">广告类型:</td>
		<td><@s.select name="pos_type_s" list="advcommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	</tr>
		<tr>
         <td align="right">所属模块</td>
         <td>
         <@s.select name="module_type_s" list="modulecommparaList"  listValue="module_name" listKey="module_type" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
	<tr>
		<td align="right">广告状态:</td>
		<td><@s.select name="adv_state_s" list=r"#{'':'请选择','0':'已审核','1':'未审核'}"/> </td>
	</tr>
	<tr>
		<td align="right">点击统计:</td>
		<td><@s.select name="iscount_s" list=r"#{'':'请选择','0':'开启','1':'关闭'}"/> </td>
	</tr>
	<tr>
		<td align="right">开始时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		</td>
	</tr>
	<tr>
		<td align="right">结束时间:</td>
		<td>
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

<!--搜索区域结束-->
   <@s.hidden name="posid" />
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
</@s.form>

  </body>
</html>