<html>
  <head>
    <title>回复</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 评论管理 > 回复
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membercomment_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
             <tr>
             <td class="table_name">文章名称：</td>
             <td>
             	 <@s.label name="membercomment.info_title" cssClass="txtinput"/>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">评论内容：</td>
             <td>
             	 <@s.label name="membercomment.content" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">审核:</td>
             <td>
             	<@s.radio name="membercomment.comm_state" list=r"#{'0':'审核通过','1':'未审核'}"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">回复：</td>
             <td>
                <@s.textarea name="membercomment.back_con" cssClass="txtinput" maxLength="300" cssStyle="width:300px;height:100px"
                onkeyup="set_textarea_length(this,300);" 	/>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="membercomment.comm_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercomment_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>