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
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>
<@s.form action="/admin_Brand_list.action" method="post">
<@s.hidden name="brand.is_recom" id="admin_brand_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 品牌管理 > 品牌列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Brand_cate.action','');">添加品牌</a></li>
     <li class="sc"><a onclick="delInfo('brand.brand_id','/admin_Brand_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
      <li class="qiyong"><a onclick="updateRecomState('1','brand.brand_id','/admin_Brand_updateisrecom.action','admin_brand_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','brand.brand_id','/admin_Brand_updateisrecom.action','admin_brand_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('brand.brand_id')"/></td>
    <td width="20%"  align="center" class="top_td">品牌名称</td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="10%" align="center" class="top_td">所属分类</td>
    <td width="10%" align="center" class="top_td">所属地区</td>
    <td width="10%"  align="center" class="top_td">信息状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="17%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list brandList as brand>

   <tr bgcolor="<#if brand_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="brand.brand_id" value="${(brand.brand_id)?if_exists}"/></td>
    <td align="left">
      <a onclick="linkToInfo('/admin_Brand_view.action','brand.brand_id=${(brand.brand_id)?if_exists}');">
       <#if  brand.title?length   lt   16  >   ${brand.title?if_exists} <#else> ${brand.title?if_exists[0..15]}..</#if>
      </a>
      <a href ="/admin_Brand_list.action?is_recom_s=1"><span class="redcolor"><#if brand.is_recom=='1'>[推荐]</#if></span></a>
    </td>
    <td align="center">
    <a href="/admin_Member_viewinfo.action?member.cust_id=${(brand.cust_id)?if_exists}" target="_blank">  
    
        <#if (brand.cust_name)?if_exists!=''><#if (brand.cust_name)?length lt 6> ${(brand.cust_name)?if_exists}<#else>${(brand.cust_name)[0..5]}</#if></#if>
        
    </a>
    </td>
    <td align="center"> 
       <a onclick="linkToInfo('/admin_Brand_list.action','cat_attr_s=${brand.cat_attr_id?if_exists}');">${brand.cat_attr?if_exists}</a>
    </td>
    <td align="center">${(brand.area_attr)?if_exists}</td>
    
    
    <td align="center">
      <a href="/admin_Brand_list.action?info_state_s=${(brand.info_state)?if_exists}">
      <#if (brand.info_state)?if_exists=='1'>
       <span class="greencolor">正常</span>
      <#elseif (brand.info_state)?if_exists=='3'>
       <span class="redcolor">禁用</span>
      </#if>
      </a>
    </td>
    <td align="center">${(brand.in_date)?if_exists}</td>
    <td align="center">
     <a onclick="linkToInfo('/admin_Brand_view.action','brand.brand_id=${(brand.brand_id)?if_exists}');"><img src="/include/images/bj.gif" /></a>
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
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cust_name_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">所属地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
	<tr>
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','1':'正常','3':'禁用'}"/></td>
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<!--搜索区域结束-->
</@s.form>

  </body>
</html>