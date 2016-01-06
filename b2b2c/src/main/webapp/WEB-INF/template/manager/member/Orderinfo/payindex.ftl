<html>
  <head>
    <title>付款</title>
<script type="text/javascript" src="/include/js/keyboard.js"></script>

    <#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
  </head>
  <body>
  <div class="cont_main">
   <@s.form  id="form" name="form" action="/member_Memberfund_updatepasswd.action" method="post" validate="true">
   <@s.hidden name="message" />
   <@s.hidden name="orderinfo.order_id" />
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的订单>付款</td>
 	</tr>
	</table>
     <table width="100%" border="0" bgcolor="#FFF7EB"  cellpadding="1" style="border:1px solid #F58B0F;margin-top:10px;">
      <tr>
       <td style="padding:20px;font-size:14px;font-weight:bold;color:#404040;">
       可用资金：${(memberfund.use_num)?if_exists}元<a href="/member_Fundrecharge_add.action">[充值]</a>
       <@s.fielderror><@s.param>litlemomey</@s.param></@s.fielderror>
       </td>
      </tr>
     </table>
     
     <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont"> 
           <tr>
	           <td style="padding:10px;">
	           		
	           		<table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1">
	           		
	           			<tr>
	           				<td style="background:#E8F2FF;">
	           					商品名称
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					单价
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					数量
	           				</td>
	           				<td style="background:#E8F2FF;">
	           					总价
	           				</td>
	           			</tr>
	           			
	           			<#list orderinfoList as orderinfo>
	           			<tr>
	           				<td>
	           					 <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.in_date)?if_exists}')")}" target="_blank">${(orderinfo.title)?if_exists}</a>
	           				</td>
	           				<td>
	           					${(orderinfo.price)?if_exists}
	           				</td>
	           				<td>
	           					${(orderinfo.goods_num)?if_exists}
	           				</td>
	           				<td>
	           					${(orderinfo.goods_fee)?if_exists}&nbsp;元
	           				</td>
	           			</tr>
	           		    </#list>
	           		</table>
	           		
	           		
	           </td>
           </tr>
           
           
           <tr>
	           <td align="right">
	           		运费：${allcarr_fee?if_exists}&nbsp;元&nbsp;&nbsp;总支付：<font size="4" color="#FF5500"><b>${total_price?if_exists}</b></font>&nbsp;元
	           </td>
           </tr>
           
            <tr>
	           <td>
	           		<b>支付密码:</b>
	           		 <@s.password id="num" name="passwd" type="password" cssClass="txtinput" maxLength="32"/>
	                 <@s.fielderror><@s.param>passwd</@s.param></@s.fielderror>
	                 <span id="span0" onClick="showkeyboard()" style={cursor:hand;}>
					<img border="0" width="56" height="18" alt="" src="/include/images/softkeyboard.gif">
					</span>
	           </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
                <input type="button"  value="确认支付" class="sub" onclick="document.forms['form'].action='/member_Orderinfo_payindex.action?ordertype=<#if order_type?if_exists='buy'>buy<#else>sell</#if>';document.forms['form'].submit();"/>
	       		<input type="button" name="returnList" value="返回列表" class="sub" onclick="location.href='/member_Orderinfo_list.action?ordertype=<#if order_type?if_exists='buy'>buy<#else>sell</#if>';"/>
           </td>
           </tr>
         </table>
 </@s.form>
</div>
  </body>
</html>