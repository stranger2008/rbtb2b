<html>
  <head>
    <title>关于我们管理</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Aboutus_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:网站管理 > 基本功能 > 关于我们
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Aboutus_add.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Aboutus_malladd.action'</#if>,'');">关于我们</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('aboutus.info_id',<#if mall_type?if_exists=='b2b'>'/admin_Aboutus_delete.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Aboutus_malldelete.action'</#if>)">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('aboutus.info_id')"/></td>
    <td width="20%" align="center" class="top_td">关于我们标题</td>
    <td width="20%"  align="center" class="top_td">栏目标识</td>
    <td width="20%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  <#list aboutusList as aboutus>
  <tr>
    <td><input type="checkbox" name="aboutus.info_id" value="${aboutus.info_id?if_exists}"/></td>
    <td align="center">${aboutus.title?if_exists}</td>
    <td align="center"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Aboutus_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Aboutus_malllist.action'</#if>,'aboutus.ch_id=${aboutus.ch_id?if_exists}');">
    ${aboutus.model_value?if_exists}</a></td>
    <td align="center">${aboutus.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Aboutus_view.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Aboutus_mallview.action'</#if>,'aboutus.info_id=${aboutus.info_id?if_exists}');">
    <img src="/include/images/bj.gif" /></a></td>
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
<@s.hidden name="mall_type" />
<div class="clear"></div>
<!--搜索区域开始-->
<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">关于我们标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
             <td align="right">关键字类型:</td>
             <td>
             <@s.select name="ch_id_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
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