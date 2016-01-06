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
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:功能模块 > 分类信息管理 > 添加分类信息
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Classified_insert.action" method="post" validate="true" onsubmit="return showPicPath();">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name">分类信息标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.title" cssClass="txtinput" maxLength="600" cssStyle="width:450px"/>
             	<@s.fielderror><@s.param>classified.title</@s.param></@s.fielderror>
             </td>
           </tr>
           
          <tr>
             <td class="table_name" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="classified.cat_attr" />
	                 <a href="/admin_Classified_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=classified&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=classified&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
             <td class="table_name" >所在地区<font color="red">*</font></td>
             <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
           </tr>
           
            <tr>
             <td class="table_name">信息图片上传:</td>
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
             <td class="table_name">补充说明:</td>
             <td>
             	<@s.textarea name="classified.info_desc" cssClass="txtinput" cssStyle="width:450px;height:150px;" id="desc" 
             	onkeyup="set_textarea_length(this,2000);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Classified.info_desc')}"/>">
             </td>
           </tr>
            <tr>
             <td class="table_name">联系人<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.contact" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>classified.contact</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">手机或电话<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="classified.phone" cssClass="txtinput"  maxLength="30"/>
             	<@s.fielderror><@s.param>classified.phone</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">qq或msn:</td>
             <td>
             	<@s.textfield name="classified.qqmsn" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>classified.qqmsn</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">地址:</td>
             <td>
             	<@s.textfield name="classified.address" cssClass="txtinput"  maxLength="200" cssStyle="width:400px"/>
             	<@s.fielderror><@s.param>classified.address</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
             	<@s.radio name="classified.is_recom" list=r"#{'0':'否','1':'是'}" value="0"/>
             </td>
           </tr>
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.textfield name="classified.fare" value="0" onkeyup="checkNum(this);" cssClass="txtinput" style="width:80px;" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>classified.fare</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield name="classified.clicknum" value="0" onkeyup="checkNum(this);" cssClass="txtinput" style="width:80px;" maxLength="10"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>">
             	<@s.fielderror><@s.param>classified.clicknum</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       <@s.hidden name="classified.info_state" value="1"/>
	       ${listSearchHiddenField?if_exists}
	      <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Classified_list.action','');" />
	     </div>
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	  </@s.form>
   </div>
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