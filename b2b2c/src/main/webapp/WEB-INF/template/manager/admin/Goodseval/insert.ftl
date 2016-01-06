<html>
  <head>
    <title>添加记录商品评价表信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品评价
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodseval_insert.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">

		           <tr>
		             <td class="table_name">商品ID号<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodseval.goods_id" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>goodseval.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="table_name">商品评级<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="goodseval.g_eval" list=r"#{'1':'好评','0':'中评','-1':'差评'}" value="1"/>
		             	<@s.fielderror><@s.param>goodseval.g_eval</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">评价内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="goodseval.g_comment" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20" onkeyup="set_textarea_length(this,600);"/>
		             	<@s.fielderror><@s.param>goodseval.g_comment</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodseval_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

