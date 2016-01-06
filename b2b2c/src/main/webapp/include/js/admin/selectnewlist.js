 /*
*功能：系统专题选择关联资讯，和选择关联图片资讯
*/
 function selInfo(field_name){
  
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
		  runCount(3);
	   }
	  if(flag==true){
	     art.dialog({
		  title: '系统信息提示',
		  content: '<div class="decorate">您确定吗？</div>',
		  width: '15%',
		  icon: 'question',
		  yesFn: function () {
		     var newsid="";
		     var newstitle="";
		     var chks =document.getElementsByTagName('input');//得到所有input
		     var inputchks=document.getElementsByTagName('input');//得到所有input
	           for(var i=0;i <chks.length;i++)
	         	 {   
	           		if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
	          		 {          		  
	                	if(chks[i].checked)
	                	{
	                		 for(var j=0;j<inputchks.length;j++)
	                		 {
	                   			 if(chks[i].value==inputchks[j].id)//用于获取更新排序的值
	                   			 {       			   
	                    			newsid+=inputchks[j].id+",";
	                    			newstitle+=inputchks[j].value+"#####";             
	                  			 }
	                		 }
	                    }
	                  }
	            }
		    var newsidlength=newsid.length;
		    var newstitlelength=newstitle.length;
            newsid=newsid.substring(0,newsidlength-1);
            newstitle=newstitle.substring(0,newstitlelength-5);    
		    var stype=document.getElementById("selecttype").value;
		    if(stype=="1")
		    {
		      opener.setNewsData(newsid,newstitle);window.close();
		    }
		    else 
		    {
		     opener.setNewsPicData(newsid,newstitle);window.close();
		    }
		    return false;
		    },
		 noText: '关闭',
		 noFn: true //为true等价于function(){}
		 });
	   }
    }