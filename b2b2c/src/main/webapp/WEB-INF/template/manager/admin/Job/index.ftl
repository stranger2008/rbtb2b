<html>
  <head>
    <title>招聘列表</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
      <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"job");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");

	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Job_list.action" method="post">
<@s.hidden name="job.is_recom" id="admin_job_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 招聘列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Job_cate.action','');">添加招聘</a></li>
     
     <li class="sc"><a onclick="delInfo('job.job_id','/admin_Job_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','job.job_id','/admin_Job_updateisrecom.action','admin_job_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','job.job_id','/admin_Job_updateisrecom.action','admin_job_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('job.job_id')"/></td>
    <td width="20%"  align="center" class="top_td">招聘标题</td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="18%"  align="center" class="top_td">分类</td>
    <td width="18%" align="center" class="top_td">地区</td>
    <td width="5%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
     <td width="5%" align="center" class="top_td" >修改</td>
  </tr>
  
  <#list jobList as sysjob>

    <tr bgcolor="<#if sysjob_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="job.job_id" value="${sysjob.job_id?if_exists}"/></td>
     <td align="left"> 
    <#if sysjob.title?if_exists!=''>
    <a onclick=linkToInfo("/admin_Job_view.action","job.job_id=${sysjob.job_id?if_exists}");>
    <#if sysjob.title?length lt 18>
   ${sysjob.title?if_exists}
   <#else>
    ${sysjob.title[0..17]}...
   </#if>
   </a>
   </#if>
   <#if sysjob.is_recom?if_exists='1'>
    <span class="redcolor">[推荐]</span>
    </#if>
   </td>
    <td align="center">
    <a href="/admin_Member_viewinfo.action?member.cust_id=${(sysjob.cust_id)?if_exists}" target="_blank">
	    <#if sysjob.cust_name?if_exists!=''>
	      <#if sysjob.cust_name?length lt 20>
	   ${sysjob.cust_name?if_exists}
	   <#else>
	    ${sysjob.cust_name[0..19]}...
	   </#if>
	   </#if>
	 </a>
    </td>
   <td align="center">
         <a onclick="linkToInfo('/admin_Job_list.action','cat_attr_s=${sysjob.cat_attr_id?if_exists}');">${sysjob.cat_attr?if_exists}</a>
   </td>
    <td align="center">${sysjob.area_attr?if_exists}</td>
   <td align="center" >
    <#if sysjob.info_state?if_exists='1'>
   <a href="/admin_Job_list.action?job_state_s=1" >
   <span class="greencolor">正常</span>
   </a>
   <#elseif sysjob.info_state?if_exists='3'>
   <a href="/admin_Job_list.action?job_state_s=3" >
   <span class="redcolor">禁用</span>
   </a>
   <#elseif sysjob.info_state?if_exists='0'>
   <a href="/admin_Job_list.action?job_state_s=0" >
   <span class="redcolor">未审核</span>
   </a>
   <#elseif sysjob.info_state?if_exists='2'>
   <a href="/admin_Job_list.action?job_state_s=2" >
   <span class="bluecolor">未通过</span>
   </a>
   </#if>
   </td>
    <td align="center" >${sysjob.in_date?if_exists}</td >
    <td align="center">
     <a onclick=linkToInfo("/admin_Job_view.action","job.job_id=${sysjob.job_id?if_exists}");>
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
		<td align="right">招聘标题:</td>
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
		<td align="right">状态:</td>
		<td><@s.select name="job_state_s" list=r"#{'4':'请选择','1':'正常','3':'禁用'}" cssStyle="width:200px;"  /> </td>
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
