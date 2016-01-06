<html>
  <head>
    <title>记录区域设置信息列表</title>   
      <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Areaset_list.action" method="post" >
<@s.hidden name="smode_id" />
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统配置 > 配送方式>区域设置列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Areaset_add.action','smode_id=${smode_id?if_exists}');">添加区域设置</a></li>
     <li class="sc"><a onclick="delInfo('areaset.areaset_id','/admin_Areaset_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
      <li class="ret"><a onclick="linkToInfo('/admin_Sendmode_list.action','');">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('areaset.areaset_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">配送方式名称</td>
    
     	 <td width="10%"  align="center" class="top_td">区域名称</td>
    
     	 <td width="10%"  align="center" class="top_td">开始地址</td>
    
     	 <td width="10%"  align="center" class="top_td">到达地址</td>
    
     	 <td width="10%"  align="center" class="top_td">首重价格</td>
    
     	 <td width="10%"  align="center" class="top_td">续重价格</td>
     	 
     	 <td width="10%"  align="center" class="top_td">首重</td>
     	
     	 <td width="10%"  align="center" class="top_td">续重</td>
    
     	 
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list areasetList as areaset>
  <tr bgcolor="<#if areaset_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="areaset.areaset_id" value="${areaset.areaset_id?if_exists}"/></td>    
 	
    	<td align="center">${areaset.smode_name?if_exists}</td>
    
    	<td align="center">${areaset.areaset_name?if_exists}</td>
    
    	<td align="center">${areaset.start_area?if_exists}</td>
    
    	<td align="center">${areaset.end_area?if_exists}</td>
    
    	<td align="center">${areaset.first_price?if_exists}</td>
    	
    	<td align="center">${areaset.cont_price?if_exists}</td>
    
        <td align="center">${areaset.first_weight?if_exists}</td>
        
    	<td align="center">${areaset.cont_weight?if_exists}</td>
    
    	
    
    	
    
          
    <td align="center"><a onclick="linkToInfo('/admin_Areaset_view.action','areaset.areaset_id=${areaset.areaset_id?if_exists}&smode_id=${smode_id?if_exists}');">
    <img src="/include/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists} </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">区域名称:</td> 
		<td><@s.textfield name="areaset_name_s"  cssStyle="width:245px;"/></td>
	    </tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','','','');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

