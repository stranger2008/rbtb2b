<html>
  <head>
    <title>电子邮件管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Emailhistory_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 电子邮件管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Emailhistory_add.action','');">发送邮件</a></li>
     <li class="guanli"><a onclick="linkToInfo('/admin_Emailtemplate_list.action','');">邮件模板</a></li>
     
     <li class="sc"><a onclick="delInfo('emailhistory.trade_id','/admin_Emailhistory_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('emailhistory.trade_id')"/></td>
    <td width="15%"  align="center" class="top_td">邮件标题</td>
    <td width="15%"  align="center" class="top_td">收件人邮箱</td>
    <td width="15%"  align="center" class="top_td">邮件模板</td>
    <td width="10%"  align="center" class="top_td">发送时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
  
  <#list emailhistoryList as emailhistory>
  <tr>
    <td><input type="checkbox" name="emailhistory.trade_id" value="${emailhistory.trade_id?if_exists}"/></td>
    <td align="center">
    <#if emailhistory.title?if_exists!=''>
    <#if emailhistory.title?length lt 20>
    <a onclick="linkToInfo('/admin_Emailhistory_view.action','emailhistory.trade_id=${emailhistory.trade_id?if_exists}');">${emailhistory.title?if_exists}</a>
    <#else>
    <a onclick="linkToInfo('/admin_Emailhistory_view.action','emailhistory.trade_id=${emailhistory.trade_id?if_exists}');">${emailhistory.title[0..19]}</a>
    </#if>
    </#if>
    </td>
    <td align="center">
    <#if emailhistory.get_email?if_exists!=''>
	    <#if emailhistory.get_email?length lt 31>
	    ${emailhistory.get_email?if_exists}
	    <#else>
	    ${emailhistory.get_email[0..30]}...
	    </#if>
    </#if>
    </td>
    <td align="center">
	<a onclick="linkToInfo('/admin_Emailhistory_view.action','emailhistory.trade_id=${emailhistory.trade_id?if_exists}');">${emailhistory.temp_name?if_exists}</a>
    </td>
    <td align="center">${emailhistory.send_date.toString().substring(0,10)?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Emailhistory_view.action','emailhistory.trade_id=${emailhistory.trade_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>
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
		<td align="right">邮件标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
         <td align="right" >模板类型:</td>
         <td>
         <@s.select name="temp_id_s" list="emailtemplateList" listValue="temp_name" listKey="temp_id" headerKey="" headerValue="请选择" />
         <@s.fielderror><@s.param>link.link_group</@s.param></@s.fielderror>
         </td>
    </tr>
    <tr>
    	<td align="right">时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />    

          至

           <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
        </td>
    </tr>
	<tr>
		<td align="center" colspan="2" style="border:1px;">
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