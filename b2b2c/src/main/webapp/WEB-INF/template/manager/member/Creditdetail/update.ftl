<html>
  <head>
    <title>会员信用指数明细表</title> 
  </head>
  <body>
<div class="cont_main">
 <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>会员资料>信用指数明细</td>
 	</tr>
	</table>
   	<div style="border:1px solid #e1e2e3;border-bottom:none;margin-top:10px;">
   	<div style="background-color:#EFF2F9;font-size:14px;padding:6px;height:18px;">
   	    <span style="float:left;">信用指数明细表</span>
   	    <span style="float:right;margin-right:20px;">
   	       信用指数:<font style="color:#990000;font-size:14px;font-weight:600;">${total_num?if_exists}</font>
   	       [<a href="/member_Credithistory_list.action?cre_id=${cre_id?if_exists}">查看详细</a>]
   	    </span>
   	</div>
   	<div>
     <table width="100%">
         <tr><td width="200px;" align="center">信用指数项目</td><td align="center">指数描述</td><td align="center">指数值</td></tr>
         <tr>
         <td align="center">企业实名认证</td>
         <td>
         <#if name_num?if_exists!='0'>
           	 企业通过实名认证
         <#else>
           	 未实名认证
         </#if>
         </td>
         <td align="center">
           ${name_num?if_exists} 
         </td>
         </tr>
	     <tr><td align="center">荣誉证书</td>
	     <td>     
	     <#if cert_num?if_exists=='0'>
           	 未上传证书
         <#else>
          <table width="100%">
             <#list certList as cert>
               <tr>
                 <td width="90%">
                   ${cert.r_name?if_exists}
                 </td>
               </tr>   
             </#list>
          </table>  
         </#if>
         </td>
	     <td align="center">${cert_num?if_exists}</td></tr>
	     <tr><td align="center"> 交易评价</td>
	     <td>
	      好评：<font color="red">${eva_good_num?if_exists}个</font>&nbsp;(加<font color="red">${eva_good_val?if_exists}</font>分)
	     &nbsp;&nbsp;&nbsp;中评：<font color="red">${eva_mid_num?if_exists}个</font>&nbsp;(不加分)
	     &nbsp;&nbsp;&nbsp;差评:<font color="red">${eva_bad_num?if_exists}个</font>&nbsp;(减<font color="red">${eva_bad_val?if_exists}</font>分)
	     </td>
	     <td align="center">${eva_num?if_exists}
	     </td></tr>
	     <tr><td align="center">VIP年限</td>
	     <td>
		 <table width="100%">
		 <#if vip_num?if_exists=='0'>
           	 未达到VIP年限时长
         <#else>
             <#list vipList as cert>
               <tr>
                 <td width="90%">
                   ${cert.r_name?if_exists}
                 </td>
               </tr>   
             </#list>
          </#if>
          </table> 
	     </td>
	     <td align="center">${vip_num?if_exists}</td></tr>
     </table>
   </div>
</div>
</div>
<div class="clear"></div>
</body>
</html>