<html>
<head>
<title>会员求购管理</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	 
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	 <script type="text/javascript">
		$(document).ready(function(){
	    	 //所属分类的回选
	         cate_select(${cfg_topcatid?if_exists},1,"buy");           	    
		});
	</script>	
</head>
<body>
<@s.form action="/member_Buy_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>商情管理>求购管理>求购列表</td>
 </tr>
</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Buy_cate.action','');" >添加求购</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('buy.buy_id','/member_Buy_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buy.buy_id')"/>&nbsp;</td>
    <td width="20%" align="center" class="top_td">求购标题</td>
    <td width="10%" align="center" class="top_td">求购类型</td>
    <td width="15%" align="center" class="top_td">所属分类</td>    
    <td width="10%" align="center" class="top_td">审核状态</td>
    <td width="10%" align="center" class="top_td">发布时间</td>
    <td width="15%" align="center" class="top_td">编辑</td>
  </tr>
  <#list buyList as buy>
    <tr bgcolor="<#if buy_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="buy.buy_id" value="${buy.buy_id?if_exists}"/></td>
    <td align="left">
    <#if buy.title?if_exists!=''>
        <a onclick="linkToInfo('/member_Buy_view.action','buy.buy_id=${buy.buy_id?if_exists}');">
	    <#if buy.title?length lt 12>
	       ${buy.title?if_exists}</a>
	    <#else>
	     ${buy.title[0..11]}...</a>
	    </#if>
    </#if>
    <td align="center">
        <a onclick="linkToInfo('/member_Buy_list.action','type_s=${buy.buy_type?if_exists}');">
    ${buy.model_value?if_exists}</a></td>
    <td align="center">${buy.cat_attr?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Buy_list.action','state_s=${buy.info_state?if_exists}');">
	    <#if buy.info_state='0'>
	     	 <font class="redcolor">未审核</font></a>
	    <#elseif buy.info_state='1'>
	     	<font class="greencolor">正常</font></a>
	    <#elseif buy.info_state='2'>
	    	<font class="bluecolor">未通过</font></a>
	    <#else>
	    	<font class="orangecolor"> 禁用</font></a>
	    </#if>   
    </td>
    <td align="center">${buy.in_date?if_exists}</td>
    <td align="center"><a  href="###"  onclick="linkToInfo('/member_Buy_view.action','buy.buy_id=${buy.buy_id?if_exists}');" class="xg">修改</a>
    <a href="javascript:delOneInfo('buy.buy_id','/member_Buy_delete.action','${(buy.buy_id)?if_exists}');" class="dele">删除</a></td>
  </tr>
</#list> 
    </table>
<div class="listbottom">
   ${pageBar?if_exists}
</div>
 
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right" style="width:90px;">求购标题:</td>
		<td><@s.textfield name="title_s" cssStyle="width:210px;"/></td>
	</tr>
	<tr>
		<td align="right">求购类型:</td>      
		<td><@s.select name="type_s" list="commparalist"  listKey="para_value" listValue="para_key" headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
    <td align="right">审核状态:</td>
	<td>
		    <@s.select name="state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}" />
		</td>
	</tr>
	<tr>
     <td align="right" >发布时间:</td>
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>&nbsp;
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
