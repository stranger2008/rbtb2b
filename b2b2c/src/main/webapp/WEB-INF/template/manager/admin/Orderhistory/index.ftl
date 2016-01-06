<html>
  <head>
    <title>订单历史记录</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Orderhistory_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 订单管理 > 订单历史记录
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="10%"  align="center" class="top_td">订单号</td>
    <td width="10%" align="center" class="top_td">操作人</td>
    <td width="20%"  align="center" class="top_td">操作记录</td>
    <td width="10%"  align="center" class="top_td">操作时间</td>
  </tr>
  
  <#list orderhistoryList as orderhistory>
  <tr>
    <td align="center">${orderhistory.order_id?if_exists}</td>
    <td align="center">${orderhistory.cust_name?if_exists}</td>
    <td align="center">${orderhistory.action_name?if_exists}</td>  
    <td align="center">${orderhistory.in_date?if_exists}</td>
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
		<td align="right">操作人:</td>
		<td><@s.textfield name="cust_name_s"/></td>
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
			<input type="button" name="search" value="搜索" onclick="document.forms[0].submit();"/>&nbsp;
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