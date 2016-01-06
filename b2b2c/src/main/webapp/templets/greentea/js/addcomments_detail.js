///////////////////////////////加载商品评论的基本信息//////////////////////////////////
 $(document).ready(function(){
 
    var infoid=$("#infoids").val();
    var info_type=$("#info_type").val();
    var permic="0%";//中评的百分比
    var pergoodc="0%";//好评的百分比
    var perbadc="0%";//差评的百分比
    var idwidth=$("#idwidth").val();//获取显示进度条的长度
    var ititle=$("#comment_title").val();
    $("#meminfo_title").val(ititle);
    var divcssli = {background: '#FFF7D2',width: idwidth,height:'30px'}; //添加LI的样式     
    var divcsstr = {background: '#FFF7D2',width: idwidth,height:'30px'};  //添加tr的样式
    var divcsstd ='background:#FFA40D;height:30px;';  
    $("#colorgoodcommentli").css(divcssli); //加载好评的LI样式 
    $("#colorgoodcomment").css(divcsstr);  //加载好评的tr样式
    $("#colormidcommentli").css(divcssli);  //加载中评的li样式
    $("#colormidcomment").css(divcsstr);  //加载中评的tr样式
    $("#colorbadcommentli").css(divcssli);  //加载差评的li样式
    $("#colorbadcomment").css(divcsstr);  //加载差评tr样式
    //加载评论信息
 	$.ajax({  	 
            type: "post",    	     
            url: "/portal/membercomment!getCountComments.action?infotype="+info_type+"&infoid="+infoid,       
            datatype:"json",
            async:false,
            success: function(data){
              var strdata=data.split("&#");
              $("#allcount").html(strdata[0].toString());//总的记录数     
              $("#goodcomment").html(strdata[1].toString());//好评的记录数
              $("#midcomment").html(strdata[2].toString());//中评的记录数
              $("#badcomment").html(strdata[3].toString());//差评的记录数
              var strgood=round(strdata[4],1);
              $("#pergoodcomment").html(strgood+"%");//好评记录数的百分比
                var strmid=round(strdata[5],1);
              $("#permidcomment").html(strmid+"%");//中评记录数的百分比
               var strbad=round(strdata[6],1);
              $("#perbadcomment").html(strbad+"%");//差评记录数的百分比
              pergoodc=(strdata[4]/100)*parseInt(idwidth);
              permic=(strdata[5]/100)*parseInt(idwidth);
              perbadc=(strdata[6]/100)*parseInt(idwidth);
              $("#colorgoodcomment").html("<td style="+divcsstd+" width='"+pergoodc+"px' >&nbsp;</td>");
              $("#colormidcomment").html("<td style="+divcsstd+" width='"+permic+"px' >&nbsp;</td>");
              $("#colorbadcomment").html("<td style="+divcsstd+" width='"+perbadc+"px'>&nbsp;</td>");
   	        } 
       });
       $.ajax({  	 
            type: "post",    	     
            url: "/portal/membercomment!checkCommentInfo.action",       
            datatype:"json",
            async:false,
            success: function(data){
               //若为2说明用户已登录
               if(data=="2")
               {
                  $("#messagepl").html("");
               } else{
                  $("#messagepl").html("(请登录评论)");
               }
   	        } 
       });	
       
    });


  ////////////////////////js对数字进行四舍五入算法//////////////////////////////////////////
  function round(v,e)
  { 
     var t=1;
    for(;e>0;t*=10,e--);
	for(;e<0;t/=10,e++);
    return  Math.round(v*t)/t;
	
  }
 ////////////////////////////////////添加评论////////////////////////////////////////////////
    function addcommentss()
    {    
     var content=$("#com_content").val();//获取评论的内容
     var rdvalue="";//获取评论等级
     var commtitle=$("#comment_title").val();//获取评论的标题
     var comtype=$("#info_type").val();//获取评论信息的类型
     var infoids=$("#infoids").val();//获取评论信息的ID
     var urls=document.location.href;
     $("#urls").val(urls);
     var goodrdcomment=document.getElementById("goodrdc");
     var midrdcoment=document.getElementById("midrdc");
     var badrdcoment=document.getElementById("badrdc");
     var commentrand=$("#rands").val();
     var iflogin="2";
      //判断选中评论等级好评
      if(goodrdcomment.checked)
      {
       rdvalue="1";
      }
       //判断选中评论等级中评
      if(midrdcoment.checked)
      {
       rdvalue="0";
      }
      //判断选中评论等级差评
      if(badrdcoment.checked)
      {
       rdvalue="-1"
      }
      
     $.ajax({  	 
            type: "post",    	     
            url: "/portal/membercomment!checkCommentInfo.action?commentrand="+commentrand,       
            datatype:"json",
            async:false,
            success: function(data){
               if(data=="1")
               {
                document.forms["commentForm"].submit(); 
  				//mostcomment();
               }
   	           if(data=="3")
   	           {
   	             alert("请先登录,再评论！");
   	             document.location.href="/login.html?loc="+urls;
   	           }
   	           else
   	           {
   	           		  if(rdvalue=="")
				      { 
				        alert("请选择评论等级");
				        return false;
				      }
				     if(content=="")
				     {
				      alert("请输入评论内容！")
				      document.getElementById('com_content').focus();
				      return false;
				     }
				     if(commentrand=="")
				     {
				      alert("请输入验证码！");
				      document.getElementById('rands').focus();
				      return false;
				     } 
   	           }
   	           if(data=="2")
   	           {
   	             alert("验证码输入错误！");
   	           }
   	        } 
       });	
    }
 ///////////////////////////////////////////////////////////////验证码的切换 //////////////////////
    function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
    }   
 //////////////////////////////////控制评论输入框的输入字数///////////////////////////////////////
function gbcount()
{
    var max="300";
    var messages=$("#com_content").val();
    if (messages.length > max) {
     document.getElementById("com_content").value = messages.substring(0,max);
     $("#countnum").html("0")
     alert("评论不能超过 300 个字!");
     return false;
    }
    else {
     var counts=max - messages.length;
    $("#countnum").html(counts);
    }
}

  //增加支持数
 function addSupport(id)
 {
  var urls=document.location.href;
  $.ajax({  	 
            type: "post",    	     
            url: "/portal/membercomment!addSupport.action?commid="+id,       
            datatype:"json",
            async:false,
            success: function(data){
               if(data=="1")
               {
                 alert("支持成功！");
                 document.location.reload();
               }
   	           else 
   	           {
   	             alert("请先登录，再支持！");
   	             document.location.href="/login.html?loc="+urls;
   	           }
   	        } 
       });	    
 }
 //增加反对数
 function addOpposition(id)
 {
  var urls=document.location.href;
  $.ajax({  	 
            type: "post",    	     
            url: "/portal/membercomment!addOpposition.action?commid="+id,       
            datatype:"json",
            async:false,
            success: function(data){
            if(data=="1")
               {
                 alert("反对成功！");
                 document.location.reload();
               }
   	           else 
   	           {
   	             alert("请先登录，再反对！");
   	             document.location.href="/login.html?loc="+urls;
   	           }
   	        } 
       });
 } 
 //参看更多评论
function mostcomment()
 {
  document.forms["commentForm"].action="/portal/membercomment!allCommentInfo.action";
  document.forms["commentForm"].submit();
  
 }
    
 
