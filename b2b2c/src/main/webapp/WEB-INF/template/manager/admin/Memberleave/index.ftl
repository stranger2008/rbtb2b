<html>
  <head>
    <title>留言管理</title>
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
			       document.getElementById('admin_memberleave_state').value = state;
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
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>



<@s.form action="/admin_Memberleave_list.action" method="post">
<@s.hidden name="memberleave.is_del" id="admin_memberleave_state"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 留言管理 
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('memberleave.leave_id','/admin_Memberleave_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','memberleave.leave_id','/admin_Memberleave_updateisdel.action');">有效</a></li>
     <li class="jingyong"><a onclick="updateState('1','memberleave.leave_id','/admin_Memberleave_updateisdel.action');">回收站</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberleave.leave_id')"/></td>
    <td width="10%" align="center" class="top_td">留言会员名称</td>
    <td width="15%" align="center" class="top_td">留言用户名称</td>
    <td width="15%"  align="center" class="top_td">留言标题</td>
    <td width="15%"  align="center" class="top_td">接收人</td>
    <td width="15%"  align="center" class="top_td">信息来源</td>
    <td width="10%"  align="center" class="top_td">是否有效</td>
    <td width="10%"  align="center" class="top_td">留言时间</td>
    <td width="5%" align="center" class="top_td">查看</td>
  </tr>
  
  <#list memberleaveList as memberleave>
  <tr>
    <td><input type="checkbox" name="memberleave.leave_id" value="${memberleave.leave_id?if_exists}"/></td>
    <td align="center">${memberleave.cust_name?if_exists}</td>
    <td align="center">${memberleave.send_user_name?if_exists}</td>
    <td align="center">
    <#if memberleave.title?if_exists!=''>
    <#if memberleave.title?length lt 21>
    ${memberleave.title?if_exists}
    <#else>
    ${memberleave.title[0..20]}...
    </#if>
    </#if>
    </td>
     <td align="center">
     <#if memberleave.get_cust_id?if_exists!=''>
    <#if memberleave.get_cust_id?length lt 10>
    ${memberleave.get_cust_id?if_exists}
    <#else>
    ${memberleave.get_cust_id[0..9]}...
    </#if>
    </#if>
    </td>
    <td align="center">
    <#if memberleave.source?if_exists!=''>
    <#if memberleave.source?length lt 8>
    ${memberleave.source?if_exists}
    <#else>
    ${memberleave.source[0..7]}...
    </#if>
    </#if>
    </td>
    <td align="center"><#if memberleave.is_del?if_exists=='0'><a onclick="linkToInfo('/admin_Memberleave_list.action','is_del_s=${memberleave.is_del?if_exists}');"><font color='green'>有效</font></a><#else><a onclick="linkToInfo('/admin_Memberleave_list.action','is_del_s=${memberleave.is_del?if_exists}');"><font color='red'>回收站</font></a></#if> </td>
    <td align="center">${memberleave.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberleave_view.action','memberleave.leave_id=${memberleave.leave_id?if_exists}');"><img src="/include/images/view.gif" /></a></td>	
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
		<td align="right">留言会员名称:</td>
		<td><@s.textfield name="cust_name_s"/></td>
	</tr>
	<tr>
		<td align="right">留言用户名称:</td>
		<td><@s.textfield name="send_user_name_s"/></td>
	</tr>
   	<tr>
         <td align="right">是否有效:</td>
          <td>
           <@s.select name="is_del_s" list=r"#{'0':'有效','1':'回收站'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
    <tr>
     <td align="right" >时间段:</td>
        <td>  
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 	
         至
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