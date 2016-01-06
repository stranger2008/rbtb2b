<html>
  <head>
    <title>会员级别管理</title>
  </head>
  <body>

<@s.form action="/admin_Memberlevel_list.action" method="post">

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 会员级别管理 
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Memberlevel_add.action','');">添加级别</a></li>
   </ul>

 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td">会员级别ID</td>
    <td width="20%" align="center" class="top_td">会员级别名称</td>
    <td width="40%" align="center" class="top_td">备注</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list memberlevelList as level>

    <tr bgcolor="<#if level_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td align="center">${level.level_id?if_exists}</td>
    <td align="center">${level.level_name?if_exists}</td>
    <td align="center">
       <#if  level.remark?length   lt   20  >   ${level.remark} <#else> ${level.remark[0..20]}</#if> 
    </td>
    <td align="center">
      <a onclick="linkToInfo('/admin_Memberlevel_view.action','memberlevel.level_id=${level.level_id?if_exists}');" title="修改"><img src="/include/images/bj.gif" /></a>
      <#if level.syslevel?if_exists=='1'>
       <a href="javascript:delOneInfo('memberlevel.level_id','/admin_Memberlevel_delete.action','${level.level_id?if_exists}');" ><img src="/include/images/admin/delete.png" /></a></td>
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
		<td align="right">级别名称:</td>
		<td><@s.textfield name="level_name_s"/></td>
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