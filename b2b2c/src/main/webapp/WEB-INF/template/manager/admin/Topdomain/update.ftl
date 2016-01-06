<html>
  <head>
    <title>修改域名信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:会员管理 > 企业会员 > 域名绑定申请>修改域名信息
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Topdomain_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
		         <tr>
		             <td class="table_name">域名<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="topdomain.domain_url" cssClass="txtinput" cssStyle="width:300px"   maxLength="100"/>
		             	 <@s.hidden  name="oldinfotitle" value="${topdomain.domain_url?if_exists}"/>
		             	<@s.fielderror><@s.param>topdomain.domain_url</@s.param></@s.fielderror>
		             </td>
		           </tr>
		              <tr>
		             <td class="table_name">跳转地址<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="topdomain.go_url" cssClass="txtinput"  cssStyle="width:300px"    maxLength="500"/>
		             	<@s.fielderror><@s.param>topdomain.go_url</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">会员标识ID</td>
		             <td>
		             	<@s.textfield name="topdomain.cust_id" cssClass="txtinput" cssStyle="width:80px"  maxLength="9"  onkeyup="checkNum(this);"  />
		             	 <@s.hidden  name="membert_type" value="${topdomain.cust_id?if_exists}"/>
		             	*(注意："0"表示"运营商",若会员申请域名时,请输入会员的标识ID.否则无需修改！)
		             	<@s.fielderror><@s.param>topdomain.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
		         <tr>
		             <td class="table_name">域名类型</td>
		             <td>
		             	 <@s.radio name="topdomain.domain_type" list=r"#{'0':'一级域名','1':'二级域名'}" />     	
             	         <@s.fielderror><@s.param>topdomain.domain_type</@s.param ></@s.fielderror >
		             </td>
		           </tr>
		              <tr>
		             <td class="table_name">是否有效</td>
		             <td>
		             	 <@s.radio name="topdomain.enabled" list=r"#{'0':'否','1':'是'}" />     	
             	         <@s.fielderror><@s.param>topdomain.enabled</@s.param ></@s.fielderror >
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       <@s.hidden name="topdomain.info_id"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Topdomain_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

