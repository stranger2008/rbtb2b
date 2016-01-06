<html>
  <head>
    <title>友情链接分组管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
     function updateLinkgroup(actionName)
      {  
         art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">您确定要修改吗？</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
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
		            document.getElementById('admin_linkgroup_id').value = group_id;//用于隐藏所有的ID
					document.forms[0].action=actionName;
					document.forms[0].submit();
		            return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    })
  
      }
      
   
      
      
      function check(){ 
 	    if($("#groupname").val()==""){
	 	$("#nameerror").html("友情链接分组不能为空,请认真填写!");
	 	return false;
       }
       return true;
     }
    </script>
  </head>
  <body>
<@s.form action="/admin_Linkgroup_list.action" method="post" >
<@s.hidden name="admin_link_group_id" id="admin_linkgroup_id"/>
<div class="main_index f_left">
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="showSearch(this,'searchDiv');">添加分组</a></li>
     
     <li class="sc"><a onclick="delInfo('link_group.id',
      <#if mall_type?if_exists=='b2b'>'/admin_Linkgroup_delete.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Linkgroup_malldelete.action'</#if>)">删除</a></li>
     <li class="xg" ><a onclick="updateLinkgroup( <#if mall_type?if_exists=='b2b'>'/admin_Linkgroup_updateGroupname.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Linkgroup_mallupdateGroupname.action'</#if>);">修改</a></li>
     <li class="ret" ><a href="<#if mall_type?if_exists=='b2b'>/admin_Link_list.action<#elseif (mall_type?if_exists)=='b2c'>/admin_Link_malllist.action</#if>">链接管理</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox"  id="checkall" onclick="selectAll('link_group.id')"/></td>
    <td align="left" class="top_td">&nbsp;友情链接分组名称</td>
  </tr>
  
  <#list link_groupList as link_group>
  <tr>
    <td><input type="checkbox"   name="link_group.id" value="${link_group.id?if_exists}"/></td>
    <td>&nbsp;<@s.textfield name="link_group.name" value="${link_group.name?if_exists}" cssClass="txtinput" maxLength="20"/></td>
    <@s.fielderror id="groupnamefiled"><@s.param>link_group.name</@s.param></@s.fielderror>
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
<@s.hidden name="mall_type" />
</@s.form>
<!--搜索区域开始-->
<div class="divceng" style="display:none;" id="searchDiv">
  	<@s.form action="/admin_Linkgroup_insert.action" method="post"  validate="true" onsubmit="return check();">
	<table align="left">
	  <tr>
             <td class="table_name">友情链接分组名称:</td>
             <td>
             	<@s.textfield id="groupname" name="link_group.name" value="" maxLength="20" cssClass="txtinput"/>
             	<span id="nameerror"></span>
             </td>
      </tr>
      <tr> 
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="name"/>
	       ${listSearchHiddenField?if_exists}
	       <td><@s.submit value="保存" /></td>
	       <td><input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/></td>
	  </tr>
	</table>
	<@s.hidden name="mall_type" />
	</@s.form>
</div>
<!--搜索区域结束-->
  </body>
</html>