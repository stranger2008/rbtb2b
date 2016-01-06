<html>
  <head>
    <title>团购商品列表</title>   
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
		  $(document).ready(function(){ 
			 //所属分类的回选
	         cate_select(${cfg_topcatid?if_exists},1,"goods");           
		  });             
	 </script>
  </head>
  <body>
<@s.form action="/admin_Groupgoods_auditList.action" method="post">
<@s.hidden name="groupgoods.is_recom" id="admin_groupgoods_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 促销管理 > 团购列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
 </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
   		 <td width="18%"  align="center" class="top_td">团购商品标题</td>
    
     	 <td width="10%"  align="center" class="top_td">商品名称</td>
    
     	 <td width="10%"  align="center" class="top_td">会员名称</td>
    
     	 <td width="10%"  align="center" class="top_td">所属分类</td>
    
     	 <td width="6%"  align="center" class="top_td">保证金</td>
    
     	 <td width="6%"  align="center" class="top_td">团购价格</td>
    
     	 <td width="8%"  align="center" class="top_td">是否已结束</td>
  
     	 <td width="10%"  align="center" class="top_td">是否免运费</td>
    
     	 <td width="10%"  align="center" class="top_td">状态</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list groupgoodsList as groupgoods>
  <tr>

 	 	<td align="center">${groupgoods.group_title?if_exists}</td>
 	 
    	<td align="center">${groupgoods.goods_name?if_exists}</td>
    
    	<td align="center">${groupgoods.cust_name?if_exists}</td>
    
    	<td align="center">${groupgoods.cat_attr?if_exists}</td>
    
    	<td align="center">${groupgoods.bond?if_exists}</td>
    
    	<td align="center">${groupgoods.group_price?if_exists}</td>
    
    	<td align="center">
			<#if groupgoods.state_in=='1'>
			     进行中
			<#elseif groupgoods.state_before=='1'>
				 未开始
			<#elseif groupgoods.state_after=='1'>
				已结束
			</#if>

		</td>
    
   
    	<td align="center">
	    	 <a onclick="linkToInfo('/admin_Groupgoods_list.action','state_s=${groupgoods.is_ship?if_exists}');">
	    		 <#if groupgoods.is_ship?if_exists=='0'><font class="greencolor"'>是</font></a></#if>
	  			 <#if groupgoods.is_ship?if_exists=='1'><font class="redcolor">否</font></a></#if>
	    	 </a>    
    	</td>

    	<td align="center">
	    	<a onclick="linkToInfo('/admin_Groupgoods_list.action','state_s=${groupgoods.info_state?if_exists}');">
			    <#if groupgoods.info_state?if_exists=='0'><font class="redcolor"'>未审核</font></#if>
			    <#if groupgoods.info_state?if_exists=='1'><font class="greencolor">正常</font></#if>
			    <#if groupgoods.info_state?if_exists=='2'><font class="bluecolor">未通过</font></#if>
			    <#if groupgoods.info_state?if_exists=='3'><font class="redcolor">禁用</font></#if>
			</a>
    	</td>
    
          
    <td align="center"><a onclick="linkToInfo('/admin_Groupgoods_audit.action','groupgoods.group_id=${groupgoods.group_id?if_exists}');"><img src="/include/images/audit.png" /></a></td>
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
   		<td align="right">团购标题:</td> 
		<td><@s.textfield name="title_s"/></td>
	</tr>
		<tr>
		<td align="right">会员名称:</td> 
		<td><@s.textfield name="cust_name_s"  /></td>
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

