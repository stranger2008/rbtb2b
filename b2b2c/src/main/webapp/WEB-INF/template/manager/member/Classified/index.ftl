<html>
<head>
<title>分类信息</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"classified");           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");	
	  });
</script>
</head>
<body>
<@s.form action="/member_Classified_list.action" method="post">
<@s.hidden name="classified.is_recom" id="admin_classified_state"/>
  <div class="cont_main">
    <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>分类信息管理>分类信息列表</td>
 </tr>
</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Classified_cate.action','');">添加分类</a></li>
       <li class="sc"><a onclick="delInfo('classified.info_id','/member_Classified_delete.action')">删除</a></li>
       <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('classified.info_id')"/></td>
    <td width="12%"  align="center" class="top_td">分类信息标题</td>
    <td width="10%"  align="center" class="top_td">所属分类</td>
    <td width="10%"  align="center" class="top_td">所在地区</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
    </tr>
  <#list classifiedList as classified>
  <tr>
    <td><input type="checkbox" name="classified.info_id" value="${classified.info_id?if_exists}"/></td>
     <td align="left">
    <#if classified.title?if_exists!=''>
    	<a onclick="linkToInfo('/member_Classified_view.action','classified.info_id=${classified.info_id?if_exists}');"/>
		    <#if classified.title?length lt 14>
		    	${classified.title?if_exists}
		    <#else>
		    	${classified.title[0..13]}
		     </#if>
	    </a>
     	<#if classified.is_recom?if_exists=='1'>
	    	<a onclick="linkToInfo('/member_Classified_list.action','is_recom_s=${classified.is_recom?if_exists}');">
	    	<span class="redcolor">[推荐]</span></a>
	    </#if>
    </#if>
    </td>
    <td align="center">${classified.cat_attr?if_exists}</td>
    <td align="center">${classified.area_attr?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Classified_list.action','info_state_s=${classified.info_state?if_exists}');">
    <#if classified.info_state?if_exists=='0'><span class="redcolor">未审核</span></a></#if>
    <#if classified.info_state?if_exists=='1'><span class="greencolor">正常</span></a></#if>
    <#if classified.info_state?if_exists=='2'><span class="bluecolor">未通过</span></a></#if>
    <#if classified.info_state?if_exists=='3'><span class="redcolor">禁用</span></a></#if>
    </a></td> 
    <td align="center">${classified.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/member_Classified_view.action','classified.info_id=${classified.info_id?if_exists}');" class="xg">修改</a>
    <a href="javascript:delOneInfo('classified.info_id','/member_Classified_delete.action','${(classified.info_id)?if_exists}');" class="dele">删除</a></td>
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
		<td align="right">分类信息标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
		<td align="right" width="30%">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">所属分类:</td>
		<td colspan="5"><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">所在地区:</td>
		<td colspan="5"><div id="arealist"></div></td>
	</tr>
   	<tr>
         <td align="right">是否推荐:</td>
          <td>
           <@s.select name="is_recom_s" list=r"#{'0':'否','1':'是'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
	<tr>
         <td align="right">状态:</td>
         <td>
         	<@s.select name="info_state_s" list=r"#{'0':'未审核','1':'正常','2':'未通过','3':'禁用'}" headerKey="" headerValue="请选择"/>  
         </td>
         </tr>
    <tr>
    <td width="25%" align="right" >时间段:</td>
        <td>  	
        
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
        至
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

<!--搜索区域结束-->
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
</@s.form>
</body>
</html>
