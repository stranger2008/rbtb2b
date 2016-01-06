<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
<head>
<title>修改商品分类</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<#include "/include/uploadInc.html">
</head>
<body>
	<div class="postion">
  		  <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">修改商品分类</a>
    </div>
<@s.form action="/bmall_Membercat_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>商品分类管理</h2></div>
     <div class="base_infor">
       <h2 class="base_title">修改商品分类</h2>
       <div class="table_infor f_left">
           <table style="width:750px;" >
				
				
		    <tr><td  class="firsttd">分类名称<font color='red'>*</font></td><td>
        	<@s.textfield name="membercat.cat_name" cssClass="txtinput"   cssStyle="width:300px;"   />
        	<@s.fielderror><@s.param>membercat.cat_name</@s.param></@s.fielderror>
            </td></tr>

			  <tr><td  class="firsttd">分类图片:</td><td>
        	 <table border="0" cellpadding="0" cellspacing="0" >
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			<@s.textfield name="membercat.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             		</tr>
             	</table> 
        	<@s.fielderror><@s.param>membercat.img_path</@s.param></@s.fielderror>
            </td></tr>  
            
             <tr><td  class="firsttd">分类级别:</td><td>
             ${membercat.cat_level!1}级
            </td></tr> 
            
          
               <tr><td  class="firsttd">是否推荐:</td><td>
           <@s.radio name="membercat.is_recom" list=r"#{'0':'否','1':'是'}"  />  
        	<@s.fielderror><@s.param>membercat.is_recom</@s.param></@s.fielderror>
            </td></tr> 
            
             <tr><td  class="firsttd">排序:</td><td>
        	<@s.textfield name="membercat.sort_no" cssClass="txtinput"  cssStyle="width:80px;"    onkeyup="checkNum(this);"/>*(只能输入数字)
        	<@s.fielderror><@s.param>membercat.sort_no</@s.param></@s.fielderror>
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Membercat_list.action','membertype=3');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>

<@s.hidden name="membercat.cat_type"  />
<@s.hidden name="membertype"  value="3"   />
<@s.hidden name="membercat.up_cat_id" value="${(membercat.up_cat_id)!'1111111111'}" />
	       <@s.hidden name="membercat.cat_id" />
	       <@s.hidden name="membercat.cat_level" value="${membercat.cat_level!1}" />
 </@s.form>
 
 
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

