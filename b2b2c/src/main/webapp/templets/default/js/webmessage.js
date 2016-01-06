function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    } 
<!--验证留言标题是否为空-->
function TitleIsNull(){   
       var str=$("#title").val(); 
       if(str.length==0||str.replace(/^\s+,""/).replace(/^\s+$/,"")==''){   
           $("#titlecheck").html('对不起，留言标题不能为空或者为空格! ');
           return false;
       }else{
         $("#titlecheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
        return true;
       }
      
}
<!--验证留言内容是否为空-->
function ContentIsNull(){   
       var str=$("#content").val(); 
       if(str.length==0||str.replace(/^\s+,""/).replace(/^\s+$/,"")==''){   
           $("#contentcheck").html('对不起，留言内容不能为空或者为空格!');
           return false;
       }else{
         $("#contentcheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
        return true;
       }
      
}
<!--验证联系人是否为空-->
function NameIsNull(){   
       var str=$("#name1").val(); 
       if(str.length==0||str.replace(/^\s+,""/).replace(/^\s+$/,"")==''){   
           $("#namecheck").html('对不起，联系人不能为空或者为空格!');
           return false;
       }else{
         $("#namecheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
        return true;
       }    
}
<!--验证电话号码 -->  
function IsPhone()    
{    
          var str=$("#phone").val(); 
          if(str.length!=0||str.replace(/^\s+,""/).replace(/^\s+$/,"")!=''){   
          reg=/^((\(\d{2,3}\))|(\d{3}\-))?13|15|18\d{9}$/;
	          if(!reg.test(str)){  
	              $("#phonecheck").html('对不起，联系电话格式不正确!');
	              return false;
	           }else{
	              $("#phonecheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
	           }  
          }else{
            $("#phonecheck").html('对不起，联系电话不能为空或者为空格!');
          }
      return true;
}

<!--验证EMAIL格式是否正确-->
function IsEmail()    
{    
           var str=$("#email").val();
           if(str.length!=0||str.replace(/^\s+,""/).replace(/^\s+$/,"")!=''){   
           reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;   
	           if(!reg.test(str)){   
	              $("#emailcheck").html('对不起，您输入的邮件格式不正确!');
	              return false;
	           }else{
	              $("#emailcheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
	           }
           }else{
            $("#emailcheck").html('对不起，邮件不能为空或者为空格!');
           }
          return true;
} 

<!--验证QQ格式是否正确-->
function IsQQ()    
{    
       var str= $("#qq").val(); 
           if(str.length!=0){   
           reg=/^[1-9]\d{4,10}$/;
           if(!reg.test(str)){ 
              $("#qqcheck").html('对不起，您输入的QQ格式不正确!');
              return false;
           }else{
              $("#qqcheck").html('<img src="../images/zt1.gif" width="25px" height="25px"/>');
           } 
       }
      return true;
}
<!--验证所有空间-->
function checkall(){
  if(TitleIsNull()&&ContentIsNull()&&NameIsNull()&&IsPhone()&&IsEmail()&&IsQQ()){
     return true;
  }else{
     return false;
  }
}
////////////////////////////////////添加评论////////////////////////////////////////////////
    function addcommentss()
    {
     var title=$("#title").val();//获取评论的标题
     var content=$("#content").val().replace("<", "").replace(">", "");//获取评论的内容
     var name=$("#name").val();//获取评论人
     var phone=$("#phone").val();//获取评论信息的手机号
     var email=$("#email").val();//获取email
     var qq=$("#qq").val();//获取QQ
     var qq=$("#msn").val();//获取QQ
     var qq=$("#skype").val();//获取QQ
     
      if(document.getElementById("ckname").checked)
     {
      iflogin="1";//是否匿名；
     }
     if(commentrand=="")
     {
      alert("请输入验证码！");
      return false;
     }
      $.ajax({  	 
            type: "post",    	     
            url: "insertmessage.action?title="+title+"&content="+content+"&name="+name+"&phone="+phone+"&email="+email+"&qq="+qq+"&msn="+msn+"&skype="+skype+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){
               if(data=="1")
               {
                 alert("评论成功！");
                 document.location.reload();
               }
               else if(data=="3")
               {
                 alert("验证码输入错误！");
               }
   	         }
       });
    
    }

   function checkcode(){
     var c_code=$("#rands").val();
     var state=0;
     $.ajax({  	 
          type: "post",    	     
          url: "/insertfrom.action?c_code="+c_code+"&ajaxRequestRandom="+Math.random(),       
          datatype:"json",
          async:false,
          success: function(data){           
             if(data==1){
                state=1;
             }else{
                state=0;
             }
          }            
       });   
      return state; 
   }
   $(document).ready(function(){
       $("#msgfrom").submit(function(){   
              if(checkcode()==1&&checkall()){
                  return true;
               }else if(checkcode()==0&&checkall()){
                 $("#commentrand").html('验证码错误!');
                 return false;
               }
               else{ 
                  return false;
               }
       });       
   });
   
