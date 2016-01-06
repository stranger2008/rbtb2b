<html>
  <head>
    <title>审核知道</title>
     <script src="/include/js/admin/ask.js" type="text/javascript"></script>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:功能模块 > 知道管理 > 审核知道列表 > 审核知道
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Ask_auditState.action" method="post" validate="true" onsubmit="return noreasron('ask.info_state','noreason','2');">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">问题标题:</td>
             <td>
             	<@s.label name="ask.title"/>
             </td>
           </tr> 
           <tr>
             <td  width="19%" class="table_name" >所属分类:</td>
             <td>
                ${(buy.cat_attr)?if_exists}       	            
             </td>
           </tr>
          <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>    
           <tr>
             <td class="table_name">标题图片:</td>
             <td>
             	<#if (ask.img_path)?if_exists != ''>
                	<img src="${ask.img_path?if_exists}" />
                </#if>
             </td>
           </tr>
            <tr>
             <td class="table_name">问题说明:</td>
             <td>
                <@s.label name="ask.ask_desc"/>
             </td>
           </tr> 
            <tr>
             <td class="table_name">补充信息:</td>
             <td>
             	<@s.label name="ask.add_desc" />
             </td>
           </tr> 
            <tr>
             <td class="table_name">悬赏积分:</td>
             <td>
                 <@s.label name="ask.integral" />
             </td>
           </tr> 
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
                <#if (ask.is_recom)?if_exists=='0'>否<#else>是</#if>
             </td>
           </tr>  
            <tr>
             <td class="table_name">回答状态:</td>
             <td>
             	<#if (ask.answer_state)?if_exists=='0'>未解决<#elseif (ask.answer_state)?if_exists=='1'>已解决<#else>已关闭</#if>
             </td>
           </tr>  
            <tr>
             <td class="table_name">发布时间:</td>
             <td>
                <@s.label name="ask.in_date"/>
             </td>
           </tr> 
            <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.label name="ask.clicknum"/>
             </td>
           </tr> 
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.label name="ask.fare"/>
             	( 默认"0"则免费!) 
             </td>
           </tr> 
            <#if if_opt_audit=="1">
           <tr>
             <td class="table_name">审核状态:</td>
             <td>
                <@s.radio name="ask.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('ask.info_state','infostate','noreason','2');"/>
             </td>
           </tr>
           <tr id="infostate" style="display:<#if infostateTip=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="reason" name="ask.no_reason" cssClass="txtinput" cssStyle="width:600px;"  maxLength="100" id="noreason"/>
             	<@s.fielderror><@s.param>ask.no_reason</@s.param></@s.fielderror>
             </td>
           </tr> 
           </#if>
         </table>
         <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="ask.ask_id"/> 
	       <@s.hidden name="ask.cust_id" />
	       
	       ${listSearchHiddenField?if_exists}
           <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Ask_auditList.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>