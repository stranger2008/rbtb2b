<html>
<head>
<title>添加资讯</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<#include "/include/uploadInc.html">
	  <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
          <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"news");  
		 </#if>    
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_News_insert.action" method="post" validate="true">
     <table width="100%" class="cont_title">
 	<tr>
    	<td>当前位置:信息管理>资讯管理>添加资讯</td>
 	</tr>
	</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
  <tr>
    <td width="17%" valign="middle" class="left_td">资讯标题<span class="mustfield">*</span></td>
    <td width="83%" colspan="3">
   <@s.textfield name="news.title" size="70" maxLength="600"/>
    <@s.fielderror><@s.param>news.title</@s.param></@s.fielderror>
    </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">缩略图:</td>
    <td>
    
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="news.litpic" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             		</tr>
             	</table> 
             	<@s.fielderror><@s.param>news.litpic</@s.param></@s.fielderror>
   </td>
  </tr>
  <tr>
    <td valign="middle" class="left_td">所属分类<span class="mustfield">*</span></td>
    <td colspan="3">
    
    
   <#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="news.cat_attr" />
	                 <a href="/member_News_cate.action?cat_attr=0">[重新选择]</a>
	            <#else>
		            <table>
	             		<tr>
	             			<td class="tdbottom">
	             				<div id="divlist"></div>
	             			</td>
	             			<td class="tdbottom">
	             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
		              		</td>
		              	</tr>
		            </table>       
	            </#if> 
	            
    </td>
  </tr>
   <#if sysmodule.is_catattr=="0">      
           		<#include "/WEB-INF/template/manager/attr.ftl" />    
           </#if> 
  <tr>
    <td valign="middle" class="left_td">tag标签:</td>
    <td colspan="3">
   <@s.textfield name="news.tag" cssStyle="width:300px;" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>news.tag</@s.param></@s.fielderror>
             	(若有多个标签，请使用逗号‘,’隔开！)
   </td>
  </tr>
     <tr>
    <td valign="middle" class="left_td" >内容摘要<span class="mustfield">*</span></td>
    <td colspan="3">
    <@s.textarea name="news.description" cssClass="txtinput"  onkeyup="set_textarea_length(this,200);"  cssStyle="width:500px;height:100px;" />
             	<@s.fielderror><@s.param>news.description</@s.param></@s.fielderror>
    </td>
  </tr>
    <tr>
    <td valign="middle" class="left_td">资讯内容<span class="mustfield">*</span></td>
    <td  colspan="3">
      <@s.textarea name="news.content" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
             	<@s.fielderror><@s.param>news.content</@s.param></@s.fielderror>
    </td>  
  </tr>
  
  <tr>
    <td colspan="4" class="subbut">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="news.info_state" value="1"/>
          <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" onclick="dosubmit();" class="sub">
           <#else>
           		<@s.submit value="保存" cssClass="sub"/>
           </#if>
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	 		<input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_News_list.action','');"/>
    </td>
  </tr>
</table>

 </div>
 </@s.form>
</div>
<div class="clear"></div>
<!--cont结束-->
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
</body>
</html>
