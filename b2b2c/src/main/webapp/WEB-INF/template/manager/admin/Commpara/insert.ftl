<html>
  <head>
    <title>添加参数</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:系统管理 > 系统工具 > 参数管理 > 添加参数
 </div>
   <div class="tj_main_cont">   
   	<@s.form action="/admin_Commpara_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">参数代码<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_code" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>commpara.para_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">参数名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>commpara.para_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">参数键<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_key" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>commpara.para_key</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">参数值<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_value" cssClass="txtinput" maxLength="200"/>
             	<@s.fielderror><@s.param>commpara.para_value</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="commpara.enabled" list=r"#{'0':'有效','1':'无效'}" value="0"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Commpara.enabled')}"/>"> 
             	<@s.fielderror><@s.param>commpara.enabled</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="commpara.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>commpara.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Commpara_list.action','');"/>
	     </div>	     
	  </@s.form>
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>