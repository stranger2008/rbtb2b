<html>
  <head>
      <title>添加分类</title>
	  <script type="text/javascript" src="/include/js/pinyin.js"></script>
	  <script type="text/javascript" src="/include/js/jquery.alert.js"></script>	  
	  <script type="text/javascript" src="/include/js/admin/category.js"></script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	当前位置:功能模块 > 分类管理 > 分类管理 > 添加${modules?if_exists}分类
   </div>
   <div class="tj_main_cont">   
   	<@s.form action="/admin_Category_insert.action" method="post" validate="true">  
        <table class="wwtable" cellspacing="1" cellpadding="8">
          <tr>
             <td width="19%" class="table_name">分类名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="cat_name" name="category.cat_name" cssClass="txtinput" maxLength="50" autocomplete="off"/>
             	<@s.fielderror><@s.param>category.cat_name</@s.param></@s.fielderror>                                   
             </td>
           </tr>
           <script type="text/javascript">      
              var cat_value=$('#cat_name').val(); 
              $('#cat_name').bind('keyup', function(){   
	              var cat_value=$('#cat_name').val();   
	              var getword=Topingyin(cat_value);
	              var en_word="";
	              if(getword.length>50){
	              	en_word=getword.substring(0,49);
				  }else{
				     en_word=getword;
				  }
	              var word=getword.substring(0,1);            
	              $('#en_name').val(en_word);
	              $('#word_index').val(word);
              })         
           </script>  
           <tr>
             <td class="table_name">分类拼音名<font color='red'>*</font></td>
             <td>
             	<@s.textfield  id="en_name" name="category.en_name" cssClass="txtinput" maxLength="50" /> 
             	<@s.fielderror><@s.param>category.en_name</@s.param></@s.fielderror>       
             </td>
           </tr>
           <tr>
             <td class="table_name">字母索引<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="word_index" name="category.word_index" cssClass="txtinput" maxLength="1" readonly="true"/>
             	<@s.fielderror><@s.param>category.word_index</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td class="table_name">上级分类名称:</td>
             <td>                
                    ${up_cat_name?if_exists}
                    <@s.fielderror><@s.param>category.up_cat_name</@s.param></@s.fielderror>      
             </td>
           </tr>
           <tr>
             <td class="table_name">分类级别:</td>
             <td>                
                   ${category.cat_level?if_exists}级
                   <@s.fielderror><@s.param>category.cat_level</@s.param></@s.fielderror>      
             </td>
           </tr>
           <tr>
             <td class="table_name">所属模块:</td>
             <td>
                  ${modules?if_exists}
             	<@s.fielderror><@s.param>category.module_type</@s.param></@s.fielderror>  
             </td>
           </tr>
           <tr>
             <td class="table_name">是否显示:</td>
             <td>
             	<@s.radio name="category.is_display" list=r"#{'1':'显示','0':'不显示'}" value="1" checked="true" />
             	<@s.fielderror><@s.param>category.is_display</@s.param></@s.fielderror> 
             </td>
           </tr>           
           <tr>
             <td class="table_name">排序:</td>
             <td>
             <@s.textfield  id="sort_no"  name="category.sort_no" cssClass="txtinput" maxLength="6" onkeyup="checkNum(this);" value="1"/>
             	<img class='ltip' src="/include/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
                <@s.fielderror><@s.param>category.sort_no</@s.param></@s.fielderror>   
             </td>
           </tr>           
           <tr>
             <td class="table_name">是否允许会员发布:</td>
             <td>
             	<@s.radio name="category.member_add" list=r"#{'0':'允许','1':'禁止'}" value="0" checked="true" />
             	<@s.fielderror><@s.param>category.member_add</@s.param></@s.fielderror> 
             </td>
           </tr> 
            <tr>
             <td class="table_name">会员类型:</td>
             <td>
             	<@s.radio name="category.mem_type" list=r"#{'2':'不限','0':'企业','1':'个人'}"  value="2" checked="true"/>
             	<@s.fielderror><@s.param>category.mem_type</@s.param></@s.fielderror> 
             </td>
           </tr>          
           <tr>
	             <td class="table_name">分类简介:</td>
	             <td>
	             	<@s.textarea name="category.cat_simple" cssClass="mailCss"  onkeyup="set_textarea_length(this,100);" />
	             	<@s.fielderror><@s.param>category.cat_simple</@s.param></@s.fielderror> 
	             </td>
            </tr>    
            <tr>
             <td class="table_name">SEO标题:</td>
             <td>
             		<@s.textarea name="category.seotitle" cssClass="mailCss"  onkeyup="set_textarea_length(this,100);" />
             		<@s.fielderror><@s.param>category.seotitle</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">SEO关键字:</td>
             <td>
             		<@s.textarea name="category.seokeyword" cssClass="mailCss"  onkeyup="set_textarea_length(this,100);" />
             		<@s.fielderror><@s.param>category.seokeyword</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr>
             <td class="table_name">SEO描述:</td>
             <td>
             		<@s.textarea name="category.seodesc" cssClass="mailCss"  onkeyup="set_textarea_length(this,200);"/>
             		<@s.fielderror><@s.param>category.seodesc</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr>
             <td class="table_name">分类描述:</td>
             <td colspan="3">
             	<@s.textarea name="category.cat_intr" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             </td>
           </tr>
         </table>
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.submit value="保存"/>
	       <@s.hidden name="category.module_type"/>
	       <@s.hidden name="category.up_cat_id" />
	       <@s.hidden name="category.cat_level" />
	       <@s.hidden name="cate_attr_str" id="cate_attr_str"/>
	       <@s.hidden name="cat_select_moudle" id="cat_select_moudle"/>	  
	       <@s.hidden name="type"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Category_list.action';document.forms[0].submit();"/>
	     </div>
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>
</body>
</html>