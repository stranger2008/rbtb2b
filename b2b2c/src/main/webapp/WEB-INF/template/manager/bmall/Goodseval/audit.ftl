<html>
<head>
<title>我的评论</title>
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
<@s.form action="/bmall_Goodseval_auditState.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的交易</h2></div>
     <div class="base_infor">
       <h2 class="base_title">我的评价</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">商品名称：</td><td>
        	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
            </td></tr>
            
             <tr>
             <#if goodseval.is_two?if_exists=='0'>
             <td  class="firsttd">商品评级<font color='red'>*</font></td>
             <td>
             <@s.radio name="goodseval.g_eval" list=r"#{'1':'好评','0':'中评','-1':'差评'}"/>
             </td>
             <#else>
              <td  class="firsttd">商品评级：</td>
              <td align="center">
		      <#if goodseval.g_eval?if_exists=='1'>好评</#if>
		      <#if goodseval.g_eval?if_exists=='0'>中评</#if>
		      <#if goodseval.g_eval?if_exists=='-1'>差评</#if>
		      </td>
		     </#if>
             </tr>
             
            <@s.hidden name="goodseval.is_two"/>

            <tr><td  class="firsttd">评论内容：</td><td>
        	<@s.label name="goodseval.g_comment" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd">评论时间：</td><td>
        	<@s.label name="goodseval.eval_date" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd">回复内容：</td>
            <td>
        	<@s.label name="goodseval.explan_content" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20"/>
            </td>
            </tr>
 
            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>	
             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Goodseval_auditList.action','');"/>
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

