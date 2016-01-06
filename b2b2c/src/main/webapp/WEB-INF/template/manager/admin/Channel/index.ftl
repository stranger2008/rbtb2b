<html>
  <head>
    <title>网站栏目</title>
    <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	<script type="text/javascript" src="/include/js/admin/channel.js"></script>
	<script type="text/javascript">
  
    </script>
  </head>
  <body>
	<@s.form action="/admin_Channel_list.action" method="post" validate="true">
	<@s.hidden name="admin_Sort_id" id="admin_Sort_id"/>
	<div class="main_index f_left">
	  <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 网站栏目管理
      </div>
 		<div class="main_top">
   		<ul class="main_top_ul">
     		<li class="tj"><a href="<#if mall_type?if_exists=='b2b'>/admin_Channel_add.action<#elseif (mall_type?if_exists)=='b2c'>/admin_Channel_malladd.action</#if>">添加栏目</a></li>
     		
     		<li class="shuaix"><a onclick="updateChannelSortNo(<#if mall_type?if_exists=='b2b'>'/admin_Channel_updateAllSortNo.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Channel_mallupdateAllSortNo.action'</#if>);">排序</a></li>
   		</ul>
 		</div>
 <div class="main_cont">
 	<script type="text/javascript">
		d = new dTree('d');
		d.add(0,-1,'网站栏目&nbsp;');	
		<#list channelList as syschannel>
		d.add(${syschannel.ch_id?if_exists},${syschannel.up_ch_id?if_exists},'<input type="checkbox" class="${syschannel.up_ch_id?if_exists}" onclick="checkStatus(${syschannel.ch_id?if_exists},this)" name="channel.ch_id" value="${syschannel.ch_id?if_exists}"/>&nbsp; ${syschannel.ch_name?if_exists} &nbsp;<@s.textfield name="channel.sort_no" id="${syschannel.ch_id?if_exists}"  onkeyup="checkNum(this)"   value="${syschannel.sort_no!0}" cssClass="txtinput" cssStyle="width:35px"  maxLength="4"/>&nbsp;<#if syschannel.sys_ch=='1'><#else><a  href=### onclick=delOneInfo("channel.ch_id","<#if mall_type?if_exists=='b2b'>/admin_Channel_delete.action<#elseif (mall_type?if_exists)=='b2c'>/admin_Channel_malldelete.action</#if>","${syschannel.ch_id?if_exists}") >删除</a></#if>&nbsp;<a href="<#if mall_type?if_exists=='b2b'>/admin_Channel_add.action<#elseif (mall_type?if_exists)=='b2c'>/admin_Channel_malladd.action</#if>?channel.up_ch_id=${syschannel.ch_id?if_exists}&channel.ch_level=${syschannel.ch_level?if_exists}">添加子栏目</a>&nbsp;<a href="<#if mall_type?if_exists=='b2b'>/admin_Channel_view.action<#elseif (mall_type?if_exists)=='b2c'>/admin_Channel_mallview.action</#if>?channel.ch_id=${syschannel.ch_id?if_exists}">修改</a>','','','','');
		 </#list>
		document.write(d);
	</script>
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>
	</@s.form>	
  </body>
</html>