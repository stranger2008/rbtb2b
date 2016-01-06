<html>
  <head>
    <title>选择风格</title>
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
			        document.getElementById('member_memberchannel_state').value = state;
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
	       alter(chks);
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
<@s.form action="/member_Membertemplate_list.action" method="post">
<@s.hidden name="is_dis_update" id="member_memberchannel_state"/>
<@s.hidden name="admin_num" id="admin_num"/>
<@s.hidden name="admin_memberchconfig_id" id="admin_memberchconfig_id"/>
  <div class="cont_main">
   <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商铺信息>商铺设置>选择风格</td>
      </tr>
    </table>
    <div style="margin-top:10px;"></div>
   <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="15%" align="center" class="top_td">预览图</td>
    <td width="10%"  align="center" class="top_td">详细信息</td>
    <td width="10%"  align="center" class="top_td">以下会员免费</td>
    <td width="10%"  align="center" class="top_td">状态</td>
    <td width="10%"  align="center" class="top_td">预览</td>
  </tr>
  
  <#list membertemplateList as membertemplate>
  <tr align="center">
    <td style="padding:10px;"><a href="/showroom/${user_name?if_exists}?para_temp_code=${membertemplate.temp_code?if_exists}&temp_loc=${membertemplate.temp_loc?if_exists}" target=”_blank">
    	<img src="${membertemplate.temp_img?if_exists}" width="160" height="100"  style="border:1px solid #e1e2e3;" />    
    </a></td>
    <td>
    <table>
    <tr><td>模板名称：</td><td>${membertemplate.temp_name?if_exists}</td></tr>
    <tr><td>价格：</td><td><#if membertemplate.price?if_exists==0><font color='green'>免费</font><#else>${membertemplate.price?if_exists}<#if membertemplate.p_unit?if_exists=='0'><font color='red'>&nbsp;积分</font><#else><font color='red'>&nbsp;资金</font></#if></#if></td></tr>
    <tr><td>作者：</td><td>${membertemplate.author?if_exists}</td></tr>
    </table>
    </td>
    <td align="center">${membertemplate.mem_level?if_exists}</td>
    <td>
    <#if membertemplate.cust_id?if_exists==0>
    <a onclick="linkToInfo('/member_Membertemplate_updatetempcode.action','temp_code=${membertemplate.temp_code?if_exists}&temp_id=${membertemplate.temp_id?if_exists}&temp_loc=${membertemplate.temp_loc?if_exists}');">
    <font color='green'>启用</font></a>
    <#else><font color='red'>正在使用</font>&nbsp;<a href="/showroom/${user_name?if_exists}" target=”_blank">我的企业站</a>    
    </#if>
    </td>
    <td><a href="/showroom/${user_name?if_exists}?para_temp_code=${membertemplate.temp_code?if_exists}&temp_loc=${membertemplate.temp_loc?if_exists}" target=”_blank">预览</a></td>
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
             <td class="table_name">放置位置:</td>
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