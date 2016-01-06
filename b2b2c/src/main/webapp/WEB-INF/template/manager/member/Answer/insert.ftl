<html>
<head>
<title>回答问题</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Answer_insert.action" method="post" validate="true">
       <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:信息管理>知道管理>回答管理>回答问题</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
         <tr>
             <td width="19%" valign="middle" class="left_td">问题描述:</td>
             <td>
                  ${title_s?if_exists}
             </td>
         </tr>  
         <tr>
             <td valign="middle" class="left_td">答案内容:</td>
             <td>
             	<@s.textarea name="answer.answer_desc" cssClass="txtinput" rows="5" cssStyle="width:500px;" onkeyup="set_textarea_length(this,600);"/>
             	<@s.fielderror><@s.param>answer.answer_desc</@s.param></@s.fielderror>
             </td>
          </tr>          
          <tr>
             <td valign="middle" class="left_td">参考资料:</td>
             <td>
				<@s.textarea name="answer.refer_data" cssClass="txtinput" cssStyle="width:500px;height:100px;"/>
				<@s.fielderror><@s.param>answer.refer_data</@s.param></@s.fielderror>
           </td>
       </tr> 
	   <tr>
	    <td colspan="4" class="subbut">
	      <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
          <@s.hidden name="answer.ask_id"/>
	      <@s.hidden name="answer.cust_id"/>
	      <@s.hidden name="answer.info_state" value="0" />
	      <@s.hidden name="answer.isselect" value="0"/>
	      ${listSearchHiddenField?if_exists}
	      <@s.submit value="保存"  cssClass="sub"/>
	      <input type="button" name="returnList" class="sub" value="返回列表" 
		 onclick="document.forms[0].action='/member_Answer_list.action';document.forms[0].submit();"/>
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
