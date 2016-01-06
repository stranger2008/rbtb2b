<html>
  <head>
    <title>分类属性管理</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>	
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/js/common.js"></script>
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
  </head>
  <body>

<@s.form action="/admin_Categoryattr_list.action" method="post">
<@s.hidden id="code_value" name="code_value"/>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:功能模块 > 分类管理 > ${modtype_name?if_exists}分类属性管理 
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a  href="###"  onclick="linkToInfo('/admin_Categoryattr_add.action?modtype_name_id=${modtype_name_id?if_exists}&intr_type=${modtype_name_id?if_exists}&url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','code_value=${code_value?if_exists}')">添加属性</a></li>
     <li class="sc"><a onclick="delInfo('categoryattr.attr_id','/admin_Categoryattr_delete.action?url_up_id=${url_up_id?if_exists}')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="6%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('categoryattr.attr_id')"/>&nbsp;全选</td>
    <td width="20%" align="center" class="top_td">属性名</td>
    <td width="10%" align="center" class="top_td">所属模块</td>
    <td width="20%" align="center" class="top_td">所属分类</td>
    <td width="20%" align="center" class="top_td">属性类型</td>
    <td width="20%" align="center" class="top_td">是否必填</td>
    <td width="10%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list categoryattrList as categoryattr>

    <tr bgcolor="<#if categoryattr_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="categoryattr.attr_id" value="${categoryattr.attr_id?if_exists}"/></td>
    <td align="center">${categoryattr.attr_name?if_exists}</td>
    <td align="center">${categoryattr.module_name?if_exists}</td>
    <td align="center">${categoryattr.cat_attr?if_exists}</td>
    <td align="center">   
    <#if categoryattr.attr_type='0'>
    <font color="#999999"> 单行文本</font>
    <#elseif categoryattr.attr_type='1'>
    <font color="#990000"> 多行文本 </font>
    <#elseif categoryattr.attr_type='2'>
    <font color="red"> 单选框</font>
    <#elseif categoryattr.attr_type='3'>
    <font color="red"> 复选框</font>
    <#elseif categoryattr.attr_type='4'>
    <font color="red">图片上传</font>
    <#elseif categoryattr.attr_type='5'>
    <font color="red">附件</font>
    <#elseif categoryattr.attr_type='6'>
    <font color="red">WEB编辑器</font>
    <#elseif categoryattr.attr_type='7'>
    <font color="red">日期</font>
    <#else>
    <font color="green"></font>
    </#if>    
    </td>
    <td align="center">
    <#if categoryattr.is_must='0'>
    <font color="red">不必填</font>
    <#else>
     <font color="green">必填</font>
    </#if> 
    </td>
    <td align="center"><a  onclick="linkToInfo('/admin_Categoryattr_view.action?modtype_name_id=${modtype_name_id?if_exists}&url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','categoryattr.attr_id=${categoryattr.attr_id?if_exists}&modtype_s=${modtype_s?if_exists}')"><img src="/include/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>

<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	<tr>
		<td align="right">属性名:</td>
		<td><@s.textfield name="attr_name_s"/></td>
	</tr>
	<tr>
		<td align="right">所属模块:</td>
		<td>		
			<@s.select id="modletype" name="modtype_s"  list="moduleList" listValue="module_name" listKey="module_type"  headerKey=""  headerValue="请选择"/> 
		</td>
	</tr>	
	<tr>
		<td align="right">所属分类:</td>
		<td><div id="divlist"></div></td>
	</tr>
	<tr>
		<td align="right">属性类型:</td>
		<td><@s.select name="attr_type_s" list=r"#{'0':'单行文本','1':'多行文本','2':'单选框','3':'复选框','4':'图片上传','5':'附件','6':'WEB编辑器','7':'日期'}" headerKey=""  headerValue="请选择"/>  </td>
	</tr>
	<tr>
		<td align="right">是否必填:</td>
		<td><@s.select name="is_must_s" list=r"#{'0':'不必填','1':'必填'}" headerKey=""  headerValue="请选择"/>  </td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv()"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>		
			<script>
				function showSelectDiv(){
					var cat_attr = document.getElementsByName('cat_attr');
					var cat_attr_str = '';
					for(var i=0;i<cat_attr.length;i++){
						if(cat_attr[i].value!='0'){
							cat_attr_str += cat_attr[i].value+',';
						}
					}
					$("#search_cat_attr").val(cat_attr_str);
					document.forms[0].submit();
				}
			</script>			
		</td>
	</tr>
	</table>
</div>
	<!--搜索区域结束-->
	<@s.hidden id="url_up_id" name="url_up_id"/>
    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
	<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
</@s.form>
 	<script type="text/javascript">
	  $(document).ready(function(){ 
         //加载分类
         cate_select(${cfg_topcatid?if_exists},1, $("#modletype").val()); 
         //select事件触发  
         $("#modletype").change(function(){
			   $('option:selected', this).each(function(){
			        $("#divlist").html("");
                    cate_select(${cfg_topcatid?if_exists},1,this.value);                   	
			   });  	
		  });     
	  });
	</script>
  </body>
</html>