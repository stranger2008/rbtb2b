<html>
  <head>
    <title>添加简历</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
 	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
	      <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"resume");  
		 </#if>          
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 添加简历
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Resume_insert.action" method="post" validate="true">      
        <table class="wwtable" cellspacing="1" cellpadding="8">           
           <tr>
             <td class="table_name">简历名称<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="resume.resume_name" cssClass="txtinput" cssStyle="width:450px;" maxLength="600"/>
             	<@s.fielderror><@s.param>resume.resume_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
           <td class="table_name">所属分类<font color='red'> *</font></td>
             <td colspan="3">
              <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="resume.cat_attr" />
	                 <a href="/admin_Resume_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=resume&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=resume&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
           <td class="table_name">真实姓名<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="resume.real_name" cssClass="txtinput" maxLength="10" csssStyle="width:100px;"/>
             	<@s.fielderror><@s.param>resume.real_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">照片:</td>
             <td colspan="3" >
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="resume.img_path" id="uploadresult" cssStyle="width:300px;"/>
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
              <@s.fielderror><@s.param>resume.img_path</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td class="table_name">性别:</td>
             <td >
             	 <@s.select  name="resume.sex" cssStyle="width:100px;" list=r"#{'男':'男','女':'女'}" />
             </td>
           <td class="table_name">婚姻状况:</td>
             <td >
              <@s.select  name="resume.marry" cssStyle="width:100px;" list=r"#{'未婚':'未婚','已婚':'已婚'}" />
             </td>
           </tr>
            <tr>
               <td class="table_name">现居住地<font color='red'> *</font></td>
             <td >
               <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
                
             </td>
              <td class="table_name">生日<font color='red'> *</font></td>
              <td >
              <@s.textfield  id="txtendDate" name="resume.birth" cssClass="Wdate" onfocus="WdatePicker({readOnly:true})" />
              <@s.fielderror><@s.param>resume.birth</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
              <td class="table_name">身高:</td>
             <td >
              <@s.textfield name="resume.height" cssClass="txtinput" cssStyle="width:50px;" maxLength="3" onkeyup="checkNum(this);"/> (cm)
             	<@s.fielderror><@s.param>resume.height</@s.param></@s.fielderror>
                
             </td>
              <td class="table_name">体重:</td>
             <td >
                  <@s.textfield name="resume.weight" cssClass="txtinput" cssStyle="width:50px;" maxLength="3" onkeyup="checkNum(this);"/> (kg)
             	<@s.fielderror><@s.param>resume.weight</@s.param></@s.fielderror>          
             </td>
           </tr>
           <tr>
             <td class="table_name">学历:</td>
             <td >
             	 <@s.select  name="resume.education"  cssStyle="width:100px;" list=r"#{'不限':'不限','初中':'初中','高中':'高中','中专':'中专','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}"  />
             </td>
               <td class="table_name">毕业院校:</td>
             <td >  
             	  <@s.textfield name="resume.college" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
             	<@s.fielderror><@s.param>resume.college</@s.param></@s.fielderror>   
             </td>
           
           </tr>
         <tr>
             <td class="table_name"  >所学专业:</td>
             <td >
              	  <@s.textfield name="resume.spec" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
             	<@s.fielderror><@s.param>resume.spec</@s.param></@s.fielderror>     
             </td>
              <td class="table_name"  >期望月薪<font color='red'> *</font></td>
             <td >
                 <@s.select name="resume.salary" list="commparaList" listValue="para_key" listKey="para_key" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>resume.salary</@s.param></@s.fielderror>
                  
             </td>
           </tr>   
             <tr>
              <td class="table_name" >工作性质:</td>
             <td >
             	<@s.select  name="resume.job_type" cssStyle="width:100px;"  list=r"#{'不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}"  />
             </td>
             <td class="table_name"  >工作经验<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="resume.work_exper" cssStyle="width:50px;"  cssClass="txtinput" maxLength="2" onkeyup="checkNum(this);"/> 年
             	<@s.fielderror><@s.param>resume.work_exper</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
              <td class="table_name">专业技能:</td>
             <td >
             	<@s.textfield name="resume.technical" cssClass="txtinput" maxLength="50" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.technical</@s.param></@s.fielderror>
             </td>
              <td class="table_name">语言水平:</td>
             <td >
             	<@s.textfield name="resume.language" cssClass="txtinput"  maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.language</@s.param></@s.fielderror>
             </td>
           </tr> 
            
             <tr>
             <td class="table_name"  >自我鉴定<font color='red'> *</font></td>
             <td colspan="3">
                <@s.textarea name="resume.self_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="desc" />
             	<@s.fielderror><@s.param>resume.self_desc</@s.param></@s.fielderror>
             </td>
             </tr>
            <tr>
             <td class="table_name">联系手机<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="resume.cellphone" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.cellphone</@s.param></@s.fielderror>
             </td>
              <td class="table_name"  >联系电话:</td>
             <td colspan="3">
             	<@s.textfield name="resume.phone" cssStyle="width:100px;"  cssClass="txtinput" maxLength="100" />
             	<@s.fielderror><@s.param>resume.phone</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name"  >电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="resume.email" cssStyle="width:150px;" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>resume.email</@s.param></@s.fielderror>
             </td>
             <td class="table_name">联系地址:</td>
             <td >
             	<@s.textfield name="resume.address" cssClass="txtinput" maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.address</@s.param></@s.fielderror>
             </td>          
           </tr>
            <tr>
              <td class="table_name">MSN:</td>
             <td >
             	<@s.textfield name="resume.msn" cssClass="txtinput" maxLength="60"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>resume.msn</@s.param></@s.fielderror>
             </td>
           
              <td class="table_name">QQ:</td>
             <td >
               <@s.textfield name="resume.qq" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.qq</@s.param></@s.fielderror>  	
             </td>
           </tr>
            <tr>
              <td class="table_name" >Skype:</td>
             <td >
                	<@s.textfield name="resume.skype" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.skype</@s.param></@s.fielderror>	
             	
             </td>
                 <td class="table_name">是否推荐:</td>
             <td >
              <@s.radio name="resume.is_recom" list=r"#{'0':'否','1':'是'}" value="0" />   
              <@s.fielderror><@s.param>resume.is_recom</@s.param></@s.fielderror>  	  	
             </td>
           </tr>
            <tr>
              <td class="table_name">点击量:</td>
             <td >
             	<@s.textfield name="resume.clicknum" cssClass="txtinput" maxLength="20"  value="0" cssStyle="width:100px;" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.click')}"/>"> 
             	<@s.fielderror><@s.param>resume.clicknum</@s.param></@s.fielderror>
             </td>
           
              <td class="table_name">内容收费:</td>
             <td >
                <@s.textfield name="resume.fare" cssClass="txtinput" maxLength="20" value="0"  cssStyle="width:150px;" onkeyup="checkNum(this);"/>
                <img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>resume.fare</@s.param></@s.fielderror>  	
             </td>
           </tr> 
           <tr>
              <td class="table_name">信息状态:</td>
             <td>
                 <@s.radio name="resume.info_state" list=r"#{'0':'未审核','1':'审核通过','2':'审核未通过','3':'禁用'}" value="1"  />  
                 <@s.fielderror><@s.param>resume.info_state</@s.param></@s.fielderror>    	
             </td>
             <td class="table_name">是否委托:</td>
             <td >
             	 <@s.radio name="resume.is_trust" list=r"#{'0':'否','1':'是'}" value="0"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
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
		   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Resume_list.action','');"/>
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