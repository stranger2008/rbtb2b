<html>
  <head>
    <title>查看留言</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 留言管理 > 查看留言
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Memberleave_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td width="19%" class="table_name">标题名称：</td>
             <td>
             	 <@s.label name="memberleave.title" cssClass="txtinput"/>
             </td>
           </tr>
           <#if memberleave.send_cust_id?if_exists!='0'>
            <tr>
             <td class="table_name">留言会员名称：</td>
             <td>
             	 <@s.label name="send_cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <#else>
            <tr>
             <td class="table_name">留言用户名称：</td>
             <td>
             	 <@s.label name="memberleave.send_user_name" cssClass="txtinput"/>
             </td>
           </tr>
           </#if>
            <tr>
             <td class="table_name">接收人：</td>
             <td>
             	 <@s.label name="get_cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">留言内容：</td>
             <td>
             	 <@s.label name="memberleave.content" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">留言时间：</td>
             <td>
             	 <@s.label name="memberleave.in_date" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="memberleave.is_del" list=r"#{'0':'有效','1':'回收站'}"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="memberleave.leave_id"/>
	       
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