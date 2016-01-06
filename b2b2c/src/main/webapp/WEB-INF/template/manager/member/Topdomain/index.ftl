<html>
<head>
<title>顶级域名管理</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Topdomain_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>商店设置>顶级域名绑定</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a href="/member_Topdomain_add.action">绑定域名</a></li>
       <li><a onclick="delInfo('topdomain.inbox_id','/member_Topdomain_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
      <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('topmain.inbox_id')"/></td>
    <td width="30%"  align="center" class="top_td">域名地址</td>
    <td width="10%"  align="center" class="top_td">是否有效</td>
    <td width="10%"  align="center" class="top_td">申请时间</td>
    <td width="5%" align="center" class="top_td">修改</td>
    </tr>
   <#list topdomainList as topdomain>
  <tr>
    <td><input type="checkbox" name="topdomain.inbox_id" value="${topdomain.inbox_id?if_exists}"/></td>
    
    <td align="center">${topdomain.resume_id?if_exists}</td>
    <td align="center">    
	<a onclick="linkToInfo('/member_Topdomain_list.action','enabled_s=${topdomain.enabled?if_exists}');">
    <#if topdomain.enabled?if_exists=='1'>
    	<font color='green'>否</font></a>
    <#else>
    	<font color='red'>是</font></a>
    </#if>
    </td>
    <td align="center">${topdomain.in_date.toString().substring(0,10)?if_exists}</td>
    <td align="center">    
	<a onclick="linkToInfo('/member_Topdomain_view.action','topdomain.inbox_id=${topdomain.inbox_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">客户名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
    <tr>
    <td class=="table_name" >时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
