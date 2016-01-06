<html>
<head>
<title>发送信件</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Memberinbox_insert.action"  method="post" validate="true">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商机管理>我的信箱>发送信件</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="19%" valign="middle" class="left_td">信件标题<font color='red'>*</font></td>
             <td>
                   <@s.textfield name="memberinbox.title" cssClass="txtinput"  maxlength="100" size="70"/>  
                   <@s.fielderror><@s.param>memberinbox.title</@s.param></@s.fielderror>           
             </td>
           </tr>
           <tr>
             <td width="19%" valign="middle" class="left_td">信件类型<font color='red'>*</font></td>
             <td>
                   <@s.select id="type" name="memberinbox.mess_type"  list="commparalist" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" cssStyle="width:80px;"/>        
                   <@s.fielderror><@s.param>memberinbox.mess_type</@s.param></@s.fielderror>    
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">发件人名称</td>
             <td>
					${cust_name?if_exists}
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">收件人名称<font color='red'>*</font></td>
             <td>
					<@s.textarea id="title" name="send_name" cssClass="txtinput" cssStyle="width:380px;height:60px;" onkeyup="set_textarea_length(this,100);"/><br/><font color="red">提示：请输入你要发送的公司名称,如果需要发送到多个公司,请用逗号隔开！</font>
					<@s.fielderror><@s.param>send_name</@s.param></@s.fielderror>  
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">信件内容:</td>
             <td>
          			<@s.textarea  name="memberinbox.content" id="content"  cssStyle="width:380px;height:120px;"/>
					<@s.fielderror><@s.param>memberinbox.content</@s.param></@s.fielderror>  
             </td>
           </tr>
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           ${listSearchHiddenField?if_exists}
           <@s.hidden id="type" name="type"/>
           <@s.submit value="保存"  cssClass="sub"/>
	       <@s.reset value="重置"  cssClass="sub"/>
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
</body>
</html>
