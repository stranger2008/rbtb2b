<html>
  <head>
    <title>会员-个人信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="tj_main_cont"> 
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">会员资料</td>
           </tr>  
           <tr>
             <td class="table_name">会员名</td>
             <td >
             	<@s.label name="member.cust_name" />
             </td>
             <td class="table_name">会员ID</td>
             <td >
             	<@s.label name="member.cust_id" />
             </td>
           </tr>
          
          <tr>
             <td class="table_name">注册时间</td>
             <td >
             	<@s.label name="member.in_date.substring(0,19)" />
             </td>
             <td class="table_name">会员级别</td>
             <td>
             	<#if (levelinfo.level_code)?if_exists=='1'>
             	个人普通会员
             	<#elseif (levelinfo.level_code)?if_exists=='2'>
             	个人VIP会员
             	<#elseif (levelinfo.level_code)?if_exists=='3'>
             	企业普通会员
             	<#elseif (levelinfo.level_code)?if_exists=='4'>
             	企业VIP会员
             	</#if>
             </td>
           </tr>  
           
          <tr>
             <td class="table_name">会员服务时间</td>
             <td colspan="3">
             	开始：<@s.label name="levelinfo.start_date.substring(0,19)" />&nbsp;&nbsp;结束：<@s.label name="levelinfo.end_date.substring(0,19)" />
             </td>
           </tr> 
          <tr>
             <td class="table_name">资金余额</td>
             <td >
             	<@s.label name="memberfund.use_num" />
             </td>
             <td class="table_name">资金锁定</td>
             <td >
             	<@s.label name="memberfund.freeze_num" />
             </td>
           </tr>
           <tr>
             <td class="table_name">VIP指数</td>
             <td >
             	<@s.label name="membercredit.c_num" />
             </td>
             <td class="table_name">会员积分</td>
             <td >
             	<@s.label name="memberinter.fund_num" />
             </td>
           </tr>
               
           <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">联系方式</td>
           </tr> 
           <tr>
              <td class="table_name" >姓名:</td>
              <td>
             	<@s.label name="member.contact_name"/>
             </td>
             <td class="table_name">邮箱:</td>
             <td >
             	<@s.label name="member.email" />
             </td>
           </tr>
           <tr>
             <td class="table_name">手机:</td>
             <td>
             	<@s.label name="member.contact_cellphone"/>
             </td>
             <td class="table_name">电话:</td>
             <td>
             	<@s.label name="member.phone"/> 
             </td> 
           </tr>
           <tr>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.label name="member.contact_qq"/>
             </td> 
             <td class="table_name">MSN:</td>
             <td>
                <@s.label name="member.contact_msn"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">邮编:</td>
             <td>
             	<@s.label name="member.post_code"/>
             </td>
             <td class="table_name">联系地址:</td>
             <td colspan="3">
             	<@s.label name="member.address"/>
             </td>
           </tr>                         
         </table>
         
         <div class="buttom">
	       <input type="button" value="关闭窗口" onclick="window.close();"/>
	     </div>	
         
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>