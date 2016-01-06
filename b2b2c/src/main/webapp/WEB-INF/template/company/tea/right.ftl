
<script src="http://ditu.google.cn/maps?file=api&amp;v=2.x&amp;key=" type="text/javascript"></script>
<script src="/templets/company/tea/js/googlemap.js" type="text/javascript"></script>
<div class="rw250">
       <input type="hidden" id="address" value="${(member.address)?if_exists}"/>
       <div class="map">  
        <h2 class="mapth2"><span class="maptpic">本站地图</span></h2>
        <div class="mapbcont">
         <div class="mapcont">
            <div class="mappic">
            <#if ismap?if_exists!='1'>
            <div id="googleResult" style="width:220px; margin:4px 0;"></div>
            <div id="googleMap" style="width:220px; height:168px; border:solid 1px #ccc">loading...</div>
            </#if>
            </div>
            <p>地址：<span>
            <#if member?if_exists>
            <#if member.address?length lt 30>${(member.address)?if_exists}<#else>${member.address[0..29]}...</#if>
            </#if></span></p>
            <p>电话：<span>${(member.phone)?if_exists}</span></p>
            <p>营业时间：<span>日常营业</span>[8:00--22:00]</p>
            <p>服务标签：<span>
            <#if member.cust_key?if_exists!=''>
            <#if member.cust_key?length lt 70>${member.cust_key?if_exists}<#else>${member.cust_key[0..70]}...</#if>
            <#else>
              暂无数据
            </#if>
                       </span></p>
            <p class="mapp"></p>
            <p><a class="scbut" href="###;" onclick="document.collectCompanyForm.submit();"><input type="button" class="intbut" value="收藏本店"></a></p>
            </div>
         </div>
       </div>
      
      
      <h2 class="mapth2"><span class="maptpic">友情链接</span></h2>
        <div class="mapbcont">
         <div class="mapcont">
            <#list linkList as link>
             <p>
             	<a href="${link.link_url}" target="_blank">
             		<#if link.title?length lt 12>${link.title?if_exists}<#else>${link.title[0..11]}...</#if>
             	</a>
             </p>
             </#list>
             <#if linkList.size()==0><li>无友情链接</li></#if>
             </div>
         </div>
       
     </div>
     
   </div>
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
   
   