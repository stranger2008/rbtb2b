<html>
  <head>
    <title>域名绑定申请</title>
  </head>
  <body>

<@s.form action="/admin_Topdomain_list.action" method="post">

<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 域名绑定申请
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
    <li> <a onclick="linkToInfo('/admin_Topdomain_add.action','');">添加域名</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('topdomain.info_id','/admin_Topdomain_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('topdomain.info_id')"/></td>
    <td width="20%" align="center" class="top_td">域名</td>
    <td width="20%"  align="center" class="top_td">企业名称</td>
    <td width="10%"  align="center" class="top_td">域名类型</td>
    <td width="10%"  align="center" class="top_td">是否有效</td>
    <td width="10%"  align="center" class="top_td">申请时间</td>
    <td width="7%" align="center" class="top_td" >修改</td>
  </tr>
  
  <#list topdomainList as topdomain>

    <tr bgcolor="<#if topdomain_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="topdomain.info_id" value="${topdomain.info_id?if_exists}"/></td>
    <td align="center"><a href="${topdomain.domain_url?if_exists}" target="_blank">${topdomain.domain_url?if_exists}</a></td>
    <td align="center">
    <#if (topdomain.cust_name)?if_exists=="">
     运营商
    <#else>
     ${topdomain.cust_name?if_exists}
    </#if>
    </td>
    <td align="center">
     <#if topdomain.domain_type=='0'>
     <font color="green">一级域名</font>
     <#else>
     <font color="blue">二级域名</font>
     </#if>
     </td>
    <td align="center">
    	<#if (topdomain.enabled)?if_exists == '1'>
     		<font color="green">有效</font> <a href="/admin_Topdomain_updateenabled.action?topdomain.info_id=${topdomain.info_id?if_exists}&topdomain.enabled=0"><font color="red">禁用</font></a>
     	<#else>
     		<font color="red">无效</font> <a href="/admin_Topdomain_updateenabled.action?topdomain.info_id=${topdomain.info_id?if_exists}&topdomain.enabled=1"><font color="blue">启用</font></a>
     	</#if>
    </td>
    <td align="center">${topdomain.in_date?if_exists}</td>
    <td align="center">
     <a onclick=linkToInfo("/admin_Topdomain_view.action","topdomain.info_id=${(topdomain.info_id)?if_exists}");>
  <img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">域名:</td>
		<td><@s.textfield name="domain_url_s"/></td>
	</tr>
	<tr>
		<td align="right">申请会员类型:</td>
		<td>
			<@s.select name="membertype_s" list=r"#{'':'请选择','0':'运营商','1':'会员'}"  />
		</td>
	</tr>
	<tr>
		<td align="right">域名类型:</td>
		<td>
			<@s.select name="domaintype_s" list=r"#{'':'请选择','0':'一级域名','1':'二级域名'}" />
		</td>
	</tr>
	<tr>
		<td align="right">企业名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">是否有效:</td>
		<td>
			<@s.select name="enabled_s" list=r"#{'':'请选择','1':'有效','0':'无效'}"  />
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
