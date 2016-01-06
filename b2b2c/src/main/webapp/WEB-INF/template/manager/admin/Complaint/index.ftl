<html>
  <head>
    <title>投诉列表</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Complaint_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 投诉列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('complaint.info_id','/admin_Complaint_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('complaint.info_id')"/></td>
    <td width="10%"  align="center" class="top_td">投诉方</td>
    <td width="10%" align="center" class="top_td">被投诉方</td>
    <td width="10%"  align="center" class="top_td">投诉类型</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">投诉时间</td>
    <td width="6%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list complaintList as complaint>
  <tr>
    <td><input type="checkbox" name="complaint.info_id" value="${complaint.info_id?if_exists}"/></td>
    
    <td align="left">
    <#if complaint.cust_name?if_exists!=''>
    <#if complaint.cust_name?length lt 14>
    ${complaint.cust_name?if_exists}
    <#else>
    ${complaint.cust_name[0..13]}
    </#if>
    </#if></td>
    
    <td align="center">
    <#if complaint.get_cust_name?if_exists!=''>
    <#if complaint.get_cust_name?length lt 14>
    ${complaint.get_cust_name?if_exists}
    <#else>
    ${complaint.get_cust_name[0..13]}
    </#if>
    </#if></td>
    <td align="center">${complaint.model_value?if_exists}</td>
    <td align="center">
    <#if complaint.state_code?if_exists=='1'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="redcolor">等待介入中</span></a></#if>
    <#if complaint.state_code?if_exists=='2'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="greencolor">人工介入中</span></a></#if>
    <#if complaint.state_code?if_exists=='3'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="bluecolor">投诉已处理</span></a></#if>
    <#if complaint.state_code?if_exists=='4'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="orangecolor">仲裁中</span></a></#if>
    <#if complaint.state_code?if_exists=='5'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="redcolor">投诉已撤销</span></a></#if>
    <#if complaint.state_code?if_exists=='6'><a onclick="linkToInfo('/admin_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');"><span class="redcolor">无效投诉</span></a></#if></td>  
    <td align="center">${complaint.in_date?if_exists}</td>
    <td align="center">
     <#if complaint.state_code?if_exists=='3' || complaint.state_code?if_exists=='6' || complaint.state_code?if_exists=='5'><a onclick="linkToInfo('/admin_Complaint_view.action','complaint.info_id=${complaint.info_id?if_exists}');" title="查看"><img src="/include/images/view.gif" /></a></#if>
    <#if complaint.state_code?if_exists=='1'><a onclick="linkToInfo('/admin_Complaint_view.action','complaint.info_id=${complaint.info_id?if_exists}');" title="审核"><img src="/include/images/bj.gif" /></a></#if>
    <#if complaint.state_code?if_exists=='2' || complaint.state_code?if_exists=='4'><a onclick="linkToInfo('/admin_Complaint_view.action','complaint.info_id=${complaint.info_id?if_exists}');" title="仲裁"><img src="/include/images/admin/medit.gif" /></a></#if></td>
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
		 <td align="right">投诉类型:</td>
		    <td>
		     <@s.select name="comp_type_s"  list="comp_typeList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
		  </td>
	</tr>
	<tr>
		 <td align="right">投诉状态:</td>
		    <td>
		     <@s.select name="state_code_s"  list="state_codeList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
		  </td>
	</tr>
    <td class="table_name" >时间段:</td>
        <td>
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 

           至
         <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  /></td>
    </tr>  
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>

  </body>
</html>