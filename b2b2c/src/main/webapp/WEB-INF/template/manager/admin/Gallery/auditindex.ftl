<html>
  <head>
    <title>图库审核列表</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"gallery");    
	  });
	</script>
  </head>
  <body>
<@s.form action="/admin_Gallery_auditList.action" method="post">
<@s.hidden name="gallery.is_recom" id="admin_gallery_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:功能模块 > 图库管理 > 图库审核列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('gallery.gal_id','/admin_Gallery_checkDel.action')">删除</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
  	<td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('gallery.gal_id')"/></td>
    <td width="10%"  align="center" class="top_td">图库标题</td>
    <td width="10%" align="center" class="top_td">会员名称</td>
    <td width="10%" align="center" class="top_td">分类</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">审核</td>
  </tr>
  
  <#list galleryList as gallery>
  <tr>
   	<td><input type="checkbox" name="gallery.gal_id" value="${gallery.gal_id?if_exists}"/></td>
    <td align="left" class="audit_left">
   
    <#if gallery.title?if_exists!=''>
    <#if gallery.title?length lt 20>
    <a onclick="linkToInfo('/admin_Gallery_audit.action','gallery.gal_id=${gallery.gal_id?if_exists}');">${gallery.title?if_exists}</a><#if gallery.is_recom?if_exists=='1'><a onclick="linkToInfo('/admin_Gallery_auditList.action','is_recom_s=${gallery.is_recom?if_exists}');"><span class="redcolor">[推荐]</span></a></#if>
    <#else>
    <a onclick="linkToInfo('/admin_Gallery_audit.action','gallery.gal_id=${gallery.gal_id?if_exists}');">${gallery.title[0..19]}</a><#if gallery.is_recom?if_exists=='1'><a onclick="linkToInfo('/admin_Gallery_auditList.action','is_recom_s=${gallery.is_recom?if_exists}');"><span class="redcolor">[推荐]</span></a></#if>
    </#if>
    </#if>
    </td>    
    <td align="center">
    <#if gallery.cust_name?if_exists!=''>
    <#if gallery.cust_name?length lt 20>
    ${gallery.cust_name?if_exists}
    <#else>
    ${gallery.cust_name[0..19]}
    </#if>
    </#if>
    </td>   
    <td align="center">
        <a onclick="linkToInfo('/admin_Gallery_auditList.action','cat_attr_s=${gallery.cat_attr_id?if_exists}');">${gallery.cat_attr?if_exists}</a>
    </td>
    <td align="center"><#if gallery.info_state?if_exists=='0'><a onclick="linkToInfo('/admin_Gallery_auditList.action','info_state_s=${gallery.info_state?if_exists}');"><span class="redcolor">未审核</span></a></#if>
    <#if gallery.info_state?if_exists=='1'><a onclick="linkToInfo('/admin_Gallery_auditList.action','info_state_s=${gallery.info_state?if_exists}');"><span class="greencolor">正常</span></a></#if>
    <#if gallery.info_state?if_exists=='2'><a onclick="linkToInfo('/admin_Gallery_auditList.action','info_state_s=${gallery.info_state?if_exists}');"><span class="bluecolor">未通过</span></a></#if>
    <#if gallery.info_state?if_exists=='3'><a onclick="linkToInfo('/admin_Gallery_auditList.action','info_state_s=${gallery.info_state?if_exists}');"><span class="redcolor">禁用</span></a></#if></td> 
    <td align="center">${gallery.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Gallery_audit.action','gallery.gal_id=${gallery.gal_id?if_exists}');"><img src="/include/images/audit.png" /></a></td>
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
		<td align="right">图库标题:</td>
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
	<tr>
		<td align="right" width="30%">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
   	<tr>
         <td align="right">是否推荐:</td>
          <td>
           <@s.select name="is_recom_s" list=r"#{'0':'否','1':'是'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
     <tr>
		<td align="right">分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
             <td align="right">状态:</td>
             <td>
             	<@s.select name="info_state_s" list=r"#{'0':'未审核','2':'未通过'}" headerKey="" headerValue="请选择"/>  
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
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->

<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />

</@s.form>

  </body>
</html>