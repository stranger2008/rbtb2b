<html>
  <head>
    <title>商城会员等级设置列表</title>   
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Malllevelset_list.action" method="post">
<@s.hidden name="malllevelset.is_recom" id="admin_malllevelset_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统设置 > 商城会员等级设置
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Malllevelset_add.action','');">添加</a></li>
     <li class="sc"><a onclick="delInfo('malllevelset.level_id','/admin_Malllevelset_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('malllevelset.level_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">等级名称</td>
    
     	 <td width="10%"  align="center" class="top_td">积分下限</td>
    
     	 <td width="10%"  align="center" class="top_td">积分上限</td>
    
     	 <td width="10%"  align="center" class="top_td">区别卖家买家</td>
    
     	 <td width="10%"  align="center" class="top_td">默认折扣率</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list malllevelsetList as malllevelset>
  <tr>
    <td><input type="checkbox" name="malllevelset.level_id" value="${malllevelset.level_id?if_exists}"/></td>    
 	
    
    	<td align="center">${malllevelset.level_name?if_exists}</td>
    
    	<td align="center">${malllevelset.inter_lower?if_exists}</td>
    
    	<td align="center">${malllevelset.inter_height?if_exists}</td>
    
    	<td align="center">
    	<#if malllevelset.mem_type?if_exists=='0'>
    	 <a href="/admin_Malllevelset_list.action?mem_type_s=0" >
	   <span class="greencolor">卖家</span>
	   </a>
    	<#elseif malllevelset.mem_type?if_exists=='1'>
    	 <a href="/admin_Malllevelset_list.action?mem_type_s=1" >
		 <span class="bluecolor">买家</span>
		 </a>
    	</#if>
    	</td>
    
    	<td align="center">${malllevelset.discount?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Malllevelset_view.action','malllevelset.level_id=${malllevelset.level_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">等级名称:</td> 
		<td><@s.textfield name="level_name_s"  cssStyle="width:200px;"/></td>
	    </tr>
	    <tr>
		<td align="right">积分下限:</td> 
		<td><@s.textfield name="inter_lower_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
		<td align="right">积分上限:</td> 
		<td><@s.textfield name="inter_height_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
		<td align="right">区别卖家买家:</td> 	
		<td>
		<@s.radio name="mem_type_s" list=r"#{'0':'卖家','1':'买家'}" /> 
		</td>
	    </tr>
	    <tr>
		<td align="right">默认折扣率:</td> 
		<td>
		<@s.textfield name="discount_s"  cssStyle="width:100px;"/></td>
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

