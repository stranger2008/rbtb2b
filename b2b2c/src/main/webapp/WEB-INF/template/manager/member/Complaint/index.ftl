<html>
<head>
<title>我发起的投诉</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Complaint_list.action" method="post">
  <div class="cont_main">
  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>投诉举报>我发起的投诉</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Complaint_add.action','');">我要投诉</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('complaint.info_id','/member_Complaint_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('complaint.info_id')"/></td>
    <td width="10%"  align="center" class="top_td">被投诉方</td>
    <td width="10%"  align="center" class="top_td">投诉类型</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">投诉时间</td>
    <td width="12%" align="center" class="top_td">编辑</td>
    </tr>
   <#list complaintList as complaint>
  <tr>
    <td><input type="checkbox" name="complaint.info_id" value="${complaint.info_id?if_exists}"/></td>
    <td align="center">
    <#if complaint.get_cust_name?if_exists!=''>
    <#if complaint.get_cust_name?length lt 8>
    ${complaint.get_cust_name?if_exists}
    <#else>
    ${complaint.get_cust_name[0..7]}</a>
    </#if>
    </#if></td>
    <td align="center">${complaint.model_value?if_exists}</td>
    <td align="center"> 
    <a onclick="linkToInfo('/member_Complaint_list.action','state_code_s=${complaint.state_code?if_exists}');">
    <#if complaint.state_code?if_exists=='1'><span class="redcolor">等待介入中</span></#if>
    <#if complaint.state_code?if_exists=='2'><span class="greencolor">人工介入中</span></#if>
    <#if complaint.state_code?if_exists=='3'><span class="bluecolor">投诉已处理</span></#if>
    <#if complaint.state_code?if_exists=='4'><span class="orangecolor">仲裁中</span></#if>
    <#if complaint.state_code?if_exists=='5'><span class="redcolor">投诉已撤销</span></#if>
    <#if complaint.state_code?if_exists=='6'><span class="redcolor">无效投诉</span></#if>
    </a>
    </td>  
    <td align="center">${complaint.in_date?if_exists}</td>
    <td align="center">
    <#if complaint.state_code?if_exists=='1' || complaint.state_code?if_exists=='6'>
    
    <a onclick="linkToInfo('/member_Complaint_view.action','complaint.info_id=${complaint.info_id?if_exists}');" class="xg">修改</a>
    </#if>
    <#if complaint.state_code?if_exists=='3'>
    
    <a onclick="linkToInfo('/member_Complaint_result.action','complaint.info_id=${complaint.info_id?if_exists}&type=send');"><img src="/include/images/view.gif"/>查看结果</a></#if>
    <#if complaint.state_code?if_exists!='5' && complaint.state_code?if_exists!='6' && complaint.state_code?if_exists!='3'>
    
    <a onclick="linkToInfo('/member_Complaint_updatestate.action','info_id=${complaint.info_id?if_exists}&state_code=5');" class="dele">撤销投诉</a>
    <#else>

    <a onclick="linkToInfo('/member_Complaint_view.action','complaint.info_id=${complaint.info_id?if_exists}');" class="xg">再次投诉</a>
    </#if>
    </td>
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
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>
</body>
</html>
