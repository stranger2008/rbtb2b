<html>
  <head>
    <title>栏目配置管理</title>
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
                   document.getElementById('admin_memberchconfig_state').value = state;
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
    <script type="text/javascript">
      function updatesort_no(actionName)
      {  
        var admin_group_id='';
        var chks =document.getElementsByTagName('input');//得到所有input
        var inputnum =document.getElementsByTagName('input');//得到所有input
       for(var i=0;i <chks.length;i++)
       { 
        
       if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
       {
            //得到所名为checkbox的input
            admin_group_id+=chks[i].value+',';
           
         }
       }
        var len=admin_group_id.length;
        var group_id=admin_group_id.substring(0,len-1);
        document.getElementById('admin_memberchconfig_id').value = group_id;//用于隐藏所有的ID
		document.forms[0].action=actionName;
		document.forms[0].submit();
      }
    </script>
  </head>
  <body>



<@s.form action="/admin_Memberchconfig_list.action" method="post">
<@s.hidden name="memberchconfig.is_dis" id="admin_memberchconfig_state"/>
<@s.hidden name="admin_memberchconfig_id" id="admin_memberchconfig_id"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 栏目配置管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Memberchconfig_add.action','');">添加栏目</a></li>
     <li class="sc"><a onclick="delInfo('memberchconfig.ch_id','/admin_Memberchconfig_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','memberchconfig.ch_id','/admin_Memberchconfig_updateisshow.action');">显示</a></li>
     <li class="jingyong"><a onclick="updateState('1','memberchconfig.ch_id','/admin_Memberchconfig_updateisshow.action');">不显示</a></li>
     <li class="shuaix" ><a onclick="updatesort_no('/admin_Memberchconfig_updatesortno.action');">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memberchconfig.ch_id')"/></td>
    <td width="10%"  align="center" class="top_td">排序</td>
    <td width="10%" align="center" class="top_td">栏目名称</td>
    <td width="10%"  align="center" class="top_td">栏目编码</td>
    <td width="10%"  align="center" class="top_td">栏目类型</td>
    <td width="10%"  align="center" class="top_td">所属平台</td>
    <td width="10%"  align="center" class="top_td">是否显示</td>
    <td width="10%"  align="center" class="top_td">数量</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list memberchconfigList as memberchconfig>
  <tr>
    <td><input type="checkbox" name="memberchconfig.ch_id" value="${memberchconfig.ch_id?if_exists}"/></td>
    <td align="center"><input name="isort_no" value="${memberchconfig.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberchconfig_view.action','memberchconfig.ch_id=${memberchconfig.ch_id?if_exists}');">${memberchconfig.ch_name?if_exists}</a></td>
    <td align="center">${memberchconfig.ch_code?if_exists}</td>
    <td align="center"><#if memberchconfig.ch_type?if_exists=='0'><a onclick="linkToInfo('/admin_Memberchconfig_list.action','ch_type_s=${memberchconfig.ch_type?if_exists}"><font color='red'>菜单</font></a></#if><#if memberchconfig.ch_type?if_exists=='1'><a onclick="linkToInfo('/admin_Memberchconfig_list.action','ch_type_s=${memberchconfig.ch_type?if_exists}"><font color='blue'>侧栏</font></a></#if><#if memberchconfig.ch_type?if_exists=='2'><a onclick="linkToInfo('/admin_Memberchconfig_list.action','ch_type_s=${memberchconfig.ch_type?if_exists}"><font color='green'>首页主栏</font></a></#if></td>
     <td align="center">${memberchconfig.plat_type?if_exists}</td>
    <td align="center"><#if memberchconfig.is_dis?if_exists=='0'><a onclick="linkToInfo('/admin_Memberchconfig_list.action','is_dis_s=${memberchconfig.is_dis?if_exists}"><font color='green'>显示</font></a><#else><a onclick="linkToInfo('/admin_Memberchconfig_list.action','is_dis_s=${memberchconfig.is_dis?if_exists}"><font color='red'>不显示</font></a></#if></td>
    <td align="center">${memberchconfig.ch_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberchconfig_view.action','memberchconfig.ch_id=${memberchconfig.ch_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">栏目名称:</td>
		<td><@s.textfield name="ch_name_s"/></td>
	</tr>
	<tr>
		<td align="right">栏目编码:</td>
		<td><@s.textfield name="ch_code_s"/></td>
	</tr>
   	<tr>
         <td align="right">是否显示:</td>
          <td>
           <@s.select name="is_dis_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
	<tr>
             <td align="right">栏目名称:</td>
             <td>
             	<@s.select name="ch_type_s" list=r"#{'0':'菜单','1':'侧栏','2':'首页'}" headerKey="" headerValue="请选择"/>  
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