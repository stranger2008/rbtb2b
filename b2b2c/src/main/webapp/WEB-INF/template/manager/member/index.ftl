<html>
<head>
<title>首页</title>
<script type="text/javascript">   
function copyToClipboard(txt) {
	if(window.clipboardData) {
	window.clipboardData.clearData();
	window.clipboardData.setData("Text", txt);
	alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友！\r\n\r\n内容如下：\r\n" + txt); 
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
	window.location = txt;
	} else if (window.netscape) {
	try {
	netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	} catch (e) {
	alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
	}
	var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
	if (!clip)
	return;
	var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
	if (!trans)
	return;
	trans.addDataFlavor('text/unicode');
	var str = new Object();
	var len = new Object();
	var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	var copytext = txt;
	str.data = copytext;
	trans.setTransferData("text/unicode",str,copytext.length*2);
	var clipid = Components.interfaces.nsIClipboard;
	if (!clip)
	return false;
	clip.setData(trans,null,clipid.kGlobalClipboard);
	alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友！\r\n\r\n内容如下：\r\n" + txt); 
	}
}
</script> 

</head>
<body>
  <div class="cont_main">
     <div class="kh">
      <div class="kh_l"></div>
      <div class="kh_c">
         <h3 class="kh_t">尊敬的客户：<script src="/include/discuzlink/loginDiscuz.jsp"></script></h3>
             <P class="kh_add">欢迎&nbsp;${user_name?if_exists} 登陆 ${web_name?if_exists}!<br />
            <#if levelinfo.level_code!='1'>
             贵公司地址是： <a id="QQ" href="http://${web_host?if_exists}/showroom/${user_name?if_exists}" target="_blank">http://${web_host?if_exists}/showroom/${user_name?if_exists}</a>
             <a href="#" onClick='copyToClipboard("http://${web_host?if_exists}/showroom/${user_name?if_exists}")'>一键复制</a> &nbsp;
              当前信用指数&nbsp;<font style="color:#CC0000;font-size:15px;font-weight:600;">${cust_name_credit?if_exists} </font>
              <a href="/member_Creditdetail_view.action">[查看明细]</a> 
             <br />
             </#if>
             您目前是             
             ${now_levelName?if_exists}
          ,有效期 ${(levelinfo.start_date)?if_exists.substring(0,19)} 至 ${(levelinfo.end_date)?if_exists.substring(0,19)} ,VIP会员 享有更多的特权, <a href="/member_Memberupgrade_add.action">立即升级会员等级</a>,享受更多贴心服务! </p>
         <#if  levelinfo.level_code=='3'||levelinfo.level_code=='4'>
         <p class="kh_P">你的公司站点推广链接:<a href="http://${web_host?if_exists}/showroom/${user_name?if_exists}" target="_blank">http://${web_host?if_exists}/showroom/${user_name?if_exists}</a>
          <a href="#" onClick='copyToClipboard("http://${web_host?if_exists}/showroom/${user_name?if_exists}")'>一键复制</a> &nbsp;
          推广你的站点,你可以获得更多的商机,同时可以添加你的积分! </P>
         </#if>
      </div>
      <div class="kh_r"></div>
    </div>
     <!--尊敬的客户结束-->
      <div class="main_left">
       <div class="main_cont">
        
       </div>
       <div class="main_cont">
          <h2 class="main_cont_t">我的信息统计</h2>
          <ul class="main_d">
          <#list countList as count>
               <li style="width:260px;float:left;">
                   ${count.module_name?if_exists}：总数<a href="/member_${count.table_name?if_exists}_list.action">${count.count?if_exists}</a>条, 
	               未审核：<a href="/member_${count.table_name?if_exists}_list.action?info_state_s=0">${count.countaudit?if_exists}</a>条,
	               		<a href="/member_${count.table_name?if_exists}_cate.action">立即发布</a>                             
               </li>    
                        
          </#list>
          <div class="clear"></div>  
          </ul>          
       </div>
       
       <div class="main_cont">
          <h2 class="main_cont_t">您当前的积分为: <span class="red">${web_integralCount?if_exists}</span>分,你目前的积分排名第<span class="red">${web_orderRank?if_exists}</span>名         
             <a href="/member_Interrule_list.action" style="font-size:12px;color:#CC0000;font-weight:normal;">[积分规则]</a>
             <a href="/member_Interhistory_list.action"  style="font-size:12px;color:#CC0000;font-weight:normal;">[积分明细]</a>
         </h2>
          <ul class="main_d">
               <li>1.积分越高，所发布的产品，商情，招商等信息在被搜索时排序越靠前! </li>
               <li>2.积分越高,被检索的机会就越大</li>
               <li>3.积分越高,更容易让你的潜在客户信任</li>
               <li>4.积分可以换取升级会员级别的折扣</li>
               <li>5.用于查看会员信息</li>
               <li>6.在活动期间可以用积分兑换奖品 </li>
               <li>7.每天获得积分的上限是${int_scoreToday?if_exists}分 </li>
          </ul>
       </div>
     </div>
      <div class="main_right">
       <div class="main_side_cont">
          <h2 class="main_side_t"><a href="/member_Memberinbox_list.action">消息中心</a></h2>
          <ul class="main_sede_d">        
            <li>未读消息（<a target="_self" href="member_Memberinbox_list.action?isread_s=0">${unReadC?if_exists}</a>）条</li>
            <li>已读消息（<a target="_self" href="member_Memberinbox_list.action?isread_s=1">${markReadC?if_exists}</a>）条</li>
          </ul>
       </div>
       <div class="main_side_cont">
          <h2 class="main_side_t"><a href="/member_Logs_list.action">操作日志</a></h2>
          <ul class="main_sede_d">
           <#list logsList as membreinboxl>
            <li>
            <#if membreinboxl.content?if_exists!=''>
            <#if membreinboxl.content?length lt 10>
     		 ${membreinboxl.content?if_exists}
    		<#else>
      		${membreinboxl.content[0..9]}
   			</#if>
   			</#if>
            (${membreinboxl.in_date[0..9]?if_exists})</li>
            </#list> 
          </ul>
       </div>
       <#if (supplyList?if_exists?size > 0)>
	       <div class="main_side_cont">
	          <h2 class="main_side_t">供应推荐</h2>
	          <ul class="main_sede_d">    
	          <#list supplyList as supply> 
	            <#assign rbttime='${(supply.in_date)?string("yyyy-MM-dd")}'/>           
		            <li>
		            <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${supply.supply_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
		            <#if supply.title?length lt 15>
				      ${supply.title?if_exists}
				    <#else>
				      ${supply.title[0..14]}
				    </#if></a>
		           </li>
	          </#list>
	          </ul>
	       </div>
	   </#if>	   
       <#if (buyList?if_exists?size > 0)>
	       <div class="main_side_cont">
	          <h2 class="main_side_t">求购推荐</h2>
	          <ul class="main_sede_d">
	          <#list buyList as buy> 
	            <#assign rbttime='${(buy.in_date)?string("yyyy-MM-dd")}'/>           
		            <li>
		            <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('buy','${buy.buy_id?if_exists}','${rbttime?if_exists}')")}" target="_blank">
		            <#if buy.title?length lt 15>
				      ${buy.title?if_exists}
				    <#else>
				      ${buy.title[0..14]}
				    </#if></a>
		           </li>
	          </#list>
	          </ul>
	       </div>
       </#if>
         <div class="main_side_cont">
          <h2 class="main_side_t">买家提醒</h2>
           <ul class="main_sede_d">
           	<li><a target="_self" href="member_Orderinfo_list.action?ordertype=buy">待付款订单(<span>${buyerOrder?if_exists}</span>)</a></li>
            <li><a target="_self" href="member_Orderinfo_list.action?ordertype=buy">待确认订单(<span>${buyerCofirm?if_exists}</span>)</a></li>
            <li><a target="_self" href="member_Orderinfo_list.action?ordertype=buy">待评价订单(<span>${buyercomment?if_exists}</span>)</a></li>
           </ul>    
       </div>
       
         <div class="main_side_cont">
          <h2 class="main_side_t">卖家提醒</h2>
     		<ul class="main_sede_d">
           	<li><a target="_self" href="member_Orderinfo_list.action?ordertype=sell">待发货订单(<span>${daiorderCount?if_exists}</span>)</a></li>
           	<li><a target="_self" href="member_Orderinfo_list.action?ordertype=sell">待评价订单(<span>${sellercomment?if_exists}</span>)</a></li>
           </ul>    
       </div>
      </div>
  </div>
</div>
<div class="clear"></div>
</body>
</html>
