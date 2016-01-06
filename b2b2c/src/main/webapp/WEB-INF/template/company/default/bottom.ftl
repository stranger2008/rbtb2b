<div class="clear"></div>

<div class="bottom">
	<p class="map">
     <#list pagepartList as pagepart>
      <#if (pagepart.ch_code)?if_exists=="index"> <a href="/showroom/${user_name?if_exists}">${(pagepart.ch_name)?if_exists} | </a>
      <#elseif (pagepart.ch_code)?if_exists!="self"> <a href="/showroom/${user_name?if_exists}/${(pagepart.ch_code)?if_exists}.html">${(pagepart.ch_name)?if_exists} | </a>
      <#else><a href="/company${(pagepart.ch_code)?if_exists}.action?user_name=${user_name?if_exists}&ch_id=${(pagepart.ch_id)?if_exists}">${(pagepart.ch_name)?if_exists} | </a>
      </#if>
      </#list>
      <a href="/login.html">管理员入口</a>
     </p>
     <p>
      ©2011${(memberconfig.web_name)?if_exists}&nbsp;版权所有&nbsp;技术支持：<a href="http://www.ruibaotong.net" target="_blank">瑞宝通B2B</a>&nbsp; 
      <#if (memberconfig.is_dis)?if_exists=="0">
      		访问量：${(memberconfig.loc_num)?if_exists}
      </#if>
    </p>
</div>