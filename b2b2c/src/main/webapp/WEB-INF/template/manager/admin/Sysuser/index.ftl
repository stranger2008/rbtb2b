<html>
  <head>
    <title>系统用户管理</title>
    
    <script>
    	//管理员启用禁用批量操作
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
				  tip = '确认启用吗?';
			   }else if(state=='1'){
				  tip = '确认禁用吗?';
			   }
			   art.dialog({
	           title: '系统信息提示',
               content: '<div class="decorate">'+tip+'</div>',
               width: '15%',
               icon: 'question',
               yesFn: function () {
                document.getElementById('admin_user_state').value = state;
				document.forms[0].action=actionName;
				document.forms[0].submit();
                return false;
              },
              noText: '关闭',
              noFn: true //为true等价于function(){}
             })
			
			}
		}
    </script>
    
  </head>
  <body>

<@s.form action="/admin_Sysuser_list.action" method="post">

<@s.hidden name="sysuser.state" id="admin_user_state"/>

<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 系统用户管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Sysuser_add.action','');">添加管理员</a></li>     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','sysuser.user_id','/admin_Sysuser_updateState.action');">启用</a></li>
     <li class="jingyong"><a onclick="updateState('1','sysuser.user_id','/admin_Sysuser_updateState.action');">禁用</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sysuser.user_id')"/></td>
    <td width="20%" align="center" class="top_td">用户名</td>
    <td width="20%"  align="center" class="top_td">昵称</td>
    <td width="20%"  align="center" class="top_td">角色</td>
    <td width="20%"  align="center" class="top_td">状态</td>
    <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list userList as user>

    <tr bgcolor="<#if user_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="sysuser.user_id" value="${user.user_id?if_exists}"/></td>
    <td align="center">${user.user_name?if_exists}</td>
    <td align="center">${user.nike_name?if_exists}</td>
    <td align="center">
    
    	<#if user.user_type=='3'>
    		<font color="red">[系统管理员]</font>
    	</#if>
    	
    	<#if user.user_type=='4'>
    		<a onclick="document.forms[0].role_id_s.value='${user.role_id?if_exists}';document.forms[0].submit();">${user.role_name?if_exists}</a>
    	</#if>
    	
    </td>
    <td align="center"><#if user.state?if_exists=='0'><font color='green'>启用</font><#else><font color='red'>禁用</font></#if> </td>
    <td align="center">
    	<a onclick=linkToInfo("/admin_Sysuser_view.action","sysuser.user_id=${user.user_id?if_exists}");><img src="/include/images/bj.gif" /></a>
    	<#if user.user_type=='4'>
    	<a href="javascript:delOneInfo('sysuser.user_id','/admin_Sysuser_delete.action','${(user.user_id)?if_exists}');"><img src="/include/images/admin/delete.png" border="0"/></a>
    	
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
		<td align="right">用户名:</td>
		<td><@s.textfield name="user_name_s"/></td>
	</tr>
	<tr>
		<td align="right">角色:</td>
		<td>
			<@s.select name="role_id_s"  list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/>  
		</td>
	</tr>
	<tr>
		<td align="right">昵称:</td>
		<td><@s.textfield name="nike_name_s"/></td>
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
