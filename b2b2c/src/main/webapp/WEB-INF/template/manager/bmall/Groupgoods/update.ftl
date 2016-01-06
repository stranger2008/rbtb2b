<html>
  <head>
    <title>修改团购商品</title>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script type="text/javascript"> 
	  $(document).ready(function(){ 
		 cate_select(${cfg_topcatid?if_exists},1,"goods");  		 
	  }); 
	</script>
	<#include "/include/uploadInc.html">
  </head>
  <body>
  	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">促销管理</a><span>></span><a href="#">团购管理</a><span>></span><a href="#">修改团购商品</a>
    </div>
<@s.form action="/bmall_Groupgoods_update.action" method="post" validate="true" >
<div class="rightside f_right">
	<div class="rpostion"><h2>修改团购商品</h2></div>
   <div class="base_infor">
		<div class="table_infor f_left" >
        <table>
       		   <script type="text/javascript">
					function select_group(){
						var goodsName=document.getElementById('ggname').value;
						window.open("/bmall_Goods_grouplist.action?name_s="+goodsName+"&ajaxRequestRandom="+Math.random());
					}	
				    function setgroup(id,name) {	
						 $("#ggid").val(id);
						 $("#ggname").val(name);
				     }
				</script>
				<#if groupgoods.info_state?if_exists=='2'>
		           <tr  id="reasonid">
		             <td class="firsttd"><h3 >未通过理由:</h3></td>
		             <td>
		             	<@s.hidden name="groupgoods.no_reason" />
		             	${groupgoods.no_reason?if_exists}	             		             	
		             </td>
		           </tr>
	           </#if>
		           <tr>
		             <td class="firsttd" >商品名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="ggname" name="groupname" disabled='true' cssClass="winput" cssStyle="width:300px;" />
		             	<@s.hidden id="ggid" name="groupgoods.goods_id"/>
		             	<input type="button" value="搜索相关商品" onclick="select_group();"/><br/>
		             	<@s.fielderror><@s.param>groupgoods.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">团购标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.group_title" cssClass="winput" cssStyle="width:300px" maxLength="200"/>
		             	<@s.fielderror><@s.param>groupgoods.group_title</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">所属分类<font color='red'>*</font></td>
		             <td>
		             	<table>
		             		<tr>
		             			<td class="tdbottom">
		             				<div id="divlist"></div>
		             			</td>
		             			<td class="tdbottom">
		             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
			              		</td>
			              	</tr>
			            </table>   
		             	
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">保证金<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.bond" cssClass="winput" maxLength="13"/>
		             	<@s.fielderror><@s.param>groupgoods.bond</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">团购价格<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.group_price" cssClass="winput" maxLength="13"/>
		             	<@s.fielderror><@s.param>groupgoods.group_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">同一会员购买的最大数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.cust_maxbuy" cssClass="winput" onkeyup="checkNum(this);" />
		             	<font  font-size="13">(0则不限制)</font>
		             	<@s.fielderror><@s.param>groupgoods.cust_maxbuy</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">最少购买数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.min_buy" cssClass="winput"  onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>groupgoods.min_buy</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">最高购买数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.max_buy" cssClass="winput"  onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>groupgoods.max_buy</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">订购商品数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.buy_num" cssClass="winput"  onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>groupgoods.buy_num</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">当前购买人数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.already_buy" cssClass="winput"  onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>groupgoods.already_buy</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">最少购买人数达到时间<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.reach_time" value="${groupgoods.reach_time?if_exists[0..9]}" cssClass="Wdate" cssStyle="width:182px;" onfocus="WdatePicker({readOnly:true})"/>
		             	<@s.fielderror><@s.param>groupgoods.reach_time</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">卖家地址<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.saler_address" cssClass="winput" maxLength="100" cssStyle="width:300px;"/>
		             	<@s.fielderror><@s.param>groupgoods.saler_address</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">联系方式<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.contact_way" cssClass="winput" maxLength="20"/>
		             	<@s.fielderror><@s.param>groupgoods.contact_way</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           	           		           <tr>
		             <td class="firsttd">购商品送积分数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.give_inter" cssClass="winput" maxLength="20"/>
		             	<@s.fielderror><@s.param>groupgoods.give_inter</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">购商品送返现金数<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.give_money" cssClass="winput" maxLength="20"/>
		             	<@s.fielderror><@s.param>groupgoods.give_money</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="firsttd">开始日期<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="txtstartDate" name="groupgoods.start_date"  value="${groupgoods.start_date?if_exists[0..9]}" type="text" cssStyle="width:180px;" 
						cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 		             	
		             	<@s.fielderror><@s.param>groupgoods.start_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">结束日期<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="txtendDate" name="groupgoods.end_date" value="${groupgoods.end_date?if_exists[0..9]}" cssStyle="width:180px;"  type="text" 
						cssClass="Wdate"  readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		             	<@s.fielderror><@s.param>groupgoods.end_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           	           
		           <tr>
		             <td class="firsttd">团购图片:</td>
		             <td>
		             <table border="0" cellpadding="0" cellspacing="0">
		             		<tr>
		             			<td style="padding:0px;">
		             			    <div id="fileQueue"></div>
			              			  <@s.textfield name="groupgoods.group_img" id="uploadresult" cssStyle="width:300px;" maxLength="1000"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			              			<script>uploadOneImgMulti();</script>
		             			</td>
		             		</tr>
		              </table>
					  <@s.fielderror><@s.param>groupgoods.group_img</@s.param></@s.fielderror>
					  </td>
		           </tr>
		           
		           <tr>
		             <td class="firsttd">详细描述:</td>
		             <td>
			             <div style="width:630px;">		
							<@s.textarea name="groupgoods.group_desc" id="content" cssClass="txtinput"   />
							<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
							<script type="text/javascript">
						     CKEDITOR.replace( 'content');  
							</script>
					     </div>
		             	<@s.fielderror><@s.param>groupgoods.group_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>

		           <tr>
		             <td class="firsttd">支持七天退款:</td>
		             <td>
		             	 <@s.radio name="groupgoods.is_seven" list=r"#{'0':'是','1':'否'}"  />  
		             	<@s.fielderror><@s.param>groupgoods.is_seven</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">支持过期退款:</td>
		             <td>
		             	 <@s.radio name="groupgoods.is_overdue" list=r"#{'0':'是','1':'否'}"  />  
		             	<@s.fielderror><@s.param>groupgoods.is_overdue</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="firsttd">支持配送:</td>
		             <td>
		             	<@s.radio name="groupgoods.is_ship" list=r"#{'0':'是','1':'否'}"  />  
		             	<@s.fielderror><@s.param>groupgoods.is_ship</@s.param></@s.fielderror>
		             </td>
		           </tr>

		           <tr>
		             <td class="firsttd">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.sort_no" cssClass="winput" onkeyup="checkNum(this);" value="0"/>
		             	<@s.fielderror><@s.param>groupgoods.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>   
		           	           
		           <tr>
		           		<td colspan="2" align="center">    
		                      <@s.submit value="提  交" cssClass="submitbut"/>
	      				      <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Groupgoods_list.action','');"/>
	      				</td>
		           </tr>
        </table>
           </div>
   <div style="text-align:center;">
   		   <@s.hidden name="groupgoods.group_id"/>    
	       <@s.hidden name="groupgoods.info_state" value="0"/>    
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value"/>
		   <!--所属分类插件隐藏字段结束区域-->
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

