<html>
  <head>
    <title>会员商机收藏</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
 <body>
     
<@s.form action="/member_Collect_list.action" method="post">
  <div class="cont_main">
  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商机管理>商机收藏>商机收藏列表</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
   <ul >
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Collect_add.action','');">添加收藏</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('collect.coll_id','/member_Collect_delete.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('collect.coll_id')"/></td>
    <td width="15%" align="center" class="top_td">信息标题</td>
    <td width="15%"  align="center" class="top_td">链接地址</td>
    <td width="10%"  align="center" class="top_td">分类</td>
    <td width="10%"  align="center" class="top_td">添加时间</td>
    <td width="15%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list collectList as collect>
  <tr>
    <td><input type="checkbox" name="collect.coll_id" value="${collect.coll_id?if_exists}"/></td>
    <td align="center">
    <a onclick=linkToInfo("/member_Collect_view.action","collect.coll_id=${(collect.coll_id)?if_exists}");>
	    	<#if collect.title?if_exists !=''>
			    <#if collect.title?length lt 8>
			    ${collect.title?if_exists}
			    <#else>
			    ${collect.title[0..7]}...
			    </#if>
			</#if>
		</a>
    </td>
    <td align="center">
    	<a href="${collect.link_url?if_exists}" target="_blank">${collect.link_url?if_exists}</a>
    </td>
    <td align="center"><a onclick="document.forms[0].cat_id_s.value=${collect.cat_id?if_exists};document.forms[0].submit();">${collect.cat_name?if_exists}</a></td>
    <td align="center">${collect.in_date.toString().substring(0,10)?if_exists}</td>
    <td align="center">
    <a class="xg" onclick=linkToInfo("/member_Collect_view.action","collect.coll_id=${(collect.coll_id)?if_exists}"); >修改</a>
    <a href="javascript:delOneInfo('collect.coll_id','/member_Collect_delete.action','${(collect.coll_id)?if_exists}');" class="dele">删除</a></td>
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
		<td align="right" width="10%">标题名称:</td>
		<td>
			<@s.textfield name="title_s"/>
		</td>
	</tr>
	<tr>
	     <td align="right">分类:</td>
	     <td>
	     	<@s.select name="cat_id_s" list="membercatList" listValue="cat_name" listKey="cat_id" headerKey="" headerValue="请选择" />
	     </td>
   	</tr>
	<tr>
    	<td align="right" >时间段:</td>
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