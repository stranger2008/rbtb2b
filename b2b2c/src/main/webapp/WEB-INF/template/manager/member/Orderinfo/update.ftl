<html>
  <head>
    <title>修改订单</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <link href="/include/components/upload/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/include/components/upload/swfobject.js"></script>
    <script type="text/javascript" src="/include/components/upload/jquery.uploadify.v2.1.4.js"></script>
    <script type="text/javascript" src="/include/components/upload/upload.js"></script>
    <link href="/include/components/upload/showpic.css" rel="stylesheet" type="text/css" />
	<script src="/include/components/upload/showpic.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript" src="/include/js/member/orderinfo.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){      
        //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	  function calculate(e){
	  var num;
	  if(e.value=="")
       {
       e.value="1";
        num=1;
       }else{
       num=$("#num").val();
       }
	   var total=$("#price").html()*num;
	   $("#goodstotal").html(total);
	   var all=0;
	   var car=$("#carriage").html();
	   all=parseInt(total)+parseInt(car);
	   $("#all").html(all);
	   
	   $("#carriage_fee").val(car);
	   $("#goods_fee").val(total);
	   $("#total_fee").val(all);
	  }
	</script> 
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Orderinfo_update.action" method="post" validate="true">
        <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:交易管理>我的订单>修改订单</td>
 	</tr>
	</table>
     </table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">订单号:</td>
             <td width="83%">
             	 <@s.label name="orderinfo.order_id" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">下单时间:</td>
             <td>
             	<input  id="order_in_date" style="border:none; background-color: #FFFFFF; color:#000000;font-size:12px;" disabled="true" />
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">商品名称:</td>
             <td>
             	<@s.label name="supply.title" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">单价:</td>
             <td>
             	<@s.label id="price" name="orderinfo.price" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">商品数量<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield id="num" name="orderinfo.goods_num" cssClass="txtinput" maxLength="8" onblur="calculate(this);" onkeyup="checkNum(this)" />
             		<@s.fielderror><@s.param>orderinfo.goods_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">商品总价:</td>
             <td>
             	<@s.label id="goodstotal" name="orderinfo.goods_fee" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">运费:</td>
             <td>
             	<@s.label id="carriage" name="orderinfo.carriage_fee" cssClass="txtinput"/>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">总共支付:</td>
             <td>
             	<@s.label id="all" name="orderinfo.total_fee" cssClass="txtinput"/>
             </td>
           </tr>
           <#if order_type?if_exists='sell'>
           <tr>
             <td valign="middle" class="left_td">买家:</td>
             <td>
                <#if (member.cust_name)?if_exists!=''>
             	<@s.label name="member.cust_name" cssClass="txtinput"/>
             	</#if>
             	 <#if (memberuser.user_name)?if_exists!=''>
             	<@s.label name="memberuser.user_name" cssClass="txtinput"/>
             	</#if>
             </td>
           </tr>
            </#if>
            <#if order_type?if_exists='buy'>
            <tr>
             <td valign="middle" class="left_td">卖家:</td>
             <td>
             	<@s.label name="seller.cust_name" cssClass="txtinput"/>
             </td>
            </tr>
            </#if>
            <tr>
             <td valign="middle" class="left_td">收货人姓名<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="orderinfo.cust_name" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>orderinfo.cust_name</@s.param></@s.fielderror>  
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">所在地区<span class="mustfield">*</span></td>
             <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">送货详细地址<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="orderinfo.address" cssClass="txtinput" cssStyle="width:400px" maxLength="200"/>
             	<@s.fielderror><@s.param>orderinfo.address</@s.param></@s.fielderror>  
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">邮政编码<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="orderinfo.post_code" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>orderinfo.post_code</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">电话号码:</td>
             <td>
             	<@s.textfield name="orderinfo.phone" cssClass="txtinput" maxLength="50"/>
             	<span class="mustfield">*</span>电话或手机最少填写一个
             	<@s.fielderror><@s.param>orderinfo.phone</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">手机号码:</td>
             <td>
             	<@s.textfield name="orderinfo.cell_phone" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>orderinfo.cell_phone</@s.param></@s.fielderror>  
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">买家留言:</td>
             <td>
             	<@s.textarea name="orderinfo.remark" cssClass="txtinput" cssStyle="height:200px;width:500px;" onkeyup="set_textarea_length(this,500);"/>
             	<@s.fielderror><@s.param>orderinfo.remark</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="ordertype"/>
	       <@s.hidden name="orderinfo.order_id"/>
	       <@s.hidden name="orderinfo.order_state"/>
	       <@s.hidden name="orderinfo.in_date" id="in_date"/>
	       <@s.hidden id="carriage_fee" name="orderinfo.carriage_fee"/>
	       <@s.hidden id="goods_fee" name="orderinfo.goods_fee"/>
	       <@s.hidden id="total_fee" name="orderinfo.total_fee"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <input type="button"  value="保存" class="sub" onclick="document.forms[0].action='/member_Orderinfo_update.action?ordertype=<#if order_type?if_exists='buy'>buy<#else>sell</#if>';document.forms[0].submit();"/>
	       <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="document.forms[0].action='/member_Orderinfo_list.action?ordertype=<#if order_type?if_exists='buy'>buy<#else>sell</#if>';document.forms[0].submit();"/>
           </td>
           </tr>
         </table>
	  </@s.form>
</div>

</div>
<div class="clear"></div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
  <script type="text/javascript">
  $(document).ready(function(){ 
          var in_date=$("#in_date").val();  
          in_date=in_date.substring(0,19); 	    	 
          if(in_date!=''){
          	 $("#order_in_date").val(in_date);
          }      		 
  });
  

</script>
  
  
  
  </body>
</html>