function addjobresume()
 {
  var infoids=$("#infoids").val();
	    $.ajax({  	 
            type: "post",    	     
            url: "/portal/job!addJobTalents.action?resumeid="+infoids+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){  
             if(data=="0")//表示会员没有登录
             {
               alert("请先登录，再加入人才库！");
               var urls=document.location.href;
               document.location.href="/login.html?loc="+urls;
             }
             else if(data=="1")//会员类型为个人会员
             {
              alert("抱歉，您的会员类型为个人会员，无法加入人才库！");
             }
             else if(data=="2")//加入人才库成功！
             {
              alert("加入人才库成功！");
             }
             else if(data=="3")
             {
              alert("该简历已经在人才库中，无需重复添加！");
             }
   	        } 
       });
 }
 //////////////////////////////////////////////////////
    