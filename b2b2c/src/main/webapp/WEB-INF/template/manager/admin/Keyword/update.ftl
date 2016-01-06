<html>
  <head>
    <title>修改关键字</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 关键字管理 > 修改关键字
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Keyword_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">关键字名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="keyword.key_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>keyword.key_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">关键字类型<font color='red'>*</font></td>
             <td>
             <@s.select name="keyword.module_type" list="commparaList" listValue="module_name" listKey="module_type" />
             </td>
           </tr>
             <tr>
             <td class="table_name">关键字搜索频率<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="keyword.num" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>keyword.num</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="keyword.key_id"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       
	       ${listSearchHiddenField?if_exists}
	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Keyword_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>