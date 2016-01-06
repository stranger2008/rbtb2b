/*
*功能：系统地区管理主要：地区（显示、插入、更新、删除）操作、创建一个地区框
*/

//显示地区信息
var areanum=1;
function showarea(area_id,level,is_one){	  
     //加载颜色分别
      $("#arealevel"+level+" span").css("border","1px solid #FFF"); 
      $("#arealevel"+level+" li").css("background-color","#FFF"); 
      $("#li"+area_id).css("background-color","#e2efeb");  
      $("#areali"+area_id).css("border","1px solid #e2efeb");  
      //加载地区
      var title=$("#areali"+area_id).html();
      $.ajax({  	 
          type: "post",    	     
          url: "/admin_Area_getList.action?up_area_id="+area_id+"&count_level="+(level+1)+"&ajaxRequestRandom="+Math.random(),       
          datatype:"json",
          success: function(data){  
          if(data.length>0){
               if(level<=areanum){  
	                 for(var i=(level+1);i<=(areanum+1);i++)
	                 {
	                     $("#arealevel"+i).remove(); 		                      
	                 }
	                 $("#arealist").append(data); 
	                 areanum=level+1;      
	                 if(is_one!=1){
		                 $("#menutitle"+(areanum)).html(title);
	                     $("#menuvalue"+(areanum)).val(area_id);	                
	                 }                     
               }
            }else{
                 creatediv(area_id,level,title);
            }        
         }
      });  	
   }	

//进入更新地区的页面     
function updatearea(area_id,level){
    $("#area_id").val(area_id);
    $("#area_level").val(level);
    document.forms[0].action="/admin_Area_view.action";
 	document.forms[0].submit();
}

//进入新增地区的页面   
function insertarea(area_id,level){
	$("#up_area_id").val(area_id);
	$("#area_level").val(level);
    document.forms[0].action="/admin_Area_add.action";
 	document.forms[0].submit();
}    

//删除一个地区
function deletearea(area_id,level){
    hiConfirm('您要删除这些选项吗，有可能会对子地区造成影响.确定要删？', '友情提示',function(r){ 
    if(r){ 
	      $.ajax({  	 
	         type: "post",
	         url: "admin_Area_delete.action?id="+area_id+"&ajaxRequestRandom="+Math.random(),    
	         datatype:"json",
	         success: function(data){   
	            $("#areali"+area_id).parent("li").remove();               
	            for(var i=level+1;i<areanum+1;i++){
	               $("#arealevel"+i).remove(); 	
	             }               	 
	            jSuccess("删除成功!");    
	         },error:function(){
	            jNotify("删除失败!"); 
	         }
	    });  	
      }
  });  
} 
 //创建一个地区框 
function creatediv(area_id,level,title){
   for(var i=(level+1);i<=(areanum+1);i++){
       $("#arealevel"+i).remove();		       
     }
   var area_level=level+1;
   $("#arealist").append("<div class='areadiv' id=arealevel"+area_level+"><h3 class='areatitle'><div id=menutitle"+area_level+" class='spantitle'>"+title+"</div></h3><input id=menuvalue"+area_level+" type='hidden' value='"+area_id+"'><div class='areacontent'><li style='text-align:center;'>您还未添加子地区</li></div><div class='areaadd'><img class='add' title='新增地区' onclick='insertarea(\""+area_id+"\","+area_level+")' src='/include/images/add.png'/></div></div>"); 
   areanum=area_level;
}

