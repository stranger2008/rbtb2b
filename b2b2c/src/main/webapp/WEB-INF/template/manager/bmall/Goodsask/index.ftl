 <html>
<head>
<title>咨询列表</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
</head>
<body>
	<div class="postion">
 		<a href="#">我是卖家</a><span>></span><a href="#">客服服务</a><span>></span><a href="#">买家咨询</a>
	</div>
<@s.form action="/bmall_Goodsask_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">客服服务</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">买家咨询</td>
       </span></h2>
       <table width="100%">
       		<tr>
					<td align="right">咨询商品:</td> 
					<td><@s.textfield name="goods_name_s"  maxLength="50"/></td>
					<td align="right">咨询类型:</td> 
					<td><@s.select name="c_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>					
					<td align="right">是否有效:</td>
					<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}" /></td>
					<td rowspan="2" width="8%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
			<tr>
					<td align="right">咨询时段:</td> 
					<td>
						<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 						
					至						
						<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>
					<td align="right">回复时段:</td>
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
	     <td class="fthstyle1" width="15%">咨询商品</td>
     	 <td class="fthstyle1" width="15%">咨询类型</td>
     	 <td class="fthstyle1" width="10%">咨询人</td>
     	 <td class="fthstyle1" width="10%">咨询时间</td>
     	 <td class="fthstyle1" width="15%">回复人</td>
     	 <td class="fthstyle1" width="10%">回复时间</td>
     	 <td class="fthstyle1" width="10%">是否有效</td>
     	 <td class="fthstyle1" width="10%">操作</td>
         <tr>
         <#list goodsaskList as goodsask>
      <tr style="text-align:center;">   
    	<td>${goodsask.goods_name?if_exists}</td>
    
    	<td>${goodsask.c_type?if_exists}</td>
    
    	<td>${goodsask.user_name?if_exists}</td>
    
    	<td>${goodsask.c_date?if_exists}</td>
    
        <td>
        <#if goodsask.re_cust_id?if_exists==''>还没有回复！<#else>${goodsask.re_cust_id?if_exists}</#if>
        </td>
    
    	<td>${goodsask.re_date?if_exists}</td>
    	<td>
    	<#if goodsask.is_enable?if_exists=='0'>有效<#else>无效</#if>
    	</td>  
    <td>
    <#if type?if_exists=='buy'>
    <a onclick="linkToInfo('/bmall_Goodsask_view.action?type=buy','goodsask.trade_id=${goodsask.trade_id?if_exists}');">查看</a>
    <#else>
    <a onclick="linkToInfo('/bmall_Goodsask_view.action','goodsask.trade_id=${goodsask.trade_id?if_exists}');">回复</a>
    </#if>
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

