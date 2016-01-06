<html>
  <head>
    <title>添加专题</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/admin/suject.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
   	<link rel="StyleSheet" href="/include/components/dtree/dtree.css" type="text/css" />
	<link rel="StyleSheet" href="/include/css/page.css" type="text/css" /> 
	<link rel="StyleSheet" href="/include/css/admin/subject.css" type="text/css" /> 
    <script type="text/javascript" src="/include/components/dtree/dtree.js"></script>
    <script type="text/javascript" src="/include/js/pluginunit.js"></script>
    <script type="text/javascript" src="/include/js/admin/adminsubject.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
      	 <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"subject");  
		 </#if>		
		 //加载文字专题搜索数据
		 title_ids();  
		 //加载图片文字专题搜索数据
		 image_ids();
	  });
	</script>	
  </head>
  <body>

<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 专题管理 > 添加专题
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Subject_insert.action" method="post" validate="true">
     	<@s.hidden id="linkcats"  name="subject.link_cat" />
     	<@s.hidden id="newsids"  name="subject.news_attr" />
     	<@s.hidden id="newstitles" />
     	<@s.hidden id="newspicids" name="subject.img_news_attr" />
     	<@s.hidden id="newspictitles" />
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td width="19%" class="table_name">专题标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="subject.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>subject.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="subject.cat_attr" />
	                 <a href="/admin_Subject_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=subject&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=subject&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
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
             <td class="table_name">标题图片:</td>
              <td> 
              
                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			   <@s.textfield name="subject.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             			<td>
             			  <@s.fielderror><@s.param>subject.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>  
              </td>
           </tr>
           <tr>
             <td class="table_name">横幅图片:</td>
             <td>
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			   <@s.textfield name="subject.header_path" id="uploadresult1" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td>
             			   &nbsp;<@s.fielderror><@s.param>subject.header_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>       
             </td>
           </tr> 
            <tr>
             <td class="table_name">专题简介<font color='red'>*</font></td>
             <td>
                <@s.textarea name="subject.sub_desc" id="sub_desc" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'sub_desc');  
				</script>
				<@s.fielderror><@s.param>subject.sub_desc</@s.param></@s.fielderror>
             </td>
           </tr> 
        
           
          <tr>
             <td class="table_name">专题文字新闻:</td>
             <td style="padding-bottom:10px;">
             	<@s.textfield  cssClass="txtinput" id="titles" cssStyle="width:500px;"  maxLength="300"/>
             	<input type="button" value="搜索标题" onclick="searchTitle();"/><br/>
	            <div id="searchnewstitle" class="searchnewstitle"></div>  
	            <ul id="showstitledata" class="showstitledata">
	            
	            </ul>	             	
             </td>
           </tr> 
           
           
            <tr>
             <td class="table_name">专题图片新闻:</td>
             <td>
             	<@s.textfield  cssClass="txtinput" cssStyle="width:500px;" id="selectnewsImage"  maxLength="300"/>
             	<input type="button" value="搜索图片标题" onclick="searchImage();"/><br/>
	            <ul id="showsimagedata" class="showstitledata">
	            
	            </ul>	  
            </td>
           </tr> 
            <tr>
             <td class="table_name">关联资讯栏目:</td>
             <td>
                <div style="height:200px;width:500px;overflow-y: auto; overflow-x:hidden;">
                <script type="text/javascript">
					d = new dTree('d');
					d.add(1111111111,-1,'关联资讯栏目&nbsp;');	
					<#list cateList as sysarea>
					d.add(${sysarea.cat_id?if_exists},${sysarea.up_cat_id?if_exists},'<input type="checkbox" class="${sysarea.up_cat_id?if_exists} newsBox" onclick="checkStatus(this)" name="area.area_id" value="${sysarea.cat_id?if_exists}"/>&nbsp; ${sysarea.cat_name?if_exists} ','','','','');
		 			</#list>
					document.write(d);
				</script>
                </div>
             </td>
           </tr> 
            <tr>
             <td class="table_name">是否允许评论:</td>
             <td>
             	<@s.radio name="subject.is_comment" list=r"#{'0':'是','1':'否'}" value="0"/>
             	<@s.fielderror><@s.param>subject.is_comment</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">所属模板:</td>
             <td>
             	<@s.textfield name="subject.temp_path" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
             	<@s.fielderror><@s.param>subject.temp_path</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">是否推荐:</td>
             <td>
                <@s.radio name="subject.is_recom" list=r"#{'0':'否','1':'是'}" value="0"/>
                <@s.fielderror><@s.param>subject.is_recom</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">状态:</td>
             <td>
             	<@s.radio name="subject.info_state" list=r"#{'0':'未审核','1':'正常','2':'未通过','3':'禁用'}" value="1"/>
             	<@s.fielderror><@s.param>subject.info_state</@s.param></@s.fielderror>
             </td>
           </tr>  
            <tr>
             <td class="table_name">点击量:</td>
             <td>
             	<@s.textfield name="subject.clicknum" cssClass="txtinput" maxLength="11" value="0" onkeyup="checkNum(this);" />
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>">
             	<@s.fielderror><@s.param>subject.clicknum</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">内容收费:</td>
             <td>
             	<@s.textfield name="subject.fare" cssClass="txtinput"  maxLength="11" value="0" onkeyup="checkNum(this);" />
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>subject.fare</@s.param></@s.fielderror>
             </td>
           </tr> 
            
         </table>
         
	     <div class="buttom">
	       <@s.hidden id="title_id" name="title_id"/>
	       <@s.hidden id="image_id" name="image_id"/>
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="name_s"/> 	       
	       ${listSearchHiddenField?if_exists}     
	       <#if sysmodule.is_catattr=="0">      
          		<@s.hidden id="cat_attr" name="cat_attr"/>
          		<script type="text/javascript">
          			function subSubmit(){
          				getcheckCat();
          				dosubmit();
          			}
          		</script>
           		<input type="button" value="保存" onclick="subSubmit();">
           <#else>
           		<input type="button" value="保存" onclick="subjectsubmit('/admin_Subject_insert.action');"/>
           </#if>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Subject_list.action','');"/>
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