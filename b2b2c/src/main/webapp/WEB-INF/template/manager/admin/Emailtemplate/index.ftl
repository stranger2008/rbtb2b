<html>
  <head>
    <title>邮件模板管理</title>
  </head>
  <body>
<@s.form action="/admin_Emailtemplate_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 邮件模板管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Emailtemplate_add.action','');">添加模板</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="ret"><a href="/admin_Emailhistory_list.action">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('emailtemplate.temp_id')"/></td>
    <td width="20%" align="center" class="top_td">模板名称</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list emailtemplateList as emailtemplate>
  <tr>
    <td><input type="checkbox" name="emailtemplate.temp_id" value="${emailtemplate.temp_id?if_exists}"/></td>
    <td align="center">${emailtemplate.temp_name?if_exists}</td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Emailtemplate_view.action','emailtemplate.temp_id=${emailtemplate.temp_id?if_exists}');"><img src="/include/images/bj.gif" /></a>
    	<#if emailtemplate.sys_temp=='0'>
    	<a href="/admin_Emailtemplate_delete.action?emailtemplate.temp_id=${emailtemplate.temp_id?if_exists}"><img src="/include/images/delete.png" border="0"/></a>
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
		<td align="right">模板名称:</td>
		<td><@s.textfield name="temp_name_s"/></td>
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