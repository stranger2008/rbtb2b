<html>
  <head>
    <title>顶级域名绑定</title>
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Topdomain_insert.action" method="post" validate="true">
   	 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>商店设置>顶级域名绑定</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">

            <tr>
             <td valign="middle" class="left_td">域名<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="topdomain.domain_url" maxLength="100" size="50"/>
             	<@s.fielderror><@s.param>topdomain.domain_url</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <#if (topdomain.enabled)?if_exists != ''>
           
           <tr>
             <td valign="middle" class="left_td">是否有效:</td>
             <td>
             	<#if (topdomain.enabled)?if_exists == '1'>
             		<font color="green">有效</font>
             	<#else>
             		<font color="red">无效</font>
             	</#if>
             	
             </td>
           </tr>
           
           <#if (topdomain.in_date)?if_exists?string != ''>
	           <tr>
	             <td valign="middle" class="left_td">申请日期:</td>
	             <td>
	         		<#if (topdomain.in_date)?length lt 19>
			    		${(topdomain.in_date)?if_exists}
			    	<#else>
			    		${(topdomain.in_date)[0..18]}
			    	</#if>
			    	<br/>
             		<font color="red">(注：重新绑定则原绑定域名无效)</font>
	             </td>
	           </tr>
	           
           </#if>
           
           </#if>
           
         </table>
	     <div class="buttom" style="text-align:center;margin-top:10px;">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       <@s.hidden name="topdomain.domain_type" value="0"/>
	       <@s.hidden name="topdomain.go_url" value="/companytemplate.action?user_name=${cust_name?if_exists}"/>
	        <@s.hidden  name="oldinfotitle" value="${(topdomain.domain_url)?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"  cssClass="sub"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
		   <@s.hidden id="hidden_up_cate_id" name="hidden_up_cate_id" value="${hidden_up_cate_id?if_exists}"/>
		   <@s.hidden id="hidden_up_level" name="hidden_up_level" value="${hidden_up_level?if_exists}"/>
		   
	     </div>
	  </@s.form>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>