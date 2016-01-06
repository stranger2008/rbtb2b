<html>
  <head>
    <title>审核商品</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <link rel="StyleSheet" href="/include/js/category.css" type="text/css" />
        <style type="text/css">
    	.del{margin-left:20px;color:#990000;}
    </style>
    	<script type="text/javascript">
		  $(document).ready(function(){    
	         disabledCss();
	  	  });
	</script> 	
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 >审核商品
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goods_auditState.action" method="post" validate="true"  onsubmit="return noreasron('goods.info_state','noreasonvalue','2');">
   	        <table class="wwtable" cellspacing="1" cellpadding="8">
	             <tr><td colspan="6" class="fmhead">商品信息</td></tr>
	         <tr>
		             <td width="280px;" class="table_name" >所属分类<font color='red'>*</font></td>
		             <td colspan="5">
		             	 ${cate_name?if_exists}
                 <@s.hidden name="cate_name" />
                  <@s.hidden name="cat_attr" />
                 <@s.hidden name="goods.cat_attr" />
  				 </td>
		    </tr>
		      <#if sysmodule.is_catattr=="0">      
	           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
	          </#if>  
	    	<tr>
		             <td class="table_name" >商品编号:</td>
		             <td colspan="5">
		             		${goods.goods_no?if_exists}
		             </td>
		           </tr>         
		          
		          
		          <tr>
		             <td class="table_name">商品名称<font color='red'>*</font></td>
		             <td colspan="5">
		             	  ${goods.goods_name?if_exists}
		             </td>
		          </tr>
		          
			          
			          		          		 
				 <tr>
		               <td class="table_name">是否为虚拟商品:</td>
		               <td  colspan="5">
		             	   <@s.radio name="goods.is_virtual" list=r"#{'0':'是','1':'否'}" value="${virtual_type?if_exists}" />    
		             	   <@s.fielderror><@s.param>goods.is_virtual</@s.param></@s.fielderror>
		               </td>
				 </tr>
				 
		 
		          
		           
		           
		          <tr>
		           	 <td class="table_name">成本价:</td>
		           	 <script type="text/javascript">
		           	  		function Calculate(){
		           	  			var price=$("#cp").val();
						        document.forms[0].action="/admin_Goods_calculate.action";
							    document.forms[0].submit();
		           	  		}
		           	 </script>
		             <td colspan="5">
		             	${goods.cost_price?if_exists}
		             </td>

		           </tr>
					<tr>
						<td class="table_name">销售价<font color='red'>*</font></td>
		             <td>
		             	${goods.sale_price?if_exists}		             	
		             </td>
		             <td class="table_name">市场价格:</td>
		             <td colspan="3">
				     	${goods.market_price?if_exists}		
		             </td>
					</tr>
		           <tr>
		             <td class="table_name">会员价:</td>
		             <td colspan="5">
		             
		               <#list malllevelList as mlevel>
	               	
		              		  <!-- 得到priceValue的长度 -->
	                     	 <#assign levellen = "${priceValue?if_exists}"?length>
		             		 <!-- 得到属性ID的位置 -->
	                     	 <#assign levelstartpos = "${priceValue?if_exists}"?index_of('${mlevel.level_name?if_exists}')>
		               		 <!-- 去掉属性ID左边的字符 -->	                     	 
	                     	 <#if (levelstartpos > -1) >	                     	 
	                            <#assign getlevelRightStr = priceValue[levelstartpos..(levellen-1)]>	                     	 
	                     	 </#if>	
		               
		               		 <!-- 得到(去掉属性ID左边的字符)右边第一个，号的位置 -->
	                     	 <#assign levelrightAttrLen = "${getlevelRightStr?if_exists}"?index_of(',')>
	                     	 
	                     	 
	                     	 
	                     	 <!--得到当前的属性ID和值-->
	                     	 <#if (levelrightAttrLen > -1) >	                     	 
	                     	 	<#assign thislevelIdVal = getlevelRightStr[0..(levelrightAttrLen-1)]>	                     	 	
	                     	 	<!--得到属性值串-->
	                     	 	<#assign thislevelVal = thislevelIdVal?replace('${mlevel.level_name?if_exists}|','')>	                     	 
	                     	 </#if>
	                     	 
	                     	 
	                     	 
	                     	  <#if (thislevelVal?if_exists=='') >
	                     	  	<#assign dislevel = "">
	                     	  <#else>
	                     	 	<#assign dislevel = (thislevelVal)?if_exists?trim>
	                     	  </#if>

           					 ${mlevel.level_name?if_exists}:
		                   	  <span style="margin-right:12px;">		                   	     
		                   	  	 ${dislevel?if_exists}
		                   	  </span>
		               </#list>
		             	<@s.fielderror><@s.param>goods.mem_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		            <tr>
		             <td class="table_name">商品自定义分类:</td>
		             <td  colspan="5">
		             	  ${membercat_attr_name?if_exists}
		             </td>
		           </tr>
		           
		           	 <tr>
		             <td class="table_name">商品标签:</td>
		             <td  colspan="5">
		             	 <@s.select name="goods.label" list="commparalist" listKey='para_value'  listValue='para_key' headerKey="" headerValue="请选择" />   
		             	<@s.fielderror><@s.param>goods.label</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="table_name">商品品牌:</td>
		             <td colspan="5">
		             	 <@s.select name="goods.brand_id" list="brandList" listKey='brand_id'  listValue='brand_name'  headerKey="" headerValue="请选择" />    	    
		             </td>
		           </tr>
	           
		         
	           		<tr>
					 <td class="table_name" >上架时间:</td>
		             <td colspan="5">	             	
			               ${goods.up_date?if_exists[0..9]}-
			               ${goods.down_date?if_exists[0..9]}
		             </td>		
		           </tr> 
		         
		           <tr>
		             <td class="table_name">总库存:</td>
		             <td colspan="5">
		             	${goods.total_stock?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">现有库存:</td>
		             <td>
		             	${goods.now_stock?if_exists}
		             </td>
		             <td class="table_name">提醒库存:</td>
		             <td colspan="3">
		             	${goods.warn_stock?if_exists}
		             </td>
		           </tr>         
		           <tr>
		             <td class="table_name">销量:</td>
		             <td colspan="5">
		             		${goods.saled_num?if_exists}
		             </td>
		           </tr>
		           
		           
		           <tr>
		             <td class="table_name">商品关键字:</td>
		              <td colspan="5">
		             	${goods.goods_wd?if_exists}
		             </td>
		           </tr>
		           
		             <tr>
		             <td class="table_name">商品简介:</td>
		             <td colspan="5">
		             	${goods.goods_desc?if_exists}
		             </td>
		           </tr>
		 </table>
		 
      <table class="wwtable" cellspacing="1" cellpadding="8">
          		<tr><td colspan="4"  class="fmhead">详细信息 </td></tr>
  			 <tr>
	             <td class="table_name"  width="280px;">商品图片:</td>
	             <td>             
	             <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td >
          			  			<div id="showcimg">
          			  				<#if goods.goods_img!=null&&goods.goods_img!=''>
										 <#list goods.goods_img?split(",") as s>
							              	<img src="${s}" width="60" height="60">
							             </#list>
							        </#if>
								</div>
	             			</td>	             			
	             		</tr>
	             	</table>
		       </td>
		     </tr>  
	           <tr>
		             <td class="table_name">商品视频:</td>
		             <td>
		             	${goods.goods_video?if_exists}
		             </td>
		           </tr>          
		           <tr>
		             <td class="table_name">商品描述:</td>
		             <td>
							${goods.goods_detail?if_exists}
		             </td>
          		
          		   <tr>  	           
		             <td class="table_name">赠送积分数:</td>
		             <td>
		             	${goods.give_inter?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">积分购买金额:</td>
		             <td>		    
		             	${goods.interbuy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">重量:</td>
		             <td>
		             	${goods.weight?if_exists}
		             	<@s.select name="goods.unit" list=r"#{'0':'克','1':'千克'}"  headerKey="0" headerValue="请选择" />    
		             	<@s.fielderror><@s.param>goods.weight</@s.param></@s.fielderror>
		             	<@s.fielderror><@s.param>goods.unit</@s.param></@s.fielderror>
		             </td>
		           </tr>

		           <tr>
		             <td class="table_name">免运费:</td>
		             <td>
		               <@s.radio name="goods.is_ship" list=r"#{'0':'是','1':'否'}" value="1" disabled="true"/>    
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否可使用优惠卷付款:</td>
		             <td>
		             	 <@s.radio name="goods.is_volume" list=r"#{'0':'是','1':'否'}" value="0" disabled="true"/>    

		             </td>
		           </tr>
		           <#if if_opt_audit=="1">
			          <tr>
			             <td class="table_name" >审核状态:</td>
			             <td>
			                  <@s.radio name="goods.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}"  onclick="getRadioValue('goods.info_state','reasonid','noreasonvalue','2');"/>    
			                   <@s.fielderror><@s.param>goods.info_state</@s.param></@s.fielderror>                	            
			             </td>
			           </tr>          
				     <#if goods.info_state?if_exists=='2'>
			             <tr  id="reasonid">
				             <td class="table_name">拒绝理由<font color='red'>*</font></td>
				             <td>
				             	<@s.textfield name="goods.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
				             	<@s.fielderror><@s.param>goods.no_reason</@s.param></@s.fielderror>
				             </td>
				           </tr>
				           <#else>
				            <tr  id="reasonid" style="display:none;">
				             <td class="table_name">拒绝理由<font color='red'>*</font></td>
				             <td>
				             	<@s.textfield name="goods.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
				             	<@s.fielderror><@s.param>goods.no_reason</@s.param></@s.fielderror>
				             </td>
				           </tr>
			           </#if>
	           </#if>
		          <tr>
		             <td class="table_name">商家备注:</td>
		             <td>
		             	${goods.busi_remark?if_exists}
		             </td>
		           </tr>

         </table>
                <script type="text/javascript">
					function select_relate(){
						var goodsName=document.getElementById('relate').value;
						window.open("/admin_Goods_ralatelist.action?name_s="+goodsName+"&ajaxRequestRandom="+Math.random());
					}	
					function select_give(){
						var goodsName=document.getElementById('give').value;
						window.open("/admin_Goods_givelist.action?name_s="+goodsName+"&ajaxRequestRandom="+Math.random());
					}	
				</script>
        <table class="wwtable" cellspacing="1" cellpadding="8">
        <tr><td colspan="4"   class="fmhead"> 其它信息</tr>
               <tr>
		             <td class="table_name"  width="280px;">相关商品:</td>
		             <td>    	
		             	<div id="relate_div">   		             	             
		             		 <#list relateList as relate>
			             		 <div class="relate_div${relate.goods_id}">
									${relate.goods_name}
									<input type="hidden" value="${relate.goods_id}" name="goods.relate_goods">

								</div>		             		 		             		 
		             		 </#list>
		             	</div>  
		             </td>
		           </tr>
	           
                  <tr>
		             <td class="table_name">相关配件:</td>
		             <td>
		             		<div id="give_div"> 
			             		  <#list giveList as relate>
				             		 <div class="give_div${relate.goods_id}">
										${relate.goods_name}
										<input type="hidden" value="${relate.goods_id}" name="goods.relate_goods">
									</div>		             		 		             		 
			             		  </#list>		             		
		             		</div>  
		             </td>
		           </tr>

         </table>

         
         <table class="wwtable" cellspacing="1" cellpadding="8">
      		<tr><td colspan="4"  class="fmhead">搜索引擎优化</td></tr>
  				 	 <tr>
		             <td class="table_name" width="280px;">SEO标题:</td>
		             <td>
		             	${goods.seo_titel?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">SEO关键字:</td>
		             <td>
		             		${goods.seo_keyword?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">SEO描述:</td>
		             <td>
		             	${goods.seo_desc?if_exists}
		             </td>
		           </tr>
		           
   	  </table>
   	   <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">      
	       <@s.hidden name="goods.goods_id"/>     
	       <@s.hidden name="goods.cust_id"/>
	       <@s.hidden name="goods.is_del" value="1"/>    
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if> 
	        <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value"/>
		   <!--所属分类插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goods_auditList.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>

</body>
</html>

