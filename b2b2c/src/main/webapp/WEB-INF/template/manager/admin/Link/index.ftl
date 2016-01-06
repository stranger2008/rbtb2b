<html>
  <head>
  <title>友情链接管理</title>
  <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
        <script>
    	//友情链接批量操作
    	function updateState(state,field_name,actionName){
			var flag = false;
			var checks = document.getElementsByName(field_name);
			for(var i=0;i<checks.length;i++){
				if(checks[i].checked){
					flag=true;
					break;
				}
			}
			if(flag==false){
				alertShow('请至少选择一条记录!','warning');
		        runCount(3);
				return false;
			}
			if(flag==true){
			    var tip = '';
				if(state=='0'){
					tip = '确认显示吗?';
				}else if(state=='1'){
					tip = '确认不显示吗?';
				}
				art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
			        document.getElementById('admin_link_state').value = state;
				    document.forms[0].action=actionName;
				    document.forms[0].submit();
			        return false;
			    },
			    noText: '关闭',
			    noFn: true //为true等价于function(){}
			    });
				
			}
		}
    </script>
     <script type="text/javascript">
	  $(document).ready(function(){       
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
<body>
<@s.form action="/admin_Link_list.action" method="post">
<@s.hidden name="link.is_display" id="admin_link_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 友情链接管理 
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_add.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malladd.action'</#if>,'');">添加链接</a></li>
     <li class="sc"><a onclick="delInfo('link.link_id',<#if mall_type?if_exists=='b2b'>'/admin_Link_delete.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malldelete.action'</#if>)">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','link.link_id',<#if mall_type?if_exists=='b2b'>'/admin_Link_updateState.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_mallupdateState.action'</#if>);">显示</a></li>
     <li class="jingyong"><a onclick="updateState('1','link.link_id',<#if mall_type?if_exists=='b2b'>'/admin_Link_updateState.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_mallupdateState.action'</#if>);">不显示</a></li>
     <li class="guanli"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Linkgroup_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Linkgroup_malllist.action'</#if>,'name=${name?if_exists}');">分组管理</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('link.link_id')"/></td>
    <td width="10%" align="center" class="top_td">友情链接标题</td>
    <td width="10%"  align="center" class="top_td">分组类型</td>
    <td width="10%" align="center" class="top_td">地区</td>
    <td width="10%"  align="center" class="top_td">链接地址</td>
     <td width="10%"  align="center" class="top_td">是否显示</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list linkList as link>
  <tr>
    <td><input type="checkbox" name="link.link_id" value="${link.link_id?if_exists}"/></td>
    <td align="center"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_view.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_mallview.action'</#if>,'link.link_id=${link.link_id?if_exists}');">${link.link_name?if_exists}</a></td>
    <td align="center"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malllist.action'</#if>,'link_group=${link.link_group?if_exists}');">${link.name?if_exists}</a></td>
    <td align="center" >${link.area_attr?if_exists}</td>
    <td align="center"><a href="${link.url?if_exists}" target="_blank">${link.url?if_exists}</a></td>
    <td align="center"><#if link.is_display?if_exists=='0'>
    <a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malllist.action'</#if>,'is_display_s=${link.is_display?if_exists}');">
    <font color='green'>显示</font></a>
    <#else>
    <a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malllist.action'</#if>,'is_display_s=${link.is_display?if_exists}');">
    <font color='red'>不显示</font></a></#if> </td>
    <td align="center"><a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_view.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_mallview.action'</#if>,'link.link_id=${link.link_id?if_exists}');">
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
		<td  align="right">友情链接标题:</td>
		<td><@s.textfield name="link_name_s" cssClass="txtinput" maxLength="20"/></td>
	</tr>
	   <tr>
             <td align="right">链接地址:</td>
             <td>
             	<@s.textfield name="url_s" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>link.url</@s.param></@s.fielderror>
             </td>
       </tr>
       <tr>
		<td align="right">地区:</td>
		<td><div id="arealist"></div></td>
	   </tr>
        <tr>
         <td align="right">是否显示:</font></td>
         <td>
         	<@s.select name="is_display_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  
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
<!--搜索区域结束-->
<!--所属地区插件隐藏字段开始区域-->
 <@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
 <@s.hidden id="hidden_up_area_id" name="hidden_up_area_id" value="${hidden_up_area_id?if_exists}"/>
 <@s.hidden name="mall_type" />
<!--所属地区插件隐藏字段结束区域-->

</@s.form>

  </body>
</html>