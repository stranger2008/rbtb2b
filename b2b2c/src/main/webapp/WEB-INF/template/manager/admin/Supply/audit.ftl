<html>
  <head>
    <title>审核供应</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
    <style type="text/css">
	 .zitd{width:100px;text-align:right;}
	 .zitxt{width:80px;}
	 .datenum{width:20px;}
	 .attr{border:1px solid #E3E3E3;}
    </style>
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
 	当前位置:功能模块 > 供应管理 > 供应审核列表 > 审核供应
   </div>
   <div class="tj_main_cont">   
   	<@s.form id="supplyadd" action="/admin_Supply_auditState.action" method="post" validate="true" onsubmit="return noreasron('supply.info_state','noreasonvalue');">   	
        <table class="wwtable" cellspacing="1" cellpadding="8"  >          
           <tr>
             <td class="table_name" width=300px;>供应标题:</td>
             <td>
             	${(supply.title)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name" >供应类型:</td>
             <td>
               ${supply.supply_type?if_exists}  	            
             </td>
           </tr>
           <tr>
             <td class="table_name" >自定义产品分类:</td>
             <td>  
             ${supply.cat_name?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name" >所属分类:</td>
             <td>
                 ${(supply.cat_attr)?if_exists}         
             </td>
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/auditattr.ftl" />   
           </#if>  
           <tr>
             <td class="table_name" >所在地区:</td>
             <td>
                ${(supply.area_attr)?if_exists} 
   	           	            
             </td>
           </tr>
          <tr>
             <td class="table_name" >产品图片:</td>
             <td>
	               <input id="uploadresult" type="hidden" value="${supply.img_path?if_exists}"/>
                   <div id="showImageDiv"></div>	                      	            
	        </td>
	       </tr>   
           <tr>
             <td class="table_name">产品品牌:</td>
             <td>
    	          ${supply.brand?if_exists}	            
             </td>
           </tr>
           <tr>
             <td class="table_name">产品型号:</td>
             <td>
             	  ${supply.model?if_exists}	     
             </td>
           </tr>
           <tr>
             <td class="table_name" >产品规格:</td>
             <td>
           	      ${supply.standard?if_exists}	          
             </td>
           </tr>
           <tr>
             <td class="table_name" >详细说明:</td>
             <td >
             	${supply.content?if_exists}     	            
             </td>
           </tr>
           <tr>
             <td class="table_name">交易条件:</td>
             <td>
                <table class="wwtable" cellspacing="1" cellpadding="8" style="margin:0px 0px 0px 0px; width:100%;">
                <tr><td class="zitd">计量单位:</td>
                <td> ${supply.unit?if_exists}</td></tr>
                <tr><td class="zitd">产品单价:</td>
                <td> ${supply.price?if_exists}元/${supply.unit?if_exists}<span id="span_price"/></td></tr>      
                <tr><td class="zitd">最小起订量:</td>
                <td>${supply.min_order?if_exists}${supply.unit?if_exists}</td></tr>  
                <tr><td class="zitd">供应总量:</td>
                <td>${supply.max_supply?if_exists}${supply.unit?if_exists}</td></tr>            
                <tr><td class="zitd">发货天数:</td>
                <td>自买家付款之日起 ${supply.send_day?if_exists}天内发货<span id="span_send_day" class="error_msg"/></td></tr>  
                </table>       	            
             </td>
           </tr>    
           <tr>
             <td class="table_name" >过期时间:</td>
             <td>
             <#if supply.end_date?if_exists?length lt 10>
             	${supply.end_date?if_exists}            
             <#else>
              	${supply.end_date?if_exists[0..9]}          
             </#if>
             </td>
           </tr>
           <tr>
             <td class="table_name" >会员推荐:</td>
             <td>
             <#if (supply.mem_recom)?if_exists=='0'>否<#else>是</#if>          
           </td>
           </tr>
            <tr>
             <td class="table_name" >是否推荐:</td>
             
             <td>
         	   <#if (supply.is_recom)?if_exists=='0'> 否<#else>是</#if> 
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
             	 ${supply.clicknum?if_exists}    	            
             </td>
           </tr>           
           <tr>
             <td class="table_name" >内容收费:</td>
             <td>
        	    ${supply.fare?if_exists}&nbsp;( 默认"0"则免费!)     	            
             </td>
           </tr>    
            <#if if_opt_audit=="1">
              <tr>
             <td class="table_name">审核状态</td>
             <td>
             	<@s.radio name="supply.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('supply.info_state','reasonid','noreasonvalue','2');" />
             	<@s.fielderror><@s.param>supply.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <#if noReasonKey?if_exists=='2'>
             <tr  id="reasonid">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="supply.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>supply.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>
           <#else>
            <tr  id="reasonid" style="display:none;">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="supply.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
             	<@s.fielderror><@s.param>supply.no_reason</@s.param></@s.fielderror>
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
	       <@s.hidden name="nike_name_s"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="supply.supply_id" />
	       <@s.hidden name="supply.cust_id" />
	       <#if cfg_auditmodel=="0">
		        <#if if_opt_audit=="1">
		        <@s.submit value="审核" style="cursor:pointer;"/>
		        </#if>
		   <#else>
		   		<@s.submit value="审核" style="cursor:pointer;"/>
	       </#if> 
	       <input type="button" name="returnList" value="返回列表" style="cursor:pointer;" 
	       onclick="linkToInfo('/admin_Supply_auditList.action','');" />              
	    </div>     
	  </@s.form>  	  
   </div>
</div>
</div>
<div class="clear"></div>
 </body>
</html>