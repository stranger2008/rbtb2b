///////////////////////////////加载知道详细信息//////////////////////////////////
$(document).ready(function(){
    //加载问题的答案
    var infoid=$("#infoids").val();
    askinfo();//加载提问者信息
    $.ajax({  	 
            type: "post",    	     
            url: "/portal/know!getallanswers.action?askid="+infoid,       
            datatype:"json",
            async:false,
            success: function(data)
            {
             var count="0";//回答记录总数
             var content="";//回答内容
             var username="";//回答会员名称
             var refdatas="";//回答关联资料
             var indate="";//回答日期
             var companyusername="";//会员的企业站的名称
             var alldivapp="";
             var divapp="<div class='hd_m'><P class='hd_m_cont'>";
             var divapp1=" </P><P class='hdz'>回答者：";
             var divapp2=" | ";
             var divapp3=" </P></div>";
             var str=data.split("$$$$$$$$$$");
             count=str[0].toString();
             var strcontent=str[1].toString().split("##########");
             for(var m=1;m<strcontent.length;m++)
             {
               //获取评论内容
               var strcon=strcontent[m].split("@@@@@@@@@@")
               if(strcon[0]!=null)
               {
                 content=strcon[0].toString();
               }
               //获取评论人
               if(strcon[1]!=null)
               {
                 var strusername=strcon[1].toString().split("&&&&&&&&&&");
                 username=strusername[0].toString();
                }
                if(username=='')
                {
                  username="匿名";
                }
                //获取评论时间
                if(strusername[1]!=null)
                {
                  var strindate=strusername[1].toString().split("%%%%%%%%%%")
                  indate=strindate[0].toString();
                }
                if(strindate[1]!=null&&strindate[1].toString()!='')
                {
                  var strrefdatas=strindate[1].toString().split("**********")
                  if(strrefdatas[0]!=null&&strrefdatas[0].toString()!='')
                  {
                   refdatas="<br/>参考资料："+strrefdatas[0].toString();//获取参考资料
                  }
                }
                //获取企业站链接地址//如果是匿名的话，就不需要链接地址
                if(strrefdatas[1]!=null&&strrefdatas[1].toString()!=''&&username!='匿名')
                {
                  companyusername="<a style='color:#07519a;' href='/showroom/"+strrefdatas[1].toString()+"' target='_blank'>"+username+"</a>";
                }
                else
                {
                 companyusername=username;
                }
                alldivapp+=divapp+" <div  style='width:650px;table-layout:fixed; word-break: break-all; overflow:hidden;'><p style='width:650px;font-size:14px;line-height:25px;'>"+content+"</p><p style='width:650px;color:#777777;'>"+refdatas+"</p></div>"+divapp1+companyusername+divapp2+indate+divapp3;  
             }
              $("#countnum1").html(count);//插入回答记录数
              $("#answers").html(alldivapp);//插入回答内容
   	        } 
       });
/////////////////////////////动态加载答案结束////////////////////////////////////////////////////////////////
/////////////////////////动态加载回答框开始///////////////////////////////////////
    $.ajax({  	 
            type: "post",    	     
            url: "/portal/know!getanswerstates.action?askid="+infoid,       
            datatype:"json",
            async:false,
            success: function(data){
 			var addhtmlbastanswer="";
 			if(data=="0")
 			{
 			    var divanswerhtml=" <form action='/portal/know!addwebanswers.action' name='asksForm' method='post'>";
 			    divanswerhtml+="<input type='hidden' name='askid' value='"+infoid+"'  />";
    		    divanswerhtml+="<div class='wljd' ><div class='jd_t'></div><div class='jd_c'>";
   				divanswerhtml+="<p class='jd_title'><span class='jd_m'>我来帮他解答</span><span class='jd_sr'>还能输入<span><label id='countnum' style='color: red;font-weight: bold;'>600</label></span>字</span></p>";
    			divanswerhtml+="<div class='clear'></div>";
    			divanswerhtml+=" <textarea name='content' onKeyDown='gbcount();' onKeyUp='gbcount();' id='content' cols='' rows='10' class='sry'></textarea>"; 
    			divanswerhtml+=" <div class='jd_m11'>参考资料: <input type='text' id='refdata' name='referdata_s' class='jd_refinput' /> </div>";  
    			divanswerhtml+=" <div class='jd_m11'>验 证 码：<input type='text' maxlength='4' name='rand_s' id='rands' style='width:50px;' /> ";
                divanswerhtml+="<img src='/imgrand.action' id='imgrads' style='vertical-align:middle;' onclick='changeValidateCode(this)'/>";
    			divanswerhtml+="<input type='button' class='butotn_jd' onclick='addanswer()' /></div>"; 
    		    divanswerhtml+="</div> <div class='jd_d'></div></div>";  
    		    divanswerhtml+="</form>";
    			$("#appenanswer").html(divanswerhtml);
 			}
 			else if(data=="1")
 			{
 			  //最佳答案
              $.ajax({  	 
                     type: "post",    	     
                     url: "/portal/know!getanswerbest.action?askid="+infoid,       
                     datatype:"json",
                     async:false,
                     success: function(data)
                     {
                       var askcontent="";//最佳答案的内容
                       var askdate="";//最佳答案回答时间
                       var askuserid="";//最佳答案回答会员ID
                       var askusername="";//最佳答案回答用户名
                       var straddlinkusername="";//构造链接企业站的URL地址
                       var strcontent="", strdate="",struserid="";
                       //获取最佳答案的内容
                       if(data!=null&&data.toString()!="")
                       {
                        strbest=data.toString().split("&&&&&&&&&&");
                        askcontent=strbest[0].toString();
                       }
                       //获取最佳答案的回答时间
                       if(strbest[1]!=null&&strbest[1]!="")
                       {
                        strdate=strbest[1].toString().split("##########");
                        askdate=strdate[0].toString();                       
                       }
                       //获取最佳答案的企业站名称
                       if(strdate[1]!=null&&strdate[1]!="")
                       {
                        struserid=strdate[1].toString().split("**********");
                        askuserid=struserid[0].toString();
                       }                 
                       //获取最佳答案的会员名称
                       if(struserid[1]!=null&&struserid[1]!="")
                       {
                        askusername=struserid[1].toString();
                       }
                       else
                       {
                        askusername="匿名";
                       }
                       //构造企业站链接地址如：http://www.ruibaotong.net/showroom/admin
                       if(askusername!="匿名")//如果是匿名的话，就不需要链接地址
                       {
                         straddlinkusername="<a style='color:#07519a;' href='/showroom/"+askuserid+"' target='_blank'>"+askusername+"</a>";
                       }
                       else
                       {
                         straddlinkusername=askusername;
                       }
                       $("#bestcontent").html(askcontent);
                       $("#bestdate").html(askdate);
                       $("#bestuser").html(straddlinkusername);
                       }
                    });
              $("#bastanswer").css('display','');
 			}
            
   	        } 
       });
});
//////////////////////////动态加载回答框结束/////////////////////////////////////
//加载处理提问者的个人信息
function askinfo()
{
   var comuserid=$("#comusername").val();
   var get_user_userid=$("#get_user_name").val();
   var addaskinfotext="";
   if(get_user_userid!=null&&get_user_userid!="")
   {
    addaskinfotext="<font style='color:#07519a;'><a href='/showroom/"+comuserid+"'>"+get_user_userid+"</a></font>";
   }
   else
   {
    addaskinfotext="匿名";
   }
   $("#askuser").html(addaskinfotext);
}
//////////////////////////////回答问题////////////////////////////////////////////////
function addanswer()
 {
  
   var askid=$("#infoids").val();
   var content=$("#content").val();
   var urls=document.location.href;
   var title=$("#titles").val();
   var rads=$("#rands").val();
   var refdata=$("#refdata").val();
    $.ajax({  	 
            type: "post",    	     
            url: "/portal/know!verificationrandusername.action?askid="+askid+"&rand_s="+rads,       
            datatype:"json",
            async:false,
            success: function(data){
             if(data=="1")
               {
                document.forms[0].submit();
                alert("回答成功！");
               }
   	           if(data=="0")
   	           {
   	             alert("请先登录，再回答！");
   	             document.location.href="/login.action?loc="+urls;
   	           }else{
   	               if(content=='')
					 {
					   alert("请输入回答内容！");
					   return false;
					 }
					 if(rads=='')
					 {
					    alert("请输入验证码！");
					    return false;
					 }
   	           }
   	           if(data=="2")
   	           {
   	             alert("验证错误！请重新输入验证码！");
   	             return false;     
   	           }
   	           else if(data=="3")
   	           {
   	             alert("抱歉,该问题是您自己发布,无法回答！");
   	             return false;     
   	           }
   	        } 
       });
    
 }

//////////////////////////////////控制评论输入框的输入字数///////////////////////////////////////
function gbcount()
{
    var max="600";
    var messages=$("#content").val();
    if (messages.length > max) {
	     document.getElementById("content").value = messages.substring(0,max);
	     $("#countnum").html("0")
	     alert("评论不能超过 600 个字!");
	     return false;
    }
    else {
     var counts=max - messages.length;
    $("#countnum").html(counts);
    }
}
 /////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////验证码的切换 //////////////////////
    function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    }   
