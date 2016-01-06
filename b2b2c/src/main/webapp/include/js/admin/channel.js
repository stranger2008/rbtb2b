 /*
*功能：系统栏目管理
*主要有：更新栏目选择更新模板、栏目排序批量操作、实现树的全选功能
*页面模板选中文件、详细页模板 选中文件、判断栏目名称是否已经存在、AJAX调用更新网站的方法所有栏目
*/
 //更新栏目选择更新模板
 function selectchanneltemp_path()
  {
     window.open("/include/fileselectlist.jsp?id=tempvalue",null, "height=580,width=700,status=no,toolbar=no,menubar=no,location=no");
  }
 //更新详细页选择更新模板
 function selectchannelarticle_temp()
 {
   window.open("/include/fileselectlist.jsp?id=tempvalueart",null, "height=580,width=700,status=yes,toolbar=no,menubar=no,location=no");
 }     
//栏目排序批量操作
function updateChannelSortNo(actionName) {
	
		var admin_ch_id = "";
		var chks = document.getElementsByTagName("input");//得到所有input
		for (var i = 0; i < chks.length; i++) {
			if (chks[i].type.toLowerCase() == "checkbox") {
                //得到所名为checkbox的input
				admin_ch_id += chks[i].value + ",";
			}
		}
		var len = admin_ch_id.length;
		if (len == "0") {
			alert("\u64cd\u4f5c\u5931\u8d25\uff01\u8bf7\u91cd\u65b0\u64cd\u4f5c\uff01");
		} else {
			var sort_no_id = admin_ch_id.substring(0, len - 1);
			document.getElementById("admin_Sort_id").value = sort_no_id;//用于隐藏所有的ID
			document.forms[0].action = "admin_Channel_updateAllSortNo.action";
			document.forms[0].submit();
		}
}
//实现树的全选功能
function checkStatus(no, chkBox) {
	var chks = document.getElementsByTagName("input");//得到所有input
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].type.toLowerCase() == "checkbox") {
                //得到所名为checkbox的input
			if (chks[i].className == no) {
                    //ID等于传进父目录ID时
				chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
				this.checkStatus(chks[i].value, chks[i]);//递归保持所有的子目录选中
			}
		}
	}
}
/////////////////////////////////////
  function  selectFile()
	{
		var objdiv =document.getElementById("searchDiv");
	    objdiv.style.display="block"; 
	} 
/////////////////////////////////////////////////	
//新版的网站更新功能
 function updateNewPage(flageName,updateId ,update_ch_name,update_type)
 {
   if(flageName=="all"){
   		updateId = "0";
   		update_ch_name = "全部";
   }
   updateAllChannelPages(updateId,update_ch_name,flageName,update_type);
}

 ///////////////////////////////////////////////////
  //AJAX调用更新网站的方法所有栏目
    function updateAllChannelPages(update_ch_id,update_ch_name ,flages,update_type){
    $("#msg").html("&nbsp;");
 	$.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_updateChannelPage.action?update_type="+update_type+"&update_state="+flages+"&update_ch_id="+update_ch_id+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){
             var datastr=data.split('@');
             var datastring=datastr[0].toString();
             var prestring="";
             if(datastr.length>1){
             prestring= datastr[1].toString();
             }
              if(datastring=="0")
              {
                $("#msg").html("更新<"+update_ch_name+">栏目失败,请重新操作！");
              }
               if(datastring=="1")
              {
               $("#msg").html("更新<"+update_ch_name+">栏目成功！"+prestring);
              }
              if(datastring=="2")
              {
               $("#msg").html("更新未全部成功！存在<"+datastr[1].toString()+">栏目,更新失败！请重新操作！");
              }
            
   	        } 
       });
        $("#loading").ajaxStart(function(){$(this).css("display", "block");});//用于显示加载图标
		$("#loading").ajaxSuccess(function(){$(this).css("display", "none");});	
       } 
//////////////////////////////////////////////////
	 function updatePage()//更新栏目
       {
       var o=document.getElementById("channelid");
       var pagesvalue=o.options[o.options.selectedIndex].value; 
       var pagestext=o.options[o.options.selectedIndex].text; 
       if(pagesvalue==-1)
       {
        alert("请选择网站栏目！");
       }
       else
       {
       updateChannelPages(pagesvalue,pagestext);
       }
       }
///////////////////////////////////////////////////       

   //AJAX调用更新网站的方法
    function updateChannelPages(update_ch_id,update_ch_name){
 	$.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_updateChannelPage.action?update_ch_id="+update_ch_id+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){    
              if(data=="0")
              {
               $("#msg").html("更新<"+update_ch_name+">栏目失败,请重新操作！");
              }
              else
              {
                 $("#msg").html("更新"+update_ch_name+"栏目成功！");
              }
            
   	        } 
       });
        $("#loading").ajaxStart(function(){$(this).css("display", "block");});//用于显示加载图标
		$("#loading").ajaxSuccess(function(){$(this).css("display", "none");});	
       } 
 /////////////////////////////////////////////      
//判断栏目名称是否已经存在
function checkcname()
     {
      var cnames=$("#cnames").val();
      $.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_getCName.action?cname="+cnames,       
            datatype:"json",
            async:false,
            success: function(data){   
              if(data !="0")
              {
               $("#cname").html("*(已经存在该栏目名称！)");
              }
              else
              {
              $("#cname").html("");
              }
             
   	        } 
       });
     }
 ////////////////////////////////////////    
  //判断栏目名称是否已经存在
function updatecheckcname()
     {
      var cnames=$("#cnames").val();
      var hid=$("#hid").val();
      $.ajax({  	 
            type: "post",    	     
            url: "/admin_Channel_getCName.action?channel_id="+hid+"&cname="+cnames+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:false,
            success: function(data){   
              if(data !="0")
              {
               $("#cname").html("*(已经存在该栏目名称！)");
              }
              else
              {
              $("#cname").html("");
              }
             
   	        } 
       });
     }
//////////////////////////////////////



//更新页面
function doHtmlPage(type,ch_id){
	$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
	var vtype="",vch_id="";
	if(type!=null){
		vtype = type;
	}
	if(ch_id!=null){
		vch_id = ch_id;
	}
	if(vtype!=""){
		$.ajax({  	 
            type: "post",    	     
            url: "/channel!updateHtmlPage.action?type="+vtype+"&ch_id="+vch_id+"&timeStamp=" + new Date().getTime()+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){ 
             if(data=="1")
             {   
               $("#msg").html("更新成功");
             }
             else if(data=="0")
             {
              $("#msg").html("更新失败");
             }
   	        } 
       });
		out.println("更新成功");
	}else{
	  	$("#msg").html("更新失败");
	  	out.println("更新失败");
	}
} 

//更新索引
function doAllIndex(){
	var mod="";
	mod=$("#mod").val();
	if(mod==""){
		alert("请选择要更新的模块!");
	}else{
		$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
		$.ajax({  	 
            type: "post",    	     
            url: "/admin_LuceneIndex_updateIndex.action?mod="+mod+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){ 
	             if(data=="1"){   
	                $("#msg").html("更新成功");
	             }else if(data=="0"){
	                $("#msg").html("更新失败");
	             }
   	        } 
       });
	}
}


//更新全部模块索引
function doAllModelIndex(){
	$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
	$.ajax({  	 
           type: "post",    	     
           url: "/admin_LuceneIndex_allUpdate.action?mod="+mod+"&ajaxRequestRandom="+Math.random(),       
           datatype:"json",
           async:true,
           cache:false,
           success: function(data){ 
             if(data=="1"){   
                $("#msg").html("更新成功");
             }else if(data=="0"){
                $("#msg").html("更新失败");
             }
  	        } 
      });
}




//增量更新索引
function doAddIndex(){
	var mod="";
	mod=$("#mod").val();
	if(mod==""){
		alert("请选择要更新的模块!");
	}else{
		$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
		$.ajax({  	 
            type: "post",    	     
            url: "/admin_LuceneIndex_updateAddIndex.action?mod="+mod+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){ 
	             if(data=="1"){   
	                $("#msg").html("更新成功");
	             }else if(data=="0"){
	                $("#msg").html("更新失败");
	             }
   	        } 
       });
	}
}




//更新静态详细页
function doArticlePage(type,ch_id){

	$("#msg").html("<img src='/include/images/admin/upLoading.gif'/>更新中...");
	var vtype="",vch_id="";
	if(type!=null){
		vtype = type;
	}
	if(ch_id!=null){
		vch_id = ch_id;
	}
	if(vtype!=""){
 	$.ajax({
            type: "post",
            url: "/channel!updateArticleHtml.action?type="+vtype+"&ch_id="+vch_id+"&timeStamp=" + new Date().getTime()+"&ajaxRequestRandom="+Math.random(),       
            datatype:"json",
            async:true,
            cache:false,
            success: function(data){    
	            if(data=="0")
	            {
	               $("#msg").html("更新失败");
	            }
	            else if(data=="1")
	            {
	             $("#msg").html("更新成功");
	            }
   	        } 
       });
    }
    else
    {
     $("#msg").html("更新失败");
    }
} 


