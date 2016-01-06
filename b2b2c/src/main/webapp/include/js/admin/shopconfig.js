	//新增物流配置
	function set(){
		//判断是否存在该控件
		if($("#s_area").length>0){	
			 //拼接开始地区串	
			 var add_area_id=""; 
			 var add_area_text="";	  
			 var sel_count=0;   		     
		     $("#s_area").find(".select").each(function(){	   	           		             		     
	       		    var sel_val =  $(this).val();
	       		    var sel_text=$(this).find("option:selected").text();
	      		    add_area_id+=sel_val+",";   		    
		     });	
		     //去除最后的一个逗号
		     var id_len=add_area_id.lastIndexOf(",");
		     var text_len=add_area_text.lastIndexOf(",");
		     if(id_len>0){
		     	add_area_id=add_area_id.substring(0,id_len);
		     }
		     if(add_area_id.indexOf("0")>-1){
		     	 jNotify("请选择开始地区");  
		     	 return ;
		     }else{
		     	 $("#start_area_str").val(add_area_id);
		     }
		 }
	     //拼接配送方式和到达地区串
	     var modearea="";
	     $(".sendmodetype").each(function(){
	    	  var s_id=$(this).val();
	    	  var li_name_str="";
	    	  var end_area_str="";
	    	  $("#show_area_set_"+s_id).find("span").each(function(){
	    			var li_name=$(this).html();	
	    			li_name_str+=li_name+"=";  		
	    	  });
	    	  modearea+=s_id+"|"+li_name_str+":";	    	  
	     });
	     $("#end_area_str").val(modearea);
	     //提交表单
	     $("#sendset").submit();
	}
	
	//新增地区 
	function mode_add_area(a_id){	           			           		      
		 var add_area_id=""; 
		 var add_area_text="";
		 var sel_count=0;   		     
	     $("#s_area_"+a_id).find(".select").each(function(){	   	           		             		     
       		    var sel_val =  $(this).val();
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
	     var count=0;
	     if(add_area_text!=""){	           		         
	         $("#show_area_set_"+a_id).find("input:hidden[name='area_set_val']").each(function(){	           		         
	               if($(this).val()==add_area_id){	           		               	  
           		    	 count++;	           		               
	               }
	         });	           		     
	     }
	     if(count>0){//如果已添加则提示信息,否则则添加成功
	      	jNotify("您已添加该地区!");  	           		        
	     }else{
	        if(add_area_text!=""){	  
	     		 $("#show_area_set_"+a_id).append("<li class='area_li' ><input type='hidden' name='area_set_val' value=\""+add_area_id+"\"/><span>"+add_area_text+"</span><a onclick='delarea(this);'><img src='/include/images/admin/delete.png' style='vertical-align:middle;'/></a></li>");
   		         jSuccess("新增地区成功!");  
   		    }else{
   		         jNotify("请选择地区!");  
   		    }
	     }
	}         
 
	//删除地区
	function delarea(obj){
		$(obj).parent("li").remove();    
		jSuccess("删除成功!");      	          		
	}	
	
	//复制地区
	function mode_copy_area(a_id){
		var area_html=$("#show_area_set_"+a_id).html();
		$(".cal_area").each(function(){		
			$(this).html(area_html);
		});
	}
	
	var areaHtml ="";	
	//修改发货地区
	function updatearea(){
		areaHtml = $("#startdiv").html();
		$("#startdiv").html("<div id='s_area' style='float:left;'></div><a style='cursor:pointer;' onclick='backarea();'>[返回]</a>");
		area_select($("#cfg_topid").val(),"s_area");
	}
	
	//返回发货地区
	function backarea(){
		$("#startdiv").html(areaHtml);
	}
	
	