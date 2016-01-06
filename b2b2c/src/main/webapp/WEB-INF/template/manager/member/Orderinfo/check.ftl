<html>
  <head>
    <title>查看订单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="/include/js/member/orderinfo.js"></script>
    <link href="/include/css/member/orderinfo.css" rel="stylesheet" type="text/css" />
    <#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
  </head>
  <body>
 <@s.form action="/member_Tradecomment_insert.action" method="post" validate="true">
<div class="cont_main">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的订单>查看订单</td>
 	</tr>
	</table>  
     <table width="100%" border="0" bgcolor="#FFF7EB"  cellpadding="1" style="border:1px solid #F58B0F;margin-top:10px;">
      <tr>
       <td style="padding:20px;font-size:14px;font-weight:bold;color:#404040;border:1px solid #FEBF90;">
      当前订单状态：<#if orderinfo.order_state?if_exists=='0'>等待买家付款<#if order_type?if_exists=='buy'><a href="/member_Orderinfo_pay.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy">(付款)</a></#if></#if>
    	<#if orderinfo.order_state?if_exists=='1'>等待卖家发货<#if order_type?if_exists=='sell'><a href="/member_Orderinfo_delivergoods.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=sell">(发货)</a></#if></#if>
	    <#if orderinfo.order_state?if_exists=='2'>等待买家确认收货<#if order_type?if_exists=='buy'><a href="/member_Orderinfo_takedeliver.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy">(确认收货)</a></#if></#if>	          
	    
	    <#if orderinfo.order_state?if_exists=='3' && commentState=='2'>	
	           交易成功，双方已评价
	    <#elseif orderinfo.order_state?if_exists=='3'>	 
	           交易已成功 <br/>  
	         <div style="padding-left:18px;font-size:12px;font-weight:400;">  
		        <#if ordertype?if_exists=='buy'> 	
			      	     <#if commentState?if_exists=="1">
			      	         1.交易成功，请给卖家- <a href="/showroom/${(memberuser.user_name)?if_exists}" target="_blank"><font  class="f_busin">[${(seller.cust_name)?if_exists}]</font></a>进行评价。		      	           
			      	     <#elseif commentState?if_exists=="0">
			      	         1.交易成功，请等待卖家评价 	
			      	     <#elseif commentState?if_exists=="3">	
			      	         1.您还未对 卖家- <a href="/showroom/${(memberuser.user_name)?if_exists}"  target="_blank"><font  class="f_busin">[${(seller.cust_name)?if_exists}]</font></a>进行评价。      	     
			      	     </#if>	                   
			     </#if>	  
			     <#if ordertype?if_exists=='sell'> 			          
			           	  <#if commentState=="1">
			      	          1.交易成功，请给买家- <a href="/showroom/${(memberuser.user_name)?if_exists}"  target="_blank"><font  class="f_busin">[${(member.cust_name)?if_exists}]</font></a>进行评价。	
			      	      <#elseif commentState=="0">
			      	          1.交易成功，请等待买家评价
			      	      <#elseif commentState?if_exists=="3">	
			      	          1.您还未对 买家- <a href="/showroom/${(memberuser.user_name)?if_exists}"  target="_blank"><font  class="f_busin">[${(member.cust_name)?if_exists}]</font></a>进行评价	
			      	      </#if>		       
			     </#if>
			     <br/>2.当双方互评后，才能相互看到对方评价。 
			     <br/>3.每个自然月，买家对同一卖家的好评分数不能超过${cfg_com_month?if_exists}分。 
		     </div>   
	    </#if>  
	    <#if orderinfo.order_state?if_exists=='4'>关闭交易</#if>
       </td>
      </tr>
     </table>     
     <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont"> 
           <tr>
           <td>
           		<b>收货地址：</b>${(orderinfo.cust_name)?if_exists},${(orderinfo.phone)?if_exists},${(orderinfo.cell_phone)?if_exists},
           		${stack.findValue("@com.rbt.function.AreaFuc@getAreaNameByMap('${(orderinfo.area_attr)?if_exists}')")},${(orderinfo.address)?if_exists},${(orderinfo.post_code)?if_exists}
           		<br/>
           		<b>买家留言：</b>${(orderinfo.remark)?if_exists}
           </td>
           </tr>
           
           
           <tr>
	           <td>
	           		<b>订单信息：</b>&nbsp;订单号:${(orderinfo.order_id)?if_exists}&nbsp;&nbsp;下单时间:${(orderinfo.in_date?if_exists).substring(0,19)}
	           </td>
           </tr>
           
            <tr>
	           <td>
	           		<b>配送方式：&nbsp; ${(sendmode.smode_name)?if_exists}</b> 
	           </td>
           </tr>
           
            <tr>
	           <td>
	           		<b>发票信息：&nbsp;<#if orderinfo.invoice_type?if_exists=='2' || orderinfo.invoice_type?if_exists==''>不开发票</#if>
	           		                 <#if orderinfo.invoice_type?if_exists=='0'>普通发票</#if>
	           		                 <#if orderinfo.invoice_type?if_exists=='1'>增值发票</#if>
	           </td>
           </tr>
           
           <#if orderinfo.invoice_type?if_exists=='0'>
           <tr>
	           <td style="padding-left:70px;">
	           <ul>           
	               <li>
	           		发票抬头：&nbsp;${orderinfo.invoice_top?if_exists}&nbsp;
                   </li>
                   <#if orderinfo.invoice_top?if_exists=='单位'>
                   <li>
	           		公司名称：&nbsp;${orderinfo.company_name?if_exists}&nbsp;
                   </li>
                   </#if>
                   <li>
	           		发票内容：&nbsp;${orderinfo.invoice_content?if_exists}&nbsp;
                   </li>
		        </ul>    
	           </td>
           </tr>
           </#if>
           
           <#if orderinfo.invoice_type?if_exists=='1'>
           <tr>
	           <td style="padding-left:70px;">
	           <ul>           
	               <li>
	           		单位名称：&nbsp;${orderinfo.company_name?if_exists}&nbsp;
                   </li>
                   <li>
	           		纳税人标识号：&nbsp;${orderinfo.ident_number?if_exists}&nbsp;
                   </li>
                   <li>
	           		注册地址：&nbsp;${orderinfo.regis_address?if_exists}&nbsp;
                   </li>
                   <li>
	           		注册电话：&nbsp;${orderinfo.regis_tel?if_exists}&nbsp;
                   </li>
                   <li>
	           		开户银行：&nbsp;${orderinfo.open_bank?if_exists}&nbsp;
                   </li>
                   <li>
	           		银行帐户：&nbsp;${orderinfo.bank_account?if_exists}&nbsp;
                   </li>
                   <li>
	           		发票内容：&nbsp;明细&nbsp;
                   </li>
		        </ul>    
	           </td>
           </tr>
           </#if>
           
           <tr>
	           <td>
	           		<b>订单流程：</b>
	           </td>
           </tr>
           
           <tr>
	           <td style="padding-left:70px;">
	           <ul>           
		           <#list orderhistoryList as orderhistory>
		               <li>
		           		 ${orderhistory.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}&nbsp;${(orderhistory.action_name)?if_exists}&nbsp;
                       </li>
		           </#list>
		        </ul>    
	            <#if orderinfo.order_state?if_exists=='3'>交易成功</#if> 
	   
	           </td>
           </tr>
           
           <#if order_type?if_exists=='buy'> 
           <tr>
	           <td>
	           		<b>卖家信息：</b>&nbsp;
	           		<a href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin"> ${(seller.cust_name)?if_exists}</a>
	           </td>
           </tr>           
           </#if>
           
           <#if order_type?if_exists=='sell'>
           <tr>
	           <td>
	           		<b>买家信息：</b>
	           </td>
           </tr>
           
           <tr>
	           <td style="padding-left:70px;">
	           		<a href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin">${(member.cust_name)?if_exists}</a>
	           </td>
           </tr>
           </#if>
           
           <tr>
	           <td style="padding:10px;">	           		
	           		<table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1">	           		
	           			<tr>
	           				<td style="background:#E8F2FF;">
	           					商品名称
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					单价
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					数量
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					总价
	           				</td>
	           			</tr>           					
	           			<tr>
	           				<td>
	           					 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">${(supply.title)?if_exists}</a>
	           				</td>
	           				<td>
	           					${(supply.price)?if_exists}
	           				</td>
	           				<td>
	           					${(orderinfo.goods_num)?if_exists}
	           				</td>
	           				<td>
	           					${(orderinfo.goods_fee)?if_exists}&nbsp;元
	           				</td>
	           			</tr>
	           		</table>
	           </td>
           </tr>        
           <tr>
	           <td align="right">
	           		运费：${(orderinfo.carriage_fee)?if_exists}&nbsp;元&nbsp;&nbsp;总支付：<font size="4" color="#FF5500"><b>${(orderinfo.total_fee)?if_exists}</b></font>&nbsp;元
	           </td>
           </tr>

           
           <#if orderinfo.order_state?if_exists=='3'>
           <tr><td>    
            <@s.hidden  name="ordertype"/>        
            <@s.hidden  name="tradecomment.order_id" value="${(orderinfo.order_id)?if_exists}"/>   
             <#if ordertype?if_exists=='buy'> 
	        	    <@s.hidden  name="tradecomment.com_way" value="1"/>
	                <@s.hidden  name="tradecomment.set_cust_id" value="${(orderinfo.cust_id)?if_exists}"/>
	                <@s.hidden  name="tradecomment.get_cust_id" value="${(orderinfo.seller_id)?if_exists}"/>   
		     <#elseif ordertype?if_exists=='sell'> 
		        	<@s.hidden  name="tradecomment.com_way" value="2"/>
		            <@s.hidden  name="tradecomment.set_cust_id" value="${(orderinfo.seller_id)?if_exists}"/>
		            <@s.hidden  name="tradecomment.get_cust_id" value="${(orderinfo.cust_id)?if_exists}"/>
		     </#if>            
           <div class="comm_div">
                   <a name="maopj"></a>
		           <div style="height:26px;padding-left:10px;background-color:#E8F2FF;">
		           <#if ordertype?if_exists=='buy'>   
		                对商品的评价
		           <#else>
		                我对买家的评价
		           </#if>
		           </div>
		           <div>
	               <div class="comm_goods">
		               <div class="comm_goods_d1">
			               <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">
			               	<#if (supply.img_path)?if_exists !=''>
								<img src="${(supply.img_path)?if_exists}" border="0" width="90px" height="90px" style="margin:8px;"/>
							<#else>
								<img src="${cfg_nopic?if_exists}" border="0" width="90px" height="90px"  style="margin:8px;"/>
							</#if>
			               </a>
		               </div>
		               <div  class="comm_goods_d2">
		                      <div class="comm_goods_d2_top">
			                      	<a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">
					                  <font style="color:#3266BF;font-weight:600;font-size:13px;">${(supply.title)?if_exists}</font>	
				                    </a>  
		                      </div>
		                      <div>
			                   	 	<#if ordertype?if_exists=='buy'>   
						              <font color="#E22A1F">卖家：</font>
						              <a style="color:#A3A3A3;font-weight:600px;" href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin"> ${(seller.cust_name)?if_exists}</a>
						           <#else>
			           		          <font color="#E22A1F">买家：</font>
			           		          <a style="color:#A3A3A3;font-weight:600px;" href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin">${(member.cust_name)?if_exists}</a>
						           </#if>  
					          </div>         
			           </div>		
			           		           
			           <div  class="comm_goods_d3">
			             <div style="height:30px;">			            		             
					                 <#if (sellcommList?size>0) >
					                 <span style="margin-top:20px;margin-left:-26px;"> 
					                       <#list sellcommList as sell>
					                            <#if sell.com_type=='1'>
					                               <div class="good_comm" style="margin-top:-20px;width:290px;">好评！
					                               <#if sell.is_score?if_exists=='0'><font class="is_score">(不计分)</font></#if>
					                            <#elseif sell.com_type=='0'>
					                               <div class="same_comm" style="margin-top:-20px;width:290px;">中评！
                                               <#elseif sell.com_type=='-1'>
					                                <div class="bad_comm" style="margin-top:-20px;width:290px;">差评！
					                            </#if>
					                           <font style="padding-left:10px;color:#AC9999;font-weight:400;">评价时间:[${sell.in_date?if_exists}]</font></div>
					                       </#list>						                      
					                  </span>	                 
					                 <#else>
							             <span>
								              <input type="radio" name="tradecomment.com_type" value="1" checked>
								              <div class="good_comm" style="margin-top:-20px;*margin-top:-30px;">好评！</div>
								         </span>
								         <span>
								              <input type="radio" name="tradecomment.com_type" value="0">
								              <div class="same_comm" style="margin-top:-20px;*margin-top:-30px;">中评！</div>
								         </span>   
								         <span>
								              <input type="radio" name="tradecomment.com_type" value="-1">
						            	      <div class="bad_comm" style="margin-top:-20px;*margin-top:-30px;">差评！</div>
						            	 </span>
						             </#if>				             
			             </div>			            
			             <div>		             
					          <#if (sellcommList?size>0) >
                                  <div style="border-top:1px solid #A8A8A8;padding:6px;">
                                      <#list sellcommList as sell>
                                            ${sell.content?if_exists}
                                      </#list>
                                  </div>
                              <#else>
                                 <@s.textarea id="content" name="tradecomment.content" cssStyle="width:343px;height:121px;" maxlength="300" onkeydown="gbcount();" onkeyup="gbcount();"/>
			                     <div class="comm_content">(还可以输入<font id="countnum" color="#F6BD6E" style="font-weight:600;">300</font>字!)</div>   
                              </#if>
			              </div>    
			           </div>	  
			              	<br class="clear"/> 	               
	               </div>     	                         
	               </div>   
	         </div>      
           <#if ordertype?if_exists=='buy' >       
	       <div class="comm_div" style="height:161px;">
		            <div style="height:26px;padding-left:10px;background-color:#E8F2FF;">
		                对卖家的动态评分
		            </div>
		            <div >
		               <!--当只是买家的才能为卖家进行卖家动态评分-->			                        
				          <div>            
					          <div style="float:left;margin-left:20px;margin-top:8px;">
					             <#if (sellcommList?size<=0) >   
					                <ul>
						               <li>商品于描述相符:<@s.radio name="tradecomment.desc_num" list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}" value="5"/></li>
						               <li>卖家的服务态度:<@s.radio name="tradecomment.service_num" list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}"  value="5"/></li>
						               <li>卖家发货的速度:<@s.radio name="tradecomment.seller_num" list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}"  value="5"/></li>
						               <li>物流发货的速度:<@s.radio name="tradecomment.ship_num" list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}" value="5"/></li>				               				               
					                 </ul> 
					              <#else>
					                <#list sellcommList as sell>
						                <ul>
							               <li>商品于描述相符: <font class="dong">${sell.desc_num?if_exists}</font>分</li>
							               <li>卖家的服务态度: <font class="dong">${sell.service_num?if_exists}</font>分</li>
							               <li>卖家发货的速度: <font class="dong">${sell.seller_num?if_exists}</font>分</li>
							               <li>物流发货的速度: <font class="dong">${sell.ship_num?if_exists}</font>分</li>				               				               
						                 </ul> 
					                 </#list>
					              </#if>             
						      </div>             
				           </div>			             
		            </div>
			</div> 		
			</#if>	    
           </td>
           </tr>
             <#if orderinfo.order_state?if_exists=='3' && commentState=='2' && (buycommList?size>0)>
               <tr ><td >    
                     
	             <div style="border:1px solid #CDD1D4;min-height:165px;_height:165px;width:785px;">	          
		           <div style="height:26px;padding-left:10px;background-color:#E8F2FF;">
		              <#if ordertype="sell">
		                    来自买家的评价
		              <#else>
		                    来自卖家的评价
		              </#if>
			       </div>			       
			               <#list buycommList as comm>  
				           <table width="100%">
			            	  <tr>
			            	  <td style="width:390px;">			            	  
				            	  <div style="height:20px;border-bottom:1px solid #A8A8A8;padding-bottom:10px;">
				            	     <font color="#333333" style="font-weight:600;">
				            	     <#if ordertype=="sell">
				            	       ${member.cust_name?if_exists}
				            	     <#else>
				            	       ${seller.cust_name?if_exists}
				            	     </#if>
				            	     </font>&nbsp;的评论:
				            	     <font color="#AC9999">[${comm.in_date?if_exists}]</font>
				            	  </div>          	  
				            	  <div style="border:1px solid #FFF;width:360px;min-height:133px;_height:133px;padding-left:10px;padding-top:2px;color:#333333;" >
				            	    ${comm.content?if_exists}
				            	  </div>
			            	  </td>
			            	  <td style="padding-left:33px;">
			            	    <ul style="<#if ordertype='sell'>float:left;<#else>float:right;</#if>width:200px;">
			            	    <li><font style="float:left;">评价:</font>
			            	    <#if comm.com_type=='1'>
			            	      <div class="good_comm">好评！
				            	      <#if comm.is_score=='0'>
					            	      <font class="is_score">(不计分)</font>
					            	  </#if>
			            	      </div>
			            	    <#elseif comm.com_type=='0'>
			            	       <div  class="same_comm">中评！</div>
			            	    <#elseif comm.com_type=='-1'>
			            	        <div class="bad_comm" >差评！</div>
			            	    </#if>
			            	    </li>
			            	        <!--只有卖家才显示该动态店铺评分-->
				            	    <#if ordertype=='sell'>
					            	    <li >商品于描述相符: <font class="dong">${comm.desc_num?if_exists}</font>分</li>
					            	    <li >卖家的服务态度: <font class="dong">${comm.service_num?if_exists}</font>分</li>
					            	    <li >卖家发货的速度: <font class="dong">${comm.seller_num?if_exists}</font>分</li>
					            	    <li >物流发货的速度: <font class="dong">${comm.ship_num?if_exists}</font>分</li>   
				            	    </#if>  	    
			            	    </ul>
			            	   </td>
			            	   </tr>
			            	</table>
		               </#list>	          
			           </#if>  	                  
	              </div>         
	           </td></tr> 	                    
           </#if>      
           <tr>
           <td colspan="4" class="subbut">
                    <#if ((sellcommList?size<=0) && orderinfo.order_state?if_exists=='3')>
                        <@s.submit value="提交评论" cssClass="sub"/> 
                    </#if>     
	       		<input type="button" name="returnList" value="返回列表" class="sub" onclick="location.href='/member_Orderinfo_list.action?ordertype=<#if ordertype?if_exists='buy'>buy<#else>sell</#if>';"/>
           </td>
           </tr>
         </table>
</div>

</div>
<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
</@s.form>  
 </body>
</html>