<html>
  <head>
    <title>系统基本设置管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	    
    <#include "/include/uploadInc.html">
  </head>
  <body>
<@s.form action="/admin_Sysconfig_list.action" method="post" >
<@s.hidden name="admin_sysconfig_varid" id="admin_sysconfig_varid"/>
<@s.hidden name="para_key" value="${para_key}"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统基本设置管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Sysconfig_add.action','module_name=${module_name?if_exists}');">添加变量</a></li>
     <li class="xg" ><a onclick="updateSysconfigBatch('/admin_Sysconfig_updateSysconfigBatch.action');">修改</a></li>
   </ul>
 </div>
 
 <div class="main_cont">
  <ul class="main_ul_type">    
    <#list commparaList as commpara>
      <li>
         <a onclick="linkToInfo('/admin_Sysconfig_list.action','para_key_s=${commpara.para_value?if_exists}');">         	
         	<#if para_key_s=commpara.para_value>
         	<font color="red">${commpara.para_key?if_exists}</font>
         	<#else>
         	${commpara.para_key?if_exists}
         	</#if>         	
         </a>&nbsp;|
      </li>
    </#list>
  </ul>
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td align="left" colspan="4" class="top_td show_top"><span style="padding-left:30px;">
    <@s.hidden name="module_name"/>
      <#if module_name?if_exists><font color="red" style="float:left;">(${module_name?if_exists}模块设置)</font></#if>
    <font color="red">请注意:系统变量不能删除</font></span></td>
  </tr>
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="20%" align="center" class="top_td show_top">变量描述</td>
    <td width="20%" align="center" class="top_td show_top">变量值</td>
    <td width="20%" align="center" class="top_td show_top">变量名</td>
    <td width="10%" align="center" class="top_td show_top" >删除</td>
  </tr>
  
  <#list sysconfigList as config>
  <tr bgcolor="<#if config_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <input name="sysconfig.var_id"  value="${config.var_id}" id="varid" type="hidden"/> 
    <td align="center">
    	${config.var_desc?if_exists}
    	<#if config.var_type='3'>
	      (字数不能超过800字)
	    </#if>
    </td>
    <td align="left">
    <#if config.var_type='0'>
      <@s.textfield name="sysconfig.var_value" onkeyup="checkNull_dou(this);" value="${config.var_value?if_exists}" cssClass="txtinput"  cssStyle="width:350px;" maxLength="200"/>
    <#elseif config.var_type='1'>
      <@s.textfield name="sysconfig.var_value" onkeyup="checkNull_dou(this);" value="${config.var_value?if_exists}" cssClass="txtinput" maxLength="10" onkeyup="checkNum(this)" />(只能输入数字)
    <#elseif config.var_type='2'>
      <@s.radio name="${config.var_name?if_exists}" list=r"#{'0':'是','1':'否'}" value="${config.var_value?if_exists}" onclick="setHiddenVal('${config.var_name?if_exists}',this.value);"/>
      
      <input type="hidden" name="sysconfig.var_value" id="set_${config.var_name?if_exists}" value="${config.var_value?if_exists}"/>
    <#elseif config.var_type='3'>
      <@s.textarea name="sysconfig.var_value" onblur="checkNull_dou(this);" value="${config.var_value?if_exists}" cssClass="txtinput" rows="3" cssStyle="width:400px;height:90px;" onkeyup="set_textarea_length(this,800);"/>
    <#else>
        <img id="show_dis_img" src="${config.var_value?if_exists}" width="100" height="100" style="margin:6px;"/>
        <div style="margin-bottom:6px;">
        <div id="fileQueue${config.var_name?if_exists}"></div>
		    <input name="sysconfig.var_value" id="uploadresult${config.var_name?if_exists}" style="width:300px;" onkeyup="checkNull_dou(this);" value="${config.var_value?if_exists}"/>
	        <div style="margin-left:310px;margin-top:-21px;">
		        <input type="file" name="uploadifyfile" id="uploadifyfile${config.var_name?if_exists}" />       
				<script> uploadImg('uploadifyfile${config.var_name?if_exists}','uploadresult${config.var_name?if_exists}','fileQueue${config.var_name?if_exists}');</script>		  
	        </div>
	    </div>
    </#if>    
    </td>
    <td align="center">${config.var_name?if_exists}</td>
    <td align="center">
      <#if config.val_sys=='1'>
       <a onclick="deleteVal('${config.var_id?if_exists}','${config.module_type?if_exists}')" title="删除"><img src="/include/images/delete.png" /></a>
      </#if> 
    </td>
  </tr>
  </#list>
</table>
 </div>
 <div class="clear"></div>
</div>
<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
<@s.hidden name="module_type_s"/>
</@s.form>
<script>
	function setHiddenVal(val_name,val){
		document.getElementById("set_"+val_name).value = val;
	}
</script>
    <script type="text/javascript">
     function updateSysconfigBatch(actionName)
     {
       art.dialog({
	   title: '系统信息提示',
       content: '<div class="decorate">您确定要修改吗？</div>',
       width: '15%',
       icon: 'question',
       yesFn: function () {
           var cfg_count=0;
		   $("input:[name='sysconfig.var_value']").each(function(){
		        if($(this).val()==''){
		        	cfg_count+=1;
		        }   
		   });
		   if(cfg_count>0){
		  	  jNotify("文本框不能为空!"); 
		   }else{
			   var admin_config_varid='';
		       var chks = document.getElementsByTagName('input');//得到所有input
	           for(var i=0;i <chks.length;i++)
	           { 
	              if(chks[i].id.toLowerCase() == 'varid')
	              {
	                admin_config_varid+=chks[i].value+',';               
	              }               
	           }           
	           var len=admin_config_varid.length;
	           var var_id=admin_config_varid.substring(0,len-1);
	           document.getElementById('admin_sysconfig_varid').value = var_id;//用于隐藏所有的ID           
			   document.forms[0].action=actionName;
			   document.forms[0].submit();		   
		   }
        return false;
    },
    noText: '关闭',
    noFn: true //为true等价于function(){}
    });
  }  
      function deleteVal(var_id,mod){
        art.dialog({
		title: '系统信息提示',
	    content: '<div class="decorate">您确定删除吗？</div>',
	    width: '15%',
	    icon: 'question',
	    yesFn: function () {
	        location.href='/admin_Sysconfig_del.action?sysconfig.var_id='+var_id+"&module_type_s="+mod;
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    });      
      }
      //验证不能为空，且如果是英文逗号的话把它替换成中文
      function checkNull_dou(obj){
          var obj_value=$(obj).val();
          if(obj_value==''){
              jNotify("该文本框不能为空!"); 
              return;
          }else if(obj_value.indexOf(','>-1)){          
              var obj_val=obj_value.replace(",","，");
              $(obj).val(obj_val);
          }
      }
   </script>
  </body>
</html>