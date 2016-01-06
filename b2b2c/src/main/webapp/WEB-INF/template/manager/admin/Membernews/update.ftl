<html>
  <head>
    <title>修改企业新闻</title>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 企业新闻管理 > 修改企业新闻
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membernews_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">   
           <tr>
             <td width="19%" class="table_name">新闻标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membernews.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>membernews.title</@s.param></@s.fielderror>
             	<@s.hidden  name="oldinfotitle" value="${membernews.title?if_exists}"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">新闻内容</td>
             <td>
                <@s.textarea name="membernews.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;" />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js" ></script>
				<script type="text/javascript" >
			     CKEDITOR.replace( 'content');  
				</script>
				<@s.fielderror><@s.param>membernews.content</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
            <td class="table_name">证书状态:</td>
             <td>
                 <@s.radio name="membernews.news_state" list=r"#{'0':'通过','1':'未审核','2':'未通过'}" />
                 <@s.fielderror><@s.param>membernews.news_state</@s.param></@s.fielderror>     	
             </td>
           </tr>   
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden name="membernews.cert_id"/>
	       <@s.hidden name="membernews.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membernews_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>