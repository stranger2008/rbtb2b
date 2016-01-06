<html>
<head>
<title>答案信息</title>
<script type="text/javascript" >
function selectgood(answer_id) {
    var ask_id = $("#ask_id").val();
    $.ajax({  	 
    type: "post",    	     
    url: "answer!updateIntegral.action?answer_id_s="+answer_id+"&ask_id_s="+ask_id+"&isselect_s=1"+"&ajaxRequestRandom="+Math.random(),       
    datatype:"json",
    success: function(data){ 
      if(data == '1'){
         alertShow("设置问题的最佳答案成功",'succeed'); 
         window.location.href="/member_Answer_isselectlist.action?ask_id_s="+ask_id;
      }
    }  
   });
             			
	
}
</script>
</head>
<body>
 <div class="cont_main">
 <@s.form method="post" validate="true" >
 <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>知道管理>回答管理>答案信息</td>
 </tr>
</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
        <tr>
             <td width="19%" valign="middle" class="left_td">问题:</td>
             <td>
                ${answer.ask_title?if_exists} 
           </td>
       </tr>
        <tr style="height:100px;">
             <td  valign="middle" class="left_td">答案内容:</td>
             <td>
                <div style="table-layout:fixed; word-break: break-all; overflow:auto;">
             	 ${answer.answer_desc?if_exists}
             	</div>
             </td>
          </tr>          
          <tr>
             <td valign="middle" class="left_td">参考资料:</td>
             <td>
                ${answer.refer_data?if_exists}
           </td>
       </tr>
	    <td colspan="4" class="subbut">
	      <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	      <@s.hidden name="answer.answer_id" id="answer_id" value="${answer.answer_id?if_exists}"/>
	      <@s.hidden name="answer.ask_id" id="ask_id" value="${answer.ask_id?if_exists}"/>
	      <@s.hidden name="answer.info_state" />
	      ${listSearchHiddenField?if_exists}
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

