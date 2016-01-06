<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<link href="/templets/${templateStyle?if_exists}/css/login.css" rel="stylesheet" type="text/css" />
<title>会员登陆-${cfg_webname?if_exists}</title>
</head>	 
<body>
<div class="top">
  <a href="${cfg_index?if_exists}"><img src="${cfg_logo_img?if_exists}" class="logo"/></a>
  <div class="top_right">
    <P><a href="${cfg_index?if_exists}">返回首页</a> | <a href="#" onClick="window.external.AddFavorite(location.href,document.title)">收藏本页</a></P>
    <P>如需帮助，请咨询或拨打<span>${web_phone?if_exists}</span> </P>
  </div>
</div>
<div class="clear"></div>
<div class="cont">
<div class="cont_adv">
  <script src="/include/advshow.jsp?pos_id=100"></script>
</div>
<div class="cont_main">
  <div class="login_main">
   <script src="/include/discuzlink/loginoutDiscuz.jsp"></script>
  	<@s.form action="/portal/memberuser!loginUser.action" method="post" validate="true">
    <h2 class="title">${cfg_webname?if_exists}会员登录</h2>
    <p class="name"><span>帐号</span><@s.textfield id="user_name" name="memberuser.user_name" maxLength="20"  cssClass="text"/><a href="/portal/memberuser!executename.action">找回登录名</a></p>
    <p class="name"><span>密码</span><@s.password name="memberuser.passwd" maxLength="20"  cssClass="text"/><a href="/portal/memberuser!executepwd.action">找回密码</a></p>
    <div class="buttom">
       <input type="hidden" name="loc" id="refloc" value=""/>
       <input type="submit" value="登 录" class="dl" style="cursor:pointer;"/>
       <input type="button" onclick="location.href='/register.html';" style="cursor:pointer;" value="注 册"  class="zc" />
    </div>
    <div class="tipDiv">
    	<@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
    	<@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror>
    </div>
    <P class="other"><b>您还可以用</b> <a>手机号码登录</a></P>
    </@s.form>
  </div>
  
  <img src="/templets/${templateStyle?if_exists}/images/daoying.png" />
</div>
</div>
<div class="clear"></div>

<div class="bottom w960">
  <P>Copyright 2011-2012 瑞宝通（厦门）信息科技有限公司 版权所有</P>
  <div class="bottom_pic">
    Powered by <a href="http://www.ruibaotong.net" target="_blank">瑞宝通B2B系统</a>
  </div>
</div>

<script type="text/javascript">
  $(document).ready(function(){ 
	     var un="#user_name";
	     var un_conent="用户名/邮箱/手机号";
		 if($(un).val()=='' || $(un).val()==un_conent){
		     $(un).val(un_conent);
		     $(un).addClass("usersize");
		 }	
		 //获得焦点事件
		 $(un).focus(function(){	
			 if($(un).val()=='' || $(un).val()==un_conent){	     
			     $(un).val("");
			     $(un).removeClass("usersize");
			  }
		 });	
		 //失去焦点事件
		 $(un).blur(function(){
		     if($(un).val()==''){
			    $(un).val(un_conent);
			    $(un).addClass("usersize");
			 }
		 });		 
   });
   var urlhref = window.location.href;
   var locstr = '?loc=';
   if(urlhref.indexOf(locstr) > -1){
   	  var posi = urlhref.indexOf('?loc=');
   	  var loc = urlhref.substring(posi,urlhref.length);
   	  loc = loc.replace(locstr,'');
   	  document.getElementById('refloc').value = loc;
   }
</script>
</body>
</html>