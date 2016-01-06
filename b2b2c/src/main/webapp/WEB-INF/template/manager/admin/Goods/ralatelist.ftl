<html>
  <head>
    <title>相关商品列表</title>   
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript" src="/include/js/admin/selectnewlist.js"></script>
	 <script type="text/javascript">
		  $(document).ready(function(){ 
			 //所属分类的回选
	         cate_select(${cfg_topcatid?if_exists},1,"goods");           
		  });
		  //选择checkbox的值
		  function selInfo(field_name,obj){  
			  var flag = false;
	  	      $(".ch_x").each(function(){
		  	 		 if(this.checked==true){
		  	 		 	flag=true;
		  	 		 }		  			 
			  });
			  if(count==0){
				  alertShow('请至少选择一条记录!','warning');
				  return false;
				  runCount(3);
			  }			   
			  if(flag==true){
			      art.dialog({
				  title: '系统信息提示',
				  content: '<div class="decorate">您确定吗？</div>',
				  width: '15%',
				  icon: 'question',
				  yesFn: function () {
				     var id="";
				     var name="";
				     $(".ch_x").each(function(){
				  	 		 if(this.checked==true){
				  	 		 	id+=$(this).val()+",";
				  	 		 	name+=$(this).parent("td").find(".hid_v").val()+"####";
				  	 		 }		  			 
					 });	
					
   			        opener.setrelate(id,name,"relate_div");
   			        window.close();
				    return false;
				    },
				 noText: '关闭',
				 noFn: true //为true等价于function(){}
				 });
			   }
		    }
	 </script>
  </head>
  <body>
<@s.form action="/admin_Goods_ralatelist.action" method="post">
<@s.hidden name="goods.is_recom" id="admin_goods_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="qiyong"><a onclick="selInfo('goods.goods_id',this);">选中</a></li>
     <li class="ret"><a href="javascript:window.close();">返回</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/></td>
     	 <td width="10%"  align="center" class="top_td">会员名称</td>
     	 <td width="10%"  align="center" class="top_td">所属分类</td>
     	 <td width="10%"  align="center" class="top_td">商品名称</td>
     	 <td width="10%"  align="center" class="top_td">商品编号</td>
     	 <td width="10%"  align="center" class="top_td">商品品牌</td>
     	 <td width="10%"  align="center" class="top_td">销售价</td>
     	 <td width="10%"  align="center" class="top_td">免运费</td>
     	 <td width="10%"  align="center" class="top_td">状态</td>
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list goodsList as goods>
  <tr>
    <td><input type="checkbox" class="ch_x" name="goods.goods_id" value="${goods.goods_id?if_exists}"/>
     <input type="hidden" class="hid_v"  value="${goods.goods_name?if_exists}" id="${goods.goods_id?if_exists}"/>
    </td>    
    	<td align="center">${goods.cust_name?if_exists}</td>
    
    	<td align="center">${goods.cat_attr?if_exists}</td>
    
    	<td align="center">${goods.goods_name?if_exists}</td>
    
    	<td align="center">${goods.goods_no?if_exists}</td>
    
    	<td align="center">${goods.brand_name?if_exists}</td>
    
    	<td align="center">${goods.sale_price?if_exists}</td>
   
    	<td align="center">
    	<a onclick="linkToInfo('/admin_Goods_list.action','state_s=${goods.is_ship?if_exists}');">
    		 <#if goods.is_ship?if_exists=='0'><font class="greencolor"'>是</font></a></#if>
  			 <#if goods.is_ship?if_exists=='1'><font class="redcolor">否</font></a></#if>
    	</a>    	
    	</td>
        <td align="center">
	    <a onclick="linkToInfo('/admin_Goods_list.action','state_s=${goods.info_state?if_exists}');">
	    <#if goods.info_state?if_exists=='0'><font class="redcolor"'>未审核</font></a></#if>
	    <#if goods.info_state?if_exists=='1'><font class="greencolor">正常</font></a></#if>
	    <#if goods.info_state?if_exists=='2'><font class="bluecolor">未通过</font></a></#if>
	    <#if goods.info_state?if_exists=='3'><font class="redcolor">禁用</font></a></#if>
	    </td>
    <td align="center"><a onclick="linkToInfo('/admin_Goods_view.action','goods.goods_id=${goods.goods_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td><@s.textfield name="cust_name_s"  /></td>
	</tr>
				<tr>
		<td align="right">商品编号:</td> 
		<td><@s.textfield name="goodsno_s" /></td>
	</tr>
			<tr>
		<td align="right">商品名称:</td> 
		<td><@s.textfield name="name_s"  cssStyle="width:245px;"/></td>
	</tr>
			<tr>
		<td align="right">所属分类:</td> 
		<td><div id="divlist"></div></td>
	</tr>
    <tr>
		<td align="right">商品品牌:</td> 
		<td><@s.textfield name="brand_s"  cssStyle="width:245px;"/></td>
	</tr>
			<tr>
		<td align="right">是否免运费:</td> 
		<td>   <@s.select name="ship_s" list=r"#{'':'请选择','0':'是','1':'否'}" /></td>
	</tr>
			<tr>
		<td align="right">状态:</td> 
		<td>   <@s.select name="state_s" list=r"#{'':'请选择','1':'正常','3':'禁用'}" /></td>
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
</@s.form>
</body>
</html>

