<html>
<head>
<title>会员自定义分类管理</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
 <script type="text/javascript" src="/include/js/member/membercat.js"></script>
</head>
<body>
<@s.form action="/member_Membercat_list.action" method="post">

<@s.hidden name="admin_cat_id" id="admin_cat_id"/>
<@s.hidden name="member_sort_no" id="member_sort_no"/>
<@s.hidden name="member_cat_id" id="member_cat_id"/>
<@s.hidden name="member_cat_name" id="member_cat_name"/>
<@s.hidden name="admin_sort_no" id="admin_sort_no"/>
<@s.hidden name="membertype"/>
  <div class="cont_main">
  <table width="100%" class="cont_title">
 	<tr>
 		<!--0：产品 1：收藏 2：商友-->
    	<td>当前位置:
    		<#if membertype == '0'>
    			产品管理>产品分类
    		</#if>
    		<#if membertype == '1'>
    			商机快递>收藏分类
    		</#if>
    		<#if membertype == '2'>
    			我的商友>商友分类
    		</#if>
    	</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="showSearch(this,'addCatDiv');">添加分类</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="updateSortNo();">排序</a></li>
       <li><a onclick="updatemembercats('/member_Membercat_updateAllMembercat.action');">修改</a></li>
       <li><a onclick="delInfo('membercat.cat_id','/member_Membercat_delete.action?membertype=${membertype?if_exists}');">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
              <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('membercat.cat_id')"/></td>
    <td width="15%" align="center" class="top_td">排序</td>
    <td align="center" class="top_td">分类名称</td>
  </tr>
   <#list membercatList as sysmembercat>
  <tr bgcolor="#FFFFFF">
    <td><input type="checkbox" name="membercat.cat_id" value="${sysmembercat.cat_id?if_exists}"/></td>
    <td align="center"> <@s.textfield onchange="checkNum(this)" onkeyup="checkNum(this)"  name="membercat.sort_no" id="${sysmembercat.cat_id?if_exists}" value="${sysmembercat.sort_no?if_exists}" cssClass="txtinput" maxLength="9" size="4"/></td>
    <td align="center">&nbsp;<@s.textfield name="${sysmembercat.cat_id?if_exists}" value="${sysmembercat.cat_name?if_exists}" cssClass="txtinput" maxLength="100" size="30"/></td>
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
		<td align="right">分类名称:</td>
		<td><@s.textfield name="cat_name_s" cssStyle="width:200px;" maxLength="100" /></td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
</@s.form>

<!--搜索区域结束-->
<!--添加分类区域开始-->

<div class="divceng" style="display:none;" id="addCatDiv">
	<@s.form action="/member_Membercat_insert.action" method="post"  validate="true" >
	<@s.hidden name="membertype"/>
	<table align="left">
	
	<tr>
		<td align="right">分类名称:</td>
		<td>
		<@s.textfield name="membercat.cat_name" cssStyle="width:200px;" maxLength="100" />
		<@s.fielderror><@s.param>membercat.cat_name</@s.param></@s.fielderror>   
		</td>
	</tr>
	<tr>
		<td align="right">分类排序:</td>
		<td>
			<@s.textfield  onkeyup="checkNum(this)" name="membercat.sort_no" maxLength="9" value="0"/>*(只能输入数字)
			<@s.fielderror><@s.param>membercat.sort_no</@s.param></@s.fielderror>         
         </td>
	</tr>
	
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="name"/>
	          
	       ${listSearchHiddenField?if_exists}	       
	       <@s.submit value="保存" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('addCatDiv')"/>
		</td>
	</tr>
	</table>
	</@s.form>
</div>

<!--添加分类区域结束-->


</body>
</html>
