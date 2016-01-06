<html>
<head>
<title>发件箱</title>
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <script type="text/javascript">
	  $(document).ready(function(){ 
		$("#type").change(function(){//事件触发  
			$('option:selected', this).each(function(){
                    var value=this.value;
                    $("#boxlist").submit();
			  });  	
    	   });      
	    });	  
    </script>
</head>
<body>
<@s.form id="boxlist" action="/member_Memberinbox_sendindex.action" method="post">
  <div class="cont_main">
  <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商机管理>我的信箱>发件箱</td>
      </tr>
    </table>
    <div class="finder_bar_bg">
     <ul>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('memberinbox.info_id','/member_Memberinbox_sendindex.action?sendel=true')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="5%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberinbox.info_id')"/>&nbsp;</td>
    <td width="10%" align="center" class="top_td"><@s.select id="type" name="cate_type_s"  list="commparalist" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择类型"/></td>
    <td width="30%" align="center" class="top_td">标题</td>
    <td width="30%" align="center" class="top_td">收件人名称</td>
    <td width="10%" align="center" class="top_td">收件时间</td>    
    <td width="10%" align="center" class="top_td">删除</td>
  </tr>
  <#list memberinboxList as memberinbox>
    <tr bgcolor="<#if memberinbox_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="memberinbox.info_id" value="${memberinbox.info_id?if_exists}"/></td>
    <td align="center">${memberinbox.mess_type?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/member_Memberinbox_sendview.action','memberinbox.info_id=${memberinbox.info_id?if_exists}&type=2');">
    <#if memberinbox.title?if_exists!=''>    
    <#if memberinbox.title?length lt 24>
    ${memberinbox.title?if_exists}
    <#else>
     ${memberinbox.title[0..23]?if_exists}
    </#if>  
    </#if>    
    </a></td>
    <td align="center">
        ${memberinbox.cust_name?if_exists}
    </td>
    <td align="center">${memberinbox.in_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/member_Memberinbox_sendindex.action','memberinbox.info_id=${memberinbox.info_id?if_exists}&sendel=true');" class="dele">删除</a></td>
  </tr>
</#list> 
    </table>
<div class="listbottom">
   ${pageBar?if_exists}
</div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->
<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">信件标题:</td>
		<td><@s.textfield name="title_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td align="right">收件人名称:</td>
		<td><@s.textfield name="cust_name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td align="right">信件类型:</td>
		<td><@s.select id="type" name="type_s"  list="commparalist" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	</tr>
     <td  align="right" >收件时间:</td>
             <td>             
             <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
             </td>
    </tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<!--搜索区域结束-->
</@s.form>
</body>
</html>
