<html>
  <head>
    <title>会员资金提现记录</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Fundtocash_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员资金提现记录
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Fundtocash_add.action','');">申请提现</a></li>
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('fundtocash.trade_id','/admin_Fundtocash_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundtocash.trade_id')"/></td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">提现金额</td>
    <td width="10%"  align="center" class="top_td">收款方式</td>
    <td width="12%"  align="center" class="top_td">收款账号</td>
    <td width="12%"  align="center" class="top_td">账号姓名</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">申请时间</td>
    <td width="10%" align="center" class="top_td">审核</td>
  </tr>
  
  <#list fundtocashList as fundtocash>
  <tr>
    <td><input type="checkbox" name="fundtocash.trade_id" value="${fundtocash.trade_id?if_exists}"/></td>
    <td align="center">
    <#if fundtocash.cust_name?if_exists!=''>
    <#if fundtocash.cust_name?length lt 20>
    <a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">${fundtocash.cust_name?if_exists}</a>
    <#else>
    <a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">${fundtocash.cust_name[0..19]}</a>
    </#if>
    </#if>
    </td>
    <td align="center">${fundtocash.fund_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Fundtocash_list.action','getcash_type_s=${fundtocash.getcash_type?if_exists}');">${fundtocash.model_value?if_exists}</a></td>
    <td align="center">${fundtocash.account?if_exists}</td>
    <td align="center">${fundtocash.account_name?if_exists}</td>
    <td align="center"><#if fundtocash.order_state?if_exists=='0'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');"><font color='red'>未审核</font></a></#if>
    <#if fundtocash.order_state?if_exists=='1'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');"><font color='blue'>已审核</font></a></#if>
    <#if fundtocash.order_state?if_exists=='2'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');"><font color='green'>已处理</font></a></#if>
    <#if fundtocash.order_state?if_exists=='3'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state_s=${fundtocash.order_state?if_exists}');"><font color='green'>作废</font></a></#if></td>
    <td align="center">${fundtocash.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
         <td align="right" >收款方式:</td>
         <td>
         <@s.select name="getcash_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
       <tr>
             <td align="right">状态:</font></td>
             <td>
             	<@s.select name="order_state" list=r"#{'0':'未审核','1':'已审核','2':'已处理','3':'作废'}" headerKey="" headerValue="请选择"/>  
             </td>
        </tr>
       <tr>
        <td align="right" >时间段:</td>
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