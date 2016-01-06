<head>
<title>商品列表</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
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
  		 <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">上下架管理</a>
    </div>
<@s.form action="/bmall_Goods_shelvesList.action"  method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>商品管理</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>上下架管理</span></h2>
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
         <tr style="text-align:center;">
             <td class="fthstyle1" width="3%"></td>
	         <td class="fthstyle1"  width="20%">商品名称</td>
	         <td class="fthstyle1"  width="17%">所属分类</td>	         
	         <td class="fthstyle1"  width="10%">是否上架</td>
	         <td class="fthstyle1"  width="10%">销售价</td>
	         <td class="fthstyle1"  width="10%">免运费</td>
	         <td class="fthstyle1"  width="10%">状态</td>
	         <td class="fthstyle1"  width="10%">修改</td>
         <tr>
         <tr><td colspan="8"  class="manadiv">
			<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/>全选
			<input type="button" value="批量上架" class="submitbut" onclick="showSearch(this,'addCatDiv');"/>
			<input type="button" value="批量下架" class="submitbut" onclick="updateshelves('bmall_Goods_updatedownshelves.action','1');"/>
         </td></tr>         
	     <#list goodsList as goods>
	<tr style="text-align:center;">
    <td><input type="checkbox" name="goods.goods_id" value="${goods.goods_id?if_exists}"/></td>    
    	<td>${goods.goods_name?if_exists}</td>
    	
    	<td>${goods.cat_attr?if_exists}</td>
    
    	<td>
    	<#if goods.state_in=='1'>
    		<font class="greencolor">已上架</font>
    	<#elseif goods.state_before=='1'>
    		<font class="bluecolor">等待上架</font>
    	<#elseif goods.state_after=='1'>
    		<font class="redcolor"'>已下架</font>	
    	</#if>        
        <#if goods.state_in=='0'&goods.state_before=='0'&goods.state_after=='0'>
        	<font class="redcolor"'>已下架</font>  
        </#if>
    	</td>
    
    	<td>${goods.sale_price?if_exists}</td>

    
    	<td>
    	<a onclick="linkToInfo('/bmall_Goods_shelvesList.action','ship_s=${goods.is_ship?if_exists}');">
    		 <#if goods.is_ship?if_exists=='0'><font class="greencolor"'>是</font></a></#if>
  			 <#if goods.is_ship?if_exists=='1'><font class="redcolor">否</font></a></#if>
    	</a>    	
    	</td>
        <td>
		    <a onclick="linkToInfo('/bmall_Goods_shelvesList.action','state_s=${goods.info_state?if_exists}');">
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
	<!--批量上架区域开始-->
	<div class="divceng" style="display:none;border:1px solid #C9C8C8;background-color:#F3F3F3;padding:10px 20px 6px 20px;" id="addCatDiv">	
		<table align="left">	
			<tr>
				<td align="right">上架时间：</td>
				<td>
		         	<@s.textfield id="txtstartDate" name="up_date_s"  type="text" cssStyle="width:160px;" cssClass="Wdate" 
				    readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				</td>
			</tr>
			<tr>
				<td align="right">下架时间：</td>
				<td>
				    <@s.textfield id="txtendDate" name="down_date" cssStyle="width:160px;"  type="text" cssClass="Wdate" 
				    readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />      
		         </td>
			</tr>
			<tr>
				<td colspan="2" style="border:0px;">

			       <@s.hidden name="name"/>

			       <input type="button" value="保存" onclick="updateshelves('bmall_Goods_updateshelves.action','0');"/>
				   <input type="button" name="close" value="关闭" onclick="closeSearch('addCatDiv')"/>
				</td>
			</tr>
		</table>
	</div>
	<!--批量上架区域结束-->
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
   
<script type="text/javascript">

  function updateshelves(actionName,temp)
      {
    	var flag = false;
		$("input:checkbox[name='goods.goods_id']").each(function(){
			if(this.checked){
				flag=true;
			}			
		});				
			
		if(flag==false){
			 alertShow('请至少选择一条记录!','warning');
		 	 return false;
	 	}
	 	
	 	if(flag==true){
	 		if(temp=='1'){ 
		    	art.dialog({
				title: '系统信息提示',
		    	content: '<div class="decorate">确认批量下架吗？</div>',
		    	width: '15%',
		    	icon: 'question',
		    	yesFn: function () {
		    	document.forms[0].action=actionName;
				document.forms[0].submit();
		        return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });
		   }else{
		    	art.dialog({
				title: '系统信息提示',
		    	content: '<div class="decorate">确认批量上架吗？</div>',
		    	width: '15%',
		    	icon: 'question',
		    	yesFn: function () {
		    	document.forms[0].action=actionName;
				document.forms[0].submit();
		        return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });
		  }
	 	}	 	
	}   
</script>
   
</@s.form>
</body>
</html>
