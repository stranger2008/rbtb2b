 <html>
<head>
<title>优惠劵列表</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">促销管理</a><span>></span><a href="#">优惠券管理</a>
    </div>
<@s.form action="/bmall_Coupon_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">促销管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">优惠券管理</td>
       </span></h2>
      
        <table width="100%">
       		<tr>
					<td align="right">优惠卷名称:</td> 
					<td><@s.textfield name="c_name_s"  maxLength="50"/></td>			
					<td align="right">优惠类型:</td>
					<td><@s.select name="c_type_s" list=r"#{'':'请选择','0':'优惠金额','1':'优惠积分','2':'优惠折扣'}" /></td>
					<td rowspan="2" width="10%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
			<tr>
					<td align="right">开始时间段:</td> 
					<td><@s.textfield size="15" id="txtStartDate" name="start_time_s"  cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtStartDate\\',{d:-1})}',readOnly:true})"/>
						至
						<@s.textfield size="15" id="txtEndDate" name="start_time_e_s"  cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})"/>
					</td>
					<td align="right" >结束时间段:</td> 
					<td><@s.textfield size="15" id="txtStartDate" name="end_time_s" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})"/>
						至
						<@s.textfield size="15" id="txtEndDate" name="end_time_e_s"  cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})"/>
					</td>
					
			</tr>
       	</table>
      
       
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
         <td width="3%" class="fthstyle1"></td>
         <td class="fthstyle1" width="14%">优惠卷名称</td>
	     <td class="fthstyle1" width="20%">优惠类型</td>
     	 
     	 <td class="fthstyle1" width="10%">使用次数</td>
     	 <td class="fthstyle1" width="10%">开始时间</td>
     	 <td class="fthstyle1" width="10%">结束时间</td>
     	 <td class="fthstyle1" width="10%">是否通用</td>
     	 <td class="fthstyle1" width="10%">操作</td>
         </tr>
         
         <tr><td colspan="8"  class="manadiv">
         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('coupon.c_id')"/>全选
			<input type="button" value="添加" class="submitbut" onclick="linkToInfo('/bmall_Coupon_add.action','');"/>
			<input type="button" value="删除" class="submitbut" onclick="delInfo('coupon.c_id','/bmall_Coupon_delete.action')"/>
         </td></tr>
         
         
         <#list couponList as coupon>
      <tr style="text-align:center;"> 
        
 	    <td><input type="checkbox" name="coupon.c_id" value="${coupon.c_id?if_exists}"/></td>
 	
    	<td>${coupon.c_name?if_exists}</td>
    
    	<td>
    	<#if coupon.c_type?if_exists=="0">优惠金额(￥<strong>${coupon.c_money?if_exists}</strong>)</#if>
    	<#if coupon.c_type?if_exists=="1">优惠积分(<strong>${coupon.c_inter?if_exists}</strong>分)</#if>
    	<#if coupon.c_type?if_exists=="2">优惠折扣(<strong>${coupon.c_discount?if_exists}</strong>折)</#if>
    	</td>
    	
    	<td>${coupon.coupon_times?if_exists}</td>
    	
    	<td>${coupon.start_time?if_exists}</td>
    	
    	<td>${coupon.end_time?if_exists}</td>
    	
    	<td>
    	<#if coupon.is_every?if_exists=="0"><font color="green">通用</font><#else><font color="red">不通用<font></#if>
    	</td>
    	
    <td>
    <a onclick="linkToInfo('/bmall_Coupon_view.action','coupon.c_id=${coupon.c_id?if_exists}');">修改</a>
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

