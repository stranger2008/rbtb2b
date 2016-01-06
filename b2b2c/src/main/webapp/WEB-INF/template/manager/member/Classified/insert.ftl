<html>
  <head>
    <title>添加分类信息</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
    
	<script type="text/javascript">
	  $(document).ready(function(){ 
        //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
		 area_select("${cfg_topareaid?if_exists}");
		 <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"classified");  
		 </#if>
	  });
	</script>
  </head>
  <body>
<div class="cont_main">
   	<@s.form action="/member_Classified_insert.action" method="post" validate="true" onsubmit="return showPicPath();">
   	  <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>分类信息管理>添加分类信息</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="17%" valign="middle" class="left_td">分类信息标题<span class="mustfield">*</span></td>
              <td width="83%">
             	<@s.textfield name="classified.title" cssClass="txtinput" cssStyle="width:450px" maxLength="600"/>
             	<@s.fielderror><@s.param>classified.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="classified.cat_attr" />
	                 <a href="/member_Classified_cate.action?cat_attr=0">[重新选择]</a>              		  
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
             </td>
           </tr>          
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>     

           <tr>
             <td valign="middle" class="left_td">所在地区<font color="red">*</font></td>
             <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">信息图片上传:</td>
             <td>
             
                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			 <div id="fileQueue"></div>
	              		<@s.textfield name="classified.img_path" id="uploadresult"  cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile" />
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImgMulti();</script>
             			</td>
             		</tr>
             	</table>
		       	<@s.fielderror><@s.param>classified.img_path</@s.param></@s.fielderror>            	            
	        </td>
	       </tr>  
            <tr>
             <td valign="middle" class="left_td">补充说明:</td>
             <td>
             	<@s.textarea name="classified.info_desc" cssClass="txtinput" cssStyle="width:450px;height:150px;" id="desc" 
             	onkeyup="set_textarea_length(this,2000);"/>
             	内容应在0~2000个字之间
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">联系人<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.contact" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>classified.contact</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">手机或电话<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.phone" cssClass="txtinput"  maxLength="30"/>
             	<@s.fielderror><@s.param>classified.phone</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">qq或msn:</td>
             <td>
             	<@s.textfield name="classified.qqmsn" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>classified.qqmsn</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">地址:</td>
             <td>
             	<@s.textfield name="classified.address" cssClass="txtinput"  maxLength="200" cssStyle="width:400px"/>
             	<@s.fielderror><@s.param>classified.address</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
           <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="classified.is_recom" value="0"/>
           <@s.hidden name="classified.fare" value="0"/>
           <@s.hidden name="classified.clicknum" value="0"/>
	       
	       ${listSearchHiddenField?if_exists}
	         <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssStyle="sub"/>
           </#if>
	       <input type="button" name="returnList" value="返回列表" class="sub" onclick="linkToInfo('/member_Classified_list.action','');" />
           </td>
           </tr>
         </table>
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->

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