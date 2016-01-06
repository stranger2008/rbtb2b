
<!--底部开始-->
<div class="clear"></div>
<div class="bottom">
   <div class="w980">
       <ul class="bottom_help">
           <li class="bottom_t way">新手上路</li>
           <#assign onecount=1>
           <#list aboutusIndexList as hplist>
            <#if hplist.ch_id?if_exists=="1" && onecount lt 5>
                 <li><a href="/mallhelp-${hplist.info_id?if_exists}.html" target="_blank">${hplist.title?if_exists}</a></li>
            	<#assign onecount=onecount+1>
            </#if>
           </#list>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t security">购物指南</li>
            <#assign twocount=1>
           <#list aboutusIndexList as hplist>
            <#if hplist.ch_id?if_exists=="2" && twocount lt 5>
                 <li><a href="/mallhelp-${hplist.info_id?if_exists}.html" target="_blank">${hplist.title?if_exists}</a></li>
            	<#assign twocount=twocount+1>
            </#if>
           </#list>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t after_sales">支付配送方式</li>
           <#assign therecount=1>
           <#list aboutusIndexList as hplist>
            <#if hplist.ch_id?if_exists=="3" && therecount lt 5>
                 <li><a href="/mallhelp-${hplist.info_id?if_exists}.html" target="_blank">${hplist.title?if_exists}</a></li>
            	<#assign therecount=therecount+1>
            </#if>
           </#list>
       </ul>
       <ul class="bottom_help">
           <li class="bottom_t about">购物条款</li>
           <#assign fourcount=1>
           <#list aboutusIndexList as hplist>
            <#if hplist.ch_id?if_exists=="4" && fourcount lt 5>
                 <li><a href="/mallhelp-${hplist.info_id?if_exists}.html" target="_blank"4>${hplist.title?if_exists}</a></li>
            	<#assign fourcount=fourcount+1>
            </#if>
           </#list>
       </ul>
       <div class="clear"></div>
   </div>
   <div class="w980 copyright">
      
      <#include "/WEB-INF/template/bmall/default/footer.ftl">
      
   </div>
</div>