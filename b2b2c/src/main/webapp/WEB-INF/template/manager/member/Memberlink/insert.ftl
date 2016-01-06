<html>
<head>
<title>添加链接</title>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Memberlink_insert.action" method="post" validate="true">
      <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商铺管理>友情链接>添加链接</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
             <td width="19%" valign="middle" class="left_td">链接名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberlink.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>memberlink.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">链接地址</td>
             <td>
               <@s.textfield name="memberlink.link_url" value="http://" cssClass="txtinput" cssStyle="width:400px;" maxLength="60"/>
               <@s.fielderror><@s.param>memberlink.link_url</@s.param></@s.fielderror>
             </td>
           </tr>   		
	     <tr>
	    <td colspan="4" class="subbut">
	     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		 ${listSearchHiddenField?if_exists}
		 <@s.hidden name="memberlink.link_state" value="1"/>
	     <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Memberlink_list.action','');"/>
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
