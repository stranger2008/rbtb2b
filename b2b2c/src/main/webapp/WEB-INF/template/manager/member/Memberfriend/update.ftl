<html>
<head>
<title>会员修改商友</title>
	<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<style type="text/css">
	  #memlist{border:1px solid #FFFFFF;height:auto; max-height:200px;overflow:hidden;}
	  .memlist{display:none};
	  #memtable div{margin:auto 0px;height:20px;font-size:13px;}
     .colorthree {background-color:#F1F0F6;font-size:14px;} 	 
	</style>
	<script type="text/javascript">
	  var tdtext="";
	  $(document).ready(function(){ 
		   $("#memtable td").click(function(){
		   	     tdtext=$(this).text();
		   	     $("#custname").val(tdtext);
		         get_msg_by_name();
		   });	
		  $("#memtable div").hover(function(){
		     $(this).addClass("colorthree")		
		  },function(){		
		     $(this).removeClass("colorthree")		
		  });	   
	  });
      function get_msg_by_name(){
 	       document.forms[0].action='/member_Memberfriend_showview.action?sg=2';
	       document.forms[0].submit();
 	   }
	</script>

</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Memberfriend_update.action" method="post" validate="true">
       <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商机信息>我的商友>修改商友</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
         <tr>
             <td width="19%" valign="middle" class="left_td">分类<font color='red'>*</font></td>
             <td>
                <@s.select name="memberfriend.cat_id" list="membercat" listValue="cat_name" listKey="cat_id" />
                <a href="/member_Membercat_list.action?membertype=2">[分类管理]</a>
             	<@s.fielderror><@s.param>memberfriend.cat_id</@s.param></@s.fielderror> 
             </td>
          </tr>
          <tr>
             <td valign="middle" class="left_td">姓名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberfriend.name" cssClass="txtinput"  maxLength="20" />
             	<@s.fielderror><@s.param>memberfriend.name</@s.param></@s.fielderror> 
             </td>
          </tr>  
          <tr>
             <td valign="middle" class="left_td">公司名称<font color='red'>*</font></td>
             <td>          
             	<@s.textfield name="memberfriend.cust_name" cssClass="txtinput" id="custname"  maxLength="100" cssStyle="width:280px;"/>
             	<input value="显示资料" style="cursor:pointer;" Class="sub" type="button" onclick="showmsg();"/>
             	<@s.fielderror><@s.param>memberfriend.cust_name</@s.param></@s.fielderror> 
             	<script type="text/javascript">
	             	 function showmsg(){
	             	      document.forms[0].action='/member_Memberfriend_showview.action?sg=2';
	                      document.forms[0].submit();

	             	 }
             	</script>
             	<div id="memlist">
             	  <table id="memtable" width="100%">
             	  <#list memberList as member>
          	      <tr >
             	     <td style="cursor:pointer;" ><div>${member.cust_name?if_exists}</div></td>
             	  </tr>
             	  </#list>
             	  </table>
             	</div>
             </td>
          </tr>     
           <tr>
             <td valign="middle" class="left_td">职位:</td>
             <td>
             	<@s.textfield name="memberfriend.career" cssClass="txtinput"  maxLength="20" />
             	<@s.fielderror><@s.param>memberfriend.career</@s.param></@s.fielderror> 
             </td>
           </tr> 
           <tr>
             <td valign="middle" class="left_td">电话:</td>
             <td>
             	<@s.textfield name="memberfriend.phone" cssClass="txtinput"  maxLength="20" />
             	<@s.fielderror><@s.param>memberfriend.phone</@s.param></@s.fielderror> 
             </td>
           </tr> 
           <tr>
             <td valign="middle" class="left_td">手机:</td>
             <td>
                <@s.textfield name="memberfriend.cellphone" cssClass="txtinput"  maxLength="20" />
             	<@s.fielderror><@s.param>memberfriend.cellphone</@s.param></@s.fielderror> 
             </td>
            </tr> 
            <tr>
             <td valign="middle" class="left_td">主页:</td>
             <td>
             	<@s.textfield name="memberfriend.website" cssClass="txtinput"  maxLength="100" cssStyle="width:320px;"/>
             	<@s.fielderror><@s.param>memberfriend.website</@s.param></@s.fielderror> 
             </td>
        </tr> 
       <tr>
             <td valign="middle" class="left_td">Email:</td>
             <td>
             	<@s.textfield name="memberfriend.email" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>memberfriend.email</@s.param></@s.fielderror> 
             </td>
        </tr> 
         <tr>
             <td valign="middle" class="left_td">QQ:</td>
             <td>
             	<@s.textfield name="memberfriend.qq" cssClass="txtinput"  maxLength="20" />
             	<@s.fielderror><@s.param>memberfriend.qq</@s.param></@s.fielderror> 
             </td>
        </tr> 
      <tr>
             <td valign="middle" class="left_td">阿里旺旺:</td>
             <td>
             	<@s.textfield name="memberfriend.aliwang" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>memberfriend.aliwang</@s.param></@s.fielderror> 
             </td>
        </tr> 
              <tr>
             <td valign="middle" class="left_td">MSN:</td>
             <td>
             	<@s.textfield name="memberfriend.msn" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>memberfriend.msn</@s.param></@s.fielderror> 
             </td>
        </tr> 
              <tr>
             <td valign="middle" class="left_td">Skype:</td>
             <td>
             	<@s.textfield name="memberfriend.skype" cssClass="txtinput"  maxLength="60" />
             	<@s.fielderror><@s.param>memberfriend.skype</@s.param></@s.fielderror> 
             </td>
        </tr> 
              <tr>
             <td valign="middle" class="left_td">备注:</td>
             <td>
				<@s.textarea name="memberfriend.remark" cssClass="txtinput" rows="5" cssStyle="width:500px;" onkeyup="set_textarea_length(this,100);"/>
             	<@s.fielderror><@s.param>memberfriend.remark</@s.param></@s.fielderror> 		
             </td>
        </tr> 
	    <tr>
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="memberfriend.info_id"/>
	       <@s.hidden name="memberfriend.f_cust_id"/>
	       <@s.submit value="提交"  cssClass="sub"/>
		 <input type="button" name="returnList" class="sub" value="返回列表"  onclick="linkToInfo('/member_Memberfriend_list.action','');"/>
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
