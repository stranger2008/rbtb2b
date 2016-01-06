 <html>
<head>
<title>我的收藏</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的收藏</a>
    </div>
<@s.form action="/bmall_Collect_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">我的交易</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的收藏</td>
       </span></h2>
       
       
        <table width="100%">
       		<tr>
					<td align="right">收藏标题:</td> 
					<td><@s.textfield name="title_s"  maxLength="50"/></td>
					<td align="right">收藏类型:</td> 
					<td><@s.select name="cat_id_s" list=r"#{'0':'商品','1':'店铺'}" headerKey="" headerValue="请选择"/></td>	
					<td rowspan="2" width="35%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
			<tr>
					<td align="right">收藏时间段:</td> 
					<td>
						<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 						
					至						
						<@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>
			</tr>
       	</table>
        
       <table cellspacing="0" class="bmall_list_table">
         <tr style="text-align:center;">
         <td width="3%" class="fthstyle1"></td>
         <td class="fthstyle1" width="40%">收藏标题</td>
         
	     <td class="fthstyle1" width="30%">链接地址</td>
    
     	 <td class="fthstyle1" width="10%">收藏类型</td>
     	 
     	 <td class="fthstyle1" width="10%">收藏时间</td>
     	 
     	 <td class="fthstyle1" width="10%">操作</td>
         </tr>
         <tr><td colspan="9"  class="manadiv">
         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('collect.coll_id')"/>
			<input type="button" class="submitbut" value="添加" onclick="linkToInfo('/bmall_Collect_add.action','');"/>
			<input type="button" class="submitbut" value="删除" onclick="delInfo('collect.coll_id','/bmall_Collect_delete.action')"/>
         </td></tr>    
         <#list collectList as collect>
      <tr align="center"> 
        
 	    <td width="3%" ><input type="checkbox" name="collect.coll_id" value="${collect.coll_id?if_exists}"/></td>
 	
    	<td><a href="${collect.link_url?if_exists}">${collect.title?if_exists}</a></td>
    
    	<td>${collect.link_url?if_exists}</td>
    
    	<td>
    	<#if collect.cat_id?if_exists=='0'>商品</#if>
    	<#if collect.cat_id?if_exists=='1'>店铺</#if>
    	</td>
    
    	<td>${collect.in_date?if_exists}</td>
    	
    <td>
    <a onclick="linkToInfo('/bmall_Collect_view.action','collect.coll_id=${collect.coll_id?if_exists}');">修改</a>	
    </td>
  </tr>
  </#list>
       </table>
        <div class="listbottom">
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

