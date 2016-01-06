<html>
  <head>
    <title>知道列表</title>
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
<@s.form action="/admin_Ask_list.action" method="post">
<@s.hidden name="ask.is_recom" id="admin_ask_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 知道管理 > 知道列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Ask_cate.action','');">添加知道</a></li>
     <li class="sc"><a onclick="delInfo('ask.ask_id','/admin_Ask_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
      <li class="qiyong"><a onclick="updateRecomState('1','ask.ask_id','/admin_Ask_updateisrecom.action','admin_ask_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','ask.ask_id','/admin_Ask_updateisrecom.action','admin_ask_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('ask.ask_id')"/></td>
    <td width="20%"  align="center" class="top_td">问题标题</td>
    <td width="20%" align="center" class="top_td">所属分类</td>
    <td width="10%" align="center" class="top_td">悬赏积分</td>
    <td width="10%" align="center" class="top_td">信息状态</td>
    <td width="10%"  align="center" class="top_td">回答状态</td>
    <td width="5%"  align="center" class="top_td">回答数</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="12%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list askList as ask>

   <tr bgcolor="<#if ask_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="ask.ask_id" value="${ask.ask_id?if_exists}"/></td>
    <td align="left">
      <a onclick="linkToInfo('/admin_Ask_view.action','ask.ask_id=${ask.ask_id?if_exists}');">
       <#if  ask.title?length   lt   16  >   ${ask.title?if_exists} <#else> ${ask.title?if_exists[0..15]}..</#if>
      </a>
      <a href ="/admin_Ask_list.action?is_recom_s=1"><span class="redcolor"><#if ask.is_recom=='1'>[推荐]</#if></span></a>
    </td>
    <td align="center">
	  <a onclick="linkToInfo('/admin_Ask_list.action','cat_attr_s=${ask.cat_attr_id?if_exists}');">${ask.cat_attr?if_exists}</a>
	</td>
    <td align="center">${ask.integral?if_exists}</td>
    <td align="center">
      <a href="/admin_Ask_list.action?info_state_s=${ask.info_state?if_exists}">
      <#if ask.info_state=='1'>
       <span class="greencolor">正常</span>
      <#else>
       <span class="redcolor">禁用</span>
      </#if>
      </a>
    </td>
    <td align="center">
      <a href="/admin_Ask_list.action?answer_state_s=${ask.answer_state?if_exists}">
       <#if ask.answer_state=='0'><span class="bluecolor">未解决</span><#elseif ask.answer_state=='1'><span class="greencolor">已解决</span><#else><span class="redcolor">已关闭</span></#if>
      </a>
    </td>
    <td align="center">${ask.answernum?if_exists}</td>
    <td align="center">${ask.in_date?if_exists}</td>
    <td align="center">
     
     <#if (ask.answer_state)?if_exists=='0'>
       <a onclick="linkToInfo('/admin_Answer_add.action','answer.ask_id=${ask.ask_id?if_exists}&answer.cust_id=${ask.cust_id?if_exists}');" title="添加答案"><img src="/include/images/add.gif" /></a>
     <#else>
       <span style="padding-left:22px;"></span>
     </#if>
     <a href="/admin_Answer_list.action?ask_id_s=${ask.ask_id?if_exists}&two_pages_link=no" title="答案查看"><img src="/include/images/view.gif" /></a>
     
     <a onclick="linkToInfo('/admin_Ask_view.action','ask.ask_id=${ask.ask_id?if_exists}');" title="修改"><img src="/include/images/bj.gif" /></a>
     
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
		<td align="right">问题标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">是否推荐:</td>
		<td><@s.select name="is_recom_s" list=r"#{'':'请选择','0':'否','1':'是'}"/></td>
	</tr>
	<tr>
		<td align="right">悬赏积分:</td>
		<td><@s.select name="integral_s" list="integralList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" /></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','1':'正常','3':'禁用'}"/></td>
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