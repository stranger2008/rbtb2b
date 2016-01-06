<html>
  <head>
    <title>栏目设置</title>
      <script type="text/javascript" src="/include/js/member/memberchannel.js"></script>
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
<@s.form action="/member_Memberchannel_list.action" method="post">
<@s.hidden name="is_dis_update" id="member_memberchannel_state"/>
<@s.hidden name="member_memberchannel_id" id="member_memberchannel_id"/>
<@s.hidden name="member_sort" id="member_sort"/>
<@s.hidden name="member_name" id="member_name"/>
<@s.hidden name="member_num" id="member_num"/>
  <div class="cont_main">
    <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>店铺设置>栏目设置</td>
 	</tr>
	</table>
    <div class="finder_bar_bg">
    <ul>
     <li class="tj"><a href="/member_Memberchannel_add.action">添加栏目</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateIsDis('0','ch_id','/member_Memberchannel_updateisshow.action','member_memberchannel_state');">显示</a></li>
     <li class="jingyong"><a onclick="updateIsDis('1','ch_id','/member_Memberchannel_updateisshow.action','member_memberchannel_state');">隐藏</a></li>
     <li class="shuaix" ><a onclick="updatesort_no('/member_Memberchannel_updatesortno.action');">修改</a></li>
    </ul>
 </div>
   <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('ch_id')"/></td>
    <td width="10%"  align="center" class="top_td">排序</td>
    <td width="10%" align="center" class="top_td">栏目名称</td>
    <td width="10%"  align="center" class="top_td">栏目类型</td>
    <td width="10%"  align="center" class="top_td">是否显示</td>
    <td width="10%"  align="center" class="top_td">数量</td>
    <td width="10%" align="center" class="top_td">修改</td>
    <td width="10%" align="center" class="top_td">删除</td>
  </tr>
  
  <#list memberchannelList as memberchannel>
  <tr>
    <td><input type="checkbox" name="ch_id" value="${memberchannel.ch_id?if_exists}"/></td>
    <td align="center"><input id="isort_no" name="isort_no" value="${memberchannel.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
    <td align="center"><input name="ch_name" value="${memberchannel.ch_name?if_exists}" style="width:120px" /></td>
    <td align="center">
     <a onclick="linkToInfo('/member_Memberchannel_list.action','ch_type_s=${memberchannel.ch_type?if_exists}');">
    <#if memberchannel.ch_type?if_exists=='0'>   
	    	<font color='red'>菜单</font></#if>
	    <#if memberchannel.ch_type?if_exists=='1'>
	    	<font color='blue'>侧栏</font></#if>
	    <#if memberchannel.ch_type?if_exists=='2'>
	    	<font color='green'>首页主栏</font>
	    </#if>
	</a>
    </td>
    <td align="center">
	    <a onclick="linkToInfo('/member_Memberchannel_list.action','is_dis_s=${memberchannel.is_dis?if_exists}');">
	    	<#if memberchannel.is_dis?if_exists=='0'>  
		   	 	<font color='green'>显示</font>
		    <#else>
	         	<font color='red'>不显示</font>
		    </#if>
		 </a>
    </td>
    <td align="center">
    	<#if  memberchannel.ch_code=='cptj'>
    	<input name="chnum" value="${memberchannel.ch_num?if_exists}" style="width:50px" onkeyup="checkNum(this)" disabled='true'/>
    	<#else>
    	<input name="chnum" value="${memberchannel.ch_num?if_exists}" style="width:50px" onkeyup="checkNum(this)" />
    	</#if>
    </td>
    <td align="center">
    <a onclick="linkToInfo('/member_Memberchannel_view.action','memberchannel.ch_id=${memberchannel.ch_id?if_exists}');"><img src="/include/images/bj.gif" /></a></td>
    <td align="center">
    	<#if memberchannel.sys_ch?if_exists=='1'>
    		<a href="javascript:delOneInfo('memberchannel.ch_id','/member_Memberchannel_delete.action','${(memberchannel.ch_id)?if_exists}');" class="dele">删除</a>
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
         <td class="table_name">是否显示:</td>
          <td>
           <@s.select name="is_dis_s" list=r"#{'0':'显示','1':'不显示'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
	<tr>
             <td class="table_name">栏目类型:</td>
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