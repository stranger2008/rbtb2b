<html>
  <head>
    <title>审核简历</title>
       <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js" ></script>
	 <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	  <script type="text/javascript">
	  $(document).ready(function(){    
	  
	   disabledCss();
         //图片展示
         firstaddimges("mypicid","addimg","100","100");
  	  });
	</script> 	
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 简历审核列表 > 审核简历
   </div>
   <div class="tj_main_cont">
    <@s.form id="supplyadd" action="/admin_Resume_auditState.action" method="post" validate="true" onsubmit="return noreasron('resume.info_state','noreasonvalue');">  
       <table class="wwtable" cellspacing="1" cellpadding="8" >
            <tr>
             <td class="table_name" width="150px">简历名称:</td>
             <td colspan="3">
                <@s.label name="resume.resume_name" /> 
             </td>
           </tr>
            <tr>
           <td class="table_name">所属分类:</td>
             <td colspan="3">
              <@s.label name="resume.cat_attr" /> 
             </td>
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>
              <tr>
           <td class="table_name">真实姓名:</td>
             <td colspan="3">
              <@s.label name="resume.real_name" /> 
             </td>
           </tr>
           <tr>
             <td class="table_name">照片:</td>
             <td colspan="3" >
              <@s.hidden name="resume.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>
             </td>
              
           </tr>
           <tr>
            <td class="table_name">性别:</td>
             <td  width="400px">
             <@s.label name="resume.sex" /> 
             </td>
           <td class="table_name" width="150px">婚姻状况:</td>
             <td >
             <@s.label name="resume.marry"  /> 
             </td>
           </tr>
            <tr>
               <td class="table_name">现居住地:</td>
             <td >
              <@s.label name="resume.area_attr" />           
             </td>
              <td class="table_name">生日:</td>
              <td >
               <@s.label name="resume.birth.substring(0,10)"  /> 
             </td>
           </tr>
           <tr>
              <td class="table_name">身高:</td>
             <td >
             <#if resume.height?if_exists!=''>
              	<@s.label name="resume.height" /> CM
             </#if>
                
             </td>
              <td class="table_name">体重:</td>
             <td >
	             <#if resume.weight?if_exists!=''>
	              <@s.label name="resume.weight" /> KG 
	             </#if>    
             </td>
           </tr>
           <tr>
             <td class="table_name">学历:</td>
             <td >
             <@s.label name="resume.education" />
             </td>
               <td class="table_name">毕业院校:</td>
             <td >  
                 <@s.label name="resume.college" /> 
             </td>
           
           </tr>
         <tr>
             <td class="table_name"  >所学专业:</td>
             <td >
              <@s.label name="resume.spec" /> 
             </td>
              <td class="table_name"  >期望月薪:</td>
             <td >
               <@s.label name="resume.salary" />  
             </td>
           </tr>   
             <tr>
              <td class="table_name" >工作性质:</td>
             <td >
              <@s.label name="resume.job_type" /> 
             </td>
             <td class="table_name"  >工作经验:</td>
             <td >
              <@s.label name="resume.work_exper" /> 
             </td>
           </tr>
            <tr>
              <td class="table_name">专业技能:</td>
             <td >
              <@s.label name="resume.technical" /> 
             </td>
              <td class="table_name">语言水平:</td>
             <td >
              <@s.label name="resume.language" /> 
             </td>
           </tr> 
             <tr>
             <td class="table_name"  >自我鉴定:</td>
             <td colspan="3">
              <div style="padding:10px 200px 10px 0">
                 ${resume.self_desc?if_exists}
             	 </div>
                      
             </td>
             </tr>
            <tr>
             <td class="table_name">联系手机:</td>
             <td >
              <@s.label name="resume.cellphone" /> 
             </td>
              <td class="table_name"  >联系电话:</td>
             <td colspan="3">
              <@s.label name="resume.phone" /> 
             </td>
           </tr>
            <tr>
             <td class="table_name"  >电子邮箱:</td>
             <td >
              <@s.label name="resume.email" /> 
             </td>
             <td class="table_name">联系地址:</td>
             <td >
              <@s.label name="resume.address" /> 
             </td>          
           </tr>
            <tr>
              <td class="table_name">MSN:</td>
             <td >
              <@s.label name="resume.msn" /> 
             </td>
           
              <td class="table_name">QQ</td>
             <td >
              <@s.label name="resume.qq" /> 	
             </td>
           </tr>
            <tr>
              <td class="table_name" >Skype：</td>
             <td >
              <@s.label name="resume.skype" /> 
             </td>
                 <td class="table_name">是否推荐:</td>
             <td >
               <#if resume.is_recom?if_exists='0'>否<#elseif resume.is_recom?if_exists='1'>是</#if>  	
             </td>
           </tr>
            <tr>
              <td class="table_name">点击量:</td>
             <td >
              <@s.label name="resume.clicknum" /> 
             </td>
           
              <td class="table_name">内容收费</td>
             <td >
          <@s.label name="resume.fare" /> ( 默认"0"则免费!)
             </td>
           </tr> 
            <#if if_opt_audit=="1">
           <tr>
              <td class="table_name">审核状态:</td>
             <td>
               <#if flageResume=="0">
               <@s.radio name="resume.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('resume.info_state','reasonid','noreasonvalue','2');" />
               <#else>
                 <#if resume.info_state?if_exists=="0">未审核
                 <#elseif resume.info_state?if_exists=="1">审核通过
                 <#elseif resume.info_state?if_exists=="2">审核未通过卖
                 <#elseif resume.info_state?if_exists=="3">禁用
                 </#if>
               </#if>
               <@s.fielderror><@s.param>resume.info_state</@s.param></@s.fielderror>
                     	
             </td>
              <td class="table_name">是否委托:</td>
             <td> 
                 <#if resume.is_trust==0>否<#else>是</#if>
             </td>
               
           </tr>
           
           <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid" >
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="resume.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>resume.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="resume.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>resume.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="resume.resume_id"/>
	       <@s.hidden name="resume.cust_id" />
	       
	       ${listSearchHiddenField?if_exists}
	        <#if flageResume=="0">
	           <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核" style="cursor:pointer;"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核" style="cursor:pointer;"/>
	       </#if> 
	            <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Resume_auditList.action','');"/>
	       <#elseif flageResume=="1">
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Resumeinbox_list.action','');"/>
	       <#elseif flageResume=="2">
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Jobtalent_list.action','');"/>
	       </#if>
	     </div>
	     
	  </@s.form>  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>