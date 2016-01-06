 <html>
<head>
<title>我是买家</title>
<link href="/include/css/bmall/backstage.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />
</head>
<body>
<div class="rightside f_right">
	<div class="ropot">
       	<h2 class="rotitle"><span>我是买家</span></h2>    
	       <div class="rightside f_right">
		     <div class="buycont">
		       <div class="buyhead f_left">

		       <#if member.logo_path?if_exists>
		       	 	<a target="_self" href="/bmall_Member_updateMemberPhoto.action"><img src="${member.logo_path?if_exists}"  width="110" height="110" alt="修改头像"></a>
		       <#else>
		       		<a target="_self" href="/bmall_Member_updateMemberPhoto.action"><img src="/include/images/nopic.jpg"  width="110" height="110"  alt="修改头像"></a>
		       </#if>
		       </div>
		       		<div class="buycright f_right">
			         <p class="buyp"><span class="buyername">${member.cust_name?if_exists}</span><span class="cbule">欢迎你!</span>上次登陆时间：<span>${login_time?if_exists.substring(0,19)}</span></p>
			         <p class="medal"><span class="med f_left">铜牌用户</span>今年已消费￥0.0，在消费￥0.0可升级到<span
			         class="cbule">银牌用户</span></p>
			         <p>账户安全：<span class="mailbox">邮箱已验证</span></p>
				         <div class="buyc">
					           <div class="buycl f_left">
					             <p><span class="spans">订单提醒：</span><a target="_self" href="/bmall_Goodsorder_list.action?&daiId=0">待处理订单(<span>${buyerOrder?if_exists}</span>)</a><a href="#"> 待评价商品(<span>${sevelCount?if_exists}</span>)</a><a href="#">待晒单商品(<span>0</span>)</a></p>
					             <p><span class="spans">我的关注：</span><a href="#">降价商品(<span>0</span>)</a><a href="#">促销商品(<span>0</span>)</a><a href="#">新到货商品(<span>0</span>)</a></p>
					             <p class="nullp"><span class="spans">消息精灵：</span><a href="#">提醒/通知：(<span>0</span>)</a><a target="_self" href="/bmall_Goodsask_auditList.action?&yiId=0">已回复的咨询(<span>${skCount?if_exists}</span>)</a></p>
					           </div>
					           <div class="buyr f_right">
					             <p><span class="spans">账户余额：</span><span>￥${memberfund.use_num?if_exists}</span></p>
					             <p><span class="spans">我的积分：</span><span class="cred">${memberinter.fund_num?if_exists}分</span></p>
					             <p class="nullp"><span class="spans">优惠劵：</span><span class="cred">${couponCount?if_exists}张</span></p>
					           </div>
				           	<div class="clear"></div>
				    	 </div>
		       </div>
	       <div class="clear"></div>
	     </div>
        <div class="buyorder">
		  <table width="100%" cellpadding="0" cellspacing="0"  class="ordertable">
			<tr><th width="15%">订单编号</th><th width="10%">收货人</th><th width="10%">订单金额</th><th  width="15%">下单时间</th><th width="10%">订单状态</th><th width="15%">操作</th></tr>   		 		  
		 <#if goodsorderList.size()?if_exists==0>
		 	<tr ><td></td><td></td><td>暂无订单信息</td><td></td><td></td><td></td></tr>
		 <#else> 
			   <#assign nncount=0>  
			    <#list goodsorderList as goodsorder > 
			     <#if nncount lt 10>
			    	<tr><td width="15%">${goodsorder.order_id?if_exists} </td><td width="10%">${goodsorder.consignee?if_exists}</td>
			    		<td width="10%"><p class="cred">￥${goodsorder.tatal_amount?if_exists}</p></td>
			    		<td  width="15%"><p>${goodsorder.order_time?if_exists}</p></td><td width="10%">${goodsorder.order_para?if_exists}</td>
			    		<td width="15%"><p><a target="_self" href="/bmall_Goodsorder_orderdetail.action?&oid=${goodsorder.order_id?if_exists}">查看订单详情</a></p></td>
			    	</tr>			    
			    <#assign nncount=nncount+1>			    
				</#if>
			    </#list>				   
		</#if>
		   </table>
     </div> 
	  
						
	</div>
    
	     </div>        
   </div>     
</div>
       
       <div class="clear"></div>

</body>
</html>

