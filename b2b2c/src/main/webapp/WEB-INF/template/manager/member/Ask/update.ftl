<html>
<head>
<title>修改提问</title>
    <script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属分类的回选
        <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"know");  
		 </#if>     
	  });	   
	</script>
	<#include "/include/uploadInc.html">
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Ask_update.action" method="post" validate="true">
     <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:信息管理>知道管理>提问管理>修改提问</td>
      </tr>
    </table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
         <#if (ask.info_state)?if_exists=='2'>
          <tr>
             <td valign="middle" class="left_td" style="font-weight:bold;">未通过理由:</td>
             <td>
             	${ask.no_reason?if_exists}
             </td>
          </tr>
         </#if>
         <tr>
             <td width="19%" valign="middle" class="left_td">问题标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="ask.title" size="70" maxLength="500"/>
             	<@s.fielderror><@s.param>ask.title</@s.param></@s.fielderror>
             </td>
          </tr>
          <tr>
             <td class="table_name">所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="buy.cat_attr" />
              		<a href="/member_Ask_cate.action?cat_attr=0&ask.ask_id=${ask.ask_id?if_exists}">[重新选择]</a>
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
             <td valign="middle" class="left_td">标题图片:</td>
             <td>
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="ask.img_path" id="uploadresult" cssStyle="width:300px;" value="${ask.img_path?if_exists}"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>ask.img_path</@s.param></@s.fielderror> 
             			</td>
             		</tr>
             	</table> 
             
             </td>
           </tr>
           <tr>
             <td valign="middle" class="left_td">问题说明:</td>
             <td>
                <@s.textarea name="ask.ask_desc" id="ask_desc" cssClass="txtinput" cssStyle="width:435px;height:90px;" />
                <@s.fielderror><@s.param>ask.ask_desc</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
             <td valign="middle" class="left_td">补充信息:</td>
             <td>
             	<@s.textfield name="ask.add_desc" cssClass="txtinput" cssStyle="width:500px;"  maxLength="300"/>
             	<@s.fielderror><@s.param>ask.add_desc</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
             <td valign="middle" class="left_td">悬赏积分:</td>
             <td>
             	<@s.select name="ask.integral" list="integralList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>ask.integral</@s.param></@s.fielderror>
             </td>
            </tr>      
	    <tr>
	    <td colspan="4" class="subbut">
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="ask.ask_id" />
	       <@s.hidden name="ask.is_recom" />
	       <@s.hidden name="ask.clicknum"/>
	       <@s.hidden name="ask.fare"/>
	       <@s.hidden name="ask.answer_state"/>
	       ${listSearchHiddenField?if_exists}
	       <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存" class='sub' onclick="dosubmit();">
           <#else>
           		<@s.submit cssClass='sub' value="保存"/>
           </#if>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->

		 <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Ask_list.action','');"/>
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


