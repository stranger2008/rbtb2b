<html>
  <head>
    <title>添加会员级别</title>
     <link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	 <script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
	  <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" >
	  $(document).ready(function(){ 
	   //checkbox 点击事件 选择框
       $("#menu_oper input:checkbox").click(function(){       
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
 	当前位置:会员管理 > 会员管理 > 会员级别管理 > 添加会员级别
 </div>
   <div class="tj_main_cont">
    
   	<@s.form id="roleadd" action="/admin_Memberlevel_insert.action" method="post" validate="true">   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">级别名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="tf_name" name="memberlevel.level_name" cssClass="txtinput" maxlength="20"/>
             	<@s.fielderror><@s.param>memberlevel.level_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">菜单权限<font color='red'>*</font></td>
             <td>
             <div id="menu_oper">
	        	<script type="text/javascript">       
		        	d = new dTree('d');
				    d.add(1111111111,-1,'会员后台菜单');	
				    <#list menuList as menu>	
		               d.add(${menu.menu_id?if_exists},${menu.up_menu_id?if_exists},'<input type="checkbox" id="${menu.up_menu_id?if_exists}${menu.menu_id?if_exists}" name="memberlevel.menu_right" value="${menu.menu_id?if_exists}"/> ${menu.menu_name?if_exists}&nbsp;${stack.findValue("@com.rbt.function.MemberRightFuc@getMenuRightListByMenuid('${menu.menu_id?if_exists}')")}');
				    </#list>
				    document.write(d);
			    </script>
		      </div>
                <@s.fielderror><@s.param>memberlevel.menu_right</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">备注<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="memberlevel.remark" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="set_textarea_length(this,200);"/>
                <@s.fielderror><@s.param>memberlevel.remark</@s.param></@s.fielderror>
             </td>
           </tr>
           
          <tr>
             <td class="table_name">模块串:</td>
             <td>
                <style type="text/css">
                 .mod_ul{width:560px;}
                 .mod_li{width:100px;float:left;}
                </style>                
                <ul class="mod_ul">
                <li style="line-height:30px;"><input type="checkbox" id="checkall" onclick="selectAll('memberlevel.module_attr')">全选</li>
             	<#list moduleList as mod>             	
				   <#assign mod_attr = "${memberlevel.module_attr?if_exists}">
				   <#assign mod_id = "${mod.module_type?if_exists}">
				    <#if ((mod_attr?if_exists?index_of(mod_id)) > -1 ) >					  
             	   		<li class="mod_li"><input type="checkbox" name="memberlevel.module_attr" checked="true" value="${mod.module_type}">${mod.module_name}</li>      	
					<#else>
					    <li class="mod_li"><input type="checkbox" name="memberlevel.module_attr" value="${mod.module_type}">${mod.module_name}</li>   
					</#if>
             	</#list>
             	</ul>
                <@s.fielderror><@s.param>memberlevel.module_attr</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">信息发布条数控制</td>
             <td>
             <ul style="width:800px;">
	             	<#list controlList as c>
	             		<li style="float:left;margin-right:10px;margin-bottom:10px;">
	             			<label>${c.para_key?if_exists}:</label>
	             			<input type="hidden" name="trokey" value="${c.para_key?if_exists}"/>
	             			<input type="hidden" name="troname" value="${c.para_value?if_exists}"/>
	             			<input type="text" name="troval" onkeyup="checkNum(this);"  style="width:50px;" value="${c.troval?if_exists}"/>
	             		</li>
	             	</#list>
             	</ul>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="level_name_s"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit  value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberlevel_list.action','');" />              
	     </div>     
	  </@s.form>	
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>