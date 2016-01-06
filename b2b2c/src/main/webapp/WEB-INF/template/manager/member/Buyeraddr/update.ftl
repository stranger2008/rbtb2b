<html>
  <head>
    <title>修改收货地址</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <link href="/include/components/upload/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/include/components/upload/swfobject.js"></script>
    <script type="text/javascript" src="/include/components/upload/jquery.uploadify.v2.1.4.js"></script>
    <script type="text/javascript" src="/include/components/upload/upload.js"></script>
    <link href="/include/components/upload/showpic.css" rel="stylesheet" type="text/css" />
	<script src="/include/components/upload/showpic.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){      
          //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script> 
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Buyeraddr_update.action" method="post" validate="true">
      	  <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:会员信息>会员资料>收货地址管理>修改收货地址</td>
 </tr>
</table>
     <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">收货人姓名<span class="mustfield">*</span></td>
              <td width="83%">
             	<@s.textfield name="buyeraddr.cust_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>buyeraddr.cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">所在地区<font color="red">*</font></td>
             <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">详细地址<font color="red">*</font></td>
             <td>
             	<@s.textfield name="buyeraddr.address" cssClass="txtinput" maxLength="100" cssStyle="width:500px"/>
             	<@s.fielderror><@s.param>buyeraddr.address</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">邮政编码<font color="red">*</font></td>
             <td>
             	<@s.textfield name="buyeraddr.post_code" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>buyeraddr.post_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">固定电话:</td>
             <td>
             	<@s.textfield name="buyeraddr.phone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>buyeraddr.phone</@s.param></@s.fielderror>
             	（固定电话和手机号码至少一个不为空）
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">手机号:</td>
             <td>
             	<@s.textfield name="buyeraddr.cell_phone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>buyeraddr.cell_phone</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
            <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	        <@s.hidden name="buyeraddr.addr_id"/>
	       <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <@s.submit value="保存" cssClass="sub"/>
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Buyeraddr_list.action','');"/>
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