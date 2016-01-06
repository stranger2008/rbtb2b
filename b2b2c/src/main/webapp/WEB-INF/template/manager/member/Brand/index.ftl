<html>
  <head>
    <title>品牌列表</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"brand");           
	  });
	</script>
  </head>
  <body>
<@s.form action="/member_Brand_list.action" method="post">
<div class="cont_main">
 <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>品牌管理>品牌列表</td>
 </tr>
</table>
  <div class="finder_bar_bg">
   <ul>
     <li class="cont_first_li"><a onclick="linkToInfo('/member_Brand_cate.action','');">添加品牌</a></li>
     <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li><a onclick="delInfo('brand.brand_id','/member_Brand_delete.action')">删除</a></li> 
   </ul>
 </div>
  <table width="100%" border="0" cellspacing="0">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('brand.brand_id')"/></td>
    <td width="20%"  align="center" class="top_td">品牌名称</td>
    <td width="20%" align="center" class="top_td">所属分类</td>
    <td width="10%"  align="center" class="top_td">信息状态</td>
    <td width="15%"  align="center" class="top_td">发布时间</td>
    <td width="15%" align="center" class="top_td">编辑</td>
  </tr>
  
  <#list brandList as brand>

   <tr bgcolor="<#if brand_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="brand.brand_id" value="${(brand.brand_id)?if_exists}"/></td>
    <td align="left">
      <a onclick="linkToInfo('/member_Brand_view.action','brand.brand_id=${(brand.brand_id)?if_exists}');">
       <#if  brand.title?length   lt   10  >   ${brand.title?if_exists} <#else> ${brand.title?if_exists[0..9]}..</#if>
      </a>
    </td>
    <td align="center">${(brand.cat_attr)?if_exists}</td>
    <td align="center">
      <a href="/member_Brand_list.action?info_state_s=${(brand.info_state)?if_exists}">
      <#if (brand.info_state)?if_exists=='0'>
       <span class="redcolor">未审核</span>
      <#elseif (brand.info_state)?if_exists=='1'>
        <span class="greencolor">正常</span>
      <#elseif (brand.info_state)?if_exists=='2'>
        <span class="bluecolor">未通过</span>
      <#else>
       <span class="orangecolor">禁用</span>
      </#if>
      </a>
    </td>
    <td align="center">${(brand.in_date)?if_exists}</td>
    <td align="center">
     <a onclick="linkToInfo('/member_Brand_view.action','brand.brand_id=${(brand.brand_id)?if_exists}');" class="xg">修改</a>
     <a onclick="delOneInfo('brand.brand_id','/member_Brand_delete.action','${(brand.brand_id)?if_exists}')" class="dele">删除</a>
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
		<td align="right">品牌名称:</td>
		<td><@s.textfield name="title_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}"/></td>
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
