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
<@s.form action="/bmall_Shiptemplate_list.action"  method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>店铺管理</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>物流模板设置</span></h2>
             
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
             <td  class="fthstyle1" width="3%"></td>
	         <td  class="fthstyle1"  width="20%">模板名称</td>
	         <td  class="fthstyle1" width="20%">开始地区</td>	
	                  
	         <td  class="fthstyle1"  width="10%">计价方式</td>
	         <td  class="fthstyle1" width="23%">支持快递</td>
	         <td  class="fthstyle1" width="17%">会员名称</td>	         
	         <td  class="fthstyle1"  width="10%">修改</td>
         </tr>
         <tr><td colspan="8"  class="manadiv">
         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('shiptemplate.ship_id')"/>
			<input type="button" class="submitbut" value="添加" onclick="linkToInfo('/bmall_Shiptemplate_add.action','');"/>
			<input type="button" class="submitbut" value="删除" onclick="delInfo('shiptemplate.ship_id','/bmall_Shiptemplate_delete.action')"/>
         </td></tr>         
  <#list shiptemplateList as shiptemplate>
  <tr style="text-align:center;">
    	<td><input type="checkbox" name="shiptemplate.ship_id" value="${shiptemplate.ship_id?if_exists}"/></td>    
 	   
    	<td >${shiptemplate.ship_name?if_exists}</td>
    
    	<td >${shiptemplate.area_attr?if_exists}</td>
    
    	<td >
    	
	    	<#if shiptemplate.valuation_mode='1'>
				按件数
			<#elseif shiptemplate.valuation_mode='2'>
				按重量
			<#elseif shiptemplate.valuation_mode='3'>    
				按体积
			</#if>	
    	
    	</td>
    
    	<td>
	    	${shiptemplate.smode_attr?if_exists}	    	
    	</td>
    
    	<td >${shiptemplate.cust_name?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/bmall_Shiptemplate_view.action','shiptemplate.ship_id=${shiptemplate.ship_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
