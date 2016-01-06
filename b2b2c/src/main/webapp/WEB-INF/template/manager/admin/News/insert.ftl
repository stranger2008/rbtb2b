<html>
  <head>
    <title>添加资讯</title>
	 <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	  <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
	      <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"news");  
		 </#if>       
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
  </head>
  <body>  
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 资讯管理 > 添加资讯
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_News_insert.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:150px;">资讯标题<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="news.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>news.title</@s.param></@s.fielderror>
             	<input name="news.title_color" id="title_color" type="text" value="" size="10"  class="iColorPicker" title="请选择标题颜色" />
             	<@s.fielderror><@s.param>news.title_color</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name"  >缩略图:</td>
             <td colspan="3" >
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="news.litpic" id="uploadresult" cssStyle="width:300px;"/>
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
             <@s.fielderror><@s.param>news.litpic</@s.param></@s.fielderror>
             </td>
             
           </tr>
           <tr>
             <td class="table_name">自定义属性:</td>
             <td >
                <@s.checkboxlist name="news.news_attr" list=r"#{'h':'头条', 'c':'推荐', 'f':'幻灯','s':'滚动','b':'加粗'}" >
                </@s.checkboxlist>
             </td>
            <td class="table_name" style="width:150px;">投票:</td>
             <td  >
                <@s.select name="news.vote_id" list="voteList" listValue="vote_title" listKey="vote_id" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>news.vote_id</@s.param></@s.fielderror>
             	<a href="/admin_Vote_list.action">[投票管理]</a>
             </td>
           </tr>
           <tr>
            <td class="table_name">所属分类<font color='red'> *</font></td>
             <td >
             
             <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="news.cat_attr" />
	                 <a href="/admin_News_cate.action?cat_attr=0">[重新选择]</a>
              		 <a href="admin_Category_list.action?type=news&ajaxRequestRandom=1" target="_blank">[分类管理]</a>  
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              			<a href="admin_Category_list.action?type=news&ajaxRequestRandom=1" target="_blank">[分类管理]</a>
		              		</td>
		              	</tr>
		            </table>       
	            </#if>
             </td>
             <td class="table_name">所属地区:</td>
             <td>
             	 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
             </td>
           </tr>
         
 				<#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
                </#if> 
         
           
           <tr>
           <td class="table_name">文章来源:</td>
             <td>
             	<@s.textfield name="news.source" cssClass="txtinput" maxLength="10" csssStyle="width:100px;"/>
             	<@s.fielderror><@s.param>news.source</@s.param></@s.fielderror>
             </td>
          
            <td class="table_name">作者:</td>
             <td>
             	<@s.textfield name="news.author" cssClass="txtinput" maxLength="10"/>
             	<@s.fielderror><@s.param>news.author</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
               <td class="table_name">跳转网页:</td>
             <td >
             	<@s.textfield name="news.out_link" cssStyle="width:300px;" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>news.out_link</@s.param></@s.fielderror>
             </td>
              <td class="table_name">文章排序:</td>
             <td >
                 <@s.select  name="news.sort_data_number"  list=r"#{'0':'默认排序','7':'置顶一周','30':'置顶一个月','90':'置顶三个月','180':'置顶半年','365':'置顶一年'}" />
             </td>
           </tr>
           <tr>
             
              <td class="table_name">关键字:</td>
             <td >
             	<@s.textfield name="news.keyword" cssClass="txtinput" maxLength="20"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.keyword')}"/>">
             	<@s.fielderror><@s.param>news.keyword</@s.param></@s.fielderror><br/>
             </td>
               <td class="table_name">内容收费:</td>
             <td >  
             	<@s.textfield name="news.fare" cssClass="txtinput" cssStyle="width:80px;" maxLength="20" value="0" onkeyup="checkNum(this);"/>            	
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Supply.fare')}"/>">
             	<@s.fielderror><@s.param>news.fare</@s.param ></@s.fielderror >
             </td>
           </tr>
           <tr>
             <td class="table_name">来源链接:</td>
             <td >
             	<@s.textfield name="news.sourcelink" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"/>
             	<@s.fielderror><@s.param>news.sourcelink</@s.param></@s.fielderror>
             </td>
               <td class="table_name">点击量:</td>
             <td >
             	<@s.textfield name="news.clicknum" cssClass="txtinput" maxLength="20" value="0" cssStyle="width:150px;" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.clicknum')}"/>"> 
             	<@s.fielderror><@s.param>news.clicknum</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">评论选项:</td>
             <td  >
               <@s.radio name="news.comment" list=r"#{'0':'支持评论','1':'禁止评论'}" value="0"/>    
             </td>
              <td class="table_name">tag标签:</td>
              <td >
             	<@s.textfield name="news.tag" cssStyle="width:300px;" cssClass="txtinput" maxLength="100"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.tag')}"/>">
             	<@s.fielderror><@s.param>news.tag</@s.param></@s.fielderror><br/>
             </td>
           </tr>
            
            <tr>
            
           </tr>
             <tr>
             <td class="table_name">内容摘要<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textarea name="news.description" cssClass="txtinput" onkeyup="set_textarea_length(this,200);" cssStyle="width:500px;height:100px;" />
             	<@s.fielderror><@s.param>news.description</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">资讯内容<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textarea name="news.content" id="content" cssClass="txtinput" />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             	<@s.fielderror><@s.param>news.content</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr> 
             <td class="table_name">信息状态:</td>
             <td colspan="3">
           <@s.radio name="news.info_state" list=r"#{'0':'未审核','1':'正常','2':'审核未通过','3':'禁用'}" value="1" /> 
             <@s.fielderror><@s.param>news.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="news.news_type" value="0"/>
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
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_News_list.action','');"/>
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