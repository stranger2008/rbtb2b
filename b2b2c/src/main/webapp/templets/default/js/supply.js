$(document).ready(function(){
       
	   var imp=$('#imgpath').val();//获取隐藏域的图片地址
	   var strStartBigImg=" <div class='panel'><img src=\'";//绑定大图4
	   var strEndBigImg="\'width='280px' height='238px' /></div>";//绑定大图
	   var strBigImg="";//绑定大图
	   var strStartSmallImg=" <li><img src=\'";//绑定小图
	   var strEndSmallImg="\'width='60px' height='60px'/></li>";//绑定小图
	   var strSmallImg="";//绑定小图
	    
	   if(imp.indexOf(",")!=-1)
	   {
	     var strimages=imp.split(",");
	     for(var i=0;i<strimages.length;i++)
	     {
	       if(strimages[i]!=null &&strimages[i].toString()!="")
	       {
	         strBigImg+=strStartBigImg+strimages[i].toString()+strEndBigImg;
	         strSmallImg+=strStartSmallImg+strimages[i].toString()+strEndSmallImg;
	       }
	     }
	   }
	   else if(imp==""||imp==null)
	   {
	     var noneimgpathbig ="/include/images/nopic.jpg";//默认无大图
	      var noneimgpathsmall="/include/images/nopic.jpg";//默认无小图
	       for(var k=0;k<3;k++)
	       {
	        strBigImg +=strStartBigImg+noneimgpathbig+strEndBigImg;
	        strSmallImg +=strStartSmallImg+noneimgpathsmall+strEndSmallImg;
	       }
	   }
	   else 
	   {
	      strBigImg+=strStartBigImg+imp+strEndBigImg;
	      strSmallImg+=strStartSmallImg+imp+strEndSmallImg;
	   }
	   var strimg=strBigImg+"<ul class='filmstrip' id='smallimgs'>"+strSmallImg+"</ul>";
	   $("#photos").html(strimg);
	   $('#photos').galleryView({
			panel_width: 280,
			panel_height: 238,
			frame_width: 60,
			frame_height: 60
		});
	});	
	function addcollects()
	{
	  
	}
    function memberuserlogin(){
        var user_name = $("#user_name").val();
        var userpwd = $("#passwd").val();
        if(user_name == "" || userpwd == ""){
          alert("请输入用户名或密码");
        }
        if(user_name != "" && userpwd != ""){
	        $.ajax({
	           type:"post",
	           url:"/portal/memberuser!userlogin.action?user_name="+user_name+"&passwd="+userpwd,
	           datatype:"json",
	           async:false,
	           success: function(data){
	              if(data=='1' || data=='2'){
	                alert("用户名或密码不正确");
	                return false;
	              }
	              if(data=='3'){
	                alert("该会员未激活，请登入您的邮箱激活");
	                return false;
	              }
	              if(data=="4"){
	                window.location.reload();
	              }
	           }
	        });
       }
    }
	
	

	
	
	