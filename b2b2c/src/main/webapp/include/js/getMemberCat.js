/*
*功能：分类地区获取
*主要有：页面加载时所属地区添加页运行的方法、加载地区的级联select、加载只读产品分类属性的显示、加载显示分类
*/

//加载地区的级联select
var membercatcount=1;
function onlyshowmembercat(id,level){
       $.ajax({  	 
           type: "post",    	     
           url: "/membercat!membercatslist.action?up_cat_id="+id+"&count="+(level+1)+"&ajaxRequestRandom="+Math.random(),       
           datatype:"json",
           async:true,
           success: function(data){ 
            if(data.length>0)
            {
                 if(level<=membercatcount)
                 {
                    for(var i=(level+1);i<=(membercatcount+1);i++)
	                    {
	                      $("#mcatlevel"+i).remove(); 		                      
	                    }
	                $("#membercatlist").append(data);	                     
                    membercatcount=level+1;
                    return false;
                 }             
            }
            else 
            {
                for(var i=(level+1);i<(areacount+1);i++)
                  {
                     $("#mcatlevel"+i).remove();//移除元素 	                      	                      
                  }	  
                membercatcount=level;                              	              
            }
         }                 
     });  	 
}

//根据上级分类ID找出本级的分类添加到select中
 function getThisMemberCat(up_cat_id,count){
 	$.ajax({  	 
            type: "post",    	     
            url: "/membercat!membercatslist.action?up_cat_id="+up_cat_id+"&count="+count+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){          
   	           $("#membercatlist").append(data);
   	        }
       });
 } 

//所属地区的回选方法
function membercat_select(id){

	var membercat_value = '';
	 membercat_value=$("#hidden_membercat_value").val();	
    if(membercat_value==""){ //默认新增的情况下，找出一级分类
    	getThisMemberCat(id,"");
    }else{//回选状态下，默认选择之前选择的分类
 	var cat_sel_val = new Array();
 	cat_sel_val = membercat_value.split(",");
	if(cat_sel_val.length > 0){
 		for(var i=0;i<cat_sel_val.length;i++){
 			if(cat_sel_val[i] != ""){
 				//根据上级找出本级分类
 				getThisMemberCat(cat_sel_val[i],(i+1));
 				//选中之前选择的分类
 				selectCheckObj("mcatlevel"+(i+1),cat_sel_val[i+1]);
 			} 			
 		}
 		membercatcount=cat_sel_val.length-1;
 	  }
   }
}

/****列表对地区和分类的搜索获取值功能****/
function showSelectDiv(cat_attrid,bandcatid){
	    //地区获取
	    if(cat_attrid!="")
	    {
		 var cat_attr = document.getElementsByName(cat_attrid);
		 var cat_attr_str = '';
		 for(var i=0;i<cat_attr.length;i++){
			if(cat_attr[i].value!='0'){
				cat_attr_str += cat_attr[i].value+',';
			}
		 }
		 if(cat_attr_str!="")
		 {
		   cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
		 }
		 $("#"+bandcatid+"").val(cat_attr_str);
		}
		document.forms[0].submit();
}

//对查找出的select 进行回选
function selectCheckObj(objid,thisvalue){
	var levelone = document.getElementById(objid);
	if(levelone!=null){
		for (var j = 0; j < levelone.options.length; j++) {
	        if (levelone.options[j].value == thisvalue) {
	            levelone.options[j].selected = true;
	        }
        }
	}
}
	