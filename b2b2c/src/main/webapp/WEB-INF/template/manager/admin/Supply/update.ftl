<html>
  <head>
    <title>修改供应</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript" src="/manager/js/supply.js"></script> 
    <#include "/include/uploadInc.html">    
	<script type="text/javascript">
	  $(document).ready(function(){
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"supply");  
		 </#if>
         var unitvalue = $("#unit option:selected").val();
         if(unitvalue == "other"){
             $("#otherunit").css("display","");
         }         
	  });
	  
	   function addUnit(){
	     	 $("select[id^=unit]").each(function(){	
             if($(this).val()=="other"){ 
                 $("#otherunit").css("display","");
			}else{
			     $("#otherunit").css("display","none");
			}
        });
	  
	  }
	  
	</script> 
	<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
    </style>
  </head>
<body>
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 供应管理 > 供应列表 > 修改供应
   </div>
   <div class="tj_main_cont">   
   	<@s.form id="supply_update" action="/admin_Supply_update.action" method="post" validate="true">   	
        <table class="wwtable" cellspacing="1" cellpadding="8"  >          
           <tr>
             <td class="table_name" width=300px;>供应标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="title" name="supply.title" cssClass="txtinput"  maxlength="600"  cssStyle="width:500px;"/>
             	<@s.fielderror><@s.param>supply.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name" >供应类型:</td>
             <td>
               <@s.radio name="supply.supply_type" list="commparalist" listKey='para_value'  listValue='para_key'  check=true/>    	
               <@s.fielderror><@s.param>supply.supply_type</@s.param></@s.fielderror>            
             </td>
           </tr>
           <tr>
               <td class="table_name">所属分类<font color="red">*</font></td>
               <td>
                <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="supply.cat_attr" />
              		<a href="/admin_Supply_cate.action?cat_attr=0&supply.supply_id=${supply.supply_id?if_exists}">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=supply&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=supply&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>    
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>     
           <tr>
             <td class="table_name" >所在地区<font color="red">*</font></td>
             <td>
             	<table>
             		<tr>
             			<td class="tdbottom">
             				<div id="arealist"></div>
             			</td>
             			<td class="tdbottom">
             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
	              		</td>
	              	</tr>
	            </table>       
             </td>
           </tr>
          <tr>
             <td class="table_name" >产品图片:</td>
             <td valign="middle;">
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			   <@s.textfield name="supply.img_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(123);uploadOneImgMulti();</script>
             			</td>
             		</tr>
             	</table>     
             	<@s.fielderror><@s.param>supply.img_path</@s.param></@s.fielderror>       	            
	        </td>
	       </tr>   
           <tr>
             <td class="table_name">产品品牌:</td>
             <td>
             	<@s.textfield  name="supply.brand" cssClass="txtinput" maxlength="50"/>
      	         <@s.fielderror><@s.param>supply.brand</@s.param></@s.fielderror>      
             </td>
           </tr>
           <tr>
             <td class="table_name">产品型号:</td>
             <td>
             	<@s.textfield  name="supply.model" cssClass="txtinput" maxlength="50"/>
          	     <@s.fielderror><@s.param>supply.model</@s.param></@s.fielderror>             
             </td>
           </tr>
           <tr>
             <td class="table_name" >产品规格:</td>
             <td>
             	<@s.textfield  name="supply.standard" cssClass="txtinput" maxlength="50"/>
           	    <@s.fielderror><@s.param>supply.standard</@s.param></@s.fielderror>           
             </td>
           </tr>
           <tr>
              <td class="table_name" >详细说明:</td>
              <td >
             	<@s.textarea  name="supply.content" id="content" cssClass="txtinput"/>
             	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>   
				 <@s.fielderror><@s.param>supply.content</@s.param></@s.fielderror>           	            
              </td>
           </tr>           
           <tr>
             <td class="table_name" >是否支持在线订购:</td>
             <td >
             	<@s.radio name="supply.onlineorder" list=r"#{'0':'否','1':'是'}"/>
             	 <@s.fielderror><@s.param>supply.onlineorder</@s.param></@s.fielderror>   
             </td>
           </tr>
           
           <tr>
             <td class="table_name">交易条件:</td>
             <td>
                <table class="wwtable" cellspacing="1" cellpadding="8" style="margin:0px 0px 0px 0px; width:100%;">
                <tr><td class="zitd">计量单位<font color="red">*</font></td>
                <td>
                   <@s.select id="unit" name="supply.unit" list="unitList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" onchange="addUnit();"/>
                
                   <span id="otherunit" style="display:none;"><@s.textfield  name="other_unit" cssClass="zitxt"  maxlength="50"/></span>
                
                <@s.fielderror><@s.param>supply.unit</@s.param></@s.fielderror>
                </td></tr>
                <tr><td class="zitd">产品单价<font color="red">*</font></td>
                <td><@s.textfield  name="supply.price" id="price" cssClass="zitxt" maxlength="20" />元
                 <img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.price')}"/>"> 
                 <@s.fielderror><@s.param>supply.price</@s.param></@s.fielderror></td></tr>      
                <tr><td class="zitd">最小起订量<font color="red">*</font></td>
                <td><@s.textfield  name="supply.min_order" cssClass="zitxt"  maxlength="20" onkeyup="checkNum(this);"/>
                <@s.fielderror><@s.param>supply.min_order</@s.param></@s.fielderror></td></tr>  
                <tr><td class="zitd">供应总量<font color="red">*</font></td>
                <td><@s.textfield  name="supply.max_supply" cssClass="zitxt"  maxlength="20" />
                 <@s.fielderror><@s.param>supply.max_supply</@s.param></@s.fielderror></td></tr>            
                <tr><td class="zitd">发货天数<font color='red'>*</font></td>
                <td>自买家付款之日起<@s.textfield  name="supply.send_day" id="send_day" cssClass="datenum"  maxlength="20" onkeyup="checkNum(this);"/>天内发货
                 <@s.fielderror><@s.param>supply.send_day</@s.param></@s.fielderror></td></tr>  
                 
                 
                  <tr><td class="zitd">运费<font color="red">*</font></td>
                <td><@s.textfield  name="supply.shipfee" cssClass="zitxt" maxlength="20" />&nbsp;元
                 <img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.shipfee')}"/>"> 
                <@s.fielderror><@s.param>supply.shipfee</@s.param></@s.fielderror></td></tr>   
                 
                </table>       	            
             </td>
           </tr>    
           <tr>
             <td class="table_name" >过期时间<font color='red'>*</font></td>
             <td>
               <#if supply.end_date?if_exists?length lt 10>
               <@s.textfield cssClass="Wdate"   name="supply.end_date" onfocus="WdatePicker({readOnly:true})" />
             <#else>
             <@s.textfield cssClass="Wdate" value="${supply.end_date?if_exists[0..9]}"  name="supply.end_date" onfocus="WdatePicker({readOnly:true})" />         
             </#if>
             
             	<@s.fielderror><@s.param>supply.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name" >会员推荐:</td>
             <td>
         	 <@s.radio name="supply.mem_recom" list=r"#{'0':'否','1':'是'}" checked="true" />         	
         	  <@s.fielderror><@s.param>supply.mem_recom</@s.param></@s.fielderror>               
           </td>
           </tr>
            <tr>
             <td class="table_name" >是否推荐:</td>
             <td>
         	 <@s.radio name="supply.is_recom" list=r"#{'0':'否','1':'是'}" checked="true" />        
         	   <@s.fielderror><@s.param>supply.is_recom</@s.param></@s.fielderror>         
             </td>
           </tr>
           <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield  name="supply.clicknum" id="clicknum" cssClass="txtinput" maxlength="11" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>supply.clicknum</@s.param></@s.fielderror>	           	            
             </td>
           </tr>
           <tr>
             <td class="table_name" >内容收费:</td>
             <td>
             	<@s.textfield id="fare" name="supply.fare" cssClass="txtinput" maxlength="11" value="0" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>"> 
             	<@s.fielderror><@s.param>supply.fare</@s.param></@s.fielderror>		        	            
             </td>
           </tr>    
           <tr>
             <td class="table_name">信息状态:</td>
             <td>
                 <@s.radio name="supply.info_state" list=r"#{'1':'正常','3':'禁用'}"  checked="true" />  
                <@s.fielderror><@s.param>supply.info_state</@s.param></@s.fielderror>		              	   
            </td>
           </tr>   

         </table>   
	    <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nike_name_s"/>	       
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="supply.infoattr_id" />
	       <@s.hidden name="supply.supply_id" />
           <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置" style="cursor:pointer;"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" style="cursor:pointer;" 
	       onclick="linkToInfo('/admin_Supply_list.action','');" />              
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