<html>
  <head>
    <title>审核企业友情链接</title>
    </script>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 友情链接管理 > 审核企业友情链接
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Memberlink_auditstate.action" method="post" validate="true" onsubmit="return noreasron('memberlink.link_state','noreason',2);">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">链接名称:</td>
             <td>
             	<@s.label name="memberlink.title" />
             </td>
           </tr>
           <tr>
             <td class="table_name">链接地址:</td>
             <td>
               <@s.label name="memberlink.link_url" />
             </td>
           </tr>  
           <tr>
             <td class="table_name">添加时间</td>
             <td>
                <@s.label name="memberlink.in_date" />
             </td>
           </tr>
            <tr>
             <td class="table_name">审核状态:</td>
             <td>
                <@s.radio name="memberlink.link_state" list=r"#{'0':'审核通过','2':'审核未通过'}" onclick="getRadioValue('memberlink.link_state','linkstate','noreason','2');"/>
                <@s.fielderror><@s.param>memberlink.link_state</@s.param></@s.fielderror>
             </td>
           </tr>   
           <tr id="linkstate" style="display:<#if linkstateTip?if_exists=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberlink.no_reason" cssClass="txtinput" cssStyle="width:600px;" maxLength="100" id="noreason"/>
             	<@s.fielderror><@s.param>memberlink.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>      
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="memberlink.cert_id"/> 
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="审核"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberlink_list.action','');" />
	     </div>	     
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
</body>
</html>