﻿<html>
<head>
<title>添加新闻</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Membernews_insert.action" method="post" validate="true">
    <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商铺管理>公司新闻>添加新闻</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
       <tr>
             <td width="19%" valign="middle" class="left_td">新闻标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membernews.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>membernews.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">新闻内容</td>
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
	    <td colspan="4" class="subbut">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	     <@s.hidden name="membernews.news_state" value="1"/>
		  ${listSearchHiddenField?if_exists}
	      <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Membernews_list.action','');"/>
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
</body>
</html>
