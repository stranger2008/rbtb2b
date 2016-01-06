<div class="w980">

  <div class="contL">
  
     <!--卖家信息-->
    <div class="ltitle">
      <h2 class="lth2">卖家信息</h2>
       <ul class="sellul">
         <li><a href="###;">${(shopconfig.shop_name)?if_exists}</a>
        <a href="#" target="_blank"><img border="0" src="http://wpa.qq.com/pa?p=1:2585108896:16" alt=""></a>
         </li>
         <li>当前主营：<a href="###;">${(shopconfig.busi_range)?if_exists}</a></li>
         <li>所在地区：${member.area_attr?if_exists} </li>
         <li class="liline">创店时间：${(shopconfig.in_date)?if_exists[0..10]}</li>
        
       </ul>
     </div>
     
     
     
     <!--店铺经营资质-->
     <div class="ltitle">
      <h2 class="loth2">店铺经营资质</h2>
      <p class="rzp">认证信息：<a href="#"><img src="/templets/shop/images/xzapprove_23.gif"></a></p>
     </div>
     
  </div>

  <div class="contR">
    <!--买家评论展示-->  
     <div class="rback">
        <div class="showback">
        
             <div class="lshow">
                <h2 class="lshowh2"><span class="sspan">卖家信用评价展示</span><span class="hpd">好评率：${goodComment?if_exists}%</span></h2>
                <div class="lshowcont">
                 
                  <div class="lshowtitle">  
                   <ul>
                     <li id="sbut1" class="showbut1" onmousedown="re_show(1,4,'sbut','show_mains','showbut1','showbut2')">最近一周</li>
                      <li id="sbut2" class="showbut2" onmousedown="re_show(2,4,'sbut','show_mains','showbut1','showbut2')">最近一月</li>
                     <li id="sbut3" class="showbut2" onmousedown="re_show(3,4,'sbut','show_mains','showbut1','showbut2')">最近半年</li>
                     <li id="sbut4" class="showbut2" onmousedown="re_show(4,4,'sbut','show_mains','showbut1','showbut2')">半年以前</li>
                   </ul>
                   <div class="clear"></div>
                  </div>
                    
                  <div class="showcont" id="show_mains1">
                     
                     <table width="100%" cellpadding="0" cellspacing="0" class="showtab">
                        <tr><th width="25%" class="hp">好评</th><th width="25%" class="zp">中评</th><th width="25%" class="cp">差评</th></tr>
                       <tr>
                       
                       <#list weekNum?split(",") as s>
                       <td width="25%">${s}</td>
                       </#list>

                       
                       </tr>
                      
                     </table>
                     
                  </div>  
                  <div class="showcont" id="show_mains2" style="display:none">
                  
                     <table width="100%" cellpadding="0" cellspacing="0" class="showtab">
                        <tr><th width="25%" class="hp">好评</th><th width="25%" class="zp">中评</th><th width="25%" class="cp">差评</th></tr>
                       <tr>
                       <#list monthNum?split(",") as s>
                       <td width="25%">${s}</td>
                       </#list>
                       </tr>
                       
                     </table>
                     
                  </div>
                  <div class="showcont" id="show_mains3" style="display:none">
                  
                     <table width="100%" cellpadding="0" cellspacing="0" class="showtab">
                        <tr><th width="25%" class="hp">好评</th><th width="25%" class="zp">中评</th><th width="25%" class="cp">差评</th></tr>
                       <tr>
                       <#list halfYearNum?split(",") as s>
                       <td width="25%">${s}</td>
                       </#list>
                       </tr>
                       
                     </table>
                     
                  </div>  
                  <div class="showcont" id="show_mains4" style="display:none">
                  
                    <table width="100%" cellpadding="0" cellspacing="0" class="showtab">
                        <tr><th width="25%" class="hp">好评</th><th width="25%" class="zp">中评</th><th width="25%" class="cp">差评</th></tr>
                       <tr>
                      <#list halfYearBeforNum?split(",") as s>
                       <td width="25%">${s}</td>
                       </#list>
                       </tr>
                      
                     </table>
                     
                  </div>
                                        
                </div>
             </div>
             
             <div class="rshow">
             
               <h2 class="rshowh2"><span class="sspan">店铺半年内动态评分</span></h2>
               
               <div class="rshowcont">
                   <ul class="scoreul">
         
            <li >
            <span class="gray">宝贝与描述相符：</span>
           <#if scoreList?if_exists && (scoreList?size > 0)>
	           <#list scoreList?first as score>
	            <span class="orange">
	            <#if score.desc_score?length lt 4>
	             	${(score.desc_score)?if_exists?string.number}
	             <#else>
	             	${score.desc_score[0..3]}
	             </#if>
			    </span>分<span class="level"><b>高</b><em>55.80%</em></span>
			    </#list>
			<#else>
			   <span class="orange">
			  暂无评分
			 </span>
			</#if>
		    </li>
            <li >
            <span class="gray">卖家的服务态度：</span>
            <#if scoreList?if_exists && (scoreList?size > 0)>
	            <#list scoreList?first as score>
	            <span class="orange">
	             <#if score.service_score?length lt 4>
	             	${score.service_score?if_exists?string.number}
	             <#else>
	             	${score.service_score[0..3]}
	             </#if>	 
	            </span>分<span class="level"><b>低</b><em>21.10%</em></span>
	            </#list>
	        <#else>
			   <span class="orange">
			  暂无评分
			 </span>
			</#if>
            </li>
            <li >
            <span class="gray">卖家发货的速度：</span>
            <#if scoreList?if_exists && (scoreList?size > 0)>
	            <#list scoreList?first as score>
	            <span class="orange">
	            <#if score.delivery_score?length lt 4>
	             	${score.delivery_score?if_exists?string.number}
	             <#else>
	             	${score.delivery_score[0..3]}
	             </#if>
	            </span>分<span class="level"><b>持平--</b></span>
	             </#list>
             <#else>
             <span class="orange">
			  暂无评分
			 </span>
			</#if>
		   </li>
         </ul>
               </div>
          
             </div>
            
             <div class="clear"></div>
             
        </div>
     </div>
    
      
  </div>
  
<div class="clear"></div>
</div>