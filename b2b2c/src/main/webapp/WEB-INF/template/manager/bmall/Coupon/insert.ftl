<html>
<head>
<title>添加优惠券</title>
     <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
<script type="text/javascript">
	   $(document).ready(function(){
	     var radvalue= $("input[name=coupon.c_type][checked]").val();
	     if(radvalue=='0'){
	       $("#jiner").show();
	     }else if(radvalue=='1'){
	       $("#jifen").show();
	     }else{
	        $("#zhekou").show();
	     }
	     
	     $("input[name=coupon.c_type]").click(function(){
           var selvalue = $(this).val();
           
            if(selvalue=='0'){
	         $("#jiner").show();
	         $("#jifen").hide();
	         $("#zhekou").hide();
	        }else if(selvalue=='1'){
	          $("#jifen").show();
	          $("#jiner").hide();
	          $("#zhekou").hide();
	        }else{
	          $("#zhekou").show();
	          $("#jifen").hide();
	          $("#jiner").hide();
	         }
          });
	   }); 
	</script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">促销管理</a><span>></span><a href="#">优惠券管理</a><span>></span><a href="#">添加优惠券</a>
    </div>
<@s.form action="/bmall_Coupon_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>促销管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">添加优惠券</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >
              <tr>
	             <td class="firsttd">优惠券名称<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="coupon.c_name" cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>coupon.c_name</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           
				   <tr>
		             <td class="firsttd">优惠券图片：</td>
		             <td> 
		             
		              <table border="0" cellpadding="0" cellspacing="0">
		             		<tr>
		             			<td style="padding:0px;">
		             			    <div id="fileQueue"></div>
			              			 <@s.textfield name="coupon.c_img" id="uploadresult" cssStyle="width:300px;"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			              			<script>uploadOneImg();</script>
		             			</td>
		             		</tr>
		             	</table>
		                  <@s.fielderror><@s.param>coupon.c_img</@s.param></@s.fielderror>
		              
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">优惠券类型<font color='red'>*</font></td>
		             <td>
		                <@s.radio name="coupon.c_type" list=r"#{'0':'优惠金额','1':'优惠积分','2':'优惠折扣'}" value="0"/>
		             	<@s.fielderror><@s.param>coupon.c_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr id="jifen" style="display:none" >
		             <td class="firsttd">优惠积分：</td>
		             <td>
		             	<@s.textfield name="coupon.c_inter" cssStyle="width:100px" cssClass="txtinput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>coupon.c_inter</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr id="jiner" style="display:none">
		             <td class="firsttd">优惠金额：</td>
		             <td>
		             	<@s.textfield name="coupon.c_money" cssStyle="width:100px" cssClass="txtinput" maxLength="20" value="0"/>
		             	<@s.fielderror><@s.param>coupon.c_money</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr id="zhekou" style="display:none">
		             <td class="firsttd">优惠折扣：</td>
		             <td>
		             	<@s.textfield name="coupon.c_discount" cssStyle="width:100px" cssClass="txtinput" maxLength="6" value="0"/>
		             	(折扣数1-100)
		             	<@s.fielderror><@s.param>coupon.c_discount</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">使用次数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="coupon.coupon_times" cssStyle="width:50px" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>coupon.coupon_times</@s.param></@s.fielderror>
		             </td>
		           </tr>
			       <tr>
				    <td class="firsttd">有效时间<font color='red'>*</font></td>
		             <td colspan="3">  
		             <@s.textfield id="txtStartDate" name="coupon.start_time" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtEndDate\\',{d:-1})}',readOnly:true})"/>
		             	至<@s.textfield id="txtEndDate" name="coupon.end_time" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtStartDate\\',{d:1})}',readOnly:true})"/>          
		             	<@s.fielderror><@s.param>coupon.start_time</@s.param></@s.fielderror>
		             	<@s.fielderror><@s.param>coupon.end_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="firsttd">是否通用<font color='red'>*</font></td>
		             <td>
		                <@s.radio name="coupon.is_every" list=r"#{'0':'是','1':'否'}" value="1"/>
		             	<@s.fielderror><@s.param>coupon.is_every</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td colspan="2" align="center">
		             	<@s.submit value="提  交" cssClass="submitbut"/>
		             	<@s.hidden name="coupon.c_id"/>
		             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Coupon_list.action','');"/>
		             </td>
                   </tr>
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
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

