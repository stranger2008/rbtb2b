
// ************************************收货地址操作脚本开始**********************************************
//初始化加载收货人地址
function loadaddr()
{
  var sname=$("#sname").html();
  //如果初始化地区加载的时候，若没有收货人信息，默认显示输入收获人信息，否则，现在已经存在收货人信息
  if(sname!=null&&sname!="")
  {
  //隐藏送货地址修改
	$("#addradd").hide();
	$("#addrmr").show();
	//显示默认送货地址
	$("#playtype").hide(); 
	$("#addreshidden").val("1");
   $("#cyadrid").show();
  }
  else
  {
	 //隐藏送货地址修改
	$("#addradd").show();
	$("#addrmr").hide();
	//显示默认送货地址
	$("#playtype").show(); 
	$("#cyadrid").hide();
	$("#addreshidden").val("0");
  }
}
 // 选择地址
  function addrselect(id){
   //所属地区的回选
   $("#arealist").html("");
   $.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!selectaddr.action?id="+id,  
          datatype:"json",
          success: function(data){  
	          var buyeraddr=data.split("|"); 
	          $("#addrid").val(buyeraddr[0]);
	          $("#name").val(buyeraddr[1]);
	          //获取area_attr地区串
	          $("#hidden_area_value").val(buyeraddr[2]);
	          $("#address").val(buyeraddr[3]);
	          $("#cell_phone").val(buyeraddr[4]);
	          $("#phone").val(buyeraddr[5]);
	          $("#post_code").val(buyeraddr[6]);
	           if(buyeraddr[2]!=null&&buyeraddr[2]!="")
	           {
	           		area_select(buyeraddr[2]);
	           }
	           else
	           {
		           $("#hidden_area_value").val("1111111111,");
			       $("#addrid").val("1111111111");
			        //所属地区的回选
			       area_select("$1111111111,");
	           }
           }
      });  	
  }
  // 显示 添加地址 修改地址  
  function buyeraddr(type){
      
       if(type=='update'){
	         //隐藏默认送货地址
		     $("#addrmr").hide(); 
		     //显示选择送货地址和修改地址
		     $("#addradd").show();
		     var addrid = $("#addrid").val();
		     if(addrid!=null&&addrid!=""&& addrid!="1111111111"){
		         addrselect(addrid);
		     }
		     else
		     {
		         //所属地区的回选
		         $("#arealist").html("");
		         area_select("1111111111");		        
		     }
       }
       //插入串
       var insertString="";
       var area_value="";
       //获取选中的地区串
       $(".select").each(function(){
              var item = $(this).find("option:selected").val();             
       	      area_value=area_value + item + ",";
       });
       if(type=='add'){
       	   var flag=false;
           var name = $("#name").val();
           var address = $("#address").val();
           var cell_phone=$("#cell_phone").val();
           var phone=$("#phone").val();
           var post_code=$("#post_code").val();
           if(name==''){
               checktextnullHtml("name","errorname","*请输入收货人姓名","text");
               flag=true;
           }else if(area_value.indexOf("0")>-1){
               $("#errorarealist").html("*请选择完整的省份地区");
               flag=true;
           }else if(address==''){
               checktextnullHtml("address","erroraddress","*请输入详细地址","text");
               flag=true;
           }else if(cell_phone==''&&phone==''){
               $("#errorphone").html("*请输入手机号码或者固定电话");
               flag=true;
           }else if(post_code==''){
               checktextnullHtml("post_code","errorpost","*请输入邮编号码","num");
               flag=true;
           }else{
       	       insertString=name + "|" + area_value + "|" + address + "|" + cell_phone + "|" + phone + "|" + post_code;
       	       insertString = encodeURIComponent(encodeURIComponent(insertString));
       	   }
       }
       if(flag){
       	  return;
       }
       //删除地址串
       var addrid="";
       if(type.indexOf("address")>-1){
         type=type.replace("address","");
         addrid=type;
       }

       $.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!updateaddr.action?instr="+insertString+"&addr_id="+addrid,
          datatype:"json",
          success: function(data){  
                 var state = data.slice(0,1);
                 var str=data.slice(2);
                 $("#selectaddr").html(str);
                 if(str!=''){
                  $("#cyadrid").show();
                 }
                 if(state=='1' && type=='add'){
                   alert("添加的收货地址超过限定个数");
                 }
                 if(state=='2' && type=='add'){
                   alert("该收货地址已存在");
                 }
          }
      });  

      var oneid=$("#addrid").val();
      if(oneid!=null&&oneid!="")
      {
     	 $("#"+oneid).attr("checked","checked");
      }
      
      
      //0：表示未保存收获地址，1：已经保存收获地址
      $("#addreshidden").val("0");
      $("#alladdressTip").css("display","none");
  }
  
  function update(type){
 		var area_value="";
	    //获取选中的地区串
	    $(".select").each(function(){
	       var item = $(this).find("option:selected").val();
	       area_value=area_value + item + ",";
	     });
	     if(area_value.indexOf(",")>-1){
	    	area_value=area_value.substring(0,(area_value.length-1));
	     }
	     //收获人名称
      	if($("#name").val()==null||$("#name").val()=="")
	     {
	       return checktextnullHtml("name","errorname","*请输入收货人姓名","text");
	     }
	     //地区选择
	     if(area_value.indexOf("0")>-1)
	     {
             $("#errorarealist").html("*请输入完整的省份地区");
             return false;
         }
	     //收获人地址
	     if($("#address").val()==null||$("#address").val()=="")
	     {
	       return checktextnullHtml("address","erroraddress","*请输入详细地址","text");
	     }
	     //收货人手机或者固定电话
	     if($("#cell_phone").val()==""&&$("#phone").val()=="")
	     {
	    	$("#errorphone").html("*请输入手机号码或者固定电话");
	    	return false;
	     }
	     //收获人邮编
	     if($("#post_code").val()==null||$("#post_code").val()=="")
	     {
	       return checktextnullHtml("post_code","errorpost","*请输入邮编号码","num");
	     }
	  	  var area_value="1111111111,";
		  if(type=='addr'){
		  }
		  if(type=='keep'){
		     //显示默认送货地址
		     $("#addrmr").show(); 
		     //隐藏选择送货地址和修改地址
		     $("#addradd").hide();
		     //
		     $("#sname").html($("#name").val());
		     $("#sarea").html($("select[name=area_attr]").find("option:selected").text());
		     $("#sdirarea").html($("#address").val());
		     $("#scell_phone").html($("#cell_phone").val());
		     $("#sphone").html($("#phone").val());
		     $("#spost").html($("#post_code").val());
		     
		     $(".select").each(function(){
	              var item = $(this).find("option:selected").val();
	       	      area_value=area_value + item + ",";
	         });
		     area_value=area_value.substring(0,area_value.length-1);
		     //将获得的值，放入隐藏域中
		     $("#sname1").val($("#name").val());
		     $("#sdirarea1").val($("#address").val());
		     $("#scell_phone1").val($("#cell_phone").val());
		     $("#sphone1").val($("#phone").val());
		     $("#spost1").val($("#post_code").val());
		     $("#hidden_area_value").val(area_value);
		     
		  }
		  if(type=='off'){
		     //显示默认送货地址
		     $("#addrmr").show(); 
		     //隐藏选择送货地址和修改地址
		     $("#addradd").hide();
		  }
	   //用于保存是否已经选择地区 0：表示未保存收获地址，1：已经保存收获地址
	   $("#addreshidden").val("1");
	   //根据不同的配送方式计算价格
	   selModePrice();
  }
//**********************************收货地址操作脚本结束****************************************



//************************************支付方式操作脚本开始**********************************************
//初始化页面加载支付以及配送方式
function loadpay()
{
  var semd_id="";
   //获取配送方式
   $("#tabelSend_mode :checked").each(function () {
	  semd_id=$(this).attr("id");
   });
  //计算运费
  addyunfare(semd_id);
  
  updateplay();
  
  getbiaojia();
  
}
//获取商品的保价状态
function getbiaojia()
{
   var baojiatext="";
   $("#tabel_baojia :checked").each(function () {
	 baojiatext=$(this).attr("text");
   });
  $("#getbaojiaid").html(baojiatext);
}
function play(){
         //显示默认送货地址
	     $("#updateplay").show(); 
	     //隐藏选择送货地址和修改地址
	     $("#playtype").hide();
	     $("#playtypehidden").val("0");
	     $("#allpayandfareTip").css("display","none");
}
function updateplay(){
  		//获取选中的支付方式
  		var paytypeflage="0";
  		var send_modetypeflage="0";
  		var rspayvalue="";
  		var rspayid="";
  		var rssend_modetype="";
  		var rssend_modeid="";
  		var rssend_modevale="";
  		var is_insured="0";
  		$("#tablepeytype :checked").each(function () {
		  rsvalue=$(this).attr("text");
		  rspayid=$(this).attr("id");
		  paytypeflage="1";
	    });

	    //判断是否选择支付方式
	    if(paytypeflage=="0")
	    {
	      return checktext("paytipid","*请选择支付方式","0");
	    }

		$("#paytypeid").html(rsvalue);
	    $("#sendtypeid").html(rssend_modetype);
	    $("#faretypeid").html(rssend_modevale);
	    $("#hidden_paytype").val(rspayid);
	    $("#hidden_Send_mode").val(rssend_modeid);
	    //显示是否支持保价选择
	    if(is_insured=="0")
	    {
		  $("#_hidden_if_insured0").attr("checked","");
		  $("#_hidden_if_insured1").attr("checked","ture");
		  $("#allbaojiafare").html("0.0");
		  $("#if_insured_id").css("display","");
		  $("#baojiashow").css("display","");
	    }
	    else
	    {
	      $("#if_insured_id").css("display","none");
	      $("#allbaojiafare").html("0.0");
     	  $("#baojiashow").css("display","none");
	    }
	     AcountMoney();
	    //显示默认送货地址
	    $("#updateplay").hide(); 
	    //隐藏选择送货地址和修改地址
	    $("#playtype").show();
	    $("#playtypehidden").val("1");
}
//************************************支付方式操作脚本结束**********************************************


//***********************************运费统计开始*********************************************************
function addyunfare(sendmod_id)
{
  var sendareaid=$("#hidden_area_value").val();
  var vargoodsid="";
  if($.cookie("cart_cookieNum") != null){
	  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	  for(var i = 1; i<= cart_cookieNum; i++)
	  {
		  var goods_id=$.cookie("goods_id" + i);
		  if(goods_id!=null&&goods_id!="")
		  {
		  		vargoodsid+=goods_id+",";	  
	  	  }
	  }
   }
   if(vargoodsid!=null&&vargoodsid!="")
   {
     vargoodsid=vargoodsid.substring(0,vargoodsid.length-1);
   }
   //统计运费
   //$.ajax({  	 
   //       type: "post",    	     
   //      url: "/mall/goods!acountSendModeFare.action?smodeid="+sendmod_id+"&sarea="+sendareaid+"&sgoodid="+vargoodsid,
   //       datatype:"json",
   //       success: function(data){  
   //          $("#fare"+sendmod_id).html(data);
   //          $("#allyunfares").html(data);
   //          //AcountMoney();
   //       }
   //   });  
   //checktext('sendtipid','','1');
}
//**********************************运费统计结束******************************************

//***********************************发票信息操作开始****************************************************
//初始化加载发票信息
function loadfapiao()
{

  var var_fp_type=$("#hidden_fp_type").val();
  if(var_fp_type==null||var_fp_type==""||var_fp_type=="2")
  {
	  $("#fapiaoupdate").show(); 
	  $("#fapiaoclose").hide();
	  $("#nofapiao").show();
	  $("#yesfapiao").hide();
	  $("#hidden_fp_type").val("2");
	  $("#fapiaohidden").val("1");
  }
  else
  { 
   	$("#fapiaoupdate").show(); 
	$("#fapiaoclose").hide();
    $("#nofapiao").hide();
	$("#yesfapiao").show();
	$("#fapiaohidden").val("0");
  }
}
function updatefapiao()
{
 	//显示默认发票信息
   $("#fapiaoclose").show(); 
   //隐藏选择发票信息和修改发票信息
   $("#fapiaoupdate").hide();
   //表示发票没有保存
   $("#fapiaohidden").val("0");
   $("#allfapiaoTip").css("display","none");
}
function closefapiao()
{
    var fpname="";
    var fpnametypeflage="0";//0：表示没有选择发票类型，1:已经选择了发票类型
    var fptitle="";
    var fptitletype="0";//0:表示没有选择发票抬头，1:选择了发票类型
    var fpcontent="";
    var fpcontenttype="0";//0：没有选择发票内容信息，1：选择了发票内容信息
    var fpisnull="0";
    //获取选择的发票类型
	$("#fapiaotype :checked").each(function () {
		  fapiaoname=$(this).attr("text");
		  fpnametypeflage="1";
	});
	//表示当前选中的类型为：不开发票
	if(fapiaoname!="2")
	{
	   //获取发票抬头类型:0:普通发票，1：增值发票
	   $("#ftitleid :checked").each(function () {
			  fptitle=$(this).attr("text");
			  fptitletype="1";
		});
		//获取发票内容
		$("#fcontentid :checked").each(function () {
			  fpcontent=$(this).attr("text");
			  fpcontenttype="1";
		});
	    
	     if(fpnametypeflage=="0")
	     {
	      return checktext("fapiaotip","*请选择发票类型","0");
	     }
	     else if($("#myuniteid").val()==""&&fptitle=="单位")
	     {
	        $("#myuniteTip").css("display","");
	        return ;
	     }
		 if(fptitletype=="0"&&fapiaoname!="1")
	     {
	       return checktext("fapiaotaitoutip","*请选择发票抬头","0");
	     }
	     if(fpcontenttype=="0"&&fapiaoname!="1")
	     {
	      return checktext("fapiaonrtip","*请选择发票内容","0");
	     }
	   
	    if(fapiaoname=="1")
	    {
	     	//验证单位名称
			 fpisnull=checktextnull('zunitnameid','zunitnameTip');
		    //验证纳税人标识
			 fpisnull=checktextnull('nasuirenhaoid','nasuirenhaoTip');
			//验证注册地址
			 fpisnull=checktextnull('registeraddrid','registeraddrTip');
			//验证注册电话
			fpisnull=checktextnullAndNumber('registertelid','registertelTip')
			//验证开户银行
			fpisnull=checktextnull('openbankid','openbankTip')
			//验证银行账户
		     fpisnull=checktextnullAndNumber('bankacountid','bankacountTip')
			if(fpisnull=="1")
			{
			  return ;
			}
	    	fptitle="单位";
	    	$("#zcontentid :checked").each(function () {
			  fpcontent=$(this).attr("text");
			});
			
	    }
	    var varfpname="";
	    if(fapiaoname=="0")
	    {
	    	varfpname="普通发票";
	    }
	    else if(fapiaoname=="1")
	    {
	      varfpname="增值发票";
	    }
	    $("#ftypevalue").html(varfpname);
	    $("#ftitlevalue").html(fptitle);
	    $("#fcontentvalue").html(fpcontent);
	    //将获得发票信息放入隐藏域中
	    
	    $("#hidden_fp_type").val(fapiaoname);
	    $("#hidden_fp_title").val(fptitle);
	    $("#hidden_fp_content").val(fpcontent);
	    
	    //控制显示是否需要开发票
	    $("#yesfapiao").css("display","");
	    $("#nofapiao").css("display","none");
    }
    else
    {
     //控制显示是否需要开发票
	    $("#yesfapiao").css("display","none");
	    $("#nofapiao").css("display","");
	    $("#hidden_fp_type").val("2");
    }
    
	 //显示默认发票信息
	 $("#fapiaoclose").hide(); 
	//隐藏选择发票信息和修改发票信息
	$("#fapiaoupdate").show();
	$("#fapiaohidden").val("1");
}

//选择发票类型
function changeInvoiceType(type)
{
    //type:1:普通发票,2:增值发票
   if(type=="1")
   {
     $("#tipinvoice").css("display","none");
     $("#tableAll").css("display","none");
     $("#tableOne").css("display","");
   }
   else if(type=="2")
   {
     $("#tableAll").css("display","");
     $("#tipinvoice").css("display","");
     $("#tableOne").css("display","none");
   } 
   else if(type=="3")
   {
     closefapiao();
   }
   //选中发票类型的时候，隐藏提示信息
   checktext('fapiaotip','','1');
}
//选择发票抬头类型
function changgeTitle(type)
{
   //type:1:个人,2:单位
   if(type=="1")
   {
     $("#dwid1").css("display","none");
     $("#dwid2").css("display","none");

   }
   else
   {
      $("#dwid1").css("display","");
     $("#dwid2").css("display","");
   } 
   checktext("fapiaotaitoutip","","0");
}

//***********************************发票信息操作结束***************************************************

//***********************************商品信息操作结束***************************************************
 //读取COOKIES的值到购物清单里面
function mallReadGoodsCookies()
{
 var vargoodshtml="";
 var varcust_id="";
 var vargoods_id="";
 if($.cookie("cart_cookieNum") != null){
 
     //获取当前购买的商品的商店
     varcust_id=getcustid();
     var strarray=varcust_id.split(",");
     for(var k=0;k<strarray.length;k++)
     {
       vargoodshtml+=AddBuyList(strarray[k]);
     }
   }
  $("#goostable").append(vargoodshtml);
  //给卖家留言与配送方式
  
  vargoods_id=getGoodsidattr();
  //将cust_id放入隐藏域中
  $("#hidden_cust_id_value").val(varcust_id);  
  $("#hidden_goods_id_value").val(vargoods_id); 
  AcountMoney();
  
}
//获取所选商品的ID
function getGoodsidattr()
{
  var goodsid="";
  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
  for(var i = 1; i<= cart_cookieNum; i++)
  {
	  var goods_id=$.cookie("goods_id" + i);
	  if(goods_id!=null&&goods_id!="")
	  {
	  	goodsid+=goods_id+",";
  	  }
  }
  if(goodsid!=null)
  {
   goodsid=goodsid.substring(0,goodsid.length-1);
  }
   return goodsid;
}
//添加购物清单
function AddBuyList(cust_id)
{
	  var vargoodshtml="";
	  var goodscust_name="";
	  var goodshtml="";
	  var goods_id_str="";
	  var goods_num_str="";
	  var shopprice=0;//获取店铺订单商品中的总价格
	  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	  //获取商品的id串
	  for(var i = 1; i<= cart_cookieNum; i++)
	  {
		  var goods_id=$.cookie("goods_id" + i);
		  var goods_cust_id=$.cookie("goods_cust_id" + i);
		  if(goods_id!=null&&goods_id!=""&&goods_cust_id==cust_id)
		  {
		  	  goods_id_str+=goods_id+",";
		  	  //读取商品名称
		      var goods_name="";
		      if($.cookie("goods_name" + i)!=null)
		      {
			    goods_name=$.cookie("goods_name" + i);
			  }
			  //读取购物数量
			  var goods_Num="1";
			  if($.cookie("goods_Num" + i)!=null)
			  {
			   goods_Num=$.cookie("goods_Num" + i);
			  }
			  goods_num_str+=goods_Num+",";
			   //读取赠送积分
			  var goods_give_inter="0";
			  if($.cookie("goods_give_inter" + i)!=null)
			  {
			   goods_give_inter=$.cookie("goods_give_inter" + i)*goods_Num;
			  }
			  //读取商品价格
			  var goods_price="0.00";
			  if($.cookie("goods_price" + i)!=null)
			  {
			   goods_price=$.cookie("goods_price" + i);
			  }
			  //读书已经选中的商品属性规格
		      var goods_attr_name="---";
		      if($.cookie("goods_attr_name" + i)!=null)
		      {
		        goods_attr_name=$.cookie("goods_attr_name" + i)
		      }
		      //读取商品图片
			  var goods_img_path="/include/images/nopic.jpg";
			  if($.cookie("goods_img_path" + i)!=null)
			  {
				  goods_img_path=$.cookie("goods_img_path" + i);
				  goods_img_path=optImgUrl(goods_img_path);
			  }
			  //计算订单商品总价
			  var countmoney=(goods_Num*goods_price).toFixed(2);
			  shopprice+=parseFloat(countmoney);
			  
			  var goods_cust_name=$.cookie("goods_cust_name" + i);
			  goodscust_name=goods_cust_name;
			  
			  goodshtml+="<tr>"
			  +"<td width='30%' class='goodsname'><a href='/mall-goodsdetail-"+goods_id+".html' target='_blank'>"+goods_name+"</a></td>"
			   +" <td width='10%' >"+goods_attr_name+"</td>"
			  +" <td width='10%' class='red'>￥"+goods_price+"</td>"
			  +"<td width='10%'> ￥0.00</td>"
			  +" <td width='10%' >"+goods_give_inter+" 分</td>"
			  +"<td width='10%'>现货</td>"
			  +" <td width='10%'>"+goods_Num+"</td>"
			  +" <td width='10%' class='red'>￥<span class='single_prc'>"+countmoney+"</span></td>"
			  +"</tr>";
			  
	  	  }
	  }
	  var hidden_cfg_openmall=$("#hidden_cfg_openmall").val();
	  vargoodshtml="<div class='singlediv'><table cellpadding='0' cellspacing='0' class='merchcont' width='100%'>";
	  if(hidden_cfg_openmall=="0")
	  {
	  		vargoodshtml=vargoodshtml+"<tr><th colspan='5'>店铺：<span>"+goodscust_name+"</span>卖家：<span>"+goodscust_name+"</span></th></tr>"
	  }
	  vargoodshtml=vargoodshtml+goodshtml+"</table>";
	  //卖家留言与配送方式html代码
	  var selHtml=getSendModeSel(goods_id_str);	  
	  vargoodshtml+="<div class='note_send'><table><tr>";
	  vargoodshtml+="<td valign='top' width='10%' rowspan='2'><span>给卖家留言:</span></td>";
	  vargoodshtml+="<td valign='top' rowspan='2'><textarea class='ta_txt'></textarea></td>";
	  vargoodshtml+="<td width='200' align='center'>配送方式:</td>";
	  vargoodshtml+="<td><input class='c_goods_id' type='hidden' value='"+goods_id_str+"'/><select class='cal_price' onchange='selModePrice();'>"
	  vargoodshtml+=selHtml+"</select><input class='c_goods_num' type='hidden' value='"+goods_num_str+"'/></td>";
	  vargoodshtml+="<td align='right' width='222'><input class='c_cust_id' type='hidden' value='"+cust_id+"'/><font class='modeprice'>0.0</font></td></tr>";
	  var varclass="";
	  if(selHtml.indexOf("免运费")>-1){
	  	 varclass="style='display:none'";
	  }
	  vargoodshtml+="<tr "+varclass+"><td colspan='5'>";
 	  vargoodshtml+="<span class='ins_span'><input type='checkbox' class='cb_insured' onclick='modeinsured(this);'/>";
 	  vargoodshtml+="<label>是否需要保价,当商品在运输过程中损坏或丢失时，可以赔偿</label><font class='ins_price'>0.00</font>";
 	  vargoodshtml+="</span><br/><span class='pricemsg'></span>";
 	  vargoodshtml+="</td></tr>";
	  vargoodshtml+="<tr><td colspan='5' valign='middle' height='40'><span class='shoptotal'><span class='shopdec'>店铺合计(含运费)：</span><font class='stprice'>"+shopprice+"</font></span></td></tr>";
	  vargoodshtml+="</table></div></div>";
	  return vargoodshtml;
	  
}


//计算出不同的配送的方式对应的配送的保价费用
function modeinsured(obj){
	// 初始化
	var $pricemsg=$(obj).closest(".singlediv").find(".pricemsg");
	var $pricehtml=$(obj).parent("span").find(".ins_price");
	var $stprice=$(obj).closest(".singlediv").find(".stprice");
	// 去除样式
	$pricehtml.removeClass("redspan");
	$pricemsg.html("");
	$pricehtml.html("0.00");
	var jprice="0.00";
	if(obj.checked==true){
		// 获取商品的价格
		var goodsprice=0;
	    $(obj).closest(".singlediv").find(".single_prc").each(function(i){
	    	goodsprice+=parseFloat($(this).html());
	    });
	    // 获取当前配送方式的ID
	    var sid=$(obj).closest(".singlediv").find(".cal_price").val();
	    if(sid!=0){
		    $.ajax({  	 
		          type: "post",
		          url: "/mall/goods!modeinsured.action?gprice="+goodsprice+"&sid="+sid,
		          datatype:"json",
		          async:false,
		          success: function(data){
		          	  data=eval("("+data+")");
	          	 	  if(data.jtag!=0){
		          	 	  $pricemsg.html(data.jprice);
					  }else{
					  	  jprice=parseFloat(data.jprice);
					  	  $pricehtml.html(jprice.toFixed(2));
					  	  // 增加样式
					  	  $pricehtml.addClass("redspan");
					  }
		          }
		    }); 
	    }
	    $(obj).closest(".singlediv").find(".shopdec").html("店铺合计(含运费,保价费)：");
	}else{
	    jprice=parseFloat(jprice);
		$pricehtml.html(jprice.toFixed(2));
		$(obj).closest(".singlediv").find(".shopdec").html("店铺合计(含运费)：");
	}
	// 保价费用加上(运费与商品总价格)
	var stprice=0.00;
	// 计算商品的总价格
	var gtprice=0;
	$(obj).closest(".singlediv").find(".single_prc").each(function(){
		gtprice=gtprice+parseFloat($(this).html());
	});
	//计算该订单的运费
	var mtprice = $(obj).closest(".singlediv").find(".modeprice").html();
	//计算单个订单总价
	stprice=parseFloat(gtprice)+parseFloat(mtprice)+parseFloat(jprice);
	stprice=stprice.toFixed(2);
	$stprice.html(stprice);
	//计算总价
	AcountMoney();
}


//计算出选择不同配送的价格
function selModePrice(){	
	$(".cal_price").each(function(i){
		var cal_val=$(this).val();//获取配送方式ID
		var $price=$(this).parent("td").parent("tr").find(".modeprice");
		var cust_id=$(this).parent("td").parent("tr").find(".c_cust_id").val();
		var $pricehtml=$(this).closest(".singlediv").find(".ins_price");
		var cprice=0.00;
		if(cal_val==0){
			$price.html(cprice.toFixed(2));
			//运费加上商品总价格
		    var goodstotalprice=0;
		    $(this).closest(".singlediv").find(".single_prc").each(function(i){
		    	goodstotalprice+=parseFloat($(this).html());
		    });
		    var totalprice=goodstotalprice+parseFloat(cprice);
		}else{
			var area_id=$("#hidden_area_value").val();
			if(area_id.lastIndexOf(",")>-1){
				var llen=area_id.lastIndexOf(",");
				area_id=area_id.substring(0,llen);
				var len=area_id.lastIndexOf(",");
				area_id=area_id.substring(len+1,area_id.length);
			}
			var goods_id_str=$(this).parent("td").find(".c_goods_id").val();//商品ID串
			var goods_id_num=$(this).parent("td").find(".c_goods_num").val();//商品数量串
			//ajax请求返回返回运费对应的价格
			$.ajax({  	 
		          type: "post",    	     
		          url: "/mall/goods!priceByGoodsid.action?gds="+goods_id_str+"&nid="+goods_id_num+"&aid="+area_id+"&sid="+cal_val,
		          datatype:"json",
		          async:false,
		          success: function(data){  
		          	 if(data!=""){
		          	 	$price.html(data);
		          	 	cprice=data;
		          	 }
		          }
		    }); 
		    //运费加上商品总价格再加个保价的费用...
		    var goodstotalprice=0;
		    $(this).closest(".singlediv").find(".single_prc").each(function(i){
		    	goodstotalprice+=parseFloat($(this).html());
		    });
		 }
		 //商品保价的价格
		 var jprice=$(".ins_price").eq(i).html();
		 //配送方式与商品总价格
	     var totalprice=parseFloat(goodstotalprice)+parseFloat(cprice);
	     totalprice=totalprice.toFixed(2);
	     //单个订单的总价格
		 $(this).closest(".note_send").find(".stprice").html(totalprice);
	});	
	
	//计算出保价费用
	$(".cb_insured").each(function(){
		modeinsured(this);
	});
}

//统计商品总金额
function AcountMoney()
{
	if($.cookie("cart_countNum")!=null&&$.cookie("cart_countNum")!="")
	{
	  	 // 计算所有订单总的商品费用
	  	 var allcount=0;
	     $("#goostable").find(".single_prc").each(function(){
	     		allcount+=parseFloat($(this).html());
	     });
	     $("#allcountmoney").html("  "+allcount.toFixed(2)+" ");
	     // 计算所有订单总运费
		 var modeTotalPrice=0;
		 $(".modeprice").each(function(){
			 var modeprice=$(this).html();
			 modeTotalPrice+=parseFloat(modeprice);
		 });
		 $("#allyunfares").html(modeTotalPrice);
		 // 计算所有订单总的保价费用
		 var insureprice=0.0;
		 $("#goostable").find(".cb_insured").each(function(i){
			 insureprice+=parseFloat($(".ins_price").eq(i).html());
		 });
		 $("#allbaojiafare").html(insureprice);
		 // 所有订单的商品总价格，配送总费用，保价总费用  	 
	   	 $("#allmoney").html("  "+(allcount+modeTotalPrice+insureprice).toFixed(2)+"  ");
	}
}


//获取订单拥有的配送方式
function getSendModeSel(gds){
	var resultDataHtml="";
	$.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!AjaxGetSendModeSel.action?gds="+gds,
          datatype:"json",
          async:false,
          success: function(data){  
          	 if(data=="0"||data.length==0){
   	             resultDataHtml+="<option value='0'>免运费</option>";  	 
          	 }else{
          	 	 data=eval("("+data+")");
	             for(var i=0;i<data.length;i++){
	             	resultDataHtml+="<option value='"+data[i].smode_id+"'>"+data[i].smode_name+"</option>";
	             } 
          	 }
          }
    }); 
    return resultDataHtml; 
}


//获取商品的店铺ID
function getcustid()
{ 
  var strcust="";
  var retcust_id="";
   if($.cookie("cart_cookieNum") != null){
	  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	  for(var i = 1; i<= cart_cookieNum; i++)
	  {
		  var cust_id=$.cookie("goods_cust_id" + i);
		  if(cust_id!=null&&cust_id!="")
		  {
		    strcust+=cust_id+","
		  }
	  }
	  if(strcust!="")
	  {
	  	strcust=strcust.substring(0,strcust.length-1);
	  }
	  //数据去掉重复字段
	  var array=strcust.split(",");  
		for(var i=0;i<array.length;i++){  
	    for(var j=i+1;j<array.length;j++){  
	       if(array[j]===array[i]) {  
	           array.splice(j,1);  
	           j--;  
	         }  
	    	}  
		}
		for(var i=0;i<array.length;i++)
		{
		  retcust_id+=array[i]+",";
		}  
		if(retcust_id!="")
		{
		 retcust_id=retcust_id.substring(0,retcust_id.length-1);
		}
		
   }
   return retcust_id;
}



//处理图片路径  
function optImgUrl(imgurl)
{
   var returl="";
   if(imgurl.indexOf(",")!=-1)
   {
     var numindex=imgurl.indexOf(",");
      returl=imgurl.substring(0,numindex);
   }
   else
   {
     returl=imgurl;
   }
   return returl;
}
//***********************************商品信息操作结束***************************************************

//***********************************提交订单的操作开始***************************************************
function submitOrderForm(){
	 //备注
  	 $(".ta_txt").each(function(){
  	     //获取cust_id
  	     var cust_id = $(this).closest(".note_send").find(".c_cust_id").val();
  	     //保存备注
  	 	 var comment=$(this).val();  	 	
  	 	 $.cookie("order_comment"+cust_id,comment);
  	 	 //保存配送方式ID
  	 	 var smode_type=$(this).val();	 
  	 	 $.cookie("smode_type"+cust_id,smode_type);
  	 	 //保存配送方式的相应费用
  	 	 var mode_price=$(this).closest(".singlediv").find(".modeprice").html();
  	 	 $.cookie("gd_smode_id" + cust_id,mode_price);
  	 	 //保存保价费用
  	 	 var insure_price=$(this).closest(".singlediv").find(".ins_price").html();
  	 	 $.cookie("gd_insure_id" + cust_id,insure_price);
  	 	 //保存每个订单的总价格
  	 	 var stprice=$(this).closest(".singlediv").find(".stprice").html();
  	 	 stprice=parseFloat(stprice);
  	 	 stprice=stprice.toFixed(2);
  	 	 $.cookie("gd_cust_id" + cust_id,stprice);  
  	 });
	 var flage="0";
	 //提示收获人地址信息没有保存
	 if($("#addreshidden").val()=="0")
	 {
	    $("#alladdressTip").css("display","");
	    flage="1";
	 }
	 //提示支付方式以及配送方式没有保存
	 if($("#playtypehidden").val()=="0")
	 {
	   $("#allpayandfareTip").css("display","");
	    flage="1";
	 }
	 //提示发票信息没有保存
	 if($("#fapiaohidden").val()=="0")
	 {
	  	$("#allfapiaoTip").css("display","");
	    flage="1";
	 }
	 if(flage=="1")
	 {
	  return false;
	 }
	 if (confirm("确定提交订单？")) {
	      document.forms[0].action="/mall/goods!addGoodsOrder.action";
	      document.forms[0].submit();
	 }
 
}
//***********************************提交订单的操作结束***************************************************

 //清空购物车所以商品
 function mallClearAllCookies(){
	  if($.cookie("cart_cookieNum") != null&&$.cookie("cart_cookieNum") != "" ){
		  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
		  var cart_countNum=parseInt($.cookie("cart_countNum"));
		  for(var i = 1; i<= cart_cookieNum; i++)
		  {
		     if( $.cookie("goods_id"+i)!=null)
		     {
				 mallClearItem(i);
			 }
		  }
		  //当前购物车最大数
		  $.cookie("cart_cookieNum", "",{ expires: 1, path: '/' });
		  //购物车实际个数
		  $.cookie("cart_countNum", "",{ expires: 1, path: '/' });
	  }
 }
 //清除购物项
 function mallClearItem(num)
 {
	  $.cookie("goods_id" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_name" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_give_inter" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_price" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_img_path" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_Num" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_now_stock" + num, "",{ expires: 1, path: '/' });
 }

//**************************************工具方法***********************************
 //验证输入的内容是否为空值，显示提示内容
 //id：目标ID,tipId:提示内容ID,tipText:提示内容,textType:输入文本内容(text:文本,num:数字)
  function  checktextnullHtml(id,tipId,tipText,textType)
  {  
     if($("#"+id).val()==""||$("#"+id).val()==null)
	 {
	    $("#"+tipId).html(tipText);
	    return false;
	 }
	 else
	 {
	    if(textType=="num"&&checkNum_order(id,tipId)==true)
	    {
	     return true;
	    }
	    else
	    {
	      $("#"+tipId).html("");
		  return false;
	    }
	 }
  }
  //验证发票输入的文本内容是否为空值
function checktextnull(name,nametip)
{
  if($("#"+name).val()==""||$("#"+name).val()==null)
  {
    $("#"+nametip).css("display","");
    return "1";
  }
  else
  {
   $("#"+nametip).css("display","none");
   return "0";
  }
}
 //提示输入的文本内容为空值
function checktext(nametip,nameText,state)
{
  if(state=="0")
  {
	  $("#"+nametip).html(nameText);
	  return false;
  }
  else
  {
    $("#"+nametip).html(nameText);
	  return true;
  }
}
//验证只能输入正整数 >=0
function checkNum_order(name,tipID)
{
    $("#"+tipID).html("");
    var num_value=$("#"+name).val();
    var re =/^(0|([1-9]\d*))$/;
    var cphone=/^([0-9]{11})?$/;
	if (!re.test(num_value))
	  {
	     if(isNaN(num_value)){
	      $("#"+tipID).html("*只能输入数字,请正确输入");
	        return false;
	     }
	  }

	  if(name=='cell_phone' && !cphone.test(num_value)){
	        $("#"+tipID).html("*手机号格式有误,请正确输入");
	        return false;
	  }
}
  //验证发票输入的文本内容是否为空值且只能输入数字
function checktextnullAndNumber(name,nametip)
{
  if($("#"+name).val()==""||$("#"+name).val()==null)
  {
    $("#"+nametip).css("display","");
    return "1";
  }
  else
  {
   if(checkNum_order(name,nametip)==false)
   {
	    $("#"+nametip).css("display","");
	    return "1";
   }
   else
   {
	   $("#"+nametip).css("display","none");
	   $("#"+nametip).html("");;
	   return "0";
   }
  }
}
//**************************************工具方法结束*******************************






















































