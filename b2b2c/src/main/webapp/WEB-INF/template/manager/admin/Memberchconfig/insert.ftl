<html>
  <head>
    <title>添加栏目</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 栏目配置管理 > 添加栏目
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Memberchconfig_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">栏目名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberchconfig.ch_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberchconfig.ch_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">栏目编码<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberchconfig.ch_code" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>memberchconfig.ch_code</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">栏目类型<font color='red'>*</font></td>
             <td>
             	<@s.select name="memberchconfig.ch_type" list=r"#{'0':'菜单','1':'侧栏','2':'首页主栏'}" headerKey="3" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>memberchconfig.ch_type</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">所属平台<font color='red'>*</font></td>
             <td>
             	<@s.select name="memberchconfig.plat_type" list=r"#{'b2b':'b2b','b2c':'b2c'}" headerKey="" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>memberchconfig.plat_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否显示:</td>
             <td>
             	<@s.radio name="memberchconfig.is_dis" list=r"#{'0':'显示','1':'不显示'}" value="0"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="memberchconfig.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchconfig.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">数量:</td>
             <td>
             	<@s.textfield name="memberchconfig.ch_num" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchconfig.ch_num</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.ch_num')}"/>"> 
             </td>
           </tr>
            
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberchconfig_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>