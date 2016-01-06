﻿<html>
<head>
	<title>提问管理</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"know");      
	  });
	</script>
</head>
<body>
<@s.form action="/member_Ask_list.action" method="post">
  <div class="cont_main">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:信息管理>知道管理>提问管理</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Ask_cate.action','');">添加提问</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('ask.ask_id','/member_Ask_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
   <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('ask.ask_id')"/></td>
    <td width="15%" align="center" class="top_td">问题标题</td>
    <td width="15%" align="center" class="top_td">所属分类</td>
    <td width="9%" align="center" class="top_td">积分</td>
    <td width="10%" align="center" class="top_td">信息状态</td>
    <td width="10%" align="center" class="top_td">回答状态</td>
    <td width="8%" align="center" class="top_td">回答数</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="20%" align="center" class="top_td">编辑</td>
  </tr>
   <#list askList as ask>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="ask.ask_id" value="${ask.ask_id?if_exists}"/></td>
    <td align="left">
      <a onclick="linkToInfo('/member_Ask_view.action','ask.ask_id=${ask.ask_id?if_exists}');">
       <#if  ask.title?length   lt   8  >   ${ask.title?if_exists} <#else> ${ask.title?if_exists[0..7]}..</#if>
      </a>
    </td>
    <td align="center"> ${ask.cat_attr?if_exists}</td>
    <td align="center">${ask.integral?if_exists}</td>
    <td align="center">
      <a href="/member_Ask_list.action?info_state_s=${ask.info_state?if_exists}">
       <#if ask.info_state=='0'><span class="redcolor">未审核</span>
       <#elseif ask.info_state=='1'><span class="greencolor">正常</span>
       <#elseif ask.info_state=='2'><span class="bluecolor">未通过</span>
       <#else><span class="orangecolor">禁用</span></#if>
      </a>   
    </td>
    <td align="center"> 
     <a href="/member_Ask_list.action?answer_state_s=${ask.answer_state?if_exists}">
     <#if ask.answer_state=='0'>
      <span class="redcolor">未解决</span><#elseif ask.answer_state=='1'>
      <span class="greencolor">已解决</span><#else>
      <span class="bluecolor">已关闭</span>
     </#if>
     </a>
    </td>
    <td align="center"> ${ask.answernum?if_exists}</td>
    <td align="center"> ${ask.in_date?if_exists}</td>
    <td align="center">
    <a href="/member_Answer_isselectlist.action?ask_id_s=${ask.ask_id?if_exists}" >回答</a>
    <a onclick="linkToInfo('/member_Ask_view.action','ask.ask_id=${ask.ask_id?if_exists}');" class="xg">修改</a>
    <a onclick="delOneInfo('ask.ask_id','/member_Ask_delete.action','${ask.ask_id?if_exists}')" class="dele">删除</a>
    </td>
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
		<td align="right">问题标题:</td>
		<td><@s.textfield name="title_s" maxLength="600"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></td>
	</tr>
	<tr>
		<td align="right">悬赏积分:</td>
		<td><@s.select name="integral_s" list="integralList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" /></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}"/></td>
	</tr>
	<tr>
		<td align="right">回答状态:</td>
		<td><@s.select name="answer_state_s" list=r"#{'':'请选择','0':'未解决','1':'已解决','2':'已关闭'}"/></td>
	</tr>
	<tr>
		<td align="right">发布时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		               
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<!--搜索区域结束-->
</@s.form>
</body>
</html>
