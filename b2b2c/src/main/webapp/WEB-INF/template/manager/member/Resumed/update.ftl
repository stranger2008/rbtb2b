<html>
<head>
<title>查看简历收件箱</title>
</head>
<body>
 <div class="cont_main">
 <@s.form   method="post" validate="true">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>招聘管理>查看我投的简历</td>
 	</tr>
	</table>
      <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">职位名称:</td>
    <td width="83%">
    <a href="/member_Job_audit.action?job.job_id=${resumeinbox.job_id?if_exists}" style="color:blue;">
             <@s.label name="resumeinbox.title"/></a>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">简历名称:</td>
    <td>
    <a href="/member_Resume_audit.action?flageResume=1&resume.resume_id=${resumeinbox.resume_id?if_exists}" style="color:blue;">
             <@s.label name="resumeinbox.resume_name"/></a>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">状态:</td>
    <td>
  <#if resumeinbox.state?if_exists='0'><@s.label value="未查看"/>
   		     <#elseif resumeinbox.state?if_exists='1' ><@s.label value="已查看"/>
   		     </#if>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">面试邀请:</td>
    <td>
    	 <#if resumeinbox.isinvite?if_exists='0'><@s.label value="未邀请"/>
             <#elseif  resumeinbox.isinvite?if_exists='1' ><@s.label value="已邀请"/>
              </#if>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">应聘时间:</td>
    <td>
   <@s.label name="resumeinbox.apply_date"/>
   </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="resumeinbox.inbox_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Resumed_list.action','');"/>
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
