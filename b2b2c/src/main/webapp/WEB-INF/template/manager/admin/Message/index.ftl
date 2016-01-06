<html>
  <head>
    <title>网站留言管理</title>
  </head>
  <body>



<@s.form action="/admin_Message_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 网站留言管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Message_add.action','');">网站留言</a></li>
     <li class="sc"><a onclick="delInfo('message.mess_id','/admin_Message_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('message.mess_id')"/></td>
    <td width="10%" align="center" class="top_td">留言主题</td>
    <!--<td width="10%"  align="center" class="top_td">留言内容</td>-->
    <td width="10%"  align="center" class="top_td">联系人</td>
    <td width="10%"  align="center" class="top_td">联系电话</td>
    <td width="10%"  align="center" class="top_td">电子邮件</td>
    <td width="10%"  align="center" class="top_td">QQ</td>
    <td width="10%"  align="center" class="top_td">MSN</td>
    <td width="10%"  align="center" class="top_td">Skype</td>
    <td width="10%"  align="center" class="top_td">留言时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
  
  <#list messageList as message>
  <tr>
    <td><input type="checkbox" name="message.mess_id" value="${message.mess_id?if_exists}"/></td>
    <td align="center">${message.title?if_exists}</td>
    <!--<td align="center">${message.content?if_exists}</td>-->
	<td align="center">${message.name?if_exists}</td>
	<td align="center">${message.phone?if_exists}</td>
	<td align="center">${message.email?if_exists}</td>
	<td align="center">${message.qq?if_exists}</td>
	<td align="center">${message.msn?if_exists}</td>
	<td align="center">${message.skype?if_exists}</td>
	<td align="center">${message.in_date.toString().substring(0,10)?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Message_view.action','message.mess_id=${message.mess_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
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
		<td align="right">留言主题:</td>
		<td><@s.textfield name="mess_title_s"/></td>
	</tr>
	<tr>
		<td align="right">联系人:</td>
		<td><@s.textfield name="mess_name_s"/></td>
	</tr>
	<tr>
		<td align="right">联系电话:</td>
		<td><@s.textfield name="mess_phone_s"/></td>
	</tr>
	<tr>
		<td align="right">电子邮件:</td>
		<td><@s.textfield name="mess_email_s"/></td>
	</tr>
	<tr>
		<td align="right">QQ:</td>
		<td><@s.textfield name="mess_qq_s"/></td>
	</tr>
	<tr>
		<td align="right">MSN:</td>
		<td><@s.textfield name="mess_msn_s"/></td>
	</tr>
	<tr>
		<td align="right">Skype:</td>
		<td><@s.textfield name="mess_skype_s"/></td>
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