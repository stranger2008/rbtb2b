<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/forgot_username.css" rel="stylesheet" type="text/css" />
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<title>忘记用户名-${cfg_mallwebname?if_exists}</title>
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

<@s.form action="/portal/memberuser!retrievename.action" method="post" validate="true">
<div class="w960">
    <h2 class="password_title">找回用户名</h2>
    <#if success?if_exists=='1'>   
	  <div class="pass_cont" >
		    <div class="pass_input" style="height:200px;">
			    <div class="pass_input_t"></div> 
				    <div class="pass_input_c" >
				             <ul>
				                 <li style="height:80px;font-size:14px;">
				                   	您好，${email?if_exists}，我们已发送您的用户名到您的邮箱上，请查收！
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
	                    <label class="lb_un">邮箱：</label>
	                    <@s.textfield name="email" cssClass="pass_text1 lb_un6" maxLength="20"/>
	                 </li>
	                 <li>
	                   <label class="lb_un">验证码：</label>
	                   <input type="text" maxlength="4" name="commentrand" id="rands" style="width:50px;" /> 
	                   <img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
	                 </li>
	             </ul>
	           </div>  
	           <div class="pass_input_d" style="height:30px;padding-top:10px;">
	           		<@s.fielderror><@s.param>email</@s.param></@s.fielderror>
	           		<@s.fielderror><@s.param>trand</@s.param></@s.fielderror>
	           </div>  
	        </div>
	        <div class="tj"> <input type="submit" class="pass_tj" value=""/></div>
	    </div>    
    </#if>
    
</div>
<@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
</@s.form>
</body>
</html>
