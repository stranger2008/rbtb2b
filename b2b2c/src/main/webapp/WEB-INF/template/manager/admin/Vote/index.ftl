
<html>
  <head>
    <title>在线调查管理</title>
     <script>
    	//单选多选批量操作
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
				if(state=='checkbox'){
					tip = '确认多选吗?';
				}else if(state=='radio'){
					tip = '确认单选吗?';
				}
				art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
			        document.getElementById('admin_vote_state').value = state;
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



<@s.form action="/admin_Vote_list.action" method="post">
<@s.hidden name="vote.is_multi" id="admin_vote_state"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 基本功能 > 在线调查管理 
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Vote_add.action','');">添加调查</a></li>
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="sc"><a onclick="delInfo('vote.vote_id','/admin_Vote_delete.action')">删除</a></li>
     <li class="jingyong"><a onclick="updateState('radio','vote.vote_id','/admin_Vote_updateis_multiState.action');">单选</a></li>
     <li class="qiyong"><a onclick="updateState('checkbox','vote.vote_id','/admin_Vote_updateis_multiState.action');">多选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('vote.vote_id')"/></td>
    <td width="20%" align="center" class="top_td">调查主题</td>
    <td width="15%"  align="center" class="top_td">是否支持多选</td>
    <td width="15%"  align="center" class="top_td">投票数</td>
    <td width="15%"  align="center" class="top_td">开始时间</td>
    <td width="15%"  align="center" class="top_td">结束时间</td>
    <td width="5%" align="center" class="top_td">修改</td>
    <td width="5%" align="center" class="top_td">选项</td>
    <td width="5%" align="center" class="top_td">记录</td>
  </tr>
  
  <#list voteList as vote>
  <tr>
    <td><input type="checkbox" name="vote.vote_id" value="${vote.vote_id?if_exists}"/></td>
    <td align="center"><a onclick="linkToInfo('/admin_Vote_view.action','vote.vote_id=${vote.vote_id?if_exists}');">${vote.vote_title?if_exists}</a></td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Vote_list.action','is_multi_s=${vote.is_multi?if_exists}');">
	    	<#if vote.is_multi?if_exists=='checkbox'>
	    		<font color='green'>多选</font>
	    	<#else>
	    		<font color='red'>单选</font>
	    	</#if> 
    	</a>
    </td>
    <td align="center">${vote.vote_count?if_exists}</td>
    <td align="center">${vote.start_date.toString().substring(0,10)?if_exists}</td>
    <td align="center">${vote.end_date.toString().substring(0,10)?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Vote_view.action','vote.vote_id=${vote.vote_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Voteoption_list.action','vote_id=${vote.vote_id?if_exists}&vote_id_s=${vote.vote_id?if_exists}');"><img src="/include/images/admin/options.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Votelog_list.action','vote_id=${vote.vote_id?if_exists}&vote_id_s=${vote.vote_id?if_exists}');"><img src="/include/images/admin/record.gif" /></a></td>
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
		<td class="table_name" align="right">调查主题:</td>
		<td><@s.textfield name="vote_title_s"/></td>
	</tr>
               <tr>
             <td class="table_name">是否多选:</font></td>
             <td>
             	<@s.select name="vote_is_multi_s" list=r"#{'radio':'单选','checkbox':'多选'}" headerKey="" headerValue="请选择"/>  
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