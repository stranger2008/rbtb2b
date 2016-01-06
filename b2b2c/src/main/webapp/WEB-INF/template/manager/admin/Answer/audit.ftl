<html>
  <head>
    <title>审核答案</title>
  </head>
  <body>

<div class="tj_main f_left">
  <div class="pageLocation">
 	当前位置:功能模块 > 知道管理 > 答案列表 > 审核答案
  </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Answer_auditstate.action" method="post">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">问题内容:</td>
             <td>
             	<@s.label name="answer.ask_title" />
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">答案内容:</td>
             <td>
             	<@s.label name="answer.answer_desc" />
             </td>
           </tr>          
            <tr>
             <td class="table_name">参考资料:</td>
             <td>
               ${answer.refer_data?if_exists}
             </td>
           </tr> 
            <tr>
             <td class="table_name">最佳答案:</td>
             <td>
                <@s.radio name="answer.isselect" list=r"#{'0':'否','1':'是'}" />
                <@s.fielderror><@s.param>answer.isselect</@s.param></@s.fielderror>
             </td>
           </tr> 
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="ask_id_s"/>
	       <@s.hidden name="answer.answer_id"/>
	       <@s.hidden name="answer.info_state" value="1"/>
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="审核"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Answer_list.action?ask_id_s=${(answer.ask_id)?if_exists}';document.forms[0].submit();"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>