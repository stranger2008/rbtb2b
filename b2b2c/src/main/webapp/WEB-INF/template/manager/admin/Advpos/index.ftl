<html>
  <head>
    <title>广告位管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript" src="/include/js/admin/advpos.js"></script>
    <script type="text/javascript" src="/include/components/artDialog4.0.3/artDialog.js?skin=default"></script>
    <script type="text/javascript" src="/include/components/artDialog4.0.3/jquery.artDialog.js?skin=default"></script>
    <script type="text/javascript">
     function updateAdvposBatch(actionName)
      {
           var admin_pos_posid='';
	       var chks =document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
          { 
            
           if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
           {
                //得到所名为checkbox的input
                admin_pos_posid+=chks[i].value+',';
               
             }
           }
            var len=admin_pos_posid.length;
            var pos_id=admin_pos_posid.substring(0,len-1);
            document.getElementById('batch_update_hidden_posid').value = pos_id;//用于隐藏所有的ID
            document.forms[0].action=actionName;
		    document.forms[0].submit();
      }   
    </script>
  </head>
  <body>
<@s.form action="/admin_Advpos_list.action" method="post">
<@s.hidden name="batch_update_hidden_posid" id="batch_update_hidden_posid"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:网站管理 > 广告管理 >广告位 > 广告位列表
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Advpos_add.action','');">添加广告位</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="xg" ><a onclick="updateAdvposBatch('/admin_Advpos_updateAdvposBatch.action');">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('advpos.pos_id')"/></td>
    <td width="8%" align="center" class="top_td">排序</td>
    <td width="5%" align="center" class="top_td">广告位ID号</td>
    <td width="8%"  align="center" class="top_td">广告类型</td>
    <td width="20%" align="center" class="top_td">广告位名称</td>
    <td width="8%"  align="center" class="top_td">规格(宽*高px)</td>
    <!--
    <td width="8%"  align="center" class="top_td">所属模块</td>
    <td width="8%"  align="center" class="top_td">所属地区</td>
    -->
    <td width="8%"  align="center" class="top_td">价格</td>
     <td width="8%"  align="center" class="top_td">示意图</td>
    <td width="8%" align="center" class="top_td">前台是否显示</td>
    <td width="8%" align="center" class="top_td">JS调用代码</td>
    <td width="8%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list advposList as pos>

  <tr bgcolor="<#if pos_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="advpos.pos_id" value="${pos.pos_id?if_exists}"/></td>
    <td align="center"><@s.textfield name="advpos.sort_no" value="${pos.sort_no?if_exists}" cssStyle="width:50px;" onkeyup="checkNum(this)"  /></td>
    <td align="center">${pos.pos_id?if_exists}</td>
    <td align="center">${pos.para_key?if_exists}</td>
    <td align="left"><a href="/admin_Advinfo_list.action?posid_s=${pos.pos_id?if_exists}">${pos.pos_name?if_exists}</a></td>
    <td align="center">${pos.p_width?if_exists}*${pos.p_height?if_exists}</td>
    <!--
    <td align="center"><#if pos.model_value?if_exists==''>无模块类型<#else>${pos.model_value?if_exists}</#if></td>
    <td align="center"><#if pos.area_attr?if_exists==''>无地区分类<#else>${pos.area_attr?if_exists}</#if></td>
    -->
    <td align="center"><#if pos.price?if_exists==0>面议<#else>${pos.price?if_exists}</#if></td>
     <td align="center"><#if pos.img_path?if_exists==''><font color='red'>暂无</font><#else><a href="${pos.img_path?if_exists}" target="_blank"><font color='green'>查看</font></a></#if> </td>
    <td align="center">
    
    <a onclick="linkToInfo('/admin_Advpos_list.action','isshow_s=${pos.isshow?if_exists}');">
    
    <#if pos.isshow?if_exists=='0'>
    
    <font color='green'>显示</font>
    
    <#else>
      <font color='red'>不显示</font>
      
      </#if> 
      
    </a>  
    </td>
    
    
    <td align="center">
       <#if (pos.pos_type)?if_exists!='f' && (pos.pos_type)?if_exists!='g'><a onclick="linkToInfo('/admin_Advpos_viewJS.action','pos_id_s=${(pos.pos_id)?if_exists}');" >查看</a></#if>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Advpos_view.action','advpos.pos_id=${pos.pos_id?if_exists}');" title="修改广告位"><img src="/include/images/bj.gif" /></a>
    <a onclick="linkToInfo('/admin_Advinfo_list.action','posid=${pos.pos_id?if_exists}&two_pages_link=no');" title="查看此广告位下的广告列表"><img src="/include/images/view.gif" /></a>
    <a onclick="linkToInfo('/admin_Advinfo_add.action','advpos.pos_id=${pos.pos_id?if_exists}&type=${pos.pos_type?if_exists}&tablename=${pos.module_type?if_exists}&posid=${pos.pos_id?if_exists}');" title="在此广告位添加广告"><img src="/include/images/add.gif" /></a>
    <#if (pos.sys_adv)?if_exists == '0'>
    	<a href="javascript:delOneInfo('advpos.pos_id','/admin_Advpos_delete.action','${pos.pos_id?if_exists}');"><img src="/include/images/admin/delete.png" border="0"/></a>
    </#if>
    
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
		<td align="right">广告位名称:</td>
		<td><@s.textfield name="pos_name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">广告类型:</td>
		<td><@s.select name="pos_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" /></td>
	</tr>
	<tr>
         <td align="right">所属模块</td>
         <td>
         <@s.select name="module_type_s" list="commpara_modelList" listValue="module_name" listKey="module_type" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
	<tr>
		<td align="right">规格:</td>
		<td><@s.textfield name="pos_size_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">价格:</td>
		<td><@s.textfield name="pos_price_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">前台是否显示:</td>
		<td><@s.select name="isshow_s" list=r"#{'':'请选择','0':'显示','1':'不显示'}"/> </td>
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
</@s.form>

  </body>
</html>