<html>
<head>
<title>添加产品</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 	     
	     //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"product");  
		 </#if> 
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");        
	  });
	</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Product_insert.action" method="post" validate="true">
  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>产品管理>添加产品</td>
 	</tr>
  </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">产品标题<span class="mustfield">*</span></td>
    <td width="83%">
   			<@s.textfield name="product.title" cssClass="txtinput"  size="70" maxLength="600"/>
            <@s.fielderror><@s.param>product.title</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">产品图片:</td>
    <td>
    
     <table border="0" cellpadding="0" cellspacing="0">
         <tr>
             <td style="padding:0px;">
             	<div id="fileQueue"></div>
	              <@s.textfield name="product.img_path" id="uploadresult" cssStyle="width:300px;"/>
             </td>
             <td style="padding-left:3px;">
             	<input type="file" name="uploadifyfile" id="uploadifyfile"/>
            </td>
             <td style="padding-left:3px;">
             	<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	             <script>uploadImgControlAndYin(123);uploadOneImgMulti();</script>
             </td>
          </tr>
     </table> 
    <@s.fielderror><@s.param>product.img_path</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr style="display:none;">
     <td valign="middle" class="left_td" id="area">所在地区<font color="red">*</font></td>
         <td>
         	<table>
         		<tr>
         			<td>
         				<div id="arealist"></div>
         			</td>
         			<td >
         				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
              		</td>
              	</tr>
            </table>       
         </td>
   </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类<span class="mustfield">*</span></td>
    <td>
    <#if sysmodule.is_catattr=="0">             
         ${cate_name?if_exists}
         <@s.hidden name="cate_name" />
         <@s.hidden name="product.cat_attr" />
         <a href="/member_Product_cate.action?cat_attr=0">[重新选择]</a>
    <#else>
        <table>
     		<tr>
     			<td class="tdbottom">
     				<div id="divlist" style='margin-left:-10px;'></div>
     			</td>
     			<td class="tdbottom">
     				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
          			<a href="member_Category_list.action?type=product&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
          		</td>
          	</tr>
        </table>       
    </#if> 
    </td>
  </tr>
   <#if sysmodule.is_catattr=="0">      
	<#include "/WEB-INF/template/manager/attr.ftl" />    
   </#if> 
  <tr style="display:none;">
    <td valign="middle" class="left_td">属性:</td>
    <td>
  				<@s.textfield name="product.attr_desc" cssClass="txtinput" cssStyle="width:300px;" />
             	<@s.fielderror><@s.param>product.attr_desc</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">产品型号:</td>
    <td>
  <@s.textfield name="product.model" cssStyle="width:200px;" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>product.model</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">产品规格:</td>
    <td>
   <@s.textfield name="product.standard" cssClass="txtinput" cssStyle="width:200px;" maxLength="50"/>
             	<@s.fielderror><@s.param>product.standard</@s.param></@s.fielderror>
   </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >产品品牌:</td>
    <td>
  <@s.textfield name="product.brand" cssClass="txtinput" cssStyle="width:200px;" maxLength="50"/>
             	<@s.fielderror><@s.param>product.brand</@s.param></@s.fielderror>
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >自定义产品分类:</td>
    <td>
    <@s.select name="product.self_cat_id" list="membercatList" listValue="cat_name" listKey="cat_id" headerKey="0" headerValue="请选择" />
    <@s.fielderror><@s.param>product.self_cat_id</@s.param></@s.fielderror>
    <a href="/member_Membercat_list.action?membertype=0">[分类管理]</a>
    
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td">详细说明<span class="mustfield">*</span></td>
    <td >
     <@s.textarea name="product.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             	<@s.fielderror><@s.param>product.content</@s.param></@s.fielderror>
    </td>  
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="product.clicknum" value="0"/>
           <@s.hidden name="product.fare" value="0"/>
           <@s.hidden name="product.is_recom" value="0"/>
           <@s.hidden name="product.info_state" value="0" />
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
		   
    <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Product_list.action','');"/>
    </td>
  </tr>
</table>

 </div>
 </@s.form>
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
