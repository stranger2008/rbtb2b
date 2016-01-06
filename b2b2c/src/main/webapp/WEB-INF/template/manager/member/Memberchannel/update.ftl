<html>
  <head>
    <title>修改栏目</title>
  </head>
  <body>
 <div class="cont_main">
   	<@s.form action="/member_Memberchannel_update.action" method="post" validate="true">
     	<table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:商铺信息>店铺设置>修改栏目</td>
 	</tr>
	</table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
           <tr>
             <td width="19%" valign="middle" class="left_td">栏目名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberchannel.ch_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberchannel.ch_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">栏目编码<font color='red'>*</font></td>
             <td>
             	<@s.label name="memberchannel.ch_code" cssClass="txtinput"/>
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">栏目类型<font color='red'>*</font></td>
             <td>
             	<#if memberchannel.ch_type?if_exists=='0'>
               	菜单
             <#elseif memberchannel.ch_type?if_exists=='1'>
             	侧栏
              <#elseif memberchannel.ch_type?if_exists=='2'>
              	首页主栏
              </#if>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">是否显示:</td>
             <td>
             	<@s.radio name="memberchannel.is_dis" list=r"#{'0':'显示','1':'不显示'}" />
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">排序:</td>
             <td>
             	<@s.textfield name="memberchannel.sort_no"  cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchannel.sort_no</@s.param></@s.fielderror>
             	（只能输入数字，且越小越往前）
             </td>
           </tr>
            <tr>
             <td valign="middle" class="left_td">显示产品数量:</td>
             <td>
             <#if  memberchannel.ch_code=='cptj'>
             <@s.select name="memberchannel.ch_num"   list=r"#{'0':'0','4':'4','8':'8','12':'12','16':'16'}" />
             <#else>
             	<@s.textfield name="memberchannel.ch_num"  cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchannel.ch_num</@s.param></@s.fielderror>
             	（只能输入数字）
             </#if>
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">关键字：</td>
             <td>
             	<@s.textfield name="memberchannel.meta_keyword" cssClass="txtinput" maxLength="100"/>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">描述：</td>
             <td>
             	<@s.textfield name="memberchannel.meta_desc" cssClass="txtinput" maxLength="200"/>
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">栏目内容：</td>
             <td>
             	<@s.textarea name="memberchannel.ch_content" id="content" cssClass="txtinput" />
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>
             </td>
           </tr>
           
           
           <tr>
			    <td colspan="4" class="subbut">
			       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
			       <@s.hidden name="memberchannel.ch_id"/>
			        <@s.hidden name="memberchannel.ch_type"/>
			       ${listSearchHiddenField?if_exists}
			       <@s.submit value="保存"  cssClass="sub"/>
			       <input type="button" class="sub" name="returnList" value="返回列表" onclick="document.forms[0].action='/member_Memberchannel_list.action';document.forms[0].submit();"/>
			    </td>
		   </tr>
            
         </table>
         
	     
	  </@s.form>
	  
	  
</div>

</div>
<div class="clear"></div>
  </body>
</html>