<html>
<head>
<title>我的订单</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
 <script type="text/javascript" src="/include/js/member/orderinfo.js"></script>
  <script type="text/javascript">
	  $(document).ready(function(){ 
	    //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");  
        //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
		 onlyshowarea("${cfg_topareaid?if_exists}",1);	
		 document.forms[0].action='/member_Orderinfo_list.action?ordertype=<#if order_type?if_exists='buy'>buy<#else>sell</#if>';
       });
       
       function updatecarriage(orderid){ 
       var carriage=$("#"+orderid).val();
         $.ajax({  	 
            type: "post",    	     
            url: "member_Orderinfo_updatecarriage.action?orderid="+orderid+"&carriage="+carriage+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data)
            {
               $("#all"+orderid).html(data);
               alert("订单号："+orderid+"运费修改成功");   
               document.forms[0].action='/member_Orderinfo_list.action?ordertype=sell';
               document.forms[0].submit();      
                    
            }
            });
       } 
       
       
	</script> 
</head>
<body>
<@s.form action="" method="post">
<@s.hidden name="member_orderinfo_id" id="member_orderinfo_id"/>
  <div class="cont_main">
  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的订单</td>
 	</tr>
	</table>
    <div class="finder_bar_bg" style="margin-bottom:3px;">
     <ul>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       </ul>
    </div>
    
<table width="100%" cellspacing="0" border="0">     
<#list orderinfoList as orderinfo>
<tr>
<td colspan="7">
<div style="border:1px solid #D4E7FF;margin-bottom:10px;">
<div style="background-color:#E8F2FF;">
&nbsp;&nbsp;订单编号: 
    <#if order_type?if_exists=='buy'>
        <a href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy"> ${(orderinfo.order_id)?if_exists}</a>
    <#else>
        <a href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=sell">${(orderinfo.order_id)?if_exists}</a>
    </#if>
&nbsp;&nbsp;下单时间:${(orderinfo.ord_date)?if_exists}
</div>
<table width="100%"  cellspacing="0" border="0" style="background-color:white;">
<tr>
<td  rowspan="2" width="100">
<div style="border:1px solid #e1e2e3;width:107px;height:107px;margin:5px;">
    <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">
	<#if (orderinfo.img_path)?if_exists !=''>
		<img src="${(orderinfo.img_path)?if_exists}" border="0" width="90px" height="90px" style="margin:8px;"/>
	<#else>
		<img src="${cfg_nopic?if_exists}" border="0" width="90px" height="90px"  style="margin:8px;"/>
	</#if>
   </a>   
</div>
</td>
</tr>
<tr>
<td style="line-height:18px;padding:6px;padding-right:18px;text-align:left;">
<a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">
<font color="#3366CC" style="font-size:13px;">
${(orderinfo.title)?if_exists}</font></a>
<br/><br/>
<#if order_type?if_exists=='buy'>
	<font color="#E22A1F">卖家：</font>${(orderinfo.seller_cust_name)?if_exists}
<#else>
	<font color="#E22A1F">买家：</font>${(orderinfo.buyer_cust_name)?if_exists}
</#if>
</td>

<td  width="90" align="center"><font color="#E22A1F">单价:</font>${(orderinfo.ord_price)?if_exists}元/${(orderinfo.unit)?if_exists}</td>
<td  width="70" align="center"><font color="#E22A1F">数量:</font>${(orderinfo.goods_num)?if_exists}</td>
<td  width="80" align="center"><font color="#E22A1F">总价:</font>${(orderinfo.total_fee)?if_exists}元    
</td>
<td  width="130" align="center" style="line-height:45px;"><font color="#E22A1F">运费:</font>
	    <#if order_type?if_exists=='buy'>
		    <font color="#808080"><#if orderinfo.carriage_fee?if_exists==0>免运费<#else>
		    ${(orderinfo.carriage_fee)?if_exists}元</#if></font>
		<#else>
		    <#if orderinfo.order_state?if_exists=='0'>
		    	<input id="${(orderinfo.order_id)?if_exists}" name="carriage" value="${(orderinfo.carriage_fee)?if_exists}" style="width:30px" onkeyup="checkNum(this)" />&nbsp;<font color="#808080">元</font>
		    	<input onclick="updatecarriage('${(orderinfo.order_id)?if_exists}')"  type="button" name="fk" value="修改运费" class="order_but"/>
		    <#else>
			    <font color="#808080"><#if orderinfo.carriage_fee?if_exists==0>
			    	免运费
			    <#else>			    
			    	<font color="#808080">${(orderinfo.carriage_fee)?if_exists}元</font><input name="carriage" type="hidden" value="${(orderinfo.carriage_fee)?if_exists}" style="width:50px"/>
			    </#if></font>
		    </#if>
	    </#if>
</td>

<td align="center" width="96" style="line-height:18px;">
    	<#if orderinfo.order_state?if_exists=='0'><font color="#404040">等待买家付款</font></#if>
    	<#if orderinfo.order_state?if_exists=='1'><font color="#404040">等待卖家发货</font></#if>
	    <#if orderinfo.order_state?if_exists=='2'><font color="#FF5500">卖家已发货</font></#if>
	    <#if orderinfo.order_state?if_exists=='3'><font color="green">交易成功</font></#if>
	    <#if orderinfo.order_state?if_exists=='4'><font color="#808080">交易关闭</font></#if>
	    <!--判断属于买家还是卖家判断-->
	    <br/>
	    <#if order_type?if_exists=='buy'>
    		<a style="color:#3366CC" href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy">订单详情</a>
    	<#else>
    		<a style="color:#3366CC" href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=sell">订单详情</a>
    	</#if>
    	<br/>
    	<!--评价详情判断-->   
    	 <#if orderinfo.commentState?if_exists=='0'>
            <font color="#808080">对方已评</font>
        <#elseif orderinfo.commentState?if_exists=='2'>
            <font color="#808080">双方已评</font>
        <#elseif orderinfo.commentState?if_exists=='1'>
            <font color="#808080">已评</font>
        </#if>   
    	<#if order_type?if_exists=='buy'>
    	   <#if orderinfo.commentState?if_exists!=''>
    		   <a style="color:#3366CC" href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy#maopj">[详细]</a>
    	   </#if>	   
    	<#else>
    	   <#if orderinfo.commentState?if_exists!=''>
    		   <a style="color:#3366CC" href="/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=sell#maopj">[详细]</a>
    	   </#if>
    	</#if> 	
</td>

<td align="center" width="100">
        <#if orderinfo.order_state?if_exists=='0'>
        	<#if order_type?if_exists=='buy'>
        		<a href="/member_Orderinfo_view.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy">修改</a><br/>
        	</#if>
        </#if>
    	<#if orderinfo.order_state?if_exists=='0'>
    		<#if order_type?if_exists=='buy'>
    			<a href="/member_Orderinfo_cancel.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy"   onclick="return confirm('确定取消此订单?')">取消订单</a>
    		</#if>
        </#if>      
        <#if orderinfo.order_state?if_exists=='0'>
    		<#if order_type?if_exists=='buy'>
    			<input onclick="location.href='/member_Orderinfo_payindex.action?orderinfo.order_id=${orderinfo.order_id?if_exists}&ordertype=buy'" type="button" name="fk" value="付款" class="order_but"/>
    		</#if>
    	</#if>
    	<#if orderinfo.order_state?if_exists=='1'>
    		<#if order_type?if_exists=='sell'>
    			<input onclick="location.href='/member_Orderinfo_delivergoods.action?orderinfo.order_id=${orderinfo.order_id?if_exists}&ordertype=sell'" type="button" name="fk" value="发货" class="order_but"/>
    		</#if>
    	</#if>
	    <#if orderinfo.order_state?if_exists=='2'>
	    	<#if order_type?if_exists=='buy'>
	    		<input onclick="location.href='/member_Orderinfo_takedeliver.action?orderinfo.order_id=${orderinfo.order_id?if_exists}&ordertype=buy'" type="button" name="fk" value="确认收货" class="order_but"/>
	    	</#if>
	    </#if>
	    <#if orderinfo.order_state?if_exists=='3'>
	    	 <#if order_type?if_exists=='buy'>	  
	    	      <#if orderinfo.commentState?if_exists!='1' && orderinfo.commentState?if_exists!='2'>
	                   <input onclick="location.href='/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=buy#maopj'" type="button" name="fk" value="评价" class="order_but"/> 
	               </#if>  	     
	        <#else>		
                   <#if orderinfo.commentState?if_exists!='1' && orderinfo.commentState?if_exists!='2'>
	                   <input onclick="location.href='/member_Orderinfo_check.action?orderinfo.order_id=${(orderinfo.order_id)?if_exists}&ordertype=sell#maopj'" type="button" name="fk" value="评价" class="order_but"/> 
	               </#if>
		     </#if>	    
	    </#if>
</td>
</tr>
</table>
</div>
</td>
</tr>
</#list>
</table>
<div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">订单号:</td>
		<td><@s.textfield name="order_id_s"/></td>
	</tr>
	<tr>
		<td align="right">商品标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
		<td align="right">卖家名称:</td>
		<td><@s.textfield name="sellername_s"/></td>
	</tr>
	<tr>
	    <td align="right">买家名称:</td>
	    <td><@s.textfield name="buyname_s"/></td>
    </tr>
   	<tr>
     <td align="right">收货人姓名:</td>
      <td><@s.textfield name="custname_s"/></td>
    </tr>
    <tr>
		<td align="right">地区:</td>
		<td colspan="5"><div id="arealist"></div></td>
	</tr>
    <tr>
    <td align="right">订单状态:</td>
     <td>
        <@s.select name="order_state_s" list="ordercommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
     </td>
    </tr>
    <tr>
    <td align="right" >下单时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
        至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
        </td>
    </tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>
</body>
</html>
