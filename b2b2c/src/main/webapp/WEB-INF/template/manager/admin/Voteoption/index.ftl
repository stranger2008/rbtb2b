
<html>
  <head>
    <title>在线调查选项管理</title>
  </head>
  <body>
<@s.form action="/admin_Voteoption_list.action" method="post" >
<@s.hidden name="vote_id" id="vote_id"/>
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Voteoption_add.action','vote_id=${vote_id?if_exists}');">添加选项</a></li>
     
     <li class="sc"><a onclick="delInfo('vote_option.option_id','/admin_Voteoption_delete.action')">删除</a></li>
     <li class="ret"><a onclick="linkToInfo('/admin_Vote_list.action','');">返回</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('vote_option.option_id')"/></td>
    <td width="20%" align="center" class="top_td">选项名称</td>
    <td width="15%"  align="center" class="top_td">投票数</td>
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list vote_optionList as vote_option>
  <tr>
    <td><input type="checkbox" name="vote_option.option_id" value="${vote_option.option_id?if_exists}"/></td>
    <td align="center">${vote_option.option_name?if_exists}</td>
    <td align="center">${vote_option.option_count?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Voteoption_view.action','vote_option.option_id=${vote_option.option_id?if_exists}&vote_id=${vote_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">选项名称:</td>
		<td><@s.textfield name="option_name_s"/></td>
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