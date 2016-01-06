<html>
<head>
<title>答案列表</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
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
<@s.form action="/member_Answer_isselectlist.action" method="post">
<table width="84%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>知道管理>提问管理>答案列表</td>
 </tr>
</table>
  <div class="cont_main">  
    <table width="100%" border="0" bgcolor="#EFF2F9"  cellpadding="7" class="cont_title">
      <tr>
       <td>问题："${(title_s)?if_exists}"</td>
      </tr>
    </table>
    <table width="100%" cellspacing="0" border="0">

   <#list answerList as ans>

   <tr bgcolor="<#if ans_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td align="left" style="padding-left:20px;">
       <span class="greencolor">答案：</span>
       ${ans.answer_desc?if_exists}
       <#if ans.isselect?if_exists=='1'> <span class="redcolor">[最佳]</span></#if><br/>  
     <#if (ans.answer_state)?if_exists=='0'>
       <input type="button" value="设为最佳答案" onclick="selectgood('${ans.answer_id?if_exists}');"/>
      </#if>
     	<span class="bluecolor">参考资料:</span>${ans.refer_data?if_exists}<br/>
     	<span class="bluecolor">回答人:</span><#if (ans.answer_name)?if_exists!=''>${(ans.answer_name)?if_exists}<#else>匿名</#if>&nbsp;&nbsp;${(ans.in_date)?if_exists}
    </td>
  </tr>
    </#list>
    </table>
    
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
  <@s.hidden name="ask_id_s" id="ask_id" value="${ask_id_s?if_exists}"/>
</@s.form>
</body>
</html>
