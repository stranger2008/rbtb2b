<html>
  <head>
    <title>会员资金充值记录表</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Fundrecharge_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金充值记录表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('fundrecharge.trade_id','/admin_Fundrecharge_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundrecharge.trade_id')"/></td>
    <td width="12%" align="center" class="top_td">会员名称</td>
    <td width="12%"  align="center" class="top_td">充值金额</td>
    <td width="12%"  align="center" class="top_td">支付平台</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="12%"  align="center" class="top_td">支付时间</td>
    <td width="10%" align="center" class="top_td">审核</td>
  </tr>
  
  <#list fundrechargeList as fundrecharge>
  <tr>
    <td><input type="checkbox" name="fundrecharge.trade_id" value="${fundrecharge.trade_id?if_exists}"/></td>
    
    <td align="center">
    <#if fundrecharge.cust_name?if_exists!=''>
    <#if fundrecharge.cust_name?length lt 20>
    ${fundrecharge.cust_name?if_exists}
    <#else>
    ${fundrecharge.cust_name[0..19]}...
    </#if>
    </#if>
    </td>
    <td align="center">${fundrecharge.fund_num?if_exists}</td>
    <td align="center">${fundrecharge.payplat?if_exists}</td>
    <td align="center"><#if fundrecharge.order_state?if_exists=='0'><a onclick="linkToInfo('/admin_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');"><font color='red'>未审核</font></a></#if><#if fundrecharge.order_state?if_exists=='1'><a onclick="linkToInfo('/admin_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');"><font color='blue'>审核</font></a></#if><#if fundrecharge.order_state?if_exists=='2'><a onclick="linkToInfo('/admin_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');"><font color='green'>作废</font></a></#if></td>
    <td align="center">${fundrecharge.pay_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Fundrecharge_view.action','fundrecharge.trade_id=${fundrecharge.trade_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
  </tr>

  </#list>
  
  
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cuts_name_s"/></td>
	</tr>
	<tr>
		<td align="right">支付平台:</td>
		<td><@s.select name="payplat_s"  list="paymentList" listValue="pay_name" listKey="pay_name" headerKey="0" headerValue="请选择"/> </td>
	</tr>
            <tr>
             <td >审核状态:</td>
             <td>
             	<@s.select name="order_state_s" list=r"#{'0':'未审核','1':'审核','2':'作废'}" headerKey="" headerValue="请选择"/>  
             </td>
           </tr>
    <tr>
    <tr>
        <td align="right" >支付时间:</td>
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
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
</@s.form>

  </body>
</html>