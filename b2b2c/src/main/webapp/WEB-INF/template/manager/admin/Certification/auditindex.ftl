<html>
  <head>
    <title>会员实名认证审核</title>

	<script type="text/javascript">
  
    </script>
  </head>
  <body>
	<@s.form action="/admin_Certification_auditList.action" method="post" validate="true">
	<div class="main_index f_left">
	  <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 实名认证审核列表
      </div>
 		<div class="main_top">
   		<ul class="main_top_ul">
     		
     		<li class="sc"><a onclick="delInfo('certification.cust_id','/admin_Certification_delete.action')">删除</a></li>
     		<li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   		</ul>
 		</div>
 <div class="main_cont">

 <table width="100%" border="0" cellspacing="0" class="indexTab">
	  <tr style="background:url(images/top_tr.gif) repeat-x;">
	    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('certification.cust_id')"/></td>
	    <td width="20%" align="center" class="top_td">公司名称</td>
	    <td width="20%"  align="center" class="top_td">法定代表人</td>
	    <td width="15%"  align="center" class="top_td">企业类型</td>
	    <td width="15%"  align="center" class="top_td">注册资本</td>
	    <td width="10%" align="center" class="top_td">申请人姓名</td>
	    <td width="10%" align="center" class="top_td">状态</td>
	    <td width="10%" align="center" class="top_td">审核</td>
	  </tr>
	  
	  <#list certificationList as cert>
		  <tr bgcolor="<#if cert_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
		    <td><input type="checkbox" name="certification.cust_id" value="${cert.cust_id?if_exists}"/></td>
		    <td align="center">
		    		${cert.cust_name?if_exists}
		    </td>
		    <td align="center">
		    	  ${cert.corporate?if_exists}
		    </td>
		    <td align="center">
		    	 ${cert.para_key?if_exists}
		    </td>
		    <td align="center">
		       ${cert.reg_money?if_exists}万
		    </td>
		    <td align="center">
		       ${cert.app_name?if_exists}
		    </td>
		    <td  align="center">
		    <a onclick="linkToInfo('/admin_Certification_auditList.action','state_s=${cert.info_state?if_exists}');">
		    <#if cert.info_state?if_exists=='0'><font color="#999999">新加入</font></a></#if>
		    <#if cert.info_state?if_exists=='1'><font class="greencolor">认证中</font></a></#if>
		    <#if cert.info_state?if_exists=='2'><font class="bluecolor">被驳回</font></a></#if>
		    <#if cert.info_state?if_exists=='3'><font class="redcolor">已通过</font></a></#if>    
		    </td>
		    <td align="center"><a onclick="linkToInfo('/admin_Certification_audit.action','certification.cust_id=${cert.cust_id?if_exists}')"><img src="/include/images/audit.png" /></a></td>
  </tr>
	  </#list>
</table>
 </div>
  <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>

<!--搜索区域开始-->


<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right" style="width:90px;">公司名称:</td>
		<td><@s.textfield name="cust_name_s" cssStyle="width:260px;"/></td>
	</tr>
	<tr>
		<td align="right">法定代表人:</td>      
		<td><@s.textfield name="corporate_s"/></td>
	</tr>
	<tr>
		<td align="right">企业类型:</td>
		<td><@s.select name="cust_type_s"  list="cust_type_List" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" />  </td>
	</tr>
	<tr>
		<td align="right">申请人姓名:</td>
		<td><@s.textfield name="app_name_s"/></td>
	</tr>
	 <tr>
    <td align="right">搜索状态:</td>
	<td>
		     <@s.select name="state_s" list=r"#{'':'请选择','0':'新加入','1':'认证中','2':'被驳回','3':'已通过'}" />
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
</div>
</div>
<div class="clear"></div>
	</@s.form>	
  </body>
</html>