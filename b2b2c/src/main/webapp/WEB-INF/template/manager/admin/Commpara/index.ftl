<html>
  <head>
    <title>参数管理</title>
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
					tip = '确认设置有效吗?';
				}else if(state=='1'){
					tip = '确认设置无效吗?';
				}
				art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
			        document.getElementById('admin_commpara_state').value = state;
				    document.forms[0].action=actionName;
				    document.forms[0].submit();
			        return false;
			    },
			    noText: '关闭',
			    noFn: true //为true等价于function(){}
			    });
				
			}
		}

     function updatesort_no(actionName)
      {  
           var admin_group_id='';
	       var chks =document.getElementsByTagName('input');//得到所有input
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
            document.getElementById('admin_commp_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }
    </script>
  </head>
  <body>
<@s.form action="/admin_Commpara_list.action" method="post">
<@s.hidden name="admin_commp_id" id="admin_commp_id"/>
<@s.hidden name="commpara.enabled" id="admin_commpara_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:系统管理 > 系统工具 > 参数管理
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Commpara_add.action','');">添加参数</a></li>
     
     <li class="sc"><a onclick="delInfo('commpara.para_id','/admin_Commpara_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateState('0','commpara.para_id','/admin_Commpara_updateisshow.action');">有效</a></li>
     <li class="jingyong"><a onclick="updateState('1','commpara.para_id','/admin_Commpara_updateisshow.action');">无效</a></li>
     <li class="xg" ><a onclick="updatesort_no('/admin_Commpara_updatesortno.action');">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('commpara.para_id')"/></td>
    <td width="10%"  align="center" class="top_td">排序</td>
    <td width="10%" align="center" class="top_td">参数编码</td>
    <td width="10%"  align="center" class="top_td">参数名称</td>
    <td width="10%"  align="center" class="top_td">参数键</td>
    <td width="10%"  align="center" class="top_td">参数值</td>
    <td width="10%"  align="center" class="top_td">是否有效</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list commparaList as syscommpara>

    <tr bgcolor="<#if syscommpara_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="commpara.para_id" value="${syscommpara.para_id?if_exists}"/></td>
    <td align="center"><input name="isort_no" value="${syscommpara.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center">${syscommpara.para_code?if_exists}</td>
    <td align="center">${syscommpara.para_name?if_exists}</td>
    <td align="center">${syscommpara.para_key?if_exists}</td>
   <td align="center">${syscommpara.para_value?if_exists}</td>
   <td align="center">

    <a onclick=linkToInfo("/admin_Commpara_list.action","isshow_s=${syscommpara.enabled?if_exists}");>
     <#if syscommpara.enabled?if_exists=='0'>
     
      <font color='green'>有效</font>
      <#else>
      <font color='red'>无效</font>
      </#if> 
      
      </a>
      </td>
    <td align="center">
    <a onclick=linkToInfo("/admin_Commpara_view.action","commpara.para_id=${(syscommpara.para_id)?if_exists}");>
    <img src="/include/images/bj.gif" /></a></td>
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
		<td align="right">参数代码:</td>
		<td><@s.textfield name="para_code_s"/></td>
	</tr>
	<tr>
		<td align="right">参数名称:</td>
		<td><@s.textfield name="para_name_s"/></td>
	</tr>
	<tr>
		<td align="right">参数键:</td>
		<td><@s.textfield name="para_key_s"/></td>
	</tr>
	<tr>
		<td align="right">参数值:</td>
		<td><@s.textfield name="para_value_s"/></td>
	</tr>
		<tr>
         <td class="table_name">是否有效:</td>
          <td>
           <@s.select name="isshow_s" list=r"#{'0':'有效','1':'无效'}" headerKey="" headerValue="请选择"/>  
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
