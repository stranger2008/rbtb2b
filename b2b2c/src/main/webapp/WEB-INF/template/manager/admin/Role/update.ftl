<html>
  <head>
     <title>修改角色</title>
     <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	 <script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	 <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	  
	 <script type="text/javascript">
	  $(document).ready(function(){ 	 	   
		     var mrl=$("#mr_list").val();
		     var opl=$("#op_list").val();		     
		     var arr = mrl.split(',');	  
		     var orr=opl.split(',');     	     
		     for(var i = 0; i < orr.length; i++){			         
		             $("input:checkbox").each(function(){	 
		                 if(orr[i]==$(this).val())
		                 {
		                   this.checked=true;  
		                 }		                 
		             });
             }	        
		     for(var i = 0; i < arr.length; i++){			         
		             $("input:checkbox").each(function(){	 
		                 if(arr[i]==$(this).val())
		                 {
		                   this.checked=true;  
		                 }		                 
		             });
             }	      
	  });  
	   $(document).ready(function(){ 
	   //checkbox 点击事件 选择框
       $("input:checkbox").click(function(){       
       	  var cb=$(this).val();
	      var str="input:checkbox[id*="+cb+"]";
          if(this.checked)
          {	
	            $(str).each(function(){	                 	  
		                this.checked=true;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=true;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=true;  
				              }); 	
		                }); 		                		             
			    }); 	
		   }
		  else
		  {
		       $(str).each(function(){	                 	  
		                this.checked=false;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=false;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=false;  
				                }); 	
		                }); 		                		             
			    }); 	
		  }       
       });     
 });  
	 </script>
    
  </head>
  <body>

<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 帐号管理 > 角色管理 > 修改角色
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Role_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           

           <tr>
             <td class="table_name">角色名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="role.role_name" cssClass="txtinput" maxlength="20"/>
             	<@s.fielderror><@s.param>role.role_name</@s.param></@s.fielderror>
             	            
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">菜单权限<font color='red'>*</font></td>
             <td>
             
             <a href="javascript: d.openAll();">全部打开</a> | <a href="javascript: d.closeAll();">全部关闭</a>
        <script type="text/javascript">
       
        	d = new dTree('d');
		    d.add(1111111111,-1,'管理员菜单&nbsp;');		
		    
		    <#list menuList as menu>	
d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="checkbox" id="${menu.up_menu_id?if_exists}${menu.menu_id?if_exists}" name="rolemenuright" value="${menu.menu_id?if_exists}"/> ${menu.menu_name?if_exists}&nbsp;${stack.findValue("@com.rbt.function.RoleRightFuc@getMenuRightListByMenuid('${menu.menu_id?if_exists}')")}');
		    </#list>
		    		
		    document.write(d);
		
	    </script>
                <@s.fielderror><@s.param>role.menu_right</@s.param></@s.fielderror>
             </td>
           </tr>

           <tr>
             <td class="table_name">备注:</td>
             <td>
             	<@s.textarea name="role.remark" cssClass="mailCss" cssStyle="height:80px;width:420px;" onkeyup="set_textarea_length(this,200);"/>
     			<@s.fielderror><@s.param>role.remark</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="role.role_id" />
	       <@s.submit value="保存"/>
	       <@s.hidden id="mr_list" value="${role.menu_right?if_exists}"/>
	       <@s.hidden id="op_list" value="${role.oper_right?if_exists}"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Role_list.action';document.forms[0].submit();"/>
	     </div>	     
	  </@s.form>  	  
   </div>
</div>

</div>
<div class="clear"></div>
 </body>
</html>