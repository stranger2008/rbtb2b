<html>
  <head>
    <title>查看商机邮件</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>



<@s.form action="/admin_Subscribeinfo_list.action" method="post">
<@s.hidden name="nav.isshow" id="admin_nav_state"/>
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('subscribeinfo.send_id','/admin_Subscribeinfo_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="ret"><a onclick="linkToInfo('/admin_Subscribe_list.action','');">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subscribeinfo.send_id')"/></td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="20%"  align="center" class="top_td">商机标题</td>
    <td width="20%"  align="center" class="top_td">信息类型</td>
    <td width="20%"  align="center" class="top_td">信息标识串</td>
    <td width="20%"  align="center" class="top_td">发送时间</td>
  </tr>
  
  <#list subscribeinfoList as subscribeinfo>
  <tr>
    <td><input type="checkbox" name="subscribeinfo.send_id" value="${subscribeinfo.send_id?if_exists}"/></td>
    <td align="center">${subscribeinfo.cust_name?if_exists}</td>
    <td align="center">${subscribeinfo.keyword?if_exists}</td>
    <td align="center"><#if subscribeinfo.info_type?if_exists=='0'>
    <a onclick="linkToInfo('/admin_Subscribeinfo_list.action','info_type_s=${subscribeinfo.info_type?if_exists}');"><font color='green'>供应</font></a>
    <#else><a onclick="linkToInfo('/admin_Subscribeinfo_list.action','info_type_s=${subscribeinfo.info_type?if_exists}');"><font color='red'>求购</font></a></#if> </td>
    <td align="center">${subscribeinfo.info_attr?if_exists}</td>
    <td align="center">${subscribeinfo.in_date?if_exists}</td>
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
		<td align="right">商机标题:</td>
		<td><@s.textfield name="keyword_s"/></td>
	</tr>
	<tr>
	<td align="right">信息类型:</td>
	<td><@s.select id="info_type_s" name="info_type_s" list=r"#{'0':'供应','1':'求购'}"  headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
    <tr>
    <td align="right" >时间段:</td>
        <td>
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
         至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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