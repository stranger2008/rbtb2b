<html>
  <head>
    <title>查看资金流</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Fundhistory_list.action" method="post">
<@s.hidden name="cust_id_s" value="${cust_id_s?if_exists}"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	  当前位置:会员管理 > 财务管理 > 查看资金流
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="ret"><a onclick="linkToInfo('/admin_Memberfund_list.action','');">返回</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">收入</td>
    <td width="10%"  align="center" class="top_td">支出</td>
    <td width="10%"  align="center" class="top_td">余额</td>
    <td width="10%"  align="center" class="top_td">操作人</td>
    <td width="10%"  align="center" class="top_td">操作时间</td>
    <td width="15%"  align="center" class="top_td">事由</td>
    <td width="15%"  align="center" class="top_td">备注</td>
  </tr>
  
  <#list fundhistoryList as fundhistory>
  <tr>
    <td align="center">
    <#if fundhistory.cust_name?if_exists!=''>
    <#if fundhistory.cust_name?length lt 20>
    ${fundhistory.cust_name?if_exists}
    <#else>
    ${fundhistory.cust_name[0..19]}...
    </#if>
    </#if>
    </td>
    <td align="center">${fundhistory.fund_in?if_exists}</td>
    <td align="center">${fundhistory.fund_out?if_exists}</td>
    <td align="center">${fundhistory.balance?if_exists}</td>
    <td align="center">${fundhistory.user_name?if_exists}</td>
    <td align="center">${fundhistory.in_date?if_exists}</td>
    <td align="center">${fundhistory.reason?if_exists}</td>
    <td align="center">${fundhistory.remark?if_exists}</td>
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
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
    <tr>
        <td align="right" >时间段:</td>
        <td> 
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />  	
      
        至
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