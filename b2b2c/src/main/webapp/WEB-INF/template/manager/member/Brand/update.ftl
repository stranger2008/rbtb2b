<html>
<head>
    <title>修改品牌</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<#include "/include/uploadInc.html">
	<script type="text/javascript">
	  $(document).ready(function(){
	     //所属分类的回选
         //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"brand");  
		 </#if>           
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}"); 
         //表单提交前的判断,先通过脚本验证，再通过异步的方式与数据库验证，通过后提交表单	 
		 $("#update").click( function (){
		      //取button的ID
		      type_button=$(this).attr("id");//获取当前标签的ID值
              checkattr();
		  });
	  });
	</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Brand_update.action" method="post" validate="true">
 <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>品牌管理>修改品牌</td>
 </tr>
</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
   <#if (brand.info_state)?if_exists='2'> 
      <tr>
         <td width="17%" valign="middle" class="left_td" style="font-weight:bold;">未通过理由:</td>
        <td> ${(brand.no_reason)?if_exists}</td>
       </tr>
   </#if>
   <tr>
         <td width="19%" valign="middle" class="left_td">品牌名称<font color='red'>*</font></td>
         <td>
         	<@s.textfield name="brand.title" cssClass="txtinput" maxLength="100" cssStyle="width:500px;" />
         	<@s.fielderror><@s.param>brand.title</@s.param></@s.fielderror>
         </td>
       </tr>
       <tr>
         <td valign="middle" class="left_td">品牌图片<font color='red'>*</font></td>
         <td>           
         <table border="0" cellpadding="0" cellspacing="0">
         		<tr>
         			<td style="padding:0px;">
         			    <div id="fileQueue"></div>
              			  <@s.textfield name="brand.img_path" id="uploadresult" maxLength="100" cssStyle="width:300px;" />
         			</td>
         			<td style="padding-left:3px;">
         				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
         			</td>
         			<td style="padding-left:3px;">
         				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
         			</td>
         		</tr>
         	</table>
         	<@s.fielderror><@s.param>brand.img_path</@s.param></@s.fielderror>
         </td>
       </tr>
       
        <tr>
               <td valign="middle" class="left_td">所属分类<font color="red">*</font></td>
               <td>
                <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="brand.cat_attr" />
              		<a href="/member_Brand_cate.action?cat_attr=0&brand.brand_id=${brand.brand_id?if_exists}">[重新选择]</a>
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>    
           </tr>
            <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if> 
       <tr>
         <td valign="middle" class="left_td">官方主页<font color='red'>*</font></td>
         <td>
         	<@s.textfield name="brand.web_url" cssClass="txtinput" maxLength="100" cssStyle="width:500px;" />
         	<@s.fielderror><@s.param>brand.web_url</@s.param></@s.fielderror>
         </td>
       </tr>
      
        <tr>
         <td valign="middle" class="left_td">品牌介绍<font color='red'>*</font></td>
         <td>
            <@s.textarea name="brand.content" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="brand_content" />
			<@s.fielderror><@s.param>brand.content</@s.param></@s.fielderror>
         </td>
      </tr> 
             
      <tr>
        <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="brand.brand_id" />
           <@s.hidden name="brand.cust_id"/>
           <@s.hidden name="brand.user_id"/>
           <@s.hidden name="brand.area_attr"/>
	       
	       ${listSearchHiddenField?if_exists}     
	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>


	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->

	      <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Brand_list.action','');"/>
         </td>
       </tr>
     </table>
 </div>
 </@s.form>
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
<!--cont结束-->
</body>
</html>