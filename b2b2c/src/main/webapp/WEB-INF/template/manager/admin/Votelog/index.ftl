
<html>
  <head>
    <title>在线调查记录管理</title>
  </head>
  <body>



<@s.form action="/admin_Votelog_list.action" method="post">
<@s.hidden name="vote_id" id="vote_id"/>
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('vote_log.log_id','/admin_Votelog_delete.action')">删除</a></li>
     <li class="ret"><a onclick="linkToInfo('/admin_Vote_list.action','');">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('vote_log.log_id')"/></td>
    <td width="20%" align="center" class="top_td">在线调查名称</td>
    <td width="20%"  align="center" class="top_td">ip地址</td>
    <td width="20%" align="center" class="top_td">选项内容</td>
    <td width="20%" align="center" class="top_td">投票时间</td>
  </tr>
  
  <#list vote_logList as vote_log>
  <tr>
    <td><input type="checkbox" name="vote_log.log_id" value="${vote_log.log_id?if_exists}"/></td>
    <td align="center">${vote_log.vote_title?if_exists}</td>
    <td align="center">${vote_log.ip_addr?if_exists}</td>
    <td align="center">${vote_log.option_attr?if_exists}</td>
    <td align="center">${vote_log.log_date?if_exists}</td>
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
		<td align="right">在线调查名称:</td>
		<td><@s.textfield name="vote_name_s"/></td>
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