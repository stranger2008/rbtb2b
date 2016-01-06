<html>
  <head>
    <title>修改配送方式</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统配置 > 修改配送方式
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Sendmode_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
	             <tr>
		             <td width="19%" class="table_name">配送方式名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendmode.smode_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sendmode.smode_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">配送方式描述</td>
		             <td>
		               <@s.textarea name="sendmode.smode_desc" cssClass="txtinput" cssStyle="width:400px;height:150px;"  />
		             	<@s.fielderror><@s.param>sendmode.smode_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
	              <tr>
		             <td class="table_name">是否支持货到付款</td>
		             <td>
		                 <@s.radio name="sendmode.reach_pay" list=r"#{'1':'否','0':'是'}" />
		             	<@s.fielderror><@s.param>sendmode.reach_pay</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">是否支持保价</td>
		             <td>
		                <@s.radio name="sendmode.is_insured" list=r"#{'1':'否','0':'是'}" />
		             	<@s.fielderror><@s.param>sendmode.is_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保价费率</td>
		             <td>
		             	<@s.textfield name="sendmode.rate" cssClass="txtinput" cssStyle="width:80px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendmode.rate</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最低保价额</td>
		             <td>
		             	<@s.textfield name="sendmode.mix_insured" cssClass="txtinput" cssStyle="width:100px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendmode.mix_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最高保价额</td>
		             <td>
		             	<@s.textfield name="sendmode.max_insured" cssClass="txtinput" cssStyle="width:100px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendmode.max_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>         
	           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendmode.sort_no" cssStyle="width:80px;" size="12" onkeyup="checkNum(this);"   maxLength="11"/>
		             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>">
		             	<@s.fielderror><@s.param>sendmode.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	        <@s.hidden name="sendmode.smode_id"/>
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sendmode_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

