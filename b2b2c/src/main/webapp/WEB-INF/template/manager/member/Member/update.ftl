<html>
<head>
<title>联系人信息</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Member_updateContactInfo.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>会员资料>联系人信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
           <td valign="middle" class="left_td" >联系人姓名<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="member.contact_name" cssClass="txtinput"  maxLength="50" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_name</@s.param></@s.fielderror>
             	&nbsp;&nbsp;<@s.radio name="member.contact_sex" list=r"#{'先生':'先生','女士':'女士'}" />
             </td>
             
           </tr>
           <tr>
             <td valign="middle" class="left_td">电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="member.email" cssClass="txtinput" maxLength="100"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.email</@s.param></@s.fielderror>
             	<@s.hidden  name="oldemail" value="${(member.email)?if_exists}"/>
             </td>
           </tr>
           <tr>
              <td valign="middle" class="left_td">联系电话:</td>
             <td>
             	<@s.textfield name="member.phone" cssClass="txtinput" maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.phone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td valign="middle" class="left_td">联系手机:</td>
             <td>
             	<@s.textfield name="member.contact_cellphone" cssClass="txtinput" maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_cellphone</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">联系人QQ:</td>
             <td>
             	<@s.textfield name="member.contact_qq" cssClass="txtinput"  maxLength="100"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_qq</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
              <td valign="middle" class="left_td">联系人MSN:</td>
             <td>
             	<@s.textfield name="member.contact_msn" cssClass="txtinput" maxLength="100" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_msn</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
              <td valign="middle" class="left_td">邮政编码:</td>
             <td>
             	<@s.textfield name="member.post_code" cssClass="txtinput"  maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.post_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">联系人部门:</td>
             <td>
             	<@s.textfield name="member.contact_job" cssClass="txtinput"  maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_job</@s.param></@s.fielderror>
             </td>
           </tr> 
          <tr>
             <td valign="middle" class="left_td">联系人职位:</td>
             <td>
             	<@s.textfield name="member.contact_depart" cssClass="txtinput" maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_depart</@s.param></@s.fielderror>
             </td>
           </tr>
	    <td colspan="4" class="subbut">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	      <@s.hidden name="member.cust_id"/>
	      <@s.hidden name="member.cust_name"/>
	     <@s.hidden name="member.mem_type"/>
	     <@s.hidden name="member.reg_date"/>
	     <@s.hidden name="member.info_state"/>
	      <@s.hidden name="member.cust_type"/>
	     <@s.hidden name="member.cust_rage"/>
	     <@s.hidden name="member.recommend"/>
	     <@s.submit value="保存"  cssClass="sub"/> 
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>

