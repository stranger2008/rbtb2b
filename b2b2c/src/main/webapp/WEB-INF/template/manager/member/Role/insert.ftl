<html>
<head>
     <title>添加角色</title>
	 <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	 <script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	 <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	
	 <script type="text/javascript" src="/include/js/member/role.js"></script>	
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Role_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>账号管理>添加角色</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
          <tr>
             <td width="19%" valign="middle" class="left_td">角色名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="role.role_name" cssClass="txtinput" maxlength="20"/>
             	<@s.fielderror><@s.param>role.role_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">菜单权限<font color='red'>*</font></td>
             <td>
             <script type="text/javascript">  
        	  d = new dTree('d');
		       d.add(1111111111,-1,'会员菜单');		
		      <#list menuList as menu>	
                d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="checkbox" id="${menu.up_menu_id?if_exists}${menu.menu_id?if_exists}" name="role.menu_right" value="${menu.menu_id?if_exists}"/> ${menu.menu_name?if_exists}&nbsp;${stack.findValue("@com.rbt.function.RoleRightFuc@getMenuRightListByMenuid('${menu.menu_id?if_exists}')")}');
		      </#list>
		      document.write(d);
	         </script>
             <@s.fielderror><@s.param>role.menu_right</@s.param></@s.fielderror>
             <@s.fielderror><@s.param>role.oper_right</@s.param></@s.fielderror>
             </td>
        </tr> 
        <tr>
             <td valign="middle" class="left_td">备注:</td>
             <td>
             	<@s.textarea name="role.remark" cssClass="mailCss" rows="3" cssStyle="width:400px;" onkeyup="set_textarea_length(this,200);"/>        
             	<@s.fielderror><@s.param>role.remark</@s.param></@s.fielderror>
             </td>
        </tr>
	    <tr>
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Role_list.action','');"/>
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
