<html>
  <head>
    <title>添加变量</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	
    <script type="text/javascript">
    function getRadioVal(name)
     {
        var radioes = document.getElementsByName(name); 
       for(var i=0;i<radioes.length;i++)
      {    
         if(radioes[i].checked)
             {
           if(radioes[i].value=="1")
            {
                 $("#varvalue").val("0");
            }
            
         }
        }
     }
    </script>
  </head>
<body>

<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统基本设置管理 > 添加变量
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Sysconfig_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td width="19%"  class="table_name">变量名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="sysconfig.var_name" cssClass="txtinput" maxLength="20"/>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.admin.Sysconfig.var_name')}"/>'>
             	<@s.fielderror><@s.param>sysconfig.var_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">变量类型:</td>
             <td> 
               <@s.radio name="sysconfig.var_type" list=r"#{'0':'文本','1':'数字','2':'布尔','3':'多行文本','4':'图片'}" value="0"  onclick="getRadioVal(name);"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">变量值<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="sysconfig.var_value" cssClass="txtinput" maxLength="300" id="varvalue"/>
             	<@s.fielderror><@s.param>sysconfig.var_value</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">变量描述<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="sysconfig.var_desc" cssClass="txtinput" rows="3" style="width:300px;" onkeyup="set_textarea_length(this,300);"/>
             	<@s.fielderror><@s.param>sysconfig.var_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <#if module_type_s?if_exists!=''>
	      	   <tr>
	             <td class="table_name">所属模块<font color='red'>*</font></td>
	             <td>	             
	             	<@s.hidden name="sysconfig.module_type" value="${module_name?if_exists}"/>
	                ${module_name?if_exists}
	             	<@s.fielderror><@s.param>sysconfig.var_group</@s.param></@s.fielderror>	             	
	             </td>
	           </tr>
	       <#else>
	      	   <tr>
	             <td class="table_name">所属组<font color='red'>*</font></td>
	             <td>
	             	<@s.select name="sysconfig.var_group" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
	             	<@s.fielderror><@s.param>sysconfig.var_group</@s.param></@s.fielderror>
	             </td>
	           </tr>
           </#if> 
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="module_type_s"/>
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysconfig_list.action','para_key=2');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

<div class="clear"></div>

  
  </body>
</html>