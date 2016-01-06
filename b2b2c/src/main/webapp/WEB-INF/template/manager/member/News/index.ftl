<html>
<head>
<title>资讯管理</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"news");  
	  });
	</script>
</head>
<body>
<@s.form action="/member_News_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>资讯管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_News_cate.action','');">添加资讯</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('news.news_id','/member_News_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
       <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('news.news_id')"/></td>
    <td width="15%" align="center" class="top_td">资讯标题</td>
    <td width="16%" align="center" class="top_td">分类</td>
    <td width="12%" align="center" class="top_td">状态</td>
    <td width="20%" align="center" class="top_td">发布时间</td>
    <td width="15%" align="center" class="top_td">编辑</td>
  </tr>
   <#list newsList as sysnews>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="news.news_id" value="${sysnews.news_id?if_exists}"/></td>
    <td align="center">
    <#if sysnews.title?if_exists!=''>
    <a onclick=linkToInfo("/member_News_view.action","news.news_id=${sysnews.news_id?if_exists}");>
    <#if sysnews.title?length lt 10>
   ${sysnews.title?if_exists}
   <#else>
    ${sysnews.title[0..9]}
   </#if>
   </a>
   </#if>
    </td>
    <td align="center">${sysnews.cat_attr?if_exists}</td>
    <td align="center">
     <#if sysnews.info_state?if_exists='1'>
    <a href="/member_News_list.action?state_code_s=1">
   <font class="greencolor">
    正常
     </font>
   </a>
    <#elseif sysnews.info_state?if_exists='0'>
    <a href="/member_News_list.action?state_code_s=0">
   <font class="redcolor">
    未审核
     </font>
   </a>
    <#elseif sysnews.info_state?if_exists='2'>
    <a href="/member_News_list.action?state_code_s=2">
   <font class="bluecolor">
    未通过
     </font>
   </a>
    <#elseif sysnews.info_state?if_exists='3'>
    <a href="/member_News_list.action?state_code_s=3">
   <font class="bluecolor">
    禁用
     </font>
   </a>
    </#if></td>
    <td align="center">${sysnews.in_date?if_exists}</td>
    <td align="center"><a onclick=linkToInfo("/member_News_view.action","news.news_id=${sysnews.news_id?if_exists}"); class="xg">修改</a>
    <a href="javascript:delOneInfo('news.news_id','/member_News_delete.action','${(sysnews.news_id)?if_exists}');" class="dele">删除</a></td>
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
		<td align="right">资讯标题:</td>
		<td><@s.textfield name="title_s" cssStyle="width:200px;" maxLength="600" /></td>
	</tr>
	<tr>
		<td align="right">分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">状态:</td>
		<td><@s.select name="state_code_s" list=r"#{'4':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}"   /> </td>
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr')"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />

</@s.form>
</body>
</html>
