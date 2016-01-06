<html>
  <head>
    <title>求购审核列表</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	 <script type="text/javascript" src="/manager/js/supply.js"></script> 
	 <script type="text/javascript">
	  $(document).ready(function(){ 
	 	//所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"buy");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");	
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Buy_auditList.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 求购管理 > 审核求购列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">    
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('buy.buy_id','/admin_Buy_checkDel.action')">删除</a></li>
   </ul>

 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buy.buy_id')"/>&nbsp;</td>
    <td width="20%" align="center" class="top_td">求购标题</td>
    <td width="14%" align="center" class="top_td">会员名称</td>
    <td width="10%" align="center" class="top_td">求购类型</td>
    <td width="10%" align="center" class="top_td">所属分类</td>
    <td width="10%" align="center" class="top_td">所在地区</td>
    <td width="10%" align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">审核</td>
  </tr>
  <#list buyList as buy>
    <tr bgcolor="<#if buy_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="buy.buy_id" value="${buy.buy_id?if_exists}"/></td>
    <td align="left" class="audit_left">
    <#if buy.title?if_exists!=''>
	    <a  onclick="linkToInfo('/admin_Buy_audit.action','buy.buy_id=${buy.buy_id?if_exists}');">
		    <#if buy.title?length lt 16>    
		    ${buy.title?if_exists}</a>
		      <#if buy.is_recom='1'>
		        <a  onclick="linkToInfo('/admin_Buy_auditList.action','is_recom_s=${buy.is_recom?if_exists}'">
		        <font class="redcolor">[推荐]</font></a>
		      </#if>
		    <#else>
		     ${buy.title[0..15]}...
		    </#if>
	    </a>
    </#if>
    </td> 
    <td align="center">${buy.cust_name?if_exists}</td>
    <td align="center"><a  onclick="linkToInfo('/admin_Buy_auditList.action','type_s=${buy.buy_type?if_exists}')">${buy.model_value?if_exists}</a></td>
    <td align="center">
		<a onclick="linkToInfo('/admin_Buy_auditList.action','cat_attr_s=${buy.cat_attr_id?if_exists}');">${buy.cat_attr?if_exists}</a>
	</td>
    <td align="center">${buy.area_attr?if_exists}</td>
    <td align="center">
	    <a  onclick="linkToInfo('/admin_Buy_auditList.action','state_s=${buy.info_state?if_exists}')">
		     <#if buy.info_state?if_exists=='0'><font class='redcolor'>未审核</font></#if>
		    <#if buy.info_state?if_exists=='1'><font class='greencolor'>正常</font></#if>
		    <#if buy.info_state?if_exists=='2'><font class='bluecolor'>不通过</font></#if>
		    <#if buy.info_state?if_exists=='3'><font class='redcolor'>禁用</font></#if>
	    </a>
   </td>
    <td align="center">${buy.in_date?if_exists}</td>
    <td align="center">
    <a  onclick="linkToInfo('/admin_Buy_audit.action','buy.buy_id=${buy.buy_id?if_exists}');"><img src="/include/images/audit.png" /></a></td>
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
		<td align="right" style="width:90px;">求购标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">求购类型:</td>      
		<td><@s.select name="type_s" list="commparalist"  listKey="para_value" listValue="para_key" headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
		<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">所在地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<td align="right">是否推荐:</td>
		<td>
   <@s.select name="is_recom_s" list=r"#{'':'请选择','0':'取消推荐','1':'推荐'}" />
		</td>
	</tr>
	<tr>
    <td align="right">审核状态:</td>
	    <td>
		  <@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','2':'未通过'}"/>
		</td>
	</tr>
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