<html>
  <head>
    <title>评论管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Membercomment_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 评论管理 
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('membercomment.comm_id','/admin_Membercomment_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('membercomment.comm_id')"/></td>
    <td width="10%" align="center" class="top_td">信息标题</td>
    <td width="10%"  align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">IP地址</td>
    <td width="10%"  align="center" class="top_td">支持数</td>
    <td width="10%"  align="center" class="top_td">反对数</td>
    <td width="10%"  align="center" class="top_td">评分</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">评论时间</td>
    <td width="5%" align="center" class="top_td">回复</td>
  </tr>
  <#list membercommentList as membercomment>
  <tr>
    <td><input type="checkbox" name="membercomment.comm_id" value="${membercomment.comm_id?if_exists}"/></td>
    <td align="center">
    	<a href="${membercomment.info_url?if_exists}">
    		<#if membercomment.info_title?if_exists !='' >
    			<#if membercomment.info_title?length lt 20>
    				${membercomment.info_title?if_exists}
    			<#else>
    				${membercomment.info_title[0..19]}...
    			</#if>
    		</#if>
    </a></td>
    <td align="center">
    <#if membercomment.user_name?if_exists>
    	${membercomment.user_name?if_exists}
    <#else>
    	匿名
    </#if>
    </td>
    <td align="center">${membercomment.ip?if_exists}</td>
    <td align="center">${membercomment.up_num?if_exists}</td>
    <td align="center">${membercomment.down_num?if_exists}</td>
    <td align="center"><#if membercomment.comm_num?if_exists==1>好评</#if><#if membercomment.comm_num?if_exists==0>中评</#if><#if membercomment.comm_num?if_exists==-1>差评</#if></td>
    <td align="center"><#if membercomment.comm_state?if_exists=='0'><a onclick="linkToInfo('/admin_Membercomment_list.action','comm_state_s=${membercomment.comm_state?if_exists}');"><font color='green'>审核通过</font></a><#else><a onclick="linkToInfo('/admin_Membercomment_list.action','comm_state_s=${membercomment.comm_state?if_exists}');"><font color='red'>未审核</font></a></#if> </td>
    <td align="center">${membercomment.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Membercomment_view.action','membercomment.comm_id=${membercomment.comm_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">信息标题:</td>
		<td><@s.textfield name="info_title_s"/></td>
	</tr>
	<tr>
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">IP地址:</td>
		<td><@s.textfield name="IP_s"/></td>
	</tr>
	 <tr>
	<td align="right">信息类型:</td>
	<td><@s.select id="comm_state_s" name="comm_state_s" list=r"#{'0':'审核通过','1':'未审核'}"  headerKey="" headerValue="请选择"/></td>
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