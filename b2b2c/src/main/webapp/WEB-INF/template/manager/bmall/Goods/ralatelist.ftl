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
<@s.form action="/bmall_Goods_ralatelist.action"  method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>商品列表</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>搜索条件</span></h2>
       <table width="100%">
       	<tr>
			<td align="right">商品编号:</td> 
			<td><@s.textfield name="goodsno_s" /></td>
			<td align="right">商品名称:</td> 
			<td><@s.textfield name="name_s"  cssStyle="width:180px;"/></td>
			<td align="right">商品品牌:</td> 
			<td><@s.textfield name="brand_s" /></td>
			<td rowspan="2">
				<input type="button" name="" value="" class="sbut"  onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');">
			</td>
		</tr>
		 <tr>
		 	<td align="right">所属分类:</td> 
			<td><div id="divlist"></div></td>
			<td align="right">是否免运费:</td> 
			<td><@s.select name="ship_s" list=r"#{'':'请选择','0':'是','1':'否'}" /></td>
			<td align="right">状态:</td> 
			<td><@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}" /></td>
		</tr>
       </table>
              
       <table cellspacing="0" class="bmall_list_table">
         <tr>
             <td class="fthstyle1" width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/></td>
	         <td class="fthstyle1"  width="20%">商品名称</td>
	         <td class="fthstyle1"  width="10%">所属分类</td>	         
	         <td class="fthstyle1"  width="10%">商品品牌</td>
	         <td class="fthstyle1"  width="10%">销售价</td>
	         <td class="fthstyle1"  width="10%">免运费</td>
	         <td class="fthstyle1"  width="10%">状态</td>
         <tr>
          <tr><td colspan="9"  class="manadiv">
			<input type="button" value="选中" onclick="selInfo('goods.goods_id',this);"/>
         </td></tr>   
	     <#list goodsList as goods>
<tr>
    <td><input type="checkbox"  class="ch_x"  name="goods.goods_id" value="${goods.goods_id?if_exists}"/>
    
         <input type="hidden" class="hid_v"  value="${goods.goods_name?if_exists}" id="${goods.goods_id?if_exists}"/>
    </td>    
    	<td align="center">${goods.goods_name?if_exists}</td>
    	
    	<td align="center">${goods.cat_attr?if_exists}</td>
    
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
			    <#if goods.info_state?if_exists=='0'><font class="redcolor"'>未审核</font></#if>
			    <#if goods.info_state?if_exists=='1'><font class="greencolor">正常</font></#if>
			    <#if goods.info_state?if_exists=='2'><font class="bluecolor">未通过</font></#if>
			    <#if goods.info_state?if_exists=='3'><font class="redcolor">禁用</font></#if>
		    </a>
	    </td>
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
