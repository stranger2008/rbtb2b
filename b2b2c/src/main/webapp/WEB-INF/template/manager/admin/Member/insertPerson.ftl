<html>
  <head>
    <title>添加个人会员</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         //获取单选框的值
	  });  
	  
	   function getMemValue() {
		var radioed=$("input[name='member.mem_type']:checked").val();
			if (radioed == "0") {
				window.location.href="/admin_Member_add.action";
			}else if(radioed == "1"){
			    window.location.href="/admin_Member_addPerson.action";
			}
	   }
	</script>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:会员管理 > 会员管理 > 添加个人会员
 </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Member_insertPerson.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td colspan="2" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员账号信息</td>
           </tr>
           <tr>
            <td  width="15%" class="table_name">会员类型<font color='red'> *</font></td>
             <td>
                <@s.radio name="member.mem_type" list=r"#{'0':'企业','1':'个人'}" value="1" onclick="getMemValue();"/>
                <@s.fielderror><@s.param>member.mem_type</@s.param></@s.fielderror>      	
             </td>
           </tr>
           <tr>
             <td class="table_name">用户名<font color='red'> *</font></td>
             <td>
                <@s.textfield id="user_name" name="username" cssClass="txtinput" cssStyle="width:200px;" maxLength="20"/>
                <@s.fielderror><@s.param>username</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">密&nbsp;&nbsp;码<font color='red'> *</font></td>
             <td>
             	<@s.password id="passwd" name="passwd" cssClass="txtinput" cssStyle="width:200px;" maxLength="32"/>
             	<@s.fielderror><@s.param>passwd</@s.param></@s.fielderror>
             </td>
           </tr>
          <tr>
             <td colspan="2" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员基本信息</td>
           </tr>
           <tr>
             <td class="table_name">会员名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="member.cust_name" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	&nbsp;&nbsp;<input type="radio" name="member.contact_sex" value="先生" checked/>先生
							<input type="radio" name="member.contact_sex" value="女士"/>女士
             	<@s.fielderror><@s.param>member.cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">个人头像:</td>
             <td>
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="member.logo_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			  <@s.fielderror><@s.param>member.logo_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
             
             </td>
           </tr>
           <tr>
             <td class="table_name">电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="member.email" cssClass="txtinput" maxLength="100"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.email</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">所在地区<font color='red'> *</font></td>
             <td>
                  <div id="arealist"></div>
                  <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>    
             </td>
           </tr>
            <tr>
             <td class="table_name">电话:</td>
             <td>
             	<@s.textfield name="member.phone" cssClass="txtinput" maxLength="50" cssStyle="width:200px;" /> 
             	<@s.fielderror><@s.param>member.phone</@s.param></@s.fielderror>
             </td> 
           </tr>
            <tr>
             <td class="table_name">手机:</td>
             <td>
             	<@s.textfield name="member.contact_cellphone" cssClass="txtinput" maxLength="50"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.contact_cellphone</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.textfield name="member.contact_qq" cssClass="txtinput"  maxLength="100"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.contact_qq</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
               <td class="table_name">MSN:</td>
             <td>
             	<@s.textfield name="member.contact_msn" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.contact_msn</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">邮政编码:</td>
             <td>
             	<@s.textfield name="member.post_code" cssClass="txtinput"  maxLength="50"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>member.post_code</@s.param></@s.fielderror>
             </td>
           </tr> 
          
            <tr>
             <td class="table_name">联系地址:</td>
             <td colspan="3">
             	<@s.textfield name="member.address" cssClass="txtinput"  maxLength="200" rows="3" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>member.address</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td class="table_name">推荐:</td>
             <td colspan="3">
             	<@s.radio name="member.recommend" list=r"#{'0':'非推荐','1':'推荐'}" value="0"/>
             	<@s.fielderror><@s.param>member.recommend</@s.param></@s.fielderror>
             </td>
          </tr>                          
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="member.cust_type" value="h"/>
	       <@s.hidden name="member.cust_rage" value="0"/>
	       <@s.hidden name="member.client_status" value="0"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       
	       <!--默认已经审核通过-->
	       <@s.hidden name="member.info_state" value="1"/>
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Member_list.action','');"/>
	     </div>	 
	         
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
<!--cont结束-->
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