/*
*功能：系统分类信息管理
*/
var attrString;//定义属性串
var ismust="must";//定义常量   
var required="must1"//表示必填
var type_button="";
function checkattr(){  
      attrString="";
	  var spanid;//定义获取当前标签的ID
	  var hidden_id;//定义隐藏域的值
	  var attrvalue="";//定义获取当前的值
      clearspan();//清空span的值
      if($("#title").val()==""){
	 	    $("#span_title").addClass("errorSpan");
	 	    $("#span_title").html("供应标题不能为空");
	 	 return false;
	  }
	  //验证所属分类的选择下拉框是否已选择
      if($("select[id^=level]").length>0){
         var catecount=0;
         $("select[id^=level]").each(function(){	
               if($(this).val()=="0"){ 
                   catecount+=1;
				}
         });
         if(catecount>0)
         {
	            $("#span_cate").addClass("errorSpan");
				$("#span_cate").html("请先选择!");
				return false;
         }         
      }  
    
     //获取下拉框的值，判断下拉框是否有选值
     if($("select[id^="+required+"]").length>0){       
               var selectcount=0;          
         $("select[id^="+required+"]").each(function(){	
              if($(this).val()=="请选择") {
                       selectcount+=1;
                    }
                    spanid=$(this).attr("id"); 
                    hidden_id=$("#hidden_"+spanid).val();
                    attrvalue=$(this).val();
                         
         });
         if(selectcount>0)
         {
             $("#span_"+spanid).addClass("errorSpan");
		     $("#span_"+spanid).html("请先选择!");
		     return false;
         }
		 else{
             attrString+=hidden_id+"|"+attrvalue+",";   
		 }
	}else{
	    $("select[id^="+ismust+"]").each(function(){	
                alert($(this).val());
                spanid=$(this).attr("id"); 
                hidden_id=$("#hidden_"+spanid).val();
                attrvalue=$(this).val();
        });
        attrString+=hidden_id+"|"+attrvalue+",";      		   
	}
     //获取长文本域中的内容
     if($("textarea[id^="+required+"]").length>0){	
         var textareacount=0;   
         $("textarea[id^="+required+"]").each(function(){ 
	        if($(this).val()==""){
	            textareacount+=1;
	        }
            spanid=$(this).attr("id"); 
            hidden_id=$("#hidden_"+spanid).val();
            attrvalue=$(this).val();
            
         });	
        if(textareacount>0){         
             $("#span_"+spanid).addClass("errorSpan");
	         $("#span_"+spanid).html("请认真填写，该字段不能为空!");
	         return false;
	     }else{
               attrString+=hidden_id+"|"+attrvalue+" "+",";
	     }
     }else{
         $("textarea[id^="+ismust+"]").each(function(){ 
            spanid=$(this).attr("id"); 
            hidden_id=$("#hidden_"+spanid).val();
            attrvalue=$(this).val();   
            attrString+=hidden_id+"|"+attrvalue+" "+",";         
         });
     }
      //获取文本框的值
      if($("input:text[id^="+required+"]").length>0){	
            var textcount=0;
       	    $("input:text[id^="+required+"]").each(function(){	
       	        if($(this).val()==""){     
       	           textcount+=1;
       	        }       	       
                spanid=$(this).attr("id");//获取当前标签的ID值
                hidden_id=$("#hidden_"+spanid).val();
			    attrvalue=$(this).val();
            }); 
             if(textcount>0){            
                $("#span_"+spanid).addClass("errorSpan");
				$("#span_"+spanid).html("请认真填写，该字段不能为空!");
				return false;
			}else{
			    attrString+=hidden_id+"|"+attrvalue+" "+",";
			}					
       }else{
       	    $("input:text[id^="+ismust+"]").each(function(){	
                spanid=$(this).attr("id");//获取当前标签的ID值
	            hidden_id=$("#hidden_"+spanid).val();
	            attrvalue=$(this).val();
	            attrString+=hidden_id+"|"+attrvalue+" "+",";
            }); 
       }
      //获取CHECKBOX中的值
	  if($("#hidden_chbox").length>0){
	           var checknum=0;//定义选择框选取的个数
	    	   hidden_id=$("#hidden_chbox").val();    
	           attrString+=hidden_id+"|";		           
	           $("input:checkbox[id^="+ismust+"]").each(function(){
			         if(this.checked==true){ 
			             checknum+=1;    
			             attrvalue=$(this).val();
			             attrString+=attrvalue+":";   
			         }  
				});
				attrString=attrString+",";
				if($("input:checkbox[id^="+required+"]").length>0){
				     if(checknum==0){
						  $("#span_chbox").addClass("errorSpan");
						  $("#span_chbox").html("请先选择");			
						  return false; 	          
				    }	 
		     }       	           
	   }    
	    //验证所属地区的选择下拉框是否已选择
	     if($("select[id^=arealevel]").length>0){
	         var areacount=0;
	         $("select[id^=arealevel]").each(function(){	
	               if($(this).val()=="0"){ 
	                   areacount+=1;
					}
	         });
	         if(areacount>0)
	         {
		            $("#span_area").addClass("errorSpan");
					$("#span_area").html("请先选择!");
					return false;
	         }         
	     }
	     
	     
	       
	//alert(attrString);
	//把字符串存入隐藏的属性串中 
    $("#attrString").val(attrString);       
 	if($("#price").val()==""){
	 	$("#span_price").addClass("errorSpan");
	 	$("#span_price").html("单价不能为空,请认真填写!");
	 	return false;
 	}
 	var suppply_price=$("#price").val();
 	if(checkNum(suppply_price)==false){
 	   $("#span_price").addClass("errorSpan");
	   $("#span_price").html("单价只能是数字,请认真填写!");
	   return false;
 	}
 	var send_day=$("#send_day").val();
 	if(checkNum(send_day)==false){
	 	$("#span_send_day").addClass("errorSpan");
	 	$("#span_send_day").html("天数只能输入数字,请正确输入!");
	 	return false;
 	} 	
 	if($("#endtime").val()==""){
	 	$("#span_endtime").addClass("errorSpan");
	 	$("#span_endtime").html("过期时间不能为空，请选择日期时间!");
	 	return false;
 	}
 	var clicknum=$("#clicknum").val();
 	if(checkNum(clicknum)==false)
	{   $("#span_clicknum").addClass("errorSpan");
	 	$("#span_clicknum").html("点击量只能输入数字，请正确输入");
	 	return false;
 	}
 	var fare=$("#fare").val();
 	if(checkNum(fare)==false){
	 	$("#span_fare").addClass("errorSpan");
	 	$("#span_fare").html("内容收费只能输入数字，请正确输入");
	 	return false;
 	}
    return true;     
}
//清空span的备选值
function clearspan()
{
 	$("#span_title").removeClass("errorSpan");
 	$("#span_title").html("");
 	$("#span_self").removeClass("errorSpan");
 	$("#span_self").html("");
 	$("#span_cate").removeClass("errorSpan");
 	$("#span_cate").html("");
 	$("#span_endtime").removeClass("errorSpan");
 	$("#span_endtime").html("");
 	$("#span_area").removeClass("errorSpan");
 	$("#span_area").html("");
 	$("#span_attrmust").removeClass("errorSpan");
 	$("#span_attrmust").html("");
 	$("#span_price").removeClass("errorSpan");
 	$("#span_price").html("");
 	$("#span_send_day").removeClass("errorSpan");
 	$("#span_send_day").html("");
    $("#span_clicknum").removeClass("errorSpan");
 	$("#span_clicknum").html("");
 	$("#span_fare").removeClass("errorSpan");
 	$("#span_fare").html("");
 	//对于以后删改字段时要注意添加或移除--等待改进
 	$("#span_must11").html("");
 	$("#span_must11").removeClass("errorSpan");
 	$("#span_must12").html("");
 	$("#span_must12").removeClass("errorSpan");
 	$("#span_must14").html("");
 	$("#span_must14").removeClass("errorSpan");
 	$("#span_chbox").html("");
 	$("#span_chbox").removeClass("errorSpan");
}
function checkNum(value){
	reg = /\d/;
	var result = reg.test(value);
	if(!result)
	{
		return false;
	}
    return true;
}
