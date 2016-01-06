<html>
  <head>
    <title>查看积分流</title>
  </head>
  <body>
<@s.form action="/admin_Interhistory_list.action" method="post">
 ${listSearchHiddenField?if_exists}
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 查看积分流 
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="ret"><a onclick="linkToInfo('/admin_Memberinter_list.action','');">返回</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">添加</td>
    <td width="10%"  align="center" class="top_td">减少</td>
    <td width="10%"  align="center" class="top_td">余下积分数</td>
    <td width="10%"  align="center" class="top_td">操作人</td>
    <td width="10%"  align="center" class="top_td">操作时间</td>
    <td width="15%"  align="center" class="top_td">事由</td>
    <td width="15%"  align="center" class="top_td">备注</td>
  </tr>
  
  <#list interhistoryList as interhistory>
  <tr>
    <td align="center">
    <#if interhistory.cust_name?if_exists!=''>
    <#if interhistory.cust_name?length lt 16>
    ${interhistory.cust_name?if_exists}
    <#else>
    ${interhistory.cust_name[0..15]}...
    </#if>
    </#if>
    </td>
    <td align="center">${interhistory.inter_in?if_exists}</td>
    <td align="center">${interhistory.inter_out?if_exists}</td>
    <td align="center">${interhistory.thisinter?if_exists}</td>
    <td align="center">${interhistory.user_name?if_exists}</td>
    <td align="center">${interhistory.in_date?if_exists}</td>
    <td align="center">
    <#if interhistory.reason?if_exists!=''>
    <#if interhistory.reason?length lt 7>
    ${interhistory.reason?if_exists}
    <#else>
    ${interhistory.reason[0..6]}...
    </#if>
    </#if>
    </td>
    <td align="center">
    <#if interhistory.remark?if_exists!=''>
    <#if interhistory.remark?length lt 7>
    ${interhistory.remark?if_exists}
    <#else>
    ${interhistory.remark[0..6]}...
    </#if>
    </#if>
    </td>
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
		<td><@s.textfield name="cust_name_s"/></td>
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