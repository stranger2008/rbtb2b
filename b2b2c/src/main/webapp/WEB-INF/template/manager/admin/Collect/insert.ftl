<html>
  <head>
    <title>添加收藏</title>
    <script>
    function geturlinfo()
    {
       var page_url = window.location.href;
       document.getElementById("urlid").value=page_url;
    }
    </script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 会员相关 > 商机收藏 > 添加收藏
   </div>
   <div class="tj_main_cont">
   	<a onclick="showSearch(this,'searchDiv');">收藏</a> 
   </div>
</div>

<div class="clear"></div>

 <!--搜索区域开始-->
    <div class="divceng" style="display:none;" id="searchDiv">
    <@s.form action="/admin_Collect_inserts.action" method="post" validate="true">
    <@s.hidden name="page_url" id="urlid"/>
	<table align="left">
	<tr>
		<td align="right">收藏标题:</td>
		<td><@s.textfield name="collect.title"/><@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror></td>
	</tr>
	 <tr>
         <td class="table_name">收藏分类:</td>
           <td>
             <@s.select name="collect.cat_id" list=r"#{'1':'商机','2':'合作','3':'投资'}" />  
             <@s.fielderror><@s.param>collect.cat_id</@s.param></@s.fielderror>
           </td>
         </tr>
	<tr>
		<td align="right">备注:</td>
		<td><@s.textarea name="collect.remark" cssStyle="width:200px;height:100px" 
		 onkeyup="set_textarea_length(this,100);"/>/>
		 <@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
		 </td>
	</tr>
	<tr>
		 <td><@s.submit value="收藏" onclick="geturlinfo();"/></td>
	     <td><input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/></td>
	</tr>
	</table>
	 </@s.form>
    </div>
    <!--搜索区域结束-->

  </body>
</html>