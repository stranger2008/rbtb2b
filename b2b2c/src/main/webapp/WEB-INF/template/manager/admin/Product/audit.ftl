<html>
  <head>
    <title>审核产品</title>
	 <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	 <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	 <script type="text/javascript">
		 $(document).ready(function(){ 
		  disabledCss();
		 firstaddimges("mytest","addimg","100","100");
		 });
	 </script>
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 产品管理 > 产品审核列表 > 审核产品
   </div>
   <div class="tj_main_cont">
   	<@s.form id="supplyadd" action="/admin_Product_auditState.action" method="post" validate="true" onsubmit="return noreasron('product.info_state','noreasonvalue');">   
       <table class="wwtable" cellspacing="1" cellpadding="8">
          <tr>
             <td class="table_name" width="150px">产品标题:</td>
             <td >
               <@s.label name="product.title" />
             </td>
           </tr>
             <tr>
             <td class="table_name"  >产品图片:</td>
             <td >
              <@s.hidden name="product.img_path" id="mytest"/>   
               <div id="addimg">
               </div>
               
             </td>
           </tr>

           </tr>
           <tr>
            <td class="table_name">所属分类:</td>
             <td >
                <@s.label name="product.cat_attr" />
             </td>    
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>  
           <td class="table_name">所在地区:</td>
             <td>
                 ${product.area_attr?if_exists}
             </td>
           </tr>
            <tr>
               <td class="table_name">产品型号:</td>
             <td >
                <@s.label name="product.model" />
             </td>
           </tr>
           <tr>
             
              <td class="table_name">产品规格:</td>
             <td >
                <@s.label name="product.standard" />
             </td>
              
           </tr>
           <tr>
             <td class="table_name">产品品牌:</td>
             <td >
                <@s.label name="product.brand" />
             </td>
              
           </tr>
           <tr>
             <td class="table_name">自定义产品分类:</td>
             <td  >
             ${product.self_cat_id?if_exists}
             </td>
           </tr>
            <tr>
             <td class="table_name">详细说明:</td>
             <td >
              <div style="padding:10px 200px 10px 0">
                ${product.content?if_exists}
               </div>
             </td>
           </tr>
              <tr> 
             <td class="table_name">是否推荐:</td>
             <td >
                <#if product.is_recom?if_exists='0'>否<#elseif product.is_recom?if_exists='1'>是</#if>
             </td>
           </tr>
             <tr> 
             <td class="table_name">点击量:</td>
             <td >
                <@s.label name="product.clicknum" />
             </td>
           </tr>
             <tr> 
             <td class="table_name">内容收费:</td>
             <td >
                <@s.label name="product.fare" />  ( 默认"0"则免费!)
             </td>
           </tr>
            <#if if_opt_audit=="1">
             <tr>
             <td class="table_name">审核状态</td>
             <td>
             	<@s.radio name="product.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('product.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>product.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="product.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>product.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="product.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>product.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="product.product_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="product.cust_id" />
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核" style="cursor:pointer;"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核" style="cursor:pointer;"/>
	       </#if> 
	        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Product_auditList.action','');"/>
	     </div>
	     
	  </@s.form>  
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>