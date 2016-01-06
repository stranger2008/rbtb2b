  <html>
<head>
<title>店铺栏目管理</title>
    <script type="text/javascript" src="/include/js/bmall/bmallchannel.js"></script>
      <script type="text/javascript" src="/include/js/common.js"></script>
      <script type="text/javascript">
      //批量显示与不显示开始
      function updateIsDis(state,field_name,actionName,is_dis){
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
				tip = '确认显示吗?';
			}else if(state=='1'){
				tip = '确认不显示吗?';
			}
			art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">'+tip+'</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		       document.getElementById(is_dis).value = state;
			   document.forms[0].action=actionName;
			   document.forms[0].submit();
		       return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    });			
		}
     }
    //批量显示与不显示结束
   </script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">店铺栏目管理</a>
    </div>
<@s.form action="/bmall_Memberchannel_bmalllist.action" method="post">
<@s.hidden name="is_dis_update" id="member_memberchannel_state"/>
<@s.hidden id="member_memberchannel_id" name="member_memberchannel_id" />
<@s.hidden name="member_sort" id="member_sort"/>
<@s.hidden name="member_name" id="member_name"/>
<@s.hidden name="member_num" id="member_num"/>
<@s.hidden name="mall_type" value="b2c"/>
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">店铺管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">
	         	栏目管理
	         </td>
       </span>
       </h2>
   
	       <table width="100%" cellspacing="0" border="0">
	         <tr>
	             <td width="5%" class="fthstyle1">
	             </td>
	            <td width="10%"  align="center" class="fthstyle1">排序</td>
			    <td width="45%" align="center" class="fthstyle1">栏目名称</td>
			    <td width="10%"  align="center" class="fthstyle1">栏目类型</td>
			    <td width="10%"  align="center" class="fthstyle1">是否显示</td>
			    <td width="10%"  align="center" class="fthstyle1">数量</td>
			    <td width="10%" align="center" class="fthstyle1">操作</td>
	         <tr>
	         
	         <tr><td colspan="7"  class="manadiv">
	         	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('ch_id')"/>全选
	         	<input type="button" class="submitbut" value="添加栏目" onclick="linkToInfo('/bmall_Memberchannel_add.action','')">
				<input type="button" class="submitbut" value="显示" onclick="updateIsDis('0','ch_id','/bmall_Memberchannel_updateisshow.action','member_memberchannel_state');"/>
				<input type="button" class="submitbut" value="隐藏" onclick="updateIsDis('1','ch_id','/bmall_Memberchannel_updateisshow.action','member_memberchannel_state');"/>
				<input type="button" class="submitbut" value="批量修改" onclick="updatesort_no('/bmall_Memberchannel_updatesortno.action');"/>
	         </td></tr>
	         
	        <#list memberchannelList as memberchannel>
	        <tr>
			    <td><input type="checkbox" name="ch_id" class='ch_box' value="${memberchannel.ch_id?if_exists}"/></td>
			    <td><input id="isort_no" name="isort_no" value="${memberchannel.sort_no?if_exists}" style="width:50px" onkeyup="check_zen_num(this)" maxLength="4"/></td>
			    <td><input name="ch_name" value="${memberchannel.ch_name?if_exists}" style="width:200px" maxLength="20"/></td>
			    <td>
			     <a onclick="linkToInfo('/bmall_Memberchannel_list.action','ch_type_s=${memberchannel.ch_type?if_exists}&mall_type=b2c');">
			    <#if memberchannel.ch_type?if_exists=='0'>   
				    	<font color='red'>菜单</font></#if>
				    <#if memberchannel.ch_type?if_exists=='1'>
				    	<font color='blue'>侧栏</font></#if>
				    <#if memberchannel.ch_type?if_exists=='2'>
				    	<font color='green'>首页主栏</font>
				    </#if>
				</a>
			    </td>
			    <td>
				    <a onclick="linkToInfo('/bmall_Memberchannel_list.action','is_dis_s=${memberchannel.is_dis?if_exists}&mall_type=b2c');">
				    	<#if memberchannel.is_dis?if_exists=='0'>  
					   	 	<font color='green'>显示</font>
					    <#else>
				         	<font color='red'>不显示</font>
					    </#if>
					 </a>
			    </td>
			    <td><input name="chnum" value="${memberchannel.ch_num?if_exists}" style="width:50px" onkeyup="check_zen_num(this)" maxLength="4" /></td>
			    <td>
			    <a onclick="linkToInfo('/bmall_Memberchannel_view.action','memberchannel.ch_id=${memberchannel.ch_id?if_exists}&mall_type=b2c');">修改</a>
			    <#if memberchannel.sys_ch?if_exists=='1'>
	    			<a href="javascript:delOneInfo('memberchannel.ch_id','/bmall_Memberchannel_delete.action?mall_type=b2c','${(memberchannel.ch_id)?if_exists}');">删除</a>
		    	<#else>
		    		系统栏目
		    	</#if>
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
