<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>团购-${cfg_mallwebname?if_exists}</title>
<link href="/templets/bmall/css/public.css" rel="stylesheet" type="text/css" />
<link href="/templets/bmall/css/buy.css" rel="stylesheet" type="text/css">
<link href="/templets/bmall/css/proshow.css" type="text/css" rel="stylesheet">	
<#include "/WEB-INF/template/bmall/default/jsinclude.ftl">
<script src="/templets/bmall/js/base.js" type=text/javascript></script>
<script src="/templets/bmall/js/zoomlib.js" type=text/javascript></script>
<script src="/templets/bmall/js/zoom.js" type=text/javascript></script>
<script src="/templets/bmall/js/use.js" type="text/javascript"></script>
<script type="text/javascript" src="/templets/bmall/js/cart.js"></script>
<script>
  function lxfEndtime(){
          $(".lxftime").each(function(){
                var lxfday=$(this).attr("lxfday");//用来判断是否显示天数的变量
                var time = $(this).attr("endtime");
                var endtime = new   Date(Date.parse(time.replace(/-/g,   "/")));//取结束日期(毫秒值)
                var nowtime = new Date().getTime();        //今天的日期(毫秒值)
                var youtime = endtime-nowtime;//还有多久(毫秒值)
                var seconds = youtime/1000;
                var minutes = Math.floor(seconds/60);
                var hours = Math.floor(minutes/60);
                var days = Math.floor(hours/24);
                var CDay= days ;
                var CHour= hours % 24;
                var CMinute= minutes % 60;
                var CSecond= Math.floor(seconds%60);//"%"是取余运算，可以理解为60进一后取余数，然后只要余数。
                if(endtime<=nowtime){
                        $(this).html("已过期")//如果结束日期小于当前日期就提示过期啦
                        }else{
                                if($(this).attr("lxfday")=="no"){
                                        $(this).html("<i>剩余时间：</i><span>"+CHour+"</span>时<span>"+CMinute+"</span>分<span>"+CSecond+"</span>秒");          //输出没有天数的数据
                                        }else{
                        $(this).html("<i>剩余时间：</i><span>"+days+"</span><em>天</em><span>"+CHour+"</span><em>时</em><span>"+CMinute+"</span><em>分</em><span>"+CSecond+"</span><em>秒</em>");          //输出有天数的数据
                                }
                        }
          });
   setTimeout("lxfEndtime()",1000);
  };
$(function(){
      lxfEndtime();
   });
   
function minusnum(){
   $('.cut').blur();
   var num = $("#num").val();
   if(num>1){
      num = num -1;
   }
   $("#num").val(num);
   
}

function plusnum(){
   var maxnum = $("#cust_maxbuy").val();
   var num = $("#num").val();
   if(Number(maxnum) > Number(num) || maxnum=='0'){
      num = Number(num) + 1;
   }
   $("#num").val(num);
   $('.add').blur();
}
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
	        $("#num").val(1);
	        return false;
	     }
	  }
}
$(document).ready(function () {

 //加载规格是否选择
		loadSelectAttr();
});
</script>
<style type="text/css">
<!--
*{
        font-style: normal;
        font-weight: normal;}
.haveday {
        padding: 20px;
        border: 1px dashed #000;
        margin-right: auto;
        margin-left: auto;
        width: 300px;
}
-->
</style>
</head>
<body>
<#include "/WEB-INF/template/bmall/default/top.ftl">
<input type="hidden" id="cust_maxbuy" value="${(groupgoods.cust_maxbuy)?if_exists}"/> 
<!--导航结束-->

<!--团购-->

<div class="w980">
 
  <div class="dew700">
    
     <div class="decont">
     
        <h2 class="deh2"><span class="deh2color"></span><#if (groupgoods.group_title)?length lt 70> ${(groupgoods.group_title)?if_exists}<#else>${(groupgoods.group_title)[0..69]}</#if></h2>
        
        <#assign s = ((groupprice/saleprice*10)?double)/>
        <#assign p = s?string.currency/>
        <div class="clear"></div>
        <div class="cy">
          <div class="lcy">
            <h2 class="cyh2"><span>￥${groupprice?if_exists}</span><a href="###;" onclick="buynow(this);" class="ct" ><img src="/templets/bmall/images/xzct_11.gif"></a></h2>
            <div class="ctcont">
               <p><span class="f_left">原价</br><b>￥${saleprice?if_exists}</b></span><span class="f_left">节省</br><b>￥${pyrprice?if_exists}</b></span><span class="f_left">折扣</br><b>${p}折</b></span></p>
               <div class="clear"></div>
               <p><b>${(groupgoods.already_buy)?if_exists}</b>&nbsp;份已售出</br><span>数量有限，下单要快哦</span></p>
               <p class="lxftime" endtime="${(groupgoods.end_date)[0..9]}"></p>
            </div>
             <!-- JiaThis Button BEGIN -->

					<div id="ckepop" style="padding-top:20px;">
						<span class="jiathis_txt">分享到：</span>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_renren"></a>
						<a class="jiathis_button_kaixin001"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<a class="jiathis_counter_style"></a>
					</div>
					<script type="text/javascript" src="http://v2.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
			  </div>
		<div class="imgshow">  
		   <div class=jqzoom id=spec-n1><img src="<#if lastpic?if_exists!=''>${lastpic?if_exists}<#else>/include/images/nopic.jpg</#if>" jqimg="<#if lastpic?if_exists!=''>${lastpic?if_exists}<#else>/include/images/nopic.jpg</#if>" width=350 height=250></div>
			<div  class="specn5" id=spec-n5>
				<div class=lcontrol id=spec-left>
					<img src="/templets/bmall/images/left.gif" />
				</div>
				<div  class="speclist" id=spec-list>
					<ul class=listul>
						<#list imglist as img>
		                <li><img src="${img}"></li>
		                </#list>
					</ul>
				</div>
				<div class=rcontrol id=spec-right>
					<img src="/templets/bmall/images/right.gif" />
				</div>
		    </div>
		 </div>
		</div> 
     </div>
      <div class="clear"></div>
       <div class="proselect">
         <div class="lpros">
            <p><span>物流运费：<#if groupgoods.is_ship?if_exists=='0'>买家承运费<#else>运费计算</#if></span></p>
            <p>评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：&nbsp;${evalcount?if_exists}条评论</p>
            <p>购商品送积分数：${(groupgoods.give_inter)?if_exists}</p>
            <p>购商品送返现金数：${(groupgoods.give_money)?if_exists}</p>
          </div>
          <div class="rpros">
            <!-- 绑定商品属性开始-->
        		  <#assign anum=1>
        		  <#assign anum_attr=1>
        		  <#list good_attr?split(",") as s>
			      		<#assign len = "${s?if_exists}"?length>	
			             <!-- 得到属性ID的位置 -->
	                     <#assign idpos = "${s?if_exists}"?index_of('|')>
	                     <#if (idpos>-1)>
	                         <#assign getRightStr = s[(idpos+1)..(len-1)]>
	                     </#if>
	                     <#assign valpos = "${getRightStr?if_exists}"?index_of('|')>
	                     <#assign rightlen = "${getRightStr?if_exists}"?length>	
	                     <#if ((valpos-1)>-1)>
	                         <#assign thisval = getRightStr[0..(valpos-1)]><!--获取值-->
	                         <#assign thisName = getRightStr[(valpos+1)..(rightlen-1)]><!--获取名称-->
	                     </#if>
	                     <!--判断是否存在多个值-->
	                     <#assign ismorevalLen = "${thisval?if_exists}"?index_of(':')>	
	                     <#if (ismorevalLen>-1)>
	                    	   <P>
		                    	   <span class="cont_t_r_b_sp_3" id="size_name_${anum}">${thisName?if_exists}</span>
		                    	   <span class="cont_t_r_b_sp_3">：</span>
		                    	   <span id="size_select_tip_${anum}" style="color:red;display:none;">*请选择${thisName?if_exists}</span>
	                    	   </P>
	                    	   <ul class="size" id="size_attr_${anum}">
	                     	   <#list thisval?split(":") as t>
	                     	   		<#if t!=''>
							    		<li>
								    		<a href="###;" onclick="checksizeattr('${anum}','size_attr_${anum}','size_name_${anum}','size_a_${anum_attr}');" 
								    		id="size_a_${anum_attr}">${t}</a>
							    		</li>
							    	</#if>
							    <#assign anum_attr=anum_attr+1>
	                     	   </#list>
	                     	   </ul>
	                     	   <input type="hidden" id="size_select_value_${anum}" value="0" />
			                   <div class="clear"></div> 
			                   <#assign anum=anum+1>   
	                     </#if> 
			      </#list>
			      <#if anum!=1>
					<P class="number" style="clear:both"><span class="cont_t_r_b_sp_3">已选择：</span>
					 <span class="cont_t_r_b_sp_3" style="font-weight: bold;" id="size_select_value"></span>
					 <input type="hidden" id="cart_goods_attr_name" />
					 <input id="type_size_count" value="${anum}" type="hidden"/>
					</P> 
					</#if>
    			   <!-- 绑定商品属性结束-->
            <p>购买数量：<img src="/templets/default/images/but_jian.gif" style="cursor:pointer;" onclick="minusnum();"/>&nbsp;<input id="num" type="text" value="1" class="atext" onkeyup="checkNum(this);">&nbsp;<img src="/templets/default/images/but_jia.gif" style="cursor:pointer;" onclick="plusnum();">&nbsp;&nbsp;件&nbsp;<#if (groupgoods.cust_maxbuy)?if_exists!='0'>(每人限购${(groupgoods.cust_maxbuy)?if_exists}件)</#if></p>
           <#if cust_name?if_exists!=''><p>商家：${cust_name?if_exists} &nbsp; <a href="/shop/${user_name?if_exists}.html">进入店铺</a></p></#if>
          </div>
          <div class="clear"></div>
        </div>
       <div class="clear"></div>
       <div class="bpshow">
       ${(groupgoods.group_desc)?if_exists}
       </div>
       
     </div>
     
    
  <div class="rw285">
     
    <h2 class="recomh2"></h2>
    
     <#list hotgroupList as hotgroup>
     <#assign s = ((hotgroup.group_price/hotgroup.market_price*10)?double)/>
      <#assign p = s?string.currency/>
    <ul class="recul">
      <li><div class="recompic"><a href="/mall/goods!groupdetail.action?group_id=${(hotgroup.group_id)?if_exists}"><img src="<#if (hotgroup.group_img)?if_exists!=''>${(hotgroup.group_img)?if_exists}<#else>/include/images/nopic.jpg</#if>" width="260px" height="176px"></a></div></li>
      <li><#if (hotgroup.group_title)?length lt 40> ${(hotgroup.group_title)?if_exists}<#else>${(hotgroup.group_title)[0..39]}</#if></li>
      <li><div class="reprice"><p class="f_left">原价：<span class="price">${(hotgroup.market_price)?if_exists}元</span><b>${p}折</b></span><p class="f_right"><b>${(hotgroup.already_buy)?if_exists}</b>人已购买</p></div></li>
      <li><div class="reback"><span class="costs">￥${(hotgroup.group_price)?if_exists}</span><a href="/mall/goods!groupdetail.action?group_id=${(hotgroup.group_id)?if_exists}" class="recbut"><img src="/templets/bmall/images/recbut_03.gif"></a></div></li>
    </ul>
    </#list>
  </div>
  
</div>
<!--加入购物车显示效果结束-->
<input id="cart_goods_id" type="hidden" value="${groupgoods.goods_id?if_exists}" />
<input id="cart_goods_name" type="hidden" value="${goods_name?if_exists}" />
<input id="cart_goods_give_inter" type="hidden" value="${groupgoods.give_inter?if_exists}" />
<input id="cart_goods_sale_price" type="hidden" value="${groupgoods.group_price?if_exists}" />
<input id="cart_goods_img_path" type="hidden" value="${groupgoods.group_img?if_exists}" />
<input id="cart_goods_now_stock" type="hidden" value="${now_stock?if_exists}" />
<input id="cart_goods_cust_name" type="hidden" value="${cust_name?if_exists}" />
<input id="cart_goods_cust_id" type="hidden" value="${cust_id?if_exists}" />


<!--底部开始-->
<#include "/WEB-INF/template/bmall/default/bottom.ftl">
</body>
</html>
