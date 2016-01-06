<html>
  <head>
    <title>商家打分信息列表</title>   
     <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
     <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Sellerscore_countindex.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 >商家打分信息列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="shuaix"><a onclick="linkToInfo('admin_Sellerscore_list.action','');">查看记录</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    
     	 <td width="30%"  align="center" class="top_td">商店名称</td>
    
     	 <td width="15%"  align="center" class="top_td">描述相符打分</td>
    
     	 <td width="15%"  align="center" class="top_td">服务态度打分</td>
    
     	 <td width="15%"  align="center" class="top_td">发货速度打分</td> 
    
   		 <td width="10%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list sellerscoreList as sellerscore>
   <tr bgcolor="<#if sellerscore_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    	<td align="center">
    	<a onclick="linkToInfo('/admin_Shopconfig_view.action','shopconfig.cust_id=${sellerscore.cust_id?if_exists}');">
    	${sellerscore.cust_name?if_exists}
    	</a>
    	
    	</td>
    	
    	<td align="center">
    	<#if sellerscore.desc_score=="">0<#else>${sellerscore.desc_score?if_exists}</#if>
    	</td>
    
    	<td align="center">
    	<#if sellerscore.service_score=="">0<#else>${sellerscore.service_score?if_exists}</#if>
    	</td>
    
    	<td align="center">
    	<#if sellerscore.delivery_score=="">0<#else>${sellerscore.delivery_score?if_exists}</#if>
    	</td>
          
    <td align="center">
	    <a onclick="linkToInfo('/admin_Sellerscore_list.action','cust_id_s=${sellerscore.cust_id?if_exists}');">
	    <img src="/include/images/bj.gif" />查看记录</a>
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
		<td align="right">会员名称</td> 
		<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','','','');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

