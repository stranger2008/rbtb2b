<html>
  <head>
    <title>修改企业友情链接</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js" ></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 友情链接管理 > 修改企业友情链接
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Memberlink_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">   
           <tr>
             <td width="19%" class="table_name">链接名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberlink.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>memberlink.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">链接地址</td>
             <td>
               <@s.textfield name="memberlink.link_url" cssClass="txtinput" cssStyle="width:400px;" maxLength="60"/>
               <@s.fielderror><@s.param>memberlink.link_url</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td class="table_name">新闻状态:</td>
             <td>
                 <@s.radio name="memberlink.link_state" list=r"#{'0':'通过','1':'未审核','2':'未通过'}" />
                 <@s.fielderror><@s.param>memberlink.link_state</@s.param></@s.fielderror>     	
             </td>
           </tr>     
         </table>
         
	     <div class="buttom">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="memberlink.cert_id"/>
	       <@s.hidden name="memberlink.cust_id"/> 
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberlink_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>

  
  </body>
</html>