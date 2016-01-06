$(document).ready(function(){
       /* 热点采购，热点求购脚本，脚本的显示*/
       $("#tagbuydiv").css("display","none");
       $("#hotsup").mouseover(function() {
               $("#hotsup").addClass("infor_h");
               $("#hotbuy").removeClass("infor_h");
               $("#taghotdiv").css("display","block");
               $("#tagbuydiv").css("display","none");      
       });
       $("#hotbuy").mouseover(function() {
               $("#hotbuy").addClass("infor_h");
               $("#hotsup").removeClass("infor_h");
               $("#tagbuydiv").css("display","block");
               $("#taghotdiv").css("display","none");
       });
       //分类样式
       $("#cate_div .cp_fl,.cp_fl_2").hover(function(){
		     $(this).addClass("cp_fl_h");		
	   },function(){		
		     $(this).removeClass("cp_fl_h");	
	   });
       
       
       //最新企业 VIP企业tab页
       $("#newmemdiv").css("display","none");
       $("#vipmem").mouseover(function() {
               $("#vipmem").addClass("infor_h");
               $("#newmem").removeClass("infor_h");
               $("#vipmemdiv").css("display","block");
               $("#newmemdiv").css("display","none");
       });
       $("#newmem").mouseover(function() {
               $("#vipmem").removeClass("infor_h");
               $("#newmem").addClass("infor_h");
               $("#vipmemdiv").css("display","none");
               $("#newmemdiv").css("display","block");
               
       });
       
       //最新产品 推荐产品tab页
       $("#newprodiv").css("display","none");
       $("#rempro").mouseover(function() {
               $("#rempro").addClass("infor_h");
               $("#newpro").removeClass("infor_h");
               $("#remprodiv").css("display","block");
               $("#newprodiv").css("display","none");
       });
       $("#newpro").mouseover(function() {
               $("#rempro").removeClass("infor_h");
               $("#newpro").addClass("infor_h");
               $("#remprodiv").css("display","none");
               $("#newprodiv").css("display","block");
               
       });
       
       //最新招聘 最新求职tab页
       $("#rec_main2").css("display","none");
       $("#newjob").mouseover(function() {
               $("#newjob").addClass("infor_h");
               $("#newresume").removeClass("infor_h");
               $("#rec_main1").css("display","block");
               $("#rec_main2").css("display","none");
       });
       $("#newresume").mouseover(function() {
               $("#newjob").removeClass("infor_h");
               $("#newresume").addClass("infor_h");
               $("#rec_main1").css("display","none");
               $("#rec_main2").css("display","block");
               
       });
       
       
      //图片轮播切换
	  $('#slides').slides({
        preload: true,
        preloadImage: '../images/loading.gif',
        play: 2000,
        pause: 2500,
        hoverPause: true
      });
});
function showvoteresut(vote_id)
{
 window.location.href="/portal/vote!view.action?vote_id="+vote_id;
}
function addvotes()
{

  var ismultis=$("#ismultis").val();
  var flage;
  if(ismultis=="checkbox")//多选
  {
    flage=ifcheckvaluesss();
    if(flage==false)
    {
     alert("请至少选择一项投票选项！");
    }
    else
    { alert("投票成功");
      document.forms[0].submit();
     
    }
  }
  else if(ismultis=="radio")//单选
  {
    flage=ifcheckvaluesss();
    if(flage==false)
    {
     alert("请选择其中一项投票选项！");
    }
    else
    { alert("投票成功");
      document.forms[0].submit();
     
    }
  }
  
}
 function ifcheckvaluesss()
 {
   var flag = false;
   var checks = document.getElementsByTagName('input');//得到所有input
     for(var i=0;i<checks.length;i++){
		  if(checks[i].checked){
		     flag=true;
		      break;
		    }
	    }
  return flag;
}
