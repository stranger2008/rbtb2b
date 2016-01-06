<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<meta name ="keywords" content="${(memberconfig.keywords)?if_exists}">
<meta name="description" content="${(memberconfig.site_desc)?if_exists}">  
<title>实名认证—${(memberconfig.web_title)?if_exists}</title>
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/public.css" rel="stylesheet" type="text/css">
<link href="/templets/company/${memberconfig.temp_code?if_exists}/css/index.css" rel="stylesheet" type="text/css">
</head>

<!--头部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/top.ftl">
<!--头部结束-->
<div class="position"><P><span class="f_left wz">当前位置: <a href="/showroom/${user_name?if_exists}" style="font-size:14px;">首页</a> > 企业实名认证</span> <span class="data f_right">今天是 ${systime?if_exists}</span></span></P></div>

<div class="w960">
  <!--左部结束-->
  <#include "/WEB-INF/template/company/${temp_loc?if_exists}/left.ftl">
  <!--左部结束-->
  
 <div class="r_body f_right">
     
       <!--目录式浏览-->
        <div class="right_main">
           <div class="r_title"><h2><span class="title_font">企业实名认证</span></h2></div> 
           <div class="other_main">
            <div class="cert_center" style="padding-left:25px;">
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
       </div>
       </div>
    <!--目录式浏览-->
   
  </div>
      
  <!--右部结束--> 
 
</div>
<!--底部开始-->
<#include "/WEB-INF/template/company/${temp_loc?if_exists}/bottom.ftl">
<!--底部结束-->
<!--所属地区插件隐藏字段开始区域-->
<@s.hidden id="hidden_area_value" name="hidden_area_value" />
</body>
</html>
