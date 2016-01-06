<html>
<head>
<title>会员信用指数记录</title>
</head>
<body>
<@s.form action="/member_Credithistory_list.action" method="post">
  <div class="cont_main">
   <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:会员信息>会员资料>信用指数记录</td>
 	</tr>
	</table>
	<div style="border:1px solid #e1e2e3;border-bottom:none;margin-top:10px;">
    <table width="100%" cellspacing="0" border="0" >
    <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="40%"  align="center" class="top_td">指数理由</td>
    <td width="20%"  align="center" class="top_td">指数值</td>
    <td width="20%"  align="center" class="top_td">现有指数</td>
    <td width="20%"  align="center" class="top_td">获得时间</td>
    </tr>
   <#list credithistoryList as  credithistory>
  <tr> 
    <td align="left">
       ${credithistory.reason?if_exists}
    </td>
        <td align="center">
         ${credithistory.c_num?if_exists}
    </td>
        <td align="center">
         ${credithistory.now_num?if_exists}
    </td>
        <td align="center">
         ${credithistory.in_date?if_exists}
    </td>
  </tr>
  </#list>
    </table>
   <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 </div>
</div>
</div>
<div class="clear"></div>
<!--搜索区域开始-->
<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">下载标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
         <td align="right">文件类型:</td>
         <td>
         <@s.select name="file_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
   	<tr>
         <td align="right">是否推荐:</td>
          <td>
           <@s.select name="is_recom_s" list=r"#{'0':'否','1':'是'}" headerKey="" headerValue="请选择"/>  
          </td>
    </tr>
     <tr>
		<td align="right">分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
         <td align="right">状态:</td>
         <td>
         	<@s.select name="info_state_s" list=r"#{'0':'未审核','1':'正常','2':'未通过','3':'禁用'}" headerKey="" headerValue="请选择"/>  
         </td>
    </tr>
    <tr>
    <td align="right">时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />
		               至
           <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
        </td>
    </tr>
           
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('','cat_attr','','search_cat_attr');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>
<!--搜索区域结束-->
<@s.hidden id="search_cat_attr" name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<!--搜索区域结束-->

</@s.form>
</body>
</html>
