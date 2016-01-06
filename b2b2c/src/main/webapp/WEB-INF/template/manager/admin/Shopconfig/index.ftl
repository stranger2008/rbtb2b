<html>
  <head>
    <title>店铺设置列表</title>   
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){         
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>
<@s.form action="/admin_Shopconfig_list.action" method="post">
<@s.hidden name="shopconfig.is_recom" id="admin_shopconfig_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 > 店铺设置列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('shopconfig.shop_id','/admin_Shopconfig_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('shopconfig.shop_id')"/></td>
    
     	 <td width="10%"  align="center" class="top_td">会员名称</td>
    
     	 <td width="10%"  align="center" class="top_td">商店名称</td>
    
     	 <td width="10%"  align="center" class="top_td">联系人</td>
    
     	 <td width="10%"  align="center" class="top_td">所在地区</td>
    
     	 <td width="5%"  align="center" class="top_td">状态</td>
    
     	 <td width="10%"  align="center" class="top_td">是否暂时关闭网站</td>
    
     	 <td width="10%"  align="center" class="top_td">操作时间</td>
    
        <td width="5%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list shopconfigList as shopconfig>
  <tr>
    <td><input type="checkbox" name="shopconfig.shop_id" value="${shopconfig.shop_id?if_exists}"/></td>    	
    
    	<td align="center">
    	 <a onclick="linkToInfo('/admin_Shopconfig_view.action','shopconfig.shop_id=${shopconfig.shop_id?if_exists}');">
    	${shopconfig.cust_name?if_exists}
    	</a>
    	</td>
    
    	<td align="center">${shopconfig.shop_name?if_exists}</td>
    
    	<td align="center">${shopconfig.contant_man?if_exists}</td>
    
    	<td align="center">${shopconfig.area_attr?if_exists}</td>
    
    	<td align="center">
    	 <#if shopconfig.info_state?if_exists=='0'>
    	     <a href="/admin_Shopconfig_list.action?info_state_s=0" >
            <font class="redcolor">未审核</font>
            </a>
         <#elseif shopconfig.info_state?if_exists=='1'>
          <a href="/admin_Shopconfig_list.action?info_state_s=1" >
            <font class="greencolor">正常</font>
             </a>
         <#elseif shopconfig.info_state?if_exists=='2'>
          <a href="/admin_Shopconfig_list.action?info_state_s=2" >
           <font class="bluecolor">未通过</font>
            </a>
         <#elseif shopconfig.info_state?if_exists=='3'>
          <a href="/admin_Shopconfig_list.action?info_state_s=3" >
            <span class="redcolor">禁用</span>
             </a>
         </#if>
    	</td>
    
    	<td align="center">
    	 <#if shopconfig.is_colse?if_exists=='0'>
    	  <a href="/admin_Shopconfig_list.action?is_colse_s=0" >
            <font class="greencolor">是</font>
            </a>
         <#else>
          <a href="/admin_Shopconfig_list.action?is_colse_s=1" >
             <span class="redcolor">否</span>
             </a>
         </#if>
    	</td>
    	<td align="center">${shopconfig.in_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Shopconfig_view.action','shopconfig.shop_id=${shopconfig.shop_id?if_exists}');"><img src="/include/images/audit.png" /></a>
    <a onclick="linkToInfo('/admin_Shiptemplate_list.action','custId=${shopconfig.cust_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
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
			<td align="right">商店名称:</td> 
			<td><@s.textfield name="shop_name_s"  cssStyle="width:245px;"/></td>
	   </tr>
	   <tr>
	   <tr>
			<td align="right">联系人:</td> 
			<td><@s.textfield name="contant_man_s"  cssStyle="width:245px;"/></td>
	   </tr>
	    <tr>
			<td align="right">地区:</td>
			<td><div id="arealist"></div></td>
	   </tr>
	    <tr>
			<td align="right">是否暂时关闭网站:</td>
			<td><@s.radio name="is_colse_s" list=r"#{'0':'是','1':'否'}"  /> </td>
	   </tr>
	    <tr>
			<td align="right">状态:</td>
			<td><@s.radio name="info_state_s" list=r"#{'1':'正常','3':'禁用'}"  /> </td>
	   </tr>
	   </tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
</@s.form>
</body>
</html>

