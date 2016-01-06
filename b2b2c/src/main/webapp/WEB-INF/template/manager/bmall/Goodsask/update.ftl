<html>
<head>
<title>回复咨询</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
 $(document).ready(function(){
     var sell=$("#c_type").find("option:selected").text();
     $("#c_value").html(sell);
 });
</script>
</head>
<body>
<div class="postion">
 		<a href="#">我是卖家</a><span>></span><a href="#">客服服务</a><span>></span><a href="#">买家咨询</a><span>></span><a href="#">回复咨询</a>
	</div>
<@s.form action="/bmall_Goodsask_update.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>客服服务</h2></div>
     <div class="base_infor">
       <h2 class="base_title">回复咨询</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">商店名称：</td><td>
        	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
            </td></tr>

            <tr>
             <td  class="firsttd">咨询类型：</td>
             <td>
             	<div id="c_value"></div>
             	<@s.select id="c_type" name="goodsask.c_type" list="commparaList" listValue="para_key" listKey="para_value" cssStyle="display:none;"/>
             </td>
            </tr>
            
            <tr><td  class="firsttd">咨询内容：</td><td>
        	<@s.label name="goodsask.c_content" cssStyle="width:400px;height:100px" cssClass="txtinput" maxLength="20"/>
            </td></tr>
            
            <tr><td  class="firsttd">提问时间：</td><td>
        	<@s.label name="goodsask.c_date.substring(0,19)" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd" valign="top">回复内容<font color='red'>*</font></td><td>
        	<@s.textarea name="goodsask.re_content" cssStyle="width:500px;height:100px" cssClass="txtinput" />
            </td></tr>
            
            <tr><td  class="firsttd">回复时间：</td><td>
        	<@s.label name="goodsask.re_date.substring(0,19)" cssClass="txtinput" />
            </td></tr>  
       
            <tr>
            <td colspan="2" align="center">
            	<@s.submit value="提  交" cssClass="submitbut"/>
             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Goodsask_list.action','');"/>
             	<@s.hidden name="goodsask.trade_id"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

