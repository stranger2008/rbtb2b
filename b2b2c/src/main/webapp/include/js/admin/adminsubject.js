//搜索标题
function searchTitle(){
	 var title=$("#titles").val();
	 if(title==""){
	 	jNotify("搜索标题不能为空!"); 
	 	return;
	 }
	 title=encodeURIComponent(encodeURIComponent(title)); 
	  //文字专题搜索
	 $("#searchnewstitle").shippopup();
	 //文字专题搜索查出数据
	 $('#searchnewstitle').mall({
	    actionName:'/subject!getsearchtitle.action?title='+title+"&ajaxRequestRandom="+Math.random(),
		columnModel:ColumnTitle,
		pageSize:16,
		iscomma:false,
		nullData:"未找到相关的专题记录",
		render:"allcbcheck(),addoper(),backtitlesub_id()"
	
	});
}

//搜索图片标题
function searchImage(){
	 var title=$("#selectnewsImage").val();
	if(title==""){
	 	jNotify("搜索图片标题不能为空!"); 
	 	return;
	 }
	 title=encodeURIComponent(encodeURIComponent(title)); 
	  //文字专题搜索
	 $("#searchnewstitle").shippopup();
	 //文字专题搜索查出数据
	 $('#searchnewstitle').mall({
	    actionName:'/subject!getsearchimage.action?title='+title+"&ajaxRequestRandom="+Math.random(),
		columnModel:ColumnTitle,
		pageSize:16,
		iscomma:false,
		nullData:"未找到相关的图片专题记录",
		render:"allcbcheck(),addimgoper(),backimagesub_id()"
	});
}

//添加标题操作功能
function addoper(){
	 //添加操作功能
	 $('#searchnewstitle').append("<div class='subbottom'></div>");
	 $('.subbottom').append("<input type='button' onclick='selectTitleDataId();' value='确定'/>&nbsp;<input type='button' value='关闭' onclick='closecover();'/>");
}

//添加图片标题操作功能
function addimgoper(){
	 //添加操作功能
	 $('#searchnewstitle').append("<div class='subbottom'></div>");
	 $('.subbottom').append("<input type='button' onclick='selectImageTitleDataId();' value='确定'/>&nbsp;<input type='button' value='关闭' onclick='closecover();'/>");
}

//确定标题后选择数据
function selectTitleDataId(){
	$("#showstitledata").html("");
	var cbstr=$("#title_id").val();
	if(cbstr!=""){
		cbstr+=",";
	}
	$(".cb_box").each(function(){
		 if(this.checked){
		 	cbstr+=$(this).val()+",";
		 }	
	});
	if(cbstr.indexOf(",")>-1){
		var len=cbstr.lastIndexOf(",");
	 	cbstr=cbstr.substring(0,len);
	}
	//赋值
	$("#title_id").val(cbstr);
	//关闭层
	closecover();
	//加载数据
	title_ids();
}


//确定图片标题后选择数据
function selectImageTitleDataId(){
	$("#showsimagedata").html("");
	var cbstr=$("#image_id").val();
	if(cbstr!=""){
		cbstr+=",";
	}
	$(".cb_box").each(function(){
		 if(this.checked){
		 	cbstr+=$(this).val()+",";
		 }	
	});
	if(cbstr.indexOf(",")>-1){
		var len=cbstr.lastIndexOf(",");
	 	cbstr=cbstr.substring(0,len);
	}
	//赋值
	$("#image_id").val(cbstr);
	//关闭层
	closecover();
	//加载数据
	image_ids();
}




//通过标题ids串加载数据
function title_ids(){
	var ids=$("#title_id").val();
	if(ids!=""){
		$.ajax({  	 
	        type: "post",    	     
	        url: "/subject!getDataByIdStr.action?ids="+ids+"&ajaxRequestRandom="+Math.random(),       
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	         	data=eval("("+data+")");
	         	var dataHtml="";
	         	for(var i=0;i<data.length;i++){
	         		if(i==data.length-1){
	         			dataHtml+="<li id='tli"+data[i].sub_id+"' class='lastline'><div class='linedata'>"+data[i].title+"</div>";
	         		}else{
	         			dataHtml+="<li id='tli"+data[i].sub_id+"'><div class='linedata'>"+data[i].title+"</div>";
	         		}
	         		dataHtml+="<span><img src='/include/images/admin/delete.png' onclick='delids("+data[i].sub_id+");'/></span><input class='titleid' type='hidden' value='"+data[i].sub_id+"'/></li>";
	         	}
	         	$("#showstitledata").append(dataHtml);
	        }                 
	    }); 
	}
}

//通过图片标题ids串加载数据
function image_ids(){
	var ids=$("#image_id").val();
	if(ids!=""){
		$.ajax({  	 
	        type: "post",    	     
	        url: "/subject!getDataByIdStr.action?ids="+ids+"&ajaxRequestRandom="+Math.random(),       
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	         	data=eval("("+data+")");
	         	var dataHtml="";
	         	for(var i=0;i<data.length;i++){
	         		if(i==data.length-1){
	         			dataHtml+="<li id='ili"+data[i].sub_id+"' class='lastline'><div class='linedata'>"+data[i].title+"</div>";
	         		}else{
	         			dataHtml+="<li id='ili"+data[i].sub_id+"'><div class='linedata'>"+data[i].title+"</div>";
	         		}
	         		dataHtml+="<span><img src='/include/images/admin/delete.png' onclick='delimgids("+data[i].sub_id+");'/></span><input class='imageid' type='hidden' value='"+data[i].sub_id+"'/></li>";
	         	}
	         	$("#showsimagedata").append(dataHtml);
	        }                 
	    }); 
	}
}



//删除文字搜索的ID
function delids(id){
	$("#tli"+id).remove();
	$("#title_id").val("");
	var cbstr="";
	$(".titleid").each(function(){
		 	cbstr+=$(this).val()+",";	
	});
	if(cbstr.indexOf(",")>-1){
		var len=cbstr.lastIndexOf(",");
	 	cbstr=cbstr.substring(0,len);
	}
	//赋值
	$("#title_id").val(cbstr);
}


//删除图片标题搜索的ID
function delimgids(id){
	$("#ili"+id).remove();
	$("#image_id").val("");
	var cbstr="";
	$(".imageid").each(function(){
		 	cbstr+=$(this).val()+",";	
	});
	if(cbstr.indexOf(",")>-1){
		var len=cbstr.lastIndexOf(",");
	 	cbstr=cbstr.substring(0,len);
	}
	//赋值
	$("#image_id").val(cbstr);
}


//标题回选所选项
function backtitlesub_id(){
	var title_ids = $("#title_id").val();
	backSub_id(title_ids);
}
//图片标题回选所选项
function backimagesub_id(){
	var image_ids = $("#image_id").val();
	backSub_id(image_ids);
}

//回选ID
function backSub_id(ids){
	if(ids!=""){
		var count=0;		
		$(".cb_box").each(function(){
			var id=ids.split(",");
			for(var i=0;i<id.length;i++){
				if(id[i]==$(this).val()){
					this.checked=true;
					count++;
				}
			}
		});
		var cb_length = $(".cb_box").length;
		if(cb_length==count){
			$(".allcb").attr("checked","true");
		}
	}
}


//关闭弹出层
function closecover(){
	$(".shipcover").remove();
	$(".searchnewstitle").css("display","none");	
}


//搜索专题标题的显示字段
var ColumnTitle = [
    {
		header :"<input type='checkbox' class='allcb' onclick='allcbcheck(this);'/>全选",
		headerIndex : '',		
		tphtml:"<input type='checkbox' class='cb_box' value='$'/>",
		tphtmlval:"news_id",	
		width:"10%"
	}, {
		header : '资讯名称',
		headerIndex : 'title',		
		width:"70%",
		cLen:"30"
	}, {
		header : '发布时间',
		headerIndex : 'in_date_data',
		width:"20%"
	}];

//全选功能
function allcbcheck(){
	$(".allcb").click(function(){		
		if(this.checked){
			$(".cb_box").each(function(){
				this.checked=true;
			});
		}else{
			$(".cb_box").each(function(){
				this.checked=false;
			});
		}		
	});
}