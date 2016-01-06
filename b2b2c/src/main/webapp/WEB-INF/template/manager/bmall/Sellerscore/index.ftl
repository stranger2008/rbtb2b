<html>
<head>
<title>店铺动态评分</title>
</head>
<body>
	<div class="postion">
  		<a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">店铺动态评分</a>
    </div>
 <@s.form action="/bmall_Sellerscore_bmalllist.action"  method="post"  validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>店铺管理</h2></div>
     <div class="ropot">
     <h2 class="rotitle"><span><td class="fthstyle1">店铺动态评分记录信息</td></span></h2>
     
       <div class="table_infor f_left">
          <table style="width:750px;" >
            <#list sellerscoreCountList as sellist>
		            <tr><td class="sellerscore_desrci" >宝贝与描述相符：</td>
		            <td>
		            <#if sellist.desc_score?if_exists!="">
			    	 <span class="sellerscore_score">${sellist.desc_score?if_exists}</span>分
			    	<#else>
			    	 暂无评分
			    	</#if>
		           </td></tr>  
		            <tr><td  class="sellerscore_desrci">卖家的服务态度：</td>
		            <td>
		             <#if sellist.service_score?if_exists!="">
			    	 <span class="sellerscore_score">${sellist.service_score?if_exists}</span>分
			    	<#else>
			    	 暂无评分
			    	</#if>
		            </td></tr>
		            <tr><td  class="sellerscore_desrci">卖家发货的速度：</td>
		            <td>
		             <#if sellist.delivery_score?if_exists!="">
			    	 <span class="sellerscore_score">${sellist.delivery_score?if_exists}</span>分
			    	<#else>
			    	 暂无评分
			    	</#if>
		            </td></tr> 
            </#list>
          </table>
     </div>
     <div class="clear"></div>
  
       <div class="table_infor">
	        <table cellspacing="0" class="bmall_list_table">
		         <tr>
				     <td class="fthstyle1">评分人</td>
			     	 <td class="fthstyle1">描述相符打分</td>
			     	 <td class="fthstyle1">服务态度打分</td>
			     	 <td class="fthstyle1">发货速度打分</td>
			     	 <td class="fthstyle1">评分时间</td>
		         </tr>
			    <#list sellerscoreList as sellerscores>
			      <tr>   
			    	<td >${sellerscores.user_name?if_exists}</td>
			    	<td >
			    	<#if sellerscores.desc_score?if_exists!="">
			    	${sellerscores.desc_score?if_exists}
			    	<#else>
			    	0
			    	</#if>
			    	</td>
			    	<td >
			    	<#if sellerscores.service_score?if_exists!="">
			    	${sellerscores.service_score?if_exists}
			    	<#else>
			    	0
			    	</#if>
			    	</td>
			    	<td >
			    	<#if sellerscores.delivery_score?if_exists!="">
			    	${sellerscores.delivery_score?if_exists}
			    	<#else>
			    	0
			    	</#if>
			    	</td>
			    	<td >${sellerscores.in_date?if_exists}</td>
			 	 </tr>
			  	</#list>
	       </table>
	        <div class="listbottom">
	        ${pageBar?if_exists}
	        </div>           
   </div>
</div>
 </@s.form>
</body>
</html>

















