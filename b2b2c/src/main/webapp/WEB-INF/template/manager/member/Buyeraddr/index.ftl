<html>
<head>
<title>收货地址管理</title>
<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
 <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  <script type="text/javascript">
	  $(document).ready(function(){ 
	     //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
		 onlyshowarea("${cfg_topareaid?if_exists}",1);		
	  });
	</script> 
</head>
<body>
<@s.form action="/member_Buyeraddr_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:会员信息>会员资料>收货地址列表</td>
 </tr>
</table>
    <div class="finder_bar_bg">
     <ul>
       <li class="cont_first_li"><a onclick="linkToInfo('/member_Buyeraddr_add.action','');">添加地址</a></li>
       <li><a onclick="delInfo('buyeraddr.addr_id','/member_Buyeraddr_delete.action')">删除</a></li>
       </ul>
    </div>
    <table width="100%" cellspacing="0" border="0">
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buyeraddr.addr_id')"/></td>
    <td width="10%"  align="center" class="top_td">收货人姓名</td>
    <td width="10%"  align="center" class="top_td">省/市区</td>
    <td width="10%"  align="center" class="top_td">详细地址</td>
    <td width="10%"  align="center" class="top_td">邮政编码</td>
    <td width="10%"  align="center" class="top_td">电话/手机</td>
    <td width="10%"  align="center" class="top_td">操作日期</td>
    <td width="12%" align="center" class="top_td">编辑</td>
    </tr>
   <#list buyeraddrList as buyeraddr>
  <tr>
    <td><input type="checkbox" name="buyeraddr.addr_id" value="${buyeraddr.addr_id?if_exists}"/></td>
    <td align="center">
    <#if buyeraddr.cust_name?if_exists!=''>
    <a onclick="linkToInfo('/member_Buyeraddr_view.action','buyeraddr.addr_id=${buyeraddr.addr_id?if_exists}');">
    <#if buyeraddr.cust_name?length lt 8>
    ${buyeraddr.cust_name?if_exists}
    <#else>
    ${buyeraddr.cust_name[0..7]}
    </#if>
    </a>
    </#if></td>
    <td align="center">${buyeraddr.area_attr?if_exists}</td>
    <td align="center">${buyeraddr.address?if_exists}</td>
    <td align="center">${buyeraddr.post_code?if_exists}</td>
    <td align="center">${buyeraddr.phone?if_exists}/${buyeraddr.cell_phone?if_exists}</td>
    <td align="center">${buyeraddr.in_date?if_exists}</td>
    <td align="center">
    
    <a onclick="linkToInfo('/member_Buyeraddr_view.action','buyeraddr.addr_id=${buyeraddr.addr_id?if_exists}');"  class="xg">修改</a>
    <a href="javascript:delOneInfo('buyeraddr.addr_id','/member_Buyeraddr_delete.action','${(buyeraddr.addr_id)?if_exists}');" class="dele">删除</a>
    </td>
  </tr>
  </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>

<!--搜索区域结束-->
<!--所属分类插件隐藏字段开始区域-->
<@s.hidden id="hiddenvalue" name="hiddenvalue" value="${hiddenvalue?if_exists}"/>
</@s.form>
</body>
</html>
