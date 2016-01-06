<html>
  <head>
    <title>修改收藏</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 会员相关 > 商机收藏 > 修改收藏
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Collect_update.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">标题名称<font color="red">*</font></td>
             <td>
             	<@s.textfield name="collect.title" cssClass="txtinput" cssStyle="width:300px" maxLength="20"/>
                <@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">链接地址<font color="red">*</font></td>
             <td>
             	<@s.textfield name="collect.link_url" cssClass="txtinput" cssStyle="width:300px" maxLength="70"/>
             	<@s.fielderror><@s.param>collect.link_url</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">分类:</td>
             <td>
             	<@s.select name="collect.cat_id" list="membercatList" listValue="cat_name" listKey="cat_id" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>collect.cat_id</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">备注：</td>
             <td>
             	<@s.textarea name="collect.remark" cssClass="txtinput" cssStyle="width:200px;height:100px;" onkeyup="set_textarea_length(this,200);" />
                <@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="collect.coll_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Collect_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>