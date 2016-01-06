<html>
  <head>
    <title>系统模块</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
    <script type="text/javascript">
      //批量启用与禁用开始
      function updateIsuse(state,field_name,actionName,recom_id){
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
			return false;
		}
		if(flag==true){
		    var tip = '';
			if(state=='0'){
				tip = '确认启用吗?';
			}else if(state=='1'){
				tip = '确认禁用吗?';
			}
			art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">'+tip+'</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		       document.getElementById(recom_id).value = state;
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
<@s.form action="/admin_Sysmodule_list.action" method="post">
<@s.hidden name="sysmodule.state_code" id="admin_Sysmodule_state"/>
<@s.hidden name="mod_type" id="scoreid"/>
<@s.hidden name="mod_sort_no" id="scoreNum"/> 
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 系统模块设置
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li><a onclick="updateSort('/admin_Sysmodule_updateSort.action');">排序</a></li>
     <li class="qiyong"><a onclick="updateIsuse('0','sysmodule.module_type','/admin_Sysmodule_updateisuse.action','admin_Sysmodule_state');">启用</a></li>
     <li class="jingyong"><a onclick="updateIsuse('1','sysmodule.module_type','/admin_Sysmodule_updateisuse.action','admin_Sysmodule_state');">禁用</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td">
    <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sysmodule.module_type')"/></td>
    <td width="10%"  align="center" class="top_td">排序</td>
    <td width="10%"  align="center" class="top_td">模块编码</td>
    <td width="10%"  align="center" class="top_td">模块名称</td>
    <td width="10%"  align="center" class="top_td">表名</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">支持分类属性</td>
    <td width="10%"  align="center" class="top_td">是否属于会员</td>   
    <td width="10%"  align="center" class="top_td">修改</td>
  </tr>
  
  <#list sysmoduleList as mod>
   <tr bgcolor="<#if mod_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	    <td ><input type="checkbox" name="sysmodule.module_type" value="${mod.module_type?if_exists}"/></td>
	    <td align="center">
			<input type="hidden" name="module_type_val" value="${mod.module_type?if_exists}"/>
			<input class="textsort" style="width:36px;" onkeyup="checkNum(this)" name="sort_no" value="${mod.sort_no?if_exists}">
		</td>	
		<td align="center">${mod.module_type?if_exists}</td>
	    <td align="center">${mod.module_name?if_exists}</td>
		<td align="center">${mod.table_name?if_exists}</td>
		<td align="center">
			<#if mod.state_code=='0'><span class="greencolor">启用</span><#else><span class="redcolor">禁用</span></#if>
		</td>
		<td align="center">
			<#if mod.is_catattr=='0'><span class="greencolor">是</span><#else><span class="redcolor">否</span></#if>
		</td>
		<td align="center">
			<#if mod.is_mem=='0'><span class="greencolor">是</span><#else><span class="redcolor">否</span></#if>
		</td>
	
	    <td align="center">	    
	      <a style="display:none;" onclick=linkToInfo("/admin_Sysmodule_view.action","sysmodule.module_type=${mod.module_type?if_exists}"); title="修改"><img src="/include/images/bj.gif" /></a>
	      <a onclick=linkToInfo("/admin_Sysmodule_audit.action","sysmodule.module_type=${mod.module_type?if_exists}"); title="查看"><img src="/include/images/view.gif" /></a>
	      <a onclick=linkToInfo("/admin_Sysconfig_list.action","module_type_s=${mod.module_type?if_exists}"); title="模块设置"><img src="/include/images/admin/medit.gif" /></a>       
	    </td>    	    
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
</@s.form>
    <script type="text/javascript">
      <!--分类排序方法-->
      function updateSort(actionName)      
      {  
 	       art.dialog({
		   title: '系统信息提示',
	       content: '<div class="decorate">您确定要排序吗？</div>',
	       width: '15%',
	       icon: 'question',
	       yesFn: function () {
	           var admin_group_id='';
	           var admin_group_num='';
		       $("input:hidden[name='module_type_val']").each(function(){	                 	  
	                 admin_group_id+=this.value+',';	 
		       });		       
		       $("input:text").each(function(){	                 	  
	                 admin_group_num+=this.value+',';     
		       });		       
	           var len=admin_group_id.length;
	           var lenNum=admin_group_num.length;
	           admin_group_id=admin_group_id.substring(0,len-1);
	           admin_group_num=admin_group_num.substring(0,lenNum-1);        
	           document.getElementById('scoreid').value = admin_group_id;//用于隐藏所有的ID
	           document.getElementById('scoreNum').value = admin_group_num;//用于隐藏文本所有的值
			   document.forms[0].action=actionName;			   		 
			   document.forms[0].submit();
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    });	   
     } 
</script> 
  </body>
</html>