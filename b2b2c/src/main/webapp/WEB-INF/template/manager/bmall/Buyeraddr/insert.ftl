<html>
<head>
<title>添加收货地址</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<script type="text/javascript">
	  $(document).ready(function(){      
          //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         
	  });
</script> 
</head>
<body>
	<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">收货地址</a><span>></span><a href="#">添加收货地址</a>
    </div>
<@s.form action="/bmall_Buyeraddr_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>账号管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">添加收货地址</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td class="firsttd">收货人姓名<font color="red">*</font></td><td>
        	<@s.textfield name="buyeraddr.cust_name" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>buyeraddr.cust_name</@s.param></@s.fielderror>
            </td></tr>
              
            <tr><td class="firsttd">地区<font color="red">*</font></td>
        	 <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
            </tr>  
            
            <tr><td  class="firsttd">详细地址<font color="red">*</font></td><td>
        	<@s.textfield name="buyeraddr.address" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>buyeraddr.address</@s.param></@s.fielderror>
            </td></tr>  
            
             <tr><td class="firsttd">邮编<font color="red">*</font></td><td>
        	<@s.textfield name="buyeraddr.post_code" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>buyeraddr.post_code</@s.param></@s.fielderror>
            </td></tr> 
            
             <tr><td class="firsttd">电话：</td><td>
        	<@s.textfield name="buyeraddr.phone" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>buyeraddr.phone</@s.param></@s.fielderror>
            </td></tr> 
            
            <tr><td class="firsttd">手机：</td><td>
        	<@s.textfield name="buyeraddr.cell_phone" cssClass="txtinput" maxLength="20"/>
        	<@s.fielderror><@s.param>buyeraddr.cell_phone</@s.param></@s.fielderror>
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
             	<!--所属地区插件隐藏字段开始区域-->
		     	<@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
		     	<!--所属地区插件隐藏字段结束区域-->
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Buyeraddr_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

