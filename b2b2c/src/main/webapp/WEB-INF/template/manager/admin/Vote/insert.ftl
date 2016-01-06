<html>
  <head>
    <title>添加在线调查</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 在线调查管理 > 添加在线调查
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Vote_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
             <tr>
             <td class="table_name">调查主题名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="vote.vote_title" size="70" maxLength="100"/>
             	<@s.fielderror><@s.param>vote.vote_title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">开始时间<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="txtStartDate" name="starttime_s"   cssClass="Wdate"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})" />
                <@s.fielderror><@s.param>vote.start_date</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">结束时间<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="txtEndDate" name="endtime_s"   cssClass="Wdate"  onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})" />
             	<@s.fielderror><@s.param>vote.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">是否多选:</td>
             <td>
             	<@s.radio name="vote.is_multi" list=r"#{'1':'单选','2':'多选'}" value="1"/>
             	<@s.fielderror><@s.param>vote.is_multi</@s.param></@s.fielderror>
             </td>
           </tr>
               <tr>
             <td class="table_name">投票数:</td>
             <td>
             	<@s.textfield name="vote.vote_count" value="0" cssClass="txtinput" style="width:80px;" maxLength="8"/>
             	<@s.fielderror><@s.param>vote.vote_count</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.ch_num')}"/>"> 
             </td>
           </tr>
           
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="vote.vote_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Vote_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>