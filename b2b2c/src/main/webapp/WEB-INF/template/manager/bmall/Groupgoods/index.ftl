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
  		 </span><a href="#">我是卖家</a><span>></span><a href="#">促销管理</a><span>></span><a href="#">团购管理</a>
    </div>
<@s.form action="/bmall_Groupgoods_list.action"  method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>促销管理</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>团购管理</span></h2>
       <table width="100%">
       	<tr>
       		<td align="right">团购标题:</td> 
			<td><@s.textfield name="title_s"/></td>
			<td align="right">商品名称:</td> 
			<td><@s.textfield name="name_s"/></td>
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
			<td colspan="3"><div id="divlist"></div></td>			
		</tr>
       </table>
              
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
             <td class="fthstyle1" width="3%"></td>
	         <td width="20%" class="fthstyle1">团购标题</td>
     		 <td width="10%"  class="fthstyle1">商品名称</td>
	    	 <td width="15%"  class="fthstyle1">商品分类</td>
	    	 <td width="10%"  class="fthstyle1">保证金（元）</td>
	    	 <td width="15%" class="fthstyle1">团购价格（元）</td>
             <td width="10%" class="fthstyle1">团购状态</td>
             <td width="10%" class="fthstyle1">审核状态</td>
             <td width="10%" class="fthstyle1">修改</td>
         </tr>
         <tr><td colspan="10"  class="manadiv">
         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('groupgoods.group_id')"/>全选
			<input type="button" value="添加" class="submitbut" onclick="linkToInfo('/bmall_Groupgoods_add.action','');"/>
			<input type="button" value="删除" class="submitbut" onclick="delInfo('groupgoods.group_id','/bmall_Groupgoods_delete.action')"/>
         </td></tr>
	      <#list groupgoodsList as groupgoods>
  	<tr style="text-align:center;">
    	<td><input type="checkbox" name="groupgoods.group_id" value="${groupgoods.group_id?if_exists}"/></td>    
 
 	 	<td>${groupgoods.group_title?if_exists}</td>
 	 
    	<td>${groupgoods.goods_name?if_exists}</td>
       
    	<td>${groupgoods.cat_attr?if_exists}</td>
    
    	<td>${groupgoods.bond?if_exists}</td>
    
    	<td>${groupgoods.group_price?if_exists}</td>
    
    	<td>
			<#if groupgoods.state_in=='1'>
			      进行中
			<#elseif groupgoods.state_before=='1'>
				 未开始
			<#elseif groupgoods.state_after=='1'>
				 已结束
			</#if>
		</td>
    	<td>
	    	<a onclick="linkToInfo('/bmall_Groupgoods_list.action','state_s=${groupgoods.info_state?if_exists}');">
			    <#if groupgoods.info_state?if_exists=='0'><font class="redcolor"'>未审核</font></#if>
			    <#if groupgoods.info_state?if_exists=='1'><font class="greencolor">正常</font></#if>
			    <#if groupgoods.info_state?if_exists=='2'><font class="bluecolor">未通过</font></#if>
			    <#if groupgoods.info_state?if_exists=='3'><font class="redcolor">禁用</font></#if>
			</a>
    	</td>
    	<td><a onclick="linkToInfo('/bmall_Groupgoods_view.action','groupgoods.group_id=${groupgoods.group_id?if_exists}');">修改</a></td>
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
