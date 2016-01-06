 <div class="l_body f_left">
     <div class="notice">
        <div class="title">
        <h2><span class="title_font f_left"> 商家信息 </span></h2>
        </div>
     </div>
     <div class="new_ct">
        <ul>
           <#if isvip?if_exists=="1">
             <li>
			 <span class="vipBackImg f_left">
			 	${(sysconfig?if_exists)}&nbsp;会员<br/>
			 	第<font class="font_col">${years?if_exists}</font>年
			 	&nbsp;指数<font  class="font_col" >${(cust_name_credit)?if_exists}</font>
			 </span>
			 </li>
          </#if>
          <li>
      		公&nbsp;司&nbsp;名：<a href="/showroom/${user_name?if_exists}/profile.html" style="font-size:12px;">${(member.cust_name)?if_exists}</a>
          </li>
          <li class="lineli">联&nbsp;系&nbsp;人：${(member.contact_name)?if_exists}&nbsp;${(member.contact_sex)?if_exists} 
          <#if (member.contact_qq)?if_exists!=''>
           <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(member.contact_qq)?if_exists}&site=qq&menu=yes">
                <img border="0" src="/templets/${templateStyle?if_exists}/images/sm_qq.gif"/>
           </a>
           </#if>
           <#if (member.contact_msn)?if_exists!=''>
           <a href="msnim:add?contact=${(member.contact_msn)?if_exists}" target="_blank">
                <img src="/templets/${templateStyle?if_exists}/images/sm_msn.gif"/>
           </a>
           </#if>
          </li>
          <li><#if certification?if_exists>
          <a class="smz" href="/showroom/${user_name?if_exists}/certifiction.html">已实名制认证</a>
          <#else>无实名认证</#if>
          <a class="zs" href="/showroom/${user_name?if_exists}/membercert.html">证书荣誉(${certnum?if_exists})</a>
          </li>
          <li>
          </li>
          <li class="lineli">信用指数：&nbsp;<font class="font_col">${(cust_name_credit)?if_exists}</font>
          	<a href="/showroom/${user_name?if_exists}/creditdetail.html">明细</a>
          </li>
          <li>经营模式：
				${member.client_status?if_exists}
          </li>
          <li class="lineli">所在地区：
				${(member.address)?if_exists}
          </li>
          <li>
             <a class="scbut" href="###;" onclick="document.collectCompanyForm.submit();">收藏公司</a>
             <a class="scbut" href="###;" onclick="document.addMemberfriendFormds.submit();">加为商友</a> 
          </li>
        </ul>
     </div>   
     
     <#list SidebarList as sidebar>
     <#if sidebar.ch_code?if_exists=='wzgg'>
      <div class="news">
        <div class="title">
        <!-- 绑定侧栏标题-->
          <h2><span class="title_font f_left">${sidebar.ch_name?if_exists}</span>
          <a href="/showroom/${user_name?if_exists}/announcement.html" class="more f_right">更多>></a></h2>
        </div>
        <!-- 绑定侧栏网站公告-->
        <div class="notice_c">
          <p> <#if memberconfig.site_notice?if_exists!=''>
          <#if memberconfig.site_notice?length lt 70>${memberconfig.site_notice?if_exists}<#else>${memberconfig.site_notice[0..70]}...</#if>
          <#else>
          暂无数据
          </#if>
          </p>
        </div>
     </div>
     <!--通告结束-->
     <div class="clear"></div>
     <#else>
     <div class="news">
        <div class="title">
          <h2><span class="title_font f_left">${sidebar.ch_name?if_exists}</span>
          <#if sidebar.ch_code?if_exists=='xwzx'>
          <a href="/showroom/${user_name?if_exists}/news.html" class="more f_right">更多>></a></h2>
          </#if>
          <#if sidebar.ch_code?if_exists=='cpzx'>
          <a href="/showroom/${user_name?if_exists}/product.html" class="more f_right">更多>></a></h2>
          </#if>
           <#if sidebar.ch_code?if_exists=='lxfs'>
          <a href="/showroom/${user_name?if_exists}/contactus.html" class="more f_right">更多>></a></h2>
          </#if>
           <#if sidebar.ch_code?if_exists=='zgzs'>
          <a href="/showroom/${user_name?if_exists}/membercert.html" class="more f_right">更多>></a></h2>
          </#if>
            <#if sidebar.ch_code?if_exists=='yqlj'>
          <a href="/showroom/${user_name?if_exists}/link.html" class="more f_right">更多>></a></h2>
          </#if>
        </div> 
        <div class="new_c">
           <ul>
             <!-- 绑定侧栏新闻中心-->
             <#if sidebar.ch_code?if_exists=='xwzx'>
	             <#list newsList as news>
	             <li><a href="/showroom/${user_name?if_exists}/news/detail_${news.cert_id?if_exists}.html">
	             <#if news.title?length lt 12>${news.title?if_exists}<#else>${news.title[0..11]}...</#if>
	             </a></li>
	             </#list>
	             <#if newsList.size()==0><li>暂无数据</li></#if>
             </#if>
             <!--绑定侧栏产品中心-->
             <#if sidebar.ch_code?if_exists=='cpzx'>
                 <#list recommendProductList?if_exists as product>
	             <li><a href="/showroom/${user_name?if_exists}/product/detail_${product.product_id?if_exists}.html">
	             <#if product.title?length lt 12>${product.title?if_exists}<#else>${product.title[0..11]}...</#if>
	             </a></li>
	             </#list>
	             <#if recommendProductList.size()==0><li>暂无数据</li></#if>
             </#if>
              <!--绑定侧栏联系方式-->
             <#if sidebar.ch_code?if_exists=='lxfs'>
             	<#if member.contact_name?if_exists>
                 <li>联系人：${(member.contact_name)?if_exists}</li>
                </#if>
                <#if member.phone?if_exists>
                 <li>电&nbsp;&nbsp;话：${(member.phone)?if_exists}</li>
                 </#if>
                 <#if member.email?if_exists>
                 <li>邮&nbsp;&nbsp;件：${(member.email)?if_exists}</li>
                 </#if>
                 <#if member.contact_cellphone?if_exists>
                 <li>手&nbsp;&nbsp;机：${(member.contact_cellphone)?if_exists}</li>
                 </#if>
                 <#if member.fax?if_exists>
                 <li>传&nbsp;&nbsp;真：${(member.fax)?if_exists}</li>
                 </#if>
             </#if>
              <!--绑定侧栏资格证书-->
              <#if sidebar.ch_code?if_exists=='zgzs'>
                 <#list membercertList as membercert>
	             <li><a href="/showroom/${user_name?if_exists}/cert/detail_${membercert.cert_id?if_exists}.html">
	             <#if membercert.title?length lt 12>${membercert.title?if_exists}<#else>${membercert.title[0..11]}...</#if>
	             </a></li>
	             </#list>
	             <#if membercertList.size()==0><li>暂无数据</li></#if>
	          </#if>
	           <!--绑定侧栏友情链接-->
	            <#if sidebar.ch_code?if_exists=='yqlj'>
                 <#list linkList as link>
	             <li><a href="${link.link_url}" target="_blank">
	             <#if link.title?length lt 12>${link.title?if_exists}<#else>${link.title[0..11]}...</#if>
	             </a></li>
	             </#list>
	             <#if linkList.size()==0><li>暂无数据</li></#if>
	          </#if>
           </ul>
        </div> 
     </div>
     </#if>
     </#list>
     <!--新闻结束-->
     <div class="clear"></div>
     
     <div class="search">
       <div class="l_search f_left"></div>
       <div class="search_c f_left">
        <input id="keyvalue" name="keyvalue" type="text" class="sea_text"/>
         <div class="search_d">
            <select id="cate_select" class="f_left"><option value="0">请选择</option><option value="1">产品</option><option value="2">供应</option>
            
            <input type="button"  onclick="sousuo();"  class="f_right sea_button"/>
            <script type="text/javascript" language="javascript">
            function sousuo()
            {
               var objSel = document.getElementById("cate_select");
               var val = objSel.options[objSel.selectedIndex].value;
               var key=encodeURIComponent(encodeURIComponent(document.getElementById("keyvalue").value));
               switch(val){
               case '1': window.location.href="/companyproduct.action?user_name=${user_name?if_exists}&selvalue="+key; bleak;
               case '2': window.location.href="/companysupply.action?user_name=${user_name?if_exists}&selvalue="+key; bleak;
               default: alert("请选择搜索类型");
               }
             
            }
            </script>
         </div> 
       </div>
       <div class="r_search f_left"></div>
     </div>
     <div class="clear"></div>
     <!--搜索结束-->
    <!-- 收藏公司的提交表单 -->
     <form action="/member_Collect_add.action" name="collectCompanyForm" method="post">
		<input type="hidden" name="collect.title" id="title" value="${(member.cust_name)?if_exists}" />	
		<input type="hidden" name="collect.link_url" id="link_url1"  />
		<input type="hidden" name="loc" value="" />
	 </form>
	<script>
		document.collectCompanyForm.loc.value = document.location.href;
		document.getElementById("link_url1").value = document.location.href;
	</script>
	<!-- 价为商友的表单 -->
 	<form action="/member_Memberfriend_showview.action" method="post" name="addMemberfriendFormds">
 		<input type="hidden" name="memberfriend.cust_name" value="${(member.cust_name)?if_exists}"/>
 		<input type="hidden" name="loc" value="" />
 	</form>
 	<script>
    	document.addMemberfriendFormds.loc.value = document.location.href;
    </script>
  </div>
  <!--左部结束-->