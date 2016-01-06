<html>
<head>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/backstage.css" rel="stylesheet" type="text/css" />
<title>会员首页</title>
</head>
<body>
    <div class="rightside f_right">
     <div class="rpostion"><h2>账号管理</h2></div>
           
     <div class="base_infor">
       <h2 class="base_title">您的基础信息</h2>
       <div class="baseInfor">
        <p><span>您的账号：</span><span class="email">${user_name?if_exists}</span><a href="/bmall_Memberuser_updatePw.action" class="cbule">修改密码</a></p>
        <p><span>登陆邮箱：</span><span class="email">${email?if_exists}</span><a href="/bmall_Memberuser_updateEmail.action" class="cbule">修改邮箱</a></p>
        <p><span>上次登陆：</span><span>${login_time?if_exists.substring(0,19)}</span><!--<span>(不是您登陆的？请<a href="#" class="cbule">点击这里</a>)</span>-->
       </div>
     </div>
        
     <div class="clear"></div>
     
     <div class="base_infor">
       <h2 class="base_title">您的安全服务</h2>
       <div class="safety">
        <p><span class="yesback">已完成</span><span>身份认证</span><span class="safespan">用于提升账号的安全性和信任级别。认证后的有卖家记录的账号不能修改认证信息</span></p>
        <p><span class="strength">强度：
        <#if pass_str?if_exists=='0'>弱</#if>
        <#if pass_str?if_exists=='1'>中</#if>
        <#if pass_str?if_exists=='2'>强</#if>
        <#if pass_str?if_exists==''>未知</#if>
        </span><span>登陆密码</span><span class="safespan">安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个包含数字和字母，并且长度超过6位以上的密码</span><a href="/bmall_Memberuser_updatePw.action" class="cbule">修改</a></p>
        <p>
        <#if isque?if_exists==0>
         <span class="deng">未设置</span>
        <#else>
         <span class="yesback">已完成</span>
        </#if>
        
        <span>密保问题</span><span class="safespan">是您找回登陆密码的方式之一。建议您设置一个容易记住且设置容易被他人获取的问题和答案，更有效保障您的密码安全。</span>
        <#if isque?if_exists==0>
        <a href="/bmall_Memberuser_viewque.action" class="cbule">设置</a>
        <#else>
        <a href="/bmall_Memberuser_viewque.action" class="cbule">修改</a>
        </#if></p></div>
     </div>
</body>
</html>

