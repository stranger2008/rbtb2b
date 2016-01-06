<div class="bar">
       <div class="barD">
        <h2>商家信息</h2>
        <ul class="shopPpromise">
           <li>正品保障</li>
           <li>提供发票</li>
           <li>七天退换</li>
        </ul>
        <div class="shopIntro">
            <div class="shopRate">
               <h4>店铺动态评分<span class="compare">与同行业相比</span></h4>
               
                 <ul>
                  <#list scoreList?first as score>
	                 <li>描述相符：<a href="###;" target="_blank" ><em class="count">
		                 <#if score.desc_score?length lt 4>
		                 	${(score.desc_score)?if_exists?string.number}`
		                 <#else>
		                 	${score.desc_score[0..3]}
		                 </#if>
	                 </em><span class="rateinfo"><b>高于</b><em>3.57%</em></span></a></li>
	                 <li>服务态度：<a href="###;" target="_blank" ><em class="count">
		                 <#if score.service_score?length lt 4>
		                 	${score.service_score?if_exists?string.number}
		                 <#else>
		                 	${score.service_score[0..3]}
		                 </#if>	                 
	                 </em><span class="rateinfo"><b>高于</b><em>3.57%</em></span></a></li>
	                 <li>发货速度：<a href="###;" target="_blank" ><em class="count">
		                 <#if score.delivery_score?length lt 4>
		                 	${score.delivery_score?if_exists?string.number}
		                 <#else>
		                 	${score.delivery_score[0..3]}
		                 </#if>
	                 </em><span class="lower"><b>低于</b><em>3.57%</em></span></a></li>
	              </#list>
               </ul> 
            </div>
            <div class="extend">
                <ul>
                    <li><label>商&nbsp;&nbsp;&nbsp;&nbsp;家：</label>${(shopconfig.shop_name)?if_exists}</li>
                   <div class="clear"></div>
                   <li><label>所 在 地：</label>${member.area_attr?if_exists}</li>
                   <div class="clear"></div>
                   <li><label>MSN&nbsp;客服：</label>
                   <a href="msnim:chat?contact=${(shopconfig.msn)?if_exists}" target=blank><img src="/templets/default/images/sm_msn.gif"/>MSN联系我</a>
                   </li>
                   <div class="clear"></div>
                   <li><label>旺旺客服：</label>
                   <a target="_blank" href="http://amos.im.alisoft.com/msg.aw?v=2&uid=${(shopconfig.alliwang)?if_exists}&site=cntaobao&s=1&charset=utf-8" >
                   <img border="0" src="http://amos.im.alisoft.com/online.aw?v=2&uid=${(shopconfig.alliwang)?if_exists}&site=cntaobao&s=1&charset=utf-8" alt="有问题随时都可以M我哦"  /></a>
                   </li>
                </ul> 
            </div>  
             <div class="other" style="text-align:center">
                 <a class="enter"  title="收藏本店铺" href="###;" onclick="insertColl('1');"></a>
                    <input type="hidden" id="title"  value="${(shopconfig.shop_name)?if_exists}" />	
			    	<input type="hidden" id="link_url"  />
                 <a class="enterl" target="_blank" title="联系我们" href="/shop/${user_name?if_exists}/shopcomm.html"></a>
             </div>  
             <@s.form id="fgoodslist" action="/shop/${memberuser.user_name?if_exists}/goodslist.html" method="post">
             <@s.hidden id="cart_goods_cust_name" value="${member.cust_name?if_exists}"/>
             <@s.hidden id="selName" name="selName"/>
             <@s.hidden id="uppri" name="uppri"/>
             <@s.hidden id="downpri" name="downpri"/>
             <div class="search_cont">
			    <p class="search_cont_P">关键字：<input type="text" style="width:118px" id="bselValue" /></p>
				<p class="search_cont_p1" style="padding:10px 0 0 25px;*padding:10px 0 0 20px">价格：<input type="text" id="downprice" value="${downpri?if_exists}" name="" style="width:47px;*width:43px"/> 到 <input type="text" id="upprice" value="${uppri?if_exists}" name="" style="width:47px;*width:43px"/></p>
			    <input type="button"  onclick="return priceselect();" class="search_b"/ > 
			 </div> 
			 </@s.form>
        </div>
     </div>
       
  <div class="Sidebar">
        <h2><span>商品分类</span></h2>
        <div class="sideFl">
           <ul class="all">
               <li class="allP"><a href="/shop/${user_name?if_exists}/goodslist.html" target="_blank">查看所有商品>></a></li> 
               <li class="ph">
                   <a href="###;" onclick="goodsaction('mr','','${user_name?if_exists}')" >默认</a>
	               <a href="###;" onclick="goodsaction('sales','','${user_name?if_exists}')"  >按销量</a>
	               <a href="###;" onclick="goodsaction('time','','${user_name?if_exists}')" >按时间</a>
	               <a href="###;" onclick="goodsaction('salesup','','${user_name?if_exists}')" >按价格</a>
               </li>
           </ul>
           
           
          <#list membercatList   as mclist>
           <#if mclist.cat_level==1>
           <ul class="zk">
            
   		    <#if mclist.img_path!=null&&mclist.img_path!="">
   		    <span >
   		     <a href="/shop/${user_name?if_exists}/goodslist/cat_${(mclist.cat_id)?if_exists}.html"  target="_blank" ><img  src="${(mclist.img_path)?if_exists}"  width="187px" height="33px"   /></a>
   		     </span>
   		    <#else>
   		       <li class="zkT">
   		    <span class="zkTs">
   				<a href="/shop/${user_name?if_exists}/goodslist/cat_${(mclist.cat_id)?if_exists}.html"  target="_blank" >${(mclist.cat_name)?if_exists}</a>
   				</span>
   				</li>
   			</#if>
               <#list membercatList   as mclisttwo>
	                <#if mclist.cat_id==mclisttwo.up_cat_id>
	               			<li><a href="/shop/${user_name?if_exists}/goodslist/cat_${(mclisttwo.cat_id)?if_exists}.html"  target="_blank">${(mclisttwo.cat_name)?if_exists}</a></li>
	               </#if>
               </#list>
           </ul>
            </#if>
		</#list>

         
        </div>
      </div>
      
       <div class="Sidebar">
         <h2><span>友情链接</span></h2>
        <ul class="links">
            <#list memberlinkList as linklist>
            <li><a href="${(linklist.link_url)?if_exists}" target="_blank">${(linklist.title)?if_exists}</a></li>
            </#list>
           
         </ul>
       </div> 
       
  </div>
<script type="text/javascript" src="/templets/shop/js/change.js"></script>