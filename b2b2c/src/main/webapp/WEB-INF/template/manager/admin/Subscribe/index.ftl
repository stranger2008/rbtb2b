<html>
  <head>
    <title>商机订阅</title>
        <script>
    	//批量操作
    	function updateState(state,field_name,actionName){
			var flag = false;
			var checks = document.getElementsByName(field_name);
			for(var i=0;i<checks.length;i++){
				if(checks[i].checked){
					flag=true;
					break;
				}
			}
			if(flag==false){
				alertShow('请至少选择一条记录!','warning');
		        runCount(3);
				return false;
			}
			if(flag==true){
			    var tip = '';
				if(state=='0'){
					tip = '确认有效吗?';
				}else if(state=='1'){
					tip = '确认放入回收站吗?';
				}
			    art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
			       document.getElementById('admin_subscribe_state').value = state;
				   document.forms[0].action=actionName;
				   document.forms[0].submit();
			        return false;
			    },
			    noText: '关闭',
			    noFn: true //为true等价于function(){}
			    });
			}
			
		}
    </script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>



<@s.form action="/admin_Subscribe_list.action" method="post">
<@s.hidden name="subscribe.enabled" id="admin_subscribe_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 商机订阅
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Subscribe_add.action','');">添加订阅</a></li>
     <li class="xg"><a onclick="linkToInfo('/admin_Subscribeinfo_list.action','');">邮件记录</a></li>
     
     <li class="sc"><a onclick="delInfo('subscribe.sub_id','/admin_Subscribe_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','subscribe.sub_id','/admin_Subscribe_updateenabled.action');">有效</a></li>
     <li class="jingyong"><a onclick="updateState('1','subscribe.sub_id','/admin_Subscribe_updateenabled.action');">回收站</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subscribe.sub_id')"/></td>
    <td width="10%" align="center" class="top_td">会员名称</td>
    <td width="20%" align="center" class="top_td">关键字</td>
    <td width="10%" align="center" class="top_td">频率(天数)</td>
    <td width="10%" align="center" class="top_td">信息类型</td>
    <td width="10%" align="center" class="top_td">发送类型</td>
    <td width="10%" align="center" class="top_td">是否有效</td>
    <td width="10%" align="center" class="top_td">订阅时间</td>
    <td width="5%"  align="center" class="top_td">修改</td>
  </tr>
  
  <#list subscribeList as subscribe>
  <tr>
    <td><input type="checkbox" name="subscribe.sub_id" value="${subscribe.sub_id?if_exists}"/></td>
    <td align="center"><a onclick="linkToInfo('/admin_Subscribe_view.action','subscribe.sub_id=${subscribe.sub_id?if_exists}');">${subscribe.cust_name?if_exists}</a></td>
    <td align="center">
    <#if subscribe.keyword?if_exists!=''>
    <#if subscribe.keyword?length lt 31>
    ${subscribe.keyword?if_exists}
    <#else>
    ${subscribe.keyword[0..30]}...
    </#if>
    </#if></td>
    <td align="center">${subscribe.period?if_exists}</td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','info_type_s=${subscribe.info_type?if_exists}');">
    		<#if subscribe.info_type?if_exists=='0'>
    			<font color='green'>供应</font>
    		<#else>
    			<font color='red'>求购</font>
    		</#if>
    	</a>
    </td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','send_type_s=${subscribe.send_type?if_exists}');">
    		<#if subscribe.send_type?if_exists=='0'>
    			<font color='green'>邮件</font>
    		<#else>
    			<font color='red'>站内信</font>
    		</#if> 
    	</a>
    </td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Subscribe_list.action','enabled_s=${subscribe.enabled?if_exists}');">
    		<#if subscribe.enabled?if_exists=='0'>
    			<font color='green'>有效</font>
    		<#else>
    			<font color='red'>回收站</font>
    		</#if> 
    	</a>
    </td>
    <td align="center">${subscribe.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Subscribe_view.action','subscribe.sub_id=${subscribe.sub_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
  </tr>
  </#list>
  
  
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">关键字:</td>
		<td><@s.textfield name="keyword_s"/></td>
	</tr>
	<tr>
	<td align="right">信息类型:</td>
	<td><@s.select id="info_type_s" name="info_type_s" list=r"#{'0':'供应','1':'求购'}"  headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
	<td align="right">发送类型:</td>
	<td><@s.select id="send_type_s" name="send_type_s" list=r"#{'0':'邮件','1':'站内信'}"  headerKey="" headerValue="请选择"/></td>
	</tr>
	<tr>
	<td align="right">是否有效:</td>
	<td><@s.select id="endabled_s" name="enabled_s" list=r"#{'0':'有效','1':'无效'}"  headerKey="" headerValue="请选择"/></td>
	</tr>
    <tr>
   <td align="right" >时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />至
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