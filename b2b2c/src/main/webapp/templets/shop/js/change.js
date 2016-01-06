function re_show(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++)
		{
			if(val==i)
			{
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}
			else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
}



function pselect(type,brandid){
    if(type=='p'){
	    var selValue=$("#selValue").val();
	    if(selValue==''||selValue=="请输入搜索条件"){
	      $("#selValue").val("请输入搜索条件");
	    }else{
	      var sel = encodeURIComponent(encodeURIComponent(selValue));
	      window.location.href="/mall/goods!list.action?p="+sel;
	    }
    }
    if(type=='b'){
	  window.location.href="/mall/goods!list.action?b="+brandid;
    }
  }	
  
  function bselect(){
      var selValue=$("#selValue").val();
      $("#selName").val(selValue);
	    if(selValue==''||selValue=="请输入搜索条件"){
	      $("#selValue").val("请输入搜索条件");
	    }else{
	      document.forms[0].submit();
	    }
  }
  
  function priceselect(){
     var selValue=$("#bselValue").val();
     $("#selName").val(selValue);
     var upprice=$("#upprice").val();
     $("#uppri").val(upprice);
     var downprice=$("#downprice").val();
     $("#downpri").val(downprice);
	    if((selValue==''||selValue=="请输入搜索条件")&& upprice=='' && downprice==''){
	      $("#bselValue").val("请输入搜索条件");
	    }else{
	     document.getElementById("fgoodslist").submit();
	    }
  }


