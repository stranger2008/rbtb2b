<style type="text/css">
	.catareadiv{border-top:1px solid #E1E2E3;margin-top:-10px;}
	.catFont{font-weight:400px;color:#000000;}
	.cattd1{padding:7px 0px 1px 8px;width:80px;text-align:right;}
	.cattd2{padding:7px 0px 1px 8px;width:80px;text-align:right;}
	.cata1{width:150px;}
	.cateli1{width:135px;list-style-type:none;float:left;margin:1px 3px 1px 5px;line-height:25px;}
	.more_span{margin-left:3px;}
</style>

<div class="catareadiv">
	<table width="100%">
	<#if cateList?exists && (cateList.size() > 0) >
		<tr>
			<td class="cattd1" valign="top">按<b>分类</b>选择:</td>
			<td>
				<div class="dis_more_1 dis_more">
				   <#list cateList as category>
				   		<li class="cateli1">
					   		<a href="###;" title="${category.cat_name?if_exists}"  onclick="setHiddenVal('cat_id_para','${(category.cat_id)?if_exists}');" >
						   		<#if category.cat_name?length lt 7>
					            	${category.cat_name?if_exists}(${category.num?if_exists})
					            <#else>
					            	${category.cat_name[0..6]}...(${category.num?if_exists})
					            </#if>
					         </a>
				   		</li>
				   </#list>
				</div>
				<#if cateList?exists && (cateList.size() > 8) >
			         <span class="display_more more_span">
			            <a href="###;" onclick="displayMore('dis_more_1','60px',this);">[显示更多]</a>
			         </span>
		     	</#if>
			</td>
		</tr>
	</#if>
	 <#if areaList?exists && (areaList.size() > 0) >	
			<tr>
				<td class="cattd2"  valign="top">按<b>地区</b>选择:</td>
				<td>
				     <div class="dis_more_2 dis_more">
					    <#list areaList as area>
					   		<li class="cateli1">
						   		<a href="###;" title="${area.area_name?if_exists}"  onclick="setHiddenVal('area_value_para','${(area.area_id)?if_exists}');" >
							   		<#if area.cat_name?length lt 7>
						            	${area.area_name?if_exists}(${area.num?if_exists})
						            <#else>
						            	${area.area_name[0..6]}...(${area.num?if_exists})
						            </#if>
						         </a>
					   		 </li>
					     </#list>
					  </div>
					    <#if areaList?exists && (areaList.size() > 8) >
					         <span class="display_more more_span">
							 	<a href="###;" onclick="displayMore('dis_more_2','60px',this);">[显示更多]</a>
							 </span>
						 </#if>  
				</td>
			</tr>
		</#if>   
		
	</table>
</div>
   
  
  