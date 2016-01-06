<html>
<head>
<title>搜索简历</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
 	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
 	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //供应分类加载第一级菜单,第一个参数为父级ID,第二个等级参数,第三个参数为所属模块参数值,到最后一级无分类后，又有属性，加载显示分类属性
		 cate_select("${cfg_topcatid?if_exists}",1,"resume");
		  //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");	
	  });
	</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Resume_resumeSearch.action" method="post" validate="true">
      <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>简历管理>搜索简历</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td valign="middle" class="left_td">所属分类</td>
    <td>
    <div id="divlist"></div>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">性别:</td>
    <td>
    <@s.select  name="sex_s" cssStyle="width:100px;" list=r"#{'请选择':'请选择','男':'男','女':'女'}" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">婚姻状况:</td>
    <td>
   <@s.select  name="marry_s" cssStyle="width:100px;" list=r"#{'请选择':'请选择','未婚':'未婚','已婚':'已婚'}" />
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">现居住地</td>
    <td>
       <div id="arealist"></div>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">学历:</td>
    <td>
      <@s.select  name="education_s"  cssStyle="width:100px;" list=r"#{'请选择':'请选择','不限':'不限','初中':'初中','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}"  />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >毕业院校:</td>
    <td>
   	  <@s.textfield name="college_s" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >所学专业:</td>
    <td>
 <@s.textfield name="spec_s" cssClass="txtinput" cssStyle="width:300px;" maxLength="20"/>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作性质:</td>
    <td>
 <@s.select  name="job_type_s" cssStyle="width:100px;"  list=r"#{'请选择':'请选择','不限':'不限','全职':'全职','兼职':'兼职','实习':'实习'}"  />
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >工作经验:</td>
    <td>
   <@s.textfield name="work_exper_s" cssStyle="width:100px;"  cssClass="txtinput" maxLength="2" onkeyup="checkNum(this);"/> 年
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >专业技能:</td>
    <td>
   <@s.textfield name="technical_s" cssClass="txtinput" maxLength="50" cssStyle="width:300px;"/>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td" >语言水平:</td>
    <td>
   <@s.textfield name="language_s" cssClass="txtinput"  maxLength="100"  cssStyle="width:300px;"/>
    </td>
  </tr>
  <tr>
		<td valign="middle" class="left_td" >发布时间:</td>
		<td>
		<@s.textfield id="in_date_s" name="in_date_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'end_date_s\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="end_date_s" name="end_date_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'in_date_s\\',{d:1})}',readOnly:true})"  />
         </td>
    </tr>
  <tr>
    <td colspan="4" class="subbut">
 	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="resume.info_state" value="0"/>
           <@s.submit value="搜索" name="search"  cssClass="sub" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>
    </td>
  </tr>
</table>

 </div>
<@s.hidden id="search_area_attr" name="area_attr_s"/>
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->

</body>
</html>
