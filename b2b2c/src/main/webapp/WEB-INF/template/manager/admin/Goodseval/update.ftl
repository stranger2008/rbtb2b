<html>
  <head>
    <title>修改记录商品评价表信息</title>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 商品管理 > 商品评价
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Goodseval_update.action" method="post" validate="true" >
        <table class="wwtable" cellspacing="1" cellpadding="8">
       		   
	           
		           <tr>
		             <td class="table_name">商品名称:</td>
		             <td>
		             	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">商品评级:</td>
		             <td>
		             	<@s.radio name="goodseval.g_eval" list=r"#{'1':'好评','0':'中评','-1':'差评'}"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">评论内容:</td>
		             <td>
		             	<@s.label name="goodseval.g_comment" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">评论时间:</td>
		             <td>
		             	<@s.label name="goodseval.eval_date" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>

	           
		           <tr>
		             <td class="table_name">回复内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="goodseval.explan_content" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20" onkeyup="set_textarea_length(this,600);"/>
		             	<@s.fielderror><@s.param>goodseval.explan_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否可用<font color='red'>*</font></td>
		             <td>
		             <@s.radio name="goodseval.is_enable" list=r"#{'0':'有效','1':'无效'}"/>
		             </td>
		           </tr>
	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.hidden name="goodseval.trade_id"/>
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

