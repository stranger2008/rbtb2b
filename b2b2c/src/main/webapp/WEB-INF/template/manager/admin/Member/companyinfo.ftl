<html>
  <head>
    <title>会员-公司信息</title>
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
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">公司资料</td>
           </tr> 
            <tr>
             <td class="table_name">公司主页</td>
             <td colspan="3">
             	<@s.label name="member.website"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司名</td>
             <td>
             	<@s.label name="member.cust_name" />
             </td>
             <td class="table_name">公司类型</td>
             <td>
             	${(member.cust_type)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">经营模式</td>
             <td>
             	${(member.client_status)?if_exists}
             </td>
             <td class="table_name">主营范围</td>
             <td>
             	<@s.label name="member.main_product" />
             </td>
           </tr>
           <tr>
             <td class="table_name">注册资本</td>
             <td>
             	<@s.label name="member.reg_money" />
                &nbsp;&nbsp;
                ${(member.reg_money_type)?if_exists}
             </td>
             <td class="table_name">公司规模</td>
             <td>
             	${(member.staff_num)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">成立年份</td>
             <td>
             	<@s.label name="member.reg_date" />
             </td>
             <td class="table_name">公司所在地</td>
             <td>
             	<@s.label name="member.area_attr"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">供应产品（服务）</td>
             <td>
             	<@s.label name="member.cust_supply" />
             </td>
             <td class="table_name">采购产品（服务）</td>
             <td>
             	<@s.label name="member.cust_stock" />
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
             <td class="table_name">手机:</td>
             <td>
             	<@s.label name="member.contact_cellphone"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">部门:</td>
             <td>
             	<@s.label name="member.contact_job"/>
             </td>
             <td class="table_name">职位:</td>
             <td>
             	<@s.label name="member.contact_depart"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">电话:</td>
             <td>
             	<@s.label name="member.phone"/> 
             </td> 
              <td class="table_name">传真:</td>
             <td>
             	<@s.label name="member.fax"/>
             </td> 
           </tr>
           <tr>
             <td class="table_name">邮箱:</td>
             <td >
             	<@s.label name="member.email" />
             </td>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.label name="member.contact_qq"/>
             </td> 
           </tr>
           <tr>
             <td class="table_name">MSN:</td>
             <td>
                <@s.label name="member.contact_msn"/>
             </td>
             <td class="table_name">邮编:</td>
             <td>
             	<@s.label name="member.post_code"/>
             </td>
           </tr> 
           <tr>
             <td class="table_name">公司经营地址:</td>
             <td colspan="3">
             	<@s.label name="member.address"/>
             </td>
           </tr>
          
          <tr>
             <td colspan="4" align="left" style="font-weight:bold;font-size:14px;padding-left:120px;">其他信息</td>
           </tr>      
           <tr>
             <td class="table_name">公司法人代表:</td>
             <td colspan="3">
               <@s.label name="member.corporate" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
               <@s.fielderror><@s.param>member.corporate</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">公司规模:</td>
             <td colspan="3">
                ${(member.staff_num)?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">年销售额:</td>
             <td colspan="3">
                ${(member.year_sum)?if_exists}
             </td>  
           </tr>
           <tr>
              <td class="table_name">商标:</td>
             <td colspan="3">
             	<@s.label name="member.brand_name" />
             </td>
           </tr>
           <tr>
             <td class="table_name">营业执照号:</td>
             <td colspan="3">
             	<@s.label name="member.reg_no" />
             </td>
           </tr>
           <tr>
             <td class="table_name">是否OEM代加工:</td>
             <td colspan="3">
                ${(member.isoem)?if_exists}
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