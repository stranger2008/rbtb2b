<html>
  <head>
    <title>公告列表</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script> 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
 <#if news_mold?if_exists=='1'>
 	 <#assign type = "goodsNewsList">
 <#else>
 	 <#assign type = "groupNewsList">
 </#if>
<@s.form action="/admin_News_${type?if_exists}.action?news_mold=${news_mold?if_exists}" method="post">
<@s.hidden name="news.news_attr" id="admin_news_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 公告管理 >  ${mold?if_exists}公告列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_News_newsinsert.action?news_mold=${news_mold?if_exists}','');">添加公告</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','news.news_id','/admin_News_updateisrecom.action?isrecom=1&news_mold=${news_mold?if_exists}','admin_news_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','news.news_id','/admin_News_updateisrecom.action?news_mold=${news_mold?if_exists}','admin_news_state');">取消推荐</a></li>
     <li class="sc"><a onclick="delInfo('news.news_id','/admin_News_delete.action?news_mold=${news_mold?if_exists}')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('news.news_id')"/></td>
    <td width="20%"  align="center" class="top_td">公告标题</td>
    <td width="7%" align="center" class="top_td">公告状态</td>
    <td width="10%" align="center" class="top_td" >发布时间</td>
    <td width="5%" align="center" class="top_td" >修改</td>
  </tr>
  
  <#list goods_newsList as sysnews>

    <tr bgcolor="<#if sysnews_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="news.news_id" value="${sysnews.news_id?if_exists}"/></td>
    <td align="left">
    <#if sysnews.title?if_exists!=''>
    	<a onclick=linkToInfo("/admin_News_view.action?news_mold=${news_mold?if_exists}","news.news_id=${sysnews.news_id?if_exists}");>
		     <#if sysnews.title?length lt 28>
		     ${sysnews.title?if_exists}
		    <#else>
		      ${sysnews.title[0..27]}...
		    </#if>
	    </a>
   </#if>
   	<#if ((sysnews.news_attr)?index_of("c") > (-1))> 
    	<span class="redcolor">[推荐]</span>
    </#if>
    </td>
    
   <td align="center" >
   <#if sysnews.info_state?if_exists='1'>
    <a href="/admin_News_list.action?state_code_s=1">
   <font class="greencolor">正常</font>
   </a>
    <#elseif sysnews.info_state?if_exists='3'>
     <a href="/admin_News_list.action?state_code_s=3">
   <font class="redcolor">  
     禁用
   </font></a>
   <#elseif sysnews.info_state?if_exists='0'>
   <a href="/admin_News_list.action?state_code_s=0">
   <font class="redcolor">未审核</font>
   </a>
    <#elseif sysnews.info_state?if_exists='2'>
     <a href="/admin_News_list.action?state_code_s=2">
   <font class="bluecolor">  
     未通过
   </font></a>
   </#if>
   </td>  
    <td align="center" >${sysnews.in_date?if_exists}</td>
    <td align="center">
    	<a onclick=linkToInfo("/admin_News_view.action?news_mold=${news_mold?if_exists}","news.news_id=${sysnews.news_id?if_exists}");><img src="/include/images/bj.gif" /></a>
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
		<td align="right">公告标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">公告状态:</td>
		<td>
		<@s.select name="state_code_s" list=r"#{'4':'请选择','1':'正常','3':'禁用'}"   /> </td>
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
