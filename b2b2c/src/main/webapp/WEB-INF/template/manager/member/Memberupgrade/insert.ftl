﻿<html>
<head>
<title>在线升级</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Memberupgrade_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:会员中心首页>在线升级</td>
      </tr>
    </table>
    <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
        <#if audit_state_s?if_exists==''>
		    <tr> 
		     <td>
		         ${user_name_s?if_exists},您好！您当前的级别为
		         <@s.label name="now_level_s"/>
		         <span class="cont_title">${levelname?if_exists}</span><#if levelinfo.level_code=='1'||levelinfo.level_code=='3'>，您可以点击以下按钮申请为<#else>，已是最高级别了</#if>
		         <#if levelinfo.level_code=='1'>
		         <span class="cont_title">个人VIP会员</span>
		         <#elseif levelinfo.level_code=='3'>
		         <span class="cont_title">企业VIP会员</span>
		         </#if>
		       </td>
		    </tr>
		     <#if levelinfo.level_code=='1'||levelinfo.level_code=='3'>
		    <tr>
		     <td colspan="4" class="subbut">
		        <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		        <@s.submit value="申请"  cssClass="sub"/>
		     </td>
		    </tr>
		     </#if>

        <#elseif audit_state_s?if_exists!='' && audit_state_s?if_exists=='0'>
        
        <tr> 
          <td>
             ${user_name_s?if_exists},您好！您已成功发送
             <#if levelinfo.level_code=='1'>
		         <span class="cont_title">个人VIP会员</span>
		         <#elseif levelinfo.level_code=='3'>
		         <span class="cont_title">企业VIP会员</span>
		     </#if>
            升级申请信息，我们将在几个工作日之内完成审核，请耐心等待... 
          </td>
        </tr>
        
        <#elseif audit_state_s?if_exists!='' && audit_state_s?if_exists=='1'>
          <tr> 
		     <td>
		         ${user_name_s?if_exists},您好！您当前的级别为
		         <@s.label name="now_level_s"/>
		         <span class="cont_title">${levelname?if_exists}</span><#if levelinfo.level_code=='1'||levelinfo.level_code=='3'>，您可以点击以下按钮申请为<#else>，已是最高级别了</#if>
		         <#if levelinfo.level_code=='1'>
		         <span class="cont_title">个人VIP会员</span>
		         <#elseif levelinfo.level_code=='3'>
		         <span class="cont_title">企业VIP会员</span>
		         </#if>
		       </td>
		    </tr>
       </#if>
      
</table>

 </div>
   <#if levelinfo.level_code=='1'>
    <@s.hidden name="memberupgrade.now_level" value="1"/>
    <@s.hidden name="memberupgrade.apply_level" value="2"/>
    <#elseif levelinfo.level_code=='3'>
    <@s.hidden name="memberupgrade.now_level" value="3"/>
    <@s.hidden name="memberupgrade.apply_level" value="4"/>
   </#if>
    
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
