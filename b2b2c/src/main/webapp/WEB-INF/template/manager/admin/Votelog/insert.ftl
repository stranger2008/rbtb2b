<html>
  <head>
    <title>修改导航</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="tj_main_cont">
   	<@s.form action="/admin_Votelog_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('option_id')"/></td>
             <td width="10%" class="table_name">在线调查试题:</td>
             <td width="80%">
             	${vote.vote_title}
             </td>
           </tr>
              <#list voteoptionList as voteoption>
             <tr>
	             <td><input type="checkbox" name="voteoption.option_id" value="${voteoption.option_id?if_exists}"/></td>
	             <td align="center">${voteoption.option_name?if_exists}</td>
             </tr>
              </#list>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="vote_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Votelog_list.action','vote_id=${vote_id?if_exists}');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>