<html>
<head>
	<title>实名认证</title>
	<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/plugins/calendar/WdatePicker.js"></script>
	<link rel="StyleSheet" href="/manager/member/Certification/index.css" type="text/css" />
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         //设置select不可用
         $("select").each(function(){
	          $(this).attr("disabled","true")
	          $(this).css("color","black");
        });
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
<@s.form action="/member_Certification_add.action" method="post" >
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
	       <#if certification.reason!=''>
	     	 <tr>
		         <td class="cert_td1"><font style="font-weight:600;font-size:14px;">未通过的理由：</font></td>
		         <td>
		         	${certification.reason?if_exists}
		         </td>
	         </tr> 
	       </#if>   
	         <tr>
		         <td class="cert_td1">公司名称</td>
		         <td>
		         ${certification.cust_name?if_exists}
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">地区</td>
	             <td>${areaName?if_exists}</td>
	         </tr>  
	         <tr>
         		 <td class="cert_td1">公司注册地址</td>
                 <td>
                 ${certification.address?if_exists}
                 </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">法定代表人</td>
		         <td>
		          ${certification.corporate?if_exists}
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">企业类型</td>
	             <td>
	                <@s.fielderror><@s.param></@s.param></@s.fielderror>
	                <#list cust_type_List as cust>
	                    <#if cust.para_value==certification.cust_type>
	                        ${cust.para_key?if_exists}
	                    </#if>
	                </#list>
	             </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">注册资本</td> 
		         <td>
			          ${certification.reg_money?if_exists}万	 
			         <#list commparaList as para>
			               <#if (para.para_value==certification.currency)>
				            	${para.para_key?if_exists}
				           </#if>			            
		             </#list>     
			                      
		         </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">经营期限</td>
		         <td>
		              ${certification.o_start_date?if_exists}~ ${certification.o_end_date?if_exists}
             	 </td>
	         </tr>  
	         <tr>
		         <td class="cert_td1">经营范围</td>
		         <td>
		         <#list catList as cat>
		               <#if (certification.class_attr?if_exists?index_of(cat.cat_id?if_exists)>-1)>
			            	<font>${cat.cat_name?if_exists}</font>&nbsp;
			           </#if>			            
		         </#list>         
		         <@s.fielderror><@s.param>certification.class_attr</@s.param></@s.fielderror>
		         </td>
	         </tr>  
	         <tr>
	             <td class="cert_td1">成立日期</td> 
              	 <td>             	 
              	 	${certification.reg_date?if_exists}
              	 </td>
	         </tr>
	         <tr>
		         <td class="cert_td1">是否年检</td>
		         <td>
		             <#if certification.is_inspect=='1'>已年检<#else>未年检</#if>
		         </td>
		     </tr>
		     <tr>
		         <td class="cert_td1">是否年检</td>
		         <td>
		             <div>
			            <#list certification.ins_record?split(",") as s>	
			              ${s}已年检&nbsp;
			             </#list>
		             </div>
		         </td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">登记机关</td> 
		         <td>
				         ${certification.reg_auth?if_exists}
		         </td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">营业执照复印件</td>
		         <td>  
		            <#list certification.license_path?split(",") as s>	
		              <div class="imgcenter" >        
			             <div class="divMiddle" style="width:183px;height:181px;line-height:181px;">        
			                 <a href="${s}" class="AMiddle"  style="width:183px;" target="_blank">
			                   <img src="${s}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${s}','173','171')")} class="ImgMiddle">
			                 <a>
			             </div>
			          </div> 
		            </#list>
		         </td>
	         </tr> 
	         <tr>
		         <td class="cert_td1">申请人姓名</td>
		          <td>
			          ${certification.app_name?if_exists}
		          </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">申请人部门</td> 
                  <td>
                      ${certification.app_depart?if_exists}
                   </td>
	         </tr> 
	         <tr>
	        	  <td class="cert_td1">申请人职位</td> 
                  <td>
	                  ${certification.app_career?if_exists}
                  </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">联系人手机</td> 
                  <td>
	                  ${certification.app_contact?if_exists}
                  </td>
	         </tr> 
	         <tr>
	              <td class="cert_td1">授权证明复印件</td> 
                  <td>
		            <#list certification.auth_path?split(",") as s>	
		              <div class="imgcenter">        
			             <div class="divMiddle" style="width:183px;height:181px;line-height:181px;">        
			                 <a href="${s}" class="AMiddle"  style="width:183px;"  target="_blank">
			                   <img src="${s}" ${stack.findValue("@com.rbt.function.ChannelFuc@getSetImgSize('${s}','173','171')")} class="ImgMiddle">
			                 <a>
			             </div>
			          </div> 
		            </#list>
                  </td>
	         </tr> 	         	       
	     </table>
	</div>
	<div style="text-align:center;">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	     <@s.hidden name="edit" value="1"/>
	     <@s.submit value="修改实名信息" cssClass="sub" cssStyle="font-size:12px;"/>
	     <!--所属地区插件隐藏字段开始区域-->
		 <@s.hidden id="hidden_area_value" name="hidden_area_value" />
		 <!--所属地区插件隐藏字段结束区域-->
	</div>
</div>
</@s.form>
</body>
</html>
