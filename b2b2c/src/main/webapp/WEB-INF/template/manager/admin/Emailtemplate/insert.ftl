<html>
  <head>
    <title>添加模板</title>
  </head>
  <body>
<div class="tj_main f_left">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 邮件模板管理 > 添加模板
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Emailtemplate_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">模板名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="emailtemplate.temp_name" cssClass="txtinput" cssStyle="width:300px" maxLength="20"/>
             	<@s.fielderror><@s.param>emailtemplate.temp_name</@s.param></@s.fielderror>
             </td>
           </tr>
         <tr>
             <td class="table_name">模板内容<font color='red'>*</font></font></td>
             <td colspan="3">
             	<@s.textarea name="emailtemplate.temp_con" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
				<@s.fielderror><@s.param>emailtemplate.temp_con</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">标签注解：</td>
             <td>
             	<@s.textarea name="emailtemplate.tag_desc" cssClass="txtinput" cssStyle="width:500px;height:100px;" 
             	 onkeyup="set_textarea_length(this,300);" />
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="temp_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Emailtemplate_list.action','');"/>
	     </div>
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>