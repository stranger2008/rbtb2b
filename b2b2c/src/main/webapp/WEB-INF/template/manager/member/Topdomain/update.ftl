<html>
  <head>
    <title>修改顶级域名</title>
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Topdomain_update.action" method="post" validate="true">
   	  	 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>商店设置>修改顶级域名</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">用户名称</td>
             <td width="83%">
             	<@s.label name="cust_name"/>
             </td>
           </tr>
 
            <tr>
             <td valign="middle" class="left_td">域名<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="topdomain.domain_url" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>topdomain.domain</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="topdomain.inbox_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
		   <@s.hidden name="topdomain.go_url" />
		   <@s.hidden name="topdomain.domain_type" />
		   <@s.hidden id="hidden_up_cate_id" name="hidden_up_cate_id" value="${hidden_up_cate_id?if_exists}"/>
		   <@s.hidden id="hidden_up_level" name="hidden_up_level" value="${hidden_up_level?if_exists}"/>
		   <!--所属分类插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/member_Topdomain_list.action';document.forms[0].submit();"/>
	     </div>
	  </@s.form>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>