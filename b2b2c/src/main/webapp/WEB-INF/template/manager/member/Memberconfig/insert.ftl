<html>
  <head>
    <title>企业站激活</title>
	<script  type="text/javascript">	
	     function adtive(){
		     if(window.confirm("确定激活?")) {
			    document.forms[0].action='/member_Memberchannel_insertinto.action';
			    document.forms[0].submit();
		    	}
	     }
    </script>
    <style>
    	.qyjihuo{
    		font-size:20px;
    		color:#FFF;
    		background-image:url(/include/images/member/adtive.gif);
    		width:135px;
    		font-weight:bold;
    		height:35px;
    		display:inline-block;
    	}
    </style>
  </head>
  <body>
  <@s.form>
   <div class="cont_main">   	
      	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>店铺设置>企业站激活</td>
 	</tr>
	</table>
      <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1"class="main_cont">
	       <tr>
	         <td align="center" style="height:60px;">
	         	 <a class="qyjihuo" onclick="adtive();">激活企业站</a>
	         </td>
	       </tr>
     </table>
	</div>
	  </@s.form>
  <div class="clear"></div>
  </body>
</html>