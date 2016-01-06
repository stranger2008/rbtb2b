<html>
  <head>
    <title>会员信息</title>
    <script type="text/javascript">  
       function selectvalue(id){
         opener.setid(id);
         window.close();
        }
</script>
  </head>
  <body>
<@s.form action="/admin_Advinfo_indexinfo.action" method="post">
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td">ID</td>
    <td width="20%"  align="center" class="top_td">信息名称</td>
    <td width="20%"  align="center" class="top_td">选择</td>
  </tr>
  
  <#list intrList as intr>
  <tr>
    <td align="center">${intr.id?if_exists}</td>
    <td align="center">${intr.title?if_exists}</td>
    <td align="center"><input type="button" name="info_id" value="选择" onclick="selectvalue('${intr.id?if_exists}');"/></td>
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
		<td class="table_name" align="right">信息名称:</td>
		<td><@s.textfield name="title_s" maxLength="20"/></td>
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