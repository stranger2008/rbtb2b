<html>
  <head>
    <title>供应审核列表</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	 <script type="text/javascript" src="/manager/js/supply.js"></script> 
	 <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"supply");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");	
	  });
	</script>
  </head>
<body>

<@s.form action="/admin_Supply_auditList.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 供应管理 > 供应审核列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('supply.supply_id','/admin_Supply_checkDel.action')">删除</a></li>
   </ul>

 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('supply.supply_id')"/>&nbsp;</td>
    <td width="20%" align="center" class="top_td">供应标题</td>
    <td width="14%" align="center" class="top_td">会员名称</td>
    <td width="10%" align="center" class="top_td">供应类型</td>
    <td width="10%" align="center" class="top_td">所在分类</td>
    <td width="10%" align="center" class="top_td">所在地区</td>
    <td width="10%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="8%" align="center" class="top_td">审核</td>
  </tr>
  <#list supplyList as supply>
    <tr bgcolor="<#if supply_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="supply.supply_id" value="${supply.supply_id?if_exists}"/></td>
    <td align="left">
	    <#if supply.title?if_exists!=''>
	       <a onclick="linkToInfo('/admin_Supply_audit.action','supply.supply_id=${supply.supply_id?if_exists}');">
			    <#if supply.title?length lt 20> ${supply.title?if_exists} <#else>${supply.title[0..18]}</#if>			    
		   </a>
		   <#if supply.is_recom='1'>
		   <a onclick="linkToInfo('/admin_Supply_auditList.action','is_recom_s=${supply.is_recom?if_exists}');">
				<font class="redcolor">[推荐]</a></font>
		   </#if>
	    </#if>
    </td>
    <td align="center"> ${supply.cust_name?if_exists}</td>
    <td align="center">
     <a onclick="linkToInfo('/admin_Supply_auditList.action','type_s=${supply.supply_type?if_exists}');">
     	${supply.model_value?if_exists}</a>
     </td>
    <td align="center">
     <a onclick="linkToInfo('/admin_Supply_list.action','cat_attr_s=${supply.cat_attr_id?if_exists}');">${supply.cat_attr?if_exists}</a>
    </td>
    <td align="center">
    ${supply.area_attr?if_exists}
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Supply_auditList.action','state_s=${supply.info_state?if_exists}');">
    <#if supply.info_state?if_exists=='0'><font class="redcolor">未审核</font></a></#if>
    <#if supply.info_state?if_exists=='1'><font class="greencolor">正常</font></a></#if>
    <#if supply.info_state?if_exists=='2'><font class="bluecolor">未通过</font></a></#if>
    <#if supply.info_state?if_exists=='3'><font class="redcolor">禁用</font></a></#if>
    </td>
    <td align="center">${supply.in_date?if_exists}</td>
    <td align="center">
     <a onclick="linkToInfo('/admin_Supply_audit.action','supply.supply_id=${supply.supply_id?if_exists}');">
     	<img src="/include/images/audit.png" alt="审核"/></a>
     	
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
		<td align="right" style="width:90px;">供应标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">供应类型:</td>      
		<td><@s.select name="type_s" list="commparalist"  listKey="para_value" listValue="para_key" headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">所在地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">是否推荐:</td>
		<td>
		     <@s.select name="is_recom_s" list=r"#{'':'请选择','0':'取消推荐','1':'推荐'}" />
		</td>
	</tr>
	 <tr>
    <td align="right">搜索状态:</td>
	<td>
		     <@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','2':'未通过'}" />
	</td>
	</tr>
	<tr>
     <td width="25%" align="right" style="width:auto;">发布时间:</td>
             <td>            
             <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
	<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
	<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<!--搜索区域结束-->
</@s.form>
  </body>
</html>