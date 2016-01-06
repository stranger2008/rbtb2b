<html>
  <head>
     <title>修改模块参数</title>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统模块设置 > 修改模块参数
   </div>
   <div class="tj_main_cont">   
   	<@s.form action="/admin_Sysmodule_audit.action" method="post" validate="true">   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">模块类型:</td>
             <td>
             	${sysmodule.module_type?if_exists}     	            
             </td>
           </tr>                
           <tr>
             <td class="table_name">模块名称:</td>
             <td>
             	 ${sysmodule.module_name?if_exists}          	            
             </td>
           </tr>  
           
           <tr>
             <td class="table_name">模块表名:</td>
             <td>
             	 ${sysmodule.table_name?if_exists}                	            
             </td>
           </tr>  
          <tr>
             <td class="table_name">是否启用:</td>
             <td>
             	 <#if  sysmodule.state_code?if_exists=='0'>启用<#else>禁用</#if>  	            
             </td>
           </tr> 
          <tr>
             <td class="table_name">分类属性:</td>
             <td>    
             	 <#if sysmodule.is_catattr?if_exists=='0'>开启<#else>关闭</#if>  	           	            
             </td>
           </tr>   
          <tr>
             <td class="table_name">排序:</td>
             <td>      	            
             	 ${sysmodule.sort_no?if_exists}        
             </td>
          </tr>    
           <tr>
             <td class="table_name">安装目录:</td>
             <td>     
             	 ${sysmodule.install_dir?if_exists}             	            
             </td>
           </tr>   
          <tr>
             <td class="table_name">关联菜单:</td>
             <td>  
             	 ${sysmodule.link_menu?if_exists}                	            
             </td>
           </tr>  
          <tr>
             <td class="table_name">关联表:</td>
             <td>  
             	 ${sysmodule.link_table?if_exists}               	            
             </td>
          </tr>           
          <tr>
             <td class="table_name">关联文件:</td> 
             <td style="width:80%;">
             	 ${sysmodule.link_file?if_exists}                  	            
             </td>
          </tr>  
        
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="sysmodule.is_mem"/>
	       <@s.hidden name="sysmodule.mod_type"/>
	       <@s.hidden name="checkonly" value="${sysmodule.module_type}"/>
	       <@s.hidden name="old_link_menu" value="${sysmodule.link_menu}"/>
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Sysmodule_list.action';document.forms[0].submit();"/>
	     </div>	     
	     
	  </@s.form>	  
   </div>
</div>

</div>
<div class="clear"></div>
 </body>
</html>