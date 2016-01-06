<head>
<title>商品列表</title>
 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"goods");           
	  });             
 </script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">商品列表</a>
    </div>
<@s.form action="/bmall_Goods_list.action"  method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>商品管理</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>商品列表</span></h2>
       <table width="100%">
       	<tr>
			<td align="right">商品编号:</td> 
			<td><@s.textfield name="goodsno_s" cssStyle="width:80px;"/></td>
			<td align="right">商品名称:</td> 
			<td><@s.textfield name="name_s"  cssStyle="width:150px;"/></td>
			<td align="right">商品品牌:</td> 
			<td><@s.textfield name="brand_s"  cssStyle="width:80px;"/></td>
			<td align="right">状态:</td> 
			<td><@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}" /></td>
			<td rowspan="2">
				<input type="button" name="" value="" class="sbut"  onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');">
			</td>
		</tr>
		 <tr>
			<td align="right">是否免运费:</td> 
			<td><@s.select name="ship_s" list=r"#{'':'请选择','0':'是','1':'否'}" /></td>
			<td align="right">所属分类:</td> 
			<td colspan="5"><div id="divlist"></div></td>
		</tr>
       </table>
              
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
             <td class="fthstyle1" width="3%"></td>
	         <td class="fthstyle1"  width="23%">商品名称</td>
	         <td class="fthstyle1"  width="20%">所属分类</td>	
	                  
	         <td class="fthstyle1"  width="10%">商品品牌</td>
	         <td class="fthstyle1"  width="10%">销售价</td>
	         <td class="fthstyle1"  width="10%">免运费</td>	         
	         <td class="fthstyle1"  width="7%">状态</td>
	         <td class="fthstyle1"  width="10%">修改</td>
         </tr>
         <tr><td colspan="8"  class="manadiv">
         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/>
			<input type="button" class="submitbut" value="添加" onclick="linkToInfo('/bmall_Goods_add.action','');"/>
			<input type="button" class="submitbut" value="删除" onclick="delInfo('goods.goods_id','/bmall_Goods_delete.action')"/>
         </td></tr>         
	     <#list goodsList as goods>
<tr style="text-align:center;">
    <td><input type="checkbox" name="goods.goods_id" value="${goods.goods_id?if_exists}"/></td>    
    	<td>${goods.goods_name?if_exists}</td>
    	
    	<td>${goods.cat_attr?if_exists}</td>
    
    	<td>${goods.brand_name?if_exists}</td>
    
    	<td>${goods.sale_price?if_exists}</td>
   
    	<td>
    	<a onclick="linkToInfo('/bmall_Goods_list.action','ship_s=${goods.is_ship?if_exists}');">
    		 <#if goods.is_ship?if_exists=='0'><font class="greencolor"'>是</font></a></#if>
  			 <#if goods.is_ship?if_exists=='1'><font class="redcolor">否</font></a></#if>
    	</a>    	
    	</td>
        <td>
		    <a onclick="linkToInfo('/bmall_Goods_list.action','state_s=${goods.info_state?if_exists}');">
			    <#if goods.info_state?if_exists=='0'><font class="redcolor"'>未审核</font></#if>
			    <#if goods.info_state?if_exists=='1'><font class="greencolor">正常</font></#if>
			    <#if goods.info_state?if_exists=='2'><font class="bluecolor">未通过</font></#if>
			    <#if goods.info_state?if_exists=='3'><font class="redcolor">禁用</font></#if>
		    </a>
	    </td>
    <td><a onclick="linkToInfo('/bmall_Goods_view.action','goods.goods_id=${goods.goods_id?if_exists}');">修改</a></td>
  </tr>
</#list>
       </table>
       
        <div class="listbottom">
       		${pageBar?if_exists}
        </div>
      </div>     
   </div>
      
</div>
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
</@s.form>
</body>
</html>
