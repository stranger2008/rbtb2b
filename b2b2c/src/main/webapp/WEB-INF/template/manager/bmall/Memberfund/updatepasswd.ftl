<html>
<head>
<title>设置支付密码</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">我的资金</a><span>></span><a href="#">设置支付密码</a><span>
    </div>
<@s.form action="/bmall_Memberfund_updatepasswd.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的资金</h2></div>
     <div class="base_infor">
       <h2 class="base_title">设置支付密码</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td class="firsttd">旧密码<font color="red">*</font></td><td>
        	<@s.password name="oldpasswd"  maxLength="32"/>
            <@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
            </td></tr>
              
            <tr><td class="firsttd">新密码<font color="red">*</font></td>
        	 <td>
             <@s.password name="newpasswd"  maxLength="32"/>
             <@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>       	           	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">确认密码<font color="red">*</font></td><td>
        	<@s.password name="confirmpasswd"  maxLength="32"/>
            <@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
            </td></tr>  
            
            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
             	<@s.hidden name="message" />
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundtocash_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

