<html>
<head>
<title>我的举报</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Memberreport_list.action" method="post">
  <div class="cont_main">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员信息>投诉举报>我的举报</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a href="/member_Memberreport_add.action">添加举报</a></li>
       <li><a onclick="delInfo('memberreport.report_id','/member_Memberreport_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberreport.report_id')"/></td>
    <td width="20%"  align="center" class="top_td">举报地址</td>
    <td width="10%"  align="center" class="top_td">举报类型</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">编辑</td>
    </tr>
   <#list memberreportList as report>
  <tr>
    <td><input type="checkbox" name="memberreport.report_id" value="${report.report_id?if_exists}"/></td>
    <td align="center">
    <#if report.link_url?if_exists!=''>
    <#if report.link_url?length lt 30>
    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url?if_exists}</a>
    <#else>
    <a href="${report.link_url?if_exists}" title="${report.link_url?if_exists}" target="_blank">${report.link_url[0..29]}</a>
    </#if>
    </#if></td>
    <td align="center">${report.model_value?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Memberreport_list.action',''info_state_s=${report.info_state?if_exists});">
	    <#if report.info_state?if_exists=='0'><span class="redcolor">等待处理</span></#if>
	    <#if report.info_state?if_exists=='1'><span class="greencolor">受理中</span></#if>
	    <#if report.info_state?if_exists=='2'><span class="bluecolor">已撤销</span></#if>
	    <#if report.info_state?if_exists=='3'><span class="orangecolor">已完结</span></#if>
	 </a>
    </td> 
     <td align="center">${report.in_date?if_exists}</td>
    <td align="center"><#if report.info_state?if_exists=="0"><a href="/member_Memberreport_view.action?memberreport.report_id=${report.report_id?if_exists}" class="xg">修改</a>
    <#else>
    <#if report.info_state?if_exists=="1">处理中…</#if>
    <#if report.info_state?if_exists=="2">已撤销</#if>
    <#if report.info_state?if_exists=="3"><a href="/member_Memberreport_audit.action?memberreport.report_id=${report.report_id?if_exists}">查看结果</a></#if>
    </#if>
    <a href="javascript:delOneInfo('memberreport.report_id','/member_Memberreport_delete.action','${(report.report_id)?if_exists}');" class="dele">删除</a></td>
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

<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>
</body>
</html>
