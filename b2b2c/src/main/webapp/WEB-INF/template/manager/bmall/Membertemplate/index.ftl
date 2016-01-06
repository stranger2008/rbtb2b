  <html>
<head>
<title>店铺风格管理</title>
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
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">店铺风格</a>
    </div>
<@s.form action="/bmall_Membertemplate_list.action" method="post">
<@s.hidden name="is_dis_update" id="member_memberchannel_state"/>
<@s.hidden name="admin_num" id="admin_num"/>
<@s.hidden name="admin_memberchconfig_id" id="admin_memberchconfig_id"/>
<@s.hidden name="mall_type" value="b2c"/>
<div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">店铺管理</td>
     </h2></div>
     <div class="ropot">
     	<h2 class="rotitle"><span><td class="fthstyle1">店铺风格管理</td></span></h2>
	       <table cellspacing="0" class="bmall_list_table"   >
		    <tr >
		        <td width="5%" class="fthstyle1">
	             </td>
			    <td width="30%" class="fthstyle1">预览图</td>
			    <td width="25%"  class="fthstyle1">详细信息</td>
			    <td width="20%"  class="fthstyle1">以下会员免费</td>
			    <td width="10%"  class="fthstyle1">状态</td>
			    <td width="10%" class="fthstyle1">操作</td>
		    </tr>
	       <#list membertemplateList as membertemplate>
	       <tr >
	             <td ></td>
			    <td >
				    <a href="/showroom/${user_name?if_exists}?para_temp_code=${membertemplate.temp_code?if_exists}" target=”_blank">
				    	<img src="${membertemplate.temp_img?if_exists}" width="160" height="100"  style="border:1px solid #e1e2e3;" />    
				    </a>
			    </td>
			    <td >
				    <table>
				    <tr><td >模板名称：</td><td>${membertemplate.temp_name?if_exists}</td></tr>
				    <tr><td >价格：</td><td><#if membertemplate.price?if_exists==0><font color='green'>免费</font><#else>${membertemplate.price?if_exists}<#if membertemplate.p_unit?if_exists=='0'><font color='red'>&nbsp;积分</font><#else><font color='red'>&nbsp;资金</font></#if></#if></td></tr>
				    <tr><td >作者：</td><td>${membertemplate.author?if_exists}</td></tr>
				    </table>
			    </td>
			    <td >${membertemplate.mem_level?if_exists}</td>
			    <td >
				    <#if membertemplate.cust_id?if_exists==0>
				    <a onclick="linkToInfo('/bmall_Membertemplate_updatetempcode.action','temp_code=${membertemplate.temp_code?if_exists}&temp_id=${membertemplate.temp_id?if_exists}&mall_type=b2c');">
				    <font color='green'>启用</font></a>
				    <#else><font color='red'>正在使用</font>&nbsp;<a href="/showroom/${user_name?if_exists}" target=”_blank">我的企业站</a>    
				    </#if>
			    </td>
			    <td >
			     <a href="/showroom/${user_name?if_exists}?para_temp_code=${membertemplate.temp_code?if_exists}" target=”_blank">预览</a>
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
