<html>
<head>
<title>账户充值</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Fundrecharge_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>账户充值</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Fundrecharge_add.action','');">账户充值</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('fundrecharge.trade_id','/member_Fundrecharge_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
     <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundrecharge.trade_id')"/></td>
    <td width="12%"  align="center" class="top_td">充值金额</td>
    <td width="12%"  align="center" class="top_td">支付平台</td>
    <td width="12%"  align="center" class="top_td">支付时间</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
   <#list fundrechargeList as fundrecharge>
  <tr bgcolor="#FFFFFF">
     <td><input type="checkbox" name="fundrecharge.trade_id" value="${fundrecharge.trade_id?if_exists}"/></td>
    <td align="center">${fundrecharge.fund_num?if_exists}</td>
    <td align="center">${fundrecharge.payplat?if_exists}</td>
    <td align="center">${fundrecharge.pay_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');">
    <#if fundrecharge.order_state?if_exists=='0'>
    <font color='red'>未审核</font></a>
    </#if>
    <#if fundrecharge.order_state?if_exists=='1'>
    <font color='blue'>审核</font></a>
    </#if>
    <#if fundrecharge.order_state?if_exists=='2'>
    <font color='green'>作废</font></a>
    </#if>
    </a>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/member_Fundrecharge_view.action','fundrecharge.trade_id=${fundrecharge.trade_id?if_exists}');">
    <img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">支付平台:</td>
		<td>
		<@s.select name="payplat_s" cssStyle="width:150px;" list="paymentList" listValue="pay_name" listKey="pay_name" headerKey="0" headerValue="请选择"/> 
		</td>
	</tr>
            <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.select name="order_state_s" cssStyle="width:150px;" list=r"#{'0':'未审核','1':'审核','2':'作废'}" headerKey="" headerValue="请选择"/>  
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
