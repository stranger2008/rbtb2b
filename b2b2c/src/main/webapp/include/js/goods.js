function Map(){
		 this.keys = new Array();
		 this.data = new Array();
		 
		 this.put = function(key,value){
			  if(this.data[key] == null){
			     this.keys.push(value);
			  }
			  this.data[key] = value;
		 };
		 
		 this.get = function(key){
		  	  return this.data[key];
		 };
		 
		 this.remove = function(key){
		     for(var i=key+1;i<this.keys.length+1;i++){          
		     	this.data[i] = null;
		     }
		     this.keys.length=key;
		 };
		 
		 this.isEmpty = function(){
		  return this.keys.length == 0;
		 };
		 
		 this.size = function(){
		  return this.keys.length;
		 };
	}
	
var modeObj = new Map();
var priceObj = new Map();

//验证RMB类型
function checkObjFloat(obj){
	//验证是否为数字正则表达式
	var reg = /^(\d){1,8}(\.)?(\d(\d)?)?$/;
	if(!reg.test($(this).val())){
		jNotify("请正确输入货币类型!");
		return false;
	}else{
		return true;
	}
}

//根据不同的运费类型加载不同的的运费计算方式
function loadByShipType(radioType){
	if(radioType==''){
		radioType= $("input:radio[name='goods.is_ship']:checked").val();
	}
	if(radioType==1 || radioType==2){
		  var sendData="";
		  $.ajax({
	           type: "post",
	           url: "/sendmode!modeList.action?ajaxRequestRandom="+Math.random(),
	           datatype:"json",
	           async:false,
	           success: function(data){ 
	           		data=eval("("+data+")");
	           		if(radioType==1){
	           			sendData=data;
	           		}else if(radioType==2){
		           		for(var i=0;i<data.length;i++){
		           			modeObj.put(data[i].smode_id,data[i].smode_name);
		           		}
	           		}	           		
	           }                 
		  });
	      if(radioType==1){
	     		var tableStr="<table  class='wwtable' cellspacing='1' cellpadding='8'>";
	     		for(var i=0;i<sendData.length;i++){
		      		tableStr+="<tr>";
		      		tableStr+="<td width='110px' align='right'><input type='hidden' class='m_hidden' value='"+sendData[i].smode_id+"'/>"+sendData[i].smode_name+":</td>";
		   			tableStr+="<td><input class='m_price' type='text' onkeyup='checkNum(this);'/></td>";
		     		tableStr+="</tr>";
	     		}	     		
	     		$("#areadata").html(tableStr);
	      }
	      if(radioType==2){
	            var vmode="";
				$.ajax({
			           type: "post",
			           url: "/shiptemplate!getTemplate.action?ajaxRequestRandom="+Math.random(),
			           datatype:"json",
			           async:false,
			           success: function(data){ 
			           data=eval("("+data+")");
			      	    var tableStr="<table  class='wwtable' cellspacing='1' cellpadding='8'>";
			      	      tableStr+="<tr>";
			     		  tableStr+="<td align='center'>点击选择配送模板</td>";
			     		  tableStr+="<td align='center'>模板类型</td>";
			     		  tableStr+="<td align='center'>发货地</td>";
			     		  tableStr+="<td align='center'>按类型</td>";
			     		  tableStr+="<td align='center'>快递类型</td>";
			     		  tableStr+="</tr>";
			     		for(var i=0;i<data.length;i++){
			     		    if(data[i].valuation_mode=='1'){
			     		      vmode="按件数";
			     		    }else if(data[i].valuation_mode=='2'){
			     		      vmode="按重量";
			     		    }else{
			     		      vmode="按体积";
			     		    }
				      		tableStr+="<tr>";
				      		tableStr+="<td><input type='radio' name='ship_id' value='"+data[i].ship_id+"'/></td>"
				      		tableStr+="<td width='110px' align='right'><a  href='"+data[i].ship_id+"'>"+data[i].ship_name+"</a>:</td>";
				      		tableStr+="<td>"+ data[i].start_area + "</td>" + "<td>"+ vmode + "</td>" + "<td>"+ data[i].smode_attr + "</td>";
				     		tableStr+="</tr>";
			     		}	     		
			     		$("#shipdata").html(tableStr);
			     		}
	           });
	      }
	 }
}

//保存价格串
function savesPrice(){
	var priceStr="";
	var radioType= $("input:radio[name='goods.is_ship']:checked").val();
	$(".m_hidden").each(function(i){		
		if($(".areaul").length>0){
			priceStr+=$(this).val()+"|";
			$(".areaul").eq(i).find(".areali").each(function(){
				if($(this).children(".m_price").val()!=''){
					priceStr+=$(this).children(".span_areaname").html()+"="+$(this).children(".m_price").val()+"#";
				}
			});	
			priceStr+=":";	
		}else{
			if($(".m_price").eq(i).val()!=''){
				priceStr+=$(this).val()+"|";
				priceStr+=$(".m_price").eq(i).val()+",";
			}
		}			
	});	
	$("#s_price").val(priceStr);
	colsecover();
	$("#td_ship").html("<a style='cursor:pointer;' onclick='areadset();'>已设置</a>");
}

//保存运送方式
var rType;
function saveship(){
	rType = $("input:radio[name='ship_id']:checked").val();
	if(rType==undefined){
	  $("#error").html("请选择运送方式");
	}else{
		colsecover();
		$("#td_ship").html("<a style='cursor:pointer;' onclick='viewship();'>已设置</a>");
	}
	$("#s_price").val(rType);
}
function viewship(){
 var shiptype = $("#s_price").val();
 $("input[name=ship_id][value="+ shiptype +"]").attr("checked",true);
 $("#showshiparea").popup({});
}
//关闭弹出层
function colsecover(){
	$(".cover").remove();
	$("#showsetarea").css("display","none");
	$("#showshiparea").css("display","none");
}

//显示已设置区域
function areadset(){
	var s_price_str=$("#s_price").val();	
	var radioType= $("input:radio[name='goods.is_ship']:checked").val();
	loadByShipType(radioType);
	if(radioType==2){
	viewship();
		//是否将值清空
		//if(s_price_str.indexOf("#")==-1){
		//	s_price_str="";
		//}
		//var s_prices=s_price_str.split(":");
		//	for(var i=0;i<s_prices.length;i++){
		//		if(s_prices[i]!=''){
		//			var s_len=s_prices[i].indexOf("|");
		//			var s_mode=s_prices[i].substring(0,s_len);
		//			if(s_len>-1){	
		//				var price_str=s_prices[i].substring((s_len+1),s_prices[i].length);
		//				var price_s=price_str.split("#");
		//				for(var j=0;j<price_s.length;j++){
		//					if(price_s[j]!=""){
		//						var mode_len=price_s[j].indexOf("=");
		//						if(mode_len>-1){
		//							var mode=price_s[j].substring(0,mode_len);
		//							var price=price_s[j].substring((mode_len+1),price_s[j].length);
		//							priceObj.put(s_mode+"|"+mode,price);
		//					}				
		//				}
		//			}
		//		}
		//	}
		
		//}		
		
		
		
		//回选价格
		$(".areali").each(function(){
			var s_mode=$(this).parent("ul").parent("td").parent("tr").find(".m_hidden").val();
			var mode = $(this).find(".span_areaname").html();
			var price = priceObj.get(s_mode+"|"+mode);
			if(price!=''){
				$(this).find(".m_price").val(price);
			}
		});
	}
	if(radioType==1){
		if(s_price_str.indexOf("#")>-1){
			s_price_str="";
		}
		var s_prices=s_price_str.split(",");
		for(var i=0;i<s_prices.length;i++){
			if(s_prices[i]!=''){
				var s_len=s_prices[i].indexOf("|");
				var s_mode=s_prices[i].substring(0,s_len);
				var s_price=s_prices[i].substring((s_len+1),s_prices[i].length);
				priceObj.put(s_mode,s_price);
			}
		}	
		//半自动价格的回选
		$(".m_hidden").each(function(){
			var s_mode=$(this).val();
			var price = priceObj.get(s_mode);
			if(price!=''){
				$(this).parent("td").parent("tr").find(".m_price").val(price);
			}
		});
	}
	$("#showsetarea").popup({});
}






















