<html>
  <head>
    <title>会员级别信息</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Levelinfo_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">会员名称:</td>
             <td>
             	${cust_name?if_exists}
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">会员级别<font color="red">*</font></td>
             <td>
                <@s.select name="levelinfo.level_code" list="levelList" listValue="level_name" listKey="level_id" headerKey="0" headerValue="请选择" />
                <@s.fielderror><@s.param>levelinfo.level_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">开始时间<font color="red">*</font></td>
             <td>
               <#if levelinfo.start_date?if_exists?length lt 10>
               <@s.textfield id="txtstartDate" name="levelinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
             <#else>
             <@s.textfield id="txtstartDate" name="levelinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
             value="${levelinfo.start_date?if_exists[0..9]}" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />          
             </#if>
              <@s.fielderror><@s.param>levelinfo.start_date</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">截止时间<font color="red">*</font></td>
             <td>
             
               <#if levelinfo.end_date?if_exists?length lt 10>
              <@s.textfield id="txtendDate" name="levelinfo.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
             <#else>
             <@s.textfield id="txtendDate" name="levelinfo.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
             value="${levelinfo.end_date?if_exists[0..9]}" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />         
             </#if>
               <@s.fielderror><@s.param>levelinfo.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="levelinfo.cust_id"/>
	       <@s.submit value="保存" />
	       <#if (levelinfo.level_code)?if_exists=='3' || (levelinfo.level_code)?if_exists=='4'>
	         <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Corpomember_list.action';document.forms[0].submit();"/>
	       <#else> 
	         <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Member_list.action';document.forms[0].submit();"/> 
	       </#if>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>