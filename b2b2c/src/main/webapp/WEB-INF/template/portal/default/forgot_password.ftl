<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/forgot_password.css" rel="stylesheet" type="text/css" />
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title>忘记密码</title>
<script type="text/javascript">
 function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    } 
</script>
</head>

<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<div class="position"><P>当前位置: <a href="/">首页</a> > <a href="###;">找回密码</a></P></div>
<@s.form action="/portal/memberuser!retrievepwd.action" method="post" validate="true">
<div class="w960">
    <h2 class="password_title">找回密码</h2>
    <#if success?if_exists=='1'>
   
	    <div class="pass_cont" >
		    <div class="pass_input" style="height:200px;">
		    	<div class="pass_input_t"></div> 
		    	<div class="pass_input_c" >
		             <ul>
		                 <li style="height:80px;font-size:14px;">
		                   	您好，${username?if_exists}，我们已重置您的账号密码，密码已经发送到&nbsp;<font color="#F68B1F"><b>${email?if_exists}</b></font>&nbsp;上，请查收！
		                 </li>
		             </ul>
		    	</div>
			    <div class="pass_input_d"></div>
		    </div>
	    </div>
    
    <#else>
    
     <div class="pass_cont">
        <div class="pass_input">
           <div class="pass_input_t"></div>   
           <div class="pass_input_c">
             <ul>
                 <li>
                    <label class="lb_un lb_un6">会员名：</label>
                    <@s.textfield name="username" cssClass="pass_text1 lb_un6" maxLength="20"/>
                 </li>
                 <li>
                    <label class="lb_un">邮箱：</label>
                    <@s.textfield name="email" cssClass="pass_text1 lb_un6" maxLength="20"/>
                 </li>
             </ul>
           </div>  
           <div class="pass_input_d" style="height:30px;padding-top:10px;">
           		<@s.fielderror><@s.param>username</@s.param></@s.fielderror>
           		<@s.fielderror><@s.param>email</@s.param></@s.fielderror>
           		<@s.fielderror><@s.param>trand</@s.param></@s.fielderror>
           </div>  
        </div>
        <div class="pass_input">
           <div class="pass_input_t"></div>   
           <div class="pass_input_c">
             <ul>
                 <li>
                   <label class="yzm lb_un6">验证码：</label>
                   <input type="text" maxlength="4" name="commentrand" id="rands" style="width:50px;" /> 
                   <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
                 </li>
             </ul>
           </div>  
           <div class="pass_input_d">
           </div>
        </div>
        <div class="tj"> <input type="submit" class="pass_tj" value=""/></div>
    </div>
    
    </#if>
    
</div>
<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
</@s.form>
<#include "/${templateRoute?if_exists}/bottom.html">
</body>
</html>
