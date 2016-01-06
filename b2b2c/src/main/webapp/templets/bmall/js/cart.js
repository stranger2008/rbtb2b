 var noCartGoodsTip="<tr id='nocartid' ><td colspan='9'><span  style='color:#808080;text-align:center;font-size:11pt;font-weight:bold;'>购物车中还没有商品，赶紧选购吧！</span></td></tr>";
 //读取COOKIES的值到购物车里面显示
function mallReadCookies()
{
 var vargoodshtml="";
 if($.cookie("cart_cookieNum") != null){
	  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	  var cart_countnum=$.cookie("cart_countNum");
	  for(var i = 1; i<= cart_cookieNum; i++)
	  {
	      
	      
	      
	      
		  var goods_id=$.cookie("goods_id" + i);
		  if(goods_id!=null&&goods_id!="")
		  {
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
			  vargoodshtml+="<tr id='item"+i+"' class='goodsid"+goods_id+"'>"
						  +" <td rowspan='1' ><a href='/mall-goodsdetail-"+goods_id+".html' target='_blank'><img class='lazy' width='55px' height='55px' src='"+goods_img_path+"' /></a></td>"
						  +" <td class='name'><a href='/mall-goodsdetail-"+goods_id+".html' target='_blank'>"+goods_name+"</a></td>"
						  +"<td>"+goods_attr_name+"</td>"
						  +"<td> <span style='color:#A10000' id='g_give_inter_"+i+"' >"+goods_give_inter+"</span>分</td>"
						  +" <td> ￥<span style='color:#A10000'>"+goods_price+"</span> 元 </td>"
						  +"<td><span class='modify-qty' >"
						  +"<a class='track' href='javascript:void(0);'  onclick='SubGoodsNum("+i+");' ><img src='/templets/bmall/images/minus.gif' alt='减少' /></a>"
						  +" <input type='text' maxlength='3' onkeyup='ChangeCartGoodsNum(this,"+i+")'  value='"+goods_Num+"' id='gnumber"+i+"' />"
						  +"<a class='track' href='javascript:void(0);' onclick='AddGoodsNum("+i+");' ><img src='/templets/bmall/images/plus.gif' alt='增加' /></a></span>"
						  +"</td>"
						  +" <td class='present'> - </td>"
						  +"<td> ￥<span id='gprice"+i+"' style='color:#A10000'>"+(goods_price*goods_Num).toFixed(2)+"</span> </td>"
						  +" <td><a class='add-faverate track' href='javascript:void(0);' onclick='addCartCollect(\""+goods_id+"\",\""+goods_name+"\")'> 移入收藏夹</a><br /><br /><a class='del track' href='javascript:void(0);' onclick='mallClearOneCookies("+i+");' > 删除</a></td>"
						  +"</tr>";
	  		}
	  }
   }
   if(vargoodshtml=="")
   {    $("#nocartid").remove();
   		vargoodshtml=noCartGoodsTip;
   }
  $("#carttable").append(vargoodshtml);
  AcountGoodsNumber();
  AcountMoney();
}
//购物车收藏商品
function addCartCollect(gid,gname)
{
  var gurl=window.location.hostname;
  gurl="http://"+gurl+"/mall-goodsdetail-"+gid+".html";
  $("#collect_link_url").val(gurl);
  $("#collect_title").val(gname);
  insertCollCat('0');
}

//商品加入购物车idTip:0:表示提示'加入购物车成功'，1：表示不提示
function AddMallCart(cartpos,idTip)
{
   //判断是否已经选择了商品属性
   if(isSelectAttr()=="0")
   {
     return false;
   }
   var goodsid=$("#cart_goods_id").val();
   var buynum=1;
   var flage=0;
   //从页面中获取商品选择的属性规格
   var get_goods_attr_name=$("#cart_goods_attr_name").val();
   if($("#buynumid").val()!=null&&$("#buynumid").val()!="")
   {
     buynum=parseInt($("#buynumid").val());
   }
   if(goodsid!=null&&goodsid!="")
   {
   	  //循环获取购物车里面的商品项
   	  if($.cookie("cart_cookieNum")!=null&&$.cookie("cart_cookieNum")!="")
   	  {
	      var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	      var catr_goods_attr_name="";
		  for(var i = 1; i<= cart_cookieNum; i++)
		  {
		    var catrgoods_id=$.cookie("goods_id" + i);
		    if(catrgoods_id!=null&&catrgoods_id!="")
		    {
		      //从cookies中获取商品属性值
		      catr_goods_attr_name=$.cookie("goods_attr_name" + i);
		      //判断购物车里面是否已经含义该商品，如果有包含该商品的话，就讲商品数量加1，否则就加入购物车
		      if(goodsid==catrgoods_id&&get_goods_attr_name==catr_goods_attr_name)
		      {
		       
		      		flage=1;
	    			var goods_now_stock=$("#cart_goods_now_stock").val();
	    			var goodnum=parseInt($.cookie("goods_Num" + i));
	    			if(goods_now_stock>=(goodnum+buynum))
	    			{
    				    goodnum=goodnum+buynum;
		    		    $.cookie("goods_Num" + i, goodnum, { expires: 1 , path: '/'});
		    		    if(idTip=="1")
		    		    {
			    			showCartGoods();
			    			showCart(cartpos,'searchDiv');
		    			}
	    			}
	    			else
	    			{
				        if(idTip=="1")
		    		    {
			    			showCartGoods();
			    			showCart(cartpos,'searchDiv');
		    			}
		    		}
		      }
		     
		    }
		  }
	  }
	  if(flage==0)
	  {
	  	mallSaveCookies(buynum,cartpos,idTip);
	  }
	  
   }
}

//商品的值加入到Cookies中  idTip:0:表示提示'加入购物车成功'，1：表示不提示
 function mallSaveCookies(buynum,cartpos,idTip)
 {
  	 var cart_cookieNum=0;
  	 var cart_countNum=0;
  	  //商品购买数量
	 var goodnum=buynum;
	 if($.cookie("cart_cookieNum") != null&&$.cookie("cart_cookieNum") != ""){
	 	//从cookie获取商品个数
	    cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
	 }
	 if($.cookie("cart_countNum") != null&&$.cookie("cart_countNum") != ""){
	 	//从cookie获取商品实际个数
	    cart_countNum = parseInt($.cookie("cart_countNum"));
	 }
	 //商品个数加一
    cart_cookieNum=cart_cookieNum+1;
     //商品实际个数加一
    cart_countNum=cart_countNum+1;
    //加入商品ID
    $.cookie("goods_id" + cart_cookieNum, $("#cart_goods_id").val(), { expires: 1 , path: '/'});
    //加入商品名称
    $.cookie("goods_name" + cart_cookieNum, $("#cart_goods_name").val(), { expires: 1 , path: '/'});
    //加入商品赠送积分
    $.cookie("goods_give_inter" + cart_cookieNum, $("#cart_goods_give_inter").val(), { expires: 1, path: '/'});
    //加入商品购物数量
    $.cookie("goods_Num" + cart_cookieNum, goodnum, { expires: 1 , path: '/'});
    //加入商品价格
    $.cookie("goods_price" + cart_cookieNum, $("#cart_goods_sale_price").val(), { expires: 1 , path: '/'});
    //加入商品图片
    $.cookie("goods_img_path" + cart_cookieNum, $("#cart_goods_img_path").val(), { expires: 1 , path: '/'});
    //加入商品当前库存
    $.cookie("goods_now_stock" + cart_cookieNum, $("#cart_goods_now_stock").val(), { expires: 1 , path: '/'});
    
    //加入商品所属商店名称
    $.cookie("goods_cust_name" + cart_cookieNum, $("#cart_goods_cust_name").val(), { expires: 1 , path: '/'});
    
     //加入商品属性
    $.cookie("goods_attr_name" + cart_cookieNum, $("#cart_goods_attr_name").val(), { expires: 1 , path: '/'});
    
    //加入商品所属商店ID
    $.cookie("goods_cust_id" + cart_cookieNum, $("#cart_goods_cust_id").val(), { expires: 1 , path: '/'});
    
     $.cookie("cart_cookieNum", cart_cookieNum, { expires: 1, path: '/' });
     $.cookie("cart_countNum", cart_countNum, { expires: 1, path: '/' });

     if(idTip=="1")
     {
	  showCartGoods();
	  showCart(cartpos,'searchDiv');
	 }
 }
 //清空购物车所以商品
 function mallClearAllCookies(){
        if (confirm("确定清除购物车商品？")) {
		  if($.cookie("cart_cookieNum") != null&&$.cookie("cart_cookieNum") != "" ){
			  var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
			  var cart_countNum=parseInt($.cookie("cart_countNum"));
			  for(var i = 1; i<= cart_cookieNum; i++)
			  {
			     if( $.cookie("goods_id"+i)!=null&&$.cookie("goods_id"+i)!="")
			     {
					 mallClearItem(i);
					 $("#item"+i).remove();
				 }
			  }
			  //当前购物车最大数
			  $.cookie("cart_cookieNum", "",{ expires: 1, path: '/' });
			  //购物车实际个数
			  $.cookie("cart_countNum", "",{ expires: 1, path: '/' });
			  AcountGoodsNumber();
			  AcountMoney();
			  //清空商品数量和价格
			   $("#goodscountall").html("0");
			   $("#allcountmoney").html("0.00");
		  }
		  $("#nocartid").remove();
		  $("#carttable").append(noCartGoodsTip);
	  }
 }
//删除购物车某一个商品
 function mallClearOneCookies(numbers){
       if (confirm("确定删除该商品？")) {
		  var cart_countNum=0;
		  if($.cookie("cart_countNum") != null&&$.cookie("cart_countNum")!=""){
			  cart_countNum= parseInt($.cookie("cart_countNum"));
			  mallClearItem(numbers);
			  cart_countNum=cart_countNum-1;
			  $.cookie("cart_countNum", cart_countNum, { expires: 1, path: '/' });
		 	 $("#item"+numbers+"").remove();
		 	
		  }
		  if(cart_countNum==0)
		  {
		   //当前购物车最大数
			  $.cookie("cart_cookieNum", "",{ expires: 1, path: '/' });
			  //购物车实际个数
			  $.cookie("cart_countNum", "",{ expires: 1, path: '/' });
			    //清空商品数量和价格
			  $("#goodscountall").html("0");
			  $("#allcountmoney").html("0.00");
			   $("#nocartid").remove();
		      $("#carttable").append(noCartGoodsTip);
		  }
		   AcountGoodsNumber();
		   AcountMoney();
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
	  $.cookie("goods_cust_name" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_attr_name" + num, "",{ expires: 1, path: '/' });
	  $.cookie("goods_cust_id" + num, "",{ expires: 1, path: '/' });
 }




//处理图片路径  
function optImgUrl(imgurl)
{
   var returl="/include/images/nopic.jpg";
   if(imgurl!=null&&imgurl!="")
   {
	   if(imgurl.indexOf(",")!=-1)
	   {
	     var numindex=imgurl.indexOf(",");
	      returl=imgurl.substring(0,numindex);
	   }
	   else
	   {
	     returl=imgurl;
	   }
   }
   return returl;
}
//统计商品总金额
function AcountMoney()
{
	  var allcount=0;
	  if($.cookie("cart_countNum")!=null&&$.cookie("cart_countNum")!="")
	  {
	      var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
		  for(var i = 1; i<= cart_cookieNum; i++)
		  {
		  	  var goods_id=$.cookie("goods_id" + i);
		  	  var goods_Num=$.cookie("goods_Num" + i);
			  var goods_price=$.cookie("goods_price" + i);
			  if(goods_id!=null&&goods_id!="")
			  {
			    allcount=allcount+(goods_Num*goods_price);
			  }
		  }
	   	 $("#allcountmoney").html("  "+allcount.toFixed(2)+" ");
	  }
}
//当商品购物车数量改变的时候，修改小计价格
function ChangePrice(num)
{
  	var allcount=0;
	var goods_Num=$.cookie("goods_Num" + num);
	var goods_price=$.cookie("goods_price" + num);
	var goods_give_inter=$.cookie("goods_give_inter" + num)*goods_Num;
	allcount=goods_Num*goods_price;
    $("#gprice"+num).html(allcount.toFixed(2));
    $("#g_give_inter_"+num).html(goods_give_inter.toFixed(0));
    
}
//改变购物车商品数量显示
function AcountGoodsNumber()
{
 //获取购物车商品数量
  var goods_cookieNum="0";
  if($.cookie("cart_countNum")!=null&&$.cookie("cart_countNum")!="")
  {
   goods_cookieNum = $.cookie("cart_countNum"); 
   $("#goodscountall").html("  "+goods_cookieNum+" ");
  }
  else
  {
   	$("#goodsjs").css("display","none");
  }
}
 //点击加号增加商品数量
 function AddGoodsNum(num)
 {
   if($.cookie("goods_Num"+num)!=null&&$.cookie("goods_Num"+num)!="")
   {
	   var goodnum= parseInt($.cookie("goods_Num"+num));
	   var now_stocks=$.cookie("goods_now_stock"+num);
	   if(goodnum<=now_stocks&&(goodnum+1)<=now_stocks)
	   {
		    goodnum=goodnum+1;
		    $.cookie("goods_Num" + num, goodnum, { expires: 1, path: '/' });
	   }
	   else
	   {
	     alert("已达商品库存最大数!");
	   }
	}
	$("#gnumber"+num).val(goodnum);
	ChangePrice(num);
	AcountGoodsNumber();
	AcountMoney();
 }
 //点击小箭头减少商品数量
 function SubGoodsNum(num)
 {
 	var goodnum=$.cookie("goods_Num"+num);
	if(goodnum>=1&&(goodnum-1)>=1)
	{
	 goodnum=goodnum-1;
	 $.cookie("goods_Num" + num, goodnum, { expires: 1, path: '/' });
	}
	$("#gnumber"+num).val(goodnum);
	ChangePrice(num);
	AcountGoodsNumber();
	AcountMoney();
 }
 //改变输入的商品的购物数量
 function ChangeCartGoodsNum(obj,num)
 {
   checkNum(obj);
   if($("#gnumber"+num).val()!=null&&$("#gnumber"+num).val()!="")
   {
	   var goodsnum=parseInt($("#gnumber"+num).val());
	   var now_stocks=parseInt($.cookie("goods_now_stock"+num));
	   if(goodsnum>now_stocks||goodsnum<1)
	   {
	     alert("商品数量超过商品库存数量");
	     $("#gnumber"+num).val($.cookie("goods_Num"+num));
	   }
	   else
	   {
	    $.cookie("goods_Num" + num, goodsnum, { expires: 1, path: '/' });
	   }
   }
   else
   {
     $("#gnumber"+num).val($.cookie("goods_Num"+num));
   }
   ChangePrice(num);
   AcountGoodsNumber();
   AcountMoney();
 }
 //验证只能输入正整数 >=0
function checkNum(obj)
{
    var num_value=$(obj).val();
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value))
	  {
	     if(isNaN(obj.value)){
	        obj.value="";
	        obj.focus();
	        alert("只能输入数字,请正确输入!"); 
	        return false;
	     }
	  }
}
//点击直接购买跳转到订单页面
function buynow(cartpos)
{
	//判断是否已经选择了商品属性
   if(isSelectAttr()=="0")
   {
     return false;
   }
   //清除购物车COOKIES的只
   mallClearAllGoods();
   //加入购物车COOKIES
   AddMallCart(cartpos,"0");
   window.location.href="/mall/goods!orderinfo.action";
} 

//直接清除COOKIES的购物商品
function mallClearAllGoods(){
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
//点击加入购物车弹出提示购物车信息
function showCartGoods()
{
    //商品总数量
    var gnumber=0;
    //商品总价
    var allcount=0;
	  if($.cookie("cart_countNum")!=null&&$.cookie("cart_countNum")!="")
	  {
	      gnumber=$.cookie("cart_countNum");
	      var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
		  for(var i = 1; i<= cart_cookieNum; i++)
		  {
		  	  var goods_id=$.cookie("goods_id" + i);
		  	  var goods_Num=$.cookie("goods_Num" + i);
			  var goods_price=$.cookie("goods_price" + i);
			  if(goods_id!=null&&goods_id!="")
			  {
			    allcount=allcount+(goods_Num*goods_price);
			  }
		  }
	  }
	  $("#gcountnum").html(gnumber);
	  $("#gcountmomey").html(allcount.toFixed(2));
	  
}

//列表筛选弹出层操作-开始
function divfixed2(r,name){ 
   var sug=document.getElementById(name);
   sug.style.left=(getPosition(r).x - 150) +"px"; 
   sug.style.top= (getPosition(r).y -100 ) +r.offsetHeight+"px"; 
   sug.style.position="absolute"; 
   sug.style.display="block"; 
}
//显示购物车
function showCart(obj,val){
	divfixed2(obj,val);
}
//关闭购物车
function closeCart(val){
	$("#"+val).css("display","none");
}
//选中商品属性的样式选择
function checksizeattr(id,sid,sname,aid)
{
  var varscount=1;//获取规格的个数
  var varsname="";//获取规格名称
  var varselectsname="";//获取已经选择的规格值
  var hidden_sel_name="";
  $("#"+sid).find("li").find("a").each(function(){
    $(this).removeClass(); 
  });
  $("#"+aid).addClass("searchsize"); 
  varsname=$("#"+sname).html();
  varscount=$("#type_size_count").val();
  for(var i=1;i<=varscount;i++)
  {
	  $("#size_attr_"+i).find("li").find("a").each(function(){
	    if( $(this).attr("class")=="searchsize")
	    {
	      varselectsname+="\""+$(this).html()+"\"  ";
	      hidden_sel_name+=$(this).html()+",";
	    }
	  });
  }
  $("#size_select_value").html(varselectsname);
  if(hidden_sel_name!=null&&hidden_sel_name!="")
  {
   hidden_sel_name=hidden_sel_name.substring(0,hidden_sel_name.length-1);
  }
  //主要用于加入购物车获取的商品规格
  $("#cart_goods_attr_name").val(hidden_sel_name);
  //用于表示该规格已经选择了
  $("#size_select_value_"+id).val("1");
  //隐藏提示
  $("#size_select_tip_"+id).css("display","none");
}
//验证是否已经选择了商品属性规格，返回值：0：没有完全选择规格，1：已经选择了规格
function isSelectAttr()
{
   var varscount=1;//获取规格的个数
   varscount=$("#type_size_count").val();
   var isselect="1";//判断是否已经选择规格，0：没有完全选择规格，1：已经选择了规格
   if(varscount!=1)
   {
	  for(var i=1;i<=varscount;i++)
	  {
		 if( $("#size_select_value_"+i).val()=="0")
		 {
		   $("#size_select_tip_"+i).css("display","");
		   isselect="0";
		 }
	  }
   }
   return isselect;
}
//加载规格是否选择
function loadSelectAttr()
{
   var varscount=1;//获取规格的个数
   varscount=$("#type_size_count").val();
   if(varscount!=1)
   {
	  for(var i=1;i<=varscount;i++)
	  {
		  $("#size_select_value_"+i).val("0");
	  }
   }
}
//判断输入购买商品的数量
function buyChangeGoodsNum(obj)
{
  var flage="0";
  var tiptext="";
  checkNum(obj);
  if($("#buynumid").val()!=null&&$("#buynumid").val()!="")
   {
	   var goodsnum=0;
	   var now_stocks=0;
	   
	   if($("#buynumid").val()!=null&&$("#buynumid").val()!="")
	   {
	   	goodsnum=parseInt($("#buynumid").val());
	   }
	   if($("#cart_goods_now_stock").val()!=null&&$("#cart_goods_now_stock").val()!="")
	   {
	    now_stocks=parseInt($("#cart_goods_now_stock").val());
	   }
	   
	   if(goodsnum<1)
	   {
	    tiptext="*购买商品数量不能低于1";
	    $("#buynumid").val("1");
	    flage="1";
	   }
	   else if(goodsnum>now_stocks)
	   {
	    tiptext="*购买商品数量超过商品库存数量";
	    $("#buynumid").val("1");
	    flage="1";
	   }
   }
   else
   {
      $("#buynumid").val("1");
      flage="1";
   }
   $("#buycountmax").html(tiptext);
    //更新运费
   var a_id=$("#sareaid").val();
   //计算运费
   shipprice(a_id);
   return flage;
}
//判断输入购买商品的数量：鼠标点击时的触发事件，清空提示
function inputGoodsnum()
{
 $("#buycountmax").html("");
}
//控制是否显示购买按钮，如果商品的库存数量为O的时候，不显示购买按钮
function ifBuyGoods()
{
   var gooodnum=$("#hidden_goods_now_stock").val();
   if(gooodnum=="0")
   {
     $("#yesbuyid").hide();
     $("#nobuyid").show();
   }
   else
   {
    $("#yesbuyid").show();
     $("#nobuyid").hide();
   }
}








  