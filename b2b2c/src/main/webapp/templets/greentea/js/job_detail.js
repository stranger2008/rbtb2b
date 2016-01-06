/////////////////////////加载简历//////////////////////////////
 $(document).ready(function(){
	    $.ajax({  	 
            type: "post",    	     
            url: "/portal/resume!getResumeInBoxs.action",       
            datatype:"json",
            async:false,
            success: function(data){
             if(data!="0")
             {
             alert(data);
              var strop="<option value='0'>--请选择简历--</option>";
              var strdata=data.split("#####");
               for(var ii=0;ii<strdata.length-1;ii++)
               {
                 var strop1=strdata[ii].toString().split("&&&&&");
                 var strid=strop1[1].toString();
                 var strtitle=strop1[0].toString();
                 strop+="<option value='"+strid+"'>"+strtitle.substring(0,10)+"</option>";
               }
               $("#resumeids").html(strop);
              $("#divresumeids").css('display','');
             }
   	        } 
       });
    });

////////////////////////申请职位////////////////////////////////////
 function addjobinbox()
 { 
    
      var infoids=$("#infoids").val();
      var custid=$("#custid").val();
      var cpname=$("#cpname").val();
      var urls=document.location.href;
      var resumeid =$("#resumeids").val();
      if(resumeid=="0")
      {
         alert("请选择简历");
         return false;
      }else{
	     $.ajax({  	 
            type: "post",    	     
            url: "/portal/resume!addResumeInBox.action?resumeid="+resumeid+"&companyid="+custid+"&jobid="+infoids,     
            datatype:"json",
            async:false,
            success: function(data){  
            
               if(data=="0")
               {
                  alert("请先登录，才可以申请职位！");
                  document.location.href="/login.action?loc="+urls;
               }
               else if(data=="1")
               {
                 alert("抱歉，您的会员类型为企业会员，无法申请职位！");
               }
               else if(data=="2")
               {
                 alert("抱歉，您还没有填写简历或您的简历没办法使用，无法申请职位，请先填写简历或更新简历！");
                  document.location.href="/member_Resume_add.action?loc="+urls;
               }
               else if(data=="3")
               {
                 alert("您已经申请过该职位了，无需重复申请该职位！");
               }
               else if(data=="4")
               {
                alert("您有多份简历，请选择其中一份简历作为申请该职位的简历！");
                
               }
               else if(data=="5")
               {
                alert("恭喜！申请《 "+cpname+" 》职位成功！");
               }
                else if(data=="6")
               {
                alert("抱歉，您的简历不合格，无法申请职位，请更新简历!");
               }
   	         } 
          });
       }
 }
 //////////////////////申请职位结束////////////////////////////////