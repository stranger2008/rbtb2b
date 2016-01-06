<html>
  <head>
    <title>会员信息</title>
    <script>
    function selInfo(field_name,actionName){
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
	   art.dialog({
		title: '系统信息提示',
		content: '<div class="decorate">您确定吗？</div>',
		width: '15%',
		icon: 'question',
		yesFn: function () {
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
  </head>
  <body>
<@s.form action="/admin_Emailhistory_indexuser.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 会员信息
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="selInfo('member.email','/admin_Emailhistory_getemail.action')">选中</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.email')"/></td>
    <td width="20%" align="center" class="top_td">会员名称</td>
    <td width="20%"  align="center" class="top_td">会员邮箱</td>
    <td width="20%"  align="center" class="top_td">会员注册时间</td>
  </tr>
  
  <#list memberList as member>
  <tr>
    <td><input type="checkbox" name="member.email" value="${member.email?if_exists}"/></td>
    <td align="center">${member.cust_name?if_exists}</td>
    <td align="center">${member.email?if_exists}</td>
    <td align="center">${member.in_date?if_exists}</td>
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
		<td class="table_name" align="right">会员名称:</td>
		<td><@s.textfield name="user_name_s" maxLength="20"/></td>
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