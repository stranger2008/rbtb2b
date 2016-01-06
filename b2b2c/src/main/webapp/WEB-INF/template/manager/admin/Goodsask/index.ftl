<html>
  <head>
    <title>商品咨询</title>   
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Goodsask_list.action" method="post">
<@s.hidden name="goodsask.is_recom" id="admin_goodsask_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置: 商城管理 > 商品管理 > 商品咨询列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('goodsask.trade_id','/admin_Goodsask_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsask.trade_id')"/></td>
    
     	 <td width="10%"  align="center" class="top_td">咨询商品</td>
    
     	 <td width="10%"  align="center" class="top_td">咨询类型</td>
     	 
     	 <td width="10%"  align="center" class="top_td">咨询人</td>
    
     	 <td width="10%"  align="center" class="top_td">咨询时间</td>
    
     	 <td width="10%"  align="center" class="top_td">回复人</td>
    
     	 <td width="10%"  align="center" class="top_td">回复时间</td>
    
     	 <td width="10%"  align="center" class="top_td">是否有效</td>
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list goodsaskList as goodsask>
  <tr>
    <td><input type="checkbox" name="goodsask.trade_id" value="${goodsask.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${goodsask.goods_name?if_exists}</td>
    
    	<td align="center">${goodsask.c_type?if_exists}</td>
    
    	<td align="center">${goodsask.user_name?if_exists}</td>
    
    	<td align="center">${goodsask.c_date?if_exists}</td>
    
        <td align="center">
        <#if goodsask.re_cust_id?if_exists==''>还没有回复！<#else>${goodsask.re_cust_id?if_exists}</#if>
        </td>
    
    	<td align="center">${goodsask.re_date?if_exists}</td>
    	<td align="center">
    	<#if goodsask.is_enable?if_exists=='0'><font color="green">有效</font><#else><font color="red">无效</font></#if>
    	</td>  
    <td align="center"><a onclick="linkToInfo('/admin_Goodsask_view.action','goodsask.trade_id=${goodsask.trade_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">商品名称:</td> 
		<td><@s.textfield name="goods_name_s"  cssStyle="width:245px;"/></td>
	  </tr>
	  <tr>
		<td align="right">咨询类型:</td> 
		<td><@s.select name="c_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	  </tr>
	  
	  <tr>
		<td align="right">是否有效:</td>
		<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
	  </tr>
	  
      <tr>
		<td align="right">留言时间段:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
	   至
		<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		
		</td>
	  </tr>	 
	  <tr>
		<td align="right">回复时间段:</td>
		<td>
		<@s.textfield id="txtstartDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
	   至
		<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		
		</td>
	  </tr>	 
	  
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>


