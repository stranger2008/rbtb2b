<html>
  <head>
    <title>修改招聘</title>
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
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 人才管理 > 招聘列表 > 修改招聘
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Job_update.action"  method="post" validate="true">
       <table class="wwtable" cellspacing="1" cellpadding="8" >
           <tr>
             <td class="table_name">信息标题<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="job.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>job.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
            <td class="table_name">所属分类<font color='red'> *</font></td>
             <td colspan="3">
             
               <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="job.cat_attr" />
              		<a href="/admin_Job_cate.action?cat_attr=0&job.job_id=${job.job_id?if_exists}">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=job&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=job&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
           <td class="table_name">工作地区<font color='red'> *</font></td>
             <td >
             <div id="arealist"></div>      
              <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td>
            
             <td class="table_name">工作性质:</td>
             <td>
                 <@s.select  name="job.job_type" cssStyle="width:100px;"  list=r"#{'不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}"  />
             </td>
           </tr>
           <tr>
            <td class="table_name">招聘人数<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="job.job_num" cssClass="txtinput" cssStyle="width:100px;" maxLength="10" onkeyup="checkNum(this);"/> 人
             	<@s.fielderror><@s.param>job.job_num</@s.param></@s.fielderror>
             </td>
           <td class="table_name">招聘部门</td>
             <td >
             <@s.textfield name="job.org_name" cssClass="txtinput" cssStyle="width:300px;" maxLength="10"/>
             	<@s.fielderror><@s.param>job.org_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
               <td class="table_name">性别要求:</td>
             <td >
                <@s.select  name="job.sex" cssStyle="width:100px;"  list=r"#{'不限':'不限','男':'男','女':'女'}" />
             </td>
              <td class="table_name">待遇水平<font color='red'> *</font></td>
              <td >
             	 <@s.select name="job.salary" list="commparaList" listValue="para_key" listKey="para_key" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>job.salary</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             
              <td class="table_name">学历要求:</td>
             <td >
               <@s.select  name="job.enducation"  cssStyle="width:100px;" list=r"#{'不限':'不限','初中':'初中','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}"  />
             </td>
              <td class="table_name">婚姻要求:</td>
             <td >
                 <@s.select  name="job.marry" cssStyle="width:100px;"  list=r"#{'不限':'不限','未婚':'未婚','已婚':'已婚'}"  />               
             </td>
           </tr>
           <tr>
             <td class="table_name">工作经验<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="job.workexper" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);"/> 年
             	<@s.fielderror><@s.param>job.workexper</@s.param></@s.fielderror>
             </td>
               <td class="table_name">年龄要求</td>
             <td >  
             	<@s.textfield name="ageStartString" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);"/> 至
             	<@s.textfield name="ageEndString" cssClass="txtinput" cssStyle="width:50px;" maxLength="2" onkeyup="checkNum(this);"/> 周岁
             	<@s.fielderror><@s.param>ageStartString</@s.param ></@s.fielderror >
             	<@s.fielderror><@s.param>ageEndString</@s.param ></@s.fielderror >
             </td>
           
           </tr>
           <tr>
             <td class="table_name">联系地址<font color='red'> *</font></td>
             <td >
                 
             	<@s.textfield name="job.address" cssStyle="width:300px;"  cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>job.address</@s.param></@s.fielderror>
             </td>
              <td class="table_name">过期时间<font color='red'> *</font></td>
             <td >
               <#if job.end_date?if_exists?length lt 10>
               <@s.textfield cssClass="Wdate"   name="job.end_date" onfocus="WdatePicker({readOnly:true})" />
             <#else>
             <@s.textfield cssClass="Wdate" value="${job.end_date?if_exists[0..9]}"  name="job.end_date" onfocus="WdatePicker({readOnly:true})" />         
             </#if>
                    <@s.fielderror><@s.param>job.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
             
            <tr>
             <td class="table_name">职位描述<font color='red'><font color='red'> *</font></font></td>
             <td colspan="3">
             	<@s.textarea name="job.job_desc" cssClass="txtinput" cssStyle="width:600px;height:150px;" id="desc" />
             	<@s.fielderror><@s.param>job.job_desc</@s.param></@s.fielderror>
             </td>
           </tr>       
             <tr>
              <td class="table_name" >联系人<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="job.contact_name" cssClass="txtinput"  cssStyle="width:150px;" maxLength="50"/>
             	<@s.fielderror><@s.param>job.contact_name</@s.param></@s.fielderror>
             </td>
             <td class="table_name"  >电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="job.email" cssStyle="width:300px;" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>job.email</@s.param></@s.fielderror>
             </td>
             
           </tr> 
            <tr>
             <td class="table_name">联系电话</td>
             <td >
             	<@s.textfield name="job.phone" cssClass="txtinput" maxLength="20" cssStyle="width:150px;" />
             	<@s.fielderror><@s.param>job.phone</@s.param></@s.fielderror>
             </td>
            
             <td class="table_name">联系手机:</td>
             <td >
             	<@s.textfield name="job.cellphone" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>job.cellphone</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">MSN:</td>
             <td >
             	<@s.textfield name="job.msn" cssStyle="width:150px;" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>job.msn</@s.param></@s.fielderror>
             </td>
              <td class="table_name">内容收费:</td>
             <td colspan="3">
             	<@s.textfield name="job.fare" cssStyle="width:100px;"  cssClass="txtinput" maxLength="100" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>job.fare</@s.param></@s.fielderror>
             </td>
             
           </tr>
            <tr>
             <td class="table_name">Skype:</td>
             <td >
             	<@s.textfield name="job.skype" cssClass="txtinput"  maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.skype</@s.param></@s.fielderror>
             </td>
              <td class="table_name">是否推荐:</td>
             <td >
                 <@s.radio name="job.is_recom" list=r"#{'0':'否','1':'是'}" />     	
                 <@s.fielderror><@s.param>job.is_recom</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
              <td class="table_name">QQ:</td>
             <td >
             	<@s.textfield name="job.qq" cssClass="txtinput" maxLength="20"  cssStyle="width:150px;"/>
             	<@s.fielderror><@s.param>job.qq</@s.param></@s.fielderror>
             </td>
              <td class="table_name">点击量:</td>
             <td >
             	<@s.textfield name="job.clicknum" cssClass="txtinput" maxLength="20"  cssStyle="width:100px;" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>"> 
             	<@s.fielderror><@s.param>job.clicknum</@s.param></@s.fielderror>
             	
             </td>
           </tr>  
           <tr>
              <td class="table_name">招聘类型:</td>
             <td >
             	 <@s.radio name="job.is_where" list=r"#{'0':'国内招聘','1':'海外招聘'}"/> 
             </td>
              <td class="table_name">是否委托:</td>
             <td >
             	 <@s.radio name="job.is_trust" list=r"#{'0':'否','1':'是'}"/>
             </td>
           </tr>   
            <tr> 
            <tr>
             <td class="table_name">信息状态:</td>
             <td colspan="3">
               <@s.radio name="job.info_state" list=r"#{'1':'正常','3':'禁用'}"  />   
               <@s.fielderror><@s.param>job.info_state</@s.param></@s.fielderror>
             </td>
                   
           </tr>   
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="job.cust_id"/>
	       <@s.hidden name="job.job_id" id="id"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="job.infoattr_id" />
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <input type="button" name="sumit" value="重置" onclick="resetModify('/admin_Job_view.action?job.job_id=');" />
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Job_list.action','');"/>
	     </div>
	     
	  </@s.form>  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>