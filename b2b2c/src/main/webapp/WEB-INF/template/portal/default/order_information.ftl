<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单确认-${cfg_webname?if_exists}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<#include "/${templatePortalFolder?if_exists}${templateFiles?if_exists}/jsinclude.html">
<link href="/templets/${templateStyle?if_exists}/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/${templateStyle?if_exists}/css/order_information.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<script type="text/javascript" src="/templets/bmall/js/orderinfo.js"></script>
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
</head>
<body>
<#include "/${templateRoute?if_exists}/top.html">
<div class="clear"></div>
<!--导航结束-->
<@s.form action="/portal/order!orderUnder.action?supplyid=${(supply.supply_id)?if_exists}&userid=${(memberuser.user_id)?if_exists}" method="post">
<!--配送地址ID号-->
<@s.hidden name="checked_id" id="checked_id"/>
<!--商品总价-->
<@s.hidden name="all_price" id="all_price"/>
<div class="w960">
   <div><img src="/templets/${templateStyle?if_exists}/images/orderbar.gif"></div>
    <p class="a_title">收货地址确定</p>
    <div class="use_adr">
       <ul>
           <#list buyeraddrList as buyeraddr>
            <li class="li2"><input type="radio" name="radioaddr" value="${(buyeraddr.addr_id)?if_exists}" onclick="closeaddr()">${(buyeraddr.cust_name)?if_exists},${(buyeraddr.area_attr)?if_exists},${(buyeraddr.address)?if_exists}</li>
           </#list>
           <li class="li2"><input id="other" type="radio" name="radioaddr" value="0" onclick="openaddr()">使用其他地址</li>
       </ul> 
     
       <div id="addr" style="display:none;" class="fill_adr">
          <p class="rad_pad f_right">为必填项</p>
          <table  class="s_table">
          
             <tr>
                <td class="td_1">收 货 人：</td>
                <td class="td_w">
                <@s.textfield id="order_cust_name" name="order_cust_name" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>order_cust_name</@s.param></@s.fielderror><span id="cust_nameError" />  
                </td>
            </tr>
             <tr>
                 <td class="td_1">地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>   
                 <td class="td_w">
                    <table><tr><td><div id="arealist"></div></td>
                    <td><@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror><span id="areaError" /></td></tr></table>
                </td>
             </tr>
             
              <tr>
                 <td class="td_1">街道地址：</td>
                 <td class="td_w" colspan="2" style="width:auto;">
                 <table><tr><td><@s.textfield name="order_address" id="order_address" cssClass="txtinput" maxLength="200" cssStyle="width:399px"/></td>
                    <td><@s.fielderror><@s.param>order_address</@s.param></@s.fielderror><span id="addressError" /></td></tr></table> 
                 <td>      
            </tr>
            
            <tr>
                 <td class="td_1">邮政编码：</td>
                 <td class="td_w">
                 <@s.textfield name="order_post_code" id="order_post_code" cssClass="txtinput" maxLength="50"/>
             	 <@s.fielderror><@s.param>order_post_code</@s.param></@s.fielderror><span id="postError" />
                 <td>
            </tr>
            
            <tr>
                 <td class="td_1">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
                 <td class="td_w" colspan="3">
                 <@s.textfield name="order_phone" id="order_phone" cssClass="txtinput" maxLength="50"/>
                 <@s.fielderror><@s.param>order_phone</@s.param></@s.fielderror>
                    或者&nbsp;&nbsp;&nbsp;&nbsp;手&nbsp;&nbsp;&nbsp;&nbsp;机：
                 <@s.textfield name="order_cell_phone" id="order_cell_phone" cssClass="txtinput" maxLength="50"/>
                 <@s.fielderror><@s.param>order_cell_phone</@s.param></@s.fielderror><span id="phoneError" />
                 <td>  
            </tr>  
            
            <tr>
              <td colspan="2"><input type="button" onclick="addsubmit();" value="保存收货地址"/></td>
            </tr>     
     
       </table> 
       </div>
       
    </div>
    
    <p class="a_title">选择交易方式</p>
    <div>
       <@s.radio name="pay_type" list="paymentList" listValue="pay_name" listKey="pay_id" value="3"/>
       <@s.fielderror><@s.param>pay_type</@s.param></@s.fielderror>
    </div>
    
    
    
     <p class="a_title">配送方式确定</p>
     </br>
     <div class="fill_adr">
          <table  class="s_table">
            <tr>
                 <td class="td_1">配送方式：</td>
                 <td class="td_w">
                 <@s.select id="smode" name="smode_id" list="sendmodeList" listValue="smode_name" listKey="smode_id"/>
             	 <@s.fielderror><@s.param>smode_id</@s.param></@s.fielderror><span id="errorsmode"/>
                 <td>
            </tr>
          </table>
    </div>
    <p class="a_title">发票信息确定</p>
     </br>
     <div class="fill_adr" id="fapiaoupdate">
        <b class="wfh2"><span>发票信息</span><a href="###;" class="bule" onclick="updatefapiao()">[修改]</a></b>
        <table  id="yesfapiao" class="s_table">
          <tr><td class="ftbstyle1">发票类型：</td><td id="ftypevalue"></td></tr>
          <tr><td class="ftbstyle1">发票抬头：</td><td id="ftitlevalue" ></td></tr>
          <tr><td class="ftbstyle1">发票内容：</td><td id="fcontentvalue"></td></tr>
          <@s.hidden id="hidden_fp_type" name="hidden_fp_type"  />
          <@s.hidden id="hidden_fp_title" name="hidden_fp_title"  />
          <@s.hidden id="hidden_fp_content" name="hidden_fp_content"  />
        </table>
        <table  id="nofapiao" style="display:none;">
          <tr><td  style="padding-left:50px;">不开发票</td></tr>
        </table>
      </div>
      
      <div class="fill_adr" id="fapiaoclose">
          <b class="wfh2"><span>发票信息</span><a href="###;" class="bule" onclick="closefapiao()">[关闭]</b>
            <span id="allfapiaoTip" style="color:red;font-size:10pt;display:none;"><img src="/templets/bmall/images/ordertip.png"  width="20px" height="20px" />请填发票信息</span>
          </h2>
          <table>
              <tr id="fapiaotype"><td class="ftbstyle1">发票类型：</td>
	              <td>
	                   <input type="radio" name="fap" onclick="changeInvoiceType('3');"  text="2"  checked ><span>不开发票</span>
		              <input type="radio" name="fap" onclick="changeInvoiceType('1');"  text="0" ><span>普通发票</span>
		              <input type="radio" name="fap"  onclick="changeInvoiceType('2');" text="1"  ><span>增值发票</span>
		              <span  id="fapiaotip"  style="color:red;"></span>
		              <span id="tipinvoice"  style="display:none;color:">
		              <font  style="color:#999999;">提示：尊敬的客户您好，如果您需要开具增值税发票，建议您联系企业客户代表，将有专人为您处理！</font></span>
	              </td>
              </tr>
         </table>
       
          <table id="tableOne">
         
              <tr><td class="ftbstyle1">发票抬头：</td>
              		<td id="ftitleid">
		              <input type="radio" name="fapiaotaitou" onclick="changgeTitle('1');" text="个人"  checked /><span>个人</span>
		              <input type="radio" name="fapiaotaitou" onclick="changgeTitle('2');" text="单位"  /><span>单位</span>
		             <span  id="fapiaotaitoutip"  style="color:red;"></span>
		             </td>
              </tr>
               <tr id="dwid1" style="display:none;">
	               <td class="ftbstyle1" ><span class="red">*</span>单位名称：</td>
	               <td><input type="text" class="td2ltext" id="myuniteid" maxlength="50"   name="hidden_mcompany_name" onblur="checktextnull('myuniteid','myuniteTip');">
	                 <span id="myuniteTip" style="display:none;"><font  style="color:red;">*单位名称不能为空！</font></span>
	               </td>
               </tr>
               <tr id="dwid2" style="display:none;">
	               <td class="ftbstyle1"></td>
	               <td style="color:#999999;">温馨提示：您填写的所有内容都将被系统自动打印到发票上，所以请千万别填写和发票抬头无关的信息。</td>
               </tr>
                
              <tr>
              <td class="ftbstyle1">发票内容：</td>
              <td id="fcontentid">
	              <input type="radio" name="fcontet" text="明细" onclick="checktext('fapiaonrtip','','0');"  checked   /><span>明细</span>
	              <input type="radio" name="fcontet" text="办公用品" onclick="checktext('fapiaonrtip','','0');"  /><span>办公用品</span>
	              <input type="radio" name="fcontet" text="电脑配件"  onclick="checktext('fapiaonrtip','','0');"  /> <span>电脑配件</span>
	              <input type="radio" name="fcontet" text="耗材" onclick="checktext('fapiaonrtip','','0');"  /><span>耗材</span>
	              <span  id="fapiaonrtip"  style="color:red;"></span>
	              </td>
	              
              </tr>
         </table>
         
         
            <table id="tableAll" style="display:none;">
         
              <tr><td class="ftbstyle1"><font color="ff6600">增值税发票</font> </td><td><font color="ff6600">专用发票资质填写：</font> </td></tr>
               <tr><td class="ftbstyle1"><span class="red">*</span>单位名称：</td>
               <td><input type="text" name="hidden_zcompany_name" id="zunitnameid"  class="td2ltext" onblur="checktextnull('zunitnameid','zunitnameTip');" maxlength="50" />
                <span id="zunitnameTip" style="display:none;"><font  style="color:red;">*单位名称不能为空！</font></span>
               </td></tr>
               <tr><td class="ftbstyle1"></td><td style="color:#999999;">温馨提示：您填写的所有内容都将被系统自动打印到发票上，所以请千万别填写和发票抬头无关的信息。</td></tr>
               
               <tr><td class="ftbstyle1"><span class="red">*</span>纳税人识别号：</td>
               <td>
	               <input type="text"  onblur="checktextnull('nasuirenhaoid','nasuirenhaoTip');" maxlength="50"  id="nasuirenhaoid"  name="hidden_nasuirenhao" class="td2ltext" />
	               
	                <span id="nasuirenhaoTip" style="display:none;"><font  style="color:red;">*纳税人识别号不能为空</font></span>
               </td>
               </tr>
               
              <tr><td class="ftbstyle1"><span class="red">*</span>注册地址：</td><td>
              <input type="text" id="registeraddrid"  name="hidden_registeraddr" class="td2ltext" maxlength="100"  onblur="checktextnull('registeraddrid','registeraddrTip');" />
              <span id="registeraddrTip" style="display:none;"><font  style="color:red;">*注册地址不能为空！</font></span></td></tr>
               
              <tr><td class="ftbstyle1"><span class="red">*</span>注册电话：</td><td>
              <input type="text"  id="registertelid" name="hidden_registertel"  class="td2ltext" maxlength="14"   onblur="checktextnullAndNumber('registertelid','registertelTip')" />
              <span id="registertelTip" style="display:none;color:red;"><font  style="color:red;">*注册电话不能为空！</font></span></td></tr>
               
              <tr><td class="ftbstyle1"><span class="red">*</span>开户银行：</td><td>
              <input type="text" id="openbankid"  name="hidden_openbank"  class="td2ltext"  maxlength="22"   onblur="checktextnull('openbankid','openbankTip')" /> 
              <span id="openbankTip" style="display:none;"><font  style="color:red;">*开户银行不能为空！</font></span></td></tr>
               
              <tr><td class="ftbstyle1"><span class="red">*</span>银行帐户：</td><td>
              <input type="text" id="bankacountid"  name="hidden_bankacount" class="td2ltext"  maxlength="19"   onblur="checktextnullAndNumber('bankacountid','bankacountTip')" />
              <span id="bankacountTip" style="display:none;color:red;"><font  style="color:red;">*银行帐户不能为空！</font></span></td></tr>
               
             <tr><td class="ftbstyle1"></td>
                    <td style="color:#999999;">
                首次开具增值税专用发票的客户需提供加盖公章的营业执照副本、税务登记证副本、一般纳税人资格证书、银行开户许可证复印件，通过传真，或提供扫描件。
                         <br>我们收到您的开票资料后，将会尽快审核。
                         <br><font color="ff6600">注意：有效增值税发票开票资质仅为一个。</font> 
                    </td>
               </tr>
              <tr><td class="ftbstyle1">发票内容：</td><td id="zcontentid"><input type="radio" text="明细"  checked><span>明细</span></td></tr>
         </table>
         
         
         
        <p class="ppad2"><a href="###;"  onclick="closefapiao();"><img src="/templets/bmall/images/xzfp_06.gif"></a></p>
      </div>
    
    
     <p class="a_title">订购信息确认</p> 
     <p class="busin">商家：<a href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin">${(member.cust_name)?if_exists}</a></p>
     <div class="order">
         <table cellspacing="0">
           <tr>
              <td class="or_pic">&nbsp;</td>
              <td class="or_titel">商品</td>
              <td class="or_tite2">运费（元）</td>
              <td class="or_tite2">单价（元）</td>
              <td class="or_tite2">数量（元）</td>
              <td class="or_tite3">金额（元）</td>
           </tr>
           <tr>
              <td class="or_manipic"><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">
              <img src="${(supply.img_path)?if_exists}" class="select_pic" width="75" height="50"/>
              </a></td>
              <td class="or_mani"><a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">
              ${(supply.title)?if_exists}
              </a>
              </td>
              <td class="or_mani2">${(supply.shipfee)?if_exists}</td>
              <td class="or_mani2">${(supply.price)?if_exists}</td>
              <td class="or_mani2"><img src="/templets/${templateStyle?if_exists}/images/but_jian.gif" onclick="jian()"><input id="num" type="text" name="ord_num" value="1" onblur="allmore();" class="num_text"><img src="/templets/${templateStyle?if_exists}/images/but_jia.gif" onclick="jia()"></td>
              <td class="or_mani3"><span id="price"></span></td>
           </tr>
         </table>
         <div class="clear"></div>
     </div>
     
     <div class="liuyan">
       <div class="lly f_left">
         <p class="f_left">给卖家留言：</p>
         <p class="f_left"> <@s.textarea id="textarea2" name="order_remark" cssClass="txtinput" maxLength="50" cols="45" rows="2"/></p>
       </div>
        <div class="rly f_right">
        <p>运费共计：${(supply.shipfee)?if_exists} 元 </p>
        <p>实付款（含运费 ）：<span id="allprice"></span>元</p>
        </div>
         <div class="clear"></div>
     </div>
     
     <div class="clear"></div>
     
     <p class="f_right"><a href="###;" onclick="ordersubmit();"><img src="/templets/${templateStyle?if_exists}/images/saf.gif"></a></p> 
</div>
 <!--所属地区插件隐藏字段开始区域-->
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	 <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
 <!--所属地区插件隐藏字段结束区域-->
</@s.form>
<div class="clear"></div>
<#include "/${templateRoute?if_exists}/bottom.html">
<style type="text/css">
  .select{margin-right:10px;}
  .errorSpan{border:1px solid #2192EF;background:#A3D7F8 url(/include/images/no_result_bg.png) 8px 2px no-repeat;height:30px;
   display:in-line;padding:5px 36px 5px 36px;}
</style>
<script type="text/javascript">
//表单提交
 function ordersubmit(){
 	if($("input[name='radioaddr']:checked").val()=="0"){
      	alert("请保存收货地址！");
      }else{
	  //存放被选中的状态
	  $("#checked_id").val($("input[name='radioaddr']:checked").val()); 
	  document.forms[0].submit();
 	 }
 }
//关闭其他地址
 function closeaddr(){
  var addr=$("#addr");
  addr.hide();
  $("#checked_id").val($("input[name='radioaddr']:checked").val()); 
 }
 //打开其他地址
 function openaddr(){
  var addr=$("#addr");
  addr.show();
  $("#checked_id").val("0");
 }

  $(document).ready(function(){  
     $("input[name=radioaddr]:eq(0)").attr("checked",'checked'); 
     if($("#checked_id").val()=='0'){
       $("#other").attr("checked","checked");
     }
      if($("input[name='radioaddr']:checked").val()=="0"){
      var addr=$("#addr");
      addr.show();
      }
      loadfapiao();
      //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
	  area_select("${cfg_topareaid?if_exists}");
      var pricenum=${(supply.price)?if_exists};
      $("#price").html(pricenum);
      $("#all_price").val(pricenum);
      $("#allprice").html((parseFloat(pricenum)+parseFloat(${(supply.shipfee)?if_exists})).toFixed(2));
   });
 
 function jian(){
  if(parseInt($("#num").val())>1){
  var number = parseInt($("#num").val())-1;
  $("#num").val(number);
   var pricenum=${(supply.price)?if_exists}*number;
   $("#price").html(pricenum.toFixed(2));
   $("#all_price").val(pricenum.toFixed(2));
   $("#allprice").html((parseFloat(pricenum)+parseFloat(${(supply.shipfee)?if_exists})).toFixed(2));
 }
 }
 
 function jia(){
 var number = parseInt($("#num").val())+1;
 $("#num").val(number);
 var pricenum=${(supply.price)?if_exists}*number;
 $("#price").html(pricenum.toFixed(2));
 $("#all_price").val(pricenum.toFixed(2));
 $("#allprice").html((parseFloat(pricenum)+parseFloat(${(supply.shipfee)?if_exists})).toFixed(2));
 }
 function allmore(){
 var pricenum=${(supply.price)?if_exists}*$("#num").val();
 $("#price").html(pricenum.toFixed(2));
 $("#all_price").val(pricenum.toFixed(2));
 $("#allprice").html((parseFloat(pricenum)+parseFloat(${(supply.shipfee)?if_exists})).toFixed(2));
 }
 
 function addsubmit(){
     //验证收货人不能为空
     var cust_name = $("#order_cust_name").val();
     if(cust_name == "") {
		$("#cust_nameError").html("收货姓名不能为空");
		$("#cust_nameError").addClass("errorSpan");
		return false;
		}else{
		$("#cust_nameError").html("");
		$("#cust_nameError").removeClass("errorSpan");
		}
		
     //验证地址不能为空
      var areacount=0;	
           //验证所属地区的选择下拉框是否已选择		           
	    if($("select[id^=arealevel]").length>0){
	  
	        $("select[id^=arealevel]").each(function(){	
	              if($(this).val()=="0"){ 
	                  areacount+=1;
				}
	        });
	    
	        if(areacount>0)
	        {
				$("#areaError").html("请选择收货地址");
				$("#areaError").addClass("errorSpan");
				return false;
	        }else{
	            $("#areaError").html("");
	        }
	    }
     
     
     //验证街道地址不能为空
     var address = $("#order_address").val();
     if(address == "") {
		$("#addressError").html("街道地址不能为空");
		$("#addressError").addClass("errorSpan");
		return false;
		}else{
		$("#addressError").html("");
		$("#addressError").removeClass("errorSpan");
		}
     
     
     //验证邮政编码不能为空
      var post_code = $("#order_post_code").val();
      var postnum =/^\d{6}$/;
      if(post_code == "") {
		$("#postError").html("邮政编码不能为空");
		$("#postError").addClass("errorSpan");
		return false;
		}else if(!postnum.test(post_code)){
		$("#postError").html("邮政编码格式不正确");
		$("#postError").addClass("errorSpan");
		return false;
		}else{
		$("#postError").html("");
		$("#postError").removeClass("errorSpan");
		}
     
     
     //验证联系方式不能为空
       var phone =/^[0-9]{3,4}\-\d{7,8}(\(\d{1,6}\))?$/;
       var order_phone = $("#order_phone").val();
       var cell_phone = $("#order_cell_phone").val();
	   if(order_phone == "" && cell_phone == "") {
			$("#phoneError").html("联系电话不能为空");
			$("#phoneError").addClass("errorSpan");
			return false;
		}else if(order_phone!='' && !phone.test(order_phone)){
			$("#phoneError").html("正确格式为:0592-8888888");
			$("#phoneError").addClass("errorSpan");
			return false;
		}else if(cell_phone!='' && !(/^13\d{9}$/g.test(cell_phone)) && !(/^15[0-35-9]\d{8}$/g.test(cell_phone)) && !(/^18[05-9]\d{8}$/g.test(cell_phone))){
			$("#phoneError").html("手机号码格式有错");
			$("#phoneError").addClass("errorSpan");
			return false;
		}else{
			$("#phoneError").html("");
			$("#phoneError").removeClass("errorSpan");
		}
		
	   var area_value='';
	   //获取选中的地区串
       $(".select").each(function(){
              var item = $(this).find("option:selected").val();             
       	      area_value=area_value + item + ",";
       });
       
	  //获取收货地址串
	  var addinfo = cust_name + '|' + area_value + '|' + address + '|' + post_code + '|' + order_phone +  '|' + cell_phone ;
	  //编码
	  var wd=encodeURIComponent(encodeURIComponent(addinfo));	
	  $.ajax({
	      type: "post",
	      url: "/portal/goods!insertadd.action?addinfo=" + wd,
	      datatype:"json",
	      async:false,
	      success: function(data){ 
	          if(data=='1'){
	            alert("保存成功");
	          }else{
	            alert("保存失败");
	          }
	      }                 
      });
      
      document.location.reload();
		
 }
</script>
</body>

</html>
