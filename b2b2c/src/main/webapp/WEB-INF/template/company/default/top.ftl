<#if (memberconfig.back_img)?if_exists!=''>
   <body style="background:#f1f6fa url(${(memberconfig.back_img)?if_exists});background-repeat: ${(memberconfig.back_img_repeat)?if_exists}; background-attachment:fixed; background-position: ${(memberconfig.back_img_position)?if_exists};">
<#else>
   <#if (memberconfig.back_color)?if_exists!=''>
   		<body style="background:${(memberconfig.back_color)?if_exists}">
   <#else>
   		<body>
   </#if>
</#if>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script src="/include/js/common.js"></script>
<input type="hidden" id="username" value="${user_name?if_exists}"/>
<div class="user_top">
  <div class="user">
       <!--<@s.form action="/portal/memberuser!loginUser.action" method="post" >-->
     <div class="toplcont f_left">
          <span>您好<a href="/memberindex.action">${(memberuserlog.user_name)?if_exists}</a>,欢迎您来${(member.cust_name)?if_exists}</span>
          <script src="/include/islogin.jsp?loc=?loc=/showroom/${user_name?if_exists}"></script>
     </div>
     <!--</@s.form>-->
  
  	<span style="float:right;">
		<a style="cursor:pointer;" onclick="addFavorite();">加入收藏</a>&nbsp;|&nbsp;
		<a style="cursor:pointer;" onclick="setMyHome();">设为首页</a>
	</span>
	<span style="float:right;">
		<a href="/">返回${cfg_webname?if_exists}首页</a>
	</span>
  </div>

      
</div>
<style type="text/css">
.font_col{color:#CC0000;font-size:15px;font-weight:600;}
</style>
<!--头部user结束-->
<div class="clear"></div> 
<div class="w960 logo_top" style="width:960px;background:url('${memberconfig.banner}') no-repeat right;">
	<div class="logo f_left">  
		<a href="/showroom/${user_name?if_exists}">
			<#if (memberconfig.logo_img)?if_exists!=''>
				<img src="${(memberconfig.logo_img)?if_exists}" onload="if(this.height>80){this.height=80;}" class="f_left"/>
			</#if>
		</a>
		<P class="f_left logo_name" ><span>${(memberconfig.web_name)?if_exists}</span><br />
			<#if (member.main_product)?if_exists != ''>
	            <#if member.main_product?length lt 31>
	            ${member.main_product?if_exists}
	            <#else>
	            ${member.main_product[0..30]}...
	            </#if>
	        </#if>
		</P>
	</div>

</div>
<!--log0结束-->
<div class="clear"></div>
<div class="w960 nav">
  <div class="nav_l f_left"></div>
  <div class="nav_r f_right"></div>
  <div class="nav_c f_left">
    <ul class="f_left">
    <#assign count=1>
      <#list pagepartList as pagepart>
      	<#if count lt 12>
	      <#if pagepart.ch_code?if_exists=="index"> 
	      		<li><a href="/showroom/${user_name?if_exists}">${pagepart.ch_name?if_exists}</a></li>
	      <#elseif pagepart.ch_code?if_exists!="myself"> 
	      		<li><a href="/showroom/${user_name?if_exists}/${pagepart.ch_code?if_exists}.html">${pagepart.ch_name?if_exists}</a></li>
	      <#else>
	      		<li><a href="/company${pagepart.ch_code}.action?user_name=${user_name?if_exists}&ch_id=${pagepart.ch_id?if_exists}">${pagepart.ch_name?if_exists}</a></li>
	      </#if>
	      <#assign count=count+1>
			</#if>
      </#list>
    </ul>
  </div>
</div>
<div id="searchDiv" onmouseover="this.style.display = 'block';"  onmouseout="this.style.display = 'none';"></div>
<div id="supply_main" style="display:none;">
      <#list pagepartlastList as pagepart>
       <ul>
	      <#if pagepartlastList.ch_code?if_exists=="index"> 
	      		<span class="navs"><li><a href="/showroom/${user_name?if_exists}">${pagepart.ch_name?if_exists}</a></li></span>
	      <#elseif pagepartlastList.ch_code?if_exists!="myself"> 
	      		<span class="navs"><li><a href="/showroom/${user_name?if_exists}/${pagepart.ch_code?if_exists}.html">${pagepart.ch_name?if_exists}</a></li></span>
	      <#else>
	      		<span class="navs"><li><a href="/company${pagepart.ch_code}.action?user_name=${user_name?if_exists}&ch_id=${pagepart.ch_id?if_exists}">${pagepart.ch_name?if_exists}</a></li></span>
	      </#if>
	   </ul>
      </#list>
</div>

<!--导航条结束-->
<div class="clear"></div>
<div class="w960" style="width:960px;height:200px;overflow:hidden;">
   <#if (memberconfig.header_img)?if_exists!=''>
   		<img src="${(memberconfig.header_img)?if_exists}" onload="if(this.height>200){this.height=200;}"/>
   <#else>
   		<img src="/templets/company/${memberconfig.temp_code?if_exists}/images/com_pic.gif"/>
   </#if>
</div>

<!--图片结束-->
