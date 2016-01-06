 <html>
<head>
<title>我是卖家</title>
<link href="/include/css/bmall/backstage.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <div class="rightside f_right">
     
     <div class="ropot">
       <h2 class="rotitle"><span>我是卖家</span></h2>
      
      <div class="rightside f_right">
     <div class="sellcont">
        <div class="sellpic f_left">      
        <#if (shopconfig.shop_logo)?if_exists>
		       	<img src="${(shopconfig.shop_logo)?if_exists}" width="100" height="100" alt="店铺LOGO">
		<#else>
		       	<img src="/include/images/nopic.jpg"  width="100" height="100" alt="店铺LOGO">
		</#if>
        </div>
        <div class="sellintro f_left">
          <p>会员名称：<span class="cbule nsell">${(member.cust_name)?if_exists}</span>
          		<span><img src="/templets/bmall/images/zs_06.gif"><img src="/templets/bmall/images/zs_06.gif"><img src="/templets/bmall/images/zs_06.gif"></span>
          </p>
          <p>店铺名称：<a href="#" class="cbule dsell">${(shopconfig.shop_name)?if_exists}</a>
          		<span>${area_String?if_exists}</span>
          </p>
          <p>邮箱：<span class="cbule">${(shopconfig.email)?if_exists}</span></p>
        </div>
        <div class="sellscore f_left">
          <p><span class="score">店铺动态评分</span><span>与同行业相比</span></p>
           <#list scoreList?first as score>
          <p><span>描述相符：</span>
          <span class="cbule">
          	<#if score.desc_score?length lt 4>
		                 	${score.desc_score?if_exists?string.number}
		    <#else>
		                 	${score.desc_score[0..3]}
		     </#if>
		  </span></p>
          <p><span class="score2">服务速度：
          <span class="cbule">
          <#if score.service_score?length lt 4>
		                 	${score.service_score?if_exists?string.number}
		                 <#else>
		                 	${score.service_score[0..3]}
		                 </#if>	</span></span><span class="gaoyu">高于</span><span class="bred">19.63%</span></p>
          <p><span class="score2">发货速度：<span class="cbule">
          <#if score.delivery_score?length lt 4>
		                 	${score.delivery_score?if_exists?string.number}
		  <#else>
		                 	${score.delivery_score[0..3]}
		  </#if>
          </span></span><span class="gaoyu">高于</span><span class="bred">19.63%</span></p>                 

           </#list>             
           <p class="sellsc"></p>
        </div>
        <div class="clear"></div>
     </div>
     
     <div class="shopRemind">
       <h2 class="sellhs">店铺提醒</h2>
       
       <div class="srw259 f_left">
         <h2 class="srtitle">商品提醒</h2>
         <div class="srcont">
           <ul>
             <li><a target="_self" href="/bmall_Goods_shelvesList.action?&upId=0">待上架商品(<span>${goodsCount?if_exists}</span>)</a></li>
             <li><a target="_self" href="/bmall_Goods_list.action?&upId=0">库存不足商品(<span>${stockCount?if_exists}</span>)</a></li>
             <li><a target="_self" href="/bmall_Goods_list.action?&tuiId=0">推荐的商品(<span>${labelCount?if_exists}</span>)</a></li>
             <li class="sellnuli"><a target="_self" href="/bmall_Goods_list.action?&cuId=0">促销中的商品(<span>${lbCount?if_exists}</span>)</a></li>
           </ul>    
         </div>
      </div>
      
      <div class="srw259 f_left">
         <h2 class="srtitle">订单管理</h2>
         <div class="srcont">
           <ul>
           	<li><a target="_self" href="/bmall_Goodsorder_manaorder.action?&daiorderId=0">待发货订单(<span>${daiorderCount?if_exists}</span>)</a></li>
            <li><a target="_self" href="/bmall_Goodsorder_manaorder.action?&weiId=0">未处理订单(<span>${orderCount?if_exists}</span>)</a></li>
            <li><a target="_self" href="/bmall_Goodsorder_manaorder.action?&jinId=0">今天订单(<span>${todayOrder?if_exists}</span>)</a></li>
            <li class="sellnuli"><a target="_self" href="/bmall_Goodsorder_manaorder.action?&daiId=0">待付款订单(<span>${payCount?if_exists}</span>)</a></li>
           </ul>    
         </div>
      </div> 
      
       <div class="srw259 f_left">
         <h2 class="srtitle">事务管理</h2>
         <div class="srcont">
           <ul>
             <li><a target="_self" href="/bmall_Busimes_list.action?&jinId=0">今日留言(<span>${countNow?if_exists}</span>)</a></li>
             <li><a target="_self" href="/bmall_Busimes_list.action?&weiId=0">未处理留言(<span>${notcount?if_exists}</span>)</a></li>
             <li><a target="_self" href="/bmall_Goodsask_list.action?&jinId=0">今日咨询(<span>${countSask?if_exists}</span>)</a></li>
             <li class="sellnuli"><a target="_self" href="/bmall_Goodsask_list.action?&daiId=0">待处理咨询(<span>${notSask?if_exists}</span>)</a></li>
           </ul>    
         </div>
       </div>
       
       <div class="clear"></div>
       
     </div>
   </div>
      
</div>
      

</body>
</html>

