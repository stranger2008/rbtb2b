<html>
  <head>
    <title>信用指数历史记录</title>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Credithistory_list.action" method="post">
<@s.hidden name="download.is_recom" id="admin_download_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 信用指数历史记录
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('credithistory.trade_id','/admin_Credithistory_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('credithistory.trade_id')"/></td>
    <td width="30%" align="center" class="top_td">会员名称</td>
    <td width="30%"  align="center" class="top_td">指数理由</td>
    <td width="10%"  align="center" class="top_td">指数值</td>
    <td width="10%"  align="center" class="top_td">现有指数</td>
    <td width="17%"  align="center" class="top_td">获得时间</td>
  </tr>
  
  <#list credithistoryList as credithistory>
  <tr  bgcolor="<#if credithistory_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="credithistory.trade_id" value="${credithistory.trade_id?if_exists}"/></td>
    <td align="center">${credithistory.cust_name?if_exists}</td>
    <td align="center">${credithistory.reason?if_exists}</td>  
    <td align="center">${credithistory.c_num?if_exists}</td>   
    <td align="center">${credithistory.now_num?if_exists}</td>
    <td align="center">${credithistory.in_date?if_exists}</td>
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
			<td align="right" width="30%">会员名称:</td>
			<td><@s.textfield name="cust_name_s"/></td>
		</tr>	
			<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
</@s.form>

  </body>
</html>