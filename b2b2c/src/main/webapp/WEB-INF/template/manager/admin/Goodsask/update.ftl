<html>
  <head>
    <title>修改记录商品咨询信息</title>
    <script type="text/javascript">
	  $(document).ready(function(){ 
		  var selvalue=$("select[name='goodsask.c_type']").find('option:selected').text(); 
          $("#idvalue").html(selvalue);
		  });
    </script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品咨询
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodsask_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
        
		           <tr>
		             <td class="table_name">商品名称:</td>
		             <td>
		             	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">咨询类型<font color='red'>*</font></td>
		             <td style="display:none;">
		                <@s.select name="goodsask.c_type" list="commparaList" listValue="para_key" listKey="para_value"/>
		             	<@s.fielderror><@s.param>goodsask.c_type</@s.param></@s.fielderror>
		             </td>
		             <td ><span id="idvalue"></span></td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">咨询内容<font color='red'>*</font></td>
		             <td>
		             	<@s.label name="goodsask.c_content" cssStyle="width:400px;height:100px" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsask.c_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">提问时间:</td>
		             <td>
		             	<@s.label name="goodsask.c_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodsask.c_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">回复内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="goodsask.re_content" cssStyle="width:400px;height:100px" cssClass="txtinput" maxLength="20" onkeyup="set_textarea_length(this,600);"/>
		             	<@s.fielderror><@s.param>goodsask.re_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否有效:</td>
		             <td>
		                <@s.radio name="goodsask.is_enable" list=r"#{'0':'有效','1':'无效'}"/>
		             	<@s.fielderror><@s.param>goodsask.is_enable</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="goodsask.trade_id"/>
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsask_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>


