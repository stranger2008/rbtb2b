<html>
  <head>
    <title>查看简历收件箱</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 查看简历收件箱
   </div>
   <div class="tj_main_cont">
   	<@s.form  method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name" width="20%">企业标识:</td>
             <td>
              <@s.label name="com_name" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td class="table_name">职位名称:</td>
             <td>
             <a href="/admin_Job_audit.action?flageJob=1&job.job_id=${resumeinbox.job_id?if_exists}" style="color:blue;">
             <@s.label name="resumeinbox.title"/></a>
              
             </td>
           </tr>
         <tr>
             <td class="table_name">简历名称:</td>
             <td><a href="/admin_Resume_audit.action?flageResume=1&resume.resume_id=${resumeinbox.resume_id?if_exists}" style="color:blue;">
             <@s.label name="resumeinbox.resume_name"/></a>      
             </td>
           </tr>
              <tr>
             <td class="table_name" >状态:</td>
             <td>
            <#if resumeinbox.state?if_exists='0'><@s.label value="未查看"/>
   		     <#elseif resumeinbox.state?if_exists='1' ><@s.label value="已查看"/>
   		     </#if>
             </td>
           </tr>
             <tr>
             <td class="table_name" >面试邀请:</td >
             <td>
             <#if resumeinbox.isinvite?if_exists='0'><@s.label value="未邀请"/>
             <#elseif  resumeinbox.isinvite?if_exists='1' ><@s.label value="已邀请"/>
              </#if>
             </td>
           </tr>
             <tr>
             <td class="table_name" >应聘时间:</td>
             <td>
              <@s.label name="resumeinbox.apply_date"/>
             </td>
           </tr>
             <tr>
             <td class="table_name" >应聘会员标识:</td>
             <td>
              <@s.label name="apply_name"/>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="resumeinbox.inbox_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Resumeinbox_list.action','');"/>
	     </div>	     
	  </@s.form>
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>