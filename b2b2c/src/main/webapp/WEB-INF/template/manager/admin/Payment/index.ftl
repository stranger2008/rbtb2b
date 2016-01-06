<html>
  <head>
    <title>支付方式管理</title>
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
					tip = '确认启用吗?';
				}else if(state=='1'){
					tip = '确认不启用吗?';
				}
				art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
			        document.getElementById('admin_payment_state').value = state;
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



<@s.form action="/admin_Payment_list.action" method="post">
<@s.hidden name="payment.enabled" id="admin_payment_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 支付方式管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('payment.pay_id','/admin_Payment_delete.action')">删除</a></li>
     <li class="qiyong"><a onclick="updateState('0','payment.pay_id','/admin_Payment_updateenabled.action');">启用</a></li>
     <li class="jingyong"><a onclick="updateState('1','payment.pay_id','/admin_Payment_updateenabled.action');">不启用</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
     <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('payment.pay_id')"/></td>
    <td width="10%"  align="center" class="top_td">支付接口编码</td>
    <td width="10%"  align="center" class="top_td">支付方式</td>
    <td width="30%"  align="center" class="top_td">描述</td>
    <td width="10%"  align="center" class="top_td">是否启用</td>
    <td width="12%" align="center" class="top_td">修改</td>
  </tr>
 <#list paymentList as payment>
  <tr>
    <td><input type="checkbox" name="payment.pay_id" value="${payment.pay_id?if_exists}"/></td>
    <td align="center">${payment.pay_code?if_exists}</td>
    <td align="center">
    <#if payment.pay_name?if_exists!=''>
    <#if payment.pay_name?length lt 8>
    <a href="/admin_Payment_view.action?payment.pay_id=${payment.pay_id?if_exists}">${payment.pay_name?if_exists}</a>
    <#else>
    <a href="/admin_Payment_view.action?payment.pay_id=${payment.pay_id?if_exists}">${payment.pay_name[0..7]}</a>
    </#if>
    </#if></td>
    <td align="center">
    
    <#if payment.pay_desc?if_exists!=''>
    <#if payment.pay_desc?length lt 30>
    ${payment.pay_desc?if_exists}
    <#else>
    ${payment.pay_desc[0..29]}
    </#if>
    </#if>
    </td>
    <td align="center"><#if payment.enabled?if_exists=='0'><a onclick="linkToInfo('/admin_Payment_list.action','enabled_s=${payment.enabled?if_exists}');"><font color='green'>是</font></a>
    <#else><a onclick="linkToInfo('/admin_Payment_list.action','enabled_s=${payment.enabled?if_exists}');"><font color='red'>否</font></a></#if></td>
    <td align="center"><a onclick="linkToInfo('/admin_Payment_view.action','payment.pay_id=${payment.pay_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
<!--
<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">支付接口编码:</td>
		<td><@s.textfield name="pay_code_s"/></td>
	</tr>
	<tr>
		<td align="right">支付方式:</td>
		<td><@s.textfield name="pay_name_s"/></td>
	</tr>
    <tr>
		<td align="right">支付账号:</td>
		<td><@s.textfield name="pay_account_s"/></td>
	</tr>
   	<tr>
         <td class="table_name">是否启用:</td>
          <td>
           <@s.select name="enabled_s" list=r"#{'0':'是','1':'否'}" headerKey="" headerValue="请选择"/>  
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
-->
<!--搜索区域结束-->
</@s.form>

  </body>
</html>