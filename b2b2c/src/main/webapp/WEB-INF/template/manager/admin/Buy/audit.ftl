<html>
  <head>
    <title>审核求购</title>
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
 	当前位置:功能模块 > 求购管理 > 审核求购列表 > 审核求购
  </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Buy_auditState.action"  method="post" validate="true" onsubmit="return noreasron('buy.info_state','noreasonvalue','2');"> 	`
        <table class="wwtable" cellspacing="1" cellpadding="8"  >          
           <tr>
             <td class="table_name" width=300px;>求购标题:</td>
             <td>
             ${buy.title?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name" >求购类型:</td>
             <td>
                   ${(buy.buy_type)?if_exists}    	            
             </td>
           </tr>
           <tr>
             <td class="table_name" >所属分类:</td>
             <td>
                ${(buy.cat_attr)?if_exists}       	            
             </td>
           </tr>
          <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>  
          <tr>
             <td class="table_name" >所在地区:</td>
             <td>
                 ${(buy.area_attr)?if_exists}       	           	            
             </td>
           </tr>
          <tr>
             <td class="table_name" >产品图片:</td>
             <td>
              <@s.hidden name="buy.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>
	       </td>
	       </tr>   
	      <tr>
             <td class="table_name" >详细说明:</td>
             <td >
             	${buy.content?if_exists}   	            
             </td>
           </tr>
          <tr>
             <td class="table_name">需求数量:</td>
             <td>
             	${buy.buy_num?if_exists}     	            
             </td>
           </tr>
           <tr>
             <td class="table_name">价格要求:</td>
             <td>
             	${buy.price?if_exists}          	            
             </td>
           </tr>
           <tr>
             <td class="table_name" >规格要求:</td>
             <td>
             	${buy.standard?if_exists}           	            
             </td>
           </tr>
          <tr>
             <td class="table_name" >包装要求:</td>
             <td>
             	${buy.pack?if_exists}           	            
             </td>
           </tr>

           <tr>
             <td class="table_name" >过期时间:</td>
             <td>
             <#if buy.end_date?if_exists?length lt 10>
             	${buy.end_date?if_exists}            
             <#else>
              	${buy.end_date?if_exists[0..9]}          
             </#if>
             </td>
           </tr>
            <tr>
             <td class="table_name" >是否推荐:</td>
             <td>
               <#if (buy.is_recom)?if_exists=='0'>是<#else>否</#if>        
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
                ${buy.clicknum?if_exists}   	            
             </td>
           </tr>
           <tr>
             <td class="table_name" >内容收费:</td>
             <td>
             	${buy.fare?if_exists}( 默认"0"则免费!)   
             </td>
           </tr>   
            <#if if_opt_audit=="1">
           <tr>
             <td class="table_name">审核状态:</td>
             <td>
             	<@s.radio name="buy.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('buy.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>buy.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr  id="reasonid" style="display:<#if noReasonKey?if_exists=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="buy.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>buy.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           </#if>
         </table>  
          <#if cfg_auditmodel=="0">
           <#include "/WEB-INF/template/manager/admin/Auditmodel/auditinfo.ftl" />   
          </#if>             
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nike_name_s"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="buy.buy_id" />
	       <@s.hidden name="buy.cust_id" />
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核"/>
	       </#if> 
	       <input type="button" name="returnList" style="cursor:pointer;" value="返回列表" 
	       onclick="linkToInfo('/admin_Buy_auditList.action','');" />              
	     </div>     
	  </@s.form>	
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>