
<#if (memberconfig.back_img)?if_exists!=''>
   <body style="background:#f1f6fa url(${(memberconfig.back_img)?if_exists})">
   <#else>
   <#if (memberconfig.back_color)?if_exists!=''>
   <body style="background:${(memberconfig.back_color)?if_exists}!'#f1f6fa'">
   </#if>
</#if>

<script src="/include/js/common.js"></script>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/top.js"></script>
<script src="/templets/company/tea/js/googlemap.js" type="text/javascript"></script>
<!--头部-->
<div class="topback">
<input type="hidden" id="higval">
<input type="hidden" id="username" value="${user_name?if_exists}"/>
<input type="hidden" name="memberuserlog.user_name" id="usname"/>
  <div class="topw960">
     <!--<@s.form action="/portal/memberuser!loginUser.action" method="post" >-->
     <div class="toplcont f_left">
          <span>您好<a href="/memberindex.action">${(memberuserlog.user_name)?if_exists}</a>,欢迎您来${(member.cust_name)?if_exists}</span>
          <#if (memberuserlog.user_name)?if_exists==''>
          <span>用户名：<@s.textfield id="user_name" name="memberuser.user_name" maxLength="20"  cssClass="toptext" value=""/></span>
          <font color="red"><@s.label id="usererror" name="usererror"/></font>
          <span>密码：<@s.password id="passwd" name="memberuser.passwd" maxLength="20"  cssClass="toptext"/></span>
          <font color="red"><@s.label id="pwerror" name="pwerror"/></font>
          <input type="hidden" name="loc" id="refloc" value=""/>
          <input type="button" value="登 录" class="topbut" onclick="usubmit()" style="cursor:pointer;"/>
          <input type="button" onclick="location.href='/register.html';" class="topbut" style="cursor:pointer;" value="注 册"  />
          <#else>
          <a href="/member_Memberuser_logout.action">[退出]</a>
          </#if>
     </div>
     <!--</@s.form>-->
     <div class="toprcont f_right"> 
            <a href="###;" onclick="setMyHome();">设为首页</a>
            <a href="###;" onClick="addFavorite();">加入收藏</a>
            <a href="/">返回${cfg_webname?if_exists}首页</a>
     </div>
  </div>
</div>
<div class="clear"></div>

<!--logo-->
<div class="w960">
  <div class="logo f_left" style="width:960px;background:url('${memberconfig.banner}') no-repeat right;">
   <a href="/showroom/${user_name?if_exists}">
			<#if (memberconfig.logo_img)?if_exists!=''>
				<img src="${(memberconfig.logo_img)?if_exists}" onload="if(this.height>80){this.height=80;}" class="f_left"/>
		    <#else>
		        <img src="/templets/company/tea/images/logo_03.gif">
			</#if>
		</a>
  </div>
</div>
<div class="clear"></div>




<!--导航-->
<div class="navback">
  <div class="navcont">
    <ul>
      <li><a href="/showroom/${user_name?if_exists}"  id="nav1" class="ahover">店铺首页</a></li>
      <li><a href="/showroom/${user_name?if_exists}/profile.html" id="nav2">本店介绍</a></li>
      <li><a href="/showroom/${user_name?if_exists}/news.html" id="nav3">本店动态</a></li>
      <li><a href="/showroom/${user_name?if_exists}/product.html" id="nav4">产品橱窗</a></li>
      <li><a href="/showroom/${user_name?if_exists}/gallery.html" id="nav5">图片展示</a></li>
      <li><a href="/companymyself.action?user_name=${user_name?if_exists}" id="nav6">本店地图</a></li>
      <li><a href="/showroom/${user_name?if_exists}/contactus.html" id="nav7">留 言</a></li>
    </ul>
  </div>
</div>
<div class="clear"></div>

<script>
	var hrefurl = window.location.href;
	var num = 1;
	if(hrefurl.indexOf('profile') > -1){num = 2;}
	if(hrefurl.indexOf('news') > -1){num = 3;}
	if(hrefurl.indexOf('product') > -1){num = 4;}
	if(hrefurl.indexOf('gallery') > -1){num = 5;}
	if(hrefurl.indexOf('companymyself') > -1){num = 6;}
	if(hrefurl.indexOf('contactus') > -1 || hrefurl.indexOf('getleave') >-1){num = 7;}
	xhClassName(num);
	function xhClassName(val){
		for(var i=1;i<=7;i++){
			if(val == i){
				document.getElementById('nav'+i).className = 'ahover';
			}else{
				document.getElementById('nav'+i).className = '';
			}
		}
	}
</script>



