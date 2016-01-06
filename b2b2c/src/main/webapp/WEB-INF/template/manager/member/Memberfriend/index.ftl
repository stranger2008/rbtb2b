<html>
<head>
<title>商友管理</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
<style>
	.td1{
		text-align:right;
	}
</style>
</head>
<body>
<@s.form action="/member_Memberfriend_list.action" method="post">
  <div class="cont_main">
  	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商机信息>我的商友>商友管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a  href="###" onclick="linkToInfo('/member_Memberfriend_add.action','');" >添加商友</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('memberfriend.info_id','/member_Memberfriend_delete.action')">删除</a></li>
       <li><a href="/member_Membercat_list.action?membertype=2">分类管理</a></li>
       </ul>
    </div>
   <#list memberfriendList as memberfriend>
       <table width="100%" cellspacing="1" cellpadding="0" style="background:white;">
       <tr>
       		<td align="left" colspan="4">
       			<input type="checkbox" name="memberfriend.info_id" value="${memberfriend.info_id?if_exists}"/>
       			<b>${memberfriend.cust_name?if_exists}</b>
       			<a  href="###" onclick="linkToInfo('/member_Memberfriend_view.action','memberfriend.info_id=${memberfriend.info_id?if_exists}');" class="xg">修改</a>
      			<a style="margin-right:10px;" href="javascript:delOneInfo('memberfriend.info_id','/member_Memberfriend_delete.action','${(memberfriend.info_id)?if_exists}');" class="dele">删除</a>
       		</td>
       </tr>
       <tr><td width="10%" class="td1">姓名:</td><td width="20%" class="td2">${memberfriend.name?if_exists}</td>
       		<td width="27%" class="td1">商友分类:</td><td class="td2">${memberfriend.cat_name?if_exists}</td>
       </tr>
       <tr><td class="td1">职位:</td><td class="td2">${memberfriend.career?if_exists}</td><td class="td1">e_mail:</td><td class="td2">${memberfriend.email?if_exists}</td></tr>
       <tr><td class="td1">电话:</td><td class="td2">${memberfriend.phone?if_exists}</td><td class="td1">添加时间:</td><td class="td2">${memberfriend.in_date?if_exists}</td></tr>
       <tr><td class="td1">手机:</td><td class="td2">${memberfriend.cellphone?if_exists}</td><td class="td1"></td><td class="td2"></td></tr>
       
       </table>
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
		<td align="right">公司名称:</td>
		<td><@s.textfield name="cust_name_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">姓名:</td>
		<td><@s.textfield name="name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">所属类别:</td>
		<td><@s.select name="cate_s" list="membercat" listValue="cat_name" listKey="cat_id" headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
		<td align="right">手机:</td>
		<td><@s.textfield name="cellphone_s" maxLength="20"/></td>
	</tr>
     <td  align="right" >发布时间:</td>
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
