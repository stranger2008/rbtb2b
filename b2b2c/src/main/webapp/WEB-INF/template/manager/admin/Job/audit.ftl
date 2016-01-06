<html>
  <head>
    <title>审核招聘</title>
      <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js" ></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
   	<script type="text/javascript">
		 $(document).ready(function(){ 
		  disabledCss();
		 });
	 </script>
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 招聘审核列表 > 审核招聘
   </div>
   <div class="tj_main_cont">
    <@s.form id="supplyadd" action="/admin_Job_auditState.action" method="post" validate="true" onsubmit="return noreasron('job.info_state','noreasonvalue');">  
       <table class="wwtable" cellspacing="1" cellpadding="8" >
           <tr>
             <td class="table_name" width="150px">信息标题:</td>
             <td colspan="3">
                <@s.label name="job.title" />
             </td>
           </tr>
            <tr>
             <td class="table_name">所属分类:</td>
             <td colspan="3">
              <@s.label name="job.cat_attr" />
             </td>
        
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>
           <tr>
               <td class="table_name">工作地区:</td>
             <td width="400px" >
             <@s.label name="job.area_attr" />
             </td>
              <td class="table_name" width="150px">工作性质:</td>
             <td>
             <@s.label name="job.job_type" />
             </td>
           </tr>
           <tr>
            <td class="table_name">招聘人数:</td>
             <td >
              <@s.label name="job.job_num" /> 人
             </td>
           <td class="table_name">招聘部门:</td>
             <td >
              <@s.label name="job.org_name" />
             </td>
           </tr>
            <tr>
               <td class="table_name">性别要求:</td>
             <td >
             <@s.label name="job.sex" />
             
             </td>
              <td class="table_name">待遇水平:</td>
              <td >
              <@s.label name="job.salary" />
             </td>
           </tr>
           <tr>
             
              <td class="table_name">学历要求:</td>
             <td >
               <@s.label name="job.enducation" />
             </td>
              <td class="table_name">婚姻要求:</td>
             <td >
              <@s.label name="job.marry" />
             </td>
           </tr>
           <tr>
             <td class="table_name">工作经验:</td>
             <td >
             	 <@s.label name="job.workexper" /> 年
             </td>
               <td class="table_name">年龄要求</td>
             <td >   
	             <#if (ageStartString?if_exists !='')&&(ageEndString?if_exists !='')>  
	             	 <@s.label name="ageStartString" /> 至
	             	 <@s.label name="ageEndString" /> 周岁
             	 <#elseif (ageStartString?if_exists !='')&&(ageEndString?if_exists =='')>  
	             	 <@s.label name="ageStartString" />周岁 至
	             	 -- 
             	 <#elseif (ageStartString?if_exists =='')&&(ageEndString?if_exists !='')>  
             	 -- 至
             	 <@s.label name="ageEndString" /> 周岁
	             </#if>
             </td>
           
           </tr>
           <tr>
             <td class="table_name"  >联系地址:</td>
             <td >
             	 <@s.label name="job.address" />
             </td>
              <td class="table_name"  >过期时间:</td>
             <td >
                 <#if job.end_date?if_exists?length lt 10>
	             	${job.end_date?if_exists}            
	             <#else>
	              	${job.end_date?if_exists[0..9]}          
	             </#if>
             </td>
           </tr>
            
            <tr>
             <td class="table_name">职位描述:</td>
             <td colspan="3"> 
                 <div style="padding:10px 200px 10px 0">
                 ${job.job_desc}
             	 </div>
             </td>
           </tr>       
             <tr>
              <td class="table_name" >联系人:</td>
             <td >
             	 <@s.label name="job.contact_name" />
             </td>
             <td class="table_name"  >电子邮件:</td>
             <td >
             	 <@s.label name="job.email" />
             </td>
             
           </tr> 
            <tr>
             <td class="table_name">联系电话:</td>
             <td >
             	 <@s.label name="job.phone" />
             </td>
            
             <td class="table_name">联系手机:</td>
             <td >
             	 <@s.label name="job.cellphone" />
             </td>
           </tr>
            <tr>
             <td class="table_name"  >MSN:</td>
             <td >
             	 <@s.label name="job.msn" />
             </td>
              <td class="table_name"  >内容收费:</td>
             <td colspan="3">
               <@s.label name="job.fare" />( 默认"0"则免费!) 
             </td>
             
           </tr>
            <tr>
             <td class="table_name">Skype:</td>
             <td >
             	 <@s.label name="job.skype" />
             </td>
              <td class="table_name">是否推荐:</td>
             <td >
               <#if job.is_recom?if_exists='0'>否<#elseif job.is_recom?if_exists='1'>是</#if>    	
             </td>
           </tr>
            <tr>
              <td class="table_name">QQ:</td>
             <td >
              <@s.label name="job.qq" />
             </td>
              <td class="table_name">点击量:</td>
             <td >
                <@s.label name="job.clicknum" />      	
             </td>
           </tr>  
            <tr>
              <td class="table_name">招聘类型:</td>
             <td >
              <#if job.is_where==0>国内招聘<#else>国外招聘</#if>
             </td>
              <td class="table_name">是否委托:</td>
             <td >
                <#if job.is_trust==0>否<#else>是</#if>   	
             </td>
           </tr>    
            <#if if_opt_audit=="1"> 
           <tr>
             <td class="table_name"  >审核状态:</td>
             <td  colspan="3">
               <#if flageJob=="0">
                <@s.radio name="job.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('job.info_state','reasonid','noreasonvalue','2');" />
               <#else>
                 <#if job.info_state=="0">未审核
                 <#elseif job.info_state=="1">审核通过
                 <#elseif job.info_state=="2">审核未通过
                  <#elseif job.info_state=="3">禁用
                 </#if>
               </#if>
               <@s.fielderror><@s.param>job.info_state</@s.param></@s.fielderror>
             </td>   
           </tr>    
           <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid" >
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="job.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>job.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="job.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>job.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>   
           </#if>
         </table>
         <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="job.job_id"/>
	       <@s.hidden name="job.cust_id" />
	       
	       ${listSearchHiddenField?if_exists}
	       <#if flageJob=="0">
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核" style="cursor:pointer;"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核" style="cursor:pointer;"/>
	       </#if> 
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Job_auditList.action','');"/>
	        <#else>
	         <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Resumeinbox_list.action','');"/>
	        </#if>
	     </div>
	     
	  </@s.form>  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>