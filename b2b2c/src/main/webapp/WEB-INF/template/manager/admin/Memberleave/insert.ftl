<html>
  <head>
    <title>留言</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 留言管理 > 留言
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Memberleave_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">留言标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberleave.title" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberleave.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">接收人<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="cust_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">留言内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="memberleave.content" cssClass="txtinput" onkeyup="set_textarea_length(this,300);" cssStyle="width:400px;height:100px"/>
             	<@s.fielderror><@s.param>memberleave.content</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">信息来源：</td>
             <td>
             	<@s.textfield name="memberleave.source" cssClass="txtinput" maxLength="20"/>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Memberleave_list.action';document.forms[0].submit();"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>