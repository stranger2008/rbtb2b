<html>
  <head>
    <title>配送方式表列表</title>   
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
      <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
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
            document.getElementById('sendmode_sortno_id').value = group_id;//用于隐藏所有的ID
			document.forms[0].action=actionName;
			document.forms[0].submit();
      }

    </script>
  </head>
  <body>
<@s.form action="/admin_Sendmode_list.action" method="post">
<@s.hidden name="sendmode_sortno_id" id="sendmode_sortno_id"/>
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统配置 > 配送方式列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Sendmode_add.action','');">添加配送方式</a></li>
     <li class="sc"><a onclick="delInfo('sendmode.smode_id','/admin_Sendmode_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="xg" ><a onclick="updatesort_no('/admin_Sendmode_updatesortno.action');">排序</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sendmode.smode_id')"/></td>
    
         <td width="3%"  align="center" class="top_td">排序</td>
     	 <td width="20%"  align="center" class="top_td">配送方式名称</td>
    
     	 <td width="10%"  align="center" class="top_td">是否支持保价</td>
    
     	 <td width="8%"  align="center" class="top_td">保价费率</td>
    
     	 <td width="8%"  align="center" class="top_td">最低保价额</td>
    
     	 <td width="8%"  align="center" class="top_td">最高保价额</td>
    
     	 <td width="10%"  align="center" class="top_td">是否支持货到付款</td>
    
    
    <td width="15%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list sendmodeList as sendmode>
  <tr bgcolor="<#if sendmode_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="sendmode.smode_id" value="${sendmode.smode_id?if_exists}"/></td>    
 	
   		 <td align="center"><input name="isort_no" value="${sendmode.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
   		 
    	<td align="center">
    	  <a onclick="linkToInfo('/admin_Sendmode_view.action','sendmode.smode_id=${sendmode.smode_id?if_exists}');">
    	${sendmode.smode_name?if_exists}
    	</a>
    	</td>
    
    	<td align="center">
    	
    	<#if sendmode.is_insured?if_exists='0'>
	    <a href="/admin_Sendmode_list.action?is_insured_s=0">
	   	   <font class="greencolor">是</font>
	   </a>
	    <#elseif sendmode.is_insured?if_exists='1'>
	     <a href="/admin_Sendmode_list.action?is_insured_s=1">
	   		<font class="redcolor">否</font>
	   	</a>
    	</#if>
    	
    	</td>
    
    	<td align="center">${sendmode.rate?if_exists}</td>
    
    	<td align="center">${sendmode.mix_insured?if_exists}</td>
    
    	<td align="center">${sendmode.max_insured?if_exists}</td>
    
    	<td align="center">
    	<#if sendmode.reach_pay?if_exists='0'>
	    <a href="/admin_Sendmode_list.action?reach_pay_s=0">
	   	   <font class="greencolor">是</font>
	   </a>
	    <#elseif sendmode.reach_pay?if_exists='1'>
	     <a href="/admin_Sendmode_list.action?reach_pay_s=1">
	   		<font class="redcolor">否</font>
	   	</a>
    	</#if>
    	</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Sendmode_view.action','sendmode.smode_id=${sendmode.smode_id?if_exists}');">
    <img src="/include/images/bj.gif" />修改</a>
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


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">配送方式名称:</td> 
		<td><@s.textfield name="smode_name_s"  cssStyle="width:200px;"/></td>
	    </tr>
	    <tr>
		<td align="right">是否支持保价:</td> 
		<td><@s.radio name="is_insured_s" list=r"#{'1':'否','0':'是'}" /></td>
	    </tr>
	    <tr>
		<td align="right">保价费率:</td> 
		<td><@s.textfield name="rate_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
		<td align="right">最低保价额:</td> 
		<td><@s.textfield name="mix_insured_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
		<td align="right">最高保价额:</td> 
		<td><@s.textfield name="max_insured_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
		<td align="right">是否支持货到付款:</td> 
		<td>   <@s.radio name="reach_pay_s" list=r"#{'1':'否','0':'是'}"/></td>
	    </tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','','','');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

