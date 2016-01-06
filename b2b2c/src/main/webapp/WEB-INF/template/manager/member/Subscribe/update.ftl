<html>
  <head>
    <title>会员修改订阅</title>
    <script type="text/javascript" src="/include/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){    
	     var value=$("#info_type").val();
	     if(value=='0'){
	         cate_select(${cfg_topcatid?if_exists},1,"supply");  
         }
         if(value=="1"){
	         cate_select(${cfg_topcatid?if_exists},1,"buy");  
         }             
         //所属地区的回选
         area_select("${cfg_topareaid?if_exists}");         
          $("#info_type").change(function(){//事件触发  
			   $('option:selected', this).each(function(){
			   $("#divlist").html("");
                   var value=this.value;
                   if(value=="0"){
                      cate_select(${cfg_topcatid?if_exists},1,"supply"); 
                   }
                   if(value=="1"){
                      cate_select(${cfg_topcatid?if_exists},1,"buy");
                   }
			   });  	
		  });    
         
	  });
	//验证数字 > 0
	function checkZeroNum(obj)
	{
	    var num_value=$(obj).val();
	    var re =/^(([1-9]\d*))$/;
		if (!re.test(obj.value))
		  {
		     if(isNaN(obj.value)){
		        obj.value="1";
		        obj.focus();
		        jNotify("频率天数不能小于1"); 
		        return false;
		     }
		  }
	}
	</script> 
  </head>
<body>
 <div class="cont_main">
   	<@s.form action="/member_Subscribe_update.action" method="post" validate="true">
   	       <table width="100%" class="cont_title">
      <tr>
       <td>当前位置:商机管理>商机快递>修改订阅</td>
      </tr>
    </table>
        <table width="100%" border="0" bgcolor="#DDDDDD" cellspacing="1" class="main_cont">           
             <tr>
             <td width="17%" valign="middle" class="left_td">信息类型：</td>
             <td>
             	<@s.select id="info_type" name="subscribe.info_type" list=r"#{'0':'供应','1':'求购'}"/>  
             	<@s.fielderror><@s.param>subscribe.info_type</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td valign="middle" class="left_td">关键字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="subscribe.keyword" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>subscribe.keyword</@s.param></@s.fielderror>
             </td>
           </tr>
           
          <tr>
             <td  valign="middle" class="left_td">所属分类<font color='red'>*</font></td>
             <td>
             	 <div id="divlist"></div>  
                <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror> 
             </td>
           </tr>        
            <tr>
             <td  valign="middle" class="left_td" >所在地区:<font color="red">*</font></td>
             <td>
                 <div id="arealist"></div>
                 <span id="span_area" class="error_msg"/>     
                  <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>         	           	            
             </td>
           </tr>
          <tr>
           
           <tr>
             <td width="19%" valign="middle" class="left_td">频率天数<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="subscribe.period" cssClass="txtinput" maxLength="4" onkeyup="checkZeroNum(this)"/>
             	<@s.fielderror><@s.param>subscribe.period</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td valign="middle" class="left_td">发送类型：</td>
             <td>
             	<@s.select name="subscribe.send_type" list=r"#{'0':'邮箱','1':'站内信'}"/>  
             	<@s.fielderror><@s.param>subscribe.send_type</@s.param></@s.fielderror>	
             </td>
           </tr>
             <tr>
             <td valign="middle" class="left_td">是否有效：</td>
             <td>
             	<@s.select name="subscribe.enabled" list=r"#{'0':'有效','1':'回收站'}"/>  
             	<@s.fielderror><@s.param>subscribe.enabled</@s.param></@s.fielderror>	
             </td>
           </tr>
          <tr>
		    <td colspan="4" class="subbut">
			       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	               <@s.hidden name="subscribe.sub_id"/>
			       
			       ${listSearchHiddenField?if_exists}
			       <@s.submit value="保存" cssClass="sub"/>
			      <!--所属分类插件隐藏字段开始区域-->
				   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
				   <!--所属分类插件隐藏字段结束区域-->
				   <!--所属地区插件隐藏字段开始区域-->
				   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
				   <!--所属地区插件隐藏字段结束区域-->
				    <input type="button" name="returnList" class="sub" value="返回列表" onclick="linkToInfo('/member_Subscribe_list.action','');"/>
		    </td>
		  </tr>
         </table>
	  </@s.form>
   </div>
</div>
<div class="clear"></div>
</body>
</html>