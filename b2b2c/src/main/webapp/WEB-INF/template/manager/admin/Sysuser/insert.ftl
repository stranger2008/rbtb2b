<html>
  <head>
    <title>添加系统用户</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/js/admin/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	     
	  });
	</script>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 系统用户管理 > 添加系统用户
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Sysuser_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">用户名<font color="red">*</font></td>
             <td>
             	<@s.textfield name="sysuser.user_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.user_name</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.user_name')}"/>">        
             </td>
           </tr>
           <tr>
             <td class="table_name">密码<font color="red">*</font></td>
             <td>
             	<@s.password name="sysuser.passwd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.passwd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">昵称:</td>
             <td>
             	<@s.textfield name="sysuser.nike_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.nike_name</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Sysuser.nike_name')}"/> '>
             </td>
           </tr>
           <tr>
             <td class="table_name">角色<font color="red">*</font></td>
             <td>
               <@s.select id="type" name="sysuser.role_id"  list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/>        
               <@s.fielderror><@s.param>sysuser.role_id</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属部门:</td>
             <td>
                <table>
             		<tr>
             			<td class="tdbottom">
             				<div id="orgselect"></div>
             			</td>
             			<td class="tdbottom">
             				<a href="admin_Organize_list.action" target="_blank">[部门管理]</a>
	              		</td>
	              	</tr>
	            </table> 
             </td>
           </tr>
           <tr>
             <td class="table_name">真实姓名:</td>
             <td>
                 <@s.textfield name="sysuser.real_name" cssClass="txtinput"/>
                 <@s.fielderror><@s.param>sysuser.real_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">邮箱:</td>
             <td>
             	<@s.textfield name="sysuser.email" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>sysuser.email</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">状态:</td>
             <td>
             	<@s.radio name="sysuser.state" list=r"#{'0':'启用','1':'禁用'}" value="0"/>
             	<@s.fielderror><@s.param>sysuser.state</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Sysuser.state')}"/> '>
             </td>
           </tr>
         </table>         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>	       
	       <@s.hidden name="org_hidden_value" id="org_value"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysuser_list.action','');"/>
	     </div>  
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>