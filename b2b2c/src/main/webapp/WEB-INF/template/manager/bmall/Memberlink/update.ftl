<html>
<head>
<title>修改友情链接</title>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">店铺管理</a><span>></span><a href="#">友情链接管理</a><span>></span><a href="#">修改友情链接</a>
    </div>
<@s.form action="/bmall_Memberlink_bmallupdate.action" method="post"  validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>友情链接管理</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">链接名称<font color='red'>*</font></td><td>
        	<@s.textfield name="memberlink.title" cssClass="txtinput" maxLength="50"/>
            </td></tr>
            <tr>
             <td  class="firsttd">链接地址<font color='red'>*</font></td>
             <td><@s.textfield name="memberlink.link_url" cssClass="txtinput" maxLength="60"/></td>
            </tr>  
            <tr>
             	<td colspan="2" align="center">
             		<@s.submit value="提  交" cssClass="submitbut"/>
             		<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Memberlink_bmalllist.action','');"/>
             		<@s.hidden name="memberlink.cert_id"/>
             	</td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

