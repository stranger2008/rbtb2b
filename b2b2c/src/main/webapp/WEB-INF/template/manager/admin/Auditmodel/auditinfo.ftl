  <table class="wwtable" cellspacing="1" cellpadding="8">
	   <tr>
			 <td>
			    <font style="font-weight: bold;color:#4E6A81;padding-right: 30px;">信息审核详情</font>    
			    <#if if_opt_audit=="1">
				     <img src="/include/images/admin/xiaoxi.gif" height="15px"/>
					 <font style="color:#EE9D05">系统提醒:请尽快审核信息!</font>
			    <#elseif if_opt_audit=="2">
				     <img src="/include/images/admin/xiaoxi.gif"  />
					<font style="color:#EE9D05">系统提醒:请等待前置审核人员"审核通过"以后,才能审核信息!</font>
			    </#if>
			 </td>
	   </tr>
	   <tr >
		 <td>
				   <table class="wwtable" cellspacing="1" >
				   
				        <#assign lcount=0>
					        <#list auditMemberList as mlist>
					        <#assign lcount=lcount+1>
				        </#list>
					    <#list auditMemberList as mlist>
					          <#assign count=0>
						      <#list auditHistoryList as hlist>
						            <#if mlist.userid==hlist.user_id>
						               <#assign count=count+1>
						            </#if>
							 </#list>
						   <tr >
						      <td  width="150px" >
								      <#if mlist_index+1!=lcount>
									       <#if count==0>
									           <img src="/include/images/admin/adutiblack.png"  />
									            <font style="font-weight: bold;color:#9E9E9E">${(mlist.username)?if_exists}</font>
										   <#else>
										        <#if mlist_index+1!=mlist.size()>
										        <img src="/include/images/admin/adutiyes.png"  />
											       <font style="font-weight: bold;color:#509FEC">${(mlist.username)?if_exists}</font>
										        <#else>
											          <img src="/include/images/admin/finallyes.png"  />
											       <font style="font-weight: bold;color:#509FEC">${(mlist.username)?if_exists}</font>
										        </#if>
										   </#if>
									  <#else>
								           <#if count==0>
									           <img src="/include/images/admin/finallno.png"  />
										       <font style="font-weight: bold;color:#9E9E9E">${(mlist.username)?if_exists}</font>
									       <#else>
										       <img src="/include/images/admin/finallyes.png"  />
										       <font style="font-weight: bold;color:#FF8C2D">${(mlist.username)?if_exists}</font>
									       </#if>
								      </#if>
						      </td>
						      <td>
						         <#assign mycount=0>
						         <#list auditHistoryList as hlist>
						            <#if mlist.userid==hlist.user_id>
						                    <#assign mycount=mycount+1>
						                    <#if hlist.in_date!=""><font style="font-weight: bold;color: #4E6A81;padding-left:10px;">审核时间</font>:
						                    	<font style="color:#6F767E;padding-right:10px;">${(hlist.out_date)?if_exists}</font>
						                    </#if>
						                    <#if hlist.info_state!=""><font style="font-weight: bold;color:#4E6A81;">状态</font>:
							                    <#if hlist.info_state=="1">
							                    	<font style="color:#1E7D24;padding-right:10px;">通过</font>
							                    <#else><font style="color:red;padding-right:10px;">未通过</font>
							                    </#if>
						                    </#if>
						                    <#if hlist.no_reason!=""><font style="font-weight: bold;color: #4E6A81">未通过原因</font>:
						                    	<font style="color:#6F767E;">${(hlist.no_reason)?if_exists}</font>
						                    </#if>
						                    <br/>
						            
						            </#if>
						         </#list>
						         <#if count==0>
						              <font style="font-weight: bold;color:#9E9E9E">状态</font>:<font style="color:#9E9E9E;">未审核</font>
						         </#if>
						      </td>
						   </tr>
					   </#list>
				  </table>
		 </td>
	 </tr>
 </table>
  