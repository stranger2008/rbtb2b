<html>
  <head>
    <title>会员资金管理</title>
  </head>
<body>
<@s.form action="/admin_Memberinter_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 会员积分管理表 
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Interhistory_list.action','');">查看积分流</a></li>
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="40%" align="center" class="top_td">会员名称</td>
    <td width="20%"  align="center" class="top_td">总积分</td>
    <td width="20%"  align="center" class="top_td">积分修改</td>
    <td width="20%"  align="center" class="top_td">查看积分变化情况</td>
  </tr>
  
  <#list memberinterList as memberinter>
  <tr>
    <td align="center">
    
    
    <#if memberinter.cust_name?if_exists != ''>
    
	    <#if memberinter.cust_name?length lt 33>
	    ${memberinter.cust_name?if_exists}
	    <#else>
	    ${memberinter.cust_name[0..32]}...
	    </#if>
    
    </#if>
    </td>
    <td align="center">${memberinter.fund_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberinter_view.action','cust_id=${memberinter.cust_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Interhistory_list.action','cust_id_s=${memberinter.cust_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
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
		<td><@s.textfield name="memberinter_name_s"/></td>
	</tr>
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