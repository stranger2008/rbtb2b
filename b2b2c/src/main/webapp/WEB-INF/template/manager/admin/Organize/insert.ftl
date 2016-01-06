<html>
  <head>
    <title>添加部门</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 组织部门管理 > 添加部门
   </div>
   <div class="tj_main_cont">   
   	<@s.form action="/admin_Organize_insert.action" method="post" validate="true">	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">部门名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.org_name" cssClass="txtinput" maxLength="12"/>
             	<@s.fielderror><@s.param>organize.org_name</@s.param></@s.fielderror>
             </td>
            </tr>
           <tr>
             <td class="table_name">部门级别:</td>
             <td>
             	  ${(organize.org_level?if_exists)} 级       
             	  <@s.fielderror><@s.param>organize.org_level</@s.param></@s.fielderror>	
             </td>
           </tr>           
           <tr>
             <td width="19%" class="table_name">所属地区:</td>
             <td>
             	  <div id="arealist"></div>
                  <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>      
             </td>
           </tr>
           
           <tr>
             <td width="19%" class="table_name">联系人<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.contact" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.contact</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">电话<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="organize.phone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.phone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">手机:</td>
             <td>
             	<@s.textfield name="organize.cellphone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.cellphone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">Skype:</td>
             <td>
             	<@s.textfield name="organize.skype" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.skype</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">QQ:</td>
             <td>
             	<@s.textfield name="organize.qq" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.qq</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">MSN:</td>
             <td>
             	<@s.textfield name="organize.msn" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.msn</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">email:</td>
             <td>
             	<@s.textfield name="organize.email" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.email</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">传真:</td>
             <td>
             	<@s.textfield name="organize.fax" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.fax</@s.param></@s.fielderror>
             </td>
           </tr>    
           <tr>
             <td width="19%" class="table_name">联系地址:</td>
             <td>
             	<@s.textfield name="organize.address" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>organize.address</@s.param></@s.fielderror>
             </td>
           </tr>       
         </table>   
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <@s.hidden name="organize.up_org_id" value="${(organize.up_org_id?if_exists)}" />
	       <@s.hidden name="organize.org_level"/>
	       <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Organize_list.action';document.forms[0].submit();"/>
	     </div>	     
	  </@s.form>
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>