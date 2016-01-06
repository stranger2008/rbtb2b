 <html>
<head>
<title>评价列表</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
</head>
<body>
	<div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的评价</a>
	</div>
<@s.form action="/bmall_Goodseval_auditList.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">交易管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的评价</td>
       </span></h2>
       <table width="100%">
       		<tr>
					<td align="right">商品名称:</td> 
					<td><@s.textfield name="goods_name_s"  maxLength="50"/></td>
					<td align="right">商品评级:</td> 
					<td><@s.select name="g_eval_s" list=r"#{'':'请选择','1':'好评','0':'中评','-1':'差评'}" /></td>
					<td align="right">是否有效:</td>
					<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}" /></td>	
					
					<td rowspan="2" width="6%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
			<tr>
					<td align="right">留言时间段:</td> 
					<td>
						<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 						
					至						
						<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>			
					<td align="right">回复时间段:</td>
					<td>
						<@s.textfield id="txtstartDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 		
						至		
						<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>		
		  </tr>
       	</table>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
	     <td class="fthstyle1" width="20%">商品名称</td>
    
     	 <td class="fthstyle1" width="10%">评论人</td>
     	 
     	 <td class="fthstyle1" width="10%">商品评级</td>
    
     	 <td class="fthstyle1" width="10%">评价时间</td>
    
     	 <td class="fthstyle1" width="17%">回复人</td>
    
     	 <td class="fthstyle1" width="10%">回复时间</td>
    
     	 <td class="fthstyle1" width="10%">是否有效</td>
     	 
     	 <td class="fthstyle1" width="8%">操作</td>
         </tr>
         <#list goodsevalList as goodseval>
      <tr style="text-align:center;">   
    	<td>${goodseval.goods_name?if_exists}</td>
    
    	<td>${goodseval.user_name?if_exists}</td>
    
       
        <td>
	    	<#if goodseval.g_eval?if_exists=='1'>好评</#if>
	    	<#if goodseval.g_eval?if_exists=='0'>中评</#if>
	    	<#if goodseval.g_eval?if_exists=='-1'>差评</#if>
    	</td>
    	
    
    	<td>${goodseval.eval_date?if_exists}</td>
    
        <td>
        <#if goodseval.explan_cust_id?if_exists==''>暂无回复<#else>${goodseval.explan_cust_id?if_exists}</#if>
        </td>
    
    	<td>${goodseval.explan_date?if_exists}</td>
    	</td>
    	<td>
    	<#if goodseval.is_enable?if_exists=='0'>有效<#else>无效</#if>
    	</td>  
    <td>
    <a onclick="linkToInfo('/bmall_Goodseval_audit.action','goodseval.trade_id=${goodseval.trade_id?if_exists}');">评价</a>
    </td>
  </tr>
  </#list>
       </table>
        <div class="listbottom">
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

