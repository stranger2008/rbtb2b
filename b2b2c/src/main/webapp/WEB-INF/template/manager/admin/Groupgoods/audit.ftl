<html>
  <head>
    <title>审核团购商品</title>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script type="text/javascript"> 
	  $(document).ready(function(){ 
		 cate_select(${cfg_topcatid?if_exists},1,"goods"); 
		 disabledCss(); 		 
	  }); 
	</script>
	<#include "/include/uploadInc.html">
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 促销管理 > 审核团购
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Groupgoods_auditState.action" method="post" validate="true"   onsubmit="return noreasron('groupgoods.info_state','noreasonvalue','2');">
        <table class="wwtable" cellspacing="1" cellpadding="8">
		           <tr>
		             <td class="table_name" width="300px;">商品名称<font color='red'>*</font></td>
		             <td>
		             	${groupname?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购标题<font color='red'>*</font></td>
		             <td>
		             		${groupgoods.group_title?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		             <td>
		             	<table>
		             		<tr>
		             			<td class="tdbottom">
		             				<div id="divlist"></div>
		             			</td>
		             			<td class="tdbottom">
		             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
			              			<a href="admin_Category_list.action?type=goods&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
			              		</td>
			              	</tr>
			            </table>   
		             	
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保证金<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.bond?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购价格<font color='red'>*</font></td>
		             <td>
		             ${groupgoods.group_price?if_exists}

	             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">同一会员购买的最大数<font color='red'>*</font></td>
		             <td>
		              ${groupgoods.cust_maxbuy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最少购买数<font color='red'>*</font></td>
		             <td>
		               ${groupgoods.min_buy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最高购买数<font color='red'>*</font></td>
		             <td>
		              ${groupgoods.max_buy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">订购商品数<font color='red'>*</font></td>
		             <td>
			             ${groupgoods.buy_num?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">当前购买人数<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.already_buy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最少购买人数达到的时间<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.reach_time?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">卖家地址<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.saler_address?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">联系方式:</td>
		             <td>
			             ${groupgoods.contact_way?if_exists}
		             </td>
		           </tr>
	           	    <tr>
		             <td class="table_name">购商品送积分数<font color='red'>*</font></td>
		             <td>
		            	 ${groupgoods.give_inter?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">购商品送返现金数<font color='red'>*</font></td>
		             <td>
			              ${groupgoods.give_money?if_exists}

		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">开始日期<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.start_date?if_exists[0..9]}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">结束日期<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.end_date?if_exists[0..9]}
		             </td>
		           </tr>
		           	           
		           <tr>
		             <td class="table_name">团购图片:</td>
		             <td>
					 <div id="showcimg">
					 <#if ( (goods.goods_img?if_exists?index_of(s)) > -1 ) > 
						 <#list goods.goods_img?split(",") as s>
			              	<img src="${s}" width="60" height="60">
			             </#list>
			          <#else>
			          	    <img src="${goods.goods_img?if_exists}" width="60" height="60">	
			          </#if>
			          
				   </div>
					  </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">详细描述:</td>
		             <td>
		             	 ${groupgoods.group_desc?if_exists}
		             </td>
		           </tr>

	           
		           <tr>
		             <td class="table_name">支持七天退款:</td>
		             <td>
		             	 <@s.radio name="groupgoods.is_seven" list=r"#{'0':'是','1':'否'}" disabled="true"/>  
		             	<@s.fielderror><@s.param>groupgoods.is_seven</@s.param></@s.fielderror>		             	
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">支持过期退款:</td>
		             <td>
		             	 <@s.radio name="groupgoods.is_overdue" list=r"#{'0':'是','1':'否'}" disabled="true"/>  
		             	<@s.fielderror><@s.param>groupgoods.is_overdue</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">支持配送:</td>
		             <td>
		             	<@s.radio name="groupgoods.is_ship" list=r"#{'0':'是','1':'否'}" disabled="true"/>  
		             	<@s.fielderror><@s.param>groupgoods.is_ship</@s.param></@s.fielderror>
		             </td>
		           </tr>

		        <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	${groupgoods.sort_no?if_exists}
		             </td>
		     	</tr>
        
		      <tr>
		     <td class="table_name" >审核状态:</td>
		             <td>
		                  <@s.radio name="groupgoods.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}"  onclick="getRadioValue('groupgoods.info_state','reasonid','noreasonvalue','2');"/>    
		                   <@s.fielderror><@s.param>goods.info_state</@s.param></@s.fielderror>                	            
		             </td>
		           </tr>          
		     <#if groupgoods.info_state?if_exists=='2'>
	             <tr  id="reasonid">
	             <td class="table_name">拒绝理由<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="groupgoods.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
	             	<@s.fielderror><@s.param>groupgoods.no_reason</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           <#else>
	            <tr  id="reasonid" style="display:none;">
	             <td class="table_name">拒绝理由<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="groupgoods.no_reason" cssClass="txtinput" cssStyle="width:600px" id="noreasonvalue"  maxLength="100"/>
	             	<@s.fielderror><@s.param>groupgoods.no_reason</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           </#if>
		           
		           
		               
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       <@s.hidden name="groupgoods.group_id"/>
	       ${listSearchHiddenField?if_exists}
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value"/>
		   <!--所属分类插件隐藏字段结束区域-->
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Groupgoods_auditList.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
</body>
</html>
