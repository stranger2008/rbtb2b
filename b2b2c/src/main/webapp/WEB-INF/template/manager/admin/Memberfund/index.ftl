<html>
  <head>
    <title>资金管理</title>
  </head>
<body>
<@s.form action="/admin_Memberfund_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 资金管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Fundhistory_list.action','');">查看资金流</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="15%"  align="center" class="top_td">总金额</td>
    <td width="15%"  align="center" class="top_td">可用金额</td>
    <td width="15%"  align="center" class="top_td">冻结金额</td>
    <td width="10%"  align="center" class="top_td">资金调整</td>
    <td width="10%"  align="center" class="top_td">查看资金变化情况</td>
    <td width="10%" align="center" class="top_td">会员充值</td>
  </tr>
  
  <#list memberfundList as memberfund>
  <tr>
     <td align="center">
     <#if memberfund.cust_name?if_exists!=''>
     <#if memberfund.cust_name?length lt 20>
     ${memberfund.cust_name?if_exists}
     <#else>
     ${memberfund.cust_name[0..19]}...
     </#if>
     </#if>
     </td>
    <td align="center">${memberfund.fund_num?if_exists}</td>
    <td align="center">${memberfund.use_num?if_exists}</td>
    <td align="center">${memberfund.freeze_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberfund_view.action','memberfund.cust_id=${memberfund.cust_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Fundhistory_list.action','cust_id_s=${memberfund.cust_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberfund_add.action','memberfund.cust_id=${memberfund.cust_id?if_exists}');"><img src="/include/images/admin/memadd.gif" /></a></td>
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


</@s.form>

  </body>
</html>