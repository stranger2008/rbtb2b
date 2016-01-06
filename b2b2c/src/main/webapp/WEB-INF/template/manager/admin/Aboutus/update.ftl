<html>
  <head>
    <title>修改关于我们</title>
  </head>
  <body>


<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:网站管理 > 基本功能 > 修改关于我们
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Aboutus_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">
           
           <tr>
             <td class="table_name">关于我们标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="aboutus.title" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>aboutus.title</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">栏目名称:</td>
             <td>
             <@s.select name="aboutus.ch_id" list="commparaList" listValue="para_key" listKey="para_value"/>
             <a href="/admin_Commpara_list.action?para_code_s=ch_id" target="_blank">[栏目管理]</a>
             <@s.fielderror><@s.param>aboutus.ch_id</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td width="19%" class="table_name">信息内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="aboutus.content" cssClass="txtinput" id="content" cssStyle="width:500px;height:100px"/>
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>
             	<@s.fielderror><@s.param>aboutus.content</@s.param></@s.fielderror>
             </td>
           </tr>
          
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="aboutus.info_id"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <@s.hidden name="mall_type" />
	        ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Aboutus_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Aboutus_malllist.action'</#if>,'');"/>
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>