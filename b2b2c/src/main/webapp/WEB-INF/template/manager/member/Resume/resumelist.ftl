<html>
<head>
<title>简历管理</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
 <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
      <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"resume");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
</head>
<body>
<@s.form action="/member_Resume_resumeSearch.action" method="post">
  <div class="cont_main">
    <table width="100%" class="cont_title">
		<tr>
			<td>简历搜索结果:</td>
		</tr>
    </table>
   <#if resumeList?exists && (resumeList.size() > 0) >	
   <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="15%" align="center" class="top_td">简历名称</td>
    <td width="15%" align="center" class="top_td">姓名</td>
    <td width="15%" align="center" class="top_td">专业</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
   </tr>
    <#list resumeList as sysresume>
    <#assign rbttime='${(sysresume.in_date)?string("yyyy-MM-dd")}'/>
  <tr bgcolor="#FFFFFF">
    <td align="left">
    &nbsp;&nbsp;<#if sysresume.resume_name?if_exists!=''>
       <a a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('resume','${sysresume.resume_id?
       if_exists}','${rbttime?if_exists}')")}" target="_blank">
    <#if sysresume.resume_name?length lt 10>
        ${sysresume.resume_name?if_exists}
    <#else>
        ${sysresume.resume_name[0..9]}
   </#if>
   </a>
   </#if>
    </td>
    <td align="center">${sysresume.real_name?if_exists}</td>
    <td align="center">${sysresume.spec?if_exists}</td>
    <td align="center">${sysresume.in_date?if_exists}</td>
  </tr>
    </#list>
    </table>
   <div class="listbottom">
      ${pageBar?if_exists}
   </div>
   <#else>
   <table width="100%" class="cont_title" style="height:50px;">
 	<tr>
    	<td align="center">暂无数据</td>
 	</tr>
	</table>
	</#if>
 <div class="clear"></div>
 </div>

</div>
<div class="clear"></div>
<!--搜索区域开始-->

<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<!--搜索区域结束-->

</@s.form>
</body>
</html>

