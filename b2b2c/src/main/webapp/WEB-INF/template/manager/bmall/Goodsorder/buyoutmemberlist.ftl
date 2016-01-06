<html>
<head>
<title>购买过的店铺</title>
</head>
<body>
	<div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">购买过的店铺</a>
	</div>
<@s.form action="/bmall_Goodsorder_buyoutmemberlist.action" method="post" >
<div class="rightside f_right">
     <div class="rpostion"><h2>
	        购买过的店铺管理
     </h2></div>
     <div class="ropot">
	       <table cellspacing="0" class="bmall_list_table" >
		    <tr style="background:url(images/top_tr.gif) repeat-x;" >
		    	<td width="5%"   class="fthstyle1" ></td>
			    <td width="20%"  align="center" class="fthstyle1">店铺LOGO</td>
			    <td width="50%"  align="center" class="fthstyle1">详细信息</td>
			    <td width="10%"  align="center" class="fthstyle1">操作</td>
		    </tr>
	       <#list buyoutmemberList as buyoutmembers>
	       <tr align="center">
	       		<td></td>
			    <td style="padding:10px;" >
				    <a href="#" target="_blank">
				    	<img src="${buyoutmembers.logo_path?if_exists}" width="80" height="60"  style="border:1px solid #e1e2e3;" />    
				    </a>
			    </td>
			    <td >
				    <table>
				    <tr><td >简介：</td><td>${buyoutmembers.main_product?if_exists}</td></tr>
				    <tr><td >店铺名称：</td><td><a href="#" target="_blank">${buyoutmembers.cust_name?if_exists}</a></td></tr>
				    </table>
			    </td>
			    <td >
			     <a href="#" target=”_blank">加入收藏</a>
			     </td>
		    </tr>
	         </#list>
	       </table>
	        <div class="listbottom">
	        ${pageBar?if_exists}
	        </div>
	     </div>
	 </div>     
</div>

<div class="clear"></div>
</@s.form>
</body>
</html>
