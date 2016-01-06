<html>
  <head>
    <title>商品评价</title>   
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Goodseval_list.action" method="post">
<@s.hidden name="goodseval.is_recom" id="admin_goodseval_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品评价列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Goodseval_add.action','');">添加</a></li>
     <li class="sc"><a onclick="delInfo('goodseval.trade_id','/admin_Goodseval_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodseval.trade_id')"/></td>

     	 <td width="10%"  align="center" class="top_td">商品名称</td>
    
     	 <td width="10%"  align="center" class="top_td">评论人</td>
    
     	 <td width="10%"  align="center" class="top_td">商品评级</td>
    
     	 <td width="10%"  align="center" class="top_td">评价时间</td>
    
     	 <td width="10%"  align="center" class="top_td">回复人</td>
    
     	 <td width="10%"  align="center" class="top_td">回复时间</td>
    
     	 <td width="10%"  align="center" class="top_td">是否有效</td>
    
    
    <td width="5%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list goodsevalList as goodseval>
  <tr>
    <td><input type="checkbox" name="goodseval.trade_id" value="${goodseval.trade_id?if_exists}"/></td>    
    
    	<td align="center">${goodseval.goods_name?if_exists}</td>
    
    	<td align="center">${goodseval.user_name?if_exists}</td>
    
    	<td align="center">
    	<#if goodseval.g_eval?if_exists=='1'>好评</#if>
    	<#if goodseval.g_eval?if_exists=='0'>中评</#if>
    	<#if goodseval.g_eval?if_exists=='-1'>差评</#if>
    	</td>

    	<td align="center">${goodseval.eval_date?if_exists}</td>
    
    	<td align="center">
    	<#if goodseval.explan_cust_id?if_exists==''>暂无回复<#else>${goodseval.explan_cust_id?if_exists}</#if>
    	</td>
    
    	<td align="center">${goodseval.explan_date?if_exists}</td>
    
    	<td align="center">
    	<#if goodseval.is_enable?if_exists=='0'><font color='green'>有效</font><#else><font color='red'>无效</font></#if>
    	</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Goodseval_view.action','goodseval.trade_id=${goodseval.trade_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">商品评级:</td>
		<td><@s.select name="g_eval_s" list=r"#{'':'请选择','1':'好评','0':'中评','-1':'差评'}"/> </td>
	</tr>
	 <tr>
		<td align="right">评价时间段:</td>
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
		<td align="right">是否有效:</td>
		<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
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

