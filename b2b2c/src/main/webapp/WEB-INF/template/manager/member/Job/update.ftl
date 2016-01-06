<html>
<head>
<title>修改招聘信息</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js" ></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){      
	  	 //所属分类的回选
        <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"job");  
		 </#if>        
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>     
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Job_update.action" method="post" validate="true">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>招聘管理>修改招聘</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr <#if job.info_state?if_exists='2'><#else>style='display:none;'</#if> >
    <td width="17%" valign="middle" class="left_td" style="font-weight:bold;">未通过理由：</td>
    <td width="83%">
   ${job.no_reason?if_exists}
    </td>
  </tr>
  <tr>
    <td width="17%" valign="middle" class="left_td">信息标题<span class="mustfield">*</span></td>
    <td width="83%">
  <@s.textfield name="job.title" cssClass="txtinput" cssStyle="width:450px;" maxLength="600"/>
             	<@s.fielderror><@s.param>job.title</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">工作地区<span class="mustfield">*</span></td>
    <td>
      <div id="arealist"></div>      
              <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类<span class="mustfield">*</span></td>
    <td>
       <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="job.cat_attr" />
              		<a href="/member_Job_cate.action?cat_attr=0&job.job_id=${job.job_id?if_exists}">[重新选择]</a>
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
    <td valign="middle" class="left_td">工作性质:</td>
    <td>
    <@s.select  name="job.job_type" cssStyle="width:100px;" list=r"#{'不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}"  />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">招聘人数<span class="mustfield">*</span></td>
    <td>
   <@s.textfield name="job.job_num" cssClass="txtinput" cssStyle="width:100px;" maxLength="10" onkeyup="checkNum(this);"/> 人
             	<@s.fielderror><@s.param>job.job_num</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">招聘部门</td>
    <td>
     <@s.textfield name="job.org_name" cssClass="txtinput" cssStyle="width:300px;" maxLength="10"/>
             	<@s.fielderror><@s.param>job.org_name</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">性别要求</td>
    <td>
   <@s.select  name="job.sex" cssStyle="width:100px;" list=r"#{'不限':'不限','男':'男','女':'女'}" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">待遇水平<span class="mustfield">*</span></td>
    <td>
   	<@s.select name="job.salary" list="commparaList" listValue="para_key" listKey="para_key" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>job.salary</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">学历要求:</td>
    <td>
     <@s.select  name="job.enducation" cssStyle="width:100px;"  list=r"#{'不限':'不限','初中':'初中','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}"  />
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">婚姻要求:</td>
    <td>
    <@s.select  name="job.marry" cssStyle="width:100px;"  list=r"#{'不限':'不限','未婚':'未婚','已婚':'已婚'}"  /> 
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">工作经验<span class="mustfield">*</span></td>
    <td>
   <@s.textfield name="job.workexper" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);"/> 年
             	<@s.fielderror><@s.param>job.workexper</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >年龄要求</td>
    <td>
   	<@s.textfield name="ageStartString" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);"/> 至
             	<@s.textfield name="ageEndString" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);" /> 周岁
             	<@s.fielderror><@s.param>ageStartString</@s.param ></@s.fielderror >
             	<@s.fielderror><@s.param>ageEndString</@s.param ></@s.fielderror >
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >过期时间<span class="mustfield">*</span></td>
    <td>
               <#if job.end_date?if_exists?length lt 10>
               <@s.textfield cssClass="Wdate"   name="job.end_date" onfocus="WdatePicker({readOnly:true})" />
             <#else>
             <@s.textfield cssClass="Wdate" value="${job.end_date?if_exists[0..9]}"  name="job.end_date" onfocus="WdatePicker({readOnly:true})" />         
             </#if>
    
       
        <@s.fielderror><@s.param>job.end_date</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系地址<span class="mustfield">*</span></td>
    <td>
 <@s.textfield name="job.address" cssStyle="width:300px;"  cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>job.address</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >职位描述<span class="mustfield">*</span></td>
    <td>
      <@s.textarea name="job.job_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="job_desc" />
      <@s.fielderror><@s.param>job.job_desc</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系人<span class="mustfield">*</span></td>
    <td>
    <@s.textfield name="job.contact_name" cssClass="txtinput"  cssStyle="width:150px;" maxLength="50"/>
             	<@s.fielderror><@s.param>job.contact_name</@s.param></@s.fielderror>
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >电子邮箱<span class="mustfield">*</span></td>
    <td>
   <@s.textfield name="job.email" cssStyle="width:300px;" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>job.email</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系电话</td>
    <td>
    <@s.textfield name="job.phone" cssClass="txtinput" maxLength="20" cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.phone</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >联系手机:</td>
    <td>
    <@s.textfield name="job.cellphone" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.cellphone</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >MSN:</td>
    <td>
   <@s.textfield name="job.msn" cssStyle="width:150px;" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>job.msn</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >Skype:</td>
    <td>
    <@s.textfield name="job.skype" cssClass="txtinput"  maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.skype</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >QQ:</td>
    <td>
    <@s.textfield name="job.qq" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.qq</@s.param></@s.fielderror>
    </td>
  </tr>
  
   <tr>
    <td valign="middle" class="left_td" >招聘类型:</td>
    <td>
    <@s.radio name="job.is_where" cssClass="txtinput" list=r"#{'0':'国内招聘','1':'海外招聘'}"/> 
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >是否委托:</td>
    <td>
    <@s.radio name="job.is_trust" cssClass="txtinput" list=r"#{'0':'否','1':'是'}"/> 
    </td>
  </tr>

  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="job.clicknum" />
           <@s.hidden name="job.is_recom" />
           <@s.hidden name="job.fare" />
           <@s.hidden name="job.job_id" />
           <@s.hidden name="job.cust_id" />
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="job.info_state" value="0" />
	        <@s.hidden name="job.infoattr_id" />
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
	 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Job_list.action','');"/>
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
