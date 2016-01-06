/* 
* tableAjaxPageUI 1.0
* Copyright (c) 2012 linjunqin http://www.ruibaotong.net
* Date: 2012-04-10 
* 使用tableAjaxPageUI来实现无刷新加载表格数据,tab页
*/ 

// 创建table和ajaxPage的一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mall = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mall.defaults, options);
	      var pt=pageMethod;
	      
	      //初始化方法
	      this.each(function(){    
	      	   //初始化加载数据  
	      	   var jsonData=RequestData(opts,"1");
	      	   //填充数据
	      	   showData(this,opts,jsonData)
	      	   
		  }); 
	  }; 
  	 //为容器填充数据
     function showData(obj,opts,jsonData){     	
     	var pt=pageMethod;
     	//清空渲染容器
     	$(obj).html("");
	 	createTable(obj,opts,jsonData);		        
		pt._currentPage(obj,opts);   
		pt._prevClickPage(obj,opts);
		pt._nextClickPage(obj,opts);
 	    pt._tableUI(obj,opts);
 	    pt._trhover(obj,opts);
 	    //渲染列标题
 	    pt._thrender(obj,opts); 	
 	    //如果其它方法不为空则执行方法    
 	    if(opts.otherMethod!=null){
 	        var md=opts.otherMethod;
 	        var count=jsonData.totalCount;
 	        var md=md+"("+count+")";
 	    	eval(md);	 	    	
 	    }
     }
  
	  // 插件的defaults默认配置  
	  $.fn.mall.defaults = {  
	  	 //列标题对象
	  	 columnModel:{},
	  	 //是否启动分页
	     pageBar:true,
	     //列的条数
	     pageSize:6,
	     prevName:"上一页",
	     nextName:"下一页",
	     //默认列宽
	     columnWidth:"10%",
	     //标题长度的截取设置
	     cLen:"",
	     //内容页td中位置设置
	     cpos:"center",
	     //自定义html代码
	     tphtml:null,
	     //自定义html代码所替代值
	     tphtmlval:null,
	     //是否截取html代码中逗号位置
	     iscomma:true,
	     //是否调用其它方法处理数据，方法名
	     otherMethod:null,
	     //当查询出数据为空时显示内容
	     nullData:"暂无数据",
	     //是否渲染方法数据，为空则不执行,如果有多个方法，用逗号隔开
	     render:""     	        
	  };
	    
      //私有方法 
      var pageMethod={
          _currentPage:function(obj,opts){
          		$(obj).find(".pageBar").find("a").click(function(){
		       		 var jsonData=RequestData(opts,$(this).html());	       		 
		       		 showData(obj,opts,jsonData); 
		        });
          },          
          _prevClickPage:function(obj,opts){
	          	$(obj).find(".prev").click(function(){
	  	  		      var curp=$(obj).find(".pageBar").find(".current").html();
	  	  		      var upp=parseInt(curp)-1;
	  	  		      if(upp>0){
	    	  		      var jsonData=RequestData(opts,upp);	       		 
		       		  	  showData(obj,opts,jsonData); 	  		      
	  	  		      }
				});
          }, 
	  	  _nextClickPage: function(obj,opts) {
				$(obj).find(".pnext").click(function(){
	  	  		      var curp=$(obj).find(".pageBar").find(".current").html();
	  	  		      var lastcur=$(obj).find(".pageBar").find("a:last").html();
	  	  		      var upp=parseInt(curp)+1;
	  	  		      if(upp>0 && upp<=parseInt(lastcur)){
	    	  		      var jsonData=RequestData(opts,upp);	       		 
		       		  	  showData(obj,opts,jsonData); 	  		      
	  	  		      }
				});
		 },
		 _tableUI:function(obj,opts){		 		
		 		$(obj).find("table").find("tr:even").css("background","#FFFFFF");
		 		$(obj).find("table").find("tr:odd").css("background","#FFFCF5"); 
		 		$(obj).find("table").find("tr:first").css("background","#FFFFFF");//设置标题列颜色		 		
		 },
		 _trhover:function(obj,opts){
		 		$(obj).find("table").find("tr:gt(0)").each(function(){
		 		      var tr_backcolor="";		 		      	 
					  $(this).hover(
						  function () {
							  tr_backcolor=$(this).css("background-Color"); //得到行本身颜色
						      $(this).css("background","#FFFFCC");
						  },
						  function () {
						      $(this).css("background",tr_backcolor);
					      }
					  );
		 		});
		  },
		  _thrender:function(obj,opts){
		  		var contain=$(obj).attr("id");		
		  		for(var i=0;i<opts.columnModel.length;i++){
		  			 var columnWidth=opts.columnWidth;
		 	    	 if(typeof(opts.columnModel[i].width)!= "undefined" ) {
		 	    	 	 columnWidth=opts.columnModel[i].width;  	 
		 	    	 }
		 	    	 $("."+contain+"_th"+i).css("width",columnWidth);
				}
		  }
	 }
     //填充表格处理
	 function  createTable(obj,opts,jsonData){	
	          //表格数据的读取与拼串
		      var tableStr="";
		      //获取容器ID
		      var contain=$(obj).attr("id");
		      tableStr+="<div class='tablediv'><table class='contentTable'><tr>";
		      /**生成表格头部标题开始**/
		      for(var i=0;i<opts.columnModel.length;i++){
		          tableStr+="<th class='"+contain+"_th"+i+"'>"+opts.columnModel[i].header+"</th>"
		      }
		      tableStr+="</tr>";
		      /**生成表格头部标题结束**/
		     
		      /**如果为空数据则返回开始**/
		      if(jsonData.data.length==0){		      	 
		         $(obj).append("<div class='nulldata'>"+opts.nullData+"</div>");
		         renderData(opts)
		      	 return;
		      }
		      /**如果为空数据则返回结束**/
		          
		      /**渲染表格内容开始**/
		      for(var i=0;i<jsonData.data.length;i++){
		      		tableStr+="<tr>";
		      		for(var j=0;j<opts.columnModel.length;j++){	
		      		     var  columnName=opts.columnModel[j].headerIndex;   
		      		     var  columnContent="";
		      		     if(columnName!=""&&typeof(eval("jsonData.data[i]."+columnName))!="undefined"){
		      		     	 columnContent=eval("jsonData.data[i]."+columnName);
		      		     }		      		     
		      		     //截取字符长度判断
		      		     var colLen;
		      		     if(typeof(opts.columnModel[j].cLen)!="undefined"&&opts.columnModel[j].cLen!=""){
		      		     	  colLen=parseInt(opts.columnModel[j].cLen);
		      		     	  columnContent=columnContent.substring(0,colLen);
		      		     }else if(opts.cLen!=""){
		      		     	  colLen=parseInt(opts.cLen);
		      		     	  columnContent=columnContent.substring(0,colLen);
		      		     }
		      		     //设置单元格的居中形式
		      		     var tdpos=typeof(opts.columnModel[j].cpos)=="undefined"?opts.cpos:opts.columnModel[j].cpos;	      		     
		      		     //设置是否支持自定义HTML代码
		      		     var tphtmlStr="";
		      		     if(typeof(opts.columnModel[j].tphtml)!="undefined"){
		      		     	  //对多个htmls标签以|线隔开处理数据	
		      		          var htmlArr=opts.columnModel[j].tphtml.split("|");
		      		          var htmlvalArr=opts.columnModel[j].tphtmlval.split("|");
		      		          for(var k=0;k<htmlArr.length;k++){
		      		          	  var tphtml=htmlArr[k];		      		          	 
			      		          var tphtmlval=eval("jsonData.data[i]."+htmlvalArr[k]);
			      		          if(tphtmlval!=null&&tphtmlval!=""){
			      		              var commaLen=(tphtmlval.toString()).indexOf(",");
				      		          if(opts.iscomma==true&&commaLen>-1){
				      		          		tphtmlval=tphtmlval.substring(0,commaLen);
				      		          }	
			      		          }			      		         
			      		          tphtmlStr+=tphtml.replace("$",tphtmlval);
		      		          }
		      		          columnContent=tphtmlStr+columnContent;
		      		     }
		      		     tableStr+="<td align='"+tdpos+"'>"+columnContent+"</td>";
		      		}
		      		tableStr+="</tr>";
		      }
		      /**渲染表格内容结束**/		      
		      tableStr+="</table></div>";	      
		      $(obj).append(tableStr);
		      //调用生成分页插件方法
		      createPageBar(obj,opts,jsonData);	
	  }
  
	  //填充分页控件,分页控件的处理
	  function createPageBar(obj,opts,jsonData){  
	  	      //初始化分页信息
		      var pBar=opts.pageBar;
		      var pSize=opts.pageSize;
		      var pCount=jsonData.totalCount;
		      var cPage=jsonData.currentPage;
		      //判断是否开启	      
		      if(pBar==true&&pCount>0){
		          var barDiv="";
		          barDiv+="<div class='pageBar'>";		          
		          if(pSize!=''){
		          	 var pageCount=parseInt(pCount/pSize);	          	 
		          	 //确定分页数是否加一
		          	 if(pageCount!=(pCount/pSize)){
		          	 	pageCount=pageCount+1;
		          	 }
		          	 var pageNum=parseInt(cPage)+5;
		          	 var prevNum=parseInt(cPage)-5;
		          	 var firstPage=1;
		          	 //判断循环开始页的位置
		          	 if(prevNum>0){
		          	 	firstPage=prevNum;
		          	 } 	          	 
		          	 //判断循环结束页的位置
		          	 if(pageNum>=pageCount){
		          	 	pageNum=pageCount;
		          	 }
		          	 //上一页
		          	 barDiv+="<span id='test' class='prev'>"+opts.prevName+"</span>";      
		          	 //前两页处理    	 	          	 
		          	 if(cPage>7){
		          	    for(var i=1;i<3;i++){
		          	       barDiv+="<a>"+i+"</a>";
		          	       if(i==2){barDiv+="...";}
		          	    }  
	          	     }
		          	 //分页循环
		          	 for(var i=firstPage;i<pageNum+1;i++){	   	     	   
	          	    	if(cPage==i){
		          	 		barDiv+="<a class='current'>"+i+"</a>";
		          	 	}else{
		          	 		barDiv+="<a>"+i+"</a>";
		          	 	}          	     	 	
		          	 }	          	 
		          	 //后两页处理
		          	 if((parseInt(cPage)+7)<pageCount){		          	 
		          	    for(var i=(pageCount-1);i<pageCount+1;i++){
		          	       if(i==(pageCount-1)){barDiv+="...";}
		          	       barDiv+="<a>"+i+"</a>";	          	       
		          	    }  
	          	     }
		          	 //下一页
		          	 barDiv+="<span class='pnext'>"+opts.nextName+"</span>";
		          }	  
		          barDiv+="</div>";
		          //为容器渲染数据
		      	  $(obj).append(barDiv);
		      	  //渲染数据方法
		      	  renderData(opts);
	      	   	  
		     }
	  }
	  
	  //渲染数据方法	
	  function renderData(opts){
	  	 var render=opts.render;
	  	 var rds=render.split(",");
	  	 for(var i=0;i<rds.length;i++){
	  	 	eval(rds[i]);
	  	 }
	  }

	  // AJAX请求数据
	  function RequestData(opts,currentPage) {   
	 	  // 定义常量	
	 	  var requestData="";  
	  	  var ps=opts.pageSize;
	  	  var cp=currentPage;  
	  	  var dataUrl=opts.actionName;//请求路径
	  	  var dataPara="?ps="+ps+"&cp="+cp+"&ajaxRequestRandom="+Math.random();//构造参数
	  	  if(dataUrl.indexOf("?")>0){
	  		  dataPara="&ps="+ps+"&cp="+cp+"&ajaxRequestRandom="+Math.random();
	  	  }
	      // 请求数据     
	      $.ajax({  	 
	           type: "post",    	     
	           url: dataUrl+dataPara,       
	           datatype:"json",
	           async:false,
	           success: function(data){ 
	            	requestData=data;
	           }                 
	      }); 
	      var jsonData="";
	      //转换成json格式数据
	   	  jsonData=eval("("+requestData+")");
	      return jsonData; 	  
	  };  
// 闭包结束  
})(jQuery);   


 // 创建弹出层一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.shippopup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.shippopup.defaults, options);
	      //初始化方法
	      this.each(function(){    
	          //获取窗体的宽度与高度
    	   	  var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	      	_documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight;
			  var _posiLeft = (_windowWidth - _popupWeight)/2;
	          //新建一个DIV遮盖层
    	   	  $("<div id='shipcover' class='shipcover' onclick='closecover();'></div>").appendTo("body");	
    	   	  $("#shipcover").height(_documentHeight);
    	   	  $("#shipcover").width("100%");
    	   	  $("#shipcover").css({"opacity": "0.3", background: "black","position":"absolute","left":"0px","top":"0px","z-index":888});
    	   	  $("#shipcover").fadeIn("slow");
			  $(this).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":9999});//设置position
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.shippopup.defaults = {  
	 
	  };	
// 闭包结束  
})(jQuery); 




