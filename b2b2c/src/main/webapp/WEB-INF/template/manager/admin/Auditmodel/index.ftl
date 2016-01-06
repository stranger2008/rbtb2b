<html>
  <head>
    <title>审核模型信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Auditmodel_list.action" method="post">
<@s.hidden name="auditmodel.is_recom" id="admin_auditmodel_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 审核模式设置
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Auditmodel_add.action','');">添加审核模型</a></li>
     <li class="sc"><a onclick="delInfo('auditmodel.model_type','/admin_Auditmodel_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="1%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('auditmodel.model_type')"/></td>
    
     	 <td width="30%"  align="center" class="top_td">模型类型</td>
    
     	 <td width="60%"  align="center" class="top_td">审核顺序</td>
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
 
  <#list auditList as auditmodel>
  <tr>
    <td><input type="checkbox" name="auditmodel.model_type" value="${auditmodel.model_type?if_exists}"/></td>    
 	
    	<td align="center">${auditmodel.para_key?if_exists}</td>
    
    	<td align="center">${auditmodel.audit_sortno_name?if_exists}</td>
              
    <td align="center"><a onclick="linkToInfo('/admin_Auditmodel_view.action','audti_model_type=${auditmodel.model_type?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">下载标题:</td> 
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

