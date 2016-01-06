<html>
  <head>
    <title>禁用IP管理</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
   <script type="text/javascript" src="/include/js/admin/banip.js"></script>
   <script type="text/javascript">
    
   </script>
  </head>
  <body>
<@s.form action="/admin_BanIp_list.action" method="post" validate="true" >
<@s.hidden name="admin_ip" id="adminip"/>
<@s.hidden name="admin_ip_id" id="admin_ipid"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统工具 > 禁止IP管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
      <li class="tj"><a onclick="showSearch(this,'addDiv');">添加IP段</a></li>
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('banip.ip_id','/admin_BanIp_delete.action')">删除</a></li>
     <li class="xg"><a onclick="updatebanip('/admin_BanIp_updateAllIP.action');">批量更新</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('banip.ip_id')"/></td>
    <td align="left" class="top_td">&nbsp;&nbsp;IP段地址</td>
    <!--
    <td width="15%"  align="center" class="top_td">操作人</td>
    <td width="15%"  align="center" class="top_td">操作时间</td>
    -->
  </tr>
  
  <#list banipList as ban_ips>

    <tr bgcolor="<#if ban_ips_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="banip.ip_id" value="${ban_ips.ip_id?if_exists}"/></td>
    <td align="left">&nbsp;&nbsp;
     <@s.textfield name="banip.ip" id="${ban_ips.ip_id?if_exists}"  onblur="checkupdateIp(this);" onfocus="tipsget(this);"
     value="${ban_ips.ip?if_exists}" cssStyle="width:300px;"  cssClass="txtinput" maxLength="60"/>
     <img id="imes_${ban_ips.ip_id?if_exists}" src="/include/images/errors.png" style="display: none;" />
     <br/>
     <span id="tip_${ban_ips.ip_id?if_exists}" style="display: none;color:blue;">*(IP段的格式为：0.0.0.0-255.255.255.255)</span>
     <@s.fielderror><@s.param>banip.ip</@s.param></@s.fielderror></td>
     <!--
    <td align="center">${ban_ips.user_name?if_exists}</td>
    <td align="center">${ban_ips.in_date?if_exists}</td>
     -->
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
		<td align="right">IP地址:</td>
		<td><@s.textfield name="ip_s"/></td>
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
<!--添加区域开始-->
<div class="divceng" style="display:none;" id="addDiv">
  <@s.form action="/admin_BanIp_insert.action" method="post"  validate="true" onsubmit="return checkip('ipname','ipnameend');" >
	<table align="center">
	  <tr>
             <td class="table_name">IP地址:</td>
             <td>
             	<@s.textfield name="ipname" id="ipname"  />-<@s.textfield name="ipnameend" id="ipnameend" />
             </td>
      </tr>
      <tr> 
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <td><@s.submit value="保存"  /></td>
	       <td><input type="button" name="close" value="关闭" onclick="closeSearch('addDiv')"/></td>
	  </tr>
	</table>
	</@s.form>
</div>
<!--添加区域结束-->
  </body>
</html>
