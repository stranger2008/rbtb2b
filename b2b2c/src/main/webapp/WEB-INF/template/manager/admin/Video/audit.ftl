<html>
  <head>
    <title>审核视频</title>
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
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 视频管理 > 视频审核列表 > 审核视频
 </div>
 <div class="tj_main_cont">
 <@s.form action="/admin_Video_auditState.action" method="post" validate="true" onsubmit="return noreasron('video.info_state','noreasonvalue',2);">
 <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="20%" class="table_name">视频标题:</td>
             <td>
             	<@s.label name="video.title" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">标题图片:</td>
             <td>
              <@s.hidden name="video.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>
             </td>
           </tr>
              <tr>
             <td class="table_name" >所属分类:</td>
             <td>
                ${(video.cat_attr)?if_exists}       	            
             </td>
           </tr>
	          <#if sysmodule.is_catattr=="0">      
	           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
	          </#if>  
            <tr>
             <td class="table_name">视频说明:</td>
             <td>
				${video.video_desc?if_exists}
             </td>
           </tr>
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
               <#if (video.is_recom)?if_exists=='0'>否<#else>是</#if>
             </td>
           </tr>
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.label name="video.fare" cssClass="txtinput"/>
             	( 默认"0"则免费!)
             </td>
           </tr>
             <#if if_opt_audit=="1">
             <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.radio name="video.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('video.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>video.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="video.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>video.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="video.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>video.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="video.video_id"/>
	       <@s.hidden name="video.cust_id" />
	       
	       ${listSearchHiddenField?if_exists}
	      <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if> 
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Video_auditList.action';document.forms[0].submit();"/>
	     </div>
	  </@s.form>
	  </div>
   </div>
</div>

</div>
<div class="clear"></div>
  
  </body>
</html>