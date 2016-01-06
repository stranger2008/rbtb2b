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
 <div class="cont_main">
 <@s.form action="/member_Showinfo_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>展会管理>添加展会</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">展会标题<span class="mustfield">*</span></td>
    <td width="83%" colspan="3">
   <@s.textfield name="showinfo.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>showinfo.title</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr >
    <td valign="middle" class="left_td" width="17%">标题图片:</td>
     <td> 
     
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
    <td valign="middle" class="left_td">所属分类<span class="mustfield">*</span></td>
    <td colspan="3">
   <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="showinfo.cat_attr" />
	                 <a href="/member_Showinfo_cate.action?cat_attr=0">[重新选择]</a>
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
  <tr>
    <td valign="middle" class="left_td">主办单位<span class="mustfield">*</span></td>
    <td colspan="3">
   <@s.textfield name="showinfo.host_unit" cssClass="txtinput" maxLength="200"  cssStyle="width:600px;"/>
             	<@s.fielderror><@s.param>showinfo.host_unit</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">承办单位:</td>
    <td colspan="3">
    	<@s.textfield name="showinfo.sponsor" cssClass="txtinput" maxLength="200"  cssStyle="width:600px;" />
             	<@s.fielderror><@s.param>showinfo.sponsor</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">展会开展日期<span class="mustfield">*</span></td>
    <td colspan="3">
   <@s.textfield id="txtstartDate" name="showinfo.start_date"   cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"/>
   <@s.fielderror><@s.param>showinfo.start_date</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">展会结束日期<span class="mustfield">*</span></td>
    <td colspan="3">
    <@s.textfield  id="txtendDate" name="showinfo.end_date"   cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"/>
    <@s.fielderror><@s.param>showinfo.end_date</@s.param></@s.fielderror>
   </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >展馆名称<span class="mustfield">*</span></td>
    <td colspan="3">
    <@s.textfield name="showinfo.hall_name" cssClass="txtinput" cssStyle="width:300px;" maxLength="50"/>
             	<@s.fielderror><@s.param>showinfo.hall_name</@s.param></@s.fielderror>
    </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >详细地址<span class="mustfield">*</span></td>
    <td colspan="3">
   <@s.textfield name="showinfo.address" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	<@s.fielderror><@s.param>showinfo.address</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td">所属地区<span class="mustfield">*</span></td>
    <td  colspan="3">
     <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
    </td>  
  </tr>
  <tr>
    <td valign="middle" class="left_td">展会备注:</td>
    <td colspan="3">
    	<@s.textfield name="showinfo.remark" size="70" maxLength="200"/>
      	<@s.fielderror><@s.param>showinfo.remark</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">展会描述<span class="mustfield">*</span></td>
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
    <td valign="middle" class="left_td">联系人<span class="mustfield">*</span></td>
    <td colspan="3">
    <@s.textfield name="showinfo.contact_man" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>showinfo.contact_man</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系电话<span class="mustfield">*</span></td>
    <td colspan="3">
    <@s.textfield name="showinfo.phone" cssClass="txtinput"  maxLength="20" cssStyle="width:100px;" />
             	<@s.fielderror><@s.param>showinfo.phone</@s.param ></@s.fielderror >
    </td>
  </tr>

   <tr>
    <td valign="middle" class="left_td" >联系手机:</td>
    <td colspan="3">
   <@s.textfield name="showinfo.cellphone" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>showinfo.cellphone</@s.param></@s.fielderror>
    </td>
  </tr>
   <tr>
    <td valign="middle" class="left_td" >联系地址:</td>
    <td colspan="3">
    <@s.textfield name="showinfo.contact_addr" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"  />
             	<@s.fielderror><@s.param>showinfo.contact_addr</@s.param ></@s.fielderror >
    </td>
  </tr>
  
  
  <tr>
    <td valign="middle" class="left_td" >联系传真:</td>
    <td colspan="3">
    <@s.textfield name="showinfo.fax" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>showinfo.fax</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td" >email:</td>
    <td colspan="3">
    <@s.textfield name="showinfo.email" cssClass="txtinput" cssStyle="width:200px;" maxLength="60"  />
             	<@s.fielderror><@s.param>showinfo.email</@s.param ></@s.fielderror >
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td" >MSN:</td>
    <td colspan="3">
   <@s.textfield name="showinfo.msn" cssClass="txtinput" maxLength="60" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>showinfo.msn</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td" >QQ:</td>
    <td colspan="3">
   <@s.textfield name="showinfo.qq" cssClass="txtinput" cssStyle="width:100px;" maxLength="20"  />
             	<@s.fielderror><@s.param>showinfo.qq</@s.param ></@s.fielderror >
    </td>
  </tr>
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="showinfo.info_state" value="0"/>
           <@s.hidden name="showinfo.fare" value="0"/>
           <@s.hidden name="showinfo.clicknum" value="0"/>
           <@s.hidden name="showinfo.is_recom" value="0"/>
	       ${listSearchHiddenField?if_exists}
         <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
		   <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Showinfo_list.action','');"/>
    </td>
  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
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
