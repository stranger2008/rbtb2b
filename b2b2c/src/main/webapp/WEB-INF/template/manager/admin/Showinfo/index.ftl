<html>
  <head>
    <title>展会列表</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
      <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"showinfo");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Showinfo_list.action" method="post">
<@s.hidden name="showinfo.is_recom" id="admin_showinfo_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 展会管理 > 展会列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Showinfo_cate.action','');">添加展会</a></li>
     
     <li class="sc"><a onclick="delInfo('showinfo.exh_id','/admin_Showinfo_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','showinfo.exh_id','/admin_Showinfo_updateisrecom.action','admin_showinfo_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','showinfo.exh_id','/admin_Showinfo_updateisrecom.action','admin_showinfo_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('showinfo.exh_id')"/></td>
    <td width="20%"  align="center" class="top_td">展会标题</td>
    <td width="18%" align="center" class="top_td">客户名称</td>
    <td width="12%" align="center" class="top_td">分类</td>
    <td width="12%" align="center" class="top_td">地区</td>
    <td width="15%" align="center" class="top_td">展会日期</td>
    <td width="5%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td" >发布时间</td>
     <td width="7%" align="center" class="top_td" >修改</td>
  </tr>
  
  <#list showList as sysexhibition>

    <tr bgcolor="<#if sysexhibition_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="showinfo.exh_id" value="${sysexhibition.exh_id?if_exists}"/></td>
     <td align="left">
     <a onclick=linkToInfo("/admin_Showinfo_view.action","showinfo.exh_id=${sysexhibition.exh_id?if_exists}");>
     <#if sysexhibition.title?if_exists!=''>
    <#if sysexhibition.title?length lt 16>
     ${sysexhibition.title?if_exists}
   <#else>
    ${sysexhibition.title[0..15]}
   </#if>
   </#if>
   </a>
   <#if sysexhibition.is_recom?if_exists='1'>
    <span class="redcolor">[推荐]</span>
    </#if>
   </td>
   <td align="center">
   <a href="/admin_Member_viewinfo.action?member.cust_id=${(sysexhibition.cust_id)?if_exists}" target="_blank"> 
	    <#if sysexhibition.cust_name?if_exists!=''>
	     <#if sysexhibition.cust_name?length lt 18>
	   ${sysexhibition.cust_name?if_exists}
	   <#else>
	    ${sysexhibition.cust_name[0..17]}...
	   </#if>
	   </#if>
	</a>
    </td>
    <td align="center" >
          <a onclick="linkToInfo('/admin_Showinfo_list.action','cat_attr_s=${sysexhibition.cat_attr_id?if_exists}');">${sysexhibition.cat_attr?if_exists}</a>
    </td>
    <td align="center" >${sysexhibition.area_attr?if_exists}</td>
    <td align="center" >
     <#if sysexhibition.start_date?length lt 10>
     ${sysexhibition.start_date?if_exists}
     <#else>
     ${sysexhibition.start_date[0..9]}
     </#if>
     -
     <#if sysexhibition.end_date?length lt 10>
     ${sysexhibition.end_date?if_exists}
     <#else>
     ${sysexhibition.end_date[0..9]}
     </#if>
    </td>
   <td align="center" >
   <#if sysexhibition.info_state?if_exists='1'>
   <a href="/admin_Showinfo_list.action?info_state_s=1" >
   <span class="greencolor">正常</span>
   </a>
   <#elseif sysexhibition.info_state?if_exists='3'>
   <a href="/admin_Showinfo_list.action?info_state_s=3" >
   <span class="redcolor">禁用</span>
   </a>
   <#elseif sysexhibition.info_state?if_exists='0'>
   <a href="/admin_Showinfo_list.action?info_state_s=0" >
   <span class="redcolor">未审核</span>
   </a>
   <#elseif sysexhibition.info_state?if_exists='2'>
   <a href="/admin_Showinfo_list.action?info_state_s=2" >
   <span class="bluecolor">未通过</span>
   </a>
   </#if></td>
    <td align="center" >${sysexhibition.in_date?if_exists}</td>
    <td align="center">
     <a onclick=linkToInfo("/admin_Showinfo_view.action","showinfo.exh_id=${sysexhibition.exh_id?if_exists}");>
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

<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">展会标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
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
		<td align="right">展会开展日期:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endstart_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
         </td>
	</tr>
	<tr>
		<td align="right">展会结束日期:</td>
		<td>
		<@s.textfield id="txtstartDate1" name="end_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate1\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate1" name="endend_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate1\\',{d:1})}',readOnly:true})"  />
         </td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td>
		<@s.select name="info_state_s" list=r"#{'4':'请选择','1':'正常','3':'禁用'}"   /> </td>
	</tr>
	<tr>
		<td align="right">发布时间:</td>
		<td>
		<@s.textfield id="txtstartDate2" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate2\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate2" name="enddate_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate2\\',{d:1})}',readOnly:true})"  />
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
