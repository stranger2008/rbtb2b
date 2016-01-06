<html>
<head>
<title>公司信息</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
    <script type="text/javascript" src="/include/js/admin/member.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         //所属分类折回选
         cate_select(${cfg_topcatid?if_exists},1,"company");          
    	 $("#com_insert").submit(function(){
              var cat_attr_str="";
              $("input:hidden[name='all_area_id_str']").each(function(){
                  cat_attr_str+=$(this).val()+"|";
              }) 
              $("#cat_attr_str").val(cat_attr_str);        
              return true;
         }); 
         //注册币种回显
         var typevalue = $("#moneytype option:selected").val();
         if(typevalue == "othertype"){
             $("#othertype").css("display","");
         }                
	  });
	  
	   //当选择币种为其他时显示输入框
	 function addMoneytype(){
	     	 $("select[id^=moneytype]").each(function(){	
             if($(this).val()=="othertype"){ 
                 $("#othertype").css("display","");
			}else{
			     $("#othertype").css("display","none");
			}
        });
	  
	  } 
	  	
	</script>

</head>
<body>
 <div class="cont_main">
 <@s.form  id="com_insert"  action="/member_Corpomember_update.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>会员资料>公司信息</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="15%" valign="middle" class="left_td">企业名称<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="member.cust_name" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	<@s.fielderror><@s.param>member.cust_name</@s.param></@s.fielderror>
             	<@s.hidden  name="oldinfotitle" value="${member.cust_name?if_exists}"/>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">企业LOGO:</td>
             <td colspan="3">
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="member.logo_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>member.logo_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table> 
             
             
             </td>
           </tr>
            <tr style="display:none;">
              <td valign="middle" class="left_td" >联系人姓名<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="member.contact_name" cssClass="txtinput"  maxLength="50" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_name</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">联系人性别:</td>
             <td>
             	<@s.select name="member.contact_sex" list=r"#{'先生':'先生','女士':'女士'}" />
             	<@s.fielderror><@s.param>member.contact_sex</@s.param></@s.fielderror>    
             </td>  
           </tr>
            <tr style="display:none;">
             <td valign="middle" class="left_td">联系手机<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="member.contact_cellphone" cssClass="txtinput" maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_cellphone</@s.param></@s.fielderror>
             </td>
              <td valign="middle" class="left_td">联系人MSN:</td>
             <td>
             	<@s.textfield name="member.contact_msn" cssClass="txtinput" maxLength="100" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_msn</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr style="display:none;">
             <td valign="middle" class="left_td">联系人QQ:</td>
             <td>
             	<@s.textfield name="member.contact_qq" cssClass="txtinput"  maxLength="100"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_qq</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">电子邮箱<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="member.email" cssClass="txtinput" maxLength="100"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.email</@s.param></@s.fielderror>
             	<@s.hidden  name="oldemail" value="${(member.email)?if_exists}"/>
             </td>
           </tr>
           <tr style="display:none;">
             <td valign="middle" class="left_td">邮政编码:</td>
             <td colspan="3">
             	<@s.textfield name="member.post_code" cssClass="txtinput"  maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.post_code</@s.param></@s.fielderror>
             </td>
          </tr>
          <tr style="display:none;">
             <td valign="middle" class="left_td">联系人部门:</td>
             <td>
             	<@s.textfield name="member.contact_job" cssClass="txtinput"  maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_job</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">联系人职位:</td>
             <td>
             	<@s.textfield name="member.contact_depart" cssClass="txtinput" maxLength="50"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.contact_depart</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">所在地区<font color='red'> *</font></td>
             <td colspan="3">
             	<div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>   
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">所属分类<font color='red'> *</font></td>
             <td colspan="3">
              <input type="hidden" id="cat_attr_str" name="cat_attr_str" value="${cat_attr_str?if_exists}">
             	<div >
             		 <div id="show_add_cat">
	                     <#list cat_attr_list as cat>
	                          <div style='line-height:20px;'>
		                          <input type='hidden' name='all_area_id_str' value="${cat.id?if_exists} "/>${cat.val?if_exists} 
		                          <a class='oper' href='###' onclick='del_add_area(this)'>[删除]</a>
	                          </div>
	                     </#list>
	                 </div>
	                 <table>
	                 	<tr><td  colspan="2"><div id="divlist" style="margin-left:-10px;"></div></td>
		                 	<td><a class="oper_add" href="###" onclick="com_add_cat()"><input type="button" value="添加分类"></a>
		                 		<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror> 
		                    </td>
	                    </tr>
	                 </table>	                
		         </div>
                <@s.fielderror><@s.param>class_attr</@s.param></@s.fielderror>
             </td>
           </tr>  
            <tr>
             <td valign="middle" class="left_td">企业类别:</td>
             <td colspan="3">
                <@s.radio name="member.cust_rage" list=r"#{'0':'供应商','1':'采购商','2':'二者皆有'}"/>
                <@s.fielderror><@s.param>member.cust_rage</@s.param></@s.fielderror> 
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">供应产品:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_supply" cssClass="txtinput"  cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>member.cust_supply</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">采购产品:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_stock" cssClass="txtinput" cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>member.cust_stock</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">企业类型<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.select name="member.cust_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
                <@s.fielderror><@s.param>member.cust_type</@s.param></@s.fielderror>
             </td>  
           </tr>
            <tr>
             <td valign="middle" class="left_td">经营模式<font color='red'> *</font></td>
             <td colspan="3">
                <@s.checkboxlist id="status" name="member.client_status" list="clientStrList" listKey="para_value"  listValue="para_key" value="#request.clientList">
                </@s.checkboxlist>
                <@s.fielderror><@s.param>member.client_status</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">公司关健词:</td>
             <td colspan="3">
             	<@s.textfield name="member.cust_key" cssClass="txtinput" cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>member.cust_key</@s.param></@s.fielderror>
             	<div style="line-height:20px;">-请填写公司主营产品名称、提供的服务、公司所属的行业、自己公司的商标等作为公司的关键词。</br>
             			-每个关键词请用逗号隔开。</br>
             			-关键词参与匹配搜索，请按照买家的习惯设置关键词。越准确、简洁，越容易被买家搜索到。
				</div>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">公司经营范围:</td>
             <td colspan="3">
             	<@s.textarea name="member.main_product" cssClass="txtinput" rows="3" cssStyle="width:650px;height:50px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>member.main_product</@s.param></@s.fielderror>             	
             </td>  
           </tr>
            <tr>
              <td valign="middle" class="left_td">公司介绍<font color='red'> *</font></td>
             <td colspan="3">
                <@s.textarea name="member.cust_desc" id="cust_desc" cssClass="txtinput" cssStyle="width:650px;height:250px;" maxLength="2000" />
				<@s.fielderror><@s.param>member.cust_desc</@s.param></@s.fielderror>
				<div style="line-height:20px;">	-我们建议您填写详细的公司介绍，例如历史、业绩、经营范围、发展前景等。</br>
						-不支持html语言。</br>
						-内容控制在50-2000字符内。
				</div>
             </td>
          </tr>   
          <tr>
             <td valign="middle" class="left_td">经营地址:</td>
             <td>
             	<@s.textfield name="member.address" cssClass="txtinput"  maxLength="200" rows="3" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.address</@s.param></@s.fielderror>
             </td>
              <td valign="middle" class="left_td">公司电话:</td>
             <td>
             	<@s.textfield name="member.phone" cssClass="txtinput" maxLength="50" cssStyle="width:250px;" />
             	<@s.fielderror><@s.param>member.phone</@s.param></@s.fielderror> 
             </td> 
         </tr>
         <tr>
             <td valign="middle" class="left_td">公司传真:</td>
             <td>
             	<@s.textfield name="member.fax" cssClass="txtinput" maxLength="50" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.fax</@s.param></@s.fielderror>
             </td> 
              <td valign="middle" class="left_td">公司网址:</td>
             <td >
             	<@s.textfield name="member.website" cssClass="txtinput" maxLength="100" cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.website</@s.param></@s.fielderror>
             </td>          
         </tr>
         
         
         
         <tr>
             <td valign="middle" class="left_td">公司法人代表:</td>
             <td>
             	<@s.textfield name="member.corporate" cssClass="txtinput"  maxLength="100"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.corporate</@s.param></@s.fielderror>
             </td>
              <td width="15%" valign="middle" class="left_td">注册资金:</td>
             <td>
                <@s.textfield name="member.reg_money" cssClass="txtinput"  maxLength="100" cssStyle="width:90px;"/>
                <@s.fielderror><@s.param>member.reg_money</@s.param></@s.fielderror>
               <@s.select id="moneytype" name="member.reg_money_type" list="moneyList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" maxLength="100" onchange="addMoneytype();"/>
                <span id="othertype" style="display:none;"><@s.textfield  name="other_money_type" cssClass="zitxt"  maxlength="50"/></span>
             </td> 
         </tr>
         <tr>
             <td valign="middle" class="left_td">公司规模:</td>
             <td>
             	<@s.select name="member.staff_num" list="staffList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="保密" maxLength="10"/>
             	<@s.fielderror><@s.param>member.staff_num</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">年销售额:</td>
             <td>
             	<@s.select name="member.year_sum" list="sumList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="保密" maxLength="200"/>百万人民币
             	<@s.fielderror><@s.param>member.year_sum</@s.param></@s.fielderror>
             </td>
         </tr>      
         <tr>
             <td valign="middle" class="left_td">公司成立年份:</td>
             <td>
               <@s.textfield name="member.reg_date" cssClass="txtinput" maxLength="100" cssStyle="width:250px;"/>
               <@s.fielderror><@s.param>member.reg_date</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">是否OEM代加工:</td>
             <td>
             	<@s.radio name="member.isoem" list=r"#{'是':'是','否':'否'}" />
             	<@s.fielderror><@s.param>member.isoem</@s.param></@s.fielderror>
             </td>
        </tr>  
        <tr>
             <td valign="middle" class="left_td">商标:</td>
             <td>
             	<@s.textfield name="member.brand_name" cssClass="txtinput" maxLength="200"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.brand_name</@s.param></@s.fielderror>
             </td>
             <td valign="middle" class="left_td">营业执照号:</td>
             <td >
             	<@s.textfield name="member.reg_no" cssClass="txtinput" maxLength="200"  cssStyle="width:250px;"/>
             	<@s.fielderror><@s.param>member.reg_no</@s.param></@s.fielderror>
             </td>
        </tr>
        <tr>
          <td  valign="middle" class="left_td">营业执照复印件:</td>
          <td colspan="3">
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			   <@s.textfield name="member.reg_no_path" id="uploadresult1" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>member.reg_no_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>       
             </td>
        </tr>
	     <tr>
	     <td colspan="4" class="subbut">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	     <@s.hidden name="member.cust_id"/>
	     <@s.hidden name="member.mem_type"/>
	     <@s.hidden name="member.info_state"/>
	     <@s.hidden name="member.recommend"/>
	     <@s.submit value="保存"  cssClass="sub"/>
		 <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		 <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
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
<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
	 .oper_add{color:#990000;}
	 .oper{margin-left:20px;color:#990000;}
	 .cert_td1{width:160px;padding-right:20px;text-align:right;}
</style>
</body>
</html>

