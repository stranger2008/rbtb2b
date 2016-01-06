 /*
 *	功能：运营商后台系统设置向导功能
 */
 
 function guidedset(){
       $("#guided").css("display","");
       $("#first").css("display","none");
	   $("#second").css("display","none");
	   $("#third").css("display","none");
   }
   function firstset(){
       $("#guided").css("display","none");
       $("#first").css("display","");
	   $("#second").css("display","none");
	   $("#third").css("display","none");
   }
   function secondset(){
       $("#guided").css("display","none");
       $("#first").css("display","none");
	   $("#second").css("display","");
	   $("#third").css("display","none");
   }
   function thirdset(){
       $("#guided").css("display","none");
       $("#first").css("display","none");
	   $("#second").css("display","none");
	   $("#third").css("display","");
   }
   
var popupStatus = 0;
//使用Jquery加载弹窗
function loadPopup() {
    //仅在开启标志popupStatus为0的情况下加载   
	if(popupStatus==0){
		$("#backgroundPopup").css({"opacity": "0.7", background: "black" }); 
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}
//使用Jquery去除弹窗效果
function disablePopup() {
    //仅在开启标志popupStatus为1的情况下去除
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
	}
}
//将弹出窗口定位在屏幕的中央 
function centerPopup(){

	var divs=document.getElementById("centerPoint");
    var d_left= getPosition(divs).x+"px";
    var d_top=getPosition(divs).y+divs.offsetHeight+"px"; 
	
	//居中设置
	$("#popupContact").css({
		"position": "absolute",
		"top": d_top,
		"left": d_left
	});
}
//键盘按下ESC时关闭窗口!
$(document).ready(function(){			 
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			disablePopup();
		}
	});

});		
//弹窗方法
function showdiv(){
 	var point = ($(window).width()-760)/2;
 	point = point+"px";
 	$("#centerPoint").css("margin-left",point);
 	centerPopup();
 	loadPopup();
}
   
function getPosition(el) 
{ 
	for (var lx=0,ly=0;el!=null;lx+=el.offsetLeft,ly+=el.offsetTop,el=el.offsetParent); 
	return {x:lx,y:ly} 
}











