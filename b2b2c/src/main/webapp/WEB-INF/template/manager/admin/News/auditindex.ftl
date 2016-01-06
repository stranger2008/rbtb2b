<html>
  <head>
    <title>资讯审核列表</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
	 	//所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"news");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_News_auditList.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 资讯管理 > 资讯审核列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('news.news_id','/admin_News_checkDel.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
  	<td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('news.news_id')"/></td>
    <td width="20%"  align="center" class="top_td">资讯标题</td>
    <td width="10%" align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">分类</td>
    <td width="10%"  align="center" class="top_td">地区</td>
    <td width="10%" align="center" class="top_td">资讯状态</td>
    <td width="10%" align="center" class="top_td" >发布时间</td>
     <td width="5%" align="center" class="top_td" >审核</td>
  </tr>
  
  <#list newsList as sysnews>

    <tr bgcolor="<#if sysnews_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="news.news_id" value="${sysnews.news_id?if_exists}"/></td>
     <td align="left" style="padding-left:15px;">
    <#if sysnews.title?if_exists!=''>
    <a onclick=linkToInfo("/admin_News_audit.action","news.news_id=${sysnews.news_id?if_exists}");>
     <#if sysnews.title?length lt 28>
     ${sysnews.title?if_exists}
    <#else>
      ${sysnews.title[0..27]}...
    </#if>
    </a>
   </#if>
    </td>
     <td align="center">
    <#if sysnews.cust_name?if_exists!=''>
    <#if sysnews.cust_name?length lt 18>
   ${sysnews.cust_name?if_exists}
   <#else>
    ${sysnews.cust_name[0..17]}...
   </#if>
   </#if>
    </td>
   <td align="center"  >
       <a onclick="linkToInfo('/admin_News_auditList.action','cat_attr_s=${sysnews.cat_attr_id?if_exists}');">${sysnews.cat_attr?if_exists}</a>
   </td>
   <td align="center">${sysnews.area_attr?if_exists}</td>
   <td align="center" >
     <#if sysnews.info_state?if_exists='0'>
     <a href="/admin_News_auditList.action?state_code_s=0" >
     <span class="redcolor">
     未审核
     </span>
     </a>
     <#elseif sysnews.info_state?if_exists='2'>
     <a href="/admin_News_auditList.action?state_code_s=2" >
     <span class="bluecolor">
     未通过
     </span>
     </a>
   </#if></td>  
    <td align="center" >${sysnews.in_date?if_exists}</td>
    <td align="center"><a onclick=linkToInfo("/admin_News_audit.action","news.news_id=${sysnews.news_id?if_exists}");><img src="/include/images/audit.png" /></a></td>
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
		<td align="right">资讯标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">资讯状态:</td>
		<td>
		<@s.select name="state_code_s" list=r"#{'4':'请选择','2':'未通过','0':'未审核'}"   /> </td>
	</tr>
	<tr>
		<td align="right">发布时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="enddateString_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
         </td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
 <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>

<!--搜索区域结束-->

</@s.form>

  </body>
</html>
