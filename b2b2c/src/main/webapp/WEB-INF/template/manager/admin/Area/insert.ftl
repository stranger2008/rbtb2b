<html>
  <head>
    <title>添加地区</title>

  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 地区管理 > 添加地区
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Area_insert.action" method="post" validate="true">	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">地区名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="area.area_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>area.area_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">英文名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="area.en_name" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>area.en_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">区域划分<font color='red'> *</font></td>
             <td>
             	<@s.select name="area.area_have" list="areahave_List" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
             	<@s.fielderror><@s.param>area.area_have</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">地区级别:</td>
             <td>
             	${(area.area_level?if_exists)}级       	
             		<@s.fielderror><@s.param>area.area_level</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">地区排序:</td>
             <td>
             	<@s.textfield name="area.sort_no" value="0" cssClass="txtinput" cssStyle="width:50px;"  onkeyup="checkNum(this);"  maxLength="4"/>
             	<@s.fielderror><@s.param>area.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt='<@s.property value="%{getText('manager.sort_no')}"/> '>
             </td>
           </tr>
         </table>   
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.submit value="保存"/>
	       <@s.hidden name="area.up_area_id" value="${(area.up_area_id)?if_exists}" />
	       <@s.hidden name="area.area_level" value="${(area.area_level?if_exists)}" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Area_list.action';document.forms[0].submit();"/>
	     </div>	     
	  </@s.form>
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>