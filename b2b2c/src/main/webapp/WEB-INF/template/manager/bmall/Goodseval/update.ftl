<html>
<head>
<title>评价回复</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
 $(document).ready(function(){
     var sell=$("#c_type").find("option:selected").text();
     $("#c_value").html(sell);
     var s= $("input[name=goodseval.g_eval][checked]").val();
     if(s=='1'){
     $("#g_value").html('好评');
     }else if(s=='0'){
     $("#g_value").html('中评');
     }else{
     $("#g_value").html('差评');
     }
 });

</script>
</head>
<body>
	<div class="postion">
 	<a href="#">我是卖家</a><span>></span><a href="#">交易管理</a><span>></span><a href="#">评价管理</a><span>></span><a href="#">评价回复</a>
	</div>
<@s.form action="/bmall_Goodseval_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>交易管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">评价回复</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">商品名称:</td><td>
        	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
            </td></tr>
            
            <tr>
             <td  class="firsttd">商品评级:</td>
             <td style="display:none;">
             <@s.radio id="g_eval" name="goodseval.g_eval" list=r"#{'1':'好评','0':'中评','-1':'差评'}" />
             </td>
             <td><span id="g_value"></td>
            </tr>  
            <tr><td  class="firsttd">评论内容:</td><td>
        	<@s.label name="goodseval.g_comment" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd">评论时间:</td><td>
        	<@s.label name="goodseval.eval_date" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            

            <tr><td  class="firsttd">回复内容<font color='red'>*</font></td>
            <td>
        	<@s.textarea name="goodseval.explan_content" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20"/>
		    <@s.fielderror><@s.param>goodseval.explan_content</@s.param></@s.fielderror>
            </td>
            </tr>
            
            <tr><td  class="firsttd">状态：</td><td>
        	<@s.radio name="goodseval.is_enable" list=r"#{'0':'有效','1':'无效'}" value="0"/>
            </td></tr> 
            
            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Goodseval_list.action','');"/>
             	<@s.hidden name="goodseval.trade_id"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

