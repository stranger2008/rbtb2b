<html>
<head>
<title>查看招聘信息</title>
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>招聘管理>查看招聘信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">信息标题:</td>
    <td width="83%">
  <@s.label name="job.title" />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">工作地区:</td>
    <td>
      <@s.label name="job.area_attr" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类:</td>
    <td>
      <@s.label name="job.cat_attr" />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">工作性质:</td>
    <td>
    <@s.label name="job.job_type" />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">招聘人数:</td>
    <td>
   <@s.label name="job.job_num" /> 人
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">招聘部门:</td>
    <td>
     <@s.label name="job.org_name" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">性别要求:</td>
    <td>
    <@s.label name="job.sex" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">待遇水平:</td>
    <td>
    <@s.label name="job.salary" /> 元/月
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">学历要求:</td>
    <td>
     <@s.label name="job.enducation" />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">婚姻要求:</td>
    <td>
      <@s.label name="job.marry" />      
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">工作经验:</td>
    <td>
    <@s.label name="job.workexper" /> 年
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >年龄要求:</td>
    <td>
   	 <@s.label name="ageStartString" /> 至
     <@s.label name="ageEndString" /> 周岁
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >过期时间:</td>
    <td>
 <@s.label name="job.end_date" /> 
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系地址:</td>
    <td>
 <@s.label name="job.address" />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >职位描述:</td>
    <td>
    <div style="padding:10px 200px 10px 0">
                 ${job.job_desc?if_exists}
             	 </div>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系人:</td>
    <td>
    <@s.label name="job.contact_name" />
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >电子邮箱:</td>
    <td>
    <@s.label name="job.email" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系电话:</td>
    <td>
    <@s.label name="job.phone" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系手机:</td>
    <td>
    <@s.label name="job.cellphone" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >MSN:</td>
    <td>
   <@s.label name="job.msn" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >Skype:</td>
    <td>
   <@s.label name="job.msn" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >QQ:</td>
    <td>
    <@s.label name="job.qq" />
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >点击量:</td>
    <td>
    	 <@s.label name="job.clicknum" />      	
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >招聘类型:</td>
    <td>
    <#if job.is_where=='0'>国有招聘<#else>海外招聘</#if>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >是否委托:</td>
    <td>
    	 <#if job.is_trust=='0'>否<#else>是</#if>
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="job.job_id" />
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="job.info_state" />
	<#if memtype?if_exists=='0'>
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="document.forms[0].action='/member_Resumeinbox_list.action';document.forms[0].submit();"/>
	 <#else>
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="document.forms[0].action='/member_Resumed_list.action';document.forms[0].submit();"/>
	 </#if>
    </td>
  </tr>
</table>
 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->

</body>
</html>
