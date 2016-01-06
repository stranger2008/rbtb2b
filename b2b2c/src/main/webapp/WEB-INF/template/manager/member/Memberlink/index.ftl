<html>
<head>
<title>链接管理</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Memberlink_list.action" method="post">
  <div class="cont_main">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商铺管理>友情链接>链接管理</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Memberlink_add.action','');">添加链接</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('memberlink.cert_id','/member_Memberlink_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
   <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberlink.cert_id')"/></td>
    <td width="20%"  align="center" class="top_td">链接名称</td>
    <td width="10%"  align="center" class="top_td">链接地址</td>
    <td width="10%"  align="center" class="top_td">链接状态</td>
    <td width="20%"  align="center" class="top_td">添加时间</td>
    <td width="17%" align="center" class="top_td">编辑</td>
  </tr>
   <#list memberlinkList as link>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="memberlink.cert_id" value="${link.cert_id?if_exists}"/></td>
    <td align="left">
    	<#if link.title?if_exists !='' >
    	  <a onclick="linkToInfo('/member_Memberlink_view.action','memberlink.cert_id=${link.cert_id?if_exists}');">
    		<#if link.title?length lt 21>${link.title?if_exists}<#else>${link.title[0..20]}..</#if>
    	  </a>
    	</#if>
    </td>
    <td align="center">${link.link_url?if_exists}</td>
    <td align="center">
       <a href="/member_Memberlink_list.action?link_state_s=${link.link_state?if_exists}">
         <#if link.link_state=='0'><span class="greencolor">正常</span>
          <#elseif link.link_state=='1'><span class="redcolor">未审核</span>
          <#else><span class="bluecolor">未通过</span>
          </#if>
       </a>
    </td>
     <td align="center"> 
    	<#if link.in_date?if_exists?string !='' >
    		${link.in_date?if_exists} 
    	</#if> 
    </td>
    <td align="center">
    <a onclick="linkToInfo('/member_Memberlink_view.action','memberlink.cert_id=${link.cert_id?if_exists}');" class="xg">修改</a>
    <a onclick="delOneInfo('memberlink.cert_id','/member_Memberlink_delete.action','${link.cert_id?if_exists}')" class="dele">删除</a>
    </td>
  </tr>
    </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">链接名称:</td>
		<td><@s.textfield name="title_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">链接地址:</td>
		<td><@s.textfield name="link_url_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">链接状态:</td>
		<td><@s.select name="link_state_s" list=r"#{'':'请选择','0':'正常','1':'未审核','2':'未通过'}"/></td>
	</tr>
	<tr>
		<td align="right">添加时间:</td>	
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		
		
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
