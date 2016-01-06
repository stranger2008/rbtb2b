<html>
  <head>
    <title>审核会员</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="tj_main_cont">
   	<@s.form action="/admin_Member_auditstate.action" method="post" validate="true" onsubmit="return noreasron('member.info_state','noreason','2');">
      
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员基本信息</td>
           </tr>  
           <tr>
            <td style="width:20%;" class="table_name">会员类型</td>
             <td colspan="3">
                 <#if member.mem_type=='0'>企业<#else>个人</#if>  	
             </td>
           </tr>
           <tr>
             <td class="table_name">会员名称</td>
             <td colspan="3">
             	<@s.label name="member.cust_name" />
             </td>
           </tr>
           <tr>
             <td class="table_name">企业LOGO:</td>
             <td colspan="3">
                <#if (member.logo_path)?if_exists!=''>
             	  <img src="${(member.logo_path)?if_exists}" width="200px" height="200px"/>
             	</#if>
             </td>
           </tr>
            <tr>
              <td class="table_name" >联系人姓名:</td>
             <td style="width:30%;">
             	<@s.label name="member.contact_name"/>
             </td>
             <td style="width:20%;" class="table_name">联系人性别:</td>
             <td style="width:30%;">
                <@s.label name="member.contact_sex"/> 
             </td>  
           </tr>
            <tr>
             <td class="table_name">联系手机:</td>
             <td>
             	<@s.label name="member.contact_cellphone"/>
             </td>
              <td class="table_name">联系人MSN:</td>
             <td>
             	<@s.label name="member.contact_msn"/>
             </td>
           </tr> 
           <tr>
             <td class="table_name">联系人QQ:</td>
             <td>
             	<@s.label name="member.contact_qq"/>
             </td>
             <td class="table_name">电子邮箱:</td>
             <td >
             	<@s.label name="member.email" />
             </td>
           </tr>
           <tr>
             <td class="table_name">邮政编码:</td>
             <td>
             	<@s.label name="member.post_code"/>
             </td>
             <td class="table_name">会员注册日期:</td>
             <td>
                <@s.label name="member.in_date.substring(0,19)"/>
             </td>
           </tr> 
          <tr>
             <td class="table_name">联系人部门:</td>
             <td>
             	<@s.label name="member.contact_job"/>
             </td>
             <td class="table_name">联系人职位:</td>
             <td>
             	<@s.label name="member.contact_depart"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">所在地区:</td>
             <td colspan="3">
             	<@s.label name="member.area_attr"/>
             </td>
           </tr>
          <tr>
             <td class="table_name">所属分类:</td>
             <td colspan="3">
             	<@s.label name="member.cat_attr"/>
             </td>
           </tr>
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">企业基本信息</td>
           </tr>   
            <tr>
             <td class="table_name">企业类别:</td>
             <td colspan="3">
                <#if member.cust_rage=='0'>供应商<#elseif member.cust_rage=='1'>采购商<#elseif member.cust_rage=='2'>二者皆有<#else></#if> 
             </td>
           </tr>
           
            <tr>
             <td class="table_name">供应产品（服务）:</td>
             <td colspan="3">
             	<@s.label name="member.cust_supply" cssClass="txtinput"  maxLength="200" rows="3"  cssStyle="width:600px;"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">采购产品（服务）:</td>
             <td colspan="3">
             	<@s.label name="member.cust_stock" cssClass="txtinput" maxLength="200" rows="3" cssStyle="width:600px;"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">企业类型:</td>
             <td colspan="3">
                ${(member.cust_type)?if_exists}
             </td>  
           </tr>
            <tr>
             <td class="table_name">经营模式:</td>
             <td colspan="3">
                ${(member.client_status)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">公司关健词:</td>
             <td colspan="3">
             	<@s.label name="member.cust_key" cssClass="txtinput" maxLength="200" rows="3" cssStyle="width:600px;"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司经营范围:</td>
             <td colspan="3">
             	<@s.label name="member.main_product" cssClass="txtinput"  maxLength="200" rows="3" cssStyle="width:600px;"/>
             </td>  
           </tr>
            <tr>
              <td class="table_name">公司介绍:</td>
             <td colspan="3" style="line-height:20px;">
                <@s.label name="member.cust_desc"/>
             </td>
           </tr>

            <tr>
             <td class="table_name">经营地址:</td>
             <td colspan="3">
             	<@s.label name="member.address"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">公司电话:</td>
             <td>
             	<@s.label name="member.phone"/> 
             </td> 
              <td class="table_name">公司传真:</td>
             <td>
             	<@s.label name="member.fax"/>
             </td> 
           </tr>
           <tr>
              <td class="table_name">公司网址:</td>
             <td colspan="3">
             	<@s.label name="member.website"/>
             </td>
           </tr>
           <tr>
              <td class="table_name">推荐:</td>
             <td colspan="3">
               <#if member.recommend=='0'>非推荐<#else>推荐</#if>
             </td>
          </tr>
          <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">企业详细信息</td>
           </tr>      
           <tr>
             <td class="table_name">公司法人代表:</td>
             <td colspan="3">
               <@s.label name="member.corporate" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
               <@s.fielderror><@s.param>member.corporate</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司规模:</td>
             <td colspan="3">
                ${(member.staff_num)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">注册资金:</td>
             <td colspan="3">
                <@s.label name="member.reg_money" />
                &nbsp;&nbsp;
                ${(member.reg_money_type)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">年销售额:</td>
             <td colspan="3">
                ${(member.year_sum)?if_exists}
             </td>  
           </tr>
           <tr>
             <td class="table_name">公司成立年份:</td>
             <td colspan="3"> 
               <@s.label name="member.reg_date" />
             </td>
           </tr>
           <tr>
              <td class="table_name">商标:</td>
             <td colspan="3">
             	<@s.label name="member.brand_name" />
             </td>
           </tr>
           <tr>
             <td class="table_name">营业执照号:</td>
             <td colspan="3">
             	<@s.label name="member.reg_no" />
             </td>
           </tr>
           <tr>
             <td class="table_name">是否OEM代加工:</td>
             <td colspan="3">
                ${(member.isoem)?if_exists}
             </td>
           </tr>   
          <tr>
             <td class="table_name">营业执照复印件:</td>
             <td colspan="3">     
                <#if (member.reg_no_path)?if_exists!=''>         
                   <img src="${(member.reg_no_path)?if_exists}" width="200px" height="200px"/>
                </#if>  
             </td>
           </tr>  
            <#if if_opt_audit=="1">
            <tr>
             <td class="table_name">审核状态:</font></td>
             <td colspan="3">
               <@s.radio name="member.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('member.info_state','state','noreason','2');"/>
               <@s.fielderror><@s.param>member.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr id="state" style="display:<#if statecodeTip=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Member_auditList.action','');"/>
	     </div>	 
	  </@s.form>
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>