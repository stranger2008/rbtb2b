
//isImgControl：缩略图片大小是否控制
//isYin：是否加水印
var isImgtemp;


function uploadImgControlAndYin(isControl){
	isImgtemp = isControl;
}

//上传商品图片工具,支持多张上传
function uploadGoodsImgMulti(){
	uploadComponent("uploadifyfile","uploadresult","fileQueue","goodsimages",true);
}


//上传一张图片工具,支持多张上传
function uploadOneImgMulti(){
	uploadComponent("uploadifyfile","uploadresult","fileQueue","image",true);
}

//上传一张图片工具
function uploadOneImg(){
	uploadImg("uploadifyfile","uploadresult","fileQueue");
}

//上传图片工具
function uploadImg(uploadifyfile,uploadresult,fileQueue){
	uploadComponent(uploadifyfile,uploadresult,fileQueue,"image",false);
}

//uploadifyfile：文件路径输入框的ID
//uploadresult：上传文件成功后的文件保存路径
//fileQueue:文件上传效果队列
//type:上传文件类型 image,file,flash
//multi:多文件上传 true,false
//isImgtemp:1: 控制缩略图；2：水印；123：控制缩略图+水印
function uploadComponent(uploadifyfile,uploadresult,fileQueue,type,multi){

	var paraString = '';
	if(isImgtemp != '' && isImgtemp=='1'){
		paraString +='?'+ 'isImgControl='+isImgtemp;
	}
	if(isImgtemp != '' && isImgtemp=='2'){
		paraString +='?'+ 'isYin='+isImgtemp;
	}
	if(isImgtemp != '' && isImgtemp=='123'){
		paraString +='?'+ 'isImgControl_isYin='+isImgtemp;
	}
	var upAction;
	var sizeLimit;
	var fileDesc;
	var fileExt;
	var bite=1024*1024;
	if(type == 'image'){
		upAction = '/uploadfy_executeUpimages.action'+paraString;
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}
	else if(type == 'file'){
		upAction = '/uploadfy_executeUpFile.action';
		sizeLimit = parseFloat($("#file_size").val())*bite;
		var ftype=$("#file_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}
	else if(type == 'flash'){
		upAction = '/uploadfy_executeUpFlash.action';
		sizeLimit = parseFloat($("#flash_size").val())*bite;
		var ftype=$("#flash_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}else if(type == 'goodsimages'){
		upAction = '/uploadfy_executeUpGoodsimages.action';
		sizeLimit = parseFloat($("#image_size").val())*bite;
		var ftype=$("#image_type").val();		
		fileDesc = '支持格式:'+ftype+".";
		var reg="\/";
		ftype=ftype.replace(new RegExp(reg,"gm"),";*.");
		fileExt="*."+ftype;
	}
    
    var imagepath="/include/components/upload";
	$("#"+uploadifyfile).uploadify({
          'uploader'       : imagepath+'/uploadify.swf',//控制弹出选择文件框
          'script'         : upAction,//执行的xx.action
          'cancelImg'      :imagepath+'/cancel.png',//选择文件到文件队列中后的每一个文件上的关闭按钮图标
          'folder'         : 'uploadifyfile',//上传文件存放的目录 。
          'queueID'        : fileQueue,//文件队列的ID，该ID与存放文件队列的div的ID一致。
          'auto'           : true,//是否自动上传图片
          'multi'          : multi,//是否支持多上传
          'sizeLimit'      : sizeLimit,//上传文件的大小限制 。
          'displayData'    : 'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比
          'fileDesc'       : fileDesc, //这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”，打开文件选择框效果
          'simUploadLimit' : 2,//支持同时上传图片的个数
          'fileExt'        : fileExt,//设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。
          'buttonText'     : 'Browser',//浏览按钮的文本，默认值：BROWSE 。
          'fileDataName'   : 'uploadifyfile',  //设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata
          'height'         : 20,  //设置浏览按钮的高度 ，默认值：30。
		  'width'          : 60,   //设置浏览按钮的宽度 ，默认值：110。
		  'buttonImg'      : imagepath+'/upload.jpg',  //浏览按钮的图片的路径 。只要是用解决按钮文字显示不支持中文的办法
		  'wmode'          : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque 。
          'onComplete'     : function (event, queueID, fileObj, response, data){ 
             if(multi==true)//判断是否为多上上传图片
             {
             	 $("#"+uploadresult).val(addMultiImageUrl(uploadresult,response));//显示上传成功结果 
             	 var resultImg=$("#"+uploadresult).val();
             	 if(resultImg.lastIndexOf(",")>-1){
             	    var lastImgLength=resultImg.lastIndexOf(",");             	    
             	    resultImg=resultImg.substring(0,lastImgLength);
             	    $("#"+uploadresult).val(resultImg);
             	 }             	 
             }
             else
             {
             
          	 	if(endWith(response,",")){
          	 		response = response.substring(0,response.length-1);
          	 	}
             	$("#"+uploadresult).val(response);//显示上传成功结果      
             }    
          }
    });
}
//处理多图片上传的时候图片路径的添加
function addMultiImageUrl(uploadresult,responses)
{
 var myresult=$("#"+uploadresult).val();
  if(responses.length==0)
   {
      myresult="";
      alert("请上传图片");
   }
  else
   {
      var len=myresult.length;
      var endstring=myresult.substring(len-1,len);
      if(endstring !=","&& myresult!="")
        {
           myresult=myresult+",";
        }
        myresult += responses+',';        
   }
  return myresult;
}
//判断字符串是否是以某个字符结尾
function endWith(s1,s2)  
{  
	if(s1.length<s2.length)
		return	false;
	if(s1==s2)
		return	true;
	if(s1.substring(s1.length-s2.length)==s2)
		return	true;
	return	false;
}



