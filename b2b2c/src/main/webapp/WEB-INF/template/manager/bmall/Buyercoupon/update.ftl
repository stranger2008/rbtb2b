<html>
<head>
<title>查看我的优惠劵</title>
<#include "/include/uploadInc.html">
</head>
<body>
	<div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的优惠券</a><span>></span><a href="#">优惠券信息</a>
	</div>
<div class="rightside f_right">
     <div class="rpostion"><h2>优惠劵信息</h2></div>

     <div class="clear"></div>
     
     <div class="base_infor">
       <div class="table_infor">
          <table  style="width:750px;">
             
            <tr><td  class="firsttd">优惠劵赠送商：</td><td>
            <@s.label name="buyercoupon.cust_name" />
             </td></tr> 
            <tr><td  class="firsttd">优惠劵名称：</td><td>
            <@s.label name="buyercoupon.c_name" />
            </td></tr> 
            <tr><td  class="firsttd">优惠卷图片：</td><td>
              <img src="${(buyercoupon.c_img)?if_exists}" width="100px" height="100px" alt="优惠劵图片"/>
            </td></tr> 
            <tr><td  class="firsttd">优惠类型：</td><td>
              <#if (buyercoupon.c_type)?if_exists=='0'>
                 优惠金额(${(buyercoupon.c_money)?if_exists})
              <#elseif buyercoupon.c_type=='1'>
                 优惠积分(${(buyercoupon.c_inter)?if_exists})
              <#elseif buyercoupon.c_type=='2'>
                 优惠折扣(${(buyercoupon.c_discount)?if_exists}%)
              </#if>
            </td></tr> 
            <tr><td  class="firsttd">有效期：</td><td>
           <@s.label name="buyercoupon.start_time" /> 至 <@s.label name="buyercoupon.end_time" />
            </td></tr> 
            <tr><td  class="firsttd">优惠卷使用次数：</td><td>
           <@s.label name="buyercoupon.coupon_times" />
            </td></tr> 
             <tr><td  class="firsttd">赠送总数量：</td><td>
           	<@s.label name="buyercoupon.coupon_num" />
            </td></tr>
            
            <tr>
             <td colspan="2" align="center">
             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Buyercoupon_list.action','');"/>
             	<@s.hidden name="coupon.c_id"/>
             </td>
            </tr> 
            
          </table>
       </div>
      
     </div>

   </div>
   
</div>
</body>
</html>