<html>
  <head>
    <title>积分规则管理</title>     
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	 
  </head>
  <body>
<@s.form action="/admin_Interrule_list.action" method="post">
<@s.hidden name="scoreid" id="scoreid"/>
<@s.hidden name="scoreNum" id="scoreNum"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 积分规则管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="xg" ><a href="###" onclick="updateNum('/admin_Interrule_updateScore.action');">修改</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('interrule.rule_id')"/></td>
    <td width="30%"  align="center" class="top_td">规则编码</td>
    <td width="37%" align="center" class="top_td">规则名称</td>
    <td width="30%"  align="center" class="top_td">操作积分数</td>
  </tr>
  
	  <#list interruleList as rule>
	    <tr bgcolor="<#if rule_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	          <td><input type="checkbox" name="interrule.rule_id" value="${rule.rule_id?if_exists}"/></td>
	          <td align="center">${rule.rule_code?if_exists}</td>
	          <td align="center">${rule.rule_name?if_exists}</td>
	          <td align="center"><input value="${rule.internum?if_exists}" id="inputNum${rule.rule_id?if_exists}" style="width:120px;text-align:right;"
	           onkeyup="check_zen_num(this)"/></td>
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
      function updateNum(actionName)
      {  
         var flag = false;
		 var checks = document.getElementsByTagName('input');//得到所有input
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
	      art.dialog({
		   title: '系统信息提示',
	       content: '<div class="decorate">您确定要修改吗？</div>',
	       width: '15%',
	       icon: 'question',
	       yesFn: function () {
	         var admin_group_id='';
	           var admin_group_num='';
		        $("input:checkbox").each(function(){	                 	  
		              if(this.checked&&this.value!="on"){
		                 //得到所名为checkbox的id
		                 admin_group_id+=this.value+',';	
		                 admin_group_num+=$("#inputNum"+this.value).val()+',';     
		              }                             		             
				 }); 	        
	           var len=admin_group_id.length;
	           var lenNum=admin_group_num.length;
	           admin_group_id=admin_group_id.substring(0,len-1);
	           admin_group_num=admin_group_num.substring(0,lenNum-1);               
	           document.getElementById('scoreid').value = admin_group_id;//用于隐藏所有的ID
	           document.getElementById('scoreNum').value = admin_group_num;//用于隐藏文本所有的值
			   document.forms[0].action=actionName;
			   document.forms[0].submit();
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    });	   
     } 
    </script> 
  </body>
</html>
