<html>
<head>
	<title>修改运费模板信息</title>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript" src="/include/js/admin/shiptemplete.js"></script> 
	<script type="text/javascript" src="/include/js/common.js"></script>
	<link rel="StyleSheet" href="/include/css/shiptemplate.css" type="text/css" />   
	<script type="text/javascript">
	  $(document).ready(function(){ 
	   	 //所属地区的回选
	   	 area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
	<style type="text/css">
		input[type=text]{
		  width:60px;
		}
		.cot_area{
			width:156px;
		}
	</style>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">物流模板设置</a><span>></span><a href="#">添加运费模板信息</a>
    </div>
<@s.form id="shipfrom" action="/bmall_Shiptemplate_update.action" method="post"  validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>添加运费模板信息</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr>
	            <td  class="firsttd">模板名称<font color='red'>*</font></td>
	            <td>
		        	<@s.textfield name="shiptemplate.ship_name"  maxLength="20" cssStyle="width:200px;"/>
		            <@s.fielderror><@s.param>shiptemplate.ship_name</@s.param></@s.fielderror>
	            </td>
            </tr>

            <tr>
                <td  class="firsttd">发货地址<font color='red'>*</font></td>
                <td>
             		<table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="arealist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
		              		</td>
		              	</tr>
		             </table>      
                </td>
            </tr>  
            
             <tr>
	            <td  class="firsttd">计价方式:</td>
	            <td>		             
             		<#if shiptemplate!=null && shiptemplate.valuation_mode?exists!=''>
             			<@s.radio name="shiptemplate.valuation_mode" list=r"#{'1':'按件数','2':'按重量','3':'按体积'}" value="${shiptemplate.valuation_mode?if_exists}"/>
					<#else>
						<@s.radio name="shiptemplate.valuation_mode" list=r"#{'1':'按件数','2':'按重量','3':'按体积'}" value="1"/>
					</#if>
             		<@s.fielderror><@s.param>shiptemplate.valuation_mode</@s.param></@s.fielderror>
             	</td>
            </tr>
            
            <tr>
		             <td class="firsttd">运送方式:</td>
		             <td>
						 <div class="elsearea">除指定地区外，其余地区的运费采用“默认运费”</div>
						 <@s.fielderror><@s.param>areasetval</@s.param></@s.fielderror>
						 <div>
						    <#list sendmodeList as sm>						    
						    <div>						    
							    	<div style="line-height:26px;">
							    			<#if (((shiptemplate.smode_attr)?if_exists?index_of("${sm.smode_id?if_exists}")) > -1 ) >
				                     	  		<#assign isCheck = 'checked'/>
				                     	    <#else>
				                     	 		<#assign isCheck = ''/>
				                     	  	</#if>    
							    		<input id="cb${sm.smode_id?if_exists}" name="smode_id" type="checkbox" value="${sm.smode_id?if_exists}" ${isCheck?if_exists}/>
							    		<input type="hidden" class='cs_num' name="smode_num" value='1'/>
							    		<input type="hidden" name="cs_smode_id" value='${sm.smode_id?if_exists}'/>
							    		<input type="hidden" name="smodeName" value='${sm.smode_name?if_exists}'/>
							    		<span>${sm.smode_name?if_exists}</span>					    	
							    	</div>
							    	<#if shiptemplate.valuation_mode=='1'>
					    				<#assign mName = '件'/>
					    				<#assign mUint = '件'/>
					    			<#elseif shiptemplate.valuation_mode=='2'>
					    				<#assign mName = '重'/>
					    				<#assign mUint = 'kg'/>
					    			<#elseif shiptemplate.valuation_mode=='3'>
					    				<#assign mName = '体积'/>
					    				<#assign mUint = 'm³'/>
					    			</#if>			
								    	<div class='modes_div' <#if isCheck=='checked'>style="display:block;"</#if> >
								    		<#if shipList!=null&&shipList?size gt 0 >
								    			<#assign s_start=0/>
					   			    			<#list shipList as ship>							    	  	 	
										    	  <#if (sm.smode_id?if_exists==ship.c_mid?if_exists) && ship.d_ship=='0'>
									    	   		   <div class="defalut_mode">
									    	   		   	    <input type='hidden' name='end_area_str' class='ckb_end_area'  value="${ship.c_area?if_exists}"/>
											    			<input type='hidden' name="default_ship"   <#if ship.d_ship?if_exists!=''>value="${ship.d_ship?if_exists}"<#else>value="0" </#if>/>
											    			<span>默认运费：<input  name='first_weight' type="text" style='width:60px;' value="${ship.f_weight?if_exists}"/> <font class='fd_unit'>${mUint?if_exists}</font>内，</span>
											    			<span><input  name='first_price' type="text"  style='width:60px;'  value="${ship.f_price?if_exists}"/> 元，</span>
											    			<span>每增加<input name='cont_weight' type="text"  style='width:60px;'  value="${ship.c_weight?if_exists}"/> <font class='fd_price'>${mUint?if_exists}</font>，</span>
											    			<span>增加运费<input  name='cont_price' type="text"  style='width:60px;'  value="${ship.c_price?if_exists}"/> 元 </span>
										    		   </div>
										    		   <#if ship.c_num==1>
										    		   		<div id="setareadiv_${sm.smode_id?if_exists}"></div>
										    		   		<br/>						    		
													    	<span style="margin-left:20px;"><a onclick="setmodearea(this,${sm.smode_id?if_exists});">为指定地区城市设置运费</a></span>
										    		   </#if>
											      </#if>
								    			  	    							    	  	 	
									    	   	  <#if (sm.smode_id?if_exists==ship.c_mid?if_exists) && ship.d_ship=='1'>			
    	   				
									    	   			<#if s_start==0 || s_start!=ship.c_num?if_exists>
									    	   			
									    	   				<#assign s_start = (ship.c_num)?if_exists/>		

									    	   				 <div id="setareadiv_${sm.smode_id?if_exists}">								    	   											    	   												    	   				
									    	   					<!--找出过滤表的首索引与个数-->									    	   				
										    	   				<table id='other_${sm.smode_id?if_exists}' width='100%' class='wwtable' cellspacing='1' cellpadding='8'>
												    			   <tr style='background-color:#f8f8f8'>
													    			   <td align='center' width='39%'>运送到</td>
													    			   <td align='center' width='13%'>首${mName?if_exists}(${mUint?if_exists})</td>
													    			   <td align='center' width='13%'>首费(元)</td>
													    			   <td align='center' width='13%'>续${mName?if_exists}(${mUint?if_exists})</td>
													    			   <td align='center' width='13%'>续费(元)</td>
													    			   <td align='center' width='13%'>操作</td>
												    			   </tr>
										    			</#if>
										    				
										    											    			 
															<tr>
																<td><input type='hidden' name='end_area_str' class='ckb_end_area' value="${ship.c_area?if_exists}"/>
																<input type='hidden' name='default_ship' value='1'/>
																<div class='cot_area'>
																<#if ship.c_area_attr!=''>
																	${ship.c_area_attr?if_exists}
																<#else>
																	未设置地区
																</#if>																
																</div>
																<span class='cot_edit'><a onclick='editarea(this);'>编辑</a></span></td>
																<td align='center'><input name='first_weight' type='text' style='width:60px' value="${ship.f_weight?if_exists}"></td>
																<td align='center'><input name='first_price' type='text' style='width:60px' value="${ship.f_price?if_exists}"></td>
																<td align='center'><input name='cont_weight' type='text' style='width:60px' value="${ship.c_weight?if_exists}"></td>
																<td align='center'><input name='cont_price' type='text' style='width:60px' value="${ship.c_price?if_exists}"></td>
																<td align='center'><a onclick="delarea_tr(this,'${sm.smode_id?if_exists}');">删除</a></td>
															</tr>
															
										    			 	<#if (ship_index+1)==(ship.c_size?if_exists)>
										    			 	 	</table>
										    			 	 </div>	
										    			 	 	<#if ship.c_num gt 1>
										    			 	 		<br/>						    		
													    			<span style="margin-left:20px;"><a onclick="setmodearea(this,${sm.smode_id?if_exists});">为指定地区城市设置运费</a></span>
													    		</#if>
													    		</div>											    			 	 	
										    				</#if>		
										    							    			 
								    			   	  </#if>
								    			    
								    			   		
								    			  
								    			</#list>

							    				</#if>							    				
								    	 </div>
							    	</div>
						    </div>
						 	</#list>	
						 	<@s.fielderror><@s.param>shiptemplate.smode_attr</@s.param></@s.fielderror>
						 </div>						 
		             </td>
		         </tr>  
            
            <tr>
             <td colspan="2" align="center">
             	 <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
		         ${listSearchHiddenField?if_exists}
	             <input type="button" value="提  交" class="submitbut" onclick="shipsubmit();"/>
		         <!--所属地区插件隐藏字段开始区域-->
				 <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
				 <!--所属地区插件隐藏字段结束区域-->    
		         <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Shiptemplate_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 <!--获取系统的所有地区-->
		<div class='showareadiv1'>
			<div class='areatitle'>
				选择区域				
			</div>
			<table width="100%">
				<#list commparalist as comm>
					<tr  bgcolor="<#if comm_index%2==0>#FFFFFF<#else>#ECF4FF</#if>">
						<td class='td_width'>
								<input id='ah${comm.para_value?if_exists}' type="checkbox" class='cb_first' value="${comm.para_value?if_exists}"/>
								<span class='tdtitle'>${comm.para_key?if_exists}</span>
						</td>
						<td class='td_content'>
							<ul class='areaul1'>
								<#list areaList as area>
										<#if comm.para_value==area.area_have && area.area_level=='2'>	
											<li><input id="ah${comm.para_value?if_exists}_${area.area_id?if_exists}"  class='cb_two'  name="end_area"   type="checkbox" value="${area.area_id?if_exists}"/>
											<span><span id='gan_${area.area_id?if_exists}'>${area.area_name?if_exists}</span><font class='areanum'></font></span>
											<div class="clear"></div>	
												<div class="threediv">
													<ul class='threeul1'>
														<#list areaList as three>
															<#if three.up_area_id==area.area_id && area.area_level='2'>
																<#if three.area_name?length gt 6>
																	<#assign swidth = 'liwidth6'/>
																<#elseif three.area_name?length == 5>
																	<#assign swidth = 'liwidth5'/>
																<#elseif three.area_name?length == 4>
																	<#assign swidth = 'liwidth4'/>
																<#elseif three.area_name?length == 3>
																	<#assign swidth = 'liwidth3'/>
																<#elseif three.area_name?length == 2>
																	<#assign swidth = 'liwidth2'/>
																</#if>															
																<li class="${swidth?if_exists}"><input id="ah${comm.para_value?if_exists}_${area.area_id?if_exists}_${three.area_id?if_exists}" class='cb_three' name="end_area" type="checkbox" value="${three.area_id?if_exists}"/>
																<span id='gan_${three.area_id?if_exists}' class='g_areaname'>${three.area_name?if_exists}</span></li>																	
															</#if>
														</#list>																										
													</ul>
													<div class='clear'></div>
													<div class='close'>
														<input class="colsethree" type="button" value="关闭" />
													</div>												
												</div>
											</li>
										</#if>					
								</#list>
							</ul>
						</td>
					</tr>				
				</#list>
			</table>
			<div class='areabottom'>
				<@s.hidden name="shiptemplate.ship_id"/>
				<@s.hidden name="shiptemplate.cust_id"/>
				<!--判断是否是更新的-->
				<@s.hidden name="is_update" value="1"/>
				<!--触发不同的方法名-->
				<@s.hidden id="sel_submit" value="4"/>
				<input type='button' value="确定" onclick="checkareaid();"/>&nbsp;
				<input type='button' onclick='colseShipCover();' value="取消"/>
			</div>
		</div>
 </@s.form>
</body>
</html>

