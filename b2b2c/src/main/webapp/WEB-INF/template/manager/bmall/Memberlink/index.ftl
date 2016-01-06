<html>
<head>
<title>友情链接管理</title>
   
</head>
<body>
	<div class="postion">
  		 </span><a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">友情链接管理</a>
    </div>
<@s.form action="/bmall_Memberlink_bmalllist.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">店铺管理</td>
     </h2></div>
     <div class="ropot">
     	<h2 class="rotitle"><span><td class="fthstyle1">友情链接管理</td></span></h2>
     	 <table width="100%">
       		<tr>
					<td align="right">链接名称:</td> 
					<td><@s.textfield name="title_s"  maxLength="50"/></td>			
					<td align="right">链接状态:</td>
					<td><@s.select name="link_state_s" list=r"#{'':'请选择','0':'正常','1':'未审核','2':'未通过'}" /></td>
					<td align="left;" width="55%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
       	</table>
       
	       <table cellspacing="0" class="bmall_list_table">
	         <tr style="text-align:center;">
	         	 <td class="fthstyle1" width="3%"></td>
		         <td class="fthstyle1" width="30%">链接名称</td>
		         <td class="fthstyle1" width="30%">链接地址</td>
		         <td class="fthstyle1" width="10%">链接状态</td>
		         <td class="fthstyle1" width="10%">添加时间</td>
		         <td class="fthstyle1" width="15%">操作</td>
	         </tr>
	         
	         <tr><td colspan="6"  class="manadiv">
	         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberlink.cert_id')"/>全选
				<input type="button" class="submitbut" value="添加" onclick="linkToInfo('/bmall_Memberlink_add.action','');"/>
				<input type="button" class="submitbut" value="删除" onclick="delInfo('memberlink.cert_id','/bmall_Memberlink_bmalldelete.action')"/>
	         </td></tr>
	         
	         <#list memberlinkList as memberlinks>
	         <tr style="text-align:center;">
	         	 <td><input type="checkbox" name="memberlink.cert_id" value="${memberlinks.cert_id?if_exists}"/></td>
			     <td>${memberlinks.title?if_exists}</td>
			     <td>${memberlinks.link_url?if_exists}</td>
			     <td>
				     <#if memberlinks.link_state=='0'><span class="greencolor">正常</span>
			          <#elseif memberlinks.link_state=='1'><span class="redcolor">未审核</span>
			          <#else><span class="bluecolor">未通过</span>
			          </#if>
			     </td>
			     <td>${memberlinks.in_date?if_exists}</td>
			     <td> 
			         <a onclick="linkToInfo('/bmall_Memberlink_view.action','memberlink.cert_id=${memberlinks.cert_id?if_exists}');">修改</a>
		         </td>
	         </tr>
	         </#list>
	       </table>
	        <div class="listbottom">
	        ${pageBar?if_exists}
	        </div>
	 </div>     
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>