<html>
  <head>
    <title>添加答案</title>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	 当前位置:功能模块 > 知道管理 > 答案列表 > 添加答案
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Answer_insert.action" method="post">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">问题描述:</td>
             <td>
             	 ${title_s?if_exists}
             </td>
           </tr> 
           <tr>
             <td class="table_name">答案内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="answer.answer_desc" cssClass="txtinput" rows="5" cssStyle="width:500px;" onkeyup="set_textarea_length(this,600);"/>
             	<@s.fielderror><@s.param>answer.answer_desc</@s.param></@s.fielderror>
             </td>
           </tr>          
            <tr>
             <td class="table_name">参考资料<font color='red'>*</font></td>
             <td>
                <@s.textarea name="answer.refer_data" cssClass="txtinput" cssStyle="width:500px;height:100px;" />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js" ></script>
				<script type="text/javascript" >
			     CKEDITOR.replace( 'refer_data');  
				</script>
				<@s.fielderror><@s.param>answer.refer_data</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">最佳答案:</td>
             <td>
             	<@s.radio name="answer.isselect" list=r"#{'0':'否','1':'是'}" value="0"/>
             	<@s.fielderror><@s.param>answer.isselect</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">信息状态:</td>
             <td>
             	<@s.radio name="answer.info_state" list=r"#{'0':'未审核','1':'审核通过'}" value="1"/>
             	<@s.fielderror><@s.param>answer.info_state</@s.param></@s.fielderror>
             </td>
           </tr> 
            
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="answer.ask_id"/>
	       <@s.hidden name="answer.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Answer_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>