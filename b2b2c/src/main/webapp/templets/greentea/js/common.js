//列表筛选弹出层操作-开始
function divfixed2(r,name){ 
   var sug=document.getElementById(name);
   var divs=document.getElementById(r);
   sug.style.left=getPosition(divs).x+"px"; 
   sug.style.top=(getPosition(divs).y)+divs.offsetHeight+"px"; 
   //sug.style.left="30px";
   //sug.style.top="30px";
   sug.style.position="absolute"; 
   sug.style.display="block"; 
}

function showSearch(enname,obj,val){
	var objdiv = document.getElementById(val);
	var str = "";
	$("#supply_main ."+enname).each(function(){
	   var text=$(this).html();
	   //alert(text);
	   str += text+"&nbsp;&nbsp;";
	});
	if(str == "") {
		str = "该字母下无分类";
	}
	
	$("#"+val).html(str);
	divfixed2(obj,val);
}
function closeSearch(val){
	document.getElementById(val).style.display="none"; 
}



function showWordDiv(enname,obj,val,wordobj){
	wordobj.className = "cp_hover";
	var objdiv = document.getElementById(val);
	var str = "";
	$("#supply_main ."+enname).each(function(){
	   var text=$(this).html();
	   //alert(text);
	   str += text;
	});
	if(str == "") {
		str = "<a>该字母下无分类</a>";
	}
	//alert(str);
	$("#"+val).html(str);
	divfixed2(obj,val);
}
function closeWordDiv(val,obj){
	obj.className = "";
	document.getElementById(val).style.display="none"; 
}




function getPosition(el) 
{ 
	for (var lx=0,ly=0;el!=null;lx+=el.offsetLeft,ly+=el.offsetTop,el=el.offsetParent); 
	return {x:lx,y:ly} 
}
//列表筛选弹出层操作-结束


//列表批量选择操作
function selectAll(field_name){
	var checkall = document.getElementById('checkall');
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checkall.checked){
			checks[i].checked = true;
		}else{
			checks[i].checked = false;
		}
	}
}
/*增加1-10的数字排行样式
*rangkmain:ul的样式
*icotop：加入小图标的位置的样式
*varnumber：数字的个数
*rak_addico_top3:前面几个小图标的样式
*rak_addico_topend：后面几个小图标的样式
*/
function addnumberstyle(rangkmain,icotop,varnumber,rak_addico_top3,rak_addico_topend)
{
  /*排行特效样式*/
	 $(""+rangkmain+" "+icotop+":lt(3)").addClass(rak_addico_top3);
	 $(""+rangkmain+" "+icotop+":gt(2)").addClass(rak_addico_topend);
	 $(""+rangkmain+" li:last").addClass("last_li");
	 var numbers='';
	 for(i=1;i<=varnumber;i++)
	 {
	  numbers+=i+",";
	 }
	 numbers=numbers.substring(0,numbers.length-1);
     var value_array = numbers.split(','); 
	 $(""+rangkmain+" "+icotop+"").each(function(i){  
		 $(this).text(value_array[i]);
     });
}



