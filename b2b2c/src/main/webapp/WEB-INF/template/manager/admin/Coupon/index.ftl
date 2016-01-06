<html>
  <head>
    <title>优惠劵列表</title> 
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>  
     <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Coupon_list.action" method="post">
<@s.hidden name="coupon.is_every" id="admin_coupon_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 促销管理 > 优惠券列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Coupon_add.action','');">添加</a></li>
     <li class="sc"><a onclick="delInfo('coupon.c_id','/admin_Coupon_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('coupon.c_id')"/></td>
    
     	 <td width="10%"  align="center" class="top_td">优惠券名称</td>
    
     	 <td width="20%"  align="center" class="top_td">优惠券类型</td>

    
     	 <td width="10%"  align="center" class="top_td">使用次数</td>
    
     	 <td width="10%"  align="center" class="top_td">开始时间</td>
    
     	 <td width="10%"  align="center" class="top_td">结束时间</td>
    
     	 <td width="10%"  align="center" class="top_td">是否通用</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list couponList as coupon>
  <tr>
    <td><input type="checkbox" name="coupon.c_id" value="${coupon.c_id?if_exists}"/></td>    
 	
    	<td align="center">${coupon.c_name?if_exists}</td>
    
    	<td align="center">
    	<#if coupon.c_type?if_exists=="0">优惠金额:<big>${coupon.c_money?if_exists}</big>￥</#if>
    	<#if coupon.c_type?if_exists=="1">优惠积分:<big>${coupon.c_inter?if_exists}</big>分</#if>
    	<#if coupon.c_type?if_exists=="2">优惠折扣:<big>${coupon.c_discount?if_exists}</big>折</#if>
    	</td>
    
    	<td align="center">${coupon.coupon_times?if_exists}</td>
    
    	<td align="center">${coupon.start_time?if_exists}</td>
    
    	<td align="center">${coupon.end_time?if_exists}</td>
    
    	<td align="center">
    	<#if coupon.is_every?if_exists=="0"><font color="green">通用</font><#else><font color="red">不通用<font></#if>
    	</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Coupon_view.action','coupon.c_id=${coupon.c_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">优惠券名称:</td> 
		<td><@s.textfield name="c_name_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">优惠券类型:</td>
		<td><@s.select name="c_type_s" list=r"#{'':'请选择','0':'优惠金额','1':'优惠积分','2':'优惠折扣'}"/> </td>
	</tr>
	<tr>
		<td align="right">是否通用:</td>
		<td><@s.select name="is_every_s" list=r"#{'':'请选择','0':'不通用','1':'通用'}"/> </td>
	</tr>
	<tr >
		<td align="right">时间段:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		
	至
		
		<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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

