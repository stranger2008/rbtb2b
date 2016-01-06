<html>
<head>
<title>会员添加求购</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="/include/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
<#include "/include/uploadInc.html">
 	<script type="text/javascript">
	  $(document).ready(function(){ 
	     //所属分类的回选
         <#if sysmodule.is_catattr=="1">
			cate_select(${cfg_topcatid?if_exists},1,"buy");  
		 </#if>		          
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");
	  });
	</script>
</head>
<body>
 <div class="cont_main">
 <@s.form action="/member_Buy_insert.action" method="post" validate="true">
 <table width="100%" class="cont_title">
 <tr>
    <td>当前位置:信息管理>商情管理>添加求购</td>
 </tr>
</table>
  <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">
 		 <tr>
             <td class="left_td" width=120px;>求购标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="buy.title" cssClass="txtinput"  maxlength="600" size="70"/>
             	<@s.fielderror><@s.param>buy.title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="left_td" >求购类型:</td>
             <td>
                <@s.radio name="buy.buy_type" list="commparalist" listKey='para_value'  listValue='para_key' value="1" check="true" />    	            
                <@s.fielderror><@s.param>buy.buy_type</@s.param></@s.fielderror>
             </td>
           </tr>
		<tr>
             <td class="left_td" >所属分类<font color='red'>*</font></td>
             <td>
             	<#if sysmodule.is_catattr=="0">             
	                 ${cate_name?if_exists}
	                 <@s.hidden name="cate_name" />
	                 <@s.hidden name="buy.cat_attr" />
	                 <a href="/member_Buy_cate.action?cat_attr=0">[重新选择]</a>
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
          <tr style="display:none;">
             <td class="left_td" >所在地区<font color="red">*</font></td>
             <td>
                 <div id="arealist"></div>
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>             	           	            
             </td>
           </tr>
          <tr>
            <td class="left_td" >产品图片:</td>
            <td>
            
             <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="buy.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			  <script>uploadImgControlAndYin(123);uploadOneImgMulti();</script>
             			</td>
             		</tr>
             	</table> 
            
              <@s.fielderror><@s.param>buy.img_path</@s.param></@s.fielderror>
	        </td>
	      </tr>   
	      <tr>
             <td class="left_td" >详细说明:</td>
             <td >
             	<@s.textarea  name="buy.content" id="content" cssClass="txtinput" />
             	<script type="text/javascript"  src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>         	            
				 <@s.fielderror><@s.param>buy.content</@s.param></@s.fielderror>
             </td>
           </tr>
          <tr>
             <td class="left_td">需求数量:</td>
             <td>
             	<@s.textfield  name="buy.buy_num" cssClass="txtinput" maxlength="50"/>
      	         <@s.fielderror><@s.param>buy.buy_num</@s.param></@s.fielderror>    
             </td>
           </tr>
           <tr>
             <td class="left_td">价格要求:</td>
             <td>
             	<@s.textfield  name="buy.price" cssClass="txtinput" maxlength="50"/>
          	    <@s.fielderror><@s.param>buy.price</@s.param></@s.fielderror>            
             </td>
           </tr>
           <tr>
             <td class="left_td" >规格要求:</td>
             <td>
             	<@s.textfield  name="buy.standard" cssClass="txtinput" maxlength="50"/>
           	     <@s.fielderror><@s.param>buy.standard</@s.param></@s.fielderror>           
             </td>
           </tr>
          <tr>
             <td class="left_td" >包装要求:</td>
             <td>
             	<@s.textfield  name="buy.pack" cssClass="txtinput" maxlength="50"/>
           	     <@s.fielderror><@s.param>buy.pack</@s.param></@s.fielderror>          
             </td>
           </tr>

           <tr>
             <td class="left_td" >过期时间<font color='red'>*</font></td>
             <td>
             	<@s.textfield cssClass="Wdate"  name="buy.end_date" onfocus="WdatePicker({readOnly:true})" />
             	<@s.fielderror><@s.param>buy.end_date</@s.param></@s.fielderror>        
             </td>
           </tr>
            <tr style="display:none;">
             <td class="left_td" >是否推荐:</td>
             <td>
         	 <@s.radio name="buy.is_recom" list=r"#{'0':'否','1':'是'}" value="0" checked="true" />  
         	  <@s.fielderror><@s.param>buy.is_recom</@s.param></@s.fielderror>       
             </td>
           </tr>
           <tr  style="display:none;">
             <td class="left_td">点击量<font color='red'>*</font></td>
             <td>
             	<@s.textfield  name="buy.clicknum" cssClass="txtinput" maxlength="11" value="0"/>
                <@s.fielderror><@s.param>buy.clicknum</@s.param></@s.fielderror>          	            
             </td>
           </tr>
           <tr  style="display:none;">
             <td class="left_td" >内容收费:</td>
             <td>
             	<@s.textfield  name="buy.fare" cssClass="txtinput" maxlength="11" value="0"/>(只能输入数字,默认"0"则免费!)
             	<@s.fielderror><@s.param>buy.fare</@s.param></@s.fielderror>          	            
             </td>
           </tr>           
  <tr>
    <td colspan="4" class="subbut">
     <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="buy.info_state" value="0"/>
         	 <#if sysmodule.is_catattr=="0">      
          		  <@s.hidden id="cat_attr" name="cat_attr"/>
           		<input type="button" value="保存"  class='sub'  onclick="dosubmit();">
           <#else>
           		 <@s.submit value="保存" cssClass="sub"/>
           </#if>
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->

	 <input type="button" name="returnList" class="sub" value="返回列表" 
	  onclick="linkToInfo('/member_Buy_list.action','');"/>
    </td>
  </tr>
</table>
 </div>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
 </@s.form>
</div>
<div class="clear"></div>

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
<!--cont结束-->
</body>
</html>
