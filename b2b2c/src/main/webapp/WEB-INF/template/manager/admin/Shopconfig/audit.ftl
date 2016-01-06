<html>
  <head>
    <title>审核店铺设置</title>
    <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	  <script type="text/javascript">
	  $(document).ready(function(){    
         //图片展示
         firstaddimges("mypicid","addimg","100","");
         //图片展示
         firstaddimges("mypicid1","addimg1","700","");
         //图片展示
         firstaddimges("mypicid2","addimg2","600","");
  	  });
	</script> 
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 > 审核店铺设置
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Shopconfig_auditstate.action" method="post" validate="true" onsubmit="return noreasron('shopconfig.info_state','noreasonvalue','2');">
        <table class="wwtable" cellspacing="1" cellpadding="8">
	           
		           <tr>
		             <td class="table_name" style="width:300px;">会员名称</td>
		             <td>
		             	<@s.label name="cust_name" cssClass="txtinput"  />
		             	 <@s.hidden name="shopconfig.cust_id" /> 
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">商店名称</td>
		             <td>
		                <@s.label name="shopconfig.shop_name" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">商店logo</td>
		             <td>
		              <@s.hidden name="shopconfig.shop_logo" id="mypicid"/>   
		               <div id="addimg">
		               </div>
		             </td>
		           </tr>
	           
	             <tr>
		             <td class="table_name">banner图片</td>
		             <td>
		              <@s.hidden name="shopconfig.banner_image" id="mypicid1"/>   
		               <div id="addimg1">
		               </div>
		             </td>
		           </tr>
		           
		           
		            <tr>
		             <td class="table_name">导航下方广告图片</td>
		             <td>
		              <@s.hidden name="shopconfig.adv_image" id="mypicid2"/>   
		               <div id="addimg2">
		               </div>
		             </td>
		           </tr>
	           
	           
	           
	           
	           
	           
		           <tr>
		             <td class="table_name">商店网址</td>
		             <td>
		              <#if shopconfig.shop_site?if_exists!=''>
		                <a href="${shopconfig.shop_site}" target="_blank">${shopconfig.shop_site}</a>
		             </#if>
		              <@s.hidden name="shopconfig.shop_site" />
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">经营范围</td>
		             <td>
		             <@s.label name="shopconfig.busi_range" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">经营模式</td>
		             <td>
		             <@s.label name="bus_model" cssClass="txtinput"  />
		             <@s.hidden name="shopconfig.busi_mode" /> 
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">联系人</td>
		             <td>
		             <@s.label name="shopconfig.contant_man" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">email</td>
		             <td>
		             <@s.label name="shopconfig.email" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">QQ</td>
		             <td>
		             <@s.label name="shopconfig.qq" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">MSN</td>
		             <td>
		             <@s.label name="shopconfig.msn" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">淘宝旺旺</td>
		             <td>
		             <@s.label name="shopconfig.alliwang" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">手机</td>
		             <td>
		             <@s.label name="shopconfig.mobile" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">固定电话</td>
		             <td>
		             <@s.label name="shopconfig.phone" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所在地区</td>
		             <td>
		             <@s.label name="area_name" cssClass="txtinput"  />
		              <@s.hidden name="shopconfig.area_attr" /> 
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">详细地址</td>
		             <td>
		             <@s.label name="shopconfig.address" cssClass="txtinput"  />
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否暂时关闭网站</td>
		             <td>
		             <#if shopconfig.is_colse?if_exists=='0'>
		                是
		             <#else>
		                否
		             </#if>
		             <@s.hidden name="shopconfig.is_colse" />
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">商店简介</td>
		             <td>
			             <div style="padding:10px 200px 10px 0">
	                        ${shopconfig.shop_intro?if_exists}
	             	     </div>
	             	     <@s.hidden name="shopconfig.shop_intro" />
		             </td>
		           </tr>
		           
		            <tr>
		             <td class="table_name">审核状态<font color='red'>*</font></td>
		             <td>
		              <@s.radio name="shopconfig.info_state" list=r"#{'1':'审核通过','2':'审核未通过'}" onclick="getRadioValue('member.info_state','state','noreason','2');" /> 
		             	<@s.fielderror><@s.param>shopconfig.info_state</@s.param></@s.fielderror>
		             </td>
		           </tr>

	           
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       <@s.hidden name="shopconfig.shop_id" />    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="审核" />
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Shopconfig_auditList.action','');"/>
	       <br/>
	       <br/>
	       <br/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>

