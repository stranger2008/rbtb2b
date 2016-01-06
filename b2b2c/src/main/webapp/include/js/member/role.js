 /*
*功能：系统角色管理
*/
 $(document).ready(function(){ 
	   //checkbox 点击事件 选择框
       $("input:checkbox").click(function(){       
       	  var cb=$(this).val();
	      var str="input:checkbox[id*="+cb+"]";
          if(this.checked)
          {	
	            $(str).each(function(){	                 	  
		                this.checked=true;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=true;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=true;  
				              }); 	
		                }); 		                		             
			    }); 	
		   }
		  else
		  {
		       $(str).each(function(){	                 	  
		                this.checked=false;  
		                var ccb=$(this).val();
		                var cstr="input:checkbox[id*="+ccb+"]";		               
		                $(cstr).each(function(){  	                 
		                     this.checked=false;  
		                     var rccb=$(this).val();
		                     var rcstr="input:checkbox[id*="+rccb+"]";	
		                     $(rcstr).each(function(){  	                 
				                 this.checked=false;  
				                }); 	
		                }); 		                		             
			    }); 	
		  }       
       });     
 });       