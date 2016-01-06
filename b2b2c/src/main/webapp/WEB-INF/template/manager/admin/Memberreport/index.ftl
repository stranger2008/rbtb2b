<html>
  <head>
    <title>举报管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Memberreport_list.action" method="post">
<@s.hidden name="video.is_recom" id="admin_video_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 举报管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('memberreport.report_id','/admin_Memberreport_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberreport.report_id')"/></td>
     <td width="10%"  align="center" class="top_td">举报人</td>
    <td width="20%"  align="center" class="top_td">举报地址</td>
    <td width="10%"  align="center" class="top_td">举报类型</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
   </tr>
  
    <#list memberreportList as report>
  <tr>
    <td><input type="checkbox" name="memberreport.report_id" value="${report.report_id?if_exists}"/></td>
    <td align="center">${report.cust_name?if_exists}</td>
    <td align="center">
    <#if report.link_url?if_exists!=''>
    <#if report.link_url?length lt 30>
    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url?if_exists}</a>
    <#else>
    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url[0..29]}</a>
    </#if>
    </#if></td>
    <td align="center">${report.model_value?if_exists}</td>
    <td align="center"><#if report.info_state?if_exists=='0'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="redcolor">等待处理</span></a></#if>
    <#if report.info_state?if_exists=='1'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="greencolor">受理中</span></a></#if>
    <#if report.info_state?if_exists=='2'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="bluecolor">已撤销</span></a></#if>
    <#if report.info_state?if_exists=='3'><a onclick="linkToInfo('/admin_Memberreport_list.action','info_state_s=${report.info_state?if_exists}');"><span class="orangecolor">已完结</span></a></#if></td> 
     <td align="center">${report.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberreport_view.action','memberreport.report_id=${report.report_id?if_exists}');"><img src="/include/images/bj.gif" /></a>
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
		<td align="right" width="30%">举报人:</td>
		<td><@s.textfield name="user_name_s"/></td>
	</tr>
	<tr>
		 <td align="right">举报类型:</td>
		    <td>
		     <@s.select name="report_type_s"  list="CommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
		  </td>
	</tr>
	 <tr>
             <td align="right">处理状态:</td>
             <td>
             	<@s.select name="info_state_s" list=r"#{'0':'等待处理','1':'受理中','2':'已撤销','3':'已完结'}" headerKey="" headerValue="请选择"/>
            </td>
     </tr>
     <tr>
    <td width="25%" align="right" >时间段:</td>
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
<!--搜索区域结束-->
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>

  </body>
</html>