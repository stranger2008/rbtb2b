<html>
  <head>
    <title>会员商机订阅</title>
      <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
      <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
      <script type="text/javascript">
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
  </head>
  <body>
<@s.form action="/member_Subscribe_list.action" method="post">

<@s.hidden name="subscribe.enabled" id="admin_subscribe_state"/>
  <div class="cont_main">
  
  <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商机管理>商机快递>商机订阅</td>
      </tr>
    </table>
  
  
  
  <div class="finder_bar_bg">
   <ul >
       <li class="cont_first_li">
       <a onclick="linkToInfo('/member_Subscribe_add.action','');">添加订阅</a></li>
       <li><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
       <li><a onclick="delInfo('subscribe.sub_id','/member_Subscribe_delete.action')">删除</a></li>
   </ul>
 </div>
  <table width="100%" cellspacing="0" border="0">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('subscribe.sub_id')"/></td>
    <td width="10%" align="center" class="top_td">会员名称</td>
    <td width="10%"  align="center" class="top_td">信息类型</td>
    <td width="10%"  align="center" class="top_td">关键字</td>
    <td width="10%"  align="center" class="top_td">发送类型</td>
    <td width="10%"  align="center" class="top_td">是否有效</td>
    <td width="10%"  align="center" class="top_td">订阅时间</td>
    <td width="10%" align="center" class="top_td">编辑</td>
  </tr>
  <#list subscribeList as subscribe>
  <tr>
    <td><input type="checkbox" name="subscribe.sub_id" value="${subscribe.sub_id?if_exists}"/></td>
    <td align="center">${subscribe.cust_name?if_exists}</td>
    <td align="center">
    <a onclick=linkToInfo("/member_Subscribe_list.action","info_type_s=${subscribe.info_type?if_exists}");>
    <#if subscribe.info_type?if_exists=='0'>
    <font color='green'>供应</font>
    <#else>
    <font color='red'>求购</font></#if> 
    </a>
    
    </td>
    <td align="center">${subscribe.keyword?if_exists}</td>
    <td align="center">
    <a onclick=linkToInfo("/member_Subscribe_list.action","send_type_s=${subscribe.send_type?if_exists}");>
       <#if subscribe.send_type?if_exists=='0'>
       <font color='green'>邮件</font>
       <#else>
       <font color='red'>站内信</font>
      </#if> 
      </a>
     </td>
    <td align="center">
    <a onclick=linkToInfo("/member_Subscribe_list.action","enabled_s=${subscribe.enabled?if_exists}");>
    <#if subscribe.enabled?if_exists=='0'>
    <font color='green'>有效</font>
    <#else>
    <font color='red'>回收站</font>
    </#if> 
    </a>
    </td>
    <td align="center">${subscribe.in_date.toString().substring(0,10)?if_exists}</td>
    <td align="center">
    
    <a class="xg" onclick=linkToInfo("/member_Subscribe_view.action","subscribe.sub_id=${subscribe.sub_id?if_exists}");>修改</a>
    <a href="javascript:delOneInfo('subscribe.sub_id','/member_Subscribe_delete.action','${(subscribe.sub_id)?if_exists}');" class="dele">删除</a></td>
  </tr>
  </#list>
</table>

 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>


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
    	<td align="right" >订阅日期:</td>
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