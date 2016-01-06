<html>
  <head>
    <title>添加商品</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript" src="/include/js/getMemberCat.js"></script> 
    <link rel="StyleSheet" href="/include/js/category.css" type="text/css" />
    <script type="text/javascript" src="/include/js/goods.js"></script> 
    <style type="text/css">
    	.del{margin-left:20px;color:#990000;}
    	.areali{padding:4px;padding-left:5px;float:left;border:1px solid #e1e2e3;margin-right:10px;margin-top:3px;}	
		.areali img{margin:-3px 2px 0px 5px;}
		.span_areaname{margin-right:5px;}
		.areaul{width:880px;}
    </style>
    <#include "/include/uploadInc.html">    
 	<script type="text/javascript"> 
	    $(document).ready(function(){ 
			 //所属分类的回选
	         <#if sysmodule.is_catattr=="1">
				cate_select(${cfg_topcatid?if_exists},1,"goods");  
			 </#if>		 
			 //回选代码
			 if($("#relate_id").val()!=''){
			 	//回选相关商品
			 	setrelate($("#relate_id").val(),$("#relate_name").val(),"relate_div");
			 }
			 if($("#relate_give").val()!=''){
			 	//回选相关商品
			 	setgive($("#give_id").val(),$("#give_name").val(),"give_div");
			 }
			 membercat_select('1111111111'); 
			 
		   $("#setship").click(function(){
		         var rType = $("input:radio[name='goods.is_ship']:checked").val();
		   		 loadByShipType("");
		   		 if(rType=='1'){
		   		    $("#showsetarea").popup({});
		   		 }
		   		 if(rType=='2'){
		   		    $("#showshiparea").popup({});
		   		 }
		      });
	     
	      $("input:radio[name='goods.is_ship']").click(function(){
		     	if($(this).val()==0){
		     		$("#tr_ship_type").hide();	
		     	}else{
		     		$("#tr_ship_type").show();	
		     	}
		     	$("#s_price").val('');	 
		     	$("#td_ship").html("<a style='cursor:pointer;' onclick='rsetship();'>[设置运费]</a>");   		     
	      });
	      
	      var list= $('input:radio[name="goods.is_ship"]:checked').val();
	      if(list==undefined){
	         $("input[name=goods.is_ship]:eq(0)").attr("checked",'checked'); 
	      }
	      
	     //判断商品分类是否为空
	     if($("#membercatlist").html()!=''){
	      $("#myself").show();
	     }
	  }); 
	  
	 function rsetship(){
	      var rType = $("input:radio[name='goods.is_ship']:checked").val();
		   		 loadByShipType("");
		   		 if(rType=='1'){
		   		    $("#showsetarea").popup({});
		   		 }
		   		 if(rType=='2'){
		   		    $("#showshiparea").popup({});
		   		 }
	 } 
	</script>
  </head>
 <body>
 	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">新增商品</a><span>></span><a href="#">选择分类</a><span>></span><a href="#">添加商品</a>
    </div>
<@s.form action="/bmall_Goods_insert.action" method="post" validate="true" >
<div class="rightside f_right">
     <div class="rpostion"><h2>添加商品</h2></div> 
     <div class="base_infor">
	    <h2 class="base_title">商品信息</h2>
	    <div class="table_infor f_left" >
			<table>
	           <tr>
             <td class="firsttd" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="goods.cat_attr" />
	                 <a href="/member_Goods_cate.action?cat_attr=0">[重新选择]</a>
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>        	             
             </td>
           </tr>
          <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>     
		 <tr>
	         <td class="firsttd" >商品编号:</td>
	         <td colspan="5">
	         	<@s.textfield name="goods.goods_no" cssClass="winput" maxLength="20" cssStyle="width:200px"/>
	         	<@s.fielderror><@s.param>goods.goods_no</@s.param></@s.fielderror>
	         </td>
	       </tr>         
	       <tr>
	         <td class="firsttd">商品名称<font color='red'>*</font></td>
	         <td colspan="5">
	         	<@s.textfield name="goods.goods_name" cssClass="winput" maxLength="100" cssStyle="width:480px"/>
	
	         	<@s.fielderror><@s.param>goods.goods_name</@s.param></@s.fielderror>
	         </td>
	       </tr>
	        		 
		  <tr>
		               <td class="firsttd">是否为虚拟商品:</td>
		               <td  colspan="5">
		             	   <@s.radio name="goods.is_virtual" list=r"#{'0':'是','1':'否'}" value="${virtual_type?if_exists}" />    
		             	   <@s.fielderror><@s.param>goods.is_virtual</@s.param></@s.fielderror>
		               </td>
		   </tr>
		 
	       	<tr>
		           	 <td class="firsttd">成本价:</td>		           	 
		             <td colspan="5">
		             	<@s.textfield id="cp" name="goods.cost_price" cssClass="winput" maxLength="20" onkeyup="calculate(this.value);" value="0.0" />
		             	(当输入成本价后，会自动生成相应的销售价和市场价，也可以对生成的价格进行修改)
		             	<@s.fielderror><@s.param>goods.cost_price</@s.param></@s.fielderror>
		             </td>

					<script type="text/javascript">
						   //计算
		           	  	   function calculate(val){
		           	  	       var re = /([0-9]+\.[0-9]{2})[0-9]*/;		           	  	   
						        var m_discount=$("#market_price").val();
						        var s_discount=$("#sale_price").val();
						        var mem_discount=$("#mem_discount").val();
						        if(checkFloat(val)){
						        	$("#gsale").val((parseFloat(val*0.01*s_discount)).toFixed(2));
						        	$("#gmarket").val((parseFloat(val*0.01*m_discount)).toFixed(2));	
						        	var m_dis=mem_discount.split(",");
						        	for(var i=0;i<m_dis.length;i++){
						        		if(m_dis[i]!=null){
						        		    if(m_dis[i].indexOf(":")>-1){
						        		    	var len=m_dis[i].indexOf(":");
						        		    	var mName=m_dis[i].substring(0,len);
						        		    	var mdiscount=m_dis[i].substring(len+1,m_dis[i].length);
							        			$("#mempricediv").find("input:hidden[name='memName']").each(function(){
				        							 if($(this).val()==mName){
				        							 	var mlprice=(parseFloat(val*0.01*mdiscount)).toFixed(2);
				        								$(this).parent("span").find("input:text[name='memPrice']").val(mlprice);
				        							 }
							        			});
						        			}
						        		}
						        	}        	
						        }
		           	  		}
		           	  		
		           	  		//验证RMB类型
		           	  		function checkFloat(f_val){
		           	  			//验证是否为数字正则表达式
		           	  			var reg = /^(\d){1,8}(\.)?(\d(\d)?)?$/;
		           	  			if(!reg.test(f_val)){
		           	  				jNotify("请正确输入货币类型!");
		           	  				return false;
		           	  			}else{
		           	  				return true;
		           	  			}
		           	  		}
		           	 </script>




		           </tr>
	       <tr>
						<td class="firsttd">销售价<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="gsale" name="goods.sale_price" cssClass="winput" maxLength="20"  />
		             	<@s.fielderror><@s.param>goods.sale_price</@s.param></@s.fielderror>
		             </td>
		             <td class="firsttd">市场价格:</td>
		             <td colspan="3">
		             	<@s.textfield id="gmarket" name="goods.market_price" cssClass="winput" maxLength="20" value="0.0" />
		             	<@s.fielderror><@s.param>goods.market_price</@s.param></@s.fielderror>
		             </td>
					</tr>
					
			<#if malllevelList!=null&&(malllevelList?size>(0))>
		           <tr>
		             <td class="firsttd">会员价:</td>
		             
		             <td colspan="5">
		             <div id="mempricediv">
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
									
           					  ${mlevel.level_name?if_exists}
		                   	  <span style="margin-top:6px;">	
		                   		  <input type="hidden" name="memName" value="${mlevel.level_name?if_exists}"/>	                   	 	 	                   	     
		                   	  	 <input type="text" name="memPrice" style="width:122px;" value="${dislevel?if_exists}" value="0.0" />
		                   	  </span>
		                   	  <br/>	
		                   	  
		               </#list>
		             	<@s.fielderror><@s.param>goods.mem_price</@s.param></@s.fielderror>
		            </div>
		             </td>
		           </tr>
	        </#if>   
	     
	       <tr id='myself' style="display:none;">
	         <td class="firsttd">自定义分类:</td>
	         <td  colspan="5">
	         	   <div id="membercatlist"></div>
	         	    <@s.fielderror><@s.param>goods.self_cat</@s.param></@s.fielderror>
	         </td>
	       </tr>
	       
	       	 <tr>
	         <td class="firsttd">商品标签:</td>
	         <td  colspan="5">
	         	 <@s.select name="goods.label" list="commparalist" listKey='para_value'  listValue='para_key' headerKey="0" headerValue="请选择" />    
	         	
	         	<@s.fielderror><@s.param>goods.label</@s.param></@s.fielderror>
	         </td>
	       </tr>
	   
	     
	   
	       <tr>
	         <td class="firsttd">商品品牌:</td>
	         <td colspan="5">
	         	 <@s.select name="goods.brand_id" list="brandList" listKey='brand_id'  listValue='brand_name'  headerKey="0" headerValue="请选择" />    	    
	         	<@s.fielderror><@s.param>goods.brand_id</@s.param></@s.fielderror>
	         </td>
	       </tr>
	   
	   	   <tr>
				 <td class="firsttd" >上架时间:</td>
		         <td  colspan="5">	             	
		             	<@s.textfield id="txtstartDate" name="goods.up_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		               <@s.fielderror><@s.param>goods.up_date</@s.param></@s.fielderror>-
		               <@s.textfield id="txtendDate" name="goods.down_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		               <@s.fielderror><@s.param>goods.down_date</@s.param></@s.fielderror>
		         </td>		
	       </tr> 
				
					
	           
		           <tr>
		             <td class="firsttd">总库存:</td>
		             <td colspan="5">
		             	<@s.textfield name="goods.total_stock" cssClass="winput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>goods.total_stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">现有库存:</td>
		             <td>
		             	<@s.textfield name="goods.now_stock" cssClass="winput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>goods.now_stock</@s.param></@s.fielderror>
		             </td>
		             
		             
		             <td class="firsttd">提醒库存:</td>
		             <td colspan="3">
		             	<@s.textfield name="goods.warn_stock" cssClass="winput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>goods.warn_stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="firsttd">销量:</td>
		             <td colspan="5">
		             	<@s.textfield name="goods.saled_num" cssClass="winput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>goods.saled_num</@s.param></@s.fielderror>
		             </td>
		           </tr> 		
		             <tr>
	         <td class="firsttd">商品关键字:</td>
	          <td colspan="5">
	         	<@s.textfield name="goods.goods_wd"  cssStyle="width:280px;"  onkeyup="set_textarea_length(this,100);"/>(如有多个关键字请以|线隔开)
	         	<@s.fielderror><@s.param>goods.goods_wd</@s.param></@s.fielderror>
	         </td>
	       </tr>
	        <tr>
	         <td class="firsttd">商品简介:</td>
	         <td colspan="5">
	         	<@s.textarea name="goods.goods_desc" cssClass="winput" maxLength="20" cssStyle="width:300px;" onkeyup="set_textarea_length(this,600);"/>
	         	<@s.fielderror><@s.param>goods.goods_desc</@s.param></@s.fielderror>
	         </td>
	       </tr>
	     </table>
	    </div>
     </div>

    <div class="clear"></div>
     <div class="base_infor">
       <h2 class="base_title">详细信息</h2>
       <div class="table_infor">
          <table >                 
                 <tr>
	             <td class="firsttd" >商品图片:</td>
	             <td>             
	             <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			  <@s.textfield name="goods.img_path" id="uploadresult" cssStyle="width:300px;"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
		              			<script>uploadGoodsImgMulti();</script>
	             			</td>
	             		</tr>
	             	</table>
				<@s.fielderror><@s.param>goods.goods_img</@s.param></@s.fielderror>
		       </td>
		     </tr>  
	           <tr>
		             <td class="firsttd">商品视频:</td>
		             <td>
		             	<@s.textfield name="goods.goods_video" cssClass="txtinput"  cssStyle="width:300px;" />
		             	<@s.fielderror><@s.param>goods.goods_video</@s.param></@s.fielderror>
		             </td>
		           </tr>          
		           
           <tr>
             <td class="firsttd">商品描述:</td>
             <td >
           		<div style="width:600px;">
	             	<@s.textarea  name="goods.goods_detail" id="content" />
	             	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
					<script type="text/javascript">
				     	CKEDITOR.replace('content');  
					</script>  
			 	</div>
             </td>
  		  </tr>
  		  
  		  
  		   <tr>  	           
             <td class="firsttd">赠送积分数:</td>
             <td>
             	<@s.textfield name="goods.give_inter" cssClass="winput" maxLength="20" value="0"/>
             	<@s.fielderror><@s.param>goods.give_inter</@s.param></@s.fielderror>
             </td>
           </tr>
	           
	           
	           <tr>
	             <td class="firsttd">积分购买金额:</td>
	             <td>
	             	<@s.textfield name="goods.interbuy" cssClass="winput" maxLength="20" value="0"/>
	             	<@s.fielderror><@s.param>goods.interbuy</@s.param></@s.fielderror>
	             </td>
	           </tr>
           
           
            <tr>
	             <td class="firsttd">重量:</td>
	             <td>
	             	<@s.textfield name="goods.weight"  cssClass="winput" maxLength="20" value="0"/>(kg)
	             	 <@s.hidden name="goods.unit" value="1"/>     
	             	<@s.fielderror><@s.param>goods.weight</@s.param></@s.fielderror>
	             	<@s.fielderror><@s.param>goods.unit</@s.param></@s.fielderror>
	             </td>
            </tr>

			<tr>
	             <td class="firsttd">体积:</td>
	             <td>
	             	<@s.textfield name="goods.volume" cssClass="winput"  maxLength="20" onkeyup="checkNum(this);" value="0"/>(m<sup>3</sup>)
	             	<@s.fielderror><@s.param>goods.volume</@s.param></@s.fielderror>
	             </td>
	        </tr>
		           
		           <tr>
		             <td class="firsttd">免运费:</td>
		             <td>
		               <@s.radio class='radioItem' name="goods.is_ship" list=r"#{'0':'是','1':'否'}" value="1" />    
		             	<@s.fielderror><@s.param>goods.is_ship</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	            <tr>
		             <td class="firsttd">运费计算类型:</td>
		             <td>
					    <@s.radio class='radioItem' name="goods.is_ship" list=r"#{'0':'免运费','1':'半自动','2':'全自动'}" />  
		             	<@s.fielderror><@s.param>goods.is_ship</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		          <tr id="tr_ship_type"  <#if shopconfig==null || shopconfig.ship_type?if_exists=='0'>style="display:none;"</#if> >
		             <td class="firsttd">运费设置模板:</td>
		             <td id="td_ship">
		                <#if goods.ship_price?if_exists!=''>
		             		<a onclick="areadset();" style="cursor:pointer;">已设置</a>
		             	<#else>
		                	<a style="cursor:pointer;" id="setship">[设置运费]</a>
		                </#if>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="firsttd">是否可使用优惠卷付款:</td>
		             <td>
		             	 <@s.radio name="goods.is_volume" list=r"#{'0':'是','1':'否'}" value="0" />    
		             	<@s.fielderror><@s.param>goods.is_volume</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           

		          <tr>
		             <td class="firsttd">商家备注:</td>
		             <td>
		             	<@s.textarea name="goods.busi_remark" cssClass="txtinput" onkeyup="set_textarea_length(this,100);" cssStyle="width:300px;height:80px;"/>
		             	<@s.fielderror><@s.param>goods.busi_remark</@s.param></@s.fielderror>
		             </td>
		           </tr>
          </table>
                    <script type="text/javascript">
					function select_relate(){
						var goodsName=document.getElementById('relate').value;
						window.open("/bmall_Goods_ralatelist.action?name_s="+goodsName+"&ajaxRequestRandom="+Math.random());
					}	
					function select_give(){
						var goodsName=document.getElementById('give').value;
						window.open("/bmall_Goods_givelist.action?name_s="+goodsName+"&ajaxRequestRandom="+Math.random());
					}	
				</script>
	      </div>
	 </div>
	 <div class="clear"></div>
    <div class="base_infor">
       <h2 class="base_title">其它信息</h2>
       <div class="table_infor" >
          <table>
               <tr>
		             <td class="firsttd" >相关商品:</td>
		             <td>
		             	<@s.textfield id="relate" cssClass="winput" maxLength="20"/>
		             	<input type="button" value="搜索商品" class="submitbut" onclick="select_relate();"/><br/>
		             	<@s.hidden  id="relate_id" name="relate_id"/>   
		             	<@s.hidden  id="relate_name"  name="relate_name"/>             	
		             		<div id="relate_div">   		             	             
		             		
		             		</div>  
		             </td>
		       </tr>
		       <tr>
		             <td class="firsttd">相关配件:</td>
		             <td>
		             	<@s.textfield id="give" name="goods.give" cssClass="winput" maxLength="20"/>
		             	<input type="button" value="搜索配件" class="submitbut" onclick="select_give();"/><br/>
		             	<@s.hidden id="give_id" name="give_id"/>   
		             	<@s.hidden  id="give_name"  name="give_name"/>             	
		             		<div id="give_div">   		             	             
		             		
		             		</div>  
		             	<@s.fielderror><@s.param>goods.give</@s.param></@s.fielderror>
		             </td>
		       </tr>
          </table>
	      </div>
	 </div>
	 <div class="clear"></div>
       <div class="base_infor">
       <h2 class="base_title">搜索引擎优化</h2>
       <div class="table_infor" >
          <table>          
              <tr>
		             <td class="firsttd"  >SEO标题:</td>
		             <td>
		             	<@s.textarea name="goods.seo_titel" cssClass="txtinput" onkeyup="set_textarea_length(this,100);" cssStyle="width:260px;"/>
		             	<@s.fielderror><@s.param>goods.seo_titel</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">SEO关键字:</td>
		             <td>
		             	<@s.textarea name="goods.seo_keyword" cssClass="txtinput" onkeyup="set_textarea_length(this,100);"  cssStyle="width:260px;height:80px;"/>
		             	<@s.fielderror><@s.param>goods.seo_keyword</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">SEO描述:</td>
		             <td>
		             	<@s.textarea name="goods.seo_desc" cssClass="txtinput" onkeyup="set_textarea_length(this,200);" cssStyle="width:320px;height:80px;"/>
		             	<@s.fielderror><@s.param>goods.seo_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td colspan="2" align="center">
		             	<#if sysmodule.is_catattr=="0">      
			          		<@s.hidden id="cat_attr" name="cat_attr"/>
			           		<input type="button"  class="submitbut" value="提  交"   onclick="dosubmit();">
			            <#else>
			           		 <@s.submit value="提  交"  cssClass="submitbut" />
			            </#if>
		             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Goods_list.action','');"/>
		             </td>
		           </tr> 
          </table>
       </div>
     </div>     
   </div>
        </div>     
   </div>
   <div style="text-align:center;">
   	      <@s.hidden name="goods.is_del" value="1"/>    
   	      <@s.hidden name="goods.ship_price" id="s_price"/>
	      <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	      ${listSearchHiddenField?if_exists}
		  <@s.hidden name="goods.info_state" value="0" />    
  	  	  <!--所属分类插件隐藏字段开始区域-->
	  	  <@s.hidden id="hidden_cat_value" name="hidden_cat_value"/>
	      <!--所属分类插件隐藏字段结束区域-->
	  	  <@s.hidden id="market_price" name="m_discount"/>
	  	  <@s.hidden id="sale_price" name="s_discount"/>
	  	  <@s.hidden id="mem_discount" name="mem_discount"/>	 
	  	  <@s.hidden id="membercat" name="membercat"/>	 
	  	  <@s.hidden id="hidden_membercat_value" name="hidden_membercat_value" />
	</div> 	  
</div>
</@s.form>

<script type="text/javascript">
	  function del(obj,value){
		  	$(obj).parent("div").remove();
		  	var r_value = $("#relate_id").val();
		  	if(r_value.indexOf(value)>-1){
		  		 r_value=r_value.replace(value+",","").replace(value,"");
		  	}		  	
		  	$("#relate_id").val(r_value);
	  }
	  function del_give(obj,value){
		  	$(obj).parent("div").remove();
		  	var r_value = $("#give_id").val();
		  	if(r_value.indexOf(value)>-1){
		  		 r_value=r_value.replace(value+",","").replace(value,"");
		  	}		  	
		  	$("#give_id").val(r_value);
	  }
	  function setrelate(id,name,id_div) {
		 var ids=id.split(",");
		 var names=name.split("####");
		 var r_ids="";
		 var r_names="";
		 for(var i=0;i<ids.length;i++){
		     //不存在该控件，且数组元素不为空时
		     if(ids[i]!=""&&$("."+id_div+ids[i]).length<=0){
		     	$("#"+id_div).append("<div class="+id_div+ids[i]+">"+names[i]+"<input name=\"goods.relate_goods\" type=\"hidden\" value="+ids[i]+"><a class=\"del\" onclick=\"del(this,"+ids[i]+");\">[删除]</a></div>");	
		    	r_ids+=ids[i]+",";
		    	r_names+=names[i]+"####";	     		 
		     }
		 }
		 //相关商品赋值		    	
		 $("#relate_id").val(r_ids);
		 $("#relate_name").val(r_names);
	  }	  
	 function setgive(id,name,id_div) {
		 var ids=id.split(",");
		 var names=name.split("####");
		 var r_ids="";
		 var r_names="";
		 for(var i=0;i<ids.length;i++){
		     //不存在该控件，且数组元素不为空时
		     if(ids[i]!=""&&$("."+id_div+ids[i]).length<=0){
		     	$("#"+id_div).append("<div class="+id_div+ids[i]+">"+names[i]+"<input name=\"goods.relate_goods\" type=\"hidden\" value="+ids[i]+"><a class=\"del\" onclick=\"del_give(this,"+ids[i]+");\">[删除]</a></div>");	
		    	r_ids+=ids[i]+",";
		    	r_names+=names[i]+"####";	     		 
		     }
		 }
		 //相关商品赋值		    	
		 $("#give_id").val(r_ids);
		 $("#give_name").val(r_names);
	  }	  
	</script>	
		<script type="text/javascript">
		 // 创建弹出层一个闭包  
		(function($) {  
			  // 插件的定义  
			  $.fn.popup= function(options) {   
			  	  //获取设置插件的选项	 
			      var opts = $.extend({}, $.fn.popup.defaults, options);
			      //初始化方法
			      this.each(function(){    
			          //获取窗体的宽度与高度
      	   			  var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
      	   			  _documentHeight =  $(document).height(),//获取当前文档的高度
					  _windowHeight = $(window).height(),//获取当前窗口高度
					  _windowWidth = $(window).width(),//获取当前窗口宽度
					  _popupHeight = $(this).height(),//获取弹出层高度
					  _popupWeight = $(this).width();//获取弹出层宽度
					  var _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight;
					  var _posiLeft = (_windowWidth - _popupWeight)/2;
			          //新建一个DIV遮盖层
	      	   		  $("<div id='cover' class='cover'></div>").appendTo("body");	
	      	   		  $("#cover").height(_documentHeight);
	      	   		  $("#cover").width("100%");
	      	   		  $("#cover").css({"opacity": "0.3", background: "black","position":"absolute","left":"0px","top":"0px","z-index":888});
	      	   		  $("#cover").fadeIn("slow");
					  $(this).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":9999});//设置position
				  }); 
			  };  
			  // 插件的defaults默认配置  
			  $.fn.popup.defaults = {  
  	 
			  };	
		// 闭包结束  
		})(jQuery); 
	</script>
	
<div id="showsetarea" style="border:1px solid #E1E2E3;width:auto;height:auto;background:#FFFFFF;display:none;">
	<div><input type="button" value="关闭" onclick="colsecover();" style="float:right;"/></div>
	<div class="clear"></div>
	
		<div id="areadata" style="padding:3px 15px 6px 0px;">
		</div>
	
	<div style="text-align:center;">
		<input type="button" value="保存" onclick="savesPrice();"/>
	</div>
	<br/>
</div>	

<div id="showshiparea" style="border:1px solid #E1E2E3;width:auto;height:auto;background:#FFFFFF;display:none;">
	<div><input type="button" value="关闭" onclick="colsecover();" style="float:right;"/></div>
	<div class="clear"></div>
	    <font color='red'><div id="error" style="padding:3px 15px 6px 0px;"></div></font>
		<div id="shipdata" style="padding:3px 15px 6px 0px;">
		</div>
	
	<div style="text-align:center;">
		<input type="button" value="保存" onclick="saveship();"/>
	</div>
	<br/>
</div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->	
</body>
</html>