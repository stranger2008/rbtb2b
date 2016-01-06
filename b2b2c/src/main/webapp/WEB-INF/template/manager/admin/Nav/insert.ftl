<html>
  <head>
    <title>添加导航</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
     $(document).ready(function(){
          $("#codeselect").change(function(){
               var mod=$(this).val();
               $("#navcode").val(mod);
          });        
          $("#navform").submit(function(){
              return true;
          });  
     })        
	</script>
</head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 导航管理 > 添加导航
   </div>
   <div class="tj_main_cont">
   
   	<@s.form id="navform" action="/admin_Nav_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">导航名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="nav.nav_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>nav.nav_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">链接地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="nav.link_url" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>nav.link_url</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
             <tr>
             <td class="table_name">放置位置<font color='red'>*</font></td>
             <td>
             	<@s.select name="nav.nav_post" list=r"#{'1':'上部','2':'中部','3':'下部'}" headerKey="0" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>nav.nav_pos</@s.param></@s.fielderror>            	
             </td>
           </tr>
            <tr>
             <td class="table_name">高亮编码<font color='red'>*</font></td>
             <td>
             	 <@s.select  list="commparalist" id="codeselect"  listValue="module_name" listKey="module_type" headerKey="0" headerValue="请选择" />
             	 或请输入自定义编码:<@s.textfield id="navcode" name="nav.nav_code" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>nav.nav_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="nav.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>nav.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">链接类型<font color='red'>*</font></td>
             <td>
             	<@s.select name="nav.isopen" list=r"#{'_self':'当前窗口打开','_blank':'新窗口中打开'}"  headerKey="0" headerValue="请选择"/>
             	<@s.fielderror><@s.param>nav.isopen</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="nav.isshow" list=r"#{'0':'有效','1':'无效'}" value="0"/>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Commpara.enabled')}"/>"> 
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	        <@s.hidden name="mall_type" />
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	      <input type="button"  name="returnList" value="返回列表" onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Nav_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_malllist.action'</#if>,'');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>