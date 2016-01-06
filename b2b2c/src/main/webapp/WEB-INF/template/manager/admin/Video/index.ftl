<html>
  <head>
    <title>视频列表</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"video");   
	  });
	</script>
  </head>
  <body>
<@s.form action="/admin_Video_list.action" method="post">
<@s.hidden name="video.is_recom" id="admin_video_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 视频管理 > 视频列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Video_cate.action','');">添加视频</a></li>
     
     <li class="sc"><a onclick="delInfo('video.video_id','/admin_Video_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','video.video_id','/admin_Video_updateisrecom.action','admin_video_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','video.video_id','/admin_Video_updateisrecom.action','admin_video_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('video.video_id')"/></td>
    <td width="20%"  align="center" class="top_td">视频标题</td>
    <td width="10%" align="center" class="top_td">会员名称</td>
    <td width="16%"  align="center" class="top_td">分类</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list videoList as video>
  <tr>
    <td><input type="checkbox" name="video.video_id" value="${video.video_id?if_exists}"/></td>
     <td align="left">
    <#if video.title?if_exists!=''>
    <#if video.title?length lt 20>
    <a onclick="linkToInfo('/admin_Video_view.action','video.video_id=${video.video_id?if_exists}');">${video.title?if_exists}</a><#if video.is_recom?if_exists=='1'><a onclick="linkToInfo('/admin_Video_list.action','is_recom_s=${video.is_recom?if_exists}');"><span class="redcolor">[推荐]</span></a></#if>
    <#else>
    <a onclick="linkToInfo('/admin_Video_view.action','video.video_id=${video.video_id?if_exists}');">${video.title[0..19]}</a><#if video.is_recom?if_exists=='1'><a onclick="linkToInfo('/admin_Video_list.action','is_recom_s=${video.is_recom?if_exists}');"><span class="redcolor">[推荐]</span></a></#if>
    </#if>
    </#if></td>
    
    <td align="center">
     <a href="/admin_Member_viewinfo.action?member.cust_id=${(video.cust_id)?if_exists}" target="_blank">
     
	    <#if video.cust_name?if_exists!=''>
	    <#if video.cust_name?length lt 6>
	    ${video.cust_name?if_exists}
	    <#else>
	    ${video.cust_name[0..5]}...
	    </#if>
	    </#if>
	 </a>
    </td>
    <td align="center">
             <a onclick="linkToInfo('/admin_Video_list.action','cat_attr_s=${video.cat_attr_id?if_exists}');">${video.cat_attr?if_exists}</a>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Video_list.action','info_state_s=${video.info_state?if_exists}');">
    <#if video.info_state?if_exists=='0'><span class="redcolor">未审核</span></a></#if>
    <#if video.info_state?if_exists=='1'><span class="greencolor">正常</span></a></#if>
    <#if video.info_state?if_exists=='2'><span class="bluecolor">未通过</span></a></#if>
    <#if video.info_state?if_exists=='3'><span class="redcolor">禁用</span></a></#if>
    </a></td> 
    <td align="center">${video.in_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Video_view.action','video.video_id=${video.video_id?if_exists}');">
    <img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">视频标题:</td>
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
             	<@s.select name="info_state_s" list=r"#{'1':'正常','3':'禁用'}" headerKey="" headerValue="请选择"/>  
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