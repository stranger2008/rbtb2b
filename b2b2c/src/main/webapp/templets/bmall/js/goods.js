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
	     nullData:"暂无数据"     	        
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
		      tableStr+="<table class='contentTable'><tr>";
		      /**生成表格头部标题开始**/
		      for(var i=0;i<opts.columnModel.length;i++){
		          tableStr+="<th class="+contain+"'_th"+i+"'>"+opts.columnModel[i].header+"</th>"
		      }
		      tableStr+="</tr>";
		      /**生成表格头部标题结束**/
		     
		      /**如果为空数据则返回开始**/
		      if(jsonData.data.length==0){		      	 
		         $(obj).append("<div class='nulldata'>"+opts.nullData+"</div>");
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
			      		          if(tphtmlval!=null){
			      		              var commaLen=tphtmlval.indexOf(",");
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
		      tableStr+="</table>";	      
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
		     }
	  }

	  // AJAX请求数据
	  function RequestData(opts,currentPage) {   
	 	  // 定义常量	
	 	  var requestData="";  
	  	  var ps=opts.pageSize;
	  	  var cp=currentPage;  
	  	  var dataUrl=opts.actionName;//请求路径
	  	  var dataPara="?ps="+ps+"&cp="+cp;//构造参数
	  	  if(dataUrl.indexOf("?")>0){
	  		  dataPara="&ps="+ps+"&cp="+cp;
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
	  $.fn.popup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup.defaults, options);
	      //初始化方法
	      this.each(function(){    
	          //获取窗体的宽度与高度
 	   		  var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
 	   		  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = $("#areabotton").offset().top +19;
			  var _posiLeft = $("#areabotton").offset().left-21;
	          //新建一个DIV遮盖层
   	   		  $("<div id='cover' class='cover'></div>").appendTo("body");	
   	   		  $("#cover").height(_documentHeight);
   	   		  $("#cover").width("100%");
   	   		  $("#cover").css({"opacity": "0.3","position":"absolute","left":"0px","top":"0px","z-index":888});
   	   		  $("#cover").fadeIn("slow");
			  $(this).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":9999});//设置position
		  }); 
	  };  
// 闭包结束  
})(jQuery); 



// 创建tab页切换一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mallTab = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mallTab.defaults, options);
	      //初始化方法
	      var tab_li=$(this).find(".tabbar").children("li");
	      var tab_div=$(this).find(".tabdiv");
	      this.each(function(){    
	      	   	tab_li.each(function(i){
	      	   		$(this).click(function(){
	      	   		     var index=$(this).index();//获取当前点击的li索引 	
	      	   		     tab_li.removeClass("selected");//去除所有样式
		      	   		 $(this).addClass("selected");//为当前选择框增加样式
		      	   		 var displayIndexStr=opts.displayIndex;//获取设置全显DIV的值		      	   		 
	      	   			 if(displayIndexStr!=null&&displayIndexStr.indexOf(index)>-1){
	      	   			  	 tab_div.css("display","block");
	      	   			 }else{
		      	   			 tab_div.css("display","none");//隐藏所有的tabdiv  	      	   			   			 
		      	   			 tab_div.eq(index).css("display","block")//显示对应的tabdiv
		      	   		}
	      	   		});	      	   
	      	   });
		  }); 
	  };  
	
	  // 插件的defaults默认配置  
	  $.fn.mallTab.defaults = {  
	  	 //选择的样式名称
	  	 selected:"selected",
	  	 //显示所有DIV的索引, 如果有多个值以,隔开
	  	 displayIndex:""
	  };	
// 闭包结束  
})(jQuery);   



var ColumnTitle = [
                {
					header : '买家',
					headerIndex : 'buy_cust_name',
					width:"10%"			
				}, {
					header : '宝贝名称',
					headerIndex : 'goods_name',
					width:"30%",
					cLen:"30"
				}, {
					header : '出价',
					headerIndex : 'order_price',
					width:"10%"			
				}, {
					header : '购买数量',
					headerIndex : 'o_goods_num',
					width:"10%"
				}, {
					header : '交易时间',
					headerIndex : 'sale_time',
					width:"20%"
				}];


var ColumnEvalTitle = [
                {
					header : '',
					headerIndex : '',
					width:"70%",
					cpos:"left",
					tphtml:"<p class='mj_p1'>$</p>|<p class='mj_p1'>$</p>",
					tphtmlval:"g_comment|evalcom_data"			
				}, {
					header : '',
					headerIndex : '',
					width:"30%",
					tphtml:"<p class='mj_p1'><a href='#'>$</a></p>|<p class='mj_p1'><img align='absmiddle' src='$'></p>",
					tphtmlval:"user_name|img_url"	
				}];

//评价条数的记录
function otherEval(count){
	$(".evalcount").html(count);
}

//成功交易的记录数
function otherTrade(count){
	$(".tradeCount").html(count);
}

//列表页面js脚本
  function attrselect(attrString){
    var en_name=$("#en_name").val();
    var cat_name=$("#cat_name").val();
    var s = encodeURIComponent(encodeURIComponent(attrString));
    window.location.href="/mall/goods!list.action?attr=" + s + "&en_name=" + en_name + "&cat_name=" + cat_name;
  }
  
  function brand(attrString){
    var en_name=$("#en_name").val();
    var s = encodeURIComponent(encodeURIComponent(attrString));
    window.location.href="/mall/goods!list.action?gsb=" + s + "&en_name=" + en_name ;
  }
    //type按类型排序，show展示样式
  	function goodsactionbmall(type,show){
        if(type!=''){
        	$("#type").val(type);
        }
        if(show!=''){
            $("#show").val(show);
        }
		document.forms[0].action='/mall/goods!list.action';
		document.forms[0].submit();
	}
	//type按类型排序，show展示样式
	function goodsaction(type,show,user_name){
	    if(type!=''){
	    	$("#serarch_type").val(type);
	    }
	    if(show!=''){
	        $("#search_show").val(show);
	    }
		//document.forms["shop"].submit();
		document.forms[0].action='/shop/'+user_name+'/goodslist.html?serarch_type='+type+'& search_show='+show+'';
		document.forms[0].submit();
}
	
  function pselect(type,brandid){
     var stype=$("#mallsearchType").val();
    if(type=='p'){
	    var selValue=$("#selValue").val();
	    if(selValue==''||selValue=="请输入搜索条件"){
	      if(stype=="shop")
	      {
	        window.location.href="/mall-shoplist.html";
	      }
	       $("#selValue").val("请输入搜索条件");
	    }else{
	      var sel = encodeURIComponent(encodeURIComponent(selValue));
	      if(stype=="shop")
	      {
	       window.location.href="/mall/goods!shopList.action?p="+sel;
	      }
	      else
	      {
	      	window.location.href="/mall/goods!list.action?p="+sel;
	      }
	    }
    }
    if(type=='b'){
	  window.location.href="/mall/goods!list.action?b="+brandid;
    }
  }	
  
  function bselect(){
      var selValue=$("#selValue").val();
      var userName = $("#username").val();
      
	    if(selValue==''||selValue=="请输入搜索条件"){
	      $("#selValue").val("请输入搜索条件");
	    }else{
	      var sel = encodeURIComponent(encodeURIComponent(selValue));
	      window.location.href="/goodslist.action?user_name=" + userName + "&selValue=" + sel;
	    }
  }
  
  $(document).ready(function() {
    var s= $("#brandlight").val();
    $("#"+s).addClass("listselectclass"); 
  });


//点击查看会员价格
function showmemprice(obj){
	var show=$("#memdiv").css("display");
	if(show=='none'){
		$("#memdiv").css("display","block");
		$(obj).find("img").attr("src","/templets/bmall/images/minusdetail.png");
	}else{
		$("#memdiv").css("display","none");
		$(obj).find("img").attr("src","/templets/bmall/images/pulsdetail.png");
	}
}

//商品咨询
function subgoodsask(){
  var item =  $("input[name='comm_type']:checked").val(); 
  if(item==undefined){
     alert("请输入咨询类型");
     return false;
  }
  var ccontent = encodeURIComponent(encodeURIComponent($("#content").val()));
  if(ccontent==""){
     alert("请输入咨询内容");
     return false;
  }
  var goodsid = $("#cart_goods_id").val();
  $.ajax({  	 
            type: "post",    	     
            url: "/portal/goods!subgoodsask.action?ccontent=" + ccontent + "&goodsid=" + goodsid + "&c_type=" +item,    
            datatype:"json",
            async:false,
            success: function(data){
              if(data=='0'){
                alert("成功提交商品咨询");
                window.location.href="/mall-goodsdetail-" + goodsid + ".html";
              }
              if(data=='1'){
                alert("您还没有登录");
              }
              
            }
   });
}

//加载第一级地区
	function shiparea(){
	  	$("#showship").popup({});
	 	 var careaid = $("#careaid").val();
	 	 //第一次加载是采用IP定位 cateid为空
	 	 if(careaid==''){
			  var shipareaid = $("#shipareaid").val();
			  $("#"+shipareaid).addClass("lired");
			  areafoc(shipareaid);
			  var areaid = $("#areaid").val();
			  $("#"+areaid).addClass("lired");
	 	 }else{
		     $("#"+careaid).addClass("lired");
	         areafoc(careaid);
	         var sareaid = $("#sareaid").val();
			 $("#"+sareaid).addClass("lired");
	  	 }
	}
	
	//加载第二级地区
	function areafoc(areaid){
	    $.ajax({
	       type: "post",
	       url: "/portal/goods!getshiparea.action?shiparea=" + areaid,
	       datatype:"json",
	       async:false,
	       success: function(data){ 
		    if(data!=''){
		          $(".twoarea").html(data);
			   }
		    }
	    });
	    //遍历careadiv 中的li 清除li的样式
	    $('#careadiv li').each(function(i){
	          $(this).removeClass("lired");
	    });
       //对选中的地区添加 高亮样式
       $("#"+areaid).addClass("lired");
	}
		
	//选中地区 赋值后关闭层
	function shipfoc(areaid,up_areaid,areaNmae){
	   $("#careaid").val(up_areaid);
	   $("#sareaid").val(areaid);
	   $("#areabotton").html(areaNmae);
	   shipprice(areaid);
	   closearea();
	}
	
	//关闭层
	function closearea(){
	  $(".cover").remove();
	  $("#showship").css("display","none");
	}
	
	//计算运费
	//第一个参数本级id
	function shipprice(id){
		var c_id=$("#c_id").val();
		var t_id=$("#t_id").val();
		var g_id=$("#cart_goods_id").val();
		var n_id=$("#buynumid").val();//获取需要购买数量的值
		var dataUrl="/portal/goods!getshipprice.action?id="+id+"&c_id="+c_id+"&t_id="+t_id+"&g_id="+g_id+"&n_id="+n_id;
		$.ajax({
	        type: "post",
	        url: dataUrl,
	        datatype:"json",
	        async:false,
	        success: function(data){ 
		        data=eval("("+data+")");
		        var strHtml="";
				for(var i=0;i<data.length;i++){
					strHtml+="<span class='smodeprice'>"+data[i].smode_attr+":"+data[i].mode_price+"&nbsp;</span>";
				}
				$("#autoship").html(strHtml);
	        }
	    });
	}
	

	
	
	
	