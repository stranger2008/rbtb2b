<html>
  <head>
    <title>会员修改收藏</title>
  </head>
  <body>
 <div class="cont_main">
   	<@s.form action="/member_Collect_update.action" method="post" validate="true">
   	   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商机管理>商机收藏>修改收藏</td>
 	</tr>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">收藏标题<font color="red">*</font></td>
             <td>
             	<@s.textfield name="collect.title" cssClass="txtinput" maxLength="100" cssStyle="width:300px"/>
                <@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" valign="middle" class="left_td">链接地址<font color="red">*</font></td>
             <td>
             	<@s.textfield name="collect.link_url" cssClass="txtinput" maxLength="100" cssStyle="width:500px"/>
                <@s.fielderror><@s.param>collect.link_url</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">分类:</td>
             <td>
             		<@s.select name="collect.cat_id" list="membercatList" listValue="cat_name" listKey="cat_id" headerKey="0" headerValue="请选择" /> 
             	<@s.fielderror><@s.param>collect.cat_id</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" valign="middle" class="left_td">备注:</td>
             <td>
             	<@s.textarea name="collect.remark" cssClass="txtinput" cssStyle="width:300px;height:100px;" onkeyup="set_textarea_length(this,100);"/>
                <@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
             </td>
           </tr>
          <tr>
           <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="collect.coll_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Collect_list.action','');"/>
	       </td>
	     </tr>
         </table>         
	  </@s.form>
   </div>
</div>
<div class="clear"></div>

  </body>
</html>