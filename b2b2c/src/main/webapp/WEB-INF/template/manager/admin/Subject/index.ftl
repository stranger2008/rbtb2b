<html>
  <head>
    <title>专题列表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 
	    //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"subject");    
	  });
	</script>
  </head>
  <body>
<@s.form action="/admin_Subject_list.action" method="post">
<@s.hidden name="subject.is_recom" id="admin_subject_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 专题管理 > 专题列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo('/admin_Subject_cate.action','');">添加专题</a></li>
     <li class="sc"><a onclick="delInfo('subject.sub_id','/admin_Subject_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','subject.sub_id','/admin_Subject_updateisrecom.action','admin_subject_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','subject.sub_id','/admin_Subject_updateisrecom.action','admin_subject_state');">取消推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subject.sub_id')"/></td>
    <td width="20%"  align="center" class="top_td">专题标题</td>
    <td width="10%"  align="center" class="top_td">所属分类</td>
    <td width="10%" align="center" class="top_td">信息状态</td>
    <td width="10%"  align="center" class="top_td">发布时间</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list subjectList as sub>

   <tr bgcolor="<#if sub_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="subject.sub_id" value="${sub.sub_id?if_exists}"/></td>
    <td align="left">
    <#if sub.title?if_exists!=''>
    <a onclick="linkToInfo('/admin_Subject_view.action','subject.sub_id=${sub.sub_id?if_exists}');">
       <#if sub.title?length lt 21>${sub.title?if_exists}<#else>${sub.title[0..20]}..</#if>
    </a>
    <a href="/admin_Subject_list.action?is_recom_s=1"><span class="redcolor"><#if sub.is_recom=='1'>[推荐]</#if></span></a>
    </#if>
    </td>
    <td align="center">
    <#if sub.cat_attr?if_exists!=''>
           <a onclick="linkToInfo('/admin_Subject_list.action','cat_attr_s=${sub.cat_attr_id?if_exists}');">${sub.cat_attr?if_exists}</a>
    </#if>
    </td>
    <td align="center">
      <a href="/admin_Subject_list.action?info_state_s=${sub.info_state?if_exists}">
       <#if sub.info_state?if_exists=='0'><span class="orangecolor">未审核</span>
       <#elseif sub.info_state?if_exists=='1'><span class="greencolor">正常</span>
       <#elseif sub.info_state?if_exists=='2'><span class="bluecolor">未通过</span>
       <#else><span class="redcolor">禁用</span></#if>
      </a>
    </td>
    <td align="center">${sub.in_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Subject_view.action','subject.sub_id=${sub.sub_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">专题标题:</td>
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
		<td align="right">信息状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','0':'未审核','1':'正常','2':'未通过','3':'禁用'}"/></td>
	</tr>
	<tr>
		<td align="right">发布时间:</td>
		<td>
		<@s.textfield id="txtstartDate" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="last_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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