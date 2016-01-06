 /*
*功能：系统会员密码管理
*主要有：控制input只能输入数字，字母，下划线、批量密码修改
*/
 //控制input只能输入数字，字母，下划线
    function checkNum(e)
     {
       if(e.value=="")
       {
       return;
       }
       /\w//g.exec(e.value);     
       if(e.value!=RegExp.$1)    
       e.value=RegExp.$1;     
      }        
//批量密码修改
function updatePasswdBatch(actionName)
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
	 	 var chks =document.getElementsByTagName('input');//得到所有input
	     var inputchks=document.getElementsByTagName('input');//得到所有的input
	       
           for(var i=0;i <chks.length;i++)
           { 
            
             if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
             {
                if(checks[i].checked)
                {  
                   for(var j=0;j<inputchks.length;j++)
                   {
                      if(chks[i].value==inputchks[j].id)
                   	  { 
                   	    if( inputchks[j].value=="")
                   	    {
                   	     alertShow('密码不能为空!','warning');
		                 runCount(3);
                   	     return false;
                   	    }
                      }
                   }
                }
             }
           }
       if(flag==true){
         art.dialog({
			title: '系统信息提示',
		    content: '<div class="decorate">您确定修改吗？</div>',
		    width: '15%',
		    icon: 'question',
		    yesFn: function () {
		           var admin_user_userid='';
		           var admin_user_passwd='';
			       var chks =document.getElementsByTagName('input');//得到所有input
			       var inputchks=document.getElementsByTagName('input');//得到所有的input
			       
		           for(var i=0;i <chks.length;i++)
		           { 
		            
		             if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
		             {
		                if(checks[i].checked)
		                {  
		                   for(var j=0;j<inputchks.length;j++)
		                   {
		                      if(chks[i].value==inputchks[j].id)
		                   	  { 
		                       admin_user_userid+=chks[i].value+',';
		                        admin_user_passwd+=inputchks[j].value+',';
		                      }
		                   }
		                }
		             }
		           }
		            var len=admin_user_userid.length;
		            var user_id=admin_user_userid.substring(0,len-1);
		            var lenpasswd=admin_user_passwd.length;
		            var adminpasswd=admin_user_passwd.substring(0,lenpasswd-1);
		            document.getElementById('admin_memberuser_userid').value = user_id;//用于隐藏所有的ID
		            document.getElementById('admin_memberuser_passwd').value = adminpasswd;
					document.forms[0].action=actionName;
					document.forms[0].submit();
		        return false;
		    },
		    noText: '关闭',
		    noFn: true //为true等价于function(){}
		    })
       }
     }