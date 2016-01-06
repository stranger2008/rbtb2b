<html>
<head>
<title>资金提取管理</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Fundtocash_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的资金>资金提取</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Fundtocash_add.action','');">申请提现</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('fundtocash.trade_id','/member_Fundtocash_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundtocash.trade_id')"/></td>
     <td width="10%"  align="center" class="top_td">提现金额</td>
    <td width="10%"  align="center" class="top_td">收款方式</td>
    <td width="10%"  align="center" class="top_td">收款账号</td>
    <td width="10%"  align="center" class="top_td">账号姓名</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="20%"  align="center" class="top_td">申请时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
   <#list fundtocashList as fundtocash>
  <tr bgcolor="#FFFFFF">
     <td><input type="checkbox" name="fundtocash.trade_id" value="${fundtocash.trade_id?if_exists}"/></td>
    <td align="center">${fundtocash.fund_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/member_Fundtocash_list.action','getcash_type_s=${fundtocash.getcash_type?if_exists}');">${fundtocash.model_value?if_exists}</a></td>
    <td align="center">${fundtocash.account?if_exists}</td>
    <td align="center">${fundtocash.account_name?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');">
    <#if fundtocash.order_state?if_exists=='0'>
    <font color='red'>未审核</font></a>
    </#if><#if fundtocash.order_state?if_exists=='1'><font color='blue'>已审核</font></a></#if>
    <#if fundtocash.order_state?if_exists=='2'><font color='green'>已处理</font></a></#if>
    <#if fundtocash.order_state?if_exists=='3'><font color='green'>作废</font></a></#if>
    </a></td>
    <td align="center">${fundtocash.in_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">
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

<div class="divceng" style="display:none;width:200px;height:100px;" id="searchDiv">
	<table align="left">
	<tr>
         <td class="table_name">收款方式:</td>
         <td>
         <@s.select name="getcash_type_s" cssStyle="width:100px;" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
       <tr>
             <td class="table_name">状态:</font></td>
             <td>
             	<@s.select cssStyle="width:100px;" name="order_state" list=r"#{'':'请选择','0':'未审核','1':'已审核','2':'作废'}" headerKey="" headerValue="请选择"/>  
             </td>
           </tr>
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
