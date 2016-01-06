<html>
  <head>
    <title>审核品牌</title>
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
 	当前位置:功能模块 > 品牌管理 > 审核品牌列表 > 审核品牌
  </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Brand_auditState.action" method="post" validate="true" onsubmit="return noreasron('brand.info_state','noreason','2');">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">品牌名称:</td>
             <td>
             	<@s.label name="brand.title" />
             </td>
           </tr>
           <tr>
             <td class="table_name">品牌图片:</td>
             <td>    
             <@s.hidden name="brand.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>       
               
             </td>
           </tr>
           
           <tr>
             <td class="table_name">所属分类:</td>
             <td>
             	 <@s.label name="brand.cat_attr"/>
             </td>
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if> 
          <tr>
             <td class="table_name">所属地区:</td>
             <td>
             	 <@s.label name="brand.area_attr"/>
             </td>
          </tr>     
          
          <tr>
             <td class="table_name">官方主页:</td>
             <td>
             	<@s.label name="brand.web_url" />
             </td>
           </tr>
          
            <tr>
             <td class="table_name">品牌介绍:</td>
             <td>
                ${(brand.content)?if_exists}
             </td>
           </tr> 
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
                <#if (brand.is_recom)?if_exists=='0'>否<#else>是</#if>
             </td>
           </tr> 
            <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.label name="brand.clicknum" />
             </td>
           </tr> 
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.label name="brand.fare"/>( 默认"0"则免费!) 
             </td>
           </tr> 
            <#if if_opt_audit=="1">
            <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.radio name="brand.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('brand.info_state','state','noreason','2');"/>
                <@s.fielderror><@s.param>brand.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="state" style="display:<#if infostateTip?if_exists=='2'><#else>none</#if>">
             <td class="table_name">拒绝的理由<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="brand.no_reason"  cssStyle="width:600px;" id="noreason" maxLength="100"/>
             	<@s.fielderror><@s.param>brand.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>
         </table>
         <#if cfg_auditmodel=="0">
             <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="brand.brand_id"/>
	       <@s.hidden name="brand.cust_id" />
	       
	       ${listSearchHiddenField?if_exists}     
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Brand_auditList.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  </body>
</html>