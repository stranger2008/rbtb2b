<html>
  <head>
    <title>添加操作权限</title>
    <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	<script type="text/javascript">
	function selectSyscode(name) {
	if (name == "sys") {
		document.getElementById("adminmenu").style.display = "";
		document.getElementById("membermenu").style.display = "none";
	} else {
		document.getElementById("adminmenu").style.display = "none";
		document.getElementById("membermenu").style.display = "";
	}
}
function selectSyscodeType(name)
	{
	  if (name == 'sys') {
		document.forms[0].action="/admin_Roleright_add.action?syscode_s=sys";
		document.forms[0].submit();
	} 
	else 
	{
		document.forms[0].action="/admin_Roleright_add.action?syscode_s=com";
		document.forms[0].submit();
	} 
}
	
	</script>
  </head>
  <body>

<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 操作权限管理 > 添加操作权限
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Roleright_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">权限名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="roleright.right_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>roleright.right_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属后台:</td>
             <td>
             	<@s.select name="roleright.syscode" list=r"#{'sys':'管理员后台','com':'会员后台'}" onchange="selectSyscodeType(this.value);"/> 
             	<@s.fielderror><@s.param>roleright.syscode</@s.param></@s.fielderror> 
             	<@s.fielderror><@s.param>roleright.syscode</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属菜单<font color='red'>*</font></td>
             <td>
           <script type="text/javascript">
        	d = new dTree('d');
		    d.add(1111111111,-1,'<#if syscode_s=='sys'>管理员菜单&nbsp;<#else>会员菜单</#if>');		
		    <#list menuList as menu>		
		    d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="radio" name="roleright.menu_attr" value="${menu.menu_id?if_exists}"/> ${menu.menu_name?if_exists}&nbsp;','#');
		    </#list>		
		    document.write(d);
	       </script>
	       <@s.fielderror><@s.param>roleright.menu_attr</@s.param></@s.fielderror>
	        <@s.fielderror><@s.param>roleright.oper_right</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">权限地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="roleright.url" size="70" maxLength="100"/>
             	<img class='ltip'src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Roleright.url')}"/> '>
             	<@s.fielderror><@s.param>roleright.url</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">操作提示信息<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="roleright.prompt" size="70" maxLength="120"/>
             	<@s.fielderror><@s.param>roleright.prompt</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">备注：</td>
             <td>
             	<@s.textarea name="roleright.remark" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>roleright.remark</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Roleright_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>