<html>
<head>
<title>查看我的店铺</title>
<#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<script type="text/javascript">
  $(document).ready(function(){
     //所属地区的回选
     area_select("${cfg_topareaid?if_exists}");
  });	
   function subMethod(){     
      document.formshopcongif.action="/bmall_Shopconfig_bmallupdate.action";     
      document.formshopcongif.submit();     
  }
</script> 
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">查看我的店铺</a>
    </div>
 <@s.form action="/bmall_Shopconfig_bmallupdate.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>店铺信息</h2></div>
     
     <div class="base_infor">
       <h2 class="base_title">基本信息</h2>
       <div class="table_infor f_left">
          <table style="width:820px;" >
            <tr>
            <td class="firsttd" >审核状态：</td>
            <td><#if shopconfig.info_state==0><font color='red'>未审核</font></#if><#if shopconfig.info_state==1><font color='red'>审核通过</font></#if>
                <#if shopconfig.info_state==2><font color='red'>审核不通过</font></#if><#if shopconfig.info_state==3><font color='red'>禁用</font></#if>
             </td>
            </tr>
            <tr><td class="firsttd" >店铺名称<font color='red'>*</font></td><td>
        	<@s.textfield name="shopconfig.shop_name" cssClass="txtinput" />
         	<@s.fielderror><@s.param>shopconfig.shop_name</@s.param></@s.fielderror>
            </td></tr>  
            <tr><td  class="firsttd">店铺LOGO<font color='red'>*</font></td><td>
             <table border="0" cellpadding="0" cellspacing="0" >
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			<@s.textfield name="shopconfig.shop_logo" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td><font color="red">(建议 200*50 )</font></td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			
             		</tr>
             		
             	</table> 
             	<@s.fielderror><@s.param>shopconfig.shop_logo</@s.param></@s.fielderror>
            </td></tr> 
            
            
            <tr><td  class="firsttd">banner图片<font color='red'>*</font></td><td>
             <table border="0" cellpadding="0" cellspacing="0" >
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			<@s.textfield name="shopconfig.banner_image" id="uploadresult1" cssStyle="width:300px;"/>
             			</td>
             			<td><font color="red">(建议1400*100)</font></td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg('uploadifyfile1','uploadresult1','fileQueue1');</script>
             			</td>
             		</tr>
             	</table> 
             	<@s.fielderror><@s.param>shopconfig.banner_image</@s.param></@s.fielderror>
            </td></tr> 
            
            
            <tr><td  class="firsttd">导航下方广告图片<font color='red'>*</font></td><td>
             <table border="0" cellpadding="0" cellspacing="0" >
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue2"></div>
	              			<@s.textfield name="shopconfig.adv_image" id="uploadresult2" cssStyle="width:300px;"/>
             			</td>
             			<td><font color="red">(建议 980*150)</font></td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile2"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult2');"/>
	              			<script>uploadComponent('uploadifyfile2','uploadresult2','fileQueue2','image',false);</script>
             			</td>
             		</tr>
             	</table> 
             	<@s.fielderror><@s.param>shopconfig.adv_image</@s.param></@s.fielderror>
            </td></tr> 
            
            
            <tr><td  class="firsttd">店铺网址：</td><td>
           <@s.textfield name="shopconfig.shop_site" cssClass="txtinput" />
           <@s.fielderror><@s.param>shopconfig.shop_site</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">我所在地区<font color='red'>*</font></td><td>
             <div id="arealist"></div>
             <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td></tr> 
            <tr><td  class="firsttd">具体地址：</td><td>
           <@s.textfield name="shopconfig.address" cssClass="txtinput" />
           <@s.fielderror><@s.param>shopconfig.address</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">经营范围：</td><td>
            <@s.textarea name="shopconfig.busi_range" cssClass="txtinput" 
             onkeyup="set_textarea_length(this,100);"cssStyle="width:300px;height:100px;"/>
             <@s.fielderror><@s.param>shopconfig.busi_range</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">经营模式<font color='red'>*</font></td><td>
             <@s.radio name="shopconfig.busi_mode" list="clientStrList" listValue="para_key" listKey="para_value" />
             <@s.fielderror><@s.param>shopconfig.busi_mode</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">是否暂时关闭网站<font color='red'>*</font></td><td>
               <@s.radio name="shopconfig.is_colse" list=r"#{'0':'是','1':'否'}"  /> 
               <@s.fielderror><@s.param>shopconfig.is_colse</@s.param></@s.fielderror>
            </td></tr> 
           <tr style="border:1px solid red;">
            <td  class="firsttd">店铺简介：</td>
            <td>
            <@s.textarea name="shopconfig.shop_intro" id="content" cssClass="txtinput"   />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
         	<@s.fielderror><@s.param>shopconfig.shop_intro</@s.param></@s.fielderror>
            </td>
           </tr> 
          </table>
       </div>
     </div>
     
     <div class="clear"></div>
     
     <div class="base_infor">
       <h2 class="base_title">联系信息</h2>
       <div class="table_infor">
          <table  style="width:750px;">
             
             <tr><td  class="firsttd">联系人：</td><td>
            <@s.textfield name="shopconfig.contant_man" cssStyle="width:200px;"  />
            <@s.fielderror><@s.param>shopconfig.contant_man</@s.param></@s.fielderror>
             </td></tr> 
            <tr><td  class="firsttd">email：</td><td>
            <@s.textfield name="shopconfig.email" cssStyle="width:200px;" />
            <@s.fielderror><@s.param>shopconfig.email</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">QQ：</td><td>
           <@s.textfield name="shopconfig.qq" cssStyle="width:200px;" />
           <@s.fielderror><@s.param>shopconfig.qq</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">msn：</td><td>
            <@s.textfield name="shopconfig.msn" cssStyle="width:200px;" />
            <@s.fielderror><@s.param>shopconfig.msn</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">淘宝旺旺：</td><td>
           <@s.textfield name="shopconfig.alliwang" cssStyle="width:200px;" />
           <@s.fielderror><@s.param>shopconfig.alliwang</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">手机：</td><td>
           <@s.textfield name="shopconfig.mobile" cssStyle="width:200px;" />
           <@s.fielderror><@s.param>shopconfig.mobile</@s.param></@s.fielderror>
            </td></tr> 
            <tr><td  class="firsttd">固定电话：</td><td>
           <@s.textfield name="shopconfig.phone" cssStyle="width:200px;" />
           <@s.fielderror><@s.param>shopconfig.phone</@s.param></@s.fielderror>
            </td></tr> 
            <tr>
         	<td colspan="2" align="center">
         		<input type="button" value="提  交" onclick="subMethod();"  class="submitbut"></p>
		       <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		        <@s.hidden  name="shopconfig.shop_id"/>
         	</td>
        </tr>    
          </table>
       </div>
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

















