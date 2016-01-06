<html>
  <head>
    <title>物流模板设置</title>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript" src="/include/js/admin/shopconfig.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 
	   	 //所属地区的回选
	   	 <#if start_area_name==''>
         	 area_select("${cfg_topareaid?if_exists}","s_area");
         </#if>
         <#list sendmodeList as smode>
        	 area_select("${cfg_topareaid?if_exists}","s_area_${smode.smode_id?if_exists}");
         </#list>
	  });
	</script>
	<style type="text/css">
		.area_li{padding:4px;padding-left:5px;float:left;border:1px solid #e1e2e3;margin-right:10px;margin-top:3px;}	
		.area_li img{margin:-3px 2px 0px 5px;}
	</style>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 系统设置 > 物流模板设置
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Shopconfig_set.action" method="post" id="sendset">

        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name" width="256">发货地址<font color='red'>*</font></td></td>
             <td>
                <div id="startdiv">
	             	<span>${start_area_name?if_exists}</span>&nbsp;
	             	<#if start_area_name==''>
	             		<div id="s_area"></div>
	             	</#if>
	             </div>
             	<@s.fielderror><@s.param>aboutus.title</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">物流方式及收货地址区域串<font color='red'>*</font></td></td>
             <td>
             	 <table class="wwtable" cellspacing="1" cellpadding="8">
             	  	 <#list sendmodeList as smode>
	             	  	<tr>
	             	  		<td width="80px;" align="right">${smode.smode_name?if_exists}:</td>
	             	  		<td>
	             	  			<input class="sendmodetype" type="hidden" value="${smode.smode_id?if_exists}"/>
             	  				<div id="s_area_${smode.smode_id?if_exists}" style="float:left;">
             	  				</div>
             	  				<span style="float:left;">
             	  					<a onclick="mode_add_area('${smode.smode_id?if_exists}');" ><img style="vertical-align:middle;" src="/include/images/add.png" title="新增配送方式到达地区"/></a>
             	  					<a onclick="mode_copy_area('${smode.smode_id?if_exists}');" ><img style="vertical-align:middle;" src="/include/images/copy.png" title="复制地区到其它配送方式"/></a>
             	  				</span>
             	  				<div class="clear"></div>
             	  				<ul id="show_area_set_${smode.smode_id?if_exists}" class="cal_area">    
             	  				<#assign mapos = "${shopconfig.reach_area?if_exists}"?index_of(':')>   
             	  				<#if ((mapos-1)>-1)>
             	  					    <#list shopconfig.reach_area?split(":") as m> 	
             	  					        <#assign startpos = "${m?if_exists}"?index_of('|')>
           	  					     	  	    <#if ((startpos-1)>-1)>
           	  					     	  	    	<#assign modval =(m)[(0)..(startpos-1)]>	 
           	  					     	  	    	<#assign len =(m?length)>
           	  					     	  	    	<#assign modareastr =(m)[(startpos+1)..(len-1)]>	
           	  					     	  	    	<#if modval==smode.smode_id>
           	  					     	  	    		<#list modareastr?split("=") as v>
           	  					     	  	    			<#if v!=''>
		           	  					     	  	    		<li class="area_li">
		           	  					     	  	    			<input type="hidden" name="area_set_val" value="no1qx5cs76,c1r38vvloe,vyi1do8bl6"><span>${v?if_exists}</span>
		           	  					     	  	    			<a onclick="delarea(this);"><img src="/include/images/admin/delete.png" style="vertical-align:middle;"></a>           	  					     	  	    		
		           	  					     	  	    		</li>
	           	  					     	  	    		 </#if>
           	  					     	  	    		 </#list>
           	  					     	  	    	</#if>           	  					     	  	    	      
             	  					     	  	</#if>
             	  					    </#list> 	
             	  					</#if>      				
             	  				</ul>	             	  			
	             	  		</td>
	             	  	 </tr>     
             	  	  </#list>
             	  </table>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">物流运费类型<font color='red'>*</font></td></td>
             <td>
             	<@s.radio name="mode_type" list=r"#{'0':'免运费','1':'手动设置运费','2':'自动设置运费'}" value="${mode_type?if_exists}"/>
             </td>
           </tr>

         </table>
         
	     <div class="buttom">
		       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>		    
         	   <@s.hidden name="start_area_str" id="start_area_str"  value="${shopconfig.start_area?if_exists}"/>   
		       <@s.hidden name="end_area_str" id="end_area_str"/>
		       <@s.hidden name="shopconfig.shop_id"/>
		       <@s.hidden id="cfg_topid" value="${cfg_topareaid?if_exists}"/>
		       ${listSearchHiddenField?if_exists}
		        <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Shopconfig_list.action','');"/>
		       <!--所属地区插件隐藏字段开始区域-->
			   		<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			   <!--所属地区插件隐藏字段结束区域-->    
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>

  
</body>
</html>