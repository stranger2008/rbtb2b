<html>
<head>
	<title>实名认证</title>
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
	<#include "/include/uploadInc.html">
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<link rel="StyleSheet" href="/manager/member/Certification/index.css" type="text/css" />
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
		<style type="text/css">
		.cert_top{ 
		 padding:10px;
		 padding-top:16px;
		 border:1px solid #009900;
		 background-color:#EEFFCC;
		 margin:6px;
		 height:26px;
		}
		.top_1{
		 font-size:16px;
		 font-weight:600;
		}
		.top_2{
		 font-size:12px;
		 font-weight:400;
		}
		.cert_center{
		 border:1px solid #D7DBE6;
		 margin:6px;
		 height:auto;
		}
		
		.cert_td1{ 
		  width:160px;
		  padding-right:20px;
		  text-align:right;
		}
	</style>
</head>

<body>
<@s.form action="/member_Certification_insert.action" method="post" >
<div class="cont_main">
 <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:会员信息>会员资料>企业实名认证</td>
 </tr>
</table>
	<div class="cert_top">
	  <font class="top_1"> 申请实名会员</font> <font class="top_2"> （ 审核状态：
	  <#if certification.info_state=='' || certification.info_state=='0'>
	 	 新加入
	  <#elseif certification.info_state=='1'>
	     认证中
	  <#elseif certification.info_state=='2'>
	     被驳回
	  <#elseif certification.info_state=='3'>
	     已通过
	  </#if> 
	  
	  ）</font>
	 <#if certification.audit_user_id!=''>
               审核人: ${sysuser.user_name?if_exists} &nbsp;
	           审核时间:${certification.audit_date?if_exists}	             
      </#if>   	
	</div>	
	<div class="cert_center">
	     <table width="100%">
	         <tr>
		         <td class="cert_td1">公司名称<font color="#FF3300"> *</font></td>
		         <td>
		         <@s.textfield name="certification.cust_name"  cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
		         <@s.fielderror><@s.param>certification.cust_name</@s.param></@s.fielderror>
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">地区<font color="#FF3300"> *</font></td>
	             <td><div id="arealist"></div>
	             <@s.fielderror><@s.param>areaerror</@s.param></@s.fielderror>
	             </td>
	         </tr>  
	         <tr>
         		 <td class="cert_td1">公司注册地址<font color="#FF3300"> *</font></td>
                 <td>
                 <@s.textfield name="certification.address" cssClass="txtinput" cssStyle="width:460px" maxLength="50"/>                 
                 <@s.fielderror><@s.param>certification.address</@s.param></@s.fielderror>
                 </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">法定代表人<font color="#FF3300"> *</font></td>
		         <td>
		         <@s.textfield name="certification.corporate"   cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
		         <@s.fielderror><@s.param>certification.corporate</@s.param></@s.fielderror>
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">企业类型<font color="#FF3300"> *</font></td>
	             <td>
	                <@s.select name="certification.cust_type"  list="cust_type_List" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" />  
	                <@s.fielderror><@s.param>certification.cust_type</@s.param></@s.fielderror>
	             </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">注册资本<font color="#FF3300"> *</font></td> 
		         <td>
			         <@s.textfield name="certification.reg_money"  cssClass="txtinput" cssStyle="width:120px" maxLength="50"/> 
			         <@s.select  list=r"#{'万':'万'}"/>
			         <@s.select name="certification.currency" list="commparaList" listKey='para_value'  listValue='para_key' /> 
			         <@s.fielderror><@s.param>certification.reg_money</@s.param></@s.fielderror> 		              
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">经营期限<font color="#FF3300"> *</font></td>
		         <td>
		              <@s.textfield id="txtstartDate" name="certification.o_start_date"  type="text" cssStyle="width:141px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> ~               
		              <@s.textfield id="txtendDate" name="certification.o_end_date" cssStyle="width:141px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />	
		               <@s.fielderror><@s.param>certification.o_start_date</@s.param></@s.fielderror> 
		                <@s.fielderror><@s.param>certification.o_end_date</@s.param></@s.fielderror> 
             	 </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">经营范围<font color="#FF3300"> *</font></td>
		         <td>
		         <ul style="float:left;width:550px;">
		         <#list catList as cat>
		            <li style="min-width:100px;_width:100px;float:left;margin:3px;">
		               <#if (certification.class_attr?if_exists?index_of(cat.cat_id?if_exists)>-1)>
			            	<input type="checkbox" checked name="certification.class_attr" value="${cat.cat_id?if_exists}"/> 
			           <#else>
			                <input type="checkbox" name="certification.class_attr" value="${cat.cat_id?if_exists}"/> 
			           </#if>
			            <font>${cat.cat_name?if_exists}</font>
			        </li>
		         </#list>         
		         </ul>
		         <@s.fielderror><@s.param>certification.class_attr</@s.param></@s.fielderror>
		         </td>
	         </tr>  
	         <tr>
	             <td class="cert_td1">成立日期<font color="#FF3300"> *</font></td> 
              	 <td>             	 
              	 	<@s.textfield cssClass="Wdate" name="certification.reg_date"  onfocus="WdatePicker({readOnly:true})" />
              	 	<@s.fielderror><@s.param>certification.reg_date</@s.param></@s.fielderror>
              	 </td>
	         </tr>
	         <tr>
		         <td class="cert_td1">是否年检<font color="#FF3300"> *</font></td>
		         <td>
		             <@s.radio name="certification.is_inspect" list=r"#{'1':'已年检','0':'未年检'}" />  
		             <@s.fielderror><@s.param>certification.is_inspect</@s.param></@s.fielderror>
		          </td>
		     </tr>
		     <tr>
		         <td class="cert_td1">年检记录<font color="#FF3300"> *</font></td>
		         <td>
		             <div>
		             <@s.hidden id="score" value="${certification.ins_record?if_exists}"/>   
			             <script>
				             var myDate = new Date(); 
				             var year=myDate.getFullYear();
				             for(var i=0;i<5;i++){
				                 var score=$("#score").val();
				                 if(score.indexOf((year-i))>-1){
				                     document.write("<input type='checkbox' checked  name='certification.ins_record' value="+(year-i)+">"+(year-i)+"已年检"); 			                 
				                 }else{
				                     document.write("<input type='checkbox'  name='certification.ins_record' value="+(year-i)+">"+(year-i)+"已年检"); 				                 
				                 }                   
				             }   
			             </script>
		             </div>
		             <@s.fielderror><@s.param>certification.ins_record</@s.param></@s.fielderror>
		         </td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">登记机关<font color="#FF3300"> *</font></td> 
		         <td>
			         <@s.textfield name="certification.reg_auth" cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
			         <@s.fielderror><@s.param>certification.reg_auth</@s.param></@s.fielderror>
		         </td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">营业执照复印件<font color="#FF3300"> *</font></td>		        
		        <td >            
	                <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			  <@s.textfield name="certification.license_path" id="uploadresult" cssStyle="width:300px;" />
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
		              			<script>uploadOneImgMulti();</script>
	             			</td>
	             			<td>
	             			   <@s.fielderror><@s.param>certification.license_path</@s.param></@s.fielderror>
	             			</td>
	             		</tr>
	             	</table>                        
             	</td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">申请人姓名<font color="#FF3300"> *</font></td>
		          <td>
			          <@s.textfield name="certification.app_name" cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
			          <@s.fielderror><@s.param>certification.app_name</@s.param></@s.fielderror>
		          </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">申请人部门<font color="#FF3300"> *</font></td> 
                  <td>
                     <@s.textfield name="certification.app_depart" cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
                     <@s.fielderror><@s.param>certification.app_depart</@s.param></@s.fielderror>
                   </td>
	         </tr> 
	         <tr>
	        	  <td class="cert_td1">申请人职位<font color="#FF3300"> *</font></td> 
                  <td>
	                  <@s.textfield name="certification.app_career"  cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
	                  <@s.fielderror><@s.param>certification.app_career</@s.param></@s.fielderror>
                  </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">联系人手机<font color="#FF3300"> *</font></td> 
                  <td>
	                  <@s.textfield name="certification.app_contact"  cssClass="txtinput" cssStyle="width:300px" maxLength="50"/>
	                  <@s.fielderror><@s.param>certification.app_contact</@s.param></@s.fielderror>
                  </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">授权证明复印件<font color="#FF3300"> *</font></td>                  
                  <td >            
	                <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue1"></div>
		              			  <@s.textfield name="certification.auth_path" id="uploadresultImg" cssStyle="width:300px;" />
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadresultImg" id="uploadifyfileImg"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresultImg');"/>
		              			 <script>uploadComponent('uploadifyfileImg','uploadresultImg','fileQueue1' ,'image',true);</script>
	             			</td>
	             			<td>
	             			   <@s.fielderror><@s.param>certification.auth_path</@s.param></@s.fielderror>
	             			</td>
	             		</tr>
	             	</table>                        
             	</td>
	         </tr> 	         	       
	     </table>
	</div>
	<div style="text-align:center;">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	     <@s.submit value="确定" cssClass="sub"/>
	     <!--所属地区插件隐藏字段开始区域-->
		 <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		 <!--所属地区插件隐藏字段结束区域-->
	</div>
</div>
</@s.form>
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
