<html>
  <head>
    <title>我要评论</title>
        <script>
    function geturlinfo()
    {
       var page_url = window.location.href;
       document.getElementById("urlid").value=page_url;
    }
    </script>
  </head>
  <body>
<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业相关 > 评论管理 > 我要评论
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membercomment_insert.action" method="post" validate="true">
   	    <@s.hidden name="page_url" id="urlid"/>
        <table class="wwtable" cellspacing="1" cellpadding="8">
            <tr>
             <td class="table_name">文章标题：</td>
             <td>
             	<@s.label name="info_title" cssClass="txtinput" maxLength="60" value="这是个神奇的网站"/>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">评论<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="membercomment.content" cssClass="txtinput" maxLength="300" cssStyle="width:300px;height:100px" 
             	 onkeyup="set_textarea_length(this,300);"/>
             	<@s.fielderror><@s.param>membercomment.content</@s.param></@s.fielderror>
             </td>
           </tr>

           <tr>
             <td class="table_name">评分:</td>
             <td>
             	<@s.radio name="comm_num" list=r"#{'1':'好评','0':'中评','-1':'差评'}" value="1"/>
             </td>
           </tr>
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercomment_list.action','');" />
	     </div>
	     
	  </@s.form>
	  
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>