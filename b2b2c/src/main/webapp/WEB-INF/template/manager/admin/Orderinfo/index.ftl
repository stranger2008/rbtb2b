<html>
  <head>
    <title>订单管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
       $(document).ready(function(){ 
        //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
		 onlyshowarea("${cfg_topareaid?if_exists}",1);	
       });
    </script>    
  </head>
  <body>
<@s.form action="/admin_Orderinfo_list.action" method="post">
<@s.hidden name="nav.isshow" id="admin_nav_state"/>
<@s.hidden name="admin_nav_id" id="admin_nav_id"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 订单管理 > 订单管理 
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="sc"><a onclick="delInfo('orderinfo.order_id','/admin_Orderinfo_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('orderinfo.order_id')"/></td>
    <td width="10%"  align="center" class="top_td">订单号</td>
    <td width="23%" align="center" class="top_td">供应信息</td>
    <td width="7%"  align="center" class="top_td">运费(元)</td>
    <td width="10%"  align="center" class="top_td">总共支付（元）</td>
    <td width="10%"  align="center" class="top_td">订单状态</td>
    <td width="10%"  align="center" class="top_td">下单时间</td>
    <td width="10%" align="center" class="top_td">查看</td>
  </tr>
  
  <#list orderinfoList as orderinfo>
  <tr>
    <td><input type="checkbox" name="orderinfo.order_id" value="${orderinfo.order_id?if_exists}"/></td>
    <td align="center"><a href="/admin_Orderinfo_view.action?orderinfo.order_id=${orderinfo.order_id?if_exists}">${orderinfo.order_id?if_exists}</a></td>
    <td>
    <table cellspacing="0" boder="0">
       <tr style="height:30px;">
           <td align="left" rowspan="4" class="sty1" style="border-bottom: none;" >
              <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">
                 <img src="${orderinfo.img_path?if_exists}" width="120" height="80" border="0" />
              </a>
           </td>
           <td align="left" colspan="2" class="sty3" style="padding-left:10px;border-bottom: none;">
             <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">${orderinfo.title?if_exists}</a>
           </td>
       </tr>     
       <tr style="height:30px;border-bottom: none;" ><td align="left" class="sty4" style="padding-left:10px;border-bottom: none;">数量:${orderinfo.goods_num?if_exists}</td><td class="sty4" style="border-bottom: none;">单价:${orderinfo.ord_price?if_exists}</td>
       <tr style="height:30px;">
       		<td align="left" colspan="2" class="sty3" style="padding-left:10px;border-bottom: none;">
       			买家：${orderinfo.buyer_cust_name?if_exists}<br/>
       			卖家：${orderinfo.seller_cust_name?if_exists}
       		</td>
       </tr>
    </table>
    </td>
    <td align="center"><#if orderinfo.carriage_fee?if_exists==0>免运费<#else>${orderinfo.carriage_fee?if_exists}</#if></td>
    <td align="center">${orderinfo.total_fee?if_exists}</td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Orderinfo_list.action','order_state_s=${(orderinfo.order_state)?if_exists}');">
    		<#if orderinfo.order_state?if_exists=='0'>
    			<span class="redcolor">等待买家付款</span>
    		</#if>
    		<#if orderinfo.order_state?if_exists=='1'>
    			<span class="greencolor">等待卖家发货</span>
    		</#if>
    		<#if orderinfo.order_state?if_exists=='2'>
    			<span class="bluecolor">等待买家确认收货</span>
    		</#if>
    		<#if orderinfo.order_state?if_exists=='3'>
    			<span class="redcolor">交易成功</span>
    		</#if>
    		<#if orderinfo.order_state?if_exists=='4'>
    			<span class="redcolor">交易关闭</span>
    		</#if>
		</a>
    </td> 
    <td align="center">${orderinfo.ord_date?if_exists}</td>
    <td align="center">
    <a onclick="linkToInfo('/admin_Orderinfo_view.action','orderinfo.order_id=${orderinfo.order_id?if_exists}');"><img src="/include/images/view.gif" title="订单详情"/></a>
    <a onclick="linkToInfo('/admin_Orderhistory_list.action','order_id_s=${orderinfo.order_id?if_exists}');"><img src="/include/images/bj.gif" title="订单历史"/></a>
    </td>
  </tr>
  </#list>
  
  
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">订单号:</td>
		<td><@s.textfield name="order_id_s"/></td>
	</tr>
	<tr>
		<td align="right">商品标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
		<td align="right">卖家名称:</td>
		<td><@s.textfield name="sellername_s"/></td>
	</tr>
	<tr>
	    <td align="right">买家名称:</td>
	    <td><@s.textfield name="buyname_s"/></td>
    </tr>
   	<tr>
     <td align="right">收货人姓名:</td>
      <td><@s.textfield name="custname_s"/></td>
    </tr>
    <tr>
		<td align="right">地区:</td>
		<td><div id="arealist"></div></td>
	</tr>
    <tr>
    <td align="right">订单状态:</td>
     <td>
        <@s.select name="order_state_s" list="ordercommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
     </td>
    </tr>
    <tr>
    <td width="25%" align="right" >下单时间段:</td>
        <td>  
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 	
        至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
       </td>
    </tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

<!--搜索区域结束-->
   <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
   <@s.hidden id="search_area_attr" name="area_attr_s"/>
   <!--所属分类插件隐藏字段开始区域-->
   <@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
   <@s.hidden id="hidden_up_cate_id" name="hidden_up_cate_id" value="${hidden_up_cate_id?if_exists}"/>
   <@s.hidden id="hidden_up_level" name="hidden_up_level" value="${hidden_up_level?if_exists}"/>
   <!--所属分类插件隐藏字段结束区域-->
</@s.form>

  </body>
</html>