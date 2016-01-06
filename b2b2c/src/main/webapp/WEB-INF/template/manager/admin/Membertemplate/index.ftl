<html>
  <head>
    <title>企业站模板管理</title>
    <script type="text/javascript" src="/include/js/admin/membertemplate.js"></script>
    <script>
     //更新排序
    function updatesort_no(actionName)
      {
           var admin_group_id='';
	       var chks =document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
          { 
            
           if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
           {
                //得到所名为checkbox的input
                admin_group_id+=chks[i].value+',';
               
             }
           }
            var len=admin_group_id.length;
            var group_id=admin_group_id.substring(0,len-1);
            document.getElementById('admin_temp_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }
    </script>
  </head>
  <body>
<@s.form action="/admin_Membertemplate_list.action" method="post">
<@s.hidden name="admin_temp_id" id="admin_temp_id"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 企业站模板管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Membertemplate_add.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Membertemplate_malladd.action'</#if>,'');">添加模板</a></li>
     <li class="sc"><a onclick="delInfo('membertemplate.temp_id',<#if mall_type?if_exists=='b2b'>'/admin_Membertemplate_delete.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Membertemplate_malldelete.action'</#if>)">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="xg" ><a onclick="updatesort_no(<#if mall_type?if_exists=='b2b'>'/admin_Membertemplate_updatesortno.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Membertemplate_mallupdatesortno.action'</#if>);">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('membertemplate.temp_id')"/></td>
    <td width="5%"  align="center" class="top_td">排序</td>
    <td width="15%" align="center" class="top_td">预览图</td>
    <td width="10%"  align="center" class="top_td">模板编码</td>
    <td width="10%"  align="center" class="top_td">详细信息</td>
    <td width="10%"  align="center" class="top_td">会员组</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list membertemplateList as membertemplate>
  <tr>
    <td><input type="checkbox" name="membertemplate.temp_id" value="${membertemplate.temp_id?if_exists}"/></td>
    <td align="center"><input name="isort_no" value="${membertemplate.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center" style="padding:10px;">
	    	<img src="${membertemplate.temp_img?if_exists}" width="160" height="100" style="border:1px solid #e1e2e3;" />
    </td>
    <td align="center">${membertemplate.temp_code?if_exists}</td>
    <td align="center">
    <table width="150" align="center">
	    <tr><td width="70" align="center" style="border:0;">模板名称：</td><td align="left" style="border:0;">${membertemplate.temp_name?if_exists}</td></tr>
	    <tr><td align="center" style="border:0;">价格：</td><td align="left" style="border:0;"><#if membertemplate.price?if_exists==0><font color='green'>免费</font><#else>${membertemplate.price?if_exists}<#if membertemplate.p_unit?if_exists=='0'><font color='red'>&nbsp;积分</font><#else><font color='red'>&nbsp;资金</font></#if></#if></td></tr>
	    <tr><td align="center" style="border:0;">作者：</td><td align="left" style="border:0;">${membertemplate.author?if_exists}</td></tr>
    </table>
    </td>
    <td align="center">${membertemplate.mem_level?if_exists}</td>
    <td align="center"> <a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Membertemplate_view.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Membertemplate_mallview.action'</#if>,'membertemplate.temp_id=${membertemplate.temp_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
<@s.hidden name="mall_type" value="${mall_type}" />

<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">模板名称:</td>
		<td><@s.textfield name="temp_name_s"/></td>
	</tr>
	<tr>
		<td align="right">模板编码:</td>
		<td><@s.textfield name="temp_code_s"/></td>
	</tr>
	<tr>
    <td align="right">会员:</td>
         <td> 
         <@s.select name="temp_mem_s" list="memberlevleList" listValue="level_name" listKey="level_id" headerKey="0" headerValue="请选择" />
         </td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
</@s.form>

  </body>
</html>