//新增分类 
function com_add_cat(){	           			           		      
		 var add_area_id=""; 
		 var add_area_text="";	  
		 var sel_count=$("input:hidden[name='all_area_id_str']").size();   	     
	     $(".cat_sel").each(function(){	   	           		             		     
       		    var sel_val =$(this).val();
       		    var sel_text=$(this).find("option:selected").text();
       		    //去掉请选择
       		    if(sel_val!="0"){
       		     	 add_area_id+=sel_val+",";
       		    	 add_area_text+=sel_text+",";		           		     
       		    }       		    
	     });	
	     //去除最后的一个逗号
	     var id_len=add_area_id.lastIndexOf(",");
	     var text_len=add_area_text.lastIndexOf(",");
	     if(id_len>0){
	     	add_area_id=add_area_id.substring(0,id_len);
	     }
	     if(text_len>0){
	     	add_area_text=add_area_text.substring(0,text_len);
	     }
	     //判断是否已经添加过该ID
	     var count=0;
	     if(add_area_text!=""){	           		         
	         $("input:hidden[name='all_area_id_str']").each(function(){	         
	               if($.trim($(this).val())==add_area_id){	           		               	  
           		    	 count++;	           		               
	               }	               
	         });	           		     
	     }
	     //判断添加的个数是否达到上限
	     if(sel_count>5){
	        jNotify("分类最多只能添加六个!"); 
	     }else if(count>0){//如果已添加则提示信息,否则则添加成功
	      	jNotify("您已添加该分类!");  	           		        
	     }else{
	        if(add_area_text!=""){	  
   		     	 $("#show_add_cat").append("<div style='line-height:20px;'><input type='hidden' name='all_area_id_str' value=\""+add_area_id+"\"/>"+add_area_text+"<a class='oper' href='javascript:void(0);' onclick='del_add_area(this)'>[删除]</a></div>");
   		         jSuccess("新增分类成功!");  
   		    }else{
   		         jNotify("请选择分类!");  
   		    }
	     }
	}         
	 
	//删除分类
	function del_add_area(obj){
			$(obj).parent("div").remove();    
			jSuccess("删除成功!");      	          		
	}	