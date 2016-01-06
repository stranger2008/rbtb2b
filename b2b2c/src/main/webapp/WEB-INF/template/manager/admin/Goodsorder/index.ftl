<html>
  <head>
    <title>订单商品表列表</title>   
  </head>
  <body>
<@s.form action="/admin_Goodsorder_list.action" method="post">
<@s.hidden name="goodsorder.is_recom" id="admin_goodsorder_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 订单管理 > 订单列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Goodsorder_add.action','');">添加订单商品表</a></li>
     <li class="sc"><a onclick="delInfo('goodsorder.order_id','/admin_Goodsorder_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsorder.order_id')"/></td>   
    
     	 <td width="10%"  align="center" class="top_td">订单号</td>
       
     	 <td width="10%"  align="center" class="top_td">收货人姓名</td>
     	 
     	 <td width="10%"  align="center" class="top_td">下单时间</td>
     	 
     	 <td width="10%"  align="center" class="top_td">订单总金额</td>
    
     	 <td width="8%"  align="center" class="top_td">付款状态</td>
    
     	 <td width="8%"  align="center" class="top_td">订单状态</td>
     	 
     	 <td width="8%"  align="center" class="top_td">发货状态</td>
       
     	 <td width="10%"  align="center" class="top_td">付款方式</td>
    
     	 <td width="10%"  align="center" class="top_td">买家会员名称</td>
    
   	     <td width="10%"  align="center" class="top_td">卖家会员名称</td>
    
     	 <td width="6%"  align="center" class="top_td">操作</td>   
  </tr>
  
  <#list goodsorderList as goodsorder>
  <tr>
    <td><input type="checkbox" name="goodsorder.order_id" value="${goodsorder.order_id?if_exists}"/></td>    
 	
    	<td align="center">${goodsorder.order_id?if_exists}</td> 
    	 
    	<td align="center">${goodsorder.consignee?if_exists}</td>
    
        <td align="center">${goodsorder.order_time?if_exists}</td>
        	
    	<td align="center">${goodsorder.tatal_amount?if_exists}</td>
    
    	<td align="center">${goodsorder.pay_id?if_exists}</td>
   
    	<td align="center">${goodsorder.order_para?if_exists}</td>
    
    	<td align="center">${goodsorder.pay_para?if_exists}</td>
    	
    	<td align="center">${goodsorder.send_para?if_exists}</td>
    
		<td align="center">${goodsorder.buy_cust_name?if_exists}</td>
 
		<td align="center">${goodsorder.sale_cust_name?if_exists}</td>
		          
    <td align="center">
	    <a onclick="linkToInfo('/admin_Goodsorder_audit.action','goodsorder.order_id=${goodsorder.order_id?if_exists}');"><img src="/include/images/view.gif" /></a>
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
			<td align="right">订单号:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">收货人姓名:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">买家会员名称:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">卖家会员名称:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">付款状态:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">订单状态:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
		</tr>
		
		<tr>
			<td align="right">发货状态:</td> 
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
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

