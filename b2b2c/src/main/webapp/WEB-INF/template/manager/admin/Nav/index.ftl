<html>
  <head>
    <title>导航管理</title>
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
			        document.getElementById('admin_nav_state').value = state;
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
            document.getElementById('admin_nav_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }

    </script>
  </head>
  <body>



<@s.form action="/admin_Nav_list.action" method="post">
<@s.hidden name="nav.isshow" id="admin_nav_state"/>
<@s.hidden name="admin_nav_id" id="admin_nav_id"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 导航管理  
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj">
     <a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Nav_add.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_malladd.action'</#if>,'');">添加导航</a>
     </li>
     <li class="sc"><a onclick="delInfo('nav.nav_id',<#if mall_type?if_exists=='b2b'>'/admin_Nav_delete.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_malldelete.action'</#if>)">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong">
     <a onclick="updateState('0','nav.nav_id',<#if mall_type?if_exists=='b2b'>'/admin_Nav_updateisshow.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_mallupdateisshow.action'</#if>);">显示</a></li>
     <li class="jingyong"><a onclick="updateState('1','nav.nav_id',<#if mall_type?if_exists=='b2b'>'/admin_Nav_updateisshow.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_mallupdateisshow.action'</#if>);">不显示</a></li>
     <li class="xg" ><a onclick="updatesort_no(<#if mall_type?if_exists=='b2b'>'/admin_Nav_updatesortno.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_mallupdatesortno.action'</#if>);">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('nav.nav_id')"/></td>
    <td width="10%"  align="center" class="top_td">排序</td>
    <td width="10%" align="center" class="top_td">导航名称</td>
    <td width="10%"  align="center" class="top_td">导航链接地址</td>
    <td width="10%"  align="center" class="top_td">导航位置</td>
    <td width="10%"  align="center" class="top_td">是否显示</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list navList as nav>
  <tr>
    <td><input type="checkbox" name="nav.nav_id" value="${nav.nav_id?if_exists}"/></td>
    <td align="center"><input name="isort_no" value="${nav.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center">${nav.nav_name?if_exists}</td>
    <td align="center">${nav.link_url?if_exists}</td>
    <td align="center">
	    <a onclick="linkToInfo(<#if (mall_type?if_exists)=='b2b'>'/admin_Nav_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_malllist.action'</#if>,'nav_post_s=${nav.nav_post?if_exists}');">
	    	<#if nav.nav_post?if_exists=='1'>
	    		<font color='red'>上部</font>
	    	</#if>
	    	<#if nav.nav_post?if_exists=='2'>
	    		<font color='blue'>中部</font>
	    	</#if>
	    	<#if nav.nav_post?if_exists=='3'>
	    		<font color='green'>下部</font>
	    	</#if>
	    </a>
    </td>
    <td align="center">
	    <a onclick="linkToInfo(<#if (mall_type?if_exists)=='b2b'>'/admin_Nav_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_malllist.action'</#if>,'isshow_s=${nav.isshow?if_exists}');">
	    	<#if nav.isshow?if_exists=='0'>
	    		<font color='green'>显示</font>
	    	<#else>
	    		<font color='red'>不显示</font>
	    	</#if>
	    </a>
    </td>
    <td align="center">
    <a onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Nav_view.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Nav_mallview.action'</#if>,'nav.nav_id=${nav.nav_id?if_exists}');">
    		<img src="/include/images/bj.gif" />
    </a>
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
 <@s.hidden name="mall_type" value="${mall_type}" />

<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">导航名称:</td>
		<td><@s.textfield name="nav_name_s"/></td>
	</tr>
	<tr>
		<td align="right">导航链接地址:</td>
		<td><@s.textfield name="link_url_s"/></td>
	</tr>
	<tr>
             <td class="table_name">导航位置:</td>
             <td>
             	<@s.select name="nav_post_s" list=r"#{'1':'上部','2':'中部','3':'下部'}" headerKey="" headerValue="请选择"/>  
             </td>
           </tr>
   	<tr>
         <td class="table_name">是否显示:</td>
          <td>
           <@s.select name="isshow_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  
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