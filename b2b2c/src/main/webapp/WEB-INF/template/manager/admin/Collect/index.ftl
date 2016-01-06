<html>
  <head>
    <title>商机收藏</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
 <body>
<@s.form action="/admin_Collect_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 会员相关 > 商机收藏
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('collect.coll_id','/admin_Collect_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('collect.coll_id')"/></td>
    <td width="20%" align="center" class="top_td">商机标题</td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="20%"  align="center" class="top_td">链接地址</td>
    <td width="15%"  align="center" class="top_td">会员自定义分类</td>
    <td width="15%"  align="center" class="top_td">添加时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list collectList as collect>
  <tr>
    <td><input type="checkbox" name="collect.coll_id" value="${collect.coll_id?if_exists}"/></td>
    <td align="center">
      <#if collect.title?if_exists!=''>
    	<#if collect.title?length lt 20>
    	 <a onclick="linkToInfo('/admin_Collect_view.action','collect.coll_id=${collect.coll_id?if_exists}');">${collect.title?if_exists}</a>
    	<#else>
    	 <a onclick="linkToInfo('/admin_Collect_view.action','collect.coll_id=${collect.coll_id?if_exists}');">${collect.title[0..19]}...</a>
    	</#if>
    	</#if>
    </td>
    <td align="center">
    	${collect.cust_name?if_exists}
    </td>
    <td align="center">
    	<a href="${collect.link_url?if_exists}" target="_blank">${collect.link_url?if_exists}</a>
    </td>
    <td align="center">${collect.cat_name?if_exists}</td>
    <td align="center">${collect.in_date.toString().substring(0,10)?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Collect_view.action','collect.coll_id=${collect.coll_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">商机标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
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