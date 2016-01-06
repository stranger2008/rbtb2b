<html>
  <head>
    <title>审核分类信息</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
    <script type="text/javascript">
	  $(document).ready(function(){
	     disabledCss();         
	  });
	</script> 
	<script type="text/javascript">
	  $(document).ready(function(){    
         disabledCss();
         //图片展示
         firstaddimges("uploadresult","showImageDiv","100","100");
  	  });
	</script> 
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:功能模块 > 分类信息管理 > 审核分类信息列表 > 审核分类信息
 </div>
 <div class="tj_main_cont">
 <@s.form action="/admin_Classified_auditstate.action" method="post" validate="true" onsubmit="return noreasron('classified.info_state','noreasonvalue','2');">
 <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="20%" class="table_name">信息分类标题:</td>
             <td>
             	<@s.label name="classified.title" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属分类:</font></td>
             <td>
                ${(classified.cat_attr)?if_exists}   
             </td>
           </tr>
           
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if> 
           
            <tr >
             <td class="table_name" >所在地区:</td>
             <td >
                 ${(classified.area_attr)?if_exists}            	           	            
             </td>
           </tr>
           
           <tr class="table_name">
             <td class="table_name" >信息图片:</td>
             <td align="left">
	               <input id="uploadresult" type="hidden" value="${classified.img_path?if_exists}"/>
                   <div id="showImageDiv"></div>	                      	            
	        </td>
	       </tr> 
            <tr>
             <td class="table_name">补充说明:</td>
             <td>
				${classified.info_desc?if_exists}
             </td>
           </tr>
            <tr>
             <td width="20%" class="table_name">联系人:</td>
             <td>
             	<@s.label name="classified.contact" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td width="20%" class="table_name">手机或电话:</td>
             <td>
             	<@s.label name="classified.phone" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td width="20%" class="table_name">qq或msn:</td>
             <td>
             	<@s.label name="classified.qqmsn" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td width="20%" class="table_name">地址:</td>
             <td>
             	<@s.label name="classified.address" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
               <#if (classified.is_recom)?if_exists=='0'>是<#else>否</#if>
             </td>
           </tr>
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.label name="classified.fare" cssClass="txtinput"/>
             	( 默认"0"则免费!) 
             </td>
           </tr>
              <#if if_opt_audit=="1">
             <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.radio name="classified.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('classified.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>classified.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey=='2'>
             <tr  id="reasonid">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>classified.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>classified.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="classified.info_id"/>
	       <@s.hidden name="classified.cust_id" />
	       ${listSearchHiddenField?if_exists}
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if> 
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Classified_auditList.action';document.forms[0].submit();"/>
	     </div>
	  </@s.form>
	  </div>
   </div>
</div>

</div>
<div class="clear"></div>
  
  </body>
</html>