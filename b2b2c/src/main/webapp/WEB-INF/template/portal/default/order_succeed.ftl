<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/order_succeed.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/order_succeed.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="/templets/${templateStyle?if_exists}/js/common.js"/></script>
<script type="text/javascript">
 function getorderid(){
  var falg = false;
	var checks = document.getElementsByName("orderinfo.order_id");
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			falg=true;
			break;
		}
	}
	if(falg==false){
		alert("请选择您要支付的订单");
		runCount(3);
		return false;
	}
   document.forms[0].action="/member_Orderinfo_payindex.action?ordertype=buy";
   document.forms[0].submit();
 }
</script>
<title>订单成功-${cfg_webname?if_exists}</title>
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
</head>
<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<@s.form action="/member_Orderinfo_list.action" method="post">
<div class="w960">
  <div class="order_pic"><img src="/templets/${templateStyle?if_exists}/images/orderbar2.gif"></div>
  
  <div class="order_cont">
      <p class="suc_order">恭喜，订单提交成功!</p> 
      <p>
        <table  class="tab_style" cellspacing="0">
           <tr>
           <td class="td_style"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('orderinfo.order_id')"/></td>
           <td class="td_style">订单号</td>
           <td class="td_style">需支付的金钱(元)</td>
           <td class="td_style4">预计配送时间</td>
           </tr>
           <#list orderinfoList as orderinfo>
           <tr>
           <td><input type="checkbox" id="orderid" name="orderinfo.order_id" value="${orderinfo.order_id?if_exists}"/></td>
           <td class="td_style1">${(orderinfo.order_id)?if_exists}</td>
           <td class="td_style3 td_font">${(orderinfo.total_fee)?if_exists} 元</td>
           <td class="td_style5">发货后${(orderinfo.send_day)?if_exists}天</td>
           </tr>
           </#list>
        </table>
      </p>
      <p class="zf_p"><a href="###;" onclick="getorderid()"><img src="/templets/${templateStyle?if_exists}/images/pay.gif"></a><span>(未支付的订单将为您保留24小时，在收到全部订单金额后，我们将尽快安排发货)</span></p>
      <p class="line"><p>
      <p class="xq_p">你现在可以进行：<a href="/member_Orderinfo_list.action?ordertype=buy&parentMenuId=2458467446">查看订单详情</a><a href="/member_Orderinfo_list.action?ordertype=buy&parentMenuId=2458467446">修改订单</a><a href="/${templateRoute?if_exists}/supply/index.html">继续购物</a></p>
   </div>
</div>

</@s.form>
<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
</body>

</html>
