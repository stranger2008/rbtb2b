<html>
<head>
<title>简历管理</title>
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
<@s.form action="/member_Resume_list.action" method="post">
  <div class="cont_main">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>简历管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li">
       <a onclick="linkToInfo('/member_Resume_cate.action','');">添加简历</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('resume.resume_id','/member_Resume_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('resume.resume_id')"/></td>
    <td width="15%" align="center" class="top_td">简历名称</td>
     <td width="15%" align="center" class="top_td">分类</td>
      <td width="15%" align="center" class="top_td">地区</td>
    <td width="7%" align="center" class="top_td">真实姓名</td>
    <td width="7%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="15%" align="center" class="top_td">编辑</td>
  </tr>
    <#list resumeList as sysresume>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="resume.resume_id" value="${sysresume.resume_id?if_exists}"/></td>
    <td align="left">
    <#if sysresume.resume_name?if_exists!=''>
    
    <a  onclick=linkToInfo("/member_Resume_view.action","resume.resume_id=${sysresume.resume_id?if_exists}");>
     <#if sysresume.resume_name?length lt 10>
   ${sysresume.resume_name?if_exists}
   <#else>
    ${sysresume.resume_name[0..9]}
   </#if>
   </a>
   </#if>
    </td>
    <td align="center">${sysresume.cat_attr?if_exists}</td>
    <td align="center">${sysresume.area_attr?if_exists}</td>
    <td align="center">${sysresume.real_name?if_exists}</td>
    <td align="center">
    <#if sysresume.info_state?if_exists='0'>
    <a href="/member_Resume_list.action?job_state_s=0">
   <font class="redcolor">
    未审核
    </font>
   </a>
    <#elseif sysresume.info_state?if_exists='1'>
    <a href="/member_Resume_list.action?job_state_s=1">
   <font class="greencolor">
    正常
    </font>
   </a>
    <#elseif sysresume.info_state?if_exists='2'>
    <a href="/member_Resume_list.action?job_state_s=2">
   <font class="bluecolor">
    未通过
    </font>
   </a>
    <#elseif sysresume.info_state?if_exists='3'>
    <a href="/member_Resume_list.action?job_state_s=3">
   <font class="orangecolor">
     禁用
     </font>
   </a>
    </#if></td>
    <td align="center">${sysresume.in_date?if_exists}</td>
    <td align="center">
       <a class="xg" onclick=linkToInfo("/member_Resume_view.action","resume.resume_id=${sysresume.resume_id?if_exists}");>修改
    </a>
    <a href="javascript:delOneInfo('resume.resume_id','/member_Resume_delete.action','${(sysresume.resume_id)?if_exists}');" class="dele">
    删除</a></td>
  </tr>
    </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">简历名称:</td>
		<td><@s.textfield name="resume_name_s" cssStyle="width:200px;" maxLength="30" /></td>
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
		<td><@s.textfield name="real_name_s" maxLength="20" cssStyle="width:200px;"/></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="job_state_s" cssStyle="width:100px;" list=r"#{'请选择':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}"  />
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
