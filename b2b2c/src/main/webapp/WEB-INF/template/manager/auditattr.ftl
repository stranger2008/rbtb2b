<script type="text/javascript">
  function Map(){
	 this.keys = new Array();
	 this.data = new Array();
	 
	 this.put = function(key,value){
	  if(this.data[key] == null){
	   this.keys.push(value);
	  }
	  this.data[key] = value;
	 };
	 
	 this.get = function(key){
	  return this.data[key];
	 };
	 
	 this.remove = function(key){
	     for(var i=key+1;i<this.keys.length+1;i++){          
	     	this.data[i] = null;
	     }
	     this.keys.length=key;
	 };
	 
	 this.isEmpty = function(){
	  return this.keys.length == 0;
	 };
	 
	 this.size = function(){
	  return this.keys.length;
	 };
	}
  	var ckeditObj = new Map();
</script>

<tr <#if attrList?exists && ( attrList.size() ==0 )>style="display:none;"</#if>>
	 <td class="<#if session_cust_type?if_exists=="admin">table_name<#else>left_td</#if>"> 所有分类属性:</td>
	 <td colspan="5">
		 <table border="0" style="width:100%;" bgcolor="#cecece" cellspacing="1" cellpadding="1">
		 		<#list attrList as attr>
				 <tr bgcolor="white">
                     <td style="text-align:right;width:120px;">
                     	 ${attr.attr_name?if_exists}
                         <font>:</font>
	                     <!--构造存放的属性值用来保存的id开始-->
                  		 <#if attr.attr_type==2 || attr.attr_type==3>
	                    	  <@s.hidden name="cate_value_trade_id" value=""/>  
						 <#else>
                			  <#list attrValueList as value>
                  				  <#if attr.attr_id?if_exists==value.attr_id?if_exists>
									   <@s.hidden name="cate_value_trade_id" value="${value.trade_id?if_exists}"/>  
								  </#if>
	                    	  </#list>
                		 </#if>
                    	 <!--构造存放的属性值用来保存的id结束-->
                     </td>      
                                    
                     <td style="padding:5px 0px 5px 3px;">
                 		<!--回选值的处理开始-->
                 			<#assign disval=""/>
                 			<#if attr.dft_value!="">
                            		<#assign disval='${attr.dft_value?if_exists}'>
                            <#else>
                                  <#list attrValueList as value>
                                  		<#if attr.attr_type!=2 && attr.attr_type!=3>
	                                  		<#if attr.attr_id?if_exists==value.attr_id?if_exists>
	                            				<#assign disval='${value.attr_value?if_exists}'>
	                            			</#if>
	                            		</#if>
                            	  </#list>
                            </#if>        
                     	<!--回选值的处理结束-->
                     		<input type="hidden" class="atype" value="${attr.attr_type}"/>
                     		<#if attr.attr_type=='0'><!--单行文本域-->
	                             ${disval?if_exists}"
                         	<#elseif attr.attr_type=='1'><!--多行文本域-->
	                             ${disval?if_exists}
	                        <#elseif (attr.attr_type=='2') ||  (attr.attr_type=='3')><!--单选框-->
	                        		<#list attrValueList as value>
		                        		    <#if ( (disval?if_exists?index_of(value.trade_id?if_exists)) > -1 ) >
				                     	  		<#assign isCheck = 'checked'>
				                     	    <#else>
				                     	 		<#assign isCheck = ''>
				                     	  	</#if>    
	                                  		<#if attr.attr_id?if_exists==value.attr_id?if_exists>
		                                  		<#if (attr.attr_type)=='2' >		                            	
						                     	  	<input type="radio" class="radioval" name="radioval${attr.attr_id?if_exists}" value="${value.trade_id?if_exists}" ${isCheck?if_exists}> 
					                     	    <#elseif (attr.attr_type)=='3'>
					                     	 	    <input type="checkbox" class="checkboxval radioval" name="checkboxval${attr.attr_id?if_exists}" disabled='disabled' ${isCheck?if_exists} value="${value.trade_id?if_exists}"/>			                    
					                     	    </#if>
		                                  		${value.attr_value?if_exists}
	                            			</#if>
	                            	</#list>
							 <#elseif attr.attr_type=='4'><!--图片上传-->		
							 	<#list disval?split(",") as ds>
							 		<img src="${s?if_exists}" width="80px" height="80px" style="margin-rgiht:8px;"/>
						        </#list>
					         <#elseif attr.attr_type=='5'><!--附件上传--> 
		              			 ${disval?if_exists}
				             <#elseif attr.attr_type=='6'><!--WEB编辑器-->
									${disval?if_exists}
                             <#elseif attr.attr_type=='7'><!--日期-->
                             		${disval?if_exists}
	                         </#if>   
                     </td>
                  </tr>
				 </#list>
				
				
		 </table>        
	 </td>
   </tr>
<@s.hidden name="ischange" value="1" />
<@s.hidden name="info_infoattr_id" />
<@s.textarea id="cate_attr_value" name="cate_attr_value" cssStyle="display:none;"/>

<script  type="text/javascript">
$(document).ready(function(){
	//checkbox框
	$(".checkboxval").click(function(){
		var cbstr="";
		$(this).parent("td").children(".checkboxval").each(function(){
			if(this.checked){
				cbstr+=$(this).val()+"/";
			}
		});
		$(this).parent("td").children(".disval").val(cbstr);
	});
	//radio框
	$(".radioval").click(function(){
		var rdstr="";
		$(this).parent("td").children(".radioval").each(function(){
			if(this.checked){
				rdstr+=$(this).val();
			}
		});
		$(this).parent("td").children(".disval").val(rdstr);
	});
});

function valuesubmit(){
		var valStr="";
		$(".atype").each(function(i){           				    
		    if($(this).val()==6){
		    	  var e_id = $(this).parent("td").children("span").children(".ed_v").val();
		          var edobj = ckeditObj.get(e_id);
			      valStr+= edobj.document.getBody().getText()+"  ##########";  
		    }else{
		   		 valStr+=$(".disval").eq(i).val()+"  ##########";           
		    }
		});	 
		if(valStr.indexOf("##########")>-1){
			valStr=valStr.substring(0,(valStr.length-10))
			$("#cate_attr_value").val(valStr);           					
		}  
	}
					
	function dosubmit(){                    
	    valuesubmit();        
	    //alert($("#cate_attr_value").val()); 				
		document.forms[0].submit();
	}								
	
</script>       

