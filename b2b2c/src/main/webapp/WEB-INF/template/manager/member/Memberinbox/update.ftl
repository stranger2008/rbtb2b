<html>
<head>
<title>查看信件</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $(document).ready(function(){   
	     $("#return_list").click(function(){
		   	 var type=$("#type").val();
		     if(type==1){	     
		         linkToInfo('/member_Memberinbox_list.action','');
		     }
		     if(type==2){
		          linkToInfo('/member_Memberinbox_sendindex.action','');
		      }
		     if(type==3){
		         linkToInfo('/member_Memberinbox_reindex.action','');
		     }
	     });
	  }); 
	</script> 
</head>
<body>
 <div class="cont_main">
 <@s.form  method="post" validate="true">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商机管理>我的信箱>查看信件</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
   <tr>
             <td width="19%" valign="middle" class="left_td">信件标题:</td>
             <td>
                   ${memberinbox.title?if_exists}
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">发件人:</td>
             <td>
					${memberinbox.cust_id?if_exists}
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">发件时间:</td>
             <td>
					${memberinbox.in_date?if_exists}
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">收件人:</td>
             <td>
					${memberinbox.send_cust_id?if_exists}
             </td>
           </tr> 
            <tr>
             <td valign="middle" class="left_td">信件内容:</td>
             <td>
          			${memberinbox.content?if_exists}   
             </td>
           </tr>       
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="membercert.cert_id"/>
           ${listSearchHiddenField?if_exists}
           <@s.hidden id="type" name="type"/>
           <@s.hidden name="is_read"/>
	    <input type="button" id="return_list" name="returnList" class="sub"  value="返回列表" />
	    </td>
	  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
</body>
</html>
