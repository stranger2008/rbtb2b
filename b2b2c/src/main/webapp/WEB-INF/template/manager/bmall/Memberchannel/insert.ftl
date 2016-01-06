<html>
<head>
<title>添加栏目</title>
</head>
<body>
	<div class="postion">
  		 </span><a href="#">我是卖家<span>></span><a href="#">店铺管理</a></a><span>></span><a href="#">店铺栏目管理</a><span>></span><a href="#">添加栏目</a>
    </div>
<@s.form action="/bmall_Memberchannel_insert.action" method="post"  validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>添加栏目</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr>
            <td  class="firsttd">栏目名称<font color='red'>*</font></td>
            <td>
        	<@s.textfield name="memberchannel.ch_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberchannel.ch_name</@s.param></@s.fielderror>
            </td>
            </tr>
			<tr>
             <td class="firsttd">栏目编码<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberchannel.ch_code" cssClass="txtinput" value="myself" readonly="true"/>
             	<@s.fielderror><@s.param>memberchannel.ch_code</@s.param></@s.fielderror>
             	（不能修改）
             </td>
           </tr>
             <tr>
             <td class="firsttd">栏目类型<font color='red'>*</font></td>
             <td>
             	<@s.select name="memberchannel.ch_type" list=r"#{'0':'菜单'}" headerKey="3" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>memberchannel.ch_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="firsttd">是否显示:</td>
             <td>
             	<@s.radio name="memberchannel.is_dis" list=r"#{'0':'显示','1':'不显示'}" value="0"/>
             </td>
           </tr>
            <tr>
             <td class="firsttd">排序:</td>
             <td>
             	<@s.textfield name="memberchannel.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchannel.sort_no</@s.param></@s.fielderror>
             	（只能输入数字，且越小越往前）
             </td>
           </tr>
            <tr>
             <td class="firsttd">数量:</td>
             <td>
             	<@s.textfield name="memberchannel.ch_num" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>memberchannel.ch_num</@s.param></@s.fielderror>
             	（只能输入数字）
             </td>
           </tr>
             <tr>
             <td class="firsttd">关键字：</td>
             <td>
             	<@s.textfield name="memberchannel.meta_keyword" cssClass="txtinput" maxLength="100"/>
             </td>
           </tr>
           <tr>
             <td class="firsttd">描述：</td>
             <td>
             	<@s.textfield name="memberchannel.meta_desc" cssClass="txtinput" maxLength="200"/>
             </td>
           </tr>
           <tr>
             <td class="firsttd">栏目内容：</td>
             <td>
             	<@s.textarea name="memberchannel.ch_content" id="content" cssClass="txtinput" />
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>
             </td>
           <tr>
				<td colspan="2" align="center">
					<@s.submit value="提  交" cssClass="submitbut"/>
             		<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="document.forms[0].action='/bmall_Memberchannel_bmalllist.action';document.forms[0].submit();"/>
             		<@s.hidden name="mall_type" value="b2c"/>
             	</td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

