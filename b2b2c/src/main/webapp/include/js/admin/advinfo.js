/*
*功能：系统广告管理
*/
function selectPosType(name) {
	if (name == "f") {
		document.getElementById("keyword").style.display = "";
	} else {
		document.getElementById("keyword").style.display = "none";
	}
	if (name == "g") {
		document.getElementById("moduletype").style.display = "";
		document.getElementById("catattr").style.display = "";
	} else {
		document.getElementById("moduletype").style.display = "none";
		document.getElementById("catattr").style.display = "none";
	}
}

function setid(id){
     $("#intrvalue").val(id);
}

function isview(tablename)
{
    window.open("/admin_Advinfo_indexinfo.action?tablename="+tablename);
}

$(document).ready(function(){ 
		  $("#paratype").change(function(){
			   $('option:selected', this).each(function(){
			        $("#divlist").html("");
                    var code=this.value;
                   	cat_select("${cfg_topcatid?if_exists}",1,code);	
			   });  	
		  });    
          disabledCss();  
          var selvalue=$("select[name='advpos.pos_type']").find('option:selected').text(); 
          $("#idvalue").html(selvalue);
          var smodulevalue=$("select[name='advpos.module_type']").find('option:selected').text(); 
          $("#modulevalue").html(smodulevalue);

		  var test = $("#advtype").val();	
		  
		  var advimage=$("#advimage");
		  var advlink=$("#advlink");
		  var advtitle=$("#advtitle");
		  var advcontent=$("#advcontent");
		  var advcode=$("#advcode");
		  var advflash=$("#advflash");
		  var advimagemulti=$("#advimagemulti");
		  var keyword=$("#keyword");
		  var advintr=$("#advintr");
		  var category=$("#category");
		  if(test=='a'){
		    advcode.show();
		    advimage.show();
		  }
		  if(test=='b'){
		    advtitle.show();
		    advcontent.show();
		    advlink.show();
		  }
		  if(test=='c'){
		    
		    advimage.show();
		    advlink.show();
		    advtitle.show();
		  }
		  if(test=='d'){
		    advflash.show();
		    advlink.show();
		  }
		  if(test=='e'){
		     advlink.show();
		     advtitle.show();
		     advimagemulti.show();
		  }
		  if(test=='f'){
		   keyword.show();
		   advintr.show();
		  }
		  if(test=='g'){
		     advlink.show();
		     advtitle.show();
		     advcontent.show();
		     category.show();
		  }

    });	
