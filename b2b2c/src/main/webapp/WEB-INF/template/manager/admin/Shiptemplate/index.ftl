<html>
  <head>
    <title>记录运费模板信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Shiptemplate_list.action" method="post">
<@s.hidden name="shiptemplate.is_recom" id="admin_shiptemplate_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统设置 > 物流模板管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Shiptemplate_add.action','');">添加模板</a></li>
     <li class="sc"><a onclick="delInfo('shiptemplate.ship_id','/admin_Shiptemplate_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('shiptemplate.ship_id')"/></td>
    
     	 <td width="20%"  align="center" class="top_td">模板名称</td>
    
     	 <td width="20%"  align="center" class="top_td">开始地区</td>
    
     	 <td width="10%"  align="center" class="top_td">计价方式</td>
    
     	 <td width="30%"  align="center" class="top_td">支持快递</td>
    
     	 <td width="10%"  align="center" class="top_td">会员名称</td>
    
    
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list shiptemplateList as shiptemplate>
  <tr>
    <td><input type="checkbox" name="shiptemplate.ship_id" value="${shiptemplate.ship_id?if_exists}"/></td>    
 	   
    	<td align="center">${shiptemplate.ship_name?if_exists}</td>
    
    	<td align="center">${shiptemplate.area_attr?if_exists}</td>
    
    	<td align="center">
    	
	    	<#if shiptemplate.valuation_mode='1'>
				按件数
			<#elseif shiptemplate.valuation_mode='2'>
				按重量
			<#elseif shiptemplate.valuation_mode='3'>    
				按体积
			</#if>	
    	
    	</td>
    
    	<td align="center">
    		${shiptemplate.smode_attr?if_exists}    		
    	</td>
    
    	<td align="center">${shiptemplate.cust_name?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Shiptemplate_view.action','shiptemplate.ship_id=${shiptemplate.ship_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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

</@s.form>
</body>
</html>

