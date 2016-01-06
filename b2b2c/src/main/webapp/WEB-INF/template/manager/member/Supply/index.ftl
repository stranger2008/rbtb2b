<html>
<head>
<title>供应管理</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	 <script type="text/javascript">
	  $(document).ready(function(){ 		 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"supply");           
	  });
	</script>
</head>
<body>
<@s.form action="/member_Supply_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>供应管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a  href="###" onclick="linkToInfo('/member_Supply_cate.action','');">添加供应</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('supply.supply_id','/member_Supply_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('supply.supply_id')"/>&nbsp;</td>
    <td width="20%" align="center" class="top_td">供应标题</td>
    <td width="8%" align="center" class="top_td">供应类型</td>
    <td width="15%" align="center" class="top_td">所属分类</td>   
    <td width="10%" align="center" class="top_td">审核状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="20%" align="center" class="top_td">编辑</td>
  </tr>
   <#list supplyList as supply>
    <tr bgcolor="<#if supply_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="supply.supply_id" value="${supply.supply_id?if_exists}"/></td>
    <td align="left">    
    <#if supply.title?if_exists!=''>
    <#if supply.title?length lt 12>
    <a  href="###" onclick="linkToInfo('/member_Supply_view.action','supply.supply_id=${supply.supply_id?if_exists}');">${supply.title?if_exists}</a>
    <#if supply.mem_recom='1'>
       <font class="redcolor"> [推荐]</font>
    </#if>  
    <#else>
     <a  href="###" onclick="linkToInfo('/member_Supply_view.action','supply.supply_id=${supply.supply_id?if_exists}');">${supply.title[0..11]}...</a>
    <#if supply.mem_recom='1'>
       <font class="redcolor"> [推荐]</font>
    </#if>  
    </#if>
    </#if></td>
    <td align="center">
    <a onclick="linkToInfo('/member_Supply_list.action','type_s=${supply.supply_type?if_exists}');">
   ${supply.model_value?if_exists}</a></td>
    <td align="center">${supply.cat_attr?if_exists}</td>
    <td align="center">
	    <a onclick="linkToInfo('/member_Supply_list.action','state_s=${supply.info_state?if_exists}');">
		    <#if supply.info_state='0'>
		    	<font class="redcolor">未审核</font>
		    <#elseif supply.info_state='1'>
		        <font class="greencolor">正常</font>
		    <#elseif supply.info_state='2'>
		   		<font class="bluecolor">未通过</font>
		    <#else>
		    	<font class="orangecolor"> 禁用</font>
		    </#if>   
	    </a>  
    </td>
    <td align="center">${supply.in_date?if_exists}</td>
    <td align="center"><a  href="###" onclick="linkToInfo('/member_Supply_view.action','supply.supply_id=${supply.supply_id?if_exists}');" class="xg">修改</a>
    <a href="javascript:delOneInfo('supply.supply_id','/member_Supply_delete.action','${(supply.supply_id)?if_exists}');"  class="dele">删除</a></td>
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
		<td align="right" style="width:90px;">供应标题:</td>
		<td><@s.textfield name="title_s" cssStyle="width:210px;"/></td>
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
		<td align="right">推荐:</td>
		<td>
		    <@s.select name="mem_recom_s" list=r"#{'':'请选择','0':'取消推荐','1':'推荐'}" />
		</td>
	</tr>
	 <tr>
    <td align="right">搜索状态:</td>
	<td>
		    <@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}" />
		</td>
	</tr>
	<tr>
     <td align="right" >发布时间:</td>
             <td>             	
             <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		               
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
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<!--搜索区域结束-->
</@s.form>
</body>
</html>
