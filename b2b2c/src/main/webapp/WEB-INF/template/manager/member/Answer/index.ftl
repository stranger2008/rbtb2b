<html>
<head>
<title>回答管理</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
</head>
<body>
<@s.form action="/member_Answer_list.action" method="post">
  <div class="cont_main">
    <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:信息管理>知道管理>回答管理</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('answer.answer_id','/member_Answer_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
   <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('answer.answer_id')"/></td>
    <td width="20%"  align="center" class="top_td">问题</td>
    <td width="20%"  align="center" class="top_td">答案</td>
    <td width="10%"  align="center" class="top_td">最佳答案</td>
    <td width="10%" align="center" class="top_td">信息状态</td>
    <td width="15%"  align="center" class="top_td">回答时间</td>
    <td width="15%" align="center" class="top_td">编辑</td>
  </tr>
   <#list answerList as ans>

   <tr bgcolor="<#if ans_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="answer.answer_id" value="${ans.answer_id?if_exists}"/></td>
    <td align="center">
       <a href="/member_Answer_list.action?ask_id_s=${ans.ask_id?if_exists}">
        <#if  ans.title?if_exists?length   lt   16  >   ${ans.title?if_exists} <#else> ${ans.title?if_exists[0..15]}..</#if>
       </a>
    </td>
    <td align="center">
    <a  href="###"  onclick="linkToInfo('/member_Answer_view.action','answer.answer_id=${ans.answer_id?if_exists}')">
       <#if  ans.answer_desc?length   lt   16  >   ${ans.answer_desc?if_exists} <#else> ${ans.answer_desc?if_exists[0..15]}..</#if>
     </a>
    </td>
    <td align="center"><#if ans.isselect=='0'>否<#else><span class="greencolor">是</span></#if></td>
    <td align="center">
     <a href="/member_Answer_list.action?info_state_s=${ans.info_state?if_exists}">
      <#if ans.info_state=='0'>
          <span class="redcolor">未审核</span>
         <#else>
          <span class="greencolor">正常</span>
      </#if>
     </a>
    </td>
    <td align="center">${ans.in_date?if_exists}</td>
    <td align="center">
    
      <a  href="###"  onclick="linkToInfo('/member_Answer_view.action','answer.answer_id=${ans.answer_id?if_exists}')">查看</a>
      
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
		<td align="right">问题:</td>
		<td><@s.textfield name="title_s" maxLength="600"/></td>
	</tr>
	<tr>
		<td align="right">答案:</td>
		<td><@s.textfield name="answer_desc_s" maxLength="600"/></td>
	</tr>
	<tr>
		<td align="right">最佳答案:</td>
		<td><@s.select name="isselect_s" list=r"#{'':'请选择','0':'是','1':'否'}"/></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','0':'未审核','1':'正常'}"/></td>
	</tr>
	<tr>
		<td align="right">回答时间:</td>
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
