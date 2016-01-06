<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>评价</title>
<link rel="StyleSheet" href="/templets/bmall/css/order.css" type="text/css" />
</head>
<body>
<@s.form action='/bmall_Goodsorder_comfirmeval.action' id='evalsubmit'>
<div class="rightside f_right">
	<div class='evaltitle'>
		<table width='100%'>
		<tr>
			<td width='20' style='pading-left:5px;'>店铺信息</td>
			<td width='90'>
				<div class='evaltitlediv'>				
					<img src="/include/images/bmall/logo_06.gif"/>				
				</div>
			</td>
			<td>
				<table>
					<tr><td><a href="#">${saleMember.cust_name?if_exists}</a></td></tr>
					<tr><td><span>被评卖家：</span>${saleMember.cust_name?if_exists}</td></tr>
					<tr><td><span>宝贝与描述相符：</span>
					  <#list scoreList?first as score>
						  <#if score.desc_score?length lt 4>
			                 	${score.desc_score?if_exists?string.number}
			              <#else>
			                 	${score.desc_score[0..3]}
			              </#if>
		              </#list>
					</td></tr>
				</table>
			</td>
		</tr>
		</table>
	</div>
	
	<div class='itemlist'>
		<div class='itemb'>
	  		<h3>商品评分<h3> 
	  	</div>
	  	<div class='listhd'>
	  		 <table width="100%" id="evaldiv">
	  		 	<tr><th width="46%">商品详细</th><th>宝贝与描述相符(打分匿名)</th></tr>
	  		 	<#list detailList as detail>
		  		 	<tr>
			  		 	<td valign='top' style='padding-left:10px;'>
								<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
					      		   <table>
					      		   <tr>
						      		   <td>
							      		  	 <div class='gs_img'>
							      				<img src="${(detail.img_path)?if_exists}" width='60' height='60'>
							      			 </div>
						      			</td>
						      		    <td class='gname' style="text-align:left;">
						      		    	<a target='_blank' href="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
								      			${(detail.goods_name)?if_exists}
								      		</a>
						      		    </td>
					      		   </tr>
					      		   </table>
						      	</a>
						</td>
			  		 	<td style='padding-left:16px;'>
				  		 	<@s.hidden  name="goodseval.goods_id"  value='${detail.goods_id?if_exists}'/>
				  		 	<@s.hidden  name="goodseval.g_eval" id='goodsgeval'  value=''/>
				  		 	<@s.hidden  name="goodseval.g_comment" id='g_comment'  value=''/>
				  		 	<@s.radio name="score${detail.goods_id?if_exists}" class='evalval' list=r"#{'1':'好评','0':'中评','-1':'差评'}" value="1"/>
				  		 	<div>
				  		 		<@s.textarea  cssClass='gment' cssStyle='width:400px;height:150px;'/>			  		 	
				  		 	</div>
			  		 	</td>
		  		 	</tr>
		  		 </#list>
	  		 	
	  		 	
	  		 </table>
	  		 
	  		 

	  		 
	  		 
	  	</div>
	  	<div class='movescore'>
			<div class='movetitle'>
				<h2>店铺动态评分</h2>			
			</div>
			<div class='movedescri'>
			
				<ul>
					<li><span>宝贝与描述相符：</span> <span><@s.radio name="sellerscore.desc_score"  list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}"  value="5"/></span></li>
					<li><span>卖家的服务态度：</span> <span><@s.radio name="sellerscore.service_score"  list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}" value="5"/></span> </li>
					<li><span>卖家发货的速度：</span> <span><@s.radio name="sellerscore.delivery_score"   list=r"#{'1':'1分','2':'2分','3':'3分','4':'4分','5':'5分'}"  value="5"/></span></li>
				</ul>
					<@s.hidden name='sellerscore.get_cust_id' value="${goodsorder.sale_cust_id?if_exists}"/>
					<@s.hidden name='oid'/>
			</div>			 	  	
		</div>	  		 

	  	
	  	<div class='btneval'>
			<input type='button' value="提交评论" onclick="doeval();"/>
				<script type="text/javascript">
					function doeval(){
					     var evalval="";
					     var evalment="";
					     
						 $("#evaldiv").find("input:radio").each(function(){           				    	  	  
   				    	        if(this.checked){
   				    	        	evalval+=(this.value+" ,");				    	        	
   				    	        } 
           				 })
           				 $("#evaldiv").find(".gment").each(function(){       
   				    	      evalment+=($(this).val()+" ##########");			    	        	
           				 })
           				 $("#goodsgeval").val(evalval);
           				 $("#g_comment").val(evalment);
           				 
           				 $("#evalsubmit").submit();
					}
				</script>
			</script>	
		</div>
	</div>
	

	

</div>
</@s.form> 
</body>
</html>
