<script type="text/javascript" src="/templets/bmall/js/goods.js"></script>
<script type="text/javascript" src="/include/js/top.js"></script>
<div class="user_top">
  <div class="user">
      <P class="welcome f_left">您好，欢迎来到<a href="/mallindex.html">${cfg_mallwebname?if_exists}</a>！  <span><script src="/include/ismalllogin.jsp"></script></span></P>
      <P class="help f_right"><span class="order"><a href="/bmall_Goodsorder_list.action">我的订单</a></span>
      <span class="help_sp"><a href="/mall/goods!mallcart.action" class="help_car">购物车</a></span>
      <span><a href="/mallhelp.html">帮助中心</a></span></P>
  </div>
</div>

<div class=" w980"> 
   <a href="###;"><img src="<#if (shopconfig.shop_logo)?if_exists!="">${(shopconfig.shop_logo)?if_exists}<#else>${cfg_malllogo?if_exists}</#if>" width="203px" height="52px"  class="f_left"/></a>
   <div class="shop f_right">
      <div class="shopInfo">
        <span><a href="#"><b>${(member.cust_name)?if_exists}</b></a>
        <a href="http://wpa.qq.com/msgrd?v=3&uin=${(member.contact_qq)?if_exists}&site=qq&menu=yes" target="_blank"><img border="0" src="http://wpa.qq.com/pa?p=1:${(member.contact_qq)?if_exists}:16" alt=""></a>
        </span>
        <span>
        <a href="###;" onclick="addFavorite()">点击收藏</a>
        </span>
      </div>
    
      <div class="sear f_left">
         <input type="text" class="searText" id="selValue" />
         <input type="button" class="searSc" onclick="return pselect('p','');"/>
         <input type="button" class="searDp" onclick="return bselect();"/>
         <input type="hidden" id="username" value="${(memberuser.user_name)?if_exists}"/>
      </div>
   </div>
   
   <div class="clear"></div>
</div>

<div class="ShopCalled">
     <img  src="<#if (shopconfig.banner_image)?if_exists!="">${(shopconfig.banner_image)?if_exists}<#else>/templets/shop/images/ShopCalled.jpg</#if>" width="100%" height="105px" />
</div>

<div class="nav">
  <ul>
     <#list memberchanneList as mlist>
     <#if mlist.ch_code?if_exists!="myself">
     <li><a href="/shop/${user_name?if_exists}/${(mlist.ch_code)?if_exists}.html">${(mlist.ch_name)?if_exists}</a></li>
     <#else>
     <li><a href="/${mlist.ch_code}.action?user_name=${user_name?if_exists}&ch_id=${mlist.ch_id?if_exists}">${(mlist.ch_name)?if_exists}</a></li>
     </#if>
     </#list>
  </ul>
</div>

















