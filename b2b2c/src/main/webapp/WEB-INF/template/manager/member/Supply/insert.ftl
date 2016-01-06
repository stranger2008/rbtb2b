<html>
<head>
<title>添加供应</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 
	  	 <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"supply");  
		 </#if>
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	  //计量单位
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
    </style>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Supply_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>供应管理>添加供应</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
   <tr>
     <td width="17%" valign="middle" class="left_td">供应标题<font color='red'>*</font></td>
     <td>
     	<@s.textfield id="title" name="supply.title" cssClass="txtinput"  maxlength="600" size="70"/>
     	<@s.fielderror><@s.param>supply.title</@s.param></@s.fielderror>
     </td>
   </tr>
   <tr>
     <td valign="middle" class="left_td" >供应类型:</td>
     <td>
       <@s.radio name="supply.supply_type" list="commparalist" listKey='para_value'  listValue='para_key' value="1" check="true" />    	 
       <@s.fielderror><@s.param>supply.supply_type</@s.param></@s.fielderror>           
     </td>
   </tr>
   <tr>
     <td valign="middle" class="left_td" >自定义产品分类:</td>
     <td>
     	<@s.select name="supply.self_cat_id" list="selfCatList" listValue="cat_name" listKey="cat_id" headerKey="0" headerValue="请选择"  cssStyle="width:120px;"/> 
     	    <@s.fielderror><@s.param>supply.self_cat_id</@s.param></@s.fielderror>              	            
     </td>
   </tr>
   <tr>
    <tr>
    <td valign="middle" class="left_td" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="supply.cat_attr" />
	                 <a href="/member_Supply_cate.action?cat_attr=0">[重新选择]</a>
	            <#else>
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
	            </#if>        	            
             </td>
           </tr>          
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if>     
   <tr style="display:none;">
     <td valign="middle" class="left_td" id="area">所在地区<font color="red">*</font></td>
         <td>
         	<table>
         		<tr>
         			<td>
         				<div id="arealist"></div>
         			</td>
         			<td >
         				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
              		</td>
              	</tr>
            </table>       
         </td>
   </tr>
  <tr>
     <td valign="middle" class="left_td" >产品图片:</td>
     <td>
     <table border="0" cellpadding="0" cellspacing="0">
         <tr>
             <td style="padding:0px;">
             	<div id="fileQueue"></div>
	             <@s.textfield name="supply.img_path" id="uploadresult" cssStyle="width:300px;"/>
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
     <td valign="middle" class="left_td">产品品牌:</td>
     <td>
     	<@s.textfield  name="supply.brand" cssClass="txtinput" maxlength="50"/>     
     	           <@s.fielderror><@s.param>supply.brand</@s.param></@s.fielderror>        
     </td>
   </tr>
   <tr>
     <td valign="middle" class="left_td">产品型号:</td>
     <td>
     	<@s.textfield  name="supply.model" cssClass="txtinput" maxlength="50"/>
  <@s.fielderror><@s.param>supply.model</@s.param></@s.fielderror>      	            
     </td>
   </tr>
   <tr>
     <td valign="middle" class="left_td" >产品规格:</td>
     <td>
     	<@s.textfield  name="supply.standard" cssClass="txtinput" maxlength="50"/>
     	  <@s.fielderror><@s.param>supply.standard</@s.param></@s.fielderror>  
   	            
     </td>
   </tr>
   <tr>
     <td valign="middle" class="left_td" >详细说明:</td>
     <td >
     	<@s.textarea  name="supply.content" id="content" cssClass="txtinput" />
     	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
		<script type="text/javascript">
	     CKEDITOR.replace('content');  
		</script>         
		     	  <@s.fielderror><@s.param>supply.content</@s.param></@s.fielderror>  	            
     </td>
   </tr>
   
   
   <tr>
     <td valign="middle" class="left_td" >是否支持在线订购:</td>
     <td>
     	<@s.radio name="supply.onlineorder" list=r"#{'0':'否','1':'是'}" value="0" />
     	 <@s.fielderror><@s.param>supply.onlineorder</@s.param></@s.fielderror>  	     
     </td>
   </tr>
   
   <tr>
     <td valign="middle" class="left_td">交易条件:</td>
     <td>
        <table class="wwtable" cellspacing="1" cellpadding="8" style="margin:0px 0px 0px 0px; width:100%;">
        <tr><td class="zitd">计量单位<font color="red">*</font></td>
        <td>
         <@s.select id="unit" name="supply.unit" list="unitList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" onchange="addUnit();"/>      
         <span id="otherunit" style="display:none;"><@s.textfield  name="other_unit" cssStyle="width:100px;"  maxlength="50"/></span>
         <@s.fielderror><@s.param>supply.unit</@s.param></@s.fielderror>
         </td>
        </tr>
        <tr><td class="zitd" >产品单价<font color="red">*</font></td>
        <td><@s.textfield id="price"  name="supply.price" cssClass="zitxt" maxlength="20"/>元(输入"0"代表"面议")
            <@s.fielderror><@s.param>supply.price</@s.param></@s.fielderror></td></tr>      
        <tr><td class="zitd">最小起订量<font color="red">*</font></td>
        <td><@s.textfield  name="supply.min_order" cssClass="zitxt" onkeyup="checkNum(this);" maxlength="20"/>
         <@s.fielderror><@s.param>supply.min_order</@s.param></@s.fielderror></td></tr>  
        <tr><td class="zitd">供应总量<font color="red">*</font></td>
        <td><@s.textfield  name="supply.max_supply" cssClass="zitxt"  maxlength="20"/>
         <@s.fielderror><@s.param>supply.max_supply</@s.param></@s.fielderror></td></tr>            
        <tr><td class="zitd">发货天数<font color='red'>*</font></td>
        <td>自买家付款之日起<@s.textfield id="send_day"  name="supply.send_day" onkeyup="checkNum(this);" cssClass="datenum"  maxlength="20" cssStyle="width:30px;"/>天内发货
        <@s.fielderror><@s.param>supply.send_day</@s.param></@s.fielderror></td></tr>         
        <tr><td class="zitd">运费<font color="red">*</font></td>
        <td><@s.textfield  name="supply.shipfee" cssClass="zitxt" value="0" maxlength="20"/>&nbsp;元(0代表免运费)
        <@s.fielderror><@s.param>supply.shipfee</@s.param></@s.fielderror></td></tr>        
        </table>       	            
     </td>
   </tr>    
   <tr>
     <td valign="middle" class="left_td" >过期时间<font color='red'>*</font></td>
     <td>
     	<@s.textfield cssClass="Wdate"  id="endtime" name="supply.end_date" onfocus="WdatePicker({readOnly:true})" />
     	<@s.fielderror><@s.param>supply.end_date</@s.param></@s.fielderror>
     </td>
   </tr>
   <tr>
         <td valign="middle" class="left_td" >推荐:</td>
         <td>
     	 <@s.radio name="supply.mem_recom" list=r"#{'0':'否','1':'是'}" value="0" checked="true" />         	      
         <@s.fielderror><@s.param>supply.mem_recom</@s.param></@s.fielderror>	     
         </td>
   </tr>
    <tr style="display:none;">
     <td valign="middle" class="left_td" >推荐:</td>
     <td>
 	 <@s.radio name="supply.is_recom" list=r"#{'0':'否','1':'是'}" value="0" checked="true" />  
 	  	      	 <@s.fielderror><@s.param>supply.is_recom</@s.param></@s.fielderror>  	           
     </td>
   </tr>
   <tr style="display:none;">
     <td valign="middle" class="left_td">点击量:</td>
     <td>
     	<@s.textfield id="clicknum"  name="supply.clicknum" cssClass="txtinput" onkeyup="checkNum(this);" maxlength="11" value="0"/>
     	<@s.fielderror><@s.param>supply.clicknum</@s.param></@s.fielderror>	  	          	            
     </td>
   </tr>
         
   <tr style="display:none;">
     <td valign="middle" class="left_td" >内容收费:</td>
     <td>
     	<@s.textfield id="fare" name="supply.fare" cssClass="txtinput" maxlength="11"  value="0"/>(只能输入数字,默认"0"则免费!)
     	<@s.fielderror><@s.param>supply.fare</@s.param></@s.fielderror>        	            
     </td>
  </tr>   
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="supply.info_state" value="0"/>
           <#if sysmodule.is_catattr=="0">      
          		<@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" class="sub" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"  cssClass="sub"/>
           </#if>
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	 <input type="button" name="returnList" class="sub" value="返回列表" 
	 onclick="linkToInfo('/member_Supply_list.action','');" />
    </td>
  </tr>
</table>

 </div>
 </@s.form>
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
<!--cont结束-->
</body>
</html>
