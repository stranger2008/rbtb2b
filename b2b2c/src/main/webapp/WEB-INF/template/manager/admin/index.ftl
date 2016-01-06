<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>后台首页</title>
<link href="/include/css/admin/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="main_index f_left">
   <div class="mian_t_top_top">
   </div>
 <div class="main_cont">
    <div class="w945  w_marlef">     
   <div class="lcon_main f_left">        
      <div class="connet">
          <h2 class="con_title">待审核信息<a class="more" href="/admin_Sysuser_fundaudit.action?user_name=xiaoge">更多>></a></h2> 
          <div class="clear"></div>
          <div class="con_connent">
                 
                 <table width="100%">
                 <#assign count11=0>
                 <#list countlist as count>
 					
					<#if count11%2==0>
					<tr width="50%">
					</#if>
					<#assign count11=count11+1>
					<td class="yw_abc" >
					    <span class="ywspanf">${count.module_name?if_exists}:</span>
                        <span class="ywspans">未审核</span> 
                        <span class="ywspant"><a  href="/admin_${count.table_name?if_exists}_auditList.action?info_state_s=0">${count.countaudit?if_exists}</a>&nbsp;条</span>   
                        <span class="ywspanf">今日新增:</span> 
                        <span class="ywspant"><a href="/admin_${count.table_name?if_exists}_list.action?today=now">${count.todaycount?if_exists}</a>&nbsp;条</span> 	   
					</td>                 
                    <#if count11%2==0>
					</tr>
					</#if>
                  </#list>
                 </table>
                 
                 
                  
                
                <div class="clear"></div>
           </div>

      </div>
       <div class="connet" style="padding-top:10px;">
          <h2 class="con_title">待处理信息</h2> 
          <div class="clear"></div>
          <div class="con_connent">
                 
                 <table width="100%">
                
					<tr width="50%">
					
					<td class="yw_abc" >
					    <span class="ywspanf">提现申请:</span>
                        <span>未处理信息&nbsp;<a href="/admin_Fundtocash_list.action?order_state_s=0">${fundcount?if_exists}</a>&nbsp;条</span> 	   
					</td>                    
					</tr>	
                 </table>
                 
                 
                  
                
                <div class="clear"></div>
           </div>

      </div>
      <div class="connet w_padtop">
          <h2 class="con_title">系统消息</h2>
          <div class="clear"></div>
          <div class="con_connents">
          <table width="100%" >
           <tr width="100%"><td class="yw_a2">程序信息</td><td class="yw_a22">ruibaotong B2B version 2.1 Release 20120901 UTF-8 ZH-CH</td></tr>
           <tr ><td class="yw_a2">安装版本</td><td class="yw_a22">公司版本</td></tr>
           <tr ><td class="yw_a2">安装时间</td><td class="yw_a22">${install_date?if_exists}</td></tr>
           <tr ><td class="yw_a2">官方网站</td><td class="yw_a22"><a href="http://www.ruibaotong.net" target="_balnk">http://www.ruibaotong.net</a></td></tr>
           <tr ><td class="yw_a2">支持论坛 </td><td class="yw_a22"><a href="http://bbs.ruibaotong.net" target="_balnk">http://bbs.ruibaotong.net</a></td></tr>
           <tr ><td class="yw_a2">使用帮助</td><td class="yw_a22"><a href="http://help.ruibaotong.net" target="_balnk">http://help.ruibaotong.net</a></td></tr>
           <tr ><td class="yw_a2">服务器时间</td><td class="yw_a22">${server_datetime?if_exists} </td></tr>
           <tr ><td class="yw_a2">服务器信息</td><td class="yw_a22">${OS_name?if_exists}&nbsp;${server_info?if_exists}[${server_ip?if_exists}] </td></tr>
           <tr ><td class="yw_a2">数据库版本</td><td class="yw_a22">MySQL&nbsp;${database_product_version?if_exists} </td></tr>
           <tr ><td class="yw_a2">数据库名</td><td class="yw_a22">${database_name?if_exists}&nbsp; </td></tr>
          </table>
          <div class="clear"></div>
           </div>
      </div>            
  </div>        
    <div class="rcon_main f_right">

      <div class="r_connent">
           <div class="rcon_title"><h2 class="rcon_font">操作记录</h2><a href="/admin_Logs_list.action?user_name=${opername?if_exists}" class="more">更多>></a></div>
          <div class="clear"></div>
          <div class="rcon_connent">
            <ul>
              <#list loglist as logs>
                  <li >
                  	<span class="logname">
                  		<#if logs.content?if_exists!=''>
                  			<#if logs.content?length lt 30>
					 			${logs.content?if_exists}
				  			<#else>
					  			${logs.content[0..29]}...    
				   			</#if>
				   		</#if>
				   	</span>
                  	<span>${logs.in_date[0..9]?if_exists}</span>
                  </li>
              </#list>
            </ul>
          </div>     
      </div>
      
      
      
      <div class="r_connent w_padtop">
           <div class="rcon_title"><h2 class="rcon_font">帐号信息</h2></div>
          <div class="clear"></div>
          <div class="rcon_connent">
             <p class="p_font">尊敬的${opername?if_exists}先生：此次是你第${logintimes?if_exists}次登陆</p>
             <p class="p_font">${session.web_name?if_exists}</p>
             <p class="p_font">你上次登陆的时间是：${login_time?if_exists[0..18]}</p>
             <p class="p_font w_padbot">你上次登陆的IP是：${login_IP?if_exists}</p>
             <div class="clear"></div>  
          </div>
      </div>  
    </div><!---rcon_main end-->
        
 </div><!--main end-->
     
    <div class="clear"></div>  
  
 </div>
 <div class="clear"></div>
</div>
</div>


  </div><!--cont_main-->
  
   <div class="clear"></div>  
</div>
 
 <div class="clear"></div>  

</div>

<div class="clear"></div>  
</body>
</html>
