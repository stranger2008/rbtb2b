<html>
  <head>
    <title>荣誉资质管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Membercert_list.action" method="post">

<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 荣誉资质管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Membercert_add.action','');">添加资质</a></li>
     <li class="sc"><a onclick="delInfo('membercert.cert_id','/admin_Membercert_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('membercert.cert_id')"/></td>
    <td width="20%"  align="center" class="top_td">证书标题</td>
    <td width="20%"  align="center" class="top_td">企业名称</td>
    <td width="20%" align="center" class="top_td">发证机构</td>
    <td width="10%"  align="center" class="top_td">证书状态</td>
    <td width="10%"  align="center" class="top_td">添加时间</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list membercertList as cert>

    <tr bgcolor="<#if cert_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="membercert.cert_id" value="${cert.cert_id?if_exists}"/></td>
    <td align="left">
    	<#if cert.title?if_exists !='' >
    	   <a onclick="linkToInfo('/admin_Membercert_view.action','membercert.cert_id=${cert.cert_id?if_exists}');">
    		<#if cert.title?length lt 19>${cert.title?if_exists}<#else>${cert.title[0..18]}..</#if>
    	   </a>
    	</#if>
    </td>
     <td align="center">     
       <a href="/admin_Member_viewinfo.action?member.cust_id=${(cert.cust_id)?if_exists}" target="_blank">    
    	<#if cert.cust_name?if_exists !='' >
    		<#if cert.cust_name?length lt 21>${cert.cust_name?if_exists}<#else>${cert.cust_name[0..20]}..</#if>
    	</#if>
      </a>	
    </td>
    <td align="center">
    	<#if cert.organize?if_exists !='' >
    		<#if cert.organize?length lt 19>${cert.organize?if_exists}<#else>${cert.organize[0..18]}..</#if>
    	</#if>
    </td>
    
    <td align="center">
       <a href="/admin_Membercert_list.action?cert_state_s=${cert.cert_state?if_exists}">
    	<#if cert.cert_state=='0'><span class="greencolor">正常</span>
    	<#elseif cert.cert_state=='1'><span class="redcolor">未审核</span>
    	<#else><span class="bluecolor">未通过</span>
    	</#if>
       </a>	
    </td>
    <td align="center"> 
    	<#if cert.in_date?if_exists?string !='' >
    		${cert.in_date?if_exists} 
    	</#if> 
    </td>
    <td align="center">
     <a onclick="linkToInfo('/admin_Membercert_view.action','membercert.cert_id=${cert.cert_id?if_exists}');" title="修改"><img src="/include/images/bj.gif" /></a>
     <a onclick="linkToInfo('/admin_Membercert_audit.action','membercert.cert_id=${cert.cert_id?if_exists}');" title="审核"><img src="/include/images/audit.png" /></a>
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
		<td align="right">证书标题:</td>
		<td><@s.textfield name="title_s" maxLength="50"/></td>
	</tr>
	<tr>
		<td align="right">企业名称:</td>
		<td><@s.textfield name="cust_name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">发证机构:</td>
		<td><@s.textfield name="organize_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">证书状态:</td>
		<td><@s.select name="cert_state_s" list=r"#{'':'请选择','0':'正常','1':'未审核','2':'未通过'}"/></td>
	</tr>
	<tr>
		<td align="right">到期日期:</td>
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