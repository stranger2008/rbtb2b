<html>
  <head>
    <title>修改投诉</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	//全局变量
	var check='';
	//判断checkbox是否被选中
	$(document).ready(function() {
                if ($("#orderid").val()=='') {
                   $("#checkorder").attr("checked",true);
                   document.getElementById("orderid").disabled=true;     
                   document.getElementById("orderid").style.backgroundColor="#e9e9e9";
                   //清空填写订单号
                   $("#orderid").val("");
                   $("#order_idError").html("");
	               $("#order_idError").removeClass("error");
                   $("#selid").val("2");
                   $("#name").show();
                }else{
                    $("#checkorder").attr("checked",false);
                    document.getElementById("orderid").disabled=true;
                    document.getElementById("orderid").style.backgroundColor="#FFF";
                    //清空填写的被投诉方名称
                    $("#getcust_name").val("");
                    $("#cust_nameError").html("");
	                $("#cust_nameError").removeClass("error");
                    $("#selid").val("1");
                    $("#name").hide();
                    
                }
               
           
        });
        
        
function orderidval(iswho)
{    
  //订单号
  var order_id=$("#orderid").val();
  //被投诉方公司名称
  var getcust_name=$("#getcust_name").val();
  
   if(getcust_name==''&&iswho=='2'){
   $("#cust_nameError").html("请输入被投诉方名称！");
   $("#cust_nameError").addClass("error");
   return false;
  }
  
  if(order_id==''&&iswho=='1'){
   return false;
  }
     $.ajax({  	 
         type: "post",    	     
         url: "complaint!valiorder.action?order_id=" + order_id + "&getcust_name="+getcust_name +"&iswho="+iswho+"&ajaxRequestRandom="+Math.random(),       
         datatype:"json",
         success: function(data){   
           if(data=='1'){
             $("#order_idError").html("订单ID号不存在！");
		     $("#order_idError").addClass("error");
		     check='1';
           }else{
             $("#order_idError").html("");
	         $("#order_idError").removeClass("error");
	         check='0';
           }   
           
           if(data=='2'){
             $("#cust_nameError").html("被投诉方名称不存在！");
		     $("#cust_nameError").addClass("error");
		     check='1';
           }else{
            $("#cust_nameError").html("");
	         $("#cust_nameError").removeClass("error");
	         check='0';
           }
	   }
  });  	
}     
    function checkall(){
    //订单号
  var order_id=$("#orderid").val();
  //被投诉方公司名称
  var getcust_name=$("#getcust_name").val();
   if(order_id==''&&$("#selid").val()=='1'){
    $("#order_idError").html("请输入订单ID号！");
   $("#order_idError").addClass("error");
   return false;
   }
  
   if(getcust_name==''&&$("#selid").val()=='2'){
   $("#cust_nameError").html("请输入被投诉方名称！");
   $("#cust_nameError").addClass("error");
   return false;
  }
     if(check=='1'){
        return false;
      }else{
      return true;}
    }
	</script>
	
	<style type="text/css">
	.error{
	width:250px;
	color:red;
	background:#FFE1E1 url(/include/images/errorIcon.gif) no-repeat 10px center;
	height:30px;
	display:inline-block;
	line-height:30px;
    color:#CB4445;
	padding:0 10px 0 30px;
	font-size:12px;
}
	</style>
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Complaint_update.action" method="post" validate="true" onsubmit="return checkall()">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>投诉举报>修改投诉</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">投诉类型<span class="mustfield">*</span></td>
              <td width="83%">
             	<@s.select name="complaint.comp_type" list="comp_typeList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
		        <@s.fielderror><@s.param>complaint.comp_type</@s.param></@s.fielderror>
             </td>
           </tr>
          
            <tr>
             <td valign="middle" class="left_td">订单号<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield id="orderid" name="complaint.order_id"  disabled="true" cssStyle="width:375px;" onblur="orderidval(1)" cssClass="txtinput" maxLength="17"/>
             	<input type="checkbox" id="checkorder" name="orderid" disabled="true" />无订单号 <span id="order_idError"></span>
             	<@s.fielderror><@s.param>complaint.order_id</@s.param></@s.fielderror>
             	</br>单号类型：支付宝担保交易、预存款交易、诚信保障合同、即时到账、保障金安全交易
             </td>
           </tr>
           
            <tr id="name" style="display:none;">
             <td valign="middle" class="left_td">被投诉方名称<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield id="getcust_name" name="complaint.get_cust_name"  disabled="true" cssStyle="width:375px;" onblur="orderidval(2)" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>complaint.get_cust_name</@s.param></@s.fielderror>
             	<span id="cust_nameError"></span>
             </td>
           </tr>
           
           
            <tr>
             <td valign="middle" class="left_td">投诉产品:</td>
             <td>
             	<@s.textfield name="complaint.product_name" cssStyle="width:375px;" cssClass="txtinput" maxLength="100"/>
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">交易金额<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="complaint.trade_fund" cssStyle="width:245px;" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>complaint.trade_fund</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">您的邮箱<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="complaint.email" cssStyle="width:245px;" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>complaint.email</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">您的联系电话<span class="mustfield">*</span></td>
             <td>
             	<@s.textfield name="complaint.phone" cssStyle="width:245px;" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>complaint.phone</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">投诉内容<span class="mustfield">*</span></td>
             <td>
             	<@s.textarea name="complaint.content" cssClass="txtinput" id="desc" maxLength="20"/>
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('desc');  
				</script>
				<@s.fielderror><@s.param>complaint.content</@s.param></@s.fielderror>
             </td>
           </tr>
           
             <tr>
             <td valign="middle" class="left_td">上传身份证明资料:</td>
              <td> 
                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			   <@s.textfield name="complaint.file_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImgMulti();</script>
             			</td>
             		</tr>
             		<tr>
             		   <td colspan="4">建议上传居民身份证、户口簿或护照等，单张最大200K (仅网站工作人员可见)</td>
             		</tr>
             	</table>  
              </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">上传举证资料:</td>
             <td>
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			   <@s.textfield name="complaint.comp_path" id="uploadresult1" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadComponent("uploadifyfile1","uploadresult1","fileQueue1","image",true);</script>
             			</td>
             		</tr>
             		<tr>
             		  <td colspan="4">建议上传交易中的聊天记录截图、实物照片等(网站工作人员和被投诉方均可见)</td>
             		</tr>
             	</table>       
             </td>
           </tr> 
	       
	      <tr>
             <td valign="middle" class="left_td">上传附件:</td>
             <td> 	
             
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			<div id="fileQueue2"></div>
				         <@s.textfield name="complaint.attach_path"  id="uploadresult2" cssStyle="width:300px;"/>   
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile2"/>
             				  <script>uploadComponent("uploadifyfile2","uploadresult2","fileQueue2","file",true);</script>
             			</td>
             		</tr>
             		<tr>
             		  <td>建议上传交易过程中的发货单、合同等电子文本</td>
             		</tr>
             	</table>
              <@s.fielderror><@s.param>complaint.attach_path</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
           <td colspan="4" class="subbut">
            <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden id="selid" name="selid" value="1"/>
	       <@s.hidden name="complaint.info_id"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="修改投诉" cssClass="sub"/>
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Complaint_list.action','');"/>
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
  
  </body>
</html>