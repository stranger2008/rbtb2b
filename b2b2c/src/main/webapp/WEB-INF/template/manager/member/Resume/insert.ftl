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
 <div class="cont_main">
 <@s.form action="/member_Resume_insert.action" method="post" validate="true">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>简历管理>添加简历</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">简历名称<span class="mustfield">*</span></td>
    <td width="83%">
  	<@s.textfield name="resume.resume_name" cssClass="txtinput" cssStyle="width:350px;" maxLength="600"/>
             	<@s.fielderror><@s.param>resume.resume_name</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类<font class="mustfield"> *</font></td>
    <td>
  <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="resume.cat_attr" />
	                 <a href="/member_Resume_cate.action?cat_attr=0">[重新选择]</a> 
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
    <td valign="middle" class="left_td">真实姓名<font class="mustfield"> *</font></td>
    <td>
      <@s.textfield name="resume.real_name" cssClass="txtinput" maxLength="100" csssStyle="width:100px;"/>
             	<@s.fielderror><@s.param>resume.real_name</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">照片:</td>
    <td>
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
    <td valign="middle" class="left_td">性别:</td>
    <td>
    <@s.select  name="resume.sex" cssStyle="width:100px;" list=r"#{'男':'男','女':'女'}" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">婚姻状况:</td>
    <td>
   <@s.select  name="resume.marry" cssStyle="width:100px;" list=r"#{'未婚':'未婚','已婚':'已婚'}" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">现居住地<font class="mustfield"> *</font></td>
    <td>
      <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">生日<font class="mustfield"> *</font></td>
    <td>
    <@s.textfield  id="txbirthDate" name="resume.birth"   cssClass="Wdate"   onfocus="WdatePicker({readOnly:true})"  />
     <@s.fielderror><@s.param>resume.birth</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">身高:</td>
    <td>
      <@s.textfield name="resume.height" cssClass="txtinput" cssStyle="width:100px;" maxLength="10" onkeyup="checkNum(this);"/> (cm)
             	<@s.fielderror><@s.param>resume.height</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">体重:</td>
    <td>
    <@s.textfield name="resume.weight" cssClass="txtinput" cssStyle="width:100px;" maxLength="10" onkeyup="checkNum(this);"/> (kg)
             	<@s.fielderror><@s.param>resume.weight</@s.param></@s.fielderror>  
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">学历:</td>
    <td>
  <@s.select  name="resume.education"  cssStyle="width:100px;" list=r"#{'不限':'不限','初中':'初中','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}"  />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >毕业院校:</td>
    <td>
   	  <@s.textfield name="resume.college" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
             	<@s.fielderror><@s.param>resume.college</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >所学专业:</td>
    <td>
 <@s.textfield name="resume.spec" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
             	<@s.fielderror><@s.param>resume.spec</@s.param></@s.fielderror>     
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >期望月薪<font class="mustfield"> *</font></td>
    <td>
  <@s.select name="resume.salary" list="commparaList" listValue="para_key" listKey="para_key" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>resume.salary</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作性质:</td>
    <td>
 <@s.select  name="resume.job_type" cssStyle="width:100px;"  list=r"#{'不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}"  />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作经验<font class="mustfield"> *</font></td>
    <td>
   <@s.textfield name="resume.work_exper" cssStyle="width:100px;"  cssClass="txtinput" maxLength="10" onkeyup="checkNum(this);"/> 年
             	<@s.fielderror><@s.param>resume.work_exper</@s.param></@s.fielderror>
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >专业技能:</td>
    <td>
   <@s.textfield name="resume.technical" cssClass="txtinput" maxLength="50" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.technical</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >语言水平:</td>
    <td>
   <@s.textfield name="resume.language" cssClass="txtinput"  maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.language</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >自我鉴定<font class="mustfield"> *</font></td>
    <td>
	  <@s.textarea name="resume.self_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="resume_desc" />
      <@s.fielderror><@s.param>resume.self_desc</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系手机<font class="mustfield"> *</font></td>
    <td>
   <@s.textfield name="resume.cellphone" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.cellphone</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系电话:</td>
    <td>
    <@s.textfield name="resume.phone" cssStyle="width:100px;" maxLength="20"  cssClass="txtinput" maxLength="150px" />
             	<@s.fielderror><@s.param>resume.phone</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >电子邮箱<font class="mustfield"> *</font></td>
    <td>
     <@s.textfield name="resume.email" cssStyle="width:150px;" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>resume.email</@s.param></@s.fielderror> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系地址:</td>
    <td>
   <@s.textfield name="resume.address" cssClass="txtinput" maxLength="100"  cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>resume.address</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >MSN:</td>
    <td>
    	<@s.textfield name="resume.msn" cssClass="txtinput" maxLength="60"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>resume.msn</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >QQ:</td>
    <td>
    	<@s.textfield name="resume.qq" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.qq</@s.param></@s.fielderror>  	
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >Skype:</td>
    <td>
    	 	<@s.textfield name="resume.skype" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>resume.skype</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td" >是否委托:</td>
    <td>
    <@s.radio name="resume.is_trust" cssClass="txtinput" list=r"#{'0':'否','1':'是'}" value="0"/> 
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="resume.info_state" value="0"/>
	       <@s.hidden name="resume.is_recom" value="0"/>
	       <@s.hidden name="resume.fare" value="0"/>
	       <@s.hidden name="resume.clicknum" value="0"/>
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
		   <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Resume_list.action','');"/>
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
