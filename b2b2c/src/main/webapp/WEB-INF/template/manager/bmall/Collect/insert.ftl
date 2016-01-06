<html>
<head>
<title>添加我的收藏</title>
<script src="/include/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/js/getcatarea.js"></script> 
   <script>
    function geturlinfo()
    {
       var page_url = window.location.href;
       document.getElementById("urlid").value=page_url;
    }
    </script>
</head>
<body>
	<div class="postion">
  		 <a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的收藏</a><span>></span><a href="#">添加我的收藏</a>
    </div>
<@s.form action="/bmall_Collect_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的收藏</h2></div>
     <div class="base_infor">
       <h2 class="base_title">添加我的收藏</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd">收藏标题<font color='red'>*</font></td><td>
        	<@s.textfield name="collect.title" cssClass="txtinput"/>
        	<@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">链接地址<font color='red'>*</font></td><td>
        	<@s.textfield name="collect.link_url" cssClass="txtinput" />
        	<@s.fielderror><@s.param>collect.link_url</@s.param></@s.fielderror>
            </td></tr>  
            
             <tr><td  class="firsttd">收藏类型:</td><td>
        	<@s.radio name="collect.cat_id" list=r"#{'0':'商品','1':'店铺'}" value="0" checked="true" />  
        	<@s.fielderror><@s.param>collect.cat_id</@s.param></@s.fielderror>
            </td></tr> 
            
             <tr><td  class="firsttd">备注:</td><td>
        	<@s.textarea name="collect.remark" cssClass="txtinput"/>
        	<@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
             	<@s.submit value="提  交" cssClass="submitbut"/>
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Collect_list.action','');"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

