<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>核对订单-${cfg_mallwebname?if_exists}</title>
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/writeInfromation.css" rel="stylesheet" type="text/css" />	
<link href="/include/css/common.css" rel="stylesheet" type="text/css" />
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
</head>

<body>
<@s.form action="" method="post">
<#include "/WEB-INF/template/bmall/default/top.ftl">
<input type="hidden"  id="hidden_area_value" name="hidden_area_value" />
<div class="position"><p>您当前的位置：<a href="${cfg_mallindexurl}" target="_blank">首页</a> > 核对订单</p></div>
<div class="w980">
  <div><img src="/templets/bmall/images/xzwfpic_03.gif"></div>
</div>
<div class="clear"></div>
<@s.hidden id="brandlight" value="${brandlight?if_exists}"/>
<div class="w980">
  <div class="wftleft f_left"></div>
  <div class="wftright f_right"></div>
  <div class="wfmidtitle f_left">填写核对订单信息</div>
  <div class="clear"></div>
  <div class="wfcont">
  
    <div class="w912">
    
      <input type="hidden" id="addreshidden" value="0" />
      <input type="hidden" id="playtypehidden" value="0" />
      <input type="hidden" id="fapiaohidden" value="0" />
      <div class="wfline1" id="addrmr">
        <h2 class="wfh2"><span>收货人信息</span><a href="###;"  style="cursor:pointer;" onclick="buyeraddr('update')" class="bule">[修改]</a></h2>
        <table>
          <tr><td class="ftbstyle1">收货人名：</td><td><@s.label id="sname"  value="${(buyeraddr.cust_name)?if_exists}" /></td></tr>
          <tr><td class="ftbstyle1">省　　份：</td><td><@s.label id="sarea" value="${(buyeraddr.area_attr)?if_exists}"/> </td></tr>
          <tr><td class="ftbstyle1">地　　址：</td><td><@s.label id="sdirarea"  value="${(buyeraddr.address)?if_exists}"/></td></tr>
          <tr><td class="ftbstyle1">手机号码：</td><td><@s.label id="scell_phone"  value="${(buyeraddr.cell_phone)?if_exists}"/> </td></tr>
          <tr><td class="ftbstyle1">固定电话：</td><td><@s.label id="sphone"  value="${(buyeraddr.phone)?if_exists}"/></td></tr>
          <tr><td class="ftbstyle1">邮　　编：</td><td><@s.label id="spost"   value="${(buyeraddr.post_code)?if_exists}"/></td></tr>
          
              <@s.hidden id="sname1" name="hidden_sname" value="${(buyeraddr.cust_name)?if_exists}"/>
              <@s.hidden id="sdirarea1" name="hidden_sdirarea" value="${(buyeraddr.address)?if_exists}"/>
              <@s.hidden id="scell_phone1" name="hidden_scell_phone" value="${(buyeraddr.cell_phone)?if_exists}"/>
              <@s.hidden id="sphone1" name="hidden_sphone" value="${(buyeraddr.phone)?if_exists}"/>
               <@s.hidden id="spost1" name="hidden_spost" value="${(buyeraddr.post_code)?if_exists}"/>
          
        </table>
      </div>
      
      <div class="wfline2" id="addradd">
        <div class="wflinec">
          <h2 class="wfh2"><span>收货人信息</span><a  href="###;"  style="cursor:pointer;" onclick="update('off')" class="bule">[关闭]</a> 
          <span id="alladdressTip" style="color:red;font-size:10pt;display:none;"><img src="/templets/bmall/images/ordertip.png"  width="20px" height="20px" />请填写收货人信息</span></h2>
          <div class="adr" id="cyadrid">
            <p >常用地址<p>
	        <p id="selectaddr"/>
          </div>
          <div class="clear"></div>
          <table>
          <@s.hidden id="addrid" name="addrid" value="${(buyeraddr.addr_id)?if_exists}"/>
          <tr><td class="ftbstyle1"><span class="red">*</span>收货人名：</td><td><input id="name" maxlength="20"   onblur="checktextnullHtml('name','errorname','*请输入收货人姓名','text');"   type="text" class="td2text"><span style="color:red;"  id="errorname"/></td></tr>
          <tr><td class="ftbstyle1"><span class="red">*</span>省　　份：</td><td><div id="arealist"></div><span style="color:red;"  id="errorarealist"/></td></tr>
          <tr><td class="ftbstyle1"><span class="red">*</span>地　　址：</td><td> <input id="address" maxlength="100"   onblur="checktextnullHtml('address','erroraddress','*请输入详细地址','text');"  class="td2text"  type="text"  style="width:400px;" /><span  style="color:red;"  id="erroraddress"/></td></tr>
          <tr><td class="ftbstyle1"><span class="red">*</span>手机号码：</td><td><input id="cell_phone" onkeyup="checkNum(this);" onblur="checkNum_order('cell_phone','errorphone');" maxlength="11" style="width:120px;"   type="text" class="td2text" /><span>或者 固定电话：</span><input id="phone" style="width:120px;"   maxlength="13"   type="text" class="td2text"><span> 用于接收发货通知短信及送货前确认  </span><span style="color:red;"  id="errorphone"/></td></tr>
          <tr><td class="ftbstyle1"><span class="red">*</span>邮　　编：</td><td><input id="post_code" maxlength="6" onkeyup="checkNum(this);"   onblur="checkNum_order('post_code','errorpost');" style="width:80px;"  type="text" class="td2text"><span>有助于快速确定送货地址</span><span  style="color:red;"  id="errorpost"/></td></tr>
        </table>
        <p class="ppad"><a href="###;"  onclick="buyeraddr('add')" style="cursor:pointer;" class="bule">[添加到常用地址]</a></p>
        <p class="ppad2"><a href="###;"  onclick="update('keep');" style="cursor:pointer;"><img src="/templets/bmall/images/xzsave_03.gif"></a></p>
        </div>  
      </div> 
      
      <div class="wfline1" id="playtype">
        <h2 class="wfh2"><span>支付方式</span><a  href="###;"  onclick="play();" style="cursor:pointer;" class="bule">[修改]</a></h2>
        <table>
          <tr><td class="ftbstyle1">支付方式：</td><td id="paytypeid">--</td></tr>          
        </table>
      </div>
      
      
      <div class="wfline2" id="updateplay">
        <div class="wflinec">
          <h2 class="wfh2"><span>支付方式</span><a href="###;" style="cursor:pointer;"  onclick="updateplay()" class="bule">[关闭]</a>
          <span id="allpayandfareTip" style="color:red;font-size:10pt;display:none;"><img src="/templets/bmall/images/ordertip.png"  width="20px" height="20px" />请填支付及配送方式</span>
          </h2>
          
          <div class="pay">
            <table id="tablepeytype">
               <tr><td class="tbpay1">支付方式</td><td class="tbpei2">备注</td></tr>
               <#list paymentList as payment>
                <tr><td class="tbpay2"><input type="radio" onclick="checktext('paytipid','','1');" <#if payment_index==0>checked</#if>  name="zhif" id="${payment.pay_id?if_exists}"  text="${payment.pay_name?if_exists}">${payment.pay_name?if_exists}</td>
                <td><span class="hscolor">${payment.pay_desc?if_exists}</span></td></tr>
               </#list>
                <tr><td colspan="2" id="paytipid" style="color:red;"></td></tr>
            </table>
          </div>

          <div class="clear"></div>
          <p class="ppad3"><a href="###;"  style="cursor:pointer;"  onclick="updateplay()"><img src="/templets/bmall/images/xzpay_03.gif"></a></p>
        </div>  
      </div> 
      
      
      
      <div class="wfline1" id="fapiaoupdate">
        <h2 class="wfh2"><span>发票信息</span><a href="###;" class="bule" onclick="updatefapiao()">[修改]</a></h2>
        <table  id="yesfapiao">
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
      
      <div class="wfline2" id="fapiaoclose">
        <div class="wflinec">
          <h2 class="wfh2"><span>发票信息</span><a href="###;" class="bule" onclick="closefapiao()">[关闭]</a>
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
	               <td   style="color:#999999;">温馨提示：您填写的所有内容都将被系统自动打印到发票上，所以请千万别填写和发票抬头无关的信息。</td>
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
      </div>
      
      <div class="wfline1">
        <h2 class="wfh2"><span class="f_left">商品清单</span><a href="/mall/goods!mallcart.action" class="bule f_right">返回修改购物车</a></h2>
        <div class="clear"></div>
        <div class="merchBill" id="goostable">
          <table cellpadding="0" cellspacing="0" class="merchtitle" width="100%">
            <tr><th width="30%">商品名称</th>
                 <th width="10%">属性</th>
	            <th width="10%">单价(元)</th>
	            <th width="10%">返现</th>
	            <th width="10%">赠送积分</th>
	            <th width="10%">库存状态</th>
	            <th width="10%">商品数量</th>
	            <th width="10%">小计(元)</th>
            </tr>
          </table>
         
      
        </div>
      </div>
      
 
      <p class="settle">结算信息</p>
      
      <div class="wfline2">
        <div class="wflinec">
          <p class="jh">商品金额：<span id="allcountmoney">0.00</span>元 + 运费：<span id="allyunfares">0.00</span>元 <span id="baojiashow" style="display:none;">+ 保价费：<span id="allbaojiafare">0.00</span>元 </span></p>
          <p class="yh"><span class="jq f_right">应付总额：<span class="bred">￥<span id="allmoney">0.00</span> </span>元</span></p>
          <div class="clear"></div>
        </div>  
      </div>
      
      <p class="checkmag"><p> 
      <p class="submitbut f_right"><a href="###;" onclick="submitOrderForm();"><img src="/templets/bmall/images/xzsubmitbut_07.gif"></a></p>
    </div>
  </div>
</div>
<@s.hidden id="hidden_cust_id_value" name="hidden_cust_id_value" />
<@s.hidden id="hidden_goods_id_value" name="hidden_goods_id_value" />
<@s.hidden id="hidden_cfg_openmall" value="${web_openmall?if_exists}" />
<@s.hidden id="hidden_paytype" name="hidden_paytype"/>

<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<script type="text/javascript" src="/templets/bmall/js/orderinfo.js"></script>
<script type="text/javascript" src="/include/js/common.js"></script> 
<script type="text/javascript" src="/include/js/jNotify.jquery.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
	   		//初始化加载收货人信息
			buyeraddr("update");
			loadaddr();
			//初始化加载支付以及配送方式信息
			loadpay();
	  		//初始化加载发票信息
			loadfapiao();
	 	 	//加载商品信息
	  		mallReadGoodsCookies();
	  	 	//计算不同配送方式的价格
	 		selModePrice();
	});
</script>
<div class="clear"></div>
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</@s.form>
</body>
</html>
