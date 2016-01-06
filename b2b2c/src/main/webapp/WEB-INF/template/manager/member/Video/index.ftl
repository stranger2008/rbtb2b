<html>
<head>
<title>视频管理</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  <script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"video");   
	  });
	</script> 
</head>
<body>
<@s.form action="/member_Video_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>视频管理</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li">
       <a onclick="linkToInfo('/member_Video_cate.action','');">添加视频</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('video.video_id','/member_Video_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('video.video_id')"/></td>
    <td width="10%"  align="center" class="top_td">视频标题</td>
    <td width="10%"  align="center" class="top_td">分类</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">编辑</td>
    </tr>
   <#list videoList as video>
  <tr>
    <td><input type="checkbox" name="video.video_id" value="${video.video_id?if_exists}"/></td>
    <td align="center">
    <#if video.title?if_exists!=''>
	    <a onclick=linkToInfo("/member_Video_view.action","video.video_id=${video.video_id?if_exists}");>
		    <#if video.title?length lt 8>
		   		 ${video.title?if_exists}
		    <#else>
		    	 ${video.title[0..7]}
		     </#if>
		    </a>
		    <#if video.is_recom?if_exists=='1'>
		    <a onclick=linkToInfo("/member_Video_list.action","is_recom_s=${video.is_recom?if_exists}");>
			    <span class="redcolor">[推荐]</span></a>
		    </#if>
	   
    </#if>
    </td>
    <td align="center">${video.cat_attr?if_exists}</td>
    <td align="center">
    <a onclick=linkToInfo("/member_Video_list.action","info_state_s=${video.info_state?if_exists}");>
  
    <#if video.info_state?if_exists=='0'>
         <span class="redcolor">未审核</span>
	</#if>
	<#if video.info_state?if_exists=='1'>
	<span class="greencolor">正常</span>   
	</#if>
	<#if video.info_state?if_exists=='2'>
	<span class="bluecolor">未通过</span>
	</#if>
	<#if video.info_state?if_exists=='3'>
    <span class="orangecolor">禁用</span>
	</#if>
	 </a>
    </td> 
   
    <td align="center">${video.in_date?if_exists}</td>
    <td align="center">
    <a class="xg" onclick=linkToInfo("/member_Video_view.action","video.video_id=${video.video_id?if_exists}");>修改</a>
    <a href="javascript:delOneInfo('video.video_id','/member_Video_delete.action','${(video.video_id)?if_exists}');" class="dele">删除</a></td>
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
		<td align="right">视频标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
   	<tr>
        <td align="right">是否推荐:</td>
          <td>
           <@s.select name="is_recom_s" list=r"#{'0':'否','1':'是'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
     <tr>
		<td align="right">分类:</td>
		<td colspan="5"><div id="divlist"></div></td>
	</tr>
	<tr>
         <td align="right">状态:</td>
             <td>
             	<@s.select name="info_state_s" list=r"#{'0':'未审核','1':'正常','2':'未通过','3':'禁用'}" headerKey="" headerValue="请选择"/>  
             </td>
           </tr>
    <tr>
    <td align="right">时间段:</td>
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
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<!--搜索区域结束-->
</@s.form>
</body>
</html>
