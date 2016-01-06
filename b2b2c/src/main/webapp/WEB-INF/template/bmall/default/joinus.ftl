 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员注册-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/regist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/templets/bmall/js/joinus.js"></script>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>
 <div class="top">
      <a href="${cfg_mallindexurl}" target="_blank"><img src="/templets/bmall/images/logo_06.gif" class="f_left"/></a>
      <a href="/mallhelp.html" class="f_right">帮助中心</a>
      <div class="clear"></div>
  </div>
  <form method="post" >
 <div class="regist">
    <h2> 注册新用户</h2>
    <div class="contLeft">
        <div class="infor">
                 <table width="800px">
					<tr >
						<td class="registertd1" width="200px">
							会员名
							<span class="star">*</span>
						</td>
						<td class="registertd2">
							<span>
								<@s.textfield id="user_name" name="user_name" cssClass="comname_text" onblur="UserNameIsNull();" onfocus="UserNameForm();" maxLength="20"/>
						    </span>
						</td>
						<td class="errordiv" width="400px">
						  <span id="user_nameError" ></span><span id="user_nameForm"></span>
                          <@s.fielderror><@s.param>user_name</@s.param></@s.fielderror>
						</td>
					</tr>

					<tr>
						<td class="registertd1">
							电子邮件
							<span class="star">*</span>
						</td>
						<td class="registertd2">
							 <@s.textfield id="email" name="member.email" cssClass="comname_text" onblur="EmailIsNull();" onfocus="EmailForm();"maxLength="30" />
						</td>
						<td class="errordiv">
							<span id="emailError"></span><span id="emailForm"></span>
                            <@s.fielderror><@s.param>member.email</@s.param></@s.fielderror>
						</td>
					</tr>

					<tr>
						<td class="registertd1">
							设置登陆密码
							<span class="star">*</span>
						</td>
						<td class="registertd2">
							<@s.password id="passwd" name="passwd" cssClass="comname_text"
								onblur="PasswdIsNull();" onfocus="PasswordForm()" />
						</td>
						<td class="errordiv">
							<span id="passwdError"></span><span id="passwordForm"></span>
							<@s.fielderror><@s.param>passwd</@s.param></@s.fielderror>
						</td>
					</tr>

					<tr>
						<td class="registertd1">
							请再次确认密码
							<span class="star">*</span>
						</td>
						<td class="registertd2">
							<@s.password id="confirm_pwd" name="confirm_pwd"
								cssClass="comname_text" onblur="Confirm_pwdIsNull();" onfocus="Confirm_pwdForm()" />
						</td>
						<td class="errordiv">
							<span id="confirm_pwdError"></span><span id="confirmpwdForm"></span>
						</td>
					</tr>
					
					<tr>
						<td class="registertd1">
							验证码
							<span class="star">*</span>
						</td>
						<td class="registertd2">
							<@s.textfield maxlength="4" name="commentrand" id="rands" cssStyle="width: 60px;" onblur="RandsIsNull();" onfocus="RandsForm()" /> 
						    <img src="/imgrand.action" style="vertical-align: middle;" onclick="changeValidateCode(this)" />			
						</td>
						<td class="errordiv">
                            <span id="randsError"></span><span id="randForm"></span>
						</td>
					</tr>
			  </table>
              <div class="clear"></div>
              <div class="bt">
                <p>请阅读<a href="#" >《服务条款》</a></p>
                <div><input type="button" value="同意以上条款并注册" onclick="registerForm();"/>
                </div>
            </div>
        </div>
        <div class="content_right">
          <p>我已经注册，现在就<a href="/signin.html">登录</a></p>
        </div>
    </div>
    
    <div class="clear"></div>
 </div>
 </form>
 <!--底部开始-->
  <#include "/WEB-INF/template/bmall/default/footer.ftl">
 <!--底部结束-->
</body>
</html>
