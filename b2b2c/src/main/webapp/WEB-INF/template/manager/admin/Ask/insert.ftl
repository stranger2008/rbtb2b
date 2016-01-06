<html>
  <head>
    <title>添加知道</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/admin/ask.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<#include "/include/uploadInc.html">
	<script type="text/javascript">
	  $(document).ready(function(){ 
 	     //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"know");  
		 </#if>		
	  });
	</script>
  </head>
  <body>

<div class="tj_main f_left">
  <div class="pageLocation">
 	当前位置:功能模块 > 知道管理 > 添加知道
  </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Ask_insert.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">问题标题<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="ask.title" cssClass="txtinput" rows="5" cssStyle="width:500px;" onkeyup="set_textarea_length(this,600);"/>
             	<@s.fielderror><@s.param>ask.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属分类<font color='red'>*</font></td>
             <td>
             ${sysmodule.is_catatrr}
                <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="buy.cat_attr" />
	                 <a href="/admin_Ask_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=know&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=know&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
             <td class="table_name">标题图片:</td>
             <td>           
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="ask.img_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             			<td>
             			  &nbsp;&nbsp;<@s.fielderror><@s.param>ask.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
            <tr>
             <td class="table_name">问题说明:</td>
             <td>
                <@s.textarea name="ask.ask_desc" id="ask_desc" cssClass="txtinput" cssStyle="width:435px;height:90px;" />
                <@s.fielderror><@s.param>ask.ask_desc</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">补充信息:</td>
             <td>
             	<@s.textarea name="ask.add_desc" cssClass="txtinput" rows="3" cssStyle="width:500px;"  onkeyup="set_textarea_length(this,300);"/>
             	<@s.fielderror><@s.param>ask.add_desc</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">悬赏积分:</td>
             <td>
             	<@s.select name="ask.integral" list="integralList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>ask.integral</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
                <@s.radio name="ask.is_recom" list=r"#{'0':'否','1':'是'}" value="0"/>
                <@s.fielderror><@s.param>ask.is_recom</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">信息状态:</td>
             <td>
             	<@s.radio name="ask.info_state" list=r"#{'0':'未审核','1':'审核通过','2':'审核未通过','3':'禁用'}" value="1"/>
             	<@s.fielderror><@s.param>ask.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">回答状态:</td>
             <td>
             	<@s.radio name="ask.answer_state" list=r"#{'0':'未解决','1':'已解决','2':'已关闭'}" value="0"/>
             	<@s.fielderror><@s.param>ask.answer_state</@s.param></@s.fielderror>
             </td>
           </tr>  
            <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield name="ask.clicknum" cssClass="txtinput" onkeyup="checkNum(this);" maxLength="11" value="0" onkeyup="checkNum(this);" />
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.click')}"/>"> 
             	<@s.fielderror><@s.param>ask.clicknum</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.textfield name="ask.fare" cssClass="txtinput" onkeyup="checkNum(this);" maxLength="11" value="0" onkeyup="checkNum(this);" />
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>ask.fare</@s.param></@s.fielderror>
             </td>
           </tr> 
            
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="name_s"/> 
	       
	       ${listSearchHiddenField?if_exists}     
	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Ask_list.action','');"/>
	     </div>
	     
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