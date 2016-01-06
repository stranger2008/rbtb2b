 <html>
<head>
<title>优惠劵列表</title>
<script type="text/javascript">
   $(document).ready(function(){
	//select改变表单提交事件
	$("select").change(function(){//事件触发 
		document.searchForm.submit();//提交表单
    });   	
});
</script>
</head>
<body>
	<div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的优惠券</a>
	</div>
   <@s.form action="/bmall_Buyercoupon_list.action" method="post" id="searchForm">
   <div class="rightside f_right">
     <div class="rpostion"><h2>我的交易</h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>我的优惠劵</span></h2>
       <div style="padding-left:45px;padding-top:10px;padding-bottom:10px;">
        优惠劵状态：<@s.select id="is_used" name="is_used_s"  list=r"#{'0':'请选择','1':'未使用','2':'已使用','3':'已过期'}"/>
        
       </div>  
       <table cellspacing="0" class="bmall_list_table">
         <tr>
             <td class="fthstyle1" width="5%"></td>
	         <td class="fthstyle1" width="20%">赠送商</td>
	         <td class="fthstyle1" width="10%">名称</td>
	         <td class="fthstyle1" width="15%">类型</td>
	         <td class="fthstyle1" width="20%">有效期</td>
	         <td class="fthstyle1" width="10%">使用次数</td>
	         <td class="fthstyle1" width="10%">总数量</td>
	         <td class="fthstyle1" width="10%">操作</td>
         <tr>
         <#list buyercouponList as buyercoupon>
         <tr>
             <td></td>
		     <td>${(buyercoupon.cust_name)?if_exists}</td>
		     <td>${(buyercoupon.c_name)?if_exists}</td>		 
		     <td>
		    	<#if buyercoupon.c_type?if_exists=="0">优惠金额(￥<strong>${buyercoupon.c_money?if_exists}</strong>)</#if>
		    	<#if buyercoupon.c_type?if_exists=="1">优惠积分(<strong>${buyercoupon.c_inter?if_exists}</strong>分)</#if>
		    	<#if buyercoupon.c_type?if_exists=="2">优惠折扣(<strong>${buyercoupon.c_discount?if_exists}</strong>折)</#if>
	    	</td>		    		     
		     <td>${(buyercoupon.start_time)?if_exists}至${(buyercoupon.end_time)?if_exists}</td>
		     <td>${buyercoupon.coupon_times?if_exists}</td>
		     <td>${buyercoupon.coupon_num?if_exists}</td>
		     <td>
		        <a  href="###"  onclick="linkToInfo('/bmall_Buyercoupon_view.action','buyercoupon.id=${(buyercoupon.id)?if_exists}');" class="xg">查看</a>
		     </td>
         </tr>
         </#list>
       </table>
       
        <div class="listbottom">
         ${pageBar?if_exists}
        </div>
      </div>     
   </div>
      
</div>
</@s.form>
</body>
</html>

