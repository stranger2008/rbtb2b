<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
  <head>
    <title>添加系统菜单</title>
  </head>
  <body>


<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统菜单管理 > 添加系统菜单
   </div>
   <div class="tj_main_cont">
   
     <#assign menu_level=0 />

     <#if (sysmenu.menu_level)?if_exists == ''>
     	<#assign menu_level = 1 />
     <#else>
     	<#assign menu_level = (sysmenu.menu_level?number+1) />
     </#if>
   
   
   	<@s.form action="/admin_Sysmenu_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">菜单名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="sysmenu.menu_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>sysmenu.menu_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属后台:</td>
             <td>
             	<@s.select name="sysmenu.syscode" list=r"#{'sys':'管理员后台','com':'会员后台','b2c':'商城后台'}"  value="sys"/>
             	<@s.fielderror><@s.param>sysmenu.syscode</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td class="table_name">菜单级别:</td>
             <td>
             	${menu_level}级
             </td>
           </tr>
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="sysmenu.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>sysmenu.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.sort_no')}"/> '>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="sysmenu.enabled" list=r"#{'0':'有效','1':'无效'}" value="0"/>
             	<@s.fielderror><@s.param>sysmenu.enabled</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.is_display')}"/> '>
             </td>
           </tr>
           <tr>
             <td class="table_name">链接地址:</td>
             <td>
             	<@s.textfield name="sysmenu.url" cssClass="txtinput" style="width:400px;"/>
             	<@s.fielderror><@s.param>sysmenu.url</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">链接类型:</td>
             <td>
             	<@s.select name="sysmenu.target" list=r"#{'_self':'当前窗口打开','_blank':'新窗口中打开'}"  value="_self"/>
             	<@s.fielderror><@s.param>sysmenu.target</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.submit value="保存"/>
	       <@s.hidden name="sysmenu.up_menu_id" value="${(sysmenu.up_menu_id)!'1111111111'}" />
	       <@s.hidden name="sysmenu.menu_level" value="${menu_level}" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sysmenu_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>