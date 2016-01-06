html>
  <head>
    <title>更新索引</title>
	<script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/include/js/admin/channel.js"></script>
  </head>
<body >
<div class="tj_main f_left">
 <div class="pageLocation">
 	   当前位置:网站管理 > 网站更新 > 更新首页
 </div>
   <div class="tj_main_cont">
   
   	  <table width="100%" cellspacing="2" cellpadding="2">
   	  
   	  <tr>
	   	  <td align="center" height="10">
	   	  <div id="modletype">请选择所属的模块: 
	   	  		<@s.select id="mod"  list="commparalist" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" />
	   	  </div>
	   	  </td>
   	  <tr>
   	  
   	  <tr>
	   	  <td align="center" height="60" style="border:1px solid #cecece;">
	   	  		<input type="button" onclick="doAddIndex();" value="增量更新索引"/>
	   	  		<input type="button" onclick="doAllIndex();" value="全量更新索引"/>
	   	  		<input type="button" onclick="doAllModelIndex();" value="更新全部模块索引"/>
	   	  		<label id="msg" ></label>
	   	  </td>
   	  <tr>
   
   	  <table>  
   	  
   </div>
</div>
</div>
<div class="clear"></div>      
  </body>
</html>