<html>
  <head>
    <title>审核展会</title>
	 <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
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
 	当前位置:功能模块 > 展会管理 > 展会审核列表 > 审核展会
   </div>
   <div class="tj_main_cont">
    <@s.form id="supplyadd" action="/admin_Showinfo_auditState.action" method="post" validate="true" onsubmit="return noreasron('showinfo.info_state','noreasonvalue');">   
       <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:150px;">展会标题:</td>
             <td colspan="3">
              <@s.label name="showinfo.title" />
             </td>
           </tr>
             <tr> 
             <td class="table_name"  >标题图片:</td>
             <td colspan="3" >
               <@s.hidden name="showinfo.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>
             </td>     
           </tr> 
           <tr>
            <td class="table_name">所属分类:</td>
             <td colspan="3" >
              <@s.label name="showinfo.cat_attr" />
             </td>
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>
           <tr>
           <td class="table_name" width="150px">主办单位:</td>
             <td width="400px">
             <@s.label name="showinfo.host_unit" />
             </td>
            <td class="table_name" width="150px">承办单位:</td>
             <td>
              <@s.label name="showinfo.sponsor" />
             </td>
           </tr>
            <tr>
               <td class="table_name">展会开展日期:</td>
             <td >
            <#if showinfo.start_date?if_exists?length lt 10>
             	${showinfo.start_date?if_exists}            
             <#else>
              	${showinfo.start_date?if_exists[0..9]}          
             </#if>
             	
             </td>
              <td class="table_name">展会结束日期:</td>
             <td >
             <#if showinfo.end_date?if_exists?length lt 10>
             	${showinfo.end_date?if_exists}            
             <#else>
              	${showinfo.end_date?if_exists[0..9]}          
             </#if>
             </td>
           </tr>
         
           
           <tr>
             <td class="table_name">详细地址:</td>
             <td  >
               <@s.label name="showinfo.address" />
             </td>
              <td class="table_name">展馆名称:</td>
             <td  >
             <@s.label name="showinfo.hall_name" />
             
             </td>
           </tr>
           <tr>
             <td class="table_name">所属地区:</td>
             <td  >
                <@s.label name="showinfo.area_attr" />
             </td>
              <td class="table_name">展会备注:</td>
             <td  >
              <@s.label name="showinfo.remark" />
              
             </td>

           </tr>
           <tr>
             <td class="table_name">展会描述:</td>
             <td colspan="3">
              <div style="padding:10px 200px 10px 0">
                 ${(showinfo.exh_desc)?if_exists}
             	 </div>
             	
             </td>
           </tr>
             <tr>
             
              <td class="table_name">联系人:</td>
             <td >
             <@s.label name="showinfo.contact_man" />
             
             </td>
               <td class="table_name">联系电话:</td>
             <td >  
              <@s.label name="showinfo.phone" />
             	
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">联系手机:</td>
             <td >
              <@s.label name="showinfo.cellphone" />
            
             </td>
               <td class="table_name">联系地址:</td>
             <td >  
              <@s.label name="showinfo.contact_addr" />
             
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">联系传真:</td>
             <td >
               <@s.label name="showinfo.fax" />           
             </td>
               <td class="table_name">email:</td>
             <td >  
               <@s.label name="showinfo.email" />
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">MSN:</td>
             <td >
              <@s.label name="showinfo.msn" />          	
             </td>
               <td class="table_name">QQ:</td>
             <td >  
                 <@s.label name="showinfo.qq" />
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">是否推荐:</td>
             <td >
              <#if showinfo.is_recom?if_exists='0'>否<#elseif showinfo.is_recom?if_exists='1'>是</#if>
             </td>
               <td class="table_name">点击量:</td>
             <td >  
              <@s.label name="showinfo.clicknum" />
             </td>
           </tr>  
             <tr>
              <td class="table_name">内容收费:</td>
             <td colspan="3" >
              <@s.label name="showinfo.fare" />( 默认"0"则免费!)
             </td>
             </td>
           </tr>
            <#if if_opt_audit=="1">
                  <tr>
             <td class="table_name">审核状态</td>
             <td colspan="3">
             	<@s.radio name="showinfo.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('showinfo.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>showinfo.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid" >
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="showinfo.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>showinfo.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="showinfo.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>showinfo.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="showinfo.exh_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="showinfo.cust_id" />
	        <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核" style="cursor:pointer;"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核" style="cursor:pointer;"/>
	       </#if> 
	        
        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Showinfo_auditList.action','');"/>
	     </div>
	     
	  </@s.form>  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>