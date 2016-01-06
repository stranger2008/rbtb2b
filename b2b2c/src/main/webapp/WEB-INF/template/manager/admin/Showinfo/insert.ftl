<html>
 <head>
    <title>添加展会</title>
	<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"  ></script>
	<script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
 	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
	      <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"showinfo");  
		 </#if>          
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
 </head>
 <body>  
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 展会管理 > 添加展会
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_Showinfo_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:150px;">展会标题<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="showinfo.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="20"/>
             	<@s.fielderror><@s.param>showinfo.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">标题图片:</td>
             <td colspan="3" >             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="showinfo.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             		</tr>
             	</table>  
             	<@s.fielderror><@s.param>showinfo.img_path</@s.param></@s.fielderror>
             </td>     
           </tr> 
           <tr>
            <td class="table_name">所属分类<font color='red'> *</font></td>
             <td colspan="3" >
             
              <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="showinfo.cat_attr" />
	                 <a href="/admin_Showinfo_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=showinfo&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=showinfo&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>
             </td>
           </tr>
           <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if> 
           <tr>
           <td class="table_name">主办单位<font color='red'> *</font></td>
           <td>
             	<@s.textfield name="showinfo.host_unit" cssClass="txtinput" maxLength="200"  cssStyle="width:400px;"/>
             	<@s.fielderror><@s.param>showinfo.host_unit</@s.param></@s.fielderror>
            </td>          
            <td class="table_name">承办单位:</td>
             <td>
             	<@s.textfield name="showinfo.sponsor" cssClass="txtinput" maxLength="200" cssStyle="width:400px;" />
             	<@s.fielderror><@s.param>showinfo.sponsor</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
               <td class="table_name">展会开展日期<font color='red'> *</font></td>
             <td >
              <@s.textfield  id="txtstartDate" name="showinfo.start_date"   cssClass="Wdate"  
              onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />
              <@s.fielderror><@s.param>showinfo.start_date</@s.param></@s.fielderror>
             	
             </td>
              <td class="table_name">展会结束日期<font color='red'> *</font></td>
             <td >
              <@s.textfield  id="txtendDate" name="showinfo.end_date"   cssClass="Wdate"  
              onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
              <@s.fielderror><@s.param>showinfo.end_date</@s.param></@s.fielderror>
               
             </td>
           </tr>
          <tr>
              <td class="table_name">展馆名称<font color='red'> *</font></td>
             <td   >
                 <@s.textfield name="showinfo.hall_name" cssClass="txtinput" cssStyle="width:300px;" maxLength="50"/>
             	<@s.fielderror><@s.param>showinfo.hall_name</@s.param></@s.fielderror>
             </td>
             <td class="table_name">详细地址<font color='red'> *</font></td>
             <td  >
              	<@s.textfield name="showinfo.address" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	<@s.fielderror><@s.param>showinfo.address</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">所属地区<font color='red'> *</font></td>
             <td colspan="3" >
             	 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td>

           </tr>
            <tr> 
             <td class="table_name">展会备注:</td>
             <td  colspan="3" >
             	<@s.textfield name="showinfo.remark" size="70" maxLength="200"/>
             	<@s.fielderror><@s.param>showinfo.remark</@s.param></@s.fielderror>
             </td>
             
           </tr>
           <tr>
             <td class="table_name">展会描述<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textarea name="showinfo.exh_desc" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             	<@s.fielderror><@s.param>showinfo.exh_desc</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             
              <td class="table_name">联系人<font color='red'> *</font></td>
             <td >
             	<@s.textfield name="showinfo.contact_man" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>showinfo.contact_man</@s.param></@s.fielderror>
             </td>
               <td class="table_name">联系电话<font color='red'> *</font></td>
             <td >  
             	<@s.textfield name="showinfo.phone" cssClass="txtinput"  maxLength="20"/>
             	<@s.fielderror><@s.param>showinfo.phone</@s.param ></@s.fielderror >
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">联系手机:</td>
             <td >
             	<@s.textfield name="showinfo.cellphone" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>showinfo.cellphone</@s.param></@s.fielderror>
             </td>
               <td class="table_name">联系地址:</td>
             <td >  
             	<@s.textfield name="showinfo.contact_addr" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"  />
             	<@s.fielderror><@s.param>showinfo.contact_addr</@s.param ></@s.fielderror >
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">联系传真:</td>
             <td >
             	<@s.textfield name="showinfo.fax" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>showinfo.fax</@s.param></@s.fielderror>
             </td>
               <td class="table_name">email:</td>
             <td >  
             	<@s.textfield name="showinfo.email" cssClass="txtinput" cssStyle="width:200px;" maxLength="60"  />
             	<@s.fielderror><@s.param>showinfo.email</@s.param ></@s.fielderror >
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">MSN:</td>
             <td >
             	<@s.textfield name="showinfo.msn" cssClass="txtinput" maxLength="60" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>showinfo.msn</@s.param></@s.fielderror>
             </td>
               <td class="table_name">QQ:</td>
             <td >  
             	<@s.textfield name="showinfo.qq" cssClass="txtinput" cssStyle="width:100px;" maxLength="20"  />
             	<@s.fielderror><@s.param>showinfo.qq</@s.param ></@s.fielderror >
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">是否推荐:</td>
             <td >
             	 <@s.radio name="showinfo.is_recom" list=r"#{'0':'否','1':'是'}" value="0" />     	
             	 <@s.fielderror><@s.param>showinfo.is_recom</@s.param ></@s.fielderror >
             </td>
               <td class="table_name">点击量:</td>
             <td >  
             	<@s.textfield name="showinfo.clicknum" cssClass="txtinput" value="0" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>showinfo.clicknum</@s.param ></@s.fielderror >
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>"> 
             </td>
           </tr>
           
             <tr>
             
              <td class="table_name">内容收费:</td>
             <td colspan="3" >
             	<@s.textfield name="showinfo.fare" cssClass="txtinput" maxLength="20" value="0" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>showinfo.fare</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>"> 
             </td>
             </td>
           </tr>
             <tr>
             
              <td class="table_name">信息状态:</td>
             <td colspan="3" >
               <@s.radio name="showinfo.info_state" list=r"#{'0':'未审核','1':'审核通过','2':'审核未通过','3':'禁用'}" value="1" />  
               <@s.fielderror><@s.param>showinfo.info_state</@s.param></@s.fielderror>
             </td>   
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	      <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();">
           <#else>
           		<@s.submit value="保存"/>
           </#if>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value" />
		   <!--所属地区插件隐藏字段结束区域-->
		   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Showinfo_list.action','');"/>
	     </div>	 
	         
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshowinfo();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
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