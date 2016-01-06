<html>
  <head>
    <title>修改公告</title>
	 <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	  <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	 <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script>  
  </head>
  <body>  
<div class="tj_main f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > ${mold?if_exists}公告管理 > 修改${mold?if_exists}公告
   </div>
   <div class="tj_main_cont">
   	<@s.form action="/admin_News_updatenotice.action?news_mold=${news_mold?if_exists}" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:150px;">${mold?if_exists}公告标题<font color='red'> *</font></td>
             <td colspan="3">
             	<@s.textfield name="news.title" cssClass="txtinput" cssStyle="width:600px;" maxLength="600"/>
             	<@s.fielderror><@s.param>news.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             	<td class="table_name">${mold?if_exists}公告内容<font color='red'> *</font></td>
             	<td colspan="3">
             		<@s.textarea name="news.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
					<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
					<script type="text/javascript">
			     		CKEDITOR.replace( 'content');  
					</script>
             		<@s.fielderror><@s.param>news.content</@s.param></@s.fielderror>
             	</td>
           </tr>
           <tr> 
             <td class="table_name">${mold?if_exists}公告状态:</td>
             <td colspan="3">
           			<@s.radio name="news.info_state" list=r"#{'0':'未审核','1':'正常','2':'审核未通过','3':'禁用'}"  /> 
             		<@s.fielderror><@s.param>news.info_state</@s.param></@s.fielderror>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	     <@s.hidden name="news.news_id" />
	     <@s.hidden name="news.description" />
	     <@s.hidden name="news.clicknum" />
	     <@s.hidden name="news.fare" />
	     <@s.hidden name="news.comment"  />
	     <@s.hidden name="news.news_type" />
	     <@s.hidden name="news.news_attr" />
	     
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	     ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_News_goodsNewsList.action?news_mold=${news_mold?if_exists}','');"/>
	     </div>	 
	  </@s.form>
	  
   </div>
</div>
</div>
<div class="clear"></div>
  </body>
</html>