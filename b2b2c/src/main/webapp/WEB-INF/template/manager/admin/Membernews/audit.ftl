<html>
  <head>
    <title>审核企业新闻</title>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 公司新闻管理 > 审核企业新闻
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membernews_auditstate.action" method="post" validate="true" onsubmit="return noreasron('membernews.news_state','noreason',2);">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">  
           <tr>
             <td width="19%" class="table_name">新闻标题:</td>
             <td>
             	<@s.label name="membernews.title" />
             </td>
           </tr>
           <tr>
             <td class="table_name">新闻内容:</td>
             <td>
                ${membernews.content?if_exists}
             </td>
           </tr>  
           <tr>
             <td class="table_name">添加时间:</td>
             <td>
                <@s.label name="membernews.in_date" /> 
             </td>
           </tr> 
           <tr>
             <td class="table_name">审核状态:</td>
             <td>
                <@s.radio name="membernews.news_state" list=r"#{'0':'审核通过','2':'审核未通过'}" onclick="getRadioValue('membernews.news_state','newsstate','noreason','2');"/>
                <@s.fielderror><@s.param>membernews.news_state</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="newsstate" style="display:<#if newsstateTip?if_exists=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membernews.no_reason" cssClass="txtinput" cssStyle="width:600px;" maxLength="100" id="noreason"/>
             	<@s.fielderror><@s.param>membernews.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>     
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="membernews.cert_id"/> 
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="审核"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membernews_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>