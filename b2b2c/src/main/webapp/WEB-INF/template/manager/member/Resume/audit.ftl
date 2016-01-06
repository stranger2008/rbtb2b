<html>
<head>
<title>查看简历</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Resume_update.action" method="post" validate="true">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>简历管理>查看简历信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
    <tr>
    <td width="17%" valign="middle" class="left_td">简历名称:</td>
    <td width="83%">
  	 <@s.label name="resume.resume_name" /> 
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类:</td>
    <td>
     <@s.label name="resume.cat_attr" /> 
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">真实姓名:</td>
    <td>
      <@s.label name="resume.real_name" /> 
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">照片:</td>
    <td>
    <img src="${resume.img_path?if_exists}" width="100px" height="100px" />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">性别:</td>
    <td>
     <@s.label name="resume.sex" /> 	
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">婚姻状况:</td>
    <td>
     <@s.label name="resume.marry" /> 
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">现居住地:</td>
    <td>
     <@s.label name="resume.area_attr" /> 
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">生日:</td>
    <td>
    <@s.label name="resume.birth.substring(0,10)"  /> 
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">身高：</td>
    <td>
      <@s.label name="resume.height" /> (CM)
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">体重：</td>
    <td>
     <@s.label name="resume.weight" /> (KG) 
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">学历:</td>
    <td>
    <@s.label name="resume.education" />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >毕业院校：</td>
    <td>
   	   <@s.label name="resume.college" /> 
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >所学专业:</td>
    <td>
 <@s.label name="resume.spec" />     
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >期望月薪:</td>
    <td>
   <@s.label name="resume.salary" /> 元/月
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作性质:</td>
    <td>
    <@s.label name="resume.job_type" />  
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作经验:</td>
    <td>
   <@s.label name="resume.work_exper" />
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >专业技能:</td>
    <td>
   <@s.label name="resume.technical" /> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >语言水平:</td>
    <td>
   <@s.label name="resume.language" /> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >自我鉴定:</td>
    <td>
    <div style="padding:10px 200px 10px 0">
                 ${resume.self_desc?if_exists}
             	 </div>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系手机:</td>
    <td>
   <@s.label name="resume.cellphone" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系电话:</td>
    <td>
    <@s.label name="resume.phone" /> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >电子邮箱:</td>
    <td>
    <@s.label name="resume.email" />  
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系地址:</td>
    <td>
    <@s.label name="resume.address" /> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >MSN:</td>
    <td>
    	<@s.label name="resume.msn" />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >QQ:</td>
    <td>
    	<@s.label name="resume.qq" /> 
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >Skype:</td>
    <td>
 <@s.label name="resume.skype" /> 
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >是否委托:</td>
    <td>
    <#if resume.is_trust=='0'>否<#else>是</#if>
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="resume.resume_id" />
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="resume.info_state" />
          <#if flageResume=="1">
              <#if memtype?if_exists=='0'>
	            <input type="button" name="returnList" class="sub" value="返回列表" onclick="document.forms[0].action='/member_Resumeinbox_list.action';document.forms[0].submit();"/>
	          <#else>
	            <input type="button" name="returnList" class="sub" value="返回列表" onclick="document.forms[0].action='/member_Resumed_list.action';document.forms[0].submit();"/>
	          </#if>
	       <#elseif flageResume=="2">
	       <input type="button" name="returnList" class="sub" value="返回列表" onclick="document.forms[0].action='/member_Jobtalent_list.action';document.forms[0].submit();"/>
	       </#if>
    </td>
  </tr>
</table>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
