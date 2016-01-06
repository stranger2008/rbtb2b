<html>
  <head>
    <title>审核个人会员</title>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 未审核会员列表 > 审核个人会员
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Member_auditPersonState.action" method="post" validate="true" onsubmit="return noreasron('member.info_state','noreason','2');">
      
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td colspan="2" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员基本信息</td>
           </tr>
           <tr>
             <td width="15%" class="table_name">会员名称:</td>
             <td>
             	<@s.label name="member.cust_name" />
             </td>
           </tr>
           <tr>
             <td class="table_name">性别:</td>
             <td>
                 ${(member.contact_sex)?if_exists} 
             </td>
           </tr>
           <tr>
             <td class="table_name">个人头像:</td>
             <td>
                 <#if (member.logo_path)?if_exists!=''>
                    <img src="${(member.logo_path)?if_exists}" width="200" height="150"/>
                 </#if>
             </td>
           </tr>
           <tr>
             <td class="table_name">电子邮箱:</td>
             <td >
             	<@s.label name="member.email"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">所在地区:</td>
             <td>
                  <@s.label name="member.area_attr"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">电话:</td>
             <td>
             	<@s.label name="member.phone" /> 
             </td> 
           </tr>
            <tr>
             <td class="table_name">手机:</td>
             <td>
             	<@s.label name="member.contact_cellphone" />
             </td>
           </tr> 
           <tr>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.label name="member.contact_qq" />
             </td>
           </tr>
           <tr>
               <td class="table_name">MSN:</td>
             <td>
             	<@s.label name="member.contact_msn" />
             </td>
           </tr>
           <tr>
             <td class="table_name">邮政编码:</td>
             <td>
             	<@s.label name="member.post_code" />
             </td>
           </tr> 
          
            <tr>
             <td class="table_name">联系地址:</td>
             <td>
             	<@s.label name="member.address" />
             </td>
           </tr>
           <tr>
              <td class="table_name">推荐:</td>
             <td>
             	<#if (member.recommend)?if_exists=='0'>非推荐<#else>推荐</#if>
             </td>
          </tr>
           <#if if_opt_audit=="1">
          <tr>
             <td class="table_name">审核状态:</font></td>
             <td>
               <@s.radio name="member.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('member.info_state','state','noreason','2');"/>
               <@s.fielderror><@s.param>member.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr id="state" style="display:<#if statecodeTip=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="member.reason" rows="3"  cssStyle="width:600px;" id="noreason" onkeyup="set_textarea_length(this,300);"/>
             	<@s.fielderror><@s.param>member.reason</@s.param></@s.fielderror>
             </td>
           </tr>   
           </#if>                               
         </table>  
          <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="member.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if> 
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Member_auditList.action','');" />
	     </div>	 
	         
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
<!--cont结束-->
  </body>
</html>