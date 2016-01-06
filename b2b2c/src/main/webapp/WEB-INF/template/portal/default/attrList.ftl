<style type="text/css">
	.searchspan{padding:3px 6px 3px 6px;}
	.searchattr{margin-top:-2px;}
</style>
<br/>
	  <#if attrList?exists && ( attrList.size() > 0 ) >	  
        <div class="industry_main">
		        <#if attrString?if_exists==''>
					<#list attrList as attr>
						<#assign attrString = attrString?if_exists + "${attr.attr_id?if_exists}|none,">
					</#list>
				</#if>
	           <#list attrList as attr>
	              <table width="100%">
	                <tr>
	                <td width="17%" valign="middle" style="border-top:1px dashed #E7CDAE;font-weight:bolder;font-size:13px;text-align:right;" >
	                ${attr.attr_name?if_exists}:</td>
	                <td width="83%" style="border-top:1px dashed #E7CDAE;"> 
	                    <a class="searchattr defalutColor" href="###;" onclick="setAttrForm(${attr.attr_id?if_exists},'none');">
	                    <span class='searchspan'
	                    <#if ( (attrString?if_exists?index_of("${attr.attr_id?if_exists}|none")) > -1 ) >   
		                    style="background-color:#4598D2;color:#FFFFFF;"
						</#if>>
	                    不限
	                    </span>
	                    </a>  
	                   <#list attrvalueList as value>  
	                   		<#if attr.attr_id == value.attr_id>
	                   			    <a class="searchattr" href="###;" 
				                    onclick="setAttrForm(${attr.attr_id?if_exists},${value.trade_id?if_exists});">
				                    <span  class='searchspan'
				                    <#if ( (attrString?if_exists?index_of("${attr.attr_id?if_exists}|${value.trade_id?if_exists}")) > -1 ) >   
					                    style="background-color:#4598D2;color:#FFFFFF;"
									</#if>>
				                   		${value.attr_value?if_exists}
				                    </span>
				                    
				                    </a>  
	                   		</#if>	                   		          
	                   </#list>
	                </td>
	                </tr>
	              </table>           
	           </#list>
        </div>   
     </#if>
     <@s.hidden name="attrString" id="product_attr_id" value="${attrString?if_exists}"/>	