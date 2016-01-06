<html>
  <head>
    <title>店铺动态评分列表</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Sellerscore_list.action" method="post">
<@s.hidden name="cust_id_s" value="${cust_id_s?if_exists}"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 >店铺动态评分列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('sellerscore.trade_id','/admin_Sellerscore_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
      <li class="ret"><a onclick="linkToInfo('admin_Sellerscore_countindex.action','');">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td">
    
    <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sellerscore.trade_id')"/></td>
    
     	 <td width="10%"  align="center" class="top_td">商店名称</td>
    
     	 <td width="10%"  align="center" class="top_td">打分人</td>
    
     	 <td width="10%"  align="center" class="top_td">描述相符打分</td>
    
     	 <td width="10%"  align="center" class="top_td">服务态度打分</td>
    
     	 <td width="10%"  align="center" class="top_td">发货速度打分</td>
    
     	 <td width="10%"  align="center" class="top_td">打分时间</td>
     	 
  </tr>
  
  <#list sellerscoreList as sellerscore>
   <tr bgcolor="<#if sellerscore_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="sellerscore.trade_id" value="${sellerscore.trade_id?if_exists}"/></td>    
    
    	<td align="center">${sellerscore.cust_name?if_exists}</td>
    
    	<td align="center">${sellerscore.user_name?if_exists}</td>
    
    	<td align="center">
    	<#if sellerscore.desc_score=="">0<#else>${sellerscore.desc_score?if_exists}</#if>
    	</td>
    
    	<td align="center">
    	<#if sellerscore.service_score=="">0<#else>${sellerscore.service_score?if_exists}</#if>
    	</td>
    
    	<td align="center">
    	<#if sellerscore.delivery_score=="">0<#else>${sellerscore.delivery_score?if_exists}</#if>
    	</td>
    
    	<td align="center">${sellerscore.in_date?if_exists}</td>
          
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
		<td align="right">会员名称:</td> 
		<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
	    </tr>
	    <tr>
	    <td align="right">打分时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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

